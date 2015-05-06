package com.quanta.sino.etl.vo;

import java.util.Date;

/**
 * <p>
 * 统计制品件数和重量
 * </p>
 * <p>
 * create: 2011-5-2 下午03:41:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZptjVO {

	/**
	 * 件数
	 */
	private Long su;

	/**
	 * 制品重量
	 */
	private Long zl;

	/**
	 * 日期
	 */
	private Date rq;

	public Long getSu() {
		return su;
	}

	public void setSu(Long su) {
		this.su = su;
	}

	public Long getZl() {
		return zl;
	}

	public void setZl(Long zl) {
		this.zl = zl;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

}
