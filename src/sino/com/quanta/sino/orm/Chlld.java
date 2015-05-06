package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 次日出货联络单
 * </p>
 * <p>
 * create: 2010-12-23 上午10:28:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_CHLLD")
public class Chlld implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 订合no.
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	/**
	 * 运输行代码
	 */
	@Column(name = "YSGS_", length = 3)
	private String ysgs;

	/**
	 * 运输行名称
	 */
	@Column(name = "YSNM_", length = 64)
	private String ysnm;

	/**
	 * 出货重量
	 */
	@Column(name = "CHZL_", columnDefinition = "numeric(7,3)", scale = 7, precision = 3)
	private Double chzl;

	/**
	 * 尺寸.厚
	 */
	@Column(name = "HOUU_", columnDefinition = "numeric(4,3)", scale = 4, precision = 3)
	private Double houu;

	/**
	 * 尺寸.宽
	 */
	@Column(name = "KUAN_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double kuan;

	/**
	 * 尺寸.长
	 */
	@Column(name = "CANG_", columnDefinition = "numeric(6,2)", scale = 6, precision = 2)
	private Double cang;

	/**
	 * 用户代码
	 */
	@Column(name = "USER_", length = 4)
	private String user;

	/**
	 * 用户略称
	 */
	@Column(name = "ABBR_", length = 16)
	private String abbr;

	/**
	 * 用户名称
	 */
	@Column(name = "NAME_", length = 60)
	private String name;

	/**
	 * 送货点代码
	 */
	@Column(name = "SHNO_", length = 3)
	private String shno;

	/**
	 * 送货点名称
	 */
	@Column(name = "SHNM_", length = 16)
	private String shnm;

	/**
	 * 到达地点
	 */
	@Column(name = "ADDR_", length = 512)
	private String addr;

	/**
	 * 出货日期（年月日）
	 */
	@Column(name = "CHQI_")
	private Date chqi;

	/**
	 * 备货状况
	 */
	@Column(name = "BHZK_", length = 512)
	private String bhzk;

	/**
	 * 打单情况
	 */
	@Column(name = "DDQK_", length = 512)
	private String ddqk;

	/**
	 * 营业员代码
	 */
	@Column(name = "YWY_", length = 3)
	private String ywy;

	/**
	 * 营业员名称
	 */
	@Column(name = "YWNM_", length = 16)
	private String ywnm;

	/**
	 * 担当者代码
	 */
	@Column(name = "DDNO_", length = 3)
	private String ddno;

	/**
	 * 担当者名称
	 */
	@Column(name = "DDNM_", length = 16)
	private String ddnm;

	/**
	 * 联络单状态
	 */
	@Column(name = "STAT_", length = 1)
	private String stat;

	/**
	 * 作成时间（年月日）
	 */
	@Column(name = "CREA_")
	private Date crea;

	/**
	 * 更新时间（年月日时分秒）
	 */
	@Column(name = "UPDA_")
	private Date upda;

	/**
	 * 运输方式代码
	 */
	@Column(name = "YSFS_", length = 4)
	private String ysfs;

	/**
	 * 运输方式名称
	 */
	@Column(name = "YSFM_", length = 32)
	private String ysfm;

	/**
	 * 天气
	 */
	@Column(name = "WEATHER_", length = 32)
	private String weather;

	/**
	 * 附着量.单位
	 */
	@Column(name = "FUDW_", length = 2)
	private String fudw;

	/**
	 * 附着量.正面
	 */
	@Column(name = "FUZM_", length = 3)
	private String fuzm;

	/**
	 * 附着量.反面
	 */
	@Column(name = "FUFM_", length = 3)
	private String fufm;

	/**
	 * 表面加工精度
	 */
	@Column(name = "FACE_", length = 2)
	private String face;

	/**
	 * 运用规格
	 */
	@Column(name = "YUNY_", length = 6)
	private String yuny;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the ysnm
	 */
	public String getYsnm() {
		return ysnm;
	}

	/**
	 * @param ysnm
	 *            the ysnm to set
	 */
	public void setYsnm(String ysnm) {
		this.ysnm = ysnm;
	}

	/**
	 * @return the chzl
	 */
	public Double getChzl() {
		return chzl;
	}

	/**
	 * @param chzl
	 *            the chzl to set
	 */
	public void setChzl(Double chzl) {
		this.chzl = chzl;
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
	 * @return the shno
	 */
	public String getShno() {
		return shno;
	}

	/**
	 * @param shno
	 *            the shno to set
	 */
	public void setShno(String shno) {
		this.shno = shno;
	}

	/**
	 * @return the shnm
	 */
	public String getShnm() {
		return shnm;
	}

	/**
	 * @param shnm
	 *            the shnm to set
	 */
	public void setShnm(String shnm) {
		this.shnm = shnm;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the chqi
	 */
	public Date getChqi() {
		return chqi;
	}

	/**
	 * @param chqi
	 *            the chqi to set
	 */
	public void setChqi(Date chqi) {
		this.chqi = chqi;
	}

	/**
	 * @return the bhzk
	 */
	public String getBhzk() {
		return bhzk;
	}

	/**
	 * @param bhzk
	 *            the bhzk to set
	 */
	public void setBhzk(String bhzk) {
		this.bhzk = bhzk;
	}

	/**
	 * @return the ddqk
	 */
	public String getDdqk() {
		return ddqk;
	}

	/**
	 * @param ddqk
	 *            the ddqk to set
	 */
	public void setDdqk(String ddqk) {
		this.ddqk = ddqk;
	}

	/**
	 * @return the ywy
	 */
	public String getYwy() {
		return ywy;
	}

	/**
	 * @param ywy
	 *            the ywy to set
	 */
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}

	/**
	 * @return the ywnm
	 */
	public String getYwnm() {
		return ywnm;
	}

	/**
	 * @param ywnm
	 *            the ywnm to set
	 */
	public void setYwnm(String ywnm) {
		this.ywnm = ywnm;
	}

	/**
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the ddnm
	 */
	public String getDdnm() {
		return ddnm;
	}

	/**
	 * @param ddnm
	 *            the ddnm to set
	 */
	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the crea
	 */
	public Date getCrea() {
		return crea;
	}

	/**
	 * @param crea
	 *            the crea to set
	 */
	public void setCrea(Date crea) {
		this.crea = crea;
	}

	/**
	 * @return the upda
	 */
	public Date getUpda() {
		return upda;
	}

	/**
	 * @param upda
	 *            the upda to set
	 */
	public void setUpda(Date upda) {
		this.upda = upda;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYsfs() {
		return ysfs;
	}

	public void setYsfs(String ysfs) {
		this.ysfs = ysfs;
	}

	public String getYsfm() {
		return ysfm;
	}

	public void setYsfm(String ysfm) {
		this.ysfm = ysfm;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getFudw() {
		return fudw;
	}

	public void setFudw(String fudw) {
		this.fudw = fudw;
	}

	public String getFuzm() {
		return fuzm;
	}

	public void setFuzm(String fuzm) {
		this.fuzm = fuzm;
	}

	public String getFufm() {
		return fufm;
	}

	public void setFufm(String fufm) {
		this.fufm = fufm;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

}
