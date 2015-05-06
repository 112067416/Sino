package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 指示对象
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_ZSDXTP")
@IdClass(ZsdxTpPk.class)
public class ZsdxTp implements Serializable {

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
	 * 分配No
	 */
	@Id
	private String fpno;

	/**
	 * 卷板No/PileNo
	 */
	@Id
	private String jbno;
	/**
	 * 原板制造商No
	 */
	@Column(name = "ZZNO_", length = 1)
	private String zzno;
	/**
	 * Pile区分
	 */
	@Column(name = "PLQF_", length = 1)
	private String plqf;

	/**
	 * 订货No.行号
	 */
	@Column(name = "DHNO_", length = 9)
	private String dhno;
	/**
	 * 余材状况，A-素材、B-中间品、C-钢卷制品、D-剪切板制品
	 */
	@Column(name = "YCZK_", length = 1)
	private String yczk;
	/**
	 * 剪边宽变更标记
	 */
	@Column(name = "JBKB_", length = 1)
	private String jbkb;
	/**
	 * 再选别标记
	 */
	@Column(name = "ZXBB_", length = 1)
	private String zxbb;
	/**
	 * 强制标记
	 */
	@Column(name = "QZBJ_", length = 1)
	private String qzbj;
	/**
	 * 采取指示量
	 */
	@Column(name = "CQZS_")
	private Integer cqzs;
	/**
	 * 超量分配标记
	 */
	@Column(name = "CLFP_", length = 1)
	private String clfp;
	/**
	 * 制品重量
	 */
	@Column(name = "ZPZL_")
	private Integer zpzl;
	/**
	 * 是否配匹
	 */
	@Column(name = "SFPP_", length = 1)
	private String sfpp;
	/**
	 * 原材客户略称
	 */
	@Column(name = "YCBR_", length = 20)
	private String ycbr;

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
	 * @return the zzno
	 */
	public String getZzno() {
		return zzno;
	}

	/**
	 * @param zzno
	 *            the zzno to set
	 */
	public void setZzno(String zzno) {
		this.zzno = zzno;
	}

	/**
	 * @return the plqf
	 */
	public String getPlqf() {
		return plqf;
	}

	/**
	 * @param plqf
	 *            the plqf to set
	 */
	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	/**
	 * @return the yczk
	 */
	public String getYczk() {
		return yczk;
	}

	/**
	 * @param yczk
	 *            the yczk to set
	 */
	public void setYczk(String yczk) {
		this.yczk = yczk;
	}

	/**
	 * @return the jbkb
	 */
	public String getJbkb() {
		return jbkb;
	}

	/**
	 * @param jbkb
	 *            the jbkb to set
	 */
	public void setJbkb(String jbkb) {
		this.jbkb = jbkb;
	}

	/**
	 * @return the zxbb
	 */
	public String getZxbb() {
		return zxbb;
	}

	/**
	 * @param zxbb
	 *            the zxbb to set
	 */
	public void setZxbb(String zxbb) {
		this.zxbb = zxbb;
	}

	/**
	 * @return the qzbj
	 */
	public String getQzbj() {
		return qzbj;
	}

	/**
	 * @param qzbj
	 *            the qzbj to set
	 */
	public void setQzbj(String qzbj) {
		this.qzbj = qzbj;
	}

	/**
	 * @return the cqzs
	 */
	public Integer getCqzs() {
		return cqzs;
	}

	/**
	 * @param cqzs
	 *            the cqzs to set
	 */
	public void setCqzs(Integer cqzs) {
		this.cqzs = cqzs;
	}

	/**
	 * @return the clfp
	 */
	public String getClfp() {
		return clfp;
	}

	/**
	 * @param clfp
	 *            the clfp to set
	 */
	public void setClfp(String clfp) {
		this.clfp = clfp;
	}

	/**
	 * @return the zpzl
	 */
	public Integer getZpzl() {
		return zpzl;
	}

	/**
	 * @param zpzl
	 *            the zpzl to set
	 */
	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
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

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	/**
	 * @return the sfpp
	 */
	public String getSfpp() {
		return sfpp;
	}

	/**
	 * @param sfpp
	 *            the sfpp to set
	 */
	public void setSfpp(String sfpp) {
		this.sfpp = sfpp;
	}

	/**
	 * @return the ycbr
	 */
	public String getYcbr() {
		return ycbr;
	}

	/**
	 * @param ycbr
	 *            the ycbr to set
	 */
	public void setYcbr(String ycbr) {
		this.ycbr = ycbr;
	}

}
