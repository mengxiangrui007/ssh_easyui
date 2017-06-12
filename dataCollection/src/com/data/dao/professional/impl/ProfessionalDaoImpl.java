package com.data.dao.professional.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Professional;

/**
 * @ClassName: professionalDaoImpl
 * @Description: TODO(专业实现类)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月27日 下午3:43:21
 */
@Repository("professionalDaoImpl")
public class ProfessionalDaoImpl extends BaseDaoImpl<Professional, Integer> {

	/**
	 * @Title: queryProfessional
	 * @Description: TODO(查询院系的专业)
	 * @param @param departmentIdList
	 * @param @return 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午3:51:31
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Professional> queryProfessional(List<Integer> departmentIdList)
			throws Exception {
		sql = "SELECT t1.ID AS id,t2.DEPARTMENT_ID as pid, t1.PROFESSIONAL_NAME AS professionalName FROM professional t1 JOIN professional_department t2 WHERE t1.ID = t2.PROFESSIONAL_ID AND t2.DEPARTMENT_ID IN (:departmentIdList)";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("pid", new org.hibernate.type.IntegerType());
		query.addScalar("professionalName", new org.hibernate.type.StringType());
		query.setParameterList("departmentIdList", departmentIdList);
		return query.setResultTransformer(
				Transformers.aliasToBean(Professional.class)).list();
	}

	/**
	 * @Title: queryChoiceProfessional
	 * @Description: TODO(查询选择的专业)
	 * @param @param asList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午5:19:12
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Professional> queryChoiceProfessional(
			List<Integer> professionalIdList) throws Exception {
		sql = "SELECT t1.ID AS id,t2.DEPARTMENT_ID as pid, t1.PROFESSIONAL_NAME AS professionalName FROM professional t1 JOIN professional_department t2 WHERE t1.ID = t2.PROFESSIONAL_ID AND t1.ID IN (:professionalIdList)";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.addScalar("id", new org.hibernate.type.IntegerType());
		query.addScalar("pid", new org.hibernate.type.IntegerType());
		query.addScalar("professionalName", new org.hibernate.type.StringType());
		query.setParameterList("professionalIdList", professionalIdList);
		return query.setResultTransformer(
				Transformers.aliasToBean(Professional.class)).list();
	}

}
