package com.quanta.sino.cmn.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.dao.api.IInformDAO;
import com.quanta.sino.orm.InformTp;

public class InformDAO implements IInformDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(InformTp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(InformTp.class, id);
	}

	@Override
	public void query(QEntity<InformTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public InformTp get(Serializable id) {
		return dao.get(InformTp.class, id);
	}

	@Override
	public InformTp getByName(String name) {
		return (InformTp) dao.getUnique("from InformTp where name=?", name);
	}

	@Override
	public void update(InformTp entity) {
		dao.update(entity);
	}

	@Override
	public boolean isExisted(String name) {
		String ql = "from InformTp where name=?";
		return dao.isExisted(ql, name);
	}

	@Override
	public void deletes(List<String> ids) {
		dao.executeForValues("delete from InformTp where id in (?)", ids);
	}
}
