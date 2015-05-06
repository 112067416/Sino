package com.coco.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.coco.core.persistence.api.DAO;
import com.coco.sys.dao.api.IMenuDAO;
import com.coco.sys.orm.Menu;

public class MenuDAO implements IMenuDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Menu entity) {
		dao.save(entity);
	}

	@Override
	public void update(Menu entity) {
		dao.update(entity);
	}

	@Override
	public List<Menu> findAll() {
		return dao.find(Menu.class, "order by order, id");
	}

	@Override
	public List<Menu> findValid() {
		return dao.find(Menu.class, "valid=? order by order, id", true);
	}

	@Override
	public Document tree(boolean onlyValid) {
		List<Menu> menus = onlyValid ? findValid() : findAll();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		if (menus != null) {
			Element item;
			for (Menu menu : menus) {
				item = root.addElement("item");
				item.addAttribute("id", menu.getId());
				item.addAttribute("pid", menu.getParent());
				item.addAttribute("label", menu.getName());
				if (menu.getOrder() != null) {
					item.addAttribute("order", menu.getOrder().toString());
				}
				if (menu.getUrl() != null && !menu.getUrl().isEmpty()) {
					item.addAttribute("url", menu.getUrl());
					item.addAttribute("leaf", "1");
				}
				else {
					item.addAttribute("leaf", "0");
				}
			}
		}
		return doc;
	}

	@Override
	public Menu get(Serializable id) {
		return dao.get(Menu.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findExclude(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		return (List<Menu>) dao.findForValues("from Menu where id not in (?)", offspringIds);
	}

	@Override
	public List<Serializable> getOffspring(Serializable id) {
		return dao.getOffspring("select id from Menu where parent=?", id);
	}

	@Override
	public void delete(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		dao.executeForValues("delete from RoleMenu where menu.id in (?)", offspringIds);
		dao.executeForValues("delete from Menu where id in (?)", offspringIds);
	}

	@Override
	public Document treeByRoles(List<String> roles) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		if (roles == null || roles.isEmpty()) {
			return doc;
		}
		@SuppressWarnings("unchecked")
		List<Menu> menus = (List<Menu>) dao.findForValues("select distinct a from Menu a, RoleMenu b where a.id=b.menu.id and a.valid=1 and b.role.valid=1 and b.role.id in (?) order by a.order asc", roles);
		if (menus != null) {
			Element item;
			for (Menu menu : menus) {
				item = root.addElement("item");
				item.addAttribute("id", menu.getId());
				item.addAttribute("pid", menu.getParent());
				item.addAttribute("label", menu.getName());
				if (menu.getOrder() != null) {
					item.addAttribute("order", menu.getOrder().toString());
				}
				if (menu.getUrl() != null && !menu.getUrl().isEmpty()) {
					item.addAttribute("url", menu.getUrl());
					item.addAttribute("leaf", "1");
				}
				else {
					item.addAttribute("leaf", "0");
				}
			}
		}
		return doc;
	}
}
