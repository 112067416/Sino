package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 角色资源
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "COCO_ROLE_SOURCE")
@IdClass(RoleSourcePK.class)
public class RoleSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Role role;

	@Id
	private SourceMethod source;

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the source
	 */
	public SourceMethod getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(SourceMethod source) {
		this.source = source;
	}

}
