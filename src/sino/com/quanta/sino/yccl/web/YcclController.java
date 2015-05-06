package com.quanta.sino.yccl.web;

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
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ZpnoCd;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.yccl.bo.api.IXpxxBO;
import com.quanta.sino.yccl.vo.DcsjVO;
import com.quanta.sino.yccl.vo.YldlVO;
import com.quanta.sino.yccl.vo.ZpVO;
import com.quanta.sino.yccl.vo.ZpkDataVO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 异常处理
 * </p>
 * <p>
 * create: 2011-3-25 下午01:15:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/yccl")
public class YcclController {

	/**
	 * 异常处理业务接口
	 */
	@Autowired
	private IXpxxBO bo;

	/**
	 * 中日达生产公共业务接口
	 */
	@Autowired
	private ICmnBO cmnBo;

	/**
	 * 序号业务接口
	 */
	@Autowired
	private ISeqBO seqBo;

	/**
	 * 制品业务实现
	 */
	@Autowired
	private IZpBO zpBO;

	/**
	 * 原板业务接口
	 */
	@Autowired
	private IYcaitpBO ycaitpBO;

	/**
	 * 指示书业务接口
	 */
	@Autowired
	private IZsBO zsBO;

	/**
	 * <p>
	 * 现品情报修正主界面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexXpqbxz")
	public String indexXpqbxz() {
		return "/sino/yccl/indexXpqbxz";
	}

	/**
	 * <p>
	 * 现品做成主界面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexXpqbzc")
	public String indexXpqbzc() {
		return "/sino/yccl/indexXpqbzc";
	}

	/**
	 * <p>
	 * 业连登录
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexYldl")
	public String indexYldl(Model model) {
		StringBuilder xpzk = new StringBuilder();
		xpzk.append("'").append(EXpzk.SC.key).append("':'").append(EXpzk.SC.value).append("','").append(EXpzk.ZJP.key).append("':'").append(EXpzk.ZJP.value).append("'");
		model.addAttribute("xpzk", xpzk.toString());
		return "/sino/yccl/indexYldl";
	}

	/**
	 * <p>
	 * 现品删除主界面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexXpqbsc")
	public String indexXpqbsc() {
		return "/sino/yccl/indexXpqbsc";
	}

	/**
	 * <p>
	 * 堆场实绩维护主界面
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexDcsj")
	public String indexDcsj() {
		return "/sino/yccl/indexDcsj";
	}

	/**
	 * <p>
	 * 制品（原材卷板与制品在库DB） 联合查询
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("indexZyzt")
	public String indexZyzt() {
		return "/sino/yccl/indexZytz";
	}

	/**
	 * <p>
	 * 进入现品保留页面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("indexXpbl")
	public String indexXpbl() {
		return "/sino/yccl/indexXpbl";
	}

	/**
	 * <p>
	 * 获取原材卷板信息或者制品在库信息详情
	 * </p>
	 * 
	 * @param jbno
	 * @param flag
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/loadZyzt", method = RequestMethod.POST)
	public @ResponseBody
	String loadZyzt(ZpkDataVO vo, Model model) {
		return bo.getZyztForJS(vo);
	}

	/**
	 * <p>
	 * 获取者制品在库信息详情
	 * </p>
	 * 
	 * @param jbno
	 * @param flag
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/loadZybl", method = RequestMethod.POST)
	public @ResponseBody
	String loadZybl(ZpkDataVO vo, Model model) {
		return bo.getZyblForJS(vo);
	}

	/**
	 * <p>
	 * 修正现品
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/updateXp", method = RequestMethod.POST)
	public @ResponseBody
	String updateXp(ZpngTp entity) {
		// 用户登录检查
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		String xpzk = entity.getXpzk();
		if (EXpzk.SC.key.equals(xpzk)) {
			YcaiTp ycai = new YcaiTp();
			ReflectUtils.copy(ycai, entity, true);
			bo.updateYcaiTp(ycai, user);
		}
		else {
			bo.updateZpngTp(entity, user);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 现品情报作成
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveXp", method = RequestMethod.POST)
	public @ResponseBody
	String saveXp(ZpngTp entity, String newJbno) {
		// 用户登录检查
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		ZpnoCd zpnoCd = null;
		if ((zpnoCd = ZpnoCd.get(newJbno.length())) == null) {
			return new Result(-1, "引用COIL / PILE No.输入有误").toString();
		}
		// 判断新作成制品号是否正确
		if (zpnoCd.len == ZpnoCd.eight.len) {
			int no = Integer.parseInt(newJbno.substring(6));
			if (no == 10) {
				return new Result(-1, "系统不能作成制品No.为" + newJbno
						+ "制品,请重新引用COIL / PILE No.").toString();
			}
		}
		else if (zpnoCd.len == ZpnoCd.eleven.len) {
			int no = Integer.parseInt(newJbno.substring(8));
			if (no == 100) {
				return new Result(-1, "系统不能作成制品No.为" + newJbno
						+ "制品,请重新引用COIL / PILE No.").toString();
			}
		}
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			YcaiTp ycaiTp = ycaitpBO.get(entity.getJbno());
			if (ycaiTp == null) {
				return new Result(-1, "引用COIL / PILE No." + entity.getJbno()
						+ "不存在").toString();
			}
			bo.saveYcaiTp(ycaiTp, entity, user, newJbno);
		}
		else {
			bo.saveZpngTp(entity, user, newJbno);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除现品
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/delXp", method = RequestMethod.POST)
	public @ResponseBody
	String delXp(String jbno) {
		// 用户登录检查
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		ZpnoCd zpnoCd = null;
		if ((zpnoCd = ZpnoCd.get(jbno.length())) == null) {
			return new Result(-1, "引用COIL / PILE No.输入有误").toString();
		}
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			bo.deleteYcaiTp(jbno, user);
		}
		else {
			bo.deleteZpngTp(jbno, user);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 判断现品情报删除
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/checkDelXp", method = RequestMethod.POST)
	public @ResponseBody
	String checkDelXp(String jbno) {
		return bo.getCheckDelXp(jbno);
	}

	/**
	 * <p>
	 * 作业停止设定
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param ztbj
	 *            作业停止标记
	 * @param czr
	 *            操作人
	 * @param zyly
	 *            作业理由
	 * @param cz
	 *            处置
	 * @return String
	 */
	@RequestMapping(value = "/updateZytz", method = RequestMethod.POST)
	public @ResponseBody
	String updateZytz(String[] ids, String ztbj, String czr, String zyly,
			String cz) {
		// 获取系统当前用户
		User user = Context.currentUser();
		// 用户登录检查
		if (user == null) {
			throw new CocoException(-1, "已超时,请重新登陆系统");
		}
		bo.updateZytz(ids, ztbj, czr, zyly, cz, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 修改作业停止处置理由
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param zyly
	 *            作业理由
	 * @param cz
	 *            处置
	 * @return String
	 */
	@RequestMapping(value = "/updateCz", method = RequestMethod.POST)
	public @ResponseBody
	String updateCz(String[] ids, String zyly, String cz) {
		// 获取系统当前用户
		User user = Context.currentUser();
		// 用户登录检查
		if (user == null) {
			throw new CocoException(-1, "已超时,请重新登陆系统");
		}
		bo.updateCz(ids, zyly, cz, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 制品保留设定
	 * </p>
	 * 
	 * @param ids
	 *            需要设定的制品号
	 * @param blbj
	 *            现品保留标记
	 * @param czr
	 *            操作人
	 * @param blly
	 *            现品保留理由
	 * @param cz
	 *            处置
	 * @return String
	 */
	@RequestMapping(value = "/updateXpbl", method = RequestMethod.POST)
	public @ResponseBody
	String updateXpbl(String[] ids, String blbj, String czr, String blly,
			String cz) {
		// 获取系统当前用户
		User user = Context.currentUser();
		// 用户登录检查
		if (user == null) {
			throw new CocoException(-1, "已超时,请重新登陆系统");
		}
		bo.updateXpbl(ids, blbj, czr, blly, cz, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 检查制品是否已设置作业停止标记
	 * </p>
	 * 
	 * @param ids
	 * @param ztbj
	 * @return String
	 */
	@RequestMapping(value = "/checkZytz", method = RequestMethod.POST)
	public @ResponseBody
	String checkZytz(String[] ids, String ztbj) {
		if (ztbj == null || ztbj.isEmpty()) {
			return Result.SUCCESS;
		}
		return bo.getCheckZytz(ids);
	}

	/**
	 * <p>
	 * 检查制品是否已设置保留标记
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/checkXpbl", method = RequestMethod.POST)
	public @ResponseBody
	String checkXpbl(String[] ids) {
		return bo.getCheckXpbl(ids);
	}

	/**
	 * <p>
	 * 业连登录
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/updateYlno", method = RequestMethod.POST)
	public @ResponseBody
	String updateYlno(YldlVO vo) {
		// 获取系统当前用户
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		bo.updateYlno(vo, user);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量设置业连No
	 * </p>
	 * 
	 * @param xpzk
	 * @param ylno
	 * @param jbnoStart
	 * @param jbnoEnd
	 * @return String
	 */
	@RequestMapping(value = "/plszYlno", method = RequestMethod.POST)
	public @ResponseBody
	String plszYlno(String xpzk, String ylFlag, String ylno, String jbnoStart,
			String jbnoEnd) {
		String $ylno = ylFlag + "-" + ylno;
		bo.updateYlno(xpzk, jbnoStart, jbnoEnd, $ylno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获取原材卷板信息或者制品在库信息详情
	 * </p>
	 * 
	 * @param jbno
	 * @param flag
	 *            true:表示的引用CoilNo来作成新的制品
	 * @return String
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String jbno, boolean flag) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "COIL/PILE No.不能为空").toString();
		}
		ZpnoCd zpnoCd = null;
		if ((zpnoCd = ZpnoCd.get(jbno.length())) == null) {
			return new Result(-1, "COIL/PILE No.输入有误").toString();
		}
		ZpngTp zp = null;
		YcaiTp ycai = null;
		if (ZpnoCd.YCAI.equals(zpnoCd.type)) {
			ycai = ycaitpBO.get(jbno);
		}
		else {
			zp = zpBO.getZp(jbno);
		}
		if (zp == null && ycai == null) {
			return new Result(-1, "COIL/PILE No." + jbno + "不存在").toString();
		}
		return bo.getZpForJS(zp, ycai, flag);
	}

	/**
	 * <p>
	 * 检验现品信息详情
	 * </p>
	 * 
	 * @param zpVO
	 * @param flag
	 *            true:表示的引用CoilNo来作成新的制品
	 * @return String
	 */
	@RequestMapping(value = "/checkXpxx", method = RequestMethod.POST)
	public @ResponseBody
	String checkXpxx(ZpVO zpVO, boolean flag) {
		return bo.getCheckXpxx(zpVO, flag);
	}

	/**
	 * <p>
	 * 查看制品信息
	 * </p>
	 * 
	 * @param id
	 * @param flag
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/loadZp", method = RequestMethod.POST)
	public @ResponseBody
	String loadZp(String jbno, boolean flag, Model model) {
		if (jbno == null || jbno.isEmpty()) {
			return new Result(-1, "COIL/PILE No.不能为空").toString();
		}
		if (ZpnoCd.get(jbno.length()) == null) {
			return new Result(-1, "COIL/PILE No.输入有误").toString();
		}
		ZpngTp zpngTp = null;
		YcaiTp ycaiTp = null;
		EScbj eScbj = null;
		if (ZpnoCd.YCAI.equals(ZpnoCd.get(jbno.length()).type)) {
			ycaiTp = ycaitpBO.get(jbno);
			if (ycaiTp == null) {
				return new Result(-1, "COIL/PILE No." + jbno + "不存在").toString();
			}
			eScbj = EScbj.get(ycaiTp.getScbj());
		}
		else {
			zpngTp = zpBO.getZp(jbno);
			if (zpngTp == null) {
				return new Result(-1, "COIL/PILE No." + jbno + "不存在").toString();
			}
			eScbj = EScbj.get(zpngTp.getScbj());
		}
		if (eScbj != null && !EScbj.CS.key.equals(eScbj.key)) {
			return new Result(-1, "COIL/PILE No.状态为" + eScbj.value
					+ ",不能对其做该操作").toString();
		}
		if (zpngTp == null && ycaiTp == null) {
			return new Result(-1, "COIL/PILE No." + jbno + "不存在").toString();
		}
		// if (flag && zsBO.getUniqueZszrTp(jbno) != null) {
		// return new Result(-1, "COIL/PILE No." + jbno
		// + "已制作生产指示书,不允许对其设置业连NO").toString();
		// }
		DcsjVO vo = parseDcsj(zpngTp, ycaiTp);
		if (vo == null) {
			return new Result(-1, "PILE No.不存在").toString();
		}
		return new Result(vo).toJsObject();
	}

	/**
	 * <p>
	 * 组装界面查看的堆场实绩VO
	 * </p>
	 * 
	 * @param zpngTp
	 * @param ycaiTp
	 * @return DcsjVO
	 */
	private DcsjVO parseDcsj(ZpngTp zpngTp, YcaiTp ycaiTp) {
		if (zpngTp == null && ycaiTp == null) {
			return null;
		}
		DcsjVO vo = new DcsjVO();
		if (ycaiTp != null) {
			ReflectUtils.copy(vo, ycaiTp, true);
			vo.setCang(0d);
			vo.setHouu(ycaiTp.getXpho());
			vo.setKuan(ycaiTp.getXpkn());
			return vo;
		}
		ReflectUtils.copy(vo, zpngTp, true);
		vo.setKuan(zpngTp.getXpkn());
		vo.setCang(zpngTp.getXpcn());
		return vo;
	}

	@RequestMapping(value = "/updateDc", method = RequestMethod.POST)
	public @ResponseBody
	String updateDc(String[] jbno, String[] kw) {
		User user = Context.currentUser();
		if (user == null) {
			return new Result(-1, "已超时,请重新登陆系统").toString();
		}
		if (jbno == null || jbno.length == 0 || kw == null || kw.length == 0) {
			return new Result(-1, "未录入要做堆场实绩维护的现品信息").toString();
		}
		// 调BO层批量保存
		bo.updateDc(jbno, kw, user);
		return Result.SUCCESS;
	}

	@RequestMapping(value = "/checkDcgz", method = RequestMethod.POST)
	public @ResponseBody
	String checkDcgz(String[] jbno, String[] kw) {
		if (jbno == null || jbno.length == 0 || kw == null || kw.length == 0) {
			return new Result(-1, "未录入要做堆场实绩维护的现品信息").toString();
		}
		return bo.getCheckDcgz(jbno, kw);
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

	public ISeqBO getSeqBo() {
		return seqBo;
	}

	public void setSeqBo(ISeqBO seqBo) {
		this.seqBo = seqBo;
	}

	public ICmnBO getCmnBo() {
		return cmnBo;
	}

	public void setCmnBo(ICmnBO cmnBo) {
		this.cmnBo = cmnBo;
	}

	public IXpxxBO getBo() {
		return bo;
	}

	public void setBo(IXpxxBO bo) {
		this.bo = bo;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

}
