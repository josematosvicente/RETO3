package org.com.dm.json;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.com.dm.bean.Vista;
import org.com.dm.util.Constants;
import org.com.dm.util.Lanzador;
import org.json.simple.JSONValue;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.internal.LinkedTreeMap;

public class Mail {
	
	private static final Logger logger = Logger.getLogger(Mail.class);
		
	public String send(HttpServletRequest request,
			LinkedHashMap<Integer, Object> parametro ,
			ModelMap model,RestTemplate restTemplate, 
			LinkedHashMap linkedHashMap) throws Exception{
		
		
		logger.info("Enviando Mensaje....");
		
		LinkedHashMap<String, Object> beanResponse = new Lanzador(restTemplate).send(Constants.RUTA_SERVICES + Constants.REST_SERVICE_MAIL, parametro, model,null);
		return (String) JSONValue.toJSONString(beanResponse);
	}
	
}
