package com.quanta.sino.etl.vo;

/**
 * <p>
 * 保存实绩录入信息
 * </p>
 * <p>
 * create: 2010-12-27 下午02:54:57
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class SjSaveVO {
	/**
	 * 指示书No
	 */
	private String zsno;
	/**
	 * 订货No
	 */
	private String dhno;
	/**
	 * 原材原板NO
	 */
	private String zrjb;
	/**
	 * ETL流水线代码（实绩）
	 */
	private String elin;

	/**
	 * 班
	 */
	private String ban;
	/**
	 * 组
	 */
	private String zu;
	/**
	 * 母卷板终了
	 */
	private Integer stat1;
	/**
	 * 附着量正面
	 */
	private Double sczm;
	/**
	 * 附着量反面
	 */
	private Double scfm;
	/**
	 * 附着量.正面
	 */
	private String fuzm;
	/**
	 * 附着量.反面
	 */
	private String fufm;
	/**
	 * 界面类型，卷板录入类型，S为板材，C为卷材
	 */
	private String mode;
	/**
	 * 装入中止
	 */
	private Integer stat2;
	/**
	 * 卷厚(mm)
	 */
	private Integer jhou;
	/**
	 * 出端COIL No.
	 */
	private String jbno;
	/**
	 * 制品尺寸•厚
	 */
	private Double houu;
	/**
	 * 制品尺寸•宽
	 */
	private Double kuan;
	/**
	 * 卷取长
	 */
	private Integer jbcn;
	/**
	 * CUT长
	 */
	private Integer cutc;
	/**
	 * LOSS长
	 */
	private Integer losc;
	/**
	 * LOSS缺陷
	 */
	private String losq;

	/**
	 * LOSS2长
	 */
	private Integer losc2;
	/**
	 * LOSS2缺陷
	 */
	private String losq2;
	/**
	 * 产量代码
	 */
	private String chan;
	/**
	 * 等级
	 */
	private String deng;
	/**
	 * 处置
	 */
	private String czdm;
	/**
	 * 缺陷
	 */
	private String qqdm;
	/**
	 * 检定员
	 */
	private String jdyn;
	/**
	 * 计数员
	 */
	private String jsyn;
	/**
	 * 录入员
	 */
	private String ddno;
	/**
	 * 出货重量(制品重量)
	 */
	private Integer zpzl;
	/**
	 * P.H个数
	 */
	private Integer phgs;
	/**
	 * 溶接个数
	 */
	private Integer rjet;
	/**
	 * 板厚不良
	 */
	private String bhbl;
	/**
	 * 卷取TRNo.
	 */
	private String jsno;
	/**
	 * 边波纹OP高度
	 */
	private String bopg;
	/**
	 * 边波纹OP间隔
	 */
	private String bopj;
	/**
	 * 边波纹Dr高度
	 */
	private String bdrg;
	/**
	 * 边波纹Dr间隔
	 */
	private String bdrj;
	/**
	 * 中波纹高度
	 */
	private String zbog;
	/**
	 * 中波纹间隔
	 */
	private String zboj;

	/**
	 * 实称重量
	 */
	private Integer sjzl;
	/**
	 * 计算重量
	 */
	private Integer jszl;
	/**
	 * 原板制品商代码
	 */
	private String zzno;
	/**
	 * 总装入重量
	 */
	private Integer allzrzl;
	/**
	 * 总产出量
	 */
	private Integer allzpzl;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 用户代码
	 */
	private String user;
	/**
	 * 是否废品
	 */
	private String isfp;
	/**
	 * 品种
	 */
	private String pzno;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getIsfp() {
		return isfp;
	}

	public void setIsfp(String isfp) {
		this.isfp = isfp;
	}

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

	public Integer getAllzrzl() {
		return allzrzl;
	}

	public void setAllzrzl(Integer allzrzl) {
		this.allzrzl = allzrzl;
	}

	public Integer getAllzpzl() {
		return allzpzl;
	}

	public void setAllzpzl(Integer allzpzl) {
		this.allzpzl = allzpzl;
	}

	public Integer getJszl() {
		return jszl;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	/**
	 * 计算长
	 */
	// private Integer jsch;
	/**
	 * 指示书的分配等级
	 */
	// private String fpdj;
	/**
	 * 捆包指定重量.上限
	 */
	// private Double kbzs;
	/**
	 * 捆包指定重量.下限
	 */
	// private Double kbzx;
	/**
	 * 销售溶接部不可
	 */
	// private String rjie;
	/**
	 * 净重量
	 */
	// private Integer jnzl;
	/**
	 * 计算重量
	 */
	// private Integer jszl;
	/**
	 * 毛重量
	 */
	// private Integer mazl;

	public String getElin() {
		return elin;
	}

	public void setElin(String elin) {
		this.elin = elin;
	}

	public Integer getStat1() {
		return stat1;
	}

	public void setStat1(Integer stat1) {
		this.stat1 = stat1;
	}

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

	public Integer getStat2() {
		return stat2;
	}

	public void setStat2(Integer stat2) {
		this.stat2 = stat2;
	}

	public Integer getJhou() {
		return jhou;
	}

	public void setJhou(Integer jhou) {
		this.jhou = jhou;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
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

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	public Integer getCutc() {
		return cutc;
	}

	public void setCutc(Integer cutc) {
		this.cutc = cutc;
	}

	public Integer getLosc() {
		return losc;
	}

	public void setLosc(Integer losc) {
		this.losc = losc;
	}

	public String getLosq() {
		return losq;
	}

	public void setLosq(String losq) {
		this.losq = losq;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getCzdm() {
		return czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	public String getQqdm() {
		return qqdm;
	}

	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	public String getJdyn() {
		return jdyn;
	}

	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	public String getJsyn() {
		return jsyn;
	}

	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Integer getPhgs() {
		return phgs;
	}

	public void setPhgs(Integer phgs) {
		this.phgs = phgs;
	}

	public Integer getRjet() {
		return rjet;
	}

	public void setRjet(Integer rjet) {
		this.rjet = rjet;
	}

	public String getBhbl() {
		return bhbl;
	}

	public void setBhbl(String bhbl) {
		this.bhbl = bhbl;
	}

	public String getJsno() {
		return jsno;
	}

	public void setJsno(String jsno) {
		this.jsno = jsno;
	}

	public String getBopj() {
		return bopj;
	}

	public void setBopj(String bopj) {
		this.bopj = bopj;
	}

	public String getBdrj() {
		return bdrj;
	}

	public void setBdrj(String bdrj) {
		this.bdrj = bdrj;
	}

	public String getBopg() {
		return bopg;
	}

	public void setBopg(String bopg) {
		this.bopg = bopg;
	}

	public String getBdrg() {
		return bdrg;
	}

	public void setBdrg(String bdrg) {
		this.bdrg = bdrg;
	}

	public String getZbog() {
		return zbog;
	}

	public void setZbog(String zbog) {
		this.zbog = zbog;
	}

	public String getZboj() {
		return zboj;
	}

	public void setZboj(String zboj) {
		this.zboj = zboj;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getZrjb() {
		return zrjb;
	}

	public void setZrjb(String zrjb) {
		this.zrjb = zrjb;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public Integer getLosc2() {
		return losc2;
	}

	public void setLosc2(Integer losc2) {
		this.losc2 = losc2;
	}

	public String getLosq2() {
		return losq2;
	}

	public void setLosq2(String losq2) {
		this.losq2 = losq2;
	}

}