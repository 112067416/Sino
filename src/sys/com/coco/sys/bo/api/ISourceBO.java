package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.SourceClass;
import com.coco.sys.orm.SourceMethod;

/**
 * <p>
 * 资源管理数据访问
 * </p>
 * <p>
 * create: 2010-12-10 下午03:51:08
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISourceBO {

	/**
	 * <p>
	 * 保存资源类
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(SourceClass entity);

	/**
	 * <p>
	 * 更新资源类
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(SourceClass entity);

	/**
	 * <p>
	 * 保存资源方法
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(SourceMethod entity);

	/**
	 * <p>
	 * 更新资源方法
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(SourceMethod entity);

	/**
	 * <p>
	 * 删除资源类，包含资源方法
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 删除资源方法
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteMethod(Serializable id);

	/**
	 * <p>
	 * 获取资源类，包含资源方法
	 * </p>
	 * 
	 * @param id
	 * @return SourceClass
	 */
	public SourceClass get(Serializable id);

	/**
	 * <p>
	 * 获取资信息
	 * </p>
	 * 
	 * @return List<SourceClass>
	 */
	public List<SourceClass> findAll();

	/**
	 * <p>
	 * 分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<SourceClass> qentity);

	/**
	 * <p>
	 * 获取唯一的资源方法
	 * </p>
	 * 
	 * @param className
	 * @param method
	 * @return SourceMethod
	 */
	public SourceMethod getUniqueMethod(String className, String method);

	/**
	 * <p>
	 * 检查是否有资源访问权限
	 * </p>
	 * 
	 * @param className
	 * @param method
	 * @param roleIds
	 * @return int
	 *         <ul>
	 *         <li>0 : 没有权限</li>
	 *         <li>1 : 有权限</li>
	 *         <li>2 : 资源没有定义</li>
	 *         </ul>
	 */
	public int checkSource(String className, String method, List<String> roleIds);
}
