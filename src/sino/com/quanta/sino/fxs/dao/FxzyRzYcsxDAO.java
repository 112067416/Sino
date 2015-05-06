package com.quanta.sino.fxs.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.dao.api.IFxzyRzYcsxDAO;
import com.quanta.sino.orm.FxzyRzYcsx;

public class FxzyRzYcsxDAO implements IFxzyRzYcsxDAO {

	private DAO dao;

	@Override
	public void save(FxzyRzYcsx entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<FxzyRzYcsx> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(FxzyRzYcsx entity) {
		dao.update(entity);
	}

	@Override
	public void updateAll(List<FxzyRzYcsx> entities) {
		for (FxzyRzYcsx ycsx : entities) {
			dao.update(ycsx);
		}
	}

	@Override
	public void query(QEntity<FxzyRzYcsx> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<FxzyRzYcsx> find(String pid) {
		return dao.find(FxzyRzYcsx.class, "pid=?", pid);
	}

	@Override
	public FxzyRzYcsx get(Serializable id) {
		return dao.get(FxzyRzYcsx.class, id);
	}

	@Override
	public void delete(List<String> pids) {
		dao.executeForValues("delete from FxzyRzYcsx where pid in (?)", pids);
	}

	@Override
	public void deleteByPid(String pid) {
		dao.executeUpdate("delete from FxzyRzYcsx where pid=?", pid);
	}

	@Override
	public boolean isFxzyRzYcsx(String pid) {
		return dao.isExisted("from FxzyRzYcsx where pid=?", pid);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
