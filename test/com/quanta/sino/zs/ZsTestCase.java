package com.quanta.sino.zs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ycai.vo.YcaiQE;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 采购合同模块测试用例
 * </p>
 * <p>
 * create time : 2010-12-10
 * </p>
 * 
 * @author 张红国
 */
public class ZsTestCase {

	private static IZsBO bo;

	// private static IZpBO zpBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IZsBO.class);
		// zpBo = Helper.getBean(IZpBO.class);
	}

	@Test
	public void getFugm() {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void query() {
		// ZsListQE qe = new ZsListQE();
		// bo.queryZsdx(qe);
		// System.out.println(qe.getCount());
	}

	/**
	 * 查询原材原板
	 */
	@Test
	public void queryYcaiTp() {
		YcaiQE qentity = new YcaiQE();
		qentity.setZsno("N00142");
		// bo.queryYcaiTp(qentity);
		System.out.println(qentity.getDatas().size());
	}

}
