/**
 * 
 */
package com.quanta.sino.cg.vo;

import java.util.Date;

/**
 * <p>
 * 供应商合同导入对象
 * </p>
 * <p>
 * create: 2011-1-4 上午10:57:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DrVO {

	/**
	 * 签约日期
	 */
	private Date qyri;

	/**
	 * 合同日
	 */
	private Date htdt;

	/**
	 * 商社代码
	 */
	private String ssno;

	/**
	 * 商社名称
	 */
	private String ssnm;

	/**
	 * 币种
	 */
	private String thqf;

	/**
	 * 上传文件ID
	 */
	private String attachId;

	/**
	 * @return the qyri
	 */
	public Date getQyri() {
		return qyri;
	}

	/**
	 * @param qyri
	 *            the qyri to set
	 */
	public void setQyri(Date qyri) {
		this.qyri = qyri;
	}

	/**
	 * @return the htdt
	 */
	public Date getHtdt() {
		return htdt;
	}

	/**
	 * @param htdt
	 *            the htdt to set
	 */
	public void setHtdt(Date htdt) {
		this.htdt = htdt;
	}

	/**
	 * @return the ssno
	 */
	public String getSsno() {
		return ssno;
	}

	/**
	 * @param ssno
	 *            the ssno to set
	 */
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	/**
	 * @return the ssnm
	 */
	public String getSsnm() {
		return ssnm;
	}

	/**
	 * @param ssnm
	 *            the ssnm to set
	 */
	public void setSsnm(String ssnm) {
		this.ssnm = ssnm;
	}

	/**
	 * @return the thqf
	 */
	public String getThqf() {
		return thqf;
	}

	/**
	 * @param thqf
	 *            the thqf to set
	 */
	public void setThqf(String thqf) {
		this.thqf = thqf;
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

}
