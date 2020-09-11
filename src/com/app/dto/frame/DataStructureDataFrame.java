package com.app.dto.frame;

import java.util.List;

import com.app.dto.ComputedFields;
import com.app.dto.FiledsPersister;

/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class DataStructureDataFrame {

	private String type;

	private List<FiledsPersister> fields;

	private List<ComputedFields> computedFields;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FiledsPersister> getFields() {
		return fields;
	}

	public void setFields(List<FiledsPersister> fields) {
		this.fields = fields;
	}

	public List<ComputedFields> getComputedFields() {
		return computedFields;
	}

	public void setComputedFields(List<ComputedFields> computedFields) {
		this.computedFields = computedFields;
	}

}
