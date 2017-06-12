package com.data.service.logger;

import javax.servlet.http.HttpServletRequest;

import com.data.model.LogType;

/**
 * @ClassName: ILogerService
 * @Description: TODO(日志接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月23日 下午1:58:48
 */
public interface ILoggerService {
	/**
	 * @Title: saveLog
	 * @Description: TODO(操作日志)
	 * @param @param type
	 * @param @param info
	 * @param @param handle
	 * @param @param request 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月23日 下午2:00:54
	 * @throws
	 */
	public void saveLog(LogType type, String info, String handle,
			HttpServletRequest request);
}
