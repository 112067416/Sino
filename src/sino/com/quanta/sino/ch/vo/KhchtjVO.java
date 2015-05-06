package com.quanta.sino.ch.vo;

import java.util.List;

import com.quanta.sino.orm.YongSize;

/**
 * <p>
 * 客户出货统计VO
 * </p>
 * <p>
 * create: 2011-8-28 下午05:59:39
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KhchtjVO {

	/**
	 * 按规格、尺寸统计
	 */
	private List<YongSize> sizeVOs;

	/**
	 * 按年统计
	 */
	private List<ChntjVO> chntjVOs;

	/**
	 * 多少种不同的规格
	 */
	private int size;

	/**
	 * 历史出货记录
	 */
	private String chjl;

	public List<ChntjVO> getChntjVOs() {
		return chntjVOs;
	}

	public void setChntjVOs(List<ChntjVO> chntjVOs) {
		this.chntjVOs = chntjVOs;
	}

	public int getSize() {
		if (this.sizeVOs != null && this.sizeVOs.size() > 0) {
			this.size = this.sizeVOs.size();
		}
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getChjl() {
		if (chntjVOs != null && chntjVOs.size() > 0) {
			StringBuilder $chjl = new StringBuilder();
			for (ChntjVO vo : chntjVOs) {
				if ($chjl.length() == 0) {
					$chjl.append(vo.getYear()).append("年      ").append(vo.getChzl());
					continue;
				}
				$chjl.append("<br />").append(vo.getYear()).append("年    ").append(vo.getChzl());
			}
			return $chjl.toString();
		}
		return chjl;
	}

	public void setChjl(String chjl) {
		this.chjl = chjl;
	}

	public List<YongSize> getSizeVOs() {
		return sizeVOs;
	}

	public void setSizeVOs(List<YongSize> sizeVOs) {
		this.sizeVOs = sizeVOs;
	}

}
