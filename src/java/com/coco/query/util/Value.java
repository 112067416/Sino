package com.coco.query.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Value {
	public static final String GROUP_SPLIT = "##$$##";

	private List<String> groupNames = new ArrayList<String>();

	private List<Map<String, Object>> mapValues = new ArrayList<Map<String, Object>>();

	private List<List<Object>> listValues = new ArrayList<List<Object>>();

	private Map<String, List<Map<String, Object>>> mapGroups = new HashMap<String, List<Map<String, Object>>>();

	private Map<String, List<List<Object>>> listGroups = new HashMap<String, List<List<Object>>>();

	/**
	 * <p>
	 * 添加数据值
	 * </p>
	 * 
	 * @param mapValue
	 *            Map值
	 * @param listValue
	 *            List值
	 */
	public void addValue(Map<String, Object> mapValue, List<Object> listValue) {
		if (mapValue == null) {
			return;
		}
		mapValues.add(mapValue);
		if (listValue != null) {
			listValues.add(listValue);
		}
		if (groupNames != null && groupNames.size() > 0) {
			String groupKey = null;
			for (String name : groupNames) {
				if (groupKey == null) {
					groupKey = String.valueOf(mapValue.get(name));
				}
				else {
					groupKey += GROUP_SPLIT + mapValue.get(name);
				}
			}
			List<Map<String, Object>> listMap = mapGroups.get(groupKey);
			if (listMap == null) {
				listMap = new ArrayList<Map<String, Object>>();
				mapGroups.put(groupKey, listMap);
			}
			listMap.add(mapValue);
			if (listValue != null) {
				List<List<Object>> listList = listGroups.get(groupKey);
				if (listList == null) {
					listList = new ArrayList<List<Object>>();
					listGroups.put(groupKey, listList);
				}
				listList.add(listValue);
			}

		}
	}

	/**
	 * <p>
	 * 获取分组值
	 * </p>
	 * 
	 * @param keys
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getMapGroup(List<String> keys) {
		if (keys == null || keys.size() == 0) {
			return null;
		}
		String groupKey = null;
		for (String key : keys) {
			if (groupKey == null) {
				groupKey = key;
			}
			else {
				groupKey += GROUP_SPLIT + key;
			}
		}
		return mapGroups.get(groupKey);
	}

	public List<List<Object>> getListGroup(List<String> keys) {
		if (keys == null || keys.size() == 0) {
			return null;
		}
		String groupKey = null;
		for (String key : keys) {
			if (groupKey == null) {
				groupKey = key;
			}
			else {
				groupKey += GROUP_SPLIT + key;
			}
		}
		return listGroups.get(groupKey);
	}

	public List<String> getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}

	public List<Map<String, Object>> getMapValues() {
		return mapValues;
	}

	public List<List<Object>> getListValues() {
		return listValues;
	}

}
