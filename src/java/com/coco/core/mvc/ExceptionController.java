package com.coco.core.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 异常控制器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:46:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
public class ExceptionController {

	@ExceptionHandler
	public @ResponseBody
	String handle(IllegalStateException e) {
		return "IllegalStateException handled!";
	}

	@RequestMapping("/exception")
	public @ResponseBody
	String exception() {
		throw new IllegalStateException("Sorry!");
	}

}