package com.data.dao.user.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Users;

/**
 * @ClassName: UserDaoImpl
 * @Description: TODO(特殊业务HQL处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午3:54:04
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<Users, Integer> {

	/**
	 * @Title: updateUserAccountLocked
	 * @Description: TODO(修改用户状态)
	 * @param @param user
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月12日 下午3:53:44
	 * @throws
	 */
	public int updateUserAccountLocked(Users user) throws Exception {
		hql = "update Users user set user.accountLocked=? where user.id=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, user.getAccountLocked());
		query.setParameter(1, user.getId());
		return query.executeUpdate();
	}
}
