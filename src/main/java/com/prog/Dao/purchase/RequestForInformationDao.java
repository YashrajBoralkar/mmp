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

import com.prog.model.erp.RequestForInformation;



@Repository
public class RequestForInformationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String insertSql = "INSERT INTO requestforinformation (requestforinformationuid, rfiissuedate, supplieruid, industryexpertise, productservicedetails, responsedeadline,  insertdate, updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public int saveRFIForm(RequestForInformation rfiForm) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        return jdbcTemplate.update(insertSql,
                rfiForm.getRequestforinformationuid(),
                rfiForm.getRfiissuedate(),
                rfiForm.getSupplieruid(),
                rfiForm.getIndustryexpertise(),
                rfiForm.getProductservicedetails(),
                rfiForm.getResponsedeadline(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    public List<Map<String, Object>> fetchAllRFIForms() {
        String sql = "SELECT rfi.id, rfi.requestforinformationuid, rfi.rfiissuedate, rfi.responsedeadline, si.suppliername FROM requestforinformation rfi JOIN supplierregistration si ON si.supplieruid = rfi.supplieruid;";
        return jdbcTemplate.queryForList(sql);
    }

    public void deleteRFIFormById(Long id) {
        String sql = "DELETE FROM requestforinformation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public RequestForInformation getRFIFormById(Long id) {
        String sql = "SELECT * FROM requestforinformation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RFIFormRowMapper(), id);
    }

    public void updateRFIForm(RequestForInformation rfiForm) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        String sql = "UPDATE requestforinformation SET  rfiissuedate = ?, industryexpertise = ?, productservicedetails = ?, responsedeadline = ?,  updatedate = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                rfiForm.getRfiissuedate(),
                rfiForm.getIndustryexpertise(),
                rfiForm.getProductservicedetails(),
                rfiForm.getResponsedeadline(),
                formattedTimestamp,

                rfiForm.getId()
        );
    }

    class RFIFormRowMapper implements RowMapper<RequestForInformation> {
        @Override
        public RequestForInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        	RequestForInformation rfiForm = new RequestForInformation();
            rfiForm.setId(rs.getLong("id"));
            rfiForm.setRequestforinformationuid(rs.getString("requestforinformationuid"));
            rfiForm.setRfiissuedate(rs.getString("rfiissuedate"));
            rfiForm.setSupplieruid(rs.getString("supplieruid"));
            rfiForm.setIndustryexpertise(rs.getString("industryexpertise"));
            rfiForm.setProductservicedetails(rs.getString("productservicedetails"));
            rfiForm.setResponsedeadline(rs.getString("responsedeadline"));
            return rfiForm;
        }
    }
    
    

}
