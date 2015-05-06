package com.coco.core.persistence.query;

import java.util.ArrayList;
import java.util.List;

import com.coco.core.util.ReflectUtils;

/**
 * <p>
 * 查询条件类
 * </p>
 * <p>
 * create: 2010-12-21 上午09:54:32
 * </p>
 * 
 * @param <T>
 *            持久实体实际类型
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class QEntity<T> {

	/**
	 * 取列表最大限制条数，这里设定10000。
	 */
	public static final int MAX_SIZE = 10000;

	/**
	 * 取列表数目，小于等于0表示全部获取不进行分页，为了更好的防止取值太多导致内存欲出，当小于等于0时，给定数目限制 {@link #MAX_SIZE}
	 */
	protected int size;

	/**
	 * 返回的列表对象
	 */
	protected List<T> datas;

	/**
	 * 排序列表，实体字段排序的拼接，如id desc
	 */
	protected String orderBys;

	/**
	 * 页标，从0开始
	 */
	protected int index;

	/**
	 * 查询总记录数
	 */
	protected long count;

	/**
	 * 是否已经查询
	 */
	protected boolean query;

	/**
	 * 求和
	 */
	private Number[] sums;

	/**
	 * <p>
	 * 获取持久实际类型
	 * </p>
	 * 
	 * @return Class<?>
	 */
	public Class<?> getPersistenceType() {
		return ReflectUtils.fetchActualType(this.getClass());
	}

	/**
	 * <p>
	 * 行内数据合计，提供给扩展接口求合计计算
	 * </p>
	 * 
	 * @param data
	 * @return double
	 */
	public double getRowSum(T data) {
		return 0.0;
	}

	/**
	 * <p>
	 * 查询数据合计
	 * </p>
	 * 
	 * @return double
	 */
	public double getDataTotal() {
		if (datas == null || datas.isEmpty()) {
			return 0.0;
		}
		double total = 0.0;
		for (T data : datas) {
			total += getRowSum(data);
		}
		return total;
	}

	public String getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(String orderBys) {
		this.orderBys = orderBys;
	}

	public List<T> getDatas() {
		return datas != null ? datas : new ArrayList<T>();
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageCount() {
		if (size <= 0) {
			return 1;
		}
		int pageCount = (int) (count / size) + 1;
		if (count % size == 0) {
			pageCount--;
		}
		return pageCount;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public boolean isQuery() {
		return query;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	/**
	 * @return the sums
	 */
	public Number[] getSums() {
		return sums;
	}

	/**
	 * @param sums
	 *            the sums to set
	 */
	public void setSums(Number[] sums) {
		this.sums = sums;
	}
}
