package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Post;
import com.coco.sys.orm.PostRole;
import com.coco.sys.orm.UserPost;

/**
 * <p>
 * 岗位业务接口
 * </p>
 * <p>
 * create: 2010-12-21 上午10:39:35
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IPostBO {

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
	 * <p>
	 * 根据指定的Id列表获取岗位
	 * </p>
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
	 * 根据岗位ID获取岗位角色
	 * </p>
	 * 
	 * @param post
	 * @return List<PostRole>
	 */
	public List<PostRole> findPostRoles(String post);

	/**
	 * <p>
	 * 根据岗位ID获取岗位角色的角色Id列表
	 * </p>
	 * 
	 * @param post
	 * @return List<String>
	 */
	public List<String> findRoleIds(String post);

	/**
	 * <p>
	 * 根据岗位ID获取用户岗位的用户Id列表
	 * </p>
	 * 
	 * @param post
	 * @return List<String>
	 */
	public List<String> findUserIds(String post);

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

	/**
	 * 获取岗位
	 * 
	 * @param id
	 * @return String
	 */
	public String getForJs(Serializable id);
}
