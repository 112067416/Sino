package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 订货主键
 * 
 * @author 许德建[xudejian@126.com]
 */
public class DhuoTpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订货No
	 */
	@Column(name = "DHNO_", length = 7)
	private String dhno;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	public DhuoTpPk() {

	}

	public DhuoTpPk(String dhno, String line) {
		this.dhno = dhno;
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((line == null) ? 0 : line.hashCode());
		result = PRIME * result + ((dhno == null) ? 0 : dhno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final DhuoTpPk other = (DhuoTpPk) obj;
		if (line == null) {
			if (other.line != null) return false;
		}
		else if (!line.equals(other.line)) return false;
		if (dhno == null) {
			if (other.dhno != null) return false;
		}
		else if (!dhno.equals(other.dhno)) return false;
		return true;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

}