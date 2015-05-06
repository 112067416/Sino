package com.coco.core.env;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.coco.core.bean.User;

/**
 * <p>
 * 系统容器上下文
 * </p>
 * <p>
 * create: 2010-12-21 上午09:25:24
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Context {

	/**
	 * 请求线程列表
	 */
	private final static Map<Thread, HttpServletRequest> REQUESTS = new Hashtable<Thread, HttpServletRequest>();

	/**
	 * 登录用户会话键名
	 */
	private final static String KEY_USER = "COCO_USER";

	/**
	 * <p>
	 * 传入请求线程
	 * </p>
	 * 
	 * @param request
	 */
	static void put(HttpServletRequest request) {
		REQUESTS.put(Thread.currentThread(), request);
	}

	/**
	 * <p>
	 * 异常请求线程
	 * </p>
	 */
	static void remove() {
		REQUESTS.remove(Thread.currentThread());
	}

	/**
	 * <p>
	 * 当前请求线程
	 * </p>
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest currentRequest() {
		return REQUESTS.get(Thread.currentThread());
	}

	/**
	 * <p>
	 * 当前用户
	 * </p>
	 * 
	 * @return User
	 */
	public static User currentUser() {
		return getUser(currentRequest());
	}

	/**
	 * <p>
	 * 当前请求的用户
	 * </p>
	 * 
	 * @param request
	 * @return User
	 */
	public static User getUser(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return getUser(request.getSession());
	}

	/**
	 * <p>
	 * 当前会话的用户
	 * </p>
	 * 
	 * @param session
	 * @return User
	 */
	public static User getUser(HttpSession session) {
		if (session == null) {
			return null;
		}
		return (User) session.getAttribute(KEY_USER);
	}

	/**
	 * <p>
	 * 设置当前会话用户
	 * </p>
	 * 
	 * @param session
	 * @param user
	 */
	public static void putUser(HttpSession session, User user) {
		if (session == null || user == null) {
			return;
		}
		session.setAttribute(KEY_USER, user);
	}

	/**
	 * <p>
	 * 移除当前会话用户
	 * </p>
	 * 
	 * @param session
	 */
	public static void removeUser(HttpSession session) {
		session.removeAttribute(KEY_USER);
	}

}
