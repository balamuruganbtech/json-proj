package com.app.dto.frame;

import java.util.List;

import com.app.dto.Fields;
/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class ReaderDataFrame {

	private String type;

	private String path;

	private boolean hasHeader;

	private String loadOption;

	private List<Fields> fields;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getHasHeader() {
		return false;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = false;
	}

	public String getLoadOption() {
		return loadOption;
	}

	public void setLoadOption(String loadOption) {
		this.loadOption = loadOption;
	}

	public List<Fields> getFields() {
		return fields;
	}

	public void setFields(List<Fields> fields) {
		this.fields = fields;
	}

}
