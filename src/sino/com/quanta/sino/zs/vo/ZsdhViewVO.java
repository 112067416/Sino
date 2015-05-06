package com.quanta.sino.zs.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 查看指示书实体
 * </p>
 * <p>
 * create: 2011-2-24 下午04:07:08
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class ZsdhViewVO {
	/**
	 * 指示书No
	 */
	private String zsno;
	/**
	 * 操作方法
	 */
	private String caoz;
	/**
	 * 制品尺寸
	 */
	private String zpcc;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 装入宽
	 */
	private Double zrkn;
	/**
	 * 剪断长
	 */
	private Double jdcn;
	/**
	 * 运用规格
	 */
	private String yuny;
	/**
	 * M单重
	 */
	private Double mdan;
	/**
	 * 薄板单重
	 */
	private Double bdan;
	/**
	 * 内径代码
	 */
	private String njno;
	/**
	 * 分配等级
	 */
	private String fpdj;
	/**
	 * 锡付着量
	 */
	private String fugm;
	/**
	 * 附着量-正(下限)
	 */
	private Double fuzx;
	/**
	 * 附着量-正(上限)
	 */
	private Double fuzs;
	/**
	 * 附着量-反(下限)
	 */
	private Double fufx;
	/**
	 * 附着量-反(上限)
	 */
	private Double fufs;
	/**
	 * 表面精加工
	 */
	private String face;
	/**
	 * 交货区分
	 */
	private String jhdd;
	/**
	 * 合同号-行号
	 */
	private String dhno;
	/**
	 * 附页NO集合
	 */
	private List<String> kpns;
	/**
	 * 备注1
	 */
	private String bz1;
	/**
	 * 备注2
	 */
	private String bz3;
	/**
	 * 木工所业联
	 */
	private String mgsn;
	/**
	 * 公差厚(上限)
	 */
	private Double mszi;
	/**
	 * 公差厚(下限)
	 */
	private Double mxzi;
	/**
	 * 公差宽(上限)
	 */
	private Double kszi;
	/**
	 * 公差宽(下限)
	 */
	private Double kxzi;
	/**
	 * 剪断长(上限)
	 */
	private Double cszi;
	/**
	 * 剪断长(下限)
	 */
	private Double cxzi;
	/**
	 * 硬度(上限)
	 */
	private String ymax;
	/**
	 * 硬度(下限)
	 */
	private String ymin;
	/**
	 * 品种
	 */
	private String pzno;
	/**
	 * 卷重下限
	 */
	private Double kbzx;
	/**
	 * 卷重上限
	 */
	private Double kbzs;
	/**
	 * 涂油种类
	 */
	private String ytyp;
	/**
	 * 涂油量
	 */
	private String yqty;
	/**
	 * 纸圈
	 */
	private String njbh;
	/**
	 * 包装张数
	 */
	private String kbsz;
	/**
	 * 捆包形式
	 */
	private String kbao;
	/**
	 * 垫木数量
	 */
	private Integer dmsz;
	/**
	 * 垫木方向
	 */
	private String dmfx;
	/**
	 * Lot重量
	 */
	private String lotz;

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public Double getBdan() {
		return bdan;
	}

	public void setBdan(Double bdan) {
		this.bdan = bdan;
	}

	public Double getMdan() {
		return mdan;
	}

	public void setMdan(Double mdan) {
		this.mdan = mdan;
	}

	public Double getMszi() {
		return mszi;
	}

	public void setMszi(Double mszi) {
		this.mszi = mszi;
	}

	public Double getMxzi() {
		return mxzi;
	}

	public void setMxzi(Double mxzi) {
		this.mxzi = mxzi;
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

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
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

	public String getZpcc() {
		return zpcc;
	}

	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
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

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getFpdj() {
		return fpdj;
	}

	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	public String getFugm() {
		return fugm;
	}

	public void setFugm(String fugm) {
		this.fugm = fugm;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getJhdd() {
		return jhdd;
	}

	public void setJhdd(String jhdd) {
		this.jhdd = jhdd;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public Double getZrkn() {
		return zrkn;
	}

	public void setZrkn(Double zrkn) {
		this.zrkn = zrkn;
	}

	public Double getJdcn() {
		return jdcn;
	}

	public void setJdcn(Double jdcn) {
		this.jdcn = jdcn;
	}

	public Double getFuzx() {
		return fuzx;
	}

	public void setFuzx(Double fuzx) {
		this.fuzx = fuzx;
	}

	public Double getFuzs() {
		return fuzs;
	}

	public void setFuzs(Double fuzs) {
		this.fuzs = fuzs;
	}

	public Double getFufx() {
		return fufx;
	}

	public void setFufx(Double fufx) {
		this.fufx = fufx;
	}

	public Double getFufs() {
		return fufs;
	}

	public void setFufs(Double fufs) {
		this.fufs = fufs;
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

	public String getNjbh() {
		return njbh;
	}

	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}

	public Double getKbzx() {
		return kbzx;
	}

	public void setKbzx(Double kbzx) {
		this.kbzx = kbzx;
	}

	public Double getKbzs() {
		return kbzs;
	}

	public void setKbzs(Double kbzs) {
		this.kbzs = kbzs;
	}

	public String getKbsz() {
		return kbsz;
	}

	public void setKbsz(String kbsz) {
		this.kbsz = kbsz;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public Integer getDmsz() {
		return dmsz;
	}

	public void setDmsz(Integer dmsz) {
		this.dmsz = dmsz;
	}

	public String getDmfx() {
		return dmfx;
	}

	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	public String getLotz() {
		return lotz;
	}

	public void setLotz(String lotz) {
		this.lotz = lotz;
	}

	public String getMgsn() {
		return mgsn;
	}

	public void setMgsn(String mgsn) {
		this.mgsn = mgsn;
	}

}
