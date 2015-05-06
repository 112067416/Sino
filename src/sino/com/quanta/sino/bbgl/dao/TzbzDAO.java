package com.quanta.sino.bbgl.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.bbgl.dao.api.ITzbzDAO;
import com.quanta.sino.orm.Tzbz;

/**
 * <p>
 * 台帐备注数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-8-31 下午02:22:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class TzbzDAO implements ITzbzDAO {

	private DAO dao;

	@Override
	public void save(Tzbz entity) {
		dao.save(entity);
	}

	@Override
	public void update(Tzbz entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Tzbz.class, id);
	}

	@Override
	public void query(QEntity<Tzbz> qentity) {
		dao.query(qentity);
	}

	@Override
	public Tzbz get(Serializable id) {
		return dao.get(Tzbz.class, id);
	}

	@Override
	public Tzbz get(String xpzk, String spbh, String ny, String bbm) {
		if (spbh == null || spbh.isEmpty()) {
			return (Tzbz) dao.getUnique("from Tzbz where xpzk=? and ny=? and bbm=?", xpzk, ny, bbm);
		}
		return (Tzbz) dao.getUnique("from Tzbz where xpzk=? and spbh=? and ny=? and bbm=?", xpzk, spbh, ny, bbm);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
