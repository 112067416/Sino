package com.quanta.sino.fxs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.fxs.bo.api.ICyrzBO;
import com.quanta.sino.fxs.bo.api.ICyrzBO.UpdateType;
import com.quanta.sino.fxs.vo.CyrzQE;
import com.quanta.sino.orm.Cyrz;

/**
 * <p>
 * 采样日志单元测试
 * </p>
 * <p>
 * create: 2011-1-7 上午10:35:57
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CyrzTesCase {

	private static ICyrzBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ICyrzBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 保存
	 * </p>
	 */
	@Test
	public void save() {
		Cyrz entity = new Cyrz();
		entity.setBan("1");
		entity.setZu("B");
		entity.setZsno("D12345");
		entity.setJbno("10000004");
		entity.setDxl("2.5/5.0");
		entity.setTyl("D50");
		entity.setFxxms(new String[] { "油", "Sn", "Cr" });
		entity.setCywzs(new String[] { "T", "M" });
		entity.setBz("单元测试");
		// bo.save(entity);
	}

	/**
	 * <p>
	 * 更新
	 * </p>
	 */
	@Test
	public void update() {
		Cyrz entity = new Cyrz();
		entity.setId("D00002");
		entity.setSlxb("C");
		bo.update(entity, UpdateType.slsd);
	}

	/**
	 * <p>
	 * 查询
	 * </p>
	 */
	@Test
	public void query() {
		CyrzQE qe = new CyrzQE();
		// qe.setId("D00002");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			qe.setDateBegin(sdf.parse("2011-01-06"));
			qe.setDateEnd(sdf.parse("2011-01-07"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		bo.query(qe);

		for (Cyrz entity : qe.getDatas()) {
			System.out.println(entity.getJbno());
		}
	}

	/**
	 * <p>
	 * 获取一个未收单的数据给剪切线SL
	 * </p>
	 */
	@Test
	public void getForSl() {
		Cyrz entity = bo.getForSl("A");
		Assert.assertNotNull("没有数据", entity);
		System.out.println(entity.getJbno());
	}

	/**
	 * <p>
	 * 获取一个未收单或者未收样的数据给分析室
	 * </p>
	 */
	@Test
	public void getForFxs() {
		Cyrz entity = bo.getForFxs();
		Assert.assertNotNull("没有数据", entity);
		System.out.println(entity.getJbno());
	}

	/**
	 * <p>
	 * 隐藏通知
	 * </p>
	 */
	@Test
	public void updateState() {
		bo.updateState("D00003", "A", UpdateType.fxsd);
	}

}
