package com.quanta.sino.cmn.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.InformTp;

/**
 * <p>
 * 资料室业务访问接口
 * </p>
 * <p>
 * create: 2011-1-8 下午04:33:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IInformBO {

	/**
	 * 新增资料
	 * 
	 * @param entity
	 */
	public void save(InformTp entity);

	/**
	 * 更新资料
	 * 
	 * @param entity
	 */
	public void update(InformTp entity);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询资料
	 * 
	 * @param qentity
	 */
	public void query(QEntity<InformTp> qentity);

	/**
	 * 获取资料
	 * 
	 * @param id
	 * @return InformTp
	 */
	public InformTp get(Serializable id);

	/**
	 * 获取资料
	 * 
	 * @param name
	 * @return InformTp
	 */
	public InformTp getByName(String name);

	/**
	 * 获取通用主文件Js串
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 判断名称是否已存在
	 * </p>
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean isExisted(String name);

	/**
	 * <p>
	 * 批量删除资料
	 * </p>
	 * 
	 * @param ids
	 */
	public void deletes(List<String> ids);

	/**
	 * <p>
	 * 为资料室目录，设置部门
	 * </p>
	 * 
	 * @param id
	 * @param depts
	 */
	public void grantDept(String id, String depts);

	/**
	 * <p>
	 * 查看资料室目录对应的部门
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getDept(String id);
}
