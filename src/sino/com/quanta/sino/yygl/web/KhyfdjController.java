package com.quanta.sino.yygl.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeYsfs;
import com.quanta.sino.cmn.constants.YfdjStat;
import com.quanta.sino.yygl.bo.api.IKhyfdjBO;
import com.quanta.sino.yygl.vo.KhyfdjQE;

/**
 * <p>
 * 客户运费单价管理控制器
 * </p>
 * <p>
 * create: 2011-2-14 下午02:25:13
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yygl/khyfdj")
public class KhyfdjController {

	/**
	 * 
	 */
	@Autowired
	private IKhyfdjBO khyfdjBO;

	/**
	 * 
	 */
	@Autowired
	private ICodeBO codeBO;

	/**
	 * <p>
	 * 客户运费单价查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(KhyfdjQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			List<String> codes = new ArrayList<String>();
			codes.add(CmnConstants.SINO_DQSG);
			codes.add(CmnConstants.SINO_DC);
			codes.add(CmnConstants.SINO_HGHY_CY);
			codes.add(CmnConstants.SINO_HGHY_MDG);
			codes.add(CmnConstants.SINO_SHHY);
			codes.add(CmnConstants.SINO_TLYS);
			codes.add(CmnConstants.SINO_CK_CY);
			codes.add(CmnConstants.SINO_CK_MDG);
			List<CodeItem> codeItems = codeBO.findItems(codes);
			StringBuilder builder = new StringBuilder();
			builder.append("'").append(CodeYsfs.ZT.name).append("':'").append(CodeYsfs.ZT.name).append("'");
			for (CodeItem item : codeItems) {
				if (builder.indexOf(item.getValue()) >= 0) continue;
				builder.append(",'").append(item.getValue()).append("':'").append(item.getValue()).append("'");
			}
			model.addAttribute("ysgss", builder.toString());
		}
		page.setStat(YfdjStat.YX.stat);
		page.setOrderBys("ysfm asc, name asc, shnm asc, addr asc, ldcn asc, djdw asc, crea desc");
		khyfdjBO.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/yygl/khyfdj/list"
				: "/sino/yygl/khyfdj/index";
	}

	/**
	 * <p>
	 * 加载客户运费单价
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String id) {
		return khyfdjBO.getForJs(id);
	}

	/**
	 * <p>
	 * 修改客户运费单价
	 * </p>
	 * 
	 * @param id
	 * @param yfdj
	 * @return String
	 */
	@RequestMapping(value = "/updateYfdj", method = RequestMethod.POST)
	public @ResponseBody
	String updateYfdj(String id, Double yfdj) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "运费单价主键为空").toString();
		}
		if (yfdj == null) {
			return new Result(-1, "运费单价为空").toString();
		}
		khyfdjBO.updateYfdj(id, yfdj);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量删除
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/dels", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		List<String> list = Arrays.asList(ids);
		for (String s : list) {
			System.out.println(s);
		}
		khyfdjBO.updateStat(list, YfdjStat.WX.stat);
		return Result.SUCCESS;
	}

	/**
	 * @return the khyfdjBO
	 */
	public IKhyfdjBO getKhyfdjBO() {
		return khyfdjBO;
	}

	/**
	 * @param khyfdjBO
	 *            the khyfdjBO to set
	 */
	public void setKhyfdjBO(IKhyfdjBO khyfdjBO) {
		this.khyfdjBO = khyfdjBO;
	}

	public ICodeBO getCodeBO() {
		return codeBO;
	}

	public void setCodeBO(ICodeBO codeBO) {
		this.codeBO = codeBO;
	}

}
