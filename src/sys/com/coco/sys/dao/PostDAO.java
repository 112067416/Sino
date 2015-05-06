package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IPostDAO;
import com.coco.sys.orm.Post;
import com.coco.sys.orm.PostRole;
import com.coco.sys.orm.Role;
import com.coco.sys.orm.User;
import com.coco.sys.orm.UserPost;

public class PostDAO implements IPostDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Post entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.executeUpdate("delete from UserPost where post.id=?", id);
		dao.executeUpdate("delete from PostRole where post.id=?", id);
		dao.delete(Post.class, id);
	}

	@Override
	public void query(QEntity<Post> qentity) {
		dao.query(qentity);
	}

	@Override
	public Post get(Serializable id) {
		return dao.get(Post.class, id);
	}

	@Override
	public void update(Post entity) {
		dao.update(entity);
	}

	@Override
	public List<UserPost> findUserPosts(String post) {
		return dao.find(UserPost.class, "post.id=?", post);
	}

	@Override
	public List<PostRole> findPostRoles(String post) {
		return dao.find(PostRole.class, "post.id=?", post);
	}

	@Override
	public void grantUser(String post, String[] users) {
		if (post == null) {
			return;
		}
		List<UserPost> deletes = findUserPosts(post);
		List<UserPost> adds = new ArrayList<UserPost>();
		UserPost item;
		if (users != null) {
			Iterator<UserPost> it;
			boolean isNew;
			for (String user : users) {
				isNew = true;
				it = deletes.iterator();
				while (it.hasNext()) {
					if (it.next().getUser().getId().equals(user)) {
						it.remove();
						isNew = false;
						break;
					}
				}
				if (isNew) {
					item = new UserPost();
					item.setPost(new Post(post));
					item.setUser(new User(user));
					adds.add(item);
				}
			}
		}
		if (!deletes.isEmpty()) {
			for (UserPost entity : deletes) {
				dao.delete(entity);
			}
		}
		if (!adds.isEmpty()) {
			for (UserPost entity : adds) {
				dao.save(entity);
			}
		}
	}

	@Override
	public void grantRole(String post, String[] roles) {
		if (post == null) {
			return;
		}
		List<PostRole> deletes = findPostRoles(post);
		List<PostRole> adds = new ArrayList<PostRole>();
		PostRole item;
		if (roles != null) {
			Iterator<PostRole> it;
			boolean isNew;
			for (String role : roles) {
				isNew = true;
				it = deletes.iterator();
				while (it.hasNext()) {
					if (it.next().getRole().getId().equals(role)) {
						it.remove();
						isNew = false;
						break;
					}
				}
				if (isNew) {
					item = new PostRole();
					item.setPost(new Post(post));
					item.setRole(new Role(role));
					adds.add(item);
				}
			}
		}
		if (!deletes.isEmpty()) {
			for (PostRole entity : deletes) {
				dao.delete(entity);
			}
		}
		if (!adds.isEmpty()) {
			for (PostRole entity : adds) {
				dao.save(entity);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> find(List<String> ids) {
		return (List<Post>) dao.findForValues("from Post where id in (?)", ids);
	}
}
