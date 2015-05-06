package com.coco.sys;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.sys.bo.api.ISourceBO;
import com.coco.sys.orm.SourceClass;
import com.coco.sys.orm.SourceMethod;
import com.coco.sys.vo.SourceQE;
import com.coco.sys.web.SourceController;

public class SourceTestCase {

	private static ISourceBO bo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bo = Helper.getBean(ISourceBO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <p>
	 * 保存资源类
	 * </p>
	 */
	@Test
	public void saveClass() {
		SourceClass entity = new SourceClass();
		entity.setClassName(SourceController.class.getName());
		entity.setName("资源管理模块");
		bo.save(entity);
	}

	/**
	 * <p>
	 * 更新资源类
	 * </p>
	 * 
	 * @param entity
	 */
	@Test
	public void updateClass() {
		SourceClass entity = new SourceClass();
		entity.setClassName(SourceController.class.getName());
		entity.setName("资源管理模块");
		bo.update(entity);
	}

	/**
	 * <p>
	 * 保存资源方法
	 * </p>
	 */
	@Test
	public void saveMethod() {

		SourceClass entity = new SourceClass();
		entity.setClassName(SourceController.class.getName());

		SourceMethod method;
		method = new SourceMethod();
		method.setSourceClass(entity);
		method.setMethod("index");
		method.setName("主页");

		// method = new SourceMethod();
		// method.setSourceClass(entity);
		// method.setMethod("save");
		// method.setName("保存");

		bo.save(method);
	}

	/**
	 * <p>
	 * 更新资源方法
	 * </p>
	 * 
	 * @param entity
	 */
	@Test
	public void updateMethod() {

		SourceClass entity = new SourceClass();
		entity.setClassName(SourceController.class.getName());

		SourceMethod method;
		method = new SourceMethod();
		method.setSourceClass(entity);
		method.setMethod("index");
		method.setName("主页");
		// method = new SourceMethod();
		// method.setSourceClass(entity);
		// method.setMethod("save");
		// method.setName("保存");

		bo.update(method);
	}

	/**
	 * <p>
	 * 删除资源类，包含资源方法
	 * </p>
	 * 
	 * @param id
	 */
	@Test
	public void delete() {
		bo.delete(SourceController.class.getName());
	}

	/**
	 * <p>
	 * 删除资源方法
	 * </p>
	 * 
	 * @param id
	 */
	@Test
	public void deleteMethod() {
		SourceMethod sourceMethod = bo.getUniqueMethod(SourceController.class.getName(), "index");
		Assert.assertNotNull(sourceMethod);
		bo.deleteMethod(sourceMethod.getId());
	}

	/**
	 * <p>
	 * 获取资源类，包含资源方法
	 * </p>
	 * 
	 * @param id
	 * @return SourceClass
	 */
	@Test
	public void get() {
		SourceClass sc = bo.get(SourceController.class.getName());
		Assert.assertNotNull(sc);
	}

	/**
	 * <p>
	 * 获取所有资源信息
	 * </p>
	 * 
	 * @return List<SourceClass>
	 */
	@Test
	public void findAll() {
		Assert.assertTrue(bo.findAll().size() > 0);
	}

	/**
	 * <p>
	 * 分页查询资源信息
	 * </p>
	 * 
	 * @return List<SourceClass>
	 */
	@Test
	public void query() {
		SourceQE qe = new SourceQE();
		qe.setName("资源管理模块");
		bo.query(qe);
		Assert.assertTrue(!qe.getDatas().isEmpty());
	}

}
