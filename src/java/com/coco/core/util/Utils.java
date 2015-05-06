package com.coco.core.util;

import java.util.UUID;

/**
 * <p>
 * 全局工具类
 * </p>
 * <p>
 * create: 2010-12-21 上午10:26:48
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Utils {

	/**
	 * <p>
	 * 全球唯一码
	 * </p>
	 * 
	 * @return String
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
