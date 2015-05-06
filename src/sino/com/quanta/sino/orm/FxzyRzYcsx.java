package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 分析作业日志异常事项表
 * </p>
 * <p>
 * create: 2011-4-8 上午10:46:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_FXZYRZ_YCSX")
public class FxzyRzYcsx implements Serializable {

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
	 * 分析作业日志
	 */
	@Column(name = "PID_", length = 40)
	private String pid;

	/**
	 * Coil No.
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 部位
	 */
	@Column(name = "BW_", length = 1)
	private String bw;

	/**
	 * 异常项目
	 */
	@Column(name = "YCXM_", length = 2)
	private String ycxm;

	/**
	 * 异常数值F-S
	 */
	@Column(name = "YCFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycfs;

	/**
	 * 异常数值F-C
	 */
	@Column(name = "YCFC_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycfc;

	/**
	 * 异常数值F-M
	 */
	@Column(name = "YCFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycfm;

	/**
	 * 异常数值B-S
	 */
	@Column(name = "YCBS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycbs;

	/**
	 * 异常数值B-C
	 */
	@Column(name = "YCBC_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycbc;

	/**
	 * 异常数值B-M
	 */
	@Column(name = "YCBM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ycbm;

	/**
	 * 酚析情况F-S
	 */
	@Column(name = "FXFS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxfs;

	/**
	 * 酚析情况F-C
	 */
	@Column(name = "FXFC_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxfc;

	/**
	 * 酚析情况F-M
	 */
	@Column(name = "FXFM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxfm;

	/**
	 * 酚析情况B-S
	 */
	@Column(name = "FXBS_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxbs;

	/**
	 * 酚析情况B-C
	 */
	@Column(name = "FXBC_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxbc;

	/**
	 * 酚析情况B-M
	 */
	@Column(name = "FXBM_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double fxbm;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 订货No
	 */
	@Id
	@Column(name = "DHNO_", length = 9)
	private String dhno;

	/**
	 * 订货尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;

	/**
	 * 订货尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;

	/**
	 * 订货尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;

	/**
	 * 表面加工精度
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

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
	 * 目标涂油量
	 */
	@Column(name = "YQTY_", length = 2)
	private String yqty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getBw() {
		return bw;
	}

	public void setBw(String bw) {
		this.bw = bw;
	}

	public String getYcxm() {
		return ycxm;
	}

	public void setYcxm(String ycxm) {
		this.ycxm = ycxm;
	}

	public Double getYcfs() {
		return ycfs;
	}

	public void setYcfs(Double ycfs) {
		this.ycfs = ycfs;
	}

	public Double getYcfc() {
		return ycfc;
	}

	public void setYcfc(Double ycfc) {
		this.ycfc = ycfc;
	}

	public Double getYcfm() {
		return ycfm;
	}

	public void setYcfm(Double ycfm) {
		this.ycfm = ycfm;
	}

	public Double getYcbs() {
		return ycbs;
	}

	public void setYcbs(Double ycbs) {
		this.ycbs = ycbs;
	}

	public Double getYcbc() {
		return ycbc;
	}

	public void setYcbc(Double ycbc) {
		this.ycbc = ycbc;
	}

	public Double getYcbm() {
		return ycbm;
	}

	public void setYcbm(Double ycbm) {
		this.ycbm = ycbm;
	}

	public Double getFxfs() {
		return fxfs;
	}

	public void setFxfs(Double fxfs) {
		this.fxfs = fxfs;
	}

	public Double getFxfc() {
		return fxfc;
	}

	public void setFxfc(Double fxfc) {
		this.fxfc = fxfc;
	}

	public Double getFxfm() {
		return fxfm;
	}

	public void setFxfm(Double fxfm) {
		this.fxfm = fxfm;
	}

	public Double getFxbs() {
		return fxbs;
	}

	public void setFxbs(Double fxbs) {
		this.fxbs = fxbs;
	}

	public Double getFxbc() {
		return fxbc;
	}

	public void setFxbc(Double fxbc) {
		this.fxbc = fxbc;
	}

	public Double getFxbm() {
		return fxbm;
	}

	public void setFxbm(Double fxbm) {
		this.fxbm = fxbm;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
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

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

}
