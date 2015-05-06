package com.quanta.sino.ss.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * SS分选数据
 * </p>
 * <p>
 * create: 2011-1-20 下午08:22:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsVO {

	/**
	 * <p>
	 * 格式成制品尺寸
	 * </p>
	 * 
	 * @return String
	 */
	public String getSize() {
		return SinoUtils.formatProductSize(houu, kuan, cang);
	}

	/**
	 * <p>
	 * 获取计算张数
	 * </p>
	 * 
	 * @return int
	 */
	public Integer getJszs() {
		Integer zshu = 0;
		if (items != null) {
			for (SsItemVO itemVo : items) {
				zshu += itemVo.getZshu();
			}
		}
		return zshu;
	}

	/**
	 * <p>
	 * 获取计算张数重量
	 * </p>
	 * 
	 * @return int
	 */
	public Integer getJszszl() {
		return (Integer) (SinoUtils.calculate(houu, kuan, cang, getJszs()));
	}

	/**
	 * <p>
	 * 获取计算重量
	 * </p>
	 * 
	 * @return int
	 */
	public Integer getJszl() {
		return (Integer) (SinoUtils.calculate(houu, kuan, cang, zshu));
	}

	/**
	 * 卷板No.
	 */
	private String jbno;

	/**
	 * 张数
	 */
	private Integer zshu;
	/**
	 * 制品尺寸
	 */
	private String zpcc;
	/**
	 * 实际重量
	 */
	private Integer sjzl;

	/**
	 * 计算重量
	 */
	private Integer jszz;

	/**
	 * 要求厚度
	 */
	private Double houu;

	/**
	 * 要求宽度
	 */
	private Double kuan;

	/**
	 * 要求长度
	 */
	private Double cang;

	/**
	 * 产量代码
	 */
	private String chan;

	/**
	 * 等级
	 */
	private String deng;

	/**
	 * 缺陷
	 */
	private String qqdm;

	/**
	 * 检定员
	 */
	private String jdyn;

	/**
	 * 计数员
	 */
	private String jsyn;

	/**
	 * 是否翻转
	 */
	private String sffz;

	/**
	 * 班
	 */
	private String ban;

	/**
	 * 组
	 */
	private String zu;

	/**
	 * 制品列表
	 */
	private List<SsItemVO> items;

	/**
	 * 正反镀锡量不一致
	 */
	private boolean diffDxl;

	/**
	 * 合并包时刻
	 */
	private Date crea;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 出口包装号
	 */
	private Integer ckno;

	/**
	 * 订货表面
	 */
	private String face;

	/**
	 * 变量
	 */
	private String variable;

	/**
	 * 剪断长
	 */
	private Double jdcn;

	/**
	 * 包装张数
	 */
	private Integer bzzs;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 附着量-正面
	 */
	private String fuzm;

	/**
	 * 附着量-正面
	 */
	private String fufm;

	/**
	 * 附页NO集合
	 */
	private List<String> kpns;

	/**
	 * 业务联络集合
	 */
	private List<String> ylns;

	/**
	 * 特记
	 */
	private String teji;
	/**
	 * 制品商代码
	 */
	private String zzsd;
	/**
	 * 修正区分
	 */
	private String clq;
	/**
	 * Pile区分
	 */
	private String plq;
	/**
	 * 订货NO
	 */
	private String dhno;

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public Integer getJszz() {
		return jszz;
	}

	public void setJszz(Integer jszz) {
		this.jszz = jszz;
	}

	public String getZpcc() {
		return zpcc;
	}

	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
	}

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the sjzl
	 */
	public Integer getSjzl() {
		return sjzl;
	}

	/**
	 * @param sjzl
	 *            the sjzl to set
	 */
	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	/**
	 * @return the deng
	 */
	public String getDeng() {
		return deng;
	}

	/**
	 * @param deng
	 *            the deng to set
	 */
	public void setDeng(String deng) {
		this.deng = deng;
	}

	/**
	 * @return the jdyn
	 */
	public String getJdyn() {
		return jdyn;
	}

	/**
	 * @param jdyn
	 *            the jdyn to set
	 */
	public void setJdyn(String jdyn) {
		this.jdyn = jdyn;
	}

	/**
	 * @return the jsyn
	 */
	public String getJsyn() {
		return jsyn;
	}

	/**
	 * @param jsyn
	 *            the jsyn to set
	 */
	public void setJsyn(String jsyn) {
		this.jsyn = jsyn;
	}

	/**
	 * @return the sffz
	 */
	public String getSffz() {
		return sffz;
	}

	/**
	 * @param sffz
	 *            the sffz to set
	 */
	public void setSffz(String sffz) {
		this.sffz = sffz;
	}

	public List<SsItemVO> getItems() {
		if (this.items == null) {
			this.items = new ArrayList<SsItemVO>();
		}
		return items;
	}

	public void setItems(List<SsItemVO> items) {
		this.items = items;
	}

	/**
	 * @return the houu
	 */
	public Double getHouu() {
		return houu;
	}

	/**
	 * @param houu
	 *            the houu to set
	 */
	public void setHouu(Double houu) {
		this.houu = houu;
	}

	/**
	 * @return the kuan
	 */
	public Double getKuan() {
		return kuan;
	}

	/**
	 * @param kuan
	 *            the kuan to set
	 */
	public void setKuan(Double kuan) {
		this.kuan = kuan;
	}

	/**
	 * @return the cang
	 */
	public Double getCang() {
		return cang;
	}

	/**
	 * @param cang
	 *            the cang to set
	 */
	public void setCang(Double cang) {
		this.cang = cang;
	}

	/**
	 * @return the chan
	 */
	public String getChan() {
		return chan;
	}

	/**
	 * @param chan
	 *            the chan to set
	 */
	public void setChan(String chan) {
		this.chan = chan;
	}

	/**
	 * @return the qqdm
	 */
	public String getQqdm() {
		return qqdm;
	}

	/**
	 * @param qqdm
	 *            the qqdm to set
	 */
	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	/**
	 * @return the diffDxl
	 */
	public boolean isDiffDxl() {
		return diffDxl;
	}

	/**
	 * @param diffDxl
	 *            the diffDxl to set
	 */
	public void setDiffDxl(boolean diffDxl) {
		this.diffDxl = diffDxl;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Double getJdcn() {
		return jdcn;
	}

	public void setJdcn(Double jdcn) {
		this.jdcn = jdcn;
	}

	public Integer getBzzs() {
		return bzzs;
	}

	public void setBzzs(Integer bzzs) {
		this.bzzs = bzzs;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
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

	public String getTeji() {
		return teji;
	}

	public void setTeji(String teji) {
		this.teji = teji;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public String getClq() {
		return clq;
	}

	public void setClq(String clq) {
		this.clq = clq;
	}

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getPlq() {
		return plq;
	}

	public void setPlq(String plq) {
		this.plq = plq;
	}

}
