package com.quanta.sino.kb.dao;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.kb.dao.api.IKbDAO;
import com.quanta.sino.orm.Kbrzb;

/**
 * <p>
 * 捆包生产数据操作实现类
 * </p>
 * <p>
 * create: 2011-1-12 上午10:17:08
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KbDAO implements IKbDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Kbrzb entity) {
		dao.save(entity);
	}

}
