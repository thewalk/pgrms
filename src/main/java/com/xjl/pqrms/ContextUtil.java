package com.xjl.pqrms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtil {
	private static ApplicationContext context;
	
	public static ApplicationContext getContext()
	{
		if(context==null) context = new ClassPathXmlApplicationContext("spring-context.xml");
		
		return context;
	}
}
