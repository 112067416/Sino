package com.coco.core.mvc.cascade.sample;

import com.coco.core.mvc.cascade.api.ICascadeExecuter;

/**
 * <p>
 * 自定义执行器范例
 * </p>
 * <p>
 * create: 2010-12-23 下午03:11:56
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SampleCascadeExecuter implements ICascadeExecuter {

	@Override
	public String execute(String modal, String value) {
		return "{$:[\"abc\"]}";
	}

}
