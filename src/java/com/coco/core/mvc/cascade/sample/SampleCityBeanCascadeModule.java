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
public class SampleCityBeanCascadeModule implements IBeanCascadeModule {

	@Override
	public List<BeanValue> execute(String value) {
		List<Property> props = new ArrayList<Property>();
		props.add(new Property("", "区镇"));
		if ("hk".equals(value)) {
			props.add(new Property("lh", "龙华"));
			props.add(new Property("ml", "美兰"));
			props.add(new Property("xy", "秀英"));
			props.add(new Property("qs", "琼山"));
		}
		else if ("lg".equals(value)) {
			props.add(new Property("lc", "临城"));
			props.add(new Property("dy", "东英"));
			props.add(new Property("xy", "新盈"));
		}
		else if ("sz".equals(value)) {
			props.add(new Property("ft", "福田"));
			props.add(new Property("lh", "罗湖"));
			props.add(new Property("ns", "南山"));
		}
		List<BeanValue> values = new ArrayList<BeanValue>();
		values.add(new BeanValue(props));
		return values;
	}

}
