package com.quanta.sino.zk.dao;

import java.io.Serializable;
import java.util.Date;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.EtlyygljlItem;
import com.quanta.sino.zk.dao.api.IEtlyygljlItemDAO;

/**
 * <p>
 * ETL药液管理记录——从表
 * </p>
 * <p>
 * create: 2011-2-15 下午04:31:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlyygljlItemDAO implements IEtlyygljlItemDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(EtlyygljlItem entity) {
		dao.save(entity);
	}

	@Override
	public void update(EtlyygljlItem entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<EtlyygljlItem> qentity) {
		dao.query(qentity);
	}

	@Override
	public EtlyygljlItem get(Serializable id) {
		return dao.get(EtlyygljlItem.class, id);
	}

	@Override
	public boolean isExisted(String id) {
		String ql = "from EtlyygljlItem where id=?";
		return dao.isExisted(ql, id);
	}

	@Override
	public EtlyygljlItem getByYyfxid(Serializable id) {
		String ql = "from EtlyygljlItem where yyfxid.id=?";
		return (EtlyygljlItem) dao.getUnique(ql, id);
	}

	@Override
	public void deleteByYyfxid(Serializable id) {
		dao.executeUpdate("delete from EtlyygljlItem where yyfxid.id=?", id);
	}

	@Override
	public void read(Serializable id, boolean readed) {
		dao.executeUpdate("update EtlyygljlItem set newed=? where id=?", readed, id);
	}

	@Override
	public boolean isExistedNew(Date rqBegin, Date rqEnd) {
		String ql = "from EtlyygljlItem where rq >=? and rq < ? and newed=?";
		return dao.isExisted(ql, rqBegin, rqEnd, true);
	}

}
