package com.coco.tag.ui;

public class IntegerInputTag extends InputTag {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Type type() {
		return Type.integer;
	}

	protected int scale = -1;

	/**
	 * 仅正数
	 */
	protected boolean positive;

	@Override
	protected void init() {
		super.imeMode = false;
		super.validate = true;
		if (maxlength <= 0 && scale > 0) {
			maxlength = scale + 1;
			if (!positive) {
				maxlength += 1;
			}
		}
	}

	@Override
	protected void appendProp(StringBuilder content) {
		if (scale > 0) {
			content.append("scale:").append(scale).append(";");
		}
		if (positive) {
			content.append("positive:1;");
		}
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return the positive
	 */
	public boolean isPositive() {
		return positive;
	}

	/**
	 * @param positive
	 *            the positive to set
	 */
	public void setPositive(boolean positive) {
		this.positive = positive;
	}
}
