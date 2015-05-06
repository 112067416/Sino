package com.quanta.sino.ycai.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.JchaTp;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 下午05:58:56
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Q
public class JchatpQE extends QEntity<JchaTp> {

	/**
	 * 原板用户略称
	 */
	@QF(alias = "ycaiTp.ycbr", operator = EO.LIKE)
	private String ycbr;

	/**
	 * 船名
	 */
	@QF(alias = "ycaiTp.ship", operator = EO.LIKE)
	private String ship;

	/**
	 * 原材卷板NO
	 */
	@QF(alias = "ycaiTp.jbno")
	private String jbno;

	/**
	 * 原材卷板NO。开始
	 */
	@QF(alias = "ycaiTp.jbno", operator = EO.GE)
	private String jbnoBegin;

	/**
	 * 原材卷板NO。终止
	 */
	@QF(alias = "ycaiTp.jbno", operator = EO.LE)
	private String jbnoEnd;

	/**
	 * 供应商合同NO
	 */
	@QF(alias = "ycaiTp.ybno")
	private String ybno;

	/**
	 * 供应商合同 行号
	 */
	@QF(alias = "ycaiTp.line")
	private String line;

	/**
	 * 现品厚
	 */
	@QF(alias = "ycaiTp.xpho")
	private Double xpho;

	/**
	 * 现品宽
	 */
	@QF(alias = "ycaiTp.xpkn")
	private Double xpkn;

	/**
	 * 表面
	 */
	@QF(alias = "ycaiTp.face")
	private String face;

	public String getYcbr() {
		return ycbr;
	}

	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getJbnoBegin() {
		return jbnoBegin;
	}

	public void setJbnoBegin(String jbnoBegin) {
		this.jbnoBegin = jbnoBegin;
	}

	public String getJbnoEnd() {
		return jbnoEnd;
	}

	public void setJbnoEnd(String jbnoEnd) {
		this.jbnoEnd = jbnoEnd;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

}
