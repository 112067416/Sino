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

/**
 * Pile（分选打包）生产实绩日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_PLSCLP")
public class PlscLp implements Serializable {

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
	@ManyToOne(optional = true)
	@JoinColumn(name = "ZSFMT_", referencedColumnName = "ID_")
	private Zsfmt zsfmt;

	/**
	 * 现品共通情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "XPFMT_", referencedColumnName = "ID_", nullable = false)
	private Xpfmt xpfmt;

	/**
	 * 中途PILENO(1)
	 */
	@Column(name = "PLN1_", length = 10)
	private String pln1;
	/**
	 * PILE区分(1)
	 */
	@Column(name = "PLQ1_", length = 1)
	private String plq1;
	/**
	 * 原板制程商(1)
	 */
	@Column(name = "ZZN1_", length = 1)
	private String zzn1;
	/**
	 * 张数(1)
	 */
	@Column(name = "ZSU1_")
	private Integer zsu1;
	/**
	 * 中途PILENO(2)
	 */
	@Column(name = "PLN2_")
	private Integer pln2;
	/**
	 * PILE区分(2)
	 */
	@Column(name = "PLQ2_", length = 1)
	private String plq2;
	/**
	 * 原板制程商(2)
	 */
	@Column(name = "ZZN2_", length = 1)
	private String zzn2;
	/**
	 * 张数(2)
	 */
	@Column(name = "ZSU2_", columnDefinition = "numeric(4,0)")
	private Integer zsu2;
	/**
	 * 中途PILENO(3)
	 */
	@Column(name = "PLN3_", length = 10)
	private String pln3;
	/**
	 * PILE区分(3)
	 */
	@Column(name = "PLQ3_", length = 1)
	private String plq3;
	/**
	 * 原板制程商(3)
	 */
	@Column(name = "ZZN3_", length = 1)
	private String zzn3;
	/**
	 * 张数(3)
	 */
	@Column(name = "ZSU3_", columnDefinition = "numeric(4,0)")
	private Integer zsu3;
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
	@Column(name = "ZSHU_", columnDefinition = "numeric(4,0)")
	private Integer zshu;
	/**
	 * 是否翻面
	 */
	@Column(name = "FMBY_", length = 1)
	private String fmby;
	/**
	 * VARIABLE
	 */
	@Column(name = "VARI_", length = 10)
	private String vari;
	/**
	 * 班别
	 */
	@Column(name = "BAN_", length = 1)
	private String ban;
	/**
	 * 组别
	 */
	@Column(name = "ZU_", length = 1)
	private String zu;
	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 表面精加工符号
	 */
	@Column(name = "FACE_", length = 2)
	private String face;
	/**
	 * 制品尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;
	/**
	 * 制品尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;
	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;
	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;
	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN1_FLAG_", length = 1)
	private String kpn1Flag;
	/**
	 * 附页KPNO.1
	 */
	@Column(name = "KPN1_", length = 9)
	private String kpn1;
	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN2_FLAG_", length = 1)
	private String kpn2Flag;
	/**
	 * 附页KPNO.2
	 */
	@Column(name = "KPN2_", length = 9)
	private String kpn2;
	/**
	 * 附页KPNO.3标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN3_FLAG_", length = 1)
	private String kpn3Flag;
	/**
	 * 附页KPNO.3
	 */
	@Column(name = "KPN3_", length = 9)
	private String kpn3;
	/**
	 * 附页KPNO.4标识位。区分ETL、SL和全部
	 */
	@Column(name = "KPN4_FLAG_", length = 1)
	private String kpn4Flag;
	/**
	 * 附页KPNO.4
	 */
	@Column(name = "KPN4_", length = 9)
	private String kpn4;
	/**
	 * 业连No
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getPln1() {
		return pln1;
	}

	public void setPln1(String pln1) {
		this.pln1 = pln1;
	}

	public String getPlq1() {
		return plq1;
	}

	public void setPlq1(String plq1) {
		this.plq1 = plq1;
	}

	public String getZzn1() {
		return zzn1;
	}

	public void setZzn1(String zzn1) {
		this.zzn1 = zzn1;
	}

	public String getPlq2() {
		return plq2;
	}

	public void setPlq2(String plq2) {
		this.plq2 = plq2;
	}

	public String getZzn2() {
		return zzn2;
	}

	public void setZzn2(String zzn2) {
		this.zzn2 = zzn2;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the zsu1
	 */
	public Integer getZsu1() {
		return zsu1;
	}

	/**
	 * @param zsu1
	 *            the zsu1 to set
	 */
	public void setZsu1(Integer zsu1) {
		this.zsu1 = zsu1;
	}

	/**
	 * @return the pln2
	 */
	public Integer getPln2() {
		return pln2;
	}

	/**
	 * @param pln2
	 *            the pln2 to set
	 */
	public void setPln2(Integer pln2) {
		this.pln2 = pln2;
	}

	/**
	 * @return the zsu2
	 */
	public Integer getZsu2() {
		return zsu2;
	}

	/**
	 * @param zsu2
	 *            the zsu2 to set
	 */
	public void setZsu2(Integer zsu2) {
		this.zsu2 = zsu2;
	}

	/**
	 * @return the pln3
	 */
	public String getPln3() {
		return pln3;
	}

	/**
	 * @param pln3
	 *            the pln3 to set
	 */
	public void setPln3(String pln3) {
		this.pln3 = pln3;
	}

	/**
	 * @return the plq3
	 */
	public String getPlq3() {
		return plq3;
	}

	/**
	 * @param plq3
	 *            the plq3 to set
	 */
	public void setPlq3(String plq3) {
		this.plq3 = plq3;
	}

	/**
	 * @return the zzn3
	 */
	public String getZzn3() {
		return zzn3;
	}

	/**
	 * @param zzn3
	 *            the zzn3 to set
	 */
	public void setZzn3(String zzn3) {
		this.zzn3 = zzn3;
	}

	/**
	 * @return the zsu3
	 */
	public Integer getZsu3() {
		return zsu3;
	}

	/**
	 * @param zsu3
	 *            the zsu3 to set
	 */
	public void setZsu3(Integer zsu3) {
		this.zsu3 = zsu3;
	}

	/**
	 * @return the jdyn
	 */
	public String getJdyn() {
		return jdyn;
	}

	/**
	 * @param jdyn
	 *            the jdyn to set
	 */
	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	/**
	 * @return the jsyn
	 */
	public String getJsyn() {
		return jsyn;
	}

	/**
	 * @param jsyn
	 *            the jsyn to set
	 */
	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	/**
	 * @return the zshu
	 */
	public Integer getZshu() {
		return zshu;
	}

	/**
	 * @param zshu
	 *            the zshu to set
	 */
	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	/**
	 * @return the fmby
	 */
	public String getFmby() {
		return fmby;
	}

	/**
	 * @param fmby
	 *            the fmby to set
	 */
	public void setFmby(String fmby) {
		this.fmby = fmby;
	}

	/**
	 * @return the vari
	 */
	public String getVari() {
		return vari;
	}

	/**
	 * @param vari
	 *            the vari to set
	 */
	public void setVari(String vari) {
		this.vari = vari;
	}

	/**
	 * @return the rsv2
	 */
	public BigDecimal getRsv2() {
		return rsv2;
	}

	/**
	 * @param rsv2
	 *            the rsv2 to set
	 */
	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(Date upda) {
		this.upda = upda;
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

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
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

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getKpn1() {
		return kpn1;
	}

	public void setKpn1(String kpn1) {
		this.kpn1 = kpn1;
	}

	public String getKpn2Flag() {
		return kpn2Flag;
	}

	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
	}

	public String getKpn2() {
		return kpn2;
	}

	public void setKpn2(String kpn2) {
		this.kpn2 = kpn2;
	}

	public String getKpn3Flag() {
		return kpn3Flag;
	}

	public void setKpn3Flag(String kpn3Flag) {
		this.kpn3Flag = kpn3Flag;
	}

	public String getKpn3() {
		return kpn3;
	}

	public void setKpn3(String kpn3) {
		this.kpn3 = kpn3;
	}

	public String getKpn4Flag() {
		return kpn4Flag;
	}

	public void setKpn4Flag(String kpn4Flag) {
		this.kpn4Flag = kpn4Flag;
	}

	public String getKpn4() {
		return kpn4;
	}

	public void setKpn4(String kpn4) {
		this.kpn4 = kpn4;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getKpn1Flag() {
		return kpn1Flag;
	}

	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
	}

}
