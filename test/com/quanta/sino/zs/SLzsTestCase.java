package com.quanta.sino.zs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.constants.ZtConstants;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.zs.bo.api.IZsBO;
import com.quanta.sino.zs.vo.JqcQE;
import com.quanta.sino.zs.vo.JqcVO;
import com.quanta.sino.zs.vo.ZsQE;
import com.quanta.sino.zs.vo.ZsVO;
import com.quanta.sino.zs.vo.ZssXpVO;

/**
 * <p>
 * Sl指示业务层单元测试
 * </p>
 * <p>
 * create: 2011-2-17 下午04:23:41
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
public class SLzsTestCase {

	private static IZsBO bo;

	private static IZpBO zpBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IZsBO.class);
		zpBo = Helper.getBean(IZpBO.class);
	}

	/**
	 * <p>
	 * 查询剪切材列表（作成指示书的材料）
	 * </p>
	 */
	@Test
	public void testQueryJqc() {
		JqcQE page = new JqcQE();
		page.setSize(-1);
		page.setOrderBys("a.dhno");
		bo.queryJqc(page);

		List<JqcVO> list = page.getDatas();
		if (list != null) {
			System.out.println("共查出剪切材数量：" + list.size() + " 条");
		}
		else {
			System.out.println("没有剪切材可以作成指示书");
		}
		for (JqcVO vo : list) {
			System.out.println("剪切材制品号：" + vo.getJbno());
		}
	}

	/**
	 * <p>
	 * 剪切指示书作成
	 * </p>
	 * <p>
	 * 输入：<br />
	 * <ul>
	 * <li>剪切材制品号: 只能录入正确的待剪切制品，生成指示书的时候没有校验</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 输出：<br />
	 * <ul>
	 * <li>写指示订货</li>
	 * <li>更新多个制品记录</li>
	 * <li>新增多个指示DB(装入卷板)</li>
	 * <li>更新多个指示对象DB</li>
	 * <li>返回：生成指示书的指示书号</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testSlzszc() {
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String[] nos = { "00040302", "00040303", "00040304" };
		List<String> jbnos = new ArrayList<String>();
		// List<String> zsnos = bo.slzszc(nos, jbnos, user);
		// System.out.println("生成的指示书号为：" + zsnos);
		// Assert.assertNotNull(zsnos);
	}

	/**
	 * <p>
	 * 将剪切材解析分组（没有数据库表的修改）
	 * </p>
	 * <p>
	 * 输入：<br />
	 * <ul>
	 * <li>待作成指示书的剪切材列表</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 输出：<br />
	 * <ul>
	 * <li>分组后的剪切材MAP</li>
	 * </ul>
	 * </p>
	 */
	// @Test
	// public void testParseZs4Slzszc() {
	// List<String> jbnos = Arrays.asList("00011307", "00011702");
	// List<ZpngTp> listZp = zpBo.listZp(jbnos);
	// Map<ESlzssType, Map<String, Map<Double, List<List<ZpngTp>>>>> map =
	// bo.parseZs4Slzszc(listZp);
	// Iterator<ESlzssType> it = map.keySet().iterator();
	// while (it.hasNext()) {
	// ESlzssType key = (ESlzssType) it.next();
	// System.out.println();
	// System.out.println("================类型为" + key.flag
	// + "的集合=================");
	// System.out.println();
	// Map<String, Map<Double, List<List<ZpngTp>>>> items = map.get(key);
	// Iterator<String> ita = items.keySet().iterator();
	// while (ita.hasNext()) {
	// String dhno = (String) ita.next();
	// System.out.println("---------订货号为:" + dhno + "的集合-------------");
	// System.out.println();
	// Map<Double, List<List<ZpngTp>>> xpkns = items.get(dhno);
	// Iterator<Double> itb = xpkns.keySet().iterator();
	// while (itb.hasNext()) {
	// // 制品宽
	// Double xpkn = (Double) itb.next();
	// // 制品组
	// List<List<ZpngTp>> item = xpkns.get(xpkn);
	// // List<List<ZpngTp>> item = items.get(type);
	// for (int i = 0; i < item.size(); i++) {
	// System.out.println("###第" + i + "批次指示书对应制品号###");
	// List<ZpngTp> list = item.get(i);
	//
	// for (ZpngTp zp : list) {
	// System.out.println("制品号为：" + zp.getJbno());
	// }
	// }
	// }
	// }
	// }
	// Assert.assertNotNull(map != null && map.size() > 0);
	// }
	/**
	 * <p>
	 * 指示书分页查询
	 * </p>
	 */
	@Test
	public void testQueryWwc() {
		ZsQE page = new ZsQE();
		page.setSize(15);
		// 当前月的第一天
		Date now = new Date();
		page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
		// 当前日期
		page.setCrea_end(now);
		// 设置指示完成标记：未完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_WWC);
		// 设置指示完成标记：已完成
		// page.setZsbj(ZtConstants.DHZS_ZSBJ_YWC);
		// 按指示书完成日期排序
		page.setOrderBys("zsny desc");
		bo.queryWwc(page);
		List<ZsVO> list = page.getDatas();

		if (list != null) {
			System.out.println("共查出未完成的指示书数量：" + list.size() + " 条");
		}
		else {
			System.out.println("没有未完成的指示书");
		}
		for (ZsVO vo : list) {
			System.out.println("未完成的指示书号：" + vo.getZsno());
		}
	}

	/**
	 * <p>
	 * 指示书撤消测试
	 * </p>
	 * <p>
	 * 输入：<br />
	 * <ul>
	 * <li>指示书号</li>
	 * <li>当前登录用户</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 输出：<br />
	 * <ul>
	 * <li>删除装入卷板表</li>
	 * <li>删除指示对象DB</li>
	 * <li>新增分配操作记录</li>
	 * <li>删除指示订货</li>
	 * <li>更新订货DB</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testSlzscx() {
		User user = new User();
		user.setNo("000");
		user.setName("管理员");

		String zsno = "W00002";
		bo.doSlZssCx(zsno, user);
	}

	/**
	 * <p>
	 * 指示书中卷的完成情况
	 * </p>
	 * <p>
	 * 输入：<br />
	 * <ul>
	 * <li>指示书号</li>
	 * <li>类型：1-未完成个数；2-已完成个数; 0/其他-总个数</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 输出：<br />
	 * <ul>
	 * <li>个数</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testGetZssWcqk() {
		// String zsno = "w00002";
		// int type = 0;
		// // long a = bo.getZssWcqk(zsno, type);
		// System.out.println("未完成：" + a);
		// type = 1;
		// // long b = bo.getZssWcqk(zsno, type);
		// System.out.println("已完成：" + b);
		// type = 2;
		// // long c = bo.getZssWcqk(zsno, type);
		// System.out.println("总个数：" + c);
	}

	/**
	 * <p>
	 * 查询指示书中的现品对象（ETL指示书对应原材，SL指示书对象中间品）
	 * </p>
	 * <p>
	 * 输入：<br />
	 * <ul>
	 * <li>指示书对象</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 输出：<br />
	 * <ul>
	 * <li>现品列表</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testListZssXp() {
		String zsno = "W00002";
		ZsdhTp zss = bo.getZsdhTp(zsno);
		List<ZssXpVO> listVo = bo.listZssXp(zss);
		if (listVo != null && listVo.size() > 0) {
			for (ZssXpVO vo : listVo) {
				System.out.println("现品号：" + vo.getJbno());
			}
		}
	}

	@Test
	public void testQuery() {
		ZsQE page = new ZsQE();
		page.setSize(15);
		// 当前月的第一天
		Date now = new Date();
		page.setCrea_begin(DateUtils.getFirstDayOfMonth(now));
		// 当前日期
		page.setCrea_end(now);

		// 设置指示完成标记：已完成
		page.setZsbj(ZtConstants.DHZS_ZSBJ_YWC);
		// 按指示书完成日期排序
		page.setOrderBys("zsny desc");
		bo.queryWwc(page);
		List<ZsVO> list = page.getDatas();
		for (ZsVO vo : list) {
			System.out.println(vo.getDhno());
			System.out.println(vo.getZsno());
			System.out.println(vo.getZsny());
		}
	}

}
