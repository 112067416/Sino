package com.quanta.sino.dbgl.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.dbgl.bo.api.IDbglBO;
import com.quanta.sino.dbgl.vo.DbglQE;
import com.quanta.sino.orm.DbglTp;

/**
 * <p>
 * 端板控制器
 * </p>
 * <p>
 * create: 2011-1-20 下午08:12:35
 * </p>
 * 
 * @author 张红国[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dbgl")
public class DbglController {

	@Autowired
	private IDbglBO bo;

	/**
	 * <p>
	 * 端板登录
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/toCreate", method = RequestMethod.GET)
	public String toCreate(Model model) {
		String jbno = bo.getJbno();
		// 添加到界面
		model.addAttribute("jbno", jbno);
		return "/sino/dbgl/create";
	}

	/**
	 * <p>
	 * 端板登录
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getjbno", method = RequestMethod.POST)
	public @ResponseBody
	String getjbno(Model model) {
		String jbno = bo.getJbno();
		// 添加到界面
		return new Result(jbno).toString();
	}

	/**
	 * <p>
	 * 保存新的端板
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(DbglTp entity) {
		bo.save(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 端板维护
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(DbglQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys(" crea desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/dbgl/list" : "/sino/dbgl/index";
	}

	/**
	 * <p>
	 * 查看端板
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/view!{id}", method = RequestMethod.POST)
	public String view(@PathVariable String id, Model model) {
		model.addAttribute("item", bo.get(id));
		return "/sino/dbgl/view";
	}

	/**
	 * <p>
	 * 删除端板
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/delete!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String delete(@PathVariable String id, Model model) {
		bo.delete(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量删除端板
	 * </p>
	 * 
	 * @param ids
	 *            合同No.和行号组合数组(no-line)
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		bo.delAll(ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 端板发货
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/doFh", method = RequestMethod.POST)
	public @ResponseBody
	String doFh(String[] ids) {
		bo.doFh(Arrays.asList(ids));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 撤消端板发货
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/doCxfh!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String doCxfh(@PathVariable String id) {
		bo.doCxfh(id);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 端板订正初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toDz", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String dz(String jbno, Model model) {
		if (jbno != null && !jbno.isEmpty()) {
			model.addAttribute("jbno", jbno);
		}
		return "/sino/dbgl/dz";
	}

	/**
	 * <p>
	 * 订正取数据
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dzCheck", method = RequestMethod.POST)
	public @ResponseBody
	String dzCheck(String jbno) {

		DbglTp ret = bo.get(jbno);
		return new Result(ret).toJsObject();
	}

	/**
	 * <p>
	 * 订正端板更新
	 * </p>
	 * 
	 * @param zp
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String update(DbglTp entity) {
		bo.update(entity);
		return Result.SUCCESS;
	}

	public IDbglBO getBo() {
		return bo;
	}

	public void setBo(IDbglBO bo) {
		this.bo = bo;
	}

}
