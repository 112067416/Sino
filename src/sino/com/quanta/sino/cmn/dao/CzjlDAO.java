/**
 * 
 */
package com.quanta.sino.cmn.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.cmn.dao.api.ICzjlDAO;
import com.quanta.sino.orm.ZkfpCzjl;

/**
 * <p>
 * 分配和余材操作记录数据访问实现类
 * </p>
 * <p>
 * create: 2011-1-20 上午09:50:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CzjlDAO implements ICzjlDAO {

	private DAO dao;

	@Override
	public void save(ZkfpCzjl entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<ZkfpCzjl> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void update(ZkfpCzjl entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(ZkfpCzjl.class, id);
	}

	@Override
	public void query(QEntity<ZkfpCzjl> qentity) {
		dao.query(qentity);
	}

	@Override
	public ZkfpCzjl get(Serializable id) {
		return dao.get(ZkfpCzjl.class, id);
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
