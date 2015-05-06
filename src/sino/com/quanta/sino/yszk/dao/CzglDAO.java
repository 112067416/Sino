package com.quanta.sino.yszk.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.yszk.dao.api.ICzglDAO;

/**
 * <p>
 * 付款发票冲帐数据访问层实现类
 * </p>
 * <p>
 * create: 2011-6-27 下午08:40:31
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CzglDAO implements ICzglDAO {

	private DAO dao;

	@Override
	public void query(QEntity<Czgl> qentity) {
		dao.query(qentity);
	}

	@Override
	public void save(Czgl entity) {
		dao.save(entity);
	}

	@Override
	public void update(Czgl entity) {
		dao.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Czgl> find(String id) {
		return (List<Czgl>) dao.query("from Czgl where khfk.id =?", id);
	}

	@Override
	public Double getTzje(String khfkId) {
		String ql = "select sum(wsyk) from Czgl where khfk.id =? and wsyk <> 0";
		Object obj = (Object) dao.getUnique(ql, khfkId);
		if (obj == null) {
			return 0d;
		}
		return (Double) obj;
	}

	@Override
	public Czgl getByKhfk(String khfkId) {
		String ql = "from Czgl where khfk.id =? and wsyk <> 0";
		return (Czgl) dao.getUnique(ql, khfkId);
	}

	@Override
	public Czgl getByKhfp(String khfpId) {
		return dao.getUnique(Czgl.class, "fkfp.id =? order by skri desc", khfpId);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Czgl.class, id);
	}

	@Override
	public Long countByKhfk(String khfkId) {
		Long count = (Long) dao.getUnique("select count(*) from Czgl where khfk.id = ?", khfkId);
		return count;
	}

	@Override
	public Long countByFkfp(String fkfpId) {
		Long count = (Long) dao.getUnique("select count(*) from Czgl where fkfp.id = ?", fkfpId);
		return count;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
