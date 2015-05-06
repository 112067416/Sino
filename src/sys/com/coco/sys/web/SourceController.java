package com.coco.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.sys.bo.api.ISourceBO;
import com.coco.sys.orm.SourceClass;
import com.coco.sys.orm.SourceMethod;
import com.coco.sys.vo.SourceQE;

/**
 * <p>
 * 资源管理控制器
 * </p>
 * <p>
 * create: 2010-12-10 下午05:05:06
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/source")
public class SourceController {

	@Autowired
	private ISourceBO bo;

	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(SourceQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sys/source/list" : "/sys/source/index";
	}

	@RequestMapping(value = "/saveClass", method = RequestMethod.POST)
	public @ResponseBody
	String saveClass(SourceClass entity, String $type) {
		if ("modify".equals($type)) {
			bo.update(entity);
		}
		else {
			bo.save(entity);
		}
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/saveMethod", method = RequestMethod.POST)
	public @ResponseBody
	String saveMethod(SourceMethod entity, String $type) {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new CocoException(-1, "名称不能为空");
		}
		if (entity.getMethod() == null || entity.getMethod().isEmpty()) {
			throw new CocoException(-1, "方法不能为空");
		}
		if ("modify".equals($type)) {
			bo.update(entity);
		}
		else {
			bo.save(entity);
		}
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/delClass", method = RequestMethod.POST)
	public @ResponseBody
	String deleteClass(String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/delMethod", method = RequestMethod.POST)
	public @ResponseBody
	String deleteMethod(String id) {
		bo.deleteMethod(id);
		return Result.SUCCESS;
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
