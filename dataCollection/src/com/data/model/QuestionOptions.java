package com.data.model;

/**
 * QuestionOptions entity. @author MyEclipse Persistence Tools
 */

public class QuestionOptions implements java.io.Serializable {

	// Fields

	private Integer id;
	private Question question;
	private Options options;
	private Integer orderNo;
	private String name;
	private Integer score;

	// Constructors

	/** default constructor */
	public QuestionOptions() {
	}

	/** minimal constructor */
	public QuestionOptions(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public QuestionOptions(Integer id, Question question, Options options) {
		this.id = id;
		this.question = question;
		this.options = options;
	}

	public QuestionOptions(Question question, Options options) {
		super();
		this.question = question;
		this.options = options;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Options getOptions() {
		return this.options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}