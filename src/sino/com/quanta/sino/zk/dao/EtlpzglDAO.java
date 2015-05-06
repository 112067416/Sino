package com.quanta.sino.zk.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.constants.EEtlpz;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.zk.dao.api.IEtlpzglDAO;

/**
 * <p>
 * ETL品质管理表
 * </p>
 * <p>
 * create: 2011-1-14 下午05:42:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class EtlpzglDAO implements IEtlpzglDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean isExisted(String jbno) {
		String ql = "from EtlpzGl where jbno=?";
		return dao.isExisted(ql, jbno);
	}

	@Override
	public void save(EtlpzGl entity) {
		dao.save(entity);
	}

	@Override
	public void update(EtlpzGl entity) {
		dao.update(entity);
	}

	@Override
	public void query(QEntity<EtlpzGl> qentity) {
		dao.query(qentity);
	}

	@Override
	public EtlpzGl get(Serializable id) {
		return dao.get(EtlpzGl.class, id);
	}

	@Override
	public EtlpzGl getByJbno(String jbno) {
		return (EtlpzGl) dao.getUnique("from EtlpzGl where jbno=? order by id desc", jbno);
	}

	@Override
	public void delete(List<String> ids) {
		dao.executeForValues("delete from EtlpzGl where id in(?)", ids);
	}

	@Override
	public void delete(String jbno) {
		dao.executeUpdate("delete from EtlpzGl where jbno=?", jbno);
	}

	@Override
	public void read(Serializable id, boolean readed) {
		dao.executeUpdate("update EtlpzGl set newed=? where id=?", readed, id);
	}

	@Override
	public EtlpzGl getLatest(Date scsj) {
		return (EtlpzGl) dao.getUnique("from EtlpzGl where scsj < ? order by scsj desc", scsj);
	}

	@Override
	public boolean isExistedNew(Date scsjBegin, Date scsjEnd) {
		String ql = "from EtlpzGl where scsj >=? and scsj < ? and newed=?";
		return dao.isExisted(ql, scsjBegin, scsjEnd, true);
	}

	@Override
	public boolean isExistedNew() {
		String ql = "from EtlpzGl where newed=? and stat=?";
		return dao.isExisted(ql, true, EEtlpz.ywc.key);
	}

	@Override
	public EtlpzGl getByStat(String stat) {
		return (EtlpzGl) dao.getUnique("from EtlpzGl where stat = ? order by scsj desc", stat);
	}

	@Override
	public Long count(Date scsj) {
		Long count = (Long) dao.getUnique("select count(*) from EtlpzGl where scsj >= ? and scsj < ?", scsj, DateUtils.add(scsj, Calendar.DAY_OF_MONTH, 1));
		return count;
	}

	@Override
	public void updateStatDqjToYwc() {
		dao.executeUpdate("update EtlpzGl set stat=? where stat=?", EEtlpz.ywc.key, EEtlpz.dqj.key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EtlpzGl> query(Date scsjBegin, Date scsjEnd) {
		return (List<EtlpzGl>) dao.query("from EtlpzGl where scsj >= ? and scsj < ? order by scsj asc", scsjBegin, scsjEnd);
	}

}
