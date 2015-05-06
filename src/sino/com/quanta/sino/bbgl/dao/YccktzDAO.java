package com.quanta.sino.bbgl.dao;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.util.DateUtils;
import com.quanta.sino.bbgl.dao.api.IYccktzDAO;
import com.quanta.sino.bbgl.vo.YccktzVO;

/**
 * <p>
 * 原材料仓库台帐数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-8-31 上午11:32:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YccktzDAO implements IYccktzDAO {

	private JdbcTemplate jt;

	@Override
	public void update(YccktzVO vo) {
		String id = vo.getId();
		if (id == null || id.isEmpty()) return;
		String[] arrs = id.split("&");
		if (arrs.length != 2) return;
		Date riqi = DateUtils.parse(arrs[0], "yyyy-MM-dd");
		String spbh = arrs[1];
		jt.update("update SINO_RPT_YCCKTZ set GJZL_=?,GJYBMY_=?,GJBS_=?,LYZL_=?,LYYBMY_=?,LYBS_=?,JCZL_=?,BSPJC_=?,YBMYJC_=? where SPBH_=? and RIQI_=?", vo.getGjzl(), vo.getGjybmy(), vo.getGjbs(), vo.getLyzl(), vo.getLyybmy(), vo.getLybs(), vo.getJczl(), vo.getBspjc(), vo.getYbmyjc(), spbh, riqi);
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
}
