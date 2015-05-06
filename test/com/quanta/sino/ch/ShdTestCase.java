package com.quanta.sino.ch;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.ch.bo.api.IShdBO;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.dao.api.IZng1DAO;
import com.quanta.sino.ch.vo.CjpVO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.orm.Khyf;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.yygl.bo.api.IKhyfBO;

public class ShdTestCase {

	private static IShdBO bo;

	private static IZng1DAO zng1dao;

	private static ICodeBO codeBO;

	private static IKhyfBO khyfBO;

	private static IZxdzBO zxdzBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IShdBO.class);
		zng1dao = Helper.getBean(IZng1DAO.class);
		codeBO = Helper.getBean(ICodeBO.class);
		khyfBO = Helper.getBean(IKhyfBO.class);
		zxdzBO = Helper.getBean(IZxdzBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void outCjp() {
		khyfBO.delete();
		// zxdzBO.outCjp();
	}

	@Test
	public void queryCjp() {
		List<CjpVO> vos = zng1dao.queryCjp();
		int i = 1;
		for (CjpVO vo : vos) {
			System.out.println("i===========" + i++ + ";          chri======"
					+ DateUtils.format(vo.getChri(), DateUtils.YMD)
					+ ";         zxno=========================" + vo.getZxno());
		}
	}

	/**
	 * <p>
	 * 删除送货单
	 * </p>
	 */
	@Test
	public void del() {
		// String id = "0000000002";
		// bo.delete(id);
		// CodeItem item = codeBO.getCodeItem(CmnConstants.CODE_118, "1");
		CodeItem item = codeBO.getCodeItem(CmnConstants.CODE_005, "b");
		System.out.println(item.getValue());
	}

	@Test
	public void query() {
		String zxno = "0000000002";
		List<Zng1Tp> vos = zng1dao.find(zxno);
		System.out.println(vos.size());
	}

	@Test
	public void get() {
		String id = "40288a853482bfef0134a20b1e4c2e37";
		Khyf khyf = khyfBO.get(id);
		if (khyf == null) {
			System.out.println("khyf is null");
		}
		else {
			Khyf $khyf = khyfBO.getUnique(khyf.getChri(), khyf.getYsgs(), khyf.getUser(), khyf.getDhno(), khyf.getLine(), khyf.getShno(), khyf.getAddr());
			if ($khyf == null) {
				System.out.println("$khyf is null");
			}
			else {
				System.out.println("=======================" + $khyf.getId());
			}

		}

	}
}