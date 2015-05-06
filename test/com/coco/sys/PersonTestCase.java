package com.coco.sys;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.IPersonBO;
import com.coco.sys.orm.Person;
import com.coco.sys.vo.PersonQE;

public class PersonTestCase {

	private static IPersonBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IPersonBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 */
	@Test
	public void query() {
		PersonQE qe = new PersonQE();
		bo.query(qe);
		for (Person person : qe.getDatas()) {
			System.out.println(person.getName());
		}
	}
}
