package com.quanta.sino.yccl.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Zyrz;
import com.quanta.sino.yccl.dao.api.IZyrzDAO;

/**
 * <p>
 * 作业日志
 * </p>
 * <p>
 * create: 2011-2-15 下午04:43:55
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class ZyrzDAO implements IZyrzDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Zyrz entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<Zyrz> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void deleteByJbno(List<String> jbnos) {
		dao.executeForValues("delete from Zyrz where jbno in (?)", jbnos);
	}

	@Override
	public void deleteByJbno(List<String> jbnos, String zylx) {
		dao.executeForValues("delete from Zyrz where jbno in (?) and zylx=?", jbnos, zylx);
	}

	@Override
	public Zyrz get(Serializable id) {
		return dao.get(Zyrz.class, id);
	}

	@Override
	public Zyrz get(String jbno, String zylx) {
		return (Zyrz) dao.getUnique("from Zyrz where jbno=? and zylx=?", jbno, zylx);
	}

	@Override
	public void update(Zyrz entity) {
		dao.update(entity);
	}

}
