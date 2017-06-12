package com.data.service.user;

import com.data.model.Users;
import com.data.tools.Page;

/**
 * @ClassName: IUserService
 * @Description: TODO(用户接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午3:53:40
 */
public interface IUserService {
	/**
	 * @Title: queryUser
	 * @Description: TODO(查询用户)
	 * @param @param userInfo
	 * @param @param page
	 * @param @param pageSize
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月26日 上午10:43:43
	 * @throws
	 */
	public Page queryUser(Users userInfo, int page, int pageSize)
			throws Exception;

	/**
	 * @Title: updateUserAccountLocked
	 * @Description: TODO(修改用户的锁定状态)
	 * @param @param user
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return boolean 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月12日 下午3:41:32
	 * @throws
	 */
	public boolean updateUserAccountLocked(Users user) throws Exception;;

}
