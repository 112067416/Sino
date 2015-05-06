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
@Q(@QSQL(sql = "from (select YUNY_+'#'+GGNO_+'#'+cast(XPHO_ as varchar)+'#'+cast(XPKN_ as varchar)+'#'+case when XPCN_  is null or XPCN_<=0 then 'COIL' else cast(XPCN_ as varchar) end+'#'+FACE_+'#'+XPZK_ as ID_, YUNY_,GGNO_,XPHO_,XPKN_,XPCN_,FACE_,XPZK_,sum(BSZL_) as BSZL_ ,sum(ZPZL_) as ZPZL_ from (select YUNY_,GGNO_,XPHO_,XPKN_,XPCN_,FACE_,XPZK_,case when SFBS_ is not null and SFBS_='2' then ZPZL_ else 0 end as BSZL_ ,ZPZL_ as ZPZL_ from SINO_ZPNGTP where DUIC_ <> 'E' AND#1# ) f group by YUNY_,GGNO_,XPHO_,XPKN_,XPCN_,FACE_,XPZK_) t", meta = "ID_,YUNY_,GGNO_,XPHO_,XPKN_,XPCN_,FACE_,XPZK_,BSZL_,ZPZL_"))
public class FspQE extends QEntity<FspVO> {

	/**
	 * 删除标记
	 */
	@QF
	private String scbj;

	/**
	 * 现品状况
	 */
	@QF
	private String xpzk;

	/**
	 * 现品状况
	 */
	@QF(alias = "XPZK_", operator = EO.IN)
	private String[] xpzks;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * 产量
	 */
	@QF
	private String chan;

	/**
	 * 表面
	 */
	@QF
	private String face;

	/**
	 * 分配余材
	 */
	@QF(alias = "FPYC_", operator = EO.IN)
	private String[] fpycs;

	/**
	 * 尺寸.厚下限
	 */
	@QF(alias = "XPHO_", operator = EO.GE)
	private Double xphoS;

	/**
	 * 尺寸.厚上限
	 */
	@QF(alias = "XPHO_", operator = EO.LE)
	private Double xphoE;

	/**
	 * 尺寸.宽下限
	 */
	@QF(alias = "XPKN_", operator = EO.GE)
	private Double xpknS;

	/**
	 * 尺寸.宽上限
	 */
	@QF(alias = "XPKN_", operator = EO.LE)
	private Double xpknE;

	/**
	 * 尺寸.长下限
	 */
	@QF(alias = "XPCN_", operator = EO.GE)
	private Double xpcnS;

	/**
	 * 尺寸.长上限
	 */
	@QF(alias = "XPCN_", operator = EO.LE)
	private Double xpcnE;

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public String[] getXpzks() {
		return xpzks;
	}

	public void setXpzks(String[] xpzks) {
		this.xpzks = xpzks;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String[] getFpycs() {
		return fpycs;
	}

	public void setFpycs(String[] fpycs) {
		this.fpycs = fpycs;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getXphoS() {
		return xphoS;
	}

	public void setXphoS(Double xphoS) {
		this.xphoS = xphoS;
	}

	public Double getXphoE() {
		return xphoE;
	}

	public void setXphoE(Double xphoE) {
		this.xphoE = xphoE;
	}

	public Double getXpknS() {
		return xpknS;
	}

	public void setXpknS(Double xpknS) {
		this.xpknS = xpknS;
	}

	public Double getXpknE() {
		return xpknE;
	}

	public void setXpknE(Double xpknE) {
		this.xpknE = xpknE;
	}

	public Double getXpcnS() {
		return xpcnS;
	}

	public void setXpcnS(Double xpcnS) {
		this.xpcnS = xpcnS;
	}

	public Double getXpcnE() {
		return xpcnE;
	}

	public void setXpcnE(Double xpcnE) {
		this.xpcnE = xpcnE;
	}

}
