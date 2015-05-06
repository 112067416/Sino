package com.quanta.sino.dy;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.dy.bo.api.IZpkBO;
import com.quanta.sino.dy.vo.ZpkVO;

public class ZpkTestCase {

	private static IZpkBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IZpkBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void get() {
		String jbno = "00477201001";
		ZpkVO vo = bo.get(jbno);
		Assert.assertNotNull(vo);
	}

}
