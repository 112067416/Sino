package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 系统辅助_数据信息说明
 * 
 * @author 方元龙
 */
@Entity
@Table(name = "ASS_DATA_INFO")
public class DataInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 代码
	 */
	@Column(name = "CODE_", length = 128)
	private String code;

	/**
	 * 类型
	 */
	@Column(name = "TYPE_", length = 128)
	private String type;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 512, nullable = false)
	private String name;

	/**
	 * 关键字
	 */
	@Column(name = "KEYW_", length = 512)
	private String keyw;

	/**
	 * 描述
	 */
	@Column(name = "MEMO_", length = 8000)
	private String memo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyw() {
		return keyw;
	}

	public void setKeyw(String keyw) {
		this.keyw = keyw;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
