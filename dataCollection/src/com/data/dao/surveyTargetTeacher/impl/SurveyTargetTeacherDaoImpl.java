package com.data.dao.surveyTargetTeacher.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Student;
import com.data.model.Survey;
import com.data.model.SurveyTargetTeacher;
import com.data.model.Teacher;

/**
 * @ClassName: SurveyTargetTeacherDaoImpl
 * @Description: TODO(调查目标教师)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月29日 下午12:48:52
 */
@Repository("surveyTargetTeacherDaoImpl")
public class SurveyTargetTeacherDaoImpl extends
		BaseDaoImpl<SurveyTargetTeacher, Integer> {

	/**
	 * @return
	 * @Title: insertTargetTeacher
	 * @Description: TODO(添加目标教师)
	 * @param @param targetTeacher
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午1:24:13
	 * @throws
	 */
	public int insertTargetTeacher(SurveyTargetTeacher targetTeacher)
			throws Exception {
		sql = "INSERT INTO survey_target_teacher (ID,USER_TYPE,GREAD, CLASS, SURVEY_ID, TEACHER_ID,DEPARTMENT_ID,PROFESSIONAL_ID,DEPARTMENT_TEACHER) VALUES (?,?, ?, ?, ?, ?,?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyTargetTeacher.class);
		query.setParameter(0, targetTeacher.getId());
		query.setParameter(1, targetTeacher.getUserType());
		query.setParameter(2, targetTeacher.getGread());
		query.setParameter(3, targetTeacher.getClass_());
		query.setParameter(4, targetTeacher.getSurvey().getId());
		query.setParameter(5, targetTeacher.getTeacher().getId());
		query.setParameter(6, targetTeacher.getDepartmentId());
		query.setParameter(7, targetTeacher.getProfessionalId());
		query.setParameter(8, targetTeacher.getDepartmentTeacher());
		return query.executeUpdate();

	}

	/**
	 * @Title: queryTeacherIsHaveSurvey
	 * @Description: TODO(查询未填写的调查)
	 * @param @param student
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Survey> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月30日 下午1:48:41
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Survey> queryTeacherIsHaveSurvey(Student student)
			throws Exception {
		sql = "SELECT t2.id, t1.ID AS id, t1.START_TIME AS startTime,"
				+ " t1.END_TIME AS endTime, t1.ITEM_ID AS itemId, "
				+ "t4.FULLNAME AS NAME,t2.id AS targetId FROM survey t1 JOIN survey_target_teacher t2 "
				+ "JOIN teacher t3 JOIN users t4 WHERE t1.ID = t2.SURVEY_ID AND "
				+ "t2.TEACHER_ID = t3.ID AND t3.USER_ID = t4.ID AND "
				+ "t1.IS_SURVEY_COMPLETE = 0 AND t2.PROFESSIONAL_ID = ? "
				+ "AND t2.GREAD = ? AND t2.CLASS = ? AND t2.ID NOT IN "
				+ "( SELECT p1.SURVEY_TARGET_ID FROM survey_teacher p1 "
				+ "JOIN student p2 WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, student.getProfessional().getId());
		query.setParameter(1, student.getGread());
		query.setParameter(2, student.getClass_());
		query.setParameter(3, student.getId());
		query.addScalar("id", new org.hibernate.type.IntegerType());

		query.addScalar("startTime", new org.hibernate.type.TimestampType());
		query.addScalar("endTime", new org.hibernate.type.TimestampType());
		query.addScalar("itemId", new org.hibernate.type.IntegerType());
		query.addScalar("name", new org.hibernate.type.StringType());
		query.addScalar("targetId", new org.hibernate.type.IntegerType());
		return query.setResultTransformer(
				Transformers.aliasToBean(Survey.class)).list();
	}

	public List<Survey> queryTeacherIsHaveSurvey(Teacher teacher)
			throws Exception {
		sql = "SELECT t1.ID AS id, t1.START_TIME AS startTime, "
				+ "t1.END_TIME AS endTime, t1.ITEM_ID AS itemId,"
				+ " t4.FULLNAME AS NAME,t2.id AS targetId FROM survey t1 JOIN survey_target_teacher t2 JOIN teacher "
				+ "t3 JOIN users t4 WHERE t1.ID = t2.SURVEY_ID AND t2.TEACHER_ID = t3.ID AND "
				+ "t3.USER_ID = t4.ID AND t1.IS_SURVEY_COMPLETE = 0 AND t2.DEPARTMENT_TEACHER"
				+ " IS NOT NULL AND t2.ID NOT IN ( SELECT p1.SURVEY_TARGET_ID FROM survey_teacher "
				+ "p1 JOIN teacher p2 WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, teacher.getId());
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("startTime", new org.hibernate.type.TimestampType());
		query.addScalar("endTime", new org.hibernate.type.TimestampType());
		query.addScalar("itemId", new org.hibernate.type.IntegerType());
		query.addScalar("name", new org.hibernate.type.StringType());
		query.addScalar("targetId", new org.hibernate.type.IntegerType());
		return query.setResultTransformer(
				Transformers.aliasToBean(Survey.class)).list();
	}

}
