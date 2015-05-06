package com.quanta.sino.fxs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.fxs.bo.api.IMktfxBO;
import com.quanta.sino.fxs.vo.MktfxQE;
import com.quanta.sino.orm.MktfxshJl;

public class MktfxTesCase {
	private static IMktfxBO dbo;

	// private static IYcaitpBO dbo1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbo = Helper.getBean(IMktfxBO.class);
		// dbo1 = Helper.getBean(IYcaitpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * <li/>获取涂油量 目标附着量 的值 先检查传进来的原材卷板号是否存在；<BR/>
	 * <li/>根据卷号获取卷板信息；<BR/>
	 * <li/>从卷板信息中得到订货号, 根据订货号获取DhuoTp信息 从DhuoTp信息中获取 涂油量和目标附着量
	 * </p>
	 */
	@Test
	public void get() {
		// dbo.getmubiao("123");
		// System.out.println("===========" + dbo.getMubiao("000001"));
	}

	/**
	 * <p>
	 * 保存马口铁数据记录
	 * </p>
	 */
	@Test
	public void save() {
		MktfxshJl entity = new MktfxshJl();
		entity.setJbno("12345678");
		entity.setFufm("22");
		entity.setXfzlCab(1.0);
		// dbo.save(entity);
	}

	/**
	 * <p>
	 * 更新马口铁数据记录
	 * </p>
	 */
	@Test
	public void update() {
		MktfxshJl entity = new MktfxshJl();
		entity.setId("402881392d4ea87c012d4ea883fd0001");
		entity.setJbno("12345678");
		entity.setFufm("23");

		// dbo.update(entity);
	}

	/**
	 * <p>
	 * 马口铁数据查询
	 * </p>
	 */
	@Test
	public void query() {
		MktfxQE page = new MktfxQE();
		// page.setColino("111");
		dbo.query(page);
		System.out.println("总记录数：" + page.getCount());
		System.out.println("========记录明细=======");
		for (MktfxshJl chatp : page.getDatas()) {
			System.out.println(chatp.getJbno());
		}
	}

	/**
	 * <p>
	 * 批量删除 马口铁数据
	 * </p>
	 */
	@Test
	public void deleteAll() {
		String[] ids = new String[2];
		ids[0] = "402881392e2c1e40012e2c1e48d90001";
		ids[0] = "402881392e2c1d6e012e2c1d78880001";
		// dbo.deletAll(ids);
	}

	/**
	 * <p>
	 * 根据ID号删除马口铁数据记录
	 * </p>
	 */
	@Test
	public void delete() {
		dbo.delete("402881392d4ed35a012d4ed4a3560002");
	}

}
