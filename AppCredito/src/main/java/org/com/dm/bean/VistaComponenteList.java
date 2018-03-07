package org.com.dm.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.com.dm.util.Constants;

public class VistaComponenteList {

	private String id_vista;
	private Map<String, VistaComponente> mapComponents = new LinkedHashMap<String, VistaComponente>();

	public void createComponentList(Vista vista, List<LinkedHashMap<String, Object>> list){
		id_vista = vista.getId_vista();
		for (LinkedHashMap linkedHashMap : list) {
			VistaComponente vistaComponente = new VistaComponente();
			vistaComponente.createComponent(linkedHashMap);
			mapComponents.put(vistaComponente.getId_componente(), vistaComponente);
		}
	}
	public VistaComponente getComponentByNameComponente(String nameComponent){
		return (VistaComponente)mapComponents.get(nameComponent);
	}
	public VistaComponente getComponentByIdComponente(String idComponent){
		for(VistaComponente vistaComponent : mapComponents.values()){ 
			if(vistaComponent.getId_componente().compareTo(idComponent)==0){
				return vistaComponent;
			}
		}
		return null;
	}
	public String getReportValueByComponentId(String idComponent){
		String value = "";
		for(VistaComponente vistaComponent : mapComponents.values()){ 
			if(vistaComponent.getId_componente().compareTo(idComponent)==0){
				if(vistaComponent.getId_tipo_componente().
						compareTo(Constants.Components.COMBO)==0){
					value = vistaComponent.getLabel_value(); 
				}else if(vistaComponent.getId_tipo_componente().
						compareTo(Constants.Components.CHECKBOX)==0){
					value = vistaComponent.getLabel_value(); 
				}else if(vistaComponent.getId_tipo_componente().
						compareTo(Constants.Components.RADIOBUTTON)==0){
					value = vistaComponent.getLabel_value(); 
				}else{
					value = (String) ((vistaComponent==null)?"":vistaComponent.getValue());
				}
			}
		}
		return value;
	}
	@Override
	public String toString() {
		return "VistaComponenteList [id_vista=" + id_vista + ", mapComponents=" + mapComponents + "]";
	}

	public String getId_vista() {
		return id_vista;
	}

	public void setId_vista(String id_vista) {
		this.id_vista = id_vista;
	}
	public Map<String, VistaComponente> getMapComponents() {
		return mapComponents;
	}

	public void setMapComponents(Map<String, VistaComponente> mapComponents) {
		this.mapComponents = mapComponents;
	}
	
}
