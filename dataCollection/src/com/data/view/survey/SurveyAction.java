package com.data.view.survey;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.Item;
import com.data.model.LogType;
import com.data.model.Options;
import com.data.model.Question;
import com.data.model.QuestionOptions;
import com.data.model.Survey;
import com.data.model.Users;
import com.data.service.survey.ISurveyService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.view.base.BaseAction;
import com.data.view.survey.vo.SurveyTargetBean;

/**
 * @ClassName: SurveyAction
 * @Description: TODO(调查Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月20日 下午4:40:03
 */
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Survey survey = createModel();
	private SurveyTargetBean surveyTarget = createSurveyTarget();
	/**
	 * @Fields iSurveyService : TODO(注入)
	 */
	@Autowired(required = true)
	private ISurveyService iSurveyService;
	/**
	 * @Fields jsonData : TODO(JSON的数据)
	 */
	private String jsonData = "";
	private Integer surveyId;

	/**
	 * @throws IOException
	 * @Title: querySurvey
	 * @Description: TODO(调查)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 下午3:48:09
	 * @throws
	 */
	public String querySurvey() throws IOException {
		try {
			pager = iSurveyService.querySurvey(survey,
					super.getDepartmentIdList(), super.getPage(),
					super.getRows());

			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Survey.class, new String[] { "id", "users",
									"departmentId", "startTime", "endTime",
									"item", "isSurveyComplete", "type" });
							put(Item.class, new String[] { "id", "name" });
							put(Users.class, new String[] { "id", "fullname" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:MM";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询调查信息失败");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryUserItem
	 * @Description: TODO(查看调查项目)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午11:45:35
	 * @throws
	 */
	public String queryUserSurvey() throws IOException {
		try {
			survey = iSurveyService.queryUserSurvey(surveyId);
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Survey.class, new String[] { "id", "item",
									"type" });
							put(Item.class, new String[] { "id", "name",
									"questions" });
							put(QuestionOptions.class,
									new String[] { "options" });
							put(Question.class, new String[] { "id", "name",
									"orderNo", "type", "score",
									"questionOptionses" });
							put(Options.class, new String[] { "id", "orderNo",
									"name", "score" });
						}
					});
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(survey, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查看调查项目失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());

			super.sendJsonMessage(false, "查看调查项目失败！");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryUserIsHaveSurvey
	 * @Description: TODO(查询用户是否有调查)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 下午2:58:42
	 * @throws
	 */
	public String queryUserIsHaveSurvey() throws IOException {
		try {
			pager = iSurveyService.queryUserIsHaveSurvey(super.userInfo);

			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Survey.class, new String[] { "id", "users",
									"departmentId", "startTime", "endTime",
									"item", "itemId", "isSurveyComplete",
									"type", "name", "targetId" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:MM";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询调查信息失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: insertSurvey
	 * @Description: TODO(新镇调查)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月29日 上午11:48:03
	 * @throws
	 */
	public String insertSurvey() throws IOException {
		try {
			List<SurveyTargetBean> surveyTargetList = JSON.parseArray(jsonData,
					SurveyTargetBean.class);
			surveyTarget.setSurveyTargetList(surveyTargetList);
			iSurveyService.insertSurvey(surveyTarget, userInfo);
			super.sendJsonMessage(true, "新镇调查成功");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "新镇调查失败请于管理员联系！");
			e.printStackTrace();

		}

		return null;
	}

	/**
	 * @Title: queryDepartmentChart
	 * @Description: TODO(部门报表)
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月3日 上午12:30:22
	 * @throws
	 */
	public String queryDepartmentChart() throws IOException {
		try {
			List<Survey> surveys = iSurveyService.queryDepartmentChart(survey);
			super.sendJsonMessage(true, "新镇调查成功");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "新镇调查失败请于管理员联系！");
			e.printStackTrace();

		}
		return null;
	}

	private SurveyTargetBean createSurveyTarget() {
		if (surveyTarget == null) {
			surveyTarget = new SurveyTargetBean();
			surveyTarget.setItem(new Item());
			surveyTarget.setSurvey(new Survey());
		}
		return surveyTarget;
	}

	private Survey createModel() {
		if (null == survey) {
			survey = new Survey();
			survey.setItem(new Item());
		}
		return survey;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public SurveyTargetBean getSurveyTarget() {
		return surveyTarget;
	}

	public void setSurveyTarget(SurveyTargetBean surveyTarget) {
		this.surveyTarget = surveyTarget;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
}
