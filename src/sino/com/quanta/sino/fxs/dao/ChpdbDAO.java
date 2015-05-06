package com.quanta.sino.fxs.dao;

import java.io.Serializable;
import java.util.Arrays;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.dao.api.IChpdbDAO;
import com.quanta.sino.orm.Chpdb;

/**
 * <p>
 * 高耐蚀性马口铁出荷判定表(#75以上)数据访问实现类
 * </p>
 * <p>
 * create: 2011-4-17 下午05:37:57
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChpdbDAO implements IChpdbDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Chpdb entity) {
		dao.save(entity);
	}

	@Override
	public void update(Chpdb entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Chpdb.class, id);
	}

	@Override
	public void query(QEntity<Chpdb> qentity) {
		dao.query(qentity);
	}

	@Override
	public Chpdb get(Serializable id) {
		return dao.get(Chpdb.class, id);
	}

	@Override
	public boolean isExisted(Serializable id) {
		return dao.isExisted("from Chpdb where jbno=?", id);
	}

	@Override
	public void deletes(String[] ids) {
		dao.executeForValues("delete from Chpdb where jbno in (?)", Arrays.asList(ids));
	}

}
