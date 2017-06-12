package com.data.service.syslog;

import com.data.model.SysLog;
import com.data.tools.Page;

/**
 * @ClassName: ISysLogService
 * @Description: TODO(日志Service)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月24日 下午2:37:36
 */
public interface ISysLogService {

	/**
	 * @Title: querySysLog
	 * @Description: TODO(查询系统日志)
	 * @param @param sysLog
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月24日 下午2:44:37
	 * @throws
	 */
	public Page querySysLog(SysLog sysLog, int page, int rows);
}
