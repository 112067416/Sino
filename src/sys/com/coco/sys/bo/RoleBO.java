package com.coco.sys.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IRoleBO;
import com.coco.sys.dao.api.IRoleDAO;
import com.coco.sys.orm.Role;
import com.coco.sys.orm.RoleMenu;
import com.coco.sys.orm.RoleSource;

public class RoleBO implements IRoleBO {

	private IRoleDAO dao;

	@Override
	public void save(Role entity) {
		dao.save(entity);

	}

	@Override
	public void update(Role entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<Role> qentity) {
		dao.query(qentity);
	}

	@Override
	public Role get(Serializable id) {
		return dao.get(id);
	}

	public IRoleDAO getDao() {
		return dao;
	}

	public void setDao(IRoleDAO dao) {
		this.dao = dao;
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public List<RoleMenu> findRoleMenus(String role) {
		return dao.findRoleMenus(role);
	}

	@Override
	public void grantMenu(String role, String[] menus) {
		dao.grantMenu(role, menus);
	}

	@Override
	public List<String> findMenuIds(String role) {
		List<RoleMenu> items = findRoleMenus(role);
		List<String> ids = new ArrayList<String>();
		for (RoleMenu item : items) {
			ids.add(item.getMenu().getId());
		}
		return ids;
	}

	@Override
	public List<RoleSource> findRoleSources(String role) {
		return dao.findRoleSources(role);
	}

	@Override
	public void grantSource(String role, String[] sources) {
		dao.grantSource(role, sources);
	}

	@Override
	public List<String> findSourceIds(String role) {
		List<RoleSource> items = findRoleSources(role);
		List<String> ids = new ArrayList<String>();
		for (RoleSource item : items) {
			ids.add(item.getSource().getId());
		}
		return ids;
	}

}
