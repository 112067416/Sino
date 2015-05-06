package com.coco.sys.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.dao.api.ICodeDAO;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.orm.CodeItemPk;

/**
 * <p>
 * 码表业务实现
 * </p>
 * <p>
 * create: 2011-2-14 上午11:10:02
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CodeBO implements ICodeBO {

	private ICodeDAO dao;

	/**
	 * 码表js显示忽略字段
	 */
	private Set<String> codeIgnoreNames = new HashSet<String>(
			Arrays.asList(new String[] { "items" }));
	/**
	 * 码表项js显示忽略字段
	 */
	private Set<String> codeItemIgnoreNames = new HashSet<String>(
			Arrays.asList(new String[] { "code" }));
	/**
	 * 码表项js显示表达式
	 */
	private Set<String> codeItemExprs = new HashSet<String>(
			Arrays.asList(new String[] { "codeId" }));

	@Override
	public void save(Code entity) {
		dao.save(entity);
	}

	@Override
	public void update(Code entity) {
		dao.update(entity);
	}

	@Override
	public void upddateItem(CodeItem codeItem) {
		dao.upddateItem(codeItem);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Code get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void query(QEntity<Code> qentity) {
		dao.query(qentity);
	}

	@Override
	public CodeItem getCodeItem(String code, String key) {
		Code entity = new Code();
		entity.setId(code);
		return dao.getCodeItem(new CodeItemPk(entity, key));
	}

	@Override
	public CodeItem getCodeItemByFudw(String code, String key, String fudw) {
		return dao.getCodeItemByFudw(code, key, fudw);
	}

	@Override
	public void saveItems(String code, List<CodeItem> items) {
		Code entity = dao.getRef(code);
		if (entity == null) {
			return;
		}
		dao.saveItems(entity, items);
	}

	@Override
	public void saveItem(CodeItem codeItem) {
		dao.saveItem(codeItem);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject(null, codeIgnoreNames);
	}

	@Override
	public List<CodeItem> findItems(String code) {
		return dao.findItems(code);
	}

	// @Override
	// public Map<String, DxlVO> findDxl(String code, String key) {
	// List<CodeItem> codeItems = dao.findDxl(code, key);
	// Map<String, DxlVO> map = new HashMap<String, DxlVO>();
	// DxlVO vo = null;
	// String min = "0";
	// String max = "0";
	// String v = null;
	// for (CodeItem codeItem : codeItems) {
	// vo = new DxlVO();
	// if ((v = codeItem.getValue()) != null && !v.isEmpty()) {
	// if (v.length() == 8) {
	// // 附着量代码值的前四位（中间加小数点）为下限
	// min = v.substring(0, 2) + "." + v.substring(2, 4);
	// // 附着量代码值的后四位（中间加小数点）为上限
	// max = v.substring(4, 6) + "." + v.substring(6, 8);
	// }
	// }
	// vo.setMax(Double.valueOf(max));
	// vo.setMin(Double.valueOf(min));
	// map.put(codeItem.getKey(), vo);
	// }
	// return map;
	// }

	@Override
	public Map<String, String> findGMtoWB() {
		Map<String, String> dxls = new HashMap<String, String>();
		dxls.put("GM011", "010");
		dxls.put("GM022", "020");
		dxls.put("GM028", "025");
		dxls.put("GM039", "035");
		dxls.put("GM048", "040");
		dxls.put("GM056", "050");
		dxls.put("GM084", "075");
		dxls.put("GM112", "100");
		dxls.put("GM151", "135");
		return dxls;
	}

	@Override
	public List<CodeItem> findItems(List<String> code) {
		return dao.findItems(code);
	}

	@Override
	public List<CodeItem> findValidItems(String code) {
		return dao.findValidItems(code);
	}

	@Override
	public boolean isExisted(String code, String key) {
		return dao.isExisted(code, key);
	}

	@Override
	public boolean isValueExisted(String code, String value) {
		return dao.isValueExisted(code, value);
	}

	@Override
	public List<CodeItem> findItems(String code, String value) {
		return dao.findItems(code, value);
	}

	@Override
	public CodeItem getUniqueItemByValue(String code, String value) {
		List<CodeItem> items = dao.findItems(code, value);
		return items != null && !items.isEmpty() ? items.get(0) : null;
	}

	@Override
	public String getCodeItemForJs(String code, String key) {
		return new Result(this.getCodeItem(code, key)).toJsObject(null, codeItemIgnoreNames, codeItemExprs);
	}

	@Override
	public String getCodeItemByValueForJs(String code, String value) {
		return new Result(this.getUniqueItemByValue(code, value)).toJsObject(null, codeItemIgnoreNames, codeItemExprs);
	}

	public ICodeDAO getDao() {
		return dao;
	}

	public void setDao(ICodeDAO dao) {
		this.dao = dao;
	}

}
