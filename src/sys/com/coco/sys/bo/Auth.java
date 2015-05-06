package com.coco.sys.bo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;

import com.coco.core.env.Config;
import com.coco.core.env.Context;
import com.coco.core.exception.CocoException;
import com.coco.core.util.CryptUtils;
import com.coco.sys.bo.api.IAuth;
import com.coco.sys.bo.api.IPostBO;
import com.coco.sys.bo.api.IUserBO;
import com.coco.sys.orm.UserPost;

public class Auth implements IAuth {

	private IUserBO userBo;

	private IPostBO postBo;

	public Auth() {

	}

	@Override
	public com.coco.sys.auth.User signIn(String userId, String password,
			HttpServletRequest request) {
		if (userId.isEmpty() || password.isEmpty()) {
			throw new CocoException(-1, "帐号和密码不能为空");
		}
		com.coco.sys.orm.User dbUser = userBo.get(userId);
		if (dbUser == null) {
			throw new CocoException(-1, "帐号不存在");
		}
		if (!dbUser.isValid()) {
			throw new CocoException(-1, "该帐号已经禁用");
		}
		if (dbUser.getPerson() == null) {
			throw new CocoException(-1, "该帐号已经没有关联人员");
		}
		if (!dbUser.getPswd().equals(CryptUtils.cryptPswd(password))) {
			throw new CocoException(-1, "密码错误");
		}
		com.coco.sys.auth.User user = new com.coco.sys.auth.User();
		user.setId(dbUser.getId());
		user.setNo(dbUser.getPerson().getNo());
		user.setPerson(dbUser.getPerson().getId());
		//员工类型
		//user.setPersonType(dbUser.getPerson());
		user.setName(dbUser.getPerson().getName());
		user.setDeptId(dbUser.getPerson().getDept().getId());
		user.setDeptName(dbUser.getPerson().getDept().getName());
		user.setDeptType(dbUser.getPerson().getDept().getType());
		user.setLoginIp(request.getRemoteAddr());
		user.setLoginTime(System.currentTimeMillis());
		user.setOperateTime(user.getLoginTime());

		// 如果不是超级管理员则设置岗位
		if (!Config.isAdmin(dbUser.getId())) {
			// 岗位
			List<String> postIds = new ArrayList<String>();
			List<UserPost> userPosts = userBo.findPosts(dbUser.getId());
			if (userPosts == null || userPosts.isEmpty()) {
				throw new CocoException(-1, "该用户没有配置岗位，请与管理员联系");
			}
			for (UserPost userPost : userPosts) {
				postIds.add(userPost.getPost().getId());
			}
			user.setPostIds(postIds);
			// 若仅有一个岗位则默认设置岗位
			if (postIds.size() == 1) {
				setPost(user, postIds.get(0));
			}

		}

		Context.putUser(request.getSession(), user);
		return user;
	}

	@Override
	public void changePost(HttpServletRequest request, String postId) {
		com.coco.core.bean.User user = Context.getUser(request);
		if (user == null) {
			throw new CocoException(-40001, "登录超时");
		}
		this.setPost((com.coco.sys.auth.User) user, postId);
	}

	private void setPost(com.coco.sys.auth.User user, String postId) {
		user.setCurrentPost(postId);
		user.setRoleIds(postBo.findRoleIds(postId));
	}

	@Override
	public void signOut(HttpServletRequest request) {
		Context.removeUser(request.getSession());
	}

	public void setUserBo(IUserBO userBo) {
		this.userBo = userBo;
	}

	public IUserBO getUserBo() {
		return userBo;
	}

	@Override
	public Document menu(HttpServletRequest request) {
		com.coco.core.bean.User user = Context.getUser(request);
		if (user == null) {
			return null;
		}
		return null;
	}

	public IPostBO getPostBo() {
		return postBo;
	}

	public void setPostBo(IPostBO postBo) {
		this.postBo = postBo;
	}

}
