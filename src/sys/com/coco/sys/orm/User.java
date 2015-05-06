package com.coco.sys.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户信息表
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "COCO_USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {

	}

	public User(String id) {
		this.id = id;
	}

	/**
	 * 用户标识
	 */
	@Id
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 用户密码
	 */
	@Column(name = "PSWD_", length = 128, nullable = false)
	private String pswd;

	/**
	 * 有效标志
	 */
	@Column(name = "VALID_")
	private boolean valid;

	/**
	 * 排序
	 */
	@Column(name = "ORDER_", length = 20)
	private String order;

	/**
	 * 级别
	 */
	@Column(name = "GRADE_", length = 10)
	private String grade;

	/**
	 * 关联员工
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PERSON_", referencedColumnName = "ID_", nullable = false)
	private Person person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
