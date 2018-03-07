package org.com.dm.web.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.com.dm.bean.Vista;
import org.com.dm.bean.VistaComponenteList;
import org.com.dm.util.Constants;

public class ControllerSessionComponent {
	
	/*
	 * Tipos de Vista:
	 * 0.- Seguridad
	 * 1.- Formulario
	 * 2.- Formulario Modal
	 * 3.- Formulario Persistente en Session
	 * */
	public void controlViewActive(
			HttpServletRequest request,
			Vista vistaRequest){
		if(request!=null){
			Vista vistaSession = (Vista)request.getSession().getAttribute(Constants.Session.VIEW);
			request.getSession().setAttribute(Constants.Session.NIVEL, vistaRequest.getVista()==null?"":vistaRequest.getVista());
			if(vistaRequest.getVista().compareTo(Constants.ZERO_STRING)==0 ||
					vistaRequest.getVista().compareTo(Constants.ONE_STRING)==0){
				if(vistaSession!=null && 
						vistaSession.getId_vista().compareTo(vistaRequest.getId_vista())!=0){
					request.getSession().removeAttribute(Constants.Session.VIEW_COMPONENTS);
				}
				request.getSession().setAttribute(Constants.Session.VIEW, vistaRequest);
				request.getSession().setAttribute(Constants.Session.PROCESS, vistaRequest);
			}
			else if(vistaRequest.getVista().compareTo(
					Constants.TWO_STRING)==0){
				if(vistaSession!=null && 
						vistaSession.getId_vista().compareTo(vistaRequest.getId_vista())!=0){
					request.getSession().removeAttribute(Constants.Session.VIEW_COMPONENTS2);
				}
				request.getSession().setAttribute(Constants.Session.VIEW2, vistaRequest);
				request.getSession().setAttribute(Constants.Session.PROCESS2, vistaRequest);
			}
			else if(vistaRequest.getVista().compareTo(
					Constants.THREE_STRING)==0){
				request.getSession().setAttribute(Constants.Session.VIEW3, vistaRequest);
				request.getSession().setAttribute(Constants.Session.PROCESS3, vistaRequest);
			}else{
				request.getSession().setAttribute(Constants.Session.PROCESS, vistaRequest);
			}
		}
	}

	public void controlComponents(
			HttpServletRequest request,
			Vista vistaRequest,
			List<LinkedHashMap<String, Object>> componentsRequestList){
		if(request!=null){
			request.getSession().setAttribute(Constants.Session.NIVEL, vistaRequest.getVista());
			if(vistaRequest.getVista().compareTo(
					Constants.ONE_STRING)==0){
				VistaComponenteList vistaComponenteList = new VistaComponenteList();
				vistaComponenteList.createComponentList(vistaRequest, componentsRequestList);
				request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS, vistaComponenteList);
			}
			else if(vistaRequest.getVista().compareTo(
					Constants.TWO_STRING)==0){
				VistaComponenteList vistaComponenteList = new VistaComponenteList();
				vistaComponenteList.createComponentList(vistaRequest, componentsRequestList);
				request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS2, vistaComponenteList);
			}
			else if(vistaRequest.getVista().compareTo(
					Constants.THREE_STRING)==0){
				VistaComponenteList vistaComponenteList = new VistaComponenteList();
				vistaComponenteList.createComponentList(vistaRequest, componentsRequestList);
				if(request.getSession().getAttribute(Constants.Session.VIEW_COMPONENTS3)==null){
					request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS3, vistaComponenteList);
				}
			}
		}
	}
	
	public Vista getActiveView(HttpServletRequest request){
		String name = Constants.Session.VIEW;
		String nivel = request.getSession().getAttribute(Constants.Session.NIVEL).toString();
		if(nivel.equals(Constants.TWO_STRING)){
			name = Constants.Session.VIEW2;
		}
		if(nivel.equals(Constants.THREE_STRING)){
			name = Constants.Session.VIEW3;
		}
		return (Vista)request.getSession().getAttribute(name);
	}
	
	public VistaComponenteList getActiveComponentsViewList(HttpServletRequest request){
		String nivel = request.getSession().getAttribute(Constants.Session.NIVEL).toString();
		if(nivel.equals(Constants.TWO_STRING))
			return (VistaComponenteList)request.getSession().getAttribute(Constants.Session.VIEW_COMPONENTS2);	
		else if(nivel.equals(Constants.THREE_STRING))
			return (VistaComponenteList)request.getSession().getAttribute(Constants.Session.VIEW_COMPONENTS3);	
		else
			return (VistaComponenteList)request.getSession().getAttribute(Constants.Session.VIEW_COMPONENTS);
	}
	
	public VistaComponenteList getComponentsViewListById(HttpServletRequest request, String idVista){
			return (VistaComponenteList)request.getSession().getAttribute(idVista);
	}
	
	public VistaComponenteList getMessageDataLogin2(HttpServletRequest request){
		return (VistaComponenteList)request.getSession().getAttribute(Constants.Session.MESSAGE_LOGIN_DATA);
	}
	
	public void setActiveComponentsViewList(
			HttpServletRequest request, 
			VistaComponenteList listComponent){
		String nivel = request.getSession().getAttribute(Constants.Session.NIVEL).toString();
		if(nivel.equals(Constants.THREE_STRING))
			request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS3, listComponent);
		else if(nivel.equals(Constants.TWO_STRING))
			request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS2, listComponent);
		else
			request.getSession().setAttribute(Constants.Session.VIEW_COMPONENTS, listComponent);
	}
	
	public void controlParam(HttpServletRequest request, 
			String nameComponent,
			Object value) {
		if(request!=null){
			VistaComponenteList listComponent = getActiveComponentsViewList(request);
			if(listComponent != null){
				if(listComponent.getComponentByNameComponente(nameComponent)!=null){
					listComponent.getComponentByNameComponente(nameComponent).setValue(value);
					setActiveComponentsViewList(request, listComponent);
				}
			}
		}
	}
	
	public void controlValueLabel(HttpServletRequest request, 
			String nameComponent,
			String value) {
		if(request!=null){
			VistaComponenteList listComponent = getActiveComponentsViewList(request);
			if(listComponent != null){
				if(listComponent.getComponentByNameComponente(nameComponent)!=null){
					listComponent.getComponentByNameComponente(nameComponent).setLabel_value(value);
					setActiveComponentsViewList(request, listComponent);
				}
			}
		}
	}
	
}