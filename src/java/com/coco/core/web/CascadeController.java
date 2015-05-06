package com.coco.core.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.mvc.cascade.CascadeExecuterFactory;
import com.coco.core.mvc.cascade.api.ICascadeExecuter;

/**
 * <p>
 * 级联控制器
 * </p>
 * <p>
 * create: 2010-12-22 下午05:07:52
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/coco")
public class CascadeController {

	@Autowired
	private CascadeExecuterFactory factory;

	@RequestMapping(value = "/cascade", method = RequestMethod.GET)
	public @ResponseBody
	String execute(String key, String module, String value,
			HttpServletResponse response) {
		ICascadeExecuter executer = factory.get(key);
		if (executer == null) {
			// 没有配置级联执行器
			response.setStatus(550);
			return "";
		}
		try {
			return executer.execute(module, value);
		}
		catch (Exception e) {
			// 执行出错
			response.setStatus(500);
			return e.getMessage();
		}
	}

	/**
	 * @return the factory
	 */
	public CascadeExecuterFactory getFactory() {
		return factory;
	}

	/**
	 * @param factory
	 *            the factory to set
	 */
	public void setFactory(CascadeExecuterFactory factory) {
		this.factory = factory;
	}

}
