package org.com.dm.report;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.com.dm.bean.VistaComponente;
import org.com.dm.table.CustomBeanFactory;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TemplateFileReport {

	private Map params = new HashMap();
	private String rutaFull;
	private String typeReport;
	private boolean hasDetail = false;
	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;

	public void executeReport() throws Exception {
		dr = buildReport();
		JRDataSource ds = getDataSource();
		jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
		if (ds != null){
			jp = JasperFillManager.fillReport(jr, params, ds);
		}else{
			jp = JasperFillManager.fillReport(jr, params);
		}
	}
	
	public DynamicReport buildReport() throws Exception {
		DynamicReportBuilder drb = new DynamicReportBuilder();
		
		Style titleStyle = new Style();
		titleStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		Font font = new Font(9, "SansSerif", false);
		titleStyle.setFont(font);
		
		Style headerStyle = new Style();
		headerStyle.setBackgroundColor(new Color(49,97,170));
		headerStyle.setBorderBottom(Border.THIN());
		headerStyle.setTextColor(Color.WHITE);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.TOP);
		headerStyle.setTransparency(Transparency.OPAQUE);
		headerStyle.setFont(font);

		Style detailStyle = new Style();
		detailStyle.setFont(font);

		drb.setTitle(
				(VistaComponente)params.get("DATA")==null?"":
				((VistaComponente)params.get("DATA")).getDescripcion()
				)
			.setDetailHeight(15)
			.setDefaultStyles(titleStyle, null, headerStyle, detailStyle);		
		if(hasDetail){
			LinkedHashMap<String, String> columnsName = 
					((CustomBeanFactory)((VistaComponente)params.get("DATA")).getValue()).getColumnsName();
			for(Entry<String,String> columnName:columnsName.entrySet()){
				AbstractColumn column = ColumnBuilder.getNew()
						.setColumnProperty(columnName.getKey(), String.class.getName())
						.setTitle(columnName.getValue())
						.build();
				if(typeReport.compareTo("CSV")==0){
					column.setFixedWidth(true);
					//drb.setAllowDetailSplit(true);
				}
				drb.addColumn(column);
			}
		}else{
			drb.setWhenNoDataAllSectionNoDetail();
		}
		drb.setUseFullPageWidth(true);
		drb.setIgnorePagination(true);
		drb.setTemplateFile(rutaFull,true,true,true,true);
		DynamicReport dr = drb.build();
		return dr;
	}

	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
/*
	protected void exportReport() throws Exception {
		ReportExporter.exportReport(jp, System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getName() + ".pdf");
		exportToJRXML();
	}
	
	protected void exportToJRXML() throws Exception {
		if (this.jr != null){
			DynamicJasperHelper.generateJRXML(this.jr, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getName() + ".jrxml");
			
		} else {
			DynamicJasperHelper.generateJRXML(this.dr, this.getLayoutManager(), this.params, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getName() + ".jrxml");
		}
	}	

	protected void exportToHTML() throws Exception {
		ReportExporter.exportReportHtml(this.jp, System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getName() + ".html");
	}	
*/
	/**
	 * @return JRDataSource
	 */
	protected JRDataSource getDataSource() {
		/*
		Collection dummyCollection = CustomBeanFactory.getBeanCollection();
		dummyCollection = SortUtils.sortCollection(dummyCollection,dr.getColumns());
		JRDataSource ds = new JRBeanCollectionDataSource(dummyCollection);		//Create a JRDataSource, the Collection used																	//here contains dummy hardcoded objects...
		return ds;
		*/
		if(hasDetail){
			Collection dummyCollection = ((CustomBeanFactory)((VistaComponente)params.get("DATA")).getValue()).getBeanCollection();
			//dummyCollection = SortUtils.sortCollection(dummyCollection,dr.getColumns());
			JRDataSource ds = new JRBeanCollectionDataSource(dummyCollection);
			return ds;
		}else{
			Collection dummyCollection = new CustomBeanFactory().getBeanCollectionDummy();
			JRDataSource ds = new JRBeanCollectionDataSource(dummyCollection);
			return ds;
		}
	}

	public DynamicReport getDynamicReport() {
		return dr;
	}
		
    public JasperPrint getJasperPrint() {
		return jp;
	}

	public void setParams(Map params) {
		this.params = params;
		if(params.get("DATA")!=null){
			hasDetail = true;
		}
	}

	public void setRutaFull(String rutaFull) {
		this.rutaFull = rutaFull;
	}

	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}

}