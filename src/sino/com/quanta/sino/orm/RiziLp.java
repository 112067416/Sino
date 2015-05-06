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
 * 日志
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_RIZILP")
@IdClass(RiziLpPk.class)
public class RiziLp implements Serializable {

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
	 * 日志种类
	 */
	@Id
	@Column(name = "RZZL_", length = 1)
	private String rzzl;

	/**
	 * 装入卷板No
	 */
	@Id
	@Column(name = "ZRJB_", length = 11)
	private String zrjb;

	/**
	 * 出端卷板No/PileNo
	 */
	@Id
	@Column(name = "CDJB_", length = 11)
	private String cdjb;

	/**
	 * 行号
	 */
	@Id
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 打印数据
	 */
	@Column(name = "DYSJ_", length = 132)
	private String dysj;

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
	 * @return the rzzl
	 */
	public String getRzzl() {
		return rzzl;
	}

	/**
	 * @param rzzl
	 *            the rzzl to set
	 */
	public void setRzzl(String rzzl) {
		this.rzzl = rzzl;
	}

	/**
	 * @return the zrjb
	 */
	public String getZrjb() {
		return zrjb;
	}

	/**
	 * @param zrjb
	 *            the zrjb to set
	 */
	public void setZrjb(String zrjb) {
		this.zrjb = zrjb;
	}

	/**
	 * @return the cdjb
	 */
	public String getCdjb() {
		return cdjb;
	}

	/**
	 * @param cdjb
	 *            the cdjb to set
	 */
	public void setCdjb(String cdjb) {
		this.cdjb = cdjb;
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
	 * @return the dysj
	 */
	public String getDysj() {
		return dysj;
	}

	/**
	 * @param dysj
	 *            the dysj to set
	 */
	public void setDysj(String dysj) {
		this.dysj = dysj;
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
