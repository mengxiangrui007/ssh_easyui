package com.data.share.interceptor;

import org.apache.struts2.ServletActionContext;

import com.data.model.Users;
import com.data.share.propertie.BaseConfigBean;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName: LoginInterceptor
 * @Description: TODO(登陆拦截器)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午7:07:37
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		Users userInfo = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute(BaseConfigBean.ROOT_WEB_SESSION_USER);
		if (null != userInfo) {
			result = invocation.invoke();
		} else {
			return "login";
		}
		return result;
	}
}
