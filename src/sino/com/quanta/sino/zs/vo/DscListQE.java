package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QE;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZsdhTp;

/**
 * <p>
 * 指示书待生产查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:44:17
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q(entities = @QE(clazz = ZsdhTp.class, alias = "a"))
public class DscListQE extends QEntity<ZsdhVO> {
	/**
	 * 指示书NO
	 */
	@QF(operator = EO.LIKE)
	private String zsno;
	/**
	 * 订货No.行号
	 */
	@QF(operator = EO.LIKE)
	private String dhno;
	/**
	 * 用户略称
	 */
	@QF(operator = EO.LIKE)
	private String abbr;

	/**
	 * 状态
	 */
	@QF
	private String stat;
	/**
	 * 指示完了标记
	 */
	@QF
	private String zsbj;
	/**
	 * 作成时间开始日期
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date crea_begin;

	/**
	 * 作成时间结束日期
	 */
	@QF(alias = "crea", operator = EO.LE, addDays = 1)
	private Date crea_end;
	/**
	 * 余材状况
	 */
	@QF(alias = "yczk")
	private String yczk;

	/**
	 * 卷板NO
	 */
	@QF
	private String jbno;

	/**
	 * 出货指示No是否为空
	 */
	@QF
	private String sfdm;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
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

	public String getZsbj() {
		return zsbj;
	}

	public void setZsbj(String zsbj) {
		this.zsbj = zsbj;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getYczk() {
		return yczk;
	}

	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	public String getSfdm() {
		return sfdm;
	}

	public void setSfdm(String sfdm) {
		this.sfdm = sfdm;
	}

}
