package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.User;
import com.coco.sys.orm.UserPost;

/**
 * <p>
 * 用户业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:46:07
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IUserBO {

	/**
	 * 新增用户
	 * 
	 * @param entity
	 */
	public void save(User entity);

	/**
	 * 修改用户
	 * 
	 * @param entity
	 */
	public void update(User entity);

	/**
	 * 删除对应的信息
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
	 * <p>
	 * 获取用户js串，不包含密码
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);

	/**
	 * <p>
	 * 根据员工Id获取用户js串，不包含密码
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	public String getByPersonForJs(Serializable id);

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
