package com.quanta.sino.cmn.bo.api;

import java.util.Date;

import com.coco.core.bean.User;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.ZpngTp;

/**
 * <p>
 * 余材化处理业务层接口
 * </p>
 * <p>
 * create: 2011-1-14 下午03:21:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public interface IYchBO {

	/**
	 * <p>
	 * 保存制口余材化处理操作。一次余材操作的制品必须属于同一个订货合同
	 * </p>
	 * <ul>
	 * <li>验证要做余材的制品是否制作装箱指示书。如果已做装箱指示书，则不能做余材操作。
	 * <li>验证要做余材的制品是否为成品（卷成品或板成品）。注：堆场是否在C、F或G，现品在库是否为制品，分配/余材标记是否为分配品，
	 * 删除标识是否为初始和状态是否为初始
	 * <li>修改对应制品，在制品在库DB表中的信息。
	 * <li>修改制品所属订货合同的指示量和合格量（ELT和SL）。当做余材化操作的制品是钢卷制品时，修改对应订货合同的ELT指示量和ETL合格量；
	 * 当做余材化操作的制品是剪切板制品时，修改对应订货合同的SL指示量和SL合格量
	 * <li>写分配操作记录表。
	 * </ul>
	 * 
	 * @param jbnos
	 * @param user
	 * @return String
	 */
	public void doZpYch(String[] jbnos, User user);

	/**
	 * <p>
	 * ETL、SL和SS在做实绩录入的时候对不合格品（判断依据是产量代码不为1和9）做余材化操作
	 * </p>
	 * <ul>
	 * <li>当现品的产量为1或9时，只用修改现品的分配/余材标记为分配品
	 * <li>当现品的产量不为1或9时，只用修改现品的分配号、订货号、用户略称、用户名称清空，同时将分配/余材标记为余材品； 记录现品余材操作
	 * <li>
	 * </ul>
	 * 
	 * @param zpngTp
	 */
	public void doScYch(ZpngTp zpngTp, String oldchan, DhuoTp dhuo, Date date);

}
