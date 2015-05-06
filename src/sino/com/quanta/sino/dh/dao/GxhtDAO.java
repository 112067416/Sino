package com.quanta.sino.dh.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.dh.dao.api.IGxhtDAO;
import com.quanta.sino.orm.GxhtTp;

/**
 * <p>
 * 购销合同数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-7-21 下午04:38:00
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class GxhtDAO implements IGxhtDAO {

	private DAO dao;

	@Override
	public void save(GxhtTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<GxhtTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(GxhtTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(GxhtTp.class, id);
	}

	@Override
	public void query(QEntity<GxhtTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public GxhtTp get(Serializable id) {
		return dao.get(GxhtTp.class, id);
	}

	@Override
	public GxhtTp getByUser(String user) {
		String ql = "from GxhtTp where user=? order by qrsj desc";
		return (GxhtTp) dao.getUnique(ql, user);
	}

	@Override
	public boolean isExisted(Serializable id) {
		return dao.isExisted("from GxhtTp where dhno=?", id);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
