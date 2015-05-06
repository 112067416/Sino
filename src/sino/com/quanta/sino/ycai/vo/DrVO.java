/**
 * 
 */
package com.quanta.sino.ycai.vo;

import java.util.Date;

/**
 * <p>
 * 文件导入对象
 * </p>
 * <p>
 * create: 2011-1-6 上午09:29:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DrVO {

	/**
	 * 船名
	 */
	private String ship;

	/**
	 * 装船时间
	 */
	private Date blny;

	/**
	 * 上传附件ID
	 */
	private String attachId;

	/**
	 * 钢卷号.前缀
	 */
	private String prefix;

	/**
	 * @return the ship
	 */
	public String getShip() {
		return ship;
	}

	/**
	 * @param ship
	 *            the ship to set
	 */
	public void setShip(String ship) {
		this.ship = ship;
	}

	/**
	 * @return the blny
	 */
	public Date getBlny() {
		return blny;
	}

	/**
	 * @param blny
	 *            the blny to set
	 */
	public void setBlny(Date blny) {
		this.blny = blny;
	}

	/**
	 * @return the attachId
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachId
	 *            the attachId to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getPrefix() {
		if (prefix == null || prefix.isEmpty()) {
			return "";
		}
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
