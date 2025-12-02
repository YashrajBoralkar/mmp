package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.prog.model.erp.ProductionQualityControl;


@Repository
public class ProductionQualityControlDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 public static class ProductionQualityControlentityRowMapper implements RowMapper<ProductionQualityControl>{
		 @Override
		 public ProductionQualityControl mapRow(ResultSet rs, int rowNum) throws SQLException {
			 ProductionQualityControl pqc = new ProductionQualityControl();
			 pqc.setId(rs.getLong("id"));
			 pqc.setProductionqualitycontroluid(rs.getString("productionqualitycontroluid"));
			 pqc.setWorkorderuid(rs.getString("workorderuid"));
			 pqc.setProductuid(rs.getString("productuid"));
			 pqc.setInspectorname(rs.getString("inspectorname"));
			 pqc.setTestperformed(rs.getString("testperformed"));
			 pqc.setMeasurementstandard(rs.getString("measurementstandard"));
			 pqc.setDefectclassification(rs.getString("defectclassification"));
			 pqc.setInspectionstatus(rs.getString("inspectionstatus"));
			 pqc.setReworkrequire(rs.getString("reworkrequire"));
			 pqc.setRootcauseanalysisreport(rs.getString("rootcauseanalysisreport"));
			 pqc.setInsertdate(rs.getString("insertdate"));
			 pqc.setUpdatedate(rs.getNString("updatedate"));

        return pqc;
		 }	 
	 }
	 
	 public int saveProductionQualityControl(ProductionQualityControl pqc) {
			
		   String sql = "insert into productionqualitycontrol (id, productionqualitycontroluid, workorderuid,productuid,inspectorname, testperformed ,measurementstandard,"
		        + "defectclassification,inspectionstatus,reworkrequire,rootcauseanalysisreport,insertdate,updatedate)"
		      + "values (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
		   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		   		String formattedTimestamp = LocalDateTime.now().format(formatter);
		   			return jdbcTemplate.update(sql,
		   		    pqc.getId(),
		    		pqc.getProductionqualitycontroluid(),
		    		pqc.getWorkorderuid(),
		    		pqc.getProductuid(),
		    		pqc.getInspectorname(),
		    		pqc.getTestperformed(),
		    		pqc.getMeasurementstandard(),
		    		pqc.getDefectclassification(),
		    		pqc.getInspectionstatus(),
		    		pqc.getReworkrequire(),
		    		pqc.getRootcauseanalysisreport(),
		    		formattedTimestamp,
	                formattedTimestamp
		    		
		    );
		}	
	 
	 public ProductionQualityControl getProductionQualityControlById(Long id) {
		   String sql = "SELECT * FROM productionqualitycontrol WHERE id = ?";
	       return jdbcTemplate.queryForObject(sql, new ProductionQualityControlentityRowMapper() , id);
	   }
	   
	 
	 public List<Map<String, Object>> showProductionQualityControlList() {
		   String sql = "SELECT pqc.id,pqc.productionqualitycontroluid,pqc.inspectorname,pqc.testperformed,pqc.rootcauseanalysisreport,\r\n"
		   		+ "pqc.reworkrequire,pqc.measurementstandard,pqc.inspectionstatus,pqc.defectclassification,\r\n"
		   		+ "pi.productuid,pi.productname,wo.reviewedby,wo.workorderuid\r\n"
		   		+ " FROM  productionqualitycontrol pqc \r\n"
		   		+ " JOIN productinfo pi ON pi.productuid=pqc.productuid\r\n"
		   		+ " join workorder wo ON wo.workorderuid=pqc.workorderuid;";
		return jdbcTemplate.queryForList(sql);
	   }
	 
	  public void deleteProductionQualityControlById(long id) {
	       String sql = "DELETE FROM productionqualitycontrol WHERE id = ?";
	        jdbcTemplate.update(sql,id);
	   }

	  public int updatProductionQualityControl(ProductionQualityControl updatepqc) {
		   
		    String sql = "UPDATE productionqualitycontrol SET testperformed = ?,measurementstandard = ?, defectclassification = ?,inspectionstatus = ?, "
		            + "reworkrequire = ?, rootcauseanalysisreport = ?, updatedate = ? WHERE id = ?";
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);
		    return jdbcTemplate.update(sql,
		    		updatepqc.getTestperformed(),
		    		updatepqc.getMeasurementstandard(),
		    		updatepqc.getDefectclassification(),
		    		updatepqc.getInspectionstatus(),
		    		updatepqc.getReworkrequire(),
		    		updatepqc.getRootcauseanalysisreport(),
		    		formattedTimestamp,
		            updatepqc.getId());
		}

	 
	 public ProductionQualityControl getroductionQualityControlById(Long id) {
		   String sql = "SELECT * FROM productionqualitycontrol WHERE id = ?";
	       return jdbcTemplate.queryForObject(sql, new ProductionQualityControlentityRowMapper() , id);
	   }
	   
	 
	 public List<String> getWorkOrderUID(){
		 String sql="select workorderuid from workorder";
		 return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("workorderuid"));
	 }
			
	 public List<Map<String,Object>> getProductDetalisByWorkOrderUID(String workorderuid){
		 String sql="SELECT wo.workorderuid, pi.productuid, pi.productname\r\n"
				 + "FROM workorder wo\r\n"
				 + "JOIN productinfo pi ON pi.productuid = wo.productuid\r\n"
				 + "WHERE wo.workorderuid = ?";
		 return jdbcTemplate.queryForList(sql, workorderuid);

	}
}
