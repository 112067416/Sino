package com.quanta.sino.cmn.bo.api;

/**
 * <p>
 * 生产班别配置业务接口
 * </p>
 * <p>
 * create: 2011-1-6 上午09:53:25
 * </p>
 * 
 * @author 张红国
 * @version 1.0
 */
public interface IBanBO {
	/**
	 * <p>
	 * 判断班别是否在班的时间段里。false为不在，true为在。
	 * </p>
	 * 
	 * @param ban
	 * @return
	 */
	public Boolean checkBan(String ban);

	public String getBan();
}
