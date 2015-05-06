package com.coco.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.DAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.dao.api.IPersonDAO;
import com.coco.sys.orm.Dept;
import com.coco.sys.orm.Person;

public class PersonDAO implements IPersonDAO {

	private DAO dao;

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void save(Person entity) {
		// 不允许有效用户中工号一样
		// 判断工号是否存在
		Person person1 = (Person) dao.getUnique("from Person where no=? and valid=?", entity.getNo(), true);
		if (person1 != null) {
			throw new CocoException(-10003, "工号已经在使用中");
		}
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(Person.class, id);
	}

	@Override
	public void query(QEntity<Person> qentity) {
		dao.query(qentity);
	}

	@Override
	public Person get(Serializable id) {
		return dao.get(Person.class, id);
	}

	@Override
	public void update(Person entity) {
		// 不允许有效用户中工号一样
		// 判断工号是否存在
		Person person1 = (Person) dao.getUnique("from Person where id<>? and no=? and valid=?", entity.getId(), entity.getNo(), true);
		if (person1 != null) {
			throw new CocoException(-10003, "工号已经在使用中");
		}
		dao.update(entity);
	}

	@Override
	public Document tree(boolean onlyValid) {
		List<Dept> depts = onlyValid ? dao.find(Dept.class, "valid=?", true)
				: dao.findAll(Dept.class);
		List<Person> persons = onlyValid ? dao.find(Person.class, "valid=?", true)
				: dao.findAll(Person.class);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("root");
		if (depts != null) {
			Element item;
			for (Dept dept : depts) {
				item = root.addElement("item");
				item.addAttribute("id", "d_" + dept.getId());
				if ("0".equals(dept.getParent())) {
					item.addAttribute("pid", "0");
				}
				else {
					item.addAttribute("pid", "d_" + dept.getParent());
				}
				item.addAttribute("label", dept.getName());
				if (dept.getOrder() != null) {
					item.addAttribute("order", "0" + dept.getOrder());
				}
				else {
					item.addAttribute("order", "0");
				}
				item.addAttribute("leaf", "0");
			}
		}
		if (persons != null) {
			Element item;
			for (Person person : persons) {
				item = root.addElement("item");
				item.addAttribute("id", person.getId());
				item.addAttribute("pid", "d_" + person.getDept().getId());
				item.addAttribute("label", person.getName());
				if (person.getOrder() != null) {
					item.addAttribute("order", "1" + person.getOrder());
				}
				else {
					item.addAttribute("order", "1");
				}
				item.addAttribute("leaf", "1");
			}
		}
		return doc;
	}

	@Override
	public boolean isExistNo(String no) {
		return dao.isExisted("from Person where no=? and valid=?", no, true);
	}

	@Override
	public Person getUniqueByNo(String no) {
		return (Person) dao.getUnique("from Person where no=? and valid=?", no, true);
	}
}
