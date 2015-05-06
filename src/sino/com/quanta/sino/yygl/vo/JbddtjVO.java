package com.quanta.sino.yygl.vo;

/**
 * <p>
 * 基板订单统计VO
 * </p>
 * <p>
 * create: 2011-7-7 上午10:54:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class JbddtjVO {

	/**
	 * 订货数量
	 */
	private Long dhsl;

	/**
	 * 确认重量
	 */
	private Long conf;

	public Long getDhsl() {
		return dhsl;
	}

	public void setDhsl(Long dhsl) {
		this.dhsl = dhsl;
	}

	public Long getConf() {
		return conf;
	}

	public void setConf(Long conf) {
		this.conf = conf;
	}

}
