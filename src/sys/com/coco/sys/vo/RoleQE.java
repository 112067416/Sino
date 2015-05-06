package com.coco.sys.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Role;

@Q
public class RoleQE extends QEntity<Role> {

	@QF
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
