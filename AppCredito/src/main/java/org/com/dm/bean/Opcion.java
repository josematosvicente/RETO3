package org.com.dm.bean;

import java.util.ArrayList;
import java.util.List;

public class Opcion {

	private Integer id;
	private Integer parentId;
	private String descripcion;
	private String url;
	private Integer nivel;
	private List<Opcion>listOpciones;
	
	public Opcion(Integer id,Integer parentId,String descripcion,String url,Integer nivel){
		this.id = id;
		this.parentId = parentId;
		this.descripcion = descripcion;
		this.url = url;
		this.nivel = nivel;
		this.listOpciones = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public List<Opcion> getListOpciones() {
		return listOpciones;
	}
	public void setListOpciones(List<Opcion> listOpciones) {
		this.listOpciones = listOpciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
