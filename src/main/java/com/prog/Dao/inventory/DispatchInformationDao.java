package com.prog.Dao.inventory;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.DispatchInformation;


@Repository
public class DispatchInformationDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<String> getAllDispatchInformation(){
		String sql="SELECT carriername from dispatchinformation";
		return jdbcTemplate.queryForList(sql,String.class);
	}
	
	public DispatchInformation getDispatchInformationById(String carrier_name) {
		String sql="SELECT transportdocumentnumber,modeoftransport,dispatchcost FROM dispatchinformation  WHERE carriername = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {carrier_name}, (rs, rowNum) -> {
			DispatchInformation di= new DispatchInformation();
			di.setTransportDocumentNumber(rs.getString("transportdocumentnumber"));
			di.setModeOfTransport(rs.getString("modeoftransport"));
			di.setDispatchCost(rs.getString("dispatchcost"));		
			return di;
		});
	}		
}