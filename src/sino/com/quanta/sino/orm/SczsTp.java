package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 营业生产指示表
 * </p>
 * <p>
 * create: 2011-7-15 下午01:20:14
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_SCZSTP")
public class SczsTp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

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
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 订货No
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

	/**
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;

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
	 * 表面加工精度
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 交货期
	 */
	@Column(name = "JHQI_")
	private Date jhqi;

	/**
	 * 分配日期
	 */
	@Column(name = "FPQI_")
	private Date fpqi;

	/**
	 * 出货日期
	 */
	@Column(name = "CHQI_")
	private Date chqi;

	/**
	 * 是否紧急
	 */
	@Column(name = "SFJJ_", length = 2)
	private String sfjj;

	/**
	 * 合同重量（吨）
	 */
	@Column(name = "HTZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double htzl;

	/**
	 * 分配量(吨)
	 */
	@Column(name = "FPLN_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double fpln;

	/**
	 * 预安排生产量(吨)
	 */
	@Column(name = "YSCL_", columnDefinition = "numeric(8,3)", scale = 8, precision = 3)
	private Double yscl;

	/**
	 * 业务员代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 业务员名称
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
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

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
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

	public Date getFpqi() {
		return fpqi;
	}

	public void setFpqi(Date fpqi) {
		this.fpqi = fpqi;
	}

	public Date getChqi() {
		return chqi;
	}

	public void setChqi(Date chqi) {
		this.chqi = chqi;
	}

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public Double getHtzl() {
		return htzl;
	}

	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	public Double getFpln() {
		return fpln;
	}

	public void setFpln(Double fpln) {
		this.fpln = fpln;
	}

	public Double getYscl() {
		return yscl;
	}

	public void setYscl(Double yscl) {
		this.yscl = yscl;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
