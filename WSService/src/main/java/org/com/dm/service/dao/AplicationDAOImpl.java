package org.com.dm.service.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.com.dm.service.transformation.Cronograma;
import org.com.dm.util.Util;
//import org.jboss.resource.adapter.jdbc.WrappedConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.lob.OracleLobHandler;
import org.springframework.stereotype.Repository;

@Repository("aplicationDAO")
public class AplicationDAOImpl implements AplicationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	// private String schemaName="RECLAMO";

	final OracleLobHandler lobHandler = new OracleLobHandler();

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public List<LinkedHashMap> saveComponent(LinkedHashMap param) throws SQLException, Exception {
		List<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
		this.simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("test")
				.withProcedureName("SP_CREAR_CRONOGRAMA")
				.declareParameters(new SqlParameter("V_VALOR_PRESTAMO", Types.DECIMAL))
				.declareParameters(new SqlParameter("V_TASA_PACTADA", Types.DECIMAL))
				.declareParameters(new SqlParameter("V_CANTIDAD_CUOTAS", Types.INTEGER))
				.declareParameters(new SqlParameter("V_FECHA_VENCIMIENTO", Types.VARCHAR))
				.returningResultSet("resultado", ParameterizedBeanPropertyRowMapper.newInstance(Cronograma.class));
		SqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("V_VALOR_PRESTAMO", new Double(param.get("1").toString()), Types.DECIMAL)
				.addValue("V_TASA_PACTADA", new Double(param.get("2").toString()), Types.DECIMAL)
				.addValue("V_CANTIDAD_CUOTAS", new Double(param.get("3").toString()), Types.DECIMAL)
				.addValue("V_FECHA_VENCIMIENTO", param.get("4").toString(), Types.VARCHAR);

		Map<String, Object> map = simpleJdbcCall.execute(paramMap);
		list = (List<LinkedHashMap>) map.get("resultado");
		return list;
	}

	public List<LinkedHashMap> readComponent(LinkedHashMap param) throws SQLException, Exception {
		List<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
		this.simpleJdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withSchemaName("test")
				.withProcedureName("SP_LEER_CRONOGRAMA")
				.declareParameters(new SqlParameter("V_id_cronograma", Types.VARCHAR))
				.returningResultSet("resultado", ParameterizedBeanPropertyRowMapper.newInstance(Cronograma.class));
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("V_id_cronograma", param.get("1"),
				Types.VARCHAR);

		Map<String, Object> map = simpleJdbcCall.execute(paramMap);
		list = (List<LinkedHashMap>) map.get("resultado");
		return list;
	}

}
