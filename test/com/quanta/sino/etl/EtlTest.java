package com.quanta.sino.etl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.bo.api.IBanBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.NoGenType;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IEtlBO;
import com.quanta.sino.etl.bo.api.IRcBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.dao.api.IEtlDAO;
import com.quanta.sino.etl.dao.api.IRcrzDAO;
import com.quanta.sino.etl.dao.api.IZpDAO;
import com.quanta.sino.etl.vo.EtlBanTjVO;
import com.quanta.sino.etl.vo.EtlSydjVO;
import com.quanta.sino.etl.vo.SjQE;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.etl.vo.XzVO;
import com.quanta.sino.etl.vo.YxRecordVO;
import com.quanta.sino.orm.Rcrz;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.vo.DscListQE;
import com.quanta.sino.zs.vo.FpListQE;
import com.quanta.sino.zs.vo.ZsListQE;

/**
 * <p>
 * 制品单元测试用例
 * </p>
 * <p>
 * create: 2011-1-18 上午09:01:14
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class EtlTest {

	private static IZpDAO zpDao;

	private static IEtlDAO sjDAO;

	private static IZsBO zsBO;
	private static JdbcTemplate jdbc;
	private static ICmnBO cmnBO;
	private static IBanBO banBO;
	private static IEtlBO etlBO;
	private static IYcaitpBO ycaiBO;
	private static IRcrzDAO rcDAO;

	private static IRcBO rcBo;
	private static IZpBO zpBO;

	// private static ICodeBO codeBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		zpDao = Helper.getBean(IZpDAO.class);
		sjDAO = Helper.getBean(IEtlDAO.class);
		zsBO = Helper.getBean(IZsBO.class);
		jdbc = Helper.getBean(JdbcTemplate.class);
		cmnBO = Helper.getBean(ICmnBO.class);
		etlBO = Helper.getBean(IEtlBO.class);
		ycaiBO = Helper.getBean(IYcaitpBO.class);
		rcDAO = Helper.getBean(IRcrzDAO.class);
		banBO = Helper.getBean(IBanBO.class);
		rcBo = Helper.getBean(IRcBO.class);
		zpBO = Helper.getBean(IZpBO.class);
		// codeBO = Helper.getBean(ICodeBO.class);
	}

	@Test
	public void updateZpForSlZss() {
		String zsno = "N44097";
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("10345701");
		jbnos.add("10345603");
		jbnos.add("10345602");
		zpBO.updateZpForSlZss(zsno, jbnos);
	}

	@Test
	public void findBanTjVO() {
		Date scsjBegin = DateUtils.parse("2011-12-14 8:00:00", DateUtils.YMD_HMS);
		Date scsjEnd = DateUtils.parse("2011-12-15 8:00:00", DateUtils.YMD_HMS);
		Map<String, EtlBanTjVO> maps = sjDAO.findBanTjVO(scsjBegin, scsjEnd);
		Iterator<String> iter = maps.keySet().iterator();
		EtlBanTjVO vo = null;
		int i = 0;
		while (iter.hasNext()) {
			System.out.println("i===" + i++);
			vo = maps.get(iter.next());
			System.out.println("zu===" + vo.getZu());
			System.out.println(vo.getZpzl());
			System.out.println(vo.getZrzlAll());
			System.out.println(vo.getZrzlS());
			System.out.println(vo.getZrzlOR());
			System.out.println(vo.getZrzlC());
			System.out.println(vo.getHgzpzlS());
			System.out.println(vo.getHgzpzlC());
			System.out.println(vo.getHgblS());
			System.out.println(vo.getHgblC());
			System.out.println(vo.getFczpzlS());
			System.out.println(vo.getFczpzlC());
			System.out.println(vo.getFszpzlS());
			System.out.println(vo.getFszpzlC());

		}

	}

	@Test
	public void getXzVO() {
		Date begin = DateUtils.parse("2011-12-03", DateUtils.YMD);
		Date end = DateUtils.parse("2011-12-13", DateUtils.YMD);
		XzVO xzVO = sjDAO.getXzVO(begin, end);
		System.out.println("xzda=============" + xzVO.getXzda());
		System.out.println("xzdj=============" + xzVO.getXzdj());
		System.out.println("xzjx=============" + xzVO.getXzjx());
		System.out.println("xzzy=============" + xzVO.getXzzy());

	}

	@Test
	public void getYxRecord() {
		Date systime = DateUtils.parse("2011-12-16 08:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtils.format(systime, "yyyy-MM-dd HH:mm:ss"));
		YxRecordVO vo = rcDAO.getYxRecord(systime);
		System.out.println("id===" + vo.getVarId());

	}

	@Test
	public void queryYxRecords() {
		Date begin = DateUtils.formatDate(DateUtils.parse("2011-12-16", "yyyy-MM-dd"), 8, 0);
		Date end = DateUtils.formatDate(DateUtils.parse("2011-12-16", "yyyy-MM-dd"), 16, 0);

		System.out.println(DateUtils.format(begin, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtils.format(end, "yyyy-MM-dd HH:mm:ss"));
		List<YxRecordVO> vos = rcDAO.queryYxRecords(begin, end);
		if (vos.size() > 0) {
			for (YxRecordVO vo : vos) {
				System.out.println("id========" + vo.getVarId());
			}
		}
	}

	@Test
	public void testBan() {
		Boolean bl = banBO.checkBan("3");
		System.out.println(bl);
	}

	/**
	 * <p>
	 * 从旧数据库导入SINO_ETLSLP表。
	 * </p>
	 * 
	 * @author 张红国
	 */
	@Test
	public void ImportETLSLP() {
		String sql;
		UUID uuid;
		StringBuilder bmZsfmt = new StringBuilder();
		String zsfmtValues = "";
		bmZsfmt.append("insert SINO_ZSFMT(ID_ ,ABB_ ,BBZ_ ,BDA_ ,BHB_ ,BZC_ ,BZX_ ,BZZ_ ,CAN_ ,CAO_ ,CON_ ,CQZ_ ,CSZ_ \r\n");
		bmZsfmt.append(",CXZ_ ,DHF_ ,DHG_ ,DHN_ ,DHQ_ ,DMF_ ,DMZ_  ,FAC_ ,FFS_ ,FFX_ ,FGM_ ,FLO_ \r\n");
		bmZsfmt.append(",FPD_ ,FZS_ ,FZX_ ,GGN_ ,GZL_ ,HDQ_ ,HJI_ ,HOU_ ,HSC_ ,HSH_ ,HSK_ ,HSZ_ ,HXC_ \r\n");
		bmZsfmt.append(",HXZ_ ,JBB_ ,JBC_ ,JBK_ ,JCK_ ,JCZ_ ,JDC_ ,JHD_ ,JHQ_ ,JIA_ ,KBA_ ,KBS_ ,KBX_ \r\n");
		bmZsfmt.append(",KL1_ ,KL2_  ,KN1_ ,KN2_  ,KSZ_ ,KUA_ ,KXZ_ ,LTK_ ,LTX_ \r\n");
		bmZsfmt.append(",MDA_ ,MSZ_ ,MXZ_ ,NAM_ ,NJB_ ,NJN_ ,PIZ_ ,PLQ_ ,PRO_ ,PZN_ ,QGC_ ,RJL_ ,RJS_ \r\n");
		bmZsfmt.append(",SPE_ ,XPC_ ,XPH_ ,XPK_ ,XSH_ ,XSW_ ,YLNO_ ,YQT_ ,YTY_ ,YUN_ ,YYA_ ,Z112_ ,ZPC_ \r\n");
		bmZsfmt.append(",ZPZ_ ,ZRJ_ ,ZRK_ ,ZSN_ ,ZZN_)values( \r\n");
		// 第一次导入，删除以下表。;
		sql = "select * from SINO_ETLSLP_OLD";
		List<Map<String, Object>> datas = jdbc.queryForList(sql);
		for (Map<String, Object> map : datas) {
			// 插入表SINO_ZSFMT
			uuid = UUID.randomUUID();
			zsfmtValues = "'" + uuid.toString().replace("-", "") + "','"
					+ map.get("ABB_") + "'," + map.get("BBZ_") + ","
					+ map.get("BDA_") + ",'" + map.get("BHB_") + "',"
					+ map.get("BZC_") + "," + map.get("BZX_") + ","
					+ map.get("BZZ_") + "," + map.get("CAN_") + ",'"
					+ map.get("CAO_") + "','" + map.get("CON_") + "',"
					+ map.get("CQZ_") + "," + map.get("CSZ_") + ","
					+ map.get("CXZ_") + ",'" + map.get("DHF_") + "','"
					+ map.get("DHG_") + "','" + map.get("DHN_") + "','"
					+ map.get("DHQ_") + "','" + map.get("DMF_") + "',"
					+ map.get("DMZ_ ") + ",'" + map.get("FAC_") + "',"
					+ map.get("FFS_") + "," + map.get("FFX_") + ",'"
					+ map.get("FGM_") + "','" + map.get("FLO_") + "','"
					+ map.get("FPD_") + "'," + map.get("FZS_") + ","
					+ map.get("FZX_") + ",'" + map.get("GGN_") + "','"
					+ map.get("GZL_") + "','" + map.get("XDQ_") + "','"
					+ map.get("HJI_") + "','" + map.get("HOU_") + "',"
					+ map.get("HSC_") + "," + map.get("HSH_") + ","
					+ map.get("HSK_") + "," + map.get("HSZ_") + ",'"
					+ map.get("HXC_") + "'," + map.get("HXZ_") + ",'"
					+ map.get("JBB_") + "'," + map.get("JBC_") + ","
					+ map.get("JBK_") + ",'" + map.get("JCK_") + "','"
					+ map.get("JCZ_") + "'," + map.get("JDC_") + ",'"
					+ map.get("JHD_") + "','" + strToDatetime(map.get("JHQ_"))
					+ "'," + map.get("JIA_") + ",'" + map.get("KBA_") + "',"
					+ map.get("KBS_") + "," + map.get("KBX_") + ",'"
					+ map.get("KL1_") + "','" + map.get("KL2_") + "','"
					+ map.get("KN1_") + "','" + map.get("KN2_ ") + "',"
					+ map.get("KSZ_") + "," + map.get("KUA_") + ","
					+ map.get("KXZ_") + ",'" + map.get("LTK_") + "',"
					+ map.get("LTX_") + "," + map.get("MDA_") + ","
					+ map.get("MSZ_") + "," + map.get("MXZ_") + ",'"
					+ map.get("NAM_") + "','" + map.get("NJB_") + "','"
					+ map.get("NJN_") + "'," + map.get("PIZ_") + ",'"
					+ map.get("PLQ_") + "','" + map.get("PRO_") + "','"
					+ map.get("PZN_") + "','" + strToDatetime(map.get("QGC_"))
					+ "'," + map.get("RJL_") + "," + map.get("RJS_") + ",'"
					+ map.get("SPE_") + "'," + map.get("XPC_") + ","
					+ map.get("XPH_") + "," + map.get("XPK_") + ","
					+ map.get("XSH_") + ",'" + map.get("XSW_") + "','"
					+ map.get("YL1_") + "-" + map.get("YN1_") + ","
					+ map.get("YL2_") + "-" + map.get("YN2_") + "','"
					+ map.get("YQT_") + "','" + map.get("YTY_") + "','"
					+ map.get("YUN_") + "','" + map.get("YYA_") + "',"
					+ map.get("112_") + ",'" + map.get("ZPC_") + "',"
					+ map.get("ZPZ_") + ",'" + map.get("ZRJ_") + "',"
					+ map.get("ZRK_") + ",'" + map.get("ZSN_") + "','"
					+ map.get("ZZN_") + "'";
			jdbc.execute(bmZsfmt.toString() + zsfmtValues + ")");
		}
		System.out.println("导入成功！");
	}

	private String strToDatetime(Object dateStr) {
		String dateStr2 = null;
		String dateStr1 = "";
		if (dateStr != null) {
			dateStr1 = dateStr.toString();
		}
		if (dateStr1.length() == 14) {
			dateStr2 = dateStr1.substring(0, 4) + "-"
					+ dateStr1.substring(4, 6) + "-" + dateStr1.substring(6, 8)
					+ " " + dateStr1.substring(8, 10) + ":"
					+ dateStr1.substring(10, 12) + ":"
					+ dateStr1.substring(12, 14);
		}
		if (dateStr1.length() == 8) {
			dateStr2 = dateStr1.substring(0, 4) + "-"
					+ dateStr1.substring(4, 6) + "-" + dateStr1.substring(6, 8);
		}
		return dateStr2;
	}

	// public static void main(String[] args) {
	// UUID uuid = UUID.randomUUID();
	// System.out.println (uuid);
	// // System.out.println (uuid.toString().replace("-", ""));
	// String dateStr1="20100910024917";
	// String dateStr2=dateStr1.substring(0, 4)+"-"+dateStr1.substring(4,
	// 6)+"-"+dateStr1.substring(6,8)+" "+dateStr1.substring(8,
	// 10)+":"+dateStr1.substring(10,12)+":"+dateStr1.substring(12,14);
	// System.out.println (dateStr2);
	// }
	// @Test
	public void getMaxNo() {
		String dhno = "D10827101";
		Integer result = sjDAO.getMaxCkno(dhno);
		System.out.println(result.intValue());
	}

	/**
	 * Test method for
	 * {@link com.quanta.sino.etl.dao.ZpDAO#getZp(java.io.Serializable)}.
	 */
	@Test
	public void testGetZp() {
		ZpngTp zp = zpDao.getZp("00507701");
		if (zp == null) {
			System.out.println("获取制品失败");
		}
		else {
			System.out.println("取得的制品对象订货合同号为:" + zp.getDhno());
		}
	}

	/**
	 * Test method for
	 * {@link com.quanta.sino.etl.dao.ZpDAO#listZp(java.util.Collection, java.lang.String)}
	 * .
	 */
	// @Test
	public void testListZp() {
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("0477201001");
		jbnos.add("0477201003");
		jbnos.add("0477201004");
		List<ZpngTp> listZp = zpDao.listZp(jbnos);
		if (listZp == null || listZp.size() <= 0) {
			System.out.println("获取不到制品");
		}
		else {
			for (ZpngTp e : listZp) {
				System.out.println("制品订货合同号:" + e.getDhno());
			}
		}
	}

	/**
	 * <p>
	 * 指示书作成
	 * </p>
	 * 
	 * @author 张红国
	 */
	@Test
	public void testzc() {
		String sql;
		sql = "delete from SINO_ZSDHTP where DHNO_='D20120206'";
		jdbc.execute(sql);
		sql = "delete from SINO_ZSZRTP where ZRJB_='001516'";
		jdbc.execute(sql);
		sql = "delete from SINO_QXBGTP where JBNO_='001516'";
		jdbc.execute(sql);
		sql = " update SINO_ZSDXTP set STAT_='0',Yczk_='A',JBKB_='0' where JBNO_='001516' and fpno_='A0000003'";
		jdbc.execute(sql);

		List<String> jbnos = new ArrayList<String>();
		jbnos.add("006307-A0000003-D10827101-0");
		List<String> nojbnos = new ArrayList<String>();
		zsBO.zc(jbnos, nojbnos, false);
		System.out.println("作成成功！");
	}

	/**
	 * <p>
	 * 测试生成制品号，根据卷板No判断制品是ETL（6、7位）还是SL（8、9位），ETL生成的制品号为8位，SL的生成的制品号为11位。
	 * </p>
	 * 
	 * @author 张红国
	 */
	@Test
	public void generateNo() {
		String[] zpno = cmnBO.generateNo("000067", NoGenType.end);
		System.out.println(zpno[0]);
		System.out.println(zpno[1]);
	}

	/**
	 * <p>
	 * 指示书分派
	 * </p>
	 */
	@Test
	public void updateZsdhTp() {
		// 指示书号
		String id = "N00016";
		// 紧急程度
		String jinj = "0";
		// 生产线
		String shch = "N";
		// zsBO.updateZsdhTp(id, jinj, shch);
		System.out.println("分派成功！");
	}

	/**
	 * <p>
	 * 撤消指示书
	 * </p>
	 */
	@Test
	public void deleteZsdh() {
		// String zsno = "N00017";
		// String dhno = "D11290323";
		// zsBO.deleteZsdh(zsno, dhno);
		System.out.println("撤消成功！");
	}

	/**
	 * <p>
	 * 取消分派
	 * </p>
	 */
	@Test
	public void deleteFP() {
		String zsno = "N00020";
		// zsBO.deleteFP(zsno);
		System.out.println("取消分派成功！");
	}

	/**
	 * <p>
	 * 查看指示书
	 * </p>
	 */
	@Test
	public void viewZsdh() {
		String zsno = "N00020";
		ZsdhTp zsdh = zsBO.getZsdhTp(zsno);
		YcaiQE qentity = new YcaiQE();
		qentity.setZsno(zsno);
		ycaiBO.query(qentity);
		System.out.println(zsdh.getZsno());
		System.out.println(qentity.getDatas().size());
	}

	/**
	 * <p>
	 * 保存排序
	 * </p>
	 */
	@Test
	public void saveDyZzs() {
		// 指示书号
		List<String> zsnos = new ArrayList<String>();
		zsnos.add("N00015");
		zsnos.add("N00013");
		zsnos.add("N00012");
		zsnos.add("N00016");
		zsnos.add("N00014");
		zsBO.saveDyZzs(zsnos);
		System.out.println("排序成功！");
	}

	/**
	 * <p>
	 * 指示书作成查询
	 * </p>
	 */
	@Test
	public void queryZsdx() {
		ZsListQE page = new ZsListQE();
		page.setSize(-1);
		// 查询状态为0，余材状况为1的指示DB
		page.setStat(ZtConstants.DHZS_STAT_WFP);
		page.setYczk(EXpzk.SC_KEY);
		page.setOrderBys(" a.dhno asc,a.jbkb asc");
		zsBO.queryZsdx(page);
		System.out.println(page.getDatas().size());
	}

	/**
	 * <p>
	 * 指示书分派查询
	 * </p>
	 */
	@Test
	public void queryZsdhTp1() {
		FpListQE page = new FpListQE();
		page.setOrderBys(" crea desc");
		// zsBO.queryZsdhTp(page);
		System.out.println(page.getDatas().size());
	}

	/**
	 * <p>
	 * 指示书待生产查询
	 * </p>
	 */
	@Test
	public void queryZsdhTp2() {
		DscListQE page = new DscListQE();
		// 已分派
		page.setStat(ZtConstants.DHZS_STAT_YFP);
		// 指示书未完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_WDY);
		// 余材状况为1
		page.setYczk(EXpzk.SC_KEY);
		// 按生产顺序和创建时间排序
		page.setOrderBys(" sort asc,crea desc");
		zsBO.queryDscZsdhTp(page);
		System.out.println(page.getDatas().size());
	}

	/**
	 * <p>
	 * 提示书完成查询
	 * </p>
	 */
	@Test
	public void queryZsdhTp3() {
		FpListQE page = new FpListQE();
		// 余材状况为1的指示DB
		page.setYczk(EXpzk.SC_KEY);
		// 指示书完了
		page.setZsbj(ZtConstants.DHZS_ZSFX_YDY);
		// 默认查询时间设置
		if ((page.getCrea_begin() == null) && (page.getCrea_end() == null)) {
			// 作成开始时间（本月第1天）
			Calendar beginDate = Calendar.getInstance();
			beginDate.set(Calendar.DATE, 1);
			beginDate.add(Calendar.DATE, -1);
			page.setCrea_begin(beginDate.getTime());
			// 作成结束时间（本月最后一天）
			Calendar endDate = Calendar.getInstance();
			endDate.set(Calendar.DATE, 1);
			endDate.add(Calendar.MONTH, 1);
			page.setCrea_end(endDate.getTime());
		}
		page.setOrderBys(" crea desc");
		// zsBO.queryZsdhTp(page);
		System.out.println(page.getDatas().size());
	}

	/**
	 * <p>
	 * 测试保存实绩录入
	 * </p>
	 */
	@Test
	public void saveSjLr() {
		SjSaveVO sjVO = new SjSaveVO();
		sjVO.setBan("1");
		sjVO.setChan("1");
		sjVO.setDdno("SS");
		sjVO.setDeng("GAA");

		sjVO.setHouu(0.155);
		sjVO.setJbcn(33);
		sjVO.setJbno("00148201");

		sjVO.setKuan(925.5);

		sjVO.setSjzl(0);
		sjVO.setZpzl(0);
		sjVO.setZrjb("001482");
		sjVO.setZsno("N00070");
		sjVO.setZu("A");
		sjVO.setElin("A");
		etlBO.saveSjLr(sjVO);
		System.out.println("保存成功！");
	}

	/**
	 * <p>
	 * 实绩订正
	 * </p>
	 */
	@Test
	public void updateSj() {
		SjSaveVO sjVO = new SjSaveVO();
		sjVO.setBan("1");
		sjVO.setChan("1");
		sjVO.setDdno("SS");
		sjVO.setDeng("GAA");

		sjVO.setHouu(0.155);
		sjVO.setJbcn(33);
		sjVO.setJbno("00011402");

		sjVO.setSjzl(0);
		sjVO.setZpzl(0);
		sjVO.setZrjb("000114");
		sjVO.setZsno("N00018");
		sjVO.setZu("A");
		etlBO.updateSjLr(sjVO);
		System.out.println("订正成功！");
	}

	/**
	 * <p>
	 * 实绩维护
	 * </p>
	 */
	@Test
	public void queryZp() {
		SjQE page = new SjQE();
		page.setOrderBys(" crea desc");
		etlBO.query(page);
		System.out.println(page.getDatas().size());
	}

	/**
	 * <p>
	 * 删除实绩
	 * </p>
	 */
	@Test
	public void deleteZp() {
		// 制品NO
		String jbno = "00010501";
		etlBO.delete(jbno);
		System.out.println("删除成功！");
	}

	/**
	 * <p>
	 * 入侧 备用卷确认
	 * </p>
	 */
	@Test
	public void save() {
		Rcrz entity = new Rcrz();
		entity.setJbno("000002");
		// rcBo.save(entity);

	}

	/**
	 * <p>
	 * 入侧 移行
	 * </p>
	 */
	@Test
	public void updateMove() {
		rcBo.updateMove("0000002");
	}

	@Test
	public void testFuzm() {
		Double ss = cmnBO.parseFzl2Gm("WB", "025");
		System.out.println(ss);
	}

	@Test
	public void fetchEtlsy() {
		EtlSydjVO vo = new EtlSydjVO();
		vo.setEtsd(DateUtils.parse("2011-02-05", null));
		OutputStream os = null;
		try {
			os = new FileOutputStream("d:\\test.xls");
			etlBO.fetchEtlsy(vo, os);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	public void importData() {
		String fromTab = "SINO_ZSDHTP";
		String toTab = "SINO_ZSDHTPNew";
		String sql = "select * from " + fromTab;
		String ss = "";
		String inserColName = "";
		String inserSql = "";
		String inserValue = "";
		SqlRowSet rs = jdbc.queryForRowSet(sql);
		SqlRowSetMetaData rsmd = rs.getMetaData();
		// 创建表
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			if (rsmd.getColumnTypeName(i + 1).equals("int")) {
				ss = ss + "," + rsmd.getColumnName(i + 1) + " "
						+ rsmd.getColumnTypeName(i + 1) + " null";
			}
			else if (rsmd.getColumnTypeName(i + 1).equals("datetime")) {
				ss = ss + "," + rsmd.getColumnName(i + 1) + " "
						+ rsmd.getColumnTypeName(i + 1) + " null";
			}
			else if (rsmd.getColumnTypeName(i + 1).equals("numeric")) {
				ss = ss + "," + rsmd.getColumnName(i + 1) + " "
						+ rsmd.getColumnTypeName(i + 1) + "("
						+ rsmd.getPrecision(i + 1) + "," + rsmd.getScale(i + 1)
						+ ") null";
			}
			else {
				ss = ss + "," + rsmd.getColumnName(i + 1) + " "
						+ rsmd.getColumnTypeName(i + 1) + "("
						+ rsmd.getPrecision(i + 1) + ") null";
			}
			inserColName = inserColName + "," + rsmd.getColumnName(i + 1);
		}
		// jdbc.execute("create table " + toTab + " (" + ss.substring(1) + ")");
		// System.out.println("创建表成功");
		// 插入表数据
		while (rs.next()) {
			for (int j = 0; j < 10; j++) {
				inserValue = "";
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					if (rsmd.getColumnTypeName(i + 1).equals("int")) {
						inserValue = inserValue + "," + rs.getObject(i + 1);
					}
					else if (rsmd.getColumnTypeName(i + 1).equals("numeric")) {
						inserValue = inserValue + "," + rs.getObject(i + 1);
					}
					else if (rs.getObject(i + 1) == null) {
						inserValue = inserValue + ",null";
					}
					else {
						inserValue = inserValue + ",'" + rs.getObject(i + 1)
								+ "'";
					}

				}
				inserSql = inserSql + ";" + "insert " + toTab + "("
						+ inserColName.substring(1) + ")VALUES("
						+ inserValue.substring(1) + ")";

			}

		}
		jdbc.execute(inserSql.substring(1));
		System.out.println("插入表成功");

	}

	@Test
	public void testCode() {
		String jbno = "013800";
		String rel = rcDAO.getSczt(jbno);
		System.out.println(rel);
	}

	@Test
	public void testzp() {
		// etlBO.getZpChang("01547301");
		System.out.println("插入表成功");
	}
}
