package com.quanta.sino.zkfp.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 对匹配比较结果
 * </p>
 * <p>
 * create: 2011-3-3 下午03:13:21
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class PError {

	/**
	 * 
	 */
	private List<CError> errors = new ArrayList<CError>();

	public List<CError> getErrors() {
		return errors;
	}

	public void setErrors(List<CError> errors) {
		this.errors = errors;
	}

}
