package com.quanta.sino.cg.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cg.dao.api.IWwhtDAO;
import com.quanta.sino.orm.WwhtTp;

/**
 * <p>
 * 采购合同数据访问接口实现
 * </p>
 * <p>
 * create: 2010-12-22 上午10:38:52
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class WwhtDAO implements IWwhtDAO {

	private DAO dao;

	@Override
	public void save(WwhtTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<WwhtTp> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(WwhtTp entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(WwhtTp.class, id);
	}

	@Override
	public void query(QEntity<WwhtTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public WwhtTp get(Serializable id) {
		return dao.get(WwhtTp.class, id);
	}

	@Override
	public WwhtTp getRef(Serializable id) {
		return dao.getRef(WwhtTp.class, id);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
