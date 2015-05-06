package com.coco.query.bean;

import java.util.List;
import java.util.Map;

import com.coco.core.bean.User;

public class Page {

	public static int TYPE_DATAS_ARRAY = 1;

	public static int TYPE_DATAS_MAP = 2;

	public int resultType;

	public String id;

	public List<String> metaIds;

	public String metaIdStr;

	public Meta[] metas;

	public List<ParamItem> params;

	public User user;

	public Order order;

	public int index;

	public int size;

	public int count;

	public int pageCount;

	public List<Map<String, Object>> maps;

	public List<Object[]> objects;

	/**
	 * 标志生成查询字段类型
	 */
	public boolean needType;

	/**
	 * 查询字段类型，必须指明needType的值为真才有效
	 */
	public Map<String, Class<?>> types;

}
