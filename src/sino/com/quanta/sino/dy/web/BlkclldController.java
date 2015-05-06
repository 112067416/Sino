package com.quanta.sino.dy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quanta.sino.dy.bo.api.IZssBO;

/**
 * <p>
 * 不良扣除缺陷单打印
 * </p>
 * <p>
 * create: 2011-1-18 上午08:46:05
 * </p>
 * 
 * @author 方元龙
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/dy")
public class BlkclldController {

	@Autowired
	private IZssBO bo;

	/**
	 * <p>
	 * 不良扣除联络单打印初始
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/blkclld")
	public String index() {
		return "/sino/dy/blkclld";
	}

	/**
	 * @return the bo
	 */
	public IZssBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IZssBO bo) {
		this.bo = bo;
	}
}
