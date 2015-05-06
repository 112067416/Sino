package com.quanta.sino.etl.vo;

import java.util.Date;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.Dmgs;

/**
 * <p>
 * 端板查询条件
 * </p>
 * <p>
 * create: 2010-12-22 上午10:51:51
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Q
public class DmQE extends QEntity<Dmgs> {

	/**
	 * 垫木方向
	 */
	@QF
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@QF
	private String kbao;
	/**
	 * 库位
	 */
	@QF
	private String kw;
	/**
	 * 制品尺寸.宽
	 */
	@QF
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@QF
	private Double cang;

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

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
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

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

}
