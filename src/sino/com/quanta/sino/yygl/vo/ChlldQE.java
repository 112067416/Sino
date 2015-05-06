package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.NumberUtils;
import com.quanta.sino.orm.Chlld;

/**
 * <p>
 * 出货联络单查询条件类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:26:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ChlldQE extends QEntity<Chlld> {

	/**
	 * 查询出货开始时间段
	 */
	@QF(alias = "chqi", operator = EO.GE)
	private Date chqiBegin;

	/**
	 * 查询出货结束时间段
	 */
	@QF(alias = "chqi", operator = EO.LT, addDays = 1)
	private Date chqiEnd;

	/**
	 * 担当者代码
	 */
	@QF
	private String ddno;

	/**
	 * 用户略称
	 */
	@QF(operator = EO.LIKE)
	private String abbr;

	/**
	 * 订货no
	 */
	@QF
	private String dhno;

	/**
	 * 订货行号
	 */
	@QF
	private String line;

	/**
	 * 
	 */
	@QF(alias = "stat", operator = EO.IN)
	private String[] stats;

	/**
	 * 
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 
	 */
	@QF(alias = "dhno", operator = EO.LIKE)
	private String dhnoLike;

	/**
	 * 
	 */
	@QF(alias = "dhno", operator = EO.NLIKE)
	private String dhnoNLike;

	public Date getChqiBegin() {
		return chqiBegin;
	}

	public void setChqiBegin(Date chqiBegin) {
		this.chqiBegin = chqiBegin;
	}

	public Date getChqiEnd() {
		return chqiEnd;
	}

	public void setChqiEnd(Date chqiEnd) {
		this.chqiEnd = chqiEnd;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

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

	/**
	 * <p>
	 * 获取吨数即总量的合计值
	 * </p>
	 * 
	 * @return Double 返回合计值
	 */
	public Double getChzls() {
		double chzls = 0.0;
		String dhno;
		for (Chlld chlld : getDatas()) {
			dhno = chlld.getDhno();
			if (dhno == null || dhno.isEmpty()
					|| dhno.toUpperCase().indexOf("W") >= 0) continue;
			chzls += chlld.getChzl();
		}
		return NumberUtils.format(chzls, 3);
	}

	public String[] getStats() {
		return stats;
	}

	public void setStats(String[] stats) {
		this.stats = stats;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

	public String getDhnoLike() {
		return dhnoLike;
	}

	public void setDhnoLike(String dhnoLike) {
		this.dhnoLike = dhnoLike;
	}

	public String getDhnoNLike() {
		return dhnoNLike;
	}

	public void setDhnoNLike(String dhnoNLike) {
		this.dhnoNLike = dhnoNLike;
	}
}
