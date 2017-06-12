package com.data.model;

/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RoleMenu implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Menu menu;
	private Role role;
	private Integer departmentId;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** minimal constructor */
	public RoleMenu(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public RoleMenu(Integer id, Menu menu, Role role, Integer departmentId) {
		this.id = id;
		this.menu = menu;
		this.role = role;
		this.departmentId = departmentId;
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @Title: equals
	 * @Description: TODO(重写对象equals)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof RoleMenu) {
			RoleMenu obj = (RoleMenu) o;
			if (this.menu.getId() == obj.getMenu().getId()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}