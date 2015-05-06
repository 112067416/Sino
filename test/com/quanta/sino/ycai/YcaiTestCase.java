package com.quanta.sino.ycai;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.WwhtTpPk;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;
import com.quanta.sino.ycai.vo.YcaiQE;

/**
 * <p>
 * 原板管理测试用例
 * </p>
 * <p>
 * create: 2010-12-30 上午11:36:59
 * </p>
 * 
 * @author 陈小攀[c_x_pvc@163.com]
 * @version 1.0
 */
public class YcaiTestCase {

	private static IYcaitpBO bo;
	private static ICodeBO codeBo;
	private static IYcaitpDAO ycaitpDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IYcaitpBO.class);
		codeBo = Helper.getBean(ICodeBO.class);
		ycaitpDAO = Helper.getBean(IYcaitpDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void totalZpzl() {
		String ship = "TONGYI";
		Long zpzl = ycaitpDAO.totalZpzl(ship);
		System.out.println(zpzl);
	}

	@Test
	public void checkShip() {
		String ship = "TONGYI";
		if (bo.isExistedShip(ship)) {
			System.out.println("the ship is exist");
		}
		else {
			System.out.println("the ship is not exist");
		}

		String zzsj = "6220500000";
		if (ycaitpDAO.isExisted(zzsj)) {
			System.out.println("the zzsj is exist");
		}
		else {
			System.out.println("the zzsj is not exist");
		}
	}

	@Test
	public void find() {
		List<String> jbnos = new ArrayList<String>();
		jbnos.add("000001000");
		// jbnos.add("000002");
		// jbnos.add("000003");
		// jbnos.add("000004");
		// jbnos.add("000005");
		// String[] jbnos = { "000001", "000002" };
		List<YcaiTp> tps = bo.find(jbnos);
		System.out.print(tps.size());
	}

	@Test
	public void saveTest() {
		System.out.println(bo.getForJs(new WwhtTpPk("OE76309", "018")));
	}

	/**
	 * <p>
	 * 保存原板信息
	 * </p>
	 */
	@Test
	public void save() {
		WwhtTp tp = bo.getWwht(new WwhtTpPk("A1D7Y810", "1"));
		if (tp == null) {
			System.out.println("合同信息不存在");
			return;
		}
		YcaiTp ycaiTp = new YcaiTp();
		// 此值每次不能重复 且为8位
		ycaiTp.setZzsj("12312325");
		ycaiTp.setZpzl(2);
		ycaiTp.setBlny(new Date());
		ycaiTp.setShip("船名称");
		ycaiTp.setYcbr(tp.getAbbr() == "" ? "" : tp.getAbbr());
		ycaiTp.setCgdj(tp.getHtdj());
		ycaiTp.setDeng(tp.getFpdj());
		ycaiTp.setYbno(tp.getHtno());
		ycaiTp.setLine(tp.getLine());
		ycaiTp.setFace(tp.getFace());
		ycaiTp.setGgnm(tp.getZrgg());
		// 预备1中暂存制造商规格略称
		ycaiTp.setRsv1(tp.getZzgg());
		ycaiTp.setPzno(tp.getPzno());
		ycaiTp.setSsno(tp.getSsno());
		ycaiTp.setXpho(tp.getHouu());
		ycaiTp.setXpkn(tp.getKuan());
		ycaiTp.setYblc(tp.getZrgg());
		ycaiTp.setZzny(tp.getHtdt());
		ycaiTp.setZzsd(tp.getZzsd());
		ycaiTp.setNjno(tp.getNeij());
		CodeItem item = null;
		item = codeBo.getCodeItem(CmnConstants.CODE_013, tp.getThqf());
		ycaiTp.setThqf(item == null ? "" : item.getValue());
		item = codeBo.getCodeItem(CmnConstants.CODE_018, tp.getZzgg());
		ycaiTp.setGgno(item == null ? "" : item.getValue());
		item = codeBo.getCodeItem(CmnConstants.CODE_019, tp.getZzgg());
		ycaiTp.setYuny(item == null ? "" : item.getValue());

		bo.save(ycaiTp);
	}

	/**
	 * <p>
	 * 原板查询
	 * </p>
	 */
	@Test
	public void query() {
		YcaiQE qe = new YcaiQE();
		qe.setSize(15);
		qe.setIndex(1);
		qe.setOrderBys("jbno desc");
		bo.query(qe);
		if (qe.getDatas().size() > 0) {
			System.out.println("1: " + qe.getDatas().get(0).getJbno() + ";  2:"
					+ qe.getDatas().get(qe.getDatas().size() - 1).getJbno());
		}
	}

	/**
	 * <p>
	 * 删除原板信息
	 * </p>
	 */
	@Test
	public void delete() {
		// String[] strs = new String[] { "000054", "000064" };
		// bo.deletAll(strs);

	}

	/**
	 * <p>
	 * 原板信息修改
	 * </p>
	 */
	@Test
	public void update() {
		YcaiTp entity = bo.get("0000094");
		// 设置修改重量
		if (entity == null) {
			System.out.println("实体对象为空");
			return;
		}
		entity.setYbno("A1D7Y812");
		entity.setLine("006");
		entity.setZpzl(10);
		CodeItem item = codeBo.getCodeItem(CmnConstants.CODE_020, entity.getNjno());
		entity.setNjno(item == null ? "" : item.getValue());
		item = codeBo.getCodeItem(CmnConstants.CODE_013, entity.getThqf());
		entity.setThqf(item == null ? "" : item.getValue());
		bo.update(entity);

	}

	/**
	 * <p>
	 * 获取采购合同信息
	 * </p>
	 */
	@Test
	public void get() {
		String str = bo.getForJs(new WwhtTpPk("0E76903", "001"));
		System.out.println(str);
	}

}
