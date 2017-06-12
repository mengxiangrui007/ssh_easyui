package com.data.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Survey entity. @author MyEclipse Persistence Tools
 */

public class Survey implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer departmentId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Item item;
	private Short type;
	private Integer itemId;
	private Integer targetId;
	private String name;
	private Short isSurveyComplete;
	private Set surveyTargetTeachers = new HashSet(0);
	private Set surveyTargetStudents = new HashSet(0);
	private Set surveyTargetDepartments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Survey() {
	}

	/** minimal constructor */
	public Survey(Integer id) {
		this.id = id;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Survey(Integer id, Users users, Integer departmentId,
			Timestamp startTime, Timestamp endTime, Item item,
			Short isSurveyComplete, Set surveyTargetTeachers,
			Set surveyTargetStudents, Set surveyTargetDepartments) {
		super();
		this.id = id;
		this.users = users;
		this.departmentId = departmentId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.item = item;
		this.isSurveyComplete = isSurveyComplete;
		this.surveyTargetTeachers = surveyTargetTeachers;
		this.surveyTargetStudents = surveyTargetStudents;
		this.surveyTargetDepartments = surveyTargetDepartments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Short getIsSurveyComplete() {
		return isSurveyComplete;
	}

	public void setIsSurveyComplete(Short isSurveyComplete) {
		this.isSurveyComplete = isSurveyComplete;
	}

	public Set getSurveyTargetTeachers() {
		return surveyTargetTeachers;
	}

	public void setSurveyTargetTeachers(Set surveyTargetTeachers) {
		this.surveyTargetTeachers = surveyTargetTeachers;
	}

	public Set getSurveyTargetStudents() {
		return surveyTargetStudents;
	}

	public void setSurveyTargetStudents(Set surveyTargetStudents) {
		this.surveyTargetStudents = surveyTargetStudents;
	}

	public Set getSurveyTargetDepartments() {
		return surveyTargetDepartments;
	}

	public void setSurveyTargetDepartments(Set surveyTargetDepartments) {
		this.surveyTargetDepartments = surveyTargetDepartments;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
}