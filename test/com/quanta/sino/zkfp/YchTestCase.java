package com.quanta.sino.zkfp;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.cmn.bo.api.IYchBO;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 制品余才化操作测试用例
 * </p>
 * <p>
 * create: 2011-2-16 下午10:10:08
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YchTestCase {

	/**
	 * 
	 */
	private static IYchBO ychBO;

	private static IZpBO zpBO;

	/**
	 * 
	 */
	private static JdbcTemplate jt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ychBO = Helper.getBean(IYchBO.class);
		jt = (JdbcTemplate) Helper.getBean("jdbcTemplate");
		zpBO = Helper.getBean(IZpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void queryZp() {
		// current2 -current1===========15109
		long current1 = System.currentTimeMillis();
		ZpQE page = new ZpQE();
		// ZppdQE page = new ZppdQE();
		page.setSize(100);
		// 判断数据库存的制品，而实物不存在
		String[] xpzks = { EXpzk.JZP.key, EXpzk.BZP.key };
		// 设置现品在库
		page.setXpzks(xpzks);
		// 设置堆场
		String[] duics = { DC.C.name, DC.F.name, DC.G.name };
		page.setDuics(duics);
		// 分配/余材标记
		page.setFpyc(EFpyc.FP.key);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		page.setStat(ZpStat.CS.stat);
		page.setOrderBys("jbno");
		zpBO.query(page);
		// zpBO.queryZppd(page);
		System.out.println("pagesize=1");
		for (ZpngTp zpngTp : page.getDatas()) {
			System.out.println(zpngTp.getJbno() + "," + zpngTp.getKw());
		}
		int pageCount = page.getPageCount();
		for (int index = 1; index < pageCount; index++) {
			System.out.println("pagesize=" + (index + 1));
			page.setIndex(index);
			zpBO.query(page);
			for (ZpngTp zpngTp : page.getDatas()) {
				System.out.println(zpngTp.getJbno() + "," + zpngTp.getKw());
			}
		}

		// for (ZppdVO zpngTp : page.getDatas()) {
		// System.out.println("jbno=" + zpngTp.getJbno());
		// }
		// int pageCount = page.getPageCount();
		// for (int index = 1; index < pageCount; index++) {
		// System.out.println("pagesize=" + (index + 1));
		// page.setIndex(index);
		// zpBO.queryZppd(page);
		// for (ZppdVO zpngTp : page.getDatas()) {
		// System.out.println("jbno=" + zpngTp.getJbno());
		// }
		// }
		long current2 = System.currentTimeMillis();
		System.out.println("current2 -current1==========="
				+ (current2 - current1));
		// System.out.println("pageCount===========" + page.getPageCount());
	}

	/**
	 * <p>
	 * 保存制口余材化处理操作。一次余材操作的制品必须属于同一个订货合同
	 * </p>
	 * <ul>
	 * <li>验证要做余材的制品是否制作装箱指示书。如果已做装箱指示书，则不能做余材操作。
	 * <li>验证要做余材的制品是否为成品（卷成品或板成品）。注：堆场是否在C、F或G，现品在库是否为制品，分配/余材标记是否为分配品，
	 * 删除标识是否为初始和状态是否为初始
	 * <li>修改对应制品，在制品在库DB表中的信息。
	 * <li>修改制品所属订货合同的指示量和合格量（ELT和SL）。
	 * <li>写分配操作记录表。
	 * <li>删除指示对象DB表中的记录（等待确定）
	 * </ul>
	 * 
	 * @param jbnos
	 * @param user
	 * @return String
	 */
	@Test
	public void doYch() {
		String[] jbnos = { "00006001001", "00006001001", "00006001004" };
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		ychBO.doZpYch(jbnos, user);
	}

	/**
	 * <p>
	 * 生成制品测试数据
	 * </p>
	 */
	@Test
	public void parseZp() {
		JdbcTemplate jt = (JdbcTemplate) Helper.getBean("jdbcTemplate");

		List<String> sql = new ArrayList<String>();
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629401', 'H/ZM.C.P', 0, '', 0, 0, '', NULL, 0, 0, '', 40, 0.98, 0, 0, 950, '1', '', '0000072020', NULL, NULL, 0, 0, 0, 0, 0, 0, '', '20110113 13:51:15.913', '+', 0, 0, '+', 0, '', 'GAA', 'D10721905', '', NULL, '', 70, NULL, 'C', '1', NULL, 'R2', '', '', '', NULL, '', '1', 'WB', '025', '025', 'MB30', 'M', '', '', '', 0, 0, 0.23, 951, 0.23, '+', 7, '311', '-', 7, 4335, '100710D2531', '', 0, '1', '1', '', '', '', '', '00', '1', 0, NULL, 7299, 4403, '1', '12', 7448, 'P11', NULL, 7.5, 1.875, 'HZMCP', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 951, '+', 0, 0, 50, 'B5', NULL, NULL, 7299, 1.718, '+', 0.016, '', '-', 0.016, '', '3', '1', 0, '', NULL, NULL, '', '', '', '', '', 'CHE007R', '', NULL, NULL, '21', '1', '', 0, '', 'B5', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.76, 0, 2.83, '0', 'CHANG.Q.14', '0000072020', NULL, 7299, NULL, NULL, '1', 'ZE0', '0', '2', NULL, 'Z002', '', 0, 0.23, 0, 0, 951, 0, 0, 'C', '0', 'SPB-MR-T3BA', 'A2Z0103152', 586, '', '', '', '', NULL, 'D', 'T3BA', '', '', '', 0, 'N', NULL, '', '', 0, '', 0, 1200, 0, 0, 'N39267', 0, 0, 0, NULL, '', '', '', '', '2', NULL, '2', '832891', 'C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629402', 'H/ZM.C.P', 0, '', 0, 0, '', NULL, 0, 0, '', 40, 0.98, 0, 0, 950, '1', '', '0000072020', NULL, NULL, 0, 0, 0, 0, 0, 0, '', '20110113 13:51:15.913', '+', 0, 0, '+', 0, '', 'GAA', 'D10721905', '', NULL, '', 70, NULL, 'C', '1', NULL, 'R2', '', '', '', NULL, '', '1', 'WB', '025', '025', 'MB30', 'M', '', '', '', 0, 0, 0.23, 951, 0.23, '+', 7, '311', '-', 7, 4296, '100710D2531', '', 0, '1', '1', '', '', '', '', '00', '1', 0, NULL, 7233, 4349, '2', '12', 7381, 'P11', NULL, 7.5, 1.875, 'HZMCP', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 951, '+', 0, 0, 35, 'B8', NULL, NULL, 7233, 1.718, '+', 0.016, '', '-', 0.016, '', '3', '1', 0, '', NULL, NULL, '', '', '', '', '', 'CHE007R', '', NULL, NULL, '21', '1', '', 0, '', 'B5', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.76, 0, 2.83, '0', 'CHANG.Q.14', '0000072020', NULL, 7233, NULL, NULL, '1', 'ZE0', '0', '2', NULL, 'Z002', '', 0, 0.23, 0, 0, 951, 0, 0, 'C', '0', 'SPB-MR-T3BA', 'A2Z0103152', 586, '', '', '', '', NULL, 'D', 'T3BA', '', '', '', 0, 'N', NULL, '', '', 0, '', 0, 1200, 0, 0, 'N39267', 0, 0, 0, NULL, '', '', '', '', '2', NULL, '2', '832891', 'C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629501', 'H/ZM.C.P', 0, '', 0, 0, '', NULL, 0, 0, '', 40, 0.98, 0, 0, 950, '1', '', '0000072020', NULL, NULL, 0, 0, 0, 0, 0, 0, '', '20110113 13:51:15.913', '+', 0, 0, '+', 0, '', 'GAA', 'D10721905', '', NULL, '', 70, NULL, 'C', '1', NULL, 'R2', '', '', '', NULL, '', '1', 'WB', '025', '025', 'MB30', 'M', '', '', '', 0, 0, 0.23, 951, 0.23, '+', 7, '311', '-', 7, 4582, '100710D2531', '', 0, '1', '1', '', '', '', '', '00', '1', 0, NULL, 7714, 4621, '1', '12', 7872, 'P11', NULL, 7.5, 1.875, 'HZMCP', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 951, '+', 0, 0, 20, 'N5', NULL, NULL, 7714, 1.718, '+', 0.016, '', '-', 0.016, '', '3', '1', 0, '', NULL, NULL, '', '', '', '', '', 'CHE007R', '', NULL, NULL, '21', '1', '', 0, '', 'B5', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.78, 0, 2.83, '0', 'CHANG.Q.14', '0000072020', NULL, 7714, NULL, NULL, '1', 'ZE0', '0', '2', NULL, 'Z002', '', 0, 0.23, 0, 0, 951, 0, 0, 'C', '0', 'SPB-MR-T3BA', 'A2Z0103152', 596, '', '', '', '', NULL, 'D', 'T3BA', '', '', '', 0, 'N', NULL, '', '', 0, '', 0, 1200, 0, 0, 'N39267', 0, 0, 0, NULL, '', '', '', '', '2', NULL, '2', '84098', 'C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL)");
		jt.batchUpdate(sql.toArray(new String[0]));
	}

	/**
	 * <p>
	 * 清空删除的制品测试数据
	 * </p>
	 */
	@Test
	public void delZp() {
		String[] sql = { "delete from SINO_ZPNGTP where JBNO_ in ('00629401', '00629402', '00629501')" };
		jt.batchUpdate(sql);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
