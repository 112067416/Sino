package com.coco.query.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.coco.query.api.QueryConfig;
import com.coco.query.api.QueryEngine;
import com.coco.query.api.QueryRepository;
import com.coco.query.api.ValueParser;
import com.coco.query.bean.Entry;

public class DefaultQueryConfig implements QueryConfig {

	private String sqlFile;

	private DataSource dataSource;

	private QueryRepository repository;

	private ValueParser valueParser;

	private QueryEngine engine;

	private Map<String, Entry> entries = new HashMap<String, Entry>();

	@Override
	public QueryEngine createEngine() {
		if (engine == null) {
			engine = new DefaultQueryEngine(this);
		}
		return engine;
	}

	@Override
	public QueryRepository createRepository() {
		if (repository == null) {
			repository = new DefaultQueryRepository(this);
		}
		return repository;
	}

	@Override
	public ValueParser createValueParser() {
		if (valueParser == null) {
			valueParser = new DefaultValueParser();
		}
		return valueParser;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public String getSqlFile() {
		return sqlFile;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setSqlFile(String sqlFile) {
		this.sqlFile = sqlFile;
	}

	@Override
	public Map<String, Entry> entries() {
		if (entries.isEmpty()) {
			List<Entry> list = null;
			if (this.repository == null) {
				list = new DefaultQueryRepository(this).loadValid();
			} else {
				list = this.repository.loadValid();
			}
			if (list != null) {
				for (Entry entry : list) {
					entries.put(entry.getId(), entry);
				}
			}
		}
		return entries;
	}

	public QueryRepository getRepository() {
		return repository;
	}

	@Override
	public void setRepository(QueryRepository repository) {
		this.repository = repository;
	}

	public QueryEngine getEngine() {
		return engine;
	}

	@Override
	public void setEngine(QueryEngine engine) {
		this.engine = engine;
	}

	public ValueParser getValueParser() {
		return valueParser;
	}

	public void setValueParser(ValueParser valueParser) {
		this.valueParser = valueParser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coco.query.api.QueryConfig#getEntry(java.lang.String)
	 */
	@Override
	public Entry getEntry(String id) {
		return this.entries().get(id);
	}

}
