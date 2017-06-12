package com.data.view.survey.vo;

import java.sql.Timestamp;
import java.util.List;

import com.data.model.Item;
import com.data.model.Survey;

/**
 * @ClassName: SurveyTargetBean
 * @Description: TODO(调查目标)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月29日 上午11:12:23
 */
public class SurveyTargetBean {

	// Fields

	private Integer id;
	private Survey survey;
	private Item item;
	private Short userType;
	private Integer gread;
	private Integer professionalId;// PROFESSIONAL_ID
	private Integer class_;
	private Integer departmentId;
	private Integer targetUserId;// 如果为教师就为教师Id 如果为学生就为学生Id
	private Timestamp startTime;
	private Timestamp endTime;
	private Short type;
	private List<SurveyTargetBean> surveyTargetList;
	private String departmentTeacher;// 参与教师的部门

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentTeacher() {
		return departmentTeacher;
	}

	public void setDepartmentTeacher(String departmentTeacher) {
		this.departmentTeacher = departmentTeacher;
	}

	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Short getUserType() {
		return this.userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public Integer getGread() {
		return this.gread;
	}

	public void setGread(Integer gread) {
		this.gread = gread;
	}

	public Integer getClass_() {
		return this.class_;
	}

	public void setClass_(Integer class_) {
		this.class_ = class_;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(Integer targetUserId) {
		this.targetUserId = targetUserId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public List<SurveyTargetBean> getSurveyTargetList() {
		return surveyTargetList;
	}

	public void setSurveyTargetList(List<SurveyTargetBean> surveyTargetList) {
		this.surveyTargetList = surveyTargetList;
	}
}
