package com.quanta.sino.ycai.bo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.JpaUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.IConvertBO;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.Attachment;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.vo.ConvertErrorVO;
import com.coco.sys.vo.ConvertRowVO;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.cmn.bo.api.ICmnBO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.CodeSpbh;
import com.quanta.sino.cmn.constants.Convert;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.Seq;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpnoCd;
import com.quanta.sino.orm.JchaTp;
import com.quanta.sino.orm.JinhLp;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.WwhtTpPk;
import com.quanta.sino.orm.Ybcbmx;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.Ycaicb;
import com.quanta.sino.util.SinoUtils;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.constants.YcaiConstants;
import com.quanta.sino.ycai.dao.api.IJchatpDAO;
import com.quanta.sino.ycai.dao.api.IJinhlpDAO;
import com.quanta.sino.ycai.dao.api.IYcaicbDAO;
import com.quanta.sino.ycai.dao.api.IYcaitpDAO;
import com.quanta.sino.ycai.vo.DrVO;
import com.quanta.sino.ycai.vo.JchatpQE;
import com.quanta.sino.ycai.vo.YcaiQE;

/**
 * <p>
 * 原板业务接口
 * </p>
 * <p>
 * create: 2010-12-21 下午05:54:38
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class YcaitpBO implements IYcaitpBO {

	private IYcaitpDAO dao;

	/**
	 * 进货日志数据访问层接口
	 */
	private IJinhlpDAO jinhlpDAO;

	/**
	 * 原板检查证明书数据操作接口
	 */
	private IJchatpDAO jchatpDAO;
	/**
	 * 供应商合同接口
	 */
	private IWwhtBO bo;
	/**
	 * 码表接口
	 */
	private ICodeBO codeBo;
	/**
	 * 序号接口
	 */
	private ISeqBO seqBo;

	/**
	 * 附件业务层接口
	 */
	private IAttachmentBO attachmentBO;

	/**
	 * 转换业务接口
	 */
	private IConvertBO convertBO;

	/**
	 * 原板成本数据访问接口
	 */
	private IYcaicbDAO ycaicbDAO;

	/**
	 * 中日达生产公共业务实现
	 */
	private ICmnBO cmnBO;

	/**
	 * 盘点机文件路径在码表中的键值
	 */
	final static String SINO_UPLOAD_PATH_KEY = "0";

	@Override
	public void saveEntity(YcaiTp entity) {
		dao.save(entity);
	}

	@Override
	public void updateEntity(YcaiTp entity) {
		dao.update(entity);
	}

	@Override
	public void save(YcaiTp entity) {
		if (dao.isExisted(entity.getZzsj())) {
			throw new CocoException(-1, "制造商卷板No已经存在");
		}
		// 原板序号生成
		String jbno = entity.getJbno();
		if (dao.isExistedYcaiByJbno(jbno)) {
			throw new CocoException(-1, "原材卷板No" + jbno + "已存在。请重新登录");
		}
		// 计算卷板长度
		int jbcn = SinoUtils.calculate(entity.getXpkn(), entity.getXpho(), entity.getTun());
		if (jbcn < 0) {
			throw new CocoException(-1, "计算卷板长有误");
		}
		// 获取商品编号
		String spbh = CodeSpbh.getByHouu(entity.getXpho());
		if (spbh == null || spbh.isEmpty()) {
			throw new CocoException(-1, "根据现品厚不能获得商品编号; ");
		}
		// 设置商品编号
		entity.setSpbh(spbh);
		entity.setJbno(jbno);
		entity.setJbcn(jbcn);
		CodeItem item = null;
		item = codeBo.getUniqueItemByValue(CmnConstants.CODE_020, entity.getNjno());
		// 由码表取内径代码
		entity.setNjno(item == null ? "" : item.getKey());
		item = codeBo.getUniqueItemByValue(CmnConstants.CODE_013, entity.getThqf());
		// 取通货区分
		entity.setThqf(item == null ? "" : item.getKey());
		Date date = new Date();
		entity.setCrea(date);
		// 原板最初为X堆场
		entity.setDuic(DC.X.name);
		// 库位
		entity.setKw(DC.X.name);
		// 原板初始状态
		entity.setStat(YbStat.CS.stat);
		// 删除标志
		entity.setScbj(EScbj.CS.key);
		// 现品状况为原板
		entity.setXpzk(EXpzk.SC_KEY);
		// 分配/余材标记
		entity.setFpyc(EFpyc.CS.key);
		// 原板成本
		entity.setYbcb(0d);
		// 钢种类型为规格的第一位
		entity.setGzlx(entity.getGgno().substring(0, 1));
		item = codeBo.getCodeItem(CmnConstants.CODE_012, entity.getZzsd());
		// 是否保税品
		entity.setSfbs(item != null ? item.getRemark() : null);
		// 设置该预备字段为空
		entity.setRsv1(null);
		// 设置装入重量
		entity.setZrzl(entity.getZpzl());
		// 写进货日志
		JinhLp jhrz = this.parseJinhLp(entity, date);
		// 保存进货日志
		jinhlpDAO.save(jhrz);
		// 修改合同的到货数量
		bo.updateDhsl(new WwhtTpPk(entity.getYbno(), entity.getLine()), entity.getTun());
		// 保存原板信息
		dao.save(entity);
		// 原板序号生成
		seqBo.sequence(Seq.ybno.name);
	}

	@Override
	public String getForJs(Serializable id) {
		WwhtTp tp = this.getWwht(id);
		if (tp == null) {
			return new Result(-1, "该供应商合同不存在").toString();
		}
		YcaiTp ycaiTp = new YcaiTp();
		ycaiTp.setYcbr(tp.getAbbr());
		ycaiTp.setCgdj(tp.getHtdj());
		ycaiTp.setDeng(tp.getFpdj());
		ycaiTp.setYbno(tp.getHtno());
		ycaiTp.setLine(tp.getLine());
		ycaiTp.setFace(tp.getFace());
		ycaiTp.setGgnm(tp.getZrgg());
		ycaiTp.setPzno(tp.getPzno());
		ycaiTp.setSsno(tp.getSsno());
		ycaiTp.setXpho(tp.getHouu());
		ycaiTp.setXpkn(tp.getKuan());
		// 制造商规格略称
		ycaiTp.setYblc(tp.getZzgg());
		ycaiTp.setZzny(tp.getHtdt());
		ycaiTp.setZzsd(tp.getZzsd());
		ycaiTp.setNjno(tp.getNeij());
		CodeItem item = null;
		item = codeBo.getCodeItem(CmnConstants.CODE_013, tp.getThqf());
		ycaiTp.setThqf(item == null ? "" : item.getValue());
		item = codeBo.getCodeItem(CmnConstants.CODE_018, tp.getZzgg());
		ycaiTp.setGgno(item == null ? "" : item.getValue());
		item = codeBo.getCodeItem(CmnConstants.CODE_019, tp.getZzgg());
		ycaiTp.setYuny(item == null ? "" : item.getValue());
		// 设置预备1为供应商名称
		ycaiTp.setRsv1(tp.getZzsm());
		return new Result(ycaiTp).toJsObject();
	}

	@Override
	public YcaiTp get(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.get(id);
	}

	@Override
	public WwhtTp getWwht(Serializable id) {
		if (id == null) {
			return null;
		}
		return bo.get(id);
	}

	@Override
	public void query(QEntity<YcaiTp> qentity) {
		dao.query(qentity);
	}

	@Override
	public void update(YcaiTp entity) {
		// 原卷板信息tp
		YcaiTp tp = dao.get(entity.getJbno());
		if (tp == null) {
			throw new CocoException(-1, "原材卷板No." + entity.getJbno() + "不存在");
		}
		if (!(YbStat.CS.stat.equals(tp.getStat()) || YbStat.YRK.stat.equals(tp.getStat()))) {
			throw new CocoException(-1, "原材卷板No." + entity.getJbno() + "状态为"
					+ YbStat.get(tp.getStat()).name + ",不能做修改操作");
		}
		if (dao.isExisted(entity.getZzsj())
				&& !tp.getZzsj().equals(entity.getZzsj())) {
			throw new CocoException(-1, "制造商卷板No." + entity.getZzsj() + "已经存在");
		}
		WwhtTp wwhtTp = bo.get(new WwhtTpPk(entity.getYbno(), entity.getLine()));
		if (wwhtTp == null) {
			throw new CocoException(-1, "供应商合同No" + entity.getYbno() + "-"
					+ entity.getLine() + "不存在");
		}

		// 原制品重量吨
		double yzpzl = tp.getTun() != null ? tp.getTun() : 0;
		// 现制品重量
		double tun = entity.getTun() != null ? entity.getTun() : 0;
		// 原订货合同号
		String yybno = tp.getYbno();
		// 原订货行号
		String yline = tp.getLine();

		tp.setJbcn(SinoUtils.calculate(entity.getXpkn(), entity.getXpho(), entity.getTun()));
		tp.setUpda(new Date());
		CodeItem item = null;
		//
		item = codeBo.getUniqueItemByValue(CmnConstants.CODE_020, entity.getNjno());
		tp.setNjno(item == null ? "" : item.getKey());
		tp.setYbno(entity.getYbno());
		tp.setLine(entity.getLine());
		tp.setZpzl(entity.getZpzl());
		tp.setZzzp(entity.getZzzp());
		tp.setShip(entity.getShip());
		tp.setBlny(entity.getBlny());
		tp.setZzsj(entity.getZzsj());
		// 原板客户
		tp.setYcbr(wwhtTp.getAbbr());
		// 通货区分
		tp.setThqf(wwhtTp.getThqf());
		// 制造商规格略称
		tp.setYblc(wwhtTp.getZzgg());
		// 规格
		item = codeBo.getCodeItem(CmnConstants.CODE_018, wwhtTp.getZzgg());
		tp.setGgno(item == null ? "" : item.getValue());
		// 运用规格
		item = codeBo.getCodeItem(CmnConstants.CODE_019, wwhtTp.getZzgg());
		tp.setYuny(item == null ? "" : item.getValue());
		// 中日达规格略称
		tp.setGgnm(wwhtTp.getZrgg());
		// 制造商代码
		tp.setZzsd(wwhtTp.getZzsd());
		// 商社代码
		tp.setSsno(wwhtTp.getSsno());
		// 品种
		tp.setPzno(wwhtTp.getPzno());
		// 等级
		tp.setDeng(wwhtTp.getFpdj());
		// 采购单价
		tp.setCgdj(wwhtTp.getHtdj());
		// 制造年月日
		tp.setZzny(wwhtTp.getHtdt());
		// 表面
		tp.setFace(wwhtTp.getFace());
		// 尺寸.厚
		tp.setXpho(wwhtTp.getHouu());
		// 尺寸.宽
		tp.setXpkn(wwhtTp.getKuan());
		//
		tp.setZzsd(wwhtTp.getZzsd());
		// 如果修改的时候更换了供应商合同，那么就要将原合同中的数量减掉新合同的到货重量加上
		if (!yybno.equals(entity.getYbno()) || !yline.equals(entity.getLine())) {
			bo.updateDhsl(new WwhtTpPk(yybno, yline), -yzpzl);
			bo.updateDhsl(new WwhtTpPk(tp.getYbno(), tp.getLine()), tun);
		}
		// 如果没有修改合同，那就直接修改合同的到货重量
		else {
			bo.updateDhsl(new WwhtTpPk(entity.getYbno(), entity.getLine()), tun
					- yzpzl);
		}
		dao.update(tp);
	}

	@Override
	public void deletes(String[] jbnos) {
		List<String> list = new ArrayList<String>();
		YcaiTp tp = null;
		WwhtTp ht = null;
		double tun = 0d;
		for (int i = 0; i < jbnos.length; i++) {
			tp = dao.get(jbnos[i]);
			if (tp == null) {
				throw new CocoException(-1, "原材卷板No." + jbnos[i] + "不存在");
			}
			if (!(YbStat.CS.stat.equals(tp.getStat()) || YbStat.YRK.stat.equals(tp.getStat()))) {
				throw new CocoException(-1, "原材卷板No." + jbnos[i] + "状态为"
						+ YbStat.get(tp.getStat()).name + ",不能做修改操作");
			}
			list.add(jbnos[i]);
			// 修改供应商合同信息
			ht = bo.get(new WwhtTpPk(tp.getYbno(), tp.getLine()));
			if (ht == null) {
				throw new CocoException(-1, "供应商合同No." + tp.getYbno() + "-"
						+ tp.getLine() + "不存在");
			}
			tun = tp.getTun() != null ? tp.getTun() : 0;
			bo.updateDhsl(new WwhtTpPk(ht.getHtno(), ht.getLine()), -tun);
			// 删除原板检查证明书
			jchatpDAO.delete(jbnos[i]);
		}
		dao.deletes(list);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public YcaiTp getForUpdate(String jbno) {
		YcaiTp yc = dao.get(jbno);
		if (yc == null) {
			throw new CocoException(-1, "原材卷板No." + jbno + "不存在");
		}
		YcaiTp ycaiTp = new YcaiTp();
		WwhtTp tp = this.getWwht(new WwhtTpPk(yc.getYbno(), yc.getLine()));
		if (tp == null) {
			throw new CocoException(-1, "供应商No" + yc.getYbno() + "-"
					+ yc.getLine() + "不存在");
		}
		ReflectUtils.copy(ycaiTp, yc, false);
		ycaiTp.setYcbr(tp.getAbbr() == null || tp.getAbbr().isEmpty() ? ""
				: tp.getAbbr());
		ycaiTp.setNjno(tp.getNeij());
		CodeItem item = codeBo.getCodeItem(CmnConstants.CODE_013, tp.getThqf());
		// 设置通货区分
		ycaiTp.setThqf(item == null ? "" : item.getValue());
		// 设置预备1为供应商名称
		ycaiTp.setRsv1(tp.getZzsm());
		return ycaiTp;
	}

	@Override
	public List<YcaiTp> getByShip(String ship) {
		return dao.findByShip(ship);
	}

	@Override
	public String importYbqd(final DrVO entity) {
		Attachment attachment = attachmentBO.get(entity.getAttachId());
		if (attachment == null) {
			return new Result(-1, "上传文件不存在").toString();
		}
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(attachment.getStream());
		}
		catch (Exception e) {
		}
		// 钢卷号.前缀
		final String prefix = entity.getPrefix();
		// 记录上传文档的错误信息
		final StringBuilder errors = new StringBuilder();
		// 记录文件中的制造商卷板NO。要保证制造商卷板NO唯一
		final List<String> zzsjs = new ArrayList<String>();
		// 创建时间
		final Date crea = new Date();
		final List<YcaiTp> ycaiTps = new ArrayList<YcaiTp>();
		convertBO.convert(Convert.ycaitp.name, YcaiTp.class, is, new IConverterCallback<YcaiTp>() {
			@Override
			public void doRow(ConvertRowVO<YcaiTp> row) {
				YcaiTp tp = row.getVo();
				if (tp == null || tp.getYbno() == null
						|| tp.getYbno().isEmpty()) {
					return;
				}
				// 记录当前行的数据验证结果
				StringBuilder result = new StringBuilder();
				// excel文件中的数据同YcaiTp实体进行映射时的异常
				if (row.getErrors() != null) {
					for (ConvertErrorVO error : row.getErrors()) {
						result.append("第").append(error.getCellIndex()).append("列, ").append(error.getCause().getMessage()).append("; ");
					}
					errors.append("第").append(row.getRowIndex()).append("行错误信息。").append(result.toString()).append("<br />");
					return;
				}
				// 供应商合同信息
				WwhtTp wwhtTp = null;
				// 验证供应商合同行号不能为空。上传的原板清单文件中供应商合同行号是由供应商合同NO和供应商合同行号组合而成。
				String ybno = null;
				if ((ybno = tp.getYbno()) != null && !ybno.isEmpty()) {
					if (ybno.length() != 10) {
						result.append("供应商合同长度不为十位; ");
					}
					else {
						WwhtTpPk tpPk = new WwhtTpPk(ybno.substring(0, 7),
								ybno.substring(7, 10));
						if ((wwhtTp = bo.get(tpPk)) == null) {
							result.append("该供应商合同不存在; ");
						}
					}
				}
				else {
					result.append("供应商合同为空; ");
				}
				// 验证制造商卷板号唯一和不能为空
				String zzsj = null, yzzsj = null;
				if ((zzsj = tp.getZzsj()) != null && !zzsj.isEmpty()) {
					yzzsj = zzsj;
					// 制造商卷板号加前缀
					zzsj = prefix + zzsj;
					if (!Pattern.matches("\\w+", zzsj)) {
						result.append("制造商卷板NO只能是数字和字母; ");
					}
					if (zzsjs.contains(zzsj)) {
						result.append("上传文件中制造商卷板NO").append(zzsj).append("重复; ");
					}
					zzsjs.add(zzsj);
					if (dao.isExisted(zzsj)) {
						result.append("该制造商卷板NO已存在; ");
					}
					tp.setZzsj(zzsj);
				}
				else {
					result.append("制造商卷板NO为空; ");
				}
				// 厚
				Double xpho = null;
				// 宽
				Double xpkn = null;
				// 表面
				String face = null;
				// 商品编号
				String spbh = null;
				// 原板规格
				String ybgg = null;
				// 将供应商合同信息与原板清单的供应商合同信息进行比较。两者必须一致
				if (wwhtTp != null) {
					// 判断供应商合同的尺寸.厚同原板清单的现品厚是否一致
					if ((xpho = tp.getXpho()) == null) {
						result.append("现品厚为空; ");
					}
					else if (xpho <= 0) {
						result.append("现品厚不能小于或等于0; ");
					}
					else if (xpho.doubleValue() != wwhtTp.getHouu().doubleValue()) {
						result.append("供应商合同的尺寸.厚同原板清单的现品厚不一致; ");
					}
					else {
						spbh = CodeSpbh.getByHouu(xpho);
						if (spbh == null || spbh.isEmpty()) {
							result.append("根据现品厚不能获得商品编号; ");
						}
					}
					// 判断供应商合同的尺寸.宽同原板清单的现品宽是否一致
					if ((xpkn = tp.getXpkn()) == null) {
						result.append("现品宽为空; ");
					}
					else if (xpkn <= 0) {
						result.append("现品宽不能小于或等于0; ");
					}
					else if (xpkn.doubleValue() != wwhtTp.getKuan().doubleValue()) {
						result.append("供应商合同的尺寸.宽同原板清单的现品宽不一致; ");
					}
					// 判断供应商合同的表面同原板清单的表面是否一致
					if ((face = tp.getFace()) == null || face.isEmpty()) {
						result.append("表面为空; ");
					}
					else if (!face.equals(wwhtTp.getFace())) {
						result.append("供应商合同的表面同原板清单的表面不一致; ");
					}
					// 规格代码
					CodeItem codeItem = codeBo.getCodeItem(CmnConstants.CODE_018, wwhtTp.getZzgg());
					if (codeItem == null) {
						result.append("制造商规格略称对应的规格代码不存在; ");
					}
					else {
						tp.setGgno(codeItem.getValue());
					}
					// 运用规格
					codeItem = codeBo.getCodeItem(CmnConstants.CODE_019, wwhtTp.getZzgg());
					if (codeItem == null) {
						result.append("制造商规格略称对应的运用规格不存在; ");
					}
					else {
						tp.setYuny(codeItem.getValue());
					}
					// 运用规格与原板导入规格进行比较
					ybgg = tp.getYbgg();
					if (ybgg != null && !ybgg.isEmpty()) {
						codeItem = codeBo.getCodeItem(CmnConstants.SINO_YBDR_GG, ybgg);
						if (codeItem == null) {
							result.append("原板导入规格在码表中不存在; ");
						}
						else {
							if (!codeItem.getValue().equals(tp.getYuny())) {
								result.append("原板导入规格与运用规格不对应; ");
							}
						}
					}
					// 内径代码
					codeItem = codeBo.getUniqueItemByValue(CmnConstants.CODE_020, wwhtTp.getNeij());
					if (codeItem == null) {
						result.append("内径代码不存在; ");
					}
					else {
						tp.setNjno(codeItem.getKey());// 原板内径代码
					}
					// 根据制造商代码，查询该原材卷板是否为保税品。在码头中备注字段是用来设置制造商代码是否为保税品。注：1：非保税，2：保税
					codeItem = codeBo.getCodeItem(CmnConstants.CODE_012, wwhtTp.getZzsd());
					tp.setSfbs(codeItem != null ? codeItem.getRemark() : null);
				}
				// 验证原材卷板重量不能为空。同时根据原材卷板重量计算原板长度
				Integer zpzl = null;
				int jbcn = 0;// 原板长度
				if ((zpzl = tp.getZpzl()) == null) {
					result.append("重量为空; ");
				}
				else if (zpzl <= 0) {
					result.append("重量的单位是吨,文件中重量值不正确; ");
				}
				else {
					// 计算原板长度
					jbcn = SinoUtils.calculate(xpkn, xpho, NumberUtils.format(zpzl / 1000.0, 3));
				}
				if (jbcn == 0) {
					result.append("计算原板长度失败; ");
				}
				// 验证文件中的数据与对应表中字段的定义是否一致
				List<JpaUtils.Error> vos = JpaUtils.validate(tp);
				for (JpaUtils.Error e : vos) {
					// 上传文件中供应商合同和行号是存在供应商合同列中。
					if (e.getField().equals("ybno")) {
						continue;
					}
					result.append(e.getMessage()).append("; ");
				}
				// 记录该行的数据验证错误信息
				if (result.length() > 0) {
					errors.append("第").append(row.getRowIndex() + 1).append("行错误信息。").append(result.toString()).append("<br />");
				}
				else {
					// 如果数据验证成功后，为对象设置公共属性
					// 原板长度
					tp.setJbcn(jbcn);
					// 商品编号
					tp.setSpbh(spbh);
					// 供应商合NO
					tp.setYbno(wwhtTp.getHtno());
					// 行号
					tp.setLine(wwhtTp.getLine());
					// 原板客户
					tp.setYcbr(wwhtTp.getAbbr());
					// 通货区分
					tp.setThqf(wwhtTp.getThqf());
					// 制造商规格略称
					tp.setYblc(wwhtTp.getZzgg());
					// 中日达规格略称
					tp.setGgnm(wwhtTp.getZrgg());
					// 制造商代码
					tp.setZzsd(wwhtTp.getZzsd());
					// 商社代码
					tp.setSsno(wwhtTp.getSsno());
					// 品种
					tp.setPzno(wwhtTp.getPzno());
					// 等级
					tp.setDeng(wwhtTp.getFpdj());
					// 采购单价
					tp.setCgdj(wwhtTp.getHtdj());
					// 制造年月日
					tp.setZzny(wwhtTp.getHtdt());
					// 船名
					tp.setShip(entity.getShip());
					// 装船时间
					tp.setBlny(entity.getBlny());
					// 堆场
					tp.setDuic(DC.X.name);
					// 库位
					tp.setKw(DC.X.name);
					// 状态
					tp.setStat(YbStat.CS.stat);
					// 现品状况
					tp.setXpzk(EXpzk.SC.key);
					// 分配/余材标记
					tp.setFpyc(EFpyc.CS.key);
					// 钢种类型
					tp.setGzlx(tp.getGgno().substring(0, 1));
					// 删除标记
					tp.setScbj(EScbj.CS.key);
					// 创建时间
					tp.setCrea(crea);
					// 生产装入量（注：新增）
					tp.setZrzl(tp.getZpzl());
					// 原板成本
					tp.setYbcb(0d);
					// 前缀
					tp.setPrex(prefix);
					// 原制造商卷板NO
					tp.setYzzsj(yzzsj);
				}
				ycaiTps.add(tp);
			}
		});
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		if (ycaiTps == null || ycaiTps.size() == 0) {
			return new Result(-1, "上传文件内容为空").toString();
		}
		// 原材卷板对应的供应商合同主键
		WwhtTpPk tpPk = null;
		for (YcaiTp ycaiTp : ycaiTps) {
			// 原材卷板NO
			ycaiTp.setJbno(seqBo.sequence(Seq.ybno.name));
			tpPk = new WwhtTpPk(ycaiTp.getYbno(), ycaiTp.getLine());
			// 记录原材卷板的进货日志
			jinhlpDAO.save(parseJinhLp(ycaiTp, crea));
			// 保存原材卷板信息
			dao.save(ycaiTp);
			// 修改供应商合同对应的到货数量
			bo.updateDhsl(tpPk, ycaiTp.getTun());
		}
		attachment.setType(Convert.ycaitp.name);
		attachmentBO.update(attachment);
		if (is != null) {
			try {
				is.close();
			}
			catch (IOException e) {
			}
		}
		return new Result(1, "保存成功").toString();
	}

	/**
	 * <p>
	 * 根据原板进货，生成原板进货日志
	 * </p>
	 * <p>
	 * author: 张良[jimsonhappy@126.com]
	 * </p>
	 * 
	 * @param ycaiTp
	 * @param date
	 * @return JinhLp
	 */
	private JinhLp parseJinhLp(YcaiTp ycaiTp, Date date) {
		JinhLp jinhLp = new JinhLp();
		jinhLp.setCrea(date);
		jinhLp.setHouu(ycaiTp.getXpho());
		jinhLp.setKuan(ycaiTp.getXpkn());
		jinhLp.setHtdj(ycaiTp.getCgdj());
		jinhLp.setHtno(ycaiTp.getYbno());
		jinhLp.setLine(ycaiTp.getLine());
		jinhLp.setJbcn(ycaiTp.getJbcn());
		jinhLp.setJbno(ycaiTp.getJbno());
		jinhLp.setSsno(ycaiTp.getSsno());
		jinhLp.setThqf(ycaiTp.getThqf());
		// 原板进货日志的原材卷板重量单位是千克
		jinhLp.setZlkg(ycaiTp.getZpzl());
		jinhLp.setZrgg(ycaiTp.getGgnm());
		jinhLp.setZzgg(ycaiTp.getYblc());
		jinhLp.setZzsd(ycaiTp.getZzsd());
		jinhLp.setZzsj(ycaiTp.getZzsj());
		return jinhLp;
	}

	@Override
	public String importJczms(DrVO entity) {
		Attachment attachment = attachmentBO.get(entity.getAttachId());
		if (attachment == null) {
			return new Result(-1, "上传文件不存在").toString();
		}
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(attachment.getStream());
		}
		catch (Exception e) {
		}
		// 钢卷号.前缀
		final String prefix = entity.getPrefix();
		// 记录上传文档的错误信息
		final StringBuilder errors = new StringBuilder();
		// 记录文件中的制造商卷板NO。要保证制造商卷板NO唯一
		final List<String> zzsjs = new ArrayList<String>();
		final List<JchaTp> jchaTps = new ArrayList<JchaTp>();
		convertBO.convert(Convert.jchaTp.name, JchaTp.class, is, new IConverterCallback<JchaTp>() {

			@Override
			public void doRow(ConvertRowVO<JchaTp> row) {
				JchaTp tp = row.getVo();
				if (tp == null || tp.getZzsj() == null
						|| tp.getZzsj().isEmpty()) {
					return;
				}
				// 记录当前行的数据验证结果
				StringBuilder result = new StringBuilder();
				// excel文件中的数据同JchaTp实体进行映射时的异常
				if (row.getErrors() != null) {
					for (ConvertErrorVO error : row.getErrors()) {
						result.append("第").append(error.getCellIndex()).append("列, ").append(error.getCause().getMessage()).append("; ");
					}
					errors.append("第").append(row.getRowIndex()).append("行错误信息。").append(result.toString()).append("<br />");
					return;
				}
				//
				YcaiTp ycaiTp = null;
				//
				String zzsj = null;
				if ((zzsj = tp.getZzsj()) == null || zzsj.isEmpty()) {
					result.append("制造商卷板NO为空; ");
				}
				else {
					// 制造商卷板No加前缀
					zzsj = prefix + zzsj;
					if (zzsjs.contains(zzsj)) {
						result.append("上传文件中制造商卷板NO").append(zzsj).append("重复; ");
					}
					// 判断制造商卷板NO唯一
					zzsjs.add(zzsj);
					//
					ycaiTp = dao.getByZzsj(zzsj);
					if (ycaiTp == null) {
						result.append("制造商卷板NO").append(zzsj).append("对应的原板不存在; ");
					}
					else {
						// 判断该原材卷板是否已存在
						if (jchatpDAO.isExisted(ycaiTp.getJbno())) {
							result.append("制造商卷板NO").append(zzsj).append("对应的原板检查证明书已存在; ");
						}
					}
					tp.setZzsj(zzsj);
				}
				// 验证吹练NO不能为空
				String chui = null;
				if ((chui = tp.getChui()) == null || chui.isEmpty()) {
					result.append("吹练NO为空; ");
				}
				Double d = null;
				// 验证硬度的数据类型和不能为空
				String ying = null;
				if ((ying = tp.getYing()) == null || ying.isEmpty()) {
					result.append("硬度为空; ");
				}
				else {
					try {
						d = Double.valueOf(ying);
						if (d <= 0) {
							result.append("硬度不能小于或等于0; ");
						}
						ying = NumberUtils.formatNumber(d.doubleValue(), 0);
						tp.setYing(ying);// 硬度
					}
					catch (NumberFormatException e) {
						d = null;
						result.append("硬度不为数值型; ");
					}
				}
				// 验证成分值SI的数据类型和不能为空
				String cfsi = null;
				if ((cfsi = tp.getCfsi()) == null || cfsi.isEmpty()) {
					result.append("成分值SI为空; ");
				}
				else {
					try {
						d = Double.valueOf(cfsi);
						if (d <= 0) {
							result.append("成分值SI不能小于或等于0; ");
						}
						cfsi = NumberUtils.formatNumber(d.doubleValue() * 100, 0);
						tp.setCfsi(cfsi);// 成分值SI
					}
					catch (NumberFormatException e) {
						d = null;
						result.append("成分值SI不为数值型; ");
					}
				}
				// 验证成分值MN的数据类型和不能为空
				String cfmn = null;
				if ((cfmn = tp.getCfmn()) == null || cfmn.isEmpty()) {
					result.append("成分值MN为空; ");
				}
				else {
					try {
						d = Double.valueOf(cfmn);
						if (d <= 0) {
							result.append("成分值MN不能小于或等于0; ");
						}
						cfmn = NumberUtils.formatNumber(d.doubleValue() * 100, 0);
						tp.setCfmn(cfmn);// 成分值MN
					}
					catch (NumberFormatException e) {
						d = null;
						result.append("成分值MN不为数值型; ");
					}
				}
				// 验证成分值P的数据类型和不能为空
				String cfpp = null;
				if ((cfpp = tp.getCfpp()) == null || cfpp.isEmpty()) {
					result.append("成分值P为空; ");
				}
				else {
					try {
						d = Double.valueOf(cfpp);
						if (d <= 0) {
							result.append("成分值P不能小于或等于0; ");
						}
						cfpp = NumberUtils.formatNumber(d.doubleValue() * 1000, 0);
						tp.setCfpp(cfpp);// 成分值P
					}
					catch (NumberFormatException e) {
						d = null;
						result.append("成分值P不为数值型; ");
					}
				}
				// 验证成分值S的数据类型和不能为空
				String cfss = null;
				if ((cfss = tp.getCfss()) == null || cfss.isEmpty()) {
					result.append("成分值S为空; ");
				}
				else {
					try {
						d = Double.valueOf(cfss);
						if (d <= 0) {
							result.append("成分值S不能小于或等于0; ");
						}
						cfss = NumberUtils.formatNumber(d.doubleValue() * 1000, 0);
						tp.setCfss(cfss);// 成分值S
					}
					catch (NumberFormatException e) {
						d = null;
						result.append("成分值S不为数值型; ");
					}
				}
				// 验证成分值C的数据类型和不能为空
				Double cfcc = null;
				if ((cfcc = tp.getCfcc()) == null) {
					result.append("成分值C为空; ");
				}
				else if (cfcc <= 0) {
					result.append("成分值C不能小于或等于0; ");
				}
				else {
					if (NumberUtils.format(cfcc, 2) > 0) {
						cfcc = NumberUtils.format(cfcc.doubleValue() * 100, 0);
					}
					else {
						cfcc = NumberUtils.format(cfcc.doubleValue() * 100, 1);
					}
					tp.setCfcc(cfcc);// 成分值C
				}
				// 验证文件中的数据与对应表中字段的定义是否一致
				List<JpaUtils.Error> vos = JpaUtils.validate(tp);
				for (JpaUtils.Error e : vos) {
					result.append(e.getMessage()).append("; ");
				}
				// 记录该行的数据验证错误信息
				if (result.length() > 0) {
					errors.append("第").append(row.getRowIndex() + 1).append("行错误信息。").append(result.toString()).append("<br />");
				}
				else {
					// 原材卷板NO
					tp.setYcaiTp(ycaiTp);
					// 船名
					tp.setShip(ycaiTp.getShip());
					// 创建时间(目的是可以通过排序创建时间来实得可以导入文件的顺序一样)
					tp.setCrea(new Date());
				}
				jchaTps.add(tp);
			}
		});
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		if (jchaTps == null || jchaTps.size() == 0) {
			return new Result(-1, "上传文件内容为空").toString();
		}
		// for (JchaTp jchaTp : jchaTps) {
		// if (jchatpDAO.isExisted(jchaTp.getYcaiTp().getJbno())) {
		// jchatpDAO.update(jchaTp);
		// }
		// else {
		// jchatpDAO.save(jchaTp);
		// }
		// }
		jchatpDAO.saveAll(jchaTps);
		attachment.setType(Convert.jchaTp.name);
		attachmentBO.update(attachment);
		if (is != null) {
			try {
				is.close();
			}
			catch (IOException e) {
			}
		}
		return new Result(1, "保存成功").toString();
	}

	@Override
	public String importYbrk(DrVO entity) {
		Attachment attachment = attachmentBO.get(entity.getAttachId());
		if (attachment == null) {
			return new Result(-1, "上传文件不存在").toString();
		}
		byte[] bytes = attachment.getStream();
		if (bytes == null || bytes.length == 0) {
			return new Result(-1, "上传文件内容为空").toString();
		}
		// 记录上传文档的错误信息
		StringBuilder errors = new StringBuilder();
		// 原板搬入堆场时间
		Date duib = new Date();
		String content = new String(bytes);
		String[] lines = content.split("\n");
		// 记录当前行的数据验证结果
		StringBuilder result = null;
		String[] arrs = null;
		int index = 1;
		for (String line : lines) {
			arrs = line.split(",");
			if (arrs.length != 2) {
				errors.append("第").append(index).append("行信息有误。").append(line).append("<br />");
				continue;
			}
			result = new StringBuilder();
			// 验证原材卷板NO是否存在和不能为空
			String jbno = arrs[0];
			//
			YcaiTp ycaiTp = null;
			if (jbno == null || (jbno = jbno.trim()).isEmpty()) {
				result.append("原材卷板No为空; ");
			}
			else {
				ycaiTp = dao.get(jbno);
				if (ycaiTp == null) {
					result.append("原材卷板No").append(jbno).append("不存在; ");
				}
				else {
					if (!(YbStat.CS.stat.equals(ycaiTp.getStat())
							|| YbStat.YRK.stat.equals(ycaiTp.getStat()) || YbStat.YFP.stat.equals(ycaiTp.getStat()))) {
						result.append("原材卷板No").append(jbno).append("对应的原材状态为 ").append(YbStat.get(ycaiTp.getStat()).name).append(",不能做原板入库操作; ");
					}
				}
			}
			// 验证库位长度和不能为空
			String kw = arrs[1];
			if (kw == null || (kw = kw.trim()).isEmpty()) {
				result.append("库位为空; ");
			}
			else if (kw.length() != 4) {
				result.append("库位" + kw + "无效,库位长度为四位; ");
			}
			else if (!(DC.A.name.equals(kw.substring(0, 1)) || DC.B.name.equals(kw.substring(0, 1)))) {
				result.append("库位" + kw + "不正确,应为A或B堆场开头; ");
			}
			else if (!codeBo.isExisted(CmnConstants.CODE_011, kw)) {
				result.append("库位" + kw + "在码表中不存在; ");
			}
			// 记录该行的数据验证错误信息
			if (result.length() > 0) {
				errors.append("第").append(index).append("行错误信息。").append(result.toString()).append("<br />");
			}
			else {
				// A堆场
				ycaiTp.setDuic(DC.A.name);
				// 库位
				ycaiTp.setKw(kw);
				// 入库时间
				ycaiTp.setDuib(duib);
				// 原板状态为：已入库
				ycaiTp.setStat(YbStat.YRK.stat);
				dao.update(ycaiTp);
			}
			index++;
		}
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		return new Result(1, "保存成功").toString();
	}

	@Override
	public List<YcaiTp> find(List<String> jbnos) {
		return dao.find(jbnos);
	}

	@Override
	public YcaiTp getRef(Serializable id) {
		return dao.getRef(id);
	}

	@Override
	public void setRksj(String ship, Date duib) {
		List<YcaiTp> ycaiTps = dao.findByShip(ship);
		StringBuffer stb = new StringBuffer();
		for (YcaiTp ycaiTp : ycaiTps) {
			if (!(YbStat.YRK.stat.equals(ycaiTp.getStat())
					|| YbStat.CS.stat.equals(ycaiTp.getStat()) || YbStat.YFP.stat.equals(ycaiTp.getStat()))) {
				stb.append("原板").append(ycaiTp.getJbno()).append("状态为").append(YbStat.getName(ycaiTp.getStat())).append("。不允许设置入库时间<br/>");
			}
		}
		if (stb.length() != 0) {
			throw new CocoException(-1, stb.toString());
		}
		String[] stats = { YbStat.CS.stat, YbStat.YRK.stat, YbStat.YFP.stat };
		// 设置的记录数
		dao.setRksj(ship, duib, stats);
	}

	@Override
	public boolean isExistedShip(String ship) {
		return dao.isExistedShip(ship);
	}

	@Override
	public String loadYbcb(String ship) {
		Ybcbmx ybcbmx = ycaicbDAO.getMx(ship);
		if (ybcbmx == null) {
			return Result.ERROR;
		}
		return new Result(ybcbmx).toJsObject();
	}

	@Override
	public String saveYbcb(Ybcbmx vo, String flag, User user) {
		List<YcaiTp> ycaiTps = dao.findByShip(vo.getShip());
		if (ycaiTps.size() == 0) {
			return new Result(-1, "在原板库中没有该船名对应的原材板卷信息").toString();
		}
		//
		ycaicbDAO.deleteMx(vo.getShip());
		ycaicbDAO.saveMx(vo);
		//
		Long hjzl = dao.totalZpzl(vo.getShip());
		if (hjzl == null || hjzl <= 0) {
			return new Result(-1, "原板信息有误，没有重量").toString();
		}
		// 重新计算原板成本
		if (flag != null && "Y".equals(flag)) {
			ycaicbDAO.deleteByShip(vo.getShip());
		}
		// 进口报关报检费
		Double bgbj = vo.getBgbj() != null ? vo.getBgbj() : 0;
		// 进口换小提单费
		Double hxtd = vo.getHxtd() != null ? vo.getHxtd() : 0;
		// 进口检验检疫费
		Double jyjy = vo.getJyjy() != null ? vo.getJyjy() : 0;
		// 重量鉴定费
		Double zljd = vo.getZljd() != null ? vo.getZljd() : 0;
		// 过磅费
		Double gdf = vo.getGdf() != null ? vo.getGdf() : 0;
		// 基础费用（包含：进口报关报检费、进口换小提单费、进口检验检疫费、重量鉴定费、过磅费）
		Double jcfy = NumberUtils.format((bgbj + hxtd + jyjy + zljd + gdf)
				* 1000.0 / hjzl, 2);
		// 原材重量
		Integer zpzl = 0;
		// 原板采购单价
		Double cgdj = null;
		// 保费汇率
		Double bfhl = vo.getBfhl();
		// 美元汇率
		Double myhl = vo.getMyhl();
		// 关税税率
		double gssl = 0d;
		// 海关加价
		double hgjj = vo.getHgjj() != null ? vo.getHgjj() : 0d;
		// 海关基价
		double hgbj = vo.getHgbj() != null ? vo.getHgbj() : 0d;
		// 每个原材对应的成本记录
		Ycaicb ycaicb = null;
		Double hjcb = 0d;
		String jbno;
		for (YcaiTp ycaiTp : ycaiTps) {
			hjcb = 0d;
			jbno = ycaiTp.getJbno();
			if (jbno == null || ZpnoCd.six.len != jbno.length()) {
				// ETL办结终止的卷材，是不用计算成本的。因为其母卷已计算
				continue;
			}
			if (ycaicbDAO.isExisted(jbno)) {
				continue;
			}
			if ((zpzl = ycaiTp.getZpzl()) == null) {
				continue;
			}
			if ((cgdj = ycaiTp.getCgdj()) == null || cgdj <= 0) {
				continue;
			}
			gssl = cmnBO.getGssl(ycaiTp.getXpho());
			ycaicb = new Ycaicb();
			ycaicb.setHgjj(hgjj);
			ycaicb.setHgbj(hgbj);
			ycaicb.setJbno(ycaiTp.getJbno());
			ycaicb.setShip(ycaiTp.getShip());
			ycaicb.setZpzl(zpzl);
			ycaicb.setDj(cgdj);
			ycaicb.setYbjg(NumberUtils.format(cgdj * zpzl / 1000.0, 2));
			ycaicb.setXcmt(vo.getXcmt());
			ycaicb.setGzsl(vo.getGzsl());
			// 港杂费用
			ycaicb.setGzf(NumberUtils.format(vo.getGzsl() * zpzl / 1000d, 2));
			hjcb += ycaicb.getGzf();
			ycaicb.setYsgs(vo.getYsgs());
			ycaicb.setYssl(vo.getYssl());
			ycaicb.setYsf(NumberUtils.format(vo.getYssl() * zpzl / 1000d, 2));
			hjcb += ycaicb.getYsf();
			ycaicb.setBfhl(bfhl);
			ycaicb.setMyhl(myhl);
			ycaicb.setGssl(gssl);
			// 保险费:数量×单价×保费汇率×美元汇率
			ycaicb.setBxf(NumberUtils.format(ycaicb.getYbjg() * bfhl * myhl, 2));
			hjcb += ycaicb.getBxf();
			// 进口关税：原材卷板进口海关关税。数量×单价×(1+保费汇率)×关税税率×美元汇率
			if (hgjj > 0) {
				ycaicb.setJkgs(NumberUtils.format((ycaicb.getYbjg() + ((hgjj * zpzl) / 1000d))
						* (1 + bfhl) * gssl * myhl, 2));
			}
			else {
				ycaicb.setJkgs(NumberUtils.format(((hgbj * zpzl) / 1000d)
						* (1 + bfhl) * gssl * myhl, 2));
			}
			hjcb += ycaicb.getJkgs();
			// 进口增值税：原材卷板进口增值税。数量×单价×(1+保费汇率)×（1+关税税率）×17%×美元汇率
			ycaicb.setJkzz(NumberUtils.format(ycaicb.getYbjg() * (1 + bfhl)
					* (1 + gssl) * YcaiConstants.JKZZ * myhl, 2));
			hjcb += ycaicb.getJkzz();
			ycaicb.setJcfy(NumberUtils.format(jcfy * zpzl / 1000d, 2));
			hjcb += ycaicb.getJcfy();
			ycaicb.setDdno(user.getNo());
			ycaicb.setDdnm(user.getName());
			ycaicbDAO.save(ycaicb);
			ycaiTp.setYbcb(hjcb);
			dao.update(ycaiTp);
		}
		return new Result(1, "操作成功").toString();
	}

	@Override
	public String updateHgjj(String jbno, Double hgjj) {
		Ycaicb entity = ycaicbDAO.get(jbno);
		if (entity == null) {
			return new Result(-1, "该原板还未登录原板成本费用").toString();
		}
		// 原板价格
		double ybjg = entity.getYbjg() != null ? entity.getYbjg() : 0d;
		// 制品重量
		int zpzl = entity.getZpzl() != null ? entity.getZpzl() : 0;
		// 保费汇率
		double bfhl = entity.getBfhl() != null ? entity.getBfhl() : 0d;
		// 美元汇率
		double myhl = entity.getMyhl() != null ? entity.getMyhl() : 0d;
		// 关税税率
		double gssl = entity.getGssl() != null ? entity.getGssl() : 0d;
		// 进口关税：原材卷板进口海关关税。数量×单价×(1+保费汇率)×关税税率×美元汇率
		entity.setJkgs(NumberUtils.format((ybjg + (hgjj * zpzl) / 1000d)
				* (1 + bfhl) * gssl * myhl, 2));
		entity.setHgjj(hgjj);
		ycaicbDAO.update(entity);
		return Result.SUCCESS;
	}

	@Override
	public Ycaicb getYbcb(String jbno) {
		return ycaicbDAO.get(jbno);
	}

	@Override
	public void queryJchatp(JchatpQE qentity) {
		jchatpDAO.query(qentity);
	}

	@Override
	public String saveJchatp(JchaTp entity) {
		if (jchatpDAO.isExisted(entity.getYcaiTp().getJbno())) {
			return new Result(-1, "制造商卷板NO" + entity.getZzsj() + "已添加商品检查书表中").toString();
		}
		entity.setCrea(new Date());
		jchatpDAO.save(entity);
		return Result.SUCCESS;
	}

	@Override
	public YcaiTp getByZzsj(String zzsj) {
		return dao.getByZzsj(zzsj);
	}

	@Override
	public String updateJchatp(JchaTp entity) {
		JchaTp jchaTp = jchatpDAO.get(entity.getYcaiTp().getJbno());
		ReflectUtils.copy(jchaTp, entity, true);
		jchatpDAO.update(jchaTp);
		return Result.SUCCESS;
	}

	@Override
	public boolean isExistedYcai(String zsno, String jbno) {
		return dao.isExistedYcai(zsno, jbno);
	}

	@Override
	public JchaTp getJchatp(Serializable id) {
		return jchatpDAO.get(id);
	}

	@Override
	public boolean isExistedWwhttp(String ybno, String line) {
		return dao.isExistedWwhttp(ybno, line);
	}

	@Override
	public void deleteJchatps(String[] ids) {
		jchatpDAO.deleteAll(Arrays.asList(ids));
	}

	@Override
	public void deleteJchatp(Serializable id) {
		jchatpDAO.delete(id);

	}

	@Override
	public String getJchatpForJs(String jbno) {
		JchaTp entity = jchatpDAO.get(jbno);
		if (entity == null) {
			return new Result(-1, "原材卷板No" + jbno + "还没有添加原板商品检查证明书").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public boolean isZl(String jbno, String elin) {
		return dao.isZl(jbno, elin);
	}

	@Override
	public boolean isExistedYcai(String zsno) {
		return dao.isExistedYcai(zsno);
	}

	@Override
	public boolean isExistedYcaiSjsj(String zsno) {
		return dao.isExistedYcaiSjsj(zsno);
	}

	@Override
	public List<YcaiTp> findByZsno(String zsno) {
		return dao.findByZsno(zsno);
	}

	@Override
	public YcaiTp getZzsj(String stat) {
		return dao.getZzsj(stat);
	}

	@Override
	public boolean isExistedJchatp(String jbno) {
		return jchatpDAO.isExisted(jbno);
	}

	@Override
	public void setYlno(List<String> jbnos, String ylno) {
		dao.setYlno(jbnos, ylno);
	}

	@Override
	public boolean isExistedBySczt(String sczt) {
		return dao.isExistedBySczt(sczt);

	}

	@Override
	public boolean isExisted(String jbno, String sfqr) {
		return dao.isExisted(jbno, sfqr);
	}

	@Override
	public void updateSczt(String id, String stat) {
		dao.updateSczt(id, stat);
	}

	@Override
	public YcaiTp getYcaiBySczt(String sczt) {
		return dao.getYcaiBySczt(sczt);
	}

	@Override
	public void updateSfqr(String id, String stat) {
		dao.updateSfqr(id, stat);
	}

	@Override
	public void updateScztAndSfqr(String id, String sczt, String sfqr) {
		dao.updateScztAndSfqr(id, sczt, sfqr);
	}

	@Override
	public boolean isExistedYcaiByJbno(String jbno) {
		return dao.isExistedYcaiByJbno(jbno);
	}

	@Override
	public boolean isExistedByJbnoAndSczt(String jbno, String sczt) {
		return dao.isExistedByJbnoAndSczt(jbno, sczt);
	}

	@Override
	public void updateYd(String jbno, Integer ying, String jdyn, Date ydsj) {
		dao.updateYd(jbno, ying, jdyn, ydsj);
	}

	@Override
	public Long getKc(Double xpho, Double xpkn, String yuny, List<String> faces) {
		return dao.getKc(xpho, xpkn, yuny, faces);
	}

	@Override
	public void updateYlno(String jbnoStart, String jbnoEnd, String ylno) {
		YcaiQE qentity = new YcaiQE();
		qentity.setSize(100);
		qentity.setCoilNoS(jbnoStart);
		qentity.setCoilNoE(jbnoEnd);
		qentity.setNeStat(YbStat.SJLR.stat);
		qentity.setScbj(EScbj.CS.key);
		dao.query(qentity);
		List<YcaiTp> ycais = qentity.getDatas();
		if (ycais.size() > 0) {
			String $ylno;
			for (YcaiTp ycaiTp : ycais) {
				$ylno = ycaiTp.getYlno();
				if ($ylno == null || $ylno.isEmpty()) {
					ycaiTp.setYlno(ylno);
				}
				else {
					ycaiTp.setYlno($ylno + "," + ylno);
				}
				dao.update(ycaiTp);
			}
		}
		// dao.updateYlno(jbnoStart, jbnoEnd, ylno);
	}

	/**
	 * @return the dao
	 */
	public IYcaitpDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(IYcaitpDAO dao) {
		this.dao = dao;
	}

	/**
	 * @return the bo
	 */
	public IWwhtBO getBo() {
		return bo;
	}

	/**
	 * @param bo
	 *            the bo to set
	 */
	public void setBo(IWwhtBO bo) {
		this.bo = bo;
	}

	/**
	 * @return the codeBo
	 */
	public ICodeBO getCodeBo() {
		return codeBo;
	}

	/**
	 * @param codeBo
	 *            the codeBo to set
	 */
	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	/**
	 * @return the seqBo
	 */
	public ISeqBO getSeqBo() {
		return seqBo;
	}

	/**
	 * @param seqBo
	 *            the seqBo to set
	 */
	public void setSeqBo(ISeqBO seqBo) {
		this.seqBo = seqBo;
	}

	/**
	 * @return the attachmentBO
	 */
	public IAttachmentBO getAttachmentBO() {
		return attachmentBO;
	}

	/**
	 * @param attachmentBO
	 *            the attachmentBO to set
	 */
	public void setAttachmentBO(IAttachmentBO attachmentBO) {
		this.attachmentBO = attachmentBO;
	}

	/**
	 * @return the convertBO
	 */
	public IConvertBO getConvertBO() {
		return convertBO;
	}

	/**
	 * @param convertBO
	 *            the convertBO to set
	 */
	public void setConvertBO(IConvertBO convertBO) {
		this.convertBO = convertBO;
	}

	/**
	 * @return the jchatpDAO
	 */
	public IJchatpDAO getJchatpDAO() {
		return jchatpDAO;
	}

	/**
	 * @param jchatpDAO
	 *            the jchatpDAO to set
	 */
	public void setJchatpDAO(IJchatpDAO jchatpDAO) {
		this.jchatpDAO = jchatpDAO;
	}

	/**
	 * @return the jinhlpDAO
	 */
	public IJinhlpDAO getJinhlpDAO() {
		return jinhlpDAO;
	}

	/**
	 * @param jinhlpDAO
	 *            the jinhlpDAO to set
	 */
	public void setJinhlpDAO(IJinhlpDAO jinhlpDAO) {
		this.jinhlpDAO = jinhlpDAO;
	}

	/**
	 * @return the ycaicbDAO
	 */
	public IYcaicbDAO getYcaicbDAO() {
		return ycaicbDAO;
	}

	/**
	 * @param ycaicbDAO
	 *            the ycaicbDAO to set
	 */
	public void setYcaicbDAO(IYcaicbDAO ycaicbDAO) {
		this.ycaicbDAO = ycaicbDAO;
	}

	/**
	 * @return the cmnBO
	 */
	public ICmnBO getCmnBO() {
		return cmnBO;
	}

	/**
	 * @param cmnBO
	 *            the cmnBO to set
	 */
	public void setCmnBO(ICmnBO cmnBO) {
		this.cmnBO = cmnBO;
	}

}
