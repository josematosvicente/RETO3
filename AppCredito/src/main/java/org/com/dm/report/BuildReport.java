package org.com.dm.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.dm.bean.Vista;
import org.com.dm.bean.VistaComponente;
import org.com.dm.bean.VistaComponenteList;
import org.com.dm.table.CustomBeanFactory;
import org.com.dm.util.Constants;
import org.com.dm.util.Lanzador;
import org.com.dm.util.Util;
import org.com.dm.web.controller.ControllerSessionComponent;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

public class BuildReport {
	
	private static final String REPORT_EXPORT_PATH = "D:\\test.pdf";
//	OutputStream oS = null;	
	byte bytes[] = new byte[10000]; 
	
	
	public byte[] generate(String jasper,
			ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response,
			List<LinkedHashMap<String, Object>> listado,
			LinkedHashMap<Integer, Object> parametros,
			String type,
			boolean exportar) 
	{
		ByteArrayOutputStream oS = new ByteArrayOutputStream(); 
		
		try{
			CustomBeanFactory customBeanFactory = new CustomBeanFactory();
			customBeanFactory.setList(listado);
			
//			String rutaFull = request.getSession().getServletContext().getRealPath("");
//		    rutaFull = rutaFull + File.separator +"resources" + File.separator;
			
		long start = System.currentTimeMillis();
		Collection collectionComponente=null;
//		Map map = request.getParameterMap();
		Map<String, Object> parameters = new HashMap<String, Object>();
//	    for (Object key: map.keySet())
//	    {
//	            String keyStr = (String)key;
//	            String[] value = (String[])map.get(keyStr);
//	            parameters.put((String)key , Arrays.toString(value));
//	    }
		//Preparing parameters
		
//		parameters.put("ReportTitle", "Address Report");
//		parameters.put("DataFile", "CustomBeanFactory.java - Bean Collection");
		
	    parameters.put("PARAMETROS", parametros);
//	    if(request.getParameter("logo")==null || request.getParameter("logo").equals("")){
	    	parameters.put("LOGO",  Constants.RUTA_RECURSOS+"/img/logodm.png");
//	    }else{
//	    	parameters.put("LOGO",  Constants.RUTA_RECURSOS+"/img/"+request.getParameter("logo")+".png");
//	    }
	    collectionComponente = customBeanFactory.getBeanCollectionArray();    

		JasperPrint jp = JasperFillManager.fillReport("D:/JOSE/WS_NUEVO/AppCredito/src/main/webapp/resources"+"/reports"+File.separator+jasper+".jasper", parameters, new JRBeanCollectionDataSource(collectionComponente));
//		JasperPrint jp = JasperCompileManager.compileReportToFile(rutaFull+"reports"+File.separator+path+".jasper", parameters, new JRBeanCollectionDataSource(collectionComponente));
//	    JasperPrint jp = JasperFillManager.fillReport(rutaFull+"reports/DataSourceReport.jasper", parameters, new JRBeanCollectionDataSource(CustomBeanFactory1.getBeanCollection()));
		System.err.println("Filling time : " + (System.currentTimeMillis() - start));
		
		
        
        if(type.equals("PDF")){
        	if(exportar) {
        	response.setContentType("application/pdf");
        	response.setHeader("Content-Disposition", "attachment;filename="+jasper+".pdf");
        	}
        	JasperExportManager.exportReportToPdfStream(jp, oS);
        }
        else if(type.equals("XLS")){
        	if(exportar) {
        	response.setContentType("application/xls");
        	response.setHeader("Content-Disposition", "attachment;filename="+jasper+".xls");
        	}
        	JRXlsExporter exporter = new JRXlsExporter();
        	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oS);
            exporter.exportReport();
        }
        else if(type.equals("CSV")){
        	if(exportar) {
        	response.setContentType("application/xls");
        	response.setHeader("Content-Disposition", "attachment;filename="+jasper+".csv");
        	}
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oS);
            exporter.exportReport();
        }
        else if(type.equals("HTML")){
        	if(exportar) {
        	response.setContentType("application/html");
        	response.setHeader("Content-Disposition", "attachment;filename="+jasper+".html");
        	}
        	JRHtmlExporter exporter = new JRHtmlExporter();
        	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oS);
            exporter.exportReport();
        }
        else if(type.equals("DOC")){
        	if(exportar) {
        	response.setContentType("application/msword");
        	response.setHeader("Content-Disposition", "attachment;filename="+jasper+".doc");
        	}
        	JRDocxExporter exporter = new JRDocxExporter();
        	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oS);
            exporter.exportReport();
        }
        
        oS.flush();
        return oS.toByteArray();
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			try{
				if(exportar) {
					bytes = oS.toByteArray(); 
					response.setContentLength(bytes.length); 
					oS.close(); 
					OutputStream ouputStream = response.getOutputStream(); 
					ouputStream.write(bytes, 0, bytes.length); 
					ouputStream.flush(); 
					ouputStream.close(); 
				}else {
					oS.close(); 
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	

}