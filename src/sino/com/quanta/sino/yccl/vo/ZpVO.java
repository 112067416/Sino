package com.quanta.sino.yccl.vo;

/**
 * <p>
 * 现品信息
 * </p>
 * <p>
 * create: 2011-4-11 下午10:59:51
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZpVO {

	/**
	 * 订货No
	 */
	private String dhno;

	/**
	 * 现品No.
	 */
	private String jbno;

	/**
	 * 现品状况
	 */
	private String xpzk;

	/**
	 * 是否已出货
	 */
	private String qzch;

	/**
	 * 装箱指示书
	 */
	private String chno;

	/**
	 * 是否出货
	 */
	private String ched;

	/**
	 * 删除标识
	 */
	private String scbj;

	/**
	 * 厚
	 */
	private Double xpho;

	/**
	 * 宽
	 */
	private Double xpkn;

	/**
	 * 长(Sheet)
	 */
	private Double xpcn;

	/**
	 * 长(Coil)
	 */
	private Integer jbcn;

	/**
	 * 计算重量
	 */
	private Integer jszl;

	/**
	 * 实际重量
	 */
	private Integer sjzl;

	/**
	 * 净重量
	 */
	private Integer jnzl;

	/**
	 * 毛重量
	 */
	private Integer mazl;

	/**
	 * 制品重量
	 */
	private Integer zpzl;

	/**
	 * 装入重量
	 */
	private Integer zrzl;

	/**
	 * 等级
	 */
	private String deng;

	/**
	 * 产量
	 */
	private String chan;

	/**
	 * 垫木重量
	 */
	private Integer dmzl;

	/**
	 * 制造商代码
	 */
	private String zzsd;

	/**
	 * 包装材料重量
	 */
	private Integer bzcl;

	/**
	 * PILE区分
	 */
	private String plqf;

	/**
	 * 运用规格
	 */
	private String yuny;

	/**
	 * 出口包装NO
	 */
	private Integer ckno;

	/**
	 * 钢种类型
	 */
	private String gzlx;

	/**
	 * 包装张数
	 */
	private Integer zshu;

	/**
	 * 再剪边标记
	 */
	private String zjbb;

	/**
	 * 内径代码
	 */
	private String njno;

	/**
	 * 表面
	 */
	private String face;

	/**
	 * 品种
	 */
	private String pzno;

	/**
	 * 涂油种类
	 */
	private String ytyp;

	/**
	 * 规格代码
	 */
	private String ggno;

	/**
	 * 附着量单位
	 */
	private String fudw;

	/**
	 * 附着量正面
	 */
	private String fuzm;

	/**
	 * 附着量反面
	 */
	private String fufm;

	/**
	 * 实绩品种等级
	 */
	private String spdj;

	/**
	 * 作业停止标记
	 */
	private String ztbj;

	/**
	 * 实绩附着量正面
	 */
	private Double sczm;

	/**
	 * 实绩附着量反面
	 */
	private Double scfm;

	/**
	 * 差厚镀锡标记
	 */
	private String chdx;

	/**
	 * 捆包形式
	 */
	private String kbao;

	/**
	 * 硬度
	 */
	private Integer ying;

	/**
	 * 锯齿形式
	 */
	private String jcxs;

	/**
	 * 标签换贴标记
	 */
	private String bqht;

	/**
	 * 业连NO
	 */
	private String ylno;

	/**
	 * 状态名称
	 */
	private String statName;

	/**
	 * 现品状况名称
	 */
	private String xpzkName;

	/**
	 * 新生成制品的No.
	 */
	private String newJbno;

	/**
	 * 订货尺寸
	 */
	private String dhcc;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getXpzk() {
		return xpzk;
	}

	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

	public String getQzch() {
		return qzch;
	}

	public void setQzch(String qzch) {
		this.qzch = qzch;
	}

	public String getChno() {
		return chno;
	}

	public void setChno(String chno) {
		this.chno = chno;
	}

	public String getChed() {
		return ched;
	}

	public void setChed(String ched) {
		this.ched = ched;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
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

	public Integer getJbcn() {
		return jbcn;
	}

	public void setJbcn(Integer jbcn) {
		this.jbcn = jbcn;
	}

	public Integer getJszl() {
		return jszl;
	}

	public void setJszl(Integer jszl) {
		this.jszl = jszl;
	}

	public Integer getSjzl() {
		return sjzl;
	}

	public void setSjzl(Integer sjzl) {
		this.sjzl = sjzl;
	}

	public Integer getJnzl() {
		return jnzl;
	}

	public void setJnzl(Integer jnzl) {
		this.jnzl = jnzl;
	}

	public Integer getMazl() {
		return mazl;
	}

	public void setMazl(Integer mazl) {
		this.mazl = mazl;
	}

	public Integer getZpzl() {
		return zpzl;
	}

	public void setZpzl(Integer zpzl) {
		this.zpzl = zpzl;
	}

	public String getDeng() {
		return deng;
	}

	public void setDeng(String deng) {
		this.deng = deng;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public Integer getDmzl() {
		return dmzl;
	}

	public void setDmzl(Integer dmzl) {
		this.dmzl = dmzl;
	}

	public String getZzsd() {
		return zzsd;
	}

	public void setZzsd(String zzsd) {
		this.zzsd = zzsd;
	}

	public Integer getBzcl() {
		return bzcl;
	}

	public void setBzcl(Integer bzcl) {
		this.bzcl = bzcl;
	}

	public String getPlqf() {
		return plqf;
	}

	public void setPlqf(String plqf) {
		this.plqf = plqf;
	}

	public String getYuny() {
		return yuny;
	}

	public void setYuny(String yuny) {
		this.yuny = yuny;
	}

	public Integer getCkno() {
		return ckno;
	}

	public void setCkno(Integer ckno) {
		this.ckno = ckno;
	}

	public String getGzlx() {
		return gzlx;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}

	public String getZjbb() {
		return zjbb;
	}

	public void setZjbb(String zjbb) {
		this.zjbb = zjbb;
	}

	public String getNjno() {
		return njno;
	}

	public void setNjno(String njno) {
		this.njno = njno;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getPzno() {
		return pzno;
	}

	public void setPzno(String pzno) {
		this.pzno = pzno;
	}

	public String getYtyp() {
		return ytyp;
	}

	public void setYtyp(String ytyp) {
		this.ytyp = ytyp;
	}

	public String getGgno() {
		return ggno;
	}

	public void setGgno(String ggno) {
		this.ggno = ggno;
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

	public String getSpdj() {
		return spdj;
	}

	public void setSpdj(String spdj) {
		this.spdj = spdj;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public Double getSczm() {
		return sczm;
	}

	public void setSczm(Double sczm) {
		this.sczm = sczm;
	}

	public Double getScfm() {
		return scfm;
	}

	public void setScfm(Double scfm) {
		this.scfm = scfm;
	}

	public String getChdx() {
		return chdx;
	}

	public void setChdx(String chdx) {
		this.chdx = chdx;
	}

	public String getKbao() {
		return kbao;
	}

	public void setKbao(String kbao) {
		this.kbao = kbao;
	}

	public Integer getYing() {
		return ying;
	}

	public void setYing(Integer ying) {
		this.ying = ying;
	}

	public String getJcxs() {
		return jcxs;
	}

	public void setJcxs(String jcxs) {
		this.jcxs = jcxs;
	}

	public String getBqht() {
		return bqht;
	}

	public void setBqht(String bqht) {
		this.bqht = bqht;
	}

	public String getYlno() {
		return ylno;
	}

	public void setYlno(String ylno) {
		this.ylno = ylno;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public String getXpzkName() {
		return xpzkName;
	}

	public void setXpzkName(String xpzkName) {
		this.xpzkName = xpzkName;
	}

	public String getNewJbno() {
		return newJbno;
	}

	public void setNewJbno(String newJbno) {
		this.newJbno = newJbno;
	}

	public String getDhcc() {
		return dhcc;
	}

	public void setDhcc(String dhcc) {
		this.dhcc = dhcc;
	}

	public Integer getZshu() {
		return zshu;
	}

	public void setZshu(Integer zshu) {
		this.zshu = zshu;
	}

	public Integer getZrzl() {
		return zrzl;
	}

	public void setZrzl(Integer zrzl) {
		this.zrzl = zrzl;
	}

}
