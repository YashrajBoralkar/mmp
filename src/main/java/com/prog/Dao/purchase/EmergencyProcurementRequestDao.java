package com.prog.Dao.purchase;

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

import com.prog.model.erp.EmergencyProcurementRequest;
import com.prog.model.erp.PurchasedAndSalesAgreement;


@Repository
public class EmergencyProcurementRequestDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String sql="insert into emergencyprocurementrequest(emergencyprocurementrequestuid, approvalstatus, reasonforemergency, requestdate,insertdate, updatedate)values(?,?,?,?,?,?)";
	
	public int saveeprform(EmergencyProcurementRequest eprf) 
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String formattedTimestamp=LocalDateTime.now().format(formatter);
		
		 return jdbcTemplate.update(sql,
				 eprf.getEmergencyprocurementrequestuid(),
				 eprf.getApprovalstatus(),
				 eprf.getReasonforemergency(),
				 eprf.getRequestdate(),
				 formattedTimestamp,
				 formattedTimestamp
				 );
	
	}
//=========================================================================================================================================

	   public List<Map<String, Object>>show_epr_list() 
	   {
		   String sql="select * from emergencyprocurementrequest";
		
		   return jdbcTemplate.queryForList(sql);

	   }
 //=========================================================================================================================================
   
	   public static class EprorderRowMapper implements RowMapper<EmergencyProcurementRequest>
	   {
		@Override
		public EmergencyProcurementRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmergencyProcurementRequest eprf=new EmergencyProcurementRequest();
			eprf.setEmergencyprocurementrequestuid(rs.getString("emergencyprocurementrequestuid"));
			eprf.setApprovalstatus(rs.getString("approvalstatus"));
			eprf.setReasonforemergency(rs.getString("reasonforemergency"));
			eprf.setRequestdate(rs.getString("requestdate"));
			eprf.setId(rs.getLong("id"));
			return eprf;
		}

	   }
//=========================================================================================================================================

	   public int deleteeprf(Long id) 
	   {
		   String sql="delete from emergencyprocurementrequest where id =?";
		   return jdbcTemplate.update(sql,id)	;	   
	   }
//=========================================================================================================================================

	   public EmergencyProcurementRequest select_uid_dao(Long id) 
	   {
		   String sql="select * from emergencyprocurementrequest where id=? ";
		  return  jdbcTemplate.queryForObject(sql,new EprorderRowMapper(),id);
		   
	   }
//=========================================================================================================================================

	   public int updateeprbyid(EmergencyProcurementRequest eprf) 
	    {
			String sql="update emergencyprocurementrequest set approvalstatus=?, reasonforemergency=?, requestdate=?, updatedate=? where id=? ";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	       
	    	return jdbcTemplate.update(sql,
	    			eprf.getApprovalstatus(),
					eprf.getReasonforemergency(),
					eprf.getRequestdate(),
	    			formattedTimestamp,
	    			eprf.getId()
	    			);

	    }
}
