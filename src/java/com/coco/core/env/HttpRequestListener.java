package com.coco.core.env;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * HTTP请求线程监听器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:34:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class HttpRequestListener implements ServletRequestListener {

	/**
	 * 请求线程销毁
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		Context.remove();
	}

	/**
	 * 请求线程初始化
	 */
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		Context.put((HttpServletRequest) event.getServletRequest());
	}

}
