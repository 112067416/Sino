package com.quanta.sino.cmn.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.dao.api.IZzsyDAO;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 仕样主文件数据访问实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:05:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZzsyDAO implements IZzsyDAO {

	private DAO dao;

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

	@Override
	public void save(ZzsyMp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(ZzsyMp.class, id);
	}

	@Override
	public void query(QEntity<ZzsyMp> qentity) {
		dao.query(qentity);
	}

	@Override
	public ZzsyMp get(Serializable id) {
		return dao.get(ZzsyMp.class, id);
	}

	@Override
	public void update(ZzsyMp entity) {
		dao.update(entity);
	}

	@Override
	public ZzsyMp getByKey(String ql, Object... values) {
		return (ZzsyMp) dao.getUnique(ql, values);
	}

	@Override
	public ZzsyMp getByKey(String ql) {
		return (ZzsyMp) dao.getUnique(ql);
	}

	@Override
	public String getMaxNo() {
		return (String) dao.getUnique("select max(syno) from ZzsyMp");
	}

	@Override
	public void executeUpdate(String ql, Object... params) {
		dao.executeUpdate(ql, params);
	}

}
