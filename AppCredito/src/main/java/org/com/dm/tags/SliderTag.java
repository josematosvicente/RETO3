package org.com.dm.tags;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.com.dm.bean.VistaComponente;
import org.com.dm.bean.VistaComponenteList;
import org.com.dm.util.Constants;
import org.com.dm.util.Util;
import org.com.dm.web.controller.ControllerSessionComponent;

public class SliderTag extends TagSupport {

	private String componente;
	private String orientation = "horizontal";
	private String range = "min";
	private String min;
	private String max;
	private String value;
	private String step;
	private String functionEventSlide;
	private String functionEventChange;
//	intro tags
	private String	dataIntro;
	private String	dataStep;
	private String	dataToolTipClass;
	private String	dataHighLightClass;
	private String	dataPosition;
	public int doStartTag() throws JspException {
        try{
        	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
	        VistaComponenteList vistaComponenteList = new ControllerSessionComponent().getActiveComponentsViewList(request);
            VistaComponente vistaComponente = vistaComponenteList.getComponentByIdComponente(componente);
            vistaComponente.setDataIntro(dataIntro);
        	vistaComponente.setDataStep(dataStep);
        	vistaComponente.setDataPosition(dataPosition);
        	vistaComponente.setDataToolTipClass(dataToolTipClass);
        	vistaComponente.setDataHighLightClass(dataHighLightClass);
            String control = "";
            if(vistaComponente!=null){
            	control="<div "
        				+ "name='"+ vistaComponente.getNameComponent() +"' "
        				+ "id='"+ vistaComponente.getId_componente() +"' "
        				+ Util.buildIntro(vistaComponente)
        				+ "/>"
        				+ "<script>$(document).ready(function(){$('#"+vistaComponente.getId_componente()+"').slider({"
        				+ "orientation: '" + orientation + "', \n"
        				+ "range: '" + range + "', \n"
        				+ (min != null?"min: " + min:"") + ", \n"
        				+ (max != null?"max: " + max:"") + ", \n"
        				+ (value != null?"value: " + value:"") + ", \n"
        				+ (step != null?"step: " + step:"") + ", \n"
        				+ (functionEventSlide != null?"slide: " + functionEventSlide: "")+ ",\n"
        				+ (functionEventChange != null?"change: " + functionEventChange:"")+ "\n"
        				+ "});})</script>";
        				
            	
            	
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
	
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getFunctionEventSlide() {
		return functionEventSlide;
	}
	public void setFunctionEventSlide(String functionEventSlide) {
		this.functionEventSlide = functionEventSlide;
	}
	public String getFunctionEventChange() {
		return functionEventChange;
	}
	public void setFunctionEventChange(String functionEventChange) {
		this.functionEventChange = functionEventChange;
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
	
	
}
