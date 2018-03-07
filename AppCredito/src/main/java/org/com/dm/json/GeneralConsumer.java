package org.com.dm.json;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.com.dm.report.BuildReport;
import org.com.dm.web.controller.ControllerSessionComponent;
import org.json.simple.JSONValue;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author jmatos
 * Consumidor de Servicios
 *
 */
public class GeneralConsumer {
	
	private static final Logger logger = Logger.getLogger(GeneralConsumer.class);
	ControllerSessionComponent controllerSessionComponent = new ControllerSessionComponent();
	
	public void load(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			LinkedHashMap<Integer, Object> parametros) throws Exception{
		
		model.addAttribute("redirect","cotizadorload");
		
	}
	
	public void loadAjax(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			LinkedHashMap<Integer, Object> parametros) throws Exception{
		
		String jsonText = (String) JSONValue.toJSONString(new EventSave().save(request, parametros, model, restTemplate, null));
		model.addAttribute("redirect", jsonText);
			
	}
	
	public void readAjax(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			LinkedHashMap<Integer, Object> parametros) throws Exception{
		
		String jsonText = (String) JSONValue.toJSONString(new EventSave().read(request, parametros, model, restTemplate, null));
		model.addAttribute("redirect", jsonText);
			
	}
	
	public void generateReport(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			HttpServletResponse response,
			String path,
			LinkedHashMap<Integer, Object> parametro) throws Exception{
		//service1
		List<LinkedHashMap<String, Object>>  listado = new EventSave().read(request, parametro, model, restTemplate, null);
		//service2
		LinkedHashMap<Integer, Object> parametroReporte = new LinkedHashMap<Integer, Object>();
		parametroReporte.put(1, parametro.get(1));
		new BuildReport().generate("CRONOGRAMA", model, request, response, listado, parametroReporte, "PDF",true);

		model.addAttribute("redirect", "Se genero el reporte correctamente");
			
	}
	
	
	public void send(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			HttpServletResponse response,
			String path,
			LinkedHashMap<Integer, Object> parametro) throws Exception{
		//Consumiendo service de consulta a la BD
		List<LinkedHashMap<String, Object>>  listado = new EventSave().read(request, parametro, model, restTemplate, null);
		//Generando Reporte
		LinkedHashMap<Integer, Object> parametroReporte = new LinkedHashMap<Integer, Object>();
		parametroReporte.put(0, parametro.get(0));
		byte[] reportebyte = new BuildReport().generate("CRONOGRAMA", model, request, response, listado, parametroReporte, "PDF",false);
		//Envio de Correo Electronico
		LinkedHashMap<Integer, Object> parametroMail = new LinkedHashMap<Integer, Object>();
		parametroMail.put(0, parametro.get(2));
		parametroMail.put(1, "");
		parametroMail.put(2, "jmatos@derrama.org.pe");
		parametroMail.put(3, "Envio de Cronograma");
		parametroMail.put(4, "Reciba este cronograma de pago en formato PDF");
		parametroMail.put(5, "PDF");
		parametroMail.put(6, DatatypeConverter.printBase64Binary(reportebyte));
		
		mailAjax( model,
				 restTemplate,
				 request, 
				 parametroMail,
				 reportebyte);
		
		model.addAttribute("redirect", "Se genero el reporte correctamente");
			
	}
	
	public void mailAjax(ModelMap model,
			RestTemplate restTemplate,
			HttpServletRequest request, 
			LinkedHashMap<Integer, Object> parameter, byte[] adjunto) throws Exception{
		
		String jsonText = new Mail().send(request, parameter, model, restTemplate, null);
		
		
		model.addAttribute("redirect", jsonText);
			
	}
	
	
}
