package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 代码主键
 * 
 * @author 许德建[xudejian@126.com]
 */
public class CodeItemPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 代码模块
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CODE_", referencedColumnName = "ID_")
	private Code code;

	/**
	 * 代码键
	 */
	@Column(name = "KEY_", length = 128)
	private String key;

	public CodeItemPk() {

	}

	public CodeItemPk(Code code, String key) {
		this.code = code;
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((key == null) ? 0 : key.hashCode());
		result = PRIME * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final CodeItemPk other = (CodeItemPk) obj;
		if (key == null) {
			if (other.key != null) return false;
		}
		else if (!key.equals(other.key)) return false;
		if (code == null) {
			if (other.code != null) return false;
		}
		else if (!code.equals(other.code)) return false;
		return true;
	}

}