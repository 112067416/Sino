package com.coco.core.exception;

/**
 * <p>
 * 公共异常类
 * </p>
 * <p>
 * create: 2010-12-21 上午09:36:28
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CocoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 异常代码
	 */
	private int code;

	/**
	 * 异常消息列表
	 */
	private String[] messages;

	public CocoException(int code, String... messages) {
		this.code = code;
		this.messages = messages;
	}

	public CocoException(int code, Throwable throwable) {
		super(throwable);
		this.code = code;
	}

	public CocoException(int code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return getMessage();
	}

	@Override
	public String getMessage() {
		String message = ExceptionMessageLoader.get(String.valueOf(code), messages);
		return message != null ? message : (messages != null
				&& messages.length == 1 ? messages[0] : null);
	}
}
