package com.quanta.sino.ss;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.orm.PlscItemLp;
import com.quanta.sino.ss.bo.api.ISsBO;
import com.quanta.sino.ss.dao.api.ISsDAO;
import com.quanta.sino.ss.vo.PlscLpQE;
import com.quanta.sino.ss.vo.SsBlVO;
import com.quanta.sino.ss.vo.SsItemVO;
import com.quanta.sino.ss.vo.SsRzVO;
import com.quanta.sino.ss.vo.SsVO;
import com.quanta.sino.ss.vo.SsXmVO;

/**
 * <p>
 * 分选业务单元测试
 * </p>
 * <p>
 * create: 2011-1-21 下午12:32:20
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SsTestCase {

	private static ISsBO bo;

	private static ISsDAO ssDAO;

	/**
	 * <p>
	 * </p>
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ISsBO.class);
		ssDAO = Helper.getBean(ISsDAO.class);
	}

	@Test
	public void testQueryPlscItemLp() {
		List<PlscItemLp> plscItemLps = ssDAO.findPlscItemLp("2c9081f83035254601303533cfb70013");
		System.out.println(plscItemLps.size());
	}

	@Test
	public void testQuery() {
		PlscLpQE qentity = new PlscLpQE();
		qentity.setSize(0);
		// 进度标记.精整实绩为选别过的
		// qentity.setJdjs(ZtConstants.ZPNG_JD_JDJS);
		// 必须是F堆场的
		// qentity.setDuic(DC.F.name);

		Calendar calendar = Calendar.getInstance();
		Date pssd = DateUtils.formatDate(calendar.getTime(), 8, 0);
		// vo.setPssd(DateUtils.parse(DateUtils.format(vo.getPssd(),
		// "yyyy-MM-dd")
		// + " 8:00:00", "yyyy-MM-dd hh:mm:ss"));
		Date pssd1 = DateUtils.add(pssd, Calendar.DAY_OF_MONTH, 1);
		System.out.println("=============================================="
				+ pssd1);
		qentity.setCreaBegin(pssd);
		qentity.setCreaEnd(DateUtils.add(pssd, Calendar.DAY_OF_MONTH, 1));
		// qentity.setBan(vo.getBan());
		// qentity.setZu(vo.getZu());
		qentity.setOrderBys(" a.crea asc");
		ssDAO.queryRZ(qentity);
	}

	/**
	 * <p>
	 * 制品合并包单元测试
	 * </p>
	 */
	@Test
	public void save() {
		List<SsItemVO> items = new ArrayList<SsItemVO>();
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("00006001001");
		jbnos.add("00006001002");
		// jbnos.add("00629601003");
		// jbnos.add("00629601004");
		// jbnos.add("00629601005");
		SsItemVO header = null;
		SsItemVO item = null;
		item = bo.fetchItem(jbnos.get(0), header);
		Assert.assertNotNull(item);
		items.add(item);
		header = item;
		item = bo.fetchItem(jbnos.get(1), header);
		Assert.assertNotNull(item);
		items.add(item);
		for (int i = 2; i < jbnos.size(); i++) {
			item = bo.fetchItem(jbnos.get(i), header);
			Assert.assertNotNull(item);
			items.add(item);
		}
		SsVO vo = new SsVO();
		vo.setItems(items);
		bo.build(vo);
		Assert.assertNotNull(vo.getJbno());
		vo.setZshu(1200);
		vo.setSjzl(1823);
		vo.setDeng("PAA");
		vo.setChan("1");
		vo.setQqdm("22");
		vo.setJdyn("A");
		vo.setJsyn("B");
		vo.setSffz("1");
		bo.save(vo);
	}

	/**
	 * <p>
	 * 消灭制品单元测试
	 * </p>
	 */
	@Test
	public void destroy() {
		String jbno = "00629601017";
		SsXmVO vo = bo.fetchXmVO(jbno);

		// 缺陷等级
		vo.setDeng1("1");
		vo.setDeng2("2");
		vo.setDeng3("3");
		vo.setDeng4("4");
		vo.setDeng5("5");
		vo.setDeng6("6");
		vo.setDeng7("7");
		vo.setDeng8("9");
		// 缺陷代码
		vo.setQxdm1("01");
		vo.setQxdm2("02");
		vo.setQxdm3("03");
		vo.setQxdm4("04");
		vo.setQxdm5("05");
		vo.setQxdm6("06");
		vo.setQxdm7("07");
		vo.setQxdm8("08");
		// 缺陷枚数
		vo.setZshu1(10);
		vo.setZshu2(20);
		vo.setZshu3(30);
		vo.setZshu4(40);
		vo.setZshu5(50);
		vo.setZshu6(60);
		vo.setZshu7(70);
		vo.setZshu8(80);

		// bo.destroy(vo);
	}

	/**
	 * <p>
	 * 导出Excel日志
	 * </p>
	 */
	@Test
	public void fetchRzExcel() {
		SsRzVO vo = new SsRzVO();
		OutputStream os = null;
		try {
			os = new FileOutputStream("E:\\ssrz.xls");
			bo.fetchRz(vo, os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
				}
			}
		}

	}

	/**
	 * <p>
	 * 导出步留明细Excel
	 * </p>
	 */
	@Test
	public void fetchBlmx() {
		SsBlVO vo = new SsBlVO();
		try {
			vo.setPxsdBegin(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-25"));
		}
		catch (ParseException e1) {
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream("E:\\blmx.xls");
			bo.fetchBlmx(vo, os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
				}
			}
		}

	}

	/**
	 * <p>
	 * 导出步留统计Excel
	 * </p>
	 */
	@Test
	public void fetchBltj() {
		SsBlVO vo = new SsBlVO();
		try {
			vo.setPxsdBegin(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-25"));
		}
		catch (ParseException e1) {
		}
		try {
			vo.setPxsdEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-28"));
		}
		catch (ParseException e1) {
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream("E:\\bltj.xls");
			bo.fetchBltj(vo, os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
				}
			}
		}

	}
}
