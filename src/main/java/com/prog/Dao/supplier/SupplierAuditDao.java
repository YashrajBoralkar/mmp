package com.prog.Dao.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.SupplierAudit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class SupplierAuditDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<SupplierAudit> rowMapper = (rs, rowNum) -> {
        SupplierAudit audit = new SupplierAudit();
        audit.setId(rs.getLong("id"));
        audit.setSupplieraudituid(rs.getString("supplieraudituid"));
        audit.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
        audit.setAuditdate(rs.getString("auditdate"));
        audit.setAuditorname(rs.getString("auditorname"));
        audit.setQualitycompliancescore(rs.getString("qualitycompliancescore"));
        audit.setSafetystandardscompliance(rs.getString("safetystandardscompliance"));
        audit.setDeliveryperformance(rs.getString("deliveryperformance"));
        audit.setCorrectiveactionrequired(rs.getString("correctiveactionrequired"));
        audit.setAuditoutcome(rs.getString("auditoutcome"));
        return audit;
    };

    public int saveSupplierAudit(SupplierAudit audit) {
       
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);
	    String sql = "INSERT INTO supplieraudit (supplieraudituid, rawmaterialsupplieruid, auditdate, auditorname, " +
                     "qualitycompliancescore, safetystandardscompliance, deliveryperformance, correctiveactionrequired, " +
                     "auditoutcome, insertdate, updatedate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        return jdbcTemplate.update(sql,
        		audit.getSupplieraudituid(),
        		audit.getRawmaterialsupplieruid(),
                audit.getAuditdate(), 
                audit.getAuditorname(), 
                audit.getQualitycompliancescore(),
                audit.getSafetystandardscompliance(), 
                audit.getDeliveryperformance(), 
                audit.getCorrectiveactionrequired(),
                audit.getAuditoutcome(),
                formattedTimestamp,
                formattedTimestamp );
    }

    public int updateSupplierAudit(SupplierAudit audit) {
        
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);
	   
       String sql = "UPDATE supplieraudit SET auditdate = ?, auditorname = ?, " +
                   "qualitycompliancescore = ?, safetystandardscompliance = ?, deliveryperformance = ?, " +
                    "correctiveactionrequired = ?, auditoutcome = ?, updatedate = ? WHERE id = ?";     
       return jdbcTemplate.update(sql,  
    		   audit.getAuditdate(),
               audit.getAuditorname(), 
               audit.getQualitycompliancescore(), 
               audit.getSafetystandardscompliance(),
               audit.getDeliveryperformance(), 
               audit.getCorrectiveactionrequired(), 
               audit.getAuditoutcome(), 
               formattedTimestamp, 
               audit.getId());
   }

    public List<SupplierAudit> getAllSupplierAudits() {
        return jdbcTemplate.query("SELECT * FROM supplieraudit", rowMapper);
    }

    public SupplierAudit getSupplierAuditById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM supplieraudit WHERE id = ?", rowMapper, id);
    }

    public void deleteSupplierAuditById(Long id) {
        String sql = "DELETE FROM supplieraudit WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public SupplierAudit getSupplierAuditBySupplierid(String rawmaterialsupplieruid) {
        String sql = "SELECT * FROM supplieraudit WHERE rawmaterialsupplieruid = ? LIMIT 1";  
        return jdbcTemplate.queryForObject(sql, rowMapper, rawmaterialsupplieruid);
    }
    public List<Map<String, Object>> getAuditWithSupplierDetails() {
        String sql = """
            SELECT A.id, A.supplieraudituid, S.rawmaterialsupplieruid, S.suppliername,
                   A.auditdate, A.auditorname, A.auditoutcome
            FROM supplieraudit A
            JOIN rawmaterialsupplier S ON A.rawmaterialsupplieruid = S.rawmaterialsupplieruid
            """;
        return jdbcTemplate.queryForList(sql);
    }

}