package com.coco.core.persistence.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 数据库表实体关联
 * </p>
 * <p>
 * create: 2010-12-21 上午09:48:33
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QE {

	/**
	 * <p>
	 * 实体类
	 * </p>
	 * 
	 * @return Class<?>
	 */
	public Class<?> clazz();

	/**
	 * <p>
	 * 实体名
	 * </p>
	 * 
	 * @return String
	 */
	public String name() default "";

	/**
	 * <p>
	 * 别名
	 * </p>
	 * 
	 * @return String
	 */
	public String alias() default "";

	/**
	 * <p>
	 * 连接,Hibernate实现的join on必须在实体中声明其关系
	 * </p>
	 * 
	 * @return String
	 */
	public String joinOn() default "";

}
