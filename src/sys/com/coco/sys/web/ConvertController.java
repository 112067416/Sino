package com.coco.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IConvertBO;
import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.vo.ConvertQE;

/**
 * <p>
 * 转换配置
 * </p>
 * <p>
 * create: 2010-12-31 下午04:54:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/convert")
public class ConvertController {

	@Autowired
	private IConvertBO bo;

	public ConvertController() {

	}

	/**
	 * 主页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(ConvertQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sys/convert/list";
		}
		return "/sys/convert/index";
	}

	/**
	 * <p>
	 * 保存
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(ConvertEntity entity, String $type) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			return Result.ERROR;
		}
		if ("modify".equals($type)) {
			bo.update(entity);
		}
		else {
			bo.save(entity);
		}
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 添加/修改跳转页面方法
	 * </p>
	 * 
	 * @param id
	 *            修改传递的主键
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(String id, Model model) {
		ConvertEntity entity = null;
		if (id != null) {
			entity = bo.get(id);
		}
		model.addAttribute("entity", entity);
		return "/sys/convert/detail";
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IConvertBO bo) {
		this.bo = bo;
	}

}
