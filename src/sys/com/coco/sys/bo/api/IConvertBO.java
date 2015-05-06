package com.coco.sys.bo.api;

import java.io.InputStream;
import java.io.Serializable;

import com.coco.core.api.IBaseBO;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.vo.ConvertVO;

public interface IConvertBO extends IBaseBO<ConvertEntity> {

	/**
	 * <p>
	 * 输入流转换成对象
	 * </p>
	 * 
	 * @param <C>
	 * @param id
	 *            配置Id
	 * @param clazz
	 *            指定转换成对象的类
	 * @param is
	 *            输入流
	 * @param callback
	 *            回调函数
	 * @return ConvertVO 返回转换结果
	 */
	public <C> ConvertVO<C> convert(Serializable id, Class<C> clazz,
			InputStream is, IConverterCallback<C> callback);
}
