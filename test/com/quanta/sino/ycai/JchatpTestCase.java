package com.quanta.sino.ycai;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;
import com.quanta.sino.ycai.vo.JchatpQE;

public class JchatpTestCase {

	private static IYcaitpDAO dao;

	// private static IYcaitpBO dbo1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = Helper.getBean(IYcaitpDAO.class);
		// dbo1 = Helper.getBean(IYcaitpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void get() {
		YcaiTp tp = dao.get("72860122");
		if (tp != null) {
			System.out.println("enter into is not null");
		}
		else {
			System.out.println(" null");
		}
	}

	@Test
	public void saveJchatp() {

		// JchaTp entity = new JchaTp();
		// entity.setJbno("0000067");

		// entity.setCfb2("233");

	}

	// <<<<<<< .mine
	// @Test
	// public void updateJchatp() {
	// JchaTp dept = new JchaTp();
	// dept.setJbno("123456");
	// // dept.setCfb1("123");
	// dept.setCfb2("233");
	// dbo.update(dept);
	// }
	// @Test
	// public void deleteJchatp() {
	//
	// dbo.delete("123456");
	// }
	// @Test
	// public void queryJchatp() {
	// JchatpQE qe=new JchatpQE();
	// qe.setjbno("123456");
	// qe.setSize(2);
	// qe.setIndex(0);
	// dbo.query(qe);
	// System.out.println("总记录数：" + qe.getCount());
	// System.out.println("========记录明细=======");
	// for (Object chatp : qe.getDatas()) {
	// System.out.println(((JchaTp) chatp).getJbno());
	// }
	// }
	// =======

	@Test
	public void updateJchatp() {
		JchaTp dept = new JchaTp();
		// dept.setJbno("0000071");
		// dept.setCfb1("123");
		dept.setCfb2("234");
	}

	@Test
	public void deleteJchatp() {

	}

	@Test
	public void queryJchatp() {
		JchatpQE qe = new JchatpQE();
		// qe.setJbno("123456");
		qe.setShip("a");
		// qe.setSize(2);
		// qe.setIndex(0);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("========记录明细=======");
		for (JchaTp chatp : qe.getDatas()) {
			// System.out.println(chatp.getJbno());
		}
	}

}
