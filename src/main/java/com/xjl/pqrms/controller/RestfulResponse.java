package com.xjl.pqrms.controller;

public class RestfulResponse {
	private boolean success;
	private Object Result;
	
	
	
	public RestfulResponse(boolean success, Object result) {
		super();
		this.success = success;
		Result = result;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public Object getResult() {
		return Result;
	}

	public void setResult(Object result) {
		Result = result;
	}

}
