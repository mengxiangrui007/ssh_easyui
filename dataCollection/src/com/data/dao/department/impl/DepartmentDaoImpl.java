package com.data.dao.department.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Department;

/**
 * @ClassName: DepartmentDaoImpl
 * @Description: TODO(院系管理特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:03:38
 */
/**
 * @ClassName: DepartmentDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月13日 上午10:15:18
 */
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Integer> {

	/**
	 * @Title: queryAllDepartment
	 * @Description: TODO(查询所有的院系)
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月12日 下午11:38:47
	 * @throws
	 */
	public List<Department> queryAllDepartment() throws Exception {
		return super.getAll();
	}

	/**
	 * @Title: queryChildDP
	 * @Description: TODO(查询本院系及其子院系部门ID)
	 * @param @param departmentID
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Long> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月13日 上午10:00:34
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> queryChildDP(Integer departmentID) throws Exception {
		sql = "select ID from department where id in( select id from department where FIND_IN_SET(id, getChildDP(?)));";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, departmentID);
		return query.list();
	}

	/**
	 * <p>
	 * 此方法存在问题！
	 * </p>
	 * 
	 * @Title: queryUserDepartment
	 * @Description: TODO(查询用户所拥有的部门)
	 * @param @param departmentIdList
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:37:42
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Department> queryUserDepartment(List<Integer> departmentIdList) {
		sql = "select * from department where id in(:departmentIdList)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameterList("departmentIdList", departmentIdList);
		return query.list();
	}

	/**
	 * @Title: queryAllConditionDepartment
	 * @Description: TODO(查询)
	 * @param @param departmentIdList
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月21日 上午11:04:09
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Department> queryAllConditionDepartment(
			List<Integer> departmentIdList) {
		hql = "from Department model where model.id in(:departmentIdList)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameterList("departmentIdList", departmentIdList);
		return query.list();
	}
}
