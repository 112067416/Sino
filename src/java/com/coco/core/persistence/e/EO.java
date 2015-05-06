package com.coco.core.persistence.e;

/**
 * <p>
 * 操作类型
 * </p>
 * <p>
 * create: 2011-4-20 下午07:21:27
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum EO {
	EQ, LT, LE, GT, GE, NE, LIKE, NLIKE, IN, NOT_IN, NULL, IS_NULL, NOT_NULL, QL, GE_NULL,

	/**
	 * 值为QL串
	 */
	VQL
}
