package com.quanta.sino.dy;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.constants.ChglConstants;
import com.quanta.sino.ch.vo.ZxzsDyQE;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;

public class ChdyTestCase {

	private static IZxzsBO zBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		zBo = Helper.getBean(IZxzsBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 装箱指示书打印
	 * </p>
	 */
	@Test
	public void print() {
		String id = "402881ef2e2e092a012e2e0bd7a00004";
		ZxzsDyQE page = new ZxzsDyQE();
		Zng1Tp zng1Tp = null;
		Assert.assertNotNull(zng1Tp);
		// System.out.println("zxno=============" + zng1Tp.getZxno());
		// page.setZxno(zng1Tp.getZxno());
		// zBo.queryZng(page);
		List<Zng2Tp> zng2Tps = page.getDatas();
		if (zng2Tps.size() == 0) {
			System.out.println("==========================null");
			return;
		}
		System.out.println("finish");
		for (Zng2Tp entity : zng2Tps) {
			// System.out.println("zpno=" + entity.getZpno());
		}
	}

	/**
	 * <p>
	 * 送货单打印
	 * </p>
	 */
	@Test
	public void shdPrint() {
		int pageSizes = 0;
		if (pageSizes == 0) {
			System.out.println("该装箱指示书没有制品");
			return;
		}
		System.out.println("送货单页数=" + pageSizes);
		ZxzsDyQE page = new ZxzsDyQE();
		page.setSize(ChglConstants.SHD_SIZE);
		page.setOrderBys("b.dhno,b.line");
		page.setZxno("0000000027");
		// zBo.queryZng(page);
		if (page.getDatas().size() == 0) {
			System.out.println("==========================null");
			return;
		}
		// for (Zng2Tp entity : page.getDatas()) {
		// System.out.println("zpno=" + entity.getJbno());
		// System.out.println("HOUU=" + entity.getBzzs());
		//
		// }
		System.out.println("finish ok");
	}

	@Test
	public void test() {
		// Zng2Tp zng2Tp = zBo.get("D112903", "01", "0000000002", "00006701");
		// System.out.println(zng2Tp.getZpno());
	}
}
