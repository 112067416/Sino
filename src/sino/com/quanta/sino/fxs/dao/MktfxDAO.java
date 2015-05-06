package com.quanta.sino.fxs.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.fxs.dao.api.IMktfxDAO;
import com.quanta.sino.orm.MktfxshJl;

/**
 * <p>
 * 马口铁检测数据维护
 * </p>
 * <p>
 * create: 2010-12-29 上午09:54:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class MktfxDAO implements IMktfxDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(MktfxshJl entity) {
		dao.save(entity);
	}

	@Override
	public void update(MktfxshJl entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(MktfxshJl.class, id);
	}

	@Override
	public void query(QEntity<MktfxshJl> qentity) {
		dao.query(qentity);
	}

	@Override
	public MktfxshJl get(Serializable id) {
		return dao.get(MktfxshJl.class, id);
	}

	@Override
	public boolean isYcaiTpExisted(String jbno) {
		String ql = "from YcaiTp where jbno=?";
		return dao.isExisted(ql, jbno);
	}

	@Override
	public MktfxshJl getByCyrzId(String cyrzId) {
		String ql = "from MktfxshJl where cyrzId=?";
		return (MktfxshJl) dao.getUnique(ql, cyrzId);
	}

	@Override
	public List<MktfxshJl> findMktfxshJls(String jbno) {
		return dao.find(MktfxshJl.class, "jbno=? order by fxr asc", jbno);
	}

}
