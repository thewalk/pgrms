package com.xjl.pqrms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;

public class MainUpdate extends BaseDAO {

	public static int setProjectOption(final int projectId,final int questionId,final int seq){
		
		@SuppressWarnings("unchecked")
		int success=getJdbcTemplate().execute(
			new CallableStatementCreator() {   
		        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
		           String storedProc = "{CALL SET_PROJECT_OPTION(?,?,?,?,?)}";  
		           CallableStatement cs = con.prepareCall(storedProc);   
		           cs.setInt("IN_PROJECT_ID",projectId);   
		           cs.setInt("IN_QUESTION_ID",questionId);   
		           cs.setInt("IN_SEQ",seq);   
		           
		           cs.registerOutParameter("OUT_SUCCESS", OracleTypes.NUMBER);
		           cs.registerOutParameter("OUT_EXCEPTION", OracleTypes.NVARCHAR);
		           
		           return cs;   
		        }   
		     }, new CallableStatementCallback() {   
		        public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {   
					
					cs.execute();     
					int success = Integer.parseInt(cs.getObject("OUT_SUCCESS").toString()); 

					return success;   
		        }   
		  });
				
		return success;
	}
}
