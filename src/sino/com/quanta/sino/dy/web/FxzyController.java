package com.quanta.sino.dy.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coco.core.bean.Result;
import com.coco.core.util.StringUtils;
import com.quanta.sino.fxs.bo.api.IFxzyrzBO;
import com.quanta.sino.fxs.bo.api.IMktfxBO;
import com.quanta.sino.fxs.vo.ChpdbQE;
import com.quanta.sino.orm.Chpdb;
import com.quanta.sino.orm.FxzyRz;
import com.quanta.sino.orm.FxzyRzYcsx;

/**
 * <p>
 * 分析作业日志打印控制器
 * </p>
 * <p>
 * create: 2011-4-11 下午04:19:05
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class FxzyController {

	/**
	 * 分析作业日志业务接口
	 */
	@Autowired
	private IFxzyrzBO bo;

	/**
	 * 
	 */
	@Autowired
	private IMktfxBO mktfxBO;

	/**
	 * <p>
	 * 分析作业日志打印入口
	 * </p>
	 * 
	 * @param ids
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/fxzyrz")
	public String fxzyrz(String[] ids, Model model) {
		model.addAttribute("ids", StringUtils.join(ids, ","));
		return "/sino/dy/fxzyrz";
	}

	/**
	 * <p>
	 * 分析作业日志打印
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/fxzyrz!print")
	public String printFxzyrz(String id, Model model) {
		FxzyRz entity = bo.get(id);
		model.addAttribute("entity", entity);
		String qt = entity.getQt();
		String $qt = null;
		if (qt != null && !qt.isEmpty()) {
			$qt = qt.replaceAll("\n", "<br />");
		}
		model.addAttribute("qt", $qt);
		String tbsx = entity.getTbsx();
		String $tbsx = null;
		if (tbsx != null && !tbsx.isEmpty()) {
			$tbsx = tbsx.replaceAll("\n", "<br />");
		}
		model.addAttribute("tbsx", $tbsx);
		model.addAttribute("ycsx", "有");
		if (!bo.isFxzyRzYcsx(id)) {
			model.addAttribute("ycsx", "无");
		}
		return "/sino/dy/fxzyrz!print";
	}

	/**
	 * <p>
	 * 分析作业日志异常事项打印
	 * </p>
	 * 
	 * @param pid
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/fxzyrzYcsx!print")
	public String printFxzyrzYcsx(String pid, Model model) {
		List<FxzyRzYcsx> entities = bo.find(pid);
		model.addAttribute("entities", entities);
		FxzyRz entity = bo.get(pid);
		String ycsx = null;
		if (entity != null) {
			ycsx = entity.getYcsx();
			if (ycsx != null && !ycsx.isEmpty()) {
				ycsx = ycsx.replaceAll("\n", "<br />");
			}
		}
		if (entities.size() == 0 && (ycsx == null || ycsx.isEmpty())) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"该分析作志日志没有异常事项"));
			return Result.URL_ERROR;
		}
		model.addAttribute("ycsx", ycsx);
		return "/sino/dy/fxzyrzYcsx!print";
	}

	/**
	 * <p>
	 * 出货判定表打印
	 * </p>
	 * 
	 * @param jbnos
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/chpdb!print")
	public String printChpdb(String jbnos, Model model) {
		Chpdb entity = null;
		List<Chpdb> entities = null;
		if (jbnos != null && !jbnos.isEmpty()) {
			ChpdbQE qentity = new ChpdbQE();
			qentity.setSize(-1);
			qentity.setJbnos(jbnos.split(","));
			mktfxBO.queryChpdb(qentity);
			entities = qentity.getDatas();
			entity = entities.size() > 0 ? entities.get(0) : null;
			model.addAttribute("datas", entities);
		}
		model.addAttribute("crea", entity !=null?entity.getCjsj():new Date());
		model.addAttribute("pageSize", 13);
		return "/sino/dy/chpdb!print";
	}

	public IFxzyrzBO getBo() {
		return bo;
	}

	public void setBo(IFxzyrzBO bo) {
		this.bo = bo;
	}

	public IMktfxBO getMktfxBO() {
		return mktfxBO;
	}

	public void setMktfxBO(IMktfxBO mktfxBO) {
		this.mktfxBO = mktfxBO;
	}
}
