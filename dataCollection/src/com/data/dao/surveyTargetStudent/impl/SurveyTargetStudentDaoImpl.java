package com.data.dao.surveyTargetStudent.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Student;
import com.data.model.Survey;
import com.data.model.SurveyTargetStudent;
import com.data.model.Teacher;

/**
 * @ClassName: SurveyTargetStudentDaoImpl
 * @Description: TODO(调查目标学生)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月29日 下午12:47:34
 */
@Repository("surveyTargetStudentDaoImpl")
public class SurveyTargetStudentDaoImpl extends
		BaseDaoImpl<SurveyTargetStudent, Integer> {

	public int insertTargetStudent(SurveyTargetStudent targetStudent)
			throws Exception {
		sql = "INSERT INTO survey_target_student (ID,USER_TYPE,GREAD, CLASS, STUDENT_ID, SURVEY_ID,DEPARTMENT_ID,PROFESSIONAL_ID,DEPARTMENT_TEACHER) VALUES (?,?, ?, ?, ?, ?,?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyTargetStudent.class);
		query.setParameter(0, targetStudent.getId());
		query.setParameter(1, targetStudent.getUserType());
		query.setParameter(2, targetStudent.getGread());
		query.setParameter(3, targetStudent.getClass_());
		query.setParameter(4, targetStudent.getStudent().getId());
		query.setParameter(5, targetStudent.getSurvey().getId());
		query.setParameter(6, targetStudent.getDepartmentId());
		query.setParameter(7, targetStudent.getProfessionalId());
		query.setParameter(8, targetStudent.getDepartmentTeacher());
		return query.executeUpdate();
	}

	/**
	 * @Title: queryStudentIsHaveSurvey
	 * @Description: TODO(对学生的未完成调查)
	 * @param @param student
	 * @param @return 设定文件
	 * @return List<Item> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午3:42:51
	 * @throws
	 */
	public List<Survey> queryStudentIsHaveSurvey(Student student) {
		sql = "SELECT t1.ID AS id, t1.START_TIME AS startTime, t1.END_TIME AS endTime, t1.ITEM_ID AS itemId,"
				+ " t4.FULLNAME AS name,t2.id AS targetId FROM"
				+ " survey t1 JOIN SURVEY_TARGET_STUDENT t2 JOIN student t3 JOIN users t4 "
				+ "WHERE t1.ID = t2.SURVEY_ID AND t2.STUDENT_ID = t3.ID AND t3.USER_ID = t4.ID "
				+ "AND t1.IS_SURVEY_COMPLETE = 0 AND t2.PROFESSIONAL_ID = ? AND t2.GREAD = ? AND t2.CLASS = ? "
				+ "AND t2.ID NOT IN ( SELECT p1.SURVEY_TARGET_ID FROM survey_student p1 JOIN student p2 "
				+ "WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
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
		query.addScalar("targetId", new org.hibernate.type.IntegerType());
		query.addScalar("name", new org.hibernate.type.StringType());
		return query.setResultTransformer(
				Transformers.aliasToBean(Survey.class)).list();
	}

	public List<Survey> queryStudentIsHaveSurvey(Teacher teacher) {
		sql = "SELECT t1.ID AS id, t1.START_TIME AS startTime, "
				+ "t1.END_TIME AS endTime, t1.ITEM_ID AS itemId, "
				+ "t4.FULLNAME AS NAME,t2.id AS targetId FROM survey t1 JOIN SURVEY_TARGET_STUDENT "
				+ "t2 JOIN student t3 JOIN users t4 WHERE t1.ID = t2.SURVEY_ID "
				+ "AND t2.STUDENT_ID = t3.ID AND t3.USER_ID = t4.ID AND"
				+ " t1.IS_SURVEY_COMPLETE = 0 AND t2.DEPARTMENT_TEACHER"
				+ " is not null AND t2.ID NOT IN "
				+ "( SELECT p1.SURVEY_TARGET_ID FROM survey_student p1 "
				+ "JOIN teacher p2 WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
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
