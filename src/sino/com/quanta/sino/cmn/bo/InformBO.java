package com.quanta.sino.cmn.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IDirectoryBO;
import com.coco.sys.orm.Directory;
import com.quanta.sino.cmn.bo.api.IInformBO;
import com.quanta.sino.cmn.dao.api.IDirectoryDeptDAO;
import com.quanta.sino.cmn.dao.api.IInformDAO;
import com.quanta.sino.orm.DirectoryDept;
import com.quanta.sino.orm.InformTp;

public class InformBO implements IInformBO {

	private IInformDAO dao;

	private IDirectoryBO directoryBO;

	private IDirectoryDeptDAO directoryDeptDAO;

	@Override
	public void save(InformTp entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<InformTp> qentity) {
		dao.query(qentity);
		if (qentity.getDatas() != null) {
			for (InformTp informTp : qentity.getDatas()) {
				// 装载目录名
				informTp.getDirectory().getName();
			}
		}
	}

	@Override
	public InformTp get(Serializable id) {
		InformTp informTp = dao.get(id);
		if (informTp != null) {
			informTp.getDirectory().getName();
		}
		return informTp;
	}

	@Override
	public InformTp getByName(String name) {
		InformTp informTp = dao.getByName(name);
		if (informTp != null) {
			informTp.getDirectory().getName();
		}
		return informTp;
	}

	@Override
	public void update(InformTp entity) {
		dao.update(entity);
	}

	@Override
	public String getForJs(Serializable id) {
		InformTp entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该文件信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public boolean isExisted(String name) {
		return dao.isExisted(name);
	}

	@Override
	public void deletes(List<String> ids) {
		dao.deletes(ids);
	}

	@Override
	public void grantDept(String id, String depts) {
		List<DirectoryDept> dds = new ArrayList<DirectoryDept>();
		List<Directory> directories = directoryBO.findAncestors(id);
		DirectoryDept dd = null;
		for (Directory directory : directories) {
			for (String s : depts.split(",")) {
				dd = new DirectoryDept();
				dd.setDirectoryId(directory.getId());
				dd.setDeptId(s);
				dds.add(dd);
			}
		}
		directoryDeptDAO.delete(id);
		directoryDeptDAO.saveAll(dds);
	}

	@Override
	public String getDept(String id) {
		List<DirectoryDept> depts = directoryDeptDAO.find(id);
		if (depts.size() == 0) {
			return new Result(-1, "该目录还未分配给任何部门").toString();
		}
		StringBuilder builder = new StringBuilder();
		for (DirectoryDept d : depts) {
			if (builder.length() == 0) {
				builder.append(d.getDeptId());
				continue;
			}
			builder.append(",").append(d.getDeptId());
		}
		return new Result(1, builder.toString()).toString();
	}

	public IInformDAO getDao() {
		return dao;
	}

	public void setDao(IInformDAO dao) {
		this.dao = dao;
	}

	public IDirectoryDeptDAO getDirectoryDeptDAO() {
		return directoryDeptDAO;
	}

	public void setDirectoryDeptDAO(IDirectoryDeptDAO directoryDeptDAO) {
		this.directoryDeptDAO = directoryDeptDAO;
	}

	public IDirectoryBO getDirectoryBO() {
		return directoryBO;
	}

	public void setDirectoryBO(IDirectoryBO directoryBO) {
		this.directoryBO = directoryBO;
	}

}
