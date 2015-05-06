package com.quanta.sino.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>
 * 打印日志表
 * </p>
 * <p>
 * create: 2011-4-20 下午03:04:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_PRINT_LOG")
public class PrintLog implements Serializable {

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
	 * 打印号
	 */
	@Column(name = "NO_", length = 32, nullable = false)
	private String no;

	/**
	 * 打印类型
	 */
	@Column(name = "TYPE_", length = 2, nullable = false)
	private String type;

	/**
	 * 打印时间
	 */
	@Column(name = "TIME_", nullable = false)
	private Date time;

	/**
	 * 操作人
	 */
	@Column(name = "OPERATOR_")
	private String operator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
