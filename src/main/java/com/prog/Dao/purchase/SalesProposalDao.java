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

import com.prog.model.erp.SalesPraposal;


@Repository
public class SalesProposalDao {
			
	@Autowired
	 private JdbcTemplate jdbctemplate;
	public int addsalesproposal(SalesPraposal sp) {
		
		   String sql = "insert into salesproposal (salesproposaluid, proposaldate,clientuid,productdescription, pricequotation ,insertdate,updatedate) "
		      + "values (?, ?, ?, ?, ?,?,?)";
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
		    return jdbctemplate.update(sql,
		    	

		    		sp.getSalesproposaluid(),
		    		sp.getProposaldate(),
		    		sp.getClientuid(),
		    		sp.getProductdescription(),
		    		sp.getPricequotation(),
		    		 formattedTimestamp,
		             formattedTimestamp
		    		
		    );
		}

	   
	   public List<Map<String, Object>> showsalesproposallist() {
		   String sql = "SELECT b.id, b.salesproposaluid, p.clientname,p.clientemail,p.clientnumber, b.proposaldate, b.productdescription,b.pricequotation FROM salesproposal b JOIN clientinfo p ON p.clientuid = b.clientuid ";
		return jdbctemplate.queryForList(sql);
	   }


	   public SalesPraposal getById(Long id) {
		   String sql = "SELECT * FROM salesproposal WHERE id = ?";
	       return jdbctemplate.queryForObject(sql, new SPentityRowMapper() , id);
	   }
	   
	   public static class SPentityRowMapper implements RowMapper<SalesPraposal>{
			 @Override
			 public SalesPraposal mapRow(ResultSet rs, int rowNum) throws SQLException {
				 SalesPraposal sp = new SalesPraposal();
				 sp.setId(rs.getLong("id"));
				 sp.setSalesproposaluid(rs.getString("salesproposaluid"));
				 sp.setProposaldate(rs.getString("proposaldate"));
				 sp.setClientuid(rs.getString("clientuid"));
				 sp.setProductdescription(rs.getString("productdescription"));
				 sp.setPricequotation(rs.getString("pricequotation"));
				
				return sp;
			 }
	   }		
		public int deleteById(long id) {
			       String sql = "DELETE FROM salesproposal WHERE id = ?";
			       return jdbctemplate.update(sql,id);
			   }
		
		 public int updatesalesproposal(SalesPraposal so) {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		        String sql = "UPDATE salesproposal SET   proposaldate = ?, productdescription=?, pricequotation=? ,updatedate=?\r\n"
		            + "WHERE id = ?";
		    return jdbctemplate.update(sql,
		            so.getProposaldate(),
		            so.getProductdescription(),
		            so.getPricequotation(),
		    		 formattedTimestamp,
		            so.getId()); 
		}


		//FETCHING CLIENT INFO FROM CLIENT TABLE
		
		 public List<String> getClientId() {
				String sql="select clientuid From clientinfo";
			  	return jdbctemplate.query(sql, (rs, rowNum) -> rs.getString("clientuid"));
			}

			 public List<Map<String, Object>>  getclientDetailsByuidsss(String clientuid){
				   String sql= "SELECT clientname,clientemail,clientnumber FROM clientinfo WHERE clientuid=?";
				   return jdbctemplate.queryForList(sql, clientuid);
						   
			 }

		
		
} 


	   



