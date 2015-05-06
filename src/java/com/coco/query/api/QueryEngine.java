package com.coco.query.api;

import java.util.List;

import com.coco.query.bean.Option;
import com.coco.query.bean.Page;

public interface QueryEngine {

	public void query(Page page);

	public List<Option> queryOptions(String sql);

}
