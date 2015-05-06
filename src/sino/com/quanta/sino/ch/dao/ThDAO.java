package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.dao.api.IThDAO;
import com.quanta.sino.orm.ThTp;

public class ThDAO implements IThDAO {

	private DAO dao;

	@Override
	public void update(ThTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(ThTp.class, id);
	}

	@Override
	public void query(QEntity<ThTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public ThTp get(Serializable id) {
		return dao.get(ThTp.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ThTp> find(List<String> ids) {
		return (List<ThTp>) dao.findForValues("from ThTp where id in(?)", ids);
	}

	@Override
	public void updateById(Serializable id, String stat) {
		dao.executeUpdate("update ThTp set zt=? where id=?", stat, id);
	}

	@Override
	public void saveAll(List<ThTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void save(ThTp entity) {
		dao.save(entity);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
