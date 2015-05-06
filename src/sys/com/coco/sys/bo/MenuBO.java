package com.coco.sys.bo;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IMenuBO;
import com.coco.sys.dao.api.IMenuDAO;
import com.coco.sys.orm.Menu;

public class MenuBO implements IMenuBO {

	private IMenuDAO dao;

	public IMenuDAO getDao() {
		return dao;
	}

	public void setDao(IMenuDAO dao) {
		this.dao = dao;
	}

	@Override
	public void saveOrUpdate(Menu entity) {
		Menu db = null;
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			db = dao.get(entity.getId());
		}
		if (db == null) {
			entity.setId(null);
			dao.save(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public List<Menu> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Menu> findValid() {
		return dao.findValid();
	}

	@Override
	public Document tree(boolean onlyValid) {
		return dao.tree(onlyValid);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public List<Menu> findExclude(Serializable id) {
		return dao.findExclude(id);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Document treeByRoles(List<String> roles) {
		return dao.treeByRoles(roles);
	}

}
