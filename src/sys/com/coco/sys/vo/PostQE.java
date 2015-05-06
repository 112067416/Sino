package com.coco.sys.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Post;

@Q
public class PostQE extends QEntity<Post> {

	@QF(alias = "dept.id")
	private String dept;

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
