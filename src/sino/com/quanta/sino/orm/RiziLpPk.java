package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 日志主键
 * 
 * @author 许德建[xudejian@126.com]
 * 
 */
public class RiziLpPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日志种类
	 */
	@Column(name = "RZZL_", length = 1)
	private String rzzl;

	/**
	 * 装入卷板No
	 */
	@Column(name = "ZRJB_", length = 11)
	private String zrjb;

	/**
	 * 出端卷板No/PileNo
	 */
	@Column(name = "CDJB_", length = 11)
	private String cdjb;

	/**
	 * 行号
	 */
	@Column(name = "LINE_", length = 2)
	private String line;

	public RiziLpPk() {

	}

	public RiziLpPk(String rzzl, String zrjb, String cdjb, String line) {
		this.rzzl = rzzl;
		this.zrjb = zrjb;
		this.cdjb = cdjb;
		this.line = line;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((line == null) ? 0 : line.hashCode());
		result = PRIME * result + ((zrjb == null) ? 0 : zrjb.hashCode());
		result = PRIME * result + ((cdjb == null) ? 0 : cdjb.hashCode());
		result = PRIME * result + ((rzzl == null) ? 0 : rzzl.hashCode());
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
		final RiziLpPk other = (RiziLpPk) obj;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (cdjb == null) {
			if (other.cdjb != null)
				return false;
		} else if (!cdjb.equals(other.cdjb))
			return false;
		if (zrjb == null) {
			if (other.zrjb != null)
				return false;
		} else if (!zrjb.equals(other.zrjb))
			return false;
		if (rzzl == null) {
			if (other.rzzl != null)
				return false;
		} else if (!rzzl.equals(other.rzzl))
			return false;
		return true;
	}

}