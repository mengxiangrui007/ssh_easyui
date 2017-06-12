package com.data.model;

/**
 * ProfessionalDepartment entity. @author MyEclipse Persistence Tools
 */

public class ProfessionalDepartment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Professional professional;
	private Department department;

	// Constructors

	/** default constructor */
	public ProfessionalDepartment() {
	}

	// Property accessors
	public Professional getProfessional() {
		return this.professional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}