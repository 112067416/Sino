package com.coco.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.coco.query.api.QueryConfig;
import com.coco.query.api.QueryParser;
import com.coco.query.api.QueryRepository;
import com.coco.query.bean.Entry;
import com.coco.query.bean.Meta;
import com.coco.query.bean.Option;
import com.coco.query.bean.Param;

public class DefaultQueryRepository implements QueryRepository {

	private QueryConfig config;

	private QueryParser parser;

	public DefaultQueryRepository() {
	}

	public DefaultQueryRepository(QueryConfig config) {
		this.config = config;
		this.config.setRepository(this);
	}

	@Override
	public void persist(List<Entry> entries) {
		if (config == null || config.getDataSource() == null || entries == null
				|| entries.isEmpty()) {
			return;
		}
		String checkEntrySql = "select count(*) from QUERY_ENTRY where ID_=?";
		String insertEntrySql = "insert into QUERY_ENTRY(ID_, NAME_, SQL_, SIZE_, DESCRIPTION_, VALID_, EXCEL_) values(?,?,?,?,?,?,?)";
		String updateEntrySql = "update QUERY_ENTRY set NAME_=?, SQL_=?, SIZE_=?, DESCRIPTION_=?, VALID_=?, EXCEL_=? where ID_=?";

		String checkMetaSql = "select count(*) from QUERY_META where ID_=? and ENTRY_=?";
		String insertMetaSql = "insert into QUERY_META(ID_, ENTRY_, EXPR_, LABEL_, ORDERABLE_) values(?,?,?,?,?)";
		String updateMetaSql = "update QUERY_META set EXPR_=?, LABEL_=?, ORDERABLE_=? where ID_=? and ENTRY_=?";
		String deleteMetaSql = "delete from QUERY_META where ENTRY_=?";

		String checkParamSql = "select count(*) from QUERY_PARAM where ID_=? and ENTRY_=?";
		String insertParamSql = "insert into QUERY_PARAM(ID_, ENTRY_, COLUMN_, LABEL_, AREA_, TYPE_, OPTIONS_, SQLOPTION_) values(?,?,?,?,?,?,?,?)";
		String updateParamSql = "update QUERY_PARAM set COLUMN_=?, LABEL_=?, AREA_=?, TYPE_=?, OPTIONS_=?, SQLOPTION_=? where ID_=? and ENTRY_=?";
		String deleteParamSql = "delete from QUERY_PARAM where ENTRY_=?";

		String deleteSql;

		Connection conn = null;
		PreparedStatement checkEntryPstmt = null;
		PreparedStatement insertEntryPstmt = null;
		PreparedStatement updateEntryPstmt = null;
		PreparedStatement checkMetaPstmt = null;
		PreparedStatement insertMetaPstmt = null;
		PreparedStatement updateMetaPstmt = null;
		PreparedStatement checkParamPstmt = null;
		PreparedStatement insertParamPstmt = null;
		PreparedStatement updateParamPstmt = null;
		PreparedStatement deletePstmt = null;
		Meta[] metas;
		Param[] params;
		int index;
		List<String> ids = new ArrayList<String>();
		try {
			conn = config.getDataSource().getConnection();
			conn.setAutoCommit(false);
			checkEntryPstmt = conn.prepareStatement(checkEntrySql);
			insertEntryPstmt = conn.prepareStatement(insertEntrySql);
			updateEntryPstmt = conn.prepareStatement(updateEntrySql);
			checkMetaPstmt = conn.prepareStatement(checkMetaSql);
			insertMetaPstmt = conn.prepareStatement(insertMetaSql);
			updateMetaPstmt = conn.prepareStatement(updateMetaSql);
			checkParamPstmt = conn.prepareStatement(checkParamSql);
			insertParamPstmt = conn.prepareStatement(insertParamSql);
			updateParamPstmt = conn.prepareStatement(updateParamSql);
			ResultSet rs;
			for (Entry entry : entries) {
				if (entry.getId() == null) {
					continue;
				}
				checkEntryPstmt.setString(1, entry.getId());
				rs = checkEntryPstmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {
					updateEntryPstmt.setString(1, entry.getName());
					updateEntryPstmt.setString(2, entry.getSql());
					updateEntryPstmt.setInt(3, entry.getDefaultSize());
					updateEntryPstmt.setString(4, entry.getDescription());
					updateEntryPstmt.setBoolean(5, entry.isValid());
					updateEntryPstmt.setBoolean(6, entry.isExcel());
					updateEntryPstmt.setString(7, entry.getId());
					updateEntryPstmt.executeUpdate();
				} else {
					insertEntryPstmt.setString(1, entry.getId());
					insertEntryPstmt.setString(2, entry.getName());
					insertEntryPstmt.setString(3, entry.getSql());
					insertEntryPstmt.setInt(4, entry.getDefaultSize());
					insertEntryPstmt.setString(5, entry.getDescription());
					insertEntryPstmt.setBoolean(6, entry.isValid());
					insertEntryPstmt.setBoolean(7, entry.isExcel());
					insertEntryPstmt.executeUpdate();
				}
				rs.close();

				metas = entry.getMetas();
				ids.clear();
				if (metas != null && metas.length > 0) {
					for (Meta meta : metas) {
						ids.add(meta.getId());
						checkMetaPstmt.setString(1, meta.getId());
						checkMetaPstmt.setString(2, entry.getId());
						rs = checkMetaPstmt.executeQuery();
						if (rs.next() && rs.getInt(1) > 0) {
							updateMetaPstmt.setString(1, meta.getExpr());
							updateMetaPstmt.setString(2, meta.getLabel());
							updateMetaPstmt.setBoolean(3, meta.isOrderable());
							updateMetaPstmt.setString(4, meta.getId());
							updateMetaPstmt.setString(5, entry.getId());
							updateMetaPstmt.executeUpdate();
						} else {
							insertMetaPstmt.setString(1, meta.getId());
							insertMetaPstmt.setString(2, entry.getId());
							insertMetaPstmt.setString(3, meta.getExpr());
							insertMetaPstmt.setString(4, meta.getLabel());
							insertMetaPstmt.setBoolean(5, meta.isOrderable());
							insertMetaPstmt.executeUpdate();
						}
						rs.close();
					}
				}
				deleteSql = deleteMetaSql;
				if (!ids.isEmpty()) {
					StringBuilder sb = new StringBuilder(" and ID_ not in (?");
					for (int i = 1; i < ids.size(); i++) {
						sb.append(",?");
					}
					sb.append(")");
					deleteSql += sb;
				}
				deletePstmt = conn.prepareStatement(deleteSql);
				deletePstmt.setString(1, entry.getId());
				index = 2;
				for (String id : ids) {
					deletePstmt.setString(index++, id);
				}
				deletePstmt.executeUpdate();
				deletePstmt.close();

				ids.clear();
				params = entry.getParams();
				if (params != null && params.length > 0) {
					for (Param param : params) {
						ids.add(param.getId());
						checkParamPstmt.setString(1, param.getId());
						checkParamPstmt.setString(2, entry.getId());
						rs = checkParamPstmt.executeQuery();
						if (rs.next() && rs.getInt(1) > 0) {
							updateParamPstmt.setString(1, param.getColumn());
							updateParamPstmt.setString(2, param.getLabel());
							updateParamPstmt.setString(3, param.getArea());
							updateParamPstmt.setString(4, param.getType()
									.getName());
							Option[] options = param.getOptions();
							if (options == null) {
								updateParamPstmt.setNull(5, Types.VARCHAR);
							} else {
								StringBuilder sb = new StringBuilder();
								for (Option option : options) {
									if (sb.length() > 0) {
										sb.append(",");
									}
									sb.append("'").append(option.key)
											.append("':'").append(option.value)
											.append("'");
								}
								updateParamPstmt.setString(5, sb.toString());
							}
							updateParamPstmt.setString(6, param.getSqlOption());
							updateParamPstmt.setString(7, param.getId());
							updateParamPstmt.setString(8, entry.getId());
							updateParamPstmt.executeUpdate();
						} else {
							insertParamPstmt.setString(1, param.getId());
							insertParamPstmt.setString(2, entry.getId());
							insertParamPstmt.setString(3, param.getColumn());
							insertParamPstmt.setString(4, param.getLabel());
							insertParamPstmt.setString(5, param.getArea());
							insertParamPstmt.setString(6, param.getType()
									.getName());
							Option[] options = param.getOptions();
							if (options == null) {
								insertParamPstmt.setNull(7, Types.VARCHAR);
							} else {
								StringBuilder sb = new StringBuilder();
								for (Option option : options) {
									if (sb.length() > 0) {
										sb.append(",");
									}
									sb.append("'").append(option.key)
											.append("':'").append(option.value)
											.append("'");
								}
								insertParamPstmt.setString(7, sb.toString());
							}
							insertParamPstmt.setString(8, param.getSqlOption());
							insertParamPstmt.executeUpdate();
						}
						rs.close();
					}
				}
				deleteSql = deleteParamSql;
				if (!ids.isEmpty()) {
					StringBuilder sb = new StringBuilder(" and ID_ not in (?");
					for (int i = 1; i < ids.size(); i++) {
						sb.append(",?");
					}
					sb.append(")");
					deleteSql += sb;
				}
				deletePstmt = conn.prepareStatement(deleteSql);
				deletePstmt.setString(1, entry.getId());
				index = 2;
				for (String id : ids) {
					deletePstmt.setString(index++, id);
				}
				deletePstmt.executeUpdate();
				deletePstmt.close();

			}
			conn.commit();
			checkEntryPstmt.close();
			insertEntryPstmt.close();
			updateEntryPstmt.close();
			checkMetaPstmt.close();
			insertMetaPstmt.close();
			updateMetaPstmt.close();
			checkParamPstmt.close();
			insertParamPstmt.close();
			updateParamPstmt.close();
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

	private List<Entry> load(boolean valid) {
		if (config == null || config.getDataSource() == null) {
			return new ArrayList<Entry>();
		}
		String entrySql = valid
				? "select ID_, NAME_, SQL_, SIZE_, DESCRIPTION_, VALID_, EXCEL_ from QUERY_ENTRY where VALID_=?"
				: "select ID_, NAME_, SQL_ SIZE_,, DESCRIPTION_, VALID_, EXCEL_ from QUERY_ENTRY";
		String metaSql = "select ID_, ENTRY_, EXPR_, LABEL_, ORDERABLE_ from QUERY_META where ENTRY_=?";
		String paramSql = "select ID_, ENTRY_, COLUMN_, LABEL_, AREA_, TYPE_, OPTIONS_, SQLOPTION_ from QUERY_PARAM where ENTRY_=?";
		Connection conn = null;
		PreparedStatement entryPstmt = null;
		PreparedStatement metaPstmt = null;
		PreparedStatement paramPstmt = null;
		List<Entry> entries = new ArrayList<Entry>();
		Entry entry;
		List<Meta> metas;
		Meta[] metaArr;
		Meta meta;
		List<Param> params;
		Param[] paramArr;
		Param param;
		String id;
		try {
			conn = config.getDataSource().getConnection();
			entryPstmt = conn.prepareStatement(entrySql);
			if (valid) {
				entryPstmt.setBoolean(1, true);
			}
			metaPstmt = conn.prepareStatement(metaSql);
			paramPstmt = conn.prepareStatement(paramSql);
			ResultSet entryRs = entryPstmt.executeQuery();
			ResultSet metaRs, paramRs;
			while (entryRs.next()) {
				metas = new ArrayList<Meta>();
				params = new ArrayList<Param>();
				id = entryRs.getString(1);

				metaPstmt.setString(1, id);
				metaRs = metaPstmt.executeQuery();
				metas = new ArrayList<Meta>();
				while (metaRs.next()) {
					meta = new Meta(metaRs.getString(1), metaRs.getString(3),
							metaRs.getString(4), metaRs.getBoolean(5));
					metas.add(meta);
				}
				metaRs.close();

				paramPstmt.setString(1, id);
				paramRs = paramPstmt.executeQuery();
				params = new ArrayList<Param>();
				while (paramRs.next()) {
					param = new Param(paramRs.getString(1),
							paramRs.getString(3), paramRs.getString(4),
							paramRs.getString(5), paramRs.getString(6),
							Option.parse(paramRs.getString(7)),
							paramRs.getString(8));
					params.add(param);
				}
				paramRs.close();

				if (metas.isEmpty()) {
					continue;
				}
				metaArr = new Meta[metas.size()];
				paramArr = new Param[params.size()];
				entry = new Entry(id, entryRs.getString(2),
						entryRs.getString(3), entryRs.getInt(4),
						metas.toArray(metaArr), params.toArray(paramArr));
				entry.setDescription(entryRs.getString(5));
				entry.setValid(entryRs.getBoolean(6));
				entry.setExcel(entryRs.getBoolean(7));
				entries.add(entry);
			}
			entryRs.close();

			entryPstmt.close();
			metaPstmt.close();
			paramPstmt.close();
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
		return entries;
	}

	@Override
	public List<Entry> loadAll() {
		return load(false);
	}

	@Override
	public List<Entry> loadValid() {
		return load(true);
	}

	@Override
	public void deploy(String xmlFile) {
		List<Entry> es = getParser().parse(xmlFile);
		if (es != null && !es.isEmpty()) {
			this.persist(es);
			List<Entry> entries = this.loadValid();
			config.entries().clear();
			for (Entry entry : entries) {
				config.entries().put(entry.getId(), entry);
			}
		}
	}

	public QueryConfig getConfig() {
		return config;
	}

	public void setConfig(QueryConfig config) {
		this.config = config;
	}

	public QueryParser getParser() {
		if (parser == null) {
			return new DefaultQueryParser();
		}
		return parser;
	}

	public void setParser(QueryParser parser) {
		this.parser = parser;
	}
}
