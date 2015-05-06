/**
 * 
 */
package com.quanta.sino.zkfp.ych;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * </p>
 * <p>
 * create: 2011-1-14 下午03:38:16
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YchTestCase {

	private static IZpBO zpBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		zpBO = Helper.getBean(IZpBO.class);
	}

	@Test
	public void getZp() {
		String jbno = "00477201001";
		ZpngTp entity = zpBO.getZp(jbno);
		Assert.assertNotNull(entity);
		System.out.println(entity.getAbbr());
	}
}
