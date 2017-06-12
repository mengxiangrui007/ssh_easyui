package com.data.model;

/**
 * UserMenu entity. @author MyEclipse Persistence Tools
 */

public class UserMenu implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Users users;
	private Menu menu;

	// Constructors

	/** default constructor */
	public UserMenu() {
	}

	/** minimal constructor */
	public UserMenu(Long id) {
		this.id = id;
	}

	/** full constructor */
	public UserMenu(Long id, Users users, Menu menu) {
		this.id = id;
		this.users = users;
		this.menu = menu;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}