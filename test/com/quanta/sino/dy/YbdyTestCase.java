package com.quanta.sino.dy;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.dy.bo.api.IYcaibqBO;
import com.quanta.sino.dy.vo.YcaibqVO;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

public class YbdyTestCase {

	private static IYcaitpBO bo;

	private static IYcaibqBO dyBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IYcaitpBO.class);
		dyBo = Helper.getBean(IYcaibqBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 获取整船信息
	 * </p>
	 */
	@Test
	public void getByShip() {
		List<YcaiTp> tps = bo.getByShip("船名称");
		if (tps.size() <= 0) {
			System.out.println("数据为空");
		}
		for (YcaiTp vo : tps) {
			System.out.println(vo.getJbno());
		}
	}

	/**
	 * <p>
	 * 获取原板主键字符串
	 * </p>
	 */
	@Test
	public void findByShip() {
		String print = dyBo.findByShip("船名称");
		System.out.println(print);
	}

	/**
	 * <p>
	 * 获取打印VO
	 * </p>
	 */
	@Test
	public void getYcaibqVO() {

		YcaibqVO vo = dyBo.get("000046");
		if (vo == null) {
			System.out.println("获取对象失败");
		}
		System.out.println(vo.getJbno());

	}

}
