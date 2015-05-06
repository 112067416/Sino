package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 付款发票管理
 * </p>
 * <p>
 * create: 2011-1-30 上午08:57:18
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_FKFP")
public class Fkfp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 客户付款主键
	 */
	@Column(name = "PID_", length = 40)
	private String pid;

	/**
	 * 发票用户名称
	 */
	@Column(name = "FPYMC_", length = 60)
	private String fpymc;

	/**
	 * 合同用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 合同用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 出货日期
	 */
	@Column(name = "CHRI_")
	private Date chri;

	/**
	 * 合同号码
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 规格代码
	 */
	@Column(name = "GGNO_", length = 4)
	private String ggno;
	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * 开票吨数
	 */
	@Column(name = "KFZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double kfzl;

	/**
	 * 合同单价
	 */
	@Column(name = "HTDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double htdj;

	/**
	 * 发票单价
	 */
	@Column(name = "KPDJ_", columnDefinition = "numeric(11,6)", scale = 11, precision = 6)
	private Double kpdj;

	/**
	 * 质量折让
	 */
	@Column(name = "ZLZR_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double zlzr;

	/**
	 * 制品尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 制品尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;

	/**
	 * 制品尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;

	/**
	 * 利息折让
	 */
	@Column(name = "LXZR_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double lxzr;

	/**
	 * 调整金额
	 */
	@Column(name = "TZJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double tzje;

	/**
	 * 发票金额
	 */
	@Column(name = "FPJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fpje;

	/**
	 * 发票号码
	 */
	@Column(name = "FPNO_", length = 10)
	private String fpno;

	/**
	 * 发票日期
	 */
	@Column(name = "FPRI_")
	private Date fpri;

	/**
	 * 收款日期
	 */
	@Column(name = "SKRI_")
	private Date skri;

	/**
	 * 收款金额
	 */
	@Column(name = "FKJE_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double fkje;

	/**
	 * 未收余款
	 */
	@Column(name = "WSYK_", columnDefinition = "numeric(11,2)", scale = 11, precision = 2)
	private Double wsyk;

	/**
	 * 状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

	/**
	 * 冲款日期
	 */
	@Column(name = "CZRI_")
	private Date czri;

	/**
	 * 创建日期
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新日期
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;

	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;

	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;

	/**
	 * 保险费率
	 */
	@Column(name = "BXFL_", columnDefinition = "numeric(7,5)", scale = 7, precision = 5)
	private Double bxfl;

	/**
	 * 商品编号
	 */
	@Column(name = "SPBH_", length = 1)
	private String spbh;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 等级
	 */
	@Column(name = "DENG_", length = 1)
	private String deng;

	/**
	 * 品种
	 */
	@Column(name = "PZ_", length = 1)
	private String pz;

	/**
	 * 币种
	 */
	@Column(name = "THQF_", length = 1)
	private String thqf;

	/**
	 * 发票品种
	 */
	@Column(name = "FPPZ_", length = 1)
	private String fppz;

	/**
	 * 加工用途条件
	 */
	@Column(name = "COND_", length = 4)
	private String cond;

	/**
	 * 加工用途名称
	 */
	@Column(name = "CDNM_", length = 24)
	private String cdnm;

	/**
	 * 营业员代码
	 */
	@Column(name = "YYNO_", length = 3)
	private String yyno;

	/**
	 * 营业员名称
	 */
	@Column(name = "YYNM_", length = 20)
	private String yynm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFpymc() {
		return fpymc;
	}

	public void setFpymc(String fpymc) {
		this.fpymc = fpymc;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getChri() {
		return chri;
	}

	public void setChri(Date chri) {
		this.chri = chri;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	public Double getKfzl() {
		return kfzl;
	}

	public void setKfzl(Double kfzl) {
		this.kfzl = kfzl;
	}

	public Double getHtdj() {
		return htdj;
	}

	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

	public Double getKpdj() {
		return kpdj;
	}

	public void setKpdj(Double kpdj) {
		this.kpdj = kpdj;
	}

	public Double getZlzr() {
		return zlzr;
	}

	public void setZlzr(Double zlzr) {
		this.zlzr = zlzr;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getLxzr() {
		return lxzr;
	}

	public void setLxzr(Double lxzr) {
		this.lxzr = lxzr;
	}

	public Double getTzje() {
		return tzje;
	}

	public void setTzje(Double tzje) {
		this.tzje = tzje;
	}

	public Double getFpje() {
		return fpje;
	}

	public void setFpje(Double fpje) {
		this.fpje = fpje;
	}

	public String getFpno() {
		return fpno;
	}

	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public Date getFpri() {
		return fpri;
	}

	public void setFpri(Date fpri) {
		this.fpri = fpri;
	}

	public Date getSkri() {
		return skri;
	}

	public void setSkri(Date skri) {
		this.skri = skri;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Double getWsyk() {
		return wsyk;
	}

	public void setWsyk(Double wsyk) {
		this.wsyk = wsyk;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDdno() {
		return ddno;
	}

	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	public String getDdnm() {
		return ddnm;
	}

	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	public Date getCzri() {
		return czri;
	}

	public void setCzri(Date czri) {
		this.czri = czri;
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

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public Double getBxfl() {
		return bxfl;
	}

	public void setBxfl(Double bxfl) {
		this.bxfl = bxfl;
	}

	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getPz() {
		return pz;
	}

	public void setPz(String pz) {
		this.pz = pz;
	}

	public String getThqf() {
		return thqf;
	}

	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	public String getFppz() {
		return fppz;
	}

	public void setFppz(String fppz) {
		this.fppz = fppz;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getCdnm() {
		return cdnm;
	}

	public void setCdnm(String cdnm) {
		this.cdnm = cdnm;
	}

	public String getYyno() {
		return yyno;
	}

	public void setYyno(String yyno) {
		this.yyno = yyno;
	}

	public String getYynm() {
		return yynm;
	}

	public void setYynm(String yynm) {
		this.yynm = yynm;
	}

}
