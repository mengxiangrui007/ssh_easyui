package com.data.service.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.model.Role;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.role.IRoleService;

@Service("iRoleService")
public class RoleServiceImpl extends BaseSericeImpl<Role, Integer> implements
		IRoleService {
	@Resource(name = "roleDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Role, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<Role> queryAllRole() throws Exception {
		return super.getAll();
	}
}
