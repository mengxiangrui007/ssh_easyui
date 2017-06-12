package com.data.view.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.data.model.LogType;
import com.data.model.Users;
import com.data.service.login.ILoginService;
import com.data.share.propertie.BaseConfigBean;
import com.data.view.base.BaseAction;

/**
 * @ClassName: LoginAction
 * @Description: TODO(登陆Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午6:50:25
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -1703385693406028749L;

	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private ILoginService iLoginService;

	/**
	 * @Fields user : TODO(用一句话描述这个变量表示什么)
	 */
	private Users user = null;

	/**
	 * @Fields username : TODO(用户名)
	 */
	private String username;

	/**
	 * @Fields passwd : TODO(密码)
	 */
	private String passwd;

	/**
	 * @throws IOException
	 * @throws IOException
	 * @Title: verifyUser
	 * @Description: TODO(验证用户登录)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月12日 下午6:52:03
	 * @throws
	 */
	public String verifyUser() throws IOException {
		try {
			user = iLoginService.verifyUser(username, passwd);
			if (null != user) {
				if (user.getAccountLocked() == 1) {
					super.sendJsonMessage(false, "此账号已被管理员锁定！");
				} else {
					super.getRequest()
							.getSession()
							.removeAttribute(
									BaseConfigBean.ROOT_WEB_SESSION_USER);
					super.getRequest()
							.getSession()
							.setAttribute(BaseConfigBean.ROOT_WEB_SESSION_USER,
									user);
					super.sendJsonMessage(true, "");
				}
			} else {
				super.sendJsonMessage(false, "用户名或密码错误请重新登陆！");
			}
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION, "登陆失败" + e.getMessage(),
					super.getResourceName(), super.getRequest());
			super.sendJsonMessage(false, "登陆失败！");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: loginout
	 * @Description: TODO(登出)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月13日 下午8:09:09
	 * @throws
	 */
	public String loginout() throws IOException {
		try {
			super.getRequest().getSession()
					.removeAttribute(BaseConfigBean.ROOT_WEB_SESSION_USER);
			super.sendJsonMessage(true, "");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION, "登出失败！" + e.getMessage(),
					super.getResourceName(), super.getRequest());
			e.getStackTrace();
			super.sendJsonMessage(false, "注销失败请于管理员联系");
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
