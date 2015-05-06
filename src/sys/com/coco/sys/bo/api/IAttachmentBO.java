package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Attachment;
import com.coco.sys.vo.AttachVO;

/**
 * <p>
 * 附件业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:39:17
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IAttachmentBO {

	/**
	 * 根据ID获取附件
	 * 
	 * @param id
	 * @return Attachment
	 */
	public Attachment get(Serializable id);

	/**
	 * 根据附件类型和关联号获取附件列表
	 * 
	 * @param type
	 * @param rel
	 * @return List<Attachment>
	 */
	public List<Attachment> find(String type, String rel);

	/**
	 * <p>
	 * 附件分页查询
	 * </p>
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Attachment> qentity);

	/**
	 * 上传附件
	 * 
	 * @param file
	 * @param vo
	 */
	public Attachment upload(MultipartFile file, AttachVO vo);

	/**
	 * 文件下载
	 * 
	 * @param id
	 * @return Attachment
	 */
	public Attachment download(Serializable id);

	/**
	 * 删除附件
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 判断附件是否存在
	 * </p>
	 * 
	 * @param id
	 *            附件ID
	 * @return boolean
	 */
	public boolean isExisted(Serializable id);

	/**
	 * <p>
	 * 保存附件
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param attachment
	 */
	public void save(Attachment attachment);

	/**
	 * <p>
	 * 修改附件
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param attachment
	 */
	public void update(Attachment attachment);
}
