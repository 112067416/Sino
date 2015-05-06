package com.quanta.sino.kb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.bean.User;
import com.coco.core.env.Helper;
import com.coco.core.util.StringUtils;
import com.quanta.sino.kb.bo.api.IKbBO;
import com.quanta.sino.kb.constants.KbConstants;

/**
 * <p>
 * 捆包生产单元测试类
 * </p>
 * <p>
 * create: 2011-1-31 下午03:00:26
 * </p>
 * 
 * @author 黄美[mei.huang.miss@gmail.com]
 * @version 1.0
 */
public class KbTestCase {

	private static IKbBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IKbBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 保存设定的库位
	 * </p>
	 */
	@Test
	public void saveSet() {
		String[] ids = { "00477201002" };
		// KwVO vo = new KwVO();
		// vo.setKw("A09");
		// vo.setIds(StringUtils.join(Arrays.asList(ids), null));
		// bo.saveSet(vo, ids);
		// System.out.println("========================:");
	}

	/**
	 * <p>
	 * 保存设定的库位
	 * </p>
	 */
	@Test
	public void qurey() {
		// String ids = "00477201002;00477201002";
		// DkbcxQE page = new DkbcxQE();
		// // 表示不需要分页
		// page.setSize(-1);
		// System.out.println("ids=============:" + ids);
		// if (ids != null && !"".equals(ids)) {
		// // 分割列表所有id及选择清除的id
		// String[] arr = ids.split(";");
		// // 获取列表中所有id
		// String[] strs = arr[0].split(",");
		// // 定义新的数组获取比较不相同的值
		// String[] strsN = null;
		// // 定义接收不相同id的字符串
		// String nstr = "";
		// // 大于2表示存在清除id
		// if (arr.length >= 2) {
		// // 则获取清除id数组
		// String[] strsD = arr[1].split(",");
		// List<String> list = Arrays.asList(strsD);
		// for (int i = 0; i < strs.length; i++) {
		// // 获取列表id中不包含清除的id的值，串联起来
		// if (!list.contains(strs[i])) {
		// if (i > 1) {
		// nstr += ",";
		//
		// }
		// nstr += strs[i];
		// }
		// }
		// if ("".equals(nstr)) {
		//
		// nstr = null;
		// }
		// }
		// if (nstr != null) {
		// if ("".equals(nstr)) {
		// strsN = strs;
		// }
		// else {
		//
		// strsN = nstr.split(",");
		// }
		// page.setJbnos(strsN);
		// bo.query(page);
		// }
		// }
		// System.out.println("ids=======page.getDatas().size()======:"
		// + page.getDatas().size());

	}

	/**
	 * <p>
	 * 设定或取消紧急材
	 * </p>
	 */
	@Test
	public void updateSfjj() {
		String[] ids = { "00477201002", "00573502003" };
		bo.updateSfjj(ids, KbConstants.ISJJ_NO);
		System.out.println("ok ok ok ok!");
	}

	/**
	 * <p>
	 * 获取库位信息
	 * </p>
	 */
	@Test
	public void getKw() {
		String[] ids = { "00477201002", "00573502003" };
		// String kw = bo.getKw(ids);
		// System.out.println("ok ok ok ok!" + kw);
	}

	/**
	 * <p>
	 * 确认捆包及捆包撤销操作
	 * </p>
	 */
	@Test
	public void kbcx() {
		String[] ids = { "00477201002", "00573502003" };
		String bz = "A";
		User user = new User();
		String flag = KbConstants.ISKB_YK;
		user.setNo("000");
		user.setName("管理员");
		// bo.isKbcx(ids, bz, user, flag);
		System.out.println("==================333333=======------------:");
	}

	/**
	 * <p>
	 * 回退调整时对制品信息进行判断
	 * </p>
	 */
	@Test
	public void getZpPd() {
		String[] ids = { "00477201002", "00573502003" };
		// String zppd = bo.getZpPd(ids);
		// System.out.println("ok ok ok o?k!" + zppd);

	}

	public static void main(String args[]) {
		String[] array1 = { "1", "2", "3", "4", "3" };
		String[] array2 = { "1", "3" };
		String ne = "";
		List<String> list = Arrays.asList(array2);
		List<String> aList = new ArrayList<String>();

		// list.remove(array2);
		for (int i = 0; i < array1.length; i++) {
			if (!list.contains(array1[i])) {
				if (i > 1) {
					ne += ",";
				}
				ne += array1[i];
			}
			// 去掉同一个数组中的重复值
			if (!aList.contains(array1[i])) aList.add(array1[i]);
		}
		System.out.println("hehe:" + aList.size() + " haha");
		String nids = "";
		if (aList.size() > 0) {
			nids = StringUtils.join(aList.toArray(), ",");
		}
		System.out.println("nids=================:" + nids);
		for (int i = 0; i < aList.size(); i++)
			System.out.println("hehe:" + aList.get(i));

		System.out.println("list=================:" + list);
		System.out.println("list=======ne==========:" + ne);

	}

}
