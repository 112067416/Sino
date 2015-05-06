package com.coco.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IAttachmentDAO;
import com.coco.sys.orm.Attachment;

public class AttachmentDAO implements IAttachmentDAO {

	private DAO dao;

	@Override
	public Attachment get(Serializable id) {
		return dao.get(Attachment.class, id);
	}

	@Override
	public List<Attachment> find(String type, String rel) {
		return dao.find(Attachment.class, "type=? and rel=?", type, rel);
	}

	@Override
	public void query(QEntity<Attachment> qentity) {
		dao.query(qentity);
	}

	@Override
	public void save(Attachment entity) {
		dao.save(entity);
	}

	@Override
	public void update(Attachment entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Attachment.class, id);
	}

	@Override
	public boolean isExisted(Serializable id) {
		String ql = "from Attachment where id=?";
		return dao.isExisted(ql, id);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
