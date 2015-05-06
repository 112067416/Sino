package com.coco.core.env;

import java.util.Collection;
import java.util.logging.Level;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.coco.core.persistence.api.DAO;

/**
 * <p>
 * 系统容器对象获取器
 * </p>
 * <p>
 * create time : 2010-2-10 下午01:31:17
 * </p>
 * 
 * @author 许德建【xudejian@126.com】
 */
public class Helper {

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Helper.class.getName());

	/**
	 * <p>
	 * Spring容器上下文
	 * </p>
	 */
	public static final ApplicationContext applicationContext;

	/**
	 * Servlet容器上下文
	 */
	public static final ServletContext SERVLET_CONTEXT;

	/**
	 * 系统绝对路径
	 */
	public static final String REAL_PATH;

	/**
	 * 默认数据访问接口，注意此接口尽量不要使用对数据库影响的方法
	 */
	public static final DAO DAO;

	static {
		ApplicationContext tempFactory = null;
		String realPath = null;
		ServletContext servletContext = null;
		// 读取web配置
		try {
			tempFactory = ContextLoader.getCurrentWebApplicationContext();
			servletContext = ((WebApplicationContext) tempFactory).getServletContext();
			realPath = servletContext.getRealPath("/");
		}
		catch (Exception e) {
		}
		if (tempFactory == null) {
			LOGGER.info("Helper Initializing : has not WebApplicationContext");
			String xmls = Config.getValue("applicationContext");
			if (xmls != null && (xmls = xmls.trim()).length() > 0) {
				tempFactory = new ClassPathXmlApplicationContext(
						xmls.split(","), true);
			}
			else {
				LOGGER.info("Helper Initializing : has not ApplicationContext config");
				tempFactory = new ClassPathXmlApplicationContext(
						new String[] { "applicationContext.xml" });
			}
		}
		applicationContext = tempFactory;
		REAL_PATH = realPath == null ? "" : realPath;
		SERVLET_CONTEXT = servletContext;
		DAO = getBean(DAO.class);
	}

	/**
	 * <p>
	 * 获取Spring配置的对象
	 * </p>
	 * 
	 * @param id
	 * @return Object
	 */
	public static Object getBean(String id) {
		if (id == null) {
			return null;
		}
		try {
			return applicationContext.getBean(id);
		}
		catch (Exception e) {
			LOGGER.log(Level.FINE, null, e);
			return null;
		}
	}

	/**
	 * <p>
	 * 根据类名获取Spring定义的Bean
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		if (applicationContext == null) {
			return null;
		}
		String[] names = applicationContext.getBeanNamesForType(clazz);
		if (names.length > 0) {
			return (T) applicationContext.getBean(names[0]);
		}
		return null;
	};

	/**
	 * <p>
	 * 根据类名获取Spring定义的Bean列表
	 * </p>
	 * 
	 * @param <T>
	 * @param clazz
	 * @return Collection<T>
	 */
	public static <T> Collection<T> listBeans(Class<T> clazz) {
		if (applicationContext == null) {
			return null;
		}
		return applicationContext.getBeansOfType(clazz).values();
	}

}
