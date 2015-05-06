package com.quanta.sino.cmn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.quanta.sino.cmn.bo.api.IZzsyBO;
import com.quanta.sino.cmn.constants.CodeTyzl;
import com.quanta.sino.cmn.vo.ZzsyConstants;
import com.quanta.sino.cmn.vo.ZzsyQE;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 仕样主文件模块控制器
 * </p>
 * <p>
 * create: 2011-2-12 上午10:08:32
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/cmn/zzsy")
public class ZzsyController {

	@Autowired
	private IZzsyBO bo;

	private static final String MODIFY = "modify";

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(ZzsyQE page, Model model) {
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			ZzsyMp entity = new ZzsyMp();
			entity.setHxzi(ZzsyConstants.HXZI);
			entity.setHszi(ZzsyConstants.HSZI);
			entity.setKxzi(ZzsyConstants.KXZI);
			entity.setKszi(ZzsyConstants.KSZI);
			entity.setCxzi(ZzsyConstants.CXZI);
			entity.setCszi(ZzsyConstants.CSZI);
			entity.setYtyp(CodeTyzl.DOS.key);
			model.addAttribute("entity", entity);
		}
		page.setOrderBys("syno desc");
		bo.query(page);
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/cmn/zzsy/data" : "/sino/cmn/zzsy/index";
	}

	/**
	 * <p>
	 * 加载主文件
	 * </p>
	 * 
	 * @param syno
	 * @param model
	 * @return String
	 */
	@RequestMapping("/get")
	public String get(String syno, Model model) {
		model.addAttribute("entity", bo.get(syno));
		return "/sino/cmn/zzsy/detail";
	}

	/**
	 * 加载主文件
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public @ResponseBody
	String load(String syno, Model model) {
		if (syno == null || syno.isEmpty()) {
			return new Result(-1, "制造仕样No不能为空").toString();
		}
		return bo.getForJs(syno);
	}

	/**
	 * 保存主文件
	 * 
	 * @param entity
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	String save(ZzsyMp entity, String $type) {
		if (entity.getSyno() == null || entity.getSyno().isEmpty()) {
			return new Result(-1, "制造仕样No不能为空").toString();
		}
		if ("modify".equals($type)) {
			return bo.update(entity);
		}
		return bo.save(entity);
	}

	/**
	 * 检验数据
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody
	String validate(ZzsyMp entity, String $type) {
		ZzsyMp $zzsyMp = bo.getByKey(entity.getGgno(), entity.getFudw(), entity.getFuzm(), entity.getFufm(), entity.getPzno(), entity.getChdx(), entity.getNwai(), entity.getHmax(), entity.getHmin(), entity.getKmax(), entity.getKmin(), entity.getCmax(), entity.getCmin(), entity.getHouu(), entity.getKuan(), entity.getCang(), entity.getCond(), entity.getUser());
		if ($zzsyMp != null && !MODIFY.equals($type)) {
			return new Result(2, $zzsyMp.getSyno()).toString();
		}
		return bo.validate(entity);
	}

	/**
	 * 删除主文件
	 * 
	 * @param mast
	 * @param mkey
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody
	String delete(String syno) {
		if (syno == null || syno.isEmpty()) {
			return new Result(-1, "制造仕样No不能为空").toString();
		}
		bo.delete(syno);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得最新的制作仕样No.
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/getNewNo", method = RequestMethod.POST)
	public @ResponseBody
	String getNewNo() {
		return new Result(bo.getNewNo()).toString();
	}

	/**
	 * <p>
	 * 根据码表中的规格代码对应的硬度上下限、附着量正面上下限和附着量反面上下限，更新对应制作仕样的信息
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/doRefresh", method = RequestMethod.POST)
	public @ResponseBody
	String doRefresh() {
		bo.doRefresh();
		return Result.SUCCESS;
	}

	public IZzsyBO getBo() {
		return bo;
	}

	public void setBo(IZzsyBO bo) {
		this.bo = bo;
	}

}