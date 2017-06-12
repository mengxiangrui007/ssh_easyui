package com.data.model;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Item item;
	private String name;
	private Integer orderNo;
	private Integer type;
	private String description;
	private Integer score;
	@JSONField(serialize = true)
	private Set<QuestionOptions> questionOptionses = new HashSet<QuestionOptions>(
			0);

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Question(Integer id, Item item, String name, Integer orderNo,
			Integer type, String description, Integer score,
			Set questionOptionses) {
		this.id = id;
		this.item = item;
		this.name = name;
		this.orderNo = orderNo;
		this.type = type;
		this.description = description;
		this.score = score;
		this.questionOptionses = questionOptionses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Set<QuestionOptions> getQuestionOptionses() {
		return questionOptionses;
	}

	public void setQuestionOptionses(Set<QuestionOptions> questionOptionses) {
		this.questionOptionses = questionOptionses;
	}
}