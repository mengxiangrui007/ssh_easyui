package com.data.model;

public enum LogType {

	// 01为操作日志，02为安全日志，03为异常日志
	OPERATION("01"), SECURITY("02"), EXCEPTION("03");

	/**
	 * @Fields value : 值，注意不能重复
	 */
	private String value;

	LogType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
