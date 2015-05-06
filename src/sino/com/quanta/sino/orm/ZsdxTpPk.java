package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * <p>
 * 指示对象主键
 * </p>
 * <p>
 * create: 2011-2-12 下午03:52:24
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZsdxTpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 指示书No
	 */
	@Column(name = "FPNO_", length = 8)
	private String fpno;

	/**
	 * 卷板No/PileNo
	 */
	@Column(name = "JBNO_", length = 11)
	private String jbno;

	public ZsdxTpPk() {

	}

	public ZsdxTpPk(String fpno, String jbno) {
		this.fpno = fpno;
		this.jbno = jbno;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((jbno == null) ? 0 : jbno.hashCode());
		result = PRIME * result + ((fpno == null) ? 0 : fpno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final ZsdxTpPk other = (ZsdxTpPk) obj;
		if (jbno == null) {
			if (other.jbno != null) return false;
		}
		else if (!jbno.equals(other.jbno)) return false;
		if (fpno == null) {
			if (other.fpno != null) return false;
		}
		else if (!fpno.equals(other.fpno)) return false;
		return true;
	}

}