package org.com.dm.util;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.com.dm.bean.UploadedFile;
import org.com.dm.bean.VistaComponente;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Util {
//	public String extraeList(ArrayList lista){
//		return lista.get(0).toString();
//	}
//	
//	public String extraeString(String valor){
//		return valor;
//	}
//	
//	public String extraeValor(Object obj){
//		if(obj.getClass().equals(ArrayList.class))
//			return extraeList((ArrayList)obj);
//		else if(obj.getClass().equals(String.class))
//			return extraeString((String)obj);
//		else if(obj.getClass().equals(LinkedHashMap.class))
//			return extraeList((ArrayList)((Map)obj).values().toArray()[0]);
//		else
//			return "";
//	}
	
	public static String encodeFileToBase64(MultipartFile file){
		String base64 = "";
		try{
			base64 = org.apache.commons.codec.binary.Base64.encodeBase64String(file.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}
		return base64;
		
	}
	
	public static String encodeFileToBase64(UploadedFile file){
		String base64 = "";
		try{
			base64 = org.apache.commons.codec.binary.Base64.encodeBase64String(file.getFile());
		}catch(Exception e){
			e.printStackTrace();
		}
		return base64;
		
	}
	
	public static byte[] decodeFileToBase64(String base64String){
		return org.apache.commons.codec.binary.Base64.decodeBase64(base64String);
	}
	
	public static File convertByteArrayToFile(byte[] data, LinkedHashMap<String, Object> variablesMap){
		File file = new File(variablesMap.get("1").toString()+variablesMap.get("2").toString());
        try {
        	FileUtils.writeByteArrayToFile(file, data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return file;
    }
	
	
	public LinkedHashMap<String, Object> ordenar (LinkedHashMap<Integer, String> lista){
		LinkedHashMap<String, Object> listaO = new LinkedHashMap<String, Object>();
		TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1.compareTo(o2);
					}
			});
		treeMap.putAll(lista);
		for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
			listaO.put(entry.getKey().toString(), entry.getValue());
		}
		return listaO;
	}
	
	public LinkedHashMap<Integer, Object> ordenar2 (LinkedHashMap<Integer, Object> lista){
		LinkedHashMap<Integer, Object> listaO = new LinkedHashMap<Integer, Object>();
		TreeMap<Integer, Object> treeMap = new TreeMap<Integer, Object>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1.compareTo(o2);
					}
			});
		treeMap.putAll(lista);
		for (Map.Entry<Integer, Object> entry : treeMap.entrySet()) {
			listaO.put(Integer.valueOf(entry.getKey()), entry.getValue());
		}
		return listaO;
	}
	
	public void prepareAttachment(LinkedHashMap<String, Object> variablesMap, Object oAttachments){
		if(oAttachments instanceof MultipartFile[] && oAttachments!=null){
			prepareAttach(variablesMap, (MultipartFile[])oAttachments);
		}else if(oAttachments instanceof List<?> && oAttachments!=null){
			prepareAttach(variablesMap, (List<UploadedFile>)oAttachments); 
		}
	}
	
	public void prepareAttach(LinkedHashMap<String, Object> variablesMap, MultipartFile[] attachments){
		int contador=0;
		if(attachments!=null){
		for (MultipartFile attachment : attachments) {
			if(attachment.getSize()>0){
				contador=contador+1;
				variablesMap.put(Constants.PARAM_ADJUNTO+Constants.DELIMITER_OBJECT+contador, Util.encodeFileToBase64(attachment));
				String[] nameSplit = attachment.getOriginalFilename().split("\\.");
				int sizeSplit = nameSplit.length;
				variablesMap.put(Constants.PARAM_NAME_ADJUNTO+Constants.DELIMITER_OBJECT+contador, attachment.getOriginalFilename().replaceAll("."+nameSplit[sizeSplit-1],""));
				variablesMap.put(Constants.PARAM_TYPE_ADJUNTO+Constants.DELIMITER_OBJECT+contador, nameSplit[sizeSplit-1]);
			}
		}
		variablesMap.put(Constants.PARAM_CANTIDAD_ADJUNTO,contador);
		}
	}
	
	public void prepareAttach(LinkedHashMap<String, Object> variablesMap, List<UploadedFile> adjuntos){
		int contador=0;
		if(adjuntos!=null){
		for (UploadedFile attachment : adjuntos) {
			if(attachment.getSize()>0){
				contador=contador+1;
				variablesMap.put(Constants.PARAM_ADJUNTO+Constants.DELIMITER_OBJECT+contador, Util.encodeFileToBase64(attachment));
				String[] nameSplit = attachment.getName().split("\\.");
				int sizeSplit = nameSplit.length;
				variablesMap.put(Constants.PARAM_NAME_ADJUNTO+Constants.DELIMITER_OBJECT+contador, attachment.getName().replaceAll("."+nameSplit[sizeSplit-1],""));
				variablesMap.put(Constants.PARAM_TYPE_ADJUNTO+Constants.DELIMITER_OBJECT+contador, nameSplit[sizeSplit-1]);
				convertByteArrayToFile(attachment.getFile(), variablesMap);
			}
		}
		variablesMap.put(Constants.PARAM_CANTIDAD_ADJUNTO,contador);
		}
	}
	
	public static boolean isContainIntoArray(String cadena, String[] array){
		for(String valor : array){
			if(cadena.trim().toUpperCase().equals(valor.trim().toUpperCase())){
				return true;
			}
		}
		return false;
	}
	
	public static String buildIntro(VistaComponente componente){
		StringBuilder html = new StringBuilder("");
		if(!StringUtils.isEmpty(componente.getDataIntro())){
			html.append(" data-intro = '").append(componente.getDataIntro()).append("' ");
		}
		if(!StringUtils.isEmpty(componente.getDataStep())){
			html.append(" data-step = '").append(componente.getDataStep()).append("' ");
		}
		if(!StringUtils.isEmpty(componente.getDataPosition())){
			html.append(" data-position = '").append(componente.getDataPosition()).append("' ");
		}
		if(!StringUtils.isEmpty(componente.getDataToolTipClass())){
			html.append(" data-tooltipClass = '").append(componente.getDataToolTipClass()).append("' ");
		}
		if(!StringUtils.isEmpty(componente.getDataHighLightClass())){
			html.append(" data-highlightClass = '").append(componente.getDataHighLightClass()).append("' ");
		}
		return html.toString();
	}
	
	
	/*
	public static void main (String[] args){
		//String temp="[{\"column\":[{\"re\":2}]}]";
//		String headers = "[{\"row\":[{\"label\":\"Name\",\"rowspan\":\"2\",\"colspan\":1},{\"label\":\"Phone\",\"rowspan\":\"3\"}]},"+
//						"{\"row\":[{\"label\":\"Name2\",\"rowspan\":\"2\",\"colspan\":1},{\"label\":\"Phone2\",\"rowspan\":\"3\"}]}]";
		String headers = "[	{\"row\":[{\"label\":\"Equipo-Usuario\",\"rowspan\":3},{\"label\":\"N°_de_Docentes\",\"rowspan\":3},{\"label\":\"N°_Teléfonos\",\"colspan\":6},{\"label\":\"Correos_Electrónicos\",\"colspan\":2,\"rowspan\":2},{\"label\":\"N°_Direcciones_Domiciliarias\",\"colspan\":2,\"rowspan\":2}]},{\"row\":[{\"label\":\"Fijo\",\"colspan\":3},{\"label\":\"Móvil\",\"colspan\":3}]},{\"row\":[{\"label\":\"1\"},{\"label\":\"2\"},{\"label\":\"3\"},{\"label\":\"1\"},{\"label\":\"2\"},{\"label\":\"3\"},{\"label\":\"1\"},{\"label\":\"2\"},{\"label\":\"1\"},{\"label\":\"2\"}]}]";
		List<Map<String,Object>> lista = null;
		Type listType = new TypeToken<List<Map<String,Object>>>() {}.getType();
		lista = new Gson().fromJson(headers, listType);
		System.out.println("elementos : " + lista.size());
		StringBuilder htmlHeader = new StringBuilder("");
		for(Map<String,Object> row : lista){
			System.out.println("row :" + row.get("row"));
			htmlHeader.append("<tr>");
			List<Map<String,Object>> listaColumns = new Gson().fromJson(row.get("row").toString(), listType);
			for(Map<String,Object> col : listaColumns){
				htmlHeader.append("<th ");
				if(col.get("rowspan") != null && !col.get("rowspan").toString().equals("")){
					Double r = Double.parseDouble(col.get("rowspan").toString());
					htmlHeader.append(" rowspan =\"").append(r.intValue()).append("\"");
				}
				if(col.get("colspan") != null && !col.get("colspan").toString().equals("")){
					Double c = Double.parseDouble(col.get("colspan").toString());
					htmlHeader.append(" colspan =\"").append(c.intValue()).append("\"");
				}
				if(col.get("style") != null && !col.get("style").toString().equals("")){
					htmlHeader.append(" style =\"").append(col.get("style").toString()).append("\"");
				}
				if(col.get("label") != null && !col.get("label").toString().equals("")){
					htmlHeader.append(">").append(col.get("label").toString()).append("</th>");
				}
			}
			htmlHeader.append("</tr>");
		}
		htmlHeader.append("");
		System.out.println(htmlHeader.toString());
	}
	*/
	
	public static String getEventFunction(String accion, String evento){
		String[] funciones = accion.split(";");
		String functionName = "";
		for( String funcion : funciones){
			if(funcion.toLowerCase().contains(evento.toLowerCase())){
				functionName = funcion.substring(
						funcion.toLowerCase().indexOf(evento.toLowerCase())+ evento.length())
						.replaceAll("=", "");
			}
		}
		return functionName;
	}
		
	public static String getReportParam(String param)throws Exception{
		//String jsonStringArray = "[\"01/12/2017\",\"02/12/2017\"]";
		param =  param.replace("[", "").replace("]", "");
		String[] strArray = param.split(",");
		if(strArray != null && strArray.length>0){
			return strArray[0];
		}else{
			return "";
		}
	}			

	
//	public static void main(String args[])throws Exception{
//		System.out.println(Util.getReportParam("[285,333]",0));
//	}
	
	public static String getReportParam(String param,int index)throws Exception{
		
		param =  param.replace("[", "").replace("]", "");
		String[] strArray = param.split(",");
		if(strArray != null && index+1<=strArray.length){
			return strArray[index];
		}else{
			return "";
		}
		
		
	}
}


