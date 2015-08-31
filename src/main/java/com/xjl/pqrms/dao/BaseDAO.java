package com.xjl.pqrms.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.xjl.pqrms.ContextUtil;

public abstract class BaseDAO {
	private static JdbcTemplate jdbcTemplate;
	
	protected static JdbcTemplate getJdbcTemplate(){
		if(jdbcTemplate==null) jdbcTemplate=(JdbcTemplate)ContextUtil.getContext().getBean("jdbcTemplate");
		
		return jdbcTemplate;
	}
}
