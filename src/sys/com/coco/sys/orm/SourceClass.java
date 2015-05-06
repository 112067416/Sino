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
 * 权限资源
 * </p>
 * <p>
 * create: 2010-12-10 下午02:51:56
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "COCO_SOURCE_CLASS")
public class SourceClass implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 类名
	 */
	@Id
	@Column(name = "CLASS_", length = 128)
	private String className;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 64, nullable = false)
	private String name;

	@OneToMany(mappedBy = "sourceClass", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("method")
	private List<SourceMethod> methods;

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
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
	 * @return the methods
	 */
	public List<SourceMethod> getMethods() {
		return methods;
	}

	/**
	 * @param methods
	 *            the methods to set
	 */
	public void setMethods(List<SourceMethod> methods) {
		this.methods = methods;
	}

}
