package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.User;
import com.coco.sys.orm.UserPost;

/**
 * <p>
 * 用户数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:56:31
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IUserDAO {

	/**
	 * 新增用户
	 * 
	 * @param entity
	 */
	public void save(User entity);

	/**
	 * 更新用户
	 * 
	 * @param entity
	 */
	public void update(User entity);

	/**
	 * 根据用户编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询用户
	 * 
	 * @param qentity
	 */
	public void query(QEntity<User> qentity);

	/**
	 * 获取用户
	 * 
	 * @param id
	 * @return User
	 */
	public User get(Serializable id);

	/**
	 * 通过员工获取用户
	 * 
	 * @param id
	 * @return User
	 */
	public User getByPerson(Serializable id);

	/**
	 * <p>
	 * 属性用户及机构树
	 * </p>
	 * 
	 * @param onlyValid
	 * @return Document
	 */
	public Document tree(boolean onlyValid);

	/**
	 * <p>
	 * 获取用户的岗位列表
	 * </p>
	 * 
	 * @param id
	 * @return List<UserPost>
	 */
	public List<UserPost> findPosts(Serializable id);
}
