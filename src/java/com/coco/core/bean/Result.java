package com.coco.core.bean;

import java.util.Set;

import com.coco.core.util.JsUtils;

/**
 * <p>
 * 返回结果对象，主要提供给ajax方法的结果对象
 * </p>
 * <p>
 * create: 2010-12-21 上午09:05:43
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class Result {

	/**
	 * 默认的返回结果属性键
	 */
	public static final String DEFAULT_KEY = "result";

	/**
	 * 公共错误显示的文件路径
	 */
	public static final String URL_ERROR = "/global/error";

	/**
	 * 公共异常显示的文件路径
	 */
	public static final String URL_EXCEPTION = "/global/exception";

	/**
	 * 公共空白显示的文件路径
	 */
	public static final String URL_BLANK = "/global/blank";

	/**
	 * 成功返回的结果串
	 */
	public static final String SUCCESS = "<!--1:OK-->";

	/**
	 * 错误返回的结果串
	 */
	public static final String ERROR = "<!---1:Error-->";

	/**
	 * 返回代码
	 */
	private int code;

	/**
	 * 返回标题
	 */
	private String title;

	/**
	 * 返回消息
	 */
	private String message;

	/**
	 * 返回对象
	 */
	private Object object;

	/**
	 * 返回的异常
	 */
	private Throwable throwable;

	public Result() {

	}

	/**
	 * 传入返回对象，对象存在则返回的代码为1，否则为-1
	 * 
	 * @param object
	 *            返回对象
	 */
	public Result(Object object) {
		if (object == null) {
			code = -1;
			message = "无法加载指定的信息";
		}
		else {
			code = 1;
			message = "OK";
		}
		this.object = object;
	}

	/**
	 * 结果对象
	 * 
	 * @param code
	 *            返回代码
	 * @param message
	 *            返回信息
	 */
	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public Result(Throwable throwable) {
		this.throwable = throwable;
	}

	/**
	 * <p>
	 * 默认返回的结果串，组成方式为&lt;!--code:message--&gt;
	 * </p>
	 * <p>
	 * 若含有对象这串后跟着写obj.toString()
	 * </p>
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("<!--");
		result.append(code);
		result.append(":");
		result.append(message);
		result.append("-->");
		if (object != null) {
			result.append(object);
		}
		return result.toString();
	}

	/**
	 * <p>
	 * 返回js对象串，组成方式为&lt;!--code:message--&gt;{field1:value1,field2:value2,...}。
	 * </p>
	 * 
	 * @return String
	 */
	public String toJsObject() {
		String content = "<!--" + code + ":" + message + "-->";
		return content + JsUtils.toSimpleObject(object);
	}

	/**
	 * <p>
	 * 返回js对象串，组成方式为&lt;!--code:message--&gt;{field1:value1,field2:value2,...}。
	 * <ul>
	 * <li>当传入的第一个参数为{@link java.util.Set} 类型时，表示指定的字段名称列表，否则全部解析对象。</li>
	 * <li>当传入的第二个参数为 {@link java.util.Set} 类型时，表示忽略的字段名称列表，否则全部解析对象。</li>
	 * </ul>
	 * </p>
	 * 
	 * @param includeFields
	 * @param ignoreFields
	 * @return String
	 */
	public String toJsObject(Set<String> includeFields, Set<String> ignoreFields) {
		String content = "<!--" + code + ":" + message + "-->";
		return content
				+ JsUtils.toSimpleObject(object, includeFields, ignoreFields);
	}

	/**
	 * <p>
	 * 返回js对象串，组成方式为&lt;!--code:message--&gt;{field1:value1,field2:value2,...}。
	 * <ul>
	 * <li>当传入的第一个参数为{@link java.util.Set} 类型时，表示指定的字段名称列表，否则全部解析对象。</li>
	 * <li>当传入的第二个参数为 {@link java.util.Set} 类型时，表示忽略的字段名称列表，否则全部解析对象。</li>
	 * <li>当传入的第三个参数为 {@link java.util.Set} 类型时，表示指定的表达式名称列表，否则全部解析对象。</li>
	 * </ul>
	 * </p>
	 * 
	 * @param includeFields
	 * @param ignoreFields
	 * @param exprs
	 * @return String
	 */
	public String toJsObject(Set<String> includeFields,
			Set<String> ignoreFields, Set<String> exprs) {
		String content = "<!--" + code + ":" + message + "-->";
		return content
				+ JsUtils.toSimpleObject(object, includeFields, ignoreFields, exprs);
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @param throwable
	 *            the throwable to set
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
