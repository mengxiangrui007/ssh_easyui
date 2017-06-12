package com.data.model;

/**
 * BasicTypes entity. @author MyEclipse Persistence Tools
 */

public class BasicTypes implements java.io.Serializable {

	// Fields

	private Integer id;
	private Short type;
	private String typename;
	private String name;
	private String code;
	private String description;

	// Constructors

	/** default constructor */
	public BasicTypes() {
	}

	/** minimal constructor */
	public BasicTypes(Integer id, Short type, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}

	/** full constructor */
	public BasicTypes(Integer id, Short type, String typename, String name,
			String code, String description) {
		this.id = id;
		this.type = type;
		this.typename = typename;
		this.name = name;
		this.code = code;
		this.description = description;
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

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}