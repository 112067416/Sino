package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.dy.bo.api.IPrintLogBO;

/**
 * <p>
 * 打印日志
 * </p>
 * <p>
 * create: 2011-4-20 下午03:55:02
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class PrintLogController {

	@Autowired
	private IPrintLogBO bo;

	/**
	 * <p>
	 * 保存打印日志
	 * </p>
	 * 
	 * @param nos
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/printLog")
	public @ResponseBody
	String printLog(String[] nos, String type) {
		User user = Context.currentUser();
		bo.save(nos, type, user != null ? user.getId() : null);
		return Result.SUCCESS;
	}

	public IPrintLogBO getBo() {
		return bo;
	}

	public void setBo(IPrintLogBO bo) {
		this.bo = bo;
	}

}
