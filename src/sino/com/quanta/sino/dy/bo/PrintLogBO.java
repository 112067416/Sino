package com.quanta.sino.dy.bo;

import java.util.Date;

import com.coco.core.persistence.api.DAO;
import com.quanta.sino.dy.bo.api.IPrintLogBO;
import com.quanta.sino.dy.constants.PrintType;
import com.quanta.sino.orm.PrintLog;

/**
 * <p>
 * 打印日志业务实现
 * </p>
 * <p>
 * create: 2011-4-20 下午03:11:46
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class PrintLogBO implements IPrintLogBO {

	private DAO dao;

	@Override
	public void save(String[] nos, String type, String operator) {
		if (nos == null || nos.length == 0 || type == null) {
			return;
		}
		PrintLog log;
		Date date = new Date();
		for (String no : nos) {
			log = new PrintLog();
			log.setNo(no);
			log.setType(type);
			log.setOperator(operator);
			log.setTime(date);
			dao.save(log);
			if (PrintType.YCAI.type.equals(type)) {
				dao.executeUpdate("update YcaiTp set sfdy=? where jbno=?", true, no);
			}
		}
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
