package com.coco.query.bean;

public class Message {

	/**
	 * 0-info, 1-warm, 2-error
	 */
	private Type type;

	private String message;

	private Message next;

	public static Message build() {
		return new Message(null, null);
	}

	private Message(Type type, String message) {
		this.type = type;
		this.message = message;
	}

	public Message info(String message) {
		return next = new Message(Type.INFO, message);
	}

	public Message warm(String message) {
		return next = new Message(Type.WARM, message);
	}

	public Message error(String message) {
		return next = new Message(Type.ERROR, message);
	}

	public Message next() {
		return next;
	}

	public String getMessage() {
		return message;
	}

	public Type getType() {
		return type;
	}

	private enum Type {
		INFO, WARM, ERROR
	}

}
