package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JbddItem;

/**
 * <p>
 * 基板订单明细查询条件类
 * </p>
 * <p>
 * create: 2010-12-21 下午06:26:37
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class JbddItemQE extends QEntity<JbddItem> {

	/**
	 * 查询作成开始时间段
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询作成结束时间段
	 */
	@QF(alias = "crea", operator = EO.LT, addDays = 1)
	private Date creaEnd;

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
	 * 担当者
	 */
	@QF(operator = EO.LIKE)
	private String ddnm;

	/**
	 * 订购单状态 不等于的处理
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 订购单状态
	 */
	@QF(alias = "stat")
	private String stat;

	/**
	 * 基板订购单
	 */
	@QF(alias = "jbdd.id")
	private String pid;

	@QF(alias = "id", operator = EO.IN)
	private String[] ids;

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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public void setIdStr(String idStr) {
		if (idStr == null || idStr.isEmpty()) {
			this.ids = null;
			return;
		}
		this.ids = idStr.split(",");
	}

	public Integer getDhsls() {
		int dhsls = 0;
		for (JbddItem item : getDatas()) {
			dhsls += item.getDhsl() == null ? 0 : item.getDhsl();
		}
		return dhsls;
	}
}
