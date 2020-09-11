package com.app.dto;

import java.util.List;
/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class Structures {

	private String name;

	private String dbName;

	private boolean isPersistHive;

	private String mergeType;

	private String tablename;

	private String outputPath;

	private List<FiledsPersister> fields;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public boolean isPersistHive() {
		return isPersistHive;
	}

	public void setPersistHive(boolean isPersistHive) {
		this.isPersistHive = isPersistHive;
	}

	public String getMergeType() {
		return mergeType;
	}

	public void setMergeType(String mergeType) {
		this.mergeType = mergeType;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public List<FiledsPersister> getFields() {
		return fields;
	}

	public void setFields(List<FiledsPersister> fields) {
		this.fields = fields;
	}
}
