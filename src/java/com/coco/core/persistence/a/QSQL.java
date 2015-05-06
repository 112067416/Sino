package com.coco.core.persistence.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 元素数据查询
 * </p>
 * <p>
 * create: 2011-1-24 下午04:16:34
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QSQL {

	/**
	 * <p>
	 * 查询体
	 * </p>
	 * 
	 * @return String
	 */
	public String sql() default "";

	/**
	 * <p>
	 * 查询元素
	 * </p>
	 * 
	 * @return String
	 */
	public String meta() default "*";

}
