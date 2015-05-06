package com.coco.query.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coco.query.api.QueryConfig;
import com.coco.query.api.ValueParser;
import com.coco.query.bean.Entry;
import com.coco.query.bean.Opt;
import com.coco.query.bean.Param;
import com.coco.query.bean.ParamItem;

public class QueryUtils {

	public static void build(QueryParseVo vo, List<ParamItem> params) {
		if (vo == null || params == null || params.isEmpty()) {
			return;
		}
		Object value;
		Object[] values;
		String area = null;
		Opt opt;
		Entry entry = vo.entry;
		Param param;
		ValueParser parser = vo.config.createValueParser();
		String clause;
		for (ParamItem item : params) {
			if (item.getId() == null || item.getId().isEmpty()) {
				continue;
			}
			param = entry.getParam(item.getId());
			if (param == null) {
				continue;
			}
			clause = null;
			area = param.getArea();
			opt = item.getOpt();
			do {
				if (opt.needValue) {
					value = parser.parse(item.getValue(), param.getType());
					if (value != null) {
						if (String.class.equals(param.getType())) {
							if ("".equals(value)) {
								clause = "1=1";
								break;
							}
						}
						if (Opt.like.equals(opt)) {
							if (!String.class.equals(param.getType())) {
								clause = "1=1";
								break;
							}
							value = "%" + value + "%";
						}
						vo.values.add(value);
						clause = param.getColumn()
								+ opt.opt
								+ (opt.equals(Opt.in) || opt.equals(Opt.notIn)
										? "(?)"
										: "?");
					} else if (item.getValues() != null
							&& item.getValues().length > 0) {
						if (!(opt.equals(Opt.in) || opt.equals(Opt.notIn))) {
							clause = "1=1";
							break;
						}
						values = parser
								.parse(item.getValues(), param.getType());
						if (values == null || values.length == 0) {
							clause = "1=1";
							break;
						}
						String preparedParamString = "(?";
						vo.values.add(values[0]);
						for (int vsIndex = 1; vsIndex < values.length; vsIndex++) {
							preparedParamString += ",?";
							vo.values.add(values[vsIndex]);
						}
						preparedParamString += ")";
						clause = param.getColumn() + opt.opt
								+ preparedParamString;
					}
				} else {
					clause = param.getColumn() + opt.opt;
				}
			} while (1 > 1);
			if (clause == null) {
				continue;
			}
			StringBuilder clauses = vo.areaParams.get(area);
			if (clauses == null) {
				clauses = new StringBuilder();
				vo.areaParams.put(area, clauses);
			} else if (clauses.charAt(clauses.length() - 1) != '(') {
				clauses.append(" and ");
			}
			if (item.getOrs() != null && !item.getOrs().isEmpty()) {
				clauses.append("(").append(clause).append(" or (");
				build(vo, item.getOrs());
				clauses.append(") )");
			} else {
				clauses.append(clause);
			}
		}
	}

	public static class QueryParseVo {

		public QueryParseVo(QueryConfig config, Entry entry) {
			this.config = config;
			this.entry = entry;
		}

		QueryConfig config;

		Entry entry;

		public Map<String, StringBuilder> areaParams = new HashMap<String, StringBuilder>();

		public List<Object> values = new ArrayList<Object>();;
	}
}
