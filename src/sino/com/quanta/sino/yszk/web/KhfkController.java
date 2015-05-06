package com.quanta.sino.yszk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.constants.FpStat;
import com.quanta.sino.orm.Khfk;
import com.quanta.sino.yszk.bo.api.IKhfkBO;
import com.quanta.sino.yszk.vo.KhfkQE;
import com.quanta.sino.yszk.vo.KhfkVO;

/**
 * <p>
 * 客户付款管理
 * </p>
 * <p>
 * create: 2011-6-30 下午12:48:09
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/hkgl/khfk")
public class KhfkController {

	/**
	 * 客户付款业务接口
	 */
	@Autowired
	private IKhfkBO bo;

	/**
	 * 
	 */
	@Autowired
	private IYongBO yongBO;

	/**
	 * <p>
	 * 客户付款查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexKhfk")
	public String indexKhfk(KhfkQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		if (!page.isQuery()) {
			List<String> list = yongBO.findFpkhs();
			StringBuilder fpkhs = new StringBuilder();
			if (list != null && list.size() > 0) {
				for (String fpkh : list) {
					if (fpkhs.length() > 0) {
						fpkhs.append(",").append("['").append(fpkh).append("','").append(fpkh).append("']");
						continue;
					}
					fpkhs.append("[['").append(fpkh).append("','").append(fpkh).append("']");
				}
				fpkhs.append("]");
			}
			StringBuilder stat = new StringBuilder();
			stat.append("'").append(FpStat.WJS.key).append("':'").append(FpStat.WJS.name).append("','").append(FpStat.YJS.key).append("':'").append(FpStat.YJS.name).append("'");
			model.addAttribute("fpkhs", fpkhs.toString());
			model.addAttribute("stat", stat.toString());
			model.addAttribute("page", page);
			return "/sino/hkgl/khfk/indexKhfk";
		}
		page.setOrderBys("name asc,crea asc");
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("fixed", FpStat.YJS.key);
		return page.isQuery() ? "/sino/hkgl/khfk/listKhfk"
				: "/sino/hkgl/khfk/indexKhfk";
	}

	/**
	 * <p>
	 * 客户余额查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/khye")
	public String khye(KhfkQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/hkgl/khfk/listKhye"
				: "/sino/hkgl/khfk/indexKhye";
	}

	/**
	 * <p>
	 * 加载客户付款
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "主文件ID不能为空").toString();
		}
		return bo.getForJs(id);
	}

	/**
	 * <p>
	 * 保存客户付款信息
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Khfk entity, String $type) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if ("modify".equals($type)) {
			return bo.update(entity);
		}
		return bo.save(entity, user);
	}

	/**
	 * <p>
	 * 删除客户付款信息
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String[] ids) {
		if (ids == null || ids.length == 0) {
			return new Result(-1, "主键不能为空").toString();
		}
		return bo.delete(ids);
	}

	/**
	 * <p>
	 * 跳转进入调整金额页面
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/tzje", method = RequestMethod.POST)
	public @ResponseBody
	String tzje(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "付款客户ID不能为空").toString();
		}
		return bo.getTzje(id);
	}

	/**
	 * <p>
	 * 保存调整金额信息
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/saveTzje", method = RequestMethod.POST)
	public @ResponseBody
	String saveTzje(KhfkVO vo) {
		if (vo == null) {
			return new Result(-1, "不能保持空信息").toString();
		}
		return bo.saveTzje(vo);
	}

	/**
	 * <p>
	 * 撤销冲账操作
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/doCxcz", method = RequestMethod.POST)
	public @ResponseBody
	String doCxcz(String id) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "主键不能为空").toString();
		}
		return bo.doCxcz(id);
	}

	public IKhfkBO getBo() {
		return bo;
	}

	public void setBo(IKhfkBO bo) {
		this.bo = bo;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

}
