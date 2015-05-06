package com.quanta.sino.cg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.WwhtTpPk;

/**
 * <p>
 * 采购合同模块测试用例
 * </p>
 * <p>
 * create time : 2010-12-10
 * </p>
 * 
 * @author 张红国
 */
public class WwhtTestCase {

	private static IWwhtBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IWwhtBO.class);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 保存采购合同用例
	 */
	@Test
	public void save() {
		WwhtTp wwtp = new WwhtTp();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			wwtp.setQyri(format.parse("2010-12-09"));
			wwtp.setZzsd("1");
			wwtp.setHtdt(format.parse("2010-12-14"));
			wwtp.setSsno("1");
			wwtp.setThqf("1");
			wwtp.setHtno("2010120");
			wwtp.setLine("01");
			wwtp.setZzgg("ddddd");
			wwtp.setZrgg("kkkkk");
			wwtp.setPzno("2");
			wwtp.setKuan(23.22);
			wwtp.setHtzl(33.09);
			wwtp.setFpdj("2");
			wwtp.setFace("3");
			wwtp.setHtdj(235.88);
			wwtp.setNeij("50");
			bo.save(wwtp);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除采购合同用例
	 */
	@Test
	public void delete() {
		List<String> ids = new ArrayList<String>();
		ids.add("971114-01");
		ids.add("971114-02");
		bo.delete(ids);
	}

	/**
	 * 更新采购合同用例
	 */
	@Test
	public void update() {
		WwhtTp wwtp = new WwhtTp();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			wwtp.setQyri(format.parse("2010-12-09"));
			wwtp.setZzsd("1");
			wwtp.setHtdt(format.parse("2010-12-14"));
			wwtp.setSsno("NE0");
			wwtp.setThqf("1");
			wwtp.setHtno("22222222");
			wwtp.setLine("01");
			wwtp.setZzgg("SPB-D-T25BA");
			wwtp.setZrgg("SPB-D-T25BA");
			wwtp.setAbbr("GGGGGGGGGG");
			wwtp.setPzno("14");
			wwtp.setKuan(2.22);
			wwtp.setHouu(1.2);
			wwtp.setHtzl(3.09);
			wwtp.setFpdj("GAA");
			wwtp.setFace("B");
			wwtp.setHtdj(235.88);
			wwtp.setNeij("419");
			wwtp.setQywl("1");
			// bo.update(wwtp);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 查看采购合同
	 * </p>
	 */
	@Test
	public void viewCg() {
		WwhtTp wwht = bo.get(new WwhtTpPk("0E76903", "033"));
		System.out.println(wwht.getHtno());
	}
}
