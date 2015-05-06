package com.coco.core.mvc.cascade.api;

import java.util.List;

import com.coco.core.mvc.cascade.bean.BeanValue;

public interface IBeanCascadeModule {

	public List<BeanValue> execute(String value);

}
