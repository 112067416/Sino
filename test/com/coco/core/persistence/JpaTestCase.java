package com.coco.core.persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.core.persistence.api.DAO;
import com.coco.sys.orm.Person;

public class JpaTestCase {

	private static DAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = Helper.getBean(DAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findForValues() {
		List<String> values = new ArrayList<String>();
		values.add("1993");
		values.add("1994");
		values.add("1995");
		values.add("1999");
		List<String> params = new ArrayList<String>();
		params.add("1026");
		params.add("999");
		String ql = "from Person where dept.id=? and no=? and no=? and id in(?) and no=?";
		List<Person> persons = (List<Person>) dao.findForValues(ql, values, "1026", "999", "999", "999");
		for (Person person : persons) {
			System.out.println(person.getName());
		}
		persons = (List<Person>) dao.findForValues(ql, values, params, "999", "999");
		for (Person person : persons) {
			System.out.println(person.getName());
		}
		persons = (List<Person>) dao.findForValues(ql, values, params.toArray(), "999", "999");
		for (Person person : persons) {
			System.out.println(person.getName());
		}
	}

}
