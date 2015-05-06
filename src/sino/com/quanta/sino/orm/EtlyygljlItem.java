package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * <p>
 * ETL药液管理记录明细表
 * </p>
 * <p>
 * create: 2011-2-16 下午03:18:41
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ETLYYGLJL_ITEM")
public class EtlyygljlItem implements Serializable {

	/**
	 * ETL药液管理记录——从表
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
	 * 药液分析id
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "YYFX_ID_", referencedColumnName = "ID_", nullable = true)
	private YyfxjgJl yyfxid;

	/**
	 * 主表id
	 */
	@Column(name = "ZB_ID_", length = 40)
	private String zbid;

	/**
	 * 碱浸补给水
	 */
	@Column(name = "JJBG_S_", length = 4)
	private String jjbgs;

	/**
	 * 碱浸补给碱
	 */
	@Column(name = "JJBG_J_", length = 4)
	private String jjbgj;

	/**
	 * 碱电解补给水
	 */
	@Column(name = "JDJBG_S_", length = 4)
	private String jdjbgs;

	/**
	 * 碱电解补给碱
	 */
	@Column(name = "JDJBG_J_", length = 4)
	private String jdjbgj;

	/**
	 * 酸电解补给水
	 */
	@Column(name = "SDJBG_S_", length = 4)
	private String sdjbgs;
	/**
	 * 酸电解补给硫酸
	 */
	@Column(name = "SDJBG_LS_", length = 4)
	private String sdjbgLs;

	/**
	 * 电镀补给ENSA
	 */
	@Column(name = "DDBG_ENSA_", length = 4)
	private String ddbgEnsa;

	/**
	 * 电镀补给PSA
	 */
	@Column(name = "DDBG_PSA_", length = 4)
	private String ddbgPsa;

	/**
	 * 去药液排入201
	 */
	@Column(name = "QYYPR_201_", length = 4)
	private String qyypr201;

	/**
	 * 去药液排入403
	 */
	@Column(name = "QYYPR_403_", length = 4)
	private String qyypr403;

	/**
	 * 化学补给水
	 */
	@Column(name = "HXBG_SH_", length = 4)
	private String hxbgSh;

	/**
	 * 化学补给铬酸酐
	 */
	@Column(name = "HXBG_GSG_", length = 4)
	private String hxbgGsg;

	/**
	 * 化学补给重铬酸钠
	 */
	@Column(name = "HXBG_ZGSN_", length = 4)
	private String hxbgZgsn;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 200)
	private String bz;

	/**
	 * ETL药液是否记录
	 */
	@Column(name = "SFLR_", length = 1)
	private String sflr;

	/**
	 * 判断马口铁分析数据是否为新的记录
	 */
	@Column(name = "NEWED_")
	private boolean newed;

	/**
	 * 记录日期
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "RQ_")
	private Date rq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public YyfxjgJl getYyfxid() {
		return yyfxid;
	}

	public void setYyfxid(YyfxjgJl yyfxid) {
		this.yyfxid = yyfxid;
	}

	public String getZbid() {
		return zbid;
	}

	public void setZbid(String zbid) {
		this.zbid = zbid;
	}

	public String getJjbgs() {
		return jjbgs;
	}

	public void setJjbgs(String jjbgs) {
		this.jjbgs = jjbgs;
	}

	public String getJjbgj() {
		return jjbgj;
	}

	public void setJjbgj(String jjbgj) {
		this.jjbgj = jjbgj;
	}

	public String getJdjbgs() {
		return jdjbgs;
	}

	public void setJdjbgs(String jdjbgs) {
		this.jdjbgs = jdjbgs;
	}

	public String getJdjbgj() {
		return jdjbgj;
	}

	public void setJdjbgj(String jdjbgj) {
		this.jdjbgj = jdjbgj;
	}

	public String getSdjbgs() {
		return sdjbgs;
	}

	public void setSdjbgs(String sdjbgs) {
		this.sdjbgs = sdjbgs;
	}

	public String getSdjbgLs() {
		return sdjbgLs;
	}

	public void setSdjbgLs(String sdjbgLs) {
		this.sdjbgLs = sdjbgLs;
	}

	public String getDdbgEnsa() {
		return ddbgEnsa;
	}

	public void setDdbgEnsa(String ddbgEnsa) {
		this.ddbgEnsa = ddbgEnsa;
	}

	public String getDdbgPsa() {
		return ddbgPsa;
	}

	public void setDdbgPsa(String ddbgPsa) {
		this.ddbgPsa = ddbgPsa;
	}

	public String getQyypr201() {
		return qyypr201;
	}

	public void setQyypr201(String qyypr201) {
		this.qyypr201 = qyypr201;
	}

	public String getQyypr403() {
		return qyypr403;
	}

	public void setQyypr403(String qyypr403) {
		this.qyypr403 = qyypr403;
	}

	public String getHxbgSh() {
		return hxbgSh;
	}

	public void setHxbgSh(String hxbgSh) {
		this.hxbgSh = hxbgSh;
	}

	public String getHxbgGsg() {
		return hxbgGsg;
	}

	public void setHxbgGsg(String hxbgGsg) {
		this.hxbgGsg = hxbgGsg;
	}

	public String getHxbgZgsn() {
		return hxbgZgsn;
	}

	public void setHxbgZgsn(String hxbgZgsn) {
		this.hxbgZgsn = hxbgZgsn;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSflr() {
		return sflr;
	}

	public void setSflr(String sflr) {
		this.sflr = sflr;
	}

	public boolean isNewed() {
		return newed;
	}

	public void setNewed(boolean newed) {
		this.newed = newed;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

}
