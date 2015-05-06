package com.quanta.sino.cmn.bo;

import com.quanta.sino.cmn.bo.api.ICzjlBO;
import com.quanta.sino.cmn.dao.api.ICzjlDAO;
import com.quanta.sino.orm.ZkfpCzjl;

/**
 * <p>
 * 分配和余材操作记录业务实现类
 * </p>
 * <p>
 * create: 2011-3-8 下午10:45:42
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CzjlBO implements ICzjlBO {

	private ICzjlDAO czjlDAO;

	@Override
	public void save(ZkfpCzjl entity) {
		czjlDAO.save(entity);
	}

	public ICzjlDAO getCzjlDAO() {
		return czjlDAO;
	}

	public void setCzjlDAO(ICzjlDAO czjlDAO) {
		this.czjlDAO = czjlDAO;
	}

}
