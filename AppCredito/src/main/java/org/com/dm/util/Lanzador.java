package org.com.dm.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.com.dm.bean.Vista;
import org.com.dm.exception.DmException;
import org.com.dm.web.controller.ControllerSessionComponent;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class Lanzador {
	
	RestTemplate restTemplate =null;
	private static final Logger logger = Logger.getLogger(Lanzador.class);
	 

	public Lanzador(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	
	public List<LinkedHashMap<String, Object>> sendReturnData(
				String url, 
				LinkedHashMap<Integer, Object> parameter, 
				ModelMap model,
				HttpServletRequest request) throws Exception{
		List<LinkedHashMap<String, Object>> list=null;
		LinkedHashMap<String, Object> retornoFormat = send(url, parameter, model, request);
		LinkedTreeMap<String, Object> message = (LinkedTreeMap<String, Object>) retornoFormat.get("MESSAGE");
		if (message.get("CODE").toString().compareTo(Constants.RESPONSE_OK) == 0) {
			Type listType2 = new TypeToken<List<LinkedHashMap<String, String>>>() {}.getType();
			list = new Gson().fromJson( JSONValue.toJSONString(retornoFormat.get("DATA")), listType2);
			if(model != null){
			model.addAttribute(Constants.PROCESS_MESSAGE, JSONValue.toJSONString(retornoFormat));
			}
		}else{
			if(model != null){
			model.addAttribute(Constants.PROCESS_MESSAGE, JSONValue.toJSONString(retornoFormat));
			}
		}
		return list;
	}

	
	
	
	public LinkedHashMap<String, Object> send(
				String url, 
				LinkedHashMap<Integer, Object> parameter, 
				ModelMap model,
				HttpServletRequest request) throws Exception{
		
		
		System.out.println("url1"+url);
		logger.info("Enviando por Lanzador al Servicio Web: "+url);
		LinkedHashMap<String, Object> retornoFormat = new LinkedHashMap<String, Object>(); 
		try{
			List<LinkedHashMap<String, Object>> list=null;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);		
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<LinkedHashMap> entity = new HttpEntity<LinkedHashMap>(parameter,headers);
			String respuesta=restTemplate.postForObject(url, entity,String.class);
			System.out.println("respuesta:"+respuesta);
			Type listType = new TypeToken<LinkedHashMap<String, Object>>() {}.getType();
			retornoFormat = (LinkedHashMap<String, Object>)new Gson().fromJson(respuesta, listType);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return retornoFormat;
	}
	
}
