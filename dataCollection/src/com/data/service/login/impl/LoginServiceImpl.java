package com.data.service.login.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.department.impl.DepartmentDaoImpl;
import com.data.dao.login.impl.LoginDaoImpl;
import com.data.dao.menu.impl.MenuDaoImpl;
import com.data.model.Department;
import com.data.model.Menu;
import com.data.model.Professional;
import com.data.model.Student;
import com.data.model.Teacher;
import com.data.model.Users;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.login.ILoginService;

@Service("iLoginService")
@Scope("prototype")
public class LoginServiceImpl extends BaseSericeImpl<Users, Integer> implements
		ILoginService {
	/**
	 * @Fields loginDaoImpl : TODO(注入)
	 */
	@Autowired(required = true)
	private LoginDaoImpl loginDaoImpl;

	/**
	 * @Fields departmentDaoImpl : TODO(院系)
	 */
	@Autowired(required = true)
	private DepartmentDaoImpl departmentDaoImpl;

	/**
	 * @Fields menuDaoImpl : TODO(菜单)
	 */
	@Autowired(required = true)
	private MenuDaoImpl menuDaoImpl;

	private Users userInfo = null;
	/**
	 * @Fields teacher : TODO(教师)
	 */
	private Teacher teacher = null;
	/**
	 * @Fields student : TODO(学生)
	 */
	private Student student = null;
	/**
	 * @Fields departmentList : TODO(企业编码)
	 */
	private List<Integer> departmentIdList = new ArrayList<Integer>();

	/**
	 * @Fields Department : TODO(院系集合)
	 */
	private List<Department> departmentList = new ArrayList<Department>();

	/**
	 * @Fields menuList : TODO(用户所拥有的菜单ID权限)
	 */
	private List<Integer> menuIdList = new ArrayList<Integer>();
	/**
	 * @Fields menuList : TODO(菜单集合)
	 */
	private List<Menu> menuList = new ArrayList<Menu>();

	@Resource(name = "loginDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Users, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Users verifyUser(String username, String passwd) throws Exception {
		userInfo = loginDaoImpl.verifyUser(username, passwd);
		if (null != userInfo) {
			if (userInfo.getAccountLocked() == 1) {
			} else if (userInfo.getAccountLocked() == 0) {
				Short type = userInfo.getRole().getType();
				// 用户所在院系
				if (userInfo.getTeachers().size() > 0) {
					teacher = (Teacher) userInfo.getTeachers().toArray()[0];
				}
				if (userInfo.getStudents().size() > 0) {
					student = (Student) userInfo.getStudents().toArray()[0];
					Professional professional = student.getProfessional();
				}
				userInfo.setDepartmentID(teacher != null ? teacher
						.getDepartment().getId() : 0);
				if (userInfo.getDepartmentID() == 0) {
					userInfo.setDepartmentID(student != null ? student
							.getDepartment().getId() : 0);
				}
				if (type == 1) { // 超级管理员
					departmentList = departmentDaoImpl.queryAllDepartment();
					for (Department department : departmentList) {
						departmentIdList.add(department.getId());
					}
					userInfo.setDepartmentList(departmentIdList);
					menuList = menuDaoImpl.queryAllMenu();
					for (Menu menu : menuList) {
						menuIdList.add(menu.getId());
					}
					userInfo.setMenuID(menuIdList);
				} else if (type == 2) {// 管理员
					if (userInfo.getDepartmentID() != 0) {
						// 查询用户所有的可查看的部门
						departmentIdList = departmentDaoImpl
								.queryChildDP(userInfo.getDepartmentID());
						userInfo.setDepartmentList(departmentIdList);
						// 查询用户的查单权限
						menuIdList = menuDaoImpl.queryHaveMenu(userInfo
								.getRole().getId(), userInfo.getDepartmentID());
						userInfo.setMenuID(menuIdList);
					}
				} else if (type == 3 || type == 4) { // 普通用户
					if (userInfo.getDepartmentID() != 0) {
						// 查询用户的查单权限
						menuIdList = menuDaoImpl.queryHaveMenu(userInfo
								.getRole().getId(), userInfo.getDepartmentID());
						userInfo.setMenuID(menuIdList);
					}
				} else {
					userInfo = null;
				}
			} else {
				userInfo = null;
			}
		}
		return userInfo;
	}
}
