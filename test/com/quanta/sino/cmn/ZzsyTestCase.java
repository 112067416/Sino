package com.quanta.sino.cmn;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.cmn.bo.api.IZzsyBO;
import com.quanta.sino.cmn.dao.api.IZzsyDAO;
import com.quanta.sino.orm.ZzsyMp;

public class ZzsyTestCase {

	private static IZzsyBO bo;

	private static IZzsyDAO zzsyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IZzsyBO.class);
		zzsyDAO = Helper.getBean(IZzsyDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getByKeyA() {
		// String ggno = "MC10";
		// String fudw = "WB";
		// String fuzm = "010";
		// String fufm = "010";
		// String pzno = "11";
		// String chdx = null;
		// String nwai = "1";
		// Double hmax = 0.6;
		// Double hmin = 0.14;
		// Double kmax = 1110.00;
		// Double kmin = 457.00;
		// Double cmax = 1110.0;
		// Double cmin = 457.0;RCCA,EH01
		String cond = "RCCA";
		String user = "EH01";

		// String cond = null;
		// String user = null;
		Double houu = 0.5;
		Double kuan = 800d;
		Double cang = 800d;

		String ggno = "MC40";
		String fudw = "WB";
		String fuzm = "025";
		String fufm = "025";
		String pzno = "11";
		String chdx = null;
		String nwai = "2";
		Double hmax = 0.6;
		Double hmin = 0.18;
		Double kmax = 1100.0;
		Double kmin = 457.0;
		Double cmax = 1100.0;
		Double cmin = 457.0;
		// cond = "RPRM";
		// ZzsyMp entity = bo.getByKey(ggno, fudw, fuzm, fufm, pzno, chdx, nwai,
		// hmax, hmin, kmax, kmin, cmax, cmin, houu, kuan, cang, cond, user);
		// Assert.assertNotNull(entity);
		// System.out.println(entity.getSyno());
		ZzsyMp entity = bo.getByKey(ggno, fudw, fuzm, fufm, pzno, chdx, nwai, houu, kuan, cang, cond, user);
		Assert.assertNotNull(entity);
		System.out.println(entity.getSyno());
	}

	@Test
	public void test() {
		String ql = "from ZzsyMp where ggno='MC40' and (chdx is null or chdx='' or chdx='D2') and hmax=0.6 and hmin=0.14 and kmax=1100.0 and kmin=457.0 and (cmax is null or cmax=0) and (cmin is null or cmin=0) and (houu is null or houu=0) and (kuan is null or kuan=0) and (cang is null or cang=0) and (cond is null or cond='' or cond='QYGA') and (user is null or user='' or user='ES01')";
		ZzsyMp zzsyMp = zzsyDAO.getByKey(ql);
		Assert.assertNotNull(zzsyMp);
		System.out.println(zzsyMp.getSyno());
	}

	/**
	 * <p>
	 * 获取新生成的号码
	 * </p>
	 */
	@Test
	public void getNewNo() {
		System.out.println(bo.getNewNo());
	}

}
