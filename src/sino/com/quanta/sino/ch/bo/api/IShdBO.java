package com.quanta.sino.ch.bo.api;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Zng1Tp;

/**
 * <p>
 * 送货单操作业务接口
 * </p>
 * <p>
 * create: 2011-1-18 上午09:22:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IShdBO {

	/**
	 * <p>
	 * 查询送货单
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Zng1Tp> qentity);

	/**
	 * <p>
	 * 送货单多条删除
	 * </p>
	 * 
	 * @param zxnos
	 */
	public void dels(String zxnos[]);

	/**
	 * <p>
	 * 送货单 单条删除
	 * </p>
	 * 
	 * @param zxno
	 */
	public void delete(String zxno);
}
