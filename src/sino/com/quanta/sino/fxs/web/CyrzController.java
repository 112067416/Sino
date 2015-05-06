package com.quanta.sino.fxs.web;

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
import com.coco.core.exception.CocoException;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.MktfxStat;
import com.quanta.sino.fxs.bo.api.ICyrzBO;
import com.quanta.sino.fxs.bo.api.ICyrzBO.UpdateType;
import com.quanta.sino.fxs.vo.CyrzQE;
import com.quanta.sino.orm.Cyrz;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 采样日志web接口
 * </p>
 * <p>
 * create: 2011-1-7 下午02:49:27
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/fxs/cyrz")
public class CyrzController {

	/**
	 * 采样业务接口
	 */
	@Autowired
	private ICyrzBO bo;

	/**
	 * 生产线别配置接口
	 */
	@Autowired
	private IScxbBO scxbBo;

	/**
	 * 指示书业务接口
	 */
	@Autowired
	private IZsBO zsBO;

	/**
	 * 原材管理业务接口
	 */
	@Autowired
	private IYcaitpBO ycaitpBO;

	/**
	 * <p>
	 * 根据指示书，自动带出目标镀锡量和涂油量
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "loadFxmb", method = RequestMethod.POST)
	public @ResponseBody
	String loadFxmb(String jbno) {
		YcaiTp ycaiTp = ycaitpBO.get(jbno);
		if (ycaiTp == null) {
			return new Result(-1, "原材卷板No." + jbno + "不存在").toString();
		}
		String zsno = ycaiTp.getZsno();
		if (zsno == null || zsno.isEmpty()) {
			return new Result(-1, "原材卷板No." + jbno + "还未制作ETL指示书").toString();
		}
		ZsdhTp zsdhTp = zsBO.getZsdhTp(zsno);
		if (zsdhTp == null) {
			return new Result(-1, "指示书No." + zsno + "不存在").toString();
		}
		return "{dxl:\"" + zsdhTp.getFugm() + "\",tyl:\"" + zsdhTp.getYqty()
				+ "\",zsno:\"" + zsno + "\"}";
	}

	/**
	 * <p>
	 * 采样日志维护查询页面
	 * </p>
	 * 
	 * @param page
	 * @return String
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(CyrzQE page, String type, Model model) {
		User user = Context.currentUser();
		if (user == null) {
			return Result.URL_ERROR;
		}
		if (page.getSize() <= 0) {
			page.setSize(10);
		}
		page.setDeleted(false);
		if (type == null || type.isEmpty()) {
			type = "etl";
		}
		if ("etl".equals(type)) {
			page.setFsxb(ECyfxxm.ETL);
			page.setOrderBys("id_ desc");
		}
		else if ("sl".equals(type)) {
			page.setFsxbs(new String[] { ECyfxxm.ETL, ECyfxxm.SL });
			page.setOrderBys("id_ desc");
			page.setJsxbs(new String[] { ECyfxxm.SL, ECyfxxm.SL_FXS });
		}
		else if ("fxs".equals(type)) {
			if (!page.isQuery()) {
				page.setStat(MktfxStat.CS.stat);
				model.addAttribute("stat", MktfxStat.CS.stat);
			}
			page.setJsxbs(new String[] { ECyfxxm.FXS, ECyfxxm.SL_FXS });
			page.setOrderBys("fxsj desc, id_ asc, fxsd asc, fxsy asc");
		}
		if ("etl".equals(type) || "sl".equals(type)) {
			model.addAttribute("ban", user.getShift());
			model.addAttribute("zu", user.getGroup());
			List<String> xms = "etl".equals(type) ? ECyfxxm.xms()
					: ECyfxxm.xms(type);
			if ("sl".equals(type)) {
				xms.add(ECyfxxm.plt.name);
			}
			model.addAttribute("xms", xms);
			int size = xms.size();
			int line = (size % 4 == 0 ? size / 4 : size / 4 + 1);
			model.addAttribute("line", line);
			model.addAttribute("bz", "etl".equals(type) ? "ETL镀锡线发送"
					: "SL剪切线发送");
		}
		bo.query(page);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("fixed", MktfxStat.CS.stat);
		return page.isQuery() ? "/sino/fxs/cyrz/list"
				: ("/sino/fxs/cyrz/index!" + type);
	}

	/**
	 * <p>
	 * 增加采样分析单
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(Cyrz entity) {
		if (entity.getId() != null && !entity.getId().isEmpty()) {
			bo.update(entity, null);
		}
		else {
			entity.setId(null);
			bo.cydEtl(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 增加SL采样分析单
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveSl", method = RequestMethod.POST)
	public @ResponseBody
	String saveSl(Cyrz entity) {
		bo.cydSl(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查看采样分析单
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody
	String get(String id) {
		return bo.getForJs(id);
	}

	/**
	 * <p>
	 * 查看采样分析单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/toFsyp", method = RequestMethod.POST)
	public String toFsyp(String id, Model model) {
		Cyrz entity = bo.get(id);
		String[] xms = null;
		if (entity != null) {
			xms = entity.getFxxms();
			model.addAttribute("xms", xms);
			int size = xms.length;
			int line = size / 6;
			if (size % 6 == 0) {
				line++;
			}
			model.addAttribute("line", line);
		}
		model.addAttribute("entity", entity);
		return "/sino/fxs/cyrz/fsyp";
	}

	/**
	 * <p>
	 * 查看采样分析单
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String view(String id, Model model) {
		model.addAttribute("item", bo.get(id));
		return "/sino/fxs/cyrz/view";
	}

	/**
	 * <p>
	 * 删除采样分析单
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String id, String fsxb) {
		if (id == null || id.isEmpty()) {
			return new Result(-1, "请指定要删除的取样单").toString();
		}
		if (fsxb == null || fsxb.isEmpty()) {
			return new Result(-1, "存在参数为空").toString();
		}
		bo.delete(id, fsxb);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param entity
	 * @param type
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(Cyrz entity, String type) {
		UpdateType $type = null;
		// String line = null;
		if ("slsd".equals(type)) {
			$type = UpdateType.slsd;
			// User user = Context.currentUser();
			// if (user == null) {
			// throw new CocoException(-40001, "登录超时");
			// }
			// // 获取登录人员的线别
			// Scxbpz xbpz = scxbBo.getByDept(user.getDeptId());
			// if (xbpz == null) {
			// throw new CocoException(-1, "您所在的线别不是SL线别");
			// }
			// line = xbpz.getCode();
		}
		else if ("slsy".equals(type)) {
			$type = UpdateType.slsy;
		}
		else if ("fxsd".equals(type)) {
			$type = UpdateType.fxsd;
		}
		else if ("fxsy".equals(type)) {
			$type = UpdateType.fxsy;
		}
		else if ("fx".equals(type)) {
			$type = UpdateType.fx;
		}
		if ($type == null) {
			throw new CocoException(-1, "不支持的操作:" + type);
		}
		// entity.setSlxb(line);
		bo.update(entity, $type);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 点击"我知道"，修改消息状态
	 * </p>
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public @ResponseBody
	String updateState(String id, String type) {
		UpdateType $type = null;
		// String line = null;
		if ("slsd".equals(type)) {
			$type = UpdateType.slsd;
			// User user = Context.currentUser();
			// if (user == null) {
			// throw new CocoException(-40001, "登录超时");
			// }
			// // 获取登录人员的线别
			// Scxbpz xbpz = scxbBo.getByDept(user.getDeptId());
			// if (xbpz == null) {
			// throw new CocoException(-1, "您所在的线别不是SL线别");
			// }
			// line = xbpz.getCode();
		}
		else if ("fxsd".equals(type)) {
			$type = UpdateType.fxsd;
		}
		else if ("fxsy".equals(type)) {
			$type = UpdateType.fxsy;
		}
		if ($type == null) {
			throw new CocoException(-1, "不支持的操作:" + type);
		}
		// 获取登录人员的线别
		bo.updateState(id, $type);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入消息提醒页面
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public String msg(Model model) {
		return "/sino/fxs/cyrz/msg";
	}

	/**
	 * <p>
	 * SL线接收消息
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getForSl", method = RequestMethod.POST)
	public String getForSl(Model model) {
		// User user = Context.currentUser();
		// if (user == null) {
		// model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "登录超时"));
		// return Result.URL_BLANK;
		// }
		// // 获取登录人员的线别
		// Scxbpz xbpz = scxbBo.getByDept(user.getDeptId());
		// if (xbpz == null) {
		// model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "没有配置线别"));
		// return Result.URL_BLANK;
		// }
		// Cyrz item = bo.getForSl(xbpz.getCode());
		Cyrz item = bo.getForSl();
		if (item == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "没有数据"));
			return Result.URL_BLANK;
		}
		model.addAttribute("item", item);
		model.addAttribute("type", "slsd");
		model.addAttribute("btnValue", "接收");
		return "/sino/fxs/cyrz/viewMsg";
	}

	/**
	 * <p>
	 * 分析室接收消息
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getForFxs", method = RequestMethod.POST)
	public String getForFxs(Model model) {
		Cyrz item = bo.getForFxs();
		if (item == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "没有数据"));
			return Result.URL_BLANK;
		}
		model.addAttribute("item", item);
		model.addAttribute("type", !item.isFxsdZt() ? "fxsd" : "fxsy");
		model.addAttribute("btnValue", !item.isFxsdZt() ? "接收采样单" : "接收送样");
		return "/sino/fxs/cyrz/viewMsg";
	}

	public ICyrzBO getBo() {
		return bo;
	}

	public void setBo(ICyrzBO bo) {
		this.bo = bo;
	}

	public IScxbBO getScxbBo() {
		return scxbBo;
	}

	public void setScxbBo(IScxbBO scxbBo) {
		this.scxbBo = scxbBo;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}
}
