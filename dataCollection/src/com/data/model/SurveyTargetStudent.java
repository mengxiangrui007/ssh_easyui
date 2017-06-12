package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SurveyTargetStudent entity. @author MyEclipse Persistence Tools
 */

public class SurveyTargetStudent implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Survey survey;
	private Short userType;
	private Integer gread;
	private Integer professionalId;// PROFESSIONAL_ID
	private Integer class_;
	private Integer departmentId;
	private Set surveyStudents = new HashSet(0);
	private String departmentTeacher;

	// Constructors

	public SurveyTargetStudent(Integer id, Student student, Survey survey,
			Short userType, Integer gread, Integer professionalId,
			Integer class_, Integer departmentId, Set surveyStudents,
			String departmentTeacher) {
		super();
		this.id = id;
		this.student = student;
		this.survey = survey;
		this.userType = userType;
		this.gread = gread;
		this.professionalId = professionalId;
		this.class_ = class_;
		this.departmentId = departmentId;
		this.surveyStudents = surveyStudents;
		this.departmentTeacher = departmentTeacher;
	}

	/** default constructor */
	public SurveyTargetStudent() {
	}

	/** minimal constructor */
	public SurveyTargetStudent(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SurveyTargetStudent(Integer id, Student student, Survey survey,
			Short userType, Integer gread, Integer class_,
			Integer departmentId, Set surveyStudents) {
		this.id = id;
		this.student = student;
		this.survey = survey;
		this.userType = userType;
		this.gread = gread;
		this.class_ = class_;
		this.departmentId = departmentId;
		this.surveyStudents = surveyStudents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public Set getSurveyStudents() {
		return this.surveyStudents;
	}

	public void setSurveyStudents(Set surveyStudents) {
		this.surveyStudents = surveyStudents;
	}

	public String getDepartmentTeacher() {
		return departmentTeacher;
	}

	public void setDepartmentTeacher(String departmentTeacher) {
		this.departmentTeacher = departmentTeacher;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}
}