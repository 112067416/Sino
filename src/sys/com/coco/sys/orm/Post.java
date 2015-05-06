package com.coco.sys.orm;

import java.io.Serializable;

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
 * <p>
 * 系统岗位
 * </p>
 * <p>
 * create time : 2010-10-14 下午09:24:06
 * </p>
 * 
 * @author 许德建(xudejian@126.com)
 */
@Entity
@Table(name = "COCO_POST")
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Post() {

	}

	public Post(String id) {
		this.id = id;
	}

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 岗位名称
	 */
	@Column(name = "NAME_", length = 32, nullable = false)
	private String name;

	/**
	 * 所属组织
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DEPT_", referencedColumnName = "ID_", nullable = false)
	private Dept dept;

	/**
	 * 权限组织
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRIV_DEPT_", referencedColumnName = "ID_", nullable = false)
	private Dept privDept;

	/**
	 * 有效标志
	 */
	@Column(name = "VALID_")
	private boolean valid;

	/**
	 * 共享标志
	 */
	@Column(name = "SHARE_")
	private boolean share;

	/**
	 * 向下兼管
	 */
	@Column(name = "CONTAIN_")
	private boolean contain;

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

	public Dept getPrivDept() {
		return privDept;
	}

	public void setPrivDept(Dept privDept) {
		this.privDept = privDept;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public boolean isContain() {
		return contain;
	}

	public void setContain(boolean contain) {
		this.contain = contain;
	}
}
