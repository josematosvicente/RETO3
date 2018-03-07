package org.com.dm.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.com.dm.bean.VistaComponente;
import org.com.dm.util.Util;

public class ReportTag extends TagSupport {
    
	private String reportName;
	private String iconName;
	private String formatReport;
	private String title;
	private String phisical;
	private String table;
	private String formatTable;
//	intro tags
	private String	dataIntro;
	private String	dataStep;
	private String	dataToolTipClass;
	private String	dataHighLightClass;
	private String	dataPosition;
	private String	bycomponente;
	private String	logo;
	private String	style;
	private boolean componente=false;
    public int doStartTag() throws JspException {
    	
        try{
        	VistaComponente vistaComponente = new VistaComponente();
        	vistaComponente.setDataIntro(dataIntro);
        	vistaComponente.setDataStep(dataStep);
        	vistaComponente.setDataPosition(dataPosition);
        	vistaComponente.setDataToolTipClass(dataToolTipClass);
        	vistaComponente.setDataHighLightClass(dataHighLightClass);
        	componente = (bycomponente!=null && !bycomponente.equals(""))? true : false;        	
        	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            String control = "";
            	control = 
            			"<a href='report/"
            			+ reportName
            			+ ((formatReport!=null && !formatReport.equals(""))? "?format="+formatReport : "?format=PDF")
            			+ ((phisical!=null && !phisical.equals(""))? "&phisical="+phisical : "&phisical=true")
            			+ ((logo!=null && !logo.equals(""))? "&logo="+logo : "");
    			if(!componente){
    					control = control + ((table!=null && !table.equals(""))? "&table="+table:"");
    			}else{
    					control = control + ((table!=null && !table.equals(""))? "&report="+table:"");
    			}
                control = control
            			+ ((formatTable!=null && !formatTable.equals(""))? "&formatTable="+formatTable:"")
            			+ "' "
            			+ "id= report_"+ reportName+" ";
    			if(componente){
    					control = control 
    							+ "onclick='makeReportLink"+reportName+"(this)' ";
    			}
                control = control
            			+ Util.buildIntro(vistaComponente)
//            			+ " target='_blank'>"
						+ " target=''>";
//                if(!iconName.equals("button")){
                control = control
            			+ "<p "
            			+ "id= img_"+ reportName+" "
            			+ "style= "+ (style==null?"":style)+" "
            			+ "onclick = 'onclick_" + reportName + "()' " 
            			+ ((iconName!=null && !iconName.equals(""))? "class='"+iconName+"' " : "class='ico_printer' ")
            			+ ((title!=null && !title.equals(""))? "title='"+title+"' " : "title='Exportar' ")
            			+ ""
            			+ "/></p>";
//                }else{
//                	control = control
//                			+ "<input type='button' "
//                			+ "onclick = 'onclick_" + reportName + "()' " 
//                			+ ((title!=null && !title.equals(""))? " value='"+title+"' " : "value='Exportar' ")
//                			+ "/>";
//                }
				
				control = control
            			+ "</a>";
    			if(componente){
					control = control 
            			+ "<script>\n"
            			+ "var report_"+ reportName+"_basicLink='reportCom/"
            			+ reportName
            			+ ((formatReport!=null && !formatReport.equals(""))? "?format="+formatReport : "?format=PDF")
            			+ ((phisical!=null && !phisical.equals(""))? "&phisical="+phisical : "&phisical=true")
            			+ ((formatTable!=null && !formatTable.equals(""))? "&formatTable="+formatTable:"")
            			+ ((table!=null && !table.equals(""))? "&report="+table:"")
            			+ ((logo!=null && !logo.equals(""))? "&logo="+logo : "")
            			+ "';\n"
            			+ "var report_"+ reportName+"= [];\n"
            			+ "function add"+reportName+"(value,position){\n"
            			+ "report_"+ reportName+"[position]=value;\n"
            			+ "}\n"
            			+ "function clean"+reportName+"(){\n"
            			+ "report_"+ reportName+"=[];\n"
            			+ "}\n"
            			+ "function makeReportLink"+reportName+"(){\n"
            			//+ " showLoading('generando reporte...porfavor, espere.'); \n"
            			+ "if(typeof(function"+ reportName+")==\"function\"){\n"
            			+ "function"+ reportName+"();\n"
            			+ "}\n"
            			+ "cantidad = report_"+ reportName+".length;\n"
            			+ "newLink = '' + report_"+ reportName+"_basicLink;\n"
            			+ "for (i = 0; i < cantidad; i++) {\n"
            			+ " newLink=newLink+'&param__'+i+'='+report_"+ reportName+"[i];\n"
            			+ "}\n"
            			+ "$('#report_"+ reportName+"').attr('href', newLink);\n"
            			//+ " closeMessage();\n"
            			+ "}\n"
            			+ "</script>";
    			}
            //Imprime en jsp.
            JspWriter out = pageContext.getOut();
            out.println(control);
        } catch (IOException e) {
            throw new JspException ("Error: IOException" + e.getMessage());
        }
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public void setFormatReport(String formatReport) {
		this.formatReport = formatReport;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPhisical(String phisical) {
		this.phisical = phisical;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setFormatTable(String formatTable) {
		this.formatTable = formatTable;
	}

	public String getDataIntro() {
		return dataIntro;
	}

	public void setDataIntro(String dataIntro) {
		this.dataIntro = dataIntro;
	}

	public String getDataStep() {
		return dataStep;
	}

	public void setDataStep(String dataStep) {
		this.dataStep = dataStep;
	}

	public String getDataToolTipClass() {
		return dataToolTipClass;
	}

	public void setDataToolTipClass(String dataToolTipClass) {
		this.dataToolTipClass = dataToolTipClass;
	}

	public String getDataHighLightClass() {
		return dataHighLightClass;
	}

	public void setDataHighLightClass(String dataHighLightClass) {
		this.dataHighLightClass = dataHighLightClass;
	}

	public String getDataPosition() {
		return dataPosition;
	}

	public void setDataPosition(String dataPosition) {
		this.dataPosition = dataPosition;
	}

	public String getBycomponente() {
		return bycomponente;
	}

	public void setBycomponente(String bycomponente) {
		this.bycomponente = bycomponente;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	
	
}
