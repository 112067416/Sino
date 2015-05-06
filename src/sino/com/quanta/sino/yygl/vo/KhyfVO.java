package com.quanta.sino.yygl.vo;

/**
 * <p>
 * 客户运费VO
 * </p>
 * <p>
 * create: 2011-2-15 下午05:13:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfVO {

	/**
	 * 客户运费主键
	 */
	private String id;

	/**
	 * 运输方式代码
	 */
	private String ysfs;

	/**
	 * 运输方式名称
	 */
	private String ysfm;

	/**
	 * 货柜海运.堆场柜数
	 */

	private Double dchy;

	/**
	 * 货柜海运.船运柜数
	 */

	private Double cyhy;

	/**
	 * 船运到客户。货柜海运柜数
	 */
	private Double cyhy1;

	/**
	 * 目的港到客户。货柜海运柜数
	 */
	private Double khhh1;

	/**
	 * 中日达到起始港口（货柜海运）(RMB/吨)
	 */

	private Double qghhdj;

	/**
	 * 堆场（货柜海运）(RMB/柜)
	 */

	private Double dchhdj;

	/**
	 * 船运（货柜海运）(RMB/柜)
	 */

	private Double cyhhdj;

	/**
	 * 
	 */
	private Double cyhhdj1;

	/**
	 * 目的港到客户（货柜海运）(RMB/吨)
	 */

	private Double khhhdj;

	/**
	 * 
	 */
	private Double khhhdj1;

	/**
	 * 中日达到起始港口（货柜海运）。相关公司代码
	 */

	private String qghhno;

	/**
	 * 中日达到起始港口（货柜海运）。相关公司名称
	 */

	private String qghhnm;

	/**
	 * 堆场（货柜海运）。相关公司代码
	 */

	private String dchhno;

	/**
	 * 堆场（货柜海运）。相关公司名称
	 */

	private String dchhnm;
	/**
	 * 船运（货柜海运）。相关公司代码
	 */

	private String cyhhno;

	/**
	 * 船运（货柜海运）。相关公司名称
	 */

	private String cyhhnm;

	/**
	 * 船运到客户（货柜海运）。相关公司代码
	 */

	private String cyhhno1;

	/**
	 * 船运到客户（货柜海运）。相关公司名称
	 */

	private String cyhhnm1;

	/**
	 * 目的港到客户（货柜海运）。相关公司代码
	 */

	private String khhhno;

	/**
	 * 目的港到客户（货柜海运）。相关公司名称
	 */

	private String khhhnm;

	/**
	 * 目的港到客户RMB/柜（货柜海运）。相关公司代码
	 */

	private String khhhno1;

	/**
	 * 目的港到客户RMB/柜（货柜海运）。相关公司名称
	 */

	private String khhhnm1;

	/**
	 * 中日达到目的港（散货海运）(RMB/吨)
	 */

	private Double qgshdj;

	/**
	 * 目的港到客户（散货海运）(RMB/吨)
	 */

	private Double khshdj;

	/**
	 * 中日达到目的港（散货海运）。相关公司代码
	 */

	private String qgshno;

	/**
	 * 中日达到目的港（散货海运）。相关公司名称
	 */

	private String qgshnm;

	/**
	 * 目的港到客户（散货海运）。相关公司代码
	 */

	private String khshno;

	/**
	 * 目的港到客户（散货海运）。相关公司名称
	 */

	private String khshnm;

	/**
	 * 中日达到客户（公路）(RMB/吨)
	 */

	private Double khgldj;

	/**
	 * 中日达到客户（公路）。相关公司代码
	 */

	private String khglno;

	/**
	 * 中日达到客户（公路）。相关公司名称
	 */

	private String khglnm;
	/**
	 * 中日达到终点站（铁路）(RMB/吨)
	 */

	private Double zdtldj;

	/**
	 * 终点站到客户（铁路）(RMB/吨)
	 */

	private Double khtldj;
	/**
	 * 中日达到终点站（铁路）。相关公司代码
	 */

	private String zdtlno;

	/**
	 * 中日达到终点站（铁路）。相关公司名称
	 */

	private String zdtlnm;
	/**
	 * 终点站到客户（铁路）。相关公司代码
	 */

	private String khtlno;

	/**
	 * 终点站到客户（铁路）。相关公司名称
	 */

	private String khtlnm;

	/**
	 * 中日达到起始港（出口）(RMB/吨)
	 */

	private Double qgckdj;

	/**
	 * 堆场（出口）(RMB/柜)
	 */

	private Double dcckdj;

	/**
	 * 船运（出口）(USD/柜)
	 */

	private Double cyckdj;

	/**
	 * 船运（出口）(RMB/柜)
	 */

	private Double cyckdj1;

	/**
	 * 目的港到客户(出口)(USD/柜)
	 */

	private Double khckdj;

	/**
	 * 出口.堆场柜数
	 */

	private Double dcck;

	/**
	 * 出口.船运柜数
	 */

	private Double cyck;

	/**
	 * 出口.船运柜数
	 */

	private Double cyck1;

	/**
	 * 出口.目的港到客户柜数
	 */

	private Double mdck;
	/**
	 * 中日达到起始港（出口）。相关公司代码
	 */

	private String qgckno;

	/**
	 * 中日达到起始港（出口）。相关公司名称
	 */

	private String qgcknm;
	/**
	 * 堆场（出口）。相关公司代码
	 */

	private String dcckno;

	/**
	 * 堆场（出口）。相关公司名称
	 */

	private String dccknm;

	/**
	 * 船运（出口）。相关公司代码
	 */

	private String cyckno;

	/**
	 * 船运（出口）。相关公司名称
	 */

	private String cycknm;

	/**
	 * 船运（出口）。相关公司代码
	 */

	private String cyckno1;

	/**
	 * 船运（出口）。相关公司名称
	 */

	private String cycknm1;

	/**
	 * 目的港到客户（出口）。相关公司代码
	 */

	private String khckno;

	/**
	 * 目的港到客户（出口）。相关公司名称
	 */

	private String khcknm;

	/**
	 * 自提
	 */
	private Double ztdj;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Double getDchy() {
		return dchy;
	}

	public void setDchy(Double dchy) {
		this.dchy = dchy;
	}

	public Double getCyhy() {
		return cyhy;
	}

	public void setCyhy(Double cyhy) {
		this.cyhy = cyhy;
	}

	public Double getCyhy1() {
		return cyhy1;
	}

	public void setCyhy1(Double cyhy1) {
		this.cyhy1 = cyhy1;
	}

	public Double getKhhh1() {
		return khhh1;
	}

	public void setKhhh1(Double khhh1) {
		this.khhh1 = khhh1;
	}

	public Double getQghhdj() {
		return qghhdj;
	}

	public void setQghhdj(Double qghhdj) {
		this.qghhdj = qghhdj;
	}

	public Double getDchhdj() {
		return dchhdj;
	}

	public void setDchhdj(Double dchhdj) {
		this.dchhdj = dchhdj;
	}

	public Double getCyhhdj() {
		return cyhhdj;
	}

	public void setCyhhdj(Double cyhhdj) {
		this.cyhhdj = cyhhdj;
	}

	public Double getCyhhdj1() {
		return cyhhdj1;
	}

	public void setCyhhdj1(Double cyhhdj1) {
		this.cyhhdj1 = cyhhdj1;
	}

	public Double getKhhhdj() {
		return khhhdj;
	}

	public void setKhhhdj(Double khhhdj) {
		this.khhhdj = khhhdj;
	}

	public Double getKhhhdj1() {
		return khhhdj1;
	}

	public void setKhhhdj1(Double khhhdj1) {
		this.khhhdj1 = khhhdj1;
	}

	public String getQghhno() {
		return qghhno;
	}

	public void setQghhno(String qghhno) {
		this.qghhno = qghhno;
	}

	public String getQghhnm() {
		return qghhnm;
	}

	public void setQghhnm(String qghhnm) {
		this.qghhnm = qghhnm;
	}

	public String getDchhno() {
		return dchhno;
	}

	public void setDchhno(String dchhno) {
		this.dchhno = dchhno;
	}

	public String getDchhnm() {
		return dchhnm;
	}

	public void setDchhnm(String dchhnm) {
		this.dchhnm = dchhnm;
	}

	public String getCyhhno() {
		return cyhhno;
	}

	public void setCyhhno(String cyhhno) {
		this.cyhhno = cyhhno;
	}

	public String getCyhhnm() {
		return cyhhnm;
	}

	public void setCyhhnm(String cyhhnm) {
		this.cyhhnm = cyhhnm;
	}

	public String getCyhhno1() {
		return cyhhno1;
	}

	public void setCyhhno1(String cyhhno1) {
		this.cyhhno1 = cyhhno1;
	}

	public String getCyhhnm1() {
		return cyhhnm1;
	}

	public void setCyhhnm1(String cyhhnm1) {
		this.cyhhnm1 = cyhhnm1;
	}

	public String getKhhhno() {
		return khhhno;
	}

	public void setKhhhno(String khhhno) {
		this.khhhno = khhhno;
	}

	public String getKhhhnm() {
		return khhhnm;
	}

	public void setKhhhnm(String khhhnm) {
		this.khhhnm = khhhnm;
	}

	public String getKhhhno1() {
		return khhhno1;
	}

	public void setKhhhno1(String khhhno1) {
		this.khhhno1 = khhhno1;
	}

	public String getKhhhnm1() {
		return khhhnm1;
	}

	public void setKhhhnm1(String khhhnm1) {
		this.khhhnm1 = khhhnm1;
	}

	public Double getQgshdj() {
		return qgshdj;
	}

	public void setQgshdj(Double qgshdj) {
		this.qgshdj = qgshdj;
	}

	public Double getKhshdj() {
		return khshdj;
	}

	public void setKhshdj(Double khshdj) {
		this.khshdj = khshdj;
	}

	public String getQgshno() {
		return qgshno;
	}

	public void setQgshno(String qgshno) {
		this.qgshno = qgshno;
	}

	public String getQgshnm() {
		return qgshnm;
	}

	public void setQgshnm(String qgshnm) {
		this.qgshnm = qgshnm;
	}

	public String getKhshno() {
		return khshno;
	}

	public void setKhshno(String khshno) {
		this.khshno = khshno;
	}

	public String getKhshnm() {
		return khshnm;
	}

	public void setKhshnm(String khshnm) {
		this.khshnm = khshnm;
	}

	public Double getKhgldj() {
		return khgldj;
	}

	public void setKhgldj(Double khgldj) {
		this.khgldj = khgldj;
	}

	public String getKhglno() {
		return khglno;
	}

	public void setKhglno(String khglno) {
		this.khglno = khglno;
	}

	public String getKhglnm() {
		return khglnm;
	}

	public void setKhglnm(String khglnm) {
		this.khglnm = khglnm;
	}

	public Double getZdtldj() {
		return zdtldj;
	}

	public void setZdtldj(Double zdtldj) {
		this.zdtldj = zdtldj;
	}

	public Double getKhtldj() {
		return khtldj;
	}

	public void setKhtldj(Double khtldj) {
		this.khtldj = khtldj;
	}

	public String getZdtlno() {
		return zdtlno;
	}

	public void setZdtlno(String zdtlno) {
		this.zdtlno = zdtlno;
	}

	public String getZdtlnm() {
		return zdtlnm;
	}

	public void setZdtlnm(String zdtlnm) {
		this.zdtlnm = zdtlnm;
	}

	public String getKhtlno() {
		return khtlno;
	}

	public void setKhtlno(String khtlno) {
		this.khtlno = khtlno;
	}

	public String getKhtlnm() {
		return khtlnm;
	}

	public void setKhtlnm(String khtlnm) {
		this.khtlnm = khtlnm;
	}

	public Double getQgckdj() {
		return qgckdj;
	}

	public void setQgckdj(Double qgckdj) {
		this.qgckdj = qgckdj;
	}

	public Double getDcckdj() {
		return dcckdj;
	}

	public void setDcckdj(Double dcckdj) {
		this.dcckdj = dcckdj;
	}

	public Double getCyckdj() {
		return cyckdj;
	}

	public void setCyckdj(Double cyckdj) {
		this.cyckdj = cyckdj;
	}

	public Double getCyckdj1() {
		return cyckdj1;
	}

	public void setCyckdj1(Double cyckdj1) {
		this.cyckdj1 = cyckdj1;
	}

	public Double getKhckdj() {
		return khckdj;
	}

	public void setKhckdj(Double khckdj) {
		this.khckdj = khckdj;
	}

	public Double getDcck() {
		return dcck;
	}

	public void setDcck(Double dcck) {
		this.dcck = dcck;
	}

	public Double getCyck() {
		return cyck;
	}

	public void setCyck(Double cyck) {
		this.cyck = cyck;
	}

	public Double getCyck1() {
		return cyck1;
	}

	public void setCyck1(Double cyck1) {
		this.cyck1 = cyck1;
	}

	public Double getMdck() {
		return mdck;
	}

	public void setMdck(Double mdck) {
		this.mdck = mdck;
	}

	public String getQgckno() {
		return qgckno;
	}

	public void setQgckno(String qgckno) {
		this.qgckno = qgckno;
	}

	public String getQgcknm() {
		return qgcknm;
	}

	public void setQgcknm(String qgcknm) {
		this.qgcknm = qgcknm;
	}

	public String getDcckno() {
		return dcckno;
	}

	public void setDcckno(String dcckno) {
		this.dcckno = dcckno;
	}

	public String getDccknm() {
		return dccknm;
	}

	public void setDccknm(String dccknm) {
		this.dccknm = dccknm;
	}

	public String getCyckno() {
		return cyckno;
	}

	public void setCyckno(String cyckno) {
		this.cyckno = cyckno;
	}

	public String getCycknm() {
		return cycknm;
	}

	public void setCycknm(String cycknm) {
		this.cycknm = cycknm;
	}

	public String getCyckno1() {
		return cyckno1;
	}

	public void setCyckno1(String cyckno1) {
		this.cyckno1 = cyckno1;
	}

	public String getCycknm1() {
		return cycknm1;
	}

	public void setCycknm1(String cycknm1) {
		this.cycknm1 = cycknm1;
	}

	public String getKhckno() {
		return khckno;
	}

	public void setKhckno(String khckno) {
		this.khckno = khckno;
	}

	public String getKhcknm() {
		return khcknm;
	}

	public void setKhcknm(String khcknm) {
		this.khcknm = khcknm;
	}

	public Double getZtdj() {
		return ztdj;
	}

	public void setZtdj(Double ztdj) {
		this.ztdj = ztdj;
	}

}
