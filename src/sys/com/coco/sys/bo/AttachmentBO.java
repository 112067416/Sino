package com.coco.sys.bo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.constants.SysConstants;
import com.coco.sys.dao.api.IAttachmentDAO;
import com.coco.sys.orm.Attachment;
import com.coco.sys.vo.AttachVO;

public class AttachmentBO implements IAttachmentBO {

	private IAttachmentDAO dao;

	private String path;

	@Override
	public Attachment get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public List<Attachment> find(String type, String rel) {
		return dao.find(type, rel);
	}

	@Override
	public void query(QEntity<Attachment> qentity) {
		dao.query(qentity);
	}

	@Override
	public Attachment upload(MultipartFile file, AttachVO vo) {
		if (file == null || vo == null) {
			return null;
		}
		if (vo.isSingle()) {
			// TODO 只允许上传一个文件的情况.
		}
		Attachment entity = new Attachment();
		String name = vo.getName();
		String type = vo.getType();
		if (name != null && (name = name.trim()).length() > 0) {
			entity.setName(name);
		}
		else {
			entity.setName(file.getOriginalFilename());
		}
		entity.setOriginalName(file.getOriginalFilename());
		entity.setContentType(file.getContentType());
		entity.setLength(file.getSize());
		entity.setCreateTime(new Date());
		entity.setType(type != null && !(type = type.trim()).isEmpty() ? type
				: SysConstants.ATTACHMENT_TYPE_COMMON);
		entity.setRel(vo.getRel());
		int lastDot = file.getOriginalFilename().lastIndexOf('.');
		if (lastDot > 0) {
			entity.setExt(file.getOriginalFilename().substring(lastDot + 1));
		}
		try {
			entity.setStream(file.getBytes());
		}
		catch (IOException e) {
			throw new CocoException(-1, e);
		}
		dao.save(entity);
		return entity;
	}

	@Override
	public Attachment download(Serializable id) {
		Attachment entity = dao.get(id);
		if (entity == null) {
			throw new CocoException(-1, "附件不存在");
		}
		entity.getStream();
		return entity;
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public boolean isExisted(Serializable id) {
		return dao.isExisted(id);
	}

	@Override
	public void save(Attachment attachment) {
		dao.save(attachment);
	}

	@Override
	public void update(Attachment attachment) {
		dao.update(attachment);
	}

	public IAttachmentDAO getDao() {
		return dao;
	}

	public void setDao(IAttachmentDAO dao) {
		this.dao = dao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
