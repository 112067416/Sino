package com.quanta.sino.zs.vo;

import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 指示未完成显示数据
 * </p>
 * <p>
 * create: 2011-1-18 上午09:08:06
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class ZsVO {

	/**
	 * 指示书No
	 */
	@QF
	private String zsno;
	/**
	 * 操作Mode
	 */
	@QF
	private String caoz;
	/**
	 * 装入宽
	 */
	@QF
	private Double zrkn;
	/**
	 * 订货No.行号
	 */
	@QF
	private String dhno;
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
	 * 分配等级
	 */
	@QF
	private String fpdj;
	/**
	 * 订货表面精加工符号
	 */
	@QF
	private String face;
	/**
	 * 制品尺寸（W表示编辑完）
	 */
	@QF
	private String zpcc;
	/**
	 * 附着量（g/m2）
	 */
	@QF
	private String fugm;
	/**
	 * 作成时间（年月日时分秒）
	 */
	@QF
	private Date crea;
	/**
	 * 分派
	 */
	@QF
	private String stat;
	/**
	 * 打印标志
	 */
	@QF
	private String zsfx;

	/**
	 * 是否紧急
	 */
	@QF
	private String jinj;
	/**
	 * Coil转sheet
	 */
	@QF
	private String sfcl;
	/**
	 * 垫木状态
	 */
	@QF
	private String sfdm;
	/**
	 * 备注
	 */
	@QF
	private String remk;
	/**
	 * 指示完了标记：0-指示未完；1-指示完了
	 */
	@QF
	private String zsbj;
	/**
	 * 指示完了时间（年月日时分秒）
	 */
	@QF
	private Date zsny;
	/**
	 * 指示书中的卷总重量
	 */
	@QF(alias = "(select sum(b.zpzl) from ZpngTp b where b.zsno=a.zsno and len(b.jbno)=8) as zzl ")
	private Long zzl;
	/**
	 * 指示书中已实绩完成的卷
	 */
	@QF(alias = "(select count(c) from ZpngTp c where c.zsno=a.zsno and c.stat in('2','3')) as ywcjs ")
	private Long ywcjs;
	/**
	 * 指示书中所有的卷
	 */
	@QF(alias = "(select count(d) from ZpngTp d where d.zsno=a.zsno and d.duic='D') as zjs")
	private Long zjs;
	/**
	 * 指示书中所有的卷垫木状态
	 */
	@QF(alias = "(select count(d) from ZpngTp d,YcaiTp c where d.zsno=a.zsno and d.duic='D' and d.rczpno=c.jbno and c.sfdm='1') as sfdmzjs")
	private Long sfdmzjs;
	/**
	 * 指示书中所有的卷紧急状态
	 */
	@QF(alias = "(select count(d) from ZpngTp d,YcaiTp c where d.zsno=a.zsno and d.duic='D' and d.rczpno=c.jbno and c.jinj='1') as jinjzjs")
	private Long jinjzjs;
	/**
	 * 指示书中正在实绩中的卷数
	 */
	@QF(alias = "(select count(f)  from ZpngTp f where f.zsno=a.zsno and f.stat='1' and len(f.jbno)<10 and slin='V') as vsjz")
	private Long vsjz;
	/**
	 * 指示书中正在实绩中的卷数
	 */
	@QF(alias = "(select count(f)  from ZpngTp f where f.zsno=a.zsno and f.stat='1' and len(f.jbno)<10 and slin='W') as wsjz")
	private Long wsjz;
	/**
	 * 序号
	 */
	@QF
	private Double sort;

	public String getZsfx() {
		return zsfx;
	}

	public void setZsfx(String zsfx) {
		this.zsfx = zsfx;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getCaoz() {
		return caoz;
	}

	public void setCaoz(String caoz) {
		this.caoz = caoz;
	}

	public Double getZrkn() {
		return zrkn;
	}

	public void setZrkn(Double zrkn) {
		this.zrkn = zrkn;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getZpcc() {
		return zpcc;
	}

	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
	}

	public String getFugm() {
		return fugm;
	}

	public void setFugm(String fugm) {
		this.fugm = fugm;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public String getJinj() {
		return jinj;
	}

	public void setJinj(String jinj) {
		this.jinj = jinj;
	}

	public String getZsbj() {
		return zsbj;
	}

	public void setZsbj(String zsbj) {
		this.zsbj = zsbj;
	}

	public Long getYwcjs() {
		return ywcjs;
	}

	public void setYwcjs(Long ywcjs) {
		this.ywcjs = ywcjs;
	}

	public Long getZjs() {
		return zjs;
	}

	public void setZjs(Long zjs) {
		this.zjs = zjs;
	}

	public Long getZzl() {
		return zzl;
	}

	public void setZzl(Long zzl) {
		this.zzl = zzl;
	}

	public Date getZsny() {
		return zsny;
	}

	public void setZsny(Date zsny) {
		this.zsny = zsny;
	}

	public String getSfcl() {
		return sfcl;
	}

	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}

	public String getSfdm() {
		return sfdm;
	}

	public void setSfdm(String sfdm) {
		this.sfdm = sfdm;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}

	public Long getVsjz() {
		return vsjz;
	}

	public void setVsjz(Long vsjz) {
		this.vsjz = vsjz;
	}

	public Long getWsjz() {
		return wsjz;
	}

	public void setWsjz(Long wsjz) {
		this.wsjz = wsjz;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Long getSfdmzjs() {
		return sfdmzjs;
	}

	public void setSfdmzjs(Long sfdmzjs) {
		this.sfdmzjs = sfdmzjs;
	}

	public Long getJinjzjs() {
		return jinjzjs;
	}

	public void setJinjzjs(Long jinjzjs) {
		this.jinjzjs = jinjzjs;
	}

}
