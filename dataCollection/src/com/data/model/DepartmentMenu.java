package com.data.model;

/**
 * DepartmentMenu entity. @author MyEclipse Persistence Tools
 */

public class DepartmentMenu implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Menu menu;
	private Department department;

	// Constructors

	/** default constructor */
	public DepartmentMenu() {
	}

	/** minimal constructor */
	public DepartmentMenu(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public DepartmentMenu(Integer id, Menu menu, Department department) {
		this.id = id;
		this.menu = menu;
		this.department = department;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof DepartmentMenu) {
			DepartmentMenu obj = (DepartmentMenu) o;
			if (this.menu.getId() == obj.getMenu().getId()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}