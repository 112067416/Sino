package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 岗位角色主键
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
public class PostRolePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "POST_", referencedColumnName = "ID_")
	private Post post;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ROLE_", referencedColumnName = "ID_")
	private Role role;

	public PostRolePK() {

	}

	public PostRolePK(Post post, Role role) {
		this.post = post;
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((role == null) ? 0 : role.hashCode());
		result = PRIME * result + ((post == null) ? 0 : post.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PostRolePK other = (PostRolePK) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		return true;
	}

}