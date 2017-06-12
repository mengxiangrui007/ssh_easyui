package com.data.dao.menu.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Menu;

/**
 * @ClassName: MenuDaoImpl
 * @Description: TODO(菜单特殊业务物理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:49:58
 */
@Repository("menuDaoImpl")
public class MenuDaoImpl extends BaseDaoImpl<Menu, Integer> {

	/**
	 * @Title: queryHaveMenu
	 * @Description: TODO(查询用户拥有的菜单)
	 * @param @param roleId
	 * @param @param departmentId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<String> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月13日 上午11:22:50
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> queryHaveMenu(Integer roleId, Integer departmentId)
			throws Exception {
		sql = "SELECT t.MENU_ID FROM role_menu t WHERE t.ROLE_ID = ? AND t.DEPARTMENT_ID = ?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, roleId);
		query.setParameter(1, departmentId);
		return query.list();
	}

	/**
	 * @Title: queryAllMenu
	 * @Description: TODO(获取所有的菜单)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Menu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月13日 下午8:18:02
	 * @throws
	 */
	public List<Menu> queryAllMenu() throws Exception {
		return super.getAll();
	}

	@SuppressWarnings("unchecked")
	public List<Menu> queryDepartmentMenu(Integer departmentId)
			throws Exception {
		sql = "SELECT t1.ID as id,t1.PID as pid,t1.TITLE as title,t2.DEPARTMENT_ID as departmentId FROM menu t1 LEFT JOIN department_menu t2 ON t1.ID = t2.MENU_ID "
				+ "AND t2.DEPARTMENT_ID=? ORDER BY ORDER_ID";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("pid", new org.hibernate.type.IntegerType());
		query.addScalar("title", new org.hibernate.type.StringType());
		query.addScalar("departmentId", new org.hibernate.type.IntegerType());
		query.setParameter(0, departmentId);
		return query.setResultTransformer(Transformers.aliasToBean(Menu.class))
				.list();
	}
}
