package com.coco.sys.converter.api;

import com.coco.sys.vo.ConvertRowVO;

public interface IConverterCallback<T> {

	public void doRow(ConvertRowVO<T> row);

}
