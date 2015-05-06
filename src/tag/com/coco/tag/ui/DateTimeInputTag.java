package com.coco.tag.ui;

public class DateTimeInputTag extends InputTag {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 显示日历
	 */
	protected boolean showCalendar = true;

	@Override
	protected Type type() {
		return Type.datetime;
	}

	@Override
	public void init() {
		super.imeMode = false;
		super.validate = true;
		super.maxlength = 19;
		super.format = "yyyy-MM-dd HH:mm:ss";
	}

	@Override
	protected void appendProp(StringBuilder content) {
		if (showCalendar) {
			content.append("calendar:true;");
		}
		super.appendProp(content);
	}

	public boolean isShowCalendar() {
		return showCalendar;
	}

	public void setShowCalendar(boolean showCalendar) {
		this.showCalendar = showCalendar;
	}

}
