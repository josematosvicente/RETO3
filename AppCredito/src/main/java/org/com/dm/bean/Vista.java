package org.com.dm.bean;

import java.util.HashMap;

public class Vista {

	private String id_vista;
	private String descripcion;
	private String page;
	private String forward;
	private String regla_navegacion;
	private String vista;
	private String publico;
	public void createView(HashMap bean){
		id_vista 		= bean.get("ID_VISTA")==null?"":bean.get("ID_VISTA").toString().trim();
		descripcion 	= bean.get("DESCRIPCION")==null?"":bean.get("DESCRIPCION").toString().trim();
		page 			= bean.get("PAGE")==null?"":bean.get("PAGE").toString().trim();
		regla_navegacion= bean.get("REGLA_NAVEGACION")==null?"":bean.get("REGLA_NAVEGACION").toString().trim();
		vista 			= bean.get("VISTA")==null?"":bean.get("VISTA").toString().trim();
		publico 		= (bean.get("PUBLICO")==null||bean.get("PUBLICO").equals(""))
								?"0":bean.get("PUBLICO").toString().trim();
	}
	@Override
	public String toString() {
		return "Vista [id_vista=" + id_vista + ", descripcion=" + descripcion + ", page=" + page + ", regla_navegacion="
				+ regla_navegacion + ", vista=" + vista + "]";
	}
	public String getId_vista() {
		return id_vista;
	}
	public void setId_vista(String id_vista) {
		this.id_vista = id_vista;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRegla_navegacion() {
		return regla_navegacion;
	}
	public void setRegla_navegacion(String regla_navegacion) {
		this.regla_navegacion = regla_navegacion;
	}
	public String getVista() {
		return vista;
	}
	public void setVista(String vista) {
		this.vista = vista;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getPublico() {
		return publico;
	}
	public void setPublico(String publico) {
		this.publico = publico;
	}
}
