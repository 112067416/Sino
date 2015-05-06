package com.coco.query.bean;

public enum Opt {

	eq(0, "=", " = ", true), ne(1, "!=", " <> ", true), lt(2, "<", " < ", true), le(
			3, "<=", " <= ", true), gt(4, ">", " > ", true), ge(5, ">=",
			" >= ", true), like(6, "含有", " like ", true), in(7, "包含", " in ",
			true), notIn(8, "不包含", " not in ", true), isNull(9, "没有值",
			" is null", false), isNotNull(10, "有值", " is not null ", false), isEmpty(
			11, "为空", " = '' ", false);

	public final int type;

	public final String memo;

	public final String opt;

	public final boolean needValue;

	private static String js;

	Opt(int type, String memo, String opt, boolean needValue) {
		this.type = type;
		this.memo = memo;
		this.opt = opt;
		this.needValue = needValue;
	}

	public static Opt parse(int type) {
		for (Opt opt : values()) {
			if (opt.type == type) {
				return opt;
			}
		}
		return eq;
	}

	public static String js() {
		if (js == null) {
			StringBuilder sb = new StringBuilder();
			for (Opt opt : Opt.values()) {
				sb.append(",{ name :\"");
				sb.append(opt.memo);
				sb.append("\", needValue:");
				sb.append(opt.needValue);
				sb.append("}");
			}
			js = '[' + (sb.length() > 0 ? sb.substring(1) : "") + ']';
		}
		return js;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @return the opt
	 */
	public String getOpt() {
		return opt;
	}

	/**
	 * @return the needValue
	 */
	public boolean isNeedValue() {
		return needValue;
	}

}
