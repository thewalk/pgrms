package com.xjl.pqrms.entity;

public abstract class BaseMapper {
	public static int castToInt(Object object){
		
		if(object!=null){
			return Integer.parseInt(object.toString());
		}
		else return -1;
	}
	
	public static String castToString(Object object){
		if(object!=null) return object.toString();
		else return "";
	}
}
