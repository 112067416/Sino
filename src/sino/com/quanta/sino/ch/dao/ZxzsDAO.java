package com.quanta.sino.ch.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.quanta.sino.ch.dao.api.IZxzsDAO;
import com.quanta.sino.cmn.constants.ChStat;
import com.quanta.sino.orm.ZxzsTp;

public class ZxzsDAO implements IZxzsDAO {

	private DAO dao;

	@Override
	public void save(ZxzsTp entity) {
		dao.save(entity);
	}

	@Override
	public void update(ZxzsTp entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<ZxzsTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public ZxzsTp get(Serializable id) {
		return dao.get(ZxzsTp.class, id);
	}

	@Override
	public String getMaxNo() {
		return (String) dao.getUnique("select max(zxno) from ZxzsTp");
	}

	@Override
	public boolean isExist(Serializable id) {
		return dao.isExisted("from ZxzsTp where zxno=?", id);
	}

	@Override
	public Double getTj(String pid) {
		String ql = "select sum(chzl) from ZxzsTp where pid=? and stat<>?";
		Object obj = (Object) dao.getUnique(ql, pid, ChStat.ZF.stat);
		if (obj == null) {
			return 0d;
		}
		return (Double) obj;
	}

	@Override
	public Double getTj(Date chri) {
		String ql = "select sum(chzl) from ZxzsTp where chri >=? and chri < ? and stat<>? and pid is not null";
		Object obj = (Object) dao.getUnique(ql, chri, DateUtils.add(chri, Calendar.DAY_OF_MONTH, 1), ChStat.ZF.stat);
		if (obj == null) {
			return 0d;
		}
		return (Double) obj;
	}

	@Override
	public boolean isExistByChlld(String pid) {
		String ql = "from ZxzsTp where pid=? and stat<>?";
		return dao.isExisted(ql, pid, ChStat.ZF.stat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryZxnos(Date chri) {
		String ql = "select zxno from ZxzsTp where stat=? and chri >= ? and chri < ? order by zxno asc";
		return (List<String>) dao.query(ql, ChStat.YFH.stat, chri, DateUtils.add(chri, Calendar.DAY_OF_MONTH, 1));
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getNum(Date chriS, Date chriE) {
		String ql = "select count(*) from ZxzsTp where stat=? and chri >= ? and chri < ? group by chri";
		List<Long> list = (List<Long>) dao.query(ql, ChStat.YFH.stat, chriS, chriE);
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
