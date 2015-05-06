package com.coco.sys.bo;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.IDirectoryBO;
import com.coco.sys.dao.api.IDirectoryDAO;
import com.coco.sys.orm.Directory;
import com.coco.sys.vo.DirectoryQE;

public class DirectoryBO implements IDirectoryBO {

	private IDirectoryDAO dao;

	public IDirectoryDAO getDao() {
		return dao;
	}

	public void setDao(IDirectoryDAO dao) {
		this.dao = dao;
	}

	@Override
	public void saveOrUpdate(Directory entity) {
		Directory db = null;
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			db = get(entity.getId());
		}
		// 同级不允许同名
		DirectoryQE qe = new DirectoryQE();
		qe.setParent(entity.getParent());
		qe.setName(entity.getName());
		dao.query(qe);
		if (db == null) {
			if (!qe.getDatas().isEmpty()) {
				throw new CocoException(-10003, "在同级目录中不允许重名");
			}
			entity.setId(null);
			dao.save(entity);
		}
		else {
			dao.update(entity);
		}
	}

	@Override
	public List<Directory> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Directory> findValid() {
		return dao.findValid();
	}

	@Override
	public Document tree(boolean onlyValid) {
		List<Directory> els = onlyValid ? findValid() : findAll();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		Element item;
		for (Directory el : els) {
			item = root.addElement("item");
			item.addAttribute("id", el.getId());
			item.addAttribute("pid", el.getParent());
			item.addAttribute("label", el.getName());
		}
		return doc;
	}

	@Override
	public Directory get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public List<Directory> findExclude(Serializable id) {
		return dao.findExclude(id);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public List<Directory> findAncestors(Serializable id) {
		return dao.findAncestors(id);
	}

}
