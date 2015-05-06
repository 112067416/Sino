package com.coco.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IDeptDAO;
import com.coco.sys.orm.Dept;

public class DeptDAO implements IDeptDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Dept entity) {
		dao.save(entity);
	}

	@Override
	public void update(Dept entity) {
		dao.update(entity);
	}

	@Override
	public List<Dept> findAll() {
		return dao.find(Dept.class, "order by order, id");
	}

	@Override
	public List<Dept> findValid() {
		return dao.find(Dept.class, "valid=? order by order, id", true);
	}

	@Override
	public Dept get(Serializable id) {
		return dao.get(Dept.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> findExclude(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		return (List<Dept>) dao.findForValues("from Dept where id not in (?)", offspringIds);
	}

	@Override
	public List<Serializable> getOffspring(Serializable id) {
		return dao.getOffspring("select id from Dept where parent=?", id);
	}

	@Override
	public void delete(Serializable id) {
		List<Serializable> offspringIds = getOffspring(id);
		offspringIds.add(id);
		try {
			dao.executeForValues("delete from Dept where id in (?)", offspringIds);
		}
		catch (Exception e) {
			throw new CocoException(-10004, "还有其他员工在该部门或者子部门下，不允许删除，请先转移其员工。");
		}
	}

	@Override
	public void query(QEntity<Dept> qentity) {
		dao.query(qentity);
	}

}
