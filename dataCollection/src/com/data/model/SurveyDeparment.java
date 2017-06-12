package com.data.model;

import java.sql.Timestamp;

/**
 * SurveyDeparment entity. @author MyEclipse Persistence Tools
 */

public class SurveyDeparment implements java.io.Serializable {

	// Fields

	private Integer id;
	private SurveyTargetDepartment surveyTargetDepartment;
	private Timestamp surveyTime;
	private Short accountStatus;
	private Integer userId;
	private Integer departmentId;

	// Constructors

	/** default constructor */
	public SurveyDeparment() {
	}

	/** minimal constructor */
	public SurveyDeparment(Integer id, Timestamp surveyTime) {
		this.id = id;
		this.surveyTime = surveyTime;
	}

	/** full constructor */
	public SurveyDeparment(Integer id,
			SurveyTargetDepartment surveyTargetDepartment,
			Timestamp surveyTime, Short accountStatus, Integer userId,
			Integer departmentId) {
		this.id = id;
		this.surveyTargetDepartment = surveyTargetDepartment;
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

	public SurveyTargetDepartment getSurveyTargetDepartment() {
		return this.surveyTargetDepartment;
	}

	public void setSurveyTargetDepartment(
			SurveyTargetDepartment surveyTargetDepartment) {
		this.surveyTargetDepartment = surveyTargetDepartment;
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