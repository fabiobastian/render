package br.com.dbc.vemser.calcadoapi.repository;

import br.com.dbc.vemser.calcadoapi.entity.FormaPagamento;
import br.com.dbc.vemser.calcadoapi.entity.Pagamento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PagamentoRepository implements JDBCRepository<Pagamento> {

    private final JdbcTemplate jdbcTemplate;

    public PagamentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pagamento> findAll() {
        String sql = "SELECT id_pagamento, id_pedido, valor, forma_pagamento FROM VS_15_EQUIPE_2.PAGAMENTO";

        return jdbcTemplate.query(sql, new RowMapper<Pagamento>() {
            @Override
            public Pagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(rs.getInt("id_pagamento"));
                pagamento.setIdPedido(rs.getInt("id_pedido"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setFormaPagamento(FormaPagamento.fromName(rs.getString("forma_pagamento")));
                return pagamento;
            }
        });
    }

    @Override
    public Pagamento insert(Pagamento entity) {
        entity.setIdPagamento(getNextId());
        String sql = "INSERT INTO VS_15_EQUIPE_2.PAGAMENTO (id_pagamento, id_pedido, valor, forma_pagamento) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIdPagamento(), entity.getIdPedido(), entity.getValor(), entity.getFormaPagamento().name());
        return entity;
    }

    @Override
    public Pagamento update(Integer idEntity, Pagamento entity) {
        entity.setIdPagamento(idEntity);
        String sql = "UPDATE VS_15_EQUIPE_2.PAGAMENTO SET id_pedido = ?, valor = ?, forma_pagamento = ? WHERE id_pagamento = ?";
        jdbcTemplate.update(sql, entity.getIdPedido(), entity.getValor(), entity.getFormaPagamento().name(), entity.getIdPagamento());
        return entity;
    }

    @Override
    public int delete(Pagamento entity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PAGAMENTO WHERE id_pagamento = ?";
        return jdbcTemplate.update(sql, entity.getIdPagamento());
    }

    @Override
    public int deleteById(Integer idEntity) {
        String sql = "DELETE FROM VS_15_EQUIPE_2.PAGAMENTO WHERE id_pagamento = ?";
        return jdbcTemplate.update(sql, idEntity);
    }

    @Override
    public Pagamento findById(int idEntity) {
        String sql = "SELECT id_pagamento, id_pedido, valor, forma_pagamento FROM VS_15_EQUIPE_2.PAGAMENTO WHERE id_pagamento = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Pagamento pagamento = new Pagamento();
            pagamento.setIdPagamento(rs.getInt("id_pagamento"));
            pagamento.setIdPedido(rs.getInt("id_pedido"));
            pagamento.setValor(rs.getDouble("valor"));
            pagamento.setFormaPagamento(FormaPagamento.fromName(rs.getString("forma_pagamento")));
            return pagamento;
        }, idEntity);
    }

    @Override
    public List<Pagamento> listByName(String name) {
        return null;
    }

    @Override
    public boolean existsById(int idEntity) {
        String sql = "SELECT COUNT(1) FROM VS_15_EQUIPE_2.PAGAMENTO WHERE id_pagamento = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idEntity);
        return count != null && count > 0;
    }

    @Override
    public Integer getNextId() {
        String sql = "SELECT VS_15_EQUIPE_2.SEQ_PAGAMENTO.NEXTVAL FROM DUAL";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
