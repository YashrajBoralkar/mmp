package com.prog.Dao.hrms;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Employee;
import com.prog.model.erp.PayrollDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PayrollDetailsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper for PayrollDetails
    private RowMapper<PayrollDetails> rowMapper = (rs, rowNum) -> {
        PayrollDetails payrollDetails = new PayrollDetails();
        payrollDetails.setId(rs.getLong("id"));
        payrollDetails.setEmployeeuid(rs.getString("employeeuid"));
        payrollDetails.setPayrolluid(rs.getString("payrolluid"));
		payrollDetails.setBankName(rs.getString("bank_name"));
        payrollDetails.setBranchName(rs.getString("branch_name"));
        payrollDetails.setAccountHolderName(rs.getString("account_holder_name"));
        payrollDetails.setBankAccountNumber(rs.getString("bank_account_number"));
        payrollDetails.setIfscCode(rs.getString("ifsc_code"));
        payrollDetails.setCurrency(rs.getString("currency"));
        payrollDetails.setTaxResidencyStatus(rs.getString("tax_residency_status"));
        payrollDetails.setTaxIdentificationNumber(rs.getString("tax_identification_number"));
        return payrollDetails;
    };

    // Save (Insert or Update)
    public void save(PayrollDetails payrollDetails) {
        if (payrollDetails.getId() == null) {
            // Insert new record
            String sql = "INSERT INTO payroll_details " +
                         "(id,employeeuid, payrolluid, bank_name, branch_name, account_holder_name, bank_account_number, ifsc_code, currency, " +
                         "tax_residency_status, tax_identification_number, insertdate, updatedate) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
           
            jdbcTemplate.update(
                sql,
                payrollDetails.getId(),
                payrollDetails.getEmployeeuid(),
                payrollDetails.getPayrolluid(),
				payrollDetails.getBankName(),
                payrollDetails.getBranchName(),
                payrollDetails.getAccountHolderName(),
                payrollDetails.getBankAccountNumber(),
                payrollDetails.getIfscCode(),
                payrollDetails.getCurrency(),
                payrollDetails.getTaxResidencyStatus(),
                payrollDetails.getTaxIdentificationNumber(),
                formattedTimestamp,
                formattedTimestamp

                
            );
        } else {
            // Update existing record
            String sql = "UPDATE payroll_details SET " +
                         "bank_name = ?, branch_name = ?, account_holder_name = ?, bank_account_number = ?, " +
                         "ifsc_code = ?, currency = ?, tax_residency_status = ?, tax_identification_number = ?, updatedate=? " +
                         "WHERE id = ?";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
           
            jdbcTemplate.update(
                sql,
                payrollDetails.getBankName(),
                payrollDetails.getBranchName(),
                payrollDetails.getAccountHolderName(),
                payrollDetails.getBankAccountNumber(),
                payrollDetails.getIfscCode(),
                payrollDetails.getCurrency(),
                payrollDetails.getTaxResidencyStatus(),
                payrollDetails.getTaxIdentificationNumber(),
                formattedTimestamp,
                payrollDetails.getId()
            );
        }
    }

    // Find all payroll details
    public List<Map<String, Object>> findAll() {
        String sql = "select pd.id,pd.bank_name, pd.branch_name, pd.tax_residency_status,\r\n"
        		+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from payroll_details pd\r\n"
        		+ "join employee emp on pd.employeeuid = emp.employeeuid";
        return jdbcTemplate.queryForList(sql);
    }

    // Delete payroll details by ID
    public void delete(Long id) {
        String sql = "DELETE FROM payroll_details WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Find payroll details by ID
    public PayrollDetails findById(Long id) {
        String sql = "SELECT * FROM payroll_details WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }
}   
