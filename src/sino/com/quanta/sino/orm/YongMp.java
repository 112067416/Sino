package com.quanta.sino.orm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户主文件，简写YO
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "SINO_YONGMP")
public class YongMp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录识别
	 */
	@Column(name = "FLAG_", length = 2)
	private String flag;

	/**
	 * 作成时间（年月日时分秒）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）
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
	@Column(name = "RSV1_", length = 60)
	private String rsv1;

	/**
	 * 预备2
	 */
	@Column(name = "RSV2_", columnDefinition = "numeric(22)")
	private BigDecimal rsv2;

	/**
	 * 用户代码
	 */
	@Id
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 所在地
	 */
	@Column(name = "ADDR_", length = 80)
	private String addr;

	/**
	 * 电话号码
	 */
	@Column(name = "TELE_", length = 18)
	private String tele;

	/**
	 * 国名
	 */
	@Column(name = "GUOM_", length = 40)
	private String guom;

	/**
	 * 商社代码
	 */
	@Column(name = "SSNO_", length = 3)
	private String ssno;

	/**
	 * 商社名称
	 */
	@Column(name = "SSNM_", length = 60)
	private String ssnm;

	/**
	 * 检查证明书页数
	 */
	@Column(name = "JCHA_", length = 2)
	private Integer jcha;

	/**
	 * 内外销
	 */
	@Column(name = "NWAI_", length = 1)
	private String nwai;

	/**
	 * 信用额度（原系统没有，新增字段）
	 */
	@Column(name = "XYED_", columnDefinition = "numeric(15,2)")
	private Double xyed;

	/**
	 * 发票客户
	 */
	@Column(name = "FPKH_", length = 60)
	private String fpkh;

	/**
	 * 用户所属区域
	 */
	@Column(name = "REGION_", length = 2)
	private String region;

	/**
	 * 是否有效
	 */
	@Column(name = "VALID_", length = 2)
	private String valid;

	/**
	 * 客户所属业务员代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 客户所属业务员名称
	 */
	@Column(name = "DDNM_", length = 20)
	private String ddnm;

	/**
	 * 资金组成
	 */
	@Column(name = "FOND_", length = 128)
	private String fond;

	/**
	 * 联系人1
	 */
	@Column(name = "LINK1_", length = 20)
	private String link1;

	/**
	 * 联系人1职务
	 */
	@Column(name = "JOB1_", length = 20)
	private String job1;

	/**
	 * 
	 */
	@Column(name = "MOBILE1_", length = 13)
	private String mobile1;

	/**
	 * 
	 */
	@Column(name = "TEL1_", length = 24)
	private String tel1;

	/**
	 * 
	 */
	@Column(name = "FAX1_", length = 24)
	private String fax1;

	/**
	 * 联系人2
	 */
	@Column(name = "LINK2_", length = 20)
	private String link2;

	/**
	 * 联系人2职务
	 */
	@Column(name = "JOB2_", length = 20)
	private String job2;

	/**
	 * 
	 */
	@Column(name = "MOBILE2_", length = 13)
	private String mobile2;

	/**
	 * 
	 */
	@Column(name = "TEL2_", length = 24)
	private String tel2;

	/**
	 * 
	 */
	@Column(name = "FAX2_", length = 24)
	private String fax2;

	/**
	 * 联系人3
	 */
	@Column(name = "LINK3_", length = 20)
	private String link3;

	/**
	 * 联系人3职务
	 */
	@Column(name = "JOB3_", length = 20)
	private String job3;

	/**
	 * 
	 */
	@Column(name = "MOBILE3_", length = 13)
	private String mobile3;

	/**
	 * 
	 */
	@Column(name = "TEL3_", length = 24)
	private String tel3;

	/**
	 * 
	 */
	@Column(name = "FAX3_", length = 24)
	private String fax3;

	/**
	 * 成立日期
	 */
	@Column(name = "FDT_", length = 14)
	private String fdt;

	/**
	 * 人数
	 */
	@Column(name = "STAFFS_")
	private Integer staffs;

	/**
	 * 设备概要
	 */
	@Column(name = "SBJY_", length = 256)
	private String sbjy;

	/**
	 * 商谈形式
	 */
	@Column(name = "CHAT_", length = 32)
	private String chat;

	/**
	 * 经销商
	 */
	@Column(name = "DEALER_", length = 32)
	private String dealer;

	/**
	 * 供应商
	 */
	@Column(name = "SUPPLIER_", length = 128)
	private String supplier;

	/**
	 * 付款方式
	 */
	@Column(name = "PAYMENT_", length = 32)
	private String payment;

	/**
	 * 历史及最近消息
	 */
	@Column(name = "NEWS_", length = 2048)
	private String news;

	/**
	 * 一季度销售量
	 */
	@Column(name = "OXSL_")
	private Long oxsl;

	/**
	 * 二季度销售量
	 */
	@Column(name = "SXSL_")
	private Long sxsl;

	/**
	 * 三季度销售量
	 */
	@Column(name = "TXSL_")
	private Long txsl;

	/**
	 * 四季度销售量
	 */
	@Column(name = "FXSL_")
	private Long fxsl;

	/**
	 * 总季度销售量
	 */
	@Column(name = "AXSL_")
	private Long axsl;

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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the tele
	 */
	public String getTele() {
		return tele;
	}

	/**
	 * @param tele
	 *            the tele to set
	 */
	public void setTele(String tele) {
		this.tele = tele;
	}

	/**
	 * @return the guom
	 */
	public String getGuom() {
		return guom;
	}

	/**
	 * @param guom
	 *            the guom to set
	 */
	public void setGuom(String guom) {
		this.guom = guom;
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
	 * @return the jcha
	 */
	public Integer getJcha() {
		return jcha;
	}

	/**
	 * @param jcha
	 *            the jcha to set
	 */
	public void setJcha(Integer jcha) {
		this.jcha = jcha;
	}

	/**
	 * @return the nwai
	 */
	public String getNwai() {
		return nwai;
	}

	/**
	 * @param nwai
	 *            the nwai to set
	 */
	public void setNwai(String nwai) {
		this.nwai = nwai;
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

	/**
	 * @return the xyed
	 */
	public Double getXyed() {
		return xyed;
	}

	/**
	 * @param xyed
	 *            the xyed to set
	 */
	public void setXyed(Double xyed) {
		this.xyed = xyed;
	}

	public String getFpkh() {
		return fpkh;
	}

	public void setFpkh(String fpkh) {
		this.fpkh = fpkh;
	}

	public String getSsnm() {
		return ssnm;
	}

	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
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

	public String getFond() {
		return fond;
	}

	public void setFond(String fond) {
		this.fond = fond;
	}

	public String getLink1() {
		return link1;
	}

	public void setLink1(String link1) {
		this.link1 = link1;
	}

	public String getJob1() {
		return job1;
	}

	public void setJob1(String job1) {
		this.job1 = job1;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getLink2() {
		return link2;
	}

	public void setLink2(String link2) {
		this.link2 = link2;
	}

	public String getJob2() {
		return job2;
	}

	public void setJob2(String job2) {
		this.job2 = job2;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getLink3() {
		return link3;
	}

	public void setLink3(String link3) {
		this.link3 = link3;
	}

	public String getJob3() {
		return job3;
	}

	public void setJob3(String job3) {
		this.job3 = job3;
	}

	public String getMobile3() {
		return mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getFax3() {
		return fax3;
	}

	public void setFax3(String fax3) {
		this.fax3 = fax3;
	}

	public String getFdt() {
		return fdt;
	}

	public void setFdt(String fdt) {
		this.fdt = fdt;
	}

	public Integer getStaffs() {
		return staffs;
	}

	public void setStaffs(Integer staffs) {
		this.staffs = staffs;
	}

	public String getSbjy() {
		return sbjy;
	}

	public void setSbjy(String sbjy) {
		this.sbjy = sbjy;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Long getOxsl() {
		return oxsl;
	}

	public void setOxsl(Long oxsl) {
		this.oxsl = oxsl;
	}

	public Long getSxsl() {
		return sxsl;
	}

	public void setSxsl(Long sxsl) {
		this.sxsl = sxsl;
	}

	public Long getTxsl() {
		return txsl;
	}

	public void setTxsl(Long txsl) {
		this.txsl = txsl;
	}

	public Long getFxsl() {
		return fxsl;
	}

	public void setFxsl(Long fxsl) {
		this.fxsl = fxsl;
	}

	public Long getAxsl() {
		return axsl;
	}

	public void setAxsl(Long axsl) {
		this.axsl = axsl;
	}

}
