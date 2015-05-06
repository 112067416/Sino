package com.coco.query.api;

import java.util.List;

import com.coco.query.bean.Entry;

public interface QueryRepository {

	public void deploy(String xmlFile);

	public void persist(List<Entry> entries);

	public List<Entry> loadAll();

	public List<Entry> loadValid();
}
