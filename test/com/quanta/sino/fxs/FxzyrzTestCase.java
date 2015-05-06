package com.quanta.sino.fxs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.fxs.bo.api.IFxzyrzBO;
import com.quanta.sino.fxs.vo.FxzyQE;
import com.quanta.sino.orm.FxzyRz;

public class FxzyrzTestCase {
	private static IFxzyrzBO dbo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbo = Helper.getBean(IFxzyrzBO.class);
		// dbo1 = Helper.getBean(IYcaitpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void get() {
		String id = "40288a853017272901301f768a670650";
		FxzyRz entity = dbo.get(id);
		String bz = entity.getQt();
		System.out.println(bz.replaceAll("\n", "<br />"));
	}

	/**
	 * <p>
	 * 保存分析作业日志表
	 * </p>
	 */
	@Test
	public void save() {
		FxzyRz entity = new FxzyRz();
		entity.setAtcJrjs(11);
		entity.setAtcJrwc(11);
		// dbo.save(entity);
	}

	/**
	 * <p>
	 * 更新分析作业日志表
	 * </p>
	 */
	@Test
	public void update() {
		FxzyRz entity = new FxzyRz();
		entity.setId("40288a853017272901301f768a670650");
		entity.setAtcJrjs(11);
		entity.setAtcJrwc(12);
		// dbo.update(entity);
	}

	/**
	 * <p>
	 * 查询分析作业日志
	 * </p>
	 */
	@Test
	public void query() {
		FxzyQE page = new FxzyQE();
		// page.setDele("0");
		dbo.query(page);
		System.out.println("总记录数：" + page.getCount());
		System.out.println("========记录明细=======");
		for (FxzyRz chatp : page.getDatas()) {
			System.out.println(chatp.getId());
		}
	}

	/**
	 * <p>
	 * 删除作业分析日志
	 * </p>
	 */
	@Test
	public void delete() {
		String[] ids = new String[2];
		ids[0] = "402881392e2c1e40012e2c1e48d90001";
		ids[0] = "402881392e2c1d6e012e2c1d78880001";
		dbo.delete(ids);
	}

	/**
	 * <p>
	 * 根据传入的ID号 获取打印列表
	 * </p>
	 */
	@Test
	public void getFxzyPrints() {
		String[] ids = new String[2];
		ids[0] = "402881392e2c1e40012e2c1e48d90001";
		ids[0] = "402881392e2c1d6e012e2c1d78880001";
		// dbo.getFxzyPrints(ids);
	}

}
