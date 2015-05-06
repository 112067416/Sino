package com.coco.core.bean;

import java.io.Serializable;

/**
 * <p>
 * 系统核心操作用户基础类
 * </p>
 * <p>
 * create: 2010-12-21 上午09:19:39
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 帐号
	 */
	private String id;

	/**
	 * 工号
	 */
	protected String no;

	/**
	 * 员工ID
	 */
	private String person;
	/**
	 * 员工类型
	 */
	private String personType;

	/**
	 * 员工姓名
	 */
	private String name;

	/**
	 * 所属部门Id
	 */
	private String deptId;

	/**
	 * 所属部门名称
	 */
	private String deptName;

	/**
	 * 所属部门类型
	 */
	private String deptType;

	/**
	 * 登录IP
	 */
	protected String loginIp;

	/**
	 * 登录时间
	 */
	protected long loginTime;

	/**
	 * 操作时间
	 */
	protected long operateTime;

	/**
	 * 组别
	 */
	protected String group;

	/**
	 * 班别
	 */
	protected String shift;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

}
