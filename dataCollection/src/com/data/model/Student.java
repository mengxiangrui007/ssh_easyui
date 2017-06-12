package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Professional professional;
	private Department department;
	private String studentNo;
	private Integer gread;
	private Integer class_;
	private Short type;
	private Integer professionalId;
	private Integer departmentId;
	private Set surveyTargetStudents = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Student(Integer id, Users users, Department department,
			String studentNo, Integer gread, Integer class_, Short type,
			Set surveyTargetStudents) {
		this.id = id;
		this.users = users;
		this.department = department;
		this.studentNo = studentNo;
		this.gread = gread;
		this.class_ = class_;
		this.type = type;
		this.surveyTargetStudents = surveyTargetStudents;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
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

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Set getSurveyTargetStudents() {
		return this.surveyTargetStudents;
	}

	public void setSurveyTargetStudents(Set surveyTargetStudents) {
		this.surveyTargetStudents = surveyTargetStudents;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
}