package com.coco.core.persistence.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 数据库字段扩展属性-注释
 * </p>
 * <p>
 * create: 2010-12-21 上午09:48:24
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnDescription {

	/**
	 * <p>
	 * 注释值
	 * </p>
	 * 
	 * @return String
	 */
	public String value() default "";

}
