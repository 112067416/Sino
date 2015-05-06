package com.coco.core.mvc;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <p>
 * Spring MVC格式转换。
 * <ul>
 * <li>时间转换成yyyy-MM-dd HH:mm:ss</li>
 * <li>字符串自动去掉前后空格</li>
 * </ul>
 * </p>
 * <p>
 * create: 2010-12-21 上午09:38:04
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class CocoWebBingdingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest wr) {
		binder.registerCustomEditor(Date.class, new MyDateEditor());
		binder.registerCustomEditor(String.class, new CocoStringEditor(true));
	}

	/**
	 * <p>
	 * 时间转换
	 * </p>
	 * <p>
	 * create: 2010-12-22 上午10:08:16
	 * </p>
	 * 
	 * @author 许德建[xudejian@126.com]
	 * @version 1.0
	 */
	private class MyDateEditor extends PropertyEditorSupport {

		private final SimpleDateFormat lsdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		private final SimpleDateFormat ssdf = new SimpleDateFormat("yyyy-MM-dd");

		@Override
		public String getAsText() {
			Date value = (Date) getValue();
			if (value == null) {
				return "";
			}
			String text = lsdf.format(value);
			return text.replaceAll(" 00:00:00", "");
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null || (text = text.trim()).isEmpty()) {
				setValue(null);
				return;
			}
			try {
				if (text.length() == 19) {
					setValue(lsdf.parse(text));
				}
				else if (text.length() == 10) {
					setValue(ssdf.parse(text));
				}
				else {
					throw new IllegalArgumentException("不支持的时间格式");
				}
			}
			catch (ParseException e) {
				throw new IllegalArgumentException(
						"不能转换成时间: " + e.getMessage(), e);
			}
		}

	}

	private class CocoStringEditor extends PropertyEditorSupport {

		private final boolean emptyAsNull;

		public CocoStringEditor(boolean emptyAsNull) {
			this.emptyAsNull = emptyAsNull;
		}

		@Override
		public void setAsText(String text) {
			if (text == null) {
				setValue(null);
				return;
			}
			if (this.emptyAsNull && text.isEmpty()) {
				setValue(null);
			}
			else {
				setValue(text);
			}
		}

		@Override
		public String getAsText() {
			Object value = getValue();
			return (value != null ? value.toString() : null);
		}

	}

}
