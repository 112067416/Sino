package com.coco.core.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.coco.core.persistence.a.ColumnDescription;
import com.coco.core.util.ReflectUtils.ClassType;

/**
 * <p>
 * Jpa工具类. 用法：
 * </p>
 * 
 * <pre style="border:1px solid #000000;">
 * List&lt;JpaUtils.Error&gt; errors = JpaUtils.validate(entity);
 * for (JpaUtils.Error error : errors) {
 * 	System.out.println(error.getMessage());
 * }
 * </pre>
 * <p>
 * create: 2011-1-12 上午11:13:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class JpaUtils {

	public static class Error {

		private String field;

		private String message;

		private Error(String field, String message) {
			this.field = field;
			this.message = message;
		}

		/**
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
	}

	public static String getColumnDescription(Field field) {
		if (field == null) {
			return null;
		}
		ColumnDescription cd = field.getAnnotation(ColumnDescription.class);
		if (cd == null) {
			return field.getName();
		}
		return cd.value();
	}

	public static List<Error> validate(Object entity) {
		if (entity == null) {
			return new ArrayList<Error>();
		}
		List<Error> errors = new ArrayList<Error>();
		List<Field> fields = ReflectUtils.fields(entity.getClass());

		Object value;
		Column c;
		ClassType ct;
		int length, scale, precision, iscale;
		String cd;
		List<String> sp;
		String str;
		for (Field field : fields) {
			c = field.getAnnotation(Column.class);
			if (c == null) {
				continue;
			}

			ct = ReflectUtils.classType(field.getType());
			value = ReflectUtils.fieldValue(entity, field);
			if (value == null) {
				if (!c.nullable()) {
					errors.add(new Error(field.getName(),
							getColumnDescription(field) + ":不能为空"));

				}
				continue;
			}

			if (ct == ClassType.number) {
				scale = 0;
				precision = 0;

				cd = c.columnDefinition();
				if (cd != null && !cd.isEmpty()) {
					sp = StringUtils.find(cd, "\\d+");
					if (sp.size() > 0) {
						try {
							scale = Integer.parseInt(sp.get(0));
						}
						catch (NumberFormatException e) {
						}
					}
					if (sp.size() > 1) {
						try {
							precision = Integer.parseInt(sp.get(1));
						}
						catch (NumberFormatException e) {
						}
					}
				}
				else {
					scale = c.scale();
					precision = c.precision();
				}
				if (scale < 0) {
					scale = 0;
				}
				if (precision == 0) {
					precision = 0;
				}
				if (scale > 0) {
					iscale = scale - precision;

					if (String.valueOf(((Number) value).longValue()).length() > iscale) {
						errors.add(new Error(field.getName(),
								getColumnDescription(field) + ":整数部分超过限定位数"
										+ (iscale)));
						continue;
					}
				}
				if (precision > 0) {
					if (value instanceof BigDecimal) {
						str = value.toString();
					}
					else if (value instanceof Long) {
						continue;
					}
					else {
						str = BigDecimal.valueOf((Double) value).toString();
					}
					int ci = str.indexOf('.');
					if (ci > 0 && str.length() - ci - 1 > precision) {
						errors.add(new Error(field.getName(),
								getColumnDescription(field) + ":小数部分超过限定位数"
										+ (precision)));
						continue;
					}
				}
			}
			else if (ct == ClassType.string) {
				length = 0;
				cd = c.columnDefinition();
				if (cd != null && !cd.isEmpty()) {
					sp = StringUtils.find(cd, "\\d+");
					if (sp.size() > 0) {
						try {
							length = Integer.parseInt(sp.get(0));
						}
						catch (NumberFormatException e) {
						}
					}
				}
				else {
					length = c.length();
				}
				if (length > 0 && ((String) value).getBytes().length > length) {
					errors.add(new Error(field.getName(),
							getColumnDescription(field) + ":超过限定位数" + length));
				}
			}
		}

		return errors;
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}

}
