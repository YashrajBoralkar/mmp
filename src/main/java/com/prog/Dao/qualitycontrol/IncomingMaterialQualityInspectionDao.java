package com.prog.Dao.qualitycontrol;



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

import com.prog.model.erp.IncomingMaterialQualityInspection;


@Repository
public class IncomingMaterialQualityInspectionDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class ImqInspectorRowMapper implements RowMapper<IncomingMaterialQualityInspection>{

		@Override
		public IncomingMaterialQualityInspection mapRow(ResultSet rs, int rowNum) throws SQLException {
			IncomingMaterialQualityInspection imq=new IncomingMaterialQualityInspection();
			imq.setId(rs.getLong("id"));
			imq.setIncomingmaterialqualityinspectionuid(rs.getString("incomingmaterialqualityinspectionuid"));
			imq.setSuppliername(rs.getString("suppliername"));
			imq.setPonumber(rs.getString("ponumber"));
			imq.setDeliverydate(rs.getString("deliverydate"));
			imq.setBatchuid(rs.getString("batchuid"));
			imq.setProductuid(rs.getString("productuid"));
			imq.setInspectiondate(rs.getString("inspectiondate"));
			imq.setInspectorname(rs.getString("inspectorname"));
			imq.setInspectioncriteria(rs.getString("inspectioncriteria"));
			imq.setSamplingmethod(rs.getString("samplingmethod"));
			imq.setTotalinspecteditems(rs.getString("totalinspecteditems"));
			imq.setDefectiveitems(rs.getString("defectiveitems"));
			imq.setDefectrate(rs.getString("defectrate"));
			imq.setInspectionstatus(rs.getString("inspectionstatus"));
			imq.setActiontaken(rs.getString("actiontaken"));
			imq.setRemarks(rs.getString("remarks"));
			
			return imq;
		}		
	}
	
	public int addImqInspectorData(IncomingMaterialQualityInspection imq) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
		String sql="insert into incomingmaterialqualityinspection(id,incomingmaterialqualityinspectionuid,suppliername,ponumber,deliverydate,batchuid,"
				+ "productuid,inspectiondate,inspectorname,inspectioncriteria,samplingmethod,totalinspecteditems,"
				+ "defectiveitems,defectrate,inspectionstatus,actiontaken,remarks,insertdate,updatedate)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				imq.getId(),
				imq.getIncomingmaterialqualityinspectionuid(),
				imq.getSuppliername(),
				imq.getPonumber(),
				imq.getDeliverydate(),
				imq.getBatchuid(),
				imq.getProductuid(),
				imq.getInspectiondate(),
				imq.getInspectorname(),
				imq.getInspectioncriteria(),
				imq.getSamplingmethod(),
				imq.getTotalinspecteditems(),
				imq.getDefectiveitems(),
				imq.getDefectrate(),
				imq.getInspectionstatus(),
				imq.getActiontaken(),
				imq.getRemarks(),
				formattedTimestamp,
                formattedTimestamp
		);
	}
	
	public IncomingMaterialQualityInspection getImqInspectorDataById(Long id) {
		String sql="Select * from incomingmaterialqualityinspection where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(IncomingMaterialQualityInspection.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public List<Map<String,Object>> fetchAllImqInspectorData(){
		String sql="SELECT  \r\n"
				+ "    imq.id, imq.incomingmaterialqualityinspectionuid, imq.suppliername, imq.ponumber,  \r\n"
				+ "    imq.deliverydate, imq.inspectiondate, imq.inspectorname,  \r\n"
				+ "    imq.inspectioncriteria, imq.samplingmethod,  \r\n"
				+ "    imq.totalinspecteditems, imq.defectiveitems, imq.defectrate,  \r\n"
				+ "    imq.inspectionstatus, imq.actiontaken, imq.remarks,  \r\n"
				+ "    pi.productname\r\n"
				+ "FROM incomingmaterialqualityinspection imq  \r\n"
				+ "JOIN productinfo pi ON imq.productuid = pi.productuid;\r\n";
				
				
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateImqInspectorForm(IncomingMaterialQualityInspection imq) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String formattedTimestamp = LocalDateTime.now().format(formatter);                 
		String sql="update incomingmaterialqualityinspection set suppliername=?, ponumber=?, deliverydate=?, "
				+ "inspectiondate=?, inspectorname=?, inspectioncriteria=?, samplingmethod=?, totalinspecteditems=?, "
				+ "defectiveitems=?, defectrate=?, inspectionstatus=?, actiontaken=?, remarks=?, updatedate=? where id=?";
		return jdbcTemplate.update(sql,
				imq.getSuppliername(),
				imq.getPonumber(),
				imq.getDeliverydate(),
				imq.getInspectiondate(),
				imq.getInspectorname(),
				imq.getInspectioncriteria(),
				imq.getSamplingmethod(),
				imq.getTotalinspecteditems(),
				imq.getDefectiveitems(),
				imq.getDefectrate(),
				imq.getInspectionstatus(),
				imq.getActiontaken(),
				imq.getRemarks(),
				formattedTimestamp,
				imq.getId()
		);		
	}
	public void deleteImqInspectorDataById(Long id) {
		String sql="Delete from incomingmaterialqualityinspection where id=?";
		jdbcTemplate.update(sql,id);
	}
	
	
//FETCHING 
	
	public List<String>getBatchId(){
    	String sql="select batchuid From batchinfo";
    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
    }
    
   public List<String>getItemId(){
	String sql="select productuid From productinfo";
	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
}
   public List<Map<String, Object>> getProductDetailsByuid(String productuid){
	   String sql= "SELECT productname,quantity FROM productinfo WHERE productuid=?";
	   return jdbcTemplate.queryForList(sql, productuid);
   }
   
  public List<Map<String, Object>> getbatchDetailsByuid(String batchuid){
   String sql= "SELECT p.productname, p.productuid FROM productinfo p JOIN batchinfo b ON p.productuid = b.productuid WHERE b.batchuid = ?";
   return jdbcTemplate.queryForList(sql, batchuid);
}
}
