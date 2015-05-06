package com.quanta.sino.ss.vo;

import java.util.ArrayList;
import java.util.List;

import com.quanta.sino.util.SinoUtils;

/**
 * <p>
 * 分选数据
 * </p>
 * <p>
 * create: 2011-1-20 下午07:59:14
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsItemVO {

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
	 * 制品尺寸
	 */
	private String zpcc;
	/**
	 * 卷板No.
	 */
	private String jbno;

	/**
	 * 张数
	 */
	private Integer zshu;

	/**
	 * 订货No.
	 */
	private String dhno;

	/**
	 * 用户代码
	 */
	private String user;
	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 附着量-正面
	 */
	private String fuzm;

	/**
	 * 附着量-反面
	 */
	private String fufm;

	/**
	 * 规格
	 */
	private String ggno;

	/**
	 * 运用
	 */
	private String yuny;

	/**
	 * 要求等级
	 */
	private String deng;

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
	 * 附页NO集合
	 */
	private List<String> kpns;

	/**
	 * 业务联络集合
	 */
	private List<String> ylns;

	/**
	 * 实际重量
	 */
	private int sjzl;

	/**
	 * pile区分
	 */
	private String plqf;
	/**
	 * 主缺陷
	 */
	private String qqdm;
	/**
	 * 缺陷2
	 */
	private String qqd2;
	/**
	 * 缺陷3
	 */
	private String qqd3;

	public String getZpcc() {
		return zpcc;
	}

	public void setZpcc(String zpcc) {
		this.zpcc = zpcc;
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

	public String getQqdm() {
		return qqdm;
	}

	public void setQqdm(String qqdm) {
		this.qqdm = qqdm;
	}

	public String getQqd2() {
		return qqd2;
	}

	public void setQqd2(String qqd2) {
		this.qqd2 = qqd2;
	}

	public String getQqd3() {
		return qqd3;
	}

	public void setQqd3(String qqd3) {
		this.qqd3 = qqd3;
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
	 * @return the zshu
	 */
	public Integer getZshu() {
		return zshu;
	}

	/**
	 * @param zshu
	 *            the zshu to set
	 */
	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
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
	 * @return the sjzl
	 */
	public int getSjzl() {
		return sjzl;
	}

	/**
	 * @param sjzl
	 *            the sjzl to set
	 */
	public void setSjzl(int sjzl) {
		this.sjzl = sjzl;
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
	 * @return the fuzm
	 */
	public String getFuzm() {
		return fuzm;
	}

	/**
	 * @param fuzm
	 *            the fuzm to set
	 */
	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	/**
	 * @return the fufm
	 */
	public String getFufm() {
		return fufm;
	}

	/**
	 * @param fufm
	 *            the fufm to set
	 */
	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	/**
	 * @return the ggno
	 */
	public String getGgno() {
		return ggno;
	}

	/**
	 * @param ggno
	 *            the ggno to set
	 */
	public void setGgno(String ggno) {
		this.ggno = ggno;
	}

	/**
	 * @return the yuny
	 */
	public String getYuny() {
		return yuny;
	}

	/**
	 * @param yuny
	 *            the yuny to set
	 */
	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	/**
	 * @return the plqf
	 */
	public String getPlqf() {
		return plqf;
	}

	/**
	 * @param plqf
	 *            the plqf to set
	 */
	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

}
