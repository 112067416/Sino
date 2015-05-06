package com.coco.tag.ui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.tag.TagUtil;

public class PrintTag extends BodyTagSupport {

	/**
	 * <p>
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	protected String name;

	@Override
	public int doStartTag() throws JspException {
		String path = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		StringBuilder content = new StringBuilder();
		content.append("<object id=\"");
		String $id = id != null && !(id = id.trim()).isEmpty() ? id : "LODOP";
		content.append($id);
		content.append("\" classid=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\" width=\"0\" height=\"0\"></object><DIV id=\"");
		content.append($id);
		content.append("_xudejian_tag\"></DIV><script type=\"text/javascript\"><!--\r\n");
		content.append("function onLoadCheckLodop(event){\n");
		content.append("	var LODOP = document.getElementById(\"").append($id).append("\");\n");
		content.append("	var LODOP_CHECKDIV = document.getElementById(\"").append($id).append("_xudejian_tag\");\n");
		content.append("	var oldVersion = LODOP.Version, newVerion=\"6.0.2.6\";\n");
		content.append("	if (oldVersion == null) {\n");
		content.append("		LODOP_CHECKDIV.innerHTML = '<h3><font color=\"#FF00FF\">打印控件未安装!点击这里<a href=\"").append(path).append("/activex/install_lodop.exe\">执行安装</a>,安装后请刷新页面。</font></h3>';\n");
		content.append("		if (window.navigator.appName == \"Netscape\") LODOP_CHECKDIV.innerHTML = '<h3><font color=\"#FF00FF\">（Firefox浏览器用户需先点击这里<a href=\"").append(path).append("/activex/npActiveXFirefox4x.xpi\">安装运行环境</a>）</font></h3>';\n");
		content.append("	}\n");
		content.append("	else if (oldVersion < newVerion) LODOP_CHECKDIV.innerHTML = '<h3><font color=\"#FF00FF\">打印控件需要升级!点击这里<a href=\"").append(path).append("/activex/install_lodop.exe\">执行升级</a>,升级后请重新进入。</font></h3>';\n");
		content.append("	if(oldVersion != null) LODOP.SET_LICENSES(\"海口量子网络科技有限公司\",\"954627879737475919278901905623\",\"\",\"\");\n");
		content.append("}\n");
		if (name != null) {
			Object $name = TagUtil.getBean(pageContext, name);
			if ($name != null) {
				content.append("	LODOP.SET_PRINTER_INDEXA(\"");
				content.append($name.toString().replaceAll("\"", "\\\\\""));
				content.append("\");\n");
			}
		}
		content.append("if(window.attachEvent) window.attachEvent('onload',onLoadCheckLodop);\n");
		content.append("else window.addEventListener('load', onLoadCheckLodop, false);\n");
		content.append("\r\n//--></script>");
		try {
			pageContext.getOut().write(content.toString());
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
