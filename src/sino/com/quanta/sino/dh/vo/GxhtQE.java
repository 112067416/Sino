package com.quanta.sino.dh.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 发生品查询条件
 * </p>
 * <p>
 * create: 2011-6-12 下午04:40:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select a.DHNO_,a.YEAR_,a.MONTH_,a.USER_,a.NAME_,a.HTZL_,CASE WHEN b.DHNO_ IS NOT NULL THEN 1 ELSE 0 END as STAT_ from SINO_V_GXHT a left join SINO_GXHTTP b on a.DHNO_=b.DHNO_ where#1#) t", meta = "DHNO_,YEAR_,MONTH_,USER_,NAME_,HTZL_,STAT_"))
public class GxhtQE extends QEntity<GxhtVO> {

	/**
	 * 
	 */
	@QF(alias = "a.DHNO_", operator = EO.EQ)
	private String dhno;

	/**
	 * 
	 */
	@QF(alias = "a.USER_", operator = EO.EQ)
	private String user;

	/**
	 * 
	 */
	@QF(alias = "a.USER_", operator = EO.GE)
	private String userBegin;

	/**
	 * 
	 */
	@QF(alias = "a.USER_", operator = EO.LE)
	private String userEnd;

	/**
	 * 
	 */
	@QF(alias = "a.YEAR_", operator = EO.GE)
	private Integer yearBegin;

	/**
	 * 
	 */
	@QF(alias = "a.YEAR_", operator = EO.LE)
	private Integer yearEnd;

	/**
	 * 
	 */
	@QF(alias = "a.MONTH_", operator = EO.GE)
	private Integer monthBegin;

	/**
	 * 
	 */
	@QF(alias = "a.MONTH_", operator = EO.LE)
	private Integer monthEnd;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserBegin() {
		return userBegin;
	}

	public void setUserBegin(String userBegin) {
		this.userBegin = userBegin;
	}

	public String getUserEnd() {
		return userEnd;
	}

	public void setUserEnd(String userEnd) {
		this.userEnd = userEnd;
	}

	public Integer getYearBegin() {
		return yearBegin;
	}

	public void setYearBegin(Integer yearBegin) {
		this.yearBegin = yearBegin;
	}

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}

	public Integer getMonthBegin() {
		return monthBegin;
	}

	public void setMonthBegin(Integer monthBegin) {
		this.monthBegin = monthBegin;
	}

	public Integer getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(Integer monthEnd) {
		this.monthEnd = monthEnd;
	}

}
