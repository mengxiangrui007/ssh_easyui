package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements Comparable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Integer pid;
	private String href;
	private String cls;
	private String description;
	private Short orderId;
	private Integer departmentId;
	private Set departmentMenus = new HashSet(0);
	private Set roleMenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public Menu(Integer id) {
		super();
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCls() {
		return this.cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Short orderId) {
		this.orderId = orderId;
	}

	public Set getDepartmentMenus() {
		return this.departmentMenus;
	}

	public void setDepartmentMenus(Set departmentMenus) {
		this.departmentMenus = departmentMenus;
	}

	public Set getRoleMenus() {
		return this.roleMenus;
	}

	public void setRoleMenus(Set roleMenus) {
		this.roleMenus = roleMenus;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Menu) {
			Menu s = (Menu) o;
			if (this.orderId.intValue() > s.orderId.intValue()) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}
}