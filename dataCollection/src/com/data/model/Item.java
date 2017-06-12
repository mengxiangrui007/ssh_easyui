package com.data.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Department department;
	private String name;
	private Timestamp createTime;
	private String description;
	private String itemTitle;
	private Short itemType;
	private String itemQuestion;
	private Short isHaveQuestion;
	@JSONField(serialize = true)
	private Set<Question> questions = new HashSet<Question>(0);

	// Constructors

	/** default constructor */
	public Item() {
	}

	/** minimal constructor */
	public Item(Integer id) {
		this.id = id;
	}

	public Item(Integer id, Users users, Department department, String name,
			Timestamp createTime, String description, String itemTitle,
			Short itemType, String itemQuestion, Short isHaveQuestion,
			Set<Question> questions) {
		super();
		this.id = id;
		this.users = users;
		this.department = department;
		this.name = name;
		this.createTime = createTime;
		this.description = description;
		this.itemTitle = itemTitle;
		this.itemType = itemType;
		this.itemQuestion = itemQuestion;
		this.isHaveQuestion = isHaveQuestion;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Short getItemType() {
		return itemType;
	}

	public void setItemType(Short itemType) {
		this.itemType = itemType;
	}

	public String getItemQuestion() {
		return itemQuestion;
	}

	public void setItemQuestion(String itemQuestion) {
		this.itemQuestion = itemQuestion;
	}

	public Short getIsHaveQuestion() {
		return isHaveQuestion;
	}

	public void setIsHaveQuestion(Short isHaveQuestion) {
		this.isHaveQuestion = isHaveQuestion;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
}