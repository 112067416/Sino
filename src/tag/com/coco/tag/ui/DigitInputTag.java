package com.coco.tag.ui;

/**
 * <p>
 * 数字输入控件
 * </p>
 * <p>
 * create: 2010-12-14 上午11:22:22
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class DigitInputTag extends InputTag {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Type type() {
		return Type.digit;
	}

	@Override
	protected void init() {
		super.imeMode = false;
		super.validate = true;
		super.init();
	}

}
