package com.coco.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 时间操作工具类
 * </p>
 * <p>
 * create: 2011-1-20 下午04:57:31
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class DateUtils {

	/**
	 * 时间偏移量
	 */
	private static final int TIME_ZONE_OFFSET = Calendar.getInstance().getTimeZone().getRawOffset();

	/**
	 * 时间格式化：年月（yyyy-MM）
	 */
	private static final SimpleDateFormat SDF_YM = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * 时间格式化：年月日（yyyy-MM-dd）
	 */
	private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 时间格式化：年月日（yyyy-MM-dd）
	 */
	public static final String YMD = "yyyy-MM-dd";

	/**
	 * 时间格式化：年月日时分秒（yyyy-MM-dd HH:mm:ss）
	 */
	public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 */
	public static final String HH_MM = "HH:mm";

	/**
	 * 
	 */
	public static final String[] DAY_OF_WEEK = { "日", "一", "二", "三", "四", "五",
			"六" };

	/**
	 * <p>
	 * 解析时间串
	 * </p>
	 * 
	 * @param dateStr
	 * @param format
	 * @return Date
	 */
	public static Date parse(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * 解析时间串
	 * </p>
	 * 
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date parse(Date date, String format) {
		if (date == null) {
			date = new Date();
		}
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(sdf.format(date));
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <p>
	 * 格式化时间为字符串
	 * </p>
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		catch (Exception e) {
			return "";
		}
	}

	/**
	 * <p>
	 * 天数比较
	 * </p>
	 * 
	 * @param date1
	 * @param date2
	 * @return int 返回代码
	 *         <ul>
	 *         <li>1 : date1在date2之前</li>
	 *         <li>-1 :　date1在date2之后</li>
	 *         <li>0 : date1和date2同一天</li>
	 *         <li>-2 : date1或date2有一个为null</li>
	 *         </ul>
	 */
	public static int compareForDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return -2;
		}
		int offset = getDayOffset(date1, date2);
		if (offset > 0) {
			return 1;
		}
		if (offset < 0) {
			return -1;
		}
		return 0;
	}

	/**
	 * <p>
	 * 获取相差天数
	 * </p>
	 * 
	 * @param begin
	 *            起始日期
	 * @param end
	 *            终止日期
	 * @return int 天数
	 */
	public static int getDayOffset(Date begin, Date end) {
		if (begin == null || end == null) {
			return 0;
		}
		long day1 = (begin.getTime() + TIME_ZONE_OFFSET) / 86400000;
		long day2 = (end.getTime() + TIME_ZONE_OFFSET) / 86400000;
		return (int) (day2 - day1);
	}

	/**
	 * <p>
	 * 获取当日日期
	 * </p>
	 * 
	 * @return date
	 */
	public static Date getCurrentDay() {
		try {
			return SDF_YMD.parse(SDF_YMD.format(new Date()));
		}
		catch (ParseException e) {
			long tm = System.currentTimeMillis();
			tm = tm - (tm + TIME_ZONE_OFFSET) % 86400000;
			return new Date(tm);
		}
	}

	/**
	 * <p>
	 * 获取指定日期的第一天
	 * </p>
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * <p>
	 * 获取指定日期的最后一天
	 * </p>
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		Date $date = cal.getTime();

		cal.setTime($date);
		cal.add(Calendar.DAY_OF_MONTH, -1);

		return cal.getTime();
	}

	/**
	 * <p>
	 * 获取指定日期的下个月第一天
	 * </p>
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getLastDayOfNextMonth(Date date) {
		if (date == null) {
			return null;
		}
		String monthStr = SDF_YM.format(date);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(SDF_YMD.parse(monthStr + "-01"));
			cal.add(Calendar.MONTH, 1);
			return cal.getTime();
		}
		catch (ParseException e) {
		}
		return null;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return DAY_OF_WEEK[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		return weekday == 1 || weekday == 7 ? true : false;
	}

	/**
	 * <p>
	 * 修改时间
	 * </p>
	 * 
	 * @param date
	 *            需要修改的时间
	 * @param type
	 *            时间类型
	 * @param offset
	 *            偏移量
	 * @return Date
	 */
	public static Date add(Date date, int type, int offset) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(type, offset);
		return cal.getTime();
	}

	/**
	 * <p>
	 * 修改时间
	 * </p>
	 * 
	 * @param date
	 * @param type
	 * @param offset
	 * @return Date
	 */
	public static Date add(long time, int type, int offset) {
		Date date = new Date(time);
		return add(date, type, offset);
	}

	/**
	 * <p>
	 * 设置日期的时间和分钟
	 * </p>
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @return Date
	 */
	public static Date formatDate(Date date, int hour, int minute) {
		Date rq = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			rq = sdf.parse(sdf.format(date));
			c.setTime(rq);
			c.add(Calendar.HOUR_OF_DAY, hour);
			c.add(Calendar.MINUTE, minute);
			rq = c.getTime();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return rq;
	}

	/**
	 * <p>
	 * 设置日期的时间、分钟和钞
	 * </p>
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return Date
	 */
	public static Date formatDate(Date date, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.getTime().getTime();
		return c.getTime();
	}

	/**
	 * <p>
	 * 将毫钞转化为分钟
	 * </p>
	 * 
	 * @param milliseconds
	 * @return long
	 */
	public static long millisecondsTominute(long milliseconds) {
		if (milliseconds == 0) return 0l;
		return milliseconds / (1000 * 60);
	}

	/**
	 * <p>
	 * 将毫钞转化为分钟
	 * </p>
	 * 
	 * @param milliseconds
	 * @return long
	 */
	public static long millisecondsToday(long milliseconds) {
		if (milliseconds == 0) return 0;
		return (long) milliseconds / (1000 * 60 * 60 * 24);
	}

	/**
	 * <p>
	 * 将分钟转化为小时
	 * </p>
	 * 
	 * @param minute
	 * @return double
	 */
	public static double minuterToHour(long minute) {
		if (minute == 0) return 0d;
		return NumberUtils.format(minute / 60d, 2);
	}

	/**
	 * <p>
	 * 将分钟转化为小时
	 * </p>
	 * 
	 * @param minute
	 * @return double
	 */
	public static double minuterToHour(Double minute) {
		if (minute == null || minute.doubleValue() == 0) return 0d;
		return NumberUtils.format(minute / 60d, 2);
	}
}
