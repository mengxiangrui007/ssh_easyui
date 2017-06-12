package com.data.service.rolemenu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.rolemenu.impl.RoleMenuDaoImpl;
import com.data.model.Menu;
import com.data.model.Role;
import com.data.model.RoleMenu;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.rolemenu.IRoleMenuService;
import com.data.tools.WebUtil;

@Service("iRoleMenuService")
public class RoleMenuServiceImpl extends BaseSericeImpl<RoleMenu, Integer>
		implements IRoleMenuService {
	@Autowired(required = true)
	private RoleMenuDaoImpl roleMenuDaoImpl;

	@Resource(name = "roleMenuDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<RoleMenu, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<RoleMenu> queryRoleMenu(RoleMenu roleMenu) throws Exception {
		return roleMenuDaoImpl.queryRoleMenu(roleMenu);
	}

	@Override
	public void insertRoleMenu(Integer departmentId, Integer roleId,
			List<Integer> asList) throws Exception {
		roleMenuDaoImpl.deleteRoleMenu(departmentId, roleId);
		RoleMenu roleMenu = null;
		for (Integer menuId : asList) {
			roleMenu = new RoleMenu();
			roleMenu.setId(Integer.parseInt(WebUtil.getPK() + ""));
			roleMenu.setDepartmentId(departmentId);
			roleMenu.setRole(new Role(roleId));
			roleMenu.setMenu(new Menu(menuId));
			iBaseDao.save(roleMenu);
		}
	}
}
