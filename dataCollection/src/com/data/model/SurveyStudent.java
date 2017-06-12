package com.data.model;

import java.sql.Timestamp;

/**
 * SurveyStudent entity. @author MyEclipse Persistence Tools
 */

public class SurveyStudent implements java.io.Serializable {

	// Fields

	private Integer id;
	private SurveyTargetStudent surveyTargetStudent;
	private Timestamp surveyTime;
	private Short accountStatus;
	private Integer userId;
	private Integer departmentId;

	// Constructors

	/** default constructor */
	public SurveyStudent() {
	}

	/** minimal constructor */
	public SurveyStudent(Integer id, Timestamp surveyTime) {
		this.id = id;
		this.surveyTime = surveyTime;
	}

	/** full constructor */
	public SurveyStudent(Integer id, SurveyTargetStudent surveyTargetStudent,
			Timestamp surveyTime, Short accountStatus, Integer userId,
			Integer departmentId) {
		this.id = id;
		this.surveyTargetStudent = surveyTargetStudent;
		this.surveyTime = surveyTime;
		this.accountStatus = accountStatus;
		this.userId = userId;
		this.departmentId = departmentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SurveyTargetStudent getSurveyTargetStudent() {
		return this.surveyTargetStudent;
	}

	public void setSurveyTargetStudent(SurveyTargetStudent surveyTargetStudent) {
		this.surveyTargetStudent = surveyTargetStudent;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}