/**
 * 
 */
package com.quanta.sino.ycai.vo;

/**
 * <p>
 * 原板成本对象
 * </p>
 * <p>
 * create: 2011-1-6 上午09:29:40
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YbcbVO {

	/**
	 * 船名
	 */
	private String ship;

	/**
	 * 运输公司
	 */
	private String ysgs;

	/**
	 * 运输税率（每吨的价格）
	 */
	private Double yssl;

	/**
	 * 卸船码头
	 */
	private String xcmt;

	/**
	 * 港杂税率（每吨的价格）
	 */
	private Double gzsl;

	/**
	 * 保费汇率
	 */
	private Double bfhl;

	/**
	 * 美元汇率
	 */
	private Double myhl;

	/**
	 * 进口报关报检费
	 */
	private Double bgbj;

	/**
	 * 进口换小提单费
	 */
	private Double hxtd;

	/**
	 * 进口检验检疫费
	 */
	private Double jyjy;

	/**
	 * 重量鉴定费
	 */
	private Double zljd;

	/**
	 * 过磅费
	 */
	private Double gdf;

	/**
	 * 海关加价
	 */
	private Double hgjj;

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
	 * @return the ysgs
	 */
	public String getYsgs() {
		return ysgs;
	}

	/**
	 * @param ysgs
	 *            the ysgs to set
	 */
	public void setYsgs(String ysgs) {
		this.ysgs = ysgs;
	}

	/**
	 * @return the xcmt
	 */
	public String getXcmt() {
		return xcmt;
	}

	/**
	 * @param xcmt
	 *            the xcmt to set
	 */
	public void setXcmt(String xcmt) {
		this.xcmt = xcmt;
	}

	/**
	 * @return the bfhl
	 */
	public Double getBfhl() {
		return bfhl;
	}

	/**
	 * @param bfhl
	 *            the bfhl to set
	 */
	public void setBfhl(Double bfhl) {
		this.bfhl = bfhl;
	}

	/**
	 * @return the myhl
	 */
	public Double getMyhl() {
		return myhl;
	}

	/**
	 * @param myhl
	 *            the myhl to set
	 */
	public void setMyhl(Double myhl) {
		this.myhl = myhl;
	}

	/**
	 * @return the bgbj
	 */
	public Double getBgbj() {
		return bgbj;
	}

	/**
	 * @param bgbj
	 *            the bgbj to set
	 */
	public void setBgbj(Double bgbj) {
		this.bgbj = bgbj;
	}

	/**
	 * @return the hxtd
	 */
	public Double getHxtd() {
		return hxtd;
	}

	/**
	 * @param hxtd
	 *            the hxtd to set
	 */
	public void setHxtd(Double hxtd) {
		this.hxtd = hxtd;
	}

	/**
	 * @return the jyjy
	 */
	public Double getJyjy() {
		return jyjy;
	}

	/**
	 * @param jyjy
	 *            the jyjy to set
	 */
	public void setJyjy(Double jyjy) {
		this.jyjy = jyjy;
	}

	/**
	 * @return the zljd
	 */
	public Double getZljd() {
		return zljd;
	}

	/**
	 * @param zljd
	 *            the zljd to set
	 */
	public void setZljd(Double zljd) {
		this.zljd = zljd;
	}

	/**
	 * @return the gdf
	 */
	public Double getGdf() {
		return gdf;
	}

	/**
	 * @param gdf
	 *            the gdf to set
	 */
	public void setGdf(Double gdf) {
		this.gdf = gdf;
	}

	/**
	 * @return the yssl
	 */
	public Double getYssl() {
		return yssl;
	}

	/**
	 * @param yssl
	 *            the yssl to set
	 */
	public void setYssl(Double yssl) {
		this.yssl = yssl;
	}

	/**
	 * @return the gzsl
	 */
	public Double getGzsl() {
		return gzsl;
	}

	/**
	 * @param gzsl
	 *            the gzsl to set
	 */
	public void setGzsl(Double gzsl) {
		this.gzsl = gzsl;
	}

	public Double getHgjj() {
		return hgjj;
	}

	public void setHgjj(Double hgjj) {
		this.hgjj = hgjj;
	}

}
