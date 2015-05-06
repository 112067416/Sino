package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 资源方法
 * </p>
 * <p>
 * create: 2010-12-10 下午03:04:29
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "COCO_SOURCE_METHOD", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CLASS_", "METHOD_" }) })
public class SourceMethod implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	public SourceMethod() {

	}

	public SourceMethod(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 关联类
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "CLASS_", referencedColumnName = "CLASS_")
	private SourceClass sourceClass;

	/**
	 * 方法名
	 */
	@Column(name = "METHOD_", length = 128, nullable = false)
	private String method;

	/**
	 * 方法说明
	 */
	@Column(name = "NAME_", length = 64, nullable = false)
	private String name;

	/**
	 * @return the sourceClass
	 */
	public SourceClass getSourceClass() {
		return sourceClass;
	}

	/**
	 * @param sourceClass
	 *            the sourceClass to set
	 */
	public void setSourceClass(SourceClass sourceClass) {
		this.sourceClass = sourceClass;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
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

}
