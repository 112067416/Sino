package com.coco.query.orm;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * <p>
 * </p>
 * <p>
 * create: 2010-12-21 上午10:31:17
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class EntryFkPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Column(name = "ENTRY_", length = 64)
	private String entry;

	/**
	 * 
	 */
	@Column(name = "ID_", length = 64)
	private String id;

	public EntryFkPk() {

	}

	public EntryFkPk(String entry, String id) {
		this.entry = entry;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((entry == null) ? 0 : entry.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final EntryFkPk other = (EntryFkPk) obj;
		if (id == null) {
			if (other.id != null) return false;
		}
		else if (!id.equals(other.id)) return false;
		if (entry == null) {
			if (other.entry != null) return false;
		}
		else if (!entry.equals(other.entry)) return false;
		return true;
	}

}