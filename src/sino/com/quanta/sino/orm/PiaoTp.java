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
 * 发票文件
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_PIAOTP")
@IdClass(PiaoTpPk.class)
public class PiaoTp implements Serializable {

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
	 * 订货No.
	 */
	@Id
	@Column(name = "DHNO_", length = 7)
	private String dhno;
	/**
	 * 行号
	 */
	@Id
	@Column(name = "LINE_", length = 2)
	private String line;
	/**
	 * 发票日1
	 */
	@Column(name = "FPR1_", length = 8)
	private String fpr1;
	/**
	 * 发票日2
	 */
	@Column(name = "FPR2_", length = 8)
	private String fpr2;
	/**
	 * 发票日3
	 */
	@Column(name = "FPR3_", length = 8)
	private String fpr3;
	/**
	 * 发票日4
	 */
	@Column(name = "FPR4_", length = 8)
	private String fpr4;
	/**
	 * 发票日5
	 */
	@Column(name = "FPR5_", length = 8)
	private String fpr5;
	/**
	 * 发票日6
	 */
	@Column(name = "FPR6_", length = 8)
	private String fpr6;
	/**
	 * 发票金额1
	 */
	@Column(name = "FPJ1_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj1;
	/**
	 * 发票金额2
	 */
	@Column(name = "FPJ2_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj2;
	/**
	 * 发票金额3
	 */
	@Column(name = "FPJ3_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj3;
	/**
	 * 发票金额4
	 */
	@Column(name = "FPJ4_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj4;
	/**
	 * 发票金额5
	 */
	@Column(name = "FPJ5_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj5;
	/**
	 * 发票金额6
	 */
	@Column(name = "FPJ6_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpj6;
	/**
	 * 发票No.1
	 */
	@Column(name = "FPN1_", length = 16)
	private String fpn1;
	/**
	 * 发票No.2
	 */
	@Column(name = "FPN2_", length = 16)
	private String fpn2;
	/**
	 * 发票No.3
	 */
	@Column(name = "FPN3_", length = 16)
	private String fpn3;
	/**
	 * 发票No.4
	 */
	@Column(name = "FPN4_", length = 16)
	private String fpn4;
	/**
	 * 发票No.5
	 */
	@Column(name = "FPN5_", length = 16)
	private String fpn5;
	/**
	 * 发票No.6
	 */
	@Column(name = "FPN6_", length = 16)
	private String fpn6;
	/**
	 * 发票重量1
	 */
	@Column(name = "FPZ1_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz1;
	/**
	 * 发票重量2
	 */
	@Column(name = "FPZ2_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz2;
	/**
	 * 发票重量3
	 */
	@Column(name = "FPZ3_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz3;
	/**
	 * 发票重量4
	 */
	@Column(name = "FPZ4_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz4;
	/**
	 * 发票重量5
	 */
	@Column(name = "FPZ5_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz5;
	/**
	 * 发票重量6
	 */
	@Column(name = "FPZ6_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double fpz6;
	/**
	 * 发票金额累计
	 */
	@Column(name = "CHKL_", columnDefinition = "numeric(13,2)", scale = 13, precision = 2)
	private Double chkl;
	/**
	 * 发票重量累计
	 */
	@Column(name = "CHKQ_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double chkq;

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
	 * @return the fpr1
	 */
	public String getFpr1() {
		return fpr1;
	}

	/**
	 * @param fpr1
	 *            the fpr1 to set
	 */
	public void setFpr1(String fpr1) {
		this.fpr1 = fpr1;
	}

	/**
	 * @return the fpr2
	 */
	public String getFpr2() {
		return fpr2;
	}

	/**
	 * @param fpr2
	 *            the fpr2 to set
	 */
	public void setFpr2(String fpr2) {
		this.fpr2 = fpr2;
	}

	/**
	 * @return the fpr3
	 */
	public String getFpr3() {
		return fpr3;
	}

	/**
	 * @param fpr3
	 *            the fpr3 to set
	 */
	public void setFpr3(String fpr3) {
		this.fpr3 = fpr3;
	}

	/**
	 * @return the fpr4
	 */
	public String getFpr4() {
		return fpr4;
	}

	/**
	 * @param fpr4
	 *            the fpr4 to set
	 */
	public void setFpr4(String fpr4) {
		this.fpr4 = fpr4;
	}

	/**
	 * @return the fpr5
	 */
	public String getFpr5() {
		return fpr5;
	}

	/**
	 * @param fpr5
	 *            the fpr5 to set
	 */
	public void setFpr5(String fpr5) {
		this.fpr5 = fpr5;
	}

	/**
	 * @return the fpr6
	 */
	public String getFpr6() {
		return fpr6;
	}

	/**
	 * @param fpr6
	 *            the fpr6 to set
	 */
	public void setFpr6(String fpr6) {
		this.fpr6 = fpr6;
	}

	/**
	 * @return the fpj1
	 */
	public Double getFpj1() {
		return fpj1;
	}

	/**
	 * @param fpj1
	 *            the fpj1 to set
	 */
	public void setFpj1(Double fpj1) {
		this.fpj1 = fpj1;
	}

	/**
	 * @return the fpj2
	 */
	public Double getFpj2() {
		return fpj2;
	}

	/**
	 * @param fpj2
	 *            the fpj2 to set
	 */
	public void setFpj2(Double fpj2) {
		this.fpj2 = fpj2;
	}

	/**
	 * @return the fpj3
	 */
	public Double getFpj3() {
		return fpj3;
	}

	/**
	 * @param fpj3
	 *            the fpj3 to set
	 */
	public void setFpj3(Double fpj3) {
		this.fpj3 = fpj3;
	}

	/**
	 * @return the fpj4
	 */
	public Double getFpj4() {
		return fpj4;
	}

	/**
	 * @param fpj4
	 *            the fpj4 to set
	 */
	public void setFpj4(Double fpj4) {
		this.fpj4 = fpj4;
	}

	/**
	 * @return the fpj5
	 */
	public Double getFpj5() {
		return fpj5;
	}

	/**
	 * @param fpj5
	 *            the fpj5 to set
	 */
	public void setFpj5(Double fpj5) {
		this.fpj5 = fpj5;
	}

	/**
	 * @return the fpj6
	 */
	public Double getFpj6() {
		return fpj6;
	}

	/**
	 * @param fpj6
	 *            the fpj6 to set
	 */
	public void setFpj6(Double fpj6) {
		this.fpj6 = fpj6;
	}

	/**
	 * @return the fpn1
	 */
	public String getFpn1() {
		return fpn1;
	}

	/**
	 * @param fpn1
	 *            the fpn1 to set
	 */
	public void setFpn1(String fpn1) {
		this.fpn1 = fpn1;
	}

	/**
	 * @return the fpn2
	 */
	public String getFpn2() {
		return fpn2;
	}

	/**
	 * @param fpn2
	 *            the fpn2 to set
	 */
	public void setFpn2(String fpn2) {
		this.fpn2 = fpn2;
	}

	/**
	 * @return the fpn3
	 */
	public String getFpn3() {
		return fpn3;
	}

	/**
	 * @param fpn3
	 *            the fpn3 to set
	 */
	public void setFpn3(String fpn3) {
		this.fpn3 = fpn3;
	}

	/**
	 * @return the fpn4
	 */
	public String getFpn4() {
		return fpn4;
	}

	/**
	 * @param fpn4
	 *            the fpn4 to set
	 */
	public void setFpn4(String fpn4) {
		this.fpn4 = fpn4;
	}

	/**
	 * @return the fpn5
	 */
	public String getFpn5() {
		return fpn5;
	}

	/**
	 * @param fpn5
	 *            the fpn5 to set
	 */
	public void setFpn5(String fpn5) {
		this.fpn5 = fpn5;
	}

	/**
	 * @return the fpn6
	 */
	public String getFpn6() {
		return fpn6;
	}

	/**
	 * @param fpn6
	 *            the fpn6 to set
	 */
	public void setFpn6(String fpn6) {
		this.fpn6 = fpn6;
	}

	/**
	 * @return the fpz1
	 */
	public Double getFpz1() {
		return fpz1;
	}

	/**
	 * @param fpz1
	 *            the fpz1 to set
	 */
	public void setFpz1(Double fpz1) {
		this.fpz1 = fpz1;
	}

	/**
	 * @return the fpz2
	 */
	public Double getFpz2() {
		return fpz2;
	}

	/**
	 * @param fpz2
	 *            the fpz2 to set
	 */
	public void setFpz2(Double fpz2) {
		this.fpz2 = fpz2;
	}

	/**
	 * @return the fpz3
	 */
	public Double getFpz3() {
		return fpz3;
	}

	/**
	 * @param fpz3
	 *            the fpz3 to set
	 */
	public void setFpz3(Double fpz3) {
		this.fpz3 = fpz3;
	}

	/**
	 * @return the fpz4
	 */
	public Double getFpz4() {
		return fpz4;
	}

	/**
	 * @param fpz4
	 *            the fpz4 to set
	 */
	public void setFpz4(Double fpz4) {
		this.fpz4 = fpz4;
	}

	/**
	 * @return the fpz5
	 */
	public Double getFpz5() {
		return fpz5;
	}

	/**
	 * @param fpz5
	 *            the fpz5 to set
	 */
	public void setFpz5(Double fpz5) {
		this.fpz5 = fpz5;
	}

	/**
	 * @return the fpz6
	 */
	public Double getFpz6() {
		return fpz6;
	}

	/**
	 * @param fpz6
	 *            the fpz6 to set
	 */
	public void setFpz6(Double fpz6) {
		this.fpz6 = fpz6;
	}

	/**
	 * @return the chkl
	 */
	public Double getChkl() {
		return chkl;
	}

	/**
	 * @param chkl
	 *            the chkl to set
	 */
	public void setChkl(Double chkl) {
		this.chkl = chkl;
	}

	/**
	 * @return the chkq
	 */
	public Double getChkq() {
		return chkq;
	}

	/**
	 * @param chkq
	 *            the chkq to set
	 */
	public void setChkq(Double chkq) {
		this.chkq = chkq;
	}

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
