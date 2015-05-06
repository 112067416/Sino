package com.quanta.sino.yygl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.yygl.bo.api.IJbddBO;
import com.quanta.sino.yygl.vo.JbddItemQE;
import com.quanta.sino.yygl.vo.JbddQE;

/**
 * <p>
 * 基本订购单单元测试类
 * </p>
 * <p>
 * create: 2011-1-31 下午02:53:28
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class JbddTestCase {
	private static IJbddBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IJbddBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 新增保存基板订单操作
	 * </p>
	 */
	@Test
	public void saveJbItem() {
		JbddItem jbitem = new JbddItem();
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		jbitem.setStat("0");
		jbitem.setAbbr("NAN");
		jbitem.setHoua(0.300);
		// bo.saveItem(jbitem, user);
		System.out.println("jbitem.getAjb()==============:" + jbitem.getAjb());
	}

	/**
	 * <p>
	 * 获取修改的基板订单信息
	 * </p>
	 */
	@Test
	public void get() {
		String id = "402881fa2ce95084012ce9556e1b0002";
		String ret = bo.getForJs(id);
		System.out.println("========获取结果ret=======" + ret);
	}

	/**
	 * <p>
	 * 修改基板订单操作
	 * </p>
	 */
	@Test
	public void updateItem() {
		String id = "402881482d2598c9012d25b24970000f";
		JbddItem entity = bo.getItem(id);
		entity.setCalc("55");
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// bo.updateItem(entity, user);

	}

	/**
	 * <p>
	 * 获取基板订购单列表信息
	 * </p>
	 */
	@Test
	public void queryJbItem() {
		JbddItemQE qe = new JbddItemQE();
		qe.setSize(3);
		qe.setIndex(0);
		bo.queryItem(qe);
		System.out.println("总记录数：" + qe.getCount());
		System.out.println("页码数：" + qe.getSize());
		System.out.println("========记录明细=======");

	}

	/**
	 * <p>
	 * 复制生成方法
	 * </p>
	 */
	@Test
	public void copy() {
		String[] ids = { "402881fa2d2abac2012d2abcdf930002",
				"402881fa2cf38253012cf38eb5b90005" };
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// bo.copyItem(ids, user);
	}

	/**
	 * <p>
	 * 生成基板订购单
	 * </p>
	 */
	@Test
	public void build() {
		String[] ids = { "402881fa2cecfabb012cecfe63dc000e" };
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// bo.build(ids, user);
	}

	/**
	 * <p>
	 * 订购单查询列表
	 * </p>
	 */
	@Test
	public void queryJbdd() {
		JbddQE qe = new JbddQE();
		qe.setSize(3);
		qe.setIndex(0);
		bo.queryJbdd(qe);
		System.out.println("总记录数2：" + qe.getCount());
		System.out.println("页码数2：" + qe.getSize());
		System.out.println("========记录明细2====qe.getDatas()===" + qe.getDatas());
	}

	/**
	 * <p>
	 * 获取选择的打印信息
	 * </p>
	 */
	@Test
	public void getPrints() {
		String[] ids = { "4028813b2e271189012e2751dc970007",
				"4028813b2e271189012e27536c10000b" };
		// List<JbddItem> jbddList = bo.findPrints(ids);
		// System.out.println("jbddList.size()=======：" + jbddList.size());
	}

	/**
	 * <p>
	 * 根据中文件编号删除对应的信息
	 * </p>
	 * void
	 */
	@Test
	public void deleteItem() {
		String[] ids = { "4028813b2e271189012e2751dc970007",
				"402881fa2cf38253012cf38ad0d60004" };
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// bo.deleteItem(ids, user);
		System.out.println("ok ok ok!");
	}
}
