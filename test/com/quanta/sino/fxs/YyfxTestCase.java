package com.quanta.sino.fxs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.fxs.bo.api.IYyfxjgjlBO;
import com.quanta.sino.fxs.vo.YyfxQE;
import com.quanta.sino.orm.YyfxjgJl;

public class YyfxTestCase {
	private static IYyfxjgjlBO dbo;

	// private static IYcaitpBO dbo1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbo = Helper.getBean(IYyfxjgjlBO.class);
		// dbo1 = Helper.getBean(IYcaitpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 查询药液分析记录
	 * </p>
	 */
	@Test
	public void query() {
		YyfxQE page = new YyfxQE();
		dbo.query(page);
		System.out.println("总记录数：" + page.getSize());
		System.out.println("========记录明细=======");
		// for (YyfxjgJl chatp : page.getDatas()) {
		// System.out.println(chatp.getId());
		// System.out.println(chatp.getHxclyCrFt());
		// }

	}

	/**
	 * <p>
	 * 保存药液分析记录
	 * </p>
	 */
	@Test
	public void save() {
		YyfxjgJl entity = new YyfxjgJl();

		// entity.setHxclyCrTwo(2.22);
		// entity.setHxclyCrThree(2.22);
		// dbo.save(entity);
	}

	/**
	 * <p>
	 * 根据ID号来删除马口铁记录
	 * </p>
	 */
	@Test
	public void delete() {
		dbo.delete("402881392d553bdc012d553be3680001");
	}

	/**
	 * <p>
	 * 更新药液分析记录
	 * </p>
	 */
	@Test
	public void update() {
		YyfxjgJl entity = new YyfxjgJl();
		entity.setId("402881392d553bdc012d553be3680001");
		entity.setBanzu("B");
		// dbo.update(entity);

	}

	/**
	 * <p>
	 * 批量删除药液分析记录
	 * </p>
	 */
	@Test
	public void deleteAll() {
		String[] ids = new String[2];
		ids[0] = "402881392e2c1e40012e2c1e48d90001";
		ids[0] = "402881392e2c1d6e012e2c1d78880001";
		// dbo.deletAll(ids);
	}
}
