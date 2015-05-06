package com.coco.sys.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户扩展信息
 * 
 * @author 许德建[xudejian@126.com]
 */
@Entity
@Table(name = "COCO_PERSON")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 姓名
	 */
	@Column(name = "NAME_", length = 32, nullable = false)
	private String name;

	/**
	 * 工号
	 */
	@Column(name = "NO_", length = 8, nullable = false)
	private String no;

	/**
	 * 所属组织
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DEPT_", referencedColumnName = "ID_", nullable = false)
	private Dept dept;

	/**
	 * 性别
	 */
	@Column(name = "SEX_", length = 2)
	private String sex;

	/**
	 * 出生年月
	 */
	@Column(name = "BIRTHDAY_")
	private Date birthday;

	/**
	 * 身份证号
	 */
	@Column(name = "IDCARD_", length = 20)
	private String idcard;

	/**
	 * 地址
	 */
	@Column(name = "ADDRESS_", length = 256)
	private String address;

	/**
	 * 电话号码
	 */
	@Column(name = "TELE_", length = 20)
	private String tele;

	/**
	 * 手机号码
	 */
	@Column(name = "MOBILE_", length = 20)
	private String mobile;

	/**
	 * QQ
	 */
	@Column(name = "QQ_", length = 20)
	private String qq;

	/**
	 * MSN
	 */
	@Column(name = "MSN_", length = 256)
	private String msn;

	/**
	 * Email
	 */
	@Column(name = "EMAIL_", length = 256)
	private String email;

	/**
	 * 备注
	 */
	@Column(name = "REMARK_", length = 40)
	private String remark;

	/**
	 * 排序
	 */
	@Column(name = "ORDER_", length = 20)
	private String order;

	/**
	 * 有效标志
	 */
	@Column(name = "VALID_")
	private boolean valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
