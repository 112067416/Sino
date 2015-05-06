package com.quanta.sino.bbgl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 成品出库台帐查询条件
 * </p>
 * <p>
 * create: 2011-8-31 上午11:24:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select convert(varchar(10),RIQI,120)+'&'+CHOU+'&'+CPLB as ID_,NY as NY_,RIQI as RIQI_,CPLB as CPLB_,CHOU as CHOU_,QBCH1 as QBCH1_,QBYBCH1 as QBYBCH1_,QBBSCH1 as QBBSCH1_,QBFH1 as QBFH1_,QBYBFH1 as QBYBFH1_,QBBSFH1 as QBBSFH1_,QBJC1 as QBJC1_,QBYBJC1 as QBYBJC1_,QBBSJC1 as QBBSJC1_,CHE as CHE_,QBYBCHE as QBYBCHE_,QBBSCHE as QBBSCHE_,FHE as FHE_,QBYBFHE as QBYBFHE_,QBBSFHE as QBBSFHE_,QBNZW as QBNZW_,QBWZN as QBWZN_ from SINO_RPT_CPCKTZ where#1#) t ", meta = "ID_,NY_,RIQI_,CPLB_,CHOU_,QBCH1_,QBYBCH1_,QBBSCH1_,QBFH1_,QBYBFH1_,QBBSFH1_,QBJC1_,QBYBJC1_,QBBSJC1_,CHE_,QBYBCHE_,QBBSCHE_,FHE_,QBYBFHE_,QBBSFHE_,QBNZW_,QBWZN_"))
public class CpcktzQE extends QEntity<CpcktzVO> {

	/**
	 * 开始日
	 */
	@QF(alias = "RIQI", operator = EO.GE)
	private Date riqiS;

	/**
	 * 结束日
	 */
	@QF(alias = "RIQI", operator = EO.LT, addDays = 1)
	private Date riqiE;

	/**
	 * 日期
	 */
	@QF(alias = "RIQI")
	private Date riqi;

	/**
	 * 年月
	 */
	@QF(alias = "NY")
	private String ny;

	/**
	 * 商品编号
	 */
	@QF(alias = "CHOU")
	private String chou;

	/**
	 * 成品类别
	 */
	@QF(alias = "CPLB")
	private String cplb;

	public Date getRiqiS() {
		return riqiS;
	}

	public void setRiqiS(Date riqiS) {
		this.riqiS = riqiS;
	}

	public Date getRiqiE() {
		return riqiE;
	}

	public void setRiqiE(Date riqiE) {
		this.riqiE = riqiE;
	}

	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public String getChou() {
		return chou;
	}

	public void setChou(String chou) {
		this.chou = chou;
	}

	public String getCplb() {
		return cplb;
	}

	public void setCplb(String cplb) {
		this.cplb = cplb;
	}

}
