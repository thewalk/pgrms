package com.xjl.pqrms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xjl.pqrms.dao.MainRead;

public class Project extends BaseMapper{
	private int id;
	private int parentProjectId;
	private String description;
	
	
	
	public Project(int id, int parentProjectId, String description) {
		super();
		this.id = id;
		this.parentProjectId = parentProjectId;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentProjectId() {
		return parentProjectId;
	}
	public void setParentProjectId(int parentProjectId) {
		this.parentProjectId = parentProjectId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Function
	public static List<Project> getProjectList(){
		return convertToProjectList(MainRead.getProjectList()); 
	}
	
	// Mapping
	private static List<Project> convertToProjectList(List<Map<String,Object>> input){
		List<Project> output = new ArrayList<Project>();
		
		if(input==null) return output;
		
		for(Map<String,Object> row:input){
			output.add(convertToProject(row));
		}
		
		return output;
	}
	
	private static Project convertToProject(List<Map<String,Object>> input){
		if(input==null) return null;
		
		return convertToProject(input.get(0));
	}
	
	private static Project convertToProject(Map<String,Object> row){
		if(row!=null)
			return new Project(
				castToInt(row.get("ID")),
				castToInt(row.get("PARENT_PROJECT_ID")),
				castToString(row.get("DESCRIPTION"))
			);
		else return null;
	}
}
