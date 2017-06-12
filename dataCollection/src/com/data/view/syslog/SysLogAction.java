package com.data.view.syslog;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.LogType;
import com.data.model.SysLog;
import com.data.service.syslog.ISysLogService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.view.base.BaseAction;

/**
 * @ClassName: SysLogAction
 * @Description: TODO(日志Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月24日 下午2:31:06
 */
@Controller
@Scope("prototype")
public class SysLogAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields iSysLogService : TODO(注入)
	 */
	@Autowired(required = true)
	private ISysLogService iSysLogService;

	/**
	 * @Fields sysLog : TODO(model)
	 */
	private SysLog sysLog = createModel();

	/**
	 * @Title: querySysLog
	 * @Description: TODO(查询系统日志)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月24日 下午2:40:00
	 * @throws
	 */
	public String querySysLog() throws Exception {
		try {
			pager = iSysLogService.querySysLog(sysLog, super.getPage(),
					super.getRows());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(SysLog.class, new String[] { "id", "type",
									"operation", "userId", "operationTime",
									"context", "ipAddress" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
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
	 * @Title: createModel
	 * @Description: TODO(单例模式创建Model)
	 * @param @return 设定文件
	 * @return SysLog 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月24日 下午2:41:03
	 * @throws
	 */
	private SysLog createModel() {
		if (null == sysLog) {
			sysLog = new SysLog();
		}
		return sysLog;
	}

	public SysLog getSysLog() {
		return sysLog;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}
}
