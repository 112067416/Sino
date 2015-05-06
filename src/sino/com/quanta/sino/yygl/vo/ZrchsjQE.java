package com.quanta.sino.yygl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 昨日出货实绩查询条件
 * </p>
 * <p>
 * create: 2011-6-12 下午04:40:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select 1 as XH_, CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,CASE WHEN NWAI_='2' THEN MAZL_ ELSE null END as MAZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ ,CYCKNM1_,CYHHNM1_ from SINO_KHYF where DHNO_ not like 'W%' and#1# union all select 1 as XH_, CHRI_, null as CYHHNM_, YSNM_, NAME_,'小计' as DHNO_,null as LINE_,sum(CHSU_) AS CHSU_,sum(CHZL_) AS CHZL_,CASE WHEN NWAI_='2' THEN sum(MAZL_) ELSE null END as MAZL_, SHNM_, ADDR_,null AS HOUU_,null AS KUAN_,null AS CANG_,null AS ZXNO_,null as ID_,null as STAT_,null as CYCKNM_,null as CYCKNM1_,null as CYHHNM1_ from SINO_KHYF where DHNO_ not like 'W%' and#1# group by CHRI_,NAME_,YSNM_,SHNM_,ADDR_,NWAI_ having count(*) > 1 union all select 2 as XH_, CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,CASE WHEN NWAI_='2' THEN MAZL_ ELSE null END as MAZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_,CYCKNM1_,CYHHNM1_ from SINO_KHYF where DHNO_ like 'W%' and#1# union all select 2 as XH_, CHRI_, null as CYHHNM_, YSNM_, NAME_,'小计' as DHNO_,null as LINE_,sum(CHSU_) AS CHSU_,sum(CHZL_) AS CHZL_,CASE WHEN NWAI_='2' THEN sum(MAZL_) ELSE null END as MAZL_, SHNM_, ADDR_,null AS HOUU_,null AS KUAN_,null AS CANG_,null AS ZXNO_,null as ID_,null as STAT_,null as CYCKNM_,null as CYCKNM1_,null as CYHHNM1_ from SINO_KHYF where DHNO_ like 'W%' and#1# group by CHRI_,NAME_,YSNM_,SHNM_,ADDR_,NWAI_ having count(*) > 1) t ", meta = "XH_, CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_,CYCKNM1_,MAZL_,CYHHNM1_"))
public class ZrchsjQE extends QEntity<ZrchsjfVO> {

	/**
	 * 出货开始日
	 */
	@QF(alias = "CHRI_", operator = EO.GE)
	private Date chriS;

	/**
	 * 出货结束日
	 */
	@QF(alias = "CHRI_", operator = EO.LT, addDays = 1)
	private Date chriE;

	/**
	 * 出货重量
	 */
	@QF(alias = "CHZL_", operator = EO.GT)
	private Double chzl;

	/**
	 * 运输公司
	 */
	@QF(alias = "YSGS_")
	private String ysgs;

	public Date getChriS() {
		return chriS;
	}

	public void setChriS(Date chriS) {
		this.chriS = chriS;
	}

	public Date getChriE() {
		return chriE;
	}

	public void setChriE(Date chriE) {
		this.chriE = chriE;
	}

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

}
