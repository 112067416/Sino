package com.quanta.sino.cmn.bo.api;

import com.quanta.sino.orm.ZkfpCzjl;

/**
 * <p>
 * 分配和余材操作记录业务接口
 * </p>
 * <p>
 * create: 2011-1-14 下午02:01:01
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface ICzjlBO {

	/**
	 * 新增制品余材操作记录
	 * 
	 * @param entity
	 */
	public void save(ZkfpCzjl entity);

}
