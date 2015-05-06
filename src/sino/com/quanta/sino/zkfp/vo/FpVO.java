/**
 * 
 */
package com.quanta.sino.zkfp.vo;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 分配指示VO
 * </p>
 * <p>
 * create: 2011-1-21 下午01:31:02
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpVO {

	/**
	 * 分配No.
	 */
	private String fpno;
	/**
	 * 订货合同号
	 */
	private String dhno;

	/**
	 * 订货合同行号
	 */
	private String line;

	/**
	 * 分配指示量
	 */
	private Double cqzs;

	/**
	 * 余材状况
	 */
	private String xpzk;

	/**
	 * 现品NO.
	 */
	private List<String> jbnos;

	/**
	 * 再选
	 */
	private String zxbb;

	/**
	 * 剪边
	 */
	private String jbkb;

	/**
	 * 允许超出
	 */
	private String clfp;

	/**
	 * 强制
	 */
	private String qzbj;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 用户略称
	 */
	private String abbr;

	/**
	 * 规格
	 */
	private String ggno;

	/**
	 * 镀锡量.单位
	 */
	private String fudw;

	/**
	 * 镀锡量.正面
	 */
	private String fuzm;
	/**
	 * 镀锡量.反面
	 */
	private String fufm;
	/**
	 * 尺寸.长
	 */
	private Double cang;

	/**
	 * 尺寸.厚
	 */
	private Double houu;

	/**
	 * 尺寸.宽
	 */
	private Double kuan;

	/**
	 * 未分配量
	 */
	private Double wfpl;

	/**
	 * 生产指示单ID
	 */
	private String sczsId;

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
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the cqzs
	 */
	public Double getCqzs() {
		return cqzs;
	}

	/**
	 * @param cqzs
	 *            the cqzs to set
	 */
	public void setCqzs(Double cqzs) {
		this.cqzs = cqzs;
	}

	/**
	 * @return the xpzk
	 */
	public String getXpzk() {
		return xpzk;
	}

	/**
	 * @param xpzk
	 *            the xpzk to set
	 */
	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	/**
	 * @return the jbnos
	 */
	public List<String> getJbnos() {
		return jbnos;
	}

	/**
	 * @param jbnos
	 *            the jbnos to set
	 */
	public void setXpnos(String xpnos) {
		if (xpnos == null || xpnos.isEmpty()) {
			this.jbnos = null;
			return;
		}
		xpnos = xpnos.replaceAll("，", ",");
		this.jbnos = Arrays.asList(xpnos.split(","));
	}

	/**
	 * @return the zxbb
	 */
	public String getZxbb() {
		return zxbb;
	}

	/**
	 * @param zxbb
	 *            the zxbb to set
	 */
	public void setZxbb(String zxbb) {
		this.zxbb = zxbb;
	}

	/**
	 * @return the jbkb
	 */
	public String getJbkb() {
		return jbkb;
	}

	/**
	 * @param jbkb
	 *            the jbkb to set
	 */
	public void setJbkb(String jbkb) {
		this.jbkb = jbkb;
	}

	/**
	 * @return the clfp
	 */
	public String getClfp() {
		return clfp;
	}

	/**
	 * @param clfp
	 *            the clfp to set
	 */
	public void setClfp(String clfp) {
		this.clfp = clfp;
	}

	/**
	 * @return the qzbj
	 */
	public String getQzbj() {
		return qzbj;
	}

	/**
	 * @param qzbj
	 *            the qzbj to set
	 */
	public void setQzbj(String qzbj) {
		this.qzbj = qzbj;
	}

	/**
	 * @return the face
	 */
	public String getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the face to set
	 */
	public void setFace(String face) {
		this.face = face;
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
	 * @return the fudw
	 */
	public String getFudw() {
		return fudw;
	}

	/**
	 * @param fudw
	 *            the fudw to set
	 */
	public void setFudw(String fudw) {
		this.fudw = fudw;
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
	 * @return the wfpl
	 */
	public Double getWfpl() {
		return wfpl;
	}

	/**
	 * @param wfpl
	 *            the wfpl to set
	 */
	public void setWfpl(Double wfpl) {
		this.wfpl = wfpl;
	}

	/**
	 * @return the fpno
	 */
	public String getFpno() {
		return fpno;
	}

	/**
	 * @param fpno
	 *            the fpno to set
	 */
	public void setFpno(String fpno) {
		this.fpno = fpno;
	}

	public String getSczsId() {
		return sczsId;
	}

	public void setSczsId(String sczsId) {
		this.sczsId = sczsId;
	}

}
