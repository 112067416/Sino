package com.quanta.sino.cmn.web;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.IDirectoryBO;
import com.coco.sys.orm.Attachment;
import com.coco.sys.vo.AttachQE;
import com.quanta.sino.cmn.bo.api.IInformBO;
import com.quanta.sino.cmn.constants.Convert;
import com.quanta.sino.cmn.constants.ImageExt;
import com.quanta.sino.cmn.vo.InformQE;
import com.quanta.sino.orm.InformTp;

/**
 * <p>
 * 资料室控制器
 * </p>
 * <p>
 * create: 2011-1-8 下午04:38:07
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cmn/inform")
public class InformController {

	/**
	 * 资料室管理业务接口
	 */
	@Autowired
	private IInformBO bo;

	/**
	 * 附件管理业务接口
	 */
	@Autowired
	private IAttachmentBO attachmentBO;

	/**
	 * 目录管理业务接口
	 */
	@Autowired
	private IDirectoryBO directoryBO;

	/**
	 * <p>
	 * 资料管理主页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(InformQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("creat desc");
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sino/cmn/inform/list";
		}
		model.addAttribute("directories", directoryBO.findValid());
		return "/sino/cmn/inform/index";
	}

	/**
	 * <p>
	 * 资料阅读管理主页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/index!view")
	public String view(InformQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("creat desc");
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sino/cmn/inform/list!view";
		}
		model.addAttribute("directories", directoryBO.findValid());
		return "/sino/cmn/inform/index!view";
	}

	/**
	 * <p>
	 * 导入文件管理主页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/index!dr")
	public String dr(AttachQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys("createTime desc");
		if (!page.isQuery() || page.getType() == null
				|| page.getType().isEmpty()) {
			page.setTypes(Convert.list());
		}
		attachmentBO.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/cmn/inform/list!dr"
				: "/sino/cmn/inform/index!dr";
	}

	/**
	 * <p>
	 * 保存资料
	 * </p>
	 * 
	 * @param entity
	 * @param type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(InformTp entity) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时，请重新登陆。").toString();
		}
		if (bo.isExisted(entity.getName())) {
			return new Result(-1, "该名称已存在").toString();
		}
		String attachId = entity.getAttachment();
		Attachment attachment = null;
		if (attachId == null || attachId.isEmpty()
				|| (attachment = attachmentBO.get(attachId)) == null) {
			return new Result(-1, "未上传附件").toString();
		}
		entity.setExt(attachment.getExt());
		entity.setDdno(user.getNo());
		entity.setDdnm(user.getName());
		entity.setCreat(new Date());
		bo.save(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除资料
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "资料ID不能为空").toString();
		}
		bo.delete(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量删除资料
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		bo.deletes(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得资料的授权用户
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getUsers!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getUsers(@PathVariable String id) {
		InformTp entity = bo.get(id);
		if (entity == null) {
			return new Result(-1, "该资料不存在").toString();
		}
		return entity.getUsers() != null && !entity.getUsers().isEmpty() ? entity.getUsers()
				: "";
	}

	/**
	 * <p>
	 * 为资料授权用户
	 * </p>
	 * 
	 * @param id
	 * @param users
	 * @return String
	 */
	@RequestMapping(value = "/grantUser!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantUser(@PathVariable String id, String users) {
		InformTp entity = bo.get(id);
		if (entity == null) {
			return new Result(-1, "该资料不存在").toString();
		}
		entity.setUsers(users);
		bo.update(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查看附件
	 * </p>
	 * 
	 * @param id
	 *            资料主键
	 * @param attachId
	 *            附件主键
	 * @param name
	 *            资料名称
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "viewAttach")
	public String viewAttach(String id, String attachId, String name,
			Model model) {
		if (attachId != null && !attachId.isEmpty()) {
			Attachment attachment = attachmentBO.get(attachId);
			model.addAttribute("attachId", attachId);
			if (ImageExt.isExisted(attachment.getExt())) {
				return "/sino/cmn/inform/viewImage";
			}
			model.addAttribute("ext", attachment.getExt());
			return "/sino/cmn/inform/viewAttach";
		}
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "先要登录"));
			return Result.URL_ERROR;
		}
		InformTp entity = null;
		if (id != null && !id.isEmpty()) {
			entity = bo.get(id);
		}
		else if (name != null && !(name = name.trim()).isEmpty()) {
			entity = bo.getByName(name);
		}
		if (entity == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "该资料不存在"));
			return Result.URL_ERROR;
		}
		if (entity.getUsers() != null && !entity.getUsers().isEmpty()) {
			if (entity.getUsers().indexOf(user.getId()) < 0) {
				model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
						"没有权限访问该资料"));
				return Result.URL_ERROR;
			}
		}
		attachId = entity.getAttachment();
		if (attachId == null || attachId.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "附件主键为空"));
			return Result.URL_ERROR;
		}
		if (!attachmentBO.isExisted(attachId)) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "附件不存在"));
			return Result.URL_ERROR;
		}
		model.addAttribute("attachId", attachId);
		if (ImageExt.isExisted(entity.getExt())) {
			return "/sino/cmn/inform/viewImage";
		}
		model.addAttribute("ext", entity.getExt());
		return "/sino/cmn/inform/viewAttach";
	}

	/**
	 * <p>
	 * 判断资料室中是否存在文件
	 * </p>
	 * 
	 * @param name
	 * @return String
	 */
	@RequestMapping(value = "/isExisted", method = RequestMethod.POST)
	public @ResponseBody
	String isExisted(String name) {
		if (name == null || name.isEmpty()) {
			return new Result(-1, "缺少参数").toString();
		}
		if (!bo.isExisted(name)) {
			return new Result(-1, "在资料室中不存在").toString();
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 为资料授权用户
	 * </p>
	 * 
	 * @param id
	 * @param users
	 * @return String
	 */
	@RequestMapping(value = "/grantDept!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String grantDept(@PathVariable String id, String depts) {
		bo.grantDept(id, depts);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得资料的授权用户
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getDepts!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String getDepts(@PathVariable String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "存在参数为空").toString();
		}
		return bo.getDept(id);
	}

	public IInformBO getBo() {
		return bo;
	}

	public void setBo(IInformBO bo) {
		this.bo = bo;
	}

	public IAttachmentBO getAttachmentBO() {
		return attachmentBO;
	}

	public void setAttachmentBO(IAttachmentBO attachmentBO) {
		this.attachmentBO = attachmentBO;
	}

	public IDirectoryBO getDirectoryBO() {
		return directoryBO;
	}

	public void setDirectoryBO(IDirectoryBO directoryBO) {
		this.directoryBO = directoryBO;
	}

}