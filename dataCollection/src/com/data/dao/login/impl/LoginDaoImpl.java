package com.data.dao.login.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Users;

/**
 * @ClassName: LoginDaoImpl
 * @Description: TODO(登陆类)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午8:17:15
 */
@Repository("loginDaoImpl")
public class LoginDaoImpl extends BaseDaoImpl<Users, Integer> {

	public Users verifyUser(String username, String passwd) throws Exception {
		hql = "FROM Users user WHERE user.account=? and user.password=?";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, passwd);
		List<Users> users = (List<Users>) query.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
}
