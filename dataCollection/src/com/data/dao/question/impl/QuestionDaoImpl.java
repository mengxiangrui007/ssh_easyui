package com.data.dao.question.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Question;

/**
 * @ClassName: QuestionDaoImpl
 * @Description: TODO(问题类特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月24日 下午10:08:00
 */
@Repository("questionDaoImpl")
public class QuestionDaoImpl extends BaseDaoImpl<Question, Integer> {

	/**
	 * @Title: saveQuestion
	 * @Description: TODO(保存问题)
	 * @param @param question
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 上午10:14:08
	 * @throws
	 */
	public int saveQuestion(Question question) throws Exception {
		sql = "INSERT INTO question (ID,NAME,ORDER_NO, TYPE, SCORE, ITEM_ID) VALUES (?,?, ?, ?, ?, ?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity(Question.class);
		query.setParameter(0, question.getId());
		query.setParameter(1, question.getName());
		query.setParameter(2, question.getOrderNo());
		query.setParameter(3, question.getType());
		query.setParameter(4, question.getScore());
		query.setParameter(5, question.getItem().getId());
		return query.executeUpdate();
	}
}
