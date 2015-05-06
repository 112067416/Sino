package com.coco.query.api;

import java.util.Map;

import javax.sql.DataSource;

import com.coco.query.bean.Entry;

public interface QueryConfig {

	public String getSqlFile();

	public DataSource getDataSource();

	public QueryEngine createEngine();

	public QueryRepository createRepository();

	public ValueParser createValueParser();

	public Map<String, Entry> entries();

	public void setRepository(QueryRepository repository);

	public void setEngine(QueryEngine engine);

	public Entry getEntry(String id);

}
