package com.coco.sys.bo;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.IDeptBO;
import com.coco.sys.dao.api.IDeptDAO;
import com.coco.sys.orm.Dept;
import com.coco.sys.vo.DeptQE;

public class DeptBO implements IDeptBO {

	private IDeptDAO dao;

	public IDeptDAO getDao() {
		return dao;
	}

	public void setDao(IDeptDAO dao) {
		this.dao = dao;
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		Dept db = null;
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			db = get(entity.getId());
		}
		// 同级不允许同名
		DeptQE qe = new DeptQE();
		qe.setParent(entity.getParent());
		qe.setName(entity.getName());
		dao.query(qe);
		List<Dept> depts = qe.getDatas();
		if (db == null) {
			if (depts.size() > 0) {
				throw new CocoException(-10003, "在同级机构中不允许重名");
			}
			entity.setId(null);
			dao.save(entity);
		}
		else {
			if (depts.size() > 0) {
				if (!depts.get(0).getId().equals(entity.getId())) {
					throw new CocoException(-10003, "在同级机构中不允许重名");
				}
			}
			dao.update(entity);
		}
	}

	@Override
	public List<Dept> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Dept> findValid() {
		return dao.findValid();
	}

	@Override
	public Document tree(boolean onlyValid) {
		List<Dept> els = onlyValid ? findValid() : findAll();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		Element item;
		for (Dept el : els) {
			item = root.addElement("item");
			item.addAttribute("id", el.getId());
			item.addAttribute("pid", el.getParent());
			item.addAttribute("label", el.getName());
		}
		return doc;
	}

	@Override
	public Dept get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public List<Dept> findExclude(Serializable id) {
		return dao.findExclude(id);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

}
