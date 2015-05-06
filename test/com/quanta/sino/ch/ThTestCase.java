package com.quanta.sino.ch;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.IThBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.orm.Zng2Tp;

/**
 * <p>
 * 退货业务测试
 * </p>
 * <p>
 * create: 2011-2-15 下午06:00:45
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ThTestCase {

	private static IThBO bo;

	private static IZxzsBO zxzsBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IThBO.class);
		zxzsBO = Helper.getBean(IZxzsBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void get() {
		String jbno = "00791601004";
		String zxno = "0000002";
		Zng2Tp zng2Tp = zxzsBO.get(jbno, zxno);
		Assert.assertNotNull(zng2Tp);
		System.out.println(zng2Tp.getDcno());
	}

	@Test
	public void save() {
		String[] ids = { "", "", "" };
		// bo.save(ids);
	}
}
