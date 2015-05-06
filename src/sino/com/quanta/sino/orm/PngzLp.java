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
 * ETL品质管理记录日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_PNGZLP")
public class PngzLp implements Serializable {

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
	 * 装入卷板NO
	 */
	@Column(name = "ZRJB_", length = 10)
	private String zrjb;
	/**
	 * 整流器极性-碱性
	 */
	@Column(name = "ZLJX_", length = 2)
	private String zljx;
	/**
	 * 整流器极性-酸性
	 */
	@Column(name = "ZLSX_", length = 2)
	private String zlsx;
	/**
	 * 前处理电流-碱性NO1
	 */
	@Column(name = "QCJ1_", columnDefinition = "numeric(2,0)")
	private Integer qcj1;
	/**
	 * 前处理电流-碱性NO2
	 */
	@Column(name = "QCJ2_", columnDefinition = "numeric(2,0)")
	private Integer qcj2;
	/**
	 * 前处理电流-酸性NO1
	 */
	@Column(name = "QCS1_", columnDefinition = "numeric(2,0)")
	private Integer qcs1;
	/**
	 * 前处理电流-酸性NO2
	 */
	@Column(name = "QCS2_", columnDefinition = "numeric(2,0)")
	private Integer qcs2;
	/**
	 * 镀金电流密度-正面
	 */
	@Column(name = "DJZM_", columnDefinition = "numeric(2,0)")
	private Integer djzm;
	/**
	 * 镀金电流密度-反面
	 */
	@Column(name = "DJFM_", columnDefinition = "numeric(3,0)")
	private Integer djfm;
	/**
	 * REFLOW-电压
	 */
	@Column(name = "RFDY_", columnDefinition = "numeric(3,0)")
	private Integer rfdy;
	/**
	 * REFLOW-电流
	 */
	@Column(name = "RFDL_", columnDefinition = "numeric(3,0)")
	private Integer rfdl;
	/**
	 * REFLOW-频率
	 */
	@Column(name = "RFPL_", columnDefinition = "numeric(3,0)")
	private Integer rfpl;
	/**
	 * CHEMICAL电量密度-正
	 */
	@Column(name = "CHZM_", columnDefinition = "numeric(2,0)")
	private Integer chzm;
	/**
	 * CHEMICAL电量密度-反
	 */
	@Column(name = "CHFM_", columnDefinition = "numeric(2,0)")
	private Integer chfm;
	/**
	 * 涂油器条件-比率
	 */
	@Column(name = "TYBL_", columnDefinition = "numeric(3,0)")
	private Integer tybl;
	/**
	 * 涂油器条件-流量
	 */
	@Column(name = "TYLL_", columnDefinition = "numeric(3,0)")
	private Integer tyll;
	/**
	 * GAMA线板厚-平均
	 */
	@Column(name = "GMPJ_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double gmpj;
	/**
	 * GAMA线板厚-最大
	 */
	@Column(name = "GMZD_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double gmzd;
	/**
	 * GAMA线板厚-最小
	 */
	@Column(name = "GMZX_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double gmzx;
	/**
	 * 流水线速度
	 */
	@Column(name = "LNSD_", columnDefinition = "numeric(3,0)")
	private Integer lnsd;

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

	public Zsfmt getZsfmt() {
		return zsfmt;
	}

	public void setZsfmt(Zsfmt zsfmt) {
		this.zsfmt = zsfmt;
	}

	public String getZrjb() {
		return zrjb;
	}

	public void setZrjb(String zrjb) {
		this.zrjb = zrjb;
	}

	public String getZljx() {
		return zljx;
	}

	public void setZljx(String zljx) {
		this.zljx = zljx;
	}

	public String getZlsx() {
		return zlsx;
	}

	public void setZlsx(String zlsx) {
		this.zlsx = zlsx;
	}

	/**
	 * @return the qcj1
	 */
	public Integer getQcj1() {
		return qcj1;
	}

	/**
	 * @param qcj1
	 *            the qcj1 to set
	 */
	public void setQcj1(Integer qcj1) {
		this.qcj1 = qcj1;
	}

	/**
	 * @return the qcj2
	 */
	public Integer getQcj2() {
		return qcj2;
	}

	/**
	 * @param qcj2
	 *            the qcj2 to set
	 */
	public void setQcj2(Integer qcj2) {
		this.qcj2 = qcj2;
	}

	/**
	 * @return the qcs1
	 */
	public Integer getQcs1() {
		return qcs1;
	}

	/**
	 * @param qcs1
	 *            the qcs1 to set
	 */
	public void setQcs1(Integer qcs1) {
		this.qcs1 = qcs1;
	}

	/**
	 * @return the qcs2
	 */
	public Integer getQcs2() {
		return qcs2;
	}

	/**
	 * @param qcs2
	 *            the qcs2 to set
	 */
	public void setQcs2(Integer qcs2) {
		this.qcs2 = qcs2;
	}

	/**
	 * @return the djzm
	 */
	public Integer getDjzm() {
		return djzm;
	}

	/**
	 * @param djzm
	 *            the djzm to set
	 */
	public void setDjzm(Integer djzm) {
		this.djzm = djzm;
	}

	/**
	 * @return the djfm
	 */
	public Integer getDjfm() {
		return djfm;
	}

	/**
	 * @param djfm
	 *            the djfm to set
	 */
	public void setDjfm(Integer djfm) {
		this.djfm = djfm;
	}

	/**
	 * @return the rfdy
	 */
	public Integer getRfdy() {
		return rfdy;
	}

	/**
	 * @param rfdy
	 *            the rfdy to set
	 */
	public void setRfdy(Integer rfdy) {
		this.rfdy = rfdy;
	}

	/**
	 * @return the rfdl
	 */
	public Integer getRfdl() {
		return rfdl;
	}

	/**
	 * @param rfdl
	 *            the rfdl to set
	 */
	public void setRfdl(Integer rfdl) {
		this.rfdl = rfdl;
	}

	/**
	 * @return the rfpl
	 */
	public Integer getRfpl() {
		return rfpl;
	}

	/**
	 * @param rfpl
	 *            the rfpl to set
	 */
	public void setRfpl(Integer rfpl) {
		this.rfpl = rfpl;
	}

	/**
	 * @return the chzm
	 */
	public Integer getChzm() {
		return chzm;
	}

	/**
	 * @param chzm
	 *            the chzm to set
	 */
	public void setChzm(Integer chzm) {
		this.chzm = chzm;
	}

	/**
	 * @return the chfm
	 */
	public Integer getChfm() {
		return chfm;
	}

	/**
	 * @param chfm
	 *            the chfm to set
	 */
	public void setChfm(Integer chfm) {
		this.chfm = chfm;
	}

	/**
	 * @return the tybl
	 */
	public Integer getTybl() {
		return tybl;
	}

	/**
	 * @param tybl
	 *            the tybl to set
	 */
	public void setTybl(Integer tybl) {
		this.tybl = tybl;
	}

	/**
	 * @return the tyll
	 */
	public Integer getTyll() {
		return tyll;
	}

	/**
	 * @param tyll
	 *            the tyll to set
	 */
	public void setTyll(Integer tyll) {
		this.tyll = tyll;
	}

	/**
	 * @return the gmpj
	 */
	public Double getGmpj() {
		return gmpj;
	}

	/**
	 * @param gmpj
	 *            the gmpj to set
	 */
	public void setGmpj(Double gmpj) {
		this.gmpj = gmpj;
	}

	/**
	 * @return the gmzd
	 */
	public Double getGmzd() {
		return gmzd;
	}

	/**
	 * @param gmzd
	 *            the gmzd to set
	 */
	public void setGmzd(Double gmzd) {
		this.gmzd = gmzd;
	}

	/**
	 * @return the gmzx
	 */
	public Double getGmzx() {
		return gmzx;
	}

	/**
	 * @param gmzx
	 *            the gmzx to set
	 */
	public void setGmzx(Double gmzx) {
		this.gmzx = gmzx;
	}

	/**
	 * @return the lnsd
	 */
	public Integer getLnsd() {
		return lnsd;
	}

	/**
	 * @param lnsd
	 *            the lnsd to set
	 */
	public void setLnsd(Integer lnsd) {
		this.lnsd = lnsd;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
