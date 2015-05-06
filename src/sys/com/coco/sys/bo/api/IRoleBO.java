package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Role;
import com.coco.sys.orm.RoleMenu;
import com.coco.sys.orm.RoleSource;

/**
 * <p>
 * 角色业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:42:16
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IRoleBO {

	/**
	 * 新增角色
	 * 
	 * @param entity
	 */
	public void save(Role entity);

	/**
	 * 更新角色
	 * 
	 * @param entity
	 */
	public void update(Role entity);

	/**
	 * 根据角色编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询角色
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Role> qentity);

	/**
	 * 获取角色
	 * 
	 * @param id
	 * @return Role
	 */
	public Role get(Serializable id);

	/**
	 * 获取角色
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 查询角色菜单
	 * </p>
	 * 
	 * @param role
	 * @return List<RoleMenu>
	 */
	public List<RoleMenu> findRoleMenus(String role);

	/**
	 * <p>
	 * 查询角色菜单的菜单Id
	 * </p>
	 * 
	 * @param role
	 * @return List<String>
	 */
	public List<String> findMenuIds(String role);

	/**
	 * <p>
	 * 授权角色菜单
	 * </p>
	 * 
	 * @param role
	 * @param menus
	 */
	public void grantMenu(String role, String[] menus);

	/**
	 * <p>
	 * 查询角色资源
	 * </p>
	 * 
	 * @param role
	 * @return List<RoleSource>
	 */
	public List<RoleSource> findRoleSources(String role);

	/**
	 * <p>
	 * 查询角色资源的资源Id
	 * </p>
	 * 
	 * @param role
	 * @return List<String>
	 */
	public List<String> findSourceIds(String role);

	/**
	 * <p>
	 * 角色资源授权
	 * </p>
	 * 
	 * @param role
	 * @param sources
	 */
	public void grantSource(String role, String[] sources);

}
