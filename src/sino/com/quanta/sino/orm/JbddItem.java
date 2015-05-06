package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 基板订单明细
 * 
 * @author 张红国 2010-11-11
 */
@Entity
@Table(name = "SINO_JBDD_ITEM")
public class JbddItem implements Serializable {

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
	 * 所属主表
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "JBDD_", referencedColumnName = "ID_", nullable = true)
	private Jbdd jbdd;

	/**
	 * 序号
	 */
	@Column(name = "ITEM_")
	private Integer item;

	/**
	 * 紧急程度
	 */
	@Column(name = "ISGI_", length = 4)
	private String isgi;

	/**
	 * 代号
	 */
	@Column(name = "INPU_", length = 4)
	private String inpu;

	/**
	 * 表面
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 厚度A
	 */
	@Column(name = "HOUA_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houa;

	/**
	 * 宽度
	 */
	@Column(name = "WIDTH_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double width;

	/**
	 * 厚度B
	 */
	@Column(name = "HOUB_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houb;
	/**
	 * 厚度A-厚度B
	 */
	@Column(name = "AJB_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double ajb;

	/**
	 * 内销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 订货数量
	 */
	@Column(name = "DHSL_")
	private Integer dhsl;

	/**
	 * 出口数量
	 */
	@Column(name = "CKSL_")
	private Integer cksl;

	/**
	 * 合计
	 */
	@Column(name = "TOTAL_")
	private Integer total;

	/**
	 * 公式
	 */
	@Column(name = "CALC_", length = 50)
	private String calc;

	/**
	 * 确认
	 */
	@Column(name = "CONF_")
	private Integer conf;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 60)
	private String abbr;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 用途
	 */
	@Column(name = "COND_", length = 60)
	private String cond;

	/**
	 * 代码
	 */
	@Column(name = "CODE_", length = 2)
	private String code;

	/**
	 * 备注
	 */
	@Column(name = "REMA_", length = 200)
	private String rema;

	/**
	 * 含碳C
	 */
	@Column(name = "HANC_", length = 2)
	private String hanc;
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
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 16)
	private String ddnm;

	/**
	 * 订购单状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 基板订单追加状态
	 */
	@Column(name = "ZJZT_", length = 1)
	private String zjzt;

	/**
	 * 基板订单追加日期（年月日）
	 */
	@Column(name = "ZJRQ_")
	private Date zjrq;

	/**
	 * 加锁状态
	 */
	@Column(name = "LOCK_", length = 1)
	private String lock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Jbdd getJbdd() {
		return jbdd;
	}

	public void setJbdd(Jbdd jbdd) {
		this.jbdd = jbdd;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getIsgi() {
		return isgi;
	}

	public void setIsgi(String isgi) {
		this.isgi = isgi;
	}

	public String getInpu() {
		return inpu;
	}

	public void setInpu(String inpu) {
		this.inpu = inpu;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getHoua() {
		return houa;
	}

	public void setHoua(Double houa) {
		this.houa = houa;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHoub() {
		return houb;
	}

	public void setHoub(Double houb) {
		this.houb = houb;
	}

	public Double getAjb() {
		return ajb;
	}

	public void setAjb(Double ajb) {
		this.ajb = ajb;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public Integer getDhsl() {
		return dhsl;
	}

	public void setDhsl(Integer dhsl) {
		this.dhsl = dhsl;
	}

	public String getCalc() {
		return calc;
	}

	public void setCalc(String calc) {
		this.calc = calc;
	}

	public Integer getConf() {
		return conf;
	}

	public void setConf(Integer conf) {
		this.conf = conf;
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

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRema() {
		return rema;
	}

	public void setRema(String rema) {
		this.rema = rema;
	}

	public String getHanc() {
		return hanc;
	}

	public void setHanc(String hanc) {
		this.hanc = hanc;
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

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Integer getCksl() {
		return cksl;
	}

	public void setCksl(Integer cksl) {
		this.cksl = cksl;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getZjzt() {
		return zjzt;
	}

	public void setZjzt(String zjzt) {
		this.zjzt = zjzt;
	}

	public Date getZjrq() {
		return zjrq;
	}

	public void setZjrq(Date zjrq) {
		this.zjrq = zjrq;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

}
