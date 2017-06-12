package com.data.share.interceptor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.data.model.LogType;
import com.data.service.logger.ILoggerService;
import com.data.view.base.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoggerInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields log : log4j句柄
	 */
	protected Logger log = Logger.getLogger(getClass());
	@Autowired(required = true)
	protected ILoggerService iLogerService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String method = invocation.getProxy().getMethod();
		String className = invocation.getProxy().getConfig().getClassName();
		String resourceName = className + "." + method;
		BaseAction action = (BaseAction) invocation.getAction();
		action.setResourceName(resourceName);
		// 如果有增删改操作，记录日志
		String[] logMethod = { "insert", "update", "delete" };
		List<String> logMethods = Arrays.asList(logMethod);
		String result = null;
		try {
			Iterator<String> iterator = logMethods.iterator();
			while (iterator.hasNext()) {
				if (method.contains(iterator.next())) {
					iLogerService.saveLog(LogType.OPERATION, "操作" + method,
							resourceName, ServletActionContext.getRequest());
					break;
				}
			}
			result = invocation.invoke();
		} catch (Exception e) {
			log.error("执行" + resourceName + "时发生异常：", e);
			throw e;
		}
		return result;
	}

}
