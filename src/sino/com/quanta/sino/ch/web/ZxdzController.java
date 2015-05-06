package com.quanta.sino.ch.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.ch.vo.CpdrVO;
import com.quanta.sino.ch.vo.ZxdzVO;

/**
 * <p>
 * 装箱对照控制器
 * </p>
 * <p>
 * create: 2010-12-29 上午08:38:34
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/ch/zxdz")
public class ZxdzController {

	/**
	 * 装箱对照业务接口
	 */
	@Autowired
	private IZxdzBO zbo;

	/**
	 * 装箱指示业务接口
	 */
	@Autowired
	private IZxzsBO zxzsBO;

	/**
	 * <p>
	 * 车牌入库
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/indexCprk")
	public String indexCprk() {
		return "/sino/ch/zxdz/indexCprk";
	}

	/**
	 * <p>
	 * 保存车牌号
	 * </p>
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/cprk", method = RequestMethod.POST)
	public @ResponseBody
	String cprk(CpdrVO entity) {
		if (entity.getAttachId() == null || entity.getAttachId().isEmpty()) {
			return new Result(-1, "未上传附件").toString();
		}
		return zbo.importCpno(entity);
	}

	/**
	 * <p>
	 * 进入装箱对照页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexZxdz")
	public String indexZxdz() {
		return "/sino/ch/zxdz/indexZxdz";
	}

	/**
	 * <p>
	 * 装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/zxdz", method = RequestMethod.POST)
	public @ResponseBody
	String zxdz(ZxdzVO vo) {
		if (vo.getItems() == null || vo.getItems().length == 0) {
			throw new CocoException(-1, "获取数据失败");
		}
		zbo.zxdz(vo);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入次级品装箱对照页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexCjpZxdz")
	public String indexCjpZxdz() {
		return "/sino/ch/zxdz/indexCjpZxdz";
	}

	/**
	 * <p>
	 * 次级品装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/cjpZxdz", method = RequestMethod.POST)
	public @ResponseBody
	String cjpZxdz(ZxdzVO vo, String[] zpno) {
		if (zpno == null || zpno.length == 0) {
			return new Result(-1, "未扫描实物制品号").toString();
		}
		zbo.cjpZxdz(vo, Arrays.asList(zpno));
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 判断装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @return String
	 */
	@RequestMapping(value = "/getCheck", method = RequestMethod.POST)
	public @ResponseBody
	String getCheck(ZxdzVO vo) {
		if (vo.getItems() == null || vo.getItems().length == 0) {
			return new Result(-1, "请输入实物制品标签").toString();
		}
		return zbo.getCheck(vo);
	}

	/**
	 * <p>
	 * 判断次级品装箱对照
	 * </p>
	 * 
	 * @param vo
	 * @param zpno
	 * @return String
	 */
	@RequestMapping(value = "/getCjpCheck", method = RequestMethod.POST)
	public @ResponseBody
	String getCjpCheck(ZxdzVO vo, String[] zpno) {
		if (zpno == null || zpno.length == 0) {
			return new Result(-1, "未扫描实物制品号").toString();
		}
		return zbo.getCjpCheck(vo, Arrays.asList(zpno));
	}

	/**
	 * <p>
	 * 进入设置制品车牌号
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping("/indexCp")
	public String indexCp() {
		return "/sino/ch/zxdz/indexCp";
	}

	/**
	 * <p>
	 * 设置制品车牌号
	 * </p>
	 * 
	 * @param cpno
	 * @param jbnos
	 * @return String
	 */
	@RequestMapping(value = "/setCpno", method = RequestMethod.POST)
	public @ResponseBody
	String setCpno(String cpno, String[] jbnos) {
		zbo.setCpno(cpno, jbnos);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 查询装箱指示信息
	 * </p>
	 * 
	 * @param zsno
	 * @return String
	 */
	@RequestMapping(value = "/getZxzs", method = RequestMethod.POST)
	public @ResponseBody
	String getZxzs(String zxno) {
		if (zxno == null || zxno.isEmpty()) {
			return new Result(-1, "装箱指示书号为空").toString();
		}
		return zxzsBO.getForJs(zxno);
	}

	public IZxdzBO getZbo() {
		return zbo;
	}

	public void setZbo(IZxdzBO zbo) {
		this.zbo = zbo;
	}

	public IZxzsBO getZxzsBO() {
		return zxzsBO;
	}

	public void setZxzsBO(IZxzsBO zxzsBO) {
		this.zxzsBO = zxzsBO;
	}

}
