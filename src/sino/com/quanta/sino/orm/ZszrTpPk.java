package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 指示书装入板卷主键
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
public class ZszrTpPk implements Serializable {

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
	 * 装入卷板No/PileNo
	 */
	@Column(name = "ZRJB_", length = 10)
	private String zrjb;

	public ZszrTpPk() {

	}

	public ZszrTpPk(String zsno, String zrjb) {
		this.zsno = zsno;
		this.zrjb = zrjb;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((zrjb == null) ? 0 : zrjb.hashCode());
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
		final ZszrTpPk other = (ZszrTpPk) obj;
		if (zrjb == null) {
			if (other.zrjb != null)
				return false;
		} else if (!zrjb.equals(other.zrjb))
			return false;
		if (zsno == null) {
			if (other.zsno != null)
				return false;
		} else if (!zsno.equals(other.zsno))
			return false;
		return true;
	}

}