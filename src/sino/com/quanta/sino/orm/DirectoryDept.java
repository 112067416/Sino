package com.quanta.sino.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * <p>
 * 资料室目录对应的部门
 * </p>
 * <p>
 * create: 2011-4-12 下午02:42:25
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_DIRECTORY_DEPT")
@IdClass(DirectoryDeptPk.class)
public class DirectoryDept {

	/**
	 * 目录
	 */
	@Id
	@Column(name = "DIRECTORY_ID_", length = 40)
	private String directoryId;

	/**
	 * 部门
	 */
	@Id
	@Column(name = "DEPT_ID_", length = 40)
	private String deptId;

	public String getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(String directoryId) {
		this.directoryId = directoryId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
