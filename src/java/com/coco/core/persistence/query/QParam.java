package com.coco.core.persistence.query;

/**
 * <p>
 * 查询参数
 * </p>
 * <p>
 * create: 2010-12-21 上午09:56:44
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class QParam {

	/**
	 * 参数名称
	 */
	private String name;

	/**
	 * 参数显示名
	 */
	private String label;

	/**
	 * 参数类型
	 */
	private Class<?> type;

	/**
	 * 或者关联
	 */
	private QParam orlink;

	public QParam or(QParam orlink) {
		this.orlink = orlink;
		return orlink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public QParam getOrlink() {
		return orlink;
	}

	public void setOrlink(QParam orlink) {
		this.orlink = orlink;
	}

}
