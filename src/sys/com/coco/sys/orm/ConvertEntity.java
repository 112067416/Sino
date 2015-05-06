package com.coco.sys.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * <p>
 * 文件转换成对象配置实体
 * </p>
 * <p>
 * create: 2010-12-31 上午09:03:50
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "COCO_CONVERT_ENTITY")
public class ConvertEntity implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 转换配置名称
	 */
	@Column(name = "NAME_", length = 32)
	private String name;

	/**
	 * 转换器类型
	 */
	@Column(name = "TYPE_", length = 1)
	private String type;

	/**
	 * 默认转换成对象的类名
	 */
	@Column(name = "DEFAULT_CLASS_", length = 256)
	private String defaultClass;

	/**
	 * 题头行号,若为-1表示没有题头
	 */
	@Column(name = "TITLE_ROW_")
	private int titleRow;

	/**
	 * 题头行分割符，当转换器为文本转换时有效，默认为","
	 */
	@Column(name = "SPLIT_", length = 20)
	private String split;

	/**
	 * 起始行数，从0算起
	 */
	@Column(name = "FIRST_")
	private int first;

	/**
	 * 转换行数，若<=0则不限定行数
	 */
	@Column(name = "LENGTH_")
	private int length;

	/**
	 * 转换配置列表
	 */
	@OneToMany(mappedBy = "entity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("index")
	private List<ConvertField> fields;

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
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(int first) {
		this.first = first;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the fields
	 */
	public List<ConvertField> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<ConvertField> fields) {
		this.fields = fields;
	}

	/**
	 * @return the titleRow
	 */
	public int getTitleRow() {
		return titleRow;
	}

	/**
	 * @param titleRow
	 *            the titleRow to set
	 */
	public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}

	/**
	 * @return the defaultClass
	 */
	public String getDefaultClass() {
		return defaultClass;
	}

	/**
	 * @param defaultClass
	 *            the defaultClass to set
	 */
	public void setDefaultClass(String defaultClass) {
		this.defaultClass = defaultClass;
	}

	/**
	 * @return the split
	 */
	public String getSplit() {
		return split;
	}

	/**
	 * @param split
	 *            the split to set
	 */
	public void setSplit(String split) {
		this.split = split;
	}

}
