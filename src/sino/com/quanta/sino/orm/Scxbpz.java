package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 生产线别配置表
 * </p>
 * <p>
 * create: 2011-1-6 上午09:22:40
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_SCXBPZ")
public class Scxbpz implements Serializable {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 生产类型
	 */
	@Column(name = "TYPE_", length = 1, nullable = false)
	private String type;

	/**
	 * 所属部门
	 */
	@Column(name = "DEPT_", length = 40, nullable = false)
	private String dept;

	/**
	 * 线别名称
	 */
	@Column(name = "NAME_", length = 40, nullable = false)
	private String name;

	/**
	 * 线别代码
	 */
	@Column(name = "CODE_", length = 2, nullable = false)
	private String code;

	/**
	 * 合格品Pile代码
	 */
	@Column(name = "QUALIFIED_", length = 2)
	private String qualified;

	/**
	 * 选别品Pile代码
	 */
	@Column(name = "UNQUALIFIED_", length = 2)
	private String unqualified;

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
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the qualified
	 */
	public String getQualified() {
		return qualified;
	}

	/**
	 * @param qualified
	 *            the qualified to set
	 */
	public void setQualified(String qualified) {
		this.qualified = qualified;
	}

	/**
	 * @return the unqualified
	 */
	public String getUnqualified() {
		return unqualified;
	}

	/**
	 * @param unqualified
	 *            the unqualified to set
	 */
	public void setUnqualified(String unqualified) {
		this.unqualified = unqualified;
	}

}
