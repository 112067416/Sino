package com.quanta.sino.ch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.dao.api.IJczmsDAO;
import com.quanta.sino.ch.vo.ZxdzItem;
import com.quanta.sino.ch.vo.ZxdzVO;
import com.quanta.sino.orm.Jczms;

/**
 * <p>
 * 装箱对照
 * </p>
 * <p>
 * create: 2011-1-6 上午09:23:29
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxdzTestCase {

	private static IZxdzBO bo;

	private static IJczmsDAO jczmsDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IZxdzBO.class);
		jczmsDAO = Helper.getBean(IJczmsDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {

	}

	@Test
	public void query() {
		List<String> zxnos = new ArrayList<String>();
		zxnos.add("0000000008 ");
		List<Jczms> jczms = jczmsDAO.query(zxnos);
		System.out.println(jczms.size());
	}

	@Test
	public void testCount() {

		Long count = jczmsDAO.count("2c9085852eec1ffa012eec278fd80004");
		System.out.println(count);
	}

	/**
	 * <p>
	 * 装箱对照
	 * </p>
	 */
	@Test
	public void zxdz() {
		ZxdzVO vo = new ZxdzVO();
		vo.setChri(new Date());
		vo.setZxno("0000000018");
		ZxdzItem item1 = new ZxdzItem();
		item1.setDhno("D20120204");
		item1.setZpno("00147005");
		ZxdzItem item2 = new ZxdzItem();
		item1.setDhno("D20120204");
		item2.setZpno("00147006");
		ZxdzItem item3 = new ZxdzItem();
		item1.setDhno("D20120204");
		item3.setZpno("00147007");
		ZxdzItem item4 = new ZxdzItem();
		item1.setDhno("D20120204");
		item4.setZpno("00147008");
		ZxdzItem items[] = new ZxdzItem[] { item1, item2, item3, item4 };
		vo.setItems(items);
		bo.zxdz(vo);
	}

	/**
	 * <p>
	 * 设置车牌号
	 * </p>
	 */
	@Test
	public void setCp() {
		// SzcpVO vos = new SzcpVO();
		// SzcpVO vo1 = new SzcpVO();
		// SzcpVO vo2 = new SzcpVO();
		// vos.setCpno("1112323");
		// vo1.setZpno("00107005");
		// vo2.setZpno("00107006");
		// SzcpVO items[] = { vo1, vo2 };
		// vos.setItems(items);
		// bo.setCp(vos);
	}
}
