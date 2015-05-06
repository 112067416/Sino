package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 指示对象VO
 * </p>
 * <p>
 * create: 2010-12-28 下午05:45:39
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class ZsdxVO {

	/**
	 * 卷板No
	 */
	@QF(alias = "a.jbno")
	private String jbno;

	/**
	 * 订货No.行号
	 */
	@QF(alias = "a.dhno")
	private String dhno;

	/**
	 * 分配No
	 */
	@QF(alias = "a.fpno")
	private String fpno;

	/**
	 * 剪边宽变更标记
	 */
	@QF(alias = "a.jbkb")
	private String jbkb;

	/**
	 * 原板等级
	 */
	@QF(alias = "b.deng")
	private String deng;

	/**
	 * 运用规格
	 */
	@QF(alias = "b.yuny")
	private String yuny;

	/**
	 * 现品尺寸.厚
	 */
	@QF(alias = "b.xpho")
	private Double xpho;

	/**
	 * 现品尺寸.宽
	 */
	@QF(alias = "b.xpkn")
	private Double xpkn;

	/**
	 * 用户略称
	 */
	@QF(alias = "b.abbr")
	private String abbr;

	/**
	 * 制品重量
	 */
	@QF(alias = "b.zpzl")
	private Integer zpzl;

	/**
	 * 表面
	 */
	@QF(alias = "b.face")
	private String face;

	/**
	 * 堆场
	 */
	@QF(alias = "b.duic")
	private String duic;
	/**
	 * 库位
	 */
	@QF(alias = "b.kw")
	private String kw;

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	/**
	 * 作业停止标志
	 */
	@QF(alias = "b.ztbj")
	private String ztbj;

	/**
	 * 作成时间
	 */
	@QF(alias = "a.crea")
	private Date crea;

	/**
	 * 原板制造商No
	 */
	@QF(alias = "b.zzsd")
	private String zzsd;

	/**
	 * 余材状况
	 */
	@QF(alias = "a.yczk")
	private String yczk;

	/**
	 * 状态
	 */
	@QF(alias = "a.stat")
	private String stat;

	/**
	 * 强制分配标识
	 */
	@QF(alias = "a.qzbj")
	private String qzbj;

	/**
	 * 是否配匹
	 */
	@QF(alias = "a.sfpp")
	private String sfpp;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		if (yuny == null) {
			this.yuny = "";
		}
		else {
			this.yuny = yuny;
		}
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getFace() {

		return face;
	}

	public void setFace(String face) {
		if (face == null) {
			this.face = "";
		}
		else {
			this.face = face;
		}
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		if (ztbj == null) {
			this.ztbj = "";
		}
		else {
			this.ztbj = ztbj;
		}

	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		if (xpho == null) {
			this.xpho = 0.0;
		}
		else {
			this.xpho = xpho;
		}
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		if (xpkn == null) {
			this.xpkn = 0.0;
		}
		else {
			this.xpkn = xpkn;
		}
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

	public String getJbkb() {
		return jbkb;
	}

	public void setJbkb(String jbkb) {
		if (jbkb == null) {
			this.jbkb = "0";
		}
		else {
			this.jbkb = jbkb;
		}
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	/**
	 * @return the yczk
	 */
	public String getYczk() {
		return yczk;
	}

	/**
	 * @param yczk
	 *            the yczk to set
	 */
	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the qzbj
	 */
	public String getQzbj() {
		return qzbj;
	}

	/**
	 * @param qzbj
	 *            the qzbj to set
	 */
	public void setQzbj(String qzbj) {
		this.qzbj = qzbj;
	}

	/**
	 * @return the sfpp
	 */
	public String getSfpp() {
		return sfpp;
	}

	/**
	 * @param sfpp
	 *            the sfpp to set
	 */
	public void setSfpp(String sfpp) {
		this.sfpp = sfpp;
	}
}
