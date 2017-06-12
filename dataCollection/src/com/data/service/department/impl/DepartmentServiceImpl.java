package com.data.service.department.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.department.impl.DepartmentDaoImpl;
import com.data.model.Department;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.department.IDepartmentService;
import com.data.share.initdata.StaticDataUtil;

@Service("iDepartmentService")
public class DepartmentServiceImpl extends BaseSericeImpl<Department, Integer>
		implements IDepartmentService {
	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private DepartmentDaoImpl departmentDaoImpl;
	/**
	 * @Fields departmentList : TODO(院系集合)
	 */
	private List<Department> departmentList = null;

	@Resource(name = "departmentDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Department, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<Department> queryAllDepartment() throws Exception {
		return super.getAll();
	}

	@Override
	public List<Department> queryUserDepartment(List<Integer> departmentIdList)
			throws Exception {
		List<Department> departmentTmps = new ArrayList<Department>();
		departmentList = getStaticDataDepartment();
		for (int i = 0, len = departmentList.size(); i < len; i++) {
			flag = departmentIdList.contains(departmentList.get(i).getId());
			if (flag) {
				departmentTmps.add(departmentList.get(i));
			}
		}
		return departmentTmps;
	}

	@Override
	public List<Department> queryAllConditionDepartment(
			List<Integer> departmentIdList) throws Exception {
		return departmentDaoImpl.queryAllConditionDepartment(departmentIdList);
	}

	@Override
	public List<Department> getStaticDataDepartment() throws Exception {
		return StaticDataUtil.getDepartmentList();
	}

}
