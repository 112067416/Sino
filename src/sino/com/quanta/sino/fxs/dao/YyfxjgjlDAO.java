package com.quanta.sino.fxs.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.dao.api.IYyfxjgjlDAO;
import com.quanta.sino.orm.YyfxjgJl;

/**
 * <p>
 * 药液分析结果记录表
 * </p>
 * <p>
 * create: 2010-12-29 下午03:51:17
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YyfxjgjlDAO implements IYyfxjgjlDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(YyfxjgJl entity) {
		dao.save(entity);
	}

	@Override
	public void update(YyfxjgJl entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(YyfxjgJl.class, id);
	}

	@Override
	public void query(QEntity<YyfxjgJl> qentity) {
		dao.query(qentity);
	}

	@Override
	public YyfxjgJl get(Serializable id) {
		return dao.get(YyfxjgJl.class, id);
	}

}
