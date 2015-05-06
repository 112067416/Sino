package com.quanta.sino.yszk.vo;

/**
 * <p>
 * 统计重量和总价
 * </p>
 * <p>
 * create: 2011-6-29 上午11:44:12
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class HjVO {

	/**
	 * 重量
	 */
	private Double chzl;

	/**
	 * 发票金额
	 */
	private Double fpje;

	/**
	 * 收款金额
	 */
	private Double fkje;

	/**
	 * 未收余款
	 */
	private Double wsyk;

	public Double getChzl() {
		return chzl;
	}

	public void setChzl(Double chzl) {
		this.chzl = chzl;
	}

	public Double getFpje() {
		return fpje;
	}

	public void setFpje(Double fpje) {
		this.fpje = fpje;
	}

	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	public Double getWsyk() {
		return wsyk;
	}

	public void setWsyk(Double wsyk) {
		this.wsyk = wsyk;
	}

}
