package com.coco.sys.bo;

import java.io.Serializable;

import org.dom4j.Document;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IPersonBO;
import com.coco.sys.dao.api.IPersonDAO;
import com.coco.sys.orm.Person;

public class PersonBO implements IPersonBO {

	private IPersonDAO dao;

	@Override
	public void saveOrUpdate(Person entity) {
		if (entity.getId() != null && entity.getId().isEmpty()) {
			entity.setId(null);
		}
		if (entity.getId() == null) {
			dao.save(entity);
			return;
		}
		Person person = this.get(entity.getId());
		if (person == null) {
			entity.setId(null);
			dao.save(entity);
		}
		else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<Person> qentity) {
		dao.query(qentity);
		// 装载机构名称
		if (qentity.getDatas() != null) {
			for (Person person : qentity.getDatas()) {
				person.getDept().getName();
			}
		}
	}

	@Override
	public Person get(Serializable id) {
		Person person = dao.get(id);
		person.getDept().getName();
		return person;
	}

	@Override
	public String getForJs(Serializable id) {
		return new Result(this.get(id)).toJsObject();
	}

	public IPersonDAO getDao() {
		return dao;
	}

	public void setDao(IPersonDAO dao) {
		this.dao = dao;
	}

	@Override
	public Document tree(boolean onlyValid) {
		return dao.tree(onlyValid);
	}

	@Override
	public boolean isExistNo(String no) {
		return dao.isExistNo(no);
	}

	@Override
	public Person getUniqueByNo(String no) {
		return dao.getUniqueByNo(no);
	}

}
