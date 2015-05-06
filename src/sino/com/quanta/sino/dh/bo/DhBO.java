package com.quanta.sino.dh.bo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.excel.api.ExcelbookDataExecuter;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.api.ISqlDAO;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.DateUtils;
import com.coco.core.util.ExcelUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.IPersonBO;
import com.coco.sys.orm.Code;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.orm.Person;
import com.quanta.sino.ch.bo.api.IZxzsBO;
import com.quanta.sino.cmn.bo.api.IInformBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.bo.api.IZzsyBO;
import com.quanta.sino.cmn.constants.ChanType;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Code013;
import com.quanta.sino.cmn.constants.Code103;
import com.quanta.sino.cmn.constants.Code104;
import com.quanta.sino.cmn.constants.Code105;
import com.quanta.sino.cmn.constants.Code118;
import com.quanta.sino.cmn.constants.Code119;
import com.quanta.sino.cmn.constants.CodeJian;
import com.quanta.sino.cmn.constants.CodeKPlate;
import com.quanta.sino.cmn.constants.CodeLtdw;
import com.quanta.sino.cmn.constants.CodeNjbh;
import com.quanta.sino.cmn.constants.CodeNwx;
import com.quanta.sino.cmn.constants.CodeProt;
import com.quanta.sino.cmn.constants.CodeTyzl;
import com.quanta.sino.cmn.constants.CodeYsfs;
import com.quanta.sino.cmn.constants.DhStat;
import com.quanta.sino.cmn.constants.Dybs;
import com.quanta.sino.cmn.constants.EDm;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.constants.ZlnrCode;
import com.quanta.sino.cmn.vo.ZzsyConstants;
import com.quanta.sino.dh.bo.api.IDhBO;
import com.quanta.sino.dh.constants.DhCh;
import com.quanta.sino.dh.constants.DhConstants;
import com.quanta.sino.dh.constants.DhFzlDw;
import com.quanta.sino.dh.constants.DhHc;
import com.quanta.sino.dh.constants.DhJzqf;
import com.quanta.sino.dh.constants.DhQf;
import com.quanta.sino.dh.constants.DhWw;
import com.quanta.sino.dh.constants.DhYy;
import com.quanta.sino.dh.dao.api.IDhDAO;
import com.quanta.sino.dh.dao.api.IGxhtDAO;
import com.quanta.sino.dh.vo.DefaultDhVO;
import com.quanta.sino.dh.vo.DhVO;
import com.quanta.sino.dh.vo.DhjdVO;
import com.quanta.sino.dh.vo.Dhjdmx;
import com.quanta.sino.dh.vo.DhuoChVO;
import com.quanta.sino.dh.vo.DhuoVO;
import com.quanta.sino.dh.vo.FspHtVO;
import com.quanta.sino.dh.vo.FspTxVO;
import com.quanta.sino.dh.vo.FspVO;
import com.quanta.sino.dh.vo.FspZlVO;
import com.quanta.sino.dh.vo.GxhtVO;
import com.quanta.sino.dh.vo.JstjVO;
import com.quanta.sino.dh.vo.ZzsyVO;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.orm.DhuoTp;
import com.quanta.sino.orm.DhuoTpPk;
import com.quanta.sino.orm.GxhtTp;
import com.quanta.sino.orm.YongMp;
import com.quanta.sino.orm.YongShdz;
import com.quanta.sino.orm.ZzsyMp;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zs.bo.api.IZsBO;

/**
 * <p>
 * 订货单业务层实现类
 * </p>
 * <p>
 * create: 2011-1-4 上午10:52:08
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class DhBO implements IDhBO {

	/**
	 * 订货单数据访问接口
	 */
	private IDhDAO dao;

	/**
	 * 引入代码业务层bo
	 */
	private ICodeBO codeBo;

	/**
	 * 引入仕样信息业务层bo
	 */
	private IZzsyBO zzsyBo;

	/**
	 * 引入制品业务层bo
	 */
	private IZpBO zpBo;

	/**
	 * 订货进度明细报表
	 */
	private ExcelbookDataExecuter<DhjdVO> dhjdExec;

	/**
	 * 订货进度明细报表模板路径
	 */
	private String dhjdPath;

	/**
	 * 订货出货对比表明细报表
	 */
	private ExcelbookDataExecuter<DhuoChVO> dhuoChExec;

	/**
	 * 订货出货对比表明细报表模板路径
	 */
	private String dhuoChPath;

	/**
	 * 指示书业务接口
	 */
	private IZsBO zsBO;

	/**
	 * 原材卷板业务接口
	 */
	private IYcaitpBO ycaitpBO;

	/**
	 * 用户管理业务接口（客户管理）
	 */
	private IYongBO yongBO;

	/**
	 * 资料室业务接口
	 */
	private IInformBO informBO;

	/**
	 * 员工业务接口
	 */
	private IPersonBO personBO;

	/**
	 * 
	 */
	private ISqlDAO sqlDao;

	/**
	 * 购销合同数据访问接口
	 */
	private IGxhtDAO gxhtDAO;

	/**
	 * 
	 */
	private IZxzsBO zxzsBO;

	/**
	 * 镀锡量(大于25号时，合金层为必填项)
	 */
	private static final String DXL = "025";

	/**
	 * 合同行号
	 */
	private static final String LINE = "01";

	@Override
	public String save(DhuoTp entity, User user) {
		if (dao.isExisted(new DhuoTpPk(entity.getDhno(), entity.getLine()))) {
			return new Result(-1, "该订货No.已存在,请重新输入").toString();
		}
		// 检查证明书张数
		YongMp yongMp = yongBO.get(entity.getUser());
		entity.setJcha(yongMp.getJcha());
		// 获取规格略称
		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, entity.getGgno());
		entity.setGgnm(codeItem != null ? codeItem.getValue() : null);
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_015, entity.getKbao());
		if (codeItem != null) {
			// 获取包装材料重量(Kg)
			if (codeItem.getValue() != null) {
				entity.setBzcl(Integer.valueOf(codeItem.getValue()));
			}
			if (codeItem.getRemark() != null) {
				// 获取 垫木重量(Kg)
				entity.setDmzl((Integer.valueOf(codeItem.getRemark())));
			}
		}
		entity.setCrea(new Date());
		// 默认状态为0即初始
		entity.setStat(DhStat.newly.key);
		// 默认结算条件.设定标志 未登录 ——0
		entity.setJstj(DhConstants.JSTJ_WDL);
		// 订货确认表打印标示
		entity.setSfdy(Dybs.WDY.key);
		// 换算尺寸
		// 长默认为订货尺寸.长
		entity.setHngc(entity.getCang());
		// 厚默认为订货尺寸.厚
		entity.setHngh(entity.getHouu());
		// 宽默认为订货尺寸.宽
		entity.setHngk(entity.getKuan());
		entity.setCjno(user.getNo());
		entity.setCjnm(user.getName());
		dao.save(entity);

		// 更新用途代码与用途名称
		updateCond(entity.getCond(), entity.getCdnm());
		return Result.SUCCESS;
	}

	@Override
	public String update(DhuoTp entity, User user, String ydhno) {
		String dhno = entity.getDhno();
		String line = entity.getLine();
		DhuoTp dhuoTp = dao.get(SinoUtils.parseDhuoPk(ydhno));
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		if (!DhStat.newly.key.equals(dhuoTp.getStat())) {
			return new Result(-1, "该订货合同状态为"
					+ DhStat.get(dhuoTp.getStat()).name + ",不能进行修改操作").toString();
		}
		boolean flag = false;
		if (!ydhno.equals(dhno + line)) {
			if (dao.isExisted(new DhuoTpPk(dhno, line))) {
				return new Result(-1, "不能将订货合同No.从" + ydhno + "改为" + dhno
						+ line + ",因为在系统中订货合同No." + dhno + line + "对应的合同已存在").toString();
			}
			flag = true;
		}
		String ddno = entity.getDdno();
		Person person = personBO.getUniqueByNo(entity.getDdno());
		if (person == null) {
			return new Result(-1, "担当者" + ddno + "不存在").toString();
		}
		dhuoTp.setDdno(ddno);
		dhuoTp.setDdnm(person.getName());
		parseDh(dhuoTp, entity);
		// 检查证明书张数
		YongMp yongMp = yongBO.get(entity.getUser());
		dhuoTp.setJcha(yongMp.getJcha());
		// 获取规格略称
		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, entity.getGgno());
		dhuoTp.setGgnm(codeItem != null ? codeItem.getValue() : null);
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_015, entity.getKbao());
		// 获取包装材料重量(Kg)
		if (codeItem != null) {
			if (codeItem.getValue() != null) {
				dhuoTp.setBzcl(Integer.valueOf(codeItem.getValue()));
			}
			if (codeItem.getRemark() != null) {
				// 获取 垫木重量(Kg)
				dhuoTp.setDmzl((Integer.valueOf(codeItem.getRemark())));
			}
		}
		// 订货确认表打印标示
		dhuoTp.setSfdy(Dybs.WDY.key);
		dhuoTp.setUpda(new Date());
		dhuoTp.setXgnm(user.getName());
		dhuoTp.setXgno(user.getNo());
		dhuoTp.setDhqr(DhConstants.DHQR);
		double htdj = dhuoTp.getHtdj() != null ? dhuoTp.getHtdj() : 0;
		double htzl = dhuoTp.getHtzl() != null ? dhuoTp.getHtzl() : 0;
		double htje = NumberUtils.format(htdj * htzl, 3);
		dhuoTp.setHtje(htje);
		if (flag) {
			dao.delete(SinoUtils.parseDhuoPk(ydhno));
			DhuoTp $dhuoTp = new DhuoTp();
			ReflectUtils.copy($dhuoTp, dhuoTp, false);
			$dhuoTp.setDhno(dhno);
			$dhuoTp.setLine(line);
			dao.save($dhuoTp);
		}
		else {
			dao.update(dhuoTp);
		}

		// 更新用途代码与用途名称
		updateCond(entity.getCond(), entity.getCdnm());
		return Result.SUCCESS;
	}

	private void parseDh(DhuoTp dhuoTp, DhuoTp entity) {
		// 处理允许为空的字段
		dhuoTp.setGgno(entity.getGgno());
		dhuoTp.setGgnm(entity.getGgnm());
		dhuoTp.setPzno(entity.getPzno());
		dhuoTp.setFudw(entity.getFudw());
		dhuoTp.setFuzm(entity.getFuzm());
		dhuoTp.setFufm(entity.getFufm());
		// 差厚
		dhuoTp.setChdx(entity.getChdx());
		dhuoTp.setNwai(entity.getNwai());
		dhuoTp.setHouu(entity.getHouu());
		dhuoTp.setKuan(entity.getKuan());

		// 订货尺寸.长
		dhuoTp.setCang(entity.getCang());

		// 长默认为订货尺寸.长
		dhuoTp.setHngc(entity.getCang());
		// 厚默认为订货尺寸.厚
		dhuoTp.setHngh(entity.getHouu());
		// 宽默认为订货尺寸.宽
		dhuoTp.setHngk(entity.getKuan());
		dhuoTp.setCond(entity.getCond());
		dhuoTp.setCdnm(entity.getCdnm());
		dhuoTp.setUser(entity.getUser());
		dhuoTp.setAbbr(entity.getAbbr());
		dhuoTp.setName(entity.getName());
		// dhuoTp.setShno(entity.getShno());
		// dhuoTp.setShnm(entity.getShnm());
		dhuoTp.setAddr(entity.getAddr());

		dhuoTp.setSsno(entity.getSsno());
		dhuoTp.setSsnm(entity.getSsnm());
		dhuoTp.setJhqi(entity.getJhqi());
		dhuoTp.setJhnr(entity.getJhnr());
		dhuoTp.setHtzl(entity.getHtzl());
		// 交货允许
		dhuoTp.setJhfz(entity.getJhfz());
		dhuoTp.setJhzz(entity.getJhzz());
		dhuoTp.setJhqf(entity.getJhqf());
		// 张数
		dhuoTp.setKbsq(entity.getKbsq());
		dhuoTp.setKbsz(entity.getKbsz());
		// 卷重
		dhuoTp.setKbzq(entity.getKbzq());
		dhuoTp.setKbzd(entity.getKbzd());
		dhuoTp.setKbzs(entity.getKbzs());
		dhuoTp.setKbzx(entity.getKbzx());
		// 捆包计算
		dhuoTp.setKbjs(entity.getKbjs());
		// 化成
		dhuoTp.setHuac(entity.getHuac());
		dhuoTp.setFace(entity.getFace());
		// 压延
		dhuoTp.setYyan(entity.getYyan());
		// 垫木
		dhuoTp.setDmfx(entity.getDmfx());
		dhuoTp.setKbao(entity.getKbao());
		// 货标
		dhuoTp.setHbbj(entity.getHbbj());
		// 外W
		dhuoTp.setRjie(entity.getRjie());
		// 内径
		dhuoTp.setNeij(entity.getNeij());
		// 特B
		dhuoTp.setTbno(entity.getTbno());
		// 特C
		dhuoTp.setTcno(entity.getTcno());
		// 需L
		dhuoTp.setYhbq(entity.getYhbq());
		dhuoTp.setJian(entity.getJian());
		dhuoTp.setLtdw(entity.getLtdw());
		dhuoTp.setLtzi(entity.getLtzi());
		dhuoTp.setYtyp(entity.getYtyp());
		dhuoTp.setPlat(entity.getPlat());
		dhuoTp.setYqty(entity.getYqty());
		dhuoTp.setHjin(entity.getHjin());
		dhuoTp.setFpdj(entity.getFpdj());
		dhuoTp.setJiao(entity.getJiao());
		dhuoTp.setProt(entity.getProt());
		dhuoTp.setYmin(entity.getYmin());
		dhuoTp.setYmax(entity.getYmax());
		dhuoTp.setFuzx(entity.getFuzx());
		dhuoTp.setFuzs(entity.getFuzs());
		dhuoTp.setFufx(entity.getFufx());
		dhuoTp.setFufs(entity.getFufs());
		dhuoTp.setJcxs(entity.getJcxs());
		dhuoTp.setHszi(entity.getHszi());
		dhuoTp.setHxzi(entity.getHxzi());
		dhuoTp.setKszi(entity.getKszi());
		dhuoTp.setKxzi(entity.getKxzi());
		dhuoTp.setCszi(entity.getCszi());
		dhuoTp.setCxzi(entity.getCxzi());
		dhuoTp.setZrkn(entity.getZrkn());
		dhuoTp.setNjbh(entity.getNjbh());
		dhuoTp.setYuny(entity.getYuny());
		dhuoTp.setKpn1Flag(entity.getKpn1Flag());
		dhuoTp.setKpn1(entity.getKpn1());
		dhuoTp.setKpn2Flag(entity.getKpn2Flag());
		dhuoTp.setKpn2(entity.getKpn2());
		dhuoTp.setKpn3Flag(entity.getKpn3Flag());
		dhuoTp.setKpn3(entity.getKpn3());
		dhuoTp.setKpn4Flag(entity.getKpn4Flag());
		dhuoTp.setKpn4(entity.getKpn4());
		String mgsn = entity.getMgsn();
		dhuoTp.setMgsn(mgsn != null ? mgsn.replaceAll("，", ",") : null);
		dhuoTp.setBz1(entity.getBz1());
		dhuoTp.setBz2(entity.getBz2());
		dhuoTp.setBz3(entity.getBz3());
		// 制造仕样No
		dhuoTp.setSyno(entity.getSyno());
		dhuoTp.setJcts(entity.getJcts());
	}

	@Override
	public String delete(String[] ids) {
		DhuoTp entity = null;
		String[] id;
		DhuoTpPk dhuoTpPk;
		List<DhuoTpPk> dhuoTpPks = new ArrayList<DhuoTpPk>();
		for (int i = 0; i < ids.length; i++) {
			id = ids[i].split("-");
			if (id == null || id.length != 2) {
				continue;
			}
			dhuoTpPk = new DhuoTpPk(id[0], id[1]);
			entity = dao.get(dhuoTpPk);
			if (entity == null) {
				return new Result(-1, "订货合同No." + id[0] + "-" + id[1] + ",不存在").toString();
			}
			if (!DhStat.newly.key.equals(entity.getStat())) {
				return new Result(-1, "该订货合同状态为"
						+ DhStat.get(entity.getStat()).name + ",不能做删除操作").toString();
			}
			dhuoTpPks.add(dhuoTpPk);
		}
		dao.deletes(dhuoTpPks);
		return Result.SUCCESS;
	}

	@Override
	public void query(QEntity<DhuoTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public DhuoTp get(String dhno, String line, String user) {
		return dao.get(dhno, line, user);
	}

	@Override
	public String getZzsy(DhuoTp dhuoTp) {
		ZzsyVO zzsyVO = null;
		ZzsyMp zzsyMp = null;
		String syno = dhuoTp.getSyno();
		if (syno != null && !syno.isEmpty()) {
			zzsyMp = zzsyBo.get(syno);
		}
		if (zzsyMp != null) {
			zzsyVO = new ZzsyVO();
			// 相同字段的就拷贝，不同的就不必拷贝
			ReflectUtils.copy(zzsyVO, zzsyMp, false);
			return new Result(zzsyVO).toJsObject();
		}
		// 需要仕样主文件模块提供接口
		zzsyMp = zzsyBo.getByKey(dhuoTp.getGgno(), dhuoTp.getFudw(), dhuoTp.getFuzm(), dhuoTp.getFufm(), dhuoTp.getPzno(), dhuoTp.getChdx(), dhuoTp.getNwai(), dhuoTp.getHouu(), dhuoTp.getKuan(), dhuoTp.getCang(), dhuoTp.getCond(), dhuoTp.getUser());
		if (zzsyMp != null) {
			zzsyVO = new ZzsyVO();
			// 相同字段的就拷贝，不同的就不必拷贝
			ReflectUtils.copy(zzsyVO, zzsyMp, false);
			return new Result(zzsyVO).toJsObject();
		}
		zzsyVO = zzsyBo.getDefaultZzsy(dhuoTp.getGgno(), dhuoTp.getFudw(), dhuoTp.getFuzm(), dhuoTp.getFufm(), dhuoTp.getPzno());
		return new Result(zzsyVO).toJsObject();
	}

	@Override
	public Result checkDhInfo(DhuoTp entity, boolean flag) {
		// 验证规格
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-6, "规格为必填项");
		}
		if (ggno.length() != 4) {
			return new Result(-6, "规格输入有误,长度为四位");
		}
		// 校验钢种类
		if (!codeBo.isExisted(CmnConstants.CODE_109, entity.getGgno().substring(0, 1))) {
			return new Result(-6, "规格输入有误,规格的第一位即钢种类在码表中不存在");
		}
		// 校验退火代号（烧炖方法）
		if (!codeBo.isExisted(CmnConstants.CODE_110, entity.getGgno().substring(1, 2))) {
			return new Result(-6, "规格输入有误,规格的第二位即退火代号在码表中不存在");
		}
		// 校验调质度代号（硬度）
		if (!codeBo.isExisted(CmnConstants.CODE_111, entity.getGgno().substring(2, 4))) {
			return new Result(-6, "规格输入有误,规格的后两位即调质度代号在码表中不存在");
		}
		if (!codeBo.isExisted(CmnConstants.CODE_003, ggno)) {
			return new Result(-6, "规格输入有误,在码表中不存在");
		}
		// 验证品种
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-7, "品种为必填项");
		}
		if (pzno.length() != 2) {
			return new Result(-7, "品种输入有误,长度为两位");
		}
		// 校验品种
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-7, "品种输入有误,品种的第一位在码表中不存在");
		}
		// 校验品种等级
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-7, "品种输入有误,品种的第二位即等级在码表中不存在");
		}
		// 验证附着量.单位
		String fudw = entity.getFudw();
		if (fudw == null || fudw.isEmpty()) {
			return new Result(-8, "附着量单位为必填项");
		}
		if (fudw.length() != 2) {
			return new Result(-8, "附着量.单位输入有误,长度为两位");
		}
		if (DhFzlDw.get(entity.getFudw()) == null) {
			return new Result(-8, "附着量.单位输入有误,只能输入WB或GM");
		}
		// 验证附着量.正面
		String fuzm = entity.getFuzm();
		if (fuzm == null || fuzm.isEmpty()) {
			return new Result(-9, "附着量.正面为必填项");
		}
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fuzm)) {
			return new Result(-9, "附着量.正面输入有误,在码表中不存在");
		}
		// 验证附着量.反面
		String fufm = entity.getFufm();
		if (fufm == null || fufm.isEmpty()) {
			return new Result(-10, "附着量.反面为必填项");
		}
		// 校验附着量.反面
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fufm)) {
			return new Result(-10, "附着量.反面输入有误,在码表中不存在");
		}
		// 验证差厚
		String chdx = entity.getChdx();
		if (fuzm.equals(fufm)) {
			if (chdx != null && !chdx.isEmpty()) {
				return new Result(-11, "差厚输入有误,当附着量.正面与附着量.反面相同,差厚必须为空");
			}
		}
		else {
			if (chdx == null || chdx.isEmpty()) {
				return new Result(-11, "差厚输入有误,当附着量.正面与附着量.反面不相同时,差厚为必填项");
			}
			else {
				// 验证输入的值必须为A1、A2、D1、D2中的一个
				if (DhCh.get(chdx) == null) {
					return new Result(-11, "差厚输入有误,差厚只能是A1、A2、D1或D2");
				}
			}
		}
		// 验证内外销为必填项
		String nwai = entity.getNwai();
		if (nwai == null || nwai.isEmpty()) {
			return new Result(-12, "内外为必填项");
		}
		if (CodeNwx.get(nwai) == null) {
			return new Result(-12, "内外输入有误,内外只能是1、2或3");
		}
		// 是否为钢卷类型合同
		boolean isCoil = Code118.coil.key.equals(pzno.substring(0, 1));
		// 验证尺寸.长
		Double houu = entity.getHouu();
		if (houu == null) {
			return new Result(-13, "尺寸.厚为必填项");
		}
		Double kuan = entity.getKuan();
		if (kuan == null) {
			return new Result(-14, "尺寸.宽为必填项");
		}
		Double cang = entity.getCang();
		if (!isCoil && cang == null) {
			return new Result(-15, "尺寸.长输入有误,当订货合同类型为剪切板时,尺寸.长为必填项");
		}
		if (isCoil && cang != null) {
			return new Result(-15, "尺寸.长输入有误,当订货合同类型为钢卷时,没有尺寸.长");
		}
		// 验证
		String cond = entity.getCond();
		if (cond == null || cond.isEmpty()) {
			return new Result(-16, "加工用途为必填项");
		}
		if (cond.length() != 4) {
			return new Result(-16, "加工用途输入有误,长度为四位");
		}
		// 校验最终用途
		if (!codeBo.isExisted(CmnConstants.CODE_114, cond.substring(0, 1))) {
			return new Result(-16, "加工用途输入有误,加工用途的第一位即最终用途在码表中不存在");
		}
		// 校验加工条件
		if (!codeBo.isExisted(CmnConstants.CODE_115, cond.substring(1, 2))) {
			return new Result(-16, "加工用途输入有误,加工用途的第二位即加工条件在码表中不存在");
		}
		// 校验罐头内装物
		if (!codeBo.isExisted(CmnConstants.CODE_116, cond.substring(2, 3))) {
			return new Result(-16, "加工用途输入有误,加工用途的第三位即罐头内装物在码表中不存在");
		}
		// 校验涂装条件
		if (!codeBo.isExisted(CmnConstants.CODE_117, cond.substring(3, 4))) {
			return new Result(-16, "加工用途输入有误,加工用途的第四位即涂装条件在码表中不存在");
		}
		String cdnm = entity.getCdnm();
		if (cdnm == null || cdnm.isEmpty()) {
			return new Result(-17, "加工用途名称为必填项");
		}
		if (!codeBo.isExisted(CmnConstants.SINO_CDNM, cdnm)) {
			return new Result(-17, "加工用途名称输入有误,加工用途名称在码表中不存在");
		}
		// 验证用户代码
		String user = entity.getUser();
		if (user == null || user.isEmpty()) {
			return new Result(-18, "用户代码为必填项");
		}
		if (!yongBO.isExistUser(user)) {
			return new Result(-18, "用户代码" + user + ",在用户主文件中不存在");
		}
		DhuoTp dhuoTp = dao.get(entity.getDhno(), null, null);
		if (dhuoTp != null && !LINE.equals(dhuoTp.getLine())
				&& !user.equals(dhuoTp.getUser())) {
			return new Result(-18, "订货号" + dhuoTp.getDhno() + ",对应的用户是"
					+ dhuoTp.getAbbr());
		}
		String addr = entity.getAddr();
		if (addr == null || addr.isEmpty()) {
			return new Result(-21, "交货地址不能为空");
		}
		// 验证交货日期
		Date jhqi = entity.getJhqi();
		if (jhqi == null) {
			return new Result(-25, "交货日期为必填项");
		}
		// 对字段：交货期 进行验证
		// 再转换为Date类型进行比较（为了去掉时 分 秒 获取日期即可）
		// 只要新增和修改订货合同时，才比较该日期
		if (flag && DateUtils.compareForDay(entity.getJhqi(), new Date()) == 1) {
			return new Result(-25, "交货日期输入有误,交货日期只能大于当前时间");
		}
		// 验证重内(交货内容)
		String jhnr = entity.getJhnr();
		if (jhnr == null || jhnr.isEmpty()) {
			return new Result(-26, "重内为必填项");
		}
		if (ZlnrCode.get(jhnr) == null) {
			return new Result(-26, "重内输入有误,重内厚只能是1、2、3、6、7或8");
		}
		Double htzl = entity.getHtzl();
		if (htzl == null) {
			return new Result(-27, "合同重量为必填项");
		}
		if (htzl <= 0) {
			return new Result(-27, "合同重量输入有误,合同重量只能为正数");
		}
		// 交货允许.区分
		String jhqf = entity.getJhqf();
		if (jhqf == null || jhqf.isEmpty()) {
			return new Result(-28, "交货允许.区分为必填项");
		}
		if (jhqf != null && !jhqf.isEmpty() && DhQf.get(jhqf) == null) {
			return new Result(-28, "交货允许.区分输入有误,交货允许.区分只能是T或W");
		}
		// 交货允许.+值及交货允许.-值
		Integer jhfz = entity.getJhfz();
		if (jhfz == null) {
			return new Result(-29, "交货允许.负值为必填项");
		}
		Integer jhzz = entity.getJhzz();
		if (jhzz == null) {
			return new Result(-30, "交货允许.正值为必填项");
		}
		if (jhzz != null && jhfz != null) {
			if (jhfz > jhzz) {
				return new Result(-30, "交货允许.负值输入有误,交货允许.正值必须大于交货允许.负值");
			}
		}
		// 包装张数、压延、垫木、卷重、外W、内径
		// 品种第一位为1或3表示马口铁剪切板或马口铁锯齿剪切板时
		if (!isCoil) {
			// 包装张数
			String kbsq = entity.getKbsq();
			if (kbsq == null || kbsq.isEmpty()) {
				return new Result(-31, "当订货合同类型为剪切板时,捆包指定张数.区分为必填项");
			}
			if (!DhConstants.KBSQ.equals(kbsq)) {
				return new Result(-31, "捆包指定张数.区分,捆包指定张数.区分只能是9");
			}
			String kbsz = entity.getKbsz();
			if (kbsz == null || kbsz.isEmpty()) {
				return new Result(-32, "当订货合同类型为剪切板时,捆包指定张数.值为必填项");
			}
			int $kbsz = Integer.parseInt(kbsz);
			// 包装张数.值的校验
			List<CodeItem> codeItems = codeBo.findItems(CmnConstants.SINO_KBSZ);
			if (codeItems != null && codeItems.size() > 0) {
				String v;
				int bcs;
				boolean isExisted = false;
				for (CodeItem codeItem : codeItems) {
					if ((v = codeItem.getValue()) == null || v.isEmpty()) continue;
					try {
						bcs = Integer.parseInt(v);
					}
					catch (NumberFormatException e) {
						continue;
					}
					if ($kbsz % bcs == 0) {
						isExisted = true;
						break;
					}
				}
				if (!isExisted) {
					return new Result(-32,
							"当订货合同类型为剪切板时,捆包指定张数.值一定要能被50、100或112除尽");
				}
			}
			// if ($kbsz % DhConstants.ZS_50 != 0
			// && $kbsz % DhConstants.ZS_100 != 0
			// && $kbsz % DhConstants.ZS_112 != 0) {
			// return new Result(-32, "当订货合同类型为剪切板时,捆包指定张数.值一定要能被50、100或112除尽");
			// }
			// 剪切板每包不能够超过2t
			double bdan = SinoUtils.calculateBdan(entity.getHouu(), entity.getKuan(), entity.getCang());
			if ($kbsz * bdan > 2000) {
				return new Result(-32, "捆包指定张数偏大,每包的重量不能够超过2吨");
			}
			// 压延（压延方向指示标志）
			String yyan = entity.getYyan();
			if (yyan == null || yyan.isEmpty()) {
				return new Result(-41, "当订货合同类型为剪切板时,压延为必填项");
			}
			if (DhYy.get(yyan) == null) {
				return new Result(-41, "当订货合同类型为剪切板时,压延只能是1或2");
			}
			if (kuan < cang && DhYy.YY_DB.key.equals(yyan)) {
				return new Result(-41, "压延输入有误,当尺寸.宽小于尺寸.长时,压延只能为2");
			}
			if (kuan >= cang && DhYy.YY_CB.key.equals(yyan)) {
				return new Result(-41, "压延输入有误,当尺寸.宽大于尺寸.长时,压延只能为1");
			}
			// 垫木（垫木方向）
			String dmfx = entity.getDmfx();
			if (dmfx == null || dmfx.isEmpty()) {
				return new Result(-42, "当订货合同类型为剪切板时,垫木为必填项");
			}
			if (EDm.get(dmfx) == null) {
				return new Result(-42, "当订货合同类型为剪切板时,垫木只能是L或C");
			}
			// 卷重
			String kbzq = entity.getKbzq();
			if (kbzq != null && !kbzq.isEmpty()) {
				return new Result(-33, "当订货合同类型为剪切板时,没有捆包指定重量.区分");
			}
			String kbzd = entity.getKbzd();
			if (kbzd != null && !kbzd.isEmpty()) {
				return new Result(-34, "当订货合同类型为剪切板时,没有捆包指定重量.单位");
			}
			Double kbzx = entity.getKbzx();
			if (kbzx != null) {
				return new Result(-35, "当订货合同类型为剪切板时,没有捆包指定重量.下限");
			}
			Double kbzs = entity.getKbzs();
			if (kbzs != null) {
				return new Result(-36, "当订货合同类型为剪切板时,没有捆包指定重量.上限");
			}
			// 外W（销售溶接部不可）
			String rjie = entity.getRjie();
			if (rjie != null && !rjie.isEmpty()) {
				return new Result(-43, "当订货合同类型为剪切板时,没有外W");
			}
			// 内径
			String neij = entity.getNeij();
			if (neij != null && !neij.isEmpty()) {
				return new Result(-44, "当订货合同类型为剪切板时,没有内径");
			}
		}
		// 品种第一位为2表示马口铁钢卷时
		if (isCoil) {
			// 包装张数
			String kbsq = entity.getKbsq();
			if (kbsq != null && !kbsq.isEmpty()) {
				return new Result(-31, "当订货合同类型为钢卷时,没有捆包指定张数.区分");
			}
			String kbsz = entity.getKbsz();
			if (kbsz != null && !kbsz.isEmpty()) {
				return new Result(-32, "当订货合同类型为钢卷时,没有捆包指定张数.值");
			}
			// 卷重
			String kbzq = entity.getKbzq();
			if (kbzq == null || kbzq.isEmpty()) {
				return new Result(-33, "当订货合同类型为钢卷时,捆包指定重量.区分为必填项");
			}
			if (DhJzqf.get(kbzq) == null) {
				return new Result(-33, "捆包指定重量.区分输入有误,捆包指定重量.区分只能是5、6或8");
			}
			String kbzd = entity.getKbzd();
			if (kbzd == null || kbzd.isEmpty()) {
				return new Result(-34, "当订货合同类型为钢卷时,捆包指定重量.单位为必填项");
			}
			if (!DhConstants.KBZD_T.equals(kbzd)) {
				return new Result(-34, "捆包指定重量.单位输入有误,捆包指定重量.单位只能是T");
			}
			Double kbzx = entity.getKbzx();
			if (kbzx == null) {
				return new Result(-35, "捆包指定重量.下限输入有误,捆包指定重量.下限必填项");
			}
			Double kbzs = entity.getKbzs();
			if (kbzs == null) {
				return new Result(-36, "捆包指定重量.上限输入有误,捆包指定重量.上限必填项");
			}
			// 外W（销售溶接部不可）
			String rjie = entity.getRjie();
			if (rjie == null || rjie.isEmpty()) {
				return new Result(-43, "当订货合同类型为钢卷时,外W必填项");
			}
			// 外W值校验
			if (DhWw.get(rjie) == null) {
				return new Result(-43, "外W输入有误,外W只能是0、1或2");
			}
			// 内径
			String neij = entity.getNeij();
			if (neij == null || neij.isEmpty()) {
				return new Result(-44, "当订货合同类型为钢卷时,内径必填项");
			}
			// 内径值校验
			if (!codeBo.isValueExisted(CmnConstants.CODE_020, neij)) {
				return new Result(-44, "内径输入有误,内径在码表中不存在");
			}
			// 压延（压延方向指示标志）
			String yyan = entity.getYyan();
			if (yyan != null && !yyan.isEmpty()) {
				return new Result(-41, "当订货合同类型为钢卷时,没有压延");
			}
			// 垫木（垫木方向）
			String dmfx = entity.getDmfx();
			if (dmfx != null && !dmfx.isEmpty()) {
				return new Result(-42, "当订货合同类型为钢卷时,没有垫木方向");
			}
		}
		// 化成（化成处理）
		String huac = entity.getHuac();
		if (huac == null || huac.isEmpty()) {
			return new Result(-38, "化成处理为必填项");
		}
		if (huac != null && !huac.isEmpty()) {
			if (DhHc.get(huac) == null) {
				return new Result(-38, "化成处理输入有误,化成只能是311或300");
			}
		}
		// 捆包计算
		String kbjs = entity.getKbjs();
		if (kbjs != null && !kbjs.isEmpty() && !DhConstants.KBJS_Z.equals(kbjs)) {
			return new Result(-37, "捆包计算输入有误,捆包计算只能是空或1");
		}
		// 捆包形式
		String kbao = entity.getKbao();
		if (kbao == null || kbao.isEmpty()) {
			return new Result(-39, "捆包形式为必填项");
		}
		// 校验捆包形式
		// 根据捆包形式查询主文件为015对应的信息
		if (!codeBo.isExisted(CmnConstants.CODE_015, kbao)) {
			return new Result(-39, "捆包形式输入有误,捆包形式在码表中不存在");
		}
		// 根据捆包计算 及尺寸长 宽验证
		// if (kbjs != null && kuan != null && cang != null) {
		// if (kuan > DhConstants.CC_KOC || cang > DhConstants.CC_KOC) {
		// if (!DhConstants.KBXS_ZS.equals(kbao)) {
		// return new Result(-1,
		// "kbao捆包形式输入有误,当捆包计算为1并且尺寸长或尺寸宽只要有一项目是大于等于850时,捆包形式必须填写为A31");
		// }
		// }
		// if (kuan < DhConstants.CC_KOC && cang < DhConstants.CC_KOC) {
		// if (!DhConstants.KBXS_ZE.equals(kbao)) {
		// return new Result(-1,
		// "kbao捆包形式输入有误,当捆包计算为1并且尺寸长和尺寸宽都小于850时,捆包形式必须填写为A21");
		// }
		// }
		// }
		// 表面（表面加工精度）
		String face = entity.getFace();
		if (face == null || face.isEmpty()) {
			return new Result(-40, "表面为必填项");
		}
		if (face != null && !codeBo.isExisted(CmnConstants.CODE_005, face)) {
			return new Result(-40, "表面输入有误,表面在码表中不存在");
		}
		// 特B（特BNO）
		String tbno = entity.getTbno();
		if (tbno != null && !DhConstants.T_BCL.equals(tbno)) {
			return new Result(-46, "特B输入有误,特B只能是空或1");
		}
		// 特C（特CNO）
		String tcno = entity.getTcno();
		if (tcno != null && !DhConstants.T_BCL.equals(tcno)) {
			return new Result(-47, "特C输入有误,特C只能是空或1");
		}
		// 需L（用户标签名表示）
		String yhbq = entity.getYhbq();
		if (yhbq != null && !DhConstants.T_BCL.equals(yhbq)) {
			return new Result(-48, "需L输入有误,需L只能是空或1");
		}
		return new Result(1, "OK");
	}

	/**
	 * <p>
	 * 判断制造仕样信息
	 * </p>
	 * 
	 * @param entity
	 * @return Result
	 */
	@Override
	public Result checkZzsy(DhuoTp entity) {
		String pzno = entity.getPzno();
		if (!Code119.prime.key.equals(pzno.substring(1, 2))) {
			return new Result(1, "OK");
		}
		String deng = pzno.substring(1);
		String nwai = entity.getNwai();
		// 是否为钢卷类型合同
		boolean isCoil = Code118.coil.key.equals(pzno.substring(0, 1));
		// 剪边 （T-剪边、N-不剪边）
		// 注：当品种代码第1位为'2'（卷材）时，仅仅指定’N’
		String jian = entity.getJian();
		if (jian != null && !jian.isEmpty()) {
			if (CodeJian.get(jian) == null) {
				return new Result(-49, "剪边输入有误,剪边只能是N或T");
			}
			if (isCoil && !CodeJian.N.key.equals(jian)) {
				return new Result(-49, "当订货合同类型为钢卷时,剪边只能是N");
			}
		}
		// 零头下限.单位 （P-张数、B-%、N-不指定）
		// 切板必填。
		String ltdw = entity.getLtdw();
		if (!isCoil && (ltdw == null || ltdw.isEmpty())) {
			return new Result(-50, "当订货合同类型为剪切板时,零头下限.单位为必填项");
		}
		if (isCoil && ltdw != null && !ltdw.isEmpty()) {
			return new Result(-50, "当订货合同类型为钢卷时,没有零头下限.单位");
		}
		if (ltdw != null && CodeLtdw.get(ltdw) == null) {
			return new Result(-50, "零头下限.单位输入有误,零头下限.单位只能是B、P或N");
		}
		Integer ltzi = entity.getLtzi();
		if (!isCoil && (ltzi == null || ltzi.intValue() == 0)) {
			return new Result(-51, "当订货合同类型为剪切板时,零头下限.值为必填项");
		}
		if (isCoil && ltzi != null && ltzi > 0) {
			return new Result(-51, "当订货合同类型为钢卷时,没有零头下限.值");
		}
		// 验证涂油种类
		String ytyp = entity.getYtyp();
		if (ytyp == null || ytyp.isEmpty()) {
			return new Result(-52, "涂油种类必须填写");
		}
		if (!CodeTyzl.DOS.key.equals(ytyp)) {
			return new Result(-52, "涂油种类输入有误,涂油种类只能是D");
		}
		// CodeTyzl codeTyzl = CodeTyzl.get(ytyp);
		// if (codeTyzl == null) {
		// return new Result(-51,"涂油种类输入有误,涂油种类只能是C、D或空");
		// }
		// 验证涂油量
		String yqty = entity.getYqty();
		if (yqty == null || yqty.isEmpty()) {
			return new Result(-53, "涂油量必填项");
		}
		// if (CodeTyzl.DOS.key.equals(ytyp)
		// && !CodeTyzl.DOS.value.equals(codeTyzl.value)) {
		// return new Result(-52,"涂油量输入有误,当涂油种类为" + CodeTyzl.DOS.key
		// + "时,涂油量只能是" + CodeTyzl.DOS.value);
		// }
		// if (CodeTyzl.CSO.key.equals(ytyp)
		// && !CodeTyzl.CSO.value.equals(codeTyzl.value)) {
		// return new Result(-52,"涂油量输入有误,当涂油种类为" + CodeTyzl.CSO.key
		// + "时,涂油量只能是" + CodeTyzl.CSO.value);
		// }
		// KPLATE
		// 说明：K值之外有错误
		String plat = entity.getPlat();
		if (plat != null && !plat.isEmpty() && !CodeKPlate.K.key.equals(plat)) {
			return new Result(-54, "KPLATE输入有误,KPLATE只能是空或K");
		}
		// 合金层
		// 说明：K、5值之外有错误
		String hjin = entity.getHjin();
		if (DXL.compareTo(entity.getFufm()) <= 0
				|| DXL.compareTo(entity.getFuzm()) <= 0) {
			if (hjin == null || hjin.isEmpty()) {
				return new Result(8, "当附着量大于" + DXL + "时,合金层为必填项");
			}
		}
		if (hjin != null && !hjin.isEmpty()
				&& !codeBo.isExisted(CmnConstants.SINO_HJIN, hjin)) {
			return new Result(-55, "合金层输入有误,在码表中不存在");
		}
		// 分配等级：通用主文件ID（103、104、105）
		// 第一位：订货等级（G-国内合格、P-出口合格、K-其他、空-无规发生）
		// 第二位：表面性状等级（S、A、B、C、D）
		// 第三位：形状等级（S、A、B、C、D）
		String fpdj = entity.getFpdj();
		if (fpdj == null || fpdj.isEmpty()) {
			return new Result(-56, "分配等级必填项");
		}
		if (fpdj.length() != 3) {
			return new Result(-56, "分配等级输入有误,分配等级长度为三位");
		}
		if (Code119.prime.key.equals(deng)) {
			if (CodeNwx.nei.key.equals(nwai)
					&& !Code103.G.key.equals(fpdj.substring(0, 1))) {
				return new Result(-56, "分配等级输入有误,当订货合同的内外销为" + CodeNwx.nei.name
						+ "并且等级为" + Code119.prime.key + "时,分配等级的订货等级（第一位）必须为"
						+ Code103.G.key);
			}
			if (CodeNwx.wai.key.equals(nwai)
					&& !Code103.P.key.equals(fpdj.substring(0, 1))) {
				return new Result(-56, "分配等级输入有误,当订货合同的内外销为" + CodeNwx.wai.name
						+ "并且等级为" + Code119.prime.key + "时,分配等级的订货等级（第一位）必须为"
						+ Code103.P.key);
			}
		}
		else {
			if (!Code103.K.key.equals(fpdj.substring(0, 1))) {
				return new Result(-56, "分配等级输入有误,当等级为" + Code119.get(deng).key
						+ "时,分配等级的订货等级（第一位）必须为" + Code103.K.key);
			}
		}
		if (Code104.get(fpdj.substring(1, 2)) == null) {
			return new Result(-56, "分配等级输入有误,分配等级的表面性状等级（第二位）只能是A、B、C、D或S");
		}
		if (Code105.get(fpdj.substring(2)) == null) {
			return new Result(-56, "分配等级输入有误,分配等级的形状等级（第三位）只能是A、B、C、D或S");
		}
		// 直角度 品种代码为1时必须指定。设定对于基准长度600mm的偏差程度。
		Double jiao = entity.getJiao();
		if (!isCoil && (jiao == null || jiao == 0)) {
			return new Result(-57, "当订货合同类型为剪切板时,直角度为必填项");
		}
		if (isCoil && jiao != null && jiao != 0) {
			return new Result(-57, "当订货合同类型为钢卷时,没有直角度");
		}
		// 保护标记（空-不要、T-仅上部、W-上下部）。马口铁剪切板
		String prot = entity.getProt();
		if (isCoil && prot != null && !prot.isEmpty()) {
			return new Result(-58, "当订货合同类型为钢卷时,没有保护标记");
		}
		if (prot != null && CodeProt.get(prot) == null) {
			return new Result(-58, "保护板输入有误,保护板只能是空、T或W");
		}
		// 锯齿形式 通用主文件ID=008。
		// 若为锯齿剪切板时，因为宽、长尺寸被指定到小数点以下2位，若以能同剪切板相区别。
		String jcxs = entity.getJcxs();
		if (isCoil && jcxs != null && !jcxs.isEmpty()) {
			return new Result(-65, "当订货合同类型为钢卷时,没有锯齿形式");
		}
		if (jcxs != null && !jcxs.isEmpty()) {
			CodeItem item = codeBo.getUniqueItemByValue(CmnConstants.CODE_008, jcxs);
			if (item == null) {
				return new Result(-65, "锯齿形式板输入有误,锯齿形式在码表中不存在");
			}
		}
		// 内径保护筒 （空-不要、1-纸保护筒、2-钢保护筒）。马口铁钢卷
		String njbh = entity.getNjbh();
		// if (!isCoil && njbh != null && !njbh.isEmpty()) {
		// return new Result(-72, "当订货合同类型为剪切板时,没有内径保护筒");
		// }
		if (njbh != null && CodeNjbh.get(njbh) == null) {
			return new Result(-73, "内径保护筒 输入有误,内径保护筒 只能是空、1或2");
		}
		// 装入宽
		Double zrkn = entity.getZrkn();
		if (isCoil && zrkn != null && zrkn > 0) {
			return new Result(-72, "当订货合同类型为钢卷时,没有装入宽");
		}
		if (CodeJian.T.key.equals(jian)
				&& (zrkn == null || zrkn.doubleValue() == 0)) {
			return new Result(-72, "装入宽 输入有误,当剪边为" + CodeJian.T.key
					+ "时,装入宽必填项");
		}
		// 验证硬度上下限、附着量正面上下限、附着量反面上下限
		ZzsyVO zzsyVO = zzsyBo.getDefaultZzsy(entity.getGgno(), entity.getFudw(), entity.getFuzm(), entity.getFufm(), entity.getPzno());
		// 校验硬度上下限
		String ymin = entity.getYmin();
		if (ymin == null || ymin.isEmpty()) {
			return new Result(-59, "硬度下限为必填项");
		}
		String ymax = entity.getYmax();
		if (ymax == null || ymax.isEmpty()) {
			return new Result(-60, "硬度上限为必填项");
		}
		if (ymax.compareTo(ymin) < 0) {
			return new Result(-60, "硬度上限值输入有误,硬度上限值不能小于硬度下限值");
		}
		// 硬度上限
		if (ymax.compareTo(zzsyVO.getYmin()) < 0
				|| ymax.compareTo(zzsyVO.getYmax()) > 0) {
			return new Result(-60, "硬度上限值输入有误,硬度上限不值在范围" + zzsyVO.getYmin()
					+ "-" + zzsyVO.getYmax() + "内");
		}
		// 硬度下限
		if (ymin.compareTo(zzsyVO.getYmin()) < 0
				|| ymin.compareTo(zzsyVO.getYmax()) > 0) {
			return new Result(-59, "硬度下限值输入有误,硬度下限不值在范围" + zzsyVO.getYmin()
					+ "-" + zzsyVO.getYmax() + "内");
		}
		// 验证附着量正面上下限
		Double fuzs = entity.getFuzs();
		if (fuzs == null || fuzs <= 0) {
			return new Result(-62, "附着量.正面上限为必填项,并且大于0");
		}
		Double fuzx = entity.getFuzx();
		if (fuzs == null || fuzs <= 0) {
			return new Result(-61, "附着量.正面上限为必填项,并且大于0");
		}
		// 附着量正面上限
		if (fuzs > zzsyVO.getFuzs() || fuzs < zzsyVO.getFuzx()) {
			return new Result(-62, "附着量.正面上限输入有误,附着量.正面上限值不在范围"
					+ zzsyVO.getFuzx() + "-" + zzsyVO.getFuzs() + " 内");
		}
		// 附着量正面下限
		if (fuzx > zzsyVO.getFuzs() || fuzx < zzsyVO.getFuzx()) {
			return new Result(-61, "附着量.正面下限输入有误,附着量.正面下限值不在范围"
					+ zzsyVO.getFuzx() + "-" + zzsyVO.getFuzs() + " 内");
		}
		// 验证附着量反面上下限
		Double fufs = entity.getFufs();
		if (fufs == null || fufs <= 0) {
			return new Result(-64, "附着量.反面上限为必填项,并且大于0");
		}
		Double fufx = entity.getFufx();
		if (fufx == null || fufx <= 0) {
			return new Result(-63, "附着量.反面下限为必填项,并且大于0");
		}
		// 附着量反面上限
		if (fufs > zzsyVO.getFufs() || fufs < zzsyVO.getFufx()) {
			return new Result(-64, "附着量.反面上限输入有误,附着量.反面上限值不在范围"
					+ zzsyVO.getFufx() + "-" + zzsyVO.getFufs() + " 内");
		}
		// 附着量反面下限
		if (fufx > zzsyVO.getFufs() || fufx < zzsyVO.getFufx()) {
			return new Result(-63, "附着量.反面下限输入有误,附着量.反面下限值不在范围"
					+ zzsyVO.getFufx() + "-" + zzsyVO.getFufs() + " 内");
		}
		// 公差长下限
		Double cxzi = entity.getCxzi();
		if (isCoil && (cxzi != null && cxzi > 0)) {
			return new Result(-70, "当订货合同类型为钢卷时,没有公差长下限");
		}
		// 公差长上限
		Double cszi = entity.getCszi();
		if (isCoil && (cszi != null && cszi > 0)) {
			return new Result(-71, "当订货合同类型为钢卷时,没有公差长上限");
		}
		// 附页kpn1Flag 标示位
		String kpn1Flag = entity.getKpn1Flag();
		String kpn1 = entity.getKpn1();
		if ((kpn1Flag == null || kpn1Flag.isEmpty()) && kpn1 != null
				&& !kpn1.isEmpty()) {
			return new Result(-76, "当别纸KpNo的标示位为空时,别纸KpNo必须为空");
		}
		if (kpn1Flag != null && !kpn1Flag.isEmpty()
				&& (kpn1 == null || kpn1.isEmpty())) {
			return new Result(-76, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项");
		}
		if (kpn1Flag != null && !kpn1Flag.isEmpty()
				&& ProductType.get(kpn1Flag) == null) {
			return new Result(-76, "别纸KpNo标示位,只能是1、2或3");
		}
		if (kpn1 != null && !kpn1.isEmpty() && !informBO.isExisted(kpn1)) {
			return new Result(-76, "别纸KpNo不存在");
		}
		// 附页kpn2Flag 标示位
		String kpn2Flag = entity.getKpn2Flag();
		String kpn2 = entity.getKpn2();
		if ((kpn2Flag == null || kpn2Flag.isEmpty()) && kpn2 != null
				&& !kpn2.isEmpty()) {
			return new Result(-78, "当别纸KpNo的标示位为空时,别纸KpNo必须为空");
		}
		if (kpn2Flag != null && !kpn2Flag.isEmpty()
				&& (kpn2 == null || kpn2.isEmpty())) {
			return new Result(-78, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项");
		}
		if (kpn2Flag != null && !kpn2Flag.isEmpty()
				&& ProductType.get(kpn2Flag) == null) {
			return new Result(-78, "别纸KpNo标示位,只能是1、2或3");
		}
		if (kpn2 != null && !kpn2.isEmpty() && !informBO.isExisted(kpn2)) {
			return new Result(-78, "别纸KpNo不存在");
		}
		// 附页kpn3Flag 标示位
		String kpn3Flag = entity.getKpn3Flag();
		String kpn3 = entity.getKpn3();
		if ((kpn3Flag == null || kpn3Flag.isEmpty()) && kpn3 != null
				&& !kpn3.isEmpty()) {
			return new Result(-80, "当别纸KpNo的标示位为空时,别纸KpNo必须为空");
		}
		if (kpn3Flag != null && !kpn3Flag.isEmpty()
				&& (kpn3 == null || kpn3.isEmpty())) {
			return new Result(-80, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项");
		}
		if (kpn3Flag != null && !kpn3Flag.isEmpty()
				&& ProductType.get(kpn3Flag) == null) {
			return new Result(-80, "别纸KpNo标示位,只能是1、2或3");
		}
		if (kpn3 != null && !kpn3.isEmpty() && !informBO.isExisted(kpn3)) {
			return new Result(-80, "别纸KpNo不存在");
		}
		// 附页kpn4Flag 标示位
		String kpn4Flag = entity.getKpn4Flag();
		String kpn4 = entity.getKpn4();
		if ((kpn4Flag == null || kpn4Flag.isEmpty()) && kpn4 != null
				&& !kpn4.isEmpty()) {
			return new Result(-82, "当别纸KpNo的标示位为空时,别纸KpNo必须为空");
		}
		if (kpn4Flag != null && !kpn4Flag.isEmpty()
				&& (kpn4 == null || kpn4.isEmpty())) {
			return new Result(-82, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项");
		}
		if (kpn4Flag != null && !kpn4Flag.isEmpty()
				&& ProductType.get(kpn4Flag) == null) {
			return new Result(-82, "别纸KpNo标示位,只能是1、2或3");
		}
		if (kpn4 != null && !kpn4.isEmpty() && !informBO.isExisted(kpn4)) {
			return new Result(-82, "别纸KpNo不存在");
		}
		// 木工所NO
		String mgsn = entity.getMgsn();
		if (mgsn != null && !mgsn.isEmpty()) {
			String[] msgsns = mgsn.split(",");
			for (String s : msgsns) {
				if (informBO.isExisted(s)) continue;
				return new Result(-83, "木工所NO " + s + "不存在");
			}
		}
		return new Result(1, "OK");
	}

	@Override
	public String getForJs(Serializable id, String flag) {
		DhuoTp entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		// 标识flag不为空表示进行的是修改操作，可以进行验证判断是否可以修改
		if (flag != null && !flag.isEmpty()
				&& !DhStat.newly.key.equals(entity.getStat())) {
			return new Result(-1, "该订货合同状态为"
					+ DhStat.get(entity.getStat()).name + ",不能做修改操作").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public void saveJstj(JstjVO jstjVO) {
		DhuoTp dhuoTp = null;
		double htje, htdj, htzl;
		for (JstjVO entity : jstjVO.getItems()) {
			dhuoTp = dao.get(new DhuoTpPk(entity.getDhno(), entity.getLine()));
			if (dhuoTp == null) continue;
			htdj = entity.getHtdj() != null ? entity.getHtdj() : 0;
			htzl = dhuoTp.getHtzl() != null ? dhuoTp.getHtzl() : 0;
			htje = NumberUtils.format(htdj * htzl, 3);
			if (DhConstants.JSTJ_DL.equals(entity.getJstj())) {
				// 登录计算条件
				dhuoTp.setJstj(DhConstants.JSTJ_DL);
				// 订货确认表打印标示
				dhuoTp.setSfdy(Dybs.WDY.key);
			}
			else {
				dhuoTp.setJstj(DhConstants.JSTJ_WDL);
			}
			// 结算条件.预付款比例
			dhuoTp.setYfkn(entity.getYfkn());
			// 结算条件.出货款比例
			dhuoTp.setChkn(entity.getChkn());
			// 结算条件.后付款比例
			dhuoTp.setHfkn(entity.getHfkn());
			// 结算条件.期限
			dhuoTp.setQixn(entity.getQixn());
			// 通货区分
			dhuoTp.setThqf(entity.getThqf());
			// 合同单价
			dhuoTp.setHtdj(htdj);
			// 运费单价
			dhuoTp.setYfei(entity.getYfei());
			// 合同金额
			dhuoTp.setHtje(htje);
			// 运输方式
			dhuoTp.setYsfs(entity.getYsfs());
			dao.update(dhuoTp);
		}
	}

	@Override
	public List<DhuoTp> findDhqrPrints(String[] ids) {
		if (ids.length == 0) return null;
		String[] arr;
		DhuoTp entity = null;
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (String id : ids) {
			arr = id.split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			dhuoTps.add(entity);
			// 订货确认表打印完后，置打印标记为：已打印
			entity.setSfdy(Dybs.YDY.key);
			dao.update(entity);
		}
		return dhuoTps;
	}

	@Override
	public List<DhuoTp> findSyPrints(String[] ids, User user) {
		DhuoTp entity = null;
		List<DhuoTp> tpList = new ArrayList<DhuoTp>();
		String[] arr;
		Date date = new Date();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length < 2) {
				continue;
			}
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity != null) {
				tpList.add(entity);
				// 状态若已上锁，则不需要再修改状态
				if (!DhStat.locked.key.equals(entity.getStat())) {
					// 同时修改状态为1即上锁
					entity.setStat(DhStat.locked.key);
					entity.setLkno(user.getNo());
					entity.setLknm(user.getName());
					entity.setLkdt(date);
					dao.update(entity);
				}
			}
		}
		return tpList;
	}

	@Override
	public List<DhuoTp> findByIds(List<String> ids) {
		return dao.findByIds(ids);
	}

	@Override
	public DhuoTp get(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.get(id);
	}

	@Override
	public boolean isExisted(Serializable id) {
		return dao.isExisted(id);
	}

	@Override
	public boolean isExisted(String dhno, String line) {
		return dao.isExisted(dhno, line);
	}

	@Override
	public boolean isExistedByDhno(String dhno) {
		return dao.isExistedByDhno(dhno);
	}

	@Override
	public DefaultDhVO getDefaultDhInfo(String dhno) {
		DhuoTp dhuoTp = dao.get(dhno, null, null);
		String prefix = dhno.substring(0, 1);
		CodeNwx nwx = CodeNwx.getByPrefix(prefix);
		DefaultDhVO vo = new DefaultDhVO();
		vo.setNwai(nwx != null ? nwx.key : CodeNwx.nei.key);
		vo.setHuac(DhHc.HC_DJ.stat);
		vo.setKbjs(DhConstants.KBJS_Z);

		if (dhuoTp != null) {
			// 送货地址
			StringBuilder shdz = new StringBuilder();
			List<YongShdz> yongShdzs = yongBO.findShdz(dhuoTp.getUser());
			if (yongShdzs.size() > 0) {
				for (YongShdz yongShdz : yongShdzs) {
					if (shdz.length() > 0) {
						shdz.append(",").append(yongShdz.getAddr());
						continue;
					}
					shdz.append(yongShdz.getAddr());
				}
				vo.setShdz(shdz.toString());
			}

			vo.setUser(dhuoTp.getUser());
			vo.setAbbr(dhuoTp.getAbbr());
			vo.setName(dhuoTp.getName());
			vo.setAddr(dhuoTp.getAddr());

			// vo.setShno(dhuoTp.getShno());
			// vo.setShnm(dhuoTp.getShnm());
			vo.setSsno(dhuoTp.getSsno());
			vo.setSsnm(dhuoTp.getSsnm());

			vo.setGgno(dhuoTp.getGgno());
			vo.setPzno(dhuoTp.getPzno());
			vo.setFudw(dhuoTp.getFudw());
			vo.setFuzm(dhuoTp.getFuzm());
			vo.setFufm(dhuoTp.getFufm());
			vo.setChdx(dhuoTp.getChdx());
			vo.setHouu(dhuoTp.getHouu());
			vo.setKuan(dhuoTp.getKuan());
			vo.setCang(dhuoTp.getCang());
			vo.setCond(dhuoTp.getCond());
			vo.setCdnm(dhuoTp.getCdnm());

			vo.setJhqi(dhuoTp.getJhqi());
			vo.setJhnr(dhuoTp.getJhnr());
			vo.setHtzl(dhuoTp.getHtzl());
			vo.setJhqf(dhuoTp.getJhqf());
			vo.setJhfz(dhuoTp.getJhfz());
			vo.setJhzz(dhuoTp.getJhzz());
			vo.setKbsq(dhuoTp.getKbsq());
			vo.setKbsz(dhuoTp.getKbsz());
			vo.setKbzq(dhuoTp.getKbzq());
			vo.setKbzd(dhuoTp.getKbzd());
			vo.setKbzx(dhuoTp.getKbzx());
			vo.setKbzs(dhuoTp.getKbzs());
			vo.setKbao(dhuoTp.getKbao());
			vo.setYyan(dhuoTp.getYyan());
			vo.setDmfx(dhuoTp.getDmfx());
			vo.setFace(dhuoTp.getFace());
			vo.setRjie(dhuoTp.getRjie());
			vo.setNeij(dhuoTp.getNeij());
			vo.setHbbj(dhuoTp.getHbbj());
			vo.setTbno(dhuoTp.getTbno());
			vo.setTcno(dhuoTp.getTcno());
			vo.setYhbq(dhuoTp.getYhbq());
		}
		return vo;
	}

	@Override
	public String doSyqr(DhuoTp entity, User user) {
		DhuoTp dhuoTp = dao.get(new DhuoTpPk(entity.getDhno(), entity.getLine()));
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		if (!(DhStat.newly.key.equals(dhuoTp.getStat()) || DhStat.locked.key.equals(dhuoTp.getStat()))) {
			return new Result(-1, "该订货状态为" + DhStat.get(dhuoTp.getStat()).name
					+ ",不能进行仕样确认操作").toString();
		}
		parseDh(dhuoTp, entity);
		// 检查证明书张数
		YongMp yongMp = yongBO.get(entity.getUser());
		dhuoTp.setJcha(yongMp.getJcha());
		// 获取规格略称
		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, entity.getGgno());
		dhuoTp.setGgnm(codeItem != null ? codeItem.getValue() : null);
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_015, entity.getKbao());
		if (codeItem != null) {
			// 获取包装材料重量(Kg)
			if (codeItem.getValue() != null) {
				dhuoTp.setBzcl(Integer.valueOf(codeItem.getValue()));
			}
			if (codeItem.getRemark() != null) {
				// 获取 垫木重量(Kg)
				dhuoTp.setDmzl((Integer.valueOf(codeItem.getRemark())));
			}
		}
		dhuoTp.setStat(DhStat.confirm.key);
		dhuoTp.setSydt(new Date());
		// dhuoTp.setSyno(user.getNo());
		dhuoTp.setSynm(user.getName());
		dao.update(dhuoTp);
		// 更新用途代码与用途名称
		updateCond(entity.getCond(), entity.getCdnm());
		return Result.SUCCESS;
	}

	@Override
	public void doSyqr(String[] ids, User user) {
		DhuoTp dhuoTp = null;
		String[] arr = null;
		Result result = null;
		for (String id : ids) {
			arr = id.split("-");
			if (arr.length != 2) continue;
			dhuoTp = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (dhuoTp == null) {
				throw new CocoException(-1, "订货合同" + id + "不存在");
			}
			if (!(DhStat.newly.key.equals(dhuoTp.getStat()) || DhStat.locked.key.equals(dhuoTp.getStat()))) {
				throw new CocoException(-1, "订货合同" + id + "状态为"
						+ DhStat.get(dhuoTp.getStat()).name + ",不能进行仕样确认操作");
			}
			result = checkZzsy(dhuoTp);
			if (result.getCode() <= 0) {
				throw new CocoException(-1, "订货合同" + id + "不能进行仕样确认操作。原因如下："
						+ result.getMessage());
			}
			dhuoTp.setStat(DhStat.confirm.key);
			dhuoTp.setSydt(new Date());
			dhuoTp.setSynm(user.getName());
			dao.update(dhuoTp);
		}
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param cond
	 * @param cdnm
	 */
	private void updateCond(String cond, String cdnm) {
		// 更新用途代码与用途名称
		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.SINO_COND_CDNM, cond);
		if (codeItem == null) {
			codeItem = new CodeItem();
			codeItem.setKey(cond);
			codeItem.setValue(cdnm);
			Code code = new Code();
			code.setId(CmnConstants.SINO_COND_CDNM);
			codeItem.setCode(code);
			codeBo.saveItem(codeItem);
		}
		else if (!cdnm.equals(codeItem.getValue())) {
			codeItem.setValue(cdnm);
			codeBo.upddateItem(codeItem);
		}
	}

	@Override
	public String doSybc(DhuoTp entity, User user) {
		DhuoTp dhuoTp = dao.get(new DhuoTpPk(entity.getDhno(), entity.getLine()));
		if (dhuoTp == null) {
			return new Result(-1, "该订货合同不存在").toString();
		}
		if (!(DhStat.newly.key.equals(dhuoTp.getStat()) || DhStat.locked.key.equals(dhuoTp.getStat()))) {
			return new Result(-1, "该订货状态为" + DhStat.get(dhuoTp.getStat()).name
					+ ",不能进行仕样保存操作").toString();
		}
		parseDh(dhuoTp, entity);
		// 检查证明书张数
		YongMp yongMp = yongBO.get(entity.getUser());
		dhuoTp.setJcha(yongMp.getJcha());
		// 获取规格略称
		CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, entity.getGgno());
		dhuoTp.setGgnm(codeItem != null ? codeItem.getValue() : null);
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_015, entity.getKbao());
		if (codeItem != null) {
			// 获取包装材料重量(Kg)
			if (codeItem.getValue() != null) {
				dhuoTp.setBzcl(Integer.valueOf(codeItem.getValue()));
			}
			if (codeItem.getRemark() != null) {
				// 获取 垫木重量(Kg)
				dhuoTp.setDmzl((Integer.valueOf(codeItem.getRemark())));
			}
		}
		dhuoTp.setXddt(new Date());
		dhuoTp.setXdnm(user.getName());
		dao.update(dhuoTp);
		// 更新用途代码与用途名称
		updateCond(entity.getCond(), entity.getCdnm());
		return Result.SUCCESS;
	}

	@Override
	public String lock(String[] ids, User user) {
		DhuoTp entity = null;
		String[] arr;
		Date date = new Date();
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			// 上锁
			if (!DhStat.newly.key.equals(entity.getStat())) {
				return new Result(-1, "订货合同" + arr[0] + "-" + arr[1] + "状态为"
						+ DhStat.get(entity.getStat()).name + ",不能再做上锁操作").toString();
			}
			entity.setStat(DhStat.locked.key);
			entity.setLkno(user.getNo());
			entity.setLknm(user.getName());
			entity.setLkdt(date);
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
		return Result.SUCCESS;
	}

	@Override
	public String deLock(String[] ids, User user) {
		DhuoTp entity = null;
		String[] arr;
		Date date = new Date();
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			// 解锁
			if (!DhStat.locked.key.equals(entity.getStat())) {
				return new Result(-1, "订货合同" + arr[0] + "-" + arr[1] + "状态为"
						+ DhStat.get(entity.getStat()).name + ",不能做解锁操作").toString();
			}
			entity.setStat(DhStat.newly.key);
			entity.setJsno(user.getNo());
			entity.setJsnm(user.getName());
			entity.setJsdt(date);
			entity.setXddt(null);
			entity.setXdnm(null);
			entity.setSynm(null);
			entity.setSydt(null);
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
		return Result.SUCCESS;
	}

	@Override
	public String finish(String[] ids, User user) {
		DhuoTp entity = null;
		String[] arr;
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			// 订货合同完成
			// if (!DhStat.assign.key.equals(entity.getStat())) {
			// return new Result(-1, "订货合同" + arr[0] + "-" + arr[1] + "还未"
			// + DhStat.assign.name + ",不能做订货合同完成操作").toString();
			// }
			entity.setStat(DhStat.over.key);
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
		return Result.SUCCESS;
	}

	@Override
	public String cancelFinish(String[] ids, User user) {
		DhuoTp entity = null;
		String[] arr;
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			// 取消订货合同完成
			if (!DhStat.over.key.equals(entity.getStat())) {
				return new Result(-1, "订货合同" + arr[0] + "-" + arr[1] + "还未"
						+ DhStat.over.name + ",不能做取消订货合同完成操作").toString();
			}
			if (zsBO.isFpForDh(ids[i])) {
				entity.setStat(DhStat.assign.key);
			}
			else {
				entity.setStat(DhStat.confirm.key);
			}
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
		return Result.SUCCESS;
	}

	@Override
	public void update(DhuoTp tp) {
		dao.update(tp);
	}

	@Override
	public DhuoTp getRef(Serializable id) {
		return dao.getRef(id);
	}

	@Override
	public String getKbjs(String kbjs, String pzno, Double kuan, Double cang) {
		if (kbjs == null || !DhConstants.KBJS_Z.equals(kbjs)) {
			return new Result(1, "").toString();
		}
		if ((kuan == null || kuan <= 0) && (cang == null || cang <= 0)) {
			return new Result(1, "").toString();
		}
		if (pzno != null && !pzno.isEmpty()
				&& Code118.coil.key.equals(pzno.substring(0, 1))) {
			return new Result(1, "P11").toString();
		}
		// 捆包计算
		// 当捆包计算为1时，尺寸长和尺寸宽都小于850时，捆包形式必须填写为A21
		if (kuan < DhConstants.CC_KOC && cang < DhConstants.CC_KOC) {
			return new Result(1, "A21").toString();
		}
		// 当捆包计算为1时，尺寸长或尺寸宽只要有一项目是大于等于850时，捆包形式必须填写为A31
		return new Result(1, "A31").toString();
	}

	@Override
	public void fetchDhjdmx(DhjdVO vo, OutputStream os) {
		String dhno = vo.getDhno().toUpperCase();
		String lineStart = vo.getLineStart();
		String lineEnd = vo.getLineEnd();
		if (lineEnd == null || lineEnd.isEmpty()) {
			lineEnd = lineStart;
		}
		String dhnoStart = dhno + lineStart;
		String dhnoEnd = dhno + lineEnd;
		Map<DhuoVO, List<Dhjdmx>> dhjdmxs = new LinkedHashMap<DhuoVO, List<Dhjdmx>>();
		List<Dhjdmx> mxs = dao.fetchDhjdmxs(dhnoStart, dhnoEnd);
		List<Dhjdmx> $mxs = null;
		DhuoVO dhuoVO = null;
		String line = null;
		int start = Integer.parseInt(lineStart);
		int end = Integer.parseInt(lineEnd);
		while (start <= end) {
			line = (start >= 10 ? "" + start : "0" + start);
			start++;
			dhuoVO = dao.getDhjd(dhno, line);
			if (dhuoVO == null) continue;
			$mxs = new ArrayList<Dhjdmx>();
			dhjdmxs.put(dhuoVO, $mxs);
			for (Dhjdmx mx : mxs) {
				if ((dhno + line).equals(mx.getDhno())) {
					$mxs.add(mx);
				}
			}
		}
		vo.setDhjdmxs(dhjdmxs);
		ExcelUtils.fillData(dhjdPath, vo, dhjdExec, os);
	}

	@Override
	public DhuoTp getLatest() {
		return dao.getLatest();
	}

	@Override
	public String cancelSyqr(String[] ids, User user) {
		String[] arr;
		DhuoTp entity = null;
		Date date = new Date();
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length < 2) {
				continue;
			}
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			if (!DhStat.confirm.key.equals(entity.getStat())) {
				return new Result(-1, "订货合同No" + arr[0] + "-" + arr[1] + "状态为"
						+ DhStat.get(entity.getStat()).name + ",不能做取消仕样确认操作").toString();
			}
			if (DhStat.assign.key.equals(entity.getStat())
					|| DhStat.over.key.equals(entity.getStat())) {
				return new Result(-1, "订货合同No" + arr[0] + "-" + arr[1] + "状态为"
						+ DhStat.get(entity.getStat()).name + ",不能做取消仕样确认操作").toString();
			}
			entity.setStat(DhStat.locked.key);
			entity.setLkdt(date);
			entity.setLknm(user.getName());
			entity.setLkno(user.getNo());
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
		return Result.SUCCESS;
	}

	@Override
	public void updates(List<DhuoTp> dhuoTps) {
		for (DhuoTp dhuoTp : dhuoTps) {
			dao.update(dhuoTp);
		}
	}

	@Override
	public int getPageSize(String dhno, Integer size) {
		Long count = dao.count(dhno);
		if (count == null || count.longValue() == 0) {
			return 0;
		}
		int pageSize = (int) count.intValue() / size;
		if (count % size != 0) {
			pageSize++;
		}
		return pageSize;
	}

	@Override
	public void updateSfdy(List<String> dhnos, List<String> lines, String sfdy) {
		dao.updateSfdy(dhnos, lines, sfdy);
	}

	@Override
	public List<String> queryIds(String sfdy) {
		return dao.queryIds(sfdy);
	}

	@Override
	public void queryFsp(QEntity<FspVO> qentity) {
		sqlDao.query(qentity);
	}

	@Override
	public void queryGxht(QEntity<GxhtVO> qentity) {
		sqlDao.query(qentity);
	}

	@Override
	public String doCjpHt(FspHtVO vo, User user) {
		// 判断订货合同是否存在
		String dhno = vo.getDhno();
		DhuoTp $dhuotp = dao.get(dhno, null, null);
		int line = $dhuotp != null ? Integer.parseInt($dhuotp.getLine()) + 1
				: 1;
		CodeItem codeItem = null;
		// 获取用户
		String $user = vo.getUser();
		YongMp yongMp = yongBO.get($user);
		Map<String, String> dxls = codeBo.findGMtoWB();
		String ggno, face, xpzk = vo.getXpzk(), pzno, cond = "RZZZ", cdnm = "杂罐", yuny = null, ggnm = null, chan = vo.getChan();
		boolean isCoil = EXpzk.JZP_KEY.equals(xpzk) ? true : false;
		// String f = isCoil ? "2" : "1";
		String f = isCoil ? Code119.grade2.key : Code119.prime.key;
		// String s = "3";
		String s = Code119.grade3.key;
		if (ChanType.bhg.key.equals(chan)) {
			s = Code119.grade3.key;
		}
		else if (ChanType.fs.key.equals(chan)) {
			s = Code119.grade4.key;
		}
		else if (ChanType.fc.key.equals(chan)) {
			s = Code119.grade5.key;
		}
		pzno = f + s;
		double htdj = vo.getHtdj() != null ? vo.getHtdj() : 0;
		double htje, htzl;
		Double xpho, xpkn, xpcn;
		String ymax = null, ymin = null;
		String v = null;
		Date crea = new Date();
		DhuoTp dhuoTp = null;
		List<FspZlVO> vos = null;
		String kbao = null;
		Map<DhuoTp, List<String>> maps = new HashMap<DhuoTp, List<String>>();
		for (FspTxVO fvo : vo.getItems()) {
			ggno = fvo.getGgno();
			yuny = fvo.getYuny();
			face = fvo.getFace();
			xpho = fvo.getXpho();
			xpkn = fvo.getXpkn();
			xpcn = fvo.getXpcn();
			if (isCoil) {
				kbao = "S01";
			}
			else if (xpkn < DhConstants.CC_KOC && xpcn < DhConstants.CC_KOC) {
				// 捆包计算
				// 当捆包计算为1时，尺寸长和尺寸宽都小于850时，捆包形式必须填写为B21
				kbao = "B21";
			}
			else {
				// 当捆包计算为1时，尺寸长或尺寸宽只要有一项目是大于等于850时，捆包形式必须填写为B31
				kbao = "B31";
			}
			codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, ggno);
			ggnm = codeItem != null ? codeItem.getValue() : null;

			// 根据规格后两位111获取硬度上下限
			if (ggno.length() == 4) {
				codeItem = codeBo.getCodeItem(CmnConstants.CODE_111, ggno.substring(2, 4));
				if (codeItem != null && (v = codeItem.getValue()) != null
						&& !v.isEmpty()) {
					if (v.length() == 2) {
						ymin = v;
					}
					else if (v.length() == 4) {
						// 规格代码值的前两位为下限
						ymin = v.substring(0, 2);
						// 规格代码值的后两位为上限
						ymax = v.substring(2, 4);
					}
				}
			}
			vos = dao.queryFsp(yuny, xpho, xpkn, xpcn, face, xpzk, chan, dxls);
			for (FspZlVO fzo : vos) {
				dhuoTp = new DhuoTp();
				dhuoTp.setCrea(crea);
				dhuoTp.setDhno(dhno);
				dhuoTp.setLine(line >= 10 ? line + "" : "0" + line);
				dhuoTp.setFudw(fzo.getFudw());
				dhuoTp.setFuzm(fzo.getFuzm());
				dhuoTp.setFufm(fzo.getFufm());
				dhuoTp.setChdx(!fzo.getFuzm().equals(fzo.getFufm()) ? "D1" : "");
				dhuoTp.setNwai(CodeNwx.NX);

				dhuoTp.setGgno(ggno);
				dhuoTp.setYuny(yuny);
				dhuoTp.setGgnm(ggnm);
				dhuoTp.setPzno(pzno);

				dhuoTp.setHouu(xpho);
				dhuoTp.setKuan(xpkn);

				dhuoTp.setCond(cond);
				dhuoTp.setCdnm(cdnm);
				dhuoTp.setUser(vo.getUser());
				dhuoTp.setAbbr(vo.getAbbr());
				dhuoTp.setName(vo.getName());
				dhuoTp.setAddr(vo.getAddr());
				// dhuoTp.setShno(vo.getShno());
				// dhuoTp.setShnm(vo.getShnm());
				dhuoTp.setSsno(yongMp.getSsno());
				dhuoTp.setSsnm(yongMp.getSsnm());
				dhuoTp.setJcha(yongMp.getJcha());
				dhuoTp.setJhqi(vo.getJhqi());

				dhuoTp.setJhnr(ZlnrCode.nr6.key);
				dhuoTp.setHtzl(NumberUtils.format(fzo.getZpzl() / 1000d, 3));
				dhuoTp.setJhqf(DhQf.QF_W.stat);
				dhuoTp.setJhfz(-10);
				dhuoTp.setJhzz(10);

				dhuoTp.setYtyp(CodeTyzl.DOS.key);
				dhuoTp.setHxzi(ZzsyConstants.HXZI);
				dhuoTp.setHszi(ZzsyConstants.HSZI);
				dhuoTp.setKxzi(ZzsyConstants.KXZI);
				dhuoTp.setKszi(ZzsyConstants.KSZI);
				dhuoTp.setCxzi(ZzsyConstants.CXZI);
				if (isCoil) {
					dhuoTp.setCang(null);
					dhuoTp.setKbzq(DhJzqf.JZ_5.stat);
					dhuoTp.setKbzd(DhConstants.KBZD_T);
					dhuoTp.setKbzx(2d);
					dhuoTp.setKbzs(8d);
					dhuoTp.setRjie("0");
					dhuoTp.setNeij("508");
					dhuoTp.setCszi(0.00);
				}
				else {
					dhuoTp.setCang(xpcn);
					dhuoTp.setKbsq(DhConstants.KBSQ);
					dhuoTp.setKbsz("1500");
					dhuoTp.setYyan(xpkn < xpcn ? DhYy.YY_CB.key
							: DhYy.YY_DB.key);
					dhuoTp.setDmfx(EDm.C.key);
					dhuoTp.setCszi(ZzsyConstants.CSZI);
				}
				dhuoTp.setKbjs(DhConstants.KBJS_Z);
				dhuoTp.setHuac(DhHc.HC_DJ.stat);
				dhuoTp.setKbao(kbao);
				dhuoTp.setFace(face);

				dhuoTp.setYmin(ymin);
				dhuoTp.setYmax(ymax);

				String fuzs = "0";
				String fuzx = "0";
				// 根据附着量.单位及正面024获取附着量正面上/下限
				codeItem = codeBo.getCodeItem(CmnConstants.CODE_024, (fzo.getFudw() + fzo.getFuzm()));
				if (codeItem != null && (v = codeItem.getValue()) != null
						&& !v.isEmpty()) {
					if (v.length() == 8) {
						// 附着量代码值的前四位（中间加小数点）为下限
						fuzx = v.substring(0, 2) + "." + v.substring(2, 4);
						// 附着量代码值的后四位（中间加小数点）为上限
						fuzs = v.substring(4, 6) + "." + v.substring(6, 8);
					}
				}
				String fufs = "0";
				String fufx = "0";
				// 根据附着量.单位及反面024获取附着量反面上/下限
				codeItem = codeBo.getCodeItem(CmnConstants.CODE_024, (fzo.getFudw() + fzo.getFufm()));
				if (codeItem != null && (v = codeItem.getValue()) != null
						&& !v.isEmpty()) {
					if (v.length() == 8) {
						// 附着量代码值的前四位（中间加小数点）为下限
						fufx = v.substring(0, 2) + "." + v.substring(2, 4);
						// 附着量代码值的后四位（中间加小数点）为上限
						fufs = v.substring(4, 6) + "." + v.substring(6, 8);
					}
				}
				dhuoTp.setFuzs(Double.valueOf(fuzs));
				dhuoTp.setFuzx(Double.valueOf(fuzx));
				dhuoTp.setFufs(Double.valueOf(fufs));
				dhuoTp.setFufx(Double.valueOf(fufx));

				// 默认状态为0即初始
				dhuoTp.setStat(DhStat.assign.key);
				// 默认结算条件.设定标志 未登录 ——0
				dhuoTp.setJstj(DhConstants.JSTJ_WDL);
				// 订货确认表打印标示
				dhuoTp.setSfdy(Dybs.WDY.key);
				// 换算尺寸
				// 长默认为订货尺寸.长
				dhuoTp.setHngc(dhuoTp.getCang());
				// 厚默认为订货尺寸.厚
				dhuoTp.setHngh(dhuoTp.getHouu());
				// 宽默认为订货尺寸.宽
				dhuoTp.setHngk(dhuoTp.getKuan());
				dhuoTp.setCjno(user.getNo());
				dhuoTp.setCjnm(user.getName());
				dhuoTp.setDdno(user.getNo());
				dhuoTp.setDdnm(user.getName());

				dhuoTp.setDmzl(vo.getDmzl());
				dhuoTp.setBzcl(vo.getBzcl());

				htzl = dhuoTp.getHtzl();
				htje = NumberUtils.format(htdj * htzl, 2);
				// 分配量
				dhuoTp.setFpln(htzl);
				// 登录计算条件
				dhuoTp.setJstj(DhConstants.JSTJ_DL);
				// 订货确认表打印标示
				dhuoTp.setSfdy(Dybs.WDY.key);
				// 结算条件.预付款比例
				dhuoTp.setYfkn(null);
				// 结算条件.出货款比例
				dhuoTp.setChkn(100);
				// 结算条件.后付款比例
				dhuoTp.setHfkn(null);
				// 结算条件.期限
				dhuoTp.setQixn(null);
				// 通货区分
				dhuoTp.setThqf(Code013.rmb.key);
				// 合同单价
				dhuoTp.setHtdj(htdj);
				// 运费单价
				dhuoTp.setYfei(null);
				// 合同金额
				dhuoTp.setHtje(htje);
				// 运输方式
				dhuoTp.setYsfs(CodeYsfs.ZT.key);

				maps.put(dhuoTp, fzo.getJbnos());
				line++;
			}
		}
		if (!maps.isEmpty()) {
			Iterator<DhuoTp> iterator = maps.keySet().iterator();
			DhuoTp entity = null;
			List<String> jbnos = null;
			while (iterator.hasNext()) {
				entity = iterator.next();
				jbnos = maps.get(entity);
				dao.save(entity);
				dao.fpCjp(jbnos, entity.getDhno() + entity.getLine(), entity.getAbbr(), entity.getPzno());
			}
		}
		return Result.SUCCESS;
	}

	@Override
	public String delCjpHt(String[] ids) {
		DhuoTp entity = null;
		String[] id;
		String pzno;
		DhuoTpPk dhuoTpPk;
		List<DhuoTpPk> dhuoTpPks = new ArrayList<DhuoTpPk>();
		for (int i = 0; i < ids.length; i++) {
			id = ids[i].split("-");
			if (id == null || id.length != 2) {
				continue;
			}
			dhuoTpPk = new DhuoTpPk(id[0], id[1]);
			entity = dao.get(dhuoTpPk);
			if (entity == null) {
				return new Result(-1, "订货合同No." + id[0] + "-" + id[1] + ",不存在").toString();
			}
			pzno = entity.getPzno();
			if (!"W".equals(id[0].substring(0, 1))
					|| Code119.prime.key.equals(pzno.substring(1, 2))) {
				return new Result(-1, "订货合同No." + id[0] + "-" + id[1]
						+ "为一级品订货合同,不能通过该功能将其删除").toString();
			}
			if (zpBo.isExistedByDhno(id[0] + id[1])) {
				return new Result(-1, "在数据中订货合同No." + id[0] + "-" + id[1]
						+ "存在制品,不能通过该功能将其删除").toString();
			}
			dhuoTpPks.add(dhuoTpPk);
		}
		dao.deletes(dhuoTpPks);
		return Result.SUCCESS;
	}

	@Override
	public GxhtTp getGxht(Serializable id) {
		return gxhtDAO.get(id);
	}

	@Override
	public void saveGxht(GxhtTp entity) {
		String dhno = entity.getDhno();
		if (gxhtDAO.isExisted(dhno)) {
			gxhtDAO.update(entity);
			return;
		}
		if ("D".equalsIgnoreCase(dhno.substring(0, 1))) {
			entity.setKhh("中国建设银行股份有限公司福州马尾支行");
			entity.setZh("35001616507050003327");
		}
		else {
			entity.setKhh("中国银行马江支行");
			entity.setZh("00553008091014");
		}
		gxhtDAO.save(entity);
	}

	@Override
	public void updateGxht(GxhtTp entity) {
		gxhtDAO.update(entity);
	}

	@Override
	public boolean isExistedGxht(String id) {
		return gxhtDAO.isExisted(id);
	}

	@Override
	public String getGxhtForJs(String id) {
		if (!dao.isExistedByDhno(id)) {
			return new Result(-1, "购销合同" + id + "不存在").toString();
		}
		GxhtTp gxhtTp = gxhtDAO.get(id);
		if (gxhtTp == null) {
			gxhtTp = new GxhtTp();
			gxhtTp.setHwpm("W".equals(id.substring(0, 1)) ? "马口铁次级品" : "马口铁一级品");
		}
		return new Result(gxhtTp).toJsObject();
	}

	@Override
	public GxhtTp getByUser(String user) {
		return gxhtDAO.getByUser(user);
	}

	@Override
	public void queryDhjd(QEntity<DhVO> qentity) {
		sqlDao.query(qentity);
	}

	@Override
	public void doHtqi(String[] ids, String htqi) {
		DhuoTp entity = null;
		String[] arr;
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			entity.setHtqi(htqi);
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
	}

	@Override
	public void updateKhno(String[] ids, String khno) {
		DhuoTp entity = null;
		String[] arr;
		List<DhuoTp> dhuoTps = new ArrayList<DhuoTp>();
		for (int i = 0; i < ids.length; i++) {
			arr = ids[i].split("-");
			if (arr.length != 2) continue;
			entity = dao.get((new DhuoTpPk(arr[0], arr[1])));
			if (entity == null) continue;
			zxzsBO.updateKhno(arr[0], arr[1], khno);
			entity.setKhno(khno);
			dhuoTps.add(entity);
		}
		updates(dhuoTps);
	}

	@Override
	public void fetchDhuoChDatas(DhuoChVO vo, OutputStream os) {
		vo.setDatas(dao.queryDhuoChVO(vo));
		ExcelUtils.fillData(dhuoChPath, vo, dhuoChExec, os);
	}

	@Override
	public void updateStat(String dhno, String line, String stat) {
		dao.updateStat(dhno, line, stat);
	}

	public String getDhjdPath() {
		return dhjdPath;
	}

	public void setDhjdPath(String dhjdPath) {
		this.dhjdPath = dhjdPath;
	}

	public IDhDAO getDao() {
		return dao;
	}

	public void setDao(IDhDAO dao) {
		this.dao = dao;
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IZzsyBO getZzsyBo() {
		return zzsyBo;
	}

	public void setZzsyBo(IZzsyBO zzsyBo) {
		this.zzsyBo = zzsyBo;
	}

	public IZpBO getZpBo() {
		return zpBo;
	}

	public void setZpBo(IZpBO zpBo) {
		this.zpBo = zpBo;
	}

	public IZsBO getZsBO() {
		return zsBO;
	}

	public void setZsBO(IZsBO zsBO) {
		this.zsBO = zsBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

	public ExcelbookDataExecuter<DhjdVO> getDhjdExec() {
		return dhjdExec;
	}

	public void setDhjdExec(ExcelbookDataExecuter<DhjdVO> dhjdExec) {
		this.dhjdExec = dhjdExec;
	}

	public IYongBO getYongBO() {
		return yongBO;
	}

	public void setYongBO(IYongBO yongBO) {
		this.yongBO = yongBO;
	}

	public IInformBO getInformBO() {
		return informBO;
	}

	public void setInformBO(IInformBO informBO) {
		this.informBO = informBO;
	}

	public IPersonBO getPersonBO() {
		return personBO;
	}

	public void setPersonBO(IPersonBO personBO) {
		this.personBO = personBO;
	}

	public ISqlDAO getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISqlDAO sqlDao) {
		this.sqlDao = sqlDao;
	}

	public IGxhtDAO getGxhtDAO() {
		return gxhtDAO;
	}

	public void setGxhtDAO(IGxhtDAO gxhtDAO) {
		this.gxhtDAO = gxhtDAO;
	}

	public IZxzsBO getZxzsBO() {
		return zxzsBO;
	}

	public void setZxzsBO(IZxzsBO zxzsBO) {
		this.zxzsBO = zxzsBO;
	}

	public ExcelbookDataExecuter<DhuoChVO> getDhuoChExec() {
		return dhuoChExec;
	}

	public void setDhuoChExec(ExcelbookDataExecuter<DhuoChVO> dhuoChExec) {
		this.dhuoChExec = dhuoChExec;
	}

	public String getDhuoChPath() {
		return dhuoChPath;
	}

	public void setDhuoChPath(String dhuoChPath) {
		this.dhuoChPath = dhuoChPath;
	}

}
