package com.quanta.sino.cmn;

import junit.framework.Assert;

import org.dom4j.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.NoGenType;

public class CmnTestCase {

	private static ICmnBO bo;

	private static IYongBO yongBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ICmnBO.class);
		yongBO = Helper.getBean(IYongBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void queryFpkhs() {
		// List<String> list = yongBO.findFpkhs();
		// if (list == null || list.size() == 0) {
		// System.out.println("fpkhs is null");
		// }
		// else {
		// for (String s : list) {
		// System.out.println("s===============" + s);
		// }
		// }
	}

	@Test
	public void generateNo() {
		String jbno = null;
		// jbno = "004772"; // 有效
		// jbno = "006556"; //无效
		jbno = "00039901"; // 有效
		NoGenType type = NoGenType.cut;
		String[] nos = bo.generateNo(jbno, type);
		Assert.assertNotNull(nos);
		if (type == NoGenType.cut) {
			System.out.println(nos[0] + " ; " + nos[1]);
		}
		else {
			System.out.println(nos[0]);
		}
	}

	@Test
	public void parentNo() {
		String jbno = "01234567891";
		jbno = "0477201006";
		System.out.println(bo.parentNo(jbno));
	}

	@Test
	public void treeNo() {
		String jbno = "0477201";
		Document doc = bo.treeNo(jbno);
		Assert.assertNotNull("无效号码", doc);
		System.out.println(doc.asXML());
	}

}
