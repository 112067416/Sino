package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 剪切材列表视图
 * </p>
 * <p>
 * create: 2011-1-18 上午09:07:15
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class JqcVO {

	/**
	 * 原板制造商代码
	 */
	@QF(alias = "a.zzno")
	private String zzno;
	/**
	 * PILE区分
	 */
	@QF(alias = "a.plqf")
	private String plqf;
	/**
	 * 品种代码
	 */
	@QF(alias = "a.pzno")
	private String pzno;

	/**
	 * 卷板NO
	 */
	@QF(alias = "a.jbno")
	private String jbno;
	/**
	 * 订货号
	 */
	@QF(alias = "a.dhno")
	private String dhno;
	/**
	 * 用户略称
	 */
	@QF(alias = "a.abbr")
	private String abbr;
	/**
	 * 现品厚
	 */
	@QF(alias = "a.xpho")
	private Double xpho;
	/**
	 * 现品宽
	 */
	@QF(alias = "a.xpkn")
	private Double xpkn;
	/**
	 * 现品长
	 */
	@QF(alias = "a.xpcn")
	private Double xpcn;
	/**
	 * 规格代码
	 */
	@QF(alias = "a.ggno")
	private String ggno;
	/**
	 * 表面
	 */
	@QF(alias = "a.face")
	private String face;
	/**
	 * 镀锡量-正面
	 */
	@QF(alias = "a.fuzm")
	private String fuzm;
	/**
	 * 实际镀锡量-反面
	 */
	@QF(alias = "a.fufm")
	private String fufm;
	/**
	 * 实际镀锡量-正面
	 */
	@QF(alias = "a.sczm")
	private Double sczm;
	/**
	 * 镀锡量-反面
	 */
	@QF(alias = "a.scfm")
	private Double scfm;

	/**
	 * 制品重量
	 */
	@QF(alias = "a.zpzl")
	private Integer zpzl;
	/**
	 * ETL实绩日期
	 */
	@QF(alias = "a.etsd")
	private Date etsd;
	/**
	 * 停止标记
	 */
	@QF(alias = "a.ztbj")
	private String ztbj;

	/**
	 * 分配No.
	 */
	@QF(alias = "a.fpno")
	private String fpno;

	/**
	 * 余材状况
	 */
	@QF(alias = "b.yczk")
	private String yczk;

	/**
	 * 等级
	 */
	@QF(alias = "a.deng")
	private String deng;

	/**
	 * 堆场
	 */
	@QF(alias = "a.duic")
	private String duic;

	/**
	 * 状态
	 */
	@QF(alias = "b.stat")
	private String stat;

	/**
	 * 强制分配
	 */
	@QF(alias = "b.qzbj")
	private String qzbj;

	/**
	 * 是否配匹
	 */
	@QF(alias = "b.sfpp")
	private String sfpp;
	/**
	 * 剪边宽变更标记
	 */
	@QF(alias = "b.jbkb")
	private String jbkb;
	/**
	 * 运用规格
	 */
	@QF(alias = "a.yuny")
	private String yuny;
	/**
	 * 制品尺寸.长
	 */
	@QF(alias = "a.cang")
	private Double cang;

	public Double getSczm() {
		return sczm;
	}

	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	public Double getScfm() {
		return scfm;
	}

	public void setScfm(Double scfm) {
		this.scfm = scfm;
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

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public Double getXpcn() {
		return xpcn;
	}

	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
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

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Date getEtsd() {
		return etsd;
	}

	public void setEtsd(Date etsd) {
		this.etsd = etsd;
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

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
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
	 * @return the deng
	 */
	public String getDeng() {
		return deng;
	}

	/**
	 * @param deng
	 *            the deng to set
	 */
	public void setDeng(String deng) {
		this.deng = deng;
	}

	/**
	 * @return the duic
	 */
	public String getDuic() {
		return duic;
	}

	/**
	 * @param duic
	 *            the duic to set
	 */
	public void setDuic(String duic) {
		this.duic = duic;
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