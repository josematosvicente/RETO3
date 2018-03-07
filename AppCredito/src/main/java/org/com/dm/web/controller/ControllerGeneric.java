package org.com.dm.web.controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.com.dm.exception.DmException;
import org.com.dm.json.GeneralConsumer;
import org.com.dm.report.BuildReport;
import org.com.dm.util.Constants;
import org.com.dm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ControllerGeneric {
	
	private static final Logger logger = Logger.getLogger(ControllerGeneric.class);
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${url_servicio}")
	private String url_services;
	@Value("${url_recurso}")
	private String url_recurso;
	@Value("${area}")
	private String area;
	

	@RequestMapping(value = "/GENERIC/{path}", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public String genericRecept(@PathVariable("path") String path,ModelMap model, HttpServletRequest request, @RequestParam("file") MultipartFile[] fileUploadBean) {
		try{
			logger.info("Inicio Controller POST MULTIPART");
			loadInitialParameters();
			LinkedHashMap<Integer, Object> parametro = new LinkedHashMap<Integer, Object>();
			parametro.put(0, path);
			new GeneralConsumer().load(model, restTemplate,request, parametro);
			logger.info("Fin Controller POST MULTIPART");
		}catch(DmException e){
			if(e.getCode().equals("S")){
				e.printStackTrace();
				model.put("error", e.getMessage());
				model.put("redirect", "error");
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put("error", e.getMessage());
			model.put("redirect", "error");
		}finally{
			return model.get("redirect").toString();
		}
	}
	
	@RequestMapping(value = "/GENERIC/{path}", method = RequestMethod.POST)
	public String genericRecept(@PathVariable("path") String path,ModelMap model, HttpServletRequest request) {
		logger.info("Inicio Controller POST");
		try{
			loadInitialParameters();
			LinkedHashMap<Integer, Object> variablesMap = new LinkedHashMap<Integer, Object>();
			variablesMap.put(0, path);
			new GeneralConsumer().load(model, restTemplate,request, variablesMap);
			logger.info("Fin Controller POST");
		}catch(DmException e){
			if(e.getCode().equals("S")){
				e.printStackTrace();
				model.put("error", e.getMessage());
				model.put("redirect", "error");
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put("error", e.getMessage());
			model.put("redirect", "error");
		}finally{
			return model.get("redirect").toString();
		}
	}
	
	@RequestMapping(value = "/GENERIC/{path}", method = RequestMethod.GET)
	public String genericReceptGet(@PathVariable("path") String path,ModelMap model, HttpServletRequest request) {
		logger.info("Fin Controller GET");
		try{
			loadInitialParameters();
			LinkedHashMap<Integer, Object> parametro = new LinkedHashMap<Integer, Object>();
			parametro.put(0, path);
			new GeneralConsumer().load(model, restTemplate,request, parametro);
			logger.info("Fin Controller GET");
		}catch(DmException e){
			if(e.getCode().equals("S")){
				e.printStackTrace();
				model.put("error", e.getMessage());
				model.put("redirect", "error");
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put("error", e.getMessage());
			model.put("redirect", "error");
		}finally{
			return model.get("redirect").toString();
		}
	}
	
	@RequestMapping(value = "/GENERIC/report/{path}", method = RequestMethod.GET)
	public void genericReportGet(@PathVariable("path") String path, ModelMap model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		try{
			
			LinkedHashMap<Integer, Object> parametros2 = new LinkedHashMap<>();
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
	            String paramName = parameterNames.nextElement();
	            parametros2.put(Integer.valueOf(paramName.split("__")[1]), request.getParameter(paramName).toString());
	        }
			
			parametros2=new Util().ordenar2(parametros2);
			
			new GeneralConsumer().generateReport(model, restTemplate,request,response, path, parametros2);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
	}
	
	public void loadInitialParameters(){
		Constants.RUTA_SERVICES = url_services;
		Constants.RUTA_RECURSOS = url_recurso;
		Constants.AREA_RECLAMO = area;
	}
	
}