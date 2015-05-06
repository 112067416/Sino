package com.coco.core.persistence.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.coco.core.persistence.e.EF;
import com.coco.core.persistence.e.EO;

/**
 * <p>
 * 数据库字段扩展属性
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
public @interface QF {

	/**
	 * <p>
	 * 字段类型
	 * </p>
	 * 
	 * @return EF
	 */
	public EF type() default EF.USE;

	/**
	 * <p>
	 * 字段别名
	 * </p>
	 * 
	 * @return String
	 */
	public String alias() default "";

	/**
	 * <p>
	 * 字段操作
	 * </p>
	 * 
	 * @return EO
	 */
	public EO operator() default EO.EQ;

	/**
	 * <p>
	 * 添加天数
	 * </p>
	 * 
	 * @return int
	 */
	public int addDays() default 0;

	/**
	 * <p>
	 * 添加月数
	 * </p>
	 * 
	 * @return int
	 */
	public int addMonths() default 0;

	/**
	 * <p>
	 * 添加年数
	 * </p>
	 * 
	 * @return int
	 */
	public int addYears() default 0;

	/**
	 * <p>
	 * 条件区域
	 * </p>
	 * 
	 * @return int
	 */
	public int area() default 1;
}
