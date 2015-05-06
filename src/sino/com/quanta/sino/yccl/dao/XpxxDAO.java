package com.quanta.sino.yccl.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Xpxx;
import com.quanta.sino.yccl.dao.api.IXpxxDAO;

/**
 * <p>
 * 现品信息日志
 * </p>
 * <p>
 * create: 2011-1-18 下午04:12:11
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class XpxxDAO implements IXpxxDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Xpxx entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<Xpxx> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Xpxx get(Serializable id) {
		return dao.get(Xpxx.class, id);
	}

	@Override
	public void update(Xpxx entity) {
		dao.update(entity);
	}

}
