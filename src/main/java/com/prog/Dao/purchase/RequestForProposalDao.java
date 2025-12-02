package com.prog.Dao.purchase;

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

import com.prog.model.erp.RequestForProposal;


@Repository
public class RequestForProposalDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class RFPFormRowMapper implements RowMapper<RequestForProposal>{

		@Override
		public RequestForProposal mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			RequestForProposal rfp=new RequestForProposal();
			rfp.setId(rs.getLong("id"));
			rfp.setRequestforproposaluid(rs.getString("requestforproposaluid"));
			rfp.setRfpissuedate(rs.getString("rfpissuedate"));
			rfp.setSubmissiondeadline(rs.getString("submissiondeadline"));
			rfp.setSupplieruid(rs.getString("supplieruid"));
			rfp.setScopeofwork(rs.getString("scopeofwork"));
			rfp.setProjectstartdate(rs.getString("projectstartdate"));
			rfp.setProjectenddate(rs.getString("projectenddate"));
			rfp.setTechnicalrequirement(rs.getString("technicalrequirement"));
			rfp.setProposedcost(rs.getString("proposedcost"));
		    return rfp;
		}
	}
	
	public int addDpfData(RequestForProposal rfp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		String sql="insert into requestforproposal (id,requestforproposaluid,rfpissuedate,submissiondeadline,supplieruid,scopeofwork,projectstartdate,"
				+ "projectenddate,technicalrequirement,proposedcost,insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		return jdbcTemplate.update(sql,
				rfp.getId(),
				rfp.getRequestforproposaluid(),
				rfp.getRfpissuedate(),
				rfp.getSubmissiondeadline(),
				rfp.getSupplieruid(),
				rfp.getScopeofwork(),
				rfp.getProjectstartdate(),
				rfp.getProjectenddate(),
				rfp.getTechnicalrequirement(),
				rfp.getProposedcost(),
				formattedTimestamp,
                formattedTimestamp
				);
	}
	
	public RequestForProposal getRFPFormDataById(Long id) {
		String sql="select * from requestforproposal where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(RequestForProposal.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }

	}
	
	public List<Map<String,Object>> getAllrfpformData(){
		String sql="SELECT rfp.id,rfp.requestforproposaluid,rfp.projectenddate, rfp.projectstartdate,rfp.proposedcost, rfp.rfpissuedate,rfp.scopeofwork,rfp.submissiondeadline,rfp.technicalrequirement,\r\n"
				+ "si.contactnumber,si.suppliername,si.email\r\n"
				+ "FROM requestforproposal rfp  \r\n"
				+ "JOIN  supplierregistration si ON si.supplieruid = rfp.supplieruid";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateRfpForm(RequestForProposal rfp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
		String sql="update requestforproposal set rfpissuedate=?,projectenddate=?,projectstartdate=?,proposedcost=?,rfpissuedate=?,scopeofwork=?,submissiondeadline=?,"
				+ "technicalrequirement=?,updatedate=? where id=?";
		return jdbcTemplate.update(sql, 
				rfp.getRfpissuedate(),
				rfp.getProjectenddate(),
				rfp.getProjectstartdate(),
				rfp.getProposedcost(),
				rfp.getRfpissuedate(),
				rfp.getScopeofwork(),
				rfp.getSubmissiondeadline(),
				rfp.getTechnicalrequirement(),
				formattedTimestamp,
				rfp.getId()
				);
	}
	
	public void deleteRFPFormDataById(Long id) {
		String sql="delete from requestforproposal where id=?";
		jdbcTemplate.update(sql,id);		
	}
	
	/*
	 * public List<String> getSupplierDetailsById() { String
	 * sql="select supplieruid from supplierinfo"; return jdbcTemplate.query(sql,
	 * (rs, rowNum) -> rs.getString("supplieruid")); }
	 */
		
//		public SupplierInfo getSupplierDetails(String supplieruid) {
//			String sql = " SELECT suppliername , email , phonenumber FROM supplierinfo WHERE supplieruid = ?";
//	    	
//	    	return jdbcTemplate.queryForObject(sql, new Object[] {supplieruid}, (rs, rowNum) -> {
//	    		SupplierInfo si = new SupplierInfo();
//	    		si.setSuppliername(rs.getString("suppliername"));
//	    		si.setEmail(rs.getString("email"));
//	    		si.setPhonenumber(rs.getString("phonenumber"));
//	    		return si;
//	    	});
//		}
	
}
