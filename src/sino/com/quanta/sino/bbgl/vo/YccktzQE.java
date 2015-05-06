package com.quanta.sino.bbgl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 原材料仓库台帐查询条件
 * </p>
 * <p>
 * create: 2011-8-31 上午11:24:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select convert(varchar(10),RIQI_,120)+'&'+SPBH_ as ID_, NY as NY_,RIQI_, SPBH_,GJZL_,GJYBMY_, GJBS_, LYZL_,LYYBMY_, LYBS_, JCZL_, BSPJC_,YBMYJC_ from SINO_RPT_YCCKTZ  where#1#) t ", meta = "ID_,NY_,RIQI_,SPBH_,GJZL_,GJYBMY_,GJBS_,LYZL_,LYYBMY_,LYBS_,JCZL_,BSPJC_,YBMYJC_"))
public class YccktzQE extends QEntity<YccktzVO> {

	/**
	 * 开始日
	 */
	@QF(alias = "RIQI_", operator = EO.GE)
	private Date riqiS;

	/**
	 * 结束日
	 */
	@QF(alias = "RIQI_", operator = EO.LT, addDays = 1)
	private Date riqiE;

	/**
	 * 日期
	 */
	@QF
	private Date riqi;

	/**
	 * 年月
	 */
	@QF(alias = "NY")
	private String ny;

	/**
	 * 商品编号
	 */
	@QF
	private String spbh;

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

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public Date getRiqi() {
		return riqi;
	}

	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

}
