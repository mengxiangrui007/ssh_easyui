package com.data.model;

import java.sql.Timestamp;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Short type;
	private String operation;
	private Integer userId;
	private Timestamp operationTime;
	private String context;
	private Integer enterId;
	private String ipAddress;
	private String departmentId;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(Integer id, Short type, Timestamp operationTime) {
		this.id = id;
		this.type = type;
		this.operationTime = operationTime;
	}

	/** full constructor */
	public SysLog(Integer id, Short type, String operation, Integer userId,
			Timestamp operationTime, String context, Integer enterId,
			String ipAddress, String departmentId) {
		this.id = id;
		this.type = type;
		this.operation = operation;
		this.userId = userId;
		this.operationTime = operationTime;
		this.context = context;
		this.enterId = enterId;
		this.ipAddress = ipAddress;
		this.departmentId = departmentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getEnterId() {
		return this.enterId;
	}

	public void setEnterId(Integer enterId) {
		this.enterId = enterId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}