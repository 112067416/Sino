package com.coco.sys.auth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import com.coco.core.env.Config;
import com.coco.core.env.Context;
import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.ISourceBO;
import com.coco.sys.web.SysController;

public class AuthAspectManager {

	private ISourceBO bo;

	public void before(JoinPoint jp) {
		User user = (User) Context.currentUser();
		Signature sign = jp.getSignature();
		if (user == null) {
			// 登录页面忽略
			if (!sign.getDeclaringType().equals(SysController.class)
					|| !sign.getName().equals("login")) {
				throw new CocoException(-40001);
			}
			return;
		}
		// 主页部分通过
		if (sign.getDeclaringType().equals(SysController.class)) {
			return;
		}
		// 判断是否有访问权限
		// 若为超级管理员，则通过
		if (Config.isAdmin(user.getId())) {
			return;
		}
		int result = bo.checkSource(sign.getDeclaringTypeName(), sign.getName(), user.getRoleIds());
		// 若没有权限，则抛出无权限异常
		if (result == 0) {
			throw new CocoException(-40002);
		}
	}

	public void afterReturning(JoinPoint jp) {
	}

	/**
	 * @return the bo
	 */
	public ISourceBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(ISourceBO bo) {
		this.bo = bo;
	}
}
