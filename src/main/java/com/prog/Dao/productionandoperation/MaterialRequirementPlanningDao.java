package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.MaterialRequirementPlanning;




@Repository
public class MaterialRequirementPlanningDao {
@Autowired JdbcTemplate jdbcTemplate;
	
	public static class MRPFormRowMapper implements RowMapper<MaterialRequirementPlanning>{

		@Override
		public MaterialRequirementPlanning mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialRequirementPlanning mrp=new MaterialRequirementPlanning();
			mrp.setId(rs.getLong("id"));
			mrp.setMaterialrequirementplanninguid(rs.getString("materialrequirementplanninguid"));
			mrp.setProductionplanninguid(rs.getString("productionplanninguid"));
			mrp.setProductuid(rs.getString("productuid"));
			mrp.setRequiredquantity(rs.getString("requiredquantity"));
			mrp.setAvailablestock(rs.getString("availablestock"));
			mrp.setRecorderlevel(rs.getString("recorderlevel"));
			mrp.setSuppliername(rs.getString("suppliername"));
			mrp.setSupplierleadtime(rs.getString("supplierleadtime"));
			mrp.setSupplierleaddate(rs.getString("supplierleaddate"));
			mrp.setProcrequeststatus(rs.getString("procrequeststatus"));
			return mrp;
		}	
	}
	public int addMRPData(MaterialRequirementPlanning mrp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        
		String sql="insert into materialrequirementplanning(id,materialrequirementplanninguid,productionplanninguid,productuid,requiredquantity,availablestock,recorderlevel,suppliername,"
				+ "supplierleadtime,supplierleaddate,procrequeststatus,insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				mrp.getId(),
				mrp.getMaterialrequirementplanninguid(),
				mrp.getProductionplanninguid(),
				mrp.getProductuid(),
				mrp.getRequiredquantity(),
				mrp.getAvailablestock(),
				mrp.getRecorderlevel(),
				mrp.getSuppliername(),
				mrp.getSupplierleadtime(),
				mrp.getSupplierleaddate(),
				mrp.getProcrequeststatus(),
				formattedTimestamp,
                formattedTimestamp
				);		
	}
	
	public MaterialRequirementPlanning getMRPFormDataById(Long id) {
		String sql="select * from materialrequirementplanning where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MaterialRequirementPlanning.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	public List<Map<String,Object>> getAllMRPData(){
		String sql="SELECT mrp.id,mrp.materialrequirementplanninguid,mrp.availablestock,mrp.procrequeststatus,mrp.recorderlevel,\r\n"
				+ "mrp.requiredquantity, mrp.supplierleaddate,mrp.supplierleadtime,mrp.suppliername,\r\n"
				+ " p.productionplanninguid, \r\n"
				+ " pi.productuid,pi.productname\r\n"
				+ "FROM materialrequirementplanning mrp\r\n"
				+ "JOIN productinfo pi  ON pi.productuid=mrp.productuid\r\n"
				+ "Join productionplanning p ON p.productionplanninguid=mrp.productionplanninguid;";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateMRPFData(MaterialRequirementPlanning mrp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
		String sql="update materialrequirementplanning set availablestock=?,procrequeststatus=?,recorderlevel=?,requiredquantity=?,supplierleaddate=?,supplierleadtime=?,"
				+ "suppliername=?,updatedate=? where id=?";
		return jdbcTemplate.update(sql,
				mrp.getAvailablestock(),
				mrp.getProcrequeststatus(),
				mrp.getRecorderlevel(),
				mrp.getRequiredquantity(),
				mrp.getSupplierleaddate(),
				mrp.getSupplierleadtime(),
				mrp.getSuppliername(),
				formattedTimestamp,
				mrp.getId()
				);
	}
	
	public void deleteMRPDataById(Long id) {
		String sql="delete from materialrequirementplanning where id=?";
		jdbcTemplate.update(sql,id);
	}
	public List<String> getProductionPlanningUID(){
		String sql="select productionplanninguid from productionplanning";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productionplanninguid"));
	}
	


	public List<Map<String,Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid){
		String sql="SELECT pp.productionplanninguid, pi.productuid, pi.productname\r\n"
				+ "FROM productionplanning pp\r\n"
				+ "JOIN productinfo pi ON pi.productuid = pp.productuid\r\n"
				+ "WHERE pp.productionplanninguid = ?";
		   return jdbcTemplate.queryForList(sql, productionplanninguid);

	}


	

}
