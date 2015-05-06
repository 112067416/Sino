package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 角色资源主键
 * 
 * @author 许德建[xudejian@126.com]
 */
public class RoleSourcePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ROLE_", referencedColumnName = "ID_")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SOURCE_", referencedColumnName = "ID_")
	private SourceMethod source;

	public RoleSourcePK() {

	}

	public RoleSourcePK(Role role, SourceMethod source) {
		this.role = role;
		this.source = source;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((source == null) ? 0 : source.hashCode());
		result = PRIME * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final RoleSourcePK other = (RoleSourcePK) obj;
		if (source == null) {
			if (other.source != null) return false;
		}
		else if (!source.equals(other.source)) return false;
		if (role == null) {
			if (other.role != null) return false;
		}
		else if (!role.equals(other.role)) return false;
		return true;
	}

}