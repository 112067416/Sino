package com.quanta.sino.cg.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.cg.constants.CgConstants;
import com.quanta.sino.cg.vo.BaseVO;
import com.quanta.sino.cg.vo.CgdjQE;
import com.quanta.sino.cg.vo.DrVO;
import com.quanta.sino.cg.vo.WwhtListQE;
import com.quanta.sino.cg.vo.WwhtSaveVO;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.WwhtTpPk;
import com.quanta.sino.orm.YbCgdj;

/**
 * <p>
 * 采购控制器
 * </p>
 * <p>
 * create: 2010-12-22 上午09:58:17
 * </p>
 * 
 * @author 张红国[gisgali@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cg")
public class CgController {

	/**
	 * 供应商合同业务接口
	 */
	@Autowired
	private IWwhtBO bo;

	/**
	 * <p>
	 * 加载原板采购单价基础费用
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getBase", method = RequestMethod.POST)
	public @ResponseBody
	String getBase() {
		return bo.getBase();
	}

	/**
	 * <p>
	 * 计算原板采购单价
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/calculateCgdj", method = RequestMethod.POST)
	public @ResponseBody
	String calculateCgdj(BaseVO vo) {
		bo.calculateCgdj(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 加载原板采购单价
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/loadCgdj", method = RequestMethod.POST)
	public @ResponseBody
	String loadCgdj(String id) {
		return bo.loadCgdj(id);
	}

	/**
	 * <p>
	 * 删除采购单价
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ids
	 * @return String
	 */
	@RequestMapping(value = "/deleteCgdj", method = RequestMethod.POST)
	public @ResponseBody
	String deleteCgdj(String[] ids) {
		bo.deleteCgdj(ids);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 保存原板采购单价
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveCgdj", method = RequestMethod.POST)
	public @ResponseBody
	String saveCgdj(YbCgdj entity) {
		return bo.saveCgdj(entity);
	}

	/**
	 * <p>
	 * 采购单价维护
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexCgdj")
	public String indexCgdj(CgdjQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		page.setOrderBys("yuny asc, knfw asc, xpho desc");
		bo.queryCgdj(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/cg/listCgdj" : "/sino/cg/indexCgdj";
	}

	/**
	 * <p>
	 * 进入采购导入页面
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexCgdr")
	public String indexCgdr(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dt = sdf.format(new Date());
		// 默认合同日为当前日期
		model.addAttribute("htdt", dt);
		// 默认签约日为当前日期
		model.addAttribute("qyri", dt);
		// 默认通货区分为人民币
		model.addAttribute("thqf", Code013.rmb.key);
		return "/sino/cg/indexCgdr";
	}

	/**
	 * <p>
	 * 供应商合同导入操作
	 * </p>
	 * <p>
	 * author 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/cgdr", method = RequestMethod.POST)
	public @ResponseBody
	String cgdr(DrVO entity) {
		if (entity.getQyri() == null) {
			return new Result(-1, "签约日不能为空").toString();
		}
		if (entity.getHtdt() == null) {
			return new Result(-1, "合同日不能为空").toString();
		}
		if (entity.getSsno() == null || entity.getSsno().isEmpty()) {
			return new Result(-1, "商社不能为空").toString();
		}
		if (entity.getThqf() == null || entity.getThqf().isEmpty()) {
			return new Result(-1, "币种不能为空").toString();
		}
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		bo.importHt(entity);
		return Result.SUCCESS;
	}

	/**
	 * 进入采购登录页面
	 * 
	 * @return String
	 */
	@RequestMapping("/indexCgdl")
	public String cgdl() {
		return "/sino/cg/indexCgdl";
	}

	/**
	 * <p>
	 * 采购维护
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexCgwh")
	public String indexCgwh(WwhtListQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(sdf.format(calendar.getTime()));
				page.setQyri_begin(date);
			}
			catch (ParseException e) {
			}
		}
		if (page.getQyri_end() == null) {
			page.setQyri_end(page.getQyri_begin());
		}
		if (page.getHtdt_end() == null) {
			page.setHtdt_end(page.getHtdt_begin());
		}
		page.setOrderBys(" crea asc,htno asc,line asc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/cg/list" : "/sino/cg/indexCgwh";
	}

	/**
	 * <p>
	 * 保存采购登录
	 * </p>
	 * 
	 * @param vo
	 * @param wsvo
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(WwhtSaveVO wsvo) {
		if (wsvo.getItems() == null || wsvo.getItems().length == 0) {
			throw new CocoException(-1, "获取列表数据为空");
		}
		// 转换成保存的采购实体
		List<WwhtTp> wwhtps = new ArrayList<WwhtTp>();
		Date date = new Date();
		for (WwhtTp wtp : wsvo.getItems()) {
			if (wtp.getHtno() == null || wtp.getHtno().isEmpty()
					|| wtp.getLine() == null || wtp.getLine().isEmpty()) {
				continue;
			}
			wtp.setQyri(wsvo.getQyri());
			wtp.setHtdt(wsvo.getHtdt());
			wtp.setThqf(wsvo.getThqf());
			// 签约完了标志赋值
			wtp.setQywl(CgConstants.CODE_QYWL_WWL);
			// 制造商代码赋值
			wtp.setZzsd(wsvo.getZzsd());
			// 制造商名称赋值
			wtp.setZzsm(wsvo.getZzsm());
			// 商社代码赋值
			wtp.setSsno(wsvo.getSsno());
			// 商社名称赋值
			wtp.setSsnm(wsvo.getSsnm());
			// 创建时间和修改时间赋值
			wtp.setCrea(date);
			wtp.setUpda(date);
			// 添加实体到实体列表
			wwhtps.add(wtp);
		}
		if (wwhtps.size() == 0) {
			return new Result(-1, "没有可保存的数据行").toString();
		}
		// 调BO层批量保存
		bo.saveAll(wwhtps);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 批量删除采购合同
	 * </p>
	 * 
	 * @param ids
	 *            合同No.和行号组合数组(no-line)
	 * @return String
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String deletes(String[] ids) {
		List<String> idsList = new ArrayList<String>();
		idsList = Arrays.asList(ids);
		bo.delete(idsList);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 删除单个合同
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
		String[] arr;
		arr = id.split("-");
		if (arr.length < 2) {
			return new Result(-1, "合同号和行号不正确").toString();
		}

		List<String> idsList = new ArrayList<String>();
		idsList.add(id);
		bo.delete(idsList);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查看采购合同
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/view!{id}", method = RequestMethod.POST)
	public String view(@PathVariable String id, Model model) {
		String[] arr;
		arr = id.split("-");
		if (arr.length < 2) {
			return new Result(-1, "合同号和行号不正确").toString();
		}
		model.addAttribute("item", bo.get(new WwhtTpPk(arr[0], arr[1])));
		return "/sino/cg/view";
	}

	/**
	 * <p>
	 * 加载采购合同
	 * </p>
	 * 
	 * @param id
	 *            合同No.和行号组合(no-line)
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/get!{id}", method = RequestMethod.POST)
	public @ResponseBody
	String get(@PathVariable String id, Model model) {
		String[] arr;
		arr = id.split("-");
		if (arr.length < 2) {
			return new Result(-1, "合同号和行号不正确").toString();
		}
		return bo.getForJs(new WwhtTpPk(arr[0], arr[1]));
	}

	/**
	 * <p>
	 * 修改合同
	 * </p>
	 * 
	 * @param vo
	 *            采购合同保存下拉框实体
	 * @param $htno
	 * @param $line
	 * @return String
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(WwhtSaveVO vo, String $htno, String $line) {
		bo.update(vo, $htno, $line);
		return Result.SUCCESS;
	}

	public IWwhtBO getBo() {
		return bo;
	}

	public void setBo(IWwhtBO bo) {
		this.bo = bo;
	}

}
