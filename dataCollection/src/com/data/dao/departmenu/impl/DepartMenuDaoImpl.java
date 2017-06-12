package com.data.dao.departmenu.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.DepartmentMenu;

/**
 * @ClassName: MenuDepartDaoImpl
 * @Description: TODO(公司和菜单特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月22日 下午2:48:21
 */
@Repository("departMenuDaoImpl")
public class DepartMenuDaoImpl extends BaseDaoImpl<DepartmentMenu, Integer> {

	/**
	 * @Title: queryDepartMenu
	 * @Description: TODO(查询院系的菜单树)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午2:50:20
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentMenu> queryDepartMenu(Integer departmentId)
			throws Exception {
		hql = "FROM DepartmentMenu t where t.department.id=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, departmentId);
		return query.list();
	}

	/**
	 * @Title: deleteDepartmentMenu
	 * @Description: TODO(删除院系与菜单表关系)
	 * @param @param departmentId
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午2:24:17
	 * @throws
	 */
	public boolean deleteDepartmentMenu(Integer departmentId) {
		hql = "DELETE FROM DepartmentMenu t where t.department.id=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, departmentId);
		return query.executeUpdate() > 0 ? true : false;
	}
}
