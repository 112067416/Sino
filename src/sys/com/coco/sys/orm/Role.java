package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 系统角色
 * </p>
 * <p>
 * create time : 2010-10-14 下午09:24:06
 * </p>
 * 
 * @author 许德建(xudejian@126.com)
 */
@Entity
@Table(name = "COCO_ROLE")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Role() {

	}

	public Role(String id) {
		this.id = id;
	}

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 32, nullable = false)
	private String name;

	/**
	 * 备注
	 */
	@Column(name = "DESCRIPTION_", length = 256)
	private String description;

	/**
	 * 有效标志
	 */
	@Column(name = "VALID_")
	private boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
