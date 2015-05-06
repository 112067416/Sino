package com.coco.sys.bo;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coco.core.api.IBaseDAO;
import com.coco.core.exception.CocoException;
import com.coco.core.persistence.query.QEntity;
import com.coco.sys.bo.api.IConvertBO;
import com.coco.sys.constants.ConverterType;
import com.coco.sys.converter.api.IConverter;
import com.coco.sys.converter.api.IConverterCallback;
import com.coco.sys.orm.ConvertEntity;
import com.coco.sys.orm.ConvertField;
import com.coco.sys.vo.ConvertVO;

/**
 * <p>
 * 转换业务实现
 * </p>
 * <p>
 * create: 2010-12-31 上午10:00:53
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ConvertBO implements IConvertBO {

	private IBaseDAO dao;

	@Override
	public void save(ConvertEntity entity) {
		if (entity == null) {
			throw new CocoException(-1, "不允许提交空数据");
		}
		dao.save(entity);
		if (entity.getFields() != null) {
			for (ConvertField field : entity.getFields()) {
				if (field.getField() == null || field.getField().isEmpty()) {
					continue;
				}
				field.setId(null);
				field.setEntity(entity);
				dao.save(field);
			}
		}
	}

	@Override
	public void update(ConvertEntity entity) {
		if (entity == null) {
			throw new CocoException(-1, "不允许提交空数据");
		}
		ConvertEntity dbEntity = dao.getRef(ConvertEntity.class, entity.getId());
		if (dbEntity == null) {
			throw new CocoException(-1, "无法获取对应的信息");
		}
		dbEntity.setName(entity.getName());
		dbEntity.setType(entity.getType());
		dbEntity.setTitleRow(entity.getTitleRow());
		dbEntity.setFirst(entity.getFirst());
		dbEntity.setLength(entity.getLength());
		dbEntity.setDefaultClass(entity.getDefaultClass());
		dbEntity.setSplit(entity.getSplit());

		List<String> ids = new ArrayList<String>();
		if (entity.getFields() != null) {
			for (ConvertField field : entity.getFields()) {
				if (field.getField() == null || field.getField().isEmpty()) {
					continue;
				}
				if (field.getId() != null && !field.getId().isEmpty()) {
					field.setEntity(entity);
					dao.update(field);
				}
				else {
					field.setId(null);
					field.setEntity(entity);
					dao.save(field);
				}
				ids.add(field.getId());
			}
		}
		// dao.executeForValues("delete from ConvertField where id not in (?)",
		// ids);
	}

	@Override
	public ConvertEntity get(Serializable id) {
		ConvertEntity entity = dao.get(ConvertEntity.class, id);
		entity.getFields().size();
		return entity;
	}

	@Override
	public void delete(Serializable id) {
		dao.delete(ConvertEntity.class, id);
	}

	@Override
	public void query(QEntity<ConvertEntity> qentity) {
		dao.query(qentity);
	}

	/**
	 * @return the dao
	 */
	public IBaseDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(IBaseDAO dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C> ConvertVO<C> convert(Serializable id, Class<C> clazz,
			InputStream is, IConverterCallback<C> callback) {
		if (id == null) {
			return null;
		}
		ConvertEntity entity = this.get(id);
		if (entity == null) {
			throw new CocoException(-1, "不能获取转换配置");
		}
		IConverter converter = ConverterType.getConverter(entity.getType());
		if (converter == null) {
			throw new CocoException(-1, "不能获取转换器类型");
		}

		if (clazz == null && entity.getDefaultClass() != null) {
			try {
				clazz = (Class<C>) Class.forName(entity.getDefaultClass());
			}
			catch (Exception e) {
				throw new CocoException(-1, "不能获取指定默认的转换类");
			}
		}
		return converter.convert(clazz, entity, is, callback);
	}
}
