package com.data.dao.score.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Score;
import com.data.model.SurveyDeparment;
import com.data.model.SurveyStudent;
import com.data.model.SurveyTeacher;

/**
 * @ClassName: ScoreDaoImpl
 * @Description: TODO(分数特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年6月2日 下午9:07:40
 */
@Repository("scoreDaoImpl")
public class ScoreDaoImpl extends BaseDaoImpl<Score, Integer> {

	public void insertSurveyStudent(SurveyStudent surveyStudent) {
		sql = "INSERT INTO survey_student (ID,SURVEY_TARGET_ID,SURVEY_TIME, USER_ID,DEPARTMENT_ID) VALUES (?,?, ?, ?, ?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyStudent.class);
		;
		query.setParameter(0, surveyStudent.getId());
		query.setParameter(1, surveyStudent.getSurveyTargetStudent().getId());
		query.setParameter(2, surveyStudent.getSurveyTime());
		query.setParameter(3, surveyStudent.getUserId());
		query.setParameter(4, surveyStudent.getDepartmentId());
		query.executeUpdate();

	}

	public void insertSurveyTeacher(SurveyTeacher surveyTeacher) {
		sql = "INSERT INTO survey_teacher (ID,USER_ID,SURVEY_TIME, SURVEY_TARGET_ID,DEPARTMENT_ID) VALUES (?,?, ?, ?, ?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyTeacher.class);
		query.setParameter(0, surveyTeacher.getId());
		query.setParameter(1, surveyTeacher.getUserId());
		query.setParameter(2, surveyTeacher.getSurveyTime());
		query.setParameter(3, surveyTeacher.getSurveyTargetTeacher().getId());
		query.setParameter(4, surveyTeacher.getDepartmentId());
		query.executeUpdate();

	}

	public void insertSurveyDeparment(SurveyDeparment surveyDeparment) {
		sql = "INSERT INTO survey_deparment (ID,SURVEY_TIME,USER_ID, SURVEY_TARGET_ID,DEPARTMENT_ID) VALUES (?,?, ?, ?, ?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(SurveyDeparment.class);
		query.setParameter(0, surveyDeparment.getId());
		query.setParameter(1, surveyDeparment.getSurveyTime());
		query.setParameter(2, surveyDeparment.getUserId());
		query.setParameter(3, surveyDeparment.getSurveyTargetDepartment()
				.getId());
		query.setParameter(4, surveyDeparment.getDepartmentId());
		query.executeUpdate();
	}

}
