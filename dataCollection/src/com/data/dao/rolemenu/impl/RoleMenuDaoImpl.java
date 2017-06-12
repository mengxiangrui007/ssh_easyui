package com.data.dao.rolemenu.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.RoleMenu;

/**
 * @ClassName: RoleMenuDaoImpl
 * @Description: TODO(院系角色和菜单的关系)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月23日 下午4:50:51
 */
@Repository("roleMenuDaoImpl")
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu, Integer> {

	/**
	 * @Title: queryRoleMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param roleMenu
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<RoleMenu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午3:19:10
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<RoleMenu> queryRoleMenu(RoleMenu roleMenu) throws Exception {
		hql = "FROM RoleMenu t where t.role.id=? and t.departmentId=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, roleMenu.getRole().getId());
		query.setParameter(1, roleMenu.getDepartmentId());
		return query.list();
	}

	/**
	 * @Title: insertRoleMenu
	 * @Description: TODO(删除角色菜单)
	 * @param @param departmentId
	 * @param @param roleId
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午3:19:14
	 * @throws
	 */
	public void deleteRoleMenu(Integer departmentId, Integer roleId)
			throws Exception {
		hql = "DELETE FROM RoleMenu t where t.departmentId=? and t.role.id=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, departmentId);
		query.setParameter(1, roleId);
		query.executeUpdate();
	}
}
