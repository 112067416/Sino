package com.quanta.sino.ycai.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.ycai.dao.api.IJchatpDAO;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 下午05:59:26
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class JchatpDAO implements IJchatpDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(JchaTp entity) {
		dao.save(entity);
	}

	@Override
	public void query(QEntity<JchaTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(JchaTp.class, id);
	}

	@Override
	public void deleteAll(List<String> ids) {
		dao.executeForValues("delete from JchaTp where ycaiTp.jbno in (?)", ids);
	}

	@Override
	public JchaTp get(Serializable id) {
		return dao.get(JchaTp.class, id);
	}

	@Override
	public JchaTp getByZzsj(String zzsj) {
		String ql = "from JchaTp where zzsj=?";
		return (JchaTp) dao.getUnique(ql, zzsj);
	}

	@Override
	public void update(JchaTp entity) {
		dao.update(entity);
	}

	@Override
	public boolean isExisted(String jbno) {
		String ql = "from JchaTp where ycaiTp.jbno=?";
		return dao.isExisted(ql, jbno);
	}

	@Override
	public void saveAll(List<JchaTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public boolean isExistedByZzsj(String zzsj) {
		String ql = "from JchaTp where zzsj=?";
		return dao.isExisted(ql, zzsj);
	}

}
