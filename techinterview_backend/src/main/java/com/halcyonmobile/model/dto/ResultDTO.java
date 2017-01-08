package com.halcyonmobile.model.dto;

import java.util.List;

import com.halcyonmobile.model.Result;

public class ResultDTO {

	private List<Result> resultList;
	
	public ResultDTO(){
		//do nothing
	}

	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "ResultDTO [resultList=" + resultList + "]";
	}
	
	
	
}
