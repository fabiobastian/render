package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProdutoRepository implements JDBCRepository<Produto> {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Produto> findAll() {
        String sql = "SELECT id_produto, nome, descricao, preco, tamanho, cor, estoque FROM VS_15_EQUIPE_2.PRODUTO";

        return jdbcTemplate.query(sql, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTamanho(rs.getDouble("tamanho"));
                produto.setCor(rs.getString("cor"));
                produto.setEstoque(rs.getInt("estoque"));
                return produto;
            }
        });
    }

    @Override
    public Produto insert(Produto entity) {
        entity.setIdProduto(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.PRODUTO (id_produto, nome, descricao, preco, tamanho, cor, estoque) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdProduto(), entity.getNome(), entity.getDescricao(), entity.getPreco(), entity.getTamanho(), entity.getCor(), entity.getEstoque());
        return entity;
    }

    @Override
    public Produto update(Integer idEntity, Produto entity) {
        entity.setIdProduto(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.PRODUTO SET nome = ?, descricao = ?, preco = ?, tamanho = ?, cor = ?, estoque = ? WHERE id_produto = ?";
        jdbcTemplate.update(sql, entity.getNome(), entity.getDescricao(), entity.getPreco(), entity.getTamanho(), entity.getCor(), entity.getEstoque(), entity.getIdProduto());
        return entity;
    }

    @Override
    public int delete(Produto entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PRODUTO WHERE id_produto = ?";
        return jdbcTemplate.update(sql, entity.getIdProduto());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PRODUTO WHERE id_produto = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public Produto findById(int idEntity) {
        String sql = "SELECT id_produto, nome, descricao, preco, tamanho, cor, estoque FROM VS_15_EQUIPE_2.PRODUTO WHERE id_produto = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setTamanho(rs.getDouble("tamanho"));
            produto.setCor(rs.getString("cor"));
            produto.setEstoque(rs.getInt("estoque"));
            return produto;
        }, idEntity);
    }

    @Override
    public List<Produto> listByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.PRODUTO WHERE id_produto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_PRODUTO.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
