package com.quanta.sino.sj.bo.api;

import java.io.Serializable;

import com.quanta.sino.cmn.constants.ERzlx;
import com.quanta.sino.etl.vo.SjSaveVO;
import com.quanta.sino.orm.RiziLp;
import com.quanta.sino.orm.Xpfmt;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.orm.ZscdTp;
import com.quanta.sino.orm.ZsdhTp;
import com.quanta.sino.orm.Zsfmt;
import com.quanta.sino.sj.vo.SjVO;
import com.quanta.sino.sl.vo.SjzkVO;

/**
 * <p>
 * 实绩日志公共格式业务接口
 * </p>
 * <p>
 * create: 2011-1-20 下午06:02:30
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public interface ISjBO {

	/**
	 * <p>
	 * 保存实绩日志（指示情报共通格式）
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Zsfmt entity);

	/**
	 * <p>
	 * 根据现品编号保存实绩日志（指示情报共通格式、现品情报共通格式）
	 * </p>
	 * 
	 * @param jbno
	 * @param zsno
	 * @return SjVO
	 */
	public SjVO saveForZp(String jbno, String clq);

	/**
	 * <p>
	 * 根据现品编号保存实绩日志（指示情报共通格式、现品情报共通格式）
	 * </p>
	 * 
	 * @param jbno
	 * @param zsno
	 * @return SjVO
	 */
	public SjVO saveForZp(String jbno, String zsno, String clq);

	/**
	 * <p>
	 * 根据现品编号保存实绩日志（指示情报共通格式、现品情报共通格式）
	 * </p>
	 * 
	 * @param sjVO
	 *            界面实体
	 * @param clq
	 * @return
	 */
	public SjVO saveForZpZZ(SjSaveVO sjVO, String clq);

	/**
	 * <p>
	 * 根据现品编号保存实绩日志（指示情报共通格式、现品情报共通格式）
	 * </p>
	 * 
	 * @param zp
	 *            界面制品录入信息
	 * @param zss
	 *            指示书实体
	 * @param clq
	 * @return
	 */
	public SjVO saveForSlZpZZ(ZpngTp zp, ZsdhTp zss, SjzkVO vo, Integer yin,
			String clq);

	/**
	 * <p>
	 * 保存实绩日志(现品情报共通格式)
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Xpfmt entity);

	/**
	 * <p>
	 * 保存日志（日志类型：{@link ERzlx}）
	 * </p>
	 * 
	 * @param entity
	 *            日志实体
	 */
	public void save(RiziLp entity);

	/**
	 * <p>
	 * 删除日志（日志类型：{@link ERzlx}）
	 * </p>
	 * 
	 * @param entity
	 *            日志实体
	 */
	public void delete(Serializable id);

	/**
	 * <p>
	 * 获取指示出端记录
	 * </p>
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public ZscdTp getZscd(Serializable id);

	/**
	 * <p>
	 * 保存指示出端记录
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveZscd(ZscdTp entity);

	/**
	 * <p>
	 * 删除指示出端
	 * </p>
	 * 
	 * @param id
	 */
	public void deleteZscd(Serializable id);

	/**
	 * <p>
	 * 保存指示共通
	 * </p>
	 * 
	 * @param zpng
	 * @param zsdh
	 * @return Zsfmt
	 */
	public Zsfmt saveZsfmt(ZpngTp zpng, ZsdhTp zsdh);

	/**
	 * <p>
	 * 解保存现品共通
	 * </p>
	 * 
	 * @param zpng
	 * @return Xpfmt
	 */
	public Xpfmt saveXpfmt(ZpngTp zpng, String clq);
}
