package com.coco.sys.converter.api;

import java.io.InputStream;

import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.vo.ConvertVO;

/**
 * <p>
 * 转换器
 * </p>
 * <p>
 * create: 2010-12-31 上午10:13:53
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface IConverter {

	/**
	 * <p>
	 * 转换操作
	 * </p>
	 * 
	 * @param <C>
	 * @param clazz
	 *            指定转化的对象类别
	 * @param entity
	 *            配置的实体
	 * @param is
	 *            输入流
	 * @param callback
	 *            回调函数
	 * @return ConvertVO 返回转换结果
	 */
	public <C> ConvertVO<C> convert(Class<C> clazz, ConvertEntity entity,
			InputStream is, IConverterCallback<C> callback);

}
