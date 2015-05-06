package com.quanta.sino.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.env.Helper;
import com.quanta.sino.ch.bo.api.IZxdzBO;
import com.quanta.sino.ch.vo.ChtjVO;
import com.quanta.sino.cmn.constants.ChStat;

/**
 * <p>
 * 出货联络单打单情况
 * </p>
 * <p>
 * create: 2011-2-16 下午06:27:59
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ChlldDdqkSpanTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 出货联络单
	 */
	protected String chlldId;

	/**
	 * 回调函数方法名
	 */
	protected String callback;

	/**
	 * 提示信息
	 */
	protected String alt;

	/**
	 * 每行显示的打单情况个数
	 */
	protected int size;

	@Override
	public int doStartTag() throws JspException {
		String $chlldId = chlldId;
		if ($chlldId == null || ($chlldId = chlldId.trim()).isEmpty()) {
			return SKIP_BODY;
		}
		IZxdzBO zxdzBO = Helper.getBean(IZxdzBO.class);
		List<ChtjVO> vos = zxdzBO.findChtj($chlldId);
		if (vos == null || vos.size() == 0) {
			return SKIP_BODY;
		}
		size = size == 0 ? 2 : size;
		StringBuilder html = new StringBuilder();
		int i = 1;
		for (ChtjVO vo : vos) {
			if (html.length() > 0) {
				if (i % (size + 1) == 0) {
					i = 1;
					html.append("<br/>");
				}
				else {
					html.append(" / ");
				}
			}
			html.append("<span onclick=\"").append(callback).append("('").append(vo.getZxno()).append("')\"");
			if (alt != null && !alt.isEmpty()) {
				html.append(" alt=\"").append(alt).append("\"");
			}
			html.append(" style=\"cursor: hand; ");
			if (ChStat.YFH.stat.equals(vo.getStat())) {
				html.append("color: red; ");
			}
			html.append("\">").append(vo.getChzl()).append("-").append(vo.getChsu()).append("</span>");
			i++;
		}
		write(html.toString());
		return SKIP_BODY;
	}

	private void write(String content) {
		try {
			pageContext.getOut().write(content);
		}
		catch (IOException e) {
		}
	}

	/**
	 * @return the chlldId
	 */
	public String getChlldId() {
		return chlldId;
	}

	/**
	 * @param chlldId
	 *            the chlldId to set
	 */
	public void setChlldId(String chlldId) {
		this.chlldId = chlldId;
	}

	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * @param callback
	 *            the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
