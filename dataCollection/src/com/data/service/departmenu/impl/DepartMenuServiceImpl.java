package com.data.service.departmenu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.departmenu.impl.DepartMenuDaoImpl;
import com.data.model.Department;
import com.data.model.DepartmentMenu;
import com.data.model.Menu;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.departmenu.IDepartMenuService;
import com.data.tools.WebUtil;

@Service("iDepartMenuService")
public class DepartMenuServiceImpl extends
		BaseSericeImpl<DepartmentMenu, Integer> implements IDepartMenuService {

	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private DepartMenuDaoImpl departMenuDaoImpl;

	@Resource(name = "departMenuDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<DepartmentMenu, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<DepartmentMenu> queryDepartMenu(Integer departmentId)
			throws Exception {
		return departMenuDaoImpl.queryDepartMenu(departmentId);
	}

	@Override
	public void insertDepartmentMenu(Integer departmentId, List<Integer> list) {
		flag = departMenuDaoImpl.deleteDepartmentMenu(departmentId);
		DepartmentMenu departmentMenu = null;
		for (Integer menuId : list) {
			departmentMenu = new DepartmentMenu(menuId);
			departmentMenu.setDepartment(new Department(departmentId));
			departmentMenu.setMenu(new Menu(menuId));
			departmentMenu.setId(Integer.parseInt(WebUtil.getPK() + ""));
			iBaseDao.save(departmentMenu);
		}
	}
}
