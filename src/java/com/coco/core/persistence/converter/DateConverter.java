package com.coco.core.persistence.converter;

import java.util.Calendar;
import java.util.Date;

import com.coco.core.persistence.a.QF;

/**
 * <p>
 * 时间转换器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:52:47
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class DateConverter implements Converter<Date> {

	@Override
	public Date convert(Date o, QF x) {
		if (o == null) {
			return null;
		}
		if (x == null
				|| (x.addDays() == 0 && x.addMonths() == 0 && x.addYears() == 0)) {
			return o;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(o);
		if (x.addDays() != 0) {
			cal.add(Calendar.DAY_OF_MONTH, x.addDays());
		}
		if (x.addMonths() != 0) {
			cal.add(Calendar.MONTH, x.addMonths());
		}
		if (x.addYears() != 0) {
			cal.add(Calendar.YEAR, x.addYears());
		}
		return cal.getTime();
	}

}
