package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 未完合同主键
 * 
 * @author 许德建[xudejian@126.com]
 */
public class Zng2TpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PID_", referencedColumnName = "ID_", nullable = false)
	private Zng1Tp zng1Tp;
	/**
	 * 制品No
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	public Zng2TpPk() {

	}

	public Zng2TpPk(Zng1Tp zng1Tp, String jbno) {
		this.zng1Tp = zng1Tp;
		this.jbno = jbno;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((zng1Tp == null) ? 0 : zng1Tp.hashCode());
		result = PRIME * result + ((jbno == null) ? 0 : jbno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final Zng2TpPk other = (Zng2TpPk) obj;
		if (zng1Tp == null) {
			if (other.zng1Tp != null) return false;
		}
		else if (!zng1Tp.equals(other.zng1Tp)) return false;
		if (jbno == null) {
			if (other.jbno != null) return false;
		}
		else if (!jbno.equals(other.jbno)) return false;
		return true;
	}

}