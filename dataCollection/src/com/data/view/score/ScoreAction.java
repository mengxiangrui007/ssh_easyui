package com.data.view.score;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.data.model.LogType;
import com.data.model.Question;
import com.data.model.Score;
import com.data.service.score.IScoreService;
import com.data.view.base.BaseAction;

/**
 * @ClassName: ScoreAction
 * @Description: TODO(打分)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年6月2日 下午9:03:23
 */
@Controller
@Scope("prototype")
public class ScoreAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IScoreService iScoreService;
	private String jsonData = "";
	private Score score = createModel();
	private Integer targetId;
	private short surveyType;

	public String insertScore() throws IOException {
		try {
			List<Score> scoreList = JSON.parseArray(jsonData, Score.class);
			score.setScoreList(scoreList);
			score.setType(surveyType);
			score.setTargetId(targetId);
			iScoreService.insertScore(score, super.userInfo);
			super.sendJsonMessage(true, "填写调查成功，谢谢您的参与！");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "填写调查失败，请于管理员联系！");
			e.printStackTrace();
		}

		return null;

	}

	private Score createModel() {
		if (score == null) {
			score = new Score();
			score.setQuestion(new Question());
		}
		return score;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public short getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(short surveyType) {
		this.surveyType = surveyType;
	}
}
