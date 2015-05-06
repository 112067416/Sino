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
 * 药液分析结果记录表
 * </p>
 * <p>
 * create: 2011-5-8 下午03:29:36
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_YYFXJGJL")
public class YyfxjgJl implements Serializable {
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
	 * 记录日期
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "RQ_")
	private Date rq;

	/**
	 * 班组
	 */
	@Column(name = "BANZU_", length = 1)
	private String banzu;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 400)
	private String bz;

	/**
	 * 碱浸液Free 浓度
	 */
	@Column(name = "JJY_FREE_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double jjyFreeCon;

	/**
	 * 碱浸液Total 浓度
	 */
	@Column(name = "JJY_TOTAL_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double jjyTotalCon;

	/**
	 * 碱电解液Free 浓度
	 */
	@Column(name = "JDJY_FREE_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double jdjyFreeCon;

	/**
	 * 碱电解液Total 浓度
	 */
	@Column(name = "JDJY_TOTAL_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double jdjyTotalCon;

	/**
	 * 酸电解液H2SO4 浓度
	 */
	@Column(name = "SDJY_HSO_CON_", columnDefinition = "numeric(5,2)", scale = 5, precision = 2)
	private Double sdjyHsoCon;

	/**
	 * 酸电解液 全铁
	 */
	@Column(name = "SDJY_QT_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double sdjyQt;

	/**
	 * 锡电镀液Sn 浓度
	 */
	@Column(name = "XDDY_SNT_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xddySntCon;

	/**
	 * 锡电镀液Acid 浓度
	 */
	@Column(name = "XDDY_ACIDT_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xddyAcidtCon;

	/**
	 * 锡电镀液 ENSA
	 */
	@Column(name = "XDDY_ENSA_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xddyEnsa;

	/**
	 * 锡电镀液 电镀Fe
	 */
	@Column(name = "XDDY_FE_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xddyFe;

	/**
	 * 锡电镀液 sludge
	 */
	@Column(name = "XDDY_SLUDGE_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double xddySludge;

	/**
	 * 拖出液Sn 浓度
	 */
	@Column(name = "TCY_SN_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tcySnCon;

	/**
	 * 拖出液Acid 浓度
	 */
	@Column(name = "TCY_ACID_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double tcyAcidCon;

	/**
	 * 化学处理液Cr 浓度
	 */
	@Column(name = "HXCLY_CR_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double hxclyCrCon;

	/**
	 * 化学处理液 PH
	 */
	@Column(name = "HXCLY_PH_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double hxclyPh;

	/**
	 * 化学处理液 sludge
	 */
	@Column(name = "HXCLY_SLUDGE_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double hxclySludge;

	/**
	 * 中央铬浓度
	 */
	@Column(name = "ZYG_CON_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double zygCon;

	/**
	 * 中央铬PH
	 */
	@Column(name = "ZYG_PH_", columnDefinition = "numeric(4,2)", scale = 4, precision = 2)
	private Double zygPh;

	/**
	 * 创建人代码
	 */
	@Column(name = "CJNO_", length = 3)
	private String cjno;

	/**
	 * 创建人名称
	 */
	@Column(name = "CJNM_", length = 16)
	private String cjnm;

	/**
	 * 创建时间
	 */
	@Column(name = "CJSJ_")
	private Date cjsj;

	/**
	 * 修改人代码
	 */
	@Column(name = "XGNO_", length = 3)
	private String xgno;

	/**
	 * 修改人名称
	 */
	@Column(name = "XGNM_", length = 16)
	private String xgnm;

	/**
	 * 修改时间
	 */
	@Column(name = "XGSJ_")
	private Date xgsj;

	/**
	 * 确认人
	 */
	@Column(name = "QRR_", length = 16)
	private String qrr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public String getBanzu() {
		return banzu;
	}

	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Double getJjyFreeCon() {
		return jjyFreeCon;
	}

	public void setJjyFreeCon(Double jjyFreeCon) {
		this.jjyFreeCon = jjyFreeCon;
	}

	public Double getJjyTotalCon() {
		return jjyTotalCon;
	}

	public void setJjyTotalCon(Double jjyTotalCon) {
		this.jjyTotalCon = jjyTotalCon;
	}

	public Double getJdjyFreeCon() {
		return jdjyFreeCon;
	}

	public void setJdjyFreeCon(Double jdjyFreeCon) {
		this.jdjyFreeCon = jdjyFreeCon;
	}

	public Double getJdjyTotalCon() {
		return jdjyTotalCon;
	}

	public void setJdjyTotalCon(Double jdjyTotalCon) {
		this.jdjyTotalCon = jdjyTotalCon;
	}

	public Double getSdjyHsoCon() {
		return sdjyHsoCon;
	}

	public void setSdjyHsoCon(Double sdjyHsoCon) {
		this.sdjyHsoCon = sdjyHsoCon;
	}

	public Double getSdjyQt() {
		return sdjyQt;
	}

	public void setSdjyQt(Double sdjyQt) {
		this.sdjyQt = sdjyQt;
	}

	public Double getXddySntCon() {
		return xddySntCon;
	}

	public void setXddySntCon(Double xddySntCon) {
		this.xddySntCon = xddySntCon;
	}

	public Double getXddyAcidtCon() {
		return xddyAcidtCon;
	}

	public void setXddyAcidtCon(Double xddyAcidtCon) {
		this.xddyAcidtCon = xddyAcidtCon;
	}

	public Double getXddyEnsa() {
		return xddyEnsa;
	}

	public void setXddyEnsa(Double xddyEnsa) {
		this.xddyEnsa = xddyEnsa;
	}

	public Double getXddyFe() {
		return xddyFe;
	}

	public void setXddyFe(Double xddyFe) {
		this.xddyFe = xddyFe;
	}

	public Double getXddySludge() {
		return xddySludge;
	}

	public void setXddySludge(Double xddySludge) {
		this.xddySludge = xddySludge;
	}

	public Double getTcySnCon() {
		return tcySnCon;
	}

	public void setTcySnCon(Double tcySnCon) {
		this.tcySnCon = tcySnCon;
	}

	public Double getTcyAcidCon() {
		return tcyAcidCon;
	}

	public void setTcyAcidCon(Double tcyAcidCon) {
		this.tcyAcidCon = tcyAcidCon;
	}

	public Double getHxclyCrCon() {
		return hxclyCrCon;
	}

	public void setHxclyCrCon(Double hxclyCrCon) {
		this.hxclyCrCon = hxclyCrCon;
	}

	public Double getHxclyPh() {
		return hxclyPh;
	}

	public void setHxclyPh(Double hxclyPh) {
		this.hxclyPh = hxclyPh;
	}

	public Double getHxclySludge() {
		return hxclySludge;
	}

	public void setHxclySludge(Double hxclySludge) {
		this.hxclySludge = hxclySludge;
	}

	public Double getZygCon() {
		return zygCon;
	}

	public void setZygCon(Double zygCon) {
		this.zygCon = zygCon;
	}

	public Double getZygPh() {
		return zygPh;
	}

	public void setZygPh(Double zygPh) {
		this.zygPh = zygPh;
	}

	public String getCjno() {
		return cjno;
	}

	public void setCjno(String cjno) {
		this.cjno = cjno;
	}

	public String getCjnm() {
		return cjnm;
	}

	public void setCjnm(String cjnm) {
		this.cjnm = cjnm;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXgno() {
		return xgno;
	}

	public void setXgno(String xgno) {
		this.xgno = xgno;
	}

	public String getXgnm() {
		return xgnm;
	}

	public void setXgnm(String xgnm) {
		this.xgnm = xgnm;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getQrr() {
		return qrr;
	}

	public void setQrr(String qrr) {
		this.qrr = qrr;
	}

}
