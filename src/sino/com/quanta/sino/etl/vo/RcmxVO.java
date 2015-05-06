package com.quanta.sino.etl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ETL品质管理 页面当前卷 和备用卷查询VO
 * </p>
 * <p>
 * create: 2011-2-15 下午04:37:16
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class RcmxVO {

	/**
	 * 卷状态
	 */
	private String stat;
	/**
	 * 指示书号
	 */
	private String zsno;
	/**
	 * 操作方法
	 */
	private String caoz;
	/**
	 * 卷材号码
	 */
	private String jbno;
	/**
	 * 板厚
	 */
	private Double houu;
	/**
	 * 板宽
	 */
	private Double kuan;
	/**
	 * 板长
	 */
	private Double cang;

	/**
	 * 现品尺寸
	 */
	private String size;
	/**
	 * 锡付着量
	 */
	private String xfuz;
	/**
	 * 运用规格
	 */
	private String yuny;
	/**
	 * 表面精加工
	 */
	private String face;
	/**
	 * 合金层
	 */
	private String hjin;
	/**
	 * 涂油量
	 */
	private String yqty;
	/**
	 * 分配等级
	 */
	private String fpdj;
	/**
	 * 内径代码
	 */
	private String njno;
	/**
	 * 软溶要否
	 */
	private String flow;

	/**
	 * 化学处理方法
	 */
	private String hxcl;

	/**
	 * 别纸号
	 */
	private String kpn;
	/**
	 * 附页NO集合
	 */
	private List<String> kpns;

	/**
	 * 业务联络
	 */
	private String ywll;
	/**
	 * 业务联络集合
	 */
	private List<String> ylns;

	/**
	 * 制品重量
	 */
	private Integer zpzl;

	/**
	 * 备用卷是否确认
	 */
	private String sfqr;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getKpns() {
		if (this.kpns == null) {
			this.kpns = new ArrayList<String>();
		}
		return kpns;
	}

	public void setKpns(List<String> kpns) {
		this.kpns = kpns;
	}

	public List<String> getYlns() {
		if (this.ylns == null) {
			this.ylns = new ArrayList<String>();
		}
		return ylns;
	}

	public void setYlns(List<String> ylns) {
		this.ylns = ylns;
	}

	public String getSfqr() {
		return sfqr;
	}

	public void setSfqr(String sfqr) {
		this.sfqr = sfqr;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
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

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
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

	public Double getKuan() {
		return kuan;
	}

	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	public String getXfuz() {
		return xfuz;
	}

	public void setXfuz(String xfuz) {
		this.xfuz = xfuz;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getHjin() {
		return hjin;
	}

	public void setHjin(String hjin) {
		this.hjin = hjin;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getHxcl() {
		return hxcl;
	}

	public void setHxcl(String hxcl) {
		this.hxcl = hxcl;
	}

	public String getKpn() {
		return kpn;
	}

	public void setKpn(String kpn) {
		this.kpn = kpn;
	}

	public String getYwll() {
		return ywll;
	}

	public void setYwll(String ywll) {
		this.ywll = ywll;
	}

}
