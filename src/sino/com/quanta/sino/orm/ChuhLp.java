package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 出货日志
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_CHUHLP")
public class ChuhLp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录识别,原字段TOFLAG
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）,原字段TOCREA
	 */
	@Column(name = "CREA_")
	private java.util.Date crea;

	/**
	 * 更新时间（年月日时分秒）,原字段TOUPDA
	 */
	@Column(name = "UPDA_")
	private java.util.Date upda;

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
	 * 自增ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

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
	 * 品种代码
	 */
	@Column(name = "PZNO_", length = 2)
	private String pzno;
	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;
	/**
	 * 结算条件.预付款比例
	 */
	@Column(name = "YFKN_", length = 3)
	private String yfkn;
	/**
	 * 结算条件.后付款比例
	 */
	@Column(name = "HFKN_", length = 3)
	private String hfkn;
	/**
	 * 结算条件.出货款比例
	 */
	@Column(name = "CHKN_", length = 3)
	private String chkn;
	/**
	 * 结算条件.期限
	 */
	@Column(name = "QIXN_", length = 3)
	private String qixn;
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
	 * 送货地点代码
	 */
	@Column(name = "SHNO_", length = 4)
	private String shno;
	/**
	 * 送货地点略称
	 */
	@Column(name = "SHNM_", length = 16)
	private String shnm;
	/**
	 * 交货期
	 */
	@Column(name = "JHQI_")
	private java.util.Date jhqi;
	/**
	 * 运输公司
	 */
	@Column(name = "YSGS_", length = 2)
	private String ysgs;
	/**
	 * 运输方式
	 */
	@Column(name = "YSFS_", length = 2)
	private String ysfs;
	/**
	 * 车No.
	 */
	@Column(name = "CENO_", length = 4)
	private String ceno;
	/**
	 * 出货日
	 */
	@Column(name = "CHRI_")
	private java.util.Date chri;
	/**
	 * 合同重量
	 */
	@Column(name = "HTZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double htzl;
	/**
	 * 合同重量标志
	 */
	@Column(name = "HTZB_", length = 1)
	private String htzb;
	/**
	 * 出货实绩量
	 */
	@Column(name = "CHUS_")
	private Double chus;
	/**
	 * 出货实绩量标记
	 */
	@Column(name = "CHSB_", length = 1)
	private String chsb;
	/**
	 * 通货区分
	 */
	@Column(name = "THQF_", length = 1)
	private String thqf;
	/**
	 * 合同单价
	 */
	@Column(name = "HTDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double htdj;
	/**
	 * 合同单价标记
	 */
	@Column(name = "HTDB_", length = 1)
	private String htdb;
	/**
	 * 合同金额标记
	 */
	@Column(name = "HTJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double htje;
	/**
	 * 出货实绩标记
	 */
	@Column(name = "HTJB_", length = 1)
	private String htjb;
	/**
	 * 出货实绩金额
	 */
	@Column(name = "CHUJ_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double chuj;
	/**
	 * 出货实绩金额标记
	 */
	@Column(name = "CHJB_", length = 1)
	private String chjb;
	/**
	 * 运费单价
	 */
	@Column(name = "YFEI_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double yfei;
	/**
	 * 运费单价标记
	 */
	@Column(name = "YFEB_", length = 1)
	private String yfeb;
	/**
	 * 交换地点
	 */
	@Column(name = "JHDD_", length = 1)
	private String jhdd;

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * flag String
	 * 
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the crea
	 */
	public java.util.Date getCrea() {
		return crea;
	}

	/**
	 * crea java.util.Date
	 * 
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(java.util.Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public java.util.Date getUpda() {
		return upda;
	}

	/**
	 * upda java.util.Date
	 * 
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(java.util.Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the prog
	 */
	public String getProg() {
		return prog;
	}

	/**
	 * prog String
	 * 
	 * @param prog
	 *            the prog to set
	 */
	public void setProg(String prog) {
		this.prog = prog;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * stat String
	 * 
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the rsv1
	 */
	public String getRsv1() {
		return rsv1;
	}

	/**
	 * rsv1 String
	 * 
	 * @param rsv1
	 *            the rsv1 to set
	 */
	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}

	/**
	 * @return the rsv2
	 */
	public BigDecimal getRsv2() {
		return rsv2;
	}

	/**
	 * rsv2 BigDecimal
	 * 
	 * @param rsv2
	 *            the rsv2 to set
	 */
	public void setRsv2(BigDecimal rsv2) {
		this.rsv2 = rsv2;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id String
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * dhno String
	 * 
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * line String
	 * 
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the pzno
	 */
	public String getPzno() {
		return pzno;
	}

	/**
	 * pzno String
	 * 
	 * @param pzno
	 *            the pzno to set
	 */
	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	/**
	 * @return the nwai
	 */
	public String getNwai() {
		return nwai;
	}

	/**
	 * nwai String
	 * 
	 * @param nwai
	 *            the nwai to set
	 */
	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	/**
	 * @return the yfkn
	 */
	public String getYfkn() {
		return yfkn;
	}

	/**
	 * yfkn String
	 * 
	 * @param yfkn
	 *            the yfkn to set
	 */
	public void setYfkn(String yfkn) {
		this.yfkn = yfkn;
	}

	/**
	 * @return the hfkn
	 */
	public String getHfkn() {
		return hfkn;
	}

	/**
	 * hfkn String
	 * 
	 * @param hfkn
	 *            the hfkn to set
	 */
	public void setHfkn(String hfkn) {
		this.hfkn = hfkn;
	}

	/**
	 * @return the chkn
	 */
	public String getChkn() {
		return chkn;
	}

	/**
	 * chkn String
	 * 
	 * @param chkn
	 *            the chkn to set
	 */
	public void setChkn(String chkn) {
		this.chkn = chkn;
	}

	/**
	 * @return the qixn
	 */
	public String getQixn() {
		return qixn;
	}

	/**
	 * qixn String
	 * 
	 * @param qixn
	 *            the qixn to set
	 */
	public void setQixn(String qixn) {
		this.qixn = qixn;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * user String
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * abbr String
	 * 
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the shno
	 */
	public String getShno() {
		return shno;
	}

	/**
	 * shno String
	 * 
	 * @param shno
	 *            the shno to set
	 */
	public void setShno(String shno) {
		this.shno = shno;
	}

	/**
	 * @return the shnm
	 */
	public String getShnm() {
		return shnm;
	}

	/**
	 * shnm String
	 * 
	 * @param shnm
	 *            the shnm to set
	 */
	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

	/**
	 * @return the jhqi
	 */
	public java.util.Date getJhqi() {
		return jhqi;
	}

	/**
	 * jhqi java.util.Date
	 * 
	 * @param jhqi
	 *            the jhqi to set
	 */
	public void setJhqi(java.util.Date jhqi) {
		this.jhqi = jhqi;
	}

	/**
	 * @return the ysgs
	 */
	public String getYsgs() {
		return ysgs;
	}

	/**
	 * ysgs String
	 * 
	 * @param ysgs
	 *            the ysgs to set
	 */
	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	/**
	 * @return the ysfs
	 */
	public String getYsfs() {
		return ysfs;
	}

	/**
	 * ysfs String
	 * 
	 * @param ysfs
	 *            the ysfs to set
	 */
	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	/**
	 * @return the ceno
	 */
	public String getCeno() {
		return ceno;
	}

	/**
	 * ceno String
	 * 
	 * @param ceno
	 *            the ceno to set
	 */
	public void setCeno(String ceno) {
		this.ceno = ceno;
	}

	/**
	 * @return the chri
	 */
	public java.util.Date getChri() {
		return chri;
	}

	/**
	 * chri java.util.Date
	 * 
	 * @param chri
	 *            the chri to set
	 */
	public void setChri(java.util.Date chri) {
		this.chri = chri;
	}

	/**
	 * @return the htzl
	 */
	public Double getHtzl() {
		return htzl;
	}

	/**
	 * htzl Double
	 * 
	 * @param htzl
	 *            the htzl to set
	 */
	public void setHtzl(Double htzl) {
		this.htzl = htzl;
	}

	/**
	 * @return the htzb
	 */
	public String getHtzb() {
		return htzb;
	}

	/**
	 * htzb String
	 * 
	 * @param htzb
	 *            the htzb to set
	 */
	public void setHtzb(String htzb) {
		this.htzb = htzb;
	}

	/**
	 * @return the chus
	 */
	public Double getChus() {
		return chus;
	}

	/**
	 * chus Double
	 * 
	 * @param chus
	 *            the chus to set
	 */
	public void setChus(Double chus) {
		this.chus = chus;
	}

	/**
	 * @return the chsb
	 */
	public String getChsb() {
		return chsb;
	}

	/**
	 * chsb String
	 * 
	 * @param chsb
	 *            the chsb to set
	 */
	public void setChsb(String chsb) {
		this.chsb = chsb;
	}

	/**
	 * @return the thqf
	 */
	public String getThqf() {
		return thqf;
	}

	/**
	 * thqf String
	 * 
	 * @param thqf
	 *            the thqf to set
	 */
	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	/**
	 * @return the htdj
	 */
	public Double getHtdj() {
		return htdj;
	}

	/**
	 * htdj Double
	 * 
	 * @param htdj
	 *            the htdj to set
	 */
	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

	/**
	 * @return the htdb
	 */
	public String getHtdb() {
		return htdb;
	}

	/**
	 * htdb String
	 * 
	 * @param htdb
	 *            the htdb to set
	 */
	public void setHtdb(String htdb) {
		this.htdb = htdb;
	}

	/**
	 * @return the htje
	 */
	public Double getHtje() {
		return htje;
	}

	/**
	 * htje Double
	 * 
	 * @param htje
	 *            the htje to set
	 */
	public void setHtje(Double htje) {
		this.htje = htje;
	}

	/**
	 * @return the htjb
	 */
	public String getHtjb() {
		return htjb;
	}

	/**
	 * htjb String
	 * 
	 * @param htjb
	 *            the htjb to set
	 */
	public void setHtjb(String htjb) {
		this.htjb = htjb;
	}

	/**
	 * @return the chuj
	 */
	public Double getChuj() {
		return chuj;
	}

	/**
	 * chuj Double
	 * 
	 * @param chuj
	 *            the chuj to set
	 */
	public void setChuj(Double chuj) {
		this.chuj = chuj;
	}

	/**
	 * @return the chjb
	 */
	public String getChjb() {
		return chjb;
	}

	/**
	 * chjb String
	 * 
	 * @param chjb
	 *            the chjb to set
	 */
	public void setChjb(String chjb) {
		this.chjb = chjb;
	}

	/**
	 * @return the yfei
	 */
	public Double getYfei() {
		return yfei;
	}

	/**
	 * yfei Double
	 * 
	 * @param yfei
	 *            the yfei to set
	 */
	public void setYfei(Double yfei) {
		this.yfei = yfei;
	}

	/**
	 * @return the yfeb
	 */
	public String getYfeb() {
		return yfeb;
	}

	/**
	 * yfeb String
	 * 
	 * @param yfeb
	 *            the yfeb to set
	 */
	public void setYfeb(String yfeb) {
		this.yfeb = yfeb;
	}

	/**
	 * @return the jhdd
	 */
	public String getJhdd() {
		return jhdd;
	}

	/**
	 * jhdd String
	 * 
	 * @param jhdd
	 *            the jhdd to set
	 */
	public void setJhdd(String jhdd) {
		this.jhdd = jhdd;
	}

}
