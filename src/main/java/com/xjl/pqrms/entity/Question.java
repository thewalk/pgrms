package com.xjl.pqrms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Question extends BaseMapper{
	private int id;
	private String description;
	
	
	
	public Question(int id, String description) {
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
	public static List<Question> convertToQuestionList(List<Map<String,Object>> input){
		List<Question> output = new ArrayList<Question>();
		
		if(input==null) return output;
		
		for(Map<String,Object> row:input){
			output.add(convertToQuestion(row));
		}
		
		return output;
	}
	
	public static Question convertToQuestion(List<Map<String,Object>> input){
		if(input==null) return null;
		
		return convertToQuestion(input.get(0));
	}
	
	public static Question convertToQuestion(Map<String,Object> row){
		if(row!=null)
			return new Question(
				castToInt(row.get("ID")),
				castToString(row.get("DESCRIPTION"))
			);
		else return null;
	}
	
	
	
}
