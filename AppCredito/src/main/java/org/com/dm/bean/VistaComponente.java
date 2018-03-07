package org.com.dm.bean;

import java.util.LinkedHashMap;

import org.com.dm.util.Constants;

/**
 * @author jmatos
 *
 */
public class VistaComponente {
	//Campos de tabla configuracion
	private String id_vista;
	private String id_componente;
	private String id_tipo_componente;
	private String id_esquema;
	private String id_package;
	private String procedure;
	private String descripcion;
	private String nombre_parametro;
	private String orden;
	private String accion;
	private String requerido;
	private String longitud;
	private String readonly;
	private boolean multipleSelect;
	private String title;
	private String multipleLabel;
	private String header;
	//Campos de negocio
	private Object value;
	private String label_value;
	private String nameComponent;
	private String tabIndex;
	private String placeholder;
	private String style;
	private String fileName;
	private String fileExtension;
	private String initial;
	private boolean onlyAlpha;
	private String datePickerBeginTo;
	private String minDate;
	private String maxDate;
	private String range;
	private String defaultDate;
	private String indexGroup;
//	intro tags
	private String	dataIntro;
	private String	dataStep;
	private String	dataToolTipClass;
	private String	dataHighLightClass;
	private String	dataPosition;
	private boolean	showPreview;
	private boolean	linkEliminar;
	
//	autocomplete
	private String autocomplete;
	private Integer autocompleteSize;
	private Integer showHeader;
	private String clase;
	private String mostrarFilter;
	
	public void createComponent(LinkedHashMap bean){
		id_vista 			= bean.get("ID_VISTA")==null?"":bean.get("ID_VISTA").toString().trim().toUpperCase();
		id_componente 		= bean.get(Constants.IDENTIFICADORES.ID_COMPONENTE)==null?"":bean.get(Constants.IDENTIFICADORES.ID_COMPONENTE).toString().trim().toUpperCase();
		id_tipo_componente 	= bean.get("ID_TIPO_COMPONENTE")==null?"":bean.get("ID_TIPO_COMPONENTE").toString().trim().toUpperCase();
		id_esquema	 		= bean.get(Constants.IDENTIFICADORES.ID_ESQUEMA)==null?"":bean.get(Constants.IDENTIFICADORES.ID_ESQUEMA).toString().trim().toUpperCase();
		id_package 			= bean.get("PACKAGE")==null?"":bean.get("PACKAGE").toString().trim().toUpperCase();
		procedure	 		= bean.get("PROCEDURE")==null?"":bean.get("PROCEDURE").toString().trim().toUpperCase();
		descripcion 		= bean.get("DESCRIPCION")==null?"":bean.get("DESCRIPCION").toString().trim().toUpperCase();
		nombre_parametro 	= bean.get("NOMBRE_PARAMETRO")==null?"":bean.get("NOMBRE_PARAMETRO").toString().trim().toUpperCase();
		orden 				= bean.get("ORDEN")==null?"0":bean.get("ORDEN").toString().trim();
		accion 				= bean.get("ACCION")==null?"":bean.get("ACCION").toString().trim();
		requerido 			= bean.get("REQUERIDO")==null?"":bean.get("REQUERIDO").toString().trim();
		longitud 			= bean.get("LONGITUD")==null?"":bean.get("LONGITUD").toString().trim();
		readonly 			= bean.get("READONLY")==null?"":bean.get("READONLY").toString().trim();
		label_value 		= "";
		//value 				= bean.get("VALUE")==null?"":bean.get("VALUE").toString().trim();
		style 				= bean.get("STYLE")==null?"":bean.get("STYLE").toString().trim();
		
		nameComponent = orden + Constants.DELIMITER_OBJECT +id_componente;
	}
	@Override
	public String toString() {
		return "VistaComponente [id_vista=" + id_vista + ", id_componente=" + id_componente + ", id_tipo_componente="
				+ id_tipo_componente + ", id_esquema=" + id_esquema + ", id_package=" + id_package + ", procedure="
				+ procedure + ", descripcion=" + descripcion + ", nombre_parametro=" + nombre_parametro + ", orden="
				+ orden + ", accion=" + accion + ", requerido=" + requerido + ", longitud=" + longitud + ", readonly="
				+ readonly + ", value=" + value + ", nameComponent=" + nameComponent + ", style=" + style + "]";
	}
	public String getId_vista() {
		return id_vista;
	}
	public void setId_vista(String id_vista) {
		this.id_vista = id_vista;
	}
	public String getId_componente() {
		return id_componente;
	}
	public void setId_componente(String id_componente) {
		this.id_componente = id_componente;
	}
	public String getId_tipo_componente() {
		return id_tipo_componente;
	}
	public void setId_tipo_componente(String id_tipo_componente) {
		this.id_tipo_componente = id_tipo_componente;
	}
	public String getId_esquema() {
		return id_esquema;
	}
	public void setId_esquema(String id_esquema) {
		this.id_esquema = id_esquema;
	}
	public String getId_package() {
		return id_package;
	}
	public void setId_package(String id_package) {
		this.id_package = id_package;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre_parametro() {
		return nombre_parametro;
	}
	public void setNombre_parametro(String nombre_parametro) {
		this.nombre_parametro = nombre_parametro;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getNameComponent() {
		return nameComponent;
	}
	public void setNameComponent(String nameComponent) {
		this.nameComponent = nameComponent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getTabIndex() {
		return tabIndex;
	}
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public String getLabel_value() {
		return label_value;
	}
	public void setLabel_value(String label_value) {
		this.label_value = label_value;
	}
	public boolean isOnlyAlpha() {
		return onlyAlpha;
	}
	public void setOnlyAlpha(boolean onlyAlpha) {
		this.onlyAlpha = onlyAlpha;
	}
	public String getDatePickerBeginTo() {
		return datePickerBeginTo;
	}
	public void setDatePickerBeginTo(String datePickerBeginTo) {
		this.datePickerBeginTo = datePickerBeginTo;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	public String getDefaultDate() {
		return defaultDate;
	}
	public void setDefaultDate(String defaultDate) {
		this.defaultDate = defaultDate;
	}
	public String getIndexGroup() {
		return indexGroup;
	}
	public void setIndexGroup(String indexGroup) {
		this.indexGroup = indexGroup;
	}
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public boolean isMultipleSelect() {
		return multipleSelect;
	}
	public void setMultipleSelect(boolean multipleSelect) {
		this.multipleSelect = multipleSelect;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMultipleLabel() {
		return multipleLabel;
	}
	public void setMultipleLabel(String multipleLabel) {
		this.multipleLabel = multipleLabel;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
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
	public boolean isShowPreview() {
		return showPreview;
	}
	public void setShowPreview(boolean showPreview) {
		this.showPreview = showPreview;
	}
	public boolean isLinkEliminar() {
		return linkEliminar;
	}
	public void setLinkEliminar(boolean linkEliminar) {
		this.linkEliminar = linkEliminar;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getAutocomplete() {
		return autocomplete;
	}
	public void setAutocomplete(String autocomplete) {
		this.autocomplete = autocomplete;
	}
	public Integer getAutocompleteSize() {
		return autocompleteSize;
	}
	public void setAutocompleteSize(Integer autocompleteSize) {
		this.autocompleteSize = autocompleteSize;
	}
	public Integer getShowHeader() {
		return showHeader;
	}
	public void setShowHeader(Integer showHeader) {
		this.showHeader = showHeader;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getMostrarFilter() {
		return mostrarFilter;
	}
	public void setMostrarFilter(String mostrarFilter) {
		this.mostrarFilter = mostrarFilter;
	}
	
}
