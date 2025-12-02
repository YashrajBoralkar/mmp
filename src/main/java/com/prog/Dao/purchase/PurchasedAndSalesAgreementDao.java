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

import com.prog.model.erp.PurchasedAndSalesAgreement;


@Repository
public class PurchasedAndSalesAgreementDao 
    {
	    @Autowired
    	private JdbcTemplate jdbcTemplate;
    	
    	private static final String sql="insert into purchaseandsalesagreement(agreementdate, purchasesalesagreementuid, buyername, contractvalue, deliveryterms,supplieruid, insertdate, updatedate)"
    			+ "values(?,?,?,?,?,?,?,?)"; 
	  
    	public int savepsaentry(PurchasedAndSalesAgreement pa) 
    	{
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	        
	       return jdbcTemplate.update(sql, 
	    		   pa.getAgreementdate(),
	    		   pa.getPurchasesalesagreementuid(),
	    		   pa.getBuyername(),
	    		   pa.getContractvalue(),
	    		   pa.getDeliveryterms(),
	    		   pa.getSupplieruid(),
	    		   formattedTimestamp,
	    		   formattedTimestamp
	    		   );
    	}

  
//=========================================================================================================================================

    public List<Map<String, Object>> showpsalist()
   
    {
    	String sql="SELECT psa.id, psa.purchasesalesagreementuid, psa.agreementdate,psa.buyername,psa.contractvalue, psa.deliveryterms, s.suppliername from purchaseandsalesagreement psa "
    			+ "JOIN supplierregistration s ON s.supplieruid = psa.supplieruid;";
		return jdbcTemplate.queryForList(sql);

    }
 //=========================================================================================================================================
  
    public static class PsaRowMapper implements RowMapper<PurchasedAndSalesAgreement>
    {
    	@Override
    	public PurchasedAndSalesAgreement mapRow(ResultSet rs, int rowNum) throws SQLException 
    	{
    		PurchasedAndSalesAgreement pa=new PurchasedAndSalesAgreement();
    		pa.setAgreementdate(rs.getString("agreementdate"));
    		pa.setPurchasesalesagreementuid(rs.getString("purchasesalesagreementuid"));
    		pa.setBuyername(rs.getString("buyername"));
    		pa.setContractvalue(rs.getString("contractvalue"));
    		pa.setDeliveryterms(rs.getString("deliveryterms"));
    		pa.setSupplieruid(rs.getString("supplieruid"));
    		pa.setId(rs.getLong("id"));
    		return pa;
    	}
    	
    }
 //=========================================================================================================================================
  
    public PurchasedAndSalesAgreement select_psa_by_id(Long id) 
    {
    	String sql="Select * from purchaseandsalesagreement where id=?";
		return jdbcTemplate.queryForObject(sql, new PsaRowMapper(),id);
   
    }
   
 //=========================================================================================================================================
  
    public void delete_psa_from_list(Long id) 
    {
    	String sql="delete from purchaseandsalesagreement where id=?";
		 jdbcTemplate.update(sql,id);

    }
//=========================================================================================================================================
   
    public int update_psa_by_id(PurchasedAndSalesAgreement pa) 
    {
		String sql="update purchaseandsalesagreement set agreementdate=?,buyername=?, contractvalue=?, deliveryterms=?, updatedate=? where id=? ";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
    	return jdbcTemplate.update(sql,
    			pa.getAgreementdate(),
    			pa.getBuyername(),
    			pa.getContractvalue(),
    			pa.getDeliveryterms(),
    			formattedTimestamp,
    			pa.getId()
    			);

    }
  //=========================================================================================================================================

    }
 