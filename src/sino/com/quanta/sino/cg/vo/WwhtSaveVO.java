package com.quanta.sino.cg.vo;

import java.util.Date;

import com.quanta.sino.orm.WwhtTp;

/**
 * <p>
 * 保存和更新下拉框信息
 * </p>
 * <p>
 * create: 2010-12-27 下午02:54:57
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class WwhtSaveVO {
	/**
	 * 供应商合同NO
	 */
	private String htno;
	/**
	 * 供应商合同行号
	 */
	private String line;
	/**
	 * 签约日期
	 */
	private Date qyri;
	/**
	 * 制造商key
	 */
	private String zzsd;
	/**
	 * 制造商名称
	 */
	private String zzsm;
	/**
	 * 币种
	 */
	private String thqf;
	/**
	 * 商社key
	 */
	private String ssno;
	/**
	 * 商社名称
	 */
	private String ssnm;
	/**
	 * 合同日
	 */
	private Date htdt;
	/**
	 * 制造商规格略称
	 */
	private String zzgg;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 品种
	 */
	private String pzno;
	/**
	 * 尺寸.厚
	 */
	private Double houu;
	/**
	 * 尺寸.宽
	 */
	private Double kuan;
	/**
	 * 重量（吨）
	 */
	private Double htzl;
	/**
	 * 等级
	 */
	private String fpdj;
	/**
	 * 表面
	 */
	private String face;
	/**
	 * 单价
	 */
	private Double htdj;
	/**
	 * 内径
	 */
	private Integer neij;
	/**
	 * 合同完成状态
	 */
	private String qywl;

	/**
	 * 
	 */
	private WwhtTp[] items;

	public String getHtno() {
		return htno;
	}

	public void setHtno(String htno) {
		this.htno = htno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Date getQyri() {
		return qyri;
	}

	public void setQyri(Date qyri) {
		this.qyri = qyri;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getZzsm() {
		return zzsm;
	}

	public void setZzsm(String zzsm) {
		this.zzsm = zzsm;
	}

	public String getThqf() {
		return thqf;
	}

	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public String getSsnm() {
		return ssnm;
	}

	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	public Date getHtdt() {
		return htdt;
	}

	public void setHtdt(Date htdt) {
		this.htdt = htdt;
	}

	public String getZzgg() {
		return zzgg;
	}

	public void setZzgg(String zzgg) {
		this.zzgg = zzgg;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
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

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getHtdj() {
		return htdj;
	}

	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

	public Integer getNeij() {
		return neij;
	}

	public void setNeij(Integer neij) {
		this.neij = neij;
	}

	public String getQywl() {
		return qywl;
	}

	public void setQywl(String qywl) {
		this.qywl = qywl;
	}

	public WwhtTp[] getItems() {
		return items;
	}

	public void setItems(WwhtTp[] items) {
		this.items = items;
	}

}