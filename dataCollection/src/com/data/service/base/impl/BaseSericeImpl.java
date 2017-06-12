package com.data.service.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.data.dao.base.IBaseDao;
import com.data.service.base.IBaseService;
import com.data.tools.Page;

/**
 * @ClassName: BaseSericeImpl
 * @Description: TODO(公共Service实现)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午3:34:09
 * @param <T>
 * @param <PK>
 */
public abstract class BaseSericeImpl<T, PK extends Serializable> implements
		IBaseService<T, PK> {
	/**
	 * @Fields result : TODO(标识位)
	 */
	public int result = 0;
	/**
	 * @Fields flag : TODO(标识)
	 */
	public boolean flag = false;
	/**
	 * @Fields propertyList : TODO(属性字段集合)
	 */
	public List<String> propertyList = null;

	/**
	 * @Fields valuesList : TODO(值字段集合)
	 */
	public List<Object> valuesList = null;
	/**
	 * @Fields values : TODO(属性值)
	 */
	public String[] propertys = {};

	/**
	 * @Fields sum : TODO(总页数)
	 */
	public int sum = 0;
	/**
	 * @Fields pager : TODO(page)
	 */
	public Page pager = null;

	public IBaseDao<T, PK> iBaseDao;

	// 注入dao
	@Resource
	public void setiBaseDao(IBaseDao<T, PK> iBaseDao) {
		this.iBaseDao = iBaseDao;
	}

	@Override
	public void save(T entity) {
		iBaseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		iBaseDao.update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		iBaseDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(PK id) {
		iBaseDao.delete(id);
	}

	@Override
	public void delete(T entity) {
		iBaseDao.delete(entity);
	}

	@Override
	public void deleteByProperty(String propertyName, Object value) {
		iBaseDao.deleteByProperty(propertyName, value);
	}

	@Override
	public T get(String hql) {
		return iBaseDao.get(hql);
	}

	@Override
	public T getById(PK id) {
		return iBaseDao.getById(id);
	}

	@Override
	public T get(String hql, Object[] params) {
		return iBaseDao.get(hql, params);
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		return iBaseDao.get(hql, params);
	}

	@Override
	public List<T> getAll() {
		return iBaseDao.getAll();
	}

	@Override
	public List<T> getAll(int page, int pageSize) {
		return iBaseDao.getAll(page, pageSize);
	}

	@Override
	public T getByProperty(String propertyName, Object value) {
		return iBaseDao.getByProperty(propertyName, value);
	}

	@Override
	public List<T> getByProperty(String propertyName, Object value, int page,
			int pageSize) {
		return iBaseDao.getByProperty(propertyName, value, page, pageSize);
	}

	@Override
	public List<T> getByPropertyOrder(String propertyName, Object value,
			int page, int pageSize, String propertyOrder, boolean isSequence) {
		return iBaseDao.getByPropertyOrder(propertyName, value, page, pageSize,
				propertyOrder, isSequence);
	}

	@Override
	public List<T> getByProperties(String[] propertyNames, Object[] values) {
		// TODO Auto-generated method stub
		return iBaseDao.getByProperties(propertyNames, values);
	}

	@Override
	public List<T> getByProperties(String[] propertyNames, Object[] values,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		return iBaseDao.getByProperties(propertyNames, values, page, pageSize);
	}

	@Override
	public List<T> getAllAndOrderByProperty(String propertyName,
			boolean isSequence) {
		// TODO Auto-generated method stub
		return iBaseDao.getAllAndOrderByProperty(propertyName, isSequence);
	}

	@Override
	public List<T> getAndOrderByProperty(int firstResult, int fetchSize,
			String propertyName, boolean isSequence) {
		// TODO Auto-generated method stub
		return iBaseDao.getAndOrderByProperty(firstResult, fetchSize,
				propertyName, isSequence);
	}

	@Override
	public long count(String hql) {
		// TODO Auto-generated method stub
		return iBaseDao.count(hql);
	}

	@Override
	public long countAll() {
		// TODO Auto-generated method stub
		return iBaseDao.countAll();
	}

	@Override
	public long countByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return iBaseDao.countAll();
	}

	@Override
	public long countByProperties(String[] propertyNames, Object[] values) {
		// TODO Auto-generated method stub
		return iBaseDao.countByProperties(propertyNames, values);
	}

	@Override
	public long count(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return iBaseDao.count(hql, params);
	}

	@Override
	public int countModelByProperties(String[] propertyNames, Object[] values,
			List<Integer> departmentList) {
		return iBaseDao.countModelByProperties(propertyNames, values,
				departmentList);
	}

	@Override
	public List<T> getModelByProperties(String[] propertyNames,
			Object[] values, List<Integer> departmentList, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return iBaseDao.getModelByProperties(propertyNames, values,
				departmentList, page, pageSize);
	}

}
