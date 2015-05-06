package com.coco.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.coco.query.api.QueryConfig;
import com.coco.query.api.QueryEngine;
import com.coco.query.bean.Entry;
import com.coco.query.bean.Meta;
import com.coco.query.bean.Option;
import com.coco.query.bean.Page;
import com.coco.query.util.QueryUtils;

public class DefaultQueryEngine implements QueryEngine {

	private QueryConfig config;

	private static final Pattern FORMAT_SQL_PATTERN = Pattern.compile(
			"(where|and|or)#\\d+", Pattern.CASE_INSENSITIVE);

	public DefaultQueryEngine() {
	}

	public DefaultQueryEngine(QueryConfig config) {
		this.config = config;
		this.config.setEngine(this);
	}

	@Override
	public void query(Page page) {
		if (page == null || page.id == null || config == null
				|| config.getDataSource() == null) {
			return;
		}
		Entry entry = config.entries().get(page.id);
		if (entry == null) {
			return;
		}
		if (page.index < 0) {
			page.index = 0;
		}
		if (page.metaIds == null || page.metaIds.isEmpty()) {
			page.metaIds = Arrays.asList(entry.allMetaIds());
			page.metas = entry.getMetas();
			page.metaIdStr = entry.allColumn();
		} else {
			StringBuilder sb = new StringBuilder();
			Meta[] metas = entry.getMetas();
			List<Meta> columns = new ArrayList<Meta>();
			Iterator<String> metaIdIt = page.metaIds.iterator();
			String metaId;
			boolean found;
			while (metaIdIt.hasNext()) {
				metaId = metaIdIt.next();
				found = false;
				for (Meta meta : metas) {
					if (meta.getId().equals(metaId)) {
						columns.add(meta);
						if (sb.length() > 0) {
							sb.append(", ");
						}
						sb.append(meta.getExpr()).append(" ")
								.append(meta.getId());
						found = true;
						break;
					}
				}
				if (!found) {
					metaIdIt.remove();
				}
			}
			if (sb.length() == 0) {
				page.metaIds = Arrays.asList(entry.allMetaIds());
				page.metas = metas;
				page.metaIdStr = entry.allColumn();
			} else {
				page.metas = new Meta[columns.size()];
				columns.toArray(page.metas);
				page.metaIdStr = sb.toString();
			}
		}

		String sql = entry.getSql();

		QueryUtils.QueryParseVo vo = new QueryUtils.QueryParseVo(config, entry);
		QueryUtils.build(vo, page.params);

		for (String areaName : vo.areaParams.keySet()) {
			sql = sql.replaceAll("#" + areaName + "(\\s+|$)", " "
					+ vo.areaParams.get(areaName) + " ");
		}
		if (sql.indexOf("#$userId") != -1) {
			String userId = page.user != null ? page.user.getId() : null;
			if (userId != null) {
				userId = "'" + userId.replaceAll("'", "''") + "'";
			} else {
				userId = "''";
			}
			sql.replaceAll("#\\$userId", userId);
		}
		if (sql.indexOf("#$deptId") != -1) {
			String deptId = page.user != null ? page.user.getDeptId() : null;
			if (deptId != null) {
				deptId = "'" + deptId.replaceAll("'", "''") + "'";
			} else {
				deptId = "''";
			}
			sql.replaceAll("#\\$deptId", deptId);
		}
		sql = FORMAT_SQL_PATTERN.matcher(sql).replaceAll("");
		String countSql = null;
		if (page.size > 0) {
			countSql = sql.replace("*", "count(*)");
		}
		sql = sql.replaceFirst("\\*", page.metaIdStr);
		System.out.println(sql);
		// 查询
		Connection conn = null;
		int index;
		try {
			conn = config.getDataSource().getConnection();
			if (page.size > 0) {
				PreparedStatement countPstmt = conn.prepareStatement(countSql);
				index = 0;
				for (Object v : vo.values) {
					countPstmt.setObject(++index, v);
				}
				ResultSet countRs = countPstmt.executeQuery();
				if (countRs.next()) {
					page.count = countRs.getInt(1);
					page.pageCount = page.count / page.size;
					if (page.count % page.size != 0) {
						page.pageCount += 1;
					}
				}
				countRs.close();
				countPstmt.close();
			}
			if (page.size <= 0 || page.pageCount > page.index) {
				Map<String, Object> mapData;
				Object[] objectData;
				Object object;
				boolean mapable = page.resultType == 0
						|| (page.resultType & Page.TYPE_DATAS_MAP) == Page.TYPE_DATAS_MAP;
				boolean arrayable = page.resultType == 0
						|| (page.resultType & Page.TYPE_DATAS_ARRAY) == Page.TYPE_DATAS_ARRAY;
				// 若两个都不是则设置两个都是
				if (!mapable && !arrayable) {
					mapable = true;
					arrayable = true;
				}
				if (mapable) {
					page.maps = new ArrayList<Map<String, Object>>();
				}
				if (arrayable) {
					page.objects = new ArrayList<Object[]>();
				}
				PreparedStatement pstmt = conn.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				index = 0;
				for (Object v : vo.values) {
					pstmt.setObject(++index, v);
				}
				if (page.size > 0) {
					pstmt.setMaxRows((page.index + 1) * page.size);
				}
				ResultSet rs = pstmt.executeQuery();
				if (page.needType) {
					ResultSetMetaData rsmd = rs.getMetaData();
					page.types = new HashMap<String, Class<?>>();
					for (int i = 0; i < page.metaIds.size(); i++) {
						try {
							page.types.put(page.metaIds.get(i), Class
									.forName(rsmd.getColumnClassName(i + 1)));
						} catch (Exception e) {
						}
					}
				}
				if (page.size > 0) {
					rs.absolute(page.index * page.size);
					rs.setFetchSize(page.size);
				}
				while (rs.next()) {
					mapData = new HashMap<String, Object>();
					objectData = new Object[page.metaIds.size()];
					for (int i = 0; i < page.metaIds.size(); i++) {
						object = rs.getObject(i + 1);
						if (mapable) {
							mapData.put(page.metaIds.get(i), object);
						}
						if (arrayable) {
							objectData[i] = object;
						}
					}
					if (mapable) {
						page.maps.add(mapData);
					}
					if (arrayable) {
						page.objects.add(objectData);
					}
				}
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public List<Option> queryOptions(String sql) {
		List<Option> options = new ArrayList<Option>();
		Connection conn = null;
		try {
			conn = config.getDataSource().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			boolean hasValue = rsmd.getColumnCount() > 1;
			Option option;
			while (rs.next()) {
				option = new Option();
				option.key = rs.getString(1);
				option.value = hasValue ? rs.getString(2) : option.key;
				options.add(option);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return options;
	}
}
