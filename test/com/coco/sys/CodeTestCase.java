package com.coco.sys;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;

public class CodeTestCase {

	private static ICodeBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ICodeBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void findItem() {
		List<CodeItem> items = bo.findItems("010", "001");
		for (CodeItem item : items) {
			System.out.println(item.getKey());
		}
	}

	@Test
	public void getCodeItem() {
		bo.getCodeItem("024", "WB025");
		bo.getCodeItem("024", "WB025");
		bo.getCodeItem("024", "WB025");
	}

}
