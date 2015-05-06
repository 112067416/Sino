package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 生产类型
 * </p>
 * <p>
 * create: 2011-1-5 下午04:25:23
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public enum ProductType {
	/**
	 * 镀锡生产ETL
	 */
	etl("1", "ETL", "镀锡生产"),
	/**
	 * 剪切生产SL
	 */
	sl("2", "SL", "剪切生产"),
	/**
	 * 分选SS
	 */
	ss("3", "SS", "分选");

	/**
	 * 代码
	 */
	public final String code;

	/**
	 * 名称
	 */
	public final String name;

	/**
	 * 描述
	 */
	public final String description;

	ProductType(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public static ProductType get(String code) {
		if (code == null || code.isEmpty()) {
			return null;
		}
		for (ProductType type : values()) {
			if (type.code.equals(code)) {
				return type;
			}
		}
		return null;
	}

}
