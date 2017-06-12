package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SurveyTargetDepartment entity. @author MyEclipse Persistence Tools
 */

public class SurveyTargetDepartment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Survey survey;
	private Department department;
	private Short userType;
	private Integer gread;
	private Integer professionalId;// PROFESSIONAL_ID
	private Integer class_;
	private Set surveyDeparments = new HashSet(0);
	private String departmentTeacher;

	// Constructors

	/** default constructor */
	public SurveyTargetDepartment() {
	}

	/** minimal constructor */
	public SurveyTargetDepartment(Integer id) {
		this.id = id;
	}

	public SurveyTargetDepartment(Integer id, Survey survey,
			Department department, Short userType, Integer gread,
			Integer professionalId, Integer class_, Set surveyDeparments,
			String departmentTeacher) {
		super();
		this.id = id;
		this.survey = survey;
		this.department = department;
		this.userType = userType;
		this.gread = gread;
		this.professionalId = professionalId;
		this.class_ = class_;
		this.surveyDeparments = surveyDeparments;
		this.departmentTeacher = departmentTeacher;
	}

	public SurveyTargetDepartment(Integer id, Survey survey,
			Department department, Short userType, Integer gread,
			Integer class_, Set surveyDeparments) {
		super();
		this.id = id;
		this.survey = survey;
		this.department = department;
		this.userType = userType;
		this.gread = gread;
		this.class_ = class_;
		this.surveyDeparments = surveyDeparments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return this.survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public String getDepartmentTeacher() {
		return departmentTeacher;
	}

	public void setDepartmentTeacher(String departmentTeacher) {
		this.departmentTeacher = departmentTeacher;
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

	public Set getSurveyDeparments() {
		return this.surveyDeparments;
	}

	public void setSurveyDeparments(Set surveyDeparments) {
		this.surveyDeparments = surveyDeparments;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}
}