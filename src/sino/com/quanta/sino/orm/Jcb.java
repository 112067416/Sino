package com.quanta.sino.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jcb")
public class Jcb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	/**
	 * 原材卷板ID
	 */
	@Column(name = "JBNO_", length = 7)
	private String jbno;
	/**
	 * 制品在库DBID
	 */
	@Column(name = "process_flag")
	private boolean processflag;

	public Integer getId() {
		return id;
	}

	public boolean isProcessflag() {
		return processflag;
	}

	public void setProcessflag(boolean processflag) {
		this.processflag = processflag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

}
