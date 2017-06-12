package com.data.service.syslog.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.syslog.impl.SysLogDaoImpl;
import com.data.model.SysLog;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.syslog.ISysLogService;
import com.data.tools.Page;

@Service("iSysLogService")
public class SysLogServiceImpl extends BaseSericeImpl<SysLog, Integer>
		implements ISysLogService {

	@Autowired(required = true)
	private SysLogDaoImpl sysLogDaoImpl;
	/**
	 * @Fields sysLogList : TODO(系统日志)
	 */
	private List<SysLog> sysLogList = null;

	@Resource(name = "sysLogDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<SysLog, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page querySysLog(SysLog sysLog, int page, int rows) {
		checkSysLogQueryCondition(sysLog);
		sum = iBaseDao.countByProperties(propertyList.toArray(propertys),
				valuesList.toArray());
		pager = new Page(sum, page, rows);
		sysLogList = iBaseDao.getByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), pager.getCurrentPage(),
				pager.getPageSize());
		pager.setRows(sysLogList);
		return pager;
	}

	private void checkSysLogQueryCondition(SysLog sysLog) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (sysLog.getType() != null && sysLog.getType() != 0) {
			propertyList.add("type");
			valuesList.add(sysLog.getType());
		}
	}
}
