package com.coco.core.persistence.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 数据库表实体
 * </p>
 * <p>
 * 2010-12-21 上午09:48:42
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Q {

	/**
	 * <p>
	 * SQL查询语句，格式如：
	 * <ul>
	 * <li>select field1_,field2_ from table where#1#</li>
	 * <li>select * from (select field1_,field2_ from table1_ where#1# union all
	 * select field1_,field2_ from table2_ where#1#) t</li>
	 * </ul>
	 * </p>
	 * 
	 * @return QSQL
	 */
	public QSQL value() default @QSQL();

	/**
	 * <p>
	 * 查询实体列表
	 * </p>
	 * 
	 * @return QE[]
	 */
	public QE[] entities() default {};

	/**
	 * <p>
	 * 关联表条件
	 * </p>
	 * 
	 * @return String
	 */
	public String where() default "";

	/**
	 * <p>
	 * 求和字段列表
	 * </p>
	 * 
	 * @return String[]
	 */
	public String[] sums() default {};

}
