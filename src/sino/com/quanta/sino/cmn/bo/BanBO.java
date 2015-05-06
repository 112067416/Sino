package com.quanta.sino.cmn.bo;

import java.util.Date;

import com.coco.core.util.DateUtils;
import com.quanta.sino.cmn.bo.api.IBanBO;

/**
 * <p>
 * 生产班别配置业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:09:43
 * </p>
 * 
 * @author 张红国
 * @version 1.0
 */
public class BanBO implements IBanBO {

	/**
	 * 1班时间段
	 */
	private String ban1;
	/**
	 * 2班时间段
	 */
	private String ban2;
	/**
	 * 3班时间段
	 */
	private String ban3;

	@Override
	public Boolean checkBan(String ban) {
		if (ban == null) {
			return false;
		}
		String newBan = null;
		String[] hourArr;
		Date date = new Date();
		Date date1;
		Date date2;
		hourArr = ban1.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				newBan = "1";
			}
		}
		hourArr = ban2.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				newBan = "2";
			}
		}
		hourArr = ban3.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				newBan = "3";
			}
		}
		if (!ban.equals(newBan)) {
			return false;
		}
		return true;
	}

	@Override
	public String getBan() {
		String[] hourArr;
		Date date = new Date();
		Date date1;
		Date date2;
		hourArr = ban1.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				return "1";
			}
		}
		hourArr = ban2.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				return "2";
			}
		}
		hourArr = ban3.split("-");
		if (hourArr.length > 1) {
			date1 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[0], "yyyy-MM-dd HH:mm:ss");
			date2 = DateUtils.parse(DateUtils.format(date, "yyyy-MM-dd") + " "
					+ hourArr[1], "yyyy-MM-dd HH:mm:ss");

			if (date.compareTo(date1) >= 0 && date.compareTo(date2) < 0) {
				return "3";
			}
		}
		return "";
	}

	public String getBan1() {
		return ban1;
	}

	public void setBan1(String ban1) {
		this.ban1 = ban1;
	}

	public String getBan2() {
		return ban2;
	}

	public void setBan2(String ban2) {
		this.ban2 = ban2;
	}

	public String getBan3() {
		return ban3;
	}

	public void setBan3(String ban3) {
		this.ban3 = ban3;
	}

}
