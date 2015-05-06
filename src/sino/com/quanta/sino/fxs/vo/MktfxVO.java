package com.quanta.sino.fxs.vo;

import java.util.Date;

/**
 * <p>
 * 马口铁分析VO
 * </p>
 * <p>
 * create: 2011-5-9 上午12:35:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class MktfxVO {

	/**
	 * 
	 */
	private String fuzm;

	/**
	 * 
	 */
	private String fufm;

	/**
	 * 
	 */
	private String ytyp;

	/**
	 * 
	 */
	private String yqty;

	/**
	 * 
	 */
	private Date fxr;

	/**
	 * 
	 */
	private String jbno;

	/**
	 * 
	 */
	private String zzsd;

	/**
	 *  
	 */
	private String cyrzId;

	/**
	 * 附着面.正面.上限值
	 */
	private Double fuzs;

	/**
	 * 附着面.正面.下限值
	 */
	private Double fuzx;

	/**
	 * 附着面.反面.上限值
	 */
	private Double fufs;

	/**
	 * 附着面.反面.下限值
	 */
	private Double fufx;

	/**
	 * 铬上限值
	 */
	private Double crs;

	/**
	 * 铬下限值
	 */
	private Double crx;

	/**
	 * 涂油上限值
	 */
	private Double tys;

	/**
	 * 涂油下限值
	 */
	private Double tyx;

	/**
	 * 合金层
	 */
	private Double hjin;

	/**
	 * TCV正面.上限值
	 */
	private Double tcvzs;

	/**
	 * TCV正面.下限值
	 */
	private Double tcvzx;

	/**
	 * TCV反面.上限值
	 */
	private Double tcvfs;

	/**
	 * TCV反面.下限值
	 */
	private Double tcvfx;

	/**
	 * ATC上限值(ATC范围：0<x<=0.12)
	 */
	private final Double atcs = 0.12;

	/**
	 * ATC下限值
	 */
	private final Double atcx = 0.0001;

	/**
	 * ISV上限值(ISV范围：0<=x<=3)
	 */
	private Double isvs = 3d;

	/**
	 * ISV下限值
	 */
	private Double isvx = 0d;

	/**
	 * TCS上限值(TCS范围：6.5<=x<=8.5)
	 */
	private Double tcss = 8.5;

	/**
	 * TCS下限值
	 */
	private Double tcsx = 6.5;

	/**
	 * Epon Flow上限值(Epon Flow范围：1<=x<=3)
	 */
	private Double epfs = 3d;

	/**
	 * Epon Flow下限值
	 */
	private Double epfx = 1d;

	/**
	 * 污点上限值(污点范围：1<=x<=3)
	 */
	private Double wds = 3d;

	/**
	 * 污点下限值
	 */
	private Double wdx = 1d;

	/**
	 * 密着性上限值(密着性范围：0<=x<=3)
	 */
	private Double mzxs = 3d;

	/**
	 * 密着性下限值
	 */
	private Double mzxx = 0d;

	/**
	 * PORE上限值(PORE范围：0<=x<=13)
	 */
	private Double pes = 13d;

	/**
	 * PORE下限值
	 */
	private Double pex = 0d;

	/**
	 * PLT上限值(PLT范围：0<=x<=10)
	 */
	private Double plts = 10d;

	/**
	 * PLT下限值
	 */
	private Double pltx = 0d;

	private String zxbz;

	private String fxbz;

	private String hjbz;

	private String tybz;

	private String crbz;

	/**
	 * tcv正面备注
	 */
	private String tcvzbz;

	/**
	 * tcv反面备注
	 */
	private String tcvfbz;

	private String atcz = "0 <= x <= 0.12";;

	private String isvz = "0 <= x <= 3";;

	private String tcsbz = "6.5 <= x <= 8.5";;

	private String epbz = "1 <= x <= 3";;

	private String wdbz = "1 <= x <= 3";

	private String mzbz = "0 <= x <= 3";

	private String pebz = "0 <= x <= 13";

	private String ptbz = "0 <= x <=10";

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

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public Date getFxr() {
		return fxr;
	}

	public void setFxr(Date fxr) {
		this.fxr = fxr;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getCyrzId() {
		return cyrzId;
	}

	public void setCyrzId(String cyrzId) {
		this.cyrzId = cyrzId;
	}

	public Double getFuzs() {
		return fuzs;
	}

	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	public Double getFuzx() {
		return fuzx;
	}

	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	public Double getFufs() {
		return fufs;
	}

	public void setFufs(Double fufs) {
		this.fufs = fufs;
	}

	public Double getFufx() {
		return fufx;
	}

	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	public Double getCrs() {
		return crs;
	}

	public void setCrs(Double crs) {
		this.crs = crs;
	}

	public Double getCrx() {
		return crx;
	}

	public void setCrx(Double crx) {
		this.crx = crx;
	}

	public Double getTys() {
		return tys;
	}

	public void setTys(Double tys) {
		this.tys = tys;
	}

	public Double getTyx() {
		return tyx;
	}

	public void setTyx(Double tyx) {
		this.tyx = tyx;
	}

	public Double getHjin() {
		return hjin;
	}

	public void setHjin(Double hjin) {
		this.hjin = hjin;
	}

	public Double getTcvzs() {
		return tcvzs;
	}

	public void setTcvzs(Double tcvzs) {
		this.tcvzs = tcvzs;
	}

	public Double getTcvzx() {
		return tcvzx;
	}

	public void setTcvzx(Double tcvzx) {
		this.tcvzx = tcvzx;
	}

	public Double getTcvfs() {
		return tcvfs;
	}

	public void setTcvfs(Double tcvfs) {
		this.tcvfs = tcvfs;
	}

	public Double getTcvfx() {
		return tcvfx;
	}

	public void setTcvfx(Double tcvfx) {
		this.tcvfx = tcvfx;
	}

	public Double getIsvs() {
		return isvs;
	}

	public void setIsvs(Double isvs) {
		this.isvs = isvs;
	}

	public Double getIsvx() {
		return isvx;
	}

	public void setIsvx(Double isvx) {
		this.isvx = isvx;
	}

	public Double getTcss() {
		return tcss;
	}

	public void setTcss(Double tcss) {
		this.tcss = tcss;
	}

	public Double getTcsx() {
		return tcsx;
	}

	public void setTcsx(Double tcsx) {
		this.tcsx = tcsx;
	}

	public Double getEpfs() {
		return epfs;
	}

	public void setEpfs(Double epfs) {
		this.epfs = epfs;
	}

	public Double getEpfx() {
		return epfx;
	}

	public void setEpfx(Double epfx) {
		this.epfx = epfx;
	}

	public Double getWds() {
		return wds;
	}

	public void setWds(Double wds) {
		this.wds = wds;
	}

	public Double getWdx() {
		return wdx;
	}

	public void setWdx(Double wdx) {
		this.wdx = wdx;
	}

	public Double getMzxs() {
		return mzxs;
	}

	public void setMzxs(Double mzxs) {
		this.mzxs = mzxs;
	}

	public Double getMzxx() {
		return mzxx;
	}

	public void setMzxx(Double mzxx) {
		this.mzxx = mzxx;
	}

	public Double getPes() {
		return pes;
	}

	public void setPes(Double pes) {
		this.pes = pes;
	}

	public Double getPex() {
		return pex;
	}

	public void setPex(Double pex) {
		this.pex = pex;
	}

	public Double getPlts() {
		return plts;
	}

	public void setPlts(Double plts) {
		this.plts = plts;
	}

	public Double getPltx() {
		return pltx;
	}

	public void setPltx(Double pltx) {
		this.pltx = pltx;
	}

	public String getZxbz() {
		return zxbz;
	}

	public void setZxbz(String zxbz) {
		this.zxbz = zxbz;
	}

	public String getFxbz() {
		return fxbz;
	}

	public void setFxbz(String fxbz) {
		this.fxbz = fxbz;
	}

	public String getHjbz() {
		return hjbz;
	}

	public void setHjbz(String hjbz) {
		this.hjbz = hjbz;
	}

	public String getTybz() {
		return tybz;
	}

	public void setTybz(String tybz) {
		this.tybz = tybz;
	}

	public String getCrbz() {
		return crbz;
	}

	public void setCrbz(String crbz) {
		this.crbz = crbz;
	}

	public String getTcvzbz() {
		return tcvzbz;
	}

	public void setTcvzbz(String tcvzbz) {
		this.tcvzbz = tcvzbz;
	}

	public String getTcvfbz() {
		return tcvfbz;
	}

	public void setTcvfbz(String tcvfbz) {
		this.tcvfbz = tcvfbz;
	}

	public String getAtcz() {
		return atcz;
	}

	public void setAtcz(String atcz) {
		this.atcz = atcz;
	}

	public String getIsvz() {
		return isvz;
	}

	public void setIsvz(String isvz) {
		this.isvz = isvz;
	}

	public String getTcsbz() {
		return tcsbz;
	}

	public void setTcsbz(String tcsbz) {
		this.tcsbz = tcsbz;
	}

	public String getEpbz() {
		return epbz;
	}

	public void setEpbz(String epbz) {
		this.epbz = epbz;
	}

	public String getWdbz() {
		return wdbz;
	}

	public void setWdbz(String wdbz) {
		this.wdbz = wdbz;
	}

	public String getMzbz() {
		return mzbz;
	}

	public void setMzbz(String mzbz) {
		this.mzbz = mzbz;
	}

	public String getPebz() {
		return pebz;
	}

	public void setPebz(String pebz) {
		this.pebz = pebz;
	}

	public String getPtbz() {
		return ptbz;
	}

	public void setPtbz(String ptbz) {
		this.ptbz = ptbz;
	}

	public Double getAtcs() {
		return atcs;
	}

	public Double getAtcx() {
		return atcx;
	}

}
