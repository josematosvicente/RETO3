package org.com.dm.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String VIEW_INDEX = "index";
	public static final String CONSULTA = "CONS";
	public static final String SAVE = "SAVE";
	public static final String VIEW_BANDEJA = "bandeja";
	public static final String RESPONSE_OK = "0";
	public static final String PROCESS_MESSAGE = "PROCESS_MESSAGE";
	public static final String PROCESS_MESSAGE2 = "PROCESS_MESSAGE2";
	
	public static String RUTA_SERVICES = "";
	public static String RUTA_RECURSOS = "";
	public static String AREA_RECLAMO = "";
	
	public static String REST_SERVICE_VALIDATE_TOKEN =  "/validateToken/";
	public static String REST_SERVICE_VALIDATE_PUBLIC =  "/validatePublic/";
	public static String REST_SERVICE_COMPONENT =  "/component/";
	public static String REST_SERVICE_SAVE =  "/component/save/";
	public static String REST_SERVICE_READ =  "/component/read/";
	public static String REST_SERVICE_SAVE_CLOB =  "/component/saveclob/";
	public static String REST_SERVICE_MAIL =  "/component/mail/";
	public static String REST_SERVICE_COMPONENTES= "/components";
	public static String REST_SERVICE_COMPONENTES_PARAMETERES= "/parameter/";
	public static String REST_SERVICE_COMPONENTES_VIEW= "/view";
	public static String REST_SERVICE_PENTAHO =  "/pentaho/";
	
	public static final Integer ZERO_INTEGER = 0;
	public static final String  ZERO_STRING = "0";
	public static final String  ONE_STRING = "1";
	public static final String  TWO_STRING = "2";
	public static final String  THREE_STRING = "3";
	public static final String  ONE_NEGATIVE_STRING = "-1";
	public static final BigDecimal  ONE_NEGATIVE_BIGDECIMAL = new BigDecimal("-1");
	public static final Integer DIAS_MAXIMO_RECLAMO = 30;
	
	public static final String  DELIMITER_OBJECT = "__";
	
	public static final String PARAM_NAME_ADJUNTO = "nameAdjunto";
	public static final String PARAM_ADJUNTO = "adjunto";
	public static final String PARAM_TYPE_ADJUNTO = "typeAdjunto";
	public static final String PARAM_CANTIDAD_ADJUNTO = "cantidadAdjunto";
	
	public static final String PARAM_LIST_ADJUNTO = "listAdjunto";
	
	
	public static  String SVNURL;
	public static  String SVNURLDESARROLLO;
	public static  String SVNURLCALIDAD;
	public static  String SVNURLPRODUCCION;
	
	public static String SVNUSUARIODESARROLLO;
	public static String SVNPASSWORDDESARROLLO;
	public static String SVNUSUARIOCALIDAD;
	public static String SVNPASSWORDCALIDAD;
	public static String SVNUSUARIOPRODUCCION;
	public static String SVNPASSWORDPRODUCCION;
	
	public static final String AMBIENTE_DESARROLLO = "DESARROLLO";
	public static final String AMBIENTE_CALIDAD = "CALIDAD";
    public static final String AMBIENTE_PRODUCCION = "PLATAFORMA";
	
	
	public class Components {
		public static final String RADIOBUTTON = "RADIO";
		public static final String CHECKBOX = "CHECK";
		public static final String PARAM = "PARAM";
		public static final String COMBO = "COMBO";
		public static final String TEXTO = "TEXTO";
		public static final String HIDDEN = "HIDDEN";
		public static final String SAVE = "SAVE";
		public static final String SAVECLOB = "SAVECLOB";
		public static final String AREA = "AREA";
		public static final String TABLA = "TABLA";
		public static final String TREETABLA = "TREETABLA";
		public static final String EMAIL = "EMAIL";
		public static final String NUMERIC = "NUMERIC";
		public static final String LOAD = "LOAD";
		public static final String RULE = "RULE";
		public static final String LABEL = "LABEL";
		public static final String FILE = "FILE";
		public static final String DATETEXT = "DATETEXT";
		public static final String ATTACHEDFILE = "AFILE";
		public static final String ATTACHEDCHECK = "ACHECK";
		public static final String PASSWORD = "PASSWORD";
		public static final String MAIL = "CORREO";
		public static final String SPINNER = "SPINNER";
		public static final String SESSION = "SESSION";
		public static final String SESSION_USER = "SESSION_USER";
		public static final String CUSTOM_TABLE = "CTABLE";
		public static final String COMMIT = "COMMIT";
		public static final String LINE = "LINE";
		public static final String BAR = "BAR";
		public static final String DONUT = "DONUT";
		public static final String STACKED = "STACKED";
		public static final String MENU = "MENU";
		public static final String MENU2 = "MENU2";
		public static final String TREE_AJAX = "TREEAJAX";
	}
	
	public class Session {
		public static final String VIEW = "VIEW";
		public static final String VIEW2 = "VIEW2";
		public static final String VIEW3 = "VIEW3";
		public static final String PROCESS = "PROCESS";
		public static final String PROCESS2 = "PROCESS2";
		public static final String PROCESS3 = "PROCESS3";
		public static final String VIEW_COMPONENTS = "VIEWCOMPONENTS";
		public static final String VIEW_COMPONENTS2 = "VIEWCOMPONENTS2";
		public static final String VIEW_COMPONENTS3 = "VIEWCOMPONENTS3";
		public static final String MESSAGE_LOGIN = "MESSAGE_LOGIN";
		public static final String MESSAGE_LOGIN_DATA = "MESSAGE_LOGIN_DATA";
		public static final String VISTA_LOGIN = "PROCESSLOGIN";
		public static final String VISTA_EXTERNALACCESS = "PROCESSEXTERNALACCESS";
		public static final String EXTERNALACCESS = "EXTERNALACCESS";
		public static final String LOGIN = "LOGIN";
		public static final String MESSAGE = "MESSAGE";
		public static final String MESSAGE_DATA = "MESSAGE_DATA";
		public static final String SESSION_VAR_COMPONENT = "$Session";
		public static final String NIVEL = "NIVEL";
	}
		
	public class IDENTIFICADORES {
		public static final String VIEW = "VIEW";
		public static final String ID_ESQUEMA="ID_ESQUEMA";
		public static final String ID_COMPONENTE="ID_COMPONENTE";
	}
	
	
	@SuppressWarnings("unchecked")
	public static final Map CONTENT_TYPE = Collections.unmodifiableMap(new HashMap(2, 1.0f){
        {
        	put("PDF","application/pdf");
        	put("TXT","application/msword");
        	put("SQL","application/msword");
        	put("PNG", "image/png");
        	put("ICO", "image/x-icon");
        	put("DOC", "application/msword");
        	put("DOCX","application/msword");
        	put("XLS", "application/vnd.ms-excel");
        	put("XLSX","application/vnd.ms-excel");
        	put("GIF", "image/gif");
        	put("JPEG","image/jpeg");
        	put("JPE", "image/jpeg");
        	put("JPG", "image/jpeg");
        	put("ZIP", "application/zip");
        	put("WAR", "application/zip");
        	put("RAR", "application/zip");
        	put("XPS", "application/vnd.ms-xpsdocument");
        }
    });
	
	@SuppressWarnings("unchecked")
	public static final Map POSITION_TYPE = Collections.unmodifiableMap(new HashMap(2, 1.0f){
        {
        	put("T","top");
        	put("B", "bottom");
        	put("R", "right");
        	put("L","left");
        }
    });
	
	public class RolesSistema{
		public static final int PREVISION_GESTOR = 3;
		public static final int PREVISION_EJECUTIVO = 4;
		public static final int PREVISION_ASESOR = 5;
		
		public static final int SEGURIDAD_GENERAL = 6;
		
		public static final int RECLAMO_ADMIN = 7;
		public static final int RECLAMO_RECLAMOS = 11;
		
		public static final int REQUERIMIENTO_ADMIN = 8;
		public static final int REQUERIMIENTO_USUARIO_REGISTRO = 10;
		
		public static final int VIVIENDA_VIVIENDA = 12;
		
		public static final int CREDITO_ADMIN_DATOS = 9;
		public static final int CREDITO_ADMIN_COBRANZA = 13;
		public static final int CREDITO_ADMIN_CREDITO = 14;
		public static final int CREDITO_ADMIN_PREVISION = 15;
		public static final int CREDITO_VALIDADOR = 16;
		
		
	}
}
