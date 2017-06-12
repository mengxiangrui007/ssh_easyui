package com.data.service.score.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.score.impl.ScoreDaoImpl;
import com.data.model.Question;
import com.data.model.Score;
import com.data.model.SurveyDeparment;
import com.data.model.SurveyStudent;
import com.data.model.SurveyTargetDepartment;
import com.data.model.SurveyTargetStudent;
import com.data.model.SurveyTargetTeacher;
import com.data.model.SurveyTeacher;
import com.data.model.Users;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.score.IScoreService;
import com.data.tools.DateUtil;
import com.data.tools.WebUtil;

@Service("iScoreService")
public class ScoreServiceImpl extends BaseSericeImpl<Score, Integer> implements
		IScoreService {
	@Autowired(required = true)
	private ScoreDaoImpl scoreDaoImpl;

	@Resource(name = "scoreDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Score, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public void insertScore(Score score, Users users) {
		List<Score> scoreList = score.getScoreList();
		for (Score scoreTmp : scoreList) {
			scoreTmp.setId(Integer.parseInt(WebUtil.getPK() + ""));
			scoreTmp.setQuestion(new Question(scoreTmp.getQuestionId()));
			scoreTmp.setDepartmentId(users.getDepartmentID());
			iBaseDao.save(scoreTmp);
		}
		if (score.getType() == 1) {
			SurveyStudent surveyStudent = new SurveyStudent();
			surveyStudent.setId(Integer.parseInt(WebUtil.getPK() + ""));
			surveyStudent.setDepartmentId(users.getDepartmentID());
			surveyStudent.setSurveyTime(DateUtil.getTimestamp());
			surveyStudent.setUserId(users.getId());
			surveyStudent.setSurveyTargetStudent(new SurveyTargetStudent(score
					.getTargetId()));
			scoreDaoImpl.insertSurveyStudent(surveyStudent);
		} else if (score.getType() == 2) {
			SurveyTeacher surveyTeacher = new SurveyTeacher();
			surveyTeacher.setId(Integer.parseInt(WebUtil.getPK() + ""));
			surveyTeacher.setDepartmentId(users.getDepartmentID());
			surveyTeacher.setSurveyTime(DateUtil.getTimestamp());
			surveyTeacher.setUserId(users.getId());
			surveyTeacher.setSurveyTargetTeacher(new SurveyTargetTeacher(score
					.getTargetId()));
			scoreDaoImpl.insertSurveyTeacher(surveyTeacher);
		} else if (score.getType() == 3) {
			SurveyDeparment surveyDeparment = new SurveyDeparment();
			surveyDeparment.setId(Integer.parseInt(WebUtil.getPK() + ""));
			surveyDeparment.setDepartmentId(users.getDepartmentID());
			surveyDeparment.setSurveyTime(DateUtil.getTimestamp());
			surveyDeparment.setUserId(users.getId());
			surveyDeparment
					.setSurveyTargetDepartment(new SurveyTargetDepartment(score
							.getTargetId()));
			scoreDaoImpl.insertSurveyDeparment(surveyDeparment);
		}
	}
}
