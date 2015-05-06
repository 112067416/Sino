package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IDirectoryDAO;
import com.coco.sys.orm.Directory;

public class DirectoryDAO implements IDirectoryDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Directory entity) {
		dao.save(entity);
	}

	@Override
	public void update(Directory entity) {
		dao.update(entity);
	}

	@Override
	public List<Directory> findAll() {
		return dao.find(Directory.class, "order by order, id");
	}

	@Override
	public List<Directory> findValid() {
		return dao.find(Directory.class, "valid=? order by order, id", true);
	}

	@Override
	public Directory get(Serializable id) {
		return dao.get(Directory.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Directory> findExclude(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		return (List<Directory>) dao.findForValues("from Directory where id not in (?)", offspringIds);
	}

	@Override
	public List<Serializable> getOffspring(Serializable id) {
		return dao.getOffspring("select id from Directory where parent=?", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Directory> findAncestors(Serializable id) {
		Directory directory = get(id);
		if (directory == null) {
			return new ArrayList<Directory>();
		}
		List<Serializable> ancestors = new ArrayList<Serializable>();
		ancestors.add(id);
		recurse(directory.getParent(), ancestors);
		return (List<Directory>) dao.findForValues("from Directory where id not in (?)", ancestors);
	}

	private void recurse(Serializable id, List<Serializable> ancestors) {
		Directory directory = (Directory) dao.getUnique("from Directory where id=?", id);
		if (directory == null) {
			return;
		}
		ancestors.add(id);
		recurse(directory.getParent(), ancestors);
	}

	@Override
	public void delete(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		try {
			dao.executeForValues("delete from Directory where id in (?)", offspringIds);
		}
		catch (Exception e) {
			throw new CocoException(-10004, "还有其他资料在该目录下，不允许删除，请先转移其资料。");
		}
	}

	@Override
	public void query(QEntity<Directory> qentity) {
		dao.query(qentity);
	}

}
