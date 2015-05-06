/**
 * 
 */
package com.quanta.sino.cmn;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.orm.Attachment;
import com.quanta.sino.cmn.bo.api.ICmnBO;

/**
 * <p>
 * </p>
 * <p>
 * create: 2011-1-8 下午10:24:03
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class InformTestCase {

	private static IAttachmentBO attachmentBO;

	private static ICmnBO cmnBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		attachmentBO = Helper.getBean(IAttachmentBO.class);
		cmnBO = Helper.getBean(ICmnBO.class);
	}

	@Test
	public void getMax() {
		System.out.println(cmnBO.generateYcaiTpNo());
	}

	@Test
	public void deletes() {

	}

	@Test
	public void get() {
		String id = "ff8080812d666a14012d66717c8a0006";
		Attachment attachment = attachmentBO.get(id);
		if (attachment == null) {
			System.out.println("the attachment is null");
		}
		else {
			System.out.println("the attachment is not null");
		}
		// InformTp informTp = dao.get(id);
		// if (informTp != null) {
		// System.out.println("is not null");
		// }
		// else {
		// System.out.println("is null");
		// }
	}
}
