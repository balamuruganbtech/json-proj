package com.app.dto;

/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class FiledsPersister {

	private String name;

	private String fieldType;

	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
