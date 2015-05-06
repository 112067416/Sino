package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 订货指示VO
 * </p>
 * <p>
 * create: 2010-12-28 下午05:45:39
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class ZsdhVO {

	/**
	 * 指示No
	 */
	@QF
	private String zsno;

	/**
	 * 订货No.行号
	 */
	@QF
	private String dhno;

	/**
	 * 生产线别
	 */
	@QF
	private String shch;

	/**
	 * 操作
	 */
	@QF
	private String caoz;

	/**
	 * 用户略称
	 */
	@QF
	private String abbr;

	/**
	 * 运用规格
	 */
	@QF
	private String yuny;

	/**
	 * 制品尺寸.厚
	 */
	@QF
	private Double houu;

	/**
	 * 制品尺寸.宽
	 */
	@QF
	private Double kuan;
	/**
	 * 制品尺寸.长
	 */
	@QF
	private Double cang;
	/**
	 * 制品尺寸（W表示编辑完）
	 */
	@QF
	private String zpcc;
	/**
	 * 附着量
	 */
	@QF
	private String fugm;

	/**
	 * 总重量(吨)
	 */
	@QF(alias = "(select sum(b.zpzl)/1000.000 from YcaiTp b where b.zsno=a.zsno) as allzpzl ")
	private Double allzpzl;

	/**
	 * 表面
	 */
	@QF
	private String face;

	/**
	 * 内径
	 */
	@QF
	private String njno;

	/**
	 * 指示书打印
	 */
	@QF
	private String zsfx;
	/**
	 * 指示书缺陷打印
	 */
	@QF
	private String zsqx;
	/**
	 * 备注
	 */
	@QF
	private String remk;

	/**
	 * 紧急
	 */
	@QF
	private String jinj;
	/**
	 * 分派
	 */
	@QF
	private String stat;
	/**
	 * 作成时间
	 */
	@QF
	private Date crea;
	/**
	 * 完成时间
	 */
	@QF
	private Date zsny;
	/**
	 * 分配等级
	 */
	@QF
	private String fpdj;
	/**
	 * 原板原材状态
	 */
	@QF(alias = "(select count(c.jbno) from YcaiTp c where c.zsno=a.zsno and c.stat='3') as ycaiStat ")
	private Long ycaiStat;
	/**
	 * 指示书中已实绩完成的卷
	 */
	@QF(alias = "(select count(c.jbno) from YcaiTp c where c.zsno=a.zsno and c.stat in('4','5')) as ywcYcaiCount ")
	private Long ywcYcaiCount;
	/**
	 * 指示书中所有的卷
	 */
	@QF(alias = "(select count(d.jbno) from YcaiTp d where d.zsno=a.zsno) as ycaiAllCount")
	private Long ycaiAllCount;
	/**
	 * 指示书中所有的卷垫木状态
	 */
	@QF(alias = "(select count(d.jbno) from YcaiTp d where d.zsno=a.zsno and d.sfdm='1') as ycaiSfdmCount")
	private Long ycaiSfdmCount;
	/**
	 * 指示书中所有的卷紧急状态
	 */
	@QF(alias = "(select count(d.jbno) from YcaiTp d where d.zsno=a.zsno and d.jinj='1') as ycaijinjCount")
	private Long ycaijinjCount;
	/**
	 * 序号
	 */
	@QF
	private Double sort;
	/**
	 * 垫木状态
	 */
	@QF
	private String sfdm;

	public String getZsqx() {
		return zsqx;
	}

	public void setZsqx(String zsqx) {
		this.zsqx = zsqx;
	}

	public String getZpcc() {
		return zpcc;
	}

	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getShch() {
		return shch;
	}

	public void setShch(String shch) {
		this.shch = shch;
	}

	public String getCaoz() {
		return caoz;
	}

	public void setCaoz(String caoz) {
		this.caoz = caoz;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Double getHouu() {
		return houu;
	}

	public void setHouu(Double houu) {
		this.houu = houu;
	}

	public Double getCang() {
		return cang;
	}

	public void setCang(Double cang) {
		this.cang = cang;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getZsfx() {
		return zsfx;
	}

	public void setZsfx(String zsfx) {
		this.zsfx = zsfx;
	}

	public String getJinj() {
		return jinj;
	}

	public void setJinj(String jinj) {
		this.jinj = jinj;
	}

	public Long getYwcYcaiCount() {
		return ywcYcaiCount;
	}

	public void setYwcYcaiCount(Long ywcYcaiCount) {
		this.ywcYcaiCount = ywcYcaiCount;
	}

	public Long getYcaiAllCount() {
		return ycaiAllCount;
	}

	public void setYcaiAllCount(Long ycaiAllCount) {
		this.ycaiAllCount = ycaiAllCount;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFugm() {
		return fugm;
	}

	public void setFugm(String fugm) {
		this.fugm = fugm;
	}

	public Double getAllzpzl() {
		return allzpzl;
	}

	public void setAllzpzl(Double allzpzl) {
		this.allzpzl = allzpzl;
	}

	public Long getYcaiStat() {
		if (this.ycaiStat != null && this.ycaiStat > 0) {
			return 1l;
		}
		return 0l;
	}

	public void setYcaiStat(Long ycaiStat) {
		this.ycaiStat = ycaiStat;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Date getZsny() {
		return zsny;
	}

	public void setZsny(Date zsny) {
		this.zsny = zsny;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}

	public String getSfdm() {
		return sfdm;
	}

	public void setSfdm(String sfdm) {
		this.sfdm = sfdm;
	}

	public Long getYcaiSfdmCount() {
		return ycaiSfdmCount;
	}

	public void setYcaiSfdmCount(Long ycaiSfdmCount) {
		this.ycaiSfdmCount = ycaiSfdmCount;
	}

	public Long getYcaijinjCount() {
		return ycaijinjCount;
	}

	public void setYcaijinjCount(Long ycaijinjCount) {
		this.ycaijinjCount = ycaijinjCount;
	}

}
