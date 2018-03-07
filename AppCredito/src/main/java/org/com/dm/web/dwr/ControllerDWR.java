package org.com.dm.web.dwr;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.dm.json.GeneralConsumer;
import org.com.dm.util.Util;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author jmatos
 * Controlador que recibe peticiones AJAX
 */
@RemoteProxy(name="dwrService")
@Controller
public class ControllerDWR {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * @param parametros de envio para el metodo grabar 
	 * Monto de Credito
	 * Tasa Anual
	 * Fecha de desembolso
	 * Periodo de Gracia
	 * @return retorna cuotas de la simulacion 
	 */
	@RemoteMethod
	public String sendAjax(String... params){
		try {
			LinkedHashMap<Integer, Object> parameter = new LinkedHashMap<Integer, Object>();
			int contador = 0;
			for (String par : params) {
				parameter.put(contador, par);
				contador = contador+1;
			}
			ModelMap model = new ModelMap();
			
			new GeneralConsumer().loadAjax(model, restTemplate,null,parameter);
			return model.get("redirect").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @param parametros de envio para la consulta de la simulacion id_credito
	 * @return
	 */
	@RemoteMethod
	public String readAjax(String... params){
		try {
			LinkedHashMap<Integer, Object> parameter = new LinkedHashMap<Integer, Object>();
			int contador = 0;
			for (String par : params) {
				parameter.put(contador, par);
				contador = contador+1;
			}
			ModelMap model = new ModelMap();
			
			new GeneralConsumer().readAjax(model, restTemplate,null,parameter);
			return model.get("redirect").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	
	@RemoteMethod
	public String sendMail(String... params){
		try {
			LinkedHashMap<Integer, Object> parameter = new LinkedHashMap<Integer, Object>();
			int contador = 0;
			for (String par : params) {
				parameter.put(contador, par);
				contador = contador+1;
			}
			ModelMap model = new ModelMap();
			new GeneralConsumer().send(model, restTemplate, null, null,null, parameter);
			return model.get("redirect").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	
	@RequestMapping(value = "/GENERIC/ajax/{path}", method = RequestMethod.GET)
	public @ResponseBody LinkedHashMap<String, Object> ajax(
			@PathVariable("path") String path,
			HttpServletRequest request,
			HttpServletResponse response){
			
			try {
				ModelMap model = new ModelMap();
				
				LinkedHashMap<Integer, Object> parameter = new LinkedHashMap<Integer, Object>();
				
				parameter.put(0, path);
				parameter.put(1, request.getParameter("start")==null?"": request.getParameter("start").toString());
				parameter.put(2, request.getParameter("length")==null?"": request.getParameter("length").toString());
				parameter.put(3, request.getParameter("search[value]")==null?"":request.getParameter("search[value]").toString());
				parameter.put(4, request.getParameter("order[0][column]")==null?"":request.getParameter("order[0][column]").toString());
				parameter.put(5, request.getParameter("order[0][dir]")==null?"":request.getParameter("order[0][dir]").toString());
				
				Enumeration<String> parameterNames = request.getParameterNames();
				while (parameterNames.hasMoreElements()) {
		             String paramName = parameterNames.nextElement();
		             parameter.put(Integer.valueOf(paramName.split("__")[0])+11, request.getParameter(paramName).toString());
		         }
				
				new GeneralConsumer().readAjax(model, restTemplate,null,parameter);
				LinkedHashMap<String, Object> respuesta=new LinkedHashMap<String, Object>();
				
				List<LinkedHashMap<String, Object>> list= (List<LinkedHashMap<String, Object>>) model.get("redirect1");
				if(list.size()>0){
					LinkedHashMap<String, Object> fila = list.get(0);
					String cantidad = (String)fila.get("DTTOTAL__");
					respuesta.put("recordsTotal", cantidad);
					respuesta.put("recordsFiltered", cantidad);
				}else{
					respuesta.put("recordsTotal", 10);
					respuesta.put("recordsFiltered", 0);
				}
				respuesta.put("data", list);
				return respuesta;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
}