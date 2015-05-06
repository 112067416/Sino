package com.coco.core.mvc.cascade.sample;

import java.util.ArrayList;
import java.util.List;

import com.coco.core.bean.Property;
import com.coco.core.mvc.cascade.api.IBeanCascadeModule;
import com.coco.core.mvc.cascade.bean.BeanValue;

/**
 * <p>
 * 自定义Bean模块实现范例-省份级联
 * </p>
 * <p>
 * create: 2010-12-23 下午03:12:07
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SampleProvBeanCascadeModule implements IBeanCascadeModule {

	@Override
	public List<BeanValue> execute(String value) {
		List<Property> props = new ArrayList<Property>();
		props.add(new Property("", "城市"));
		if ("hi".equals(value)) {
			props.add(new Property("hk", "海口"));
			props.add(new Property("lg", "临高"));
			props.add(new Property("cm", "澄迈"));
		}
		else if ("gd".equals(value)) {
			props.add(new Property("gz", "广州"));
			props.add(new Property("sz", "深圳"));
			props.add(new Property("dg", "东莞"));
		}
		List<BeanValue> values = new ArrayList<BeanValue>();
		values.add(new BeanValue(props));
		return values;
	}

}
