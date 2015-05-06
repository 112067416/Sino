package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.ISeqDAO;
import com.coco.sys.orm.Seq;
import com.coco.sys.orm.SeqRule;

public class SeqDAO implements ISeqDAO {

	private DAO dao;

	@Override
	public void delete(Serializable id) {
		dao.delete(Seq.class, id);
	}

	@Override
	public Seq get(Serializable id) {
		return dao.get(Seq.class, id);
	}

	@Override
	public void query(QEntity<Seq> qentity) {
		dao.query(qentity);
	}

	@Override
	public void save(Seq entity) {
		dao.save(entity);
	}

	public void update(Seq entity) {
		Seq ref = dao.getRef(Seq.class, entity.getId());
		if (ref != null) {
			ref.setDescription(entity.getDescription());
			ref.setValue(entity.getValue());
			ref.setScale(entity.getScale());
			ref.setStep(entity.getStep());
			ref.setValid(entity.isValid());
			ref.setNumber(entity.getNumber());
		}
	}

	@Override
	public void saveRules(Seq code, List<SeqRule> rules) {
		List<SeqRule> deletes = dao.find(SeqRule.class, "seq.id=?", code.getId());
		List<SeqRule> adds = new ArrayList<SeqRule>();
		List<SeqRule> updates = new ArrayList<SeqRule>();
		if (rules != null) {
			Iterator<SeqRule> it;
			for (SeqRule rule : rules) {
				if (rule.getType() == null || rule.getType().isEmpty()) {
					continue;
				}
				rule.setSeq(code);
				if (rule.getId() == null || rule.getId().isEmpty()) {
					rule.setId(null);
					adds.add(rule);
				}
				else {
					it = deletes.iterator();
					boolean isUpdate = false;
					while (it.hasNext()) {
						if (it.next().getId().equals(rule.getId())) {
							updates.add(rule);
							it.remove();
							isUpdate = true;
							break;
						}
					}
					if (!isUpdate) {
						rule.setId(null);
						adds.add(rule);
					}
				}
			}
		}
		if (!deletes.isEmpty()) {
			for (SeqRule rule : deletes) {
				dao.delete(rule);
			}
		}
		if (!adds.isEmpty()) {
			for (SeqRule rule : adds) {
				dao.save(rule);
			}
		}
		if (!updates.isEmpty()) {
			for (SeqRule rule : updates) {
				dao.update(rule);
			}
		}
	}

	@Override
	public List<SeqRule> findRules(String id) {
		return dao.find(SeqRule.class, "seq.id=? order by order", id);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
