package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Options entity. @author MyEclipse Persistence Tools
 */

public class Options implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer orderNo;
	private String name;
	private Integer score;
	private Set scores = new HashSet(0);

	// Constructors

	/** default constructor */
	public Options() {
	}

	public Options(Integer orderNo, String name, Integer score) {
		super();
		this.orderNo = orderNo;
		this.name = name;
		this.score = score;
	}

	/** minimal constructor */
	public Options(Integer id) {
		this.id = id;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

}