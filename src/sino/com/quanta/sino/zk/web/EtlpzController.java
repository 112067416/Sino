package com.quanta.sino.zk.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coco.core.bean.Result;
import com.coco.core.bean.User;
import com.coco.core.env.Context;
import com.quanta.sino.cmn.constants.ECyfxxm;
import com.quanta.sino.cmn.constants.EEtlpz;
import com.quanta.sino.cmn.constants.ESczt;
import com.quanta.sino.etl.bo.api.IRcBO;
import com.quanta.sino.fxs.vo.YyfxVO;
import com.quanta.sino.orm.EtlpzGl;
import com.quanta.sino.orm.Etlyygljl;
import com.quanta.sino.orm.EtlyygljlItem;
import com.quanta.sino.ycai.bo.api.IYcaitpBO;
import com.quanta.sino.zk.bo.api.IEtlpzglBO;
import com.quanta.sino.zk.bo.api.IEtlyyglBO;
import com.quanta.sino.zk.vo.DqEtlpzglQE;
import com.quanta.sino.zk.vo.EtlyyglQE;

/**
 * <p>
 * 中控管理控制器
 * </p>
 * <p>
 * create: 2011-4-18 下午01:06:46
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Controller
@RequestMapping("/sino/zk")
public class EtlpzController {

	/**
	 * 品质管理业务接口
	 */
	@Autowired
	private IEtlpzglBO bo;

	/**
	 * 入侧业务接口
	 */
	@Autowired
	private IRcBO rcBO;

	/**
	 * 药液管理业务接口
	 */
	@Autowired
	private IEtlyyglBO yyglbo;

	/**
	 * 原板业务接口
	 */
	@Autowired
	private IYcaitpBO ycaitpBO;

	/**
	 * <p>
	 * ETL品质管理页面刷新操作
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/doRefreshEtlpz", method = RequestMethod.POST)
	public @ResponseBody
	String doRefreshEtlpz(String[] jbnos, String[] sczts) {
		String jbno = null, sczt = null, dqSczt = null, bySczt = null;
		if (jbnos != null && jbnos.length > 0) {
			for (int i = 0; i < jbnos.length; i++) {
				jbno = jbnos[i];
				if (jbno == null || jbno.isEmpty()) continue;
				if (sczts.length < i) break;
				sczt = sczts[i];
				if (sczt == null || sczt.isEmpty()) continue;
				if (ESczt.DQJ.key.equals(sczt)) dqSczt = ESczt.DQJ.key;
				if (ESczt.BYJ.key.equals(sczt)) bySczt = ESczt.BYJ.key;
				if (!ycaitpBO.isExistedByJbnoAndSczt(jbno, sczt)) {
					return Result.ERROR;
				}
			}
		}
		if ((dqSczt == null || dqSczt.isEmpty())
				&& ycaitpBO.isExistedBySczt(ESczt.DQJ.key)) {
			return Result.ERROR;
		}
		if ((bySczt == null || bySczt.isEmpty())
				&& ycaitpBO.isExistedBySczt(ESczt.BYJ.key)) {
			return Result.ERROR;
		}
		// Calendar calendar = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date scsjBegin = null, scsjEnd = null;
		// try {
		// scsjBegin = sdf.parse(sdf.format(calendar.getTime()));
		// calendar.add(Calendar.DAY_OF_MONTH, 1);
		// scsjEnd = sdf.parse(sdf.format(calendar.getTime()));
		// }
		// catch (ParseException e) {
		// }
		if (bo.isExistedNew()) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 刷新药液分析页面
	 * </p>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/doRefreshYygl", method = RequestMethod.POST)
	public @ResponseBody
	String doRefreshYygl() {
		Date rqBegin = null;
		Date rqEnd = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date date = c.getTime();
			Date rq = sdf.parse(sdf.format(date));
			c.setTime(rq);
			c.add(Calendar.HOUR_OF_DAY, 7);
			c.add(Calendar.MINUTE, 30);
			sdf.applyPattern("yyyy-MM-dd HH:mm");
			rqBegin = sdf.parse(sdf.format(c.getTime()));
			if (rqBegin.after(date)) {
				c.add(Calendar.DAY_OF_MONTH, -1);
				rqBegin = sdf.parse(sdf.format(c.getTime()));
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
			rqEnd = sdf.parse(sdf.format(c.getTime()));
		}
		catch (ParseException e) {
		}
		if (yyglbo.isExistedNew(rqBegin, rqEnd)) {
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 获得ETL实时品质管理记录特记
	 * </p>
	 * 
	 * @param jbno
	 * @return String
	 */
	@RequestMapping(value = "/getTj", method = RequestMethod.POST)
	public @ResponseBody
	String getTj(String jbno) {
		EtlpzGl etlpzGl = bo.getByJbno(jbno);
		if (etlpzGl == null) {
			return new Result(-1, "Coil No." + jbno + "还未生产").toString();
		}
		String tj = etlpzGl.getTj();
		return "{jbno:\"" + jbno + "\",tj:\""
				+ (tj != null && !tj.isEmpty() ? tj : "") + "\"}";
	}

	/**
	 * <p>
	 * ETL实时品质管理
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexPzgl")
	public String indexPzgl(DqEtlpzglQE page, Model model) {
		User user = Context.currentUser();
		if (user == null) {
			return Result.URL_ERROR;
		}
		if (page.getSize() <= 0) {
			page.setSize(15);
		}
		if (!page.isQuery()) {
			model.addAttribute("ban", user.getShift());
			model.addAttribute("zu", user.getGroup());
			List<String> xms = ECyfxxm.xms();
			model.addAttribute("xms", xms);
			int size = xms.size();
			int line = (size % 4 == 0 ? size / 4 : size / 4 + 1);
			model.addAttribute("line", line);
			model.addAttribute("bz", "ETL镀锡线发送");
			model.addAttribute("scsj", new Date());
		}
		page.setStat(EEtlpz.ywc.key);
		bo.queryDq(page);
		EtlpzGl etlpzGl = bo.getByStat(EEtlpz.dqj.key);
		if (etlpzGl != null) {
			model.addAttribute("etlpzGl", etlpzGl);
		}
		model.addAttribute("page", page);
		return page.isQuery() ? "/sino/zk/listPzgl" : "/sino/zk/indexPzgl";
	}

	/**
	 * <p>
	 * 查询ETL实时生产记录
	 * </p>
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping("/querySsjl")
	public String querySsjl(Model model) {
		model.addAttribute("rcmxs", rcBO.findByStates());
		return "/sino/zk/listSsjl";
	}

	/**
	 * <p>
	 * ETL品质管理查询主界面
	 * </p>
	 * 
	 * @param page
	 * @param model
	 * @return String
	 */
	@RequestMapping("/indexYygl")
	public String indexYygl(EtlyyglQE page, Model model) {
		page.setSize(-1);
		page.setOrderBys("rq");
		Etlyygljl entity = null;
		if (!page.isQuery()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				Date date = c.getTime();
				Date rq = sdf.parse(sdf.format(date));
				c.setTime(rq);
				c.add(Calendar.HOUR_OF_DAY, 7);
				c.add(Calendar.MINUTE, 30);
				sdf.applyPattern("yyyy-MM-dd HH:mm");
				Date rqBegin = sdf.parse(sdf.format(c.getTime()));
				if (rqBegin.after(date)) {
					c.add(Calendar.DAY_OF_MONTH, -1);
					rqBegin = sdf.parse(sdf.format(c.getTime()));
					sdf.applyPattern("yyyy-MM-dd");
					rq = sdf.parse(sdf.format(c.getTime()));
				}
				model.addAttribute("rq", rq);
				page.setRqBegin(rqBegin);
				page.setRqEnd(rqBegin);
				yyglbo.query(page);
				entity = yyglbo.getByJlsj(rq);
			}
			catch (ParseException e) {
				model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "获取日期有误"));
				return Result.URL_EXCEPTION;
			}
		}
		if (page.isQuery()) {
			entity = yyglbo.getByJlsj(page.getRqBegin());
			Calendar c = Calendar.getInstance();
			c.setTime(page.getRqBegin());
			c.add(Calendar.HOUR_OF_DAY, 7);
			c.add(Calendar.MINUTE, 30);
			page.setRqBegin(c.getTime());
			page.setRqEnd(c.getTime());
			yyglbo.query(page);
		}
		long pageSize = page.getCount() > 15 ? page.getCount() : 15;
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", page.getDatas().size());
		model.addAttribute("page", page);
		model.addAttribute("entity", entity);
		model.addAttribute("cnsts", new YyfxVO());
		return page.isQuery() ? "/sino/zk/listYygl" : "/sino/zk/indexYygl";
	}

	/**
	 * <p>
	 * 获取ETL药液管理记录详情
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getEtlyygljlItem", method = RequestMethod.POST)
	public String getEtlyygljlItem(String id, Model model) {
		EtlyygljlItem item = null;
		if (id != null && !id.isEmpty()) {
			item = yyglbo.getItem(id);
		}
		else {
			item = new EtlyygljlItem();
			item.setRq(new Date());
		}
		model.addAttribute("item", item);
		return "/sino/zk/detail";
	}

	/**
	 * <p>
	 * 保存ETL药液明细记录
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/updateYyglItem", method = RequestMethod.POST)
	public @ResponseBody
	String updateYyglItem(EtlyygljlItem entity) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			yyglbo.saveItem(entity);
		}
		else {
			yyglbo.updateYyglItem(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 进入更新品质管理记录页面
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/getEtlpzGl", method = RequestMethod.POST)
	public String getEtlpzGl(String id, Model model) {
		User user = Context.currentUser();
		if (user == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "已超时，请重新登陆。"));
			return Result.URL_ERROR;
		}
		EtlpzGl entity = bo.get(id);
		if (entity == null) {
			model.addAttribute(Result.DEFAULT_KEY, new Result(-1, "主键为" + id
					+ "对应品质管理记录不存在"));
			return Result.URL_ERROR;
		}
		if (entity.getBan() == null) {
			entity.setBan(user.getShift());
		}
		if (entity.getZu() == null) {
			entity.setZu(user.getGroup());
		}
		if (entity.getUpda() == null) {
			EtlpzGl $entity = bo.getLatest(entity.getScsj());
			if ($entity != null) {
				entity.setCheDlmdb($entity.getCheDlmdb());
				entity.setCheDlmdt($entity.getCheDlmdt());
				entity.setOilerBl($entity.getOilerBl());
				entity.setPass($entity.getPass());
			}
		}
		model.addAttribute("entity", entity);
		return "/sino/zk/indexEtlpzGl";
	}

	/**
	 * <p>
	 * 保存ETL药液明细记录
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/updateEtlpzGl", method = RequestMethod.POST)
	public @ResponseBody
	String updateEtlpzGl(EtlpzGl entity) {
		return bo.updateEtlpzGl(entity);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param id
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/getYygl", method = RequestMethod.POST)
	public String getYygl(Date jlsj, Model model) {
		Etlyygljl entity = yyglbo.getByJlsj(jlsj);
		model.addAttribute("entity", entity);
		return "/sino/zk/indexEtlyygljl";
	}

	/**
	 * <p>
	 * 保存ETL药液明细记录
	 * </p>
	 * 
	 * @param entity
	 * @return String
	 */
	@RequestMapping(value = "/saveYygl", method = RequestMethod.POST)
	public @ResponseBody
	String saveYygl(Etlyygljl entity) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			yyglbo.save(entity);
		}
		else {
			yyglbo.update(entity);
		}
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 新增ETL实时品质管理记录
	 * </p>
	 * 
	 * @param jbno
	 * @param scsj
	 * @return String
	 */
	@RequestMapping(value = "/addEtlpzGl", method = RequestMethod.POST)
	public @ResponseBody
	String addEtlpzGl(String jbno, Date scsj) {
		return bo.addEtlpzGl(jbno, scsj);
	}

	/**
	 * <p>
	 * 已读品质管理记录
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/readEtlpzGl", method = RequestMethod.POST)
	public @ResponseBody
	String readEtlpzGl(String id) {
		bo.read(id, false);
		return Result.SUCCESS;
	}

	/**
	 * <p>
	 * 已读药液管理记录
	 * </p>
	 * 
	 * @param id
	 * @return String
	 */
	@RequestMapping(value = "/readYygl", method = RequestMethod.POST)
	public @ResponseBody
	String readYygl(String id) {
		yyglbo.read(id, false);
		return Result.SUCCESS;
	}

	public IEtlpzglBO getBo() {
		return bo;
	}

	public void setBo(IEtlpzglBO bo) {
		this.bo = bo;
	}

	public IEtlyyglBO getYyglbo() {
		return yyglbo;
	}

	public void setYyglbo(IEtlyyglBO yyglbo) {
		this.yyglbo = yyglbo;
	}

	public IRcBO getRcBO() {
		return rcBO;
	}

	public void setRcBO(IRcBO rcBO) {
		this.rcBO = rcBO;
	}

	public IYcaitpBO getYcaitpBO() {
		return ycaitpBO;
	}

	public void setYcaitpBO(IYcaitpBO ycaitpBO) {
		this.ycaitpBO = ycaitpBO;
	}
}
