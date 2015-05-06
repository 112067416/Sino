package com.quanta.sino.yygl.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Khyfdj;
import com.quanta.sino.yygl.dao.api.IKhyfdjDAO;

/**
 * <p>
 * 客户运费单价数据访问层实现类
 * </p>
 * <p>
 * create: 2011-2-14 下午02:20:04
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfdjDAO implements IKhyfdjDAO {

	private DAO dao;

	@Override
	public void save(Khyfdj entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<Khyfdj> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(Khyfdj entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Khyfdj.class, id);
	}

	@Override
	public void query(QEntity<Khyfdj> qentity) {
		dao.query(qentity);
	}

	@Override
	public Khyfdj get(Serializable id) {
		return dao.get(Khyfdj.class, id);
	}

	@Override
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String ld) {
		String ql = "from Khyfdj where ysnm=? and user=? and shno=? and addr=? and ysfs=? and stat=? and ld=? order by crea desc";
		return (Khyfdj) dao.getUnique(ql, ysnm, user, shno, addr, ysfs, stat, ld);
	}

	@Override
	public Khyfdj getUnique(String ysnm, String user, String shno, String addr,
			String ysfs, String stat, String djdw, String ld) {
		String ql = "from Khyfdj where ysnm=? and user=? and shno=? and addr=? and ysfs=? and stat=? and djdw=? and ld=? order by crea desc";
		return (Khyfdj) dao.getUnique(ql, ysnm, user, shno, addr, ysfs, stat, djdw, ld);
	}

	@Override
	public void updateStat(List<String> ids, String stat) {
		dao.executeForValues("update Khyfdj set stat=? where id in (?)", ids, stat);
	}

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
