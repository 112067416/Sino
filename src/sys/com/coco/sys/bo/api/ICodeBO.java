package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;

/**
 * <p>
 * 码表业务接口
 * </p>
 * <p>
 * create: 2011-2-14 上午11:09:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ICodeBO {

	public void save(Code entity);

	public void update(Code entity);

	/**
	 * <p>
	 * 更新代码项
	 * </p>
	 * 
	 * @param codeItem
	 */
	public void upddateItem(CodeItem codeItem);

	public void saveItems(String code, List<CodeItem> items);

	public void saveItem(CodeItem codeItem);

	public void delete(Serializable id);

	public Code get(Serializable id);

	public void query(QEntity<Code> qentity);

	/**
	 * <p>
	 * 获取码表信息
	 * </p>
	 * 
	 * @param code
	 * @param key
	 * @return CodeItem
	 */
	public CodeItem getCodeItem(String code, String key);

	/**
	 * <p>
	 * 获取码表信息js串
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取码表项信息js串
	 * </p>
	 * 
	 * @param code
	 * @param key
	 * @return String
	 */
	public String getCodeItemForJs(String code, String key);

	/**
	 * <p>
	 * 获取码表项信息js串
	 * </p>
	 * 
	 * @param code
	 * @param value
	 * @return String
	 */
	public String getCodeItemByValueForJs(String code, String value);

	/**
	 * <p>
	 * 查询码表列表
	 * </p>
	 * 
	 * @param code
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findItems(String code);

	/**
	 * <p>
	 * 查找代码项列表
	 * </p>
	 * 
	 * @param code
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findItems(List<String> code);

	/**
	 * <p>
	 * 查询有效的码表列表
	 * </p>
	 * 
	 * @param code
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findValidItems(String code);

	/**
	 * <p>
	 * 检查码表的键是否存在
	 * </p>
	 * 
	 * @param code
	 *            码表模块
	 * @param key
	 *            码表主键
	 * @return boolean
	 */
	public boolean isExisted(String code, String key);

	/**
	 * <p>
	 * 检查码表的值是否存在
	 * </p>
	 * 
	 * @param code
	 *            码表模块
	 * @param value
	 *            码表的值
	 * @return boolean
	 */
	public boolean isValueExisted(String code, String value);

	/**
	 * <p>
	 * 根据代码模块和值获取码表
	 * </p>
	 * 
	 * @param code
	 *            码表模块
	 * @param value
	 *            代码值
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findItems(String code, String value);

	//
	// /**
	// * <p>
	// * </p>
	// *
	// * @param code
	// * @param key
	// * @return Map<String, DxlVO>
	// */
	// public Map<String, DxlVO> findDxl(String code, String key);

	/**
	 * <p>
	 * GM与WB的对应关系
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, String> findGMtoWB();

	/**
	 * <p>
	 * 根据代码模块和值获取码表。若存在多个key对应一个值，则返回其中一个；若不存在，则返回null
	 * </p>
	 * 
	 * @param code
	 *            码表模块
	 * @param value
	 *            代码值
	 * @return CodeItem
	 */
	public CodeItem getUniqueItemByValue(String code, String value);

	/**
	 * <p>
	 * 根据GM取WB码表
	 * </p>
	 * 
	 * @param code
	 * @param key
	 * @param fudw
	 * @return CodeItem
	 */
	public CodeItem getCodeItemByFudw(String code, String key, String fudw);
}
