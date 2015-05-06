package com.quanta.sino.cg.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cg.dao.api.ICgdjDAO;
import com.quanta.sino.orm.YbCgdj;

/**
 * <p>
 * 原板采购单价数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-7-8 下午03:55:53
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CgdjDAO implements ICgdjDAO {

	private DAO dao;

	@Override
	public void save(YbCgdj entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<YbCgdj> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(YbCgdj entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(YbCgdj.class, id);
	}

	@Override
	public void query(QEntity<YbCgdj> qentity) {
		dao.query(qentity);
	}

	@Override
	public List<YbCgdj> find() {
		return dao.findAll(YbCgdj.class);
	}

	@Override
	public YbCgdj get(Serializable id) {
		return dao.get(YbCgdj.class, id);
	}

	@Override
	public YbCgdj get() {
		return (YbCgdj) dao.getUnique("from YbCgdj where base is not null and base > 0");
	}

	@Override
	public Double getCgdj(Double houu, String knfw, String yuny) {
		String ql = "select cgdj from YbCgdj where base is not null and base > 0 and xpho=? and knfw=? and yuny=?";
		Object obj = dao.getUnique(ql, houu, knfw, yuny);
		if (obj == null) {
			return 0d;
		}
		return (Double) obj;
	}

	@Override
	public boolean isExisted(Double houu, String knfw, String yuny) {
		String ql = "from YbCgdj where xpho=? and knfw=? and yuny=?";
		return dao.isExisted(ql, houu, knfw, yuny);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
