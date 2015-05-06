package com.coco.core.persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coco.core.env.Helper;
import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 原生SQL访问接口测试
 * </p>
 * <p>
 * create: 2011-1-24 下午05:20:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class SqlTestCase {

	private static ISqlDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = Helper.getBean(ISqlDAO.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void query() {

		UnionQE qe = new UnionQE();
		qe.setDhno("D10723501");
		qe.setSize(10);
		dao.query(qe);
		System.out.println(qe.getCount() + " : " + qe.getPageCount());
		for (UnionVO vo : qe.getDatas()) {
			System.out.println(vo.getDhno() + "\t" + vo.getJbno());
		}
	}

	@Q(@QSQL(sql = "from (select JBNO_,DHNO_ from SINO_YCAITP where#1# union all select JBNO_,DHNO_ from SINO_ZPNGTP where#1#) t ", meta = "JBNO_,DHNO_"))
	public static class UnionQE extends QEntity<UnionVO> {

		@QF
		private String dhno;

		/**
		 * @return the dhno
		 */
		public String getDhno() {
			return dhno;
		}

		/**
		 * @param dhno
		 *            the dhno to set
		 */
		public void setDhno(String dhno) {
			this.dhno = dhno;
		}

	}

	public static class UnionVO {

		private String jbno;

		private String dhno;

		/**
		 * @return the jbno
		 */
		public String getJbno() {
			return jbno;
		}

		/**
		 * @param jbno
		 *            the jbno to set
		 */
		public void setJbno(String jbno) {
			this.jbno = jbno;
		}

		/**
		 * @return the dhno
		 */
		public String getDhno() {
			return dhno;
		}

		/**
		 * @param dhno
		 *            the dhno to set
		 */
		public void setDhno(String dhno) {
			this.dhno = dhno;
		}

	}

}
