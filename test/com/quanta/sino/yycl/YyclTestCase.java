package com.quanta.sino.yycl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.etl.vo.ZpQE;
import com.quanta.sino.orm.Xpxx;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;
import com.quanta.sino.yccl.bo.api.IXpxxBO;

/**
 * <p>
 * 异常处理模块单元测试
 * </p>
 * <p>
 * create: 2011-1-20 下午02:16:57
 * </p>
 * 
 * @author 肖俊勇[xiaojunyonglb@163.com]
 * @version 1.0
 */
public class YyclTestCase {

	private static IXpxxBO bo;
	private static IYcaitpDAO dao;
	private static IZpBO zpBo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IXpxxBO.class);
		dao = Helper.getBean(IYcaitpDAO.class);
		zpBo = Helper.getBean(IZpBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 根据制品号 获取原材卷板信息 或者制品在库db信息
	 * </p>
	 */
	@Test
	public void getForJs() {
		System.out.println("========记录明细=======");
		// bo.getForJs("123456");
		System.out.println(bo.getForJs("0000001"));

	}

	/**
	 * <p>
	 * 更新原材卷板信息
	 * </p>
	 */
	@Test
	public void update() {
		YcaiTp entity = dao.get("000001");
		entity.setSjzl(11);
		// bo.updateYcaiTp(entity);
	}

	/**
	 * <p>
	 * 保存原材卷板信息
	 * </p>
	 */
	@Test
	public void save() {
		YcaiTp entity = new YcaiTp();
		entity.setJbno("004268");
		entity.setRsv1("0000001");
		// bo.saveYcaiTp(entity);
	}

	/**
	 * <p>
	 * 保存现品信息表
	 * </p>
	 */
	@Test
	public void saveXpxx() {
		Xpxx entity = new Xpxx();
		entity.setJbno("004267");

		// 保存更新日志
		// bo.save(entity);

	}

	@Test
	public void getZp() {
		// bo.getZpngTp("00420921001");

		YcaiTp newYcai = new YcaiTp();
		newYcai.setJbno("12345");
		newYcai.setAbbr(null);
	}

	/**
	 * 根据卷板号删除制品信息
	 */
	@Test
	public void delete() {
		// bo.deleteYcaiTp("004290");
	}

	/**
	 * 查询制品信息
	 */
	@Test
	public void query() {
		ZpQE page = new ZpQE();
		page.setJbno("00");
		// bo.queryZpngTp(page);
		System.out.println("总记录数：" + page.getCount());
		System.out.println("========记录明细=======");
		for (ZpngTp chatp : page.getDatas()) {
			System.out.println(chatp.getJbno());
		}
	}

	/**
	 * <p>
	 * 批量更新业连NO
	 * </p>
	 */
	@Test
	public void updateYlno() {
		String[] str = new String[2];
		str[0] = "000001";
		str[0] = "000002";
		String ylno = "1-123456";
		// bo.updateYlno(str, ylno);
	}

	/**
	 * <p>
	 * 修改原版或者制品在库DB作业停止标记
	 * </p>
	 */
	@Test
	public void updateZytz() {
		String[] str = new String[2];
		str[0] = "000001";
		str[0] = "000002";
		System.out.println("====");
		// bo.updateZytz(str, "GE", "USER", "作业理由");

	}

	/**
	 * <p>
	 * 批量更新 更新原材卷板或者制品在库db 堆场信息
	 * </p>
	 */
	@Test
	public void updateDc() {
		// DcVO item1 = new DcVO();
		// item1.setJbno("000001");
		// item1.setKw("F");
		// DcVO item2 = new DcVO();
		// item2.setJbno("000002");
		// item1.setKw("F");
		// DcsVO dcs = new DcsVO();
		// DcVO[] items = new DcVO[2];
		// items[0] = item1;
		// items[1] = item2;
		// dcs.setItems(items);
		// bo.updateDc(dcs);
	}

	/**
	 * <p>
	 * 修改制品在库DB 制品信息
	 * </p>
	 */
	@Test
	public void updateZpngTp() {
		ZpngTp entity = new ZpngTp();
		// entity.setJbno("00146703");

		entity = zpBo.getZp("00145801");
		System.out.println(entity.getSjzl());

		System.out.println("==" + entity.getScbj());
		// bo.updateZpngTp(entity);
	}

	/**
	 * <p>
	 * 修改原材卷板 制品信息
	 * </p>
	 */
	@Test
	public void updateYcaiTp() {
		YcaiTp entity = dao.get("001446");

		// entity.setBanz("A");
		// bo.updateYcaiTp(entity);
	}

	/**
	 * <p>
	 * 删除原材卷板 制品信息
	 * </p>
	 */
	@Test
	public void deleteYcaiTp() {
		// bo.deleteYcaiTp("000001");
	}

	/**
	 * <p>
	 * 删除制品在库db 制品信息
	 * </p>
	 */
	@Test
	public void deleteZpngTp() {
		// bo.deleteYcaiTp("000001");
	}
}
