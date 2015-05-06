package com.coco.sys.vo;

import java.util.List;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Attachment;

/**
 * <p>
 * 附件查询条件
 * </p>
 * <p>
 * create: 2011-3-1 上午11:33:36
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q
public class AttachQE extends QEntity<Attachment> {

	/**
	 * 附件类型
	 */
	@QF
	private String type;

	/**
	 * 多个附件类型
	 */
	@QF(alias = "type", operator = EO.IN)
	private List<String> types;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
