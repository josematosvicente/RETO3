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

public class SpinnerTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String componente;
	private Integer step = 1;
	private String format = "n";
	private String valorMinimo;
	private String valorMaximo;
	private String placeholder;
	private String value;
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
	            
	            if(vistaComponente.getValue()!=null && vistaComponente.getValue().toString().compareTo("")!=0){
	            	value = vistaComponente.getValue().toString();
	            }else{
	            	if(value==null){
	            		value="";
	            	}
	            }
	            String control = "";
	            if(vistaComponente!=null){
	            	control="<input "
	        				+ "name='"+ vistaComponente.getNameComponent() +"' "
	        				+ "id='"+ vistaComponente.getId_componente() +"' "
	        				+ "placeholder='"+ (placeholder==null?"":placeholder) +"' "
	        				+ "class='spinner' "
	        				+ "value='"+value+"' "
	        				+ Util.buildIntro(vistaComponente)
	        				+ (vistaComponente.getTabIndex()!=null?("tabindex='"+vistaComponente.getTabIndex())+"' ":"") 
	        				+ ((new BigDecimal(vistaComponente.getLongitud()).compareTo(Constants.ONE_NEGATIVE_BIGDECIMAL) != 0)? "maxlength='"+ vistaComponente.getLongitud() +"' " : "")
	        				+ ((vistaComponente.getRequerido().compareTo(Constants.ONE_STRING)==0)? "class='required error' " : "")
	        				+ vistaComponente.getAccion() +" "
	        				+ ((vistaComponente.getReadonly() .compareTo(Constants.ONE_STRING)==0)? "readonly " : "")
	        				+ "/>\n"
	        				+ "<script>\n"
	        				+ "$(document).ready(function(){\n"
	        				+ "$('#"+vistaComponente.getId_componente()+"').spinner({\n"
	        				+ (format != null?"numberFormat: '" + format:"") + "', \n"
	    	        		+ (step != null?"step: " + step:"") + ", \n"
	        				+ (valorMinimo != null?"min: " + valorMinimo:"") + ", \n"
	        				+ (valorMaximo != null?"max: " + valorMaximo:"") + ", \n"
	        				//+ "}).bind('keydown', function (event){event.preventDefault();});\n"
	        				+ "});\n"
	        				+ "});\n"
	        				+ "</script>\n"
	        				+ "<script>$(document).ready(function(){$('#"+vistaComponente.getId_componente()+"').numeric();});</script>\n"
	        				+ "<script>\n"
	        				+ "$('#"+vistaComponente.getId_componente()+"').keyup(function(){\n";
	        		if(valorMinimo != null && valorMinimo.compareTo("")!=0){
		            	control = control
		            		+ "if( $('#"+vistaComponente.getId_componente()+"').val() < "+valorMinimo+" ){ \n"
		            		+ "    $('#"+vistaComponente.getId_componente()+"').val('"+valorMinimo+"'); \n"
		            		+ "}; \n";
	        		}
	        		if(valorMaximo != null && valorMaximo.compareTo("")!=0){
		            	control = control
		            		+ "if( $('#"+vistaComponente.getId_componente()+"').val() > "+valorMaximo+" ){ \n"
		            		+ "    $('#"+vistaComponente.getId_componente()+"').val('"+valorMaximo+"'); \n"
		            		+ "}; \n";
	        		}
	            	control = control
	        				+ "});\n"
	        				+ "</script>\n";
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
	
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(String valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public String getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(String valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
