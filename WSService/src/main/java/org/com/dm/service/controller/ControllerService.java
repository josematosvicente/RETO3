package org.com.dm.service.controller;
 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import org.com.dm.service.dao.AplicationDAO;
import org.com.dm.util.MailAuthenticator;
import org.com.dm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
public class ControllerService {
	@Autowired
    private AplicationDAO aplicationDAO;
	LinkedHashMap<String, Object> rest=null;
	List<LinkedHashMap> data = null;
	LinkedHashMap<String, String>  response;
	LinkedHashMap<String, String> header;
	
	//{paquete:,validateToken:}
    
	
    
    
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RequestMapping(value = "/component/save", method = RequestMethod.POST)
    public LinkedHashMap<String, Object> saveComponent(@RequestBody LinkedHashMap param) throws Exception{
    	try{
    		cleanData();
    		prepareResponse(aplicationDAO.saveComponent(param));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally {
        	rest.put("MESSAGE", response);			
		}
    	return rest;
    }
    
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RequestMapping(value = "/component/read", method = RequestMethod.POST)
    public LinkedHashMap<String, Object> readComponent(@RequestBody LinkedHashMap param) throws Exception{
    	try{
    		cleanData();
    		prepareResponse(aplicationDAO.readComponent(param));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally {
        	rest.put("MESSAGE", response);			
		}
    	return rest;
    }
    
    
    
    @RequestMapping(value = "/component/mail", method = RequestMethod.POST)
    public LinkedHashMap<String, Object> mailComponent(@RequestBody LinkedHashMap param) throws Exception{
    	try{
    		cleanData();
//    		prepareResponse(aplicationDAO.saveComponent(param));
    		sendMail(param);
    	}catch(Exception ex){
    		ex.printStackTrace();
    		try{
    			putMessage("-1", ex.getCause().getMessage().split("\n")[0].toString().toUpperCase());
    		}catch(Exception ex1){
    			putMessage("-1", "ERROR EN PROCESAMIENTO DE SERVICIO");
    		}
    	}finally {
        	rest.put("MESSAGE", response);			
		}
    	return rest;
    }
   
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sendMail(LinkedHashMap param) {
    	boolean seguridad=false;
		String to = param.get("0").toString();
		String cc = param.get("1").toString();
		String from = param.get("2").toString();
		String asunto = param.get("3").toString();
		String mensaje = param.get("4").toString();
		String extension = param.get("5").toString();
		Object adjunto = param.get("6");//param.get("ADJUNTO");
		
		String host = "demamail01.derrama.org.pe";
		
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		if(seguridad) {
		Properties props = new Properties();
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.starttls.enable","true");
	    props.put("mail.smtp.debug", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    MailAuthenticator auth = new MailAuthenticator();
	    Session session = Session.getInstance(props, auth);
	    session.setDebug(true);
		}
		
		
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			MimeMultipart mimeMultipart = new MimeMultipart();
			String addressTo[] = to.split(";");
			Address addressArrayTo[] = new InternetAddress[addressTo.length];
			for(int i=0;i<addressTo.length;i++){
				addressArrayTo[i] =  new InternetAddress(addressTo[i]);
			}
			message.setFrom(new InternetAddress(from));		
			message.addRecipients(Message.RecipientType.TO, addressArrayTo);
//			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.setSubject(asunto);
			
			BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(mensaje, "text/html");
	        mimeMultipart.addBodyPart(messageBodyPart);
	        
	        if(adjunto!=null){ 
        	Object content = Util.CONTENT_TYPE.get(extension);
        	DataSource dataSource = new ByteArrayDataSource((byte[])DatatypeConverter.parseBase64Binary(adjunto.toString()), content==null ? "text/plain": content.toString());
            MimeBodyPart adjuntoBodyPart = new MimeBodyPart();
            adjuntoBodyPart.setDataHandler(new DataHandler(dataSource));
            adjuntoBodyPart.setFileName("Adjunto".concat(".").concat(extension));
            mimeMultipart.addBodyPart(adjuntoBodyPart);
	        }
            
            
            message.setContent(mimeMultipart, "text/html");
			
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
    
    private void prepareResponse(List data){
    	response = new LinkedHashMap<String, String>();
		header = new LinkedHashMap<String, String>();
		rest = new LinkedHashMap<String, Object>();
		putHeader("", "");
		rest.put("HEADER", header);
    	rest.put("DATA", data);
    	succes();
    }
    
    private void cleanData(){
    	response = new LinkedHashMap<String, String>();
		header = new LinkedHashMap<String, String>();
		rest = new LinkedHashMap<String, Object>();
		data = new ArrayList();
		putHeader("", "");
		rest.put("HEADER", header);
		rest.put("DATA", data);
		//valida
    }
    
    private void putHeader(String user, String token){
    	header.put("USER", user);
    	header.put("TOKEN", user);
    }
    
    private void putMessage(String code, String responseMessage){
    	response.put("CODE", code);
    	response.put("RESPONSE", responseMessage);
    }
    
    private void succes(){
    	putMessage("0", "");
    }
}