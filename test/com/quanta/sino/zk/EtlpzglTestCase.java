package com.quanta.sino.zk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.orm.EtlyygljlItem;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zk.bo.api.IEtlyyglBO;
import com.quanta.sino.zk.dao.api.IEtlpzglDAO;
import com.quanta.sino.zk.vo.EtlpzglQE;
import com.quanta.sino.zk.vo.EtlyyglQE;

/**
 * <p>
 * ETl品质管理记录表 单元测试
 * </p>
 * <p>
 * create: 2011-1-12 上午10:00:33
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class EtlpzglTestCase {

	private static IEtlpzglBO bo;

	private static IEtlyyglBO yybo;

	private static IEtlpzglDAO etlpzglDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IEtlpzglBO.class);
		yybo = Helper.getBean(IEtlyyglBO.class);
		etlpzglDAO = Helper.getBean(IEtlpzglDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testUpdate() {
		bo.updateStatDqjToYwc();
	}

	/**
	 * <p>
	 * <li/>根据sczt 字段 查询原材卷板当期卷和备用卷记录 <br/>
	 * <li/>状态=0（初始）状态=1 （备用卷）和状态=2（当前卷）状态=3（生产完成）的 原材卷板信息
	 * </p>
	 */
	@Test
	public void queryYcaiVO() {
		System.out.println("========记录明细=======");
		// List<YcaiVO> page = bo.findByStates();
		//
		// System.out.println("总记录数：" + page.size());
		// System.out.println("========记录明细=======");
		// // for (YcaiVO chatp : page) {
		// // System.out.println(chatp.getJbno());
		// // }
	}

	/**
	 * <p>
	 * <li/>获取24小时以内 ETL实时品质管理记录<br>
	 * <li/>from YcaiTp where scsj>dateadd(day,-1,getdate())
	 * </p>
	 */
	@Test
	public void queryEtlpzVO() {
		// List<EtlpzVO> page = bo.findByDates();
		// System.out.println("总记录数：" + page.size());
		// System.out.println("========记录明细=======");
		// for (EtlpzVO chatp : page) {
		// System.out.println(chatp.getJbno());
		// }
	}

	/**
	 * <p>
	 * 批量保存EtlpzGl记录，判断实体的jbno是否存在 ，存在的话保存数据。表格为双行表格 所以有一列是为空的
	 * </p>
	 */
	@Test
	public void saveall() {
		List<EtlpzGl> entity = new ArrayList<EtlpzGl>();
		EtlpzGl entity1 = new EtlpzGl();
		EtlpzGl entity2 = new EtlpzGl();
		entity2.setJbno("0000001");
		EtlpzGl entity3 = new EtlpzGl();
		entity3.setJbno("0000002");
		entity.add(entity1);
		entity.add(entity2);
		entity.add(entity3);
		System.out.println("===============" + entity2.getJbno());
		System.out.println("===============" + entity3.getJbno());
		// bo.saveAll(entity);
	}

	/**
	 * <p>
	 * 品质管理记录查询 <br/>
	 * 查询List<EtlpzVO> 和YcaiTp
	 * </p>
	 */
	@Test
	public void query() {
		EtlpzglQE page = new EtlpzglQE();
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		// List<EtlpzVO> list = new ArrayList<EtlpzVO>();
		// list = bo.query(page);
		bo.query(page);
		// System.out.println("总记录数：" + list.size());
		System.out.println("========记录明细=======");
		// for (EtlpzVO chatp : list) {
		// System.out.println(chatp.getJbno());
		// }
	}

	/**
	 * <p>
	 * 根据时间获取 Etlyygljl
	 * </p>
	 */
	@Test
	public void save() {
		// Etlyygljl etlyy = yybo.getByDate(new java.util.Date());
		Etlyygljl etlyy = null;
		System.out.println(etlyy);
	}

	/**
	 * <p>
	 * 药液管理记录查询 <br/>
	 * 查询EtlyyglQE
	 * </p>
	 * 
	 * @throws ParseException
	 */
	@Test
	public void queryEtlyyglQE() throws ParseException {
		EtlyyglQE page = new EtlyyglQE();
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		String date2 = "2011-02-01 08:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date cDate2 = df.parse(date2);
		Date dd2 = new Date(cDate2.getTime());

		page.setRqBegin(dd2);
		page.setRqEnd(dd2);
		yybo.query(page);
		System.out.println("总记录数：" + page.getCount());
		System.out.println("========记录明细=======");
		// for (YyfxjgJl chatp : page.getDatas()) {
		// System.out.println(chatp.getId());
		// }
	}

	/**
	 * <p>
	 * 批量保存Etl药液品质管理记录
	 * </p>
	 */
	@Test
	public void saveEtlyygljl() {
		List<EtlyygljlItem> list = new ArrayList<EtlyygljlItem>();
		Etlyygljl entity = new Etlyygljl();

		entity.setId("402881392e3682df012e36afe2350002");
		EtlyygljlItem item1 = new EtlyygljlItem();
		item1.setId("402881392e3682df012e36afe2350002");
		// EtlyygljlItem item2 = new EtlyygljlItem();
		// item2.setId("1234567890123456789012345678901234567892");
		list.add(item1);
		// list.add(item2);
		// yybo.saveAll(list, entity);
	}

	/**
	 * <p>
	 * 查询YyfxjgJl Etlyygl；<br>
	 * 然后根据通过药液分析记录查询的结果 根据id在EtlyygljlItem中取出记录组装为一个EtlyyglVO
	 * </p>
	 */
	@Test
	public void queryVo() {
		EtlyyglQE qentity = new EtlyyglQE();
		qentity.setRqBegin(new java.util.Date());
		yybo.query(qentity);
		// System.out.println("总记录数iiiiid：" + qentity.getEtlyygl());
		System.out.println("========记录明细=======");
		// System.out.println("总记录数：" + qentity.getVos().size());
		// for (EtlyyglVO chatp : qentity.getVos()) {
		// System.out.println(chatp.getId());
		// }
	}

	/**
	 * <p>
	 * 更新 ETL药液管理记录——从表
	 * </p>
	 */
	@Test
	public void updateYy() {

		EtlyygljlItem item1 = new EtlyygljlItem();
		item1.setId("1234567890123456789012345678901234567890");
		item1.setBz("1111");
		// yybo.update(item1);
	}

	/**
	 * <p>
	 * 根据jbno 获取最新的马口铁记录
	 * </p>
	 */
	@Test
	public void getByNew() {
		// MktfxshJl entity = bo.getByNew("000001");
		// System.out.println(entity);
	}

}
