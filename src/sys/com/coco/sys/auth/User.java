package com.coco.sys.auth;

import java.util.List;

public class User extends com.coco.core.bean.User {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	private List<String> postIds;

	private String currentPost;

	private List<String> roleIds;

	public List<String> getPostIds() {
		return postIds;
	}

	public void setPostIds(List<String> postIds) {
		this.postIds = postIds;
	}

	public String getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(String currentPost) {
		this.currentPost = currentPost;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

}
