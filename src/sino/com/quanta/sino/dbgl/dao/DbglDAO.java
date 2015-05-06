package com.quanta.sino.dbgl.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dbgl.dao.api.IDbglDAO;
import com.quanta.sino.orm.DbglLp;
import com.quanta.sino.orm.DbglTp;

public class DbglDAO implements IDbglDAO {
	private DAO dao;

	@Override
	public void save(DbglTp entity) {
		dao.save(entity);
	}

	@Override
	public void save(DbglLp entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<DbglTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public DbglTp get(Serializable id) {
		return dao.get(DbglTp.class, id);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(DbglTp.class, id);
	}

	@Override
	public void update(DbglTp entity) {
		dao.update(entity);
	}

	@Override
	public void updateZt(List<String> jbnos, String zt) {
		dao.executeForValues("update DbglTp set zt=? where jbno in (?)", jbnos, zt);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
