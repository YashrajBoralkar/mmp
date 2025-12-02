package com.prog.Dao.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.RawMaterialSupplierRegistration;
import com.prog.model.erp.RawmaterialInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RawMaterailSupplierRegistrationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RawMaterialSupplierRegistration> rowMapper = new RowMapper<>() {
        @Override
        public RawMaterialSupplierRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
            RawMaterialSupplierRegistration supplier = new RawMaterialSupplierRegistration();
            supplier.setId(rs.getLong("id"));
            supplier.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            supplier.setSuppliername(rs.getString("suppliername"));
            supplier.setContactperson(rs.getString("contactperson"));
            supplier.setMobilenumber(rs.getString("mobilenumber"));
            supplier.setEmailaddress(rs.getString("emailaddress"));
            supplier.setBusinessaddress(rs.getString("businessaddress"));
            supplier.setGstnumber(rs.getString("gstnumber"));
            supplier.setRawmaterialuid(rs.getString("rawmaterialuid"));
            supplier.setRawmaterialname(rs.getString("rawmaterialname"));
            supplier.setPannumber(rs.getString("pannumber"));
            supplier.setSuppliertype(rs.getString("suppliertype"));
            supplier.setBankaccountnumber(rs.getString("bankaccountnumber"));
            supplier.setIfsccode(rs.getString("ifsccode"));
            supplier.setPaymentterms(rs.getString("paymentterms"));
            supplier.setCompliancecertifications(rs.getString("compliancecertifications"));
            supplier.setWebsiteurl(rs.getString("websiteurl"));
            supplier.setRegistrationdate(rs.getString("registrationdate"));
            supplier.setSupplierdoc(rs.getBytes("supplierdoc")); // Add this line

            return supplier;
        }
    };

    public int saveSupplier(RawMaterialSupplierRegistration supplier) {
        String sql = "INSERT INTO rawmaterialsupplier " +
                "(rawmaterialsupplieruid, suppliername, contactperson, mobilenumber, emailaddress, businessaddress, gstnumber, " +
                "rawmaterialuid,rawmaterialname,  pannumber, suppliertype, bankaccountnumber, ifsccode, paymentterms, " +
                "compliancecertifications, websiteurl, registrationdate, supplierdoc,insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
       return jdbcTemplate.update(sql,
                supplier.getRawmaterialsupplieruid(),
                supplier.getSuppliername(),
                supplier.getContactperson(),
                supplier.getMobilenumber(),
                supplier.getEmailaddress(),
                supplier.getBusinessaddress(),
                supplier.getGstnumber(),
                supplier.getRawmaterialuid(),
                supplier.getRawmaterialname(),
                supplier.getPannumber(),
                supplier.getSuppliertype(),
                supplier.getBankaccountnumber(),
                supplier.getIfsccode(),
                supplier.getPaymentterms(),
                supplier.getCompliancecertifications(),
                supplier.getWebsiteurl(),
                supplier.getRegistrationdate(),
                supplier.getSupplierdoc(),
                formattedTimestamp,
                formattedTimestamp
                );
    }

    public int updateSupplier(RawMaterialSupplierRegistration supplier) {
        String sql = "UPDATE rawmaterialsupplier SET " +
                "suppliername = ?, contactperson = ?, mobilenumber = ?, emailaddress = ?, " +
                "businessaddress = ?, gstnumber = ?, rawmaterialuid = ?, rawmaterialname = ?, pannumber = ?, " +
                "suppliertype = ?, bankaccountnumber = ?, ifsccode = ?, paymentterms = ?, " +
                "compliancecertifications = ?, websiteurl = ?, registrationdate = ?, supplierdoc=?, updatedate = ? " +
                "WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        return jdbcTemplate.update(sql,
                supplier.getSuppliername(),
                supplier.getContactperson(),
                supplier.getMobilenumber(),
                supplier.getEmailaddress(),
                supplier.getBusinessaddress(),
                supplier.getGstnumber(),
                supplier.getRawmaterialuid(),
                supplier.getRawmaterialname(),
                supplier.getPannumber(),
                supplier.getSuppliertype(),
                supplier.getBankaccountnumber(),
                supplier.getIfsccode(),
                supplier.getPaymentterms(),
                supplier.getCompliancecertifications(),
                supplier.getWebsiteurl(),
                supplier.getRegistrationdate(),
                supplier.getSupplierdoc(),
                formattedTimestamp,
                supplier.getId()
        );
    }

    public int deleteSupplierByid(Long id) {
    	String sql = "DELETE FROM rawmaterialsupplier WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public RawMaterialSupplierRegistration getSupplierByid(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM rawmaterialsupplier WHERE id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public List<Map<String, Object>> getAllSupplierdata() {
        String sql = "SELECT * FROM rawmaterialsupplier";
        return jdbcTemplate.queryForList(sql);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    public List<String> getAllSupplieruids() {
	   	 
        String sql = "SELECT rawmaterialsupplieruid FROM rawmaterialsupplier"; // Replace with your actual table and column names
        return jdbcTemplate.queryForList(sql, String.class);
    
    }


 
  public List<Map<String, Object>> getSupplierDetailsBySupplieruid(String rawmaterialsupplieruid) {
        String sql = "SELECT suppliername,rawmaterialname AS materialname,mobilenumber FROM rawmaterialsupplier WHERE rawmaterialsupplieruid = ?";
        return jdbcTemplate.queryForList(sql, rawmaterialsupplieruid);
    }
  
//Get material names by supplier UID
  public List<String> findrawMaterialNamesBySupplierUid(String rawmaterialsupplieruid) {
      String sql = "SELECT rawmaterialname AS materialname FROM rawmaterialsupplier WHERE rawmaterialsupplieruid = ?";
      return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("materialname"), rawmaterialsupplieruid);
  }
 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public List<RawMaterialSupplierRegistration> getAllSuppliers() {
//        return jdbcTemplate.query("SELECT * FROM rawmaterialsupplier", rowMapper);
//    }

    public List<RawmaterialInfo> findByUids(List<String> uids) {
        if (uids.isEmpty()) return List.of();
        String inSql = String.join(",", Collections.nCopies(uids.size(), "?"));
        String sql = "SELECT * FROM rawmaterialinfo WHERE rawmaterialuid IN (" + inSql + ")";
        return jdbcTemplate.query(sql, uids.toArray(), (rs, rowNum) -> {
        	RawmaterialInfo mat = new RawmaterialInfo();
            mat.setRawmaterialuid(rs.getString("rawmaterialuid"));
            mat.setRawmaterialname(rs.getString("rawmaterialname"));
            return mat;
        });
    }
    public List<RawmaterialInfo> findAllRawMaterials() {
        String sql = "SELECT * FROM rawmaterialinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        	RawmaterialInfo mat = new RawmaterialInfo();
            mat.setRawmaterialuid(rs.getString("rawmaterialuid"));
            mat.setRawmaterialname(rs.getString("rawmaterialname"));
            return mat;
        });
    }
}
