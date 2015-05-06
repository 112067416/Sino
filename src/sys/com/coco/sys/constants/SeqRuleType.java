package com.coco.sys.constants;

public enum SeqRuleType {

	sequence("0", "流水项"), consts("1", "固定串"), date("2", "时间串");

	public final String value;
	public final String description;

	SeqRuleType(String value, String description) {
		this.value = value;
		this.description = description;
	}

}
