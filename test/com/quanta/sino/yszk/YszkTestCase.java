package com.quanta.sino.yszk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.orm.Czgl;
import com.quanta.sino.orm.Fkfp;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.bo.api.IFkfpBO;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yszk.dao.api.ICzglDAO;
import com.quanta.sino.yszk.dao.api.IKhfkDAO;
import com.quanta.sino.yszk.vo.KhfkQE;
import com.quanta.sino.yszk.vo.KhfkVO;
import com.quanta.sino.yszk.vo.YszkQE;

/**
 * <p>
 * 应收账款管理模块单元测试类
 * </p>
 * <p>
 * create: 2011-1-31 上午09:20:32
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class YszkTestCase {

	private static IKhfkBO khBo;

	private static IFkfpBO ysBo;

	private static IKhfkDAO khfkDAO;

	private static ICzglDAO czglDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		khBo = Helper.getBean(IKhfkBO.class);
		ysBo = Helper.getBean(IFkfpBO.class);
		khfkDAO = Helper.getBean(IKhfkDAO.class);
		czglDAO = Helper.getBean(ICzglDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void queryCzgl() {
		// String id = "40288a853116afa60131170d345100cf";
		// List<Czgl> czgls = khBo.find(id);
		// List<Czgl> czgls = khBo.find(id);
		// if (czgls == null || czgls.size() == 0) {
		// System.out.println("is null=================");
		// }
		// else {
		// for (Czgl czgl : czgls) {
		// System.out.println("=========================" + czgl.getId());
		// System.out.println("========================="
		// + czgl.getKhfk().getId());
		// }
		// }
	}

	/**
	 * <p>
	 * 付款发票查询
	 * </p>
	 */
	@Test
	public void queryYszk() {
		YszkQE page = new YszkQE();
		ysBo.query(page);
		System.out.println("ok ok ok ok !" + page.getDatas().size());
	}

	/**
	 * <p>
	 * 付款发票中获取订货信息
	 * </p>
	 */
	@Test
	public void getDhInfo() {
		String dhno = "D112903";
		String line = "01";
		// String rel = ysBo.getDhInfo(dhno, line);
		// System.out.println("ok ok ok ok !" + rel);
	}

	/**
	 * <p>
	 * 新增或修改保存付款发票信息
	 * </p>
	 */
	@Test
	public void saveFp() {
		User user = new User();
		user.setName("管理员");
		user.setNo("000");
		Fkfp fkfp = new Fkfp();
		List<Fkfp> fkfps = new ArrayList<Fkfp>();
		fkfp.setDhno("D112903");
		fkfp.setLine("01");
		fkfp.setCang(10.25);
		fkfp.setHouu(0.566);
		fkfp.setKuan(5.25);
		fkfp.setChri(new Date());
		fkfp.setAbbr("KUISHI");
		fkfp.setUser("1002");
		fkfp.setGgno("DC10");
		fkfp.setHtdj(563.2);
		fkfp.setFpri(new Date());
		fkfp.setFudw("GM");
		fkfp.setFufm("011");
		fkfp.setFuzm("011");

		// fkfp.setFpdm("1002");
		// fkfp.setFpnm("KUISHI");
		fkfp.setKfzl(26.0);
		fkfp.setFpno("00024");
		fkfp.setKpdj(563.2);
		fkfp.setLxzr(50.0);
		fkfp.setZlzr(10.0);
		fkfp.setFpje(144119.2);
		fkfps.add(fkfp);
		// vo.setItems(fkfp);
		// ysBo.saveAll(fkfps, user);
		// ysBo.saveUpd(fkfps, user);
		System.out.println("ok ok ok ok!");

	}

	/**
	 * <p>
	 * 删除付款发票信息
	 * </p>
	 */
	@Test
	public void deleteFp() {
		String id = "53545555";
		String fpje = "0";
		ysBo.delete(id);
		System.out.println("ok ok ok!" + Double.valueOf(fpje));
	}

	/**
	 * <p>
	 * 计算发票金额
	 * </p>
	 */
	@Test
	public void getFkje() {
		Double kfzl = 256.0;
		Double kpdj = 563.2;
		Double lxzr = 50.0;
		Double zlzr = 10.0;
		// String fkje = ysBo.getFkje(kfzl, kpdj, lxzr, zlzr);
		// System.out.println("fkje=============:" + fkje);
		// System.out.println("fkje=========Double====:" +
		// Double.valueOf(fkje));
		// String fpje = "0";
		// System.out.println("fkje=========000====:" + Double.valueOf(fpje));

	}

	/**
	 * <p>
	 * 获取客户付款金额信息
	 * </p>
	 * 
	 * @throws ParseException
	 */
	@Test
	public void getKhfkInfo() throws ParseException {
		String date = "2011-02-14";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date crea = sdf.parse(date);
		String user = "1002";
		String ids = "402881fa2e21fbf3012e21fc15ac0001";
		// String fkje = ysBo.getKhfkInfo(crea, user, ids);
		// System.out.println("fkje==客户付款金额===========:" + fkje);
	}

	/**
	 * <p>
	 * 保存冲账相关操作
	 * </p>
	 * 
	 * @throws ParseException
	 */
	@Test
	public void saveCz() throws ParseException {
		String date = "2011-02-14";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date crea = sdf.parse(date);
		KhfkVO vo = new KhfkVO();
		// vo.setIds("402881fa2e21fbf3012e21fc15ac0001");
		vo.setId("402881fa2e2204d5012e22551f7f0001");
		// vo.setUser("1002");
		// vo.setAbbr("KUISHI");
		vo.setName("大连魁氏罐头");
		vo.setFkje(5642.2);
		vo.setFkye(5642.2);
		vo.setCrea(crea);
		// ysBo.saveCz(vo);
		System.out.println("ok ok ok ok !");
	}

	/**
	 * <p>
	 * 删除冲账关联
	 * </p>
	 */
	@Test
	public void deleteCzgl() {
		String id = "402881fa2dd970ee012dd995d7ed0002";
		// ysBo.deleteCzgl(id);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 查询客户付款信息
	 * </p>
	 */
	@Test
	public void queryKh() {
		KhfkQE page = new KhfkQE();
		page.setOrderBys(" crea desc ");
		khBo.query(page);
		System.out.println("ok ok ok!" + page.getDatas().size());
	}

	/**
	 * <p>
	 * 修改或删除保存客户付款信息
	 * </p>
	 */
	@Test
	public void saveOrUpdate() {
		Khfk tp = new Khfk();
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		tp.setId("402881fa2da7b0ab012da7b304520001");
		// tp.setUser("1002");
		// tp.setAbbr("KUISHI");
		tp.setName("大连魁氏罐头");
		tp.setFkje(5689.85);
		// khBo.save(tp, user);
		khBo.update(tp);
		System.out.println("ok ok ok!" + tp.getFkje());

	}

	/**
	 * <p>
	 * 保存冲账关联表信息
	 * </p>
	 */
	@Test
	public void saveCzgl() {
		Czgl cz = new Czgl();
		// ysBo.saveCzgl(cz);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 删除客户信息
	 * </p>
	 */
	@Test
	public void deleteKh() {
		String[] ids = { "402881fa2e2204d5012e22551f7f0001" };
		khBo.delete(ids);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 获取调整金额
	 * </p>
	 */
	@Test
	public void getTzje() {
		String id = "402881fa2dca780d012dca7a51510001";
		String tzje = khBo.getTzje(id);
		System.out.println("tzje========" + tzje);
	}

	/**
	 * <p>
	 * 保存调整金额
	 * </p>
	 */
	@Test
	public void saveTz() {
		KhfkVO vo = new KhfkVO();
		vo.setId("402881fa2e2204d5012e22551f7f0001");
		vo.setTzye(0.2);
		// khBo.saveTz(vo);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 撤销冲账需要修改的关联信息（包括表有：付款发票、客户付款、冲账关联）
	 * </p>
	 */
	@Test
	public void updateCxcz() {
		String id = "402881fa2e2204d5012e22551f7f0001";
		// khBo.updateCxcz(id);
		System.out.println("ok ok ok!");

	}

}
