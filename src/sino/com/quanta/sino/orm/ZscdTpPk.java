package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 指示书出端卷板主键
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
public class ZscdTpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 指示书No
	 */
	@Column(name = "ZSNO_", length = 6)
	private String zsno;

	/**
	 * 出端卷板No/PileNo
	 */
	@Column(name = "CDJB_", length = 11)
	private String cdjb;

	public ZscdTpPk() {

	}

	public ZscdTpPk(String zsno, String cdjb) {
		this.zsno = zsno;
		this.cdjb = cdjb;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((cdjb == null) ? 0 : cdjb.hashCode());
		result = PRIME * result + ((zsno == null) ? 0 : zsno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ZscdTpPk other = (ZscdTpPk) obj;
		if (cdjb == null) {
			if (other.cdjb != null)
				return false;
		} else if (!cdjb.equals(other.cdjb))
			return false;
		if (zsno == null) {
			if (other.zsno != null)
				return false;
		} else if (!zsno.equals(other.zsno))
			return false;
		return true;
	}

}