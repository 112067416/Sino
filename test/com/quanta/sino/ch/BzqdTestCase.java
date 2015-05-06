package com.quanta.sino.ch;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.IBzqdBO;
import com.quanta.sino.ch.bo.api.IJczmsBO;
import com.quanta.sino.dy.vo.PbzqdVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 包装清单业务测试
 * </p>
 * <p>
 * create: 2011-2-15 下午06:01:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class BzqdTestCase {

	private static IBzqdBO bo;

	private static IZpBO zpBo;

	private static IJczmsBO jczmsBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IBzqdBO.class);
		zpBo = Helper.getBean(IZpBO.class);
		jczmsBO = Helper.getBean(IJczmsBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void deleteItems() {
		String zxno = "0000077633";
		// jczmsBO.deleteItems(zxno);

	}

	@Test
	public void find() {
		// List<String> jbnos = new ArrayList<String>();
		String[] s = { "00791501001", "00791501002", "00791501003",
				"00791501004", "00791501005", "00791501006", "00791501007",
				"00791501008", "00791501009", "00791501013", "00791501015",
				"00791601001", "00791601002", "00791601003", "00791601004" };
		String zxno = "0000002";
		List<PbzqdVO> vos = bo.findBzqdVos(Arrays.asList(s), zxno);
		if (vos != null) {
			for (PbzqdVO vo : vos) {
				System.out.println(vo.getDhno());
			}
		}

	}

	@Test
	public void query() {
		ZpQE page = new ZpQE();
		page.setDhnoLine("D10619601");
		page.setSize(10);
		page.setOrderBys("duic asc");
		zpBo.query(page);
		System.out.println("总记录数：" + page.getCount());
		System.out.println("========记录明细=======");
		for (ZpngTp tp : page.getDatas()) {
			System.out.println(tp.getJbno());
		}
	}

	public static void main(String ags[]) {
		String uuids = "";
		for (int i = 0; i < 5; i++) {
			if ("".equals(uuids)) {
				uuids = "a";
				continue;
			}
			uuids += "," + "b";
		}

		System.out.println("uuids====" + uuids);
	}
}
