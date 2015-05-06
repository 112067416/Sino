package com.quanta.sino.dh.vo;

import java.util.Date;

/**
 * <p>
 * 订货进度VO
 * </p>
 * <p>
 * create: 2011-3-4 下午04:01:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhuoVO {

	/**
	 * 用户代码
	 */
	private String user;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 交货期
	 */
	private Date jhqi;

	/**
	 * 订货No
	 */
	private String dhno;

	/**
	 * 行号
	 */
	private String line;

	/**
	 * 品种
	 */
	private String pzno;

	/**
	 * 规格代码
	 */
	private String ggno;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 附着量.单位
	 */
	private String fudw;

	/**
	 * 附着量.正面
	 */
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	private String fufm;

	/**
	 * 订货尺寸.厚
	 */
	private Double houu;

	/**
	 * 订货尺寸.宽
	 */
	private Double kuan;

	/**
	 * 订货尺寸.长
	 */
	private Double cang;

	/**
	 * 表面加工精度
	 */
	private String face;

	/**
	 * 压方
	 */
	private String yyan;

	/**
	 * S足
	 */
	private String dmfx;

	/**
	 * 合同重量（吨）
	 */
	private Double htzl;

	/**
	 * 交货区分
	 */
	private String jhqf;

	/**
	 * 交货区分.允许负值
	 */
	private Integer jhfz;

	/**
	 * 交货区分.允许正值
	 */
	private Integer jhzz;

	/**
	 * 分配量(吨)
	 */
	private Double fpln;

	/**
	 * ETL未生产
	 */
	private Double etlw;

	/**
	 * ETL合格量(吨)
	 */
	private Double etlh;

	/**
	 * SL未生产
	 */
	private Double slw;

	/**
	 * SL合格量(吨)
	 */
	private Double slhg;

	/**
	 * 出货实绩量
	 */
	private Double chus;

	/**
	 * 捆包实绩量
	 */
	private Double kbus;

	/**
	 * 库存量
	 */
	private Double kcus;

	/**
	 * 退货实绩量
	 */
	private Double thus;

	/**
	 * 营业员
	 */
	private String ddnm;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJhqi() {
		return jhqi;
	}

	public void setJhqi(Date jhqi) {
		this.jhqi = jhqi;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
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

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getYyan() {
		return yyan;
	}

	public void setYyan(String yyan) {
		this.yyan = yyan;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public String getJhqf() {
		return jhqf;
	}

	public void setJhqf(String jhqf) {
		this.jhqf = jhqf;
	}

	public Integer getJhfz() {
		return jhfz;
	}

	public void setJhfz(Integer jhfz) {
		this.jhfz = jhfz;
	}

	public Integer getJhzz() {
		return jhzz;
	}

	public void setJhzz(Integer jhzz) {
		this.jhzz = jhzz;
	}

	public Double getFpln() {
		return fpln;
	}

	public void setFpln(Double fpln) {
		this.fpln = fpln;
	}

	public Double getEtlw() {
		return etlw;
	}

	public void setEtlw(Double etlw) {
		this.etlw = etlw;
	}

	public Double getEtlh() {
		return etlh;
	}

	public void setEtlh(Double etlh) {
		this.etlh = etlh;
	}

	public Double getSlw() {
		return slw;
	}

	public void setSlw(Double slw) {
		this.slw = slw;
	}

	public Double getSlhg() {
		return slhg;
	}

	public void setSlhg(Double slhg) {
		this.slhg = slhg;
	}

	public Double getChus() {
		return chus;
	}

	public void setChus(Double chus) {
		this.chus = chus;
	}

	public Double getKbus() {
		return kbus;
	}

	public void setKbus(Double kbus) {
		this.kbus = kbus;
	}

	public Double getKcus() {
		return kcus;
	}

	public void setKcus(Double kcus) {
		this.kcus = kcus;
	}

	public Double getThus() {
		return thus;
	}

	public void setThus(Double thus) {
		this.thus = thus;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

}
