package com.quanta.sino.cmn;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.quanta.sino.cmn.bo.api.IScxbBO;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.vo.ScxbpzFormVO;
import com.quanta.sino.cmn.vo.ScxbpzQE;
import com.quanta.sino.orm.Scxbpz;

public class ScxbTestCase {

	private static IScxbBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(IScxbBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void queryEtl() {
		ScxbpzQE qentity = new ScxbpzQE();
		qentity.setType(ProductType.etl.code);
		bo.query(qentity);
		for (Scxbpz entity : qentity.getDatas()) {
			System.out.println(entity.getName());
		}
	}

	@Test
	public void querySl() {
		ScxbpzQE qentity = new ScxbpzQE();
		qentity.setType(ProductType.sl.code);
		bo.query(qentity);
		for (Scxbpz entity : qentity.getDatas()) {
			System.out.println(entity.getName());
		}
	}

	@Test
	public void saveEtl() {
		ScxbpzFormVO vo = new ScxbpzFormVO();
		Scxbpz[] entities = new Scxbpz[2];
		Scxbpz entity = null;

		entity = new Scxbpz();
		entity.setDept("1001");
		entity.setCode("A");
		entity.setName("镀锡一线");

		entities[0] = entity;

		entity = new Scxbpz();
		entity.setDept("1002");
		entity.setCode("B");
		entity.setName("镀锡二线");

		entities[1] = entity;

		vo.setScxbs(entities);
		vo.setType(ProductType.etl.code);
		bo.save(vo);
	}

	@Test
	public void saveSl() {
		ScxbpzFormVO vo = new ScxbpzFormVO();

		Scxbpz[] entities = new Scxbpz[2];
		Scxbpz entity = null;

		entity = new Scxbpz();
		entity.setDept("1003");
		entity.setCode("W");
		entity.setName("剪切一线");
		entity.setQualified("Y");
		entity.setUnqualified("N");

		entities[0] = entity;

		entity = new Scxbpz();
		entity.setDept("1004");
		entity.setCode("V");
		entity.setName("剪切二线");
		entity.setQualified("T");
		entity.setUnqualified("F");

		entities[1] = entity;

		vo.setScxbs(entities);
		vo.setType(ProductType.etl.code);
		bo.save(vo);
	}

	@Test
	public void delete() {
		bo.delete("402881422d595671012d5956888d0002");
	}

}
