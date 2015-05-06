package com.coco.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.xml.XMLHelper;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.orm.Seq;
import com.coco.sys.orm.SeqRule;
import com.coco.sys.vo.SeqQE;

/**
 * @author 许德建[xudejian@126.com]
 */
@Controller
@RequestMapping("/sys/seq")
public class SeqController {

	@Autowired
	private ISeqBO bo;

	public void setBo(ISeqBO bo) {
		this.bo = bo;
	}

	public SeqController() {
	}

	/**
	 * <p>
	 * 主页面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(SeqQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		if (page.isQuery()) {
			return "/sys/seq/list";
		}
		return "/sys/seq/index";
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
	String save(Seq entity, String $type) {
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

	@RequestMapping(value = "/del!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id) {
		return bo.getForJs(id);
	}

	@RequestMapping(value = "/listRule!{id}", method = RequestMethod.POST)
	public String listRule(@PathVariable String id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("rules", bo.findRules(id));
		return "/sys/seq/rules";
	}

	@RequestMapping(value = "/view!{id}", method = RequestMethod.POST)
	public String view(@PathVariable String id, Model model) {
		model.addAttribute("seq", bo.get(id));
		model.addAttribute("rules", bo.findRules(id));
		return "/sys/seq/view";
	}

	@RequestMapping(value = "/saveItem!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String saveItem(@PathVariable String id, HttpServletRequest request) {
		List<SeqRule> rules = null;
		try {
			rules = XMLHelper.createNode2Bean(SeqRule.class).parseToBeans(request.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR;
		}
		bo.saveRules(id, rules);
		return Result.SUCCESS;
	}

}
