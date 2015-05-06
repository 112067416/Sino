package com.quanta.sino.yygl;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.constants.KhyfStat;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.yygl.bo.api.IKhyfBO;
import com.quanta.sino.yygl.vo.KhyfVO;
import com.quanta.sino.yygl.vo.ZrchsjQE;
import com.quanta.sino.yygl.vo.ZrchsjfVO;

/**
 * <p>
 * 客户运费管理测试用类
 * </p>
 * <p>
 * create: 2011-2-15 下午05:35:52
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhyfTestCase {

	private static IKhyfBO khyfBO;

	private static JdbcTemplate template;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		khyfBO = Helper.getBean(IKhyfBO.class);
		template = (JdbcTemplate) Helper.getBean("jdbcTemplate");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testDate() {
		// final StringBuilder sql = new StringBuilder(
		// " select CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ from (");
		// sql.append("select CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ from SINO_KHYF where DHNO_ not like 'W%' and CHRI_>=?");
		// sql.append("union all select CHRI_, null as CYHHNM_, null as YSNM_, NAME_,'小计' as DHNO_,null as LINE_,sum(CHSU_) AS CHSU_,sum(CHZL_) AS CHZL_,null AS SHNM_,NULL AS ADDR_,null AS HOUU_,null AS KUAN_,null AS CANG_,null AS ZXNO_,null as ID_,null as STAT_,null as CYCKNM_ from SINO_KHYF where DHNO_ not like 'W%' and CHRI_>=? group by CHRI_,NAME_ having count(*) > 1");
		// sql.append(") t  ORDER BY CHRI_ ASC, NAME_ ASC, DHNO_ ASC, LINE_ ASC");
		// final StringBuilder sql = new StringBuilder(
		// " select CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ from (");
		// sql.append("select CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ from SINO_KHYF where DHNO_ not like 'W%' and CHRI_>=?");
		// sql.append(" union all select CHRI_, null as CYHHNM_, null as YSNM_, NAME_,'小计' as DHNO_,null as LINE_,sum(CHSU_) AS CHSU_,sum(CHZL_) AS CHZL_,null AS SHNM_,NULL AS ADDR_,null AS HOUU_,null AS KUAN_,null AS CANG_,null AS ZXNO_,null as ID_,null as STAT_,null as CYCKNM_ from SINO_KHYF where DHNO_ not like 'W%' and CHRI_>=? group by CHRI_,NAME_ having count(*) > 1");
		// sql.append(") t  ORDER BY CHRI_ ASC, NAME_ ASC, DHNO_ ASC, LINE_ ASC");
		//
		final String sql = "select CHRI_,CYHHNM_,YSNM_,NAME_,DHNO_,LINE_,CHSU_,CHZL_,SHNM_,ADDR_, HOUU_ ,KUAN_,CANG_,ZXNO_,ID_,STAT_,CYCKNM_ from SINO_KHYF where DHNO_ not like 'W%' and CHRI_>=?";
		// final List<Date> values = new ArrayList<Date>();
		java.util.Date date = DateUtils.parse("2011-07-08", "yyyy-MM-dd");
		// values.add(DateUtils.parse("2011-06-01", "yyyy-MM-dd"));
		// Date da = (java.sql.Date) date;
		// // values.add(DateUtils.parse("2011-06-01", "yyyy-MM-dd"));
		// template.execute(new ConnectionCallback<Void>() {
		//
		// @Override
		// public Void doInConnection(Connection conn) throws SQLException,
		// DataAccessException {
		// int index = 1;
		// PreparedStatement pstmt = null;
		// ResultSet rs;
		// pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
		// ResultSet.CONCUR_READ_ONLY);
		// index = 1;
		// pstmt.setObject(1, d);
		// // for (Date value : values) {
		// // pstmt.set
		// // pstmt.setDate(index, value);
		// // // pstmt.setObject(index++, value);
		// // }
		// pstmt.setMaxRows(1000);
		// rs = pstmt.executeQuery();
		//
		// return null;
		// }
		//
		// });

	}

	@Test
	public void queryChsj() {
		ZrchsjQE page = new ZrchsjQE();
		page.setChriS(DateUtils.parse("2011-06-01", "yyyy-MM-dd"));
		// page.setYsgs("05");
		page.setSize(-1);
		page.setOrderBys("CHRI_ ASC, NAME_ ASC, DHNO_ ASC, LINE_ ASC");
		khyfBO.query(page);
		System.out.println("size==========" + page.getDatas().size());
		if (page.getDatas().size() > 1) {
			int size = 1;
			for (ZrchsjfVO vo : page.getDatas()) {
				if (vo.getId() == null || vo.getId().isEmpty()) {
					System.out.println("小计。"
							+ DateUtils.format(vo.getChri(), "yyyy-MM-dd")
							+ ";  " + vo.getYsnm() + ";  " + vo.getName()
							+ ";   " + vo.getDhno() + ";   " + vo.getLine()
							+ ";  " + vo.getChsu() + ";   " + vo.getChzl()
							+ ";   " + vo.getShnm() + ";    " + vo.getAddr()
							+ ";   " + vo.getHouu() + ";    " + vo.getKuan()
							+ ";   " + vo.getCang() + ";   " + vo.getZxno());
					continue;
				}
				System.out.println("第" + size++ + "条。"
						+ DateUtils.format(vo.getChri(), "yyyy-MM-dd") + ";  "
						+ vo.getYsnm() + ";  " + vo.getName() + ";   "
						+ vo.getDhno() + ";   " + vo.getLine() + ";  "
						+ vo.getChsu() + ";   " + vo.getChzl() + ";   "
						+ vo.getShnm() + ";    " + vo.getAddr() + ";   "
						+ vo.getHouu() + ";    " + vo.getKuan() + ";   "
						+ vo.getCang() + ";   " + vo.getZxno());

			}
		}
	}

	/**
	 * <p>
	 * 生成客户运费数据
	 * </p>
	 */
	@Test
	public void saveKhyf() {

		Khyf entity = new Khyf();
		entity.setChri(new Date());
		entity.setYsgs("05");
		entity.setYsnm("福州畅港公司");
		entity.setUser("1008");
		entity.setAbbr("ZHB/LN");
		entity.setDhno("D119286");
		entity.setLine("07");
		entity.setChzl(89.4);
		entity.setChsu(198);
		entity.setShno("1");
		entity.setShnm("厦门");
		entity.setAddr("厦门市中山路34号");
		entity.setHouu(0.23);
		entity.setKuan(890.0);
		entity.setCang(1200.0);
		entity.setStat(KhyfStat.CS.stat);

		khyfBO.save(entity);
	}

	/**
	 * <p>
	 * 为客户出货数据，设置客户运费单价
	 * </p>
	 */
	@Test
	public void saveYf() {
		KhyfVO vo = new KhyfVO();
		vo.setId("297e947a2e2c9fba012e2c9fe8510001");
		vo.setYsfs("1");
		vo.setYsfm("货柜海运");
		//
		// 出口运输方式。
		// 中日达起始港
		vo.setQgckdj(1200.0);
		vo.setQgckno("1");
		vo.setQgcknm("福州畅港公司");
		// 堆场
		vo.setDcckdj(2000d);
		// vo.setDcck(2);
		vo.setDcckno("1");
		vo.setDccknm("天择堆场");
		// 船运
		vo.setCyckdj(1300d);
		// vo.setCyck(1);
		vo.setCyckno("1");
		vo.setCycknm("中迅货运");
		// 目的港到客户
		vo.setKhckdj(1500d);
		// vo.setMdck(3);
		vo.setKhckno("2");
		vo.setKhcknm("上海杨思交通运输管理站");

		// 铁路运输方式。
		// 中日达到终点站
		vo.setZdtldj(2300d);
		vo.setZdtlno("1");
		vo.setZdtlnm("福建鸿达货运公司");
		// 终点站到客户
		vo.setKhtldj(2009d);
		vo.setKhtlno("1");
		vo.setKhtlnm("福建鸿达货运公司");
		// 公路运输方式
		// 中日达到客户
		vo.setKhgldj(null);
		vo.setKhglno(null);
		vo.setKhglnm(null);
		// 散货海运
		// 中日达到目的港
		vo.setQgshdj(null);
		vo.setQgshno(null);
		vo.setQgshnm(null);
		// 目的港到客户
		vo.setKhshdj(null);
		vo.setKhshno(null);
		vo.setKhshnm(null);
		// 货柜海运
		// 中日达到起始港
		vo.setQghhdj(1200d);
		vo.setQghhno("1");
		vo.setQghhnm("福州畅港公司");
		// 堆场
		// vo.setDchy(2);
		vo.setDchhdj(2000d);
		vo.setDchhno("1");
		vo.setDchhnm("天择堆场");
		// 船运
		// vo.setCyhy(1);
		vo.setCyhhdj(1300d);
		vo.setCyhhno("1");
		vo.setCyhhnm("中迅货运");
		// 目的港岛客户
		vo.setKhhhdj(1500d);
		vo.setKhhhno("2");
		vo.setKhhhnm("上海杨思交通运输管理站");

		khyfBO.saveYf(vo);
	}
}
