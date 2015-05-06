package com.coco.sys.bo.api;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;

import com.coco.sys.orm.Dept;

public interface IDeptBO {

	public void saveOrUpdate(Dept entity);

	public List<Dept> findAll();

	public List<Dept> findValid();

	public Document tree(boolean onlyValid);

	public Dept get(Serializable id);

	public String getForJs(Serializable id);

	public List<Dept> findExclude(Serializable id);

	public void delete(Serializable id);
}
