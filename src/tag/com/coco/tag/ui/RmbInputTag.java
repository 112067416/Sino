package com.coco.tag.ui;

public class RmbInputTag extends InputTag {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Type type() {
		return Type.rmb;
	}

	@Override
	protected void init() {
		super.imeMode = false;
		super.validate = true;
		super.init();
	}

}
