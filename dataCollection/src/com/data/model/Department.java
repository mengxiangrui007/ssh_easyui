package com.data.model;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String type;
	private Integer pid;
	private String description;
	private String loaderName; // 后加字段
	@JSONField(serialize = false)
	private Set students = new HashSet(0);
	@JSONField(serialize = false)
	private Set departmentMenus = new HashSet(0);
	@JSONField(serialize = false)
	private Set teachers = new HashSet(0);
	@JSONField(serialize = false)
	private Set surveyTargetDepartments = new HashSet(0);
	private Set<Professional> professionalDepartments = new HashSet<Professional>(
			0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	public Department(Integer id) {
		super();
		this.id = id;
	}

	/** minimal constructor */
	public Department(Integer id, String name, Integer pid) {
		this.id = id;
		this.name = name;
		this.pid = pid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	public Set getDepartmentMenus() {
		return this.departmentMenus;
	}

	public void setDepartmentMenus(Set departmentMenus) {
		this.departmentMenus = departmentMenus;
	}

	public Set getTeachers() {
		return this.teachers;
	}

	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}

	public Set getSurveyTargetDepartments() {
		return surveyTargetDepartments;
	}

	public void setSurveyTargetDepartments(Set surveyTargetDepartments) {
		this.surveyTargetDepartments = surveyTargetDepartments;
	}

	public String getLoaderName() {
		return loaderName;
	}

	public void setLoaderName(String loaderName) {
		this.loaderName = loaderName;
	}

	public Set<Professional> getProfessionalDepartments() {
		return professionalDepartments;
	}

	public void setProfessionalDepartments(
			Set<Professional> professionalDepartments) {
		this.professionalDepartments = professionalDepartments;
	}
}