package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository implements JDBCRepository<Cliente> {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT id_cliente, nome, email, telefone FROM VS_15_EQUIPE_2.CLIENTE";

        return jdbcTemplate.query(sql, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                return cliente;
            }
        });
    }

    @Override
    public Cliente insert(Cliente entity) {
        entity.setIdCliente(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.CLIENTE (id_cliente, nome, email, telefone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdCliente(), entity.getNome(), entity.getEmail(), entity.getTelefone());
        return entity;
    }

    @Override
    public Cliente update(Integer idEntity, Cliente entity) {
        entity.setIdCliente(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.CLIENTE SET nome = ?, email = ?, telefone = ? WHERE id_cliente = ?";
        jdbcTemplate.update(sql, entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getIdCliente());
        return entity;
    }

    @Override
    public int delete(Cliente entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.CLIENTE WHERE id_cliente = ?";
        return jdbcTemplate.update(sql, entity.getIdCliente());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.CLIENTE WHERE id_cliente = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public Cliente findById(int idEntity) {
        String sql = "SELECT id_cliente, nome, email, telefone FROM VS_15_EQUIPE_2.CLIENTE WHERE id_cliente = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            return cliente;
        }, idEntity);
    }

    @Override
    public List<Cliente> listByName(String name) {
        String sql = "SELECT id_cliente, nome, email, telefone FROM VS_15_EQUIPE_2.CLIENTE WHERE nome LIKE ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            return cliente;
        }, "%" + name + "%");
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.CLIENTE WHERE id_cliente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_CLIENTE.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
