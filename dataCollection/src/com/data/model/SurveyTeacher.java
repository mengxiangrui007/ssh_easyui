package com.data.model;

import java.sql.Timestamp;

/**
 * SurveyTeacher entity. @author MyEclipse Persistence Tools
 */

public class SurveyTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private SurveyTargetTeacher surveyTargetTeacher;
	private Integer userId;
	private Timestamp surveyTime;
	private Short accountStatus;
	private Integer departmentId;

	// Constructors

	/** default constructor */
	public SurveyTeacher() {
	}

	/** minimal constructor */
	public SurveyTeacher(Integer id, Timestamp surveyTime) {
		this.id = id;
		this.surveyTime = surveyTime;
	}

	/** full constructor */
	public SurveyTeacher(Integer id, SurveyTargetTeacher surveyTargetTeacher,
			Integer userId, Timestamp surveyTime, Short accountStatus,
			Integer departmentId) {
		this.id = id;
		this.surveyTargetTeacher = surveyTargetTeacher;
		this.userId = userId;
		this.surveyTime = surveyTime;
		this.accountStatus = accountStatus;
		this.departmentId = departmentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SurveyTargetTeacher getSurveyTargetTeacher() {
		return this.surveyTargetTeacher;
	}

	public void setSurveyTargetTeacher(SurveyTargetTeacher surveyTargetTeacher) {
		this.surveyTargetTeacher = surveyTargetTeacher;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getSurveyTime() {
		return this.surveyTime;
	}

	public void setSurveyTime(Timestamp surveyTime) {
		this.surveyTime = surveyTime;
	}

	public Short getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Short accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}