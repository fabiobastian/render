package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.Pedido;
import br.com.dbc.vemser.calcadoapi.entity.StatusPedido;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoRepository implements JDBCRepository<Pedido> {

    private final JdbcTemplate jdbcTemplate;

    public PedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pedido> findAll() {
        String sql = "SELECT id_pedido, id_cliente, data, status_pedido FROM VS_15_EQUIPE_2.PEDIDO";
        return jdbcTemplate.query(sql, new RowMapper<Pedido>() {
            @Override
            public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setData(rs.getDate("data").toLocalDate());
                pedido.setStatusPedido(StatusPedido.fromName(rs.getString("status_pedido")));
                return pedido;
            }
        });
    }

    @Override
    public Pedido insert(Pedido entity) {
        entity.setIdPedido(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.PEDIDO (id_pedido, id_cliente, data, status_pedido) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdPedido(), entity.getIdCliente(), entity.getData(), entity.getStatusPedido().name());
        return entity;
    }

    @Override
    public Pedido update(Integer idEntity, Pedido entity) {
        entity.setIdPedido(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.PEDIDO SET id_cliente = ?, status_pedido = ? WHERE id_pedido = ?";
        jdbcTemplate.update(sql, entity.getIdCliente(), entity.getStatusPedido().name(), entity.getIdPedido());
        return entity;
    }

    @Override
    public int delete(Pedido entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PEDIDO WHERE id_pedido = ?";
        return jdbcTemplate.update(sql, entity.getIdPedido());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PEDIDO WHERE id_pedido = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public Pedido findById(int idEntity) {
        String sql = "SELECT id_pedido, id_cliente, data, status_pedido FROM VS_15_EQUIPE_2.PEDIDO WHERE id_pedido = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("id_pedido"));
            pedido.setIdCliente(rs.getInt("id_cliente"));
            pedido.setData(rs.getDate("data").toLocalDate());
            pedido.setStatusPedido(StatusPedido.fromName(rs.getString("status_pedido")));
            return pedido;
        }, idEntity);
    }

    @Override
    public List<Pedido> listByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.PEDIDO WHERE id_pedido = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    public Double getValorTotalById(int idEntity) {
        String sql = """
                SELECT SUM(ip.quantidade * pr.preco) AS total FROM VS_15_EQUIPE_2.PEDIDO pe
                INNER JOIN VS_15_EQUIPE_2.ITEM_PEDIDO ip ON pe.ID_PEDIDO = ip.ID_PEDIDO
                INNER JOIN VS_15_EQUIPE_2.PRODUTO pr ON ip.ID_PRODUTO = pr.ID_PRODUTO
                WHERE pe.ID_PEDIDO = ? GROUP BY pe.ID_PEDIDO
                """;

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getDouble("total"), idEntity);
        } catch (EmptyResultDataAccessException e) {
            return 0.0;
        }
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_PEDIDO.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}