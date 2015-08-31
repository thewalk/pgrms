package com.xjl.pqrms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Risk extends BaseMapper{
	private int id;
	private String description;
	
	
	
	public Risk(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	// Mapping
	public static List<Risk> convertToRiskList(List<Map<String,Object>> input){
		List<Risk> output = new ArrayList<Risk>();
		
		if(input==null) return output;
		
		for(Map<String,Object> row:input){
			output.add(convertToRisk(row));
		}
		
		return output;
	}
	
	public static Risk convertToRisk(List<Map<String,Object>> input){
		if(input==null) return null;
		
		return convertToRisk(input.get(0));
	}
	
	public static Risk convertToRisk(Map<String,Object> row){
		if(row!=null)
			return new Risk(
				castToInt(row.get("ID")),
				castToString(row.get("DESCRIPTION"))
			);
		else return null;
	}
	
	
	

}
