package com.quanta.sino.dy.vo;

import java.util.List;

/**
 * <p>
 * 指示书视图
 * </p>
 * <p>
 * create: 2011-1-14 上午09:37:11
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZssVO {

	/**
	 * 指示书类型：ETL|SL
	 */
	private String type;

	/**
	 * 指示连号
	 */
	private String zsno;

	/**
	 * 操作方法
	 */
	private String czff;

	/**
	 * 装入宽
	 */
	private String zrk;

	/**
	 * 剪断长
	 */
	private String jdc;

	/**
	 * 剪边标志
	 */
	private String trim;

	/**
	 * trim后宽
	 */
	private String trimHk;

	/**
	 * 运用规格
	 */
	private String yygg;

	/**
	 * 锡附着量（上/下）
	 */
	private String xfzl;

	/**
	 * 附着量.正.下限
	 */
	private String fzxx;

	/**
	 * 附着量.正.上限
	 */
	private String fzsx;

	/**
	 * 附着量.反.下限
	 */
	private String ffxx;

	/**
	 * 附着量.反.上限
	 */
	private String ffsx;

	/**
	 * reflow：是|否
	 */
	private String reflow;

	/**
	 * 表面精加工
	 */
	private String bmjg;

	/**
	 * 合金层
	 */
	private String hjc;

	/**
	 * k-plate洗净强化
	 */
	private String kplate;

	/**
	 * 化学处理方法
	 */
	private String hxcl;

	/**
	 * 涂油种类
	 */
	private String tyzl;

	/**
	 * 涂油量
	 */
	private String tyl;

	/**
	 * 用户略称
	 */
	private String yhlc;

	/**
	 * 合同号
	 */
	private String dhno;

	/**
	 * 合同行号
	 */
	private String dhline;

	/**
	 * 销售W否
	 */
	private String w;

	/**
	 * 分配等级
	 */
	private String fpdj;

	/**
	 * 钢种
	 */
	private String gz;

	/**
	 * 直角度
	 */
	private String zjd;

	/**
	 * m单重
	 */
	private String mdz;

	/**
	 * SHEET单重
	 */
	private String sdz;

	/**
	 * 112张单重
	 */
	private String dz112;
	/**
	 * 交货区分
	 */
	private String jhqf;

	/**
	 * 内径代码
	 */
	private String neij;

	/**
	 * SLEEVE
	 */
	private String sleeve;

	/**
	 * PROTEXCT
	 */
	private String protect;

	/**
	 * SCROLL可否
	 */
	private String sckf;

	/**
	 * SCROLL种类
	 */
	private String sczl;

	/**
	 * 制品尺寸
	 */
	private String size;

	/**
	 * 订货规格
	 */
	private String dhgg;

	/**
	 * 订货附着量略称
	 */
	private String dhdxl;

	/**
	 * 尺寸允许范围-板厚%-下限
	 */
	private String bhxx;

	/**
	 * 尺寸允许范围-板厚%-上限
	 */
	private String bhsx;

	/**
	 * 尺寸允许范围-板厚mm-下限
	 */
	private String bhmxx;

	/**
	 * 尺寸允许范围-板厚mm-上限
	 */
	private String bhmsx;

	/**
	 * 尺寸允许范围-板宽mm-下限
	 */
	private String bkmxx;

	/**
	 * 尺寸允许范围-板宽mm-上限
	 */
	private String bkmsx;

	/**
	 * 尺寸允许范围-板长mm-下限
	 */
	private String bcmxx;

	/**
	 * 尺寸允许范围-板长mm-上限
	 */
	private String bcmsx;

	/**
	 * 包装张数
	 */
	private String bzzs;

	/**
	 * Lot重量
	 */
	private String lotZl;

	/**
	 * Box数
	 */
	private String boxs;

	/**
	 * B/B重量
	 */
	private String bbZl;

	/**
	 * 零头可否
	 */
	private String ling;

	/**
	 * 零头下限
	 */
	private String ltxx;

	/**
	 * Tin补正系数
	 */
	private String tin;

	/**
	 * 垫木方向
	 */
	private String dmfx;

	/**
	 * 垫木重量
	 */
	private String dmzl;

	/**
	 * 包装材料重量
	 */
	private String bzclZl;

	/**
	 * 捆包指定-下限
	 */
	private String kbxx;

	/**
	 * 捆包指定-上限
	 */
	private String kbsx;

	/**
	 * 捆包形式
	 */
	private String kbxs;

	/**
	 * 规格
	 */
	private String ggnm;

	/**
	 * 货标
	 */
	private String huob;

	/**
	 * Package No.
	 */
	private String packageNo;

	/**
	 * 用户名称
	 */
	private String user;

	/**
	 * 订货纳期
	 */
	private String dhnq;

	/**
	 * 制品列表
	 */
	private List<JbVO> jbs;

	/**
	 * 备注1
	 */
	private String bz1;

	/**
	 * 备注2
	 */
	private String bz2;

	/**
	 * 备注3
	 */
	private String bz3;

	/**
	 * 木工所No.
	 */
	private String mgsn;

	/**
	 * 用途
	 */
	private String cond;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the zsno
	 */
	public String getZsno() {
		return zsno;
	}

	/**
	 * @param zsno
	 *            the zsno to set
	 */
	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	/**
	 * @return the czff
	 */
	public String getCzff() {
		return czff;
	}

	/**
	 * @param czff
	 *            the czff to set
	 */
	public void setCzff(String czff) {
		this.czff = czff;
	}

	/**
	 * @return the zrk
	 */
	public String getZrk() {
		return zrk;
	}

	/**
	 * @param zrk
	 *            the zrk to set
	 */
	public void setZrk(String zrk) {
		this.zrk = zrk;
	}

	/**
	 * @return the jdc
	 */
	public String getJdc() {
		return jdc;
	}

	/**
	 * @param jdc
	 *            the jdc to set
	 */
	public void setJdc(String jdc) {
		this.jdc = jdc;
	}

	/**
	 * @return the trim
	 */
	public String getTrim() {
		return trim;
	}

	/**
	 * @param trim
	 *            the trim to set
	 */
	public void setTrim(String trim) {
		this.trim = trim;
	}

	/**
	 * @return the trimHk
	 */
	public String getTrimHk() {
		return trimHk;
	}

	/**
	 * @param trimHk
	 *            the trimHk to set
	 */
	public void setTrimHk(String trimHk) {
		this.trimHk = trimHk;
	}

	/**
	 * @return the yygg
	 */
	public String getYygg() {
		return yygg;
	}

	/**
	 * @param yygg
	 *            the yygg to set
	 */
	public void setYygg(String yygg) {
		this.yygg = yygg;
	}

	/**
	 * @return the xfzl
	 */
	public String getXfzl() {
		return xfzl;
	}

	/**
	 * @param xfzl
	 *            the xfzl to set
	 */
	public void setXfzl(String xfzl) {
		this.xfzl = xfzl;
	}

	/**
	 * @return the fzsx
	 */
	public String getFzsx() {
		return fzsx;
	}

	/**
	 * @param fzsx
	 *            the fzsx to set
	 */
	public void setFzsx(String fzsx) {
		this.fzsx = fzsx;
	}

	/**
	 * @return the fzxx
	 */
	public String getFzxx() {
		return fzxx;
	}

	/**
	 * @param fzxx
	 *            the fzxx to set
	 */
	public void setFzxx(String fzxx) {
		this.fzxx = fzxx;
	}

	/**
	 * @return the ffsx
	 */
	public String getFfsx() {
		return ffsx;
	}

	/**
	 * @param ffsx
	 *            the ffsx to set
	 */
	public void setFfsx(String ffsx) {
		this.ffsx = ffsx;
	}

	/**
	 * @return the ffxx
	 */
	public String getFfxx() {
		return ffxx;
	}

	/**
	 * @param ffxx
	 *            the ffxx to set
	 */
	public void setFfxx(String ffxx) {
		this.ffxx = ffxx;
	}

	/**
	 * @return the reflow
	 */
	public String getReflow() {
		return reflow;
	}

	/**
	 * @param reflow
	 *            the reflow to set
	 */
	public void setReflow(String reflow) {
		this.reflow = reflow;
	}

	/**
	 * @return the bmjg
	 */
	public String getBmjg() {
		return bmjg;
	}

	/**
	 * @param bmjg
	 *            the bmjg to set
	 */
	public void setBmjg(String bmjg) {
		this.bmjg = bmjg;
	}

	/**
	 * @return the hjc
	 */
	public String getHjc() {
		return hjc;
	}

	/**
	 * @param hjc
	 *            the hjc to set
	 */
	public void setHjc(String hjc) {
		this.hjc = hjc;
	}

	/**
	 * @return the kplate
	 */
	public String getKplate() {
		return kplate;
	}

	/**
	 * @param kplate
	 *            the kplate to set
	 */
	public void setKplate(String kplate) {
		this.kplate = kplate;
	}

	/**
	 * @return the hxcl
	 */
	public String getHxcl() {
		return hxcl;
	}

	/**
	 * @param hxcl
	 *            the hxcl to set
	 */
	public void setHxcl(String hxcl) {
		this.hxcl = hxcl;
	}

	/**
	 * @return the tyzl
	 */
	public String getTyzl() {
		return tyzl;
	}

	/**
	 * @param tyzl
	 *            the tyzl to set
	 */
	public void setTyzl(String tyzl) {
		this.tyzl = tyzl;
	}

	/**
	 * @return the tyl
	 */
	public String getTyl() {
		return tyl;
	}

	/**
	 * @param tyl
	 *            the tyl to set
	 */
	public void setTyl(String tyl) {
		this.tyl = tyl;
	}

	/**
	 * @return the yhlc
	 */
	public String getYhlc() {
		return yhlc;
	}

	/**
	 * @param yhlc
	 *            the yhlc to set
	 */
	public void setYhlc(String yhlc) {
		this.yhlc = yhlc;
	}

	/**
	 * @return the jbs
	 */
	public List<JbVO> getJbs() {
		return jbs;
	}

	/**
	 * @param jbs
	 *            the jbs to set
	 */
	public void setJbs(List<JbVO> jbs) {
		this.jbs = jbs;
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

	/**
	 * @return the w
	 */
	public String getW() {
		return w;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(String w) {
		this.w = w;
	}

	/**
	 * @return the fpdj
	 */
	public String getFpdj() {
		return fpdj;
	}

	/**
	 * @param fpdj
	 *            the fpdj to set
	 */
	public void setFpdj(String fpdj) {
		this.fpdj = fpdj;
	}

	/**
	 * @return the dhdxl
	 */
	public String getDhdxl() {
		return dhdxl;
	}

	/**
	 * @param dhdxl
	 *            the dhdxl to set
	 */
	public void setDhdxl(String dhdxl) {
		this.dhdxl = dhdxl;
	}

	/**
	 * @return the bhxx
	 */
	public String getBhxx() {
		return bhxx;
	}

	/**
	 * @param bhxx
	 *            the bhxx to set
	 */
	public void setBhxx(String bhxx) {
		this.bhxx = bhxx;
	}

	/**
	 * @return the bhsx
	 */
	public String getBhsx() {
		return bhsx;
	}

	/**
	 * @param bhsx
	 *            the bhsx to set
	 */
	public void setBhsx(String bhsx) {
		this.bhsx = bhsx;
	}

	/**
	 * @return the bhmxx
	 */
	public String getBhmxx() {
		return bhmxx;
	}

	/**
	 * @param bhmxx
	 *            the bhmxx to set
	 */
	public void setBhmxx(String bhmxx) {
		this.bhmxx = bhmxx;
	}

	/**
	 * @return the bhmsx
	 */
	public String getBhmsx() {
		return bhmsx;
	}

	/**
	 * @param bhmsx
	 *            the bhmsx to set
	 */
	public void setBhmsx(String bhmsx) {
		this.bhmsx = bhmsx;
	}

	/**
	 * @return the bkmxx
	 */
	public String getBkmxx() {
		return bkmxx;
	}

	/**
	 * @param bkmxx
	 *            the bkmxx to set
	 */
	public void setBkmxx(String bkmxx) {
		this.bkmxx = bkmxx;
	}

	/**
	 * @return the bkmsx
	 */
	public String getBkmsx() {
		return bkmsx;
	}

	/**
	 * @param bkmsx
	 *            the bkmsx to set
	 */
	public void setBkmsx(String bkmsx) {
		this.bkmsx = bkmsx;
	}

	/**
	 * @return the bcmxx
	 */
	public String getBcmxx() {
		return bcmxx;
	}

	/**
	 * @param bcmxx
	 *            the bcmxx to set
	 */
	public void setBcmxx(String bcmxx) {
		this.bcmxx = bcmxx;
	}

	/**
	 * @return the bcmsx
	 */
	public String getBcmsx() {
		return bcmsx;
	}

	/**
	 * @param bcmsx
	 *            the bcmsx to set
	 */
	public void setBcmsx(String bcmsx) {
		this.bcmsx = bcmsx;
	}

	/**
	 * @return the bzzs
	 */
	public String getBzzs() {
		return bzzs;
	}

	/**
	 * @param bzzs
	 *            the bzzs to set
	 */
	public void setBzzs(String bzzs) {
		this.bzzs = bzzs;
	}

	/**
	 * @return the lotZl
	 */
	public String getLotZl() {
		return lotZl;
	}

	/**
	 * @param lotZl
	 *            the lotZl to set
	 */
	public void setLotZl(String lotZl) {
		this.lotZl = lotZl;
	}

	/**
	 * @return the boxs
	 */
	public String getBoxs() {
		return boxs;
	}

	/**
	 * @param boxs
	 *            the boxs to set
	 */
	public void setBoxs(String boxs) {
		this.boxs = boxs;
	}

	/**
	 * @return the bbZl
	 */
	public String getBbZl() {
		return bbZl;
	}

	/**
	 * @param bbZl
	 *            the bbZl to set
	 */
	public void setBbZl(String bbZl) {
		this.bbZl = bbZl;
	}

	/**
	 * @return the ling
	 */
	public String getLing() {
		return ling;
	}

	/**
	 * @param ling
	 *            the ling to set
	 */
	public void setLing(String ling) {
		this.ling = ling;
	}

	/**
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}

	/**
	 * @param tin
	 *            the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * @return the dmfx
	 */
	public String getDmfx() {
		return dmfx;
	}

	/**
	 * @param dmfx
	 *            the dmfx to set
	 */
	public void setDmfx(String dmfx) {
		this.dmfx = dmfx;
	}

	/**
	 * @return the dmzl
	 */
	public String getDmzl() {
		return dmzl;
	}

	/**
	 * @param dmzl
	 *            the dmzl to set
	 */
	public void setDmzl(String dmzl) {
		this.dmzl = dmzl;
	}

	/**
	 * @return the bzclZl
	 */
	public String getBzclZl() {
		return bzclZl;
	}

	/**
	 * @param bzclZl
	 *            the bzclZl to set
	 */
	public void setBzclZl(String bzclZl) {
		this.bzclZl = bzclZl;
	}

	/**
	 * @return the kbxx
	 */
	public String getKbxx() {
		return kbxx;
	}

	/**
	 * @param kbxx
	 *            the kbxx to set
	 */
	public void setKbxx(String kbxx) {
		this.kbxx = kbxx;
	}

	/**
	 * @return the kbsx
	 */
	public String getKbsx() {
		return kbsx;
	}

	/**
	 * @param kbsx
	 *            the kbsx to set
	 */
	public void setKbsx(String kbsx) {
		this.kbsx = kbsx;
	}

	/**
	 * @return the kbxs
	 */
	public String getKbxs() {
		return kbxs;
	}

	/**
	 * @param kbxs
	 *            the kbxs to set
	 */
	public void setKbxs(String kbxs) {
		this.kbxs = kbxs;
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
	 * @return the huob
	 */
	public String getHuob() {
		return huob;
	}

	/**
	 * @param huob
	 *            the huob to set
	 */
	public void setHuob(String huob) {
		this.huob = huob;
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
	 * @return the gz
	 */
	public String getGz() {
		return gz;
	}

	/**
	 * @param gz
	 *            the gz to set
	 */
	public void setGz(String gz) {
		this.gz = gz;
	}

	/**
	 * @return the zjd
	 */
	public String getZjd() {
		return zjd;
	}

	/**
	 * @param zjd
	 *            the zjd to set
	 */
	public void setZjd(String zjd) {
		this.zjd = zjd;
	}

	/**
	 * @return the mdz
	 */
	public String getMdz() {
		return mdz;
	}

	/**
	 * @param mdz
	 *            the mdz to set
	 */
	public void setMdz(String mdz) {
		this.mdz = mdz;
	}

	/**
	 * @return the sdz
	 */
	public String getSdz() {
		return sdz;
	}

	/**
	 * @param sdz
	 *            the sdz to set
	 */
	public void setSdz(String sdz) {
		this.sdz = sdz;
	}

	/**
	 * @return the dz112
	 */
	public String getDz112() {
		return dz112;
	}

	/**
	 * @param dz112
	 *            the dz112 to set
	 */
	public void setDz112(String dz112) {
		this.dz112 = dz112;
	}

	/**
	 * @return the jhqf
	 */
	public String getJhqf() {
		return jhqf;
	}

	/**
	 * @param jhqf
	 *            the jhqf to set
	 */
	public void setJhqf(String jhqf) {
		this.jhqf = jhqf;
	}

	/**
	 * @return the neij
	 */
	public String getNeij() {
		return neij;
	}

	/**
	 * @param neij
	 *            the neij to set
	 */
	public void setNeij(String neij) {
		this.neij = neij;
	}

	/**
	 * @return the sleeve
	 */
	public String getSleeve() {
		return sleeve;
	}

	/**
	 * @param sleeve
	 *            the sleeve to set
	 */
	public void setSleeve(String sleeve) {
		this.sleeve = sleeve;
	}

	/**
	 * @return the protect
	 */
	public String getProtect() {
		return protect;
	}

	/**
	 * @param protect
	 *            the protect to set
	 */
	public void setProtect(String protect) {
		this.protect = protect;
	}

	/**
	 * @return the sckf
	 */
	public String getSckf() {
		return sckf;
	}

	/**
	 * @param sckf
	 *            the sckf to set
	 */
	public void setSckf(String sckf) {
		this.sckf = sckf;
	}

	/**
	 * @return the sczl
	 */
	public String getSczl() {
		return sczl;
	}

	/**
	 * @param sczl
	 *            the sczl to set
	 */
	public void setSczl(String sczl) {
		this.sczl = sczl;
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
	 * @return the ltxx
	 */
	public String getLtxx() {
		return ltxx;
	}

	/**
	 * @param ltxx
	 *            the ltxx to set
	 */
	public void setLtxx(String ltxx) {
		this.ltxx = ltxx;
	}

	/**
	 * @return the dhnq
	 */
	public String getDhnq() {
		return dhnq;
	}

	/**
	 * @param dhnq
	 *            the dhnq to set
	 */
	public void setDhnq(String dhnq) {
		this.dhnq = dhnq;
	}

	/**
	 * @return the dhgg
	 */
	public String getDhgg() {
		return dhgg;
	}

	/**
	 * @param dhgg
	 *            the dhgg to set
	 */
	public void setDhgg(String dhgg) {
		this.dhgg = dhgg;
	}

	public String getMgsn() {
		return mgsn;
	}

	public void setMgsn(String mgsn) {
		this.mgsn = mgsn;
	}

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

}
