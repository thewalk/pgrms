package com.xjl.pqrms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;

import com.xjl.pqrms.entity.Project;

public class MainRead extends BaseDAO {
	
	public static List<Map<String,Object>> getProjectList(){
		List<Map<String,Object>> list = getJdbcTemplate().queryForList("SELECT ID,PARENT_PROJECT_ID,DESCRIPTION FROM PROJECT");
		
		return list;
		
	}
	
	public static Map<String,List<Map<String,Object>>> getWorkspace(final int projectId){
		
		@SuppressWarnings("unchecked")
		Map<String,List<Map<String,Object>>> resultList=getJdbcTemplate().execute(
			new CallableStatementCreator() {   
		        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
		           String storedProc = "{CALL GET_WORKSPACE(?,?,?,?,?)}";  
		           CallableStatement cs = con.prepareCall(storedProc);   
		           cs.setInt("IN_PROJECT_ID",projectId);   
		           
		           cs.registerOutParameter("OUT_SUCCESS", OracleTypes.NUMBER);
		           cs.registerOutParameter("OUT_EXCEPTION", OracleTypes.NVARCHAR);
		           
		           cs.registerOutParameter("QUESTION_CURSOR", OracleTypes.CURSOR);
		           cs.registerOutParameter("RISK_CURSOR", OracleTypes.CURSOR);
		           return cs;   
		        }   
		     }, new CallableStatementCallback() {   
		        public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {   

					Map<String,List<Map<String,Object>>> result=new HashMap<String,List<Map<String,Object>>>();
					
					cs.execute(); 
					
					
					ResultSet rs = (ResultSet) cs.getObject("QUESTION_CURSOR"); 
					List<Map<String,Object>> questionList=new ArrayList<Map<String,Object>>();
					
					while (rs.next()) { 
						Map rowMap = new HashMap();   
						rowMap.put("ID", rs.getString("ID"));   
						rowMap.put("DESCRIPTION", rs.getString("DESCRIPTION"));   
						questionList.add(rowMap);   
					}  
					result.put("QUESTION_LIST", questionList);
					rs.close();
					
					rs = (ResultSet) cs.getObject("RISK_CURSOR"); 
					List<Map<String,Object>> riskList=new ArrayList<Map<String,Object>>();
					
					while (rs.next()) { 
						Map rowMap = new HashMap();   
						rowMap.put("ID", rs.getString("ID"));   
						rowMap.put("DESCRIPTION", rs.getString("DESCRIPTION"));   
						riskList.add(rowMap);   
						
					}  
					result.put("RISK_LIST", riskList);
					
					rs.close();   
					return result;   
		        }   
		  });
				
		return resultList;
	}
	
}
