package com.quanta.sino.etl.web;

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
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.etl.bo.api.IRcBO;
import com.quanta.sino.etl.vo.RcSaveVO;
import com.quanta.sino.etl.vo.RcVO;
import com.quanta.sino.etl.vo.RcmxVO;
import com.quanta.sino.orm.Scxbpz;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

/**
 * <p>
 * ETL入侧控件器
 * </p>
 * <p>
 * create: 2011-1-18 上午08:53:15
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/etl/rc")
public class RcController {

	@Autowired
	private IScxbBO scxbBo;

	@Autowired
	private IRcBO rcBo;

	@Autowired
	private IYcaitpBO yaiBo;

	/**
	 * <p>
	 * 入侧初始<br />
	 * 取原材卷板集合——正在生产和待生产的卷。
	 * 取正在生产的和待生产的卷对象数据，并显示在界面，如果没有备用卷，则弹出另外的界面提示（需要调用外部接口）。
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		User user = Context.currentUser();
		// 用户登录检查
		if (user == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "已超时,请重新登陆系统"));
			return Result.URL_ERROR;
		}
		RcVO rc = new RcVO();
		// 生产线用户检查
		Scxbpz scx = scxbBo.getByDept(user.getDeptId());
		// 入侧 用户检查
		if (scx == null || scx.getCode() == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1,
					"当前用户不能操作此项功能"));
			return Result.URL_ERROR;
		}
		// 界面实体查询
		rc.setSlin(scx.getCode());
		rc.setZu(user.getGroup());
		rc.setBan(user.getShift());
		List<RcmxVO> rcmxs = rcBo.findByStates();
		rc.setRcmxs(rcmxs);
		model.addAttribute("page", rc);

		model.addAttribute("ban", user.getShift());
		model.addAttribute("zu", user.getGroup());
		List<String> xms = ECyfxxm.xms();
		model.addAttribute("xms", xms);
		model.addAttribute("plt", ECyfxxm.plt.name);
		int size = xms.size();
		int line = (size % 4 == 0 ? size / 4 : size / 4 + 1);
		model.addAttribute("line", line);
		model.addAttribute("bz", "ETL镀锡线发送");

		return "sino/etl/rc/index";
	}

	/**
	 * <p>
	 * 通过第三方接口更新本地备用卷信息
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/updateYCBY", method = RequestMethod.POST)
	public @ResponseBody
	String updateYCBY(String jbno) {
		// 通过第三方接口更新本地备用卷信息,成功返回1，失败返回-1
		Integer rel = rcBo.updateYCBY();
		return new Result(rel, "").toString();
	}

	/**
	 * <p>
	 * 通过第三方接口查询备用卷信息
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/getbygif", method = RequestMethod.POST)
	public @ResponseBody
	String getbygif(String jbno) {
		// 通过第三方接口更新本地备用卷信息,1为不能加备用卷，2为请加备用卷，-1为不显示
		Integer rel = rcBo.getbygif();
		return new Result(rel, "").toString();
	}

	/**
	 * <p>
	 * 添加备用卷
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(RcSaveVO entity, String $type) {
		if (entity.getJbno() == null || entity.getJbno().isEmpty()) {
			return new Result(-1, "COIL.NO不能为空").toString();
		}
		rcBo.save(entity);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 备用卷确认--向外PC6000预写备用卷的信息
	 * </p>
	 * 
	 * @param ywllNo
	 * @return String
	 */
	@RequestMapping(value = "/byjqr", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	String updateSfqr(String jbno) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "COIL.NO不能为空").toString();
		}
		YcaiTp ycai = yaiBo.get(jbno);
		if (ycai == null) {
			return new Result(-1, "COIL No.不存在").toString();
		}
		// 卷的状态已经为终了或中止了
		if (YbStat.SJLR.stat.equals(ycai.getStat())
				|| YbStat.SJZL.stat.equals(ycai.getStat())
				|| YbStat.SJZZ.stat.equals(ycai.getStat())) {
			return new Result(-1, "该卷COIL No.已做"
					+ YbStat.getName(ycai.getStat()) + "不能将其设置为当前卷").toString();
		}
		rcBo.updateBySfqr(jbno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 强制转移
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/updateMove", method = RequestMethod.POST)
	public @ResponseBody
	String updateMove(String dqjbno) {
		if (dqjbno == null || dqjbno.isEmpty()) {
			return new Result(-1, "当前卷板号不能为空").toString();
		}
		rcBo.updateMove(dqjbno);

		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 撤消
	 * </p>
	 * 
	 * @param entity
	 * @param $type
	 * @return String
	 */
	@RequestMapping(value = "/deleteBY", method = RequestMethod.POST)
	public @ResponseBody
	String deleteBY(String jbno) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "备用卷板NO不能为空").toString();
		}
		rcBo.deleteBY(jbno);

		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 弹出备用卷加入警告
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/popMsg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String popMsg() {
		return "/sino/etl/rc/rcmsg";
	}

	public IScxbBO getScxbBo() {
		return scxbBo;
	}

	public void setScxbBo(IScxbBO scxbBo) {
		this.scxbBo = scxbBo;
	}

	public IRcBO getRcBo() {
		return rcBo;
	}

	public void setRcBo(IRcBO rcBo) {
		this.rcBo = rcBo;
	}

	public IYcaitpBO getYaiBo() {
		return yaiBo;
	}

	public void setYaiBo(IYcaitpBO yaiBo) {
		this.yaiBo = yaiBo;
	}

}
