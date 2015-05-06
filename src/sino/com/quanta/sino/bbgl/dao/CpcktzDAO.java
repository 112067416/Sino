package com.quanta.sino.bbgl.dao;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.coco.core.util.DateUtils;
import com.quanta.sino.bbgl.dao.api.ICpcktzDAO;
import com.quanta.sino.bbgl.vo.CpcktzVO;

/**
 * <p>
 * 成品仓库台帐数据访问接口实现类
 * </p>
 * <p>
 * create: 2011-8-31 上午11:32:15
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class CpcktzDAO implements ICpcktzDAO {

	private JdbcTemplate jt;

	@Override
	public void update(CpcktzVO vo, String cplb) {
		String id = vo.getId();
		if (id == null || id.isEmpty()) return;
		String[] arrs = id.split("&");
		if (arrs.length != 3) return;
		Date riqi = DateUtils.parse(arrs[0], "yyyy-MM-dd");
		String chou = arrs[1];
		if ("C".equals(cplb)) {
			jt.update("update SINO_RPT_CPCKTZ set qbch1=?, qbybch1=?, qbbsch1=?, qbfh1=?, qbybfh1=?, qbbsfh1=?, qbnzw=?, qbwzn=?, qbjc1=?, qbybjc1=?, qbbsjc1=? where CHOU=? and RIQI=? and CPLB=?", vo.getQbch1(), vo.getQbybch1(), vo.getQbbsch1(), vo.getQbfh1(), vo.getQbybfh1(), vo.getQbbsfh1(), vo.getQbnzw(), vo.getQbwzn(), vo.getQbjc1(), vo.getQbybjc1(), vo.getQbbsjc1(), chou, riqi, cplb);
		}
		else if ("Z".equals(cplb)) {
			jt.update("update SINO_RPT_CPCKTZ set qbch1=?, qbybch1=?, qbbsch1=?, qbfh1=?, qbybfh1=?, qbbsfh1=?, qbjc1=?, qbybjc1=?, qbbsjc1=? where CHOU=? and RIQI=? and CPLB=?", vo.getQbch1(), vo.getQbybch1(), vo.getQbbsch1(), vo.getQbfh1(), vo.getQbybfh1(), vo.getQbbsfh1(), vo.getQbjc1(), vo.getQbybjc1(), vo.getQbbsjc1(), chou, riqi, cplb);
		}
		else {
			jt.update("update SINO_RPT_CPCKTZ set qbch1=?, qbybch1=?, qbbsch1=?, che=?, qbybche=?, qbbsche=?, qbfh1=?, qbybfh1=?, qbbsfh1=?, qbnzw=?, fhe=?, qbybfhe=?, qbbsfhe=?, qbwzn=?, qbjc1=?, qbybjc1=?, qbbsjc1=? where CHOU=? and RIQI=? and CPLB=?", vo.getQbch1(), vo.getQbybch1(), vo.getQbbsch1(), vo.getChe(), vo.getQbybche(), vo.getQbbsche(), vo.getQbfh1(), vo.getQbybfh1(), vo.getQbbsfh1(), vo.getQbnzw(), vo.getFhe(), vo.getQbybfhe(), vo.getQbbsfhe(), vo.getQbwzn(), vo.getQbjc1(), vo.getQbybjc1(), vo.getQbbsjc1(), chou, riqi, cplb);
		}
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
}
