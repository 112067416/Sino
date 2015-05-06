package com.quanta.sino.cmn.dao;

import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.cmn.dao.api.IDirectoryDeptDAO;
import com.quanta.sino.orm.DirectoryDept;

public class DirectoryDeptDAO implements IDirectoryDeptDAO {

	private DAO dao;

	@Override
	public void save(DirectoryDept entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<DirectoryDept> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void delete(String id) {
		dao.executeUpdate("delete from DirectoryDept where directoryId=?", id);
	}

	@Override
	public List<DirectoryDept> find(String id) {
		return dao.find(DirectoryDept.class, "directoryId=?", id);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
