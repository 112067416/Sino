package com.quanta.sino.ch;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.dao.api.IZng2DAO;
import com.quanta.sino.ch.dao.api.IZxzsDAO;
import com.quanta.sino.ch.vo.ChsjVO;
import com.quanta.sino.ch.vo.ZxzsDyQE;
import com.quanta.sino.ch.vo.ZxzsQE;
import com.quanta.sino.ch.vo.ZxzsVO;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.Zng1Tp;
import com.quanta.sino.orm.Zng2Tp;
import com.quanta.sino.yygl.bo.api.IChlldBO;

/**
 * <p>
 * 装箱指示书测试用例
 * </p>
 * <p>
 * create: 2010-12-30 上午11:37:24
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZxzsTestCase {

	private static IZxzsBO zbo;

	private static IZng2DAO zng2dao;

	private static IZxzsDAO zxzsDAO;

	/**
	 * 出货联络单业务处理接口
	 */
	private static IChlldBO cbo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		zbo = Helper.getBean(IZxzsBO.class);
		cbo = Helper.getBean(IChlldBO.class);
		zng2dao = Helper.getBean(IZng2DAO.class);
		zxzsDAO = Helper.getBean(IZxzsDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void queryZxnos() {
		// String dhno = "D115374";
		// String line = "03";
		// String jbno = "00885801012";
		String dhno = "";
		String line = "";
		String jbno = "";
		List<String> zxnos = zbo.queryZxnos(dhno, line, jbno);
		if (zxnos.size() == 0) {
			System.out.println("size============is null");
		}
		else {
			for (String zxno : zxnos) {
				System.out.println("zxno============" + zxno);
			}
		}
	}

	@Test
	public void queryNum() {
		long num = zbo.getNum(DateUtils.parse("2011-06-01", "yyyy-MM-dd"), DateUtils.parse("2011-06-30", "yyyy-MM-dd"));
		System.out.println("num==============" + num);
	}

	@Test
	public void queryChtjByPzno() {
		Map<Date, List<ChsjVO>> chsjs = zbo.queryChtjByPzno(DateUtils.parse("2011-06-06", "yyyy-MM-dd"), DateUtils.parse("2011-06-22", "yyyy-MM-dd"));
		List<ChsjVO> vos = null;
		for (Date chqi : chsjs.keySet()) {
			vos = chsjs.get(chqi);
			System.out.println("出货日期：" + DateUtils.format(chqi, "yyyy-MM-dd"));
			for (ChsjVO vo : vos) {
				// vo.setPzno((String) row[1]);
				// vo.setSpbh((String) row[2]);
				// vo.setChzl((Double) row[3]);
				System.out.println("pzno===" + vo.getPzno() + "; spbh==="
						+ vo.getSpbh() + ";   chzl===" + vo.getChzl());

			}
		}
	}

	@Test
	public void queryZxzsa() {
		// List<ChtjVO> vos = zxzsDAO.queryChtj(DateUtils.parse("2001-01-02",
		// "yyyy-MM-dd"), DateUtils.parse("2021-01-02", "yyyy-MM-dd"));
		// if (vos != null && vos.size() > 0) {
		// for (ChtjVO vo : vos) {
		// System.out.println("chri==============" + vo.getChri());
		// }
		// }
		// zbo.outZxzs(null, null);
		// zng2dao.get("====================");
		// List<ZxzsVO> vos = zng2dao.queryZxzs();
		// for (ZxzsVO vo : vos) {
		// System.out.println(vo.getZxno());
		// }
		// String zxno = "0000077875";
		// String jbnos = zng2dao.queryJbnos(zxno);
		// System.out.println(jbnos);
		// zng2dao.findBzqdVos(null, null);
	}

	@Test
	public void outZxzs() {
		// zbo.outZxzs();
		// zbo.outZxzs("0000077451");
	}

	/**
	 * <p>
	 * 装箱指示书做成
	 * </p>
	 */
	@Test
	public void save() {
		ZxzsVO vo = new ZxzsVO();
		// 先找出一个今日出货联络单
		Chlld chlld = cbo.get("402881162e276c26012e276d10460001");
		if (chlld == null) {
			System.out.println("出货联络单信息不存在");
			return;
		}
		vo.setAbbr(chlld.getAbbr());
		vo.setChsu(2);
		vo.setChzl(14d);
		vo.setChqi(chlld.getChqi());
		vo.setDhno(chlld.getDhno());
		vo.setLine(chlld.getLine());
		vo.setPid(chlld.getId());
		vo.setShnm(chlld.getShnm());
		vo.setShno(chlld.getShno());
		vo.setUser(chlld.getUser());
		vo.setYsgs(chlld.getYsgs());
		vo.setYsnm(chlld.getYsnm());
		// 设置装箱指示书的制品
		vo.setZpnos("00006701,00006702");
		User user = Context.currentUser();
		zbo.save(vo, user);
	}

	@Test
	public void query() {
		ZxzsDyQE page = new ZxzsDyQE();
		page.setSize(-1);
		// page.setOrderBys("zng1Tp.dhno,zng1Tp.line");
		Zng1Tp zng1Tp = null;
		Assert.assertNotNull(zng1Tp);
		// page.setZxno(zng1Tp.getZxno());
		// zbo.queryZng(page);
		System.out.println("总记录数：");
		System.out.println("========记录明细=======");
		if (page.getDatas().size() == 0) {
			System.out.println("========记录weikong=======");
		}
		else {
			System.out.println("========记录明细=======" + page.getDatas().size());
			for (Zng2Tp tp : page.getDatas()) {
				// System.out.println(tp.getZpno());
				System.out.println(tp.getZng1Tp().getDhno());
			}
		}
	}

	@Test
	public void queryZxzs() {
		ZxzsQE qentity = new ZxzsQE();
		zbo.queryZxzs(qentity);
		List<Zng1Tp> vos = null;
		// List<Zng1Tp> vos = qentity.getDatas();
		if (vos == null || vos.size() == 0) {
			System.out.println("null==================");
		}
		// System.out.println(vos.size());
		// for (Zng1Tp vo : vos) {
		// // System.out.println("zxno=" + vo.getZxno());
		// // System.out.println("abbr=" + vo.getAbbr());
		// System.out.println("chsu=" + vo.getChsu());
		// System.out.println("chzl=" + vo.getChzl());
		// }
	}

	/**
	 * <p>
	 * 获取装箱指示书的页数
	 * </p>
	 */
	@Test
	public void getSize() {
		// 每页10条记录
		// int pageSize = zbo.getSize("402881492db7aaab012db7d827d1000a", 10);
		// System.out.println(pageSize);
	}

	/**
	 * <p>
	 * 获取内外销
	 * </p>
	 */
	@Test
	public void getNwai() {
		System.out.println(zbo.getNwai(new DhuoTpPk("D106196", "01")));
	}

}
