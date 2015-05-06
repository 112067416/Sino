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
 * Pile（分选打包）消灭实绩日志
 * 
 * @author 方元龙[F.YuanLong@gmail.com]
 */
@Entity
@Table(name = "SINO_PLXMLP")
public class PlxmLp implements Serializable {

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
	@ManyToOne(optional = true)
	@JoinColumn(name = "ZSFMT_", referencedColumnName = "ID_")
	private Zsfmt zsfmt;

	/**
	 * 现品共通情报-FK
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "XPFMT_", referencedColumnName = "ID_", nullable = false)
	private Xpfmt xpfmt;

	/**
	 * 缺陷1-缺陷代码
	 */
	@Column(name = "QXN1_", length = 2)
	private String qxn1;
	/**
	 * 缺陷1-等级
	 */
	@Column(name = "QXD1_", length = 1)
	private String qxd1;
	/**
	 * 缺陷1-张数
	 */
	@Column(name = "QXZ1_", columnDefinition = "numeric(4,0)")
	private Integer qxz1;
	/**
	 * 缺陷2-缺陷代码
	 */
	@Column(name = "QXN2_", length = 2)
	private String qxn2;
	/**
	 * 缺陷2-等级
	 */
	@Column(name = "QXD2_", length = 1)
	private String qxd2;
	/**
	 * 缺陷2-张数
	 */
	@Column(name = "QXZ2_", columnDefinition = "numeric(4,0)")
	private Integer qxz2;
	/**
	 * 缺陷3-缺陷代码
	 */
	@Column(name = "QXN3_", length = 2)
	private String qxn3;
	/**
	 * 缺陷3-等级
	 */
	@Column(name = "QXD3_", length = 1)
	private String qxd3;
	/**
	 * 缺陷3-张数
	 */
	@Column(name = "QXZ3_", columnDefinition = "numeric(4,0)")
	private Integer qxz3;
	/**
	 * 缺陷4-缺陷代码
	 */
	@Column(name = "QXN4_", length = 2)
	private String qxn4;
	/**
	 * 缺陷4-等级
	 */
	@Column(name = "QXD4_", length = 1)
	private String qxd4;
	/**
	 * 缺陷4-张数
	 */
	@Column(name = "QXZ4_", columnDefinition = "numeric(4,0)")
	private Integer qxz4;
	/**
	 * 缺陷5-缺陷代码
	 */
	@Column(name = "QXN5_", length = 2)
	private String qxn5;
	/**
	 * 缺陷5-等级
	 */
	@Column(name = "QXD5_", length = 1)
	private String qxd5;
	/**
	 * 缺陷5-张数
	 */
	@Column(name = "QXZ5_", columnDefinition = "numeric(4,0)")
	private Integer qxz5;
	/**
	 * 缺陷6-缺陷代码
	 */
	@Column(name = "QXN6_", length = 2)
	private String qxn6;
	/**
	 * 缺陷6-等级
	 */
	@Column(name = "QXD6_", length = 1)
	private String qxd6;
	/**
	 * 缺陷6-张数
	 */
	@Column(name = "QXZ6_", columnDefinition = "numeric(4,0)")
	private Integer qxz6;
	/**
	 * 缺陷7-缺陷代码
	 */
	@Column(name = "QXN7_", length = 2)
	private String qxn7;
	/**
	 * 缺陷7-等级
	 */
	@Column(name = "QXD7_", length = 1)
	private String qxd7;
	/**
	 * 缺陷7-张数
	 */
	@Column(name = "QXZ7_", columnDefinition = "numeric(4,0)")
	private Integer qxz7;
	/**
	 * 缺陷8-缺陷代码
	 */
	@Column(name = "QXN8_", length = 2)
	private String qxn8;
	/**
	 * 缺陷8-等级
	 */
	@Column(name = "QXD8_", length = 1)
	private String qxd8;
	/**
	 * 缺陷8-张数
	 */
	@Column(name = "QXZ8_", columnDefinition = "numeric(4,0)")
	private Integer qxz8;
	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

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

	public Xpfmt getXpfmt() {
		return xpfmt;
	}

	public void setXpfmt(Xpfmt xpfmt) {
		this.xpfmt = xpfmt;
	}

	public String getQxn1() {
		return qxn1;
	}

	public void setQxn1(String qxn1) {
		this.qxn1 = qxn1;
	}

	public String getQxd1() {
		return qxd1;
	}

	public void setQxd1(String qxd1) {
		this.qxd1 = qxd1;
	}

	public Integer getQxz1() {
		return qxz1;
	}

	public void setQxz1(Integer qxz1) {
		this.qxz1 = qxz1;
	}

	public String getQxn2() {
		return qxn2;
	}

	public void setQxn2(String qxn2) {
		this.qxn2 = qxn2;
	}

	public String getQxd2() {
		return qxd2;
	}

	public void setQxd2(String qxd2) {
		this.qxd2 = qxd2;
	}

	public Integer getQxz2() {
		return qxz2;
	}

	public void setQxz2(Integer qxz2) {
		this.qxz2 = qxz2;
	}

	public String getQxn3() {
		return qxn3;
	}

	public void setQxn3(String qxn3) {
		this.qxn3 = qxn3;
	}

	public String getQxd3() {
		return qxd3;
	}

	public void setQxd3(String qxd3) {
		this.qxd3 = qxd3;
	}

	public Integer getQxz3() {
		return qxz3;
	}

	public void setQxz3(Integer qxz3) {
		this.qxz3 = qxz3;
	}

	public String getQxn4() {
		return qxn4;
	}

	public void setQxn4(String qxn4) {
		this.qxn4 = qxn4;
	}

	public String getQxd4() {
		return qxd4;
	}

	public void setQxd4(String qxd4) {
		this.qxd4 = qxd4;
	}

	public Integer getQxz4() {
		return qxz4;
	}

	public void setQxz4(Integer qxz4) {
		this.qxz4 = qxz4;
	}

	public String getQxn5() {
		return qxn5;
	}

	public void setQxn5(String qxn5) {
		this.qxn5 = qxn5;
	}

	public String getQxd5() {
		return qxd5;
	}

	public void setQxd5(String qxd5) {
		this.qxd5 = qxd5;
	}

	public Integer getQxz5() {
		return qxz5;
	}

	public void setQxz5(Integer qxz5) {
		this.qxz5 = qxz5;
	}

	public String getQxn6() {
		return qxn6;
	}

	public void setQxn6(String qxn6) {
		this.qxn6 = qxn6;
	}

	public String getQxd6() {
		return qxd6;
	}

	public void setQxd6(String qxd6) {
		this.qxd6 = qxd6;
	}

	public Integer getQxz6() {
		return qxz6;
	}

	public void setQxz6(Integer qxz6) {
		this.qxz6 = qxz6;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	/**
	 * @return the qxn7
	 */
	public String getQxn7() {
		return qxn7;
	}

	/**
	 * @param qxn7
	 *            the qxn7 to set
	 */
	public void setQxn7(String qxn7) {
		this.qxn7 = qxn7;
	}

	/**
	 * @return the qxd7
	 */
	public String getQxd7() {
		return qxd7;
	}

	/**
	 * @param qxd7
	 *            the qxd7 to set
	 */
	public void setQxd7(String qxd7) {
		this.qxd7 = qxd7;
	}

	/**
	 * @return the qxz7
	 */
	public Integer getQxz7() {
		return qxz7;
	}

	/**
	 * @param qxz7
	 *            the qxz7 to set
	 */
	public void setQxz7(Integer qxz7) {
		this.qxz7 = qxz7;
	}

	/**
	 * @return the qxn8
	 */
	public String getQxn8() {
		return qxn8;
	}

	/**
	 * @param qxn8
	 *            the qxn8 to set
	 */
	public void setQxn8(String qxn8) {
		this.qxn8 = qxn8;
	}

	/**
	 * @return the qxd8
	 */
	public String getQxd8() {
		return qxd8;
	}

	/**
	 * @param qxd8
	 *            the qxd8 to set
	 */
	public void setQxd8(String qxd8) {
		this.qxd8 = qxd8;
	}

	/**
	 * @return the qxz8
	 */
	public Integer getQxz8() {
		return qxz8;
	}

	/**
	 * @param qxz8
	 *            the qxz8 to set
	 */
	public void setQxz8(Integer qxz8) {
		this.qxz8 = qxz8;
	}

}
