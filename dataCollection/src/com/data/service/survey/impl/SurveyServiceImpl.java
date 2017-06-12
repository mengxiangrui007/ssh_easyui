package com.data.service.survey.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.survey.impl.SurveyDaoImpl;
import com.data.dao.surveyTargetDepartment.impl.SurveyTargetDepartmentDaoImpl;
import com.data.dao.surveyTargetStudent.impl.SurveyTargetStudentDaoImpl;
import com.data.dao.surveyTargetTeacher.impl.SurveyTargetTeacherDaoImpl;
import com.data.model.Department;
import com.data.model.Item;
import com.data.model.Student;
import com.data.model.Survey;
import com.data.model.SurveyTargetDepartment;
import com.data.model.SurveyTargetStudent;
import com.data.model.SurveyTargetTeacher;
import com.data.model.Teacher;
import com.data.model.Users;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.survey.ISurveyService;
import com.data.tools.Page;
import com.data.tools.WebUtil;
import com.data.view.survey.vo.SurveyTargetBean;

/**
 * @ClassName: SurveyServiceImpl
 * @Description: TODO(实现层面)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月25日 下午3:40:36
 */
@Service("iSurveyService")
public class SurveyServiceImpl extends BaseSericeImpl<Survey, Integer>
		implements ISurveyService {
	/**
	 * @Fields surveyList : TODO(创建一个调查的集合)
	 */
	private List<Survey> surveyList = new ArrayList<Survey>();

	/**
	 * @Fields itemList : TODO(创建一个调查项目集合)
	 */
	private List<Item> itemList = null;
	/**
	 * @Fields iSurveyService : TODO(注入)
	 */
	@Autowired(required = true)
	private SurveyDaoImpl surveyDaoImpl;
	@Autowired(required = true)
	private SurveyTargetStudentDaoImpl surveyTargetStudentDaoImpl;
	@Autowired(required = true)
	private SurveyTargetTeacherDaoImpl surveyTargetTeacherDaoImpl;
	@Autowired(required = true)
	private SurveyTargetDepartmentDaoImpl surveyTargetDepartmentDaoImpl;
	private Survey survey = null;

	@Resource(name = "surveyDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Survey, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page querySurvey(Survey survey, List<Integer> departmentIdList,
			int page, int rows) throws Exception {
		checkSurveyQueryCondition(survey);
		sum = iBaseDao.countModelByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), departmentIdList);
		pager = new Page(sum, page, rows);
		List<Survey> surveyList = iBaseDao.getModelByProperties(
				propertyList.toArray(propertys), valuesList.toArray(),
				departmentIdList, pager.getCurrentPage(), pager.getPageSize());
		pager.setRows(surveyList);
		return pager;
	}

	/**
	 * @Title: checkSurveyQueryCondition
	 * @Description: TODO(用户的查询条件)
	 * @param @param survey 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 下午4:25:59
	 * @throws
	 */
	private void checkSurveyQueryCondition(Survey survey) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (survey.getItem().getName() != null
				&& survey.getItem().getName().length() != 0) {
			propertyList.add("item.name");
			valuesList.add(survey.getItem().getName());
		}
	}

	@Override
	public void insertSurvey(SurveyTargetBean surveyTarget, Users userInfo)
			throws Exception {
		survey = new Survey();
		survey.setId(Integer.parseInt(WebUtil.getPK() + ""));
		survey.setDepartmentId(userInfo.getDepartmentID());
		survey.setIsSurveyComplete((short) 0);
		survey.setStartTime(surveyTarget.getStartTime());
		survey.setEndTime(surveyTarget.getEndTime());
		survey.setItem(surveyTarget.getItem());
		survey.setType(surveyTarget.getType());
		survey.setUsers(userInfo);
		surveyDaoImpl.insertSurvey(survey);
		for (SurveyTargetBean targetBean : surveyTarget.getSurveyTargetList()) {
			if (survey.getType().intValue() == 1) {
				SurveyTargetStudent targetStudent = new SurveyTargetStudent();
				targetStudent.setId(Integer.parseInt(WebUtil.getPK() + ""));
				Student student = new Student();
				student.setId(surveyTarget.getTargetUserId());
				targetStudent.setStudent(student);
				targetStudent.setUserType(surveyTarget.getType());
				targetStudent.setProfessionalId(targetBean.getProfessionalId());
				targetStudent.setGread(targetBean.getGread());
				targetStudent.setClass_(targetBean.getClass_());
				targetStudent.setSurvey(survey);
				targetStudent.setDepartmentId(targetBean.getDepartmentId());
				targetStudent.setDepartmentTeacher(surveyTarget
						.getDepartmentTeacher());
				surveyTargetStudentDaoImpl.insertTargetStudent(targetStudent);
			} else if (survey.getType().intValue() == 2) {
				SurveyTargetTeacher targetTeacher = new SurveyTargetTeacher();
				targetTeacher.setId(Integer.parseInt(WebUtil.getPK() + ""));
				Teacher teacher = new Teacher();
				teacher.setId(surveyTarget.getTargetUserId());
				targetTeacher.setTeacher(teacher);
				targetTeacher.setUserType(surveyTarget.getType());
				targetTeacher.setProfessionalId(targetBean.getProfessionalId());
				targetTeacher.setGread(targetBean.getGread());
				targetTeacher.setClass_(targetBean.getClass_());
				targetTeacher.setSurvey(survey);
				targetTeacher.setDepartmentId(targetBean.getDepartmentId());
				targetTeacher.setDepartmentTeacher(surveyTarget
						.getDepartmentTeacher());
				surveyTargetTeacherDaoImpl.insertTargetTeacher(targetTeacher);
			} else if (survey.getType().intValue() == 3) {
				SurveyTargetDepartment targetDepartment = new SurveyTargetDepartment();
				targetDepartment.setId(Integer.parseInt(WebUtil.getPK() + ""));
				Department department = new Department();
				department.setId(surveyTarget.getDepartmentId());
				targetDepartment.setDepartment(department);
				targetDepartment.setUserType(surveyTarget.getType());
				targetDepartment.setProfessionalId(targetBean
						.getProfessionalId());
				targetDepartment.setGread(targetBean.getGread());
				targetDepartment.setClass_(targetBean.getClass_());
				targetDepartment.setSurvey(survey);
				targetDepartment.setDepartmentTeacher(surveyTarget
						.getDepartmentTeacher());
				surveyTargetDepartmentDaoImpl
						.insertTargetDepartment(targetDepartment);
			}
		}
	}

	@Override
	public Page queryUserIsHaveSurvey(Users users) throws Exception {
		List<Survey> notSurveyTotal = new ArrayList<Survey>();
		Teacher teacher = null;
		Student student = null;
		pager = new Page();
		if (users.getTeachers().size() > 0) {
			teacher = (Teacher) users.getTeachers().toArray()[0];
			surveyList = surveyTargetStudentDaoImpl
					.queryStudentIsHaveSurvey(teacher);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
			surveyList.clear();
			surveyList = surveyTargetTeacherDaoImpl
					.queryTeacherIsHaveSurvey(teacher);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
			surveyList.clear();
			surveyList = surveyTargetDepartmentDaoImpl
					.queryDepartmentIsHaveSurvey(teacher);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
		} else if (users.getStudents().size() > 0) {
			student = (Student) users.getStudents().toArray()[0];
			// 当前学生可以进行的测评
			surveyList = surveyTargetStudentDaoImpl
					.queryStudentIsHaveSurvey(student);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
			surveyList.clear();
			surveyList = surveyTargetTeacherDaoImpl
					.queryTeacherIsHaveSurvey(student);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
			surveyList.clear();
			surveyList = surveyTargetDepartmentDaoImpl
					.queryDepartmentIsHaveSurvey(student);
			if (surveyList.size() > 0) {
				notSurveyTotal.addAll(surveyList);
			}
		}
		pager.setRows(notSurveyTotal);
		return pager;
	}

	@Override
	public Survey queryUserSurvey(Integer surveyId) throws Exception {
		return iBaseDao.getById(surveyId);
	}

	@Override
	public List<Survey> queryDepartmentChart(Survey survey) throws Exception {
		// TODO Auto-generated method stub
		return surveyDaoImpl.queryDepartmentChart(survey);
	}
}
