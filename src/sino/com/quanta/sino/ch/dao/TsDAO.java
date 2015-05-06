package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.ch.dao.api.ITsDAO;
import com.quanta.sino.orm.TsTp;

/**
 * <p>
 * 制品投诉数据访问实现类
 * </p>
 * <p>
 * create: 2011-2-24 下午02:20:36
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class TsDAO implements ITsDAO {

	private DAO dao;

	@Override
	public void save(TsTp entity) {
		dao.save(entity);
	}

	@Override
	public void update(TsTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(TsTp.class, id);
	}

	@Override
	public void query(QEntity<TsTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public TsTp get(Serializable id) {
		return dao.get(TsTp.class, id);
	}

	@Override
	public TsTp getTsTp(String jbno, String shno, String stat) {
		return (TsTp) dao.getUnique("from TsTp where jbno=? and shno=? and stat!=?", jbno, shno, stat);
	}

	@Override
	public void updateStat(List<String> ids, String stat) {
		dao.executeForValues("update TsTp set stat=? where id in(?)", ids, stat);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
