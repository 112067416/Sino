package com.quanta.sino.kcgl.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coco.core.bean.Result;
import com.coco.sys.bo.api.IAttachmentBO;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.Attachment;
import com.quanta.sino.cmn.constants.CmnConstants;
import com.quanta.sino.cmn.constants.DC;
import com.quanta.sino.cmn.constants.EFpyc;
import com.quanta.sino.cmn.constants.EScbj;
import com.quanta.sino.cmn.constants.EXpzk;
import com.quanta.sino.cmn.constants.YbStat;
import com.quanta.sino.cmn.constants.ZpStat;
import com.quanta.sino.etl.bo.api.IZpBO;
import com.quanta.sino.kcgl.bo.api.IKcglBO;
import com.quanta.sino.kcgl.vo.ZppdQE;
import com.quanta.sino.kcgl.vo.ZppdVO;
import com.quanta.sino.orm.YcaiTp;
import com.quanta.sino.orm.ZpngTp;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.ycai.vo.DrVO;
import com.quanta.sino.ycai.vo.YcaiQE;

/**
 * <p>
 * 库存管理实现类
 * </p>
 * <p>
 * create: 2011-2-18 上午10:39:19
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
public class KcglBO implements IKcglBO {

	/**
	 * 制品业务层接口
	 */
	private IZpBO zpBO;

	/**
	 * 原板业务接口
	 */
	private IYcaitpBO ycaitpBO;

	/**
	 * 码表接口
	 */
	private ICodeBO codeBo;

	/**
	 * 
	 */
	private IAttachmentBO attachmentBO;

	@Override
	public String importZprk(DrVO entity) {
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
			ZpngTp zpngTp = null;
			if (jbno == null || (jbno = jbno.trim()).isEmpty()) {
				result.append("制品NO为空; ");
			}
			else {
				zpngTp = zpBO.getZp(jbno);
				if (zpngTp == null) {
					result.append("制品NO为").append(jbno).append("不存在; ");
				}
				else {
					if (!ZpStat.CS.stat.equals(zpngTp.getStat())) {
						result.append("该制品状态为 ").append(ZpStat.get(zpngTp.getStat()).name).append(",不能做制品入库操作; ");
					}
				}
			}
			// 验证库位长度和不能为空
			String kw = arrs[1];
			if (kw == null || (kw = kw.trim()).isEmpty()) {
				result.append("库位为空; ");
			}
			else if (kw.length() != 4) {
				result.append("库位长度无效; ");
			}
			else if (!DC.G.name.equals(kw.substring(0, 1))) {
				result.append("库位标签不正确,应为G堆场开头; ");
			}
			else if (!codeBo.isExisted(CmnConstants.CODE_011, kw)) {
				result.append("库位" + kw + "在码表中不存在; ");
			}
			// 记录该行的数据验证错误信息
			if (result.length() > 0) {
				errors.append("第").append(index).append("行错误信息。").append(result.toString()).append("<br />");
			}
			else {
				// G堆场
				zpngTp.setDuic(DC.G.name);
				// 库位
				zpngTp.setKw(kw);
				// 入库时间
				zpngTp.setDuib(duib);
				zpBO.update(zpngTp);
			}
			index++;
		}
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		return new Result(1, "保存成功").toString();
	}

	@Override
	public String zppd(DrVO entity, String[] duics) {
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
		String content = new String(bytes);
		String[] lines = content.split("\n");
		List<String> swJbnos = new ArrayList<String>();
		// 记录当前行的数据验证结果
		StringBuilder result = null;
		String[] arrs = null;
		int index = 1;
		// 将实物与数据库的制品信息进行比较
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
			ZpngTp zpngTp = null;
			if (jbno == null || (jbno = jbno.trim()).isEmpty()) {
				result.append("制品NO为空; ");
			}
			else {
				swJbnos.add(jbno);
				zpngTp = zpBO.getZp(jbno);
				if (zpngTp == null) {
					result.append("数据库中制品").append(jbno).append("不存在; ");
				}
				else {
					if (!EScbj.CS.key.equals(zpngTp.getScbj())) {
						result.append("数据库中制品").append(jbno).append("已做").append(EScbj.get(zpngTp.getScbj()).value).append(",不过实物存在; ");
					}
					else {
						// 验证库位长度和不能为空
						String kw = arrs[1];
						kw = kw != null ? kw.trim() : "";
						String $kw = zpngTp.getKw();
						$kw = $kw != null ? $kw.trim() : "";
						if (!kw.equalsIgnoreCase($kw)) {
							result.append("数据库中制品").append(jbno).append("存放在").append($kw).append(",不过实物实际存放位置在").append(kw);
						}
					}
				}
			}
			// 记录该行的数据验证错误信息
			if (result.length() > 0) {
				errors.append("第").append(index).append("行错误信息。").append(result.toString()).append("<br />");
			}
			index++;
		}
		StringBuilder $errors = new StringBuilder();
		ZppdQE page = new ZppdQE();
		page.setSize(100);
		// 判断数据库存的制品，而实物不存在
		String[] xpzks = { EXpzk.JZP.key, EXpzk.BZP.key };
		// 设置现品在库
		page.setXpzks(xpzks);
		// 设置堆场
		// String[] duics = { DC.C.name, DC.F.name, DC.G.name };
		page.setDuics(duics);
		// 分配/余材标记
		page.setFpyc(EFpyc.FP.key);
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		page.setStat(ZpStat.CS.stat);
		page.setOrderBys("jbno");
		zpBO.queryZppd(page);
		for (ZppdVO zppdVo : page.getDatas()) {
			if (swJbnos.contains(zppdVo.getJbno())) continue;
			if ($errors.length() == 0) {
				$errors.append(zppdVo.getJbno());
			}
			else {
				$errors.append("、").append(zppdVo.getJbno());
			}
		}
		int pageCount = page.getPageCount();
		for (int pageIndex = 1; pageIndex < pageCount; pageIndex++) {
			page.setIndex(pageIndex);
			zpBO.queryZppd(page);
			for (ZppdVO zppdVo : page.getDatas()) {
				if (swJbnos.contains(zppdVo.getJbno())) continue;
				if ($errors.length() == 0) {
					$errors.append(zppdVo.getJbno());
				}
				else {
					$errors.append("、").append(zppdVo.getJbno());
				}
			}
		}
		if ($errors.length() > 0) {
			errors.append("<br />制品在数据库中存在,而实物不存在。<br />").append($errors.toString());
		}
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		return new Result(1, "保存成功").toString();
	}

	@Override
	public String scpd(DrVO entity, String[] duics) {
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
		String content = new String(bytes);
		String[] lines = content.split("\n");
		List<String> swJbnos = new ArrayList<String>();
		// 记录当前行的数据验证结果
		StringBuilder result = null;
		String[] arrs = null;
		int index = 1;
		// 将实物与数据库的制品信息进行比较
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
				result.append("制品NO为空; ");
			}
			else {
				swJbnos.add(jbno);
				ycaiTp = ycaitpBO.get(jbno);
				if (ycaiTp == null) {
					result.append("数据库中原材").append(jbno).append("不存在; ");
				}
				else {
					if (!EScbj.CS.key.equals(ycaiTp.getScbj())) {
						result.append("数据库中原材").append(jbno).append("已做").append(EScbj.get(ycaiTp.getScbj()).value).append(",不过实物存在; ");
					}
					else {
						// 验证库位长度和不能为空
						String kw = arrs[1];
						kw = kw != null ? kw.trim() : "";
						String $kw = ycaiTp.getKw();
						$kw = $kw != null ? $kw.trim() : "";
						if (!kw.equalsIgnoreCase($kw)) {
							result.append("数据库中原材").append(jbno).append("存放在").append($kw).append(",不过实物实际存放位置在").append(kw);
						}
					}
				}
			}
			// 记录该行的数据验证错误信息
			if (result.length() > 0) {
				errors.append("第").append(index).append("行错误信息。").append(result.toString()).append("<br />");
			}
			index++;
		}
		StringBuilder $errors = new StringBuilder();
		YcaiQE page = new YcaiQE();
		page.setSize(100);
		page.setDuics(duics);
		// 判断数据库存的制品，而实物不存在
		// 删除标记
		page.setScbj(EScbj.CS.key);
		// 状态
		String[] stats = { YbStat.CS.stat, YbStat.YRK.stat, YbStat.YFP.stat };
		page.setStats(stats);
		page.setOrderBys("jbno");
		ycaitpBO.query(page);
		for (YcaiTp ycaiTp : page.getDatas()) {
			if (swJbnos.contains(ycaiTp.getJbno())) continue;
			if ($errors.length() == 0) {
				$errors.append(ycaiTp.getJbno());
			}
			else {
				$errors.append("、").append(ycaiTp.getJbno());
			}
		}
		int pageCount = page.getPageCount();
		for (int pageIndex = 1; pageIndex < pageCount; pageIndex++) {
			page.setIndex(pageIndex);
			ycaitpBO.query(page);
			for (YcaiTp ycaiTp : page.getDatas()) {
				if (swJbnos.contains(ycaiTp.getJbno())) continue;
				if ($errors.length() == 0) {
					$errors.append(ycaiTp.getJbno());
				}
				else {
					$errors.append("、").append(ycaiTp.getJbno());
				}
			}
		}
		if ($errors.length() > 0) {
			errors.append("<br />原板在数据库中存在,而实物不存在。<br />").append($errors.toString());
		}
		if (errors.length() > 0) {
			return new Result(-1, errors.toString()).toString();
		}
		return new Result(1, "保存成功").toString();
	}

	public IZpBO getZpBO() {
		return zpBO;
	}

	public void setZpBO(IZpBO zpBO) {
		this.zpBO = zpBO;
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

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}

}
