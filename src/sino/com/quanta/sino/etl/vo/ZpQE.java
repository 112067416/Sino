/**
 * 
 */
package com.quanta.sino.etl.vo;

import java.util.Date;
import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品在库表查询条件
 * </p>
 * <p>
 * create: 2011-1-13 下午02:35:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class ZpQE extends QEntity<ZpngTp> {

	/**
	 * 订货号
	 */
	@QF(alias = "dhno", operator = EO.LIKE)
	private String dhnoLine;

	/**
	 * 订货号
	 */
	@QF(alias = "dhno", operator = EO.EQ)
	private String dhnoAndLine;
	/**
	 * 删除标记
	 */
	@QF
	private String scbj;

	/**
	 * 删除标记
	 */
	@QF(alias = "scbj", operator = EO.NE)
	private String nscbj;

	/**
	 * 张数
	 */
	@QF(alias = "zshu", operator = EO.GT)
	private Integer zshu;

	/**
	 * 删除标记
	 */
	@QF(alias = "scbj", operator = EO.IN)
	private String[] scbjs;

	/**
	 * 分配余材
	 */
	@QF(alias = "fpyc", operator = EO.NE)
	private String neFpyc;

	/**
	 * 分配余材
	 */
	@QF
	private String fpyc;

	/**
	 * 分配余材
	 */
	@QF(alias = "fpyc", operator = EO.IN)
	private String[] fpycs;

	/**
	 * 状态
	 */
	@QF
	private String stat;

	/**
	 * 状态
	 */
	@QF(alias = "stat", operator = EO.NE)
	private String neStat;

	/**
	 * 现品状况
	 */
	@QF
	private String xpzk;

	/**
	 * 现品状况
	 */
	@QF(alias = "xpzk", operator = EO.IN)
	private String[] xpzks;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.IN)
	private String[] duics;

	/**
	 * 堆场
	 */
	@QF(alias = "duic", operator = EO.EQ)
	private String duic;

	/**
	 * 装箱指示书号
	 */
	@QF
	private String chno;

	/**
	 * 指示No
	 */
	@QF
	private String zsno;
	/**
	 * 内外销
	 */
	@QF
	private String nwai;

	/**
	 * 卷板No/PlieNo
	 */
	@QF
	private String jbno;

	/**
	 * 卷板No/PlieNo
	 */
	@QF(alias = "jbno", operator = EO.IN)
	private String[] jbnos;

	/**
	 * 开始卷板No/PlieNo
	 */
	@QF(alias = "jbno", operator = EO.GE)
	private String jbnoStart;

	/**
	 * 结束卷板No/PlieNo
	 */
	@QF(alias = "jbno", operator = EO.LE)
	private String jbnoEnd;
	/**
	 * 出货指示No是否为空
	 */
	@QF(alias = "chno", operator = EO.NULL)
	private boolean chnoIsNull;

	/**
	 * 运用规格
	 */
	@QF
	private String yuny;

	/**
	 * 产量代码
	 */
	@QF
	private String chan;

	/**
	 * 产量代码
	 */
	@QF(alias = "chan", operator = EO.IN)
	private String[] chans;

	/**
	 * 表面
	 */
	@QF
	private String face;

	/**
	 * 起始现品厚
	 */
	@QF(alias = "xpho", operator = EO.GE)
	private Double xphoS;

	/**
	 * 结束现品厚
	 */
	@QF(alias = "xpho", operator = EO.LE)
	private Double xphoE;

	/**
	 * 现品厚
	 */
	@QF(alias = "xpho", operator = EO.EQ)
	private Double xpho;

	/**
	 * 起始现品宽
	 */
	@QF(alias = "xpkn", operator = EO.GE)
	private Double xpknS;

	/**
	 * 结束现品宽
	 */
	@QF(alias = "xpkn", operator = EO.LE)
	private Double xpknE;

	/**
	 * 现品宽
	 */
	@QF(alias = "xpkn", operator = EO.EQ)
	private Double xpkn;
	/**
	 * 起始现品长
	 */
	@QF(alias = "xpcn", operator = EO.GE)
	private Double xpcnS;

	/**
	 * 结束现品长
	 */
	@QF(alias = "xpcn", operator = EO.LE)
	private Double xpcnE;

	/**
	 * 现品长
	 */
	@QF(alias = "xpcn", operator = EO.EQ)
	private Double xpcn;

	/**
	 * 入侧卷号
	 */
	@QF
	private String rczpno;
	/**
	 * 不等于删除标记
	 */
	@QF(alias = "scbj", operator = EO.NOT_IN)
	private String[] neScbjs;

	/**
	 * 生成时间起始
	 */
	@QF(alias = "crea", operator = EO.GE)
	private Date creaBegin;

	/**
	 * 生成时间终止
	 */
	@QF(alias = "crea", operator = EO.LT)
	private Date creaEnd;

	/**
	 * 选别时间起始
	 */
	@QF(alias = "pssd", operator = EO.GE)
	private Date pssdBegin;

	/**
	 * 选别时间终止
	 */
	@QF(alias = "pssd", operator = EO.LT)
	private Date pssdEnd;

	/**
	 * 消灭时间起始
	 */
	@QF(alias = "pxsd", operator = EO.GE)
	private Date pxsdBegin;

	/**
	 * 消灭时间终止
	 */
	@QF(alias = "pxsd", operator = EO.LT)
	private Date pxsdEnd;

	/**
	 * 进度标记.精整实绩
	 */
	@QF
	private String jdjs;

	/**
	 * 班
	 */
	@QF
	private String ban;

	/**
	 * 组
	 */
	@QF
	private String zu;

	/**
	 * 开始用户代码
	 */
	@QF(alias = "user", operator = EO.LE)
	private String userBegin;

	/**
	 * 结束用户代码
	 */
	@QF(alias = "user", operator = EO.GE)
	private String userEnd;
	/**
	 * 查询捆包日期开始时间段
	 */
	@QF(alias = "kbsd", operator = EO.GE)
	private Date kbsdBegin;

	/**
	 * 查询捆包日期结束时间段
	 */
	@QF(alias = "kbsd", operator = EO.LT, addDays = 1)
	private Date kbsdEnd;

	/**
	 * 库位
	 */
	@QF
	private String kw;

	/**
	 * 对卷板NO进行模糊查询
	 */
	@QF(alias = "jbno", operator = EO.LIKE)
	private String likeJbno;

	/**
	 * 是否紧急
	 */
	@QF
	private String sfjj;

	/**
	 * 捆包时间
	 */
	@QF(alias = "kbsd", operator = EO.IS_NULL)
	private Boolean kbsded;

	/**
	 * 不等于卷板No
	 */
	@QF(alias = "jbno", operator = EO.NOT_IN)
	private List<String> neJbnos;

	/**
	 * 捆包形式
	 */
	@QF
	private String kbao;

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public String getDhnoLine() {
		return dhnoLine;
	}

	public void setDhnoLine(String dhnoLine) {
		this.dhnoLine = dhnoLine;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String getFpyc() {
		return fpyc;
	}

	public void setFpyc(String fpyc) {
		this.fpyc = fpyc;
	}

	public String[] getFpycs() {
		return fpycs;
	}

	public void setFpycs(String[] fpycs) {
		this.fpycs = fpycs;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public String[] getDuics() {
		return duics;
	}

	public void setDuics(String[] duics) {
		this.duics = duics;
	}

	public String getChno() {
		return chno;
	}

	public void setChno(String chno) {
		this.chno = chno;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String[] getXpzks() {
		return xpzks;
	}

	public void setXpzks(String[] xpzks) {
		this.xpzks = xpzks;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String[] getJbnos() {
		return jbnos;
	}

	public void setJbnoStr(String jbnos) {
		if (jbnos == null || jbnos.isEmpty()) {
			this.jbnos = null;
			return;
		}
		this.jbnos = jbnos.replaceAll("，", ",").split(",");
	}

	public void setJbnos(String[] jbnos) {
		this.jbnos = jbnos;
	}

	public String getNwai() {
		return nwai;
	}

	public void setNwai(String nwai) {
		this.nwai = nwai;
	}

	public boolean isChnoIsNull() {
		return chnoIsNull;
	}

	public void setChnoIsNull(boolean chnoIsNull) {
		this.chnoIsNull = chnoIsNull;
	}

	public String[] getScbjs() {
		return scbjs;
	}

	public void setScbjs(String[] scbjs) {
		this.scbjs = scbjs;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Double getXphoS() {
		return xphoS;
	}

	public void setXphoS(Double xphoS) {
		this.xphoS = xphoS;
	}

	public Double getXphoE() {
		return xphoE;
	}

	public void setXphoE(Double xphoE) {
		this.xphoE = xphoE;
	}

	public Double getXpknS() {
		return xpknS;
	}

	public void setXpknS(Double xpknS) {
		this.xpknS = xpknS;
	}

	public Double getXpknE() {
		return xpknE;
	}

	public void setXpknE(Double xpknE) {
		this.xpknE = xpknE;
	}

	public Double getXpcnS() {
		return xpcnS;
	}

	public void setXpcnS(Double xpcnS) {
		this.xpcnS = xpcnS;
	}

	public Double getXpcnE() {
		return xpcnE;
	}

	public void setXpcnE(Double xpcnE) {
		this.xpcnE = xpcnE;
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

	public String getJbnoStart() {
		return jbnoStart;
	}

	public void setJbnoStart(String jbnoStart) {
		this.jbnoStart = jbnoStart;
	}

	public String getJbnoEnd() {
		return jbnoEnd;
	}

	public void setJbnoEnd(String jbnoEnd) {
		this.jbnoEnd = jbnoEnd;
	}

	public String getRczpno() {
		return rczpno;
	}

	public void setRczpno(String rczpno) {
		this.rczpno = rczpno;
	}

	public String[] getNeScbjs() {
		return neScbjs;
	}

	public void setNeScbjs(String[] neScbjs) {
		this.neScbjs = neScbjs;
	}

	public Date getCreaBegin() {
		return creaBegin;
	}

	public void setCreaBegin(Date creaBegin) {
		this.creaBegin = creaBegin;
	}

	public Date getCreaEnd() {
		return creaEnd;
	}

	public void setCreaEnd(Date creaEnd) {
		this.creaEnd = creaEnd;
	}

	public Date getPssdBegin() {
		return pssdBegin;
	}

	public void setPssdBegin(Date pssdBegin) {
		this.pssdBegin = pssdBegin;
	}

	public Date getPssdEnd() {
		return pssdEnd;
	}

	public void setPssdEnd(Date pssdEnd) {
		this.pssdEnd = pssdEnd;
	}

	public Date getPxsdBegin() {
		return pxsdBegin;
	}

	public void setPxsdBegin(Date pxsdBegin) {
		this.pxsdBegin = pxsdBegin;
	}

	public Date getPxsdEnd() {
		return pxsdEnd;
	}

	public void setPxsdEnd(Date pxsdEnd) {
		this.pxsdEnd = pxsdEnd;
	}

	public String getJdjs() {
		return jdjs;
	}

	public void setJdjs(String jdjs) {
		this.jdjs = jdjs;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getNscbj() {
		return nscbj;
	}

	public void setNscbj(String nscbj) {
		this.nscbj = nscbj;
	}

	public String getUserBegin() {
		return userBegin;
	}

	public void setUserBegin(String userBegin) {
		this.userBegin = userBegin;
	}

	public String getUserEnd() {
		return userEnd;
	}

	public void setUserEnd(String userEnd) {
		this.userEnd = userEnd;
	}

	public Date getKbsdBegin() {
		return kbsdBegin;
	}

	public void setKbsdBegin(Date kbsdBegin) {
		this.kbsdBegin = kbsdBegin;
	}

	public Date getKbsdEnd() {
		return kbsdEnd;
	}

	public void setKbsdEnd(Date kbsdEnd) {
		this.kbsdEnd = kbsdEnd;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getLikeJbno() {
		return likeJbno;
	}

	public void setLikeJbno(String likeJbno) {
		this.likeJbno = likeJbno;
	}

	public String getNeFpyc() {
		return neFpyc;
	}

	public void setNeFpyc(String neFpyc) {
		this.neFpyc = neFpyc;
	}

	public String getDhnoAndLine() {
		return dhnoAndLine;
	}

	public void setDhnoAndLine(String dhnoAndLine) {
		this.dhnoAndLine = dhnoAndLine;
	}

	public String getSfjj() {
		return sfjj;
	}

	public void setSfjj(String sfjj) {
		this.sfjj = sfjj;
	}

	public Boolean getKbsded() {
		return kbsded;
	}

	public void setKbsded(Boolean kbsded) {
		this.kbsded = kbsded;
	}

	public List<String> getNeJbnos() {
		return neJbnos;
	}

	public void setNeJbnos(List<String> neJbnos) {
		this.neJbnos = neJbnos;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public String[] getChans() {
		return chans;
	}

	public void setChans(String[] chans) {
		this.chans = chans;
	}

	public String getNeStat() {
		return neStat;
	}

	public void setNeStat(String neStat) {
		this.neStat = neStat;
	}

}
