package com.quanta.sino.zkfp;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.zkfp.bo.api.IFpBO;
import com.quanta.sino.zkfp.vo.CError;
import com.quanta.sino.zkfp.vo.FpVO;
import com.quanta.sino.zkfp.vo.PError;

/**
 * <p>
 * 在库分配测试用例
 * </p>
 * <p>
 * create: 2011-2-16 下午10:29:44
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class FpTestCase {

	/**
	 * 在库分配业务接口
	 */
	private static IFpBO fpBO;

	/**
	 * 
	 */
	private static IDhBO dhBO;

	/**
	 * 
	 */
	private static IZpBO zpBO;

	/**
	 * 
	 */
	private static JdbcTemplate jt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fpBO = Helper.getBean(IFpBO.class);
		dhBO = Helper.getBean(IDhBO.class);
		zpBO = Helper.getBean(IZpBO.class);
		jt = (JdbcTemplate) Helper.getBean("jdbcTemplate");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 生成订货合同数据
	 * </p>
	 */
	@Test
	public void outDhht() {
		String[] sql = { "insert into SINO_DHUOTP (DHNO_, LINE_, ABBR_, BZ1_, BZ2_, BZ3_, BZCL_, CANG_, CHDX_, CHKN_, CHUJ_, CHUS_, CHUZ_, COND_, CREA_, CSFU_, CSZI_, CXFU_, CXZI_, DDNM_, DDNO_, DHQR_, DMFX_, DMZL_, ETLH_, ETLZ_, FACE_, FLAG_, FPDJ_, FPJL_, FPLN_, FPQF_, FUDW_, FUFM_, FUFS_, FUFX_, FUZM_, FUZS_, FUZX_, GGNM_, GGNO_, HBBJ_, HBWL_, HFKN_, HJIN_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HTDJ_, HTJE_, HTZL_, HUAC_, HXFU_, HXZI_, JCXS_, JHFZ_, JHNR_, JHQF_, JHQI_, JHZZ_, JIAN_, JIAO_, JSDT_, JSNM_, JSNO_, JSST_, JSTJ_, KBAO_, KBJS_, KBSQ_, KBSZ_, KBUS_, KBZD_, KBZQ_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LKDT_, LKNM_, LKNO_, LTDW_, LTZI_, MSGN_, NAME_, NEIJ_, NJBH_, NWAI_, PLAT_, PROG_, PROT_, PZNO_, QIXN_, RJIE_, RSV1_, RSV2_, SHNM_, SHNO_, SLHG_, SLZS_, SSNM_, SSNO_, STAT_, SYDT_, SYNM_, SYNO_, TBNO_, TCNO_, THQF_, THUS_, UPDA_, USER_, XGNM_, XGNO_, YFEI_, YFKN_, YHBQ_, YMAX_, YMIN_, YQTY_, YTYP_, YUNY_, YYAN_, ZRKN_, ZZQR_) values ('D201102', '03', 'KUISHI', NULL, NULL, NULL, NULL, 14.23, 'A2', NULL, NULL, NULL, NULL, 'QAAA', '20110218 11:12:59.063', NULL, NULL, NULL, NULL, '管理员', '000', NULL, 'L', NULL, NULL, NULL, 'R1', NULL, 'GAA', NULL, NULL, NULL, 'GM', '022', 4, 1.8, '011', 1.6, 0.9, 'SPTE-D-T1CA', 'DC10', '1', NULL, NULL, NULL, 14.23, 0.155, 925.5, 0.155, NULL, NULL, NULL, NULL, 100.5, '311', NULL, NULL, NULL, 1, '1', 'W', '20110228', 99, 'N', 0.6, NULL, NULL, NULL, NULL, '0', 'A31', '1', '9', '1000', NULL, 'T', '5', 25, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 925.5, NULL, NULL, NULL, NULL, NULL, 'N', NULL, NULL, 'DALIAN', NULL, NULL, '1', 'K', NULL, NULL, '11', NULL, '1', NULL, NULL, '中国海南海口玉沙路', NULL, NULL, NULL, 'NKKTRADING(BJ)LIMITED', 'NAB', '2', '20110218 11:23:17.267', '管理员', '000', '1', '1', NULL, NULL, NULL, '1002', NULL, NULL, NULL, NULL, '1', '52', '46', '40', 'D', 'T1CA', '1', 925.5, NULL)" };
		jt.batchUpdate(sql);
	}

	/**
	 * <p>
	 * 删除订货合同数据
	 * </p>
	 */
	@Test
	public void delDhht() {
		String[] sql = { "delete from SINO_DHUOTP where DHNO_='D201102' and LINE_='03'" };
		jt.batchUpdate(sql);

	}

	/**
	 * <p>
	 * 生成素材数据
	 * </p>
	 */
	@Test
	public void outSc() {
		List<String> sql = new ArrayList<String>();
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001015', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986246', 'KY6044', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001016', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986247', 'KY6045', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001017', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986248', 'KY6046', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001018', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986249', 'KY6047', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001019', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986250', 'KY6048', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001020', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986251', 'KY6049', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001021', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986252', 'KY6050', '1', 10230)");
		sql.add("insert into SINO_YCAITP (JBNO_, ABBR_, BANZ_, BLNY_, CANG_, CGDJ_, CHAN_, CREA_, CZDM_, DENG_, DHNO_, DUIB_, DUIC_, ELIN_, FACE_, FLAG_, FPNO_, FPYC_, GGNM_, GGNO_, GZLX_, HNGC_, HNGH_, HNGK_, HOUU_, JBCN_, JDES_, JDEZ_, JDYN_, JNZL_, JSYN_, JSZL_, KUAN_, KW_, LINE_, MAZL_, MDAN_, NJNO_, PROG_, PZNO_, QDBJ_, QQDM_, RCZPNO_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCSJ_, SCZT_, SHIP_, SJSJ_, SJZL_, SPBH_, SSNO_, STAT_, THQF_, UPDA_, USER_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, YBLC_, YBNO_, YCBR_, YLNO_, YSUO_, YUNY_, YYAN_, ZPZL_, ZSNO_, ZTBJ_, ZZNY_, ZZSD_, ZZSJ_, ZZZP_, SFBS_, ZRZL_) VALUES ('001022', NULL, NULL, '20110218', NULL, 860.13, NULL, '20110218 12:07:27.250', NULL, 'PBB', NULL, '20110218 14:41:10.267', 'A', NULL, 'R1', NULL, NULL, '0', 'SPTE-D-T1CA', 'DC10', 'D', NULL, NULL, NULL, NULL, 9084, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'AB01', '006', NULL, NULL, '1', NULL, '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, '201202', NULL, NULL, '1', 'DE0', '1', '1', NULL, NULL, 0.155, NULL, NULL, 925.5, NULL, NULL, 'A', 'SPB-D-T1CA', '0E76903', '锦州中策', NULL, NULL, 'T1CA', NULL, 10230, NULL, NULL, '20110228', '2', '73986253', 'KY6051', '1', 10230)");
		jt.batchUpdate(sql.toArray(new String[0]));
	}

	/**
	 * <p>
	 * 删除素材数据
	 * </p>
	 */
	@Test
	public void delSc() {
		String[] sql = { "delete from SINO_YCAITP where JBNO_ in ('001015', '001016', '001017', '001018', '001019','001020', '001021', '001022')" };
		jt.batchUpdate(sql);

	}

	/**
	 * <p>
	 * 分配素板。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的素材对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断素材是否可以用来做分配操作。
	 * <li>判断素材与订货合同的信息是否配匹。
	 * <li>设置素材的分配信息。
	 * <li>根据素材信息，生成指示对象。
	 * <li>根据素材信息，生成分配操作记录对象。
	 * <li>如果素材信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>修改订货合同的分配量。
	 * </ul>
	 */
	@Test
	public void fpSc() {
		//
		FpVO vo = new FpVO();
		vo.setDhno("D201102");
		vo.setLine("03");
		vo.setXpzk(EXpzk.SC.key);
		vo.setXpnos("001015, 001016, 001017");
		vo.setCqzs(10.23 * 3);
		// 剪边
		vo.setJbkb(null);
		// 再选
		vo.setZxbb(null);
		// 允许超出
		vo.setClfp(null);
		// 强制
		vo.setQzbj(null);
		// 分配操作人
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String result = fpBO.doFp(vo, user);
		System.out.println(result);
	}

	/**
	 * <p>
	 * 分配中间品
	 * </p>
	 */
	@Test
	public void fpZjp() {
		//
		FpVO vo = new FpVO();
		vo.setDhno("YY20112");
		vo.setLine("19");
		vo.setXpzk(EXpzk.ZJP_KEY);
		vo.setXpnos("00040109,00040201,0004051");
		vo.setCqzs(10.006);
		vo.setWfpl(100.5);
		// 剪边
		vo.setJbkb(null);
		// 再选
		vo.setZxbb(null);
		// 允许超出
		vo.setClfp(null);
		// 强制
		vo.setQzbj("1");
		// 分配操作人
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String result = fpBO.doFp(vo, user);
		System.out.println(result);

	}

	/**
	 * <p>
	 * 取消素材分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的素材对象。
	 * <li>修改素材信息。
	 * <li>为取消分配素材，生成分配操作记录对象
	 * <li>修改订货合同的分配量
	 * <li>删除取消分配的指示对象
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 */
	@Test
	public void fpqxSc() {
		// 先做分配
		FpVO vo = new FpVO();
		vo.setDhno("D201102");
		vo.setLine("03");
		vo.setXpzk(EXpzk.SC.key);
		vo.setXpnos("001018, 001019, 001020");
		vo.setCqzs(10.23 * 3);
		// 分配操作人
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String result = fpBO.doFp(vo, user);
		int index;
		int code = 0;
		String msg = null;
		if ("<!--".equals(result.substring(0, 4))
				&& (index = result.indexOf("-->")) != -1) {
			String msgs = result.substring(4, index - 4);
			int msgIndex = msgs.indexOf(":");
			code = msgIndex != -1 ? Integer.parseInt(msgs.substring(0, msgIndex))
					: 0;
			msg = msgIndex != -1 ? msgs.substring(msgIndex + 1) : "";
		}
		if (code <= 0) {
			System.out.println("分配操作失败");
			return;
		}
		// 取消分配
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("001018");
		jbnos.add("001019");
		jbnos.add("001020");
		result = fpBO.doFpqx(jbnos, msg, "D201102", "03", EXpzk.SC.key, user);
		System.out.println(result);
	}

	/**
	 * <p>
	 * 生成板制品数据
	 * </p>
	 */
	@Test
	public void outBzp() {
		List<String> sql = new ArrayList<String>();
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601019', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, NULL, 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', '11', '6', 0.6, NULL, 800, 9516, '2', '11', 800, 'A21', NULL, 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 10:42:56.877', NULL, '11', '1', '', 0, '', '11', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '1', 'CHANG.Q.14', '0000072073', NULL, 700, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, 0, 'N00001', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'FI04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601020', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, NULL, 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', '11', '6', 0.6, NULL, 700, 9516, '2', '11', 700, 'A21', NULL, 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 10:46:07.207', NULL, '11', '1', '', 0, '', '11', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '0', 'CHANG.Q.14', '0000072073', NULL, 100, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, 0, 'N00001', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'FI04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601021', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, '20110130 17:10:53.983', 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', '11', '6', 0.6, NULL, 600, 9516, '2', '11', 600, 'A21', '20110128 15:49:06.563', 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 10:48:29.167', NULL, '11', '1', '', 0, '', '11', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '0', 'CHANG.Q.14', '0000072073', NULL, 500, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, 0, 'N00001', NULL, NULL, NULL, '1', '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'F', '20110128 15:49:06.563', NULL, NULL, NULL, NULL, NULL, NULL, '2', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601022', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, NULL, 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', '11', '6', 0.6, NULL, 300, 9516, '2', '11', 300, 'A21', NULL, 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 10:54:13.747', NULL, '11', '1', '', 0, '', '11', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '0', 'CHANG.Q.14', '0000072073', NULL, 123, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, -100, 'N00001', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'FI04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601023', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, NULL, 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', 'AA', '6', 0.6, NULL, 600, 9516, '2', 'AA', 600, 'A21', NULL, 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 11:06:32.453', NULL, '11', '1', '', 0, '', '1', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '0', 'CHANG.Q.14', '0000072073', NULL, 520, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, 300, 'N00001', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'FI04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)");
		sql.add("insert into SINO_ZPNGTP (JBNO_, ABBR_, BDAN_, BDJI_, BDRG_, BDRJ_, BHBL_, BLNY_, BOPG_, BOPJ_, BQHT_, BZCL_, BZXS_, BZZS_, CANG_, CGDJ_, CHAN_, CHDX_, CHNO_, CHSD_, CHZS_, CKNO_, CM05_, CP00_, CP05_, CP10_, CP15_, CQPL_, CREA_, CSFU_, CSZI_, CUTC_, CXFU_, CXZI_, CZDM_, DENG_, DHNO_, DMFX_, DMQR_, DMRK_, DMZL_, DUIB_, DUIC_, ELIN_, ETSD_, FACE_, FANG_, FLAG_, FMBY_, FMSD_, FPNO_, FPYC_, FUDW_, FUFM_, FUZM_, GGNO_, GZLX_, HMRK_, HNDD_, HNDF_, HNDZ_, HNGC_, HNGH_, HNGK_, HOUU_, HSFU_, HSZI_, HXCL_, HXFU_, HXZI_, JBCN_, JCNO_, JCXS_, JDCN_, JDES_, JDEZ_, JDJS_, JDJZ_, JDSS_, JDSZ_, JDYN_, JHNR_, JIAO_, JMJC_, JNZL_, JSCN_, JSNO_, JSYN_, JSZL_, KBAO_, KBSD_, KBZS_, KBZX_, KPN1_, KPN1_FLAG_, KPN2_, KPN2_FLAG_, KPN3_, KPN3_FLAG_, KPN4_, KPN4_FLAG_, KSFU_, KSZI_, KUAN_, KXFU_, KXZI_, LNSD_, LOSC_, LOSQ_, MAOS_, MAOX_, MAZL_, MDAN_, MSFU_, MSZI_, MUDI_, MXFU_, MXZI_, NJBH_, NJNO_, NWAI_, PHGS_, PLAT_, PLIN2_, PLIN3_, PLN1_, PLQ1_, PLQ2_, PLQ3_, PLQF_, PROG_, PROT_, PSSD_, PXSD_, PZNO_, QDBJ_, QDUF_, QDUZ_, QMRK_, QQDM_, QXD1_, QXD2_, QXD3_, QXD4_, QXD5_, QXD6_, QXN1_, QXN2_, QXN3_, QXN4_, QXN5_, QXN6_, QXZ1_, QXZ2_, QXZ3_, QXZ4_, QXZ5_, QXZ6_, QZCH_, RJET_, RJLY_, RJSX_, RSV1_, RSV2_, SCBJ_, SCFM_, SCKN_, SCZM_, SFJJ_, SHIP_, SHNO_, SIQL_, SJZL_, SLIN_, SLSD_, SPDJ_, SSNO_, STAT_, THQF_, UPDA_, USER_, VARI_, XPCN_, XPHO_, XPHS_, XPHX_, XPKN_, XPKS_, XPKX_, XPZK_, XSWK_, YBLC_, YBNO_, YING_, YLL1_, YLL2_, YLN1_, YLN2_, YSUO_, YTYP_, YUNY_, YYAN_, ZBWD_, ZDRF_, ZDRZ_, ZJBB_, ZKQR_, ZNDD_, ZNDF_, ZNDZ_, ZOPF_, ZOPZ_, ZPZL_, ZRKN_, ZSHU_, ZSNO_, ZSU1_, ZSU2_, ZSU3_, ZTBJ_, ZYZE_, ZZN1_, ZZN2_, ZZN3_, ZZNO_, ZZNY_, ZZSD_, ZZSJ_, KW_, BJDY_, RCZPNO_, YLNO_, DDNO_, ZBOG_, ZBOJ_, CPNO_, BAN_, ZU_, DBBJ_, QQD2_, QQD3_, QXD7_, QXD8_, QXN7_, QXN8_, QXZ7_, QXZ8_, LLYD_, HBBJ_, BANZ_, SJSJ_, SFBS_, SPBH_, ZRZL_) VALUES ('00629601024', 'FCP HARB', 0.877, 'A', 8, 135, '', NULL, 8, 155, '', 18, 0, 1300, 632, 950, '1', '', '0000072073', NULL, NULL, 0, 0, 0, 5, 0, 0, '4', '20110113 13:51:15.913', '+', 1.5, 20, '+', 0, '', 'GBA', 'D10414305', 'C', NULL, '', 12, NULL, 'F', '1', NULL, 'R2', '', '', '1', NULL, 'B27354', '1', 'WB', '025', '025', 'MC30', 'M', '', 'A', '', 0, 632, 0.23, 768, 0.23, '+', 7, '311', '-', 7, 0, '100713D1622', '', 632.51, '1', '1', '0', '0', '1', '1', '11', '6', 0.6, NULL, 1000, 9516, '2', '11', 1000, 'A21', NULL, 0, 0, '', NULL, '', NULL, NULL, NULL, NULL, NULL, '+', 1, 768, '+', 0, 96, 0, '', NULL, NULL, 1140, 1.387, '+', 0.016, '', '-', 0.016, '', '', '1', 0, '', NULL, NULL, NULL, NULL, NULL, NULL, 'T', 'CHE007R', 'W', '20110128 11:10:27.167', NULL, '11', '1', '', 0, '', '11', '', '', '', '', '', '', '', '', '', '', '', '', 0, 0, 0, 0, 0, 0, '', 0, 0, 0, '', 0, '0', 2.79, 768.74, 2.76, '0', 'CHANG.Q.14', '0000072073', NULL, 800, 'B', NULL, '1', 'ZE0', '0', '2', NULL, 'H002', 'H1;00/000', 632, 0.23, 0, 0, 768, 0, 0, 'C', '', 'SPB-MR-T3CA', 'A2Z0103161', 562, NULL, NULL, NULL, NULL, NULL, 'D', 'T3CA', '1', 'A', '+', 15, '', NULL, 'A', '-', 5, '+', 7, 1200, 0, 1000, 'N00001', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2', NULL, '2', '85257', 'FI04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', 'B', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)");
		jt.batchUpdate(sql.toArray(new String[0]));
	}

	/**
	 * <p>
	 * 删除板制品数据
	 * </p>
	 */
	@Test
	public void delZp() {
		String[] sql = { "delete from SINO_ZPNGTP where JBNO_ in ('00629601019',' 00629601020',' 00629601021','00629601022',' 00629601023',' 00629601024')" };
		jt.batchUpdate(sql);
	}

	/**
	 * <p>
	 * 分配剪切板制品。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做分配操作的制品对象。
	 * <li>生成分配no。分配no由两部分组成，前缀是现品状况，后部分是流水号。
	 * <li>判断制品是否可以用来做分配操作。
	 * <li>判断制品与订货合同的信息是否配匹。
	 * <li>设置制品的分配信息。
	 * <li>根据制品信息，生成指示对象。
	 * <li>如果是钢卷成品分配给剪切板的合同，则要生成指示对象。
	 * <li>根据制品信息，生成分配操作记录对象。
	 * <li>如果制品信息与订货合同信息不配匹。则要生成分配错误操作记录对象。
	 * <li>对剪切剪切板制品做分配操作是，要修改订货合同的SL指示量和SL分配量。
	 * </ul>
	 */
	@Test
	public void fpBzp() {
		//
		FpVO vo = new FpVO();
		vo.setDhno("D201102");
		vo.setLine("03");
		vo.setXpzk(EXpzk.BZP.key);
		vo.setXpnos("00629601019,00629601020,00629601021");
		vo.setCqzs(1.2 * 3);
		// 剪边
		vo.setJbkb(null);
		// 再选
		vo.setZxbb(null);
		// 允许超出
		vo.setClfp(null);
		// 强制
		vo.setQzbj(null);
		// 分配操作人
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String result = fpBO.doFp(vo, user);
		System.out.println(result);
	}

	/**
	 * <p>
	 * 取消剪切板制品分配。操作如下：
	 * </p>
	 * <ul>
	 * <li>根据页面传入的现品no，查询要做取消分配操作的剪切板制品对象。
	 * <li>修改剪切板制品信息。
	 * <li>为取消分配剪切板制品，生成分配操作记录对象
	 * <li>修改订货合同的SL指示量和SL合格量
	 * <li>删除取消分配的指示对象
	 * <li>修改指示对象的分配量和修改日期
	 * </ul>
	 */
	@Test
	public void fpqxBzp() {
		// 先做分配
		FpVO vo = new FpVO();
		vo.setDhno("D201102");
		vo.setLine("03");
		vo.setXpzk(EXpzk.BZP.key);
		vo.setXpnos("00629601022,00629601023,00629601024");
		vo.setCqzs(1.2 * 3);
		// 分配操作人
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String result = fpBO.doFp(vo, user);
		int index;
		int code = 0;
		String msg = null;
		if ("<!--".equals(result.substring(0, 4))
				&& (index = result.indexOf("-->")) != -1) {
			String msgs = result.substring(4, index - 4);
			int msgIndex = msgs.indexOf(":");
			code = msgIndex != -1 ? Integer.parseInt(msgs.substring(0, msgIndex))
					: 0;
			msg = msgIndex != -1 ? msgs.substring(msgIndex + 1) : "";
		}
		if (code <= 0) {
			System.out.println("分配操作失败");
			return;
		}
		// 取消分配
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("00629601022");
		jbnos.add("00629601023");
		jbnos.add("00629601024");
		result = fpBO.doFpqx(jbnos, msg, "D201102", "03", EXpzk.BZP.key, user);
		System.out.println(result);
	}

	/**
	 * <p>
	 * 现品与订货信息进行比较
	 * </p>
	 */
	@Test
	public void match() {
		String dhno = "D201202";
		String line = "01";
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno, line));
		String jbno = "00049104";
		String xpzk = EXpzk.JZP.key;
		ZpngTp zpngTp = zpBO.getZp(jbno);
		YcaiTp ycaiTp = null;
		List<PError> errors = fpBO.findCompareErrors(ycaiTp, zpngTp, dhuoTp, xpzk);
		if (errors.size() > 0) {
			for (PError error : errors) {
				for (CError cerror : error.getErrors()) {
					System.out.println(cerror.getName());
				}
			}
		}
	}
}
