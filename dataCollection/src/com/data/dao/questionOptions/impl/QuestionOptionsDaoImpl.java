package com.data.dao.questionOptions.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.QuestionOptions;

/**
 * @ClassName: QuestionOptionsDaoImpl
 * @Description: TODO(选项与问题中间表)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月25日 上午10:26:23
 */
@Repository("questionOptionsDaoImpl")
public class QuestionOptionsDaoImpl extends
		BaseDaoImpl<QuestionOptions, Integer> {

	public int saveQuestionOptions(QuestionOptions questionOptions)
			throws Exception {

		sql = "INSERT INTO question_options (ID,OPTIONS_ID,QUESTION_ID) VALUES (?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, questionOptions.getId());
		query.setParameter(1, questionOptions.getOptions().getId());
		query.setParameter(2, questionOptions.getQuestion().getId());
		return query.executeUpdate();

	}
}
