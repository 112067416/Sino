package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;

/**
 * <p>
 * 代码数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:52:47
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ICodeDAO {
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

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Code entity);

	/**
	 * <p>
	 * 更新
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Code entity);

	/**
	 * <p>
	 * 更新代码项
	 * </p>
	 * 
	 * @param codeItem
	 */
	public void upddateItem(CodeItem codeItem);

	/**
	 * <p>
	 * 保存代码项
	 * </p>
	 * 
	 * @param code
	 * @param items
	 */
	public void saveItems(Code code, List<CodeItem> items);

	/**
	 * <p>
	 * 保存代码项
	 * </p>
	 * 
	 * @param codeItem
	 */
	public void saveItem(CodeItem codeItem);

	/**
	 * <p>
	 * 删除代码
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取代码
	 * </p>
	 * 
	 * @param id
	 * @return Code
	 */
	public Code get(Serializable id);

	/**
	 * <p>
	 * 获取代码数据库引用
	 * </p>
	 * 
	 * @param id
	 * @return Code
	 */
	public Code getRef(Serializable id);

	/**
	 * <p>
	 * 查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Code> qentity);

	/**
	 * <p>
	 * 获取代码项
	 * </p>
	 * 
	 * @param id
	 * @return CodeItem
	 */
	public CodeItem getCodeItem(Serializable id);

	/**
	 * <p>
	 * 查找代码项列表
	 * </p>
	 * 
	 * @param code
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findItems(String code);

	/**
	 * <p>
	 * 查询镀锡量对应的上下限值
	 * </p>
	 * 
	 * @param code
	 * @param key
	 * @return List<CodeItem>
	 */
	public List<CodeItem> findDxl(String code, String key);

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
	 * 查找有效的代码项列表
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
	 *            码表主键
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

}
