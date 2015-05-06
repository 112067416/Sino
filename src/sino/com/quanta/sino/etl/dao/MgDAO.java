package com.quanta.sino.etl.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.etl.dao.api.IMgDAO;
import com.quanta.sino.orm.DmLp;
import com.quanta.sino.orm.Dmgs;

public class MgDAO implements IMgDAO {
	private DAO dao;

	@Override
	public void save(Dmgs entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<Dmgs> qentity) {
		dao.query(qentity);
	}

	@Override
	public Dmgs get(Serializable id) {
		return dao.get(Dmgs.class, id);
	}

	@Override
	public void update(Dmgs entity) {
		dao.update(entity);
	}

	@Override
	public Dmgs getUnique(Double kuan, Double cang, String dmfx, String kbao,
			String kw) {
		if (dmfx == null || dmfx.isEmpty()) {
			return dao.getUnique(Dmgs.class, "kuan=? and cang=?  and kbao=? and kw=? and (dmfx='' or dmfx is null)", kuan, cang, kbao, kw);
		}
		else {
			return dao.getUnique(Dmgs.class, "kuan=? and cang=? and dmfx=? and kbao=? and kw=?", kuan, cang, dmfx, kbao, kw);
		}

	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Dmgs.class, id);
	}

	@Override
	public void saveDmlp(DmLp entity) {
		dao.save(entity);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
