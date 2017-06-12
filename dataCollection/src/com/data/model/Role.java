package com.data.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private String description;
	private Timestamp createdDate;
	private Short type;
	@JSONField(serialize = false)
	private Set userses = new HashSet(0);
	@JSONField(serialize = false)
	private Set roleMenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	public Role(Integer id) {
		super();
		this.id = id;
	}

	/** minimal constructor */
	public Role(Integer id, String name, Timestamp createdDate, Short type) {
		this.id = id;
		this.name = name;
		this.createdDate = createdDate;
		this.type = type;
	}

	/** full constructor */
	public Role(Integer id, Integer pid, String name, String description,
			Timestamp createdDate, Short type, Set userses, Set roleMenus) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.type = type;
		this.userses = userses;
		this.roleMenus = roleMenus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getRoleMenus() {
		return this.roleMenus;
	}

	public void setRoleMenus(Set roleMenus) {
		this.roleMenus = roleMenus;
	}

}