package com.quanta.sino.sl.vo;

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
public class SLSjSaveVO {
	/**
	 * 出端COIL No.
	 */
	private String jbno;
	/**
	 * 指示书No
	 */
	private String zsno;
	/**
	 * 入端NO
	 */
	private String rczpno;
	/**
	 * sl流水线代码（实绩）
	 */
	private String slin;
	/**
	 * 制造商代码
	 */
	private String zzno;
	/**
	 * 制品尺寸•厚
	 */
	private Double houu;
	/**
	 * 制品尺寸•宽
	 */
	private Double kuan;
	/**
	 * 制品尺寸•长
	 */
	private Double cang;
	/**
	 * 薄板单重
	 */
	private Double bdan;
	/**
	 * 班
	 */
	private String ban;
	/**
	 * 组
	 */
	private String zu;
	/**
	 * 实绩附着量正
	 */
	private Double sczm;
	/**
	 * 实绩附着量正反
	 */
	private Double scfm;
	/**
	 * 母卷终了
	 */
	private Integer zl;
	/**
	 * 装入中止
	 */
	private Integer zz;
	/**
	 * 卷厚
	 */
	private Integer jh;
	/**
	 * 硬度
	 */
	private Integer ying;
	/**
	 * 端板标记
	 */
	private Integer dbbj;
	/**
	 * 计算重量
	 */
	private Integer jszl;
	/**
	 * 实际重量
	 */
	private Integer sjzl;
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
	 * 主缺
	 */
	private String qqdm;
	/**
	 * 缺陷2
	 */
	private String qqd2;
	/**
	 * 缺陷3
	 */
	private String qqd3;
	/**
	 * 检定员
	 */
	private String jdyn;
	/**
	 * 计数员
	 */
	private String jsyn;

	/**
	 * 枚数
	 */
	private Integer zshu;
	/**
	 * D(A)-MARK
	 */
	private String dmrk;
	/**
	 * PILER
	 */
	private String cqpl;
	/**
	 * 录入员
	 */
	private String ddno;
	/**
	 * 实测宽
	 */
	private String sckn;
	/**
	 * 实测剪断长
	 */
	private String jdcn;
	/**
	 * LINE-SPEED
	 */
	private Integer lnsd;
	/**
	 * 长分布1
	 */
	private Integer cm05;
	/**
	 * 长分布2
	 */
	private Integer cp00;
	/**
	 * 长分布3
	 */
	private Integer cp05;
	/**
	 * 长分布4
	 */
	private Integer cp10;
	/**
	 * 长分布5
	 */
	private Integer cp15;
	/**
	 * 耳波op高度
	 */
	private String bopg;
	/**
	 * 耳波op间隔
	 */
	private String bopj;
	/**
	 * 耳波Dr高度
	 */
	private String bdrg;
	/**
	 * 耳波Dr间隔
	 */
	private String bdrj;
	/**
	 * 耳波等级
	 */
	private String bdji;
	/**
	 * 直角Op
	 */
	private String zopz;
	/**
	 * 直角Dr
	 */
	private String zdrz;
	/**
	 * L反
	 */
	private String zndz;
	/**
	 * C反
	 */
	private String hndz;
	/**
	 * 中伸高度
	 */
	private String zbog;
	/**
	 * 中伸间隔
	 */
	private String zboj;
	/**
	 * 翘度
	 */
	private String qduz;
	/**
	 * 毛边上
	 */
	private String maos;
	/**
	 * 毛边下
	 */
	private String maox;

	/**
	 * 修边毛边OP
	 */
	private String maop;

	/**
	 * 修边毛边DR
	 */
	private String madr;

	/**
	 * 矢切量
	 */
	private String siql;
	/**
	 * 镜面检查
	 */
	private String jmjc;
	/**
	 * 垫足确认
	 */
	private String dmqr;
	/**
	 * 针孔确认
	 */
	private String zkqr;
	/**
	 * 净重量 JNZL
	 */
	private Integer jnzl;
	/**
	 * 制品重量
	 */
	private Integer zpzl;
	/**
	 * 装入重量
	 */
	private Integer zrzl;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 制品商代码
	 */
	private String zzsd;
	/**
	 * 用户代码
	 */
	private String user;
	/**
	 * 删除标志
	 */
	private String scbj;
	/**
	 * 确认标志
	 */
	private String qdbj;
	/**
	 * 特记
	 */
	private String vari;

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getQdbj() {
		return qdbj;
	}

	public void setQdbj(String qdbj) {
		this.qdbj = qdbj;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getJnzl() {
		return jnzl;
	}

	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public Integer getZrzl() {
		return zrzl;
	}

	public void setZrzl(Integer zrzl) {
		this.zrzl = zrzl;
	}

	public Integer getZl() {
		return zl;
	}

	public void setZl(Integer zl) {
		this.zl = zl;
	}

	public Integer getZz() {
		return zz;
	}

	public void setZz(Integer zz) {
		this.zz = zz;
	}

	public Integer getJh() {
		return jh;
	}

	public void setJh(Integer jh) {
		this.jh = jh;
	}

	public Integer getDbbj() {
		return dbbj;
	}

	public void setDbbj(Integer dbbj) {
		this.dbbj = dbbj;
	}

	public Integer getJszl() {
		return jszl;
	}

	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
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

	public String getQqd2() {
		return qqd2;
	}

	public void setQqd2(String qqd2) {
		this.qqd2 = qqd2;
	}

	public String getQqd3() {
		return qqd3;
	}

	public void setQqd3(String qqd3) {
		this.qqd3 = qqd3;
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

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public String getDmrk() {
		return dmrk;
	}

	public void setDmrk(String dmrk) {
		this.dmrk = dmrk;
	}

	public String getCqpl() {
		return cqpl;
	}

	public void setCqpl(String cqpl) {
		this.cqpl = cqpl;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getSckn() {
		return sckn;
	}

	public void setSckn(String sckn) {
		this.sckn = sckn;
	}

	public String getJdcn() {
		return jdcn;
	}

	public void setJdcn(String jdcn) {
		this.jdcn = jdcn;
	}

	public Integer getLnsd() {
		return lnsd;
	}

	public void setLnsd(Integer lnsd) {
		this.lnsd = lnsd;
	}

	public Integer getCm05() {
		return cm05;
	}

	public void setCm05(Integer cm05) {
		this.cm05 = cm05;
	}

	public Integer getCp00() {
		return cp00;
	}

	public void setCp00(Integer cp00) {
		this.cp00 = cp00;
	}

	public Integer getCp05() {
		return cp05;
	}

	public void setCp05(Integer cp05) {
		this.cp05 = cp05;
	}

	public Integer getCp10() {
		return cp10;
	}

	public void setCp10(Integer cp10) {
		this.cp10 = cp10;
	}

	public Integer getCp15() {
		return cp15;
	}

	public void setCp15(Integer cp15) {
		this.cp15 = cp15;
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

	public String getBdji() {
		return bdji;
	}

	public void setBdji(String bdji) {
		this.bdji = bdji;
	}

	public String getZopz() {
		return zopz;
	}

	public void setZopz(String zopz) {
		this.zopz = zopz;
	}

	public String getZdrz() {
		return zdrz;
	}

	public void setZdrz(String zdrz) {
		this.zdrz = zdrz;
	}

	public String getZndz() {
		return zndz;
	}

	public void setZndz(String zndz) {
		this.zndz = zndz;
	}

	public String getHndz() {
		return hndz;
	}

	public void setHndz(String hndz) {
		this.hndz = hndz;
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

	public String getQduz() {
		return qduz;
	}

	public void setQduz(String qduz) {
		this.qduz = qduz;
	}

	public String getMaos() {
		return maos;
	}

	public void setMaos(String maos) {
		this.maos = maos;
	}

	public String getMaox() {
		return maox;
	}

	public void setMaox(String maox) {
		this.maox = maox;
	}

	public String getSiql() {
		return siql;
	}

	public void setSiql(String siql) {
		this.siql = siql;
	}

	public String getJmjc() {
		return jmjc;
	}

	public void setJmjc(String jmjc) {
		this.jmjc = jmjc;
	}

	public String getDmqr() {
		return dmqr;
	}

	public void setDmqr(String dmqr) {
		this.dmqr = dmqr;
	}

	public String getZkqr() {
		return zkqr;
	}

	public void setZkqr(String zkqr) {
		this.zkqr = zkqr;
	}

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

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

	public String getSlin() {
		return slin;
	}

	public void setSlin(String slin) {
		this.slin = slin;
	}

	public String getZzno() {
		return zzno;
	}

	public void setZzno(String zzno) {
		this.zzno = zzno;
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

	public Double getBdan() {
		return bdan;
	}

	public void setBdan(Double bdan) {
		this.bdan = bdan;
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

	public String getVari() {
		return vari;
	}

	public void setVari(String vari) {
		this.vari = vari;
	}

	public String getMaop() {
		return maop;
	}

	public void setMaop(String maop) {
		this.maop = maop;
	}

	public String getMadr() {
		return madr;
	}

	public void setMadr(String madr) {
		this.madr = madr;
	}

}