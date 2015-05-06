/**
 * 
 */
package com.quanta.sino.cmn.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 导入转换配置标识
 * </p>
 * <p>
 * create: 2011-1-12 下午02:17:30
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public enum Convert {
	/**
	 * 原板商品检查书导入
	 */
	jchaTp("JchaTp", "原板商品检查书导入"),
	/**
	 * 供应商合同导入
	 */
	wwhttp("Wwhttp", "供应商合同导入"),
	/**
	 * 原板清单导入
	 */
	ycaitp("Ycaitp", "原板清单导入"),

	/**
	 * 原板入库
	 */
	ybrk("Ybrk", "原板入库"),
	/**
	 * 制品入库
	 */
	zprk("Zprk", "制品入库"),
	/**
	 * 考勤人员导入
	 */
	kqry("Kqry", "考勤人员导入"),
	/**
	 * 考勤明细导入
	 */
	kqrymx("Kqrymx", "考勤明细导入");

	public final String name;

	public final String description;

	Convert(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public static List<String> list() {
		List<String> list = new ArrayList<String>();
		for (Convert c : values()) {
			list.add(c.name);
		}
		return list;
	}
}
