package org.com.dm.service.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class CallThread extends Thread{

	SimpleJdbcCall simpleJdbcCall;
	SqlParameterSource paramMap;
	
	public void run(){
		System.out.println("CallThread:");
		List<LinkedHashMap> list = new ArrayList();
		Map<String, Object> map = simpleJdbcCall.execute(paramMap);
		list = (List<LinkedHashMap>)map.get("OUT_DATA");
		System.out.println("CallThread:list:"+list.toString());
	}
	
	public void setSimpleJdbcCall(SimpleJdbcCall simpleJdbcCall) {
		this.simpleJdbcCall = simpleJdbcCall;
	}
	
	public void setParamMap(SqlParameterSource paramMap) {
		this.paramMap = paramMap;
	}
	
}
