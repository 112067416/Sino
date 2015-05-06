package com.coco.sys.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COCO_SEQUENCE_RULE")
public class SeqRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SEQ_", referencedColumnName = "ID_", nullable = false)
	private Seq seq;

	@Column(name = "TYPE_", length = 10)
	private String type;

	@Column(name = "EXPR_", length = 256)
	private String expr;

	@Column(name = "VALUE_", length = 256)
	private String value;

	@Column(name = "RESETABLE_")
	private boolean resetable;

	@Column(name = "ORDER_", length = 10)
	private String order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Seq getSeq() {
		return seq;
	}

	public void setSeq(Seq seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isResetable() {
		return resetable;
	}

	public void setResetable(boolean resetable) {
		this.resetable = resetable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}