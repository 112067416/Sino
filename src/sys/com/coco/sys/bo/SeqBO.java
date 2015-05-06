package com.coco.sys.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coco.core.bean.Result;
import com.coco.core.persistence.query.QEntity;
import com.coco.core.util.StringUtils;
import com.coco.sys.bo.api.ISeqBO;
import com.coco.sys.constants.SeqRuleType;
import com.coco.sys.dao.api.ISeqDAO;
import com.coco.sys.orm.Seq;
import com.coco.sys.orm.SeqRule;

public class SeqBO implements ISeqBO {

	private ISeqDAO dao;

	@Override
	public void save(Seq entity) {
		dao.save(entity);
	}

	@Override
	public void update(Seq entity) {
		dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}

	@Override
	public Seq get(Serializable id) {
		return dao.get(id);
	}

	@Override
	public void query(QEntity<Seq> qentity) {
		dao.query(qentity);
	}

	@Override
	public void saveRules(String id, List<SeqRule> rules) {
		Seq seq = new Seq();
		seq.setId(id);
		dao.saveRules(seq, rules);
	}

	@Override
	public String getForJs(Serializable id) {
		Set<String> ignoreNames = new HashSet<String>();
		ignoreNames.add("rules");
		return new Result(dao.get(id)).toJsObject(null, ignoreNames);
	}

	@Override
	public List<SeqRule> findRules(String id) {
		return dao.findRules(id);
	}

	public ISeqDAO getDao() {
		return dao;
	}

	public void setDao(ISeqDAO dao) {
		this.dao = dao;
	}

	@Override
	public String sequence(String id) {
		// 获取流水编码对象
		Seq seq = dao.get(id);
		if (seq == null || !seq.isValid()) {
			return null;
		}
		// 流水值
		String value = null;
		// 当前流水号
		long number = seq.getNumber() != null ? seq.getNumber() : 0l;
		// 下个流水号
		long nextNumber = number + (seq.getStep() != null ? seq.getStep() : 1);
		String nextNumberStr = String.valueOf(nextNumber);
		// 当指定流水值宽度时，超过宽带自动复位
		if (seq.getScale() != null && seq.getScale() > 0) {
			if (nextNumberStr.length() > seq.getScale()) {
				nextNumber = 1;
				nextNumberStr = "1";
			}
		}

		List<SeqRule> rules = seq.getRules();
		// 若没有任何规则，则以普通流水方式显示
		if (rules == null || rules.size() == 0) {
			if (seq.getScale() != null && seq.getScale() > 0) {
				value = StringUtils.fillChar(nextNumberStr, seq.getScale(), '0');
			}
			else {
				value = nextNumberStr;
			}
		}
		else {
			// 按规则显示流水值
			List<String> values = new ArrayList<String>();
			int indexSeq = -1;
			boolean reset = false;
			Date date = new Date();
			for (SeqRule rule : rules) {
				// 流水规则
				if (SeqRuleType.sequence.value.equals(rule.getType())) {
					indexSeq = values.size();
					values.add("");
				}
				// 固定串规则
				else if (SeqRuleType.consts.value.equals(rule.getType())) {
					if (rule.isResetable() && rule.getValue() != null
							&& rule.getValue().length() > 0
							&& !rule.getExpr().equals(rule.getValue())) {
						reset = true;
					}
					values.add(rule.getExpr());
					rule.setValue(rule.getExpr());
				}
				// 时间规则
				else if (SeqRuleType.date.value.equals(rule.getType())) {
					SimpleDateFormat sdf = new SimpleDateFormat(rule.getExpr());
					String dateStr = sdf.format(date);
					if (rule.isResetable() && rule.getValue() != null
							&& rule.getValue().length() > 0
							&& !dateStr.equals(rule.getValue())) {
						reset = true;
					}
					values.add(dateStr);
					rule.setValue(dateStr);
				}
			}
			if (indexSeq == -1) {
				indexSeq = values.size();
				values.add("");
			}
			// 需要复位
			if (reset) {
				nextNumber = 1;
				nextNumberStr = "1";
			}
			StringBuffer valueBuf = new StringBuffer();
			for (int i = 0; i < values.size(); i++) {
				if (indexSeq == i) {
					if (seq.getScale() != null && seq.getScale() > 0) {
						valueBuf.append(StringUtils.fillChar(nextNumberStr, seq.getScale(), '0'));
					}
					else {
						valueBuf.append(nextNumberStr);
					}
				}
				else {
					valueBuf.append(values.get(i));
				}
			}
			value = valueBuf.toString();
		}
		seq.setNumber(nextNumber);
		seq.setValue(value);
		dao.update(seq);
		return value;
	}

	@Override
	public String sequenceNoUpdate(String id) {
		// 获取流水编码对象
		Seq seq = dao.get(id);
		if (seq == null || !seq.isValid()) {
			return null;
		}
		// 流水值
		String value = null;
		// 当前流水号
		long number = seq.getNumber() != null ? seq.getNumber() : 0l;
		// 下个流水号
		long nextNumber = number + (seq.getStep() != null ? seq.getStep() : 1);
		String nextNumberStr = String.valueOf(nextNumber);
		// 当指定流水值宽度时，超过宽带自动复位
		if (seq.getScale() != null && seq.getScale() > 0) {
			if (nextNumberStr.length() > seq.getScale()) {
				nextNumber = 1;
				nextNumberStr = "1";
			}
		}

		List<SeqRule> rules = seq.getRules();
		// 若没有任何规则，则以普通流水方式显示
		if (rules == null || rules.size() == 0) {
			if (seq.getScale() != null && seq.getScale() > 0) {
				value = StringUtils.fillChar(nextNumberStr, seq.getScale(), '0');
			}
			else {
				value = nextNumberStr;
			}
		}
		else {
			// 按规则显示流水值
			List<String> values = new ArrayList<String>();
			int indexSeq = -1;
			boolean reset = false;
			Date date = new Date();
			for (SeqRule rule : rules) {
				// 流水规则
				if (SeqRuleType.sequence.value.equals(rule.getType())) {
					indexSeq = values.size();
					values.add("");
				}
				// 固定串规则
				else if (SeqRuleType.consts.value.equals(rule.getType())) {
					if (rule.isResetable() && rule.getValue() != null
							&& rule.getValue().length() > 0
							&& !rule.getExpr().equals(rule.getValue())) {
						reset = true;
					}
					values.add(rule.getExpr());
					rule.setValue(rule.getExpr());
				}
				// 时间规则
				else if (SeqRuleType.date.value.equals(rule.getType())) {
					SimpleDateFormat sdf = new SimpleDateFormat(rule.getExpr());
					String dateStr = sdf.format(date);
					if (rule.isResetable() && rule.getValue() != null
							&& rule.getValue().length() > 0
							&& !dateStr.equals(rule.getValue())) {
						reset = true;
					}
					values.add(dateStr);
					rule.setValue(dateStr);
				}
			}
			if (indexSeq == -1) {
				indexSeq = values.size();
				values.add("");
			}
			// 需要复位
			if (reset) {
				nextNumber = 1;
				nextNumberStr = "1";
			}
			StringBuffer valueBuf = new StringBuffer();
			for (int i = 0; i < values.size(); i++) {
				if (indexSeq == i) {
					if (seq.getScale() != null && seq.getScale() > 0) {
						valueBuf.append(StringUtils.fillChar(nextNumberStr, seq.getScale(), '0'));
					}
					else {
						valueBuf.append(nextNumberStr);
					}
				}
				else {
					valueBuf.append(values.get(i));
				}
			}
			value = valueBuf.toString();
		}
		// seq.setNumber(nextNumber);
		// seq.setValue(value);
		// dao.update(seq);
		return value;
	}

}
