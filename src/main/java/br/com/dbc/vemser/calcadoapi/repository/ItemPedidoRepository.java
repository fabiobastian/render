package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.ItemPedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemPedidoRepository implements JDBCRepository<ItemPedido> {

    private final JdbcTemplate jdbcTemplate;

    public ItemPedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemPedido> findAll() {
        String sql = "SELECT id_item_pedido, id_pedido, id_produto, quantidade FROM VS_15_EQUIPE_2.ITEM_PEDIDO";

        return jdbcTemplate.query(sql, new RowMapper<ItemPedido>() {
            @Override
            public ItemPedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setIdItemPedido(rs.getInt("id_item_pedido"));
                itemPedido.setIdPedido(rs.getInt("id_pedido"));
                itemPedido.setIdProduto(rs.getInt("id_produto"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                return itemPedido;
            }
        });
    }

    public List<ItemPedido> findAllByPedidoId(Integer idPedido) {
        String sql = "SELECT id_item_pedido, id_pedido, id_produto, quantidade FROM VS_15_EQUIPE_2.ITEM_PEDIDO WHERE id_pedido = ?";

        return jdbcTemplate.query(sql, new RowMapper<ItemPedido>() {
            @Override
            public ItemPedido mapRow(ResultSet rs, int rowNum) throws SQLException {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setIdItemPedido(rs.getInt("id_item_pedido"));
                itemPedido.setIdPedido(rs.getInt("id_pedido"));
                itemPedido.setIdProduto(rs.getInt("id_produto"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                return itemPedido;
            }
        }, idPedido);
    }

    @Override
    public ItemPedido insert(ItemPedido entity) {
        entity.setIdItemPedido(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.ITEM_PEDIDO (id_item_pedido, id_pedido, id_produto, quantidade) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdItemPedido(), entity.getIdPedido(), entity.getIdProduto(), entity.getQuantidade());
        return entity;
    }

    @Override
    public ItemPedido update(Integer idEntity, ItemPedido entity) {
        entity.setIdItemPedido(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.ITEM_PEDIDO SET id_pedido = ?, id_produto = ?, quantidade = ? WHERE id_item_pedido = ?";
        jdbcTemplate.update(sql, entity.getIdPedido(), entity.getIdProduto(), entity.getQuantidade(), entity.getIdItemPedido());
        return entity;
    }

    @Override
    public int delete(ItemPedido entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.ITEM_PEDIDO WHERE id_item_pedido = ?";
        return jdbcTemplate.update(sql, entity.getIdItemPedido());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.ITEM_PEDIDO WHERE id_item_pedido = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public ItemPedido findById(int idEntity) {
        String sql = "SELECT id_item_pedido, id_pedido, id_produto, quantidade FROM VS_15_EQUIPE_2.ITEM_PEDIDO WHERE id_item_pedido = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setIdItemPedido(rs.getInt("id_item_pedido"));
            itemPedido.setIdPedido(rs.getInt("id_pedido"));
            itemPedido.setIdProduto(rs.getInt("id_produto"));
            itemPedido.setQuantidade(rs.getInt("quantidade"));
            return itemPedido;
        }, idEntity);
    }

    @Override
    public List<ItemPedido> listByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.ITEM_PEDIDO WHERE id_item_pedido = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_ITEM_PEDIDO.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
