package com.coco.sys.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.CryptUtils;
import com.coco.sys.bo.api.IUserBO;
import com.coco.sys.dao.api.IUserDAO;
import com.coco.sys.orm.Person;
import com.coco.sys.orm.User;
import com.coco.sys.orm.UserPost;

public class UserBO implements IUserBO {

	private IUserDAO dao;

	@Override
	public void save(User entity) {
		if (dao.get(entity.getId()) != null) {
			throw new CocoException(-10003, "该帐号已经被使用，请更换其他帐号");
		}
		entity.setPswd(CryptUtils.cryptPswd(entity.getPswd()));
		dao.save(entity);
	}

	@Override
	public void update(User entity) {
		User dbEntity = this.get(entity.getId());
		if (dbEntity == null) {
			throw new CocoException(-20001);
		}
		// 若没有给定信息的密码则不修改密码
		if (entity.getPswd() == null || entity.getPswd().isEmpty()) {
			entity.setPswd(dbEntity.getPswd());
		}
		else {
			entity.setPswd(CryptUtils.cryptPswd(entity.getPswd()));
		}
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<User> qentity) {
		dao.query(qentity);
	}

	@Override
	public User get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(dao.get(id)).toJsObject();
	}

	@Override
	public String getByPersonForJs(Serializable id) {
		User user = dao.getByPerson(id);
		if (user == null) {
			user = new User();
			Person person = new Person();
			person.setId((String) id);
			user.setPerson(person);
		}
		Set<String> ignores = new HashSet<String>();
		ignores.add("pswd");
		return new Result(user).toJsObject(null, ignores);
	}

	public IUserDAO getDao() {
		return dao;
	}

	public void setDao(IUserDAO dao) {
		this.dao = dao;
	}

	@Override
	public Document tree(boolean onlyValid) {
		return dao.tree(onlyValid);
	}

	@Override
	public List<UserPost> findPosts(Serializable id) {
		return dao.findPosts(id);
	}

}
