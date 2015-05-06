package com.quanta.sino.dy.vo;

/**
 * <p>
 * 制品标签打印数据
 * </p>
 * <p>
 * create: 2011-1-13 下午02:58:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZpbqVO {

	/**
	 * 用户(全称)
	 */
	private String user;
	/**
	 * 用户略称
	 */
	private String abbr;
	/**
	 * 商品名
	 */
	private String spm;

	/**
	 * 等级（制品等级）
	 */
	private String pzdj;

	/**
	 * Package No.
	 */
	private String packageNo;

	/**
	 * 规格名称
	 */
	private String ggnm;

	/**
	 * 镀锡量
	 */
	private String dxl;

	/**
	 * 尺寸
	 */
	private String size;

	/**
	 * 表面精加工
	 */
	private String face;

	/**
	 * 表面精加工键值
	 */
	private String faceK;

	/**
	 * 质量
	 */
	private String zl;

	/**
	 * G/W
	 */
	private String gw;

	/**
	 * 长（批量）
	 */
	private String cang;

	/**
	 * 制造年月日
	 */
	private String date;

	/**
	 * 制品号
	 */
	private String zpno;

	/**
	 * 订货号
	 */
	private String dhno;

	/**
	 * 订货行号
	 */
	private String dhline;

	/**
	 * 捆包形式
	 */
	private String kbao;

	/**
	 * 现品厚
	 */
	private Double xpho;

	/**
	 * 现品长
	 */
	private Double xpkn;

	/**
	 * 现品宽
	 */
	private Double xpcn;

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
	 * @return the spm
	 */
	public String getSpm() {
		return spm;
	}

	/**
	 * @param spm
	 *            the spm to set
	 */
	public void setSpm(String spm) {
		this.spm = spm;
	}

	/**
	 * @return the packageNo
	 */
	public String getPackageNo() {
		return packageNo;
	}

	/**
	 * @param packageNo
	 *            the packageNo to set
	 */
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	/**
	 * @return the ggnm
	 */
	public String getGgnm() {
		return ggnm;
	}

	/**
	 * @param ggnm
	 *            the ggnm to set
	 */
	public void setGgnm(String ggnm) {
		this.ggnm = ggnm;
	}

	/**
	 * @return the dxl
	 */
	public String getDxl() {
		return dxl;
	}

	/**
	 * @param dxl
	 *            the dxl to set
	 */
	public void setDxl(String dxl) {
		this.dxl = dxl;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
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
	 * @return the zl
	 */
	public String getZl() {
		return zl;
	}

	/**
	 * @param zl
	 *            the zl to set
	 */
	public void setZl(String zl) {
		this.zl = zl;
	}

	/**
	 * @return the gw
	 */
	public String getGw() {
		return gw;
	}

	/**
	 * @param gw
	 *            the gw to set
	 */
	public void setGw(String gw) {
		this.gw = gw;
	}

	/**
	 * @return the cang
	 */
	public String getCang() {
		return cang;
	}

	/**
	 * @param cang
	 *            the cang to set
	 */
	public void setCang(String cang) {
		this.cang = cang;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the zpno
	 */
	public String getZpno() {
		return zpno;
	}

	/**
	 * @param zpno
	 *            the zpno to set
	 */
	public void setZpno(String zpno) {
		this.zpno = zpno;
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
	 * @return the kbao
	 */
	public String getKbao() {
		return kbao;
	}

	/**
	 * @param kbao
	 *            the kbao to set
	 */
	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	/**
	 * @return the pzdj
	 */
	public String getPzdj() {
		return pzdj;
	}

	/**
	 * @param pzdj
	 *            the pzdj to set
	 */
	public void setPzdj(String pzdj) {
		this.pzdj = pzdj;
	}

	/**
	 * @return the dhline
	 */
	public String getDhline() {
		return dhline;
	}

	/**
	 * @param dhline
	 *            the dhline to set
	 */
	public void setDhline(String dhline) {
		this.dhline = dhline;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getFaceK() {
		return faceK;
	}

	public void setFaceK(String faceK) {
		this.faceK = faceK;
	}

	public Double getXpho() {
		return xpho;
	}

	public void setXpho(Double xpho) {
		this.xpho = xpho;
	}

	public Double getXpkn() {
		return xpkn;
	}

	public void setXpkn(Double xpkn) {
		this.xpkn = xpkn;
	}

	public Double getXpcn() {
		return xpcn;
	}

	public void setXpcn(Double xpcn) {
		this.xpcn = xpcn;
	}

}
