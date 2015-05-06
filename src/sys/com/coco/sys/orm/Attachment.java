package com.coco.sys.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统附件
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "COCO_ATTACHMENT")
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 附件名
	 */
	@Column(name = "NAME_", length = 256, nullable = false)
	private String name;

	/**
	 * 附件类型
	 */
	@Column(name = "TYPE_", length = 8, nullable = false)
	private String type;

	/**
	 * 关联ID
	 */
	@Column(name = "REL_", length = 64)
	private String rel;

	/**
	 * 扩展名
	 */
	@Column(name = "EXT_", length = 32)
	private String ext;

	/**
	 * 文件类型
	 */
	@Column(name = "CONTEXT_TYPE_", length = 64)
	private String contentType;

	/**
	 * 文件大小
	 */
	@Column(name = "LENGTH_")
	private long length;

	/**
	 * 原文件名
	 */
	@Column(name = "ORIGINAL_NAME", length = 256)
	private String originalName;

	/**
	 * 文件创建时间
	 */
	@Column(name = "CREATE_TIME_")
	private Date createTime;

	/**
	 * 文件流
	 */
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "STREAM_", length = 2000000000)
	private byte[] stream;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel
	 *            the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext
	 *            the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the length
	 */
	public long getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(long length) {
		this.length = length;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the stream
	 */
	public byte[] getStream() {
		return stream;
	}

	/**
	 * @param stream
	 *            the stream to set
	 */
	public void setStream(byte[] stream) {
		this.stream = stream;
	}

	/**
	 * @return the originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * @param originalName
	 *            the originalName to set
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

}
