package com.quanta.sino.cmn.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.quanta.sino.orm.DataInfo;

@Q
public class DataInfoQE extends QEntity<DataInfo> {

	@QF(operator = EO.LIKE)
	private String code;

	@QF(operator = EO.LIKE)
	private String type;

	@QF(operator = EO.LIKE)
	private String name;

	@QF(operator = EO.LIKE)
	private String keyw;

	@QF(operator = EO.LIKE)
	private String memo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getKeyw() {
		return keyw;
	}

	public void setKeyw(String keyw) {
		this.keyw = keyw;
	}

}
