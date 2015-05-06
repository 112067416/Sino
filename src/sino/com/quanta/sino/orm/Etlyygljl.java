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
 * ETL药液管理记录
 * </p>
 * <p>
 * create: 2011-1-14 下午04:38:41
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_ETLYYGLJL")
public class Etlyygljl implements Serializable {
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
	 * 沉淀物电镀
	 */
	@Column(name = "CDW_DD_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double cdwDd;

	/**
	 * 沉淀物化学
	 */
	@Column(name = "CDW_HX_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double cdwHx;

	/**
	 * 电镀铁浓度
	 */
	@Column(name = "DDTND_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double ddtnd;

	/**
	 * T101液量1
	 */
	@Column(name = "YL_T1011_", length = 4)
	private String ylT1011;

	/**
	 * T101液量2
	 */
	@Column(name = "YL_T1012_", length = 4)
	private String ylT1012;

	/**
	 * T101液量3
	 */
	@Column(name = "YL_T1013_", length = 4)
	private String ylT1013;

	/**
	 * T102液量1
	 */
	@Column(name = "YL_T1021_", length = 4)
	private String ylT1021;

	/**
	 * T102液量2
	 */
	@Column(name = "YL_T1022_", length = 4)
	private String ylT1022;

	/**
	 * T102液量3
	 */
	@Column(name = "YL_T1023_", length = 4)
	private String ylT1023;

	/**
	 * T103液量1
	 */
	@Column(name = "YL_T1031_", length = 4)
	private String ylT1031;

	/**
	 * T103液量2
	 */
	@Column(name = "YL_T1032_", length = 4)
	private String ylT1032;

	/**
	 * T103液量3
	 */
	@Column(name = "YL_T1033_", length = 4)
	private String ylT1033;

	/**
	 * 电镀液T201液量1
	 */
	@Column(name = "DDY_T2011_", length = 4)
	private String ddyT2011;

	/**
	 * 电镀液T201液量2
	 */
	@Column(name = "DDY_T2012_", length = 4)
	private String ddyT2012;

	/**
	 * 电镀液T201液量3
	 */
	@Column(name = "DDY_T2013_", length = 4)
	private String ddyT2013;

	/**
	 * 电镀液液量1
	 */
	@Column(name = "DDY_YL1_", length = 4)
	private String ddyYl1;

	/**
	 * 电镀液液量2
	 */
	@Column(name = "DDY_YL2_", length = 4)
	private String ddyYl2;

	/**
	 * 电镀液液量3
	 */
	@Column(name = "DDY_YL3_", length = 4)
	private String ddyYl3;

	/**
	 * 电镀液T202液量1
	 */
	@Column(name = "DDY_T2021_", length = 4)
	private String ddyT2021;

	/**
	 * 电镀液T202液量2
	 */
	@Column(name = "DDY_T2022_", length = 4)
	private String ddyT2022;

	/**
	 * 电镀液T202液量3
	 */
	@Column(name = "DDY_T2023_", length = 4)
	private String ddyT2023;

	/**
	 * 电镀液T203液量1
	 */
	@Column(name = "DDY_T2031_", length = 4)
	private String ddyT2031;

	/**
	 * 电镀液T203液量2
	 */
	@Column(name = "DDY_T2032_", length = 4)
	private String ddyT2032;

	/**
	 * 电镀液T203液量3
	 */
	@Column(name = "DDY_T2033_", length = 4)
	private String ddyT2033;

	/**
	 * 去药液抽到动力T403 1
	 */
	@Column(name = "QYYCD_T4031_", length = 4)
	private String qyycdT4031;

	/**
	 * 去药液抽到动力T403 2
	 */
	@Column(name = "QYYCD_T4032_", length = 4)
	private String qyycdT4032;

	/**
	 * 去药液抽到动力T403 3
	 */
	@Column(name = "QYYCD_T4033_", length = 4)
	private String qyycdT4033;

	/**
	 * 化学药量T304 1
	 */
	@Column(name = "HXYL_T3041_", length = 4)
	private String hxylT3041;

	/**
	 * 化学药量T304 2
	 */
	@Column(name = "HXYL_T3042_", length = 4)
	private String hxylT3042;

	/**
	 * 化学药量T304 3
	 */
	@Column(name = "HXYL_T3043_", length = 4)
	private String hxylT3043;

	/**
	 * 药液记录时间
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "JLSJ_")
	private Date jlsj;

	/**
	 * 确认人1
	 */
	@Column(name = "QRR1_", length = 10)
	private String qrr1;

	/**
	 * 确认人2
	 */
	@Column(name = "QRR2_", length = 10)
	private String qrr2;

	/**
	 * 确认人3
	 */
	@Column(name = "QRR3_", length = 10)
	private String qrr3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYlT1011() {
		return ylT1011;
	}

	public void setYlT1011(String ylT1011) {
		this.ylT1011 = ylT1011;
	}

	public String getYlT1012() {
		return ylT1012;
	}

	public void setYlT1012(String ylT1012) {
		this.ylT1012 = ylT1012;
	}

	public String getYlT1013() {
		return ylT1013;
	}

	public void setYlT1013(String ylT1013) {
		this.ylT1013 = ylT1013;
	}

	public String getYlT1021() {
		return ylT1021;
	}

	public void setYlT1021(String ylT1021) {
		this.ylT1021 = ylT1021;
	}

	public String getYlT1022() {
		return ylT1022;
	}

	public void setYlT1022(String ylT1022) {
		this.ylT1022 = ylT1022;
	}

	public String getYlT1023() {
		return ylT1023;
	}

	public void setYlT1023(String ylT1023) {
		this.ylT1023 = ylT1023;
	}

	public String getYlT1031() {
		return ylT1031;
	}

	public void setYlT1031(String ylT1031) {
		this.ylT1031 = ylT1031;
	}

	public String getYlT1032() {
		return ylT1032;
	}

	public void setYlT1032(String ylT1032) {
		this.ylT1032 = ylT1032;
	}

	public String getYlT1033() {
		return ylT1033;
	}

	public void setYlT1033(String ylT1033) {
		this.ylT1033 = ylT1033;
	}

	public String getDdyT2011() {
		return ddyT2011;
	}

	public void setDdyT2011(String ddyT2011) {
		this.ddyT2011 = ddyT2011;
	}

	public String getDdyT2012() {
		return ddyT2012;
	}

	public void setDdyT2012(String ddyT2012) {
		this.ddyT2012 = ddyT2012;
	}

	public String getDdyT2013() {
		return ddyT2013;
	}

	public void setDdyT2013(String ddyT2013) {
		this.ddyT2013 = ddyT2013;
	}

	public String getDdyYl1() {
		return ddyYl1;
	}

	public void setDdyYl1(String ddyYl1) {
		this.ddyYl1 = ddyYl1;
	}

	public String getDdyYl2() {
		return ddyYl2;
	}

	public void setDdyYl2(String ddyYl2) {
		this.ddyYl2 = ddyYl2;
	}

	public String getDdyYl3() {
		return ddyYl3;
	}

	public void setDdyYl3(String ddyYl3) {
		this.ddyYl3 = ddyYl3;
	}

	public String getDdyT2021() {
		return ddyT2021;
	}

	public void setDdyT2021(String ddyT2021) {
		this.ddyT2021 = ddyT2021;
	}

	public String getDdyT2022() {
		return ddyT2022;
	}

	public void setDdyT2022(String ddyT2022) {
		this.ddyT2022 = ddyT2022;
	}

	public String getDdyT2023() {
		return ddyT2023;
	}

	public void setDdyT2023(String ddyT2023) {
		this.ddyT2023 = ddyT2023;
	}

	public String getDdyT2031() {
		return ddyT2031;
	}

	public void setDdyT2031(String ddyT2031) {
		this.ddyT2031 = ddyT2031;
	}

	public String getDdyT2032() {
		return ddyT2032;
	}

	public void setDdyT2032(String ddyT2032) {
		this.ddyT2032 = ddyT2032;
	}

	public String getDdyT2033() {
		return ddyT2033;
	}

	public void setDdyT2033(String ddyT2033) {
		this.ddyT2033 = ddyT2033;
	}

	public String getQyycdT4031() {
		return qyycdT4031;
	}

	public void setQyycdT4031(String qyycdT4031) {
		this.qyycdT4031 = qyycdT4031;
	}

	public String getQyycdT4032() {
		return qyycdT4032;
	}

	public void setQyycdT4032(String qyycdT4032) {
		this.qyycdT4032 = qyycdT4032;
	}

	public String getQyycdT4033() {
		return qyycdT4033;
	}

	public void setQyycdT4033(String qyycdT4033) {
		this.qyycdT4033 = qyycdT4033;
	}

	public String getHxylT3041() {
		return hxylT3041;
	}

	public void setHxylT3041(String hxylT3041) {
		this.hxylT3041 = hxylT3041;
	}

	public String getHxylT3042() {
		return hxylT3042;
	}

	public void setHxylT3042(String hxylT3042) {
		this.hxylT3042 = hxylT3042;
	}

	public String getHxylT3043() {
		return hxylT3043;
	}

	public void setHxylT3043(String hxylT3043) {
		this.hxylT3043 = hxylT3043;
	}

	public Date getJlsj() {
		return jlsj;
	}

	public void setJlsj(Date jlsj) {
		this.jlsj = jlsj;
	}

	public String getQrr1() {
		return qrr1;
	}

	public void setQrr1(String qrr1) {
		this.qrr1 = qrr1;
	}

	public String getQrr2() {
		return qrr2;
	}

	public void setQrr2(String qrr2) {
		this.qrr2 = qrr2;
	}

	public String getQrr3() {
		return qrr3;
	}

	public void setQrr3(String qrr3) {
		this.qrr3 = qrr3;
	}

	public Double getCdwDd() {
		return cdwDd;
	}

	public void setCdwDd(Double cdwDd) {
		this.cdwDd = cdwDd;
	}

	public Double getCdwHx() {
		return cdwHx;
	}

	public void setCdwHx(Double cdwHx) {
		this.cdwHx = cdwHx;
	}

	public Double getDdtnd() {
		return ddtnd;
	}

	public void setDdtnd(Double ddtnd) {
		this.ddtnd = ddtnd;
	}

}
