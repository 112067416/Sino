package com.coco.core.excel.api;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * <p>
 * Excel填充执行器
 * </p>
 * <p>
 * create: 2011-1-28 下午03:55:05
 * </p>
 * 
 * @param <T>
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ExcelDataExecuter<T> {

	/**
	 * <p>
	 * 数据填充执行
	 * </p>
	 * 
	 * @param sheet
	 * @param data
	 */
	public void execute(HSSFSheet sheet, T data);

}
