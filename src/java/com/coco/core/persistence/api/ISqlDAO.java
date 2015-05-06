package com.coco.core.persistence.api;

import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 原生SQL查询
 * </p>
 * <p>
 * create: 2011-1-24 下午03:13:46
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISqlDAO {

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param <T>
	 * @param qentity
	 */
	public <T> void query(QEntity<T> qentity);

}
