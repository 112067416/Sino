package com.coco.query.exception;

import com.coco.query.bean.Message;

public class QueryException extends RuntimeException {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	private Message error;

	public QueryException() {
		super();
	}

	public QueryException(Message error) {
		this.error = error;
	}

	public QueryException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public QueryException(String message) {
		super(message);
	}

	public QueryException(Throwable throwable) {
		super(throwable);
	}

	public Message getError() {
		return error;
	}

}
