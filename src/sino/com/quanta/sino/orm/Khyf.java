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
 * 客户运费管理
 * </p>
 * <p>
 * create: 2011-2-13 下午07:02:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_KHYF")
public class Khyf implements Serializable {

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
	 * 出货日
	 */
	@Column(name = "CHRI_")
	private java.util.Date chri;

	/**
	 * 运输公司代码
	 */
	@Column(name = "YSGS_", length = 2)
	private String ysgs;

	/**
	 * 运输公司名称
	 */
	@Column(name = "YSNM_", length = 64)
	private String ysnm;

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
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 订货No.
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;
	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 出货重量
	 */
	@Column(name = "CHZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chzl;

	/**
	 * 毛重量
	 */
	@Column(name = "MAZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double mazl;

	/**
	 * 出货制品数
	 */
	@Column(name = "CHSU_")
	private Integer chsu;

	/**
	 * 送货点代码
	 */
	@Column(name = "SHNO_", length = 2)
	private String shno;

	/**
	 * 送货点略称
	 */
	@Column(name = "SHNM_", length = 16)
	private String shnm;

	/**
	 * 到达地点
	 */
	@Column(name = "ADDR_", length = 126)
	private String addr;

	/**
	 * 运输方式代码
	 */
	@Column(name = "YSFS_", length = 4)
	private String ysfs;

	/**
	 * 运输方式名称
	 */
	@Column(name = "YSFM_", length = 32)
	private String ysfm;
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
	 * 尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 货柜海运.堆场柜数
	 */
	@Column(name = "DCHY_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double dchy;

	/**
	 * 货柜海运.船运柜数
	 */
	@Column(name = "CYHY_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double cyhy;

	/**
	 * 货柜海运.船运柜数
	 */
	@Column(name = "CYHY1_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double cyhy1;

	/**
	 * 货柜海运.目的港到客户柜数
	 */
	@Column(name = "KHHH1_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double khhh1;

	/**
	 * 出口.堆场柜数
	 */
	@Column(name = "DCCK_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double dcck;

	/**
	 * 出口.船运柜数
	 */
	@Column(name = "CYCK_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double cyck;

	/**
	 * 出口.船运柜数
	 */
	@Column(name = "CYCK1_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double cyck1;

	/**
	 * 出口.目的港到客户柜数
	 */
	@Column(name = "MDCK_", columnDefinition = "numeric(3,1)", scale = 3, precision = 1)
	private Double mdck;

	/**
	 * 中日达到起始港口（货柜海运）。相关公司代码
	 */
	@Column(name = "QGHHNO_", length = 2)
	private String qghhno;

	/**
	 * 中日达到起始港口（货柜海运）。相关公司名称
	 */
	@Column(name = "QGHHNM_", length = 64)
	private String qghhnm;

	/**
	 * 堆场（货柜海运）。相关公司代码
	 */
	@Column(name = "DCHHNO_", length = 2)
	private String dchhno;

	/**
	 * 堆场（货柜海运）。相关公司名称
	 */
	@Column(name = "DCHHNM_", length = 64)
	private String dchhnm;
	/**
	 * 船运（货柜海运）。相关公司代码
	 */
	@Column(name = "CYHHNO_", length = 2)
	private String cyhhno;

	/**
	 * 船运（货柜海运）。相关公司名称
	 */
	@Column(name = "CYHHNM_", length = 64)
	private String cyhhnm;

	/**
	 * 船运到客户（货柜海运）。相关公司代码
	 */
	@Column(name = "CYHHNO1_", length = 2)
	private String cyhhno1;

	/**
	 * 船运到客户（货柜海运）。相关公司名称
	 */
	@Column(name = "CYHHNM1_", length = 64)
	private String cyhhnm1;

	/**
	 * 目的港到客户（货柜海运）。相关公司代码
	 */
	@Column(name = "KHHHNO1_", length = 2)
	private String khhhno1;

	/**
	 * 目的港到客户（货柜海运）。相关公司名称
	 */
	@Column(name = "KHHHNM1_", length = 64)
	private String khhhnm1;

	/**
	 * 目的港到客户（货柜海运）。相关公司代码
	 */
	@Column(name = "KHHHNO_", length = 2)
	private String khhhno;

	/**
	 * 目的港到客户（货柜海运）。相关公司名称
	 */
	@Column(name = "KHHHNM_", length = 64)
	private String khhhnm;

	/**
	 * 中日达到目的港（散货海运）。相关公司代码
	 */
	@Column(name = "QGSHNO_", length = 2)
	private String qgshno;

	/**
	 * 中日达到目的港（散货海运）。相关公司名称
	 */
	@Column(name = "QGSHNM_", length = 64)
	private String qgshnm;

	/**
	 * 目的港到客户（散货海运）。相关公司代码
	 */
	@Column(name = "KHSHNO_", length = 2)
	private String khshno;

	/**
	 * 目的港到客户（散货海运）。相关公司名称
	 */
	@Column(name = "KHSHNM_", length = 64)
	private String khshnm;

	/**
	 * 中日达到客户（公路）。相关公司代码
	 */
	@Column(name = "KHGLNO_", length = 2)
	private String khglno;

	/**
	 * 中日达到客户（公路）。相关公司名称
	 */
	@Column(name = "KHGLNM_", length = 64)
	private String khglnm;

	/**
	 * 中日达到终点站（铁路）。相关公司代码
	 */
	@Column(name = "ZDTLNO_", length = 2)
	private String zdtlno;

	/**
	 * 中日达到终点站（铁路）。相关公司名称
	 */
	@Column(name = "ZDTLNM_", length = 64)
	private String zdtlnm;
	/**
	 * 终点站到客户（铁路）。相关公司代码
	 */
	@Column(name = "KHTLNO_", length = 2)
	private String khtlno;

	/**
	 * 终点站到客户（铁路）。相关公司名称
	 */
	@Column(name = "KHTLNM_", length = 64)
	private String khtlnm;
	/**
	 * 中日达到超始港（出口）。相关公司代码
	 */
	@Column(name = "QGCKNO_", length = 2)
	private String qgckno;

	/**
	 * 中日达到超始港（出口）。相关公司名称
	 */
	@Column(name = "QGCKNM_", length = 64)
	private String qgcknm;
	/**
	 * 堆场（出口）。相关公司代码
	 */
	@Column(name = "DCCKNO_", length = 2)
	private String dcckno;

	/**
	 * 堆场（出口）。相关公司名称
	 */
	@Column(name = "DCCKNM_", length = 64)
	private String dccknm;

	/**
	 * 船运（出口）。相关公司代码
	 */
	@Column(name = "CYCKNO_", length = 2)
	private String cyckno;

	/**
	 * 船运（出口）。相关公司名称
	 */
	@Column(name = "CYCKNM_", length = 64)
	private String cycknm;

	/**
	 * 船运（出口）。相关公司代码
	 */
	@Column(name = "CYCKNO1_", length = 2)
	private String cyckno1;

	/**
	 * 船运（出口）。相关公司名称
	 */
	@Column(name = "CYCKNM1_", length = 64)
	private String cycknm1;

	/**
	 * 港口到客户（出口）。相关公司代码
	 */
	@Column(name = "KHCKNO_", length = 2)
	private String khckno;

	/**
	 * 港口到客户（出口）。相关公司名称
	 */
	@Column(name = "KHCKNM_", length = 64)
	private String khcknm;

	/**
	 * 中日达到起始港口（货柜海运）(RMB/吨)
	 */
	@Column(name = "QGHHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double qghhdj;

	/**
	 * 堆场（货柜海运）(RMB/柜)
	 */
	@Column(name = "DCHHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double dchhdj;

	/**
	 * 船运（货柜海运）(RMB/柜)
	 */
	@Column(name = "CYHHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double cyhhdj;

	/**
	 * 船运到客户（货柜海运）(RMB/柜)
	 */
	@Column(name = "CYHHDJ1_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double cyhhdj1;

	/**
	 * 目的港到客户（货柜海运）(RMB/吨)
	 */
	@Column(name = "KHHHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khhhdj;

	/**
	 * 目的港到客户（货柜海运）(RMB/柜)
	 */
	@Column(name = "KHHHDJ1_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khhhdj1;

	/**
	 * 中日达到目的港（散货海运）(RMB/吨)
	 */
	@Column(name = "QGSHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double qgshdj;

	/**
	 * 目的港到客户（散货海运）(RMB/吨)
	 */
	@Column(name = "KHSHDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khshdj;

	/**
	 * 中日达到客户（公路）(RMB/吨)
	 */
	@Column(name = "KHGLDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khgldj;

	/**
	 * 中日达到终点站（铁路）(RMB/吨)
	 */
	@Column(name = "ZDTLDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double zdtldj;

	/**
	 * 终点站到客户（铁路）(RMB/吨)
	 */
	@Column(name = "KHTLDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khtldj;

	/**
	 * 中日达到超始港（出口）(RMB/吨)
	 */
	@Column(name = "QGCKDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double qgckdj;

	/**
	 * 堆场（出口）(RMB/柜)
	 */
	@Column(name = "DCCKDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double dcckdj;

	/**
	 * 船运（出口）(USD/柜)
	 */
	@Column(name = "CYCKDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double cyckdj;

	/**
	 * 船运（出口）(USD/柜)
	 */
	@Column(name = "CYCKDJ1_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double cyckdj1;

	/**
	 * 港口到客户(出口)(USD/柜)
	 */
	@Column(name = "KHCKDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double khckdj;

	/**
	 * 自提(RMB/吨)
	 */
	@Column(name = "ZTDJ_", columnDefinition = "numeric(7,2)", scale = 7, precision = 2)
	private Double ztdj;

	/**
	 * 运费设置时间
	 */
	@Column(name = "SZSJ_")
	private Date szsj;

	/**
	 * 标识是否设置运费单价
	 */
	@Column(name = "STAT_", length = 2)
	private String stat;

	/**
	 * 装箱指示书No.
	 */
	@Column(name = "ZXNO_", length = 126)
	private String zxno;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.util.Date getChri() {
		return chri;
	}

	public void setChri(java.util.Date chri) {
		this.chri = chri;
	}

	public String getYsgs() {
		return ysgs;
	}

	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	public String getYsnm() {
		return ysnm;
	}

	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
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

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Double getMazl() {
		return mazl;
	}

	public void setMazl(Double mazl) {
		this.mazl = mazl;
	}

	public Integer getChsu() {
		return chsu;
	}

	public void setChsu(Integer chsu) {
		this.chsu = chsu;
	}

	public String getShno() {
		return shno;
	}

	public void setShno(String shno) {
		this.shno = shno;
	}

	public String getShnm() {
		return shnm;
	}

	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getYsfs() {
		return ysfs;
	}

	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	public String getYsfm() {
		return ysfm;
	}

	public void setYsfm(String ysfm) {
		this.ysfm = ysfm;
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

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public Double getDchy() {
		return dchy;
	}

	public void setDchy(Double dchy) {
		this.dchy = dchy;
	}

	public Double getCyhy() {
		return cyhy;
	}

	public void setCyhy(Double cyhy) {
		this.cyhy = cyhy;
	}

	public Double getCyhy1() {
		return cyhy1;
	}

	public void setCyhy1(Double cyhy1) {
		this.cyhy1 = cyhy1;
	}

	public Double getKhhh1() {
		return khhh1;
	}

	public void setKhhh1(Double khhh1) {
		this.khhh1 = khhh1;
	}

	public Double getDcck() {
		return dcck;
	}

	public void setDcck(Double dcck) {
		this.dcck = dcck;
	}

	public Double getCyck() {
		return cyck;
	}

	public void setCyck(Double cyck) {
		this.cyck = cyck;
	}

	public Double getCyck1() {
		return cyck1;
	}

	public void setCyck1(Double cyck1) {
		this.cyck1 = cyck1;
	}

	public Double getMdck() {
		return mdck;
	}

	public void setMdck(Double mdck) {
		this.mdck = mdck;
	}

	public String getQghhno() {
		return qghhno;
	}

	public void setQghhno(String qghhno) {
		this.qghhno = qghhno;
	}

	public String getQghhnm() {
		return qghhnm;
	}

	public void setQghhnm(String qghhnm) {
		this.qghhnm = qghhnm;
	}

	public String getDchhno() {
		return dchhno;
	}

	public void setDchhno(String dchhno) {
		this.dchhno = dchhno;
	}

	public String getDchhnm() {
		return dchhnm;
	}

	public void setDchhnm(String dchhnm) {
		this.dchhnm = dchhnm;
	}

	public String getCyhhno() {
		return cyhhno;
	}

	public void setCyhhno(String cyhhno) {
		this.cyhhno = cyhhno;
	}

	public String getCyhhnm() {
		return cyhhnm;
	}

	public void setCyhhnm(String cyhhnm) {
		this.cyhhnm = cyhhnm;
	}

	public String getCyhhno1() {
		return cyhhno1;
	}

	public void setCyhhno1(String cyhhno1) {
		this.cyhhno1 = cyhhno1;
	}

	public String getCyhhnm1() {
		return cyhhnm1;
	}

	public void setCyhhnm1(String cyhhnm1) {
		this.cyhhnm1 = cyhhnm1;
	}

	public String getKhhhno1() {
		return khhhno1;
	}

	public void setKhhhno1(String khhhno1) {
		this.khhhno1 = khhhno1;
	}

	public String getKhhhnm1() {
		return khhhnm1;
	}

	public void setKhhhnm1(String khhhnm1) {
		this.khhhnm1 = khhhnm1;
	}

	public String getKhhhno() {
		return khhhno;
	}

	public void setKhhhno(String khhhno) {
		this.khhhno = khhhno;
	}

	public String getKhhhnm() {
		return khhhnm;
	}

	public void setKhhhnm(String khhhnm) {
		this.khhhnm = khhhnm;
	}

	public String getQgshno() {
		return qgshno;
	}

	public void setQgshno(String qgshno) {
		this.qgshno = qgshno;
	}

	public String getQgshnm() {
		return qgshnm;
	}

	public void setQgshnm(String qgshnm) {
		this.qgshnm = qgshnm;
	}

	public String getKhshno() {
		return khshno;
	}

	public void setKhshno(String khshno) {
		this.khshno = khshno;
	}

	public String getKhshnm() {
		return khshnm;
	}

	public void setKhshnm(String khshnm) {
		this.khshnm = khshnm;
	}

	public String getKhglno() {
		return khglno;
	}

	public void setKhglno(String khglno) {
		this.khglno = khglno;
	}

	public String getKhglnm() {
		return khglnm;
	}

	public void setKhglnm(String khglnm) {
		this.khglnm = khglnm;
	}

	public String getZdtlno() {
		return zdtlno;
	}

	public void setZdtlno(String zdtlno) {
		this.zdtlno = zdtlno;
	}

	public String getZdtlnm() {
		return zdtlnm;
	}

	public void setZdtlnm(String zdtlnm) {
		this.zdtlnm = zdtlnm;
	}

	public String getKhtlno() {
		return khtlno;
	}

	public void setKhtlno(String khtlno) {
		this.khtlno = khtlno;
	}

	public String getKhtlnm() {
		return khtlnm;
	}

	public void setKhtlnm(String khtlnm) {
		this.khtlnm = khtlnm;
	}

	public String getQgckno() {
		return qgckno;
	}

	public void setQgckno(String qgckno) {
		this.qgckno = qgckno;
	}

	public String getQgcknm() {
		return qgcknm;
	}

	public void setQgcknm(String qgcknm) {
		this.qgcknm = qgcknm;
	}

	public String getDcckno() {
		return dcckno;
	}

	public void setDcckno(String dcckno) {
		this.dcckno = dcckno;
	}

	public String getDccknm() {
		return dccknm;
	}

	public void setDccknm(String dccknm) {
		this.dccknm = dccknm;
	}

	public String getCyckno() {
		return cyckno;
	}

	public void setCyckno(String cyckno) {
		this.cyckno = cyckno;
	}

	public String getCycknm() {
		return cycknm;
	}

	public void setCycknm(String cycknm) {
		this.cycknm = cycknm;
	}

	public String getCyckno1() {
		return cyckno1;
	}

	public void setCyckno1(String cyckno1) {
		this.cyckno1 = cyckno1;
	}

	public String getCycknm1() {
		return cycknm1;
	}

	public void setCycknm1(String cycknm1) {
		this.cycknm1 = cycknm1;
	}

	public String getKhckno() {
		return khckno;
	}

	public void setKhckno(String khckno) {
		this.khckno = khckno;
	}

	public String getKhcknm() {
		return khcknm;
	}

	public void setKhcknm(String khcknm) {
		this.khcknm = khcknm;
	}

	public Double getQghhdj() {
		return qghhdj;
	}

	public void setQghhdj(Double qghhdj) {
		this.qghhdj = qghhdj;
	}

	public Double getDchhdj() {
		return dchhdj;
	}

	public void setDchhdj(Double dchhdj) {
		this.dchhdj = dchhdj;
	}

	public Double getCyhhdj() {
		return cyhhdj;
	}

	public void setCyhhdj(Double cyhhdj) {
		this.cyhhdj = cyhhdj;
	}

	public Double getCyhhdj1() {
		return cyhhdj1;
	}

	public void setCyhhdj1(Double cyhhdj1) {
		this.cyhhdj1 = cyhhdj1;
	}

	public Double getKhhhdj() {
		return khhhdj;
	}

	public void setKhhhdj(Double khhhdj) {
		this.khhhdj = khhhdj;
	}

	public Double getKhhhdj1() {
		return khhhdj1;
	}

	public void setKhhhdj1(Double khhhdj1) {
		this.khhhdj1 = khhhdj1;
	}

	public Double getQgshdj() {
		return qgshdj;
	}

	public void setQgshdj(Double qgshdj) {
		this.qgshdj = qgshdj;
	}

	public Double getKhshdj() {
		return khshdj;
	}

	public void setKhshdj(Double khshdj) {
		this.khshdj = khshdj;
	}

	public Double getKhgldj() {
		return khgldj;
	}

	public void setKhgldj(Double khgldj) {
		this.khgldj = khgldj;
	}

	public Double getZdtldj() {
		return zdtldj;
	}

	public void setZdtldj(Double zdtldj) {
		this.zdtldj = zdtldj;
	}

	public Double getKhtldj() {
		return khtldj;
	}

	public void setKhtldj(Double khtldj) {
		this.khtldj = khtldj;
	}

	public Double getQgckdj() {
		return qgckdj;
	}

	public void setQgckdj(Double qgckdj) {
		this.qgckdj = qgckdj;
	}

	public Double getDcckdj() {
		return dcckdj;
	}

	public void setDcckdj(Double dcckdj) {
		this.dcckdj = dcckdj;
	}

	public Double getCyckdj() {
		return cyckdj;
	}

	public void setCyckdj(Double cyckdj) {
		this.cyckdj = cyckdj;
	}

	public Double getCyckdj1() {
		return cyckdj1;
	}

	public void setCyckdj1(Double cyckdj1) {
		this.cyckdj1 = cyckdj1;
	}

	public Double getKhckdj() {
		return khckdj;
	}

	public void setKhckdj(Double khckdj) {
		this.khckdj = khckdj;
	}

	public Double getZtdj() {
		return ztdj;
	}

	public void setZtdj(Double ztdj) {
		this.ztdj = ztdj;
	}

	public Date getSzsj() {
		return szsj;
	}

	public void setSzsj(Date szsj) {
		this.szsj = szsj;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getZxno() {
		return zxno;
	}

	public void setZxno(String zxno) {
		this.zxno = zxno;
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

}
