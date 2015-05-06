package com.quanta.sino.dy.vo;

import java.util.List;

/**
 * <p>
 * 卷板缺陷表打印条件和结果
 * </p>
 * <p>
 * create: 2011-1-11 下午04:52:45
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class JbqxbDataVO {

	/**
	 * 条件类型为卷板No.
	 */
	public static final String TYPE_JB = "1";

	/**
	 * 起止号码的条件类型，1-表示卷板No.，其他表示指示NO.。
	 */
	private String type;

	/**
	 * 起始号码
	 */
	private String noBegin;

	/**
	 * 终止号码
	 */
	private String noEnd;

	/**
	 * 返回数据
	 */
	private List<JbqxbVO> datas;

	/**
	 * 初始No.列表，以逗号分隔
	 */
	private String nos;

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
	 * @return the noBegin
	 */
	public String getNoBegin() {
		return noBegin;
	}

	/**
	 * @param noBegin
	 *            the noBegin to set
	 */
	public void setNoBegin(String noBegin) {
		this.noBegin = noBegin;
	}

	/**
	 * @return the noEnd
	 */
	public String getNoEnd() {
		return noEnd;
	}

	/**
	 * @param noEnd
	 *            the noEnd to set
	 */
	public void setNoEnd(String noEnd) {
		this.noEnd = noEnd;
	}

	/**
	 * @return the datas
	 */
	public List<JbqxbVO> getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(List<JbqxbVO> datas) {
		this.datas = datas;
	}

	public String getNos() {
		return nos;
	}

	public void setNos(String nos) {
		this.nos = nos;
	}

}
