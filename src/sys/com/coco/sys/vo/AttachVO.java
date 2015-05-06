package com.coco.sys.vo;

/**
 * 文件上传信息对象
 * 
 * @author 许德建[xudejian@126.com]
 */
public class AttachVO {

	/**
	 * 附件类型
	 */
	private String type;

	/**
	 * 附件引用ID
	 */
	private String rel;

	/**
	 * 附件重命名
	 */
	private String name;

	/**
	 * 附件仅存一个
	 */
	private boolean single;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel
	 *            the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the single
	 */
	public boolean isSingle() {
		return single;
	}

	/**
	 * @param single
	 *            the single to set
	 */
	public void setSingle(boolean single) {
		this.single = single;
	}

}
