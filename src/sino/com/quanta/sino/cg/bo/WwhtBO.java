package com.quanta.sino.cg.bo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.coco.core.bean.Result;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.JpaUtils;
import com.coco.core.util.NumberUtils;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.bo.api.IConvertBO;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.Attachment;
import com.coco.sys.orm.CodeItem;
import com.coco.sys.vo.ConvertErrorVO;
import com.coco.sys.vo.ConvertRowVO;
import com.quanta.sino.cg.bo.api.IWwhtBO;
import com.quanta.sino.cg.constants.CgConstants;
import com.quanta.sino.cg.dao.api.ICgdjDAO;
import com.quanta.sino.cg.dao.api.IWwhtDAO;
import com.quanta.sino.cg.vo.BaseVO;
import com.quanta.sino.cg.vo.DrVO;
import com.quanta.sino.cg.vo.WwhtSaveVO;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.Convert;
import com.quanta.sino.cmn.constants.EKnfw;
import com.quanta.sino.orm.WwhtTp;
import com.quanta.sino.orm.WwhtTpPk;
import com.quanta.sino.orm.YbCgdj;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;

/**
 * <p>
 * 采购合同业务接口实现
 * </p>
 * <p>
 * create: 2010-12-22 上午10:23:38
 * </p>
 * 
 * @author 张良[jimsnhappy@126.com]
 * @version 1.0
 */
public class WwhtBO implements IWwhtBO {

	/**
	 * 采购合同数据访问接口
	 */
	private IWwhtDAO dao;

	/**
	 * 代码管理业务层接口
	 */
	private ICodeBO codeBo;

	/**
	 * 原材业务层接口
	 */
	private IYcaitpBO ycaiBO;

	/**
	 * 附件业务层接口
	 */
	private IAttachmentBO attachmentBO;

	/**
	 * 转换业务接口
	 */
	private IConvertBO bo;

	/**
	 * 采购单价数据访问接口
	 */
	private ICgdjDAO cgdjDAO;

	/**
	 * DR材
	 */
	private static String DR = "DR";

	/**
	 * 非DR材
	 */
	private static String SR = "SR";

	@Override
	public void save(WwhtTp entity) {
		dao.save(entity);
	}

	@Override
	public void saveAll(List<WwhtTp> entities) {
		// 检查是否有输入重复的合同（合同号和行号）
		validateDuplicate(entities);
		// 校验数据
		int i = 1;
		for (WwhtTp wtp : entities) {
			validate(wtp, i++, false);
		}
		dao.saveAll(entities);

	}

	/**
	 * <p>
	 * 数据校验
	 * </p>
	 * 
	 * @param wtp
	 * @param i
	 * @param isModify
	 *            是否是修改
	 */
	private void validate(WwhtTp wtp, int i, boolean isModify) {
		CodeItem code;
		// 校验制造商
		if (!codeBo.isExisted(CmnConstants.CODE_012, wtp.getZzsd())) {
			throw new CocoException(-1, "制造商在码表不存在！");
		}

		// 校验商社
		if (!codeBo.isExisted(CmnConstants.CODE_010, wtp.getSsno())) {
			throw new CocoException(-1, "商社在码表不存在！");
		}

		// 校验币种
		if (!codeBo.isExisted(CmnConstants.CODE_013, wtp.getThqf())) {
			throw new CocoException(-1, "币种在码表不存在！");
		}

		// 校验制造商规格略称
		if (!codeBo.isExisted(CmnConstants.CODE_017, wtp.getZzgg())) {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的制造商规格略称在码表不存在！"
					: "制造商规格略称在码表不存在");
		}

		// 给中日达规格略称赋值
		code = codeBo.getCodeItem(CmnConstants.CODE_017, wtp.getZzgg());
		if (code != null) {
			wtp.setZrgg(code.getValue());
		}

		// 校验品种代码
		if (wtp.getPzno().length() >= 2) {
			// 校验品种
			if (!codeBo.isExisted(CmnConstants.CODE_118, wtp.getPzno().substring(0, 1))) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的品种在码表不存在！"
						: "品种在码表不存在！");
			}
			// 校验品种等级
			if (!codeBo.isExisted(CmnConstants.CODE_119, wtp.getPzno().substring(1, 2))) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的品种的等级在码表不存在！"
						: "品种的等级在码表不存在！");
			}
		}
		else {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的品种输入不全！"
					: "品种输入不全！");
		}

		// 校验等级代码
		if (wtp.getFpdj().length() >= 3) {
			// 校验订货等级
			if (!codeBo.isExisted(CmnConstants.CODE_103, wtp.getFpdj().substring(0, 1))) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的等级第一位在码表不存在！"
						: "等级第一位在码表不存在！");
			}
			// 校验表面性状等级
			if (!codeBo.isExisted(CmnConstants.CODE_104, wtp.getFpdj().substring(1, 2))) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的等级第二位在码表不存在！"
						: "等级第二位在码表不存在！");
			}
			// 校验 形状等级
			if (!codeBo.isExisted(CmnConstants.CODE_105, wtp.getFpdj().substring(2, 3))) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的等级第三位在码表不存在！"
						: "等级第三位在码表不存在！");
			}
		}
		else {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的等级输入不全！"
					: "等级输入不全！");
		}

		// 校验表面
		if (!codeBo.isExisted(CmnConstants.CODE_005, wtp.getFace())) {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的表面在码表不存在！"
					: "表面在码表不存在！");
		}

		// 内径值不允许为空
		if ((wtp.getNeij() == null) || (wtp.getNeij().isEmpty())) {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的内径在码表不存在！"
					: "内径在码表不存在！");
		}
		// 校验内径值是否存在
		if (!codeBo.isValueExisted(CmnConstants.CODE_020, wtp.getNeij())) {
			throw new CocoException(-1, i > 0 ? "第" + i + "行的内径在码表不存在！"
					: "内径在码表不存在！");
		}
		if (!isModify) {
			// 校验合同NO和行号是否唯一
			if (dao.get(new WwhtTpPk(wtp.getHtno(), wtp.getLine())) != null) {
				throw new CocoException(-1, i > 0 ? "第" + i + "行的合同NO和行号不唯一！"
						: "合同NO和行号不唯一！");
			}
		}
	}

	/**
	 * <p>
	 * 校验输入的合同NO和行号是否重复
	 * </p>
	 * 
	 * @param entities
	 * @return
	 */
	private void validateDuplicate(List<WwhtTp> entities) {
		int size = entities.size();
		WwhtTp wtp0, wtp1;
		for (int i = 0; i < size; i++) {
			wtp0 = entities.get(i);
			for (int j = i + 1; j < size; j++) {
				wtp1 = entities.get(j);
				if ((wtp0.getHtno().equals(wtp1.getHtno()))
						&& (wtp0.getLine().equals(wtp1.getLine()))) {
					throw new CocoException(-1, "第" + (i + 1) + "行和第" + (j + 1)
							+ "行有重复的合同NO和行号！");
				}
			}
		}
	}

	@Override
	public void update(WwhtSaveVO wsvo, String $htno, String $line) {
		boolean flag = false;
		WwhtTp entity = dao.get(new WwhtTpPk($htno, $line));
		if (entity == null) {
			flag = true;
			entity = new WwhtTp();
			WwhtTp wwhtTp = dao.get(new WwhtTpPk(wsvo.getHtno(), wsvo.getLine()));
			if (wwhtTp == null) {
				throw new CocoException(-1, "供应商合同NO" + wsvo.getHtno() + "-"
						+ wsvo.getLine() + "不存在");
			}
			ReflectUtils.copy(entity, wwhtTp, false);
			entity.setHtno($htno);
			entity.setLine($line);
			dao.delete(new WwhtTpPk(wsvo.getHtno(), wsvo.getLine()));
		}
		// String qywl = wsvo.getQywl();
		// if (qywl.equals(entity.getQywl()) && entity.getDhsl() != null
		// && entity.getDhsl() > 0) {
		// throw new CocoException(-1, "供应商合同NO" + wsvo.getHtno() + "-"
		// + wsvo.getLine() + "已有到货原材,不能做修改操作");
		// }
		// if (qywl.equals(entity.getQywl())
		// && CgConstants.CODE_QYWL_YWL.equals(entity.getQywl())) {
		// throw new CocoException(-1, "供应商合同NO" + wsvo.getHtno() + "-"
		// + wsvo.getLine() + "已" + CgConstants.CODE_QYWL_YWL
		// + ",不能做修改操作");
		// }
		// if (!qywl.equals(entity.getQywl()) && entity.getDhsl() != null
		// && entity.getDhsl() > 0
		// && CgConstants.CODE_QYWL_YWL.equals(entity.getQywl())) {
		// entity.setQywl(qywl);
		// dao.update(entity);
		// return;
		//
		// }
		// 签约日期
		entity.setQyri(wsvo.getQyri());
		// 制造商代码赋值
		entity.setZzsd(wsvo.getZzsd());
		// 制造商名称赋值
		entity.setZzsm(wsvo.getZzsm());
		// 币种
		entity.setThqf(wsvo.getThqf());
		// 商社代码赋值
		entity.setSsno(wsvo.getSsno());
		// 商社名称赋值
		entity.setSsnm(wsvo.getSsnm());
		// 合同日
		entity.setHtdt(wsvo.getHtdt());
		// 制造商规格略称
		entity.setZzgg(wsvo.getZzgg());
		// 给中日达规格略称赋值
		CodeItem code;
		code = codeBo.getCodeItem(CmnConstants.CODE_017, wsvo.getZzgg());
		if (code != null) {
			entity.setZrgg(code.getValue());
		}
		// 用户略称
		entity.setAbbr(wsvo.getAbbr());
		// 品种
		entity.setPzno(wsvo.getPzno());
		// 尺寸.厚
		entity.setHouu(wsvo.getHouu());
		// 尺寸.宽
		entity.setKuan(wsvo.getKuan());
		// 重量（吨）
		entity.setHtzl(wsvo.getHtzl());
		// 等级
		entity.setFpdj(wsvo.getFpdj());
		// 表面
		entity.setFace(wsvo.getFace());
		// 单价
		entity.setHtdj(wsvo.getHtdj());
		// 内径
		entity.setNeij(wsvo.getNeij().toString());
		// 合同完成状态
		entity.setQywl(wsvo.getQywl());
		// 更新时间赋值
		entity.setUpda(new Date());
		// 数据输入校验
		validate(entity, -1, true);
		if (flag) {
			dao.save(entity);
		}
		else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(List<String> htnoAndLines) {
		if (htnoAndLines == null || htnoAndLines.isEmpty()) {
			throw new CocoException(-1, "没有可删除的数据");
		}
		String[] arr;
		WwhtTp wwhtTp = null;
		for (String noAndLine : htnoAndLines) {
			arr = noAndLine.split("-");
			if (arr.length < 2) {
				continue;
			}
			wwhtTp = dao.get(new WwhtTpPk(arr[0], arr[1]));
			if (wwhtTp == null) {
				continue;
			}
			if (wwhtTp.getDhsl() != null && wwhtTp.getDhsl() > 0) {
				throw new CocoException(-1, "供应商合同NO" + arr[0] + "-" + arr[1]
						+ "已有原材,不能做删除操作");
			}
			if (CgConstants.CODE_QYWL_YWL.equals(wwhtTp.getQywl())) {
				throw new CocoException(-1, "供应商合同NO" + arr[0] + "-" + arr[1]
						+ "已" + CgConstants.CODE_QYWL_YWL + ",不能做删除操作");
			}
			dao.delete(new WwhtTpPk(arr[0], arr[1]));
		}
	}

	@Override
	public void query(QEntity<WwhtTp> qentity) {
		dao.query(qentity);
	}

	public IWwhtDAO getDao() {
		return dao;
	}

	public void setDao(IWwhtDAO dao) {
		this.dao = dao;
	}

	@Override
	public String getForJs(Serializable id) {
		WwhtTp entity = dao.get(id);
		if (entity == null) {
			return new Result(-1, "该原板采购合同信息不存在,请联系管理员").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public WwhtTp get(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.get(id);
	}

	@Override
	public void importHt(final DrVO vo) {
		Attachment attachment = attachmentBO.get(vo.getAttachId());
		if (attachment == null) {
			throw new CocoException(-1, "上传文件不存在");
		}
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(attachment.getStream());
		}
		catch (Exception e) {
		}
		if (is == null) {
			throw new CocoException(-1, "上传文件数据错误");
		}
		// 记录上传文档的错误信息
		final StringBuilder errors = new StringBuilder();
		// 记录文件中的供应商合同-行号。要保证供应商合同-行号唯一
		final List<String> htnos = new ArrayList<String>();
		// 创建时间
		final List<WwhtTp> wwhtTps = new ArrayList<WwhtTp>();
		bo.convert(Convert.wwhttp.name, WwhtTp.class, is, new IConverterCallback<WwhtTp>() {

			@Override
			public void doRow(ConvertRowVO<WwhtTp> row) {
				WwhtTp tp = row.getVo();
				if (tp == null || tp.getHtno() == null
						|| tp.getHtno().isEmpty()) {
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
				// 验证供应商合同-行号不能为空和唯一性。上传的供应商合同文件中供应商合-行号是由供应商合同NO和行号组合而成。
				String htno = null;
				if ((htno = tp.getHtno()) != null && !htno.isEmpty()) {
					if (htnos.contains(htno)) {
						result.append("上传文件中供应商合同-行号:").append(htno).append("重复; ");
					}
					htnos.add(htno);
					String[] s = htno.split("-");
					if (s.length == 2) {
						if (s[0].length() != 7) {
							result.append("供应商合同NO长度不为七位; ");
						}
						if (!Pattern.matches("\\w+", s[0])) {
							result.append("供应商合同NO只能是数字和字母; ");
						}
						if (s[1].length() != 3) {
							result.append("供应商合同行号长度不为三位; ");
						}
						if (!Pattern.matches("\\w+", s[1])) {
							result.append("供应商合同行号只能是数字和字母; ");
						}
						WwhtTpPk tpPk = new WwhtTpPk(s[0], s[1]);
						if (get(tpPk) != null) {
							result.append("该供应商合同已存在; ");
						}
						tp.setHtno(s[0]);
						tp.setLine(s[1]);
					}
					else {
						result.append("供应商合同的格式不正确。正确格式如：XXXXXXX-XXX; ");
					}
				}
				else {
					result.append("供应商合同为空; ");
				}
				// 验证制造商代码。判断不能为空和在码表中是否存在,同时根据制造商代码获得对应的制造商名称
				String zzsd = null;
				CodeItem codeItem = null;
				if ((zzsd = tp.getZzsd()) != null && !zzsd.isEmpty()) {
					codeItem = codeBo.getCodeItem(CmnConstants.CODE_012, zzsd);
					if (codeItem == null) {
						result.append("制造商在码表中不存在; ");
					}
					else {
						tp.setZzsm(codeItem.getValue());
					}
				}
				else {
					result.append("制造商为空; ");
				}
				// 验证表面。判断不能为空和在码表中是否存在
				String face = null;
				if ((face = tp.getFace()) != null && !face.isEmpty()) {
					if (!codeBo.isExisted(CmnConstants.CODE_005, face)) {

						result.append("表面在码表中不存在; ");
					}
				}
				else {
					result.append("表面为空; ");
				}
				// 验证尺寸.厚。判断不能为空并且大于0
				Double houu = null;
				if ((houu = tp.getHouu()) == null) {
					result.append("尺寸.厚为空; ");
				}
				else if (houu <= 0) {
					result.append("尺寸.厚不能小于或等于0; ");
				}
				// 验证尺寸.宽。判断不能为空并且大于0
				Double kuan = null;
				if ((kuan = tp.getKuan()) == null) {
					result.append("尺寸.宽为空; ");
				}
				else if (kuan <= 0) {
					result.append("尺寸.宽不能小于或等于0; ");
				}
				// 验证合同重量。判断不能为空并且大于0
				Double htzl = null;
				if ((htzl = tp.getHtzl()) == null) {
					result.append("重量为空; ");
				}
				else if (htzl <= 0) {
					result.append("重量不能小于或等于0; ");
				}
				// 验证制造商规格略称。判断不能为空和在码表中是否存在,同时根据制造商规格略称获得中日达规格略称
				String zzgg = null;
				if ((zzgg = tp.getZzgg()) != null && !zzgg.isEmpty()) {
					codeItem = codeBo.getCodeItem(CmnConstants.CODE_017, zzgg);
					if (codeItem == null) {
						result.append("制造商规格略称在码表中不存在; ");
					}
					else {
						tp.setZrgg(codeItem.getValue());
					}
				}
				else {
					result.append("制造商规格略称为空; ");
				}
				// 验证合同单价。判断不能为空并且大于0
				double htdj = tp.getHtdj() != null ? tp.getHtdj() : 0d;
				String yuny = null, knfw = null;
				if (htdj <= 0 && zzgg != null) {
					yuny = zzgg.indexOf(DR) >= 0 ? DR : SR;
					knfw = kuan > 700 ? EKnfw.s.key : EKnfw.x.key;
					htdj = cgdjDAO.getCgdj((houu >= 0.3 ? 0.3 : houu), knfw, yuny);
					if (htdj <= 0) {
						result.append("采购单价管理中板厚为" + houu + "、板宽为" + kuan
								+ "、材质为" + yuny + "的原板采购单价未设置; ");
					}
					tp.setHtdj(htdj);
				}
				// 验证品种。判断不能为空和在码表中是否存在。品种的第一位和第二位分别对应不同的码表
				String pzno = null;
				if ((pzno = tp.getPzno()) != null && pzno.length() == 2) {
					if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
						result.append("品种第一位在码表中不存在; ");
					}
					if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
						result.append("品种第二位在码表中不存在; ");
					}
				}
				else if (pzno != null) {
					result.append("品种格式不正确。正确格式如：XX; ");
				}
				else {
					result.append("品种为空; ");
				}
				// 验证内径。判断不能为空和在码表中是否存在。
				String neij = null;
				if ((neij = tp.getNeij()) != null && !neij.isEmpty()) {
					if (codeBo.getUniqueItemByValue(CmnConstants.CODE_020, neij) == null) {
						result.append("内径在码表中不存在; ");
					}
				}
				else {
					result.append("内径为空; ");
				}
				// 验证等级。判断不能为空和在码表中是否存在。等级的第一位、第二位和第三位分别对应不同的码表
				String fpdj = null;
				if ((fpdj = tp.getFpdj()) != null && fpdj.length() == 3) {
					if (!codeBo.isExisted(CmnConstants.CODE_103, fpdj.substring(0, 1))) {
						result.append("分配等级第一位在码表中不存在; ");
					}
					if (!codeBo.isExisted(CmnConstants.CODE_104, fpdj.substring(1, 2))) {
						result.append("分配等级第二位在码表中不存在; ");
					}
					if (!codeBo.isExisted(CmnConstants.CODE_105, fpdj.substring(2, 3))) {
						result.append("分配等级第三位在码表中不存在; ");
					}
				}
				else if (fpdj != null) {
					result.append("分配等级格式不正确。正确格式如：XXX; ");
				}
				else {
					result.append("分配等级格式为空;  ");
				}
				// 验证文件中的数据与对应表中字段的定义是否一致
				List<JpaUtils.Error> vos = JpaUtils.validate(tp);
				for (JpaUtils.Error e : vos) {
					// 上传文件中供应商合同和行号是存在供应商合同-行号列中。
					if (e.getField().equals("htno")) {
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
					// 签约日期
					tp.setQyri(vo.getQyri());
					// 合同日
					tp.setHtdt(vo.getHtdt());
					// 商社代码
					tp.setSsno(vo.getSsno());
					// 商社名称
					tp.setSsnm(vo.getSsnm());
					// 币种
					tp.setThqf(vo.getThqf());
					// 签约完了标志
					tp.setQywl(CgConstants.CODE_QYWL_WWL);
					// 创建时间
					tp.setCrea(new Date());
				}
				wwhtTps.add(tp);
			}
		});
		if (is != null) {
			try {
				is.close();
			}
			catch (IOException e) {
			}
		}
		if (errors.length() > 0) {
			throw new CocoException(-2, errors.toString());
		}
		if (wwhtTps == null || wwhtTps.isEmpty()) {
			throw new CocoException(-2, "上传文件内容为空");
		}
		dao.saveAll(wwhtTps);
		attachment.setType(Convert.wwhttp.name);
		attachmentBO.update(attachment);
	}

	@Override
	public void updateDhsl(Serializable id, Double zpzl) {
		if (id == null) {
			throw new CocoException(-1, "供应商合同主键为空");
		}
		if (zpzl == null) {
			throw new CocoException(-1, "原板重量为空");
		}
		WwhtTp wwhtTp = dao.get(id);
		if (wwhtTp == null) {
			throw new CocoException(-1, "供应商合同不存在");
		}
		// 到货数量
		double dhsl = wwhtTp.getDhsl() != null ? wwhtTp.getDhsl() : 0;
		// 增加到货数量
		wwhtTp.setDhsl(dhsl + zpzl);
		// 设置签约完了标识
		if (wwhtTp.getDhsl() >= wwhtTp.getHtzl()) {
			wwhtTp.setQywl(CgConstants.CODE_QYWL_YWL);
		}
		else {
			wwhtTp.setQywl(CgConstants.CODE_QYWL_WWL);
		}
		dao.update(wwhtTp);
	}

	@Override
	public void queryCgdj(QEntity<YbCgdj> qentity) {
		cgdjDAO.query(qentity);
	}

	@Override
	public String saveCgdj(YbCgdj entity) {
		if (cgdjDAO.isExisted(entity.getXpho(), entity.getKnfw(), entity.getYuny())) {
			return new Result(-1, "板厚：" + entity.getXpho() + "、板宽："
					+ EKnfw.get(entity.getKnfw()).value + "、材质："
					+ entity.getYuny() + ",已存在").toString();
		}
		YbCgdj $entity = cgdjDAO.get();
		double extra = entity.getExtra() != null ? entity.getExtra() : 0d;
		double base = 0d, lxll = 0d, fob = 0d, yf = 0d, freight = 0d, cfr = 0d, cgdj = 0d, jj = 0d;
		int days = 0;
		if ($entity != null) {
			base = $entity.getBase() != null ? $entity.getBase() : 0d;
			lxll = $entity.getLxll() != null ? $entity.getLxll() : 0d;
			yf = $entity.getYf() != null ? $entity.getYf() : 0d;
			days = $entity.getDays() != null ? $entity.getDays() : 0;

			fob = NumberUtils.format(base + extra, 2);
			freight = cfr = NumberUtils.format(fob + yf, 2);
			jj = (days == 0 ? 0 : (cfr * (days / 360d) * (lxll + 0.013)));

			cgdj = NumberUtils.format(cfr + jj, 2);
		}
		entity.setFob(fob);
		entity.setFreight(freight);
		entity.setCfr(cfr);
		entity.setCgdj(cgdj);

		entity.setBase(base);
		entity.setYf(yf);
		entity.setLxll(lxll);
		entity.setDays(days);
		if (entity.getId() == null || entity.getId().isEmpty()) {
			entity.setCrea(new Date());
			cgdjDAO.save(entity);
		}
		else {
			cgdjDAO.update(entity);
		}
		return Result.SUCCESS;
	}

	@Override
	public void deleteCgdj(String[] ids) {
		for (String id : ids) {
			cgdjDAO.delete(id);
		}
	}

	@Override
	public void calculateCgdj(BaseVO vo) {
		List<YbCgdj> ybCgdjs = cgdjDAO.find();
		if (ybCgdjs.size() > 0) {
			double base = 0d, lxll = 0d, fob = 0d, yf = 0d, freight = 0d, cfr = 0d, cgdj = 0d, extra = 0d, jj = 0d;
			int days = 0;
			for (YbCgdj entity : ybCgdjs) {
				extra = entity.getExtra() != null ? entity.getExtra() : 0d;
				base = vo.getBase() != null ? vo.getBase() : 0d;
				lxll = vo.getLxll() != null ? vo.getLxll() : 0d;
				yf = vo.getYf() != null ? vo.getYf() : 0d;
				days = vo.getDays() != null ? vo.getDays() : 0;

				fob = NumberUtils.format(base + extra, 2);
				freight = cfr = NumberUtils.format(fob + yf, 2);
				jj = (days == 0 ? 0 : (cfr * (days / 360d) * (lxll + 0.013)));
				cgdj = NumberUtils.format(cfr + jj, 2);
				entity.setFob(fob);
				entity.setFreight(freight);
				entity.setCfr(cfr);
				entity.setCgdj(cgdj);

				entity.setBase(base);
				entity.setYf(yf);
				entity.setLxll(lxll);
				entity.setDays(days);
			}
			cgdjDAO.saveAll(ybCgdjs);
		}
	}

	@Override
	public String loadCgdj(Serializable id) {
		YbCgdj entity = cgdjDAO.get(id);
		if (entity == null) {
			return new Result(-1, "该原板采购单价信息不存在,请联系管理员").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public String getBase() {
		YbCgdj ybCgdj = cgdjDAO.get();
		if (ybCgdj == null) {
			return new Result(-1, "还未设置基础费用").toString();
		}
		BaseVO vo = new BaseVO();
		vo.setLxll(ybCgdj.getLxll());
		vo.setDays(ybCgdj.getDays());
		vo.setBase(ybCgdj.getBase());
		vo.setYf(ybCgdj.getYf());
		return new Result(vo).toJsObject();
	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IAttachmentBO getAttachmentBO() {
		return attachmentBO;
	}

	public void setAttachmentBO(IAttachmentBO attachmentBO) {
		this.attachmentBO = attachmentBO;
	}

	public IConvertBO getBo() {
		return bo;
	}

	public void setBo(IConvertBO bo) {
		this.bo = bo;
	}

	public IYcaitpBO getYcaiBO() {
		return ycaiBO;
	}

	public void setYcaiBO(IYcaitpBO ycaiBO) {
		this.ycaiBO = ycaiBO;
	}

	public ICgdjDAO getCgdjDAO() {
		return cgdjDAO;
	}

	public void setCgdjDAO(ICgdjDAO cgdjDAO) {
		this.cgdjDAO = cgdjDAO;
	}

}
