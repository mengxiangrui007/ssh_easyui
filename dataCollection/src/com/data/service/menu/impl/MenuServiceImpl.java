package com.data.service.menu.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.menu.impl.MenuDaoImpl;
import com.data.model.Menu;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.menu.IMenuService;
import com.data.share.initdata.StaticDataUtil;

@Service("iMenuService")
public class MenuServiceImpl extends BaseSericeImpl<Menu, Integer> implements
		IMenuService {

	@Autowired(required = true)
	private MenuDaoImpl menuDaoImpl;

	@Resource(name = "menuDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Menu, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<Menu> queryDepartmentMenu(Integer departmentId)
			throws Exception {
		return menuDaoImpl.queryDepartmentMenu(departmentId);
	}

	@Override
	public List<Menu> queryAllMenu() throws Exception {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public List<Menu> queryUserMenu(List<Integer> menuIDs) throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		Menu tempMenu = null;
		for (int i = 0, len = queryCacheMenu().size(); i < len; i++) {
			tempMenu = queryCacheMenu().get(i);
			for (Integer menuId : menuIDs) {
				if (menuId.intValue() == tempMenu.getId().intValue()) {
					menus.add(tempMenu);
				}
			}
		}
		Collections.sort(menus);
		return menus;
	}

	/**
	 * @Title: queryCacheMenu
	 * @Description: TODO(从缓存用查询菜单)
	 * @param @return 设定文件
	 * @return List<Menu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月1日 下午11:42:37
	 * @throws
	 */
	public List<Menu> queryCacheMenu() {
		return StaticDataUtil.getMenuList();
	}
}
