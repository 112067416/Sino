package com.quanta.sino.yygl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.coco.core.util.DateUtils;
import com.quanta.sino.orm.Chlld;
import com.quanta.sino.yygl.bo.api.IChlldBO;
import com.quanta.sino.yygl.vo.ChlldQE;
import com.quanta.sino.yygl.vo.ChlldVO;

/**
 * <p>
 * 次日出货联络单单元测试类
 * </p>
 * <p>
 * create: 2011-1-31 下午02:39:40
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class ChlldTestCase {
	private static IChlldBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IChlldBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 次日联络单保存
	 * </p>
	 */
	@Test
	public void save() {
		Chlld chlld = new Chlld();
		User user = new User();
		// Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);// 获取当前时间的后一天
		user.setNo("000");
		user.setName("管理员");
		chlld.setId("402881482d3a8c61012d3bb61da8000c");
		chlld.setDhno("0000010");// 订货No
		chlld.setLine("01");// 订货行号
		chlld.setChqi(calendar.getTime());// 出货日期
		chlld.setAbbr("COFCO");// 用户略称
		chlld.setUser("003");// 用户代码
		chlld.setChqi(new Date());
		bo.save(chlld, user);

	}

	/**
	 * <p>
	 * 次日联络单修改
	 * </p>
	 * void
	 */
	@Test
	public void update() {
		Chlld chlld = new Chlld();
		User user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);// 获取当前时间的后一天
		user.setNo("000");
		user.setName("管理员");
		chlld.setId("402881fa2d019eff012d019f18840001");
		chlld.setDhno("0000001");// 订货No
		chlld.setLine("01");// 订货行号
		chlld.setChqi(calendar.getTime());// 出货日期
		chlld.setAbbr("GUANBIN");// 用户略称
		chlld.setUser("002");// 用户代码
		chlld.setAddr("");
		// bo.update(chlld, user);
	}

	/**
	 * <p>
	 * 次日出货联络单查询
	 * </p>
	 */
	@Test
	public void index() {
		ChlldQE qe = new ChlldQE();
		qe.setSize(3);
		qe.setIndex(0);
		qe.setDdno("000");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);// 获取当前时间的后一天
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String da = sf.format(calendar.getTime());
		try {
			System.out.println("=======sf.parse(da)======" + sf.parse(da));
			qe.setChqiBegin(sf.parse(da));
			qe.setChqiEnd(sf.parse(da));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		bo.query(qe);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("页码数：" + qe.getSize());
		System.out.println("========记录明细=======");
	}

	/**
	 * <p>
	 * 删除次日出货联络单
	 * </p>
	 */
	@Test
	public void delete() {
		String[] ids = { "402881482d2a76a2012d2c0f59b9001d",
				"402881482d2a76a2012d2c0f2bc3001c" };
		bo.delete(ids);
		System.out.println("ok ok ok!");

	}

	/**
	 * <p>
	 * 次日出货联络单上锁及解锁
	 * </p>
	 */
	@Test
	public void updateLock() {
		String[] ids = { "402881fa2e084254012e086795b80003",
				"402881fa2da11006012da1137bbe0001" };
		// bo.updateLock(ids, ChlldStat.YS.key);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 获取传递设置或分解的信息
	 * </p>
	 */
	@Test
	public void getSetInfo() {
		String[] ids = { "402881fa2e084254012e086795b80003",
				"402881fa2da11006012da1137bbe0001" };
		// bo.getSetInfo(ids);
		// System.out.println("ok ok ok!" + bo.getSetInfo(ids));
	}

	/**
	 * <p>
	 * 处理获取打印数据集合
	 * </p>
	 */
	@Test
	public void getPrints() {
		Calendar calendar = Calendar.getInstance();
		System.out.println("calendar.getTime()====：" + calendar.getTime());
		calendar.add(Calendar.DATE, -1);// 获取当前时间的后一天
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String da = sf.format(calendar.getTime());
		System.out.println("jbddList.size()===dada===DateUtils.parse===："
				+ DateUtils.parse(da, "yyyy-MM-dd"));
		// List<Chlld> list = bo.findPrints(DateUtils.parse(da, "yyyy-MM-dd"));
		// System.out.println("jbddList.size()=======：" + list.size());
	}

	/**
	 * <p>
	 * 设置保存操作
	 * </p>
	 */
	@Test
	public void saveSet() {
		String[] ids = { "402881482d2a76a2012d2c0f59b9001d",
				"402881482d2a76a2012d2c0f2bc3001c" };
		ChlldVO entity = new ChlldVO();
		entity.setYsgs("020");
		entity.setYsnm("福州畅港公司 ");
		entity.setShno("15");
		entity.setShnm("福建");
		// bo.saveSet(entity, ids);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 保存联络单分解的信息
	 * </p>
	 */
	@Test
	public void saveAnal() {
		ChlldVO vo = new ChlldVO();
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// vo.setIds("402881fa2e084254012e086795b80003");
		vo.setYsgs("05");
		vo.setYsnm("福州畅港公司");
		vo.setShno("2");
		vo.setShnm("漳州");
		vo.setChzl(20.1);
		// bo.saveAnal(vo, user);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 验证是否存在订货db信息
	 * </p>
	 */
	@Test
	public void getDbInfo() {
		// String tp = bo.getDbInfo(null, "E111001", "1003");
		// System.out.println("tp===============:" + tp);
	}

	/**
	 * <p>
	 * 获取合计重量
	 * </p>
	 */
	@Test
	public void getHjzl() {
		String chqi = "2011-01-12";
		// List<Chlld> list = bo.findPrints(DateUtils.parse(chqi,
		// "yyyy-MM-dd"));
		// String hjzl = bo.getHjzl(list);
		// System.out.println("hjzl============:" + hjzl);
	}
}
