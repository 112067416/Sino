package com.quanta.sino.ch;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.ITsBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.TsQE;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.TsTp;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 投诉业务测试
 * </p>
 * <p>
 * create: 2011-2-15 下午05:58:11
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class TsTestCase {

	private static ITsBO bo;

	private static IZpBO zpBo;

	private static IZxzsBO zsBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ITsBO.class);
		zpBo = Helper.getBean(IZpBO.class);
		zsBo = Helper.getBean(IZxzsBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getForJs() {
		// Zng2Tp tp = zsBo.get("D201202", "04", "0000000027", "00146502");
		// if (tp == null) {
		// System.out.println("null");
		// }
		// String result = bo.getForJs("D20120204", "00146502", "0000000027");
		// System.out.println("see the result==" + result);
	}

	/**
	 * <p>
	 * 保存投诉信息
	 * </p>
	 */
	@Test
	public void save() {
		TsTp tp = new TsTp();
		ZpngTp zp = zpBo.getZp("00006001002");
		if (zp == null) {
			System.out.println("制品信息为空");
			return;
		}
		tp.setJbno(zp.getJbno());
		tp.setDhno(zp.getDhno().substring(0, zp.getDhno().length() - 2));
		tp.setLine(zp.getDhno().substring(zp.getDhno().length() - 2));
		tp.setAbbr(zp.getAbbr());
		tp.setXpcn(zp.getXpcn());
		tp.setXpho(zp.getXpho());
		tp.setXpkn(zp.getXpkn());
		tp.setDeng(zp.getDeng());
		tp.setFace(zp.getFace());
		tp.setGgno(zp.getGgno());
		tp.setScfm(zp.getScfm());
		tp.setSczm(zp.getSczm());
		tp.setZpzl(zp.getZpzl());
		tp.setZshu(zp.getZshu());
		tp.setChri(zp.getChsd());
		tp.setTsri(new Date());
		tp.setBz("备注备注");
		// bo.save(tp);

	}

	/**
	 * <p>
	 * 查询投诉信息
	 * </p>
	 */
	@Test
	public void query() {
		TsQE qe = new TsQE();
		qe.setSize(15);
		qe.setIndex(0);
		bo.query(qe);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("========记录明细=======");
		for (TsTp tp : qe.getDatas()) {
			System.out.println(tp.getJbno());
			System.out.println(tp.getId());
		}
	}

	/**
	 * <p>
	 * 修改投诉信息
	 * </p>
	 */
	@Test
	public void update() {
		// TsTp tp = bo.get("402881492da148b5012da14966b80001");
		// tp.setChri(new Date());
		// bo.update(tp);
	}

	/**
	 * <p>
	 * 删除投诉信息
	 * </p>
	 */
	@Test
	public void delete() {
		TsQE qe = new TsQE();
		qe.setSize(15);
		qe.setIndex(0);
		bo.query(qe);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("========记录明细=======");
		for (TsTp tp : qe.getDatas()) {
			System.out.println(tp.getJbno());
			System.out.println(tp.getId());
		}
		// bo.delete("402881492da28607012da29c0dab0001");
		bo.query(qe);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("========记录明细=======");
		for (TsTp tp : qe.getDatas()) {
			System.out.println(tp.getJbno());
			System.out.println(tp.getId());
		}
	}

}
