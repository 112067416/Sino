package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 用户岗位
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
@Entity
@Table(name = "COCO_USER_POST")
@IdClass(UserPostPK.class)
public class UserPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private User user;

	@Id
	private Post post;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
