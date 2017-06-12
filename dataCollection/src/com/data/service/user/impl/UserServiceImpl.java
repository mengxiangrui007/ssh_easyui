package com.data.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.user.impl.UserDaoImpl;
import com.data.model.Users;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.user.IUserService;
import com.data.tools.Page;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午3:56:09
 */
/**
 * @ClassName: UserServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月28日 下午1:27:29
 */
@Service("iUserService")
public class UserServiceImpl extends BaseSericeImpl<Users, Long> implements
		IUserService {
	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private UserDaoImpl userDaoImpl;
	/**
	 * @Fields userList : TODO(用户List)
	 */
	private List<Users> userList = null;

	/**
	 * 重写该方法,目的是为了覆盖超类中该方法的注解,指明注入指定的Dao对象, 否则spring 无法确定注入哪个Dao---有多个满足条件的Dao.
	 */
	@Resource(name = "userDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Users, Long> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page queryUser(Users userInfo, int page, int pageSize)
			throws Exception {
		checkUserQueryCondition(userInfo);
		sum = iBaseDao.countByProperties(propertyList.toArray(propertys),
				valuesList.toArray());
		pager = new Page(sum, page, pageSize);
		userList = iBaseDao.getByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), pager.getCurrentPage(),
				pager.getPageSize());
		pager.setRows(userList);
		return pager;
	}

	@Override
	public boolean updateUserAccountLocked(Users user) throws Exception {
		result = userDaoImpl.updateUserAccountLocked(user);
		return result > 0 ? true : false;
	}

	/**
	 * @Title: checkUserQueryCondition
	 * @Description: TODO(查询用户的查询条件)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月28日 下午2:26:28
	 * @throws
	 */
	public void checkUserQueryCondition(Users user) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (user.getAccount() != null && user.getAccount().length() != 0) {
			propertyList.add("account");
			valuesList.add(user.getAccount());
		}
		if (user.getFullname() != null && user.getFullname().length() != 0) {
			propertyList.add("fullname");
			valuesList.add(user.getFullname());
		}
	}

}
