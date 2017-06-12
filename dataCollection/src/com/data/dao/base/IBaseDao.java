package com.data.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IBaseDao
 * @Description: TODO(公共Dao)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午2:24:26
 */
public interface IBaseDao<T, PK extends Serializable> {

	/**
	 * @Title: save
	 * @Description: TODO(保存一个实体对象到数据库)
	 * @param @param entity 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:51:22
	 * @throws
	 */
	public void save(T entity);

	/**
	 * @Title: update
	 * @Description: TODO(更新一个实体对象)
	 * @param @param entity 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:52:42
	 * @throws
	 */
	public void update(T entity);

	/**
	 * @Title: saveOrUpdate
	 * @Description: TODO(保存或者更新一个对象到数据库)
	 * @param @param entity 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:51:34
	 * @throws
	 */
	public void saveOrUpdate(T entity);

	/**
	 * @Title: delete
	 * @Description: TODO(通过ID删除一个对象)
	 * @param @param id 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:53:40
	 * @throws
	 */
	public void delete(PK id);

	/**
	 * @Title: delete
	 * @Description: TODO(删除一个对象)
	 * @param @param entity 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:53:54
	 * @throws
	 */
	public void delete(T entity);

	/**
	 * @Title: deleteByProperty
	 * @Description: TODO(通过给定属性名及值删除一个对象，如删除的是“名字”为“张三”的对象)
	 * @param @param propertyName
	 * @param @param value 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:54:14
	 * @throws
	 */
	public void deleteByProperty(String propertyName, Object value);

	/**
	 * @Title: get
	 * @Description: TODO(通过HQL语句获取对象)
	 * @param @param hql
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:55:17
	 * @throws
	 */
	public T get(String hql);

	/**
	 * @Title: getById
	 * @Description: TODO(通过ID获取一个对象)
	 * @param @param id
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午2:55:52
	 * @throws
	 */
	public T getById(PK id);

	/**
	 * @Title: get
	 * @Description: TODO(通过HQL和属性参数数组获取对象)
	 * @param @param hql
	 * @param @param params
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:03:45
	 * @throws
	 */
	public T get(String hql, Object[] params);

	/**
	 * @Title: get
	 * @Description: TODO(通过HQL和属性参数Map获取对象)
	 * @param @param hql
	 * @param @param params
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:04:19
	 * @throws
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * @Title: getAll
	 * @Description: TODO(获取所有对象)
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:07:09
	 * @throws
	 */
	public List<T> getAll();

	/**
	 * @Title: getAll
	 * @Description: TODO(获取所有对象，只取第page页，每页pageSize条记录)
	 * @param @param page
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:08:31
	 * @throws
	 */
	public List<T> getAll(int page, int pageSize);

	/**
	 * @Title: getByProperty
	 * @Description: TODO(通过对象和属性获取对象)
	 * @param @param propertyName
	 * @param @param value
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:05:13
	 * @throws
	 */
	public T getByProperty(String propertyName, Object value);

	/**
	 * @Title: getByProperty
	 * @Description: TODO(获取符合某个属性及其取值条件的所有记录,并分页)
	 * @param @param propertyName
	 * @param @param value
	 * @param @param page
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:16:14
	 * @throws
	 */
	public List<T> getByProperty(String propertyName, Object value, int page,
			int pageSize);

	/**
	 * @Title: getByPropertyOrder
	 * @Description: TODO(返回所有记录并按propertyName字段排序，true为升序，false为降序)
	 * @param @param propertyName
	 * @param @param value
	 * @param @param page
	 * @param @param pageSize
	 * @param @param propertyOrder
	 * @param @param isSequence
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:09:06
	 * @throws
	 */
	public List<T> getByPropertyOrder(String propertyName, Object value,
			int page, int pageSize, String propertyOrder, boolean isSequence);

	/**
	 * @Title: getByProperties
	 * @Description: TODO(获取符合某些属性及其取值条件的所有记录)
	 * @param @param propertyNames
	 * @param @param values
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:17:01
	 * @throws
	 */
	public List<T> getByProperties(String[] propertyNames, Object[] values);

	/**
	 * @Title: getByProperties
	 * @Description: TODO(获取符合某些属性及其取值条件的所有记录,并分页)
	 * @param @param propertyNames
	 * @param @param values
	 * @param @param page
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:17:23
	 * @throws
	 */
	public List<T> getByProperties(String[] propertyNames, Object[] values,
			int page, int pageSize);

	/**
	 * @Title: getAllAndOrderByProperty
	 * @Description: TODO(获取所有记录，并按某一属性升序或者降序排序)
	 * @param @param propertyName
	 * @param @param isSequence
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:17:44
	 * @throws
	 */
	public List<T> getAllAndOrderByProperty(String propertyName,
			boolean isSequence);

	/**
	 * @Title: getAndOrderByProperty
	 * @Description: 
	 *               TODO(获取所有记录，并按某一属性升序或者降序排序,同时从第firstResult条记录开始获取fetchSize条记录
	 *               )
	 * @param @param firstResult
	 * @param @param fetchSize
	 * @param @param propertyName
	 * @param @param isSequence
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:18:43
	 * @throws
	 */
	public List<T> getAndOrderByProperty(int firstResult, int fetchSize,
			String propertyName, boolean isSequence);

	/**
	 * @Title: count
	 * @Description: TODO(返回记录总数，无参数)
	 * @param @param hql
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:20:05
	 * @throws
	 */
	public long count(String hql);

	/**
	 * @Title: countAll
	 * @Description: TODO( 获取所有记录总数)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:20:44
	 * @throws
	 */
	public int countAll();

	/**
	 * @Title: countByProperty
	 * @Description: TODO(按某个属性查询符合其取值的记录总数，如查询所有名字为张三的人的总数)
	 * @param @param propertyName
	 * @param @param value
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:21:31
	 * @throws
	 */
	public int countByProperty(String propertyName, Object value);

	/**
	 * @Title: countByProperties
	 * @Description: TODO(按某些属性查询符合其取值的记录总数，如查询所有名字为张三且年龄为20的的人的总数)
	 * @param @param propertyNames
	 * @param @param values
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:21:45
	 * @throws
	 */
	public int countByProperties(String[] propertyNames, Object[] values);

	/**
	 * @Title: count
	 * @Description: TODO(返回记录总数，带参数)
	 * @param @param hql
	 * @param @param params
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月19日 下午3:21:57
	 * @throws
	 */
	public long count(String hql, Map<String, Object> params);

	/**
	 * @Title: countTeacherByProperties
	 * @Description: TODO(条件查询所在院系个数)
	 * @param @param propertyNames
	 * @param @param values
	 * @param @param departmentList
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 上午11:11:45
	 * @throws
	 */
	public int countModelByProperties(String[] propertyNames, Object[] values,
			List<Integer> departmentList);

	/**
	 * @Title: getTeacherByProperties
	 * @Description: TODO(条件查询所在院系个数)
	 * @param @param propertyNames
	 * @param @param values
	 * @param @param departmentList
	 * @param @param page
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 上午11:12:12
	 * @throws
	 */
	public List<T> getModelByProperties(String[] propertyNames,
			Object[] values, List<Integer> departmentList, int page,
			int pageSize);
}
