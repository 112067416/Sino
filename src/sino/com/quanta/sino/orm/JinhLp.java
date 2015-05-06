package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 进货日志
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_JINHLP")
public class JinhLp implements Serializable {

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
	 * 自增ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 原材卷板No
	 */
	@Column(name = "JBNO_", length = 7)
	private String jbno;
	/**
	 * 制造商代码
	 */
	@Column(name = "ZZSD_", length = 1)
	private String zzsd;
	/**
	 * 制造商略称
	 */
	@Column(name = "ZZSM_", length = 16)
	private String zzsm;
	/**
	 * 制造商卷板No
	 */
	@Column(name = "ZZSJ_", length = 15)
	private String zzsj;
	/**
	 * 制造商规格略称
	 */
	@Column(name = "ZZGG_", length = 40)
	private String zzgg;
	/**
	 * 中日达规格略称
	 */
	@Column(name = "ZRGG_", length = 30)
	private String zrgg;
	/**
	 * 尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;
	/**
	 * 尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;
	/**
	 * 卷板长
	 */
	@Column(name = "JBCN_")
	private Integer jbcn;
	/**
	 * 重量标记
	 */
	@Column(name = "ZLBJ_", length = 1)
	private String zlbj;
	/**
	 * 重量(Kg)
	 */
	@Column(name = "ZLKG_")
	private Integer zlkg;
	/**
	 * 单价标记
	 */
	@Column(name = "DJBJ_", length = 1)
	private String djbj;
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
	 * 商社代码
	 */
	@Column(name = "SSNO_", length = 3)
	private String ssno;
	/**
	 * 合同No
	 */
	@Column(name = "HTNO_", length = 8)
	private String htno;
	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 3)
	private String line;
	/**
	 * 到货日
	 */
	@Column(name = "DAOH_", length = 8)
	private String daoh;
	/**
	 * 新订区分
	 */
	@Column(name = "XDXF_", length = 1)
	private String xdxf;

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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

	public Date getUpda() {
		return upda;
	}

	public void setUpda(Date upda) {
		this.upda = upda;
	}

	/**
	 * @return the prog
	 */
	public String getProg() {
		return prog;
	}

	/**
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
	 * @param rsv1
	 *            the rsv1 to set
	 */
	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
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
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the zzsd
	 */
	public String getZzsd() {
		return zzsd;
	}

	/**
	 * @param zzsd
	 *            the zzsd to set
	 */
	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	/**
	 * @return the zzsm
	 */
	public String getZzsm() {
		return zzsm;
	}

	/**
	 * @param zzsm
	 *            the zzsm to set
	 */
	public void setZzsm(String zzsm) {
		this.zzsm = zzsm;
	}

	/**
	 * @return the zzsj
	 */
	public String getZzsj() {
		return zzsj;
	}

	/**
	 * @param zzsj
	 *            the zzsj to set
	 */
	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	/**
	 * @return the zzgg
	 */
	public String getZzgg() {
		return zzgg;
	}

	/**
	 * @param zzgg
	 *            the zzgg to set
	 */
	public void setZzgg(String zzgg) {
		this.zzgg = zzgg;
	}

	/**
	 * @return the zrgg
	 */
	public String getZrgg() {
		return zrgg;
	}

	/**
	 * @param zrgg
	 *            the zrgg to set
	 */
	public void setZrgg(String zrgg) {
		this.zrgg = zrgg;
	}

	/**
	 * @return the houu
	 */
	public Double getHouu() {
		return houu;
	}

	/**
	 * @param houu
	 *            the houu to set
	 */
	public void setHouu(Double houu) {
		this.houu = houu;
	}

	/**
	 * @return the kuan
	 */
	public Double getKuan() {
		return kuan;
	}

	/**
	 * @param kuan
	 *            the kuan to set
	 */
	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	/**
	 * @return the jbcn
	 */
	public Integer getJbcn() {
		return jbcn;
	}

	/**
	 * @param jbcn
	 *            the jbcn to set
	 */
	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	/**
	 * @return the zlbj
	 */
	public String getZlbj() {
		return zlbj;
	}

	/**
	 * @param zlbj
	 *            the zlbj to set
	 */
	public void setZlbj(String zlbj) {
		this.zlbj = zlbj;
	}

	/**
	 * @return the zlkg
	 */
	public Integer getZlkg() {
		return zlkg;
	}

	/**
	 * @param zlkg
	 *            the zlkg to set
	 */
	public void setZlkg(Integer zlkg) {
		this.zlkg = zlkg;
	}

	/**
	 * @return the djbj
	 */
	public String getDjbj() {
		return djbj;
	}

	/**
	 * @param djbj
	 *            the djbj to set
	 */
	public void setDjbj(String djbj) {
		this.djbj = djbj;
	}

	/**
	 * @return the thqf
	 */
	public String getThqf() {
		return thqf;
	}

	/**
	 * @param thqf
	 *            the thqf to set
	 */
	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	/**
	 * @return the ssno
	 */
	public String getSsno() {
		return ssno;
	}

	/**
	 * @param ssno
	 *            the ssno to set
	 */
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * @return the htno
	 */
	public String getHtno() {
		return htno;
	}

	/**
	 * @param htno
	 *            the htno to set
	 */
	public void setHtno(String htno) {
		this.htno = htno;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the daoh
	 */
	public String getDaoh() {
		return daoh;
	}

	/**
	 * @param daoh
	 *            the daoh to set
	 */
	public void setDaoh(String daoh) {
		this.daoh = daoh;
	}

	/**
	 * @return the xdxf
	 */
	public String getXdxf() {
		return xdxf;
	}

	/**
	 * @param xdxf
	 *            the xdxf to set
	 */
	public void setXdxf(String xdxf) {
		this.xdxf = xdxf;
	}

	/**
	 * @return the htdj
	 */
	public Double getHtdj() {
		return htdj;
	}

	/**
	 * @param htdj
	 *            the htdj to set
	 */
	public void setHtdj(Double htdj) {
		this.htdj = htdj;
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

}
