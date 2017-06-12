package com.data.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.data.dao.base.IBaseDao;

/**
 * @ClassName: BaseDaoImpl
 * @Description: TODO(用户实现IBaseDao接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午2:27:45
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, PK extends Serializable> implements
		IBaseDao<T, PK> {
	/**
	 * @Fields sql : TODO(SQL语句)
	 */
	protected String sql;

	/**
	 * @Fields hql : TODO(HQL语句)
	 */
	protected String hql;
	@Resource
	private SessionFactory sessionFactory;

	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		this.getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(PK id) {
		this.getSessionFactory().getCurrentSession().delete(this.getById(id));
	}

	@Override
	public void delete(T entity) {
		this.getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void deleteByProperty(String propertyName, Object value) {
		String queryString = "delete from "
				+ this.getPersistentClass().getName()
				+ " as model where model." + propertyName + "=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		query.setParameter(0, value);
		query.executeUpdate();
	}

	@Override
	public void update(T entity) {
		this.getSessionFactory().getCurrentSession().update(entity);
	}

	@Override
	public T getById(PK id) {
		return (T) this.getSessionFactory().getCurrentSession()
				.get(getPersistentClass(), id);
	}

	@Override
	public int countAll() {
		String queryString = "select count(*) from "
				+ this.getPersistentClass().getName();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		List<T> list = query.list();
		Long result = (Long) list.get(0);
		return result.intValue();
	}

	@Override
	public int countByProperty(String propertyName, Object value) {
		String[] propertyNames = new String[] { propertyName };
		Object[] values = new Object[] { value };
		return this.countByProperties(propertyNames, values);
	}

	public T get(String hql) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;

	}

	public T get(String hql, Object[] params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public T get(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		String queryString = "from " + this.getPersistentClass().getName();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		return query.list();
	}

	@Override
	public List<T> getAll(int page, int pageSize) {
		String queryString = "from " + getPersistentClass().getName();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		int firstResult = (page - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public T getByProperty(String propertyName, Object value) {
		String queryString = "from " + getPersistentClass().getName()
				+ " as model where model." + propertyName + "=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		query.setParameter(0, value);
		List<T> list = query.list();
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<T> getByProperty(String propertyName, Object value, int page,
			int pageSize) {
		return this.getByProperties(new String[] { propertyName },
				new Object[] { value }, page, pageSize);
	}

	@Override
	public List<T> getByPropertyOrder(String propertyName, Object value,
			int page, int pageSize, String propertyOrder, boolean isSequence) {
		String queryString = "from " + getPersistentClass().getName()
				+ " as model where model." + propertyName
				+ "=? order by model." + propertyOrder;
		if (isSequence) {
			queryString += " asc";
		} else {
			queryString += " desc";
		}
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		query.setParameter(0, value);
		int firstResult = (page - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> getByProperties(String[] propertyNames, Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + getPersistentClass().getName());
		strBuffer.append(" as model where ");
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			strBuffer.append(" like ");
			strBuffer.append("? ");
		}
		String queryString = strBuffer.toString();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		return query.list();
	}

	@Override
	public int countByProperties(String[] propertyNames, Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select count(id) from "
				+ getPersistentClass().getName());
		if (propertyNames.length > 0) {
			strBuffer.append(" as model where ");
		}
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			String fieldName = "";
			try {
				fieldName = getPersistentClass()
						.getDeclaredField(propertyNames[i]).getType().getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					strBuffer.append(" = ");
					strBuffer.append("? ");
				} else {
					strBuffer.append(" like ");
					strBuffer.append("? ");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String queryString = strBuffer.toString();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			String fieldName = "";
			try {
				fieldName = getPersistentClass()
						.getDeclaredField(propertyNames[i]).getType().getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					query.setParameter(i, values[i]);
				} else {
					query.setParameter(i, "%" + values[i] + "%");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@SuppressWarnings("rawtypes")
		List list = query.list();
		Long result = (Long) list.get(0);
		return result.intValue();
	}

	@Override
	public List<T> getByProperties(String[] propertyNames, Object[] values,
			int page, int pageSize) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + getPersistentClass().getName());
		if (propertyNames.length > 0) {
			strBuffer.append(" as model where ");
		}
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			String fieldName = "";
			try {
				fieldName = getPersistentClass()
						.getDeclaredField(propertyNames[i]).getType().getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					strBuffer.append(" = ");
					strBuffer.append("? ");
				} else {
					strBuffer.append(" like ");
					strBuffer.append("? ");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String queryString = strBuffer.toString();

		int firstResult = (page - 1) * pageSize;

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		for (int i = 0; i < values.length; i++) {
			String fieldName = "";
			try {
				fieldName = getPersistentClass()
						.getDeclaredField(propertyNames[i]).getType().getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					query.setParameter(i, values[i]);
				} else {
					query.setParameter(i, "%" + values[i] + "%");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return query.list();
	}

	@Override
	public List<T> getAllAndOrderByProperty(String propertyName,
			boolean isSequence) {
		String queryString = "from " + getPersistentClass().getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		return query.list();
	}

	@Override
	public List<T> getAndOrderByProperty(int firstResult, int fetchSize,
			String propertyName, boolean isSequence) {
		String queryString = "from " + getPersistentClass().getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}

		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		query.setFirstResult(firstResult);
		query.setMaxResults(fetchSize);
		return query.list();
	}

	public List<T> find(String hql) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public long count(String hql) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public long count(String hql, Map<String, Object> params) {
		Query q = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int countModelByProperties(String[] propertyNames, Object[] values,
			List<Integer> departmentList) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select count(id) from "
				+ getPersistentClass().getName());
		strBuffer.append(" as model where 1=1  ");
		for (int i = 0; i < propertyNames.length; i++) {
			strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			String fieldName = "";
			Class<?> clazz = null;
			try {
				String propertyName = propertyNames[i];
				boolean contain = propertyName.contains(".");
				if (contain) {
					String[] propertyArr = propertyName.split("\\.");
					String clazzName = propertyArr[propertyArr.length - 2];
					clazz = getPersistentClass().getDeclaredField(clazzName)
							.getType();
					propertyName = propertyArr[propertyArr.length - 1];
				} else {
					clazz = getPersistentClass();
				}
				fieldName = clazz.getDeclaredField(propertyName).getType()
						.getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					strBuffer.append(" = ");
					strBuffer.append("? ");
				} else {
					strBuffer.append(" like ");
					strBuffer.append("? ");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Field[] fields = getPersistentClass().getDeclaredFields();
		for (Field field : fields) {
			if ("department".equals(field.getName())) {
				strBuffer
						.append("and model.department.id in (:departmentList)");
				break;
			}
		}
		if (!strBuffer.toString().contains("department.id")) {
			strBuffer.append("and model.departmentId in (:departmentList)");
		}
		String queryString = strBuffer.toString();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			String fieldName = "";
			Class<?> clazz = null;
			try {
				String propertyName = propertyNames[i];
				boolean contain = propertyName.contains(".");
				if (contain) {
					String[] propertyArr = propertyName.split("\\.");
					String clazzName = propertyArr[propertyArr.length - 2];
					clazz = getPersistentClass().getDeclaredField(clazzName)
							.getType();
					propertyName = propertyArr[propertyArr.length - 1];
				} else {
					clazz = getPersistentClass();
				}
				fieldName = clazz.getDeclaredField(propertyName).getType()
						.getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					query.setParameter(i, values[i]);
				} else {
					query.setParameter(i, "%" + values[i] + "%");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (departmentList != null) {
			query.setParameterList("departmentList", departmentList);
		} else {
			query.setParameter("departmentList", -1);
		}
		List list = query.list();
		Long result = (Long) list.get(0);
		return result.intValue();
	}

	@Override
	public List<T> getModelByProperties(String[] propertyNames,
			Object[] values, List<Integer> departmentList, int page,
			int pageSize) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + getPersistentClass().getName());
		strBuffer.append(" as model where 1=1");
		for (int i = 0; i < propertyNames.length; i++) {
			strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			String fieldName = "";
			Class<?> clazz = null;
			try {
				String propertyName = propertyNames[i];
				boolean contain = propertyName.contains(".");
				if (contain) {
					String[] propertyArr = propertyName.split("\\.");
					String clazzName = propertyArr[propertyArr.length - 2];
					clazz = getPersistentClass().getDeclaredField(clazzName)
							.getType();
					propertyName = propertyArr[propertyArr.length - 1];
				} else {
					clazz = getPersistentClass();
				}
				fieldName = clazz.getDeclaredField(propertyName).getType()
						.getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					strBuffer.append(" = ");
					strBuffer.append("? ");
				} else {
					strBuffer.append(" like ");
					strBuffer.append("? ");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Field[] fields = getPersistentClass().getDeclaredFields();
		for (Field field : fields) {
			if ("department".equals(field.getName())) {
				strBuffer
						.append("and model.department.id in (:departmentList)");
				break;
			}
		}
		if (!strBuffer.toString().contains("department.id")) {
			strBuffer.append("and model.departmentId in (:departmentList)");
		}
		String queryString = strBuffer.toString();
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(queryString);
		int firstResult = (page - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		for (int i = 0; i < values.length; i++) {
			String fieldName = "";
			Class<?> clazz = null;
			try {
				String propertyName = propertyNames[i];
				boolean contain = propertyName.contains(".");
				if (contain) {
					String[] propertyArr = propertyName.split("\\.");
					String clazzName = propertyArr[propertyArr.length - 2];
					clazz = getPersistentClass().getDeclaredField(clazzName)
							.getType();
					propertyName = propertyArr[propertyArr.length - 1];
				} else {
					clazz = getPersistentClass();
				}
				fieldName = clazz.getDeclaredField(propertyName).getType()
						.getName();
				if ("java.lang.Short".equals(fieldName)
						|| "java.sql.Timestamp".equals(fieldName)
						|| "java.lang.Integer".equals(fieldName)) {
					query.setParameter(i, values[i]);
				} else {
					query.setParameter(i, "%" + values[i] + "%");
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (departmentList != null) {
			query.setParameterList("departmentList", departmentList);
		} else {
			query.setParameter("departmentList", -1);
		}
		return query.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
