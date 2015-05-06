package com.coco.core.mvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * <p>
 * XML文档消息转换器
 * </p>
 * <p>
 * create: 2010-12-21 上午09:44:25
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class DocumentHttpMessageConverter implements
		HttpMessageConverter<Document> {

	private static final List<MediaType> MEDIA_TYPES = new ArrayList<MediaType>();

	private static final MediaType DEFAULT_TYPE = new MediaType("text", "xml",
			Charset.forName("UTF-8"));

	static {
		MEDIA_TYPES.add(DEFAULT_TYPE);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#canRead(java.
	 * lang.Class, org.springframework.http.MediaType)
	 */
	@Override
	public boolean canRead(Class<?> clazz, MediaType type) {
		return Document.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.http.converter.HttpMessageConverter#canWrite(java
	 * .lang.Class, org.springframework.http.MediaType)
	 */
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return Document.class.isAssignableFrom(clazz);
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
	public Document read(Class<? extends Document> clazz, HttpInputMessage him)
			throws IOException, HttpMessageNotReadableException {
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
	public void write(Document document, MediaType type, HttpOutputMessage hom)
			throws IOException, HttpMessageNotWritableException {
		if (document != null) {
			hom.getHeaders().setContentType(DEFAULT_TYPE);
			hom.getBody().write(document.asXML().getBytes("UTF-8"));
		}
	}

}
