package com.data.dao.survey.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Survey;

/**
 * @ClassName: SurveyDaoImpl
 * @Description: TODO(调查管理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月25日 下午3:38:22
 */
@Repository("surveyDaoImpl")
public class SurveyDaoImpl extends BaseDaoImpl<Survey, Integer> {

	/**
	 * @Title: insertSurvey
	 * @Description: TODO(保存调查)
	 * @param @param survey
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午1:35:52
	 * @throws
	 */
	public int insertSurvey(Survey survey) throws Exception {
		sql = "INSERT INTO survey (ID,USER_ID,DEPARTMENT_ID, START_TIME, END_TIME,ITEM_ID,IS_SURVEY_COMPLETE,TYPE) VALUES (?,?, ?, ?, ?, ?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(Survey.class);
		query.setParameter(0, survey.getId());
		query.setParameter(1, survey.getUsers().getId());
		query.setParameter(2, survey.getDepartmentId());
		query.setParameter(3, survey.getStartTime());
		query.setParameter(4, survey.getEndTime());
		query.setParameter(5, survey.getItem().getId());
		query.setParameter(6, survey.getIsSurveyComplete());
		query.setParameter(7, survey.getType());
		return query.executeUpdate();

	}

	public List<Survey> queryDepartmentChart(Survey survey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
