package com.coco.sys.bo.api;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;

import com.coco.sys.auth.User;

/**
 * <p>
 * 系统认证接口
 * </p>
 * <p>
 * create: 2010-12-27 上午10:27:57
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IAuth {

	/**
	 * <p>
	 * 登录
	 * </p>
	 * 
	 * @param userId
	 * @param password
	 * @param request
	 * @return User
	 */
	public User signIn(String userId, String password,
			HttpServletRequest request);

	/**
	 * <p>
	 * 注销
	 * </p>
	 * 
	 * @param request
	 */
	public void signOut(HttpServletRequest request);

	/**
	 * <p>
	 * 更换岗位
	 * </p>
	 * 
	 * @param request
	 * @param postId
	 */
	public void changePost(HttpServletRequest request, String postId);

	/**
	 * <p>
	 * 登录人员的菜单
	 * </p>
	 * 
	 * @param request
	 * @return Document
	 */
	public Document menu(HttpServletRequest request);

}
