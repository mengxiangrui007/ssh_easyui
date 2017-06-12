package com.data.service.login;

import com.data.model.Users;

/**
 * @ClassName: ILoginService
 * @Description: TODO(登陆类接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午9:10:45
 */
public interface ILoginService {

	/**
	 * @Title: verifyUser
	 * @Description: TODO(验证用户)
	 * @param @param username
	 * @param @param passwd
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Users 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月13日 下午8:30:40
	 * @throws
	 */
	public Users verifyUser(String username, String passwd) throws Exception;

}
