package br.com.dbc.vemser.calcadoapi.repository;

import java.util.List;

public interface JDBCRepository<OBJETO> {
    public List<OBJETO> findAll();

    public OBJETO insert(OBJETO entity);

    public OBJETO update(Integer idEntity, OBJETO entity);

    public int delete(OBJETO entity);

    public int deleteById(Integer idEntity);

    public OBJETO findById(int idEntity);

    public List<OBJETO> listByName(String name);

    public boolean existsById(int idEntity);

    public Integer getNextId();
}
