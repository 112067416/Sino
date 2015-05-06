package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 岗位角色
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
@Entity
@Table(name = "COCO_POST_ROLE")
@IdClass(PostRolePK.class)
public class PostRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Post post;

	@Id
	private Role role;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
