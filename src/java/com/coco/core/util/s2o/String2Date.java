package com.coco.core.util.s2o;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 字符串转换成时间 <div>只支持一下格式</div>
 * <ul>
 * <li>1. yyyy-MM-dd HH:mm:ss.SSS 如:2001-02-03 04:05:06.007</li>
 * <li>2. yyyy-MM-dd HH:mm:ss 如:2001-02-03 04:05:06</li>
 * <li>3. yyyy-MM-dd HH:mm 如:2001-02-03 04:05</li>
 * <li>4. yyyy-MM-dd HH 如:2001-02-03 04</li>
 * <li>5. yyyy-MM-dd 如:2001-02-03</li>
 * <li>6. yyyy-MM 如:2001-02</li>
 * </ul>
 * </p>
 * <p>
 * create: 2010-12-21 上午09:58:01
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class String2Date implements IString2Object<Date> {

	private final static String DATE_FORMAT[] = { "yyyy-MM-dd HH:mm:ss.SSS",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
			"yyyy-MM-dd", "yyyy-MM" };

	@Override
	public Date valueOf(String value) {
		if (value == null || (value = value.trim()).length() < 6) {
			return null;
		}
		value = value.replaceAll("\\s+", " ");
		Date date = null;
		SimpleDateFormat sdf = null;
		int first = 4;
		int len = value.length();
		if (len > 17) {
			first = 0;
		}
		else if (len > 15) {
			first = 1;
		}
		else if (len > 13) {
			first = 2;
		}
		else if (len > 10) {
			first = 3;
		}
		for (int i = first; i < DATE_FORMAT.length; i++) {
			sdf = new SimpleDateFormat(DATE_FORMAT[i]);
			try {
				date = sdf.parse(value);
				break;
			}
			catch (ParseException e) {
			}
		}
		return date;
	}

}
