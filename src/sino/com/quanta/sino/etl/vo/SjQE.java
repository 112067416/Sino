package com.quanta.sino.etl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 实绩录入维护查询条件
 * </p>
 * <p>
 * create: 2011-1-28 上午10:45:45
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q
public class SjQE extends QEntity<ZpngTp> {
	/**
	 * etl 生产线
	 */
	@QF
	private String elin;
	/**
	 * 班
	 */
	@QF
	private String ban;
	/**
	 * 组
	 */
	@QF
	private String zu;
	/**
	 * 指示书NO
	 */
	@QF(operator = EO.LIKE)
	private String zsno;
	/**
	 * 不良扣除联络单打印标志
	 */
	@QF
	private String lody;
	/**
	 * 制品状态
	 */
	@QF
	private String stat;

	/**
	 * 现品状况
	 */
	@QF(alias = "xpzk", operator = EO.IN)
	private String[] xpzk;
	/**
	 * <p>
	 * 作成时间-开始
	 * </p>
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * <p>
	 * 作成时间-结束
	 * </p>
	 */
	@QF(alias = "crea", operator = EO.LE, addDays = 1)
	private Date crea_end;

	public String[] getXpzk() {
		return xpzk;
	}

	public void setXpzk(String[] xpzk) {
		this.xpzk = xpzk;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getLody() {
		return lody;
	}

	public void setLody(String lody) {
		this.lody = lody;
	}

	/**
	 * COIL.No.
	 */
	@QF(operator = EO.LIKE)
	private String jbno;

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public Date getCrea_begin() {
		return crea_begin;
	}

	public void setCrea_begin(Date crea_begin) {
		this.crea_begin = crea_begin;
	}

	public Date getCrea_end() {
		return crea_end;
	}

	public void setCrea_end(Date crea_end) {
		this.crea_end = crea_end;
	}

}
