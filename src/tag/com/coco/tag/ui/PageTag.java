package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String title;

	protected String image;

	protected String width;

	private static final String HTML_0 = "<table ";

	private static final String HTML_1 = "align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"page\"><colgroup><col width=\"10\" /><col /><col width=\"10\" /></colgroup>\r\n<tr>\r\n<td height=\"30\" class=\"page-tl\"></td>\r\n<td class=\"page-tc\">\r\n<table><tr><td width=\"22\"><img src=\"";

	private static final String HTML_2 = "\" width=\"18\" height=\"18\" align=\"absMiddle\" /></td><td class=\"page-title\">";

	private static final String HTML_3 = "</td></tr></table>\r\n</td>\r\n<td class=\"page-tr\"></td>\r\n</tr>\r\n<tr>\r\n<td class=\"page-ml\"></td>\r\n<td valign=\"top\" class=\"page-mc\">";

	private static final String HTML_4 = "</td>\r\n<td class=\"page-mr\"></td>\r\n</tr>\r\n<tr>\r\n<td height=\"8\" class=\"page-bl\"></td>\r\n<td class=\"page-bc\">&nbsp;</td>\r\n<td class=\"page-br\"></td>\r\n</tr>\r\n</table>";

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write(HTML_4);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String $image = pageContext.getServletContext().getContextPath();
		try {
			pageContext.getOut().write(HTML_0);
			if (id != null) {
				pageContext.getOut().write(" id=\"" + id + "\"");
			}
			if (width == null || (width = width.trim()).isEmpty()) {
				pageContext.getOut().write(" width=\"99%\"");
			} else {
				pageContext.getOut().write(" width=\"" + width + "\"");
			}
			pageContext.getOut().write(HTML_1);

			if (image == null || (image = image.trim()).isEmpty()) {
				$image += "/images/main/page_title.gif";
			} else {
				$image += image;
			}
			pageContext.getOut().write($image);
			pageContext.getOut().write(HTML_2);
			if (title != null) {
				pageContext.getOut().write(title);
			}
			pageContext.getOut().write(HTML_3);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}
