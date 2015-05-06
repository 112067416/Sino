package com.quanta.sino.etl.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.coco.core.persistence.a.QF;
import com.coco.core.util.NumberUtils;

/**
 * <p>
 * 待生产SL制品
 * </p>
 * <p>
 * create: 2011-1-18 上午09:08:06
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class ZpSLVO {

	/**
	 * COIL.No.
	 */
	@QF(alias = "a.jbno")
	private String jbno;
	/**
	 * <p>
	 * 垫木方向
	 * </p>
	 */
	@QF(alias = "a.dmfx")
	private String dmfx;
	/**
	 * 捆包形式
	 */
	@QF(alias = "a.kbao")
	private String kbao;
	/**
	 * 订货No.行号
	 */
	@QF(alias = "a.dhno")
	private String dhno;

	/**
	 * 指示书No
	 */
	@QF(alias = "(select zsno from YcaiTp b where b.jbno=a.rczpno ) as zsno ")
	private String zsno;
	/**
	 * 厚
	 */
	@QF(alias = "a.houu")
	private Double houu;
	/**
	 * 宽
	 */
	@QF(alias = "a.kuan")
	private Double kuan;
	/**
	 * 长
	 */
	@QF(alias = "a.cang")
	private Double cang;
	/**
	 * 制品重量
	 */
	@QF(alias = "a.zpzl")
	private Integer zpzl;
	/**
	 * 创建时间
	 */
	@QF(alias = "a.crea")
	private Date crea;
	/**
	 * 垫木个数
	 */
	@QF(alias = "(select a.zpzl/(1*b.kbsz*a.bdan)  from DhuoTp b where b.dhno=substring(a.dhno,1,7) and b.line=substring(a.dhno,8,2) ) as dmgs ")
	private Double dmgs;
	/**
	 * lot重量
	 */
	@QF(alias = "(select 1*b.kbsz*a.bdan  from DhuoTp b where b.dhno=substring(a.dhno,1,7) and b.line=substring(a.dhno,8,2) ) as lotzl ")
	private Double lotzl;

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

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

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

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Double getDmgs() {
		return NumberUtils.format(dmgs, 1);

	}

	public void setDmgs(Double dmgs) {
		this.dmgs = dmgs;
	}

	public Integer getLotzl() {
		return NumberUtils.formatDouToInt(lotzl, 0, BigDecimal.ROUND_HALF_UP);
	}

	public void setLotzl(Double lotzl) {
		this.lotzl = lotzl;
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

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

}
