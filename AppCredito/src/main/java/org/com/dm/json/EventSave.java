package org.com.dm.json;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.com.dm.bean.Vista;
import org.com.dm.exception.DmException;
import org.com.dm.util.Constants;
import org.com.dm.util.Lanzador;
import org.json.simple.JSONValue;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.internal.LinkedTreeMap;

public class EventSave {
	
	private static final Logger logger = Logger.getLogger(EventSave.class);
		
	public List<LinkedHashMap<String,Object>> save(HttpServletRequest request,
			LinkedHashMap<Integer, Object> parametro ,
			ModelMap model,RestTemplate restTemplate, 
			LinkedHashMap linkedHashMap) throws Exception{
		
			return new Lanzador(restTemplate).sendReturnData(Constants.RUTA_SERVICES + Constants.REST_SERVICE_SAVE, parametro, model,null);
	}

	public List<LinkedHashMap<String,Object>> read(HttpServletRequest request,
			LinkedHashMap<Integer, Object> parametro ,
			ModelMap model,RestTemplate restTemplate, 
			LinkedHashMap linkedHashMap) throws Exception{
		
			return new Lanzador(restTemplate).sendReturnData(Constants.RUTA_SERVICES + Constants.REST_SERVICE_READ, parametro, model,null);
	}

}
