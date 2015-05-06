package com.coco.sys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.IConvertBO;
import com.coco.sys.constants.ConverterType;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.orm.ConvertField;
import com.coco.sys.vo.ConvertErrorVO;
import com.coco.sys.vo.ConvertRowVO;
import com.quanta.sino.orm.YcaiTp;

/**
 * <p>
 * 转换器单元测试
 * </p>
 * <p>
 * create: 2010-12-31 上午11:53:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ConvertTestCase {

	private static IConvertBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IConvertBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void saveTest() {
		// ConvertEntity entity;
		// ConvertField field;
		// List<ConvertField> fields;
		// entity = new ConvertEntity();
		// entity.setId("Sample");
		// entity.setName("范例");
		// entity.setType(ConverterType.excel.type);
		//
		// fields = new ArrayList<ConvertField>();
		// field = new ConvertField();
		// field.setField("name");
		// field.setTitle("姓名");
		// fields.add(field);
		// field = new ConvertField();
		// field.setField("mobile");
		// field.setTitle("电话");
		// fields.add(field);

	}

	@Test
	public void saveWwhttp() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = new ConvertEntity();
		entity.setId("Wwhttp");
		entity.setName("供应商合同导入");
		entity.setType(ConverterType.excel.type);

		fields = new ArrayList<ConvertField>();

		field = new ConvertField();
		field.setField("htno");
		field.setTitle("供应商合同-行号");
		fields.add(field);

		field = new ConvertField();
		field.setField("zzsd");
		field.setTitle("制造商");
		fields.add(field);

		field = new ConvertField();
		field.setField("face");
		field.setTitle("表面");
		fields.add(field);

		field = new ConvertField();
		field.setField("houu");
		field.setTitle("尺寸.厚");
		fields.add(field);

		field = new ConvertField();
		field.setField("kuan");
		field.setTitle("尺寸.宽");
		fields.add(field);

		field = new ConvertField();
		field.setField("htzl");
		field.setTitle("重量");
		fields.add(field);

		field = new ConvertField();
		field.setField("htdj");
		field.setTitle("单价");
		fields.add(field);

		field = new ConvertField();
		field.setField("zzgg");
		field.setTitle("制造商规格略称");
		fields.add(field);

		field = new ConvertField();
		field.setField("abbr");
		field.setTitle("用户略称");
		fields.add(field);

		field = new ConvertField();
		field.setField("pzno");
		field.setTitle("品种");
		fields.add(field);

		field = new ConvertField();
		field.setField("neij");
		field.setTitle("内径");
		fields.add(field);

		field = new ConvertField();
		field.setField("fpdj");
		field.setTitle("等级");
		fields.add(field);
		entity.setFields(fields);

		bo.save(entity);
	}

	@Test
	public void saveYcaitp() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = new ConvertEntity();
		entity.setId("Ycaitp");
		entity.setName("原板清单导入");
		entity.setType(ConverterType.excel.type);

		fields = new ArrayList<ConvertField>();

		field = new ConvertField();
		field.setField("ybno");
		field.setTitle("供应商合同");
		fields.add(field);

		field = new ConvertField();
		field.setField("xpho");
		field.setTitle("厚");
		fields.add(field);

		field = new ConvertField();
		field.setField("xpkn");
		field.setTitle("宽");
		fields.add(field);

		field = new ConvertField();
		field.setField("zzsj");
		field.setTitle("制造商卷板NO");
		fields.add(field);

		field = new ConvertField();
		field.setField("zzzp");
		field.setTitle("制造商制品NO");
		fields.add(field);

		field = new ConvertField();
		field.setField("tun");
		field.setTitle("重量");
		fields.add(field);

		field = new ConvertField();
		field.setField("face");
		field.setTitle("表面");
		fields.add(field);
		entity.setFields(fields);

		bo.save(entity);
	}

	@Test
	public void saveJchaTp() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = new ConvertEntity();
		entity.setId("JchaTp");
		entity.setName("原板检查证书导入");
		entity.setType(ConverterType.excel.type);

		fields = new ArrayList<ConvertField>();

		field = new ConvertField();
		field.setField("zzsj");
		field.setTitle("制造商卷板NO");
		fields.add(field);

		field = new ConvertField();
		field.setField("cfcc");
		field.setTitle("C");
		fields.add(field);

		field = new ConvertField();
		field.setField("cfsi");
		field.setTitle("Si");
		fields.add(field);

		field = new ConvertField();
		field.setField("cfmn");
		field.setTitle("Mn");
		fields.add(field);

		field = new ConvertField();
		field.setField("cfpp");
		field.setTitle("P");
		fields.add(field);

		field = new ConvertField();
		field.setField("cfss");
		field.setTitle("S");
		fields.add(field);

		field = new ConvertField();
		field.setField("ying");
		field.setTitle("硬度");
		fields.add(field);
		entity.setFields(fields);

		field = new ConvertField();
		field.setField("chui");
		field.setTitle("吹练NO");
		fields.add(field);
		entity.setFields(fields);

		bo.save(entity);
	}

	@Test
	public void update() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = bo.get("Sample");
		entity.setName("范例-员工导入");
		entity.setType(ConverterType.excel.type);

		fields = entity.getFields();
		if (fields == null) {
			fields = new ArrayList<ConvertField>();
			entity.setFields(fields);
		}
		field = new ConvertField();
		field.setField("email");
		field.setTitle("Email");
		fields.add(field);

		bo.update(entity);
	}

	@Test
	public void saveTxt() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = new ConvertEntity();
		entity.setId("SampleTxt");
		entity.setName("范例-文本上传");
		entity.setSplit(" ");
		entity.setType(ConverterType.text.type);

		fields = new ArrayList<ConvertField>();
		field = new ConvertField();
		field.setField("name");
		field.setTitle("姓名");
		fields.add(field);
		field = new ConvertField();
		field.setField("mobile");
		field.setTitle("电话");
		fields.add(field);
		field = new ConvertField();
		field.setField("email");
		field.setTitle("Email");
		fields.add(field);

		entity.setFields(fields);

		bo.save(entity);
	}

	@Test
	public void saveYbrk() {
		ConvertEntity entity;
		ConvertField field;
		List<ConvertField> fields;
		entity = new ConvertEntity();
		entity.setId("Ybrk");
		entity.setName("原板入库");
		entity.setSplit(" ");
		entity.setType(ConverterType.text.type);

		fields = new ArrayList<ConvertField>();
		field = new ConvertField();
		field.setField("operate");
		field.setTitle("操作类别");
		fields.add(field);
		field = new ConvertField();
		field.setField("jbno");
		field.setTitle("原材卷板NO");
		fields.add(field);
		field = new ConvertField();
		field.setField("kw");
		field.setTitle("库位");
		fields.add(field);

		entity.setFields(fields);

		bo.save(entity);
	}

	@Test
	public void convert() {
		InputStream is = null;
		try {
			is = new FileInputStream("E:\\原板清单导入模板3.xls");
			bo.convert("Ycaitp", YcaiTp.class, is, new IConverterCallback<YcaiTp>() {

				@Override
				public void doRow(ConvertRowVO<YcaiTp> row) {
					if (row.getErrors() != null) {
						for (ConvertErrorVO error : row.getErrors()) {
							System.out.println(error.getValue()
									+ "============; " + error.getField()
									+ "==" + error.getCellIndex() + " : "
									+ error.getCause().getMessage());
						}
					}
					else {
						System.out.println("===" + row.getVo().getZpzl());
					}
					// System.out.println(row.getVo().getName() + ","
					// + row.getVo().getMobile() + ","
					// + row.getVo().getEmail());
				}
			});
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	@Test
	public void convertTxt() {
		InputStream is = null;
		try {
			is = new FileInputStream("E:\\原板入库.txt");
			// bo.convert("Ybrk", YbrkVO.class, is, new
			// IConverterCallback<YbrkVO>() {
			//
			// @Override
			// public void doRow(ConvertRowVO<YbrkVO> row) {
			// System.out.println(row.getVo().getJbno());
			// // System.out.println(row.getVo().getName() + ","
			// // + row.getVo().getMobile() + ","
			// // + row.getVo().getEmail());
			// }
			// });
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
				}
			}
		}
	}
}
