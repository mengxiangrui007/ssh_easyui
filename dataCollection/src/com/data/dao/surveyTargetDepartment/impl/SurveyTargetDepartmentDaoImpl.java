package com.data.dao.surveyTargetDepartment.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Student;
import com.data.model.Survey;
import com.data.model.SurveyTargetDepartment;
import com.data.model.Teacher;

/**
 * @ClassName: SurveyTargetDepartmentDaoImpl
 * @Description: TODO(调查目标部门)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月29日 下午12:50:16
 */
@Repository("surveyTargetDepartmentDaoImpl")
public class SurveyTargetDepartmentDaoImpl extends
		BaseDaoImpl<SurveyTargetDepartment, Integer> {

	/**
	 * @Title: insertTargetDepartment
	 * @Description: TODO(添加目标部门)
	 * @param @param targetDepartment
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午1:26:42
	 * @throws
	 */
	public int insertTargetDepartment(SurveyTargetDepartment targetDepartment) {
		sql = "INSERT INTO survey_target_department (ID,USER_TYPE,GREAD, CLASS, DEPARTMENT_ID,SURVEY_ID,PROFESSIONAL_ID,DEPARTMENT_TEACHER) VALUES (?,?, ?, ?, ?, ?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyTargetDepartment.class);
		query.setParameter(0, targetDepartment.getId());
		query.setParameter(1, targetDepartment.getUserType());
		query.setParameter(2, targetDepartment.getGread());
		query.setParameter(3, targetDepartment.getClass_());
		query.setParameter(4, targetDepartment.getDepartment().getId());
		query.setParameter(5, targetDepartment.getSurvey().getId());
		query.setParameter(6, targetDepartment.getProfessionalId());
		query.setParameter(7, targetDepartment.getDepartmentTeacher());
		return query.executeUpdate();

	}

	public List<Survey> queryDepartmentIsHaveSurvey(Student student) {
		sql = "SELECT t1.ID AS id, t1.START_TIME AS startTime, t1.END_TIME AS endTime,"
				+ " t1.ITEM_ID AS itemId, t3. NAME AS NAME,t2.id AS targetId FROM survey t1 JOIN survey_target_department"
				+ " t2 JOIN department t3 WHERE t1.ID = t2.SURVEY_ID AND t2.DEPARTMENT_ID = t3.ID AND "
				+ "t1.IS_SURVEY_COMPLETE = 0 AND t2.PROFESSIONAL_ID = ? AND t2.GREAD = ? "
				+ "AND t2.CLASS = ? AND t2.ID NOT IN ( SELECT p1.SURVEY_TARGET_ID FROM"
				+ " survey_deparment p1 JOIN student p2 WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
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

	public List<Survey> queryDepartmentIsHaveSurvey(Teacher teacher) {
		sql = "SELECT t1.ID AS id, t1.START_TIME AS startTime,"
				+ " t1.END_TIME AS endTime, t1.ITEM_ID AS itemId, "
				+ "t3. NAME AS NAME,t2.id AS targetId FROM survey t1 JOIN survey_target_department t2"
				+ " JOIN department t3 WHERE t1.ID = t2.SURVEY_ID AND t2.DEPARTMENT_ID = t3.ID"
				+ " AND t1.IS_SURVEY_COMPLETE = 0 AND t2.DEPARTMENT_TEACHER IS NOT NULL AND "
				+ "t2.ID NOT IN ( SELECT p1.SURVEY_TARGET_ID FROM survey_teacher p1 JOIN "
				+ "teacher p2 WHERE p1.USER_ID = p2.USER_ID AND p2.ID = ? )";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, teacher.getId());
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("startTime", new org.hibernate.type.TimestampType());
		query.addScalar("endTime", new org.hibernate.type.TimestampType());
		query.addScalar("targetId", new org.hibernate.type.IntegerType());
		query.addScalar("itemId", new org.hibernate.type.IntegerType());
		query.addScalar("name", new org.hibernate.type.StringType());
		return query.setResultTransformer(
				Transformers.aliasToBean(Survey.class)).list();
	}

}
