package com.data.dao.item.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Item;

/**
 * @ClassName: ItemDaoImpl
 * @Description: TODO(调查项目特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月20日 下午5:04:46
 */
@Repository("itemDaoImpl")
public class ItemDaoImpl extends BaseDaoImpl<Item, Integer> {

	/**
	 * @Title: updateItemIsHaveQuestion
	 * @Description: TODO(更新调查项问题)
	 * @param @param item
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 下午3:00:21
	 * @throws
	 */
	public int updateItemIsHaveQuestion(Item item) throws Exception {
		sql = "UPDATE item SET ISHAVA_QUESTION=1 WHERE (ID=?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, item.getId());
		return query.executeUpdate();
	}

	/**
	 * @Title: queryHaveQuestionItem
	 * @Description: TODO(查询已创建问题调查项目)
	 * @param @param departmentIdList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Item> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月26日 下午9:36:06
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Item> queryHaveQuestionItem(List<Integer> departmentIdList)
			throws Exception {
		sql = "select t1.id as id,t1.name as name,t1.DESCRIPTION as description from item t1 where t1.ISHAVA_QUESTION = 1 and t1.DEPARTMENT_ID in(:departmentIdList)";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("name", new org.hibernate.type.StringType());
		query.addScalar("description", new org.hibernate.type.StringType());
		query.setParameterList("departmentIdList", departmentIdList);
		return query.setResultTransformer(Transformers.aliasToBean(Item.class))
				.list();
	}
}
