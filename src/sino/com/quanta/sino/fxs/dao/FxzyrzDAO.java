package com.quanta.sino.fxs.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.dao.api.IFxzyrzDAO;
import com.quanta.sino.orm.FxzyRz;

/**
 * <p>
 * 分析作业日志表
 * </p>
 * <p>
 * create: 2010-12-29 下午03:51:35
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FxzyrzDAO implements IFxzyrzDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(FxzyRz entity) {
		dao.save(entity);
	}

	@Override
	public void update(FxzyRz entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<FxzyRz> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<FxzyRz> find(List<String> ids) {
		return dao.find(FxzyRz.class, "id in (?)", ids);
	}

	@Override
	public FxzyRz get(Serializable id) {
		return dao.get(FxzyRz.class, id);
	}

	@Override
	public void delete(List<String> ids) {
		dao.executeForValues("delete from FxzyRz where id in(?)", ids);
	}

}
