package com.data.dao.student.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Student;

/**
 * @ClassName: StudentDaoImpl
 * @Description: TODO(学生特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午1:25:40
 */
@Repository("studentDaoImpl")
public class StudentDaoImpl extends BaseDaoImpl<Student, Integer> {

	/**
	 * @Title: queryProfessional
	 * @Description: TODO(查询年级)
	 * @param @param departmentIdList
	 * @param @return 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午3:51:31
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<Student> queryGread(List<Integer> professionalIdList)
			throws Exception {
		sql = "select DISTINCT t1.PROFESSIONAL_ID as professionalId,  t1.GREAD as gread from student t1 where t1.PROFESSIONAL_ID IN (:professionalIdList) ";
		SQLQuery query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.addScalar("professionalId", new org.hibernate.type.IntegerType());
		query.addScalar("gread", new org.hibernate.type.IntegerType());
		query.setParameterList("professionalIdList", professionalIdList);
		return query.setResultTransformer(
				Transformers.aliasToBean(Student.class)).list();
	}

	/**
	 * @Title: queryClass
	 * @Description: TODO(查询班级)
	 * @param @param asList
	 * @param @param asList2
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Student> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午4:34:14
	 * @throws
	 */
	public List<Student> queryClass(List<String> professionaGreadList)
			throws Exception {
		List<Student> class_s = new ArrayList<Student>();
		for (String professionaGread : professionaGreadList) {
			sql = "SELECT DISTINCT t1.DEPARTMENT_ID AS departmentId,t1.PROFESSIONAL_ID AS professionalId,  t1.GREAD as gread, t1.CLASS AS class_ FROM student t1 WHERE t1.PROFESSIONAL_ID = ? AND t1.GREAD =?";
			SQLQuery query = this.getSessionFactory().getCurrentSession()
					.createSQLQuery(sql);
			query.addScalar("departmentId",
					new org.hibernate.type.IntegerType());
			query.addScalar("professionalId",
					new org.hibernate.type.IntegerType());
			query.addScalar("gread", new org.hibernate.type.IntegerType());
			query.addScalar("class_", new org.hibernate.type.IntegerType());
			query.setParameter(0,
					Integer.parseInt(professionaGread.split(":")[0]));
			query.setParameter(1,
					Integer.parseInt(professionaGread.split(":")[1]));
			Student student = (Student) query
					.setResultTransformer(
							Transformers.aliasToBean(Student.class)).list()
					.get(0);
			class_s.add(student);
		}
		return class_s;
	}

}
