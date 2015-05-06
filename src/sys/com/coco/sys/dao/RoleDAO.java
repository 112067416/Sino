package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IRoleDAO;
import com.coco.sys.orm.Menu;
import com.coco.sys.orm.Role;
import com.coco.sys.orm.RoleMenu;
import com.coco.sys.orm.RoleSource;
import com.coco.sys.orm.SourceMethod;

public class RoleDAO implements IRoleDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Role entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.executeUpdate("delete from RoleMenu where role.id=?", id);
		dao.executeUpdate("delete from RoleSource where role.id=?", id);
		dao.executeUpdate("delete from PostRole where role.id=?", id);
		dao.delete(Role.class, id);
	}

	@Override
	public void query(QEntity<Role> qentity) {
		dao.query(qentity);
	}

	@Override
	public Role get(Serializable id) {
		return dao.get(Role.class, id);
	}

	@Override
	public void update(Role entity) {
		dao.update(entity);
	}

	@Override
	public List<RoleMenu> findRoleMenus(String role) {
		return dao.find(RoleMenu.class, "role.id=? and menu.url is not null and menu.url != ''", role);
	}

	@Override
	public void grantMenu(String role, String[] menus) {
		if (role == null) {
			return;
		}
		dao.executeUpdate("delete RoleMenu where role.id=?", role);
		List<RoleMenu> adds = new ArrayList<RoleMenu>();
		RoleMenu item;
		for (String menu : menus) {
			item = new RoleMenu();
			item.setRole(new Role(role));
			item.setMenu(new Menu(menu));
			adds.add(item);
		}
		dao.saveAll(adds);
		//
		// List<RoleMenu> deletes = findRoleMenus(role);
		// List<RoleMenu> adds = new ArrayList<RoleMenu>();
		// RoleMenu item;
		// if (menus != null) {
		// Iterator<RoleMenu> it;
		// boolean isNew;
		// for (String menu : menus) {
		// isNew = true;
		// it = deletes.iterator();
		// while (it.hasNext()) {
		// if (it.next().getMenu().getId().equals(menu)) {
		// it.remove();
		// isNew = false;
		// break;
		// }
		// }
		// if (isNew) {
		// item = new RoleMenu();
		// item.setRole(new Role(role));
		// item.setMenu(new Menu(menu));
		// adds.add(item);
		// }
		// }
		// }
		// if (!deletes.isEmpty()) {
		// for (RoleMenu entity : deletes) {
		// dao.delete(entity);
		// }
		// }
		// if (!adds.isEmpty()) {
		// for (RoleMenu entity : adds) {
		// dao.save(entity);
		// }
		// }
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.IRoleDAO#findRoleSources(java.lang.String)
	 */
	@Override
	public List<RoleSource> findRoleSources(String role) {
		return dao.find(RoleSource.class, "role.id=?", role);
	}

	/*
	 * (non-Javadoc)
	 * @see com.coco.sys.dao.api.IRoleDAO#grantSource(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public void grantSource(String role, String[] sources) {
		if (role == null) {
			return;
		}
		List<RoleSource> deletes = findRoleSources(role);
		List<RoleSource> adds = new ArrayList<RoleSource>();
		RoleSource item;
		if (sources != null) {
			Iterator<RoleSource> it;
			boolean isNew;
			for (String source : sources) {
				isNew = true;
				it = deletes.iterator();
				while (it.hasNext()) {
					if (it.next().getSource().getId().equals(source)) {
						it.remove();
						isNew = false;
						break;
					}
				}
				if (isNew) {
					item = new RoleSource();
					item.setRole(new Role(role));
					item.setSource(new SourceMethod(source));
					adds.add(item);
				}
			}
		}
		if (!deletes.isEmpty()) {
			for (RoleSource entity : deletes) {
				dao.delete(entity);
			}
		}
		if (!adds.isEmpty()) {
			for (RoleSource entity : adds) {
				dao.save(entity);
			}
		}
	}

}
