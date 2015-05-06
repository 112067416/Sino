package com.quanta.sino.cmn.dao.api;

import java.util.List;

import com.quanta.sino.orm.DirectoryDept;

/**
 * <p>
 * 资料室目录对应部门室目录对应部门数据访问接口
 * </p>
 * <p>
 * create: 2011-1-8 下午04:33:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IDirectoryDeptDAO {

	/**
	 * 新增资料室目录对应部门
	 * 
	 * @param entity
	 */
	public void save(DirectoryDept entity);

	/**
	 * <p>
	 * 批量新增资料室目录对应部门
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveAll(List<DirectoryDept> entities);

	/**
	 * 根据中文件编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * <p>
	 * 查看资料室目录对应的部门
	 * </p>
	 * 
	 * @param id
	 * @return List<DirectoryDept>
	 */
	public List<DirectoryDept> find(String id);
}
