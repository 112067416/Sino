/**
 * 
 */
package com.quanta.sino.yd.vo;

import com.coco.core.persistence.a.Q;
import com.coco.core.persistence.a.QF;
import com.coco.core.persistence.a.QSQL;
import com.coco.core.persistence.e.EO;
import com.coco.core.persistence.query.QEntity;

/**
 * <p>
 * 硬度模块现品查询体
 * </p>
 * <p>
 * create: 2011-5-5 下午10:30:27
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Q(@QSQL(sql = "from(select a.JBNO_,a.ZZSD_,a.DHNO_,b.ABBR_,b.HOUU_,b.KUAN_,b.CANG_,b.YUNY_,b.FPDJ_,b.FACE_,b.FUZM_,b.FUFM_,a.SJSJ_,a.YDSJ_,b.YMIN_,b.YMAX_,a.YING_,a.YJDR_ from SINO_YCAITP a left join SINO_DHUOTP b on a.DHNO_=b.DHNO_+b.LINE_ where len(a.JBNO_)=6 and a.DHNO_ not like 'T%' and#1#) t", meta = "JBNO_,ZZSD_,DHNO_,ABBR_,HOUU_,KUAN_,CANG_,YUNY_,FPDJ_,FACE_,FUZM_,FUFM_,SJSJ_,YDSJ_,YMIN_,YMAX_,YING_,YJDR_"))
public class YdQE extends QEntity<YdVO> {

	/**
	 * <p>
	 * 订货号
	 * </p>
	 */
	@QF(alias = "b.DHNO_")
	private String dhno;

	/**
	 * <p>
	 * 行号
	 * </p>
	 */
	@QF(alias = "b.LINE_")
	private String line;

	/**
	 * <p>
	 * 指示书号
	 * </p>
	 */
	@QF(alias = "a.ZSNO_")
	private String zsno;

	/**
	 * <p>
	 * 卷板号
	 * </p>
	 */
	@QF(alias = "a.JBNO_")
	private String jbno;

	/**
	 * <p>
	 * 用户代码开始
	 * </p>
	 */
	@QF(alias = "b.USER_", operator = EO.GE)
	private String userBegin;

	/**
	 * <p>
	 * 用户代码结束
	 * </p>
	 */
	@QF(alias = "b.USER_", operator = EO.LE)
	private String userEnd;

	/**
	 * 用户代码
	 */
	@QF(alias = "b.USER_", operator = EO.EQ)
	private String user;

	/**
	 * 硬度是否录入
	 */
	@QF(alias = "a.YING_", operator = EO.IS_NULL)
	private Boolean ying;

	/**
	 * 原板状态
	 */
	@QF(alias = "a.STAT_", operator = EO.IN)
	private String[] stats;

	public String getDhno() {
		return dhno;
	}

	public void setDhno(String dhno) {
		this.dhno = dhno;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getZsno() {
		return zsno;
	}

	public void setZsno(String zsno) {
		this.zsno = zsno;
	}

	public String getJbno() {
		return jbno;
	}

	public void setJbno(String jbno) {
		this.jbno = jbno;
	}

	public String getUserBegin() {
		return userBegin;
	}

	public void setUserBegin(String userBegin) {
		this.userBegin = userBegin;
	}

	public String getUserEnd() {
		return userEnd;
	}

	public void setUserEnd(String userEnd) {
		this.userEnd = userEnd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Boolean getYing() {
		return ying;
	}

	public void setYing(Boolean ying) {
		this.ying = ying;
	}

	public String[] getStats() {
		return stats;
	}

	public void setStats(String[] stats) {
		this.stats = stats;
	}

}
