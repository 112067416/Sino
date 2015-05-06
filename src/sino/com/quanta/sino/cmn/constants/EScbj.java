package com.quanta.sino.cmn.constants;

/**
 * <p>
 * 删除标记 删除标记除初始状态外,其余状态均表示现品删除,即实物已不存在
 * </p>
 * <p>
 * create: 2011-1-18 上午08:42:49
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public enum EScbj {

	CS("0", "初始"), YSJZL("1", "已实绩终了"), YXM("2", "已消灭"), YCH("3", "已出货"), YCCL(
			"4", "异常处理删除");

	/**
	 * 键
	 */
	public final String key;
	/**
	 * 值
	 */
	public final String value;

	/**
	 * 构造
	 * 
	 * @param key
	 * @param value
	 */
	EScbj(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static EScbj get(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		for (EScbj scbj : values()) {
			if (scbj.key.equals(key)) {
				return scbj;
			}
		}
		return null;
	}

}
