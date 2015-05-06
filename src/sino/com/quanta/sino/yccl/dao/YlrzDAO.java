package com.quanta.sino.yccl.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Ylrz;
import com.quanta.sino.yccl.dao.api.IYlrzDAO;

/**
 * <p>
 * 业连日志
 * </p>
 * <p>
 * create: 2011-2-15 下午04:43:32
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YlrzDAO implements IYlrzDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void saveAll(List<Ylrz> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void save(Ylrz entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<Ylrz> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Ylrz get(Serializable id) {
		return dao.get(Ylrz.class, id);
	}

	@Override
	public void update(Ylrz entity) {
		dao.update(entity);
	}

}
