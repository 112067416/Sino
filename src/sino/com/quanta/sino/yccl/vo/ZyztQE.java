package com.quanta.sino.yccl.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 原材与制品联合查询
 * </p>
 * <p>
 * create: 2011-1-13 下午05:26:29
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select JBNO_,DHNO_,ABBR_,XPHO_ ,XPKN_ ,'0' AS XPCN_ , FACE_,'' AS FUZM_ ,'' AS FUFM_,ZPZL_,DUIC_,ZTBJ_,ZZSD_,'' AS PLQF_,KW_,'' AS CHNO_ from  SINO_YCAITP where STAT_ in ('0','1','2') AND#1# union all select JBNO_,DHNO_,ABBR_,HOUU_ as XPHO_ ,KUAN_ as XPKN_ ,CANG_  as XPCN_ , FACE_,FUZM_ ,FUFM_,ZPZL_,DUIC_,ZTBJ_,ZZSD_,PLQF_,KW_,CHNO_ from  SINO_ZPNGTP where STAT_='0' AND#1#) t ", meta = "JBNO_,DHNO_,ABBR_,XPHO_ ,XPKN_ ,XPCN_ , FACE_,FUZM_ ,FUFM_,ZPZL_,DUIC_,ZTBJ_,ZZSD_,PLQF_,KW_,CHNO_"))
public class ZyztQE extends QEntity<ZyztVO> {

	/**
	 * 卷板NO
	 */
	@QF(operator = EO.LIKE)
	private String jbno;

	/**
	 * 卷板NO
	 */
	@QF(alias = "JBNO_", operator = EO.IN)
	private String[] jbnos;

	/**
	 * 订货No.行号
	 */
	@QF(alias = "DHNO_", operator = EO.LIKE)
	private String dhnoAndLine;

	/**
	 * 堆场
	 */
	@QF
	private String duic;

	/**
	 * 作业停止标志
	 */
	@QF
	private String ztbj;

	/**
	 * 删除标记
	 */
	@QF
	private String scbj;

	/**
	 * 不等于删除标记
	 */
	@QF(alias = "SCBJ_", operator = EO.NOT_IN)
	private String[] neScbjs;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String[] getJbnos() {
		return jbnos;
	}

	public void setJbnoStr(String jbnoStr) {
		if (jbnoStr == null || jbnoStr.isEmpty()) return;
		this.jbnos = jbnoStr.split(",");
	}

	public void setJbnos(String[] jbnos) {
		this.jbnos = jbnos;
	}

	public String getDhnoAndLine() {
		return dhnoAndLine;
	}

	public void setDhnoAndLine(String dhnoAndLine) {
		this.dhnoAndLine = dhnoAndLine;
	}

	public String getDuic() {
		return duic;
	}

	public void setDuic(String duic) {
		this.duic = duic;
	}

	public String getZtbj() {
		return ztbj;
	}

	public void setZtbj(String ztbj) {
		this.ztbj = ztbj;
	}

	public String getScbj() {
		return scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	public String[] getNeScbjs() {
		return neScbjs;
	}

	public void setNeScbjs(String[] neScbjs) {
		this.neScbjs = neScbjs;
	}

}
