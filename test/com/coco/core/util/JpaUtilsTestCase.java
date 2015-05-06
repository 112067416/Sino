package com.coco.core.util;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.quanta.sino.orm.YcaiTp;

/**
 * <p>
 * JPAUtils测试
 * </p>
 * <p>
 * create: 2011-1-12 下午12:06:13
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class JpaUtilsTestCase {

	@Test
	public void validate() {

		YcaiTp entity = new YcaiTp();
		entity.setChan("1");
		entity.setCang(859.09);
		entity.setJbcn(12099);
		entity.setCrea(new Date());
		List<JpaUtils.Error> errors = JpaUtils.validate(entity);
		for (JpaUtils.Error error : errors) {
			System.out.println(error.getField() + " : " + error.getMessage());
		}
		// WwhtTp wwhtTp = new WwhtTp();
		// wwhtTp.setHtno("0E76301-001");
		// wwhtTp.setZzsd("0");
		// wwhtTp.setFace("R1");
		// wwhtTp.setHouu(0.155);
		// wwhtTp.setKuan(925.5);
		// // wwhtTp.setHtzl(Double.valueOf(16));
		// wwhtTp.setHtdj(860.13);
		// wwhtTp.setZzgg("SPB-D-T1BA");
		// errors = JpaUtils.validate(wwhtTp);
		// System.out.println("size2=" + errors.size());
		// for (JpaUtils.Error error : errors) {
		// System.out.println(error.getField() + " : " + error.getMessage());
		// }
		// Double d = 234.98;
	}

}
