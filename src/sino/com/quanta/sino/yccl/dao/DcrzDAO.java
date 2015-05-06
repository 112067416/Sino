package com.quanta.sino.yccl.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Dcrz;
import com.quanta.sino.yccl.dao.api.IDcrzDAO;

/**
 * <p>
 * 堆场日志
 * </p>
 * <p>
 * create: 2011-1-25 上午11:55:05
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class DcrzDAO implements IDcrzDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Dcrz entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<Dcrz> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Dcrz get(Serializable id) {
		return dao.get(Dcrz.class, id);
	}

	@Override
	public void update(Dcrz entity) {
		dao.update(entity);
	}

}
