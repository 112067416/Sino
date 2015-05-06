/**
 * 
 */
package com.quanta.sino.dy.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.util.StringUtils;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.dy.vo.KwbqVO;

/**
 * <p>
 * 库位标签打印
 * </p>
 * <p>
 * create: 2011-1-7 下午04:44:43
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class KwbqController {

	/**
	 * 每张打印库位标签的个数
	 */
	private static final Integer size = 60;

	/**
	 * <p>
	 * 进入库位标签页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "kwbq")
	public String index(Model model) {
		model.addAttribute("size", size);
		return "/sino/dy/kwbq";
	}

	/**
	 * <p>
	 * 生成库位标签
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "outKwbq", method = RequestMethod.POST)
	public @ResponseBody
	String kwbq(Model model, @RequestParam("prefix") String prefix,
			@RequestParam("start") Integer start,
			@RequestParam("end") Integer end) {
		if (prefix == null || prefix.isEmpty()) {
			return new Result(-1, "库位标签前缀为空").toString();
		}
		if (prefix.length() != 2) {
			return new Result(-1, "库位标签前缀长度必须为2").toString();
		}
		if (DC.get(prefix.substring(0, 1)) == null) {
			return new Result(-1, "库位标签前缀第一位应为X、A、B、C、D、E、F或G").toString();
		}
		if (start == null || start <= 0) {
			return new Result(-1, "库位标签-开始号为空").toString();
		}
		if (end == null || end <= 0) {
			return new Result(-1, "库位标签-结束号为空").toString();
		}
		if (start > end) {
			return new Result(-1, "库位标签-结束号小于开始号").toString();
		}
		List<String> vos = new ArrayList<String>();
		for (int i = start; i <= end; i++) {
			if (i < 10) {
				vos.add(prefix + "0" + i);
			}
			else {
				vos.add(prefix + i);
			}
		}
		return StringUtils.join(vos, ",");
	}

	/**
	 * <p>
	 * 打印
	 * </p>
	 * 
	 * @param kws
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "kwbq!print")
	public String print(String kws, Model model) {
		if (kws == null || kws.isEmpty()) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "库位为空"));
			return Result.URL_BLANK;
		}
		List<KwbqVO> entities = new ArrayList<KwbqVO>();
		KwbqVO entity = null;
		int point = 1;
		int num = 1;
		String[] s = kws.split(",");
		for (String e : s) {
			if (num > size) {
				break;
			}
			if (e.trim().length() != 4) {
				model.addAttribute(Result.DEFAULT_KEY, new Result(-1, e
						+ "长度不为4"));
				return Result.URL_BLANK;
			}
			switch (point) {
			case 1:
				entity = new KwbqVO();
				entities.add(entity);

				entity.setBq1(e);
				break;
			case 2:
				entity.setBq2(e);
				break;
			case 3:
				entity.setBq3(e);
				break;
			case 4:
				entity.setBq4(e);
				break;
			case 5:
				entity.setBq5(e);
				point = 0;
				break;
			default:
				break;
			}
			point++;
			num++;
		}
		model.addAttribute("entities", entities);
		return "/sino/dy/kwbq!print";
	}

}
