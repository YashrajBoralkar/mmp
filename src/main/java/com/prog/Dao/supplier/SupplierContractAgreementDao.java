package com.prog.Dao.supplier;


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

import com.prog.model.erp.SupplierContractAgreement;


@Repository
public class SupplierContractAgreementDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final String insertSql = "INSERT INTO suppliercontractagreement (suppliercontractagreementuid, rawmaterialsupplieruid, contractstartdate, contractenddate, " +
            "scopeofsupply, pricingagreement, qualitycompliancestandards, paymentterms, terminationclause, renewalterms, " +
            "supplierrepresentativename, companyrepresentativename, dateofapproval,insertdate, updatedate) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedTimestamp = LocalDateTime.now().format(formatter);

    public int saveSupplierContract(SupplierContractAgreement contract) {

    	return jdbcTemplate.update(insertSql,
                contract.getSuppliercontractagreementuid(),
                contract.getRawmaterialsupplieruid(),
                contract.getContractstartdate(),
                contract.getContractenddate(),
                contract.getScopeofsupply(),
                contract.getPricingagreement(),
                contract.getQualitycompliancestandards(),
                contract.getPaymentterms(),
                contract.getTerminationclause(),
                contract.getRenewalterms(),
                contract.getSupplierrepresentativename(),
                contract.getCompanyrepresentativename(),
                contract.getDateofapproval(),
                formattedTimestamp,
                formattedTimestamp
               
        );
    }
    public List<Map<String, Object>> fetchAllSupplierContractAgreement() {
        String sql =
            "SELECT sc.id, " +
            "       sc.suppliercontractagreementuid, " +
            "       MIN(sc.contractstartdate) AS contractstartdate, " +
            "       MIN(sc.contractenddate) AS contractenddate, " +
            "       MIN(sc.terminationclause) AS terminationclause, " +
            "       MIN(sc.supplierrepresentativename) AS supplierrepresentativename, " +
            "       MIN(sc.companyrepresentativename) AS companyrepresentativename, " +
            "       MIN(sc.dateofapproval) AS dateofapproval, " +
            "       MIN(s.rawmaterialsupplieruid) AS rawmaterialsupplieruid, " +
            "       MIN(s.rawmaterialname) AS rawmaterialname, " +
            "       MIN(s.suppliername) AS suppliername " +
            "FROM suppliercontractagreement sc " +
            "INNER JOIN rawmaterialsupplier s " +
            "        ON s.rawmaterialsupplieruid = sc.rawmaterialsupplieruid " +
            "GROUP BY sc.id, sc.suppliercontractagreementuid " +
            "ORDER BY sc.id";

        return jdbcTemplate.queryForList(sql);
    }

    public void deleteSupplierContractById(Long id) {
        String sql = "DELETE FROM suppliercontractagreement WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No SupplierContract found with id " + id);
        }
    }

    public SupplierContractAgreement getSupplierContractById(Long id) {
        String sql = "SELECT * FROM suppliercontractagreement WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SupplierContractRowMapper(), id);
    }

    public void updateSupplierContract(SupplierContractAgreement contract) {
    	    String sql = "UPDATE suppliercontractagreement SET  contractstartdate = ?, contractenddate = ?, " +
                "scopeofsupply = ?, pricingagreement = ?, qualitycompliancestandards = ?, paymentterms = ?, terminationclause = ?, " +
                "renewalterms = ?, supplierrepresentativename = ?, companyrepresentativename = ?, dateofapproval = ?, updatedate=? WHERE id = ?";

        jdbcTemplate.update(sql,
                contract.getContractstartdate(),
                contract.getContractenddate(),
                contract.getScopeofsupply(),
                contract.getPricingagreement(),
                contract.getQualitycompliancestandards(),
                contract.getPaymentterms(),
                contract.getTerminationclause(),
                contract.getRenewalterms(),
                contract.getSupplierrepresentativename(),
                contract.getCompanyrepresentativename(),
                contract.getDateofapproval(),
                formattedTimestamp,
                contract.getId()
        );
    }
    class SupplierContractRowMapper implements RowMapper<SupplierContractAgreement> {
        @Override
        public SupplierContractAgreement mapRow(ResultSet rs, int rowNum) throws SQLException {
            SupplierContractAgreement contract = new SupplierContractAgreement();
            contract.setId(rs.getLong("id"));
            contract.setSuppliercontractagreementuid(rs.getString("suppliercontractagreementuid"));
            contract.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            contract.setContractstartdate(rs.getString("contractstartdate"));
            contract.setContractenddate(rs.getString("contractenddate"));
            contract.setScopeofsupply(rs.getString("scopeofsupply"));
            contract.setPricingagreement(rs.getString("pricingagreement"));
            contract.setQualitycompliancestandards(rs.getString("qualitycompliancestandards"));
            contract.setPaymentterms(rs.getString("paymentterms"));
            contract.setTerminationclause(rs.getString("terminationclause"));
            contract.setRenewalterms(rs.getString("renewalterms"));
            contract.setSupplierrepresentativename(rs.getString("supplierrepresentativename"));
            contract.setCompanyrepresentativename(rs.getString("companyrepresentativename"));
            contract.setDateofapproval(rs.getString("dateofapproval"));
           
            return contract;
        }
    }
    
    
//    public List<Map<String, Object>> getDataBySupplieruid(String rawmaterialsupplieruid) {
//        String sql = "SELECT suppliername,rawmaterialname FROM rawmaterialsupplier WHERE rawmaterialsupplieruid = ?";
//        return jdbcTemplate.queryForList(sql, rawmaterialsupplieruid);
//    }
//
//
//
//    // Retrieve all product UIDs
//    public List<String> fetchRawMaterialSupplierUIds() {
//        String sql = "SELECT rawmaterialsupplieruid FROM rawmaterialsupplier";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialsupplieruid"));
//    }
    
   
}