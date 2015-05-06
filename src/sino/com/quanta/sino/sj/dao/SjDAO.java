package com.quanta.sino.sj.dao;

import java.io.Serializable;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.Zsfmt;
import com.quanta.sino.sj.dao.api.ISjDAO;

/**
 * <p>
 * 实绩日志公共格式数据访问实现
 * </p>
 * <p>
 * create: 2011-1-20 下午07:34:41
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SjDAO implements ISjDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Zsfmt entity) {
		dao.save(entity);
	}

	@Override
	public void save(Xpfmt entity) {
		dao.save(entity);
	}

	@Override
	public void update(Xpfmt entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(RiziLp.class, id);
	}

	@Override
	public void save(RiziLp entity) {
		dao.save(entity);
	}

	@Override
	public Zsfmt getZs(Serializable id) {
		return dao.get(Zsfmt.class, id);
	}

	@Override
	public Xpfmt getXp(Serializable id) {
		return dao.get(Xpfmt.class, id);
	}

	@Override
	public ZscdTp getZscd(Serializable id) {
		return dao.get(ZscdTp.class, id);
	}

	@Override
	public void saveZscd(ZscdTp entity) {
		dao.save(entity);
	}

	@Override
	public void deleteZscd(Serializable id) {
		dao.delete(ZscdTp.class, id);
	}

}
