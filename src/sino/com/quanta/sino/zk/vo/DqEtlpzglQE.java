package com.quanta.sino.zk.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 当前ETL品质管理记录查询(前30条)
 * </p>
 * <p>
 * create: 2011-6-15 下午05:22:56
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from (select top 30 ID_,JYCS_,PASS_,BAN_,ZU_,ZZSD_,JBNO_,FUGM_,SCZM_,SCFM_,XFZL_CMT_,XFZL_CMB_,XFZL_CAT_,XFZL_CAB_,XFZL_SMT_,XFZL_SMB_,XFZL_SAT_,XFZL_SAB_,XFZL_NMT_,XFZL_NMB_,XFZL_NAT_,XFZL_NAB_,YQTY_,YTYP_,OILER_BL_,TYL_ST_,TYL_SB_,TYL_CT_,TYL_CB_,TYL_NT_,TYL_NB_,CHE_DLMDT_,CHE_DLMDB_,CR_ST_,CR_SB_,CR_CT_,CR_CB_,CR_NT_,CR_NB_,TJ_,TYJMS_,BZ_,SCSJ_,UPDA_,STAT_,NEWED_,FUZS_,FUZX_,FUFS_,FUFX_,CRS_,CRX_,TYS_,TYX_,HJIN_ from SINO_ETLPZGL where#1# order by NEWED_ desc,SCSJ_ desc) t ", meta = "ID_,JYCS_,PASS_,BAN_,ZU_,ZZSD_,JBNO_,FUGM_,SCZM_,SCFM_,XFZL_CMT_,XFZL_CMB_,XFZL_CAT_,XFZL_CAB_,XFZL_SMT_,XFZL_SMB_,XFZL_SAT_,XFZL_SAB_,XFZL_NMT_,XFZL_NMB_,XFZL_NAT_,XFZL_NAB_,YQTY_,YTYP_,OILER_BL_,TYL_ST_,TYL_SB_,TYL_CT_,TYL_CB_,TYL_NT_,TYL_NB_,CHE_DLMDT_,CHE_DLMDB_,CR_ST_,CR_SB_,CR_CT_,CR_CB_,CR_NT_,CR_NB_,TJ_,TYJMS_,BZ_,SCSJ_,UPDA_,STAT_,NEWED_,FUZS_,FUZX_,FUFS_,FUFX_,CRS_,CRX_,TYS_,TYX_,HJIN_"))
public class DqEtlpzglQE extends QEntity<EtlpzGlVO> {

	/**
	 * Coil No.
	 */
	@QF(alias = "JBNO_")
	private String jbno;

	/**
	 * 状态
	 */
	@QF(alias = "STAT_")
	private String stat;

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
