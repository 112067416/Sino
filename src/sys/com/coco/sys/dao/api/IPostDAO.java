package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Post;
import com.coco.sys.orm.PostRole;
import com.coco.sys.orm.UserPost;

/**
 * <p>
 * 岗位数据访问接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:55:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IPostDAO {

	/**
	 * 新增岗位
	 * 
	 * @param entity
	 */
	public void save(Post entity);

	/**
	 * 更新岗位
	 * 
	 * @param entity
	 */
	public void update(Post entity);

	/**
	 * 根据岗位编号删除对应的信息
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 查询岗位
	 * 
	 * @param qentity
	 */
	public void query(QEntity<Post> qentity);

	/**
	 * 获取岗位
	 * 
	 * @param id
	 * @return Post
	 */
	public Post get(Serializable id);

	/**
	 * 获取岗位列表
	 * 
	 * @param ids
	 * @return List<Post>
	 */
	public List<Post> find(List<String> ids);

	/**
	 * <p>
	 * 根据岗位ID获取用户岗位
	 * </p>
	 * 
	 * @param post
	 * @return List<UserPost>
	 */
	public List<UserPost> findUserPosts(String post);

	/**
	 * <p>
	 * 获取岗位的角色
	 * </p>
	 * 
	 * @param post
	 * @return List<PostRole>
	 */
	public List<PostRole> findPostRoles(String post);

	/**
	 * <p>
	 * 保存用户岗位
	 * </p>
	 * 
	 * @param post
	 * @param users
	 */
	public void grantUser(String post, String[] users);

	/**
	 * <p>
	 * 保存岗位角色
	 * </p>
	 * 
	 * @param post
	 * @param roles
	 */
	public void grantRole(String post, String[] roles);

}
