package com.quanta.sino.dh.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DhuoTp;

/**
 * <p>
 * 订货管理查询条件定义类
 * </p>
 * <p>
 * create: 2011-1-4 上午10:52:23
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
@Q
public class DhQE extends QEntity<DhuoTp> {

	/**
	 * 订货No
	 */
	@QF
	private String dhno;

	/**
	 * 行号
	 */
	@QF
	private String line;

	/**
	 * 开始行号
	 */
	@QF(alias = "line", operator = EO.GE)
	private String lineStart;

	/**
	 * 结束行号
	 */
	@QF(alias = "line", operator = EO.LE)
	private String lineEnd;

	/**
	 * 查询交货期开始时间段
	 */
	@QF(alias = "jhqi", operator = EO.GE)
	private Date jhqiBegin;

	/**
	 * 查询交货期结束时间段
	 */
	@QF(alias = "jhqi", operator = EO.LT, addDays = 1)
	private Date jhqiEnd;

	/**
	 * 查询仕样期开始时间段
	 */
	@QF(alias = "sydt", operator = EO.GE)
	private Date sydtBegin;

	/**
	 * 查询仕样期结束时间段
	 */
	@QF(alias = "sydt", operator = EO.LT, addDays = 1)
	private Date sydtEnd;

	/**
	 * 查询作成日期开始时间段
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询作成日期结束时间段
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaEnd;

	/**
	 * 查询用户代码开始段
	 */
	@QF(alias = "user", operator = EO.GE)
	private String userBegin;

	/**
	 * 查询用户代码结束段
	 */
	@QF(alias = "user", operator = EO.LT)
	private String userEnd;

	/**
	 * 用户代码
	 */
	@QF
	private String user;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * 结算条件.设定标志
	 */
	@QF
	private String jstj;

	/**
	 * 仕样未确认模块专使用的状态
	 */
	@QF(alias = "stat", operator = EO.IN)
	private String[] stats;

	/**
	 * 是否打印
	 */
	@QF
	private String sfdy;

	/**
	 * 仕样是否已修订
	 */
	@QF(alias = "xdnm", operator = EO.IS_NULL)
	private Boolean syxded;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Date getJhqiBegin() {
		return jhqiBegin;
	}

	public void setJhqiBegin(Date jhqiBegin) {
		this.jhqiBegin = jhqiBegin;
	}

	public Date getJhqiEnd() {
		return jhqiEnd;
	}

	public void setJhqiEnd(Date jhqiEnd) {
		this.jhqiEnd = jhqiEnd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getJstj() {
		return jstj;
	}

	public void setJstj(String jstj) {
		this.jstj = jstj;
	}

	public String[] getStats() {
		return stats;
	}

	public void setStats(String[] stats) {
		this.stats = stats;
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

	public String getSfdy() {
		return sfdy;
	}

	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}

	public String getLineStart() {
		return lineStart;
	}

	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	public String getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	public Boolean getSyxded() {
		return syxded;
	}

	public void setSyxded(Boolean syxded) {
		this.syxded = syxded;
	}

	public Date getCreaBegin() {
		return creaBegin;
	}

	public void setCreaBegin(Date creaBegin) {
		this.creaBegin = creaBegin;
	}

	public Date getCreaEnd() {
		return creaEnd;
	}

	public void setCreaEnd(Date creaEnd) {
		this.creaEnd = creaEnd;
	}

	public Date getSydtBegin() {
		return sydtBegin;
	}

	public void setSydtBegin(Date sydtBegin) {
		this.sydtBegin = sydtBegin;
	}

	public Date getSydtEnd() {
		return sydtEnd;
	}

	public void setSydtEnd(Date sydtEnd) {
		this.sydtEnd = sydtEnd;
	}

}
