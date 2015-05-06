package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实绩日志（现品情报共通格式）
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_XPFMT")
public class Xpfmt implements Serializable {

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
	 * 卷板NO/PILENO
	 */
	@Column(name = "JBN_", length = 11)
	private String jbn;

	/**
	 * 处理区分
	 */
	@Column(name = "CLQ_", length = 1)
	private String clq;
	/**
	 * 原板制造商NO
	 */
	@Column(name = "ZZN_", length = 1)
	private String zzn;
	/**
	 * PILE区分
	 */
	@Column(name = "PLQ_", length = 1)
	private String plq;
	/**
	 * PILER
	 */
	@Column(name = "Cqp_", length = 1)
	private String Cqp;

	/**
	 * 出口包装No
	 */
	@Column(name = "CKN_", columnDefinition = "numeric(6,0)")
	private Integer ckn;
	/**
	 * 计算重量
	 */
	@Column(name = "JSZ_", columnDefinition = "numeric(5,0)")
	private Integer jsz;
	/**
	 * 实际重量
	 */
	@Column(name = "SJZ_", columnDefinition = "numeric(5,0)")
	private Integer sjz;
	/**
	 * 净重量
	 */
	@Column(name = "JNZ_", columnDefinition = "numeric(5,0)")
	private Integer jnz;
	/**
	 * 毛重量
	 */
	@Column(name = "MAZ_", columnDefinition = "numeric(5,0)")
	private Integer maz;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZ_", columnDefinition = "numeric(5,0)")
	private Integer zpz;
	/**
	 * 现品尺寸-厚
	 */
	@Column(name = "XPH_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double xph;
	/**
	 * 现品尺寸-宽
	 */
	@Column(name = "XPK_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpk;
	/**
	 * 现品尺寸-长
	 */
	@Column(name = "XPC_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double xpc;
	/**
	 * 卷板长
	 */
	@Column(name = "JBC_", columnDefinition = "numeric(6,0)")
	private Integer jbc;
	/**
	 * 产量
	 */
	@Column(name = "CHA_", length = 1)
	private String cha;
	/**
	 * 等级
	 */
	@Column(name = "DEN_", length = 3)
	private String den;
	/**
	 * 处置代码
	 */
	@Column(name = "CZD_", length = 1)
	private String czd;
	/**
	 * 缺陷代码
	 */
	@Column(name = "QQD_", length = 2)
	private String qqd;
	/**
	 * 缺陷2
	 */
	@Column(name = "QQ2_", length = 2)
	private String qq2;
	/**
	 * 缺陷3
	 */
	@Column(name = "QQ3_", length = 2)
	private String qq3;
	/**
	 * 毛边上
	 */
	@Column(name = "MAS_", length = 5)
	private String mas;
	/**
	 * 毛边下
	 */
	@Column(name = "MAX_", length = 5)
	private String max;

	/**
	 * 修边毛边DR
	 */
	@Column(name = "MADR_", length = 5)
	private String madr;

	/**
	 * 修边毛边OP
	 */
	@Column(name = "MAOP_", length = 5)
	private String maop;

	public String getMas() {
		return mas;
	}

	public void setMas(String mas) {
		this.mas = mas;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getQq2() {
		return qq2;
	}

	public void setQq2(String qq2) {
		this.qq2 = qq2;
	}

	public String getQq3() {
		return qq3;
	}

	public void setQq3(String qq3) {
		this.qq3 = qq3;
	}

	/**
	 * 实绩附着量-正面
	 */
	@Column(name = "SCZ_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scz;
	/**
	 * 实绩附着量-反面
	 */
	@Column(name = "SCF_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double scf;
	/**
	 * 硬度
	 */
	@Column(name = "YIN_", columnDefinition = "numeric(3,0)")
	private Integer yin;
	/**
	 * 业连NO
	 */
	@Column(name = "YLNO_", length = 256)
	private String ylno;
	/**
	 * 实绩品种等级
	 */
	@Column(name = "SPD_", length = 1)
	private String spd;

	/**
	 * @return the clq
	 */
	public String getClq() {
		return clq;
	}

	/**
	 * @param clq
	 *            the clq to set
	 */
	public void setClq(String clq) {
		this.clq = clq;
	}

	/**
	 * @return the jbn
	 */
	public String getJbn() {
		return jbn;
	}

	/**
	 * @param jbn
	 *            the jbn to set
	 */
	public void setJbn(String jbn) {
		this.jbn = jbn;
	}

	/**
	 * @return the zzn
	 */
	public String getZzn() {
		return zzn;
	}

	/**
	 * @param zzn
	 *            the zzn to set
	 */
	public void setZzn(String zzn) {
		this.zzn = zzn;
	}

	/**
	 * @return the plq
	 */
	public String getPlq() {
		return plq;
	}

	/**
	 * @param plq
	 *            the plq to set
	 */
	public void setPlq(String plq) {
		this.plq = plq;
	}

	/**
	 * @return the ckn
	 */
	public Integer getCkn() {
		return ckn;
	}

	/**
	 * @param ckn
	 *            the ckn to set
	 */
	public void setCkn(Integer ckn) {
		this.ckn = ckn;
	}

	/**
	 * @return the jsz
	 */
	public Integer getJsz() {
		return jsz;
	}

	/**
	 * @param jsz
	 *            the jsz to set
	 */
	public void setJsz(Integer jsz) {
		this.jsz = jsz;
	}

	/**
	 * @return the sjz
	 */
	public Integer getSjz() {
		return sjz;
	}

	/**
	 * @param sjz
	 *            the sjz to set
	 */
	public void setSjz(Integer sjz) {
		this.sjz = sjz;
	}

	/**
	 * @return the jnz
	 */
	public Integer getJnz() {
		return jnz;
	}

	/**
	 * @param jnz
	 *            the jnz to set
	 */
	public void setJnz(Integer jnz) {
		this.jnz = jnz;
	}

	/**
	 * @return the maz
	 */
	public Integer getMaz() {
		return maz;
	}

	/**
	 * @param maz
	 *            the maz to set
	 */
	public void setMaz(Integer maz) {
		this.maz = maz;
	}

	/**
	 * @return the zpz
	 */
	public Integer getZpz() {
		return zpz;
	}

	/**
	 * @param zpz
	 *            the zpz to set
	 */
	public void setZpz(Integer zpz) {
		this.zpz = zpz;
	}

	/**
	 * @return the xph
	 */
	public Double getXph() {
		return xph;
	}

	/**
	 * @param xph
	 *            the xph to set
	 */
	public void setXph(Double xph) {
		this.xph = xph;
	}

	/**
	 * @return the xpk
	 */
	public Double getXpk() {
		return xpk;
	}

	/**
	 * @param xpk
	 *            the xpk to set
	 */
	public void setXpk(Double xpk) {
		this.xpk = xpk;
	}

	/**
	 * @return the xpc
	 */
	public Double getXpc() {
		return xpc;
	}

	/**
	 * @param xpc
	 *            the xpc to set
	 */
	public void setXpc(Double xpc) {
		this.xpc = xpc;
	}

	/**
	 * @return the jbc
	 */
	public Integer getJbc() {
		return jbc;
	}

	/**
	 * @param jbc
	 *            the jbc to set
	 */
	public void setJbc(Integer jbc) {
		this.jbc = jbc;
	}

	/**
	 * @return the cha
	 */
	public String getCha() {
		return cha;
	}

	/**
	 * @param cha
	 *            the cha to set
	 */
	public void setCha(String cha) {
		this.cha = cha;
	}

	/**
	 * @return the den
	 */
	public String getDen() {
		return den;
	}

	/**
	 * @param den
	 *            the den to set
	 */
	public void setDen(String den) {
		this.den = den;
	}

	/**
	 * @return the czd
	 */
	public String getCzd() {
		return czd;
	}

	/**
	 * @param czd
	 *            the czd to set
	 */
	public void setCzd(String czd) {
		this.czd = czd;
	}

	/**
	 * @return the qqd
	 */
	public String getQqd() {
		return qqd;
	}

	/**
	 * @param qqd
	 *            the qqd to set
	 */
	public void setQqd(String qqd) {
		this.qqd = qqd;
	}

	/**
	 * @return the scz
	 */
	public Double getScz() {
		return scz;
	}

	/**
	 * @param scz
	 *            the scz to set
	 */
	public void setScz(Double scz) {
		this.scz = scz;
	}

	/**
	 * @return the scf
	 */
	public Double getScf() {
		return scf;
	}

	/**
	 * @param scf
	 *            the scf to set
	 */
	public void setScf(Double scf) {
		this.scf = scf;
	}

	/**
	 * @return the yin
	 */
	public Integer getYin() {
		return yin;
	}

	/**
	 * @param yin
	 *            the yin to set
	 */
	public void setYin(Integer yin) {
		this.yin = yin;
	}

	/**
	 * @return the spd
	 */
	public String getSpd() {
		return spd;
	}

	/**
	 * @param spd
	 *            the spd to set
	 */
	public void setSpd(String spd) {
		this.spd = spd;
	}

	/**
	 * @return the ylno
	 */
	public String getYlno() {
		return ylno;
	}

	/**
	 * @param ylno
	 *            the ylno to set
	 */
	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCqp() {
		return Cqp;
	}

	public void setCqp(String cqp) {
		Cqp = cqp;
	}

	public String getMadr() {
		return madr;
	}

	public void setMadr(String madr) {
		this.madr = madr;
	}

	public String getMaop() {
		return maop;
	}

	public void setMaop(String maop) {
		this.maop = maop;
	}
}
