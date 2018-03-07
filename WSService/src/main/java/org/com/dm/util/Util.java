package org.com.dm.util;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
	
//	public static File convertByteArrayToFile(byte[] data){
//		File file = new File("D:\\logo.png");
//        try {
//        	
//        	FileUtils.writeByteArrayToFile(file, data);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return file;
//    }
	/*
	public static String encodeFileToBase64(MultipartFile file){
		String base64 = "";
		try{
			base64 = org.apache.commons.codec.binary.Base64.encodeBase64String(file.getBytes());;
		}catch(Exception e){
			e.printStackTrace();
		}
		return base64;
		
	}
	
	public static byte[] decodeFileToBase64(String base64String){
		return org.apache.commons.codec.binary.Base64.decodeBase64(base64String);
	}
	
	public static byte[] encodeByteArrayToBase64(byte[] data){
		return org.apache.commons.codec.binary.Base64.encodeBase64(data);
	}
	
	public static void  convertBlobElementToByteArray(String elementName, List<LinkedHashMap> listData){
		try{

			if(listData != null){
				for(LinkedHashMap<String, Object> item : listData){
					BLOB param = (BLOB)item.get(elementName);
					if(param != null){
						item.remove(elementName);
						item.put(elementName,encodeByteArrayToBase64(param.getBytes()));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	public void addParameterDummy(LinkedHashMap<String, Object> param){
		param.put("1", "");
		param.put("2", "");
		param.put("3", "");
		param.put("4", "");
	}
	
	public static final Map CONTENT_TYPE = Collections.unmodifiableMap(new HashMap(2, 1.0f){
        {
        	put("PDF","application/pdf");
        	put("PNG", "image/png");
        	put("ICO", "image/x-icon");
        	put("DOC", "application/msword");
        	put("DOCX","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        	put("XLS", "application/vnd.ms-excel");
        	put("XLSX","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        	put("GIF", "image/gif");
        	put("JPEG","image/jpeg");
        	put("JPE", "image/jpeg");
        	put("JPG", "image/jpeg");
        	put("ZIP", "application/zip");
        }
    });
	
	public class IDENTIFICADORES {
		public static final String VIEW = "VIEW";
		public static final String ID_ESQUEMA="ID_ESQUEMA";
		public static final String ID_COMPONENTE="ID_COMPONENTE";
	}
}
