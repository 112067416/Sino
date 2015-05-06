package com.quanta.sino.dh.vo;

/**
 * <p>
 * 结算条件实体类
 * </p>
 * <p>
 * create: 2011-5-13 上午11:26:16
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JstjVO {

	/**
	 * 订货合同
	 */
	private String dhno;

	/**
	 * 行号
	 */
	private String line;

	/**
	 * 结算条件.预付款比例
	 */
	private Integer yfkn;

	/**
	 * 结算条件.出货款比例
	 */
	private Integer chkn;

	/**
	 * 结算条件.后付款比例
	 */
	private Integer hfkn;

	/**
	 * 结算条件.期限
	 */
	private Integer qixn;

	/**
	 * 通货区分
	 */
	private String thqf;

	/**
	 * 合同单价
	 */
	private Double htdj;

	/**
	 * 运费单价
	 */
	private Double yfei;

	/**
	 * 合同金额
	 */
	private Double htje;

	/**
	 * 运输方式
	 */
	private String ysfs;

	/**
	 * 结算条件
	 */
	private String jstj;

	/**
	 * 
	 */
	private JstjVO[] items;

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

	public Integer getYfkn() {
		return yfkn;
	}

	public void setYfkn(Integer yfkn) {
		this.yfkn = yfkn;
	}

	public Integer getChkn() {
		return chkn;
	}

	public void setChkn(Integer chkn) {
		this.chkn = chkn;
	}

	public Integer getHfkn() {
		return hfkn;
	}

	public void setHfkn(Integer hfkn) {
		this.hfkn = hfkn;
	}

	public Integer getQixn() {
		return qixn;
	}

	public void setQixn(Integer qixn) {
		this.qixn = qixn;
	}

	public String getThqf() {
		return thqf;
	}

	public void setThqf(String thqf) {
		this.thqf = thqf;
	}

	public Double getHtdj() {
		return htdj;
	}

	public void setHtdj(Double htdj) {
		this.htdj = htdj;
	}

	public Double getYfei() {
		return yfei;
	}

	public void setYfei(Double yfei) {
		this.yfei = yfei;
	}

	public Double getHtje() {
		return htje;
	}

	public void setHtje(Double htje) {
		this.htje = htje;
	}

	public String getYsfs() {
		return ysfs;
	}

	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	public String getJstj() {
		return jstj;
	}

	public void setJstj(String jstj) {
		this.jstj = jstj;
	}

	public JstjVO[] getItems() {
		return items;
	}

	public void setItems(JstjVO[] items) {
		this.items = items;
	}

}
