package com.data.service.logger.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.model.LogType;
import com.data.model.SysLog;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.logger.ILoggerService;
import com.data.share.propertie.BaseConfigHelper;
import com.data.tools.DateUtil;
import com.data.tools.WebUtil;

@Service("iLoggerService")
public class LoggerServiceImpl extends BaseSericeImpl<SysLog, Integer>
		implements ILoggerService {
	/**
	 * 如果保存日志也报错 保存到Log中
	 * 
	 * @Fields log : log4j句柄
	 */
	protected Logger log = Logger.getLogger(getClass());

	@Resource(name = "logerDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<SysLog, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public void saveLog(LogType type, String info, String handle,
			HttpServletRequest request) {
		try {
			SysLog sysLog = new SysLog();
			sysLog.setId(Integer.parseInt(WebUtil.getRanName()));
			sysLog.setUserId(BaseConfigHelper.getUser(request).getId());
			sysLog.setType(Short.parseShort(type.getValue()));
			sysLog.setOperationTime(DateUtil.getTimestamp());
			sysLog.setContext(info);
			sysLog.setOperation(handle);
			sysLog.setIpAddress(request.getRemoteAddr());
			iBaseDao.save(sysLog);
		} catch (Exception e) {
			log.error("保存日志时发生错误saveLog时发生异常：", e);
		}
	}
}
