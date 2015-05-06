package com.quanta.sino.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.coco.core.env.Helper;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.Sfpp;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zkfp.bo.api.IFpBO;
import com.quanta.sino.zkfp.constants.ZkfpConstance;

/**
 * <p>
 * 在库分配操作，判断订货合同与现品信息是否配匹
 * </p>
 * <p>
 * create: 2011-2-13 下午03:41:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class ZkfpSfppSpanTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订货合同号
	 */
	protected String dhno;

	/**
	 * 订货合同行号
	 */
	protected String line;

	/**
	 * 现品No.
	 */
	protected String jbno;

	/**
	 * 现品状况（素材、中间品或制品）
	 */
	protected String xpzk;

	@Override
	public int doStartTag() throws JspException {
		String $dhno = dhno;
		if ($dhno == null || ($dhno = dhno.trim()).isEmpty()) {
			return SKIP_BODY;
		}
		String $line = line;
		if ($line == null || ($line = line.trim()).isEmpty()) {
			return SKIP_BODY;
		}
		String $jbno = jbno;
		if ($jbno == null || ($jbno = jbno.trim()).isEmpty()) {
			return SKIP_BODY;
		}
		String $xpzk = xpzk;
		if ($xpzk == null || ($xpzk = xpzk.trim()).isEmpty()
				|| EXpzk.get($xpzk) == null) {
			return SKIP_BODY;
		}
		IDhBO dhBO = Helper.getBean(IDhBO.class);
		DhuoTp dhuoTp = dhBO.get(new DhuoTpPk(dhno, line));
		if (dhuoTp == null) {
			return SKIP_BODY;
		}
		YcaiTp ycaiTp = null;
		ZpngTp zpngTp = null;
		if (EXpzk.SC.key.equals($xpzk)) {
			IYcaitpBO ycaitpBO = Helper.getBean(IYcaitpBO.class);
			if ((ycaiTp = ycaitpBO.get($jbno)) == null) {
				return SKIP_BODY;
			}
		}
		else {
			IZpBO zpBO = Helper.getBean(IZpBO.class);
			if ((zpngTp = zpBO.getZp($jbno)) == null) {
				return SKIP_BODY;
			}
		}
		IFpBO fpBO = Helper.getBean(IFpBO.class);
		Sfpp sfpp = fpBO.getSfpp(ycaiTp, zpngTp, dhuoTp, $xpzk, ZkfpConstance.ZKFP_JBKB_0);
		if (Sfpp.match.key.equals(sfpp.key)) {
			write("<span>" + sfpp.name + "</span");
		}
		else {
			write("<span style=\"color: red;\">" + sfpp.name + "</span");
		}
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
	 * @return the dhno
	 */
	public String getDhno() {
		return dhno;
	}

	/**
	 * @param dhno
	 *            the dhno to set
	 */
	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the jbno
	 */
	public String getJbno() {
		return jbno;
	}

	/**
	 * @param jbno
	 *            the jbno to set
	 */
	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	/**
	 * @return the xpzk
	 */
	public String getXpzk() {
		return xpzk;
	}

	/**
	 * @param xpzk
	 *            the xpzk to set
	 */
	public void setXpzk(String xpzk) {
		this.xpzk = xpzk;
	}

}
