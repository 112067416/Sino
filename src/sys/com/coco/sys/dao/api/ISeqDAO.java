package com.coco.sys.dao.api;

import java.io.Serializable;
import java.util.List;

import com.coco.core.persistence.query.QEntity;
import com.coco.sys.orm.Seq;
import com.coco.sys.orm.SeqRule;

public interface ISeqDAO {

	public void save(Seq entity);

	public void update(Seq entity);

	public void saveRules(Seq code, List<SeqRule> rules);

	public void delete(Serializable id);

	public Seq get(Serializable id);

	public void query(QEntity<Seq> qentity);

	public List<SeqRule> findRules(String id);
}
