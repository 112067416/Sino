package com.quanta.sino.ycai;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.Attachment;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.cg.vo.DrVO;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.kcgl.bo.api.IKcglBO;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

/**
 * <p>
 * 文件导入测试用例
 * </p>
 * <p>
 * create: 2011-2-15 下午10:59:48
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DrTestCase {

	/**
	 * 供应商合同业务接口
	 */
	private static IWwhtBO wwhtBO;

	/**
	 * 原板进货管理业务接口
	 */
	private static IYcaitpBO ycaitpBO;

	/**
	 * 附件管理业务接口
	 */
	private static IAttachmentBO attachmentBO;

	/**
	 * 库存管理业务接口
	 */
	private static IKcglBO kcglBO;

	private static ICodeBO cmnBO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		wwhtBO = Helper.getBean(IWwhtBO.class);
		ycaitpBO = Helper.getBean(IYcaitpBO.class);
		attachmentBO = Helper.getBean(IAttachmentBO.class);
		kcglBO = Helper.getBean(IKcglBO.class);
		cmnBO = Helper.getBean(ICodeBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		CodeItem code = cmnBO.getCodeItem("SINO_UPLOAD_PATH", "0");
		if (code == null) {
			System.out.println("null");
		}
		System.out.println(code.getValue().replaceAll("//", "\\\\"));
		File file = new File(code.getValue());
		if (file.isDirectory()) {
			System.out.println(file.list().length);
		}
	}

	public void upload(Attachment entity, String path) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
			Assert.assertNotNull(is);
			byte[] bytes = new byte[2 * 1024];
			int len;
			try {
				while ((len = is.read(bytes)) > 0) {
					bos.write(bytes, 0, len);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (null != is) {
					is.close();
				}
				if (null != bos) {
					bos.close();
				}
			}
			entity.setStream(bos.toByteArray());
			entity.setLength(bos.toByteArray().length);
		}
		catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		// File file = new File("e:\\原板入库.txt");
		// try {
		// FileInputStream is = new FileInputStream(file);
		// byte[] bytes = new byte[2 * 1024];
		// ByteArrayOutputStream os = new ByteArrayOutputStream();
		// File newFile = new File("e:\\原板入库11.txt");
		// FileOutputStream fos = new FileOutputStream(newFile);
		// int len = 0;
		// while ((len = is.read(bytes)) > 0) {
		// os.write(bytes, 0, len);
		// }
		// String content = new String(os.toByteArray());
		// content = content + "\n1221111111";
		// fos.write(content.getBytes());
		// System.out.println(content);
		// }
		// catch (Exception e) {
		// e.printStackTrace();
		// }
		double d = Math.pow(2, 300);
		System.out.println(d % 7);
		for (int i = 1; i <= 100; i++) {
			if (isprime(i)) {
				System.out.println(i);
			}
		}
	}

	public static boolean isprime(int e) {
		for (int i = 3; i < e / 2 + 1; i++) {
			if (e % i == 0) return false;
		}
		return true;
	}

	/**
	 * <p>
	 * 供应商合同导入。文件包含内容如下：
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>htno</td>
	 * <td>供应商合同-行号</td>
	 * <td>文件中该列是将数据库表的供应商合同号和行号组合起来的。两者之间用'-'隔开。要验证供应商合同号的长度为7，并且行号为3。正确格式为：
	 * XXXXXXX-XXX</td>
	 * </tr>
	 * <tr>
	 * <td>zzsd</td>
	 * <td>制造商</td>
	 * <td>文件中该列存的是制造商代码。要验证制造商代码不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称。对应的码表为：012</td>
	 * </tr>
	 * <tr>
	 * <td>face</td>
	 * <td>表面</td>
	 * <td>验证表面不能为空和在码表中是否存在。对应的码表为：005</td>
	 * </tr>
	 * <tr>
	 * <td>houu</td>
	 * <td>尺寸.厚</td>
	 * <td>验证尺寸.厚不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>kuan</td>
	 * <td>尺寸.宽</td>
	 * <td>验证尺寸.宽不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>htzl</td>
	 * <td>重量</td>
	 * <td>验证合同重量不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>htdj</td>
	 * <td>单价</td>
	 * <td>验证合同单价不能为空并且大于0</td>
	 * </tr>
	 * <tr>
	 * <td>zzgg</td>
	 * <td>制造商规格略称</td>
	 * <td>验证制造商规格略称不能为空和在码表中是否存在,同时根据制造商规格略称获得中日达规格略称。对应的码表为：017</td>
	 * </tr>
	 * <tr>
	 * <td>abbr</td>
	 * <td>用户略称</td>
	 * <td>不用验证</td>
	 * </tr>
	 * <tr>
	 * <td>pzno</td>
	 * <td>品种</td>
	 * <td>验证品种不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表。对应的码表为：118和119</td>
	 * </tr>
	 * <tr>
	 * <td>neij</td>
	 * <td>内径</td>
	 * <td>验证内径不能为空和在码表中是否存在</td>
	 * </tr>
	 * <tr>
	 * <td>fpdj</td>
	 * <td>等级</td>
	 * <td>验证不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表。对应的码表为：103、104和105</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void importHt() {
		Attachment entity = new Attachment();
		entity.setName("wwhttp.xls");
		entity.setOriginalName("wwhttp.xls");
		entity.setContentType("application/octet-stream");
		entity.setCreateTime(new Date());
		entity.setType("DR");
		entity.setExt("xls");
		String path = "file/wwhttp.xls";
		upload(entity, path);
		attachmentBO.save(entity);

		DrVO vo = new DrVO();
		Date date = new Date();
		vo.setQyri(date);
		vo.setHtdt(date);
		vo.setThqf(Code013.rmb.key);
		vo.setSsnm("SUMITOMOGUANGZHOU");
		vo.setSsno("ZAG");
		// 手工设定一个附件
		vo.setAttachId(entity.getId());

		wwhtBO.importHt(vo);
	}

	/**
	 * <p>
	 * 原板清单导入
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>ybno</td>
	 * <td>供应商合同</td>
	 * <td style="text-align: left;">
	 * 文件中该列是将数据库表的供应商合同号和行号组合起来的。两者之间用'-'隔开。要验证供应商合同号的长度为7，并且行号为3。正确格式为：
	 * XXXXXXX -XXX。最后，还要判断该供应商合同在供应商合同表（SINO_WWHTTP）是否存在，因为原板卷必须有对应的供应商合同。<br>
	 * 原板表中的供应商合NO、 行号、原板客户、通货区分、制造商规格略称、中日达规格略称、制造商代码、商社代码、品种、等级、采购单价和制造年月日，
	 * 是通过供应商合同号到供应商合同表中查询得到。</td>
	 * </tr>
	 * <tr>
	 * <td>xpho</td>
	 * <td>厚</td>
	 * <td style="text-align: left;">判断供应商合同的尺寸.厚同原板清单的现品厚是否一致</td>
	 * </tr>
	 * <tr>
	 * <td>xpkn</td>
	 * <td>宽</td>
	 * <td style="text-align: left;">判断供应商合同的尺寸.宽同原板清单的现品宽是否一致</td>
	 * </tr>
	 * <tr>
	 * <td>zzsj</td>
	 * <td>制造商卷板NO</td>
	 * <td style="text-align: left;">验证制造商卷板号唯一和不能为空</td>
	 * </tr>
	 * <tr>
	 * <td>zzzp</td>
	 * <td>制造商制品NO</td>
	 * <td style="text-align: left;">不用验证</td>
	 * </tr>
	 * <tr>
	 * <td>tun</td>
	 * <td>重量</td>
	 * <td style="text-align: left;">验证原材卷板重量不能为空和大于0。同时根据原材卷板重量计算原板长度</td>
	 * </tr>
	 * <tr>
	 * <td>face</td>
	 * <td>表面</td>
	 * <td style="text-align: left;">判断供应商合同的表面同原板清单的表面是否一致</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">原板表中，以下字段的获取方式：
	 * <ul>
	 * <li>规格代码。根据制造商规格略称和码表ID:018，在码表中查询得到，取值。
	 * <li>运用规格。根据制造商规格略称和码表ID:019，在码表中查询得到，取值。
	 * <li>内径代码。根据内径值和码表ID:020，在码表中查询得到，取键。
	 * <li>是否保税。根据制造商代码和码表ID:012，在码表中查询得到，取备注。注：1：非保税，2：保税
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void importYbqd() {
		Attachment entity = new Attachment();
		entity.setName("ycaitp.xls");
		entity.setOriginalName("ycaitp.xls");
		entity.setContentType("application/octet-stream");
		entity.setCreateTime(new Date());
		entity.setType("DR");
		entity.setExt("xls");
		String path = "file/ycaitp.xls";
		upload(entity, path);
		attachmentBO.save(entity);

		com.quanta.sino.ycai.vo.DrVO vo = new com.quanta.sino.ycai.vo.DrVO();
		vo.setBlny(new Date());
		vo.setShip("SINO-SHIP 2011");
		// 手工设定一个附件
		vo.setAttachId(entity.getId());

		ycaitpBO.importYbqd(vo);
	}

	/**
	 * <p>
	 * 原板入库
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>operate</td>
	 * <td>操作类别</td>
	 * <td style="text-align: left;">验证操作类别是否存在和不能为空。（21：原板入库)和（31：制品入库）</td>
	 * </tr>
	 * <tr>
	 * <td>jbno</td>
	 * <td>原材卷板NO</td>
	 * <td style="text-align: left;">验证原材卷板NO是否存在和不能为空。</td>
	 * </tr>
	 * <tr>
	 * <td>kw</td>
	 * <td>库位</td>
	 * <td style="text-align: left;">验证库位和不能为空。库位标签以A开头，长度为4</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">原板表中，以下字段的获取方式：
	 * <ul>
	 * <li>堆场：A。
	 * <li>库位：文件库位值。
	 * <li>入库时间：当前时间。
	 * <li>原板状态：已入库
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void importYbrk() {
		// Attachment entity = new Attachment();
		// entity.setName("ybrk.txt");
		// entity.setOriginalName("ybrk.txt");
		// entity.setContentType("application/octet-stream");
		// entity.setCreateTime(new Date());
		// entity.setType("DR");
		// entity.setExt("txt");
		// String path = "file/ybrk.txt";
		// upload(entity, path);
		// attachmentBO.save(entity);
		//
		// com.quanta.sino.ycai.vo.DrVO vo = new com.quanta.sino.ycai.vo.DrVO();
		// // 手工设定一个附件
		// vo.setAttachId(entity.getId());
		File file = new File("c:\\原板入库(right).txt");
		if (!file.isFile()) {
			System.out.println("文件不存在");
		}
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		int len = 0;
		byte[] bytes = new byte[1024];
		try {
			bos = new ByteArrayOutputStream();
			is = new FileInputStream(file);
			while ((len = is.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String content = bos.toString();
		String[] ss = content.split("\n");
		// System.out.println(bos.toString());
		// ycaitpBO.importYbrk(content);
		// System.out.println("ss==" + ss.length);
	}

	/**
	 * <p>
	 * 制品入库
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>operate</td>
	 * <td>操作类别</td>
	 * <td style="text-align: left;">验证操作类别是否存在和不能为空。（21：原板入库)和（31：制品入库）</td>
	 * </tr>
	 * <tr>
	 * <td>jbno</td>
	 * <td>制品NO</td>
	 * <td style="text-align: left;">验证制品NO是否存在和不能为空。</td>
	 * </tr>
	 * <tr>
	 * <td>kw</td>
	 * <td>库位</td>
	 * <td style="text-align: left;">验证库位和不能为空。库位标签以G开头，长度为4</td>
	 * </tr>
	 * <tr>
	 * <td colspan="3" style="text-align: left;">制品表中，以下字段的获取方式：
	 * <ul>
	 * <li>堆场：G。
	 * <li>库位：文件库位值。
	 * <li>入库时间：当前时间。
	 * </ul>
	 * </td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void importZprk() {
		// Attachment entity = new Attachment();
		// entity.setName("zprk.txt");
		// entity.setOriginalName("zprk.txt");
		// entity.setContentType("application/octet-stream");
		// entity.setCreateTime(new Date());
		// entity.setType("DR");
		// entity.setExt("txt");
		// String path = "file/zprk.txt";
		// upload(entity, path);
		// attachmentBO.save(entity);
		//
		// com.quanta.sino.ycai.vo.DrVO vo = new com.quanta.sino.ycai.vo.DrVO();
		// // 手工设定一个附件
		// vo.setAttachId(entity.getId());
		// // kcglBO.importZprk(vo);
		File file = new File("c:\\原板入库(right).txt");
		if (!file.isFile()) {
			System.out.println("文件不存在");
		}
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		int len = 0;
		byte[] bytes = new byte[1024];
		try {
			bos = new ByteArrayOutputStream();
			is = new FileInputStream(file);
			while ((len = is.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String content = bos.toString();
		// System.out.println(bos.toString());
		// kcglBO.importZprk(content);
	}

	/**
	 * <p>
	 * 原板商品检查书导入
	 * </p>
	 * <table width="700" border="1" bordercolor="#000000" style="border-collapse:collapse; text-align:center;">
	 * <colgroup><col width="10%" /><col width="20%" /><col width="70%"
	 * /></colgroup>
	 * <tr>
	 * <th>字段</th>
	 * <th>题头</th>
	 * <th>说明</th>
	 * </tr>
	 * <tr>
	 * <td>zzsj</td>
	 * <td>制造商卷板NO</td>
	 * <td style="text-align: left;">
	 * 验证制造商卷板NO不能为空。同时，判断该制造商卷板NO在原板表中是否存在。只有状态为初始或已入库的原板，才能设置商品检查书信息</td>
	 * </tr>
	 * <tr>
	 * <td>cfcc</td>
	 * <td>C</td>
	 * <td style="text-align: left;">验证成分值C为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfsi</td>
	 * <td>Si</td>
	 * <td style="text-align: left;">验证成分值SI为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfmn</td>
	 * <td>Mn</td>
	 * <td style="text-align: left;">验证成分值MN为数据类型和不能为空。在文件传入值的基础上乘以100</td>
	 * </tr>
	 * <tr>
	 * <td>cfpp</td>
	 * <td>P</td>
	 * <td style="text-align: left;">验证成分值P为数据类型和不能为空。在文件传入值的基础上乘以1000</td>
	 * </tr>
	 * <tr>
	 * <td>cfss</td>
	 * <td>S</td>
	 * <td style="text-align: left;">验证成分值S为数据类型和不能为空。在文件传入值的基础上乘以1000</td>
	 * </tr>
	 * <tr>
	 * <td>ying</td>
	 * <td>硬度</td>
	 * <td style="text-align: left;">验证硬度为数据类型和不能为空</td>
	 * </tr>
	 * <tr>
	 * <td>chui</td>
	 * <td>吹练NO</td>
	 * <td style="text-align: left;">验证吹练NO不能为空</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void importJczms() {
		Attachment entity = new Attachment();
		entity.setName("jchatp.xls");
		entity.setOriginalName("jchatp.xls");
		entity.setContentType("application/octet-stream");
		entity.setCreateTime(new Date());
		entity.setType("DR");
		entity.setExt("xls");
		String path = "file/jchatp.xls";
		upload(entity, path);
		attachmentBO.save(entity);

		com.quanta.sino.ycai.vo.DrVO vo = new com.quanta.sino.ycai.vo.DrVO();
		// 手工设定一个附件
		vo.setAttachId(entity.getId());
		ycaitpBO.importJczms(vo);
	}
}
