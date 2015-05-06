package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;

public class DirectoryDeptPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 目录
	 */
	@Column(name = "DIRECTORY_ID_", length = 40)
	private String directoryId;

	/**
	 * 部门
	 */
	@Column(name = "DEPT_ID_", length = 40)
	private String deptId;

	public DirectoryDeptPk() {

	}

	public DirectoryDeptPk(String directoryId, String deptId) {
		this.directoryId = directoryId;
		this.deptId = deptId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = PRIME * result
				+ ((directoryId == null) ? 0 : directoryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final DirectoryDeptPk other = (DirectoryDeptPk) obj;
		if (deptId == null) {
			if (other.deptId != null) return false;
		}
		else if (!deptId.equals(other.deptId)) return false;
		if (directoryId == null) {
			if (other.directoryId != null) return false;
		}
		else if (!directoryId.equals(other.directoryId)) return false;
		return true;
	}

}