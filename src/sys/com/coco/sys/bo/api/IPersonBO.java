package com.coco.sys.bo.api;

import java.io.Serializable;

import org.dom4j.Document;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Person;

/**
 * <p>
 * 员工业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:39:12
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IPersonBO {

	/**
	 * 新增或者修改员工信息
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Person entity);

	/**
	 * 删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询员工
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Person> qentity);

	/**
	 * 获取员工
	 * 
	 * @param id
	 * @return Person
	 */
	public Person get(Serializable id);

	/**
	 * <p>
	 * 获取员工基本信息js对象
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 获取员工属性列表
	 * </p>
	 * 
	 * @param onlyValid
	 * @return Document
	 */
	public Document tree(boolean onlyValid);

	/**
	 * <p>
	 * 判断工号是否存在
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param no
	 * @return boolean
	 */
	public boolean isExistNo(String no);

	/**
	 * <p>
	 * 根据工号获得用户信息
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param no
	 * @return boolean
	 */
	public Person getUniqueByNo(String no);

}
