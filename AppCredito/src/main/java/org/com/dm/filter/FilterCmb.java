package org.com.dm.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.dm.util.Constants;
import org.com.dm.util.Lanzador;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.google.gson.internal.LinkedTreeMap;

@Controller
public class FilterCmb implements Filter {
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy!!!!!!!!!!!!!!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    
	    System.out.println();
		
		
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("config.properties");
			// ...
			Properties properties = new Properties();
			properties.load(input);
			Constants.RUTA_SERVICES = properties.get("url_servicio").toString();
		}catch(Exception e){
			
		}
		
		
		
		
		if (!req.getRequestURI().startsWith(req.getContextPath()) || req.getRequestURI().contains("error.jsp")) { // Skip JSF resources (CSS/JS/Images/etc)
	        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        res.setDateHeader("Expires", 0); // Proxies.
		}else {
			String token =retornaToken(req);
			LinkedTreeMap responseLogin = validaToken(token,req.getRequestURI());
			
			if(responseLogin!=null) {
				String respuesta=responseLogin.get("URLLOGIN").toString();
				String urllogin=responseLogin.get("URL").toString();
				if( !respuesta.equals("OK") && !req.getRequestURI().contains("/DMSeguridad/")) {
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					req.getSession().setAttribute(Constants.Session.MESSAGE_LOGIN_DATA,null);
					req.getSession().removeAttribute(Constants.Session.MESSAGE_LOGIN_DATA);
					httpResponse.sendRedirect(urllogin);
					return;
				}
			}else {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect("pages/GENERIC/error.jsp");
				return;
			}
			
//			if(responseLogin!=null && !responseLogin.equals("OK") && !req.getRequestURI().contains("/DMSeguridad/")){
//				HttpServletResponse httpResponse = (HttpServletResponse) response;
//				req.getSession().setAttribute(Constants.Session.MESSAGE_LOGIN_DATA,null);
//				req.getSession().removeAttribute(Constants.Session.MESSAGE_LOGIN_DATA);
//				httpResponse.sendRedirect(urllogin);
//				return;
//			}
		}
	    chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init!!!!!!!!!!!!!!");
		
	}
	
	public String retornaToken(HttpServletRequest req){
		String token= req.getParameter("0__TOKEN");
		if(token==null){
			LinkedHashMap<String, Object> message_login_data = (LinkedHashMap<String, Object>)
					req.getSession().getAttribute(Constants.Session.MESSAGE_LOGIN_DATA);
			if(message_login_data!=null && message_login_data.get("DATA")!=null && ((ArrayList)message_login_data.get("DATA")).size()>0){
			Object object = ((ArrayList)message_login_data.get("DATA")).get(0);
			if(object.getClass().toString().contains("LinkedTreeMap")){
				LinkedTreeMap<String, Object> data1 = (LinkedTreeMap)((ArrayList)message_login_data.get("DATA")).get(0);
				token = data1.get("TOKEN").toString();
			}
			if(object.getClass().toString().contains("LinkedHashMap")){
				LinkedHashMap<String, Object> data1 = (LinkedHashMap)((ArrayList)message_login_data.get("DATA")).get(0);
				token = data1.get("TOKEN").toString();
			}
			
			}
		}
		return token;
	}
	
	public LinkedTreeMap validaToken(String token, String url){
		RestTemplate restTemplate = new RestTemplate();
	    LinkedHashMap beanResponse = null;
		//parametros para consulta
	    LinkedHashMap<Integer, Object> parametro = new LinkedHashMap<Integer, Object>();
		parametro.put(0, token);
		parametro.put(1, url);
		try {
			beanResponse = new Lanzador(restTemplate).send(Constants.RUTA_SERVICES + Constants.REST_SERVICE_COMPONENT, parametro, null,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Construye control
		if(beanResponse.size()>0) {
			ArrayList<LinkedTreeMap> data = (ArrayList<LinkedTreeMap>)beanResponse.get("DATA");
			String urlLogin = "";
			String forceLogin = ""; 
			return data.get(0);
		}else {
			return null;
		}
	}
	
	
}
