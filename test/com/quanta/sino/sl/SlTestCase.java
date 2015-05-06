package com.quanta.sino.sl;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.sl.bo.api.ISlBO;
import com.quanta.sino.sl.vo.SllrVO;
import com.quanta.sino.sl.vo.SlsjQE;
import com.quanta.sino.sl.vo.SlsjVO;

public class SlTestCase {

	private static ISlBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ISlBO.class);
	}

	/**
	 * <p>
	 * 进入实绩录入界面
	 * </p>
	 */
	@Test
	public void testLead() {
		User user = new User();
		user.setDeptId("1035");
		user.setId("sl2");
		user.setPerson("1997");
		SllrVO vo = bo.lead(user);
		Assert.assertNotNull(vo);
	}

	/**
	 * <p>
	 * SL实绩查询
	 * </p>
	 */
	@Test
	public void query() {
		SlsjQE page = new SlsjQE();
		// page.setZsno("W00013");
		// page.setZu("A");

		bo.query(page);
		System.out.println("总记录数：" + page.getXpzk());
		System.out.println("总记录数：" + page.getDatas().size());
		System.out.println("========记录明细=======");
		for (SlsjVO chatp : page.getDatas()) {
			System.out.println(chatp.getZsno());
		}
	}

	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewSjzs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSjlr() {
		fail("Not yet implemented");
	}

	@Test
	public void testDzCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testSjdz() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testView() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
