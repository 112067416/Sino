package com.coco.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IUserDAO;
import com.coco.sys.orm.Dept;
import com.coco.sys.orm.User;
import com.coco.sys.orm.UserPost;

public class UserDAO implements IUserDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(User entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.executeUpdate("delete from UserPost where user.id=?", id);
		dao.delete(User.class, id);
	}

	@Override
	public void query(QEntity<User> qentity) {
		dao.query(qentity);
	}

	@Override
	public User get(Serializable id) {
		return dao.get(User.class, id);
	}

	@Override
	public void update(User entity) {
		dao.update(entity);
	}

	@Override
	public User getByPerson(Serializable id) {
		List<User> objs = dao.find(User.class, "person.id=?", id);
		return objs != null && !objs.isEmpty() ? objs.get(0) : null;
	}

	@Override
	public Document tree(boolean onlyValid) {
		List<Dept> depts = onlyValid ? dao.find(Dept.class, "valid=?", true)
				: dao.findAll(Dept.class);
		List<User> users = onlyValid ? dao.find(User.class, "valid=? and person.valid=?", true, true)
				: dao.findAll(User.class);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		if (depts != null) {
			Element item;
			for (Dept dept : depts) {
				item = root.addElement("item");
				item.addAttribute("id", "d_" + dept.getId());
				if ("0".equals(dept.getParent())) {
					item.addAttribute("pid", "0");
				}
				else {
					item.addAttribute("pid", "d_" + dept.getParent());
				}
				item.addAttribute("label", dept.getName());
				if (dept.getOrder() != null) {
					item.addAttribute("order", "0" + dept.getOrder());
				}
				else {
					item.addAttribute("order", "0");
				}
				item.addAttribute("leaf", "0");
			}
		}
		if (users != null) {
			Element item;
			for (User user : users) {
				item = root.addElement("item");
				item.addAttribute("id", user.getId());
				item.addAttribute("pid", "d_"
						+ user.getPerson().getDept().getId());
				item.addAttribute("label", user.getPerson().getName());
				if (user.getPerson().getOrder() != null) {
					item.addAttribute("order", "0"
							+ user.getPerson().getOrder());
				}
				else {
					item.addAttribute("order", "1");
				}
				item.addAttribute("leaf", "1");
			}
		}
		return doc;
	}

	@Override
	public List<UserPost> findPosts(Serializable id) {
		return dao.find(UserPost.class, "user.id=?", id);
	}

}
