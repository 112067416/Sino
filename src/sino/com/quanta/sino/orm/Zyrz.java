package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * <p>
 * 记录作业停止标记日志
 * </p>
 * <p>
 * create: 2011-4-20 下午01:51:54
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ZYRZ")
public class Zyrz implements Serializable {

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
	 * 制品号
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	/**
	 * 订货合同号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;

	/**
	 * 作业类型
	 */
	@Column(name = "ZYLX_", length = 1)
	private String zylx;

	/**
	 * 作业理由
	 */
	@Column(name = "ZYLY_", length = 512)
	private String zyly;

	/**
	 * 处置意见
	 */
	@Column(name = "CZ_", length = 512)
	private String cz;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;

	/**
	 * 现品尺寸组合
	 */
	@Column(name = "XPCC_", length = 20)
	private String xpcc;

	/**
	 * 附着量正反面组合
	 */
	@Column(name = "FUZL_", length = 12)
	private String fuzl;

	/**
	 * 作业停止标记
	 */
	@Column(name = "ZTBJ_", length = 2)
	private String ztbj;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;
	/**
	 * 操作人
	 */
	@Column(name = "CZR_", length = 10)
	private String czr;

	/**
	 * 系统用户
	 */
	@Column(name = "XTYH_", length = 10)
	private String xtyh;
	/**
	 * 操作时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "CZSJ_")
	private Date czsj;

	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;

	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;

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

	public String getZylx() {
		return zylx;
	}

	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	public String getZyly() {
		return zyly;
	}

	public void setZyly(String zyly) {
		this.zyly = zyly;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getXtyh() {
		return xtyh;
	}

	public void setXtyh(String xtyh) {
		this.xtyh = xtyh;
	}

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getXpcc() {
		return xpcc;
	}

	public void setXpcc(String xpcc) {
		this.xpcc = xpcc;
	}

	public String getFuzl() {
		return fuzl;
	}

	public void setFuzl(String fuzl) {
		this.fuzl = fuzl;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

}
