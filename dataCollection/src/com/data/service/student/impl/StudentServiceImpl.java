package com.data.service.student.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.student.impl.StudentDaoImpl;
import com.data.dao.user.impl.UserDaoImpl;
import com.data.model.Student;
import com.data.model.Users;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.student.IStudentService;
import com.data.tools.DateUtil;
import com.data.tools.Page;
import com.data.tools.WebUtil;

/**
 * @ClassName: StudentServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午3:56:09
 */
@Service("iStudentService")
public class StudentServiceImpl extends BaseSericeImpl<Student, Integer>
		implements IStudentService {

	/**
	 * @Fields studentDaoImpl : TODO(注入)
	 */
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	@Autowired
	private UserDaoImpl userDaoImpl;
	/**
	 * @Fields userList : TODO(用户List)
	 */
	private List<Student> studentList = null;

	@Resource(name = "studentDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Student, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page queryStudent(Student student, List<Integer> departmentIdList,
			int page, int rows) throws Exception {
		checkStudentQueryCondition(student);
		sum = iBaseDao.countModelByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), departmentIdList);
		pager = new Page(sum, page, rows);
		studentList = iBaseDao.getModelByProperties(
				propertyList.toArray(propertys), valuesList.toArray(),
				departmentIdList, pager.getCurrentPage(), pager.getPageSize());
		pager.setRows(studentList);
		return pager;
	}

	/**
	 * @Title: checkStudentQueryCondition
	 * @Description: TODO(用户的查询条件)
	 * @param @param student 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 下午9:42:13
	 * @throws
	 */
	private void checkStudentQueryCondition(Student stu) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (stu.getUsers().getFullname() != null
				&& stu.getUsers().getFullname().length() != 0) {
			propertyList.add("users.fullname");
			valuesList.add(stu.getUsers().getFullname());
		}
		if (stu.getStudentNo() != null && stu.getStudentNo().length() != 0) {
			propertyList.add("studentNo");
			valuesList.add(stu.getStudentNo());
		}
		if (stu.getProfessional().getProfessionalName() != null
				&& stu.getProfessional().getProfessionalName().length() != 0) {
			propertyList.add("professional.professionalName");
			valuesList.add(stu.getProfessional().getProfessionalName());
		}
	}

	@Override
	public void insertStudent(Student student) {
		student.setId(Integer.parseInt(WebUtil.getPK() + ""));
		student.getUsers().setId(Integer.parseInt(WebUtil.getPK() + ""));
		student.getUsers().setAccountCreated(DateUtil.getTimestamp());
		student.getUsers().setAccount(student.getStudentNo());
		student.getUsers().setAccountLocked((short) 1);
		Users users = student.getUsers();
		userDaoImpl.save(users);
		if (student.getUsers().getPassword() == null
				|| student.getUsers().getPassword().length() == 0) {
			student.getUsers().setPassword("123456");
		}
		iBaseDao.save(student);
	}

	@Override
	public Student showStudent(Student student) throws Exception {
		return iBaseDao.getById(student.getId());
	}

	@Override
	public boolean ajaxValidateStudent(Student student) throws Exception {
		return (iBaseDao.getById(student.getId())) != null ? true : false;
	}

	@Override
	public List<Student> queryGread(List<Integer> asList) throws Exception {
		// TODO Auto-generated method stub
		return studentDaoImpl.queryGread(asList);
	}

	@Override
	public List<Student> queryClass(List<String> professionaGreadList)
			throws Exception {
		// TODO Auto-generated method stub
		return studentDaoImpl.queryClass(professionaGreadList);
	}
}
