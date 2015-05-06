package com.quanta.sino.dh;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.dao.api.IDhDAO;
import com.quanta.sino.dh.vo.DhQE;
import com.quanta.sino.dh.vo.DhuoVO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;

/**
 * <p>
 * 订货管理模块的测试类
 * </p>
 * <p>
 * create: 2011-1-11 上午09:04:42
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class DhTestCase {
	private static IDhBO bo;

	private static IDhDAO dhDAO;

	// private static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IDhBO.class);
		dhDAO = Helper.getBean(IDhDAO.class);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getDhjd() {
		String dhno = "E111018", line = "011";
		// String dhnoAndLine = "E11101801";
		DhuoVO entity = dhDAO.getDhjd(dhno, line);
		if (entity == null) {
			System.out.println("dhuoTp is null");
		}
		else {
			System.out.println("dhno===" + entity.getDhno());
			System.out.println("line===" + entity.getLine());
		}

	}

	// public static void main(String[] args) {
	// // SQLServerDriver sqlServer = new SQLServerDriver();
	// // String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=Sino";
	// // Properties props = new Properties();
	// // props.put("username", "sa");
	// // props.put("password", "abc123");
	// // Connection conn = null;
	// // Statement stmt = null;
	// // PreparedStatement pstmt = null;
	// // ResultSet rs = null;
	// // try {
	// // conn = sqlServer.connect(url, props);
	// // stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
	// // ResultSet.CONCUR_READ_ONLY);
	// // rs =
	// //
	// stmt.executeQuery("select top 100 a.USER_,a.RSV1_,a.NAME_ from SINO_YONGMP a");
	// // pstmt =
	// //
	// conn.prepareStatement("select top 100 a.USER_,a.RSV1_,a.NAME_ from SINO_YONGMP a ");
	// // // pstmt.setString(1, "F001");
	// // rs = pstmt.executeQuery();
	// // while (rs.next()) {
	// // System.out.println(rs.getObject(1));
	// // System.out.println(rs.getObject(2));
	// // System.out.println(rs.getObject(3));
	// // System.out.println("========================================");
	// // }
	// // }
	// // catch (SQLException e) {
	// // e.printStackTrace();
	// // }
	// // String s = "111&lt111&gt111";
	// // String s1 = s.replaceAll("&lt", "<").replaceAll("&gt", ">");
	// // System.out.println(s1);
	// // String s2 = "abc";
	// // char[] chars = s2.toCharArray();
	// // char[] $chars = new char[chars.length];
	// // for (int i = chars.length - 1; i <= 0; i--) {
	// //
	// // }
	// HashSet<String> hset = new HashSet<String>();
	// hset.add("111");
	// hset.add("111");
	// hset.add("111");
	// for (Object s : hset.toArray()) {
	// System.out.println(s.toString());
	// }
	// TreeSet<String> ts = new TreeSet<String>();
	// ts.add("4");
	// ts.add("3");
	// ts.add("1");
	// ts.add("2");
	// for (Object s : ts.toArray()) {
	// System.out.println(s.toString());
	// }
	// }

	/**
	 * <p>
	 * 验证订货No和line的唯一性
	 * </p>
	 */
	@Test
	public void checkDhno() {
		String dhno = "D104143";
		String line = "01";
		DhuoTp entity = bo.get(new DhuoTpPk(dhno, line));
		if (entity != null) {
			System.out.println("行号与订货No在订货DB中已经存在!");
		}
	}

	/**
	 * <p>
	 * 订货DB新增或修改保存
	 * </p>
	 */
	@Test
	public void save() {
		DhuoTp tp = new DhuoTp();
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		tp.setDhno("D121001");
		tp.setLine("01");
		tp.setGgno("MB30");
		tp.setPzno("11");
		tp.setFudw("WB");
		tp.setFuzm("025");
		tp.setFufm("025");
		tp.setNwai("2");
		tp.setHouu(0.170);
		tp.setKuan(705.00);
		tp.setCang(826.00);
		tp.setCond("RGKA");
		tp.setUser("1003");
		tp.setAbbr("NAN");
		tp.setSsno("NAB");
		tp.setJhqi(new Date());
		tp.setJhnr("6");
		tp.setHtzl(50.00);
		tp.setJhqf("W");
		tp.setJhfz(10);
		tp.setJhzz(10);
		tp.setKbsq("9");
		tp.setKbsz("1200");
		tp.setHuac("311");
		tp.setFace("B");
		tp.setYyan("2");
		tp.setDmfx("C");
		tp.setKbao("A2D");
		tp.setYmax("60");
		tp.setYmin("54");
		tp.setFuzx(02.45);
		tp.setFuzs(05.05);
		tp.setFufx(02.45);
		tp.setFufs(05.05);
		tp.setYuny("T3BA");
		System.out.println("ssno length:" + tp.getSsno().length());
		// bo.update(tp, user);
		bo.save(tp, user);
		System.out.println("ok ok ok!");

	}

	/**
	 * <p>
	 * 根据key1与key2获取仕样信息
	 * </p>
	 */
	@Test
	public void getZzsy() {
		DhuoTp entity = new DhuoTp();
		entity.setCang(826.00);
		entity.setFudw("WB");
		entity.setFufm("025");
		entity.setFuzm("020");
		entity.setGgno("MC40");
		entity.setHouu(0.170);
		entity.setKuan(705.00);
		entity.setPzno("11");
		entity.setCond("RGKA");
		entity.setUser("1003");
		String ret = bo.getZzsy(entity);
		System.out.println("打印仕样内容：" + ret);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 通过订货no获取用户关联的信息（为实现统一订货no一样的时候表示的是同一个客户，通过行号表示一个可以有多个订单）
	 * </p>
	 */
	@Test
	public void getUserInfo() {
		String dhno = "E121001";
		// System.out.println("获取用户关联信息：" + bo.getUserInfo(dhno));
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 查询订货合同信息列表
	 * </p>
	 */
	@Test
	public void query() {
		DhQE page = new DhQE();
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setJhqiEnd(page.getJhqiBegin());
		// 若无查询条件时 默认显示状态为初始
		if (!page.isQuery()) {
			page.setStat(DhStat.newly.key);
		}
		page.setOrderBys(" crea desc");
		bo.query(page);
		System.out.println("ok ok ok!" + page.getDatas().size());
	}

	/**
	 * <p>
	 * 删除订货合同信息
	 * </p>
	 */
	@Test
	public void delete() {
		String[] ids = { "E121001-01", "D109286-64" };
		bo.delete(ids);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 通过合同重量及合同单价计算合同金额
	 * </p>
	 */
	@Test
	public void compJe() {
		Double htzl = 50.00;
		Double htdj = 15.2;
		// System.out.println("ok ok ok!" + bo.getCompJe(htzl, htdj));
	}

	/**
	 * <p>
	 * 保存订货合同结算条件信息
	 * </p>
	 */
	@Test
	public void saveJstj() {
		DhuoTp entity = new DhuoTp();
		entity.setDhno("E121001");
		entity.setLine("01");
		// entity.setYfkn("56%");
		// entity.setChkn("20%");
		// entity.setHfkn("24%");
		// entity.setQixn("1年");
		entity.setThqf("2");
		entity.setHtdj(15.2);
		entity.setHtje(760.0);
		// bo.saveJstj(entity);
		System.out.println("ok ok ok!" + entity.getHtje());
	}

	/**
	 * <p>
	 * 保存仕样确认信息
	 * </p>
	 */
	@Test
	public void saveSyqr() {
		DhuoTp tp = new DhuoTp();
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		// 订货信息
		tp.setDhno("E201102");
		tp.setLine("01");
		tp.setGgno("MB30");
		tp.setPzno("11");
		tp.setFudw("WB");
		tp.setFuzm("025");
		tp.setFufm("025");
		tp.setNwai("2");
		tp.setHouu(0.170);
		tp.setKuan(705.00);
		tp.setCang(826.00);
		tp.setCond("RGKA");
		tp.setUser("1003");
		tp.setAbbr("NAN");
		tp.setSsno("NAB");
		tp.setJhqi(new Date());
		tp.setJhnr("6");
		tp.setHtzl(50.00);
		tp.setJhqf("W");
		tp.setJhfz(10);
		tp.setJhzz(10);
		tp.setKbsq("9");
		tp.setKbsz("1200");
		tp.setHuac("311");
		tp.setFace("B");
		tp.setYyan("2");
		tp.setDmfx("C");
		tp.setKbao("A2D");
		tp.setYmax("60");
		tp.setYmin("54");
		tp.setFuzx(02.45);
		tp.setFuzs(05.05);
		tp.setFufx(02.45);
		tp.setFufs(05.05);
		tp.setYuny("T3BA");
		tp.setDdnm("管理员");
		tp.setDdno("000");
		// 录入的仕样信息
		tp.setJian("N");
		tp.setLtdw("P");
		tp.setYqty("50");
		tp.setYuny("T3BA");
		tp.setYmax("60");
		tp.setYmin("54");
		tp.setFuzs(5.05);
		tp.setFuzx(02.45);
		tp.setJiao(1.0);
		tp.setStat(DhStat.newly.key);// 确认
		System.out.println("ssno length:" + tp.getSsno().length());
		bo.doSyqr(tp, user);// 确认
		// bo.doSybc(tp, user);//保存
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 仕样确认信息上锁或解锁状态的修改及订货合同完成确认及撤销状态的修改（通过flag赋值决定进行是何操作）
	 * </p>
	 * void
	 */
	@Test
	public void updateStat() {
		User user = new User();
		user.setNo("000");
		user.setName("管理员");
		String[] ids = { "D109286-64", "T107001-01" };
		String flag = DhStat.locked.key;// 上锁操作...
		// bo.updateStat(ids, flag, user);
		System.out.println("ok ok ok!");
	}

	/**
	 * <p>
	 * 判断是否统一订货no
	 * </p>
	 */
	@Test
	public void loadDhqrb() {
		String[] ids = { "D105168-02", "D105168-01" };
		Boolean bool = true;
		for (int i = 0; i < ids.length - 1; i++) {
			System.out.println("i:" + ids[i].substring(0, 7));
			System.out.println("i+1:" + ids[i + 1].substring(0, 7));
			if (!ids[i].substring(0, 7).equals(ids[i + 1].substring(0, 7))) {
				System.out.println("1 1 1:" + i);
				bool = false;
				break;
			}
		}
		if (bool) {
			System.out.println("ok ok ok!");
		}
		else {
			System.out.println("no no no!");
		}
	}

	/**
	 * <p>
	 * 订货确认表打印
	 * </p>
	 */
	@Test
	public void getPrints() {
		String[] ids = { "D105158-02", "D105158-01", "D105158-01", "D105158-01" };
		List<DhuoTp> list = bo.findDhqrPrints(ids);
		System.out.println("list.size()==:" + list.size());
		System.out.println("list.get(0)=getDhno()=:" + list.get(0).getDhno());
	}

	/**
	 * <p>
	 * 仕样未确认打印
	 * </p>
	 */
	@Test
	public void getSyPrints() {
		String[] ids = { "D105167-02", "D105168-01" };
		User user = new User();
		user.setName("管理员");
		user.setNo("000");
		List<DhuoTp> list = bo.findSyPrints(ids, user);
		System.out.println("list.size()=Sy========:" + list.size());
		System.out.println("list.get(0)=getDhno()===Sy=====:"
				+ list.get(0).getDhno());

	}
}
