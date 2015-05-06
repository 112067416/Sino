package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Role;
import com.coco.sys.orm.RoleMenu;
import com.coco.sys.orm.RoleSource;

/**
 * <p>
 * 角色数据访问接口
 * </p>
 * <p>
 * create: 2010-12-16 下午04:20:00
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IRoleDAO {

	/**
	 * <p>
	 * 新增角色
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Role entity);

	/**
	 * <p>
	 * 更新角色
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Role entity);

	/**
	 * <p>
	 * 根据角色编号删除对应的信息
	 * </p>
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 查询角色
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Role> qentity);

	/**
	 * <p>
	 * 获取角色
	 * </p>
	 * 
	 * @param id
	 * @return Role
	 */
	public Role get(Serializable id);

	/**
	 * <p>
	 * 获取已经授权的角色菜单
	 * </p>
	 * 
	 * @param role
	 * @return List<RoleMenu>
	 */
	public List<RoleMenu> findRoleMenus(String role);

	/**
	 * <p>
	 * 授权于功能菜单
	 * </p>
	 * 
	 * @param role
	 * @param menus
	 */
	public void grantMenu(String role, String[] menus);

	/**
	 * <p>
	 * 获取已授权的角色资源
	 * </p>
	 * 
	 * @param role
	 * @return List<RoleMenu>
	 */
	public List<RoleSource> findRoleSources(String role);

	/**
	 * <p>
	 * 授权于资源
	 * </p>
	 * 
	 * @param role
	 * @param sources
	 */
	public void grantSource(String role, String[] sources);
}
