package com.quanta.sino.kb.dao.api;

import com.quanta.sino.orm.Kbrzb;

/**
 * <p>
 * 捆包生产数据库操作层接口
 * </p>
 * <p>
 * create: 2011-1-12 上午09:58:00
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IKbDAO {

	/**
	 * <p>
	 * 新增捆包日记
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Kbrzb entity);

}
