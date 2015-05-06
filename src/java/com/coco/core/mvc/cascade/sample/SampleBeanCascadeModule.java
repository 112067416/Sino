package com.coco.core.mvc.cascade.sample;

import java.util.ArrayList;
import java.util.List;

import com.coco.core.bean.Property;
import com.coco.core.mvc.cascade.api.IBeanCascadeModule;
import com.coco.core.mvc.cascade.bean.BeanValue;

/**
 * <p>
 * 自定义Bean模块实现范例
 * </p>
 * <p>
 * create: 2010-12-23 下午03:12:07
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SampleBeanCascadeModule implements IBeanCascadeModule {

	@Override
	public List<BeanValue> execute(String value) {
		List<BeanValue> values = new ArrayList<BeanValue>();
		values.add(new BeanValue("st\"r"));
		values.add(new BeanValue("beanInput1", value));
		List<Property> props = new ArrayList<Property>();
		props.add(new Property("a\"b", "啊"));
		props.add(new Property("o", "哦"));
		props.add(new Property("e", "饿"));
		values.add(new BeanValue("beanSelect1", props));
		return values;
	}

}
