package com.coco.sys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.ICodeDAO;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;

public class CodeDAO implements ICodeDAO {

	private DAO dao;

	@Override
	public void delete(final Serializable id) {
		dao.delete(Code.class, id);
	}

	@Override
	public Code get(Serializable id) {
		return dao.get(Code.class, id);
	}

	@Override
	public Code getRef(Serializable id) {
		return dao.getRef(Code.class, id);
	}

	@Override
	public void query(QEntity<Code> qentity) {
		dao.query(qentity);
	}

	@Override
	public void save(Code entity) {
		dao.save(entity);
	}

	@Override
	public void update(Code entity) {
		dao.update(entity);
	}

	@Override
	public void upddateItem(CodeItem codeItem) {
		dao.update(codeItem);
	}

	@Override
	public void saveItems(Code code, List<CodeItem> items) {
		List<CodeItem> deletes = dao.find(CodeItem.class, "code.id=?", code.getId());
		Set<String> keys = new HashSet<String>();
		for (CodeItem ci : deletes) {
			keys.add(ci.getKey());
		}
		List<CodeItem> adds = new ArrayList<CodeItem>();
		List<CodeItem> updates = new ArrayList<CodeItem>();
		Set<String> newKeys = new HashSet<String>();
		if (items != null) {
			for (CodeItem item : items) {
				if (item.getKey() == null || item.getKey().isEmpty()) {
					continue;
				}
				newKeys.add(item.getKey());
				item.setCode(code);
				if (keys.contains(item.getKey())) {
					updates.add(item);
				}
				else {
					adds.add(item);
				}
			}
		}
		if (!deletes.isEmpty()) {
			for (CodeItem item : deletes) {
				if (!newKeys.contains(item.getKey())) {
					dao.delete(item);
				}
			}
		}
		if (!adds.isEmpty()) {
			for (CodeItem item : adds) {
				dao.save(item);
			}
		}
		if (!updates.isEmpty()) {
			for (CodeItem item : updates) {
				dao.update(item);
			}
		}
	}

	@Override
	public void saveItem(CodeItem codeItem) {
		dao.save(codeItem);
	}

	@Override
	public CodeItem getCodeItem(Serializable id) {
		return dao.get(CodeItem.class, id);
	}

	@Override
	public CodeItem getCodeItemByFudw(String code, String key, String fudw) {
		String ql = "  number =(select number from  CodeItem where key=? AND code.id=?) and key like ?";
		return dao.getUnique(CodeItem.class, ql, key, code, fudw + "%");
		// return dao.find(CodeItem.class, ql, key, code);
	}

	@Override
	public List<CodeItem> findItems(String code) {
		return dao.find(CodeItem.class, "code.id=? order by order", code);
	}

	@Override
	public List<CodeItem> findDxl(String code, String key) {
		return dao.find(CodeItem.class, "code.id=? and key like ? order by key asc", code, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeItem> findItems(List<String> code) {
		return (List<CodeItem>) dao.findForValues("from CodeItem where code.id in (?) and valid=? ", code, true);
	}

	@Override
	public List<CodeItem> findValidItems(String code) {
		return dao.find(CodeItem.class, "valid=? and code.id=? order by order", true, code);
	}

	@Override
	public boolean isExisted(String code, String key) {
		return dao.isExisted("from CodeItem where valid=? and code.id=? and key=?", true, code, key);
	}

	@Override
	public boolean isValueExisted(String code, String value) {
		return dao.isExisted("from CodeItem where valid=? and code.id=? and value=?", true, code, value);
	}

	@Override
	public List<CodeItem> findItems(String code, String value) {
		return dao.find(CodeItem.class, " valid=? and code.id=? and value=? order by order asc", true, code, value);
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
