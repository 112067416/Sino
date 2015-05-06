package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Attachment;

/**
 * <p>
 * 附件数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:52:23
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IAttachmentDAO {

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
	 * 保存附件
	 * 
	 * @param entity
	 */
	public void save(Attachment entity);

	/**
	 * 修改附件
	 * 
	 * @param entity
	 */
	public void update(Attachment entity);

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
}
