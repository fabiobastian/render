package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.Endereco;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EnderecoRepository implements JDBCRepository<Endereco> {

    private final JdbcTemplate jdbcTemplate;

    public EnderecoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Endereco> findAll() {
        String sql = "SELECT id_endereco, id_cliente, logradouro, numero, bairro, cidade, cep FROM VS_15_EQUIPE_2.ENDERECO";

        return jdbcTemplate.query(sql, new RowMapper<Endereco>() {
            @Override
            public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setIdCliente(rs.getInt("id_cliente"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                return endereco;
            }
        });
    }

    @Override
    public Endereco insert(Endereco entity) {
        entity.setIdEndereco(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.ENDERECO (id_endereco, id_cliente, logradouro, numero, bairro, cidade, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdEndereco(), entity.getIdCliente(), entity.getLogradouro(), entity.getNumero(), entity.getBairro(), entity.getCidade(), entity.getCep());
        return entity;
    }

    @Override
    public Endereco update(Integer idEntity, Endereco entity) {
        entity.setIdEndereco(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.ENDERECO SET id_cliente = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, cep = ? WHERE id_endereco = ?";
        jdbcTemplate.update(sql, entity.getIdCliente(), entity.getLogradouro(), entity.getNumero(), entity.getBairro(), entity.getCidade(), entity.getCep(), entity.getIdEndereco());
        return entity;
    }

    @Override
    public int delete(Endereco entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.ENDERECO WHERE id_endereco = ?";
        return jdbcTemplate.update(sql, entity.getIdCliente());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.ENDERECO WHERE id_endereco = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public Endereco findById(int idEntity) {
        String sql = "SELECT id_endereco, id_cliente, logradouro, numero, bairro, cidade, cep FROM VS_15_EQUIPE_2.ENDERECO WHERE id_endereco = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(rs.getInt("id_endereco"));
            endereco.setIdCliente(rs.getInt("id_cliente"));
            endereco.setLogradouro(rs.getString("logradouro"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setCep(rs.getString("cep"));
            return endereco;
        }, idEntity);
    }

    @Override
    public List<Endereco> listByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.ENDERECO WHERE id_endereco = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_ENDERECO.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
