package com.quanta.sino.yccl.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Jlbg;
import com.quanta.sino.yccl.dao.api.IJlbgDAO;

/**
 * <p>
 * 记录变更日志
 * </p>
 * <p>
 * create: 2011-2-9 上午11:50:52
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class JlbgDAO implements IJlbgDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Jlbg entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<Jlbg> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void query(QEntity<Jlbg> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Jlbg get(Serializable id) {
		return dao.get(Jlbg.class, id);
	}

	@Override
	public void update(Jlbg entity) {
		dao.update(entity);
	}

}
