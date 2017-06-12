package com.data.service.teacher.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.teacher.impl.TeacherDaoImpl;
import com.data.model.Teacher;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.teacher.ITeacherService;
import com.data.tools.Page;

/**
 * @ClassName: TeacherServiceImpl
 * @Description: TODO(教师信息业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午1:31:12
 */
@Service("iTeacherService")
public class TeacherServiceImpl extends BaseSericeImpl<Teacher, Integer>
		implements ITeacherService {
	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private TeacherDaoImpl teacherDaoImpl;
	/**
	 * @Fields userList : TODO(用户List)
	 */
	private List<Teacher> teacherList = null;

	@Resource(name = "teacherDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Teacher, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page queryTeacher(Teacher teacher, List<Integer> departmentIdList,
			int page, int rows) throws Exception {
		checkTeacherQueryCondition(teacher);
		sum = iBaseDao.countModelByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), departmentIdList);
		pager = new Page(sum, page, rows);
		teacherList = iBaseDao.getModelByProperties(
				propertyList.toArray(propertys), valuesList.toArray(),
				departmentIdList, pager.getCurrentPage(), pager.getPageSize());
		pager.setRows(teacherList);
		return pager;
	}

	/**
	 * @Title: checkUserQueryCondition
	 * @Description: TODO(查询用户的查询条件)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月28日 下午2:26:28
	 * @throws
	 */
	public void checkTeacherQueryCondition(Teacher teacher) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (teacher.getUsers().getFullname() != null
				&& teacher.getUsers().getFullname().length() != 0) {
			propertyList.add("users.fullname");
			valuesList.add(teacher.getUsers().getFullname());
		}
		if (teacher.getTeachNo() != null && teacher.getTeachNo().length() != 0) {
			propertyList.add("teachNo");
			valuesList.add(teacher.getTeachNo());
		}
	}
}
