package com.coco.core.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * <p>
 * excel消息转换器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:45:44
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ExcelHttpMessageConverter implements
		HttpMessageConverter<HSSFWorkbook> {

	private static final List<MediaType> MEDIA_TYPES = new ArrayList<MediaType>();

	static {
		MEDIA_TYPES.add(MediaType.ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#canRead(java.
	 * lang.Class, org.springframework.http.MediaType)
	 */
	@Override
	public boolean canRead(Class<?> clazz, MediaType type) {
		return HSSFWorkbook.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#canWrite(java
	 * .lang.Class, org.springframework.http.MediaType)
	 */
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return HSSFWorkbook.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#
	 * getSupportedMediaTypes()
	 */
	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return MEDIA_TYPES;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#read(java.lang
	 * .Class, org.springframework.http.HttpInputMessage)
	 */
	@Override
	public HSSFWorkbook read(Class<? extends HSSFWorkbook> clazz,
			HttpInputMessage him) throws IOException,
			HttpMessageNotReadableException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#write(java.lang
	 * .Object, org.springframework.http.MediaType,
	 * org.springframework.http.HttpOutputMessage)
	 */
	@Override
	public void write(HSSFWorkbook book, MediaType type, HttpOutputMessage hom)
			throws IOException, HttpMessageNotWritableException {
		if (book != null) {
			hom.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
			book.write(hom.getBody());
			hom.getBody();
		}
	}

}
