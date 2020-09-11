package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
@JsonInclude(Include.NON_EMPTY)
public class ComputedFields {

	private String name;

	private String fieldType;

	private String sqlCommand;

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

	public String getSqlCommand() {
		return sqlCommand;
	}

	public void setSqlCommand(String sqlCommand) {
		this.sqlCommand = sqlCommand;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
