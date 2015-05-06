package com.quanta.sino.kcgl.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.kcgl.bo.api.IKcglBO;
import com.quanta.sino.ycai.vo.DrVO;

/**
 * <p>
 * 库存管理
 * </p>
 * <p>
 * create: 2011-2-18 上午10:14:32
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/kcgl")
public class KcglController {

	/**
	 * 库存管理业务接口
	 */
	@Autowired
	private IKcglBO bo;

	/**
	 * <p>
	 * 进入制品入库页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexZprk")
	public String indexZprk(Model model) {
		return "/sino/kcgl/indexZprk";
	}

	/**
	 * <p>
	 * 制品入库操作
	 * </p>
	 * 
	 * @param content
	 * @return String
	 */
	@RequestMapping(value = "/zprk", method = RequestMethod.POST)
	public @ResponseBody
	String zprk(DrVO entity) {
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		return bo.importZprk(entity);
	}

	/**
	 * <p>
	 * 进入制品盘点页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexZppd")
	public String indexZppd(Model model) {
		// StringBuilder builder = new StringBuilder();
		// builder.append("'").append(EPdlx.SC.key).append("':'").append(EPdlx.SC.value).append("','").append(EPdlx.ZP.key).append("':'").append(EPdlx.ZP.value).append("'");
		model.addAttribute("dcs", DC.dcs());
		return "/sino/kcgl/indexZppd";
	}

	/**
	 * <p>
	 * 制品制品操作
	 * </p>
	 * 
	 * @param content
	 * @return String
	 */
	@RequestMapping(value = "/zppd", method = RequestMethod.POST)
	public @ResponseBody
	String zppd(DrVO entity, String[] duics) {
		if (duics == null || duics.length == 0) {
			return new Result(-1, "未选择堆场").toString();
		}
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		List<String> $duics = Arrays.asList(duics);
		if ($duics.contains(DC.X.name) || $duics.contains(DC.A.name)
				|| $duics.contains(DC.B.name)) {
			// 素材
			return bo.scpd(entity, duics);
		}
		// 制品盘点
		return bo.zppd(entity, duics);
	}

	/**
	 * @return the bo
	 */
	public IKcglBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IKcglBO bo) {
		this.bo = bo;
	}

}
