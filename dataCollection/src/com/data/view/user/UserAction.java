package com.data.view.user;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.LogType;
import com.data.model.Role;
import com.data.model.Users;
import com.data.service.user.IUserService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.view.base.BaseAction;

/**
 * @ClassName: UserAction
 * @Description: TODO(用户信息Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午4:02:47
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private IUserService iUserService;

	/**
	 * @Fields user : TODO(用一句话描述这个变量表示什么)
	 */
	private Users user = getUsersModel();

	/**
	 * @throws IOException
	 * @Title: queryUser
	 * @Description: TODO(查询用户信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月26日 上午10:23:31
	 * @throws
	 */
	public String queryUser() throws Exception {
		try {
			pager = iUserService.queryUser(user, super.getPage(),
					super.getRows());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Users.class, new String[] { "id", "account",
									"fullname", "sexy", "mobilephone",
									"accountStatus", "accountLocked",
									"accountCreated", "email", "role" });
							put(Role.class, new String[] { "name" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (IOException e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询用户信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询用户信息失败");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throwsException
	 * @Title: updateUserAccountLocked
	 * @Description: TODO(修改用户的锁定状态)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月12日 下午3:34:28
	 * @throws
	 */
	public String updateUserAccountLocked() throws Exception {
		try {
			user.setAccountLocked((short) (user.getAccountLocked() == 1 ? 0 : 1));
			flag = iUserService.updateUserAccountLocked(user);
			if (flag) {
				super.sendJsonMessage(true, user.getAccountLocked() + "");
			} else {
				super.sendJsonMessage(false, "修改锁定状态失败");
			}
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"修改用户状态失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "修改锁定状态失败");
			e.getStackTrace();
		}
		return null;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * @Title: getUsersModel
	 * @Description: TODO(单例模式创建model)
	 * @param @return 设定文件
	 * @return Users 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午1:46:25
	 * @throws
	 */
	public Users getUsersModel() {
		if (null == user) {
			user = new Users();
		}
		return user;
	}
}
