package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

/**
 * 上传附件控件
 * 
 * @author 许德建[xudejian@126.com]
 */
public class AttachmentTag extends BodyTagSupport {

	private enum TypeFilter {
		image("image", "图片 (*.gif,*.jpg,*.jpeg,*.png):*.gif;*.jpg;*.jpeg;*.png"), excel(
				"excel", "电子表格(*.xls, *.xlsx):*.xls;*.xlsx"), word("word",
				"Word文档(*.doc,*.docx):*.doc;*docx"), text("text",
				"文本(*.txt):*.txt");

		public final String key;

		public final String value;

		TypeFilter(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected int width;

	protected int height;

	protected boolean single;

	protected String uri = "/sys/attachment/upload.do";

	protected String type;

	protected String label;

	protected String rel;

	protected boolean rename;

	/**
	 * 文件类型过滤
	 */
	protected String typeFilter;

	/**
	 * 隐藏加号图标
	 */
	protected boolean hidePlus;

	/**
	 * 上传时调用的js函数名
	 */
	protected String uploading;

	/**
	 * 上传后调用的js函数名
	 */
	protected String uploaded;

	/**
	 * 附件ID存储的隐藏域名称
	 */
	protected String attachName;

	/**
	 * 初始附件值
	 */
	protected String value;

	@Override
	public int doStartTag() throws JspException {
		Object $value = TagUtil.getBean(pageContext, value);
		String path = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		StringBuilder content = new StringBuilder();
		content.append("<script type=\"text/javascript\">\ndocument.write(createFlash({");
		content.append("id : \"").append(id).append("\"");
		content.append(",movie : \"").append(path).append("/activex/Upload.swf\"");
		if (width > 0) {
			content.append(",width : ").append(width);
		}
		if (height > 0) {
			content.append(",height : ").append(height);
		}
		if (single) {
			content.append(",single : \"1\"");
		}
		content.append(",uri : \"").append(path).append(uri).append("\"");

		if (label != null) {
			content.append(",label : \"").append(label.trim()).append("\"");
		}
		Object $type = TagUtil.getBean(pageContext, type);
		if ($type != null) {
			content.append(",type : \"").append($type).append("\"");
		}
		Object $rel = TagUtil.getBean(pageContext, rel);
		if ($rel != null) {
			content.append(",rel : \"").append($rel).append("\"");
		}
		if (rename) {
			content.append(",rename : \"1\"");
		}
		if (hidePlus) {
			content.append(",hidePlus : \"1\"");
		}
		if (attachName != null && attachName.length() > 0) {
			content.append(",attachName : \"").append(attachName).append("\"");
		}
		content.append(",path : \"").append("/".equals(path) ? "" : path).append("\"");
		if (typeFilter != null && !typeFilter.isEmpty()) {
			String $typeFilter = typeFilter;
			for (TypeFilter tf : TypeFilter.values()) {
				$typeFilter = $typeFilter.replace("#" + tf.key + "#", tf.value);
			}
			content.append(",typeFilter : \"").append($typeFilter).append("\"");
		}
		if (uploading != null && !uploading.isEmpty()) {
			content.append(",uploading : \"").append(uploading).append("\"");
		}
		if (uploaded != null && !uploaded.isEmpty()) {
			content.append(",uploaded : \"").append(uploaded).append("\"");
		}
		content.append("}));\n</script>");
		if (attachName != null && attachName.length() > 0) {
			content.append("<input type=\"hidden\" id=\"").append(id).append("_").append(attachName).append("\" name=\"").append(attachName).append("\"");
			if ($value != null) {
				content.append(" value=\"").append($value).append("\"");
			}
			content.append(" />");
		}
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the single
	 */
	public boolean isSingle() {
		return single;
	}

	/**
	 * @param single
	 *            the single to set
	 */
	public void setSingle(boolean single) {
		this.single = single;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel
	 *            the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return the rename
	 */
	public boolean isRename() {
		return rename;
	}

	/**
	 * @param rename
	 *            the rename to set
	 */
	public void setRename(boolean rename) {
		this.rename = rename;
	}

	/**
	 * @return the uploaded
	 */
	public String getUploaded() {
		return uploaded;
	}

	/**
	 * @param uploaded
	 *            the uploaded to set
	 */
	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	/**
	 * @return the typeFilter
	 */
	public String getTypeFilter() {
		return typeFilter;
	}

	/**
	 * @param typeFilter
	 *            the typeFilter to set
	 */
	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
	}

	/**
	 * @return the uploading
	 */
	public String getUploading() {
		return uploading;
	}

	/**
	 * @param uploading
	 *            the uploading to set
	 */
	public void setUploading(String uploading) {
		this.uploading = uploading;
	}

	/**
	 * @return the hidePlus
	 */
	public boolean isHidePlus() {
		return hidePlus;
	}

	/**
	 * @param hidePlus
	 *            the hidePlus to set
	 */
	public void setHidePlus(boolean hidePlus) {
		this.hidePlus = hidePlus;
	}

	/**
	 * @return the attachName
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * @param attachName
	 *            the attachName to set
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
