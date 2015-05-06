package com.coco.core.mvc.cascade.executer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coco.core.bean.Property;
import com.coco.core.mvc.cascade.api.IBeanCascadeModule;
import com.coco.core.mvc.cascade.api.ICascadeExecuter;
import com.coco.core.mvc.cascade.bean.BeanValue;
import com.coco.core.util.StringUtils;

/**
 * <p>
 * 级联Bean执行器
 * </p>
 * <p>
 * create: 2010-12-23 下午02:39:24
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class BeanCascadeExecuter implements ICascadeExecuter {

	/**
	 * 级联Bean模块列表
	 */
	private Map<String, IBeanCascadeModule> modules;

	@Override
	public String execute(String module, String value) {
		if (module == null) {
			return "{}";
		}
		IBeanCascadeModule beanModule = modules.get(module);
		if (beanModule == null) {
			return "{}";
		}
		List<BeanValue> values = beanModule.execute(value);
		if (values == null || values.isEmpty()) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		String field;
		Set<String> fields = new HashSet<String>();
		for (BeanValue beanValue : values) {
			field = beanValue.getField();
			if (field == null || field.isEmpty()) {
				field = "$";
			}
			if (fields.contains(field)) {
				continue;
			}
			// 获取值
			if (beanValue.getValue() != null) {
				if (!fields.isEmpty()) {
					sb.append(",");
				}
				sb.append(field).append(":[\"");
				sb.append(StringUtils.toJsString(beanValue.getValue()));
				sb.append("\"]");
				fields.add(field);
			}
			// 获取下拉列表
			else if (beanValue.getOptions() != null) {
				if (!fields.isEmpty()) {
					sb.append(",");
				}
				sb.append(field).append(":[\"");
				for (Property prop : beanValue.getOptions()) {
					sb.append("<option value=\\\"").append(StringUtils.toJsString(StringUtils.toJsString(prop.getKey()))).append("\\\">").append(prop.getValue()).append("</option>");
				}
				sb.append("\",1]");
				fields.add(field);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the modules
	 */
	public Map<String, IBeanCascadeModule> getModules() {
		return modules;
	}

	/**
	 * @param modules
	 *            the modules to set
	 */
	public void setModules(Map<String, IBeanCascadeModule> modules) {
		this.modules = modules;
	}
}
