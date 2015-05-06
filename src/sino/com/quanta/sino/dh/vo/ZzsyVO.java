package com.quanta.sino.dh.vo;

/**
 * <p>
 * 根据订货合同,获得初始的制造仕样信息
 * </p>
 * <p>
 * create: 2011-3-15 下午08:15:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZzsyVO {

	/**
	 * 制造仕样NO
	 */
	private String syno;

	/**
	 * 剪边
	 */
	private String jian;

	/**
	 * 零头下限.单位
	 */
	private String ltdw;
	/**
	 * 零头下限.值
	 */
	private Integer ltzi;

	/**
	 * 硬度.上限值
	 */
	private String ymax;
	/**
	 * 硬度.下限值
	 */
	private String ymin;

	/**
	 * 合金层
	 */
	private String hjin;

	/**
	 * 涂油种类
	 */
	private String ytyp;

	/**
	 * 涂油量
	 */
	private String yqty;

	/**
	 * K-Plate
	 */
	private String plat;

	/**
	 * 附着面.正面.上限值
	 */
	private Double fuzs;

	/**
	 * 附着面.正面.下限值
	 */
	private Double fuzx;

	/**
	 * 附着面.反面.上限值
	 */
	private Double fufs;

	/**
	 * 附着面.反面.下限值
	 */
	private Double fufx;

	/**
	 * 附页KPNO.1标识位。区分ETL、SL和全部
	 */
	private String kpn1Flag;

	/**
	 * 附页KPNO.1
	 */
	private String kpn1;

	/**
	 * 附页KPNO.2标识位。区分ETL、SL和全部
	 */
	private String kpn2Flag;

	/**
	 * 附页KPNO.2
	 */
	private String kpn2;

	/**
	 * 附页KPNO.3标识位。区分ETL、SL和全部
	 */
	private String kpn3Flag;

	/**
	 * 附页KPNO.3
	 */
	private String kpn3;

	/**
	 * 附页KPNO.4标识位。区分ETL、SL和全部
	 */
	private String kpn4Flag;

	/**
	 * 附页KPNO.4
	 */
	private String kpn4;
	/**
	 * 木工所NO
	 */
	private String mgsn;

	/**
	 * 备注1(etl和木工所备注)
	 */
	private String bz1;

	/**
	 * 备注2(sl备注)
	 */
	private String bz2;

	/**
	 * 备注3(etl、木工所和sl备注)
	 */
	private String bz3;

	/**
	 * 分配等级
	 */
	private String fpdj;

	/**
	 * 直角度
	 */
	private Double jiao;

	/**
	 * 内径保护筒
	 */
	private String njbh;
	/**
	 * 保护标志
	 */
	private String prot;

	/**
	 * 运用规格
	 */
	private String yuny;
	/**
	 * 装入宽
	 */
	private Double zrkn;

	/**
	 * 尺寸允许范围.厚%上限.值
	 */
	private Double hszi;

	/**
	 * 尺寸允许范围.厚%下限.值
	 */
	private Double hxzi;

	/**
	 * 尺寸允许范围.宽mm上限.值
	 */
	private Double kszi;

	/**
	 * 尺寸允许范围.宽mm下限.值
	 */
	private Double kxzi;

	/**
	 * 尺寸允许范围.长mm上限.值
	 */
	private Double cszi;

	/**
	 * 尺寸允许范围.长mm下限.值
	 */
	private Double cxzi;

	/**
	 * 锯齿形式
	 */
	private String jcxs;

	/**
	 * 证书提示信息
	 */
	private String jcts;

	public String getSyno() {
		return syno;
	}

	public void setSyno(String syno) {
		this.syno = syno;
	}

	public String getJian() {
		return jian;
	}

	public void setJian(String jian) {
		this.jian = jian;
	}

	public String getLtdw() {
		return ltdw;
	}

	public void setLtdw(String ltdw) {
		this.ltdw = ltdw;
	}

	public Integer getLtzi() {
		return ltzi;
	}

	public void setLtzi(Integer ltzi) {
		this.ltzi = ltzi;
	}

	public String getYmax() {
		return ymax;
	}

	public void setYmax(String ymax) {
		this.ymax = ymax;
	}

	public String getYmin() {
		return ymin;
	}

	public void setYmin(String ymin) {
		this.ymin = ymin;
	}

	public String getHjin() {
		return hjin;
	}

	public void setHjin(String hjin) {
		this.hjin = hjin;
	}

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getYqty() {
		return yqty;
	}

	public void setYqty(String yqty) {
		this.yqty = yqty;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public Double getFuzs() {
		return fuzs;
	}

	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	public Double getFuzx() {
		return fuzx;
	}

	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	public Double getFufs() {
		return fufs;
	}

	public void setFufs(Double fufs) {
		this.fufs = fufs;
	}

	public Double getFufx() {
		return fufx;
	}

	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	public String getKpn1Flag() {
		return kpn1Flag;
	}

	public void setKpn1Flag(String kpn1Flag) {
		this.kpn1Flag = kpn1Flag;
	}

	public String getKpn1() {
		return kpn1;
	}

	public void setKpn1(String kpn1) {
		this.kpn1 = kpn1;
	}

	public String getKpn2Flag() {
		return kpn2Flag;
	}

	public void setKpn2Flag(String kpn2Flag) {
		this.kpn2Flag = kpn2Flag;
	}

	public String getKpn2() {
		return kpn2;
	}

	public void setKpn2(String kpn2) {
		this.kpn2 = kpn2;
	}

	public String getKpn3Flag() {
		return kpn3Flag;
	}

	public void setKpn3Flag(String kpn3Flag) {
		this.kpn3Flag = kpn3Flag;
	}

	public String getKpn3() {
		return kpn3;
	}

	public void setKpn3(String kpn3) {
		this.kpn3 = kpn3;
	}

	public String getKpn4Flag() {
		return kpn4Flag;
	}

	public void setKpn4Flag(String kpn4Flag) {
		this.kpn4Flag = kpn4Flag;
	}

	public String getKpn4() {
		return kpn4;
	}

	public void setKpn4(String kpn4) {
		this.kpn4 = kpn4;
	}

	public String getMgsn() {
		return mgsn;
	}

	public void setMgsn(String mgsn) {
		this.mgsn = mgsn;
	}

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public Double getJiao() {
		return jiao;
	}

	public void setJiao(Double jiao) {
		this.jiao = jiao;
	}

	public String getNjbh() {
		return njbh;
	}

	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}

	public String getProt() {
		return prot;
	}

	public void setProt(String prot) {
		this.prot = prot;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Double getZrkn() {
		return zrkn;
	}

	public void setZrkn(Double zrkn) {
		this.zrkn = zrkn;
	}

	public Double getHszi() {
		return hszi;
	}

	public void setHszi(Double hszi) {
		this.hszi = hszi;
	}

	public Double getHxzi() {
		return hxzi;
	}

	public void setHxzi(Double hxzi) {
		this.hxzi = hxzi;
	}

	public Double getKszi() {
		return kszi;
	}

	public void setKszi(Double kszi) {
		this.kszi = kszi;
	}

	public Double getKxzi() {
		return kxzi;
	}

	public void setKxzi(Double kxzi) {
		this.kxzi = kxzi;
	}

	public Double getCszi() {
		return cszi;
	}

	public void setCszi(Double cszi) {
		this.cszi = cszi;
	}

	public Double getCxzi() {
		return cxzi;
	}

	public void setCxzi(Double cxzi) {
		this.cxzi = cxzi;
	}

	public String getJcxs() {
		return jcxs;
	}

	public void setJcxs(String jcxs) {
		this.jcxs = jcxs;
	}

	public String getJcts() {
		return jcts;
	}

	public void setJcts(String jcts) {
		this.jcts = jcts;
	}

}
