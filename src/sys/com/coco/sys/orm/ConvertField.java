package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 文件转换成对象配置字段
 * </p>
 * <p>
 * create: 2010-12-31 上午09:04:05
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "COCO_CONVERT_FIELD")
public class ConvertField implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 转换配置实体
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ENTITY_", referencedColumnName = "ID_")
	private ConvertEntity entity;

	/**
	 * 题头
	 */
	@Column(name = "TITLE_", length = 128)
	private String title;

	/**
	 * 列号，从0算起。若指定了题头，则列号无效。
	 */
	@Column(name = "INDEX_")
	private Integer index;

	/**
	 * 转换字段名称
	 */
	@Column(name = "FIELD_", length = 64, nullable = false)
	private String field;

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
	 * @return the entity
	 */
	public ConvertEntity getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(ConvertEntity entity) {
		this.entity = entity;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

}
