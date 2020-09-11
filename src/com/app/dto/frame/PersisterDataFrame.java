package com.app.dto.frame;

import java.util.List;

import com.app.dto.Structures;
/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class PersisterDataFrame {
	
	private String type;
	
	private List<Structures> structures;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Structures> getStructures() {
		return structures;
	}

	public void setStructures(List<Structures> structures) {
		this.structures = structures;
	}
	
	

}
