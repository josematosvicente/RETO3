package org.com.dm.table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import org.com.dm.report.CustomBean;

public class CustomBeanFactory{
	
	private LinkedHashMap<String, String> columnsName = new LinkedHashMap();
	private List<RecordBean> data;
	private RecordBean[] dataArray={new RecordBean()};
	
	private String abc;
	
	
 	public void setList(List<LinkedHashMap<String, Object>> list) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		if(list!=null && !list.isEmpty()){
			//Cabeceras
			LinkedHashMap keys = list.get(0);
			Iterator it = keys.keySet().iterator();
			int j = 1;
			while(it.hasNext()){
				String headName = it.next().toString().trim();
				if(headName.contains("_HDR_")){
					continue;
				}
				if(headName.contains("_HDT_")){
					headName = headName.toUpperCase().replaceAll("_HDT_", " ");
				}
				if(headName.toString().contains("ADJUNTO__")){
					headName="ADJUNTOS";
				}
				headName = headName.toUpperCase().replaceAll("_", " ");
				columnsName.put(
						"column"+((j<10)?"0"+j:j).toString(),
						headName
						);
				j++;
			}
			//Data
			data = new ArrayList<RecordBean>();
			dataArray = new RecordBean[list.size()];
			int k = 0;
			for (LinkedHashMap linkedHashMap : list) {
				RecordBean bean = new RecordBean();
				int i = 1;
				Iterator it2 = linkedHashMap.keySet().iterator();
				while(it2.hasNext()){
				  Object key = it2.next();
				  if(key.toString().contains("_HDR_")){
						continue;
					}
				  linkedHashMap.get(key);
				  bean.getClass().getDeclaredFields();
				  bean.getClass().getDeclaredMethods();
				  bean.getClass().getDeclaredField("column"+((i<10)?"0"+i:i).toString()).setAccessible(true);
				  Method metodo = bean.getClass().getDeclaredMethod("setColumn"+((i<10)?"0"+i:i).toString(), String.class);
				  metodo.invoke(bean, linkedHashMap.get(key)==null?"":linkedHashMap.get(key).toString()); 
				  i++;
				}
				data.add(bean);
				dataArray[k]=bean;
				k=k+1;
			}
		}
	}
 	public Collection<RecordBean> getBeanCollection(){
		return data;
	}
 	public Collection<RecordBean> getBeanCollectionArray(){
		return Arrays.asList(dataArray);
	}
 	public Collection<RecordBean> getBeanCollectionDummy(){
		return new ArrayList<RecordBean>();
	}
	public LinkedHashMap<String, String> getColumnsName() {
		return columnsName;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}
	
	
}