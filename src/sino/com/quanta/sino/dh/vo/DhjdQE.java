package com.quanta.sino.dh.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 订货进度查询条件
 * </p>
 * <p>
 * create: 2011-7-18 上午09:42:23
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(value = @QSQL(sql = "from (select a.NAME_,a.ABBR_,a.JHQI_,a.DHNO_,a.LINE_,a.PZNO_,a.GGNO_,a.YUNY_,a.FUDW_,a.FUZM_,a.FUFM_,a.HOUU_,a.KUAN_,a.CANG_,a.HTZL_,a.FPLN_,a.ETLW,a.ETLH_,a.SLW,a.SLHG_,a.CHUS_,a.KBUS_,a.KCUS_,a.THUS_,a.DDNM_,a.STAT_ from SINO_V_DHUO_SCTJ a where#1# ) t", meta = "NAME_,ABBR_,JHQI_,DHNO_,LINE_,PZNO_,GGNO_,YUNY_,FUDW_,FUZM_,FUFM_,HOUU_,KUAN_,CANG_,HTZL_,FPLN_,ETLW,ETLH_,SLW,SLHG_,CHUS_,KBUS_,KCUS_,THUS_,DDNM_,STAT_"))
public class DhjdQE extends QEntity<DhVO> {

	/**
	 * 
	 */
	@QF
	private String dhno;

	/**
	 * 行号
	 */
	@QF
	private String line;

	/**
	 * 查询交货期开始时间段
	 */
	@QF(alias = "JHQI_", operator = EO.GE)
	private Date jhqiBegin;

	/**
	 * 查询交货期结束时间段
	 */
	@QF(alias = "JHQI_", operator = EO.LT, addDays = 1)
	private Date jhqiEnd;

	/**
	 * 查询用户代码开始段
	 */
	@QF(alias = "USER_", operator = EO.GE)
	private String userBegin;

	/**
	 * 查询用户代码结束段
	 */
	@QF(alias = "USER_", operator = EO.LT)
	private String userEnd;

	/**
	 * 查询作成日期开始时间段
	 */
	@QF(alias = "CREA_", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 查询作成日期结束时间段
	 */
	@QF(alias = "CREA_", operator = EO.LT, addDays = 1)
	private Date creaEnd;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * 状态
	 */
	@QF(alias = "STAT_", operator = EO.NE)
	private String neStat;

	/**
	 * 用户代码
	 */
	@QF
	private String user;

	/**
	 * 
	 */
	@QF(alias = "PZNO_", operator = EO.IN)
	private String[] pznos;

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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String[] getPznos() {
		return pznos;
	}

	public void setPznos(String[] pznos) {
		this.pznos = pznos;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

}
