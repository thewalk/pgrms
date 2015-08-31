package com.xjl.pqrms.entity;

import java.util.List;
import java.util.Map;

import com.xjl.pqrms.dao.MainRead;
import com.xjl.pqrms.dao.MainUpdate;

public class Workspace extends BaseMapper{
	private List<Question> questionList;
	private List<Risk> riskList;
	
	
	
	public Workspace(List<Question> questionList, List<Risk> riskList) {
		super();
		this.questionList = questionList;
		this.riskList = riskList;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	public List<Risk> getRiskList() {
		return riskList;
	}
	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}
	
	public static Workspace getWorkspace(int projectId){
		
		Map<String,List<Map<String,Object>>> result=MainRead.getWorkspace(projectId);
		
		List<Question> questionList=Question.convertToQuestionList(result.get("QUESTION_LIST"));
		List<Risk> riskList=Risk.convertToRiskList(result.get("RISK_LIST"));
		
		return new Workspace(questionList,riskList);
		
	}
	
	public static int setProjectOpion(int projectId, int questionId, int seq){
		return MainUpdate.setProjectOption(projectId, questionId, seq);
	}
	
	

}
