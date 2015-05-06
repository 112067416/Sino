package com.quanta.sino.yygl.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.EYN;
import com.quanta.sino.cmn.constants.JbddItemStat;
import com.quanta.sino.orm.Jbdd;
import com.quanta.sino.orm.JbddItem;
import com.quanta.sino.yygl.bo.api.IJbddBO;
import com.quanta.sino.yygl.vo.JbddItemQE;
import com.quanta.sino.yygl.vo.JbddQE;
import com.quanta.sino.yygl.vo.JbddVO;

/**
 * <p>
 * 基板订单明细控制器
 * </p>
 * <p>
 * create: 2010-12-13 上午11:46:55
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yygl/jbdd")
public class JbddController {

	/**
	 * 基板订购单业务接口
	 */
	@Autowired
	private IJbddBO bo;

	/**
	 * <p>
	 * 对基板订单上锁
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	public @ResponseBody
	String lock(String ids) {
		return bo.updateLock(ids.split(","), EYN.Y.key);
	}

	/**
	 * <p>
	 * 对基板订单QE解锁
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/unLock", method = RequestMethod.POST)
	public @ResponseBody
	String unLock(String ids) {
		return bo.updateLock(ids.split(","), EYN.N.key);
	}

	/**
	 * <p>
	 * 修改基板订单状态
	 * </p>
	 * 
	 * @param ids
	 * @param stat
	 * @return String
	 */
	@RequestMapping(value = "/updateJbddStat", method = RequestMethod.POST)
	public @ResponseBody
	String updateJbddStat(String ids, String stat) {
		bo.updateJdbbStat(ids.split(","), stat);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 基板订单入口
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toJbdd", method = RequestMethod.GET)
	public String toJbdd(Model model) {
		return "/sino/yygl/jbdd/jbddList";
	}

	/**
	 * <p>
	 * 导出基板订单Excel流
	 * </p>
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/outJbddExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void outJbddExcel(JbddVO vo, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=tmp.xls");
		try {
			bo.fetchJbdd(vo, response.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 返回基板订单登录页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/toJbdd")
	public String toJbdd() {
		return "/sino/yygl/jbdd/toJbdd";
	}

	/**
	 * <p>
	 * 我的基板订单明细
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(JbddItemQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		User user = Context.currentUser();
		if (user == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "登录超时，请重新登录"));
			return Result.URL_ERROR;
		}
		if (!page.isQuery()) {
			page.setStat(JbddItemStat.CS.stat);
		}
		// 只获取当前人的订购单
		page.setDdno(user.getNo());
		page.setIds(null);
		page.setOrderBys("stat asc,crea desc,id asc");
		bo.queryItem(page);
		model.addAttribute("page", page);
		model.addAttribute("tjVO", bo.getTj(page.getPid(), page.getStat(), page.getDdno(), page.getAbbr()));
		model.addAttribute("fixed", JbddItemStat.YZZ.stat);
		return page.isQuery() ? "/sino/yygl/jbdd/list"
				: "/sino/yygl/jbdd/index";
	}

	/**
	 * <p>
	 * 根据基板订单明细生成基板订单列表信息
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/queryJbdd")
	public String queryJbdd(JbddItemQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		String pid = page.getPid();
		if (pid == null || pid.isEmpty()) {
			page.setStat(JbddItemStat.CS.stat);
		}
		page.setIds(null);
		page.setOrderBys("item asc,zjzt asc,zjrq asc,nwai asc,ajb asc,code desc,cond desc,abbr asc,houa asc,id asc");
		// page.setOrderBys("abbr asc, houa asc, id asc");
		bo.queryItem(page);
		model.addAttribute("page", page);
		model.addAttribute("tjVO", bo.getTj(page.getPid(), page.getStat(), null, null));
		return page.isQuery() ? "/sino/yygl/jbdd/listZzJbdd"
				: "/sino/yygl/jbdd/indexZzJbdd";
	}

	/**
	 * <p>
	 * 基板订单查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexJbdd")
	public String indexJbdd(JbddQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setOrderBys("jbno desc, id asc");
		bo.queryJbdd(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/yygl/jbdd/listJbdd"
				: "/sino/yygl/jbdd/indexJbdd";
	}

	/**
	 * <p>
	 * 保存基板订单明细
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @param flag
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(JbddItem entity, String $type, String flag) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if ("modify".equals($type)) {
			return bo.updateItem(entity, flag);
		}
		else {
			entity.setDdno(user.getNo());
			entity.setDdnm(user.getName());
			bo.saveItem(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * 加载基板订单明细
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		return bo.getForJs(id);
	}

	/**
	 * <p>
	 * 加载默认基板订单明细
	 * </p>
	 * 
	 * @param abbr
	 * @param face
	 * @param yuny
	 * @param houa
	 * @param houb
	 * @param width
	 * @return String
	 */
	@RequestMapping(value = "/loadDefault", method = RequestMethod.POST)
	public @ResponseBody
	String loadDefault(String abbr, String face, String yuny, Double houa,
			Double houb, Double width) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		return bo.getForJs(abbr, face, yuny, houa, houb, width, user.getNo());
	}

	/**
	 * 加载基板订单
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/loadJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String loadJbdd(String id, Model model) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		Jbdd entity = bo.getJbdd(id);
		if (entity == null) {
			return new Result(-1, "基板订购单不存在").toString();
		}
		return "{id:\"" + entity.getId() + "\",jbno:\"" + entity.getJbno()
				+ "\"}";
	}

	/**
	 * <p>
	 * 删除基板订购单
	 * </p>
	 * 
	 * @param ids
	 *            主键
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String ids) {
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		return bo.deleteItem(ids.split(","));
	}

	/**
	 * <p>
	 * 删除基板订购单
	 * </p>
	 * 
	 * @param ids
	 *            主键
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String dels(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		return bo.deleteItem(ids.split(","));
	}

	/**
	 * <p>
	 * 复制生成新的基板订购单
	 * </p>
	 * 
	 * @param ids
	 *            主键
	 * @return String
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public @ResponseBody
	String copy(String ids) {
		if (ids == null || ids.length() == 0) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		bo.copyItem(ids.split(","));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 生成基板订购单
	 * </p>
	 * 
	 * @param ids
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/cjJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String cjJbdd(String ids, String jbno) {
		// 获取当前登录用户信息
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "登录超时，请重新登录").toString();
		}
		if (ids == null || ids.isEmpty()) {
			return new Result(-1, "基板订购单主键不能为空").toString();
		}
		return bo.build(ids.split(","), user, jbno);
	}

	/**
	 * <p>
	 * 追加基板订购单
	 * </p>
	 * 
	 * @param ids
	 * @param pid
	 * @return String
	 */
	@RequestMapping(value = "/zjJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String zjJbdd(String ids, String pid) {
		bo.zjJbdd(ids.split(","), pid);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 补加基板订购单
	 * </p>
	 * 
	 * @param ids
	 * @param pid
	 * @return String
	 */
	@RequestMapping(value = "/bjJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String bjJbdd(String ids, String pid) {
		bo.bjJbdd(ids.split(","), pid);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 移出该基板订单
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/ycJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String ycJbdd(String ids) {
		bo.ycJbdd(ids.split(","));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除生成基板订购单
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/deleteJbdd", method = RequestMethod.POST)
	public @ResponseBody
	String deleteJbdd(String pid) {
		bo.deleteJbdd(pid);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 修改基板订购单号
	 * </p>
	 * 
	 * @param id
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/updateJbno", method = RequestMethod.POST)
	public @ResponseBody
	String updateJbno(String id, String jbno) {
		return bo.updateJbno(id, jbno);
	}

	public IJbddBO getBo() {
		return bo;
	}

	public void setBo(IJbddBO bo) {
		this.bo = bo;
	}

}
