package com.quanta.sino.ch.vo;

import com.coco.core.util.NumberUtils;

/**
 * <p>
 * 按季度统计出货量
 * </p>
 * <p>
 * create: 2011-8-28 下午05:42:31
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class QuarterVO {

	/**
	 * 年份
	 */
	private Integer year;

	/**
	 * 1季度
	 */
	private Long quarter1;

	/**
	 * 2季度
	 */
	private Long quarter2;

	/**
	 * 3季度
	 */
	private Long quarter3;

	/**
	 * 4季度
	 */
	private Long quarter4;

	/**
	 * 总出货量
	 */
	private Long chzl;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getChzl() {
		return chzl;
	}

	public void setChzl(int month, Double chzl) {
		if (chzl == null || chzl <= 0d) return;
		if (month == 1 || month == 2 || month == 3) {
			long $quarter1 = this.quarter1 != null ? this.quarter1 : 0l;
			this.quarter1 = NumberUtils.formatDouToLong(NumberUtils.add($quarter1, chzl).doubleValue(), 3);
		}
		else if (month == 4 || month == 5 || month == 6) {
			long $quarter2 = this.quarter2 != null ? this.quarter2 : 0l;
			this.quarter2 = NumberUtils.formatDouToLong(NumberUtils.add($quarter2, chzl).doubleValue(), 3);
		}
		else if (month == 7 || month == 8 || month == 9) {
			long $quarter3 = this.quarter3 != null ? this.quarter3 : 0l;
			this.quarter3 = NumberUtils.formatDouToLong(NumberUtils.add($quarter3, chzl).doubleValue(), 3);
		}
		else {
			long $quarter4 = this.quarter4 != null ? this.quarter4 : 0l;
			this.quarter4 = NumberUtils.formatDouToLong(NumberUtils.add($quarter4, chzl).doubleValue(), 3);
		}
		long $chzl = this.chzl != null ? this.chzl : 0l;
		this.chzl = NumberUtils.formatDouToLong(NumberUtils.add($chzl, chzl).doubleValue(), 3);
	}

	public Long getQuarter1() {
		return quarter1;
	}

	public void setQuarter1(Long quarter1) {
		this.quarter1 = quarter1;
	}

	public Long getQuarter2() {
		return quarter2;
	}

	public void setQuarter2(Long quarter2) {
		this.quarter2 = quarter2;
	}

	public Long getQuarter3() {
		return quarter3;
	}

	public void setQuarter3(Long quarter3) {
		this.quarter3 = quarter3;
	}

	public Long getQuarter4() {
		return quarter4;
	}

	public void setQuarter4(Long quarter4) {
		this.quarter4 = quarter4;
	}

}
