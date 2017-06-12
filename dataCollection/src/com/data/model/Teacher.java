package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Department department;
	private String teachNo;
	private Short type;
	private Set surveyTargetTeachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Teacher(Integer id, Users users, Department department,
			String teachNo, Short type, Set surveyTargetTeachers) {
		this.id = id;
		this.users = users;
		this.department = department;
		this.teachNo = teachNo;
		this.type = type;
		this.surveyTargetTeachers = surveyTargetTeachers;
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

	public String getTeachNo() {
		return this.teachNo;
	}

	public void setTeachNo(String teachNo) {
		this.teachNo = teachNo;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Set getSurveyTargetTeachers() {
		return this.surveyTargetTeachers;
	}

	public void setSurveyTargetTeachers(Set surveyTargetTeachers) {
		this.surveyTargetTeachers = surveyTargetTeachers;
	}

}