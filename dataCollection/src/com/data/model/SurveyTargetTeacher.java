package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SurveyTargetTeacher entity. @author MyEclipse Persistence Tools
 */

public class SurveyTargetTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private Survey survey;
	private Teacher teacher;
	private Short userType;
	private Integer gread;
	private Integer professionalId;// PROFESSIONAL_ID
	private Integer class_;
	private Integer departmentId;
	private Set surveyTeachers = new HashSet(0);
	private String departmentTeacher;

	// Constructors

	public SurveyTargetTeacher(Integer id, Survey survey, Teacher teacher,
			Short userType, Integer gread, Integer professionalId,
			Integer class_, Integer departmentId, Set surveyTeachers,
			String departmentTeacher) {
		super();
		this.id = id;
		this.survey = survey;
		this.teacher = teacher;
		this.userType = userType;
		this.gread = gread;
		this.professionalId = professionalId;
		this.class_ = class_;
		this.departmentId = departmentId;
		this.surveyTeachers = surveyTeachers;
		this.departmentTeacher = departmentTeacher;
	}

	/** default constructor */
	public SurveyTargetTeacher() {
	}

	/** minimal constructor */
	public SurveyTargetTeacher(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SurveyTargetTeacher(Integer id, Survey survey, Teacher teacher,
			Short userType, Integer gread, Integer class_,
			Integer departmentId, Set surveyTeachers) {
		this.id = id;
		this.survey = survey;
		this.teacher = teacher;
		this.userType = userType;
		this.gread = gread;
		this.class_ = class_;
		this.departmentId = departmentId;
		this.surveyTeachers = surveyTeachers;
	}

	// Property accessors

	public String getDepartmentTeacher() {
		return departmentTeacher;
	}

	public void setDepartmentTeacher(String departmentTeacher) {
		this.departmentTeacher = departmentTeacher;
	}

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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public Set getSurveyTeachers() {
		return this.surveyTeachers;
	}

	public void setSurveyTeachers(Set surveyTeachers) {
		this.surveyTeachers = surveyTeachers;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}
}