package com.data.service.survey;

import java.util.List;

import com.data.model.Survey;
import com.data.model.Users;
import com.data.tools.Page;
import com.data.view.survey.vo.SurveyTargetBean;

/**
 * @ClassName: ISurveyService
 * @Description: TODO(调查业务处理接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月25日 下午3:39:32
 */
public interface ISurveyService {

	/**
	 * @Title: querySurvey
	 * @Description: TODO(查询调查信息)
	 * @param @param survey
	 * @param @param departmentIdList
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 下午4:21:26
	 * @throws
	 */
	public Page querySurvey(Survey survey, List<Integer> departmentIdList,
			int page, int rows) throws Exception;

	/**
	 * @Title: insertSurvey
	 * @Description: TODO(新增调查和调查目标)
	 * @param @param surveyTarget
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午12:34:53
	 * @throws
	 */
	public void insertSurvey(SurveyTargetBean surveyTarget, Users userInfo)
			throws Exception;

	/**
	 * @Title: queryUserIsHaveSurvey
	 * @Description: TODO(查询用户是否有调查)
	 * @param @param users
	 * @param @param departmentIdList
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午3:03:56
	 * @throws
	 */
	public Page queryUserIsHaveSurvey(Users users) throws Exception;

	/**
	 * @Title: queryUserSurvey
	 * @Description: TODO(查询用户调查)
	 * @param @param surveyId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Survey 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 下午6:43:27
	 * @throws
	 */
	public Survey queryUserSurvey(Integer surveyId) throws Exception;

	/**
	 * @Title: queryDepartmentChart
	 * @Description: TODO(查看部门报表)
	 * @param @param survey
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Survey> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月3日 上午12:33:29
	 * @throws
	 */
	public List<Survey> queryDepartmentChart(Survey survey) throws Exception;

}
