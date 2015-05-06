package com.quanta.sino.yszk.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.dao.api.IKhfkDAO;

/**
 * <p>
 * 客户付款数据访问层实现类
 * </p>
 * <p>
 * create: 2011-6-30 下午12:46:50
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhfkDAO implements IKhfkDAO {

	private DAO dao;

	@Override
	public void save(Khfk entity) {
		dao.save(entity);
	}

	@Override
	public void update(Khfk entity) {
		dao.update(entity);
	}

	@Override
	public void delete(List<String> ids) {
		dao.executeForValues("delete from Khfk where id in (?)", ids);
	}

	@Override
	public void query(QEntity<Khfk> qentity) {
		dao.query(qentity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Khfk> find(List<String> ids) {
		StringBuilder ql = new StringBuilder("from Khfk where id in ('");
		ql.append(StringUtils.join(ids, "','")).append("')");
		return (List<Khfk>) dao.query(ql.toString());
	}

	@Override
	public Khfk get(Serializable id) {
		return dao.get(Khfk.class, id);
	}

	@Override
	public Khfk getRef(Serializable id) {
		return dao.getRef(Khfk.class, id);
	}

	// @Override
	// public Khfk get(String user, Date crea) {
	// StringBuilder ql = new StringBuilder("from Khfk where");
	// int i = 0;
	// if (user != null && !user.isEmpty()) {
	// i ^= 1;
	// ql.append(" user=? and");
	// }
	// if (crea != null) {
	// i ^= 2;
	// ql.append(" crea=?");
	// }
	// ql.append(" order by crea desc");
	// if (i == 0) {
	// return (Khfk) dao.getUnique(ql.toString());
	// }
	// else if (i == 1) {
	// return (Khfk) dao.getUnique(ql.toString(), user);
	// }
	// else if (i == 2) {
	// return (Khfk) dao.getUnique(ql.toString(), crea);
	// }
	// else if (i == 3) {
	// return (Khfk) dao.getUnique(ql.toString(), user, crea);
	// }
	// return null;
	// }

	// @Override
	// public Khfk get(String user, Date crea, String[] stats) {
	// StringBuilder ql = new StringBuilder("from Khfk where");
	// int i = 0;
	// if (user != null && !user.isEmpty()) {
	// i ^= 1;
	// ql.append(" user=? and");
	// }
	// if (crea != null) {
	// i ^= 2;
	// ql.append(" crea=? and");
	// }
	// ql.append(" stat in ('").append(StringUtils.join(stats,
	// "','")).append("')").append(" order by crea desc");
	// if (i == 0) {
	// return (Khfk) dao.getUnique(ql.toString());
	// }
	// else if (i == 1) {
	// return (Khfk) dao.getUnique(ql.toString(), user);
	// }
	// else if (i == 2) {
	// return (Khfk) dao.getUnique(ql.toString(), crea);
	// }
	// else if (i == 3) {
	// return (Khfk) dao.getUnique(ql.toString(), user, crea);
	// }
	// return null;
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Khfk> find(String fpymc, List<String> stats) {
		return (List<Khfk>) dao.findForValues("from Khfk where name=? and stat in (?) order by crea asc", stats, fpymc);
	}

	@Override
	public boolean isExisted(String fpymc, List<String> stats) {
		StringBuilder ql = new StringBuilder(
				"from Khfk where name=? and stat in ('");
		ql.append(StringUtils.join(stats, "','")).append("')");
		return dao.isExisted(ql.toString(), fpymc);
	}

	@Override
	public Double getFkye(String fpymc) {
		String ql = "select sum(fkye) from Khfk where from name=?";
		Double fkye = (Double) dao.getUnique(ql, fpymc);
		return fkye;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
