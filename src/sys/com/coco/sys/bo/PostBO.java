package com.coco.sys.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IPostBO;
import com.coco.sys.dao.api.IPostDAO;
import com.coco.sys.orm.Post;
import com.coco.sys.orm.PostRole;
import com.coco.sys.orm.UserPost;

public class PostBO implements IPostBO {

	private IPostDAO dao;

	@Override
	public void save(Post entity) {
		dao.save(entity);
	}

	@Override
	public void update(Post entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<Post> qentity) {
		dao.query(qentity);
	}

	@Override
	public Post get(Serializable id) {
		return dao.get(id);
	}

	public IPostDAO getDao() {
		return dao;
	}

	public void setDao(IPostDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<UserPost> findUserPosts(String post) {
		return dao.findUserPosts(post);
	}

	@Override
	public List<PostRole> findPostRoles(String post) {
		return dao.findPostRoles(post);
	}

	@Override
	public List<String> findUserIds(String post) {
		List<UserPost> items = findUserPosts(post);
		List<String> ids = new ArrayList<String>();
		for (UserPost item : items) {
			ids.add(item.getUser().getId());
		}
		return ids;
	}

	@Override
	public List<String> findRoleIds(String post) {
		List<PostRole> items = findPostRoles(post);
		List<String> ids = new ArrayList<String>();
		for (PostRole item : items) {
			ids.add(item.getRole().getId());
		}
		return ids;
	}

	@Override
	public void grantUser(String post, String[] users) {
		dao.grantUser(post, users);
	}

	@Override
	public void grantRole(String post, String[] roles) {
		dao.grantRole(post, roles);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public List<Post> find(List<String> ids) {
		return dao.find(ids);
	}

}
