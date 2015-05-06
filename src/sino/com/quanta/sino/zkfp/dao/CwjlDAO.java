/**
 * 
 */
package com.quanta.sino.zkfp.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZkfpCwjl;
import com.quanta.sino.zkfp.dao.api.ICwjlDAO;

/**
 * <p>
 * 分配错误记录数据访问实现类
 * </p>
 * <p>
 * create: 2011-1-21 下午03:42:56
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CwjlDAO implements ICwjlDAO {

	private DAO dao;

	@Override
	public void save(ZkfpCwjl entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<ZkfpCwjl> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(ZkfpCwjl entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(ZkfpCwjl.class, id);
	}

	@Override
	public void query(QEntity<ZkfpCwjl> qentity) {
		dao.query(qentity);
	}

	@Override
	public ZkfpCwjl get(Serializable id) {
		return dao.get(ZkfpCwjl.class, id);
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
