package com.quanta.sino.ch.vo;

import com.coco.core.util.NumberUtils;

/**
 * <p>
 * 出货按年统计
 * </p>
 * <p>
 * create: 2011-8-28 下午05:12:20
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChntjVO {

	/**
	 * 年
	 */
	private int year;

	/**
	 * 出货量
	 */
	private Long chzl;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Long getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		if (chzl == null) return;
		this.chzl = NumberUtils.formatDouToLong(chzl, 3);
	}

}
