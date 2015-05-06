package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

///**
// * 直角度OP-符号
// */
//@Column(name = "ZOPF_", length = 1)
//private String zopf;
///**
// * 直角度DR-符号
// */
//@Column(name = "ZDRF_", length = 1)
//private String zdrf;
///**
// * 纵向挠度-符号
// */
//@Column(name = "ZNDF_", length = 1)
//private String zndf;
///**
// * 横向挠度-符号
// */
//@Column(name = "HNDF_", length = 1)
//private String hndf;
///**
// * 翘度符号
// */
//@Column(name = "QDUF_", length = 1)
//private String qduf;
/**
 * SL（剪切）实绩日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_SLSJLP")
public class SlsjLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 更新程序名
	 */
	@Column(name = "PROG_", length = 8)
	private String prog;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 预备1
	 */
	@Column(name = "RSV1_", length = 40)
	private String rsv1;

	/**
	 * 预备2
	 */
	@Column(name = "RSV2_", columnDefinition = "numeric(20,0)")
	private BigDecimal rsv2;

	/**
	 * 指示情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "ZSFMT_", referencedColumnName = "ID_", nullable = false)
	private Zsfmt zsfmt;

	/**
	 * 现品共通情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "XPFMT_", referencedColumnName = "ID_", nullable = false)
	private Xpfmt xpfmt;

	/**
	 * 装入中止卷板标记
	 */
	@Column(name = "ZRZZ_", length = 1)
	private String zrzz;
	/**
	 * 实绩流水线代码
	 */
	@Column(name = "LINE_", length = 1)
	private String line;
	/**
	 * 检定员
	 */
	@Column(name = "JDYN_", length = 2)
	private String jdyn;
	/**
	 * 计数员
	 */
	@Column(name = "JSYN_", length = 2)
	private String jsyn;
	/**
	 * 张数
	 */
	@Column(name = "ZSHU_")
	private Integer zshu;
	/**
	 * D-MARK标记
	 */
	@Column(name = "DMRK_", length = 1)
	private String dmrk;
	/**
	 * 采取PILER
	 */
	@Column(name = "CQPL_", length = 1)
	private String cqpl;
	/**
	 * 长度分布(+1.5)
	 */
	@Column(name = "CP15_")
	private Integer cp15;
	/**
	 * 长度分布(+1.0)
	 */
	@Column(name = "CP10_")
	private Integer cp10;
	/**
	 * 长度分布(+0.5)
	 */
	@Column(name = "CP05_")
	private Integer cp05;
	/**
	 * 长度分布(+0.0)
	 */
	@Column(name = "CP00_")
	private Integer cp00;
	/**
	 * 长度分布(-0.5)
	 */
	@Column(name = "CM05_")
	private Integer cm05;
	/**
	 * 实测宽
	 */
	@Column(name = "SCKN_", length = 7)
	private String sckn;
	/**
	 * 实测剪断长
	 */
	@Column(name = "JDCN_", length = 7)
	private String jdcn;
	/**
	 * 流水线速度
	 */
	@Column(name = "LNSD_")
	private Integer lnsd;
	/**
	 * 边波纹OP-高度
	 */
	@Column(name = "BOPG_", length = 2)
	private String bopg;
	/**
	 * 边波纹OP-间隔
	 */
	@Column(name = "BOPJ_", length = 3)
	private String bopj;
	/**
	 * 边波纹DP-高度
	 */
	@Column(name = "BDRG_", length = 2)
	private String bdrg;
	/**
	 * 边波纹DP-间隔
	 */
	@Column(name = "BDRJ_", length = 3)
	private String bdrj;
	/**
	 * 边波纹等级
	 */
	@Column(name = "BDJI_", length = 1)
	private String bdji;
	/**
	 * 直角度OP-值
	 */
	@Column(name = "ZOPZ_", length = 4)
	private String zopz;

	/**
	 * 直角度DR-值
	 */
	@Column(name = "ZDRZ_", length = 4)
	private String zdrz;
	/**
	 * 纵向挠度-值
	 */
	@Column(name = "ZNDZ_", length = 3)
	private String zndz;
	/**
	 * 纵向挠度-等级
	 */
	@Column(name = "ZNDD_", length = 1)
	private String zndd;
	/**
	 * 横向挠度-值
	 */
	@Column(name = "HNDZ_", length = 3)
	private String hndz;
	/**
	 * 横向挠度-等级
	 */
	@Column(name = "DNDD_", length = 1)
	private String dndd;
	/**
	 * 中波纹等级
	 */
	@Column(name = "ZBWD_", length = 1)
	private String zbwd;

	/**
	 * 翘度值
	 */
	@Column(name = "QDUZ_", length = 3)
	private String qduz;
	/**
	 * VARIABLE
	 */
	@Column(name = "VARI_", length = 10)
	private String vari;

	/**
	 * 端板标记
	 */
	@Column(name = "DBBJ_", length = 1)
	private String dbbj;
	/**
	 * 班
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;
	/**
	 * 组
	 */
	@Column(name = "ZU_", length = 1)
	private String zu;
	/**
	 * 缺陷2
	 */
	@Column(name = "QQD2_", length = 2)
	private String qqd2;
	/**
	 * 缺陷3
	 */
	@Column(name = "QQD3_", length = 2)
	private String qqd3;
	/**
	 * 失切量
	 */
	@Column(name = "SIQL_", length = 5)
	private String siql;
	/**
	 * 镜面检查测试
	 */
	@Column(name = "JMJC_", length = 1)
	private String jmjc;
	/**
	 * 毛边上
	 */
	@Column(name = "MAOS_", length = 5)
	private String maos;
	/**
	 * 毛边下
	 */
	@Column(name = "MAOX_", length = 5)
	private String maox;
	/**
	 * 入侧端COIL/PILE
	 */
	@Column(name = "RCZPNO_", length = 11)
	private String rczpno;
	/**
	 * 垫木方向确认
	 */
	@Column(name = "DMQR_", length = 1)
	private String dmqr;
	/**
	 * 针孔确认
	 */
	@Column(name = "ZKQR_", length = 1)
	private String zkqr;
	/**
	 * 库位
	 */
	@Column(name = "KW_", length = 4)
	private String kw;
	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 4)
	private String ddno;
	/**
	 * 中波纹高度
	 */
	@Column(name = "ZBOG_", length = 2)
	private String zbog;
	/**
	 * 中波纹间隔
	 */
	@Column(name = "ZBOJ_", length = 3)
	private String zboj;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Date getUpda() {
		return upda;
	}

	public void setUpda(Date upda) {
		this.upda = upda;
	}

	public String getProg() {
		return prog;
	}

	public void setProg(String prog) {
		this.prog = prog;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRsv1() {
		return rsv1;
	}

	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}

	public BigDecimal getRsv2() {
		return rsv2;
	}

	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	public Zsfmt getZsfmt() {
		return zsfmt;
	}

	public void setZsfmt(Zsfmt zsfmt) {
		this.zsfmt = zsfmt;
	}

	public Xpfmt getXpfmt() {
		return xpfmt;
	}

	public void setXpfmt(Xpfmt xpfmt) {
		this.xpfmt = xpfmt;
	}

	public String getZrzz() {
		return zrzz;
	}

	public void setZrzz(String zrzz) {
		this.zrzz = zrzz;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
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

	public Integer getCp15() {
		return cp15;
	}

	public void setCp15(Integer cp15) {
		this.cp15 = cp15;
	}

	public Integer getCp10() {
		return cp10;
	}

	public void setCp10(Integer cp10) {
		this.cp10 = cp10;
	}

	public Integer getCp05() {
		return cp05;
	}

	public void setCp05(Integer cp05) {
		this.cp05 = cp05;
	}

	public Integer getCp00() {
		return cp00;
	}

	public void setCp00(Integer cp00) {
		this.cp00 = cp00;
	}

	public Integer getCm05() {
		return cm05;
	}

	public void setCm05(Integer cm05) {
		this.cm05 = cm05;
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

	public String getBdji() {
		return bdji;
	}

	public void setBdji(String bdji) {
		this.bdji = bdji;
	}

	public String getZndd() {
		return zndd;
	}

	public void setZndd(String zndd) {
		this.zndd = zndd;
	}

	public String getDndd() {
		return dndd;
	}

	public void setDndd(String dndd) {
		this.dndd = dndd;
	}

	public String getZbwd() {
		return zbwd;
	}

	public void setZbwd(String zbwd) {
		this.zbwd = zbwd;
	}

	public String getQduz() {
		return qduz;
	}

	public void setQduz(String qduz) {
		this.qduz = qduz;
	}

	public String getVari() {
		return vari;
	}

	public void setVari(String vari) {
		this.vari = vari;
	}

	public String getDbbj() {
		return dbbj;
	}

	public void setDbbj(String dbbj) {
		this.dbbj = dbbj;
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

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
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

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getBopg() {
		return bopg;
	}

	public void setBopg(String bopg) {
		this.bopg = bopg;
	}

	public String getBopj() {
		return bopj;
	}

	public void setBopj(String bopj) {
		this.bopj = bopj;
	}

	public String getBdrg() {
		return bdrg;
	}

	public void setBdrg(String bdrg) {
		this.bdrg = bdrg;
	}

	public String getBdrj() {
		return bdrj;
	}

	public void setBdrj(String bdrj) {
		this.bdrj = bdrj;
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

}
