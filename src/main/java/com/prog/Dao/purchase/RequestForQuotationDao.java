package com.prog.Dao.purchase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.RequestforQuotation;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class RequestForQuotationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RequestforQuotation> rowMapper = (rs, rowNum) -> {
        RequestforQuotation rfq = new RequestforQuotation();
        rfq.setId(rs.getLong("id"));
        rfq.setRequestforquotationuid(rs.getString("requestforquotationuid"));
        rfq.setRfqdate(rs.getString("rfqdate"));
        rfq.setIssuedby(rs.getString("issuedby"));
        rfq.setRawmaterialuid(rs.getString("rawmaterialuid"));   // ✅ only rawmaterialuid here
        rfq.setRawmaterialname(rs.getString("rawmaterialname"));
        rfq.setExpecteddeliverydate(rs.getString("expecteddeliverydate"));
        rfq.setSupplierdetails(rs.getString("supplierdetails"));
        return rfq;
    };


    public int saveRFQ(RequestforQuotation rfq) {
        String sql = "INSERT INTO requestforquotation (requestforquotationuid, rfqdate, issuedby, " +
                     "rawmaterialsupplieruid, rawmaterialuid,rawmaterialname, " +
                     "  expecteddeliverydate, supplierdetails, insertdate, updatedate) " +
                     "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        

        return jdbcTemplate.update(sql, 
        		rfq.getRequestforquotationuid(), 
        		rfq.getRfqdate(), 
        		rfq.getIssuedby(),
              //  rfq.getSubmissiondeadline(), 
                rfq.getRawmaterialsupplieruid(),
                rfq.getRawmaterialuid(), 
                rfq.getRawmaterialname(),
              //  rfq.getQuantityrequired(),
               // rfq.getUnitofmeasure(), 
                rfq.getExpecteddeliverydate(),
                rfq.getSupplierdetails(),   
                formattedTimestamp,
                formattedTimestamp
                );
    }
    public int updateRFQ(RequestforQuotation rfq) {
        String sql = "UPDATE requestforquotation SET " +
                     "rfqdate=?, issuedby=?, rawmaterialuid=?, rawmaterialname=?, " +
                     "expecteddeliverydate=?, supplierdetails=?, updatedate=? " +
                     "WHERE id=?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(sql,
                rfq.getRfqdate(),
                rfq.getIssuedby(),
                rfq.getRawmaterialuid(),
                rfq.getRawmaterialname(),
                rfq.getExpecteddeliverydate(),
                rfq.getSupplierdetails(), // ✅ JSON update
                formattedTimestamp,
                rfq.getId()
        );
    }


    public RequestforQuotation getRFQById(Long id) {
        String sql = "SELECT * FROM requestforquotation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Map<String, Object>> getAllRFQs() {
        String sql = "SELECT rfq.id, rfq.requestforquotationuid, rfq.issuedby, " +
                     "rfq.expecteddeliverydate, rfq.rawmaterialuid, rfq.supplierdetails, " +
                     "rm.rawmaterialname " +
                     "FROM requestforquotation rfq " +
                     "JOIN rawmaterialinfo rm ON rm.rawmaterialuid = rfq.rawmaterialuid";
        return jdbcTemplate.queryForList(sql);
    }


    public int deleteRFQ(Long id) {
        String sql = "DELETE FROM requestforquotation WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

	

	
}
