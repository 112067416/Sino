package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * <p>
 * 采样日志接收线别
 * </p>
 * <p>
 * create: 2011-1-7 上午11:24:03
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CyrzSlxbPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 单号
	 */
	@Column(name = "ID_", length = 10)
	private String id;

	/**
	 * 线别
	 */
	@Column(name = "LINE_", length = 10)
	private String line;

	public CyrzSlxbPk() {

	}

	public CyrzSlxbPk(String id, String line) {
		this.id = id;
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((line == null) ? 0 : line.hashCode());
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final CyrzSlxbPk other = (CyrzSlxbPk) obj;
		if (line == null) {
			if (other.line != null) return false;
		}
		else if (!line.equals(other.line)) return false;
		if (id == null) {
			if (other.id != null) return false;
		}
		else if (!id.equals(other.id)) return false;
		return true;
	}

}