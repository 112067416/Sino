package com.coco.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * <p>
 * 数值操作工具
 * </p>
 * <p>
 * create: 2011-1-14 上午11:12:41
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class NumberUtils {

	/**
	 * <p>
	 * 格式化数值
	 * </p>
	 * 
	 * @param num
	 * @return Integer
	 */
	public Integer format(Float num) {
		return Math.round(num.floatValue());
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @return BigDecimal
	 */
	public static BigDecimal format(BigDecimal num) {
		return format(num, 2);
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return BigDecimal
	 */
	public static BigDecimal format(BigDecimal num, int scale) {
		if (num == null) {
			return null;
		}
		if (scale < 0) {
			scale = 0;
		}
		return num.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @return Double
	 */
	public static Double format(double num) {
		return format(new Double(num));
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return Double
	 */
	public static Double format(double num, int scale) {
		return format(new Double(num), scale);
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @return Double
	 */
	public static Double format(Double num) {
		return format(num, 2);
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return Double
	 */
	public static Double format(Double num, int scale) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @param Roundingmode
	 *            0为四舍五入，1为不四舍五入
	 * @return Double
	 */
	public static Double format(Double num, int scale, int Roundingmode) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, Roundingmode).doubleValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// Roundingmode).doubleValue();
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return Integer
	 */
	public static Integer formatDouToInt(Double num, int scale) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).intValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// BigDecimal.ROUND_UP).intValue();
	}

	/**
	 * <p>
	 * 格式化数值
	 * </p>
	 * 
	 * @param num
	 * @param scale
	 * @param Roundingmode
	 *            0为四舍五入，1为不四舍五入
	 * @return
	 */
	public static Integer formatDouToInt(Double num, int scale, int Roundingmode) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, Roundingmode).intValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// Roundingmode).intValue();
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return Long
	 */
	public static Long formatDouToLong(Double num, int scale) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).longValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// BigDecimal.ROUND_HALF_UP).longValue();
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return Float
	 */
	public static Float format(Float num, int scale) {
		if (num == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(Double.toString(num));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
		// return new BigDecimal(num.doubleValue()).setScale(scale,
		// BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 格式化数值
	 * 
	 * @param num
	 * @param scale
	 * @return String
	 */
	public static String formatNumber(Object num, int scale) {
		if (num == null) {
			return String.valueOf(format(new BigDecimal(0.0d), scale));
		}
		try {
			return String.valueOf(format(new BigDecimal(String.valueOf(num)), scale));
		}
		catch (Exception ex) {
		}
		if (scale <= 0) {
			return "0";
		}
		StringBuffer numBuf = new StringBuffer();
		numBuf.append("0.");
		while (scale-- > 0) {
			numBuf.append('0');
		}
		return numBuf.toString();
	}

	/**
	 * <p>
	 * 格式化数值
	 * </p>
	 * 
	 * @param number
	 * @param format
	 * @return String
	 */
	public static String formatNumber(Number number, String format) {
		String numStr = number == null ? "0" : number.toString();
		int scale = -1;
		boolean isPercent = false;
		StringBuilder sb = new StringBuilder();
		if (format != null && format.length() > 0) {
			if (Pattern.matches("%[0-9]{2}d", format)) {
				return String.format(format, number);
			}
			if (Pattern.matches("#+[,|.]#+[0]?[.]?0*", format)) {
				return NumberUtils.decimalFormat(number, format);
			}
			if (format.indexOf('%') != -1) {
				double num = Double.parseDouble(numStr);
				num *= 100;
				numStr = num + "";
				format = format.replace("%", "");
				isPercent = true;
			}
			try {
				scale = Integer.parseInt(format);
			}
			catch (NumberFormatException ex) {
			}
		}
		if (scale < 0) {
			if (numStr.indexOf('.') != -1) {
				sb.append(numStr.replaceAll("(\\.|)0+$", ""));
			}
			else {
				sb.append(numStr);
			}
		}
		else {
			BigDecimal num = new BigDecimal(numStr);
			// sb.append(num.setScale(scale,
			// BigDecimal.ROUND_HALF_UP).toString());
			BigDecimal one = new BigDecimal("1");
			sb.append(num.divide(one, scale, BigDecimal.ROUND_HALF_UP));
		}
		if (isPercent) {
			sb.append("%");
		}
		return sb.toString();
	}

	/**
	 * 格式化数值，带有逗号
	 * 
	 * @param num
	 * @param scale
	 * @return String
	 */
	public static String formatNumberComma(Object number, int scale) {
		String value = formatNumber(number, scale);
		int dot = value.indexOf('.');
		String iv = dot != -1 ? value.substring(0, dot) : value;
		String pv = dot != -1 ? value.substring(dot + 1) : null;
		int commas = iv.length() / 3;
		int f = iv.length() % 3;
		if (f == 0) {
			commas -= 1;
		}
		if (commas == 0) {
			return value;
		}
		StringBuilder sb = new StringBuilder();
		if (f == 0) {
			f = 3;
		}
		sb.append(iv.substring(0, f));
		int begin = f;
		for (int i = 0; i < commas; i++) {
			sb.append(",");
			sb.append(iv.substring(begin, begin + 3));
			begin += 3;
		}
		if (pv != null) {
			sb.append(".").append(pv);
		}
		return sb.toString();
	}

	/**
	 * 格式化数值，带有逗号
	 * 
	 * @param num
	 * @param format
	 * @return String
	 */
	public static String formatNumberComma(Number number, String format) {
		String value = formatNumber(number, format);
		int dot = value.indexOf('.');
		String iv = dot != -1 ? value.substring(0, dot) : value;
		String pv = dot != -1 ? value.substring(dot + 1) : null;
		int commas = iv.length() / 3;
		int f = iv.length() % 3;
		if (f == 0) {
			commas -= 1;
		}
		if (commas == 0) {
			return value;
		}
		StringBuilder sb = new StringBuilder();
		if (f == 0) {
			f = 3;
		}
		sb.append(iv.substring(0, f));
		int begin = f;
		for (int i = 0; i < commas; i++) {
			sb.append(",");
			sb.append(iv.substring(begin, begin + 3));
			begin += 3;
		}
		if (pv != null) {
			sb.append(".").append(pv);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 求和，若元素为null则认为0来计算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static int sum(Integer... values) {
		if (values == null || values.length == 0) {
			return 0;
		}
		int sum = 0;
		for (Integer value : values) {
			if (value != null) {
				sum += value;
			}
		}
		return sum;
	}

	/**
	 * <p>
	 * 求和，若元素为null则认为0来计算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static long sum(Long... values) {
		if (values == null || values.length == 0) {
			return 0;
		}
		long sum = 0;
		for (Long value : values) {
			if (value != null) {
				sum += value;
			}
		}
		return sum;
	}

	/**
	 * <p>
	 * 求和，若元素为null则认为0来计算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static double sum(Double... values) {
		if (values == null || values.length == 0) {
			return 0.0;
		}
		double sum = 0.0;
		for (Double value : values) {
			if (value != null) {
				sum += value;
			}
		}
		return sum;
	}

	/**
	 * <p>
	 * 求平均值，若元素为null则认为0来计算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static double avg(Double... values) {
		if (values == null || values.length == 0) {
			return 0.0;
		}
		double sum = 0.0;
		for (Double value : values) {
			if (value != null) {
				sum += value;
			}
		}
		return sum / values.length;
	}

	/**
	 * <p>
	 * 求平均值，若元素为null则不计运算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static double avgNotNull(Double... values) {
		if (values == null || values.length == 0) {
			return 0.0;
		}
		double sum = 0.0;
		int length = 0;
		for (Double value : values) {
			if (value != null) {
				sum += value;
				length++;
			}
		}
		if (length == 0) {
			return 0.0;
		}
		return sum / length;
	}

	/**
	 * <p>
	 * 求平均值，若元素为null或者0则不计运算
	 * </p>
	 * 
	 * @param values
	 * @return double
	 */
	public static double avgNotZero(Double... values) {
		if (values == null || values.length == 0) {
			return 0.0;
		}
		double sum = 0.0;
		int length = 0;
		for (Double value : values) {
			if (value != null && value != 0) {
				sum += value;
				length++;
			}
		}
		if (length == 0) {
			return 0.0;
		}
		return sum / length;
	}

	/**
	 * <p>
	 * 解析对象成Double
	 * </p>
	 * 
	 * @param number
	 * @return Double
	 */
	public static Double parseDouble(Number number) {
		if (number == null) {
			return null;
		}
		if (number instanceof Number) {
			return null;
		}
		return ((Number) number).doubleValue();
	}

	/**
	 * <p>
	 * 解析数值字符串为整数，去掉前缀字符，如N0123则为123，若不能转换则返回0
	 * </p>
	 * 
	 * @param value
	 * @return int
	 */
	public static int parseInt(String value) {
		if (value == null) {
			return 0;
		}
		value = value.replaceAll("^(\\D|0)+", "");
		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * <p>
	 * 将数值，用指定位表示（如：千分位）
	 * </p>
	 * <p>
	 * author:张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param number
	 * @param format
	 * @return String
	 */
	public static String decimalFormat(Number number, String format) {
		if (number == null) {
			return null;
		}
		if (format == null || format.isEmpty()) {
			return String.valueOf(number);
		}
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * <p>
	 * 相减
	 * </p>
	 * 
	 * @return
	 */
	public static BigDecimal subtract(Object... values) {
		BigDecimal bigDecimal = new BigDecimal(0);
		if (values.length == 0) {
			return bigDecimal;
		}
		Object obj;
		BigDecimal result = new BigDecimal(values[0].toString());
		for (int i = 1; i < values.length; i++) {
			obj = values[i];
			if (obj == null) continue;
			result = result.subtract(new BigDecimal(obj.toString()));
		}
		return result;
	}

	/**
	 * <p>
	 * 相加
	 * </p>
	 * 
	 * @return
	 */
	public static BigDecimal add(Object... values) {
		BigDecimal bigDecimal = new BigDecimal(0);
		if (values.length == 0) {
			return bigDecimal;
		}
		Object obj;
		for (int i = 0; i < values.length; i++) {
			obj = values[i];
			if (obj == null) continue;
			bigDecimal = bigDecimal.add(new BigDecimal(obj.toString()));
		}
		return bigDecimal;
	}

	/**
	 * <p>
	 * 相乘
	 * </p>
	 * 
	 * @return
	 */
	public static BigDecimal multiply(Object... values) {
		BigDecimal bigDecimal = new BigDecimal(0);
		if (values.length == 0) {
			return bigDecimal;
		}
		Object obj;
		BigDecimal result = new BigDecimal(values[0].toString());
		for (int i = 1; i < values.length; i++) {
			obj = values[i];
			if (obj == null) continue;
			result = result.multiply(new BigDecimal(obj.toString()));
		}
		return result;
	}
}
