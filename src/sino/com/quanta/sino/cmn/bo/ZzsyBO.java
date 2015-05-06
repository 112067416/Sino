package com.quanta.sino.cmn.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.ReflectUtils;
import com.coco.sys.bo.api.ICodeBO;
import com.coco.sys.orm.CodeItem;
import com.quanta.sino.cmn.bo.api.IInformBO;
import com.quanta.sino.cmn.bo.api.IYongBO;
import com.quanta.sino.cmn.bo.api.IZzsyBO;
import com.quanta.sino.cmn.constants.CmnConstants;
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
import com.quanta.sino.cmn.constants.ProductType;
import com.quanta.sino.cmn.dao.api.IZzsyDAO;
import com.quanta.sino.cmn.vo.ZzsyConstants;
import com.quanta.sino.dh.constants.DhCh;
import com.quanta.sino.dh.constants.DhFzlDw;
import com.quanta.sino.dh.vo.ZzsyVO;
import com.quanta.sino.orm.ZzsyMp;

/**
 * <p>
 * 制造仕样主文件业务实现
 * </p>
 * <p>
 * create: 2011-2-12 上午10:09:05
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ZzsyBO implements IZzsyBO {

	private IZzsyDAO dao;

	/**
	 * 码表
	 */
	private ICodeBO codeBo;

	/**
	 * 用户主文件
	 */
	private IYongBO yongBo;

	/**
	 * 资料室业务接口
	 */
	private IInformBO informBO;

	/**
	 * 镀锡量(大于25号时，合金层为必填项)
	 */
	private static final String DXL = "025";

	/**
	 * 
	 */
	private static final Set<String> EXCLUDE_FIELDS = new HashSet<String>();

	static {
		EXCLUDE_FIELDS.add("crea");
	}

	@Override
	public String save(ZzsyMp entity) {
		if (this.get(entity.getSyno()) != null) {
			return new Result(-1, "该仕样号码已经在使用中").toString();
		}
		entity.setCrea(new Date());
		String mgsn = entity.getMgsn();
		entity.setMgsn(mgsn != null ? mgsn.replaceAll("，", ",") : null);
		dao.save(entity);
		return Result.SUCCESS;
	}

	@Override
	public String update(ZzsyMp entity) {
		ZzsyMp zzsyMp = null;
		if ((zzsyMp = dao.get(entity.getSyno())) == null) {
			return new Result(-1, "无法获取仕样信息").toString();
		}
		ReflectUtils.copy(zzsyMp, entity, null, EXCLUDE_FIELDS, false);
		zzsyMp.setUpda(new Date());
		String mgsn = zzsyMp.getMgsn();
		zzsyMp.setMgsn(mgsn != null ? mgsn.replaceAll("，", ",") : null);
		dao.update(zzsyMp);
		return Result.SUCCESS;
	}

	@Override
	public String validate(ZzsyMp entity) {

		// 验证规格
		String ggno = entity.getGgno();
		if (ggno == null || ggno.isEmpty()) {
			return new Result(-3, "规格为必填项").toString();
		}
		if (ggno.length() != 4) {
			return new Result(-3, "规格输入有误,长度为四位").toString();
		}
		// 校验钢种类
		if (!codeBo.isExisted(CmnConstants.CODE_109, entity.getGgno().substring(0, 1))) {
			return new Result(-3, "规格输入有误,规格的第一位即钢种类在码表中不存在").toString();
		}
		// 校验退火代号（烧炖方法）
		if (!codeBo.isExisted(CmnConstants.CODE_110, entity.getGgno().substring(1, 2))) {
			return new Result(-3, "规格输入有误,规格的第二位即退火代号在码表中不存在").toString();
		}
		// 校验调质度代号（硬度）
		if (!codeBo.isExisted(CmnConstants.CODE_111, entity.getGgno().substring(2, 4))) {
			return new Result(-3, "规格输入有误,规格的后两位即调质度代号在码表中不存在").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_003, ggno)) {
			return new Result(-3, "规格输入有误,在码表中不存在").toString();
		}
		// 验证品种
		String pzno = entity.getPzno();
		if (pzno == null || pzno.isEmpty()) {
			return new Result(-7, "品种为必填项").toString();
		}
		if (pzno.length() != 2) {
			return new Result(-7, "品种输入有误,长度为两位").toString();
		}
		// 校验品种
		if (!codeBo.isExisted(CmnConstants.CODE_118, pzno.substring(0, 1))) {
			return new Result(-7, "品种输入有误,品种的第一位在码表中不存在").toString();
		}
		// 校验品种等级
		if (!codeBo.isExisted(CmnConstants.CODE_119, pzno.substring(1, 2))) {
			return new Result(-7, "品种输入有误,品种的第二位即等级在码表中不存在").toString();
		}
		// 验证附着量.单位
		String fudw = entity.getFudw();
		if (fudw == null || fudw.isEmpty()) {
			return new Result(-4, "附着量单位为必填项").toString();
		}
		if (fudw.length() != 2) {
			return new Result(-4, "附着量.单位输入有误,长度为两位").toString();
		}
		if (DhFzlDw.get(entity.getFudw()) == null) {
			return new Result(-4, "附着量.单位输入有误,只能输入WB或GM").toString();
		}
		// 验证附着量.正面
		String fuzm = entity.getFuzm();
		if (fuzm == null || fuzm.isEmpty()) {
			return new Result(-5, "附着量.正面为必填项").toString();
		}
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fuzm)) {
			return new Result(-5, "附着量.正面输入有误,在码表中不存在").toString();
		}
		// 验证附着量.反面
		String fufm = entity.getFufm();
		if (fufm == null || fufm.isEmpty()) {
			return new Result(-6, "附着量.反面为必填项").toString();
		}
		// 校验附着量.反面
		if (!codeBo.isExisted(CmnConstants.CODE_024, fudw + fufm)) {
			return new Result(-6, "附着量.反面输入有误,在码表中不存在").toString();
		}
		// 验证差厚
		String chdx = entity.getChdx();
		if (fuzm.equals(fufm)) {
			if (chdx != null && !chdx.isEmpty()) {
				return new Result(-8, "差厚输入有误,当附着量.正面与附着量.反面相同,差厚必须为空").toString();
			}
		}
		else {
			if (chdx == null || chdx.isEmpty()) {
				return new Result(-8, "差厚输入有误,当附着量.正面与附着量.反面不相同时,差厚为必填项").toString();
			}
			else {
				// 验证输入的值必须为A1、A2、D1、D2中的一个
				if (DhCh.get(chdx) == null) {
					return new Result(-8, "差厚输入有误,差厚只能是A1、A2、D1或D2").toString();
				}
			}
		}
		// 验证内外销为必填项
		String nwai = entity.getNwai();
		if (nwai == null || nwai.isEmpty()) {
			return new Result(-9, "内外为必填项").toString();
		}
		if (nwai != null && nwai.isEmpty() && CodeNwx.get(nwai) == null) {
			return new Result(-9, "内外输入有误,内外只能是1、2或3").toString();
		}
		// 是否为钢卷类型合同
		boolean isCoil = Code118.coil.key.equals(pzno.substring(0, 1));
		double cmin = entity.getCmin() != null ? entity.getCmin() : 0d;
		if (isCoil && cmin > 0) {
			return new Result(-14, "钢卷合同没有剪切长").toString();
		}
		double cmax = entity.getCmax() != null ? entity.getCmax() : 0d;
		if (isCoil && cmax > 0) {
			return new Result(-15, "钢卷合同没有剪切长").toString();
		}
		double cang = entity.getCang() != null ? entity.getCang() : 0d;
		if (isCoil && cang > 0) {
			return new Result(-18, "钢卷合同没有剪切长").toString();
		}
		if (!isCoil && cmin == 0 && cmax == 0 && cang == 0) {
			return new Result(-14, "剪切板合同剪切长为必填项").toString();
		}
		// 订货合同等级
		String deng = pzno.substring(1);
		// 验证
		String cond = entity.getCond();
		if (cond != null && !cond.isEmpty()) {
			if (cond.length() != 4) {
				return new Result(-20, "加工用途输入有误,长度为四位").toString();
			}
			// 校验最终用途
			if (!codeBo.isExisted(CmnConstants.CODE_114, cond.substring(0, 1))) {
				return new Result(-20, "加工用途输入有误,加工用途的第一位即最终用途在码表中不存在").toString();
			}
			// 校验加工条件
			if (!codeBo.isExisted(CmnConstants.CODE_115, cond.substring(1, 2))) {
				return new Result(-20, "加工用途输入有误,加工用途的第二位即加工条件在码表中不存在").toString();
			}
			// 校验罐头内装物
			if (!codeBo.isExisted(CmnConstants.CODE_116, cond.substring(2, 3))) {
				return new Result(-20, "加工用途输入有误,加工用途的第三位即罐头内装物在码表中不存在").toString();
			}
			// 校验涂装条件
			if (!codeBo.isExisted(CmnConstants.CODE_117, cond.substring(3, 4))) {
				return new Result(-20, "加工用途输入有误,加工用途的第四位即涂装条件在码表中不存在").toString();
			}
		}
		// 验证用户代码
		String user = entity.getUser();
		if (user != null && !yongBo.isExistUser(user)) {
			return new Result(-21, "用户代码" + user + ",在用户主文件中不存在").toString();
		}
		// 剪边 （T-剪边、N-不剪边）
		// 注：当品种代码第1位为'2'（卷材）时，仅仅指定’N’
		String jian = entity.getJian();
		if (jian != null && !jian.isEmpty()) {
			if (CodeJian.get(jian) == null) {
				return new Result(-23, "剪边输入有误,剪边只能是N或T").toString();
			}
			if (isCoil && !CodeJian.N.key.equals(jian)) {
				return new Result(-23, "当订货合同类型为钢卷时,剪边只能是N").toString();
			}
		}
		// 零头下限.单位 （P-张数、B-%、N-不指定）
		// 切板必填。
		String ltdw = entity.getLtdw();
		if (!isCoil && (ltdw == null || ltdw.isEmpty())) {
			return new Result(-24, "当订货合同类型为剪切板时,零头下限.单位为必填项").toString();
		}
		if (isCoil && ltdw != null && !ltdw.isEmpty()) {
			return new Result(-24, "当订货合同类型为钢卷时,没有零头下限.单位").toString();
		}
		if (ltdw != null && CodeLtdw.get(ltdw) == null) {
			return new Result(-24, "零头下限.单位输入有误,零头下限.单位只能是B、P或N").toString();
		}
		Integer ltzi = entity.getLtzi();
		if (!isCoil && (ltzi == null || ltzi.intValue() == 0)) {
			return new Result(-25, "当订货合同类型为剪切板时,零头下限.值为必填项").toString();
		}
		if (isCoil && ltzi != null && ltzi > 0) {
			return new Result(-25, "当订货合同类型为钢卷时,没有零头下限.值").toString();
		}
		// 验证涂油种类
		String ytyp = entity.getYtyp();
		if (ytyp == null || ytyp.isEmpty()) {
			return new Result(-28, "涂油种类必须填写").toString();
		}
		if (!CodeTyzl.DOS.key.equals(ytyp)) {
			return new Result(-28, "涂油种类输入有误,涂油种类只能是D").toString();
		}
		// 验证涂油量
		String yqty = entity.getYqty();
		if (yqty == null || yqty.isEmpty()) {
			return new Result(-32, "涂油量必填项").toString();
		}
		// KPLATE
		// 说明：K值之外有错误
		String plat = entity.getPlat();
		if (plat != null && !plat.isEmpty() && !CodeKPlate.K.key.equals(plat)) {
			return new Result(-29, "KPLATE输入有误,KPLATE只能是空或K").toString();
		}
		// 合金层
		// 说明：K、5值之外有错误
		String hjin = entity.getHjin();
		if (DXL.compareTo(entity.getFufm()) <= 0
				|| DXL.compareTo(entity.getFuzm()) <= 0) {
			if (hjin == null || hjin.isEmpty()) {
				return new Result(8, "当附着量大于" + DXL + "时,合金层为必填项").toString();
			}
		}
		if (hjin != null && !hjin.isEmpty()
				&& !codeBo.isExisted(CmnConstants.SINO_HJIN, hjin)) {
			return new Result(-33, "合金层输入有误,在码表中不存在").toString();
		}
		// 分配等级：通用主文件ID（103、104、105）
		// 第一位：订货等级（G-国内合格、P-出口合格、K-其他、空-无规发生）
		// 第二位：表面性状等级（S、A、B、C、D）
		// 第三位：形状等级（S、A、B、C、D）
		String fpdj = entity.getFpdj();
		if (fpdj == null || fpdj.isEmpty()) {
			return new Result(-36, "分配等级必填项").toString();
		}
		if (fpdj.length() != 3) {
			return new Result(-36, "分配等级输入有误,分配等级长度为三位").toString();
		}
		if (Code119.prime.key.equals(deng)) {
			if (CodeNwx.nei.key.equals(nwai)
					&& !Code103.G.key.equals(fpdj.substring(0, 1))) {
				return new Result(-36, "分配等级输入有误,当订货合同的内外销为" + CodeNwx.nei.name
						+ "并且等级为" + Code119.prime.key + "时,分配等级的订货等级（第一位）必须为"
						+ Code103.G.key).toString();
			}
			if (CodeNwx.wai.key.equals(nwai)
					&& !Code103.P.key.equals(fpdj.substring(0, 1))) {
				return new Result(-36, "分配等级输入有误,当订货合同的内外销为" + CodeNwx.wai.name
						+ "并且等级为" + Code119.prime.key + "时,分配等级的订货等级（第一位）必须为"
						+ Code103.P.key).toString();
			}
		}
		else {
			if (!Code103.K.key.equals(fpdj.substring(0, 1))) {
				return new Result(-36, "分配等级输入有误,当等级为" + Code119.get(deng).key
						+ "时,分配等级的订货等级（第一位）必须为" + Code103.K.key).toString();
			}
		}
		if (Code104.get(fpdj.substring(1, 2)) == null) {
			return new Result(-36, "分配等级输入有误,分配等级的表面性状等级（第二位）只能是A、B、C、D或S").toString();
		}
		if (Code105.get(fpdj.substring(2)) == null) {
			return new Result(-36, "分配等级输入有误,分配等级的形状等级（第三位）只能是A、B、C、D或S").toString();
		}
		// 直角度 品种代码为1时必须指定。设定对于基准长度600mm的偏差程度。
		Double jiao = entity.getJiao();
		if (!isCoil && jiao == null) {
			return new Result(-39, "当订货合同类型为剪切板时,直角度为必填项").toString();
		}
		// 保护标记（空-不要、T-仅上部、W-上下部）。马口铁剪切板
		String prot = entity.getProt();
		if (isCoil && prot != null && !prot.isEmpty()) {
			return new Result(-46, "当订货合同类型为钢卷时,没有保护标记").toString();
		}
		if (prot != null && CodeProt.get(prot) == null) {
			return new Result(-46, "保护板输入有误,保护板只能是空、T或W").toString();
		}
		// 锯齿形式 通用主文件ID=008。
		// 若为锯齿剪切板时，因为宽、长尺寸被指定到小数点以下2位，若以能同剪切板相区别。
		String jcxs = entity.getJcxs();
		if (isCoil && jcxs != null && !jcxs.isEmpty()) {
			return new Result(-48, "当订货合同类型为钢卷时,没有锯齿形式").toString();
		}
		if (jcxs != null && !jcxs.isEmpty()) {
			CodeItem item = codeBo.getUniqueItemByValue(CmnConstants.CODE_008, jcxs);
			if (item == null) {
				return new Result(-48, "锯齿形式板输入有误,锯齿形式在码表中不存在").toString();
			}
		}
		// 内径保护筒 （空-不要、1-纸保护筒、2-钢保护筒）。马口铁钢卷
		String njbh = entity.getNjbh();
		// if (!isCoil && njbh != null && !njbh.isEmpty()) {
		// return new Result(-42, "当订货合同类型为剪切板时,没有内径保护筒").toString();
		// }
		if (njbh != null && CodeNjbh.get(njbh) == null) {
			return new Result(-42, "内径保护筒 输入有误,内径保护筒 只能是空、1或2").toString();
		}
		// 装入宽
		Double zrkn = entity.getZrkn();
		if (isCoil && zrkn != null && zrkn > 0) {
			return new Result(-47, "当订货合同类型为钢卷时,没有装入宽").toString();
		}
		if (CodeJian.T.key.equals(jian)
				&& (zrkn == null || zrkn.doubleValue() == 0)) {
			return new Result(-47, "装入宽 输入有误,当剪边为" + CodeJian.T.key
					+ "时,装入宽必填项").toString();
		}
		// 验证硬度上下限、附着量正面上下限、附着量反面上下限
		ZzsyVO zzsyVO = getDefaultZzsy(entity.getGgno(), entity.getFudw(), entity.getFuzm(), entity.getFufm(), entity.getPzno());
		// 校验硬度上下限
		String ymin = entity.getYmin();
		if (ymin == null || ymin.isEmpty()) {
			return new Result(-26, "硬度下限为必填项").toString();
		}
		String ymax = entity.getYmax();
		if (ymax == null || ymax.isEmpty()) {
			return new Result(-27, "硬度上限为必填项").toString();
		}
		if (ymax.compareTo(ymin) < 0) {
			return new Result(-27, "硬度上限值输入有误,硬度上限值不能小于硬度下限值").toString();
		}
		// 硬度上限
		if (ymax.compareTo(zzsyVO.getYmin()) < 0
				|| ymax.compareTo(zzsyVO.getYmax()) > 0) {
			return new Result(-27, "硬度上限值输入有误,硬度上限不值在范围" + zzsyVO.getYmin()
					+ "-" + zzsyVO.getYmax() + "内").toString();
		}
		// 硬度下限
		if (ymin.compareTo(zzsyVO.getYmin()) < 0
				|| ymin.compareTo(zzsyVO.getYmax()) > 0) {
			return new Result(-26, "硬度下限值输入有误,硬度下限不值在范围" + zzsyVO.getYmin()
					+ "-" + zzsyVO.getYmax() + "内").toString();
		}
		// 验证附着量正面上下限
		Double fuzs = entity.getFuzs();
		if (fuzs == null || fuzs <= 0) {
			return new Result(-31, "附着量.正面上限为必填项,并且大于0").toString();
		}
		Double fuzx = entity.getFuzx();
		if (fuzs == null || fuzs <= 0) {
			return new Result(-30, "附着量.正面上限为必填项,并且大于0").toString();
		}
		// 附着量正面上限
		if (fuzs > zzsyVO.getFuzs() || fuzs < zzsyVO.getFuzx()) {
			return new Result(-31, "附着量.正面上限输入有误,附着量.正面上限值不在范围"
					+ zzsyVO.getFuzx() + "-" + zzsyVO.getFuzs() + " 内").toString();
		}
		// 附着量正面下限
		if (fuzx > zzsyVO.getFuzs() || fuzx < zzsyVO.getFuzx()) {
			return new Result(-30, "附着量.正面下限输入有误,附着量.正面下限值不在范围"
					+ zzsyVO.getFuzx() + "-" + zzsyVO.getFuzs() + " 内").toString();
		}
		// 验证附着量反面上下限
		Double fufs = entity.getFufs();
		if (fufs == null || fufs <= 0) {
			return new Result(-35, "附着量.反面上限为必填项,并且大于0").toString();
		}
		Double fufx = entity.getFufx();
		if (fufx == null || fufx <= 0) {
			return new Result(-34, "附着量.反面下限为必填项,并且大于0").toString();
		}
		// 附着量反面上限
		if (fufs > zzsyVO.getFufs() || fufs < zzsyVO.getFufx()) {
			return new Result(-35, "附着量.反面上限输入有误,附着量.反面上限值不在范围"
					+ zzsyVO.getFufx() + "-" + zzsyVO.getFufs() + " 内").toString();
		}
		// 附着量反面下限
		if (fufx > zzsyVO.getFufs() || fufx < zzsyVO.getFufx()) {
			return new Result(-34, "附着量.反面下限输入有误,附着量.反面下限值不在范围"
					+ zzsyVO.getFufx() + "-" + zzsyVO.getFufs() + " 内").toString();
		}
		// 公差长下限
		Double cxzi = entity.getCxzi();
		if (isCoil && (cxzi != null && cxzi > 0)) {
			return new Result(-44, "当订货合同类型为钢卷时,没有公差长下限").toString();
		}
		// 公差长上限
		Double cszi = entity.getCszi();
		if (isCoil && (cszi != null && cszi > 0)) {
			return new Result(-45, "当订货合同类型为钢卷时,没有公差长上限").toString();
		}
		// 附页kpn1Flag 标示位
		String kpn1Flag = entity.getKpn1Flag();
		String kpn1 = entity.getKpn1();
		if ((kpn1Flag == null || kpn1Flag.isEmpty()) && kpn1 != null
				&& !kpn1.isEmpty()) {
			return new Result(-50, "当别纸KpNo的标示位为空时,别纸KpNo必须为空").toString();
		}
		if (kpn1Flag != null && !kpn1Flag.isEmpty()
				&& (kpn1 == null || kpn1.isEmpty())) {
			return new Result(-50, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项").toString();
		}
		if (kpn1Flag != null && !kpn1Flag.isEmpty()
				&& ProductType.get(kpn1Flag) == null) {
			return new Result(-50, "别纸KpNo标示位,只能是1、2或3").toString();
		}
		if (kpn1 != null && !kpn1.isEmpty() && !informBO.isExisted(kpn1)) {
			return new Result(-50, "别纸KpNo不存在").toString();
		}
		// 附页kpn2Flag 标示位
		String kpn2Flag = entity.getKpn2Flag();
		String kpn2 = entity.getKpn2();
		if ((kpn2Flag == null || kpn2Flag.isEmpty()) && kpn2 != null
				&& !kpn2.isEmpty()) {
			return new Result(-52, "当别纸KpNo的标示位为空时,别纸KpNo必须为空").toString();
		}
		if (kpn2Flag != null && !kpn2Flag.isEmpty()
				&& (kpn2 == null || kpn2.isEmpty())) {
			return new Result(-52, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项").toString();
		}
		if (kpn2Flag != null && !kpn2Flag.isEmpty()
				&& ProductType.get(kpn2Flag) == null) {
			return new Result(-52, "别纸KpNo标示位,只能是1、2或3").toString();
		}
		if (kpn2 != null && !kpn2.isEmpty() && !informBO.isExisted(kpn2)) {
			return new Result(-52, "别纸KpNo不存在").toString();
		}
		// 附页kpn3Flag 标示位
		String kpn3Flag = entity.getKpn3Flag();
		String kpn3 = entity.getKpn3();
		if ((kpn3Flag == null || kpn3Flag.isEmpty()) && kpn3 != null
				&& !kpn3.isEmpty()) {
			return new Result(-54, "当别纸KpNo的标示位为空时,别纸KpNo必须为空").toString();
		}
		if (kpn3Flag != null && !kpn3Flag.isEmpty()
				&& (kpn3 == null || kpn3.isEmpty())) {
			return new Result(-54, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项").toString();
		}
		if (kpn3Flag != null && !kpn3Flag.isEmpty()
				&& ProductType.get(kpn3Flag) == null) {
			return new Result(-54, "别纸KpNo标示位,只能是1、2或3").toString();
		}
		if (kpn3 != null && !kpn3.isEmpty() && !informBO.isExisted(kpn3)) {
			return new Result(-54, "别纸KpNo不存在").toString();
		}
		// 附页kpn4Flag 标示位
		String kpn4Flag = entity.getKpn4Flag();
		String kpn4 = entity.getKpn4();
		if ((kpn4Flag == null || kpn4Flag.isEmpty()) && kpn4 != null
				&& !kpn4.isEmpty()) {
			return new Result(-56, "当别纸KpNo的标示位为空时,别纸KpNo必须为空").toString();
		}
		if (kpn4Flag != null && !kpn4Flag.isEmpty()
				&& (kpn4 == null || kpn4.isEmpty())) {
			return new Result(-56, "当别纸KpNo的标示位不为空时,别纸KpNo为必填项").toString();
		}
		if (kpn4Flag != null && !kpn4Flag.isEmpty()
				&& ProductType.get(kpn4Flag) == null) {
			return new Result(-56, "别纸KpNo标示位,只能是1、2或3").toString();
		}
		if (kpn4 != null && !kpn4.isEmpty() && !informBO.isExisted(kpn4)) {
			return new Result(-56, "别纸KpNo不存在").toString();
		}
		// 木工所NO
		String mgsn = entity.getMgsn();
		if (mgsn != null && !mgsn.isEmpty()) {
			String[] msgsns = mgsn.split(",");
			for (String s : msgsns) {
				if (informBO.isExisted(s)) continue;
				return new Result(-58, "木工所NO " + s + "不存在").toString();
			}
		}

		return new Result(1, "OK").toString();
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public void query(QEntity<ZzsyMp> qentity) {
		dao.query(qentity);
	}

	/**
	 * @return the dao
	 */
	public IZzsyDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(IZzsyDAO dao) {
		this.dao = dao;
	}

	@Override
	public ZzsyMp get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public String getForJs(Serializable id) {
		ZzsyMp entity = this.get(id);
		if (entity == null) {
			return new Result(-1, "无法获取该文件信息").toString();
		}
		return new Result(entity).toJsObject();
	}

	@Override
	public ZzsyMp getByKey(String ggno, String fudw, String fuzm, String fufm,
			String pzno, String chdx, String nwai, Double hmax, Double hmin,
			Double kmax, Double kmin, Double cmax, Double cmin, Double houu,
			Double kuan, Double cang, String cond, String user) {
		if (ggno == null || fudw == null || fuzm == null || fufm == null
				|| pzno == null || nwai == null) {
			return null;
		}
		StringBuilder ql = new StringBuilder(
				"from ZzsyMp where ggno=? and fudw=? and fuzm=? and fufm=? and pzno=? and nwai=?");
		if (chdx != null) {
			ql.append(" and (chdx is null or chdx='' or chdx='" + chdx + "')");
		}
		else {
			ql.append(" and (chdx is null or chdx='')");
		}

		if (hmax == null || hmax.doubleValue() == 0) {
			ql.append(" and (hmax is null or hmax=0)");
		}
		else {
			ql.append(" and hmax=" + hmax);
		}

		if (hmin == null || hmin.doubleValue() == 0) {
			ql.append(" and (hmin is null or hmin=0)");
		}
		else {
			ql.append(" and hmin=" + hmin);
		}

		if (kmax == null || kmax.doubleValue() == 0) {
			ql.append(" and (kmax is null or kmax=0)");
		}
		else {
			ql.append(" and kmax=" + kmax);
		}

		if (kmin == null || kmin.doubleValue() == 0) {
			ql.append(" and (kmin is null or kmin=0)");
		}
		else {
			ql.append(" and kmin=" + kmin);
		}

		if (cmax == null || cmax.doubleValue() == 0) {
			ql.append(" and (cmax is null or cmax=0)");
		}
		else {
			ql.append(" and cmax=" + cmax);
		}

		if (cmin == null || cmin.doubleValue() == 0) {
			ql.append(" and (cmin is null or cmin=0)");
		}
		else {
			ql.append(" and cmin=" + cmin);
		}

		if (houu == null || houu.doubleValue() == 0) {
			ql.append(" and (houu is null or houu=0)");
		}
		else {
			ql.append(" and houu=" + houu);
		}
		if (kuan == null || kuan.doubleValue() == 0) {
			ql.append(" and (kuan is null or kuan=0)");
		}
		else {
			ql.append(" and kuan=" + kuan);
		}
		if (cang == null || cang.doubleValue() == 0) {
			ql.append(" and (cang is null or cang=0)");
		}
		else {
			ql.append(" and cang=" + cang);
		}
		if (cond != null && !cond.isEmpty()) {
			// ql.append(" and (cond is null or cond='' or cond='" + cond +
			// "')");
			ql.append(" and cond='" + cond + "'");
		}
		else {
			ql.append(" and (cond is null or cond='')");
		}
		if (user != null && !user.isEmpty()) {
			// ql.append(" and (user is null or user='' or user='" + user +
			// "')");
			ql.append(" and user='" + user + "'");
		}
		else {
			ql.append(" and (user is null or user='')");
		}
		ql.append(" order by syno desc");
		return dao.getByKey(ql.toString(), ggno, fudw, fuzm, fufm, pzno, nwai);
	}

	@Override
	public ZzsyMp getByKey(String ggno, String fudw, String fuzm, String fufm,
			String pzno, String chdx, String nwai, Double houu, Double kuan,
			Double cang, String cond, String user) {
		if (ggno == null || fudw == null || fuzm == null || fufm == null
				|| pzno == null || houu == null || kuan == null || nwai == null) {
			return null;
		}
		StringBuilder ql = new StringBuilder(
				"from ZzsyMp where ggno=? and fudw=? and fuzm=? and fufm=? and pzno=? and nwai=? ");
		if (chdx != null) {
			ql.append(" and (chdx is null or chdx='' or chdx='" + chdx + "')");
		}
		else {
			ql.append(" and (chdx is null or chdx='')");
		}
		if (houu != null && houu > 0) {
			ql.append(" and (houu=" + houu + " or ((hmax is null or hmax>="
					+ houu + ") and (hmin<=" + houu + " or hmin is null)))");
		}
		if (kuan != null && kuan > 0) {
			ql.append(" and (kuan=" + kuan + " or ((kmax is null or kmax>="
					+ kuan + ") and (kmin<=" + kuan + " or kmin is null)))");
		}
		if (cang != null && cang > 0) {
			ql.append(" and (cang=" + cang + " or ((cmax is null or cmax>="
					+ cang + ") and (cmin<=" + cang + " or cmin is null)))");
		}
		if (cond != null && !cond.isEmpty()) {
			ql.append(" and (cond='" + cond + "' or cond is null or cond='')");
		}
		else {
			ql.append(" and (cond is null or cond='')");
		}
		if (user != null && !user.isEmpty()) {
			ql.append(" and (user='" + user + "' or user is null or user='')");
		}
		else {
			ql.append(" and (user is null or user='')");
		}
		ql.append(" order by user desc, syno desc");
		return dao.getByKey(ql.toString(), ggno, fudw, fuzm, fufm, pzno, nwai);
	}

	@Override
	public String getNewNo() {
		String sMaxNo = dao.getMaxNo();
		int maxNo = -1;
		if (sMaxNo != null) {
			try {
				maxNo = Integer.parseInt(sMaxNo);
			}
			catch (NumberFormatException e) {
			}
		}
		if (maxNo < 0) {
			maxNo = 1;
		}
		else {
			maxNo += 1;
		}
		return String.format("%06d", maxNo);
	}

	@Override
	public ZzsyVO getDefaultZzsy(String ggno, String fudw, String fuzm,
			String fufm, String pzno) {
		ZzsyVO zzsyVO = new ZzsyVO();
		CodeItem codeItem = null;
		String v = null;
		String ymax = "";
		String ymin = "";
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
		String fuzs = "0";
		String fuzx = "0";
		// 根据附着量.单位及正面024获取附着量正面上/下限
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_024, (fudw + fuzm));
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
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_024, (fudw + fufm));
		if (codeItem != null && (v = codeItem.getValue()) != null
				&& !v.isEmpty()) {
			if (v.length() == 8) {
				// 附着量代码值的前四位（中间加小数点）为下限
				fufx = v.substring(0, 2) + "." + v.substring(2, 4);
				// 附着量代码值的后四位（中间加小数点）为上限
				fufs = v.substring(4, 6) + "." + v.substring(6, 8);
			}
		}
		// 根据规格代码003获取运用规格
		codeItem = codeBo.getCodeItem(CmnConstants.CODE_003, ggno);
		String yuny = null;
		if (codeItem != null && (v = codeItem.getRemark()) != null
				&& !v.isEmpty()) {
			yuny = v;
			if (v.length() > 6) {
				yuny = v.substring(0, 6);
			}
		}
		zzsyVO.setYmax(ymax);
		zzsyVO.setYmin(ymin);
		zzsyVO.setFuzs(Double.valueOf(fuzs));
		zzsyVO.setFuzx(Double.valueOf(fuzx));
		zzsyVO.setFufs(Double.valueOf(fufs));
		zzsyVO.setFufx(Double.valueOf(fufx));
		zzsyVO.setYuny(yuny);
		zzsyVO.setYtyp(CodeTyzl.DOS.key);
		zzsyVO.setHxzi(ZzsyConstants.HXZI);
		zzsyVO.setHszi(ZzsyConstants.HSZI);
		zzsyVO.setKxzi(ZzsyConstants.KXZI);
		zzsyVO.setKszi(ZzsyConstants.KSZI);
		zzsyVO.setCxzi(ZzsyConstants.CXZI);
		if (Code118.coil.key.equals(pzno.substring(0, 1))) {
			zzsyVO.setCszi(0.00);
		}
		else {
			zzsyVO.setCszi(ZzsyConstants.CSZI);
		}
		return zzsyVO;
	}

	@Override
	public ZzsyMp getByKey(String ql) {
		return dao.getByKey(ql);
	}

	@Override
	public void doRefresh() {
		String v, ymax = null, ymin = null, key;
		// 根据规格后两位111获取硬度上下限
		List<CodeItem> items = codeBo.findValidItems(CmnConstants.CODE_111);
		if (items.size() > 0) {
			for (CodeItem item : items) {
				if (item == null) continue;
				v = item.getValue();
				if (v == null || v.isEmpty()) continue;
				key = item.getKey();
				if (v.length() == 2) {
					ymin = v;
				}
				else if (v.length() == 4) {
					// 规格代码值的前两位为下限
					ymin = v.substring(0, 2);
					// 规格代码值的后两位为上限
					ymax = v.substring(2, 4);
				}
				dao.executeUpdate("update ZzsyMp set ymin=?, ymax=? where ggno like ? ", ymin, ymax, "%"
						+ key);
			}
		}
		double s = 0, x = 0;
		String dw, fu;
		items = codeBo.findValidItems(CmnConstants.CODE_024);
		if (items.size() > 0) {
			for (CodeItem item : items) {
				if (item == null) continue;
				v = item.getValue();
				if (v == null || v.isEmpty() || v.length() != 8) continue;
				key = item.getKey();
				if (key == null || key.isEmpty()) continue;
				// 附着量代码值的前四位（中间加小数点）为下限
				x = Double.parseDouble(v.substring(0, 2) + "."
						+ v.substring(2, 4));
				// 附着量代码值的后四位（中间加小数点）为上限
				s = Double.parseDouble(v.substring(4, 6) + "."
						+ v.substring(6, 8));
				dw = key.substring(0, 2);
				fu = key.substring(2);
				dao.executeUpdate("update ZzsyMp set fuzx=?, fuzs=? where fudw=? and fuzm=? ", x, s, dw, fu);
				dao.executeUpdate("update ZzsyMp set fufx=?, fufs=? where fudw=? and fufm=? ", x, s, dw, fu);
			}
		}

	}

	public ICodeBO getCodeBo() {
		return codeBo;
	}

	public void setCodeBo(ICodeBO codeBo) {
		this.codeBo = codeBo;
	}

	public IYongBO getYongBo() {
		return yongBo;
	}

	public void setYongBo(IYongBO yongBo) {
		this.yongBo = yongBo;
	}

	public IInformBO getInformBO() {
		return informBO;
	}

	public void setInformBO(IInformBO informBO) {
		this.informBO = informBO;
	}

}
