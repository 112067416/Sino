package com.quanta.sino.etl.vo;

import java.util.Date;
import java.util.List;

import com.quanta.sino.orm.ETLVariTp;

/**
 * <p>
 * etl速报报表填充数据
 * </p>
 * <p>
 * create: 2011-3-1 上午10:41:56
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
public class EtlSbMainDataVO {
	/**
	 * 1各班生产特记数据
	 */
	private ETLVariTp vari1;
	/**
	 * 2各班生产特记数据
	 */
	private ETLVariTp vari2;
	/**
	 * 3各班生产特记数据
	 */
	private ETLVariTp vari3;
	/**
	 * 各班生产停线数据
	 */
	private List<EtlSbStopDataVO> stopbms;

	/**
	 * 一天中各组的生产数据
	 */
	private List<EtlBanTjVO> vos;
	/**
	 * 当日日期
	 */
	private Date crea;
	/**
	 * 当日计划
	 */
	private Double jszl;
	/**
	 * 当日实绩
	 */
	private Double sjzl;
	/**
	 * 当日较差
	 */
	private Double chazl;
	/**
	 * 操业时间1
	 */
	private Double zysj1;
	/**
	 * 操业时间2
	 */
	private Double zysj2;
	/**
	 * 操业时间3
	 */
	private Double zysj3;
	/**
	 * 当日操业时间
	 */
	private Double zysj;

	/**
	 * 实动时间1
	 */
	private Double sdsj1;
	/**
	 * 实动时间2
	 */
	private Double sdsj2;
	/**
	 * 实动时间3
	 */
	private Double sdsj3;
	/**
	 * 当日总实动时间
	 */
	private Double sdsj;
	/**
	 * 总实动时间
	 */
	private Double sdsjAll;
	/**
	 * 实动率1
	 */
	private Double sdli1;
	/**
	 * 实动率1
	 */
	private Double sdli2;
	/**
	 * 实动率1
	 */
	private Double sdli3;
	/**
	 * 当日实动率
	 */
	private Double sdli;
	/**
	 * 能率1
	 */
	private Double nli1;
	/**
	 * 能率1
	 */
	private Double nli2;
	/**
	 * 能率3
	 */
	private Double nli3;
	/**
	 * 当日能率
	 */
	private Double nli;
	/**
	 * 总能率
	 */
	private Double nliAll;
	/**
	 * 总计划
	 */
	private Double jszlAll;
	/**
	 * 总实绩
	 */
	private Double sjzlAll;
	/**
	 * 总较差
	 */
	private Double chazlAll;
	/**
	 * 总经历
	 */
	private Double jlAll;
	/**
	 * 总操业
	 */
	private Double zyAll;
	/**
	 * 总实动
	 */
	private Double sdAll;
	/**
	 * 总修正
	 */
	private Double xzAll;
	/**
	 * 当日pass时间
	 */
	private Double pass;
	/**
	 * 当日pass时间使用率
	 */
	private Double passli;
	/**
	 * 总pass时间
	 */
	private Double passAll;
	/**
	 * 总pass时间使用率
	 */
	private Double passAllli;
	/**
	 * 当日休止I
	 */
	private Double xz1;
	/**
	 * 当日休止II
	 */
	private Double xz2;
	/**
	 * 当日休止II
	 */
	private Double xz3;
	/**
	 * 当日休止II
	 */
	private Double xz4;
	/**
	 * 累计休止I
	 */
	private Double xzAll1;
	/**
	 * 累计休止II
	 */
	private Double xzAll2;
	/**
	 * 累计休止II
	 */
	private Double xzAll3;
	/**
	 * 累计休止II
	 */
	private Double xzAll4;
	/**
	 * 累计休止I
	 */
	private Double xzAllhour1;
	/**
	 * 累计休止II
	 */
	private Double xzAllhour2;
	/**
	 * 累计休止II
	 */
	private Double xzAllhour3;
	/**
	 * 累计休止II
	 */
	private Double xzAllhour4;

	public List<EtlBanTjVO> getVos() {
		return vos;
	}

	public void setVos(List<EtlBanTjVO> vos) {
		this.vos = vos;
	}

	public Date getCrea() {
		return crea;
	}

	public void setCrea(Date crea) {
		this.crea = crea;
	}

	public Double getJszl() {
		return jszl;
	}

	public void setJszl(Double jszl) {
		this.jszl = jszl;
	}

	public Double getSjzl() {
		return sjzl;
	}

	public void setSjzl(Double sjzl) {
		this.sjzl = sjzl;
	}

	public Double getChazl() {
		return chazl;
	}

	public void setChazl(Double chazl) {
		this.chazl = chazl;
	}

	public Double getZysj1() {
		return zysj1;
	}

	public void setZysj1(Double zysj1) {
		this.zysj1 = zysj1;
	}

	public Double getZysj2() {
		return zysj2;
	}

	public void setZysj2(Double zysj2) {
		this.zysj2 = zysj2;
	}

	public Double getZysj3() {
		return zysj3;
	}

	public void setZysj3(Double zysj3) {
		this.zysj3 = zysj3;
	}

	public Double getZysj() {
		return zysj;
	}

	public void setZysj(Double zysj) {
		this.zysj = zysj;
	}

	public Double getSdsj1() {
		return sdsj1;
	}

	public void setSdsj1(Double sdsj1) {
		this.sdsj1 = sdsj1;
	}

	public Double getSdsj2() {
		return sdsj2;
	}

	public void setSdsj2(Double sdsj2) {
		this.sdsj2 = sdsj2;
	}

	public Double getSdsj3() {
		return sdsj3;
	}

	public void setSdsj3(Double sdsj3) {
		this.sdsj3 = sdsj3;
	}

	public Double getSdsj() {
		return sdsj;
	}

	public void setSdsj(Double sdsj) {
		this.sdsj = sdsj;
	}

	public Double getSdsjAll() {
		return sdsjAll;
	}

	public void setSdsjAll(Double sdsjAll) {
		this.sdsjAll = sdsjAll;
	}

	public Double getSdli1() {
		return sdli1;
	}

	public void setSdli1(Double sdli1) {
		this.sdli1 = sdli1;
	}

	public Double getSdli2() {
		return sdli2;
	}

	public void setSdli2(Double sdli2) {
		this.sdli2 = sdli2;
	}

	public Double getSdli3() {
		return sdli3;
	}

	public void setSdli3(Double sdli3) {
		this.sdli3 = sdli3;
	}

	public Double getSdli() {
		return sdli;
	}

	public void setSdli(Double sdli) {
		this.sdli = sdli;
	}

	public Double getNli1() {
		return nli1;
	}

	public void setNli1(Double nli1) {
		this.nli1 = nli1;
	}

	public Double getNli2() {
		return nli2;
	}

	public void setNli2(Double nli2) {
		this.nli2 = nli2;
	}

	public Double getNli3() {
		return nli3;
	}

	public void setNli3(Double nli3) {
		this.nli3 = nli3;
	}

	public Double getNli() {
		return nli;
	}

	public void setNli(Double nli) {
		this.nli = nli;
	}

	public Double getNliAll() {
		return nliAll;
	}

	public void setNliAll(Double nliAll) {
		this.nliAll = nliAll;
	}

	public Double getJszlAll() {
		return jszlAll;
	}

	public void setJszlAll(Double jszlAll) {
		this.jszlAll = jszlAll;
	}

	public Double getSjzlAll() {
		return sjzlAll;
	}

	public void setSjzlAll(Double sjzlAll) {
		this.sjzlAll = sjzlAll;
	}

	public Double getChazlAll() {
		return chazlAll;
	}

	public void setChazlAll(Double chazlAll) {
		this.chazlAll = chazlAll;
	}

	public Double getJlAll() {
		return jlAll;
	}

	public void setJlAll(Double jlAll) {
		this.jlAll = jlAll;
	}

	public Double getZyAll() {
		return zyAll;
	}

	public void setZyAll(Double zyAll) {
		this.zyAll = zyAll;
	}

	public Double getSdAll() {
		return sdAll;
	}

	public void setSdAll(Double sdAll) {
		this.sdAll = sdAll;
	}

	public Double getXzAll() {
		return xzAll;
	}

	public void setXzAll(Double xzAll) {
		this.xzAll = xzAll;
	}

	public Double getPass() {
		return pass;
	}

	public void setPass(Double pass) {
		this.pass = pass;
	}

	public Double getPassli() {
		return passli;
	}

	public void setPassli(Double passli) {
		this.passli = passli;
	}

	public Double getPassAll() {
		return passAll;
	}

	public void setPassAll(Double passAll) {
		this.passAll = passAll;
	}

	public Double getPassAllli() {
		return passAllli;
	}

	public void setPassAllli(Double passAllli) {
		this.passAllli = passAllli;
	}

	public Double getXz1() {
		return xz1;
	}

	public void setXz1(Double xz1) {
		this.xz1 = xz1;
	}

	public Double getXz2() {
		return xz2;
	}

	public void setXz2(Double xz2) {
		this.xz2 = xz2;
	}

	public Double getXz3() {
		return xz3;
	}

	public void setXz3(Double xz3) {
		this.xz3 = xz3;
	}

	public Double getXz4() {
		return xz4;
	}

	public void setXz4(Double xz4) {
		this.xz4 = xz4;
	}

	public Double getXzAll1() {
		return xzAll1;
	}

	public void setXzAll1(Double xzAll1) {
		this.xzAll1 = xzAll1;
	}

	public Double getXzAll2() {
		return xzAll2;
	}

	public void setXzAll2(Double xzAll2) {
		this.xzAll2 = xzAll2;
	}

	public Double getXzAll3() {
		return xzAll3;
	}

	public void setXzAll3(Double xzAll3) {
		this.xzAll3 = xzAll3;
	}

	public Double getXzAll4() {
		return xzAll4;
	}

	public void setXzAll4(Double xzAll4) {
		this.xzAll4 = xzAll4;
	}

	public Double getXzAllhour1() {
		return xzAllhour1;
	}

	public void setXzAllhour1(Double xzAllhour1) {
		this.xzAllhour1 = xzAllhour1;
	}

	public Double getXzAllhour2() {
		return xzAllhour2;
	}

	public void setXzAllhour2(Double xzAllhour2) {
		this.xzAllhour2 = xzAllhour2;
	}

	public Double getXzAllhour3() {
		return xzAllhour3;
	}

	public void setXzAllhour3(Double xzAllhour3) {
		this.xzAllhour3 = xzAllhour3;
	}

	public Double getXzAllhour4() {
		return xzAllhour4;
	}

	public void setXzAllhour4(Double xzAllhour4) {
		this.xzAllhour4 = xzAllhour4;
	}

	public ETLVariTp getVari1() {
		return vari1;
	}

	public void setVari1(ETLVariTp vari1) {
		this.vari1 = vari1;
	}

	public ETLVariTp getVari2() {
		return vari2;
	}

	public void setVari2(ETLVariTp vari2) {
		this.vari2 = vari2;
	}

	public ETLVariTp getVari3() {
		return vari3;
	}

	public void setVari3(ETLVariTp vari3) {
		this.vari3 = vari3;
	}

	public List<EtlSbStopDataVO> getStopbms() {
		return stopbms;
	}

	public void setStopbms(List<EtlSbStopDataVO> stopbms) {
		this.stopbms = stopbms;
	}

}
