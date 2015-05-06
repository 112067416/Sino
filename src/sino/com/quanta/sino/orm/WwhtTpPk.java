package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 未完合同主键
 * 
 * @author 许德建[xudejian@126.com]
 */
public class WwhtTpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 合同No
	 */
	@Column(name = "HTNO_", length = 8)
	private String htno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 3)
	private String line;

	public WwhtTpPk() {

	}

	public WwhtTpPk(String htno, String line) {
		this.htno = htno;
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((line == null) ? 0 : line.hashCode());
		result = PRIME * result + ((htno == null) ? 0 : htno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final WwhtTpPk other = (WwhtTpPk) obj;
		if (line == null) {
			if (other.line != null) return false;
		}
		else if (!line.equals(other.line)) return false;
		if (htno == null) {
			if (other.htno != null) return false;
		}
		else if (!htno.equals(other.htno)) return false;
		return true;
	}

}