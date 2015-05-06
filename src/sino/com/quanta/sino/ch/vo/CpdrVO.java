/**
 * 
 */
package com.quanta.sino.ch.vo;

/**
 * <p>
 * 文件导入对象
 * </p>
 * <p>
 * create: 2011-11-25 下午12:27:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CpdrVO {

	/**
	 * 车牌号
	 */
	private String cph;

	/**
	 * 上传附件ID
	 */
	private String attachId;

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

}
