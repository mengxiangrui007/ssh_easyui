package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Professional entity. @author MyEclipse Persistence Tools
 */

public class Professional implements java.io.Serializable {

	// Fields

	private Integer id;
	private String professionalName;
	private Short professionLevel;
	private Short professionalSystem;
	private Integer pid;
	private Set students = new HashSet(0);
	private Set professionalDepartments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Professional() {
	}

	/** minimal constructor */
	public Professional(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Professional(Integer id, String professionalName,
			Short professionLevel, Short professionalSystem, Set students,
			Set professionalDepartments) {
		this.id = id;
		this.professionalName = professionalName;
		this.professionLevel = professionLevel;
		this.professionalSystem = professionalSystem;
		this.students = students;
		this.professionalDepartments = professionalDepartments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfessionalName() {
		return this.professionalName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public Short getProfessionLevel() {
		return this.professionLevel;
	}

	public void setProfessionLevel(Short professionLevel) {
		this.professionLevel = professionLevel;
	}

	public Short getProfessionalSystem() {
		return this.professionalSystem;
	}

	public void setProfessionalSystem(Short professionalSystem) {
		this.professionalSystem = professionalSystem;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	public Set getProfessionalDepartments() {
		return this.professionalDepartments;
	}

	public void setProfessionalDepartments(Set professionalDepartments) {
		this.professionalDepartments = professionalDepartments;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}