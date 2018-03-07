package org.com.dm.service.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public interface AplicationDAO{
	
	public List<LinkedHashMap> saveComponent(LinkedHashMap<String, Object> param) throws SQLException, Exception;
	
	public List<LinkedHashMap> readComponent(LinkedHashMap<String, Object> param) throws SQLException, Exception;
	
}
