package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PanelTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String title;

	protected String image;

	protected String width;

	protected boolean popup;

	protected boolean display = true;

	protected boolean movable = true;

	protected boolean closable;

	protected String onclose;

	private void out(String html) {
		try {
			pageContext.getOut().write(html);
		}
		catch (IOException e) {
		}
	}

	@Override
	public int doEndTag() throws JspException {
		out("</td>\r\n<td class=\"panel-mr\">&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td height=\"6\" class=\"panel-bl\"></td>\r\n<td class=\"panel-bc\"></td>\r\n<td class=\"panel-br\"></td>\r\n</tr>\r\n</table></div>");
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String path = pageContext.getServletContext().getContextPath();
		out("<div");
		if (id != null) {
			out(" id=\"" + id + "\"");
		}
		if (popup) {
			out(" class=\"panel-popup\"");
		}
		String style = "";
		if (!display) {
			style += "display:none;";
		}
		if (!style.isEmpty()) {
			out("style=\"" + style + "\"");
		}
		out("><table ");
		if (width == null || (width = width.trim()).isEmpty()) {
			out(" width=\"99%\"");
		}
		else {
			out(" width=\"" + width + "\"");
		}
		out("align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"panel\"><colgroup><col width=\"8\" /><col /><col width=\"8\" /></colgroup><tr><td height=\"29\" class=\"panel-tl\">&nbsp;</td><td class=\"panel-tc\"");
		if (popup && movable) {
			out(" xu.move=\"block\" xu.move.target=\"" + id + "\"");
		}
		out(">\r\n<table width=\"100%\"><colgroup><col width=\"20\" /><col /><col width=\"30\" /></colgroup><tr><td width=\"20\"><img src=\"");
		if (image == null) {
			out(path + "/images/main/panel_title.gif");
		}
		else if (!(image = image.trim()).isEmpty()) {
			out(path + image);
		}
		out("\" width=\"16\" height=\"16\" align=\"absMiddle\" /></td><td class=\"panel-title\" >");
		if (title != null) {
			out(title);
		}
		out("</td><td align=\"right\" valign=\"middle\">");
		if (closable) {
			out("<img xu.id=\""
					+ id
					+ "\" src=\""
					+ path
					+ "/images/main/icon_x_0.gif\" onmouseover=\"this.src=this.src.replace(/_0/,'_1');\" onmouseout=\"this.src=this.src.replace(/_1/,'_0');\" onclick=\"coco.hidePage('"
					+ id
					+ "');if(document.attachEvent) event.cancelBubble = true;else event.stopPropagation();");
			if (onclose != null) {
				out(onclose);
			}
			out("\" >");
		}
		out("</td></tr></table></td><td class=\"panel-tr\">&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td class=\"panel-ml\">&nbsp;</td>\r\n<td valign=\"top\" class=\"panel-mc\">");
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

	/**
	 * @return the popup
	 */
	public boolean isPopup() {
		return popup;
	}

	/**
	 * @param popup
	 *            the popup to set
	 */
	public void setPopup(boolean popup) {
		this.popup = popup;
	}

	/**
	 * @return the display
	 */
	public boolean isDisplay() {
		return display;
	}

	/**
	 * @param display
	 *            the display to set
	 */
	public void setDisplay(boolean display) {
		this.display = display;
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

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		this.closable = closable;
	}

	public String getOnclose() {
		return onclose;
	}

	public void setOnclose(String onclose) {
		this.onclose = onclose;
	}

	/**
	 * @return the movable
	 */
	public boolean isMovable() {
		return movable;
	}

	/**
	 * @param movable
	 *            the movable to set
	 */
	public void setMovable(boolean movable) {
		this.movable = movable;
	}

}
