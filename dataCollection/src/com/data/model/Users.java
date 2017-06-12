package com.data.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Role role;
	private String account;
	private String fullname;
	private Short sexy;
	private String mobilephone;
	private String password;
	private String description;
	private Short accountLocked;
	private Timestamp accountCreated;
	private List<Integer> departmentList;// 拥有的院系ID
	private Integer departmentID; // 所在院系ID 空为没有院系
	private List<Integer> menuID;// 用户所拥有的菜单
	private String email;
	private Set teachers = new HashSet(0);
	private Set students = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	public Users(Integer id, Role role, String account, String fullname,
			Short sexy, String mobilephone, String password,
			String description, Short accountLocked, Timestamp accountCreated,
			List<Integer> departmentList, Integer departmentID,
			List<Integer> menuID, String email, Set teachers, Set students) {
		super();
		this.id = id;
		this.role = role;
		this.account = account;
		this.fullname = fullname;
		this.sexy = sexy;
		this.mobilephone = mobilephone;
		this.password = password;
		this.description = description;
		this.accountLocked = accountLocked;
		this.accountCreated = accountCreated;
		this.departmentList = departmentList;
		this.departmentID = departmentID;
		this.menuID = menuID;
		this.email = email;
		this.teachers = teachers;
		this.students = students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Short getSexy() {
		return sexy;
	}

	public void setSexy(Short sexy) {
		this.sexy = sexy;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Short accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Timestamp getAccountCreated() {
		return accountCreated;
	}

	public void setAccountCreated(Timestamp accountCreated) {
		this.accountCreated = accountCreated;
	}

	public List<Integer> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Integer> departmentList) {
		this.departmentList = departmentList;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public List<Integer> getMenuID() {
		return menuID;
	}

	public void setMenuID(List<Integer> menuID) {
		this.menuID = menuID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getTeachers() {
		return teachers;
	}

	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}

	public Set getStudents() {
		return students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

}