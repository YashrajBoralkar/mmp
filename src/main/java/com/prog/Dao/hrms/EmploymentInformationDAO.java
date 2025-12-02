package com.prog.Dao.hrms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.EmploymentInformation;

@Repository
public class EmploymentInformationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(EmploymentInformation employmentInformation) {
        String sql = "INSERT INTO employment_information " +
                     "(employeeuid, empinfouid, designation, reporting_manager, employment_type, work_location, " +
                     "office_address, remote_location, date_of_joining, probation_end_date, status, shift_details, work_hours, insertdate, updatedate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        jdbcTemplate.update(
           sql,
           employmentInformation.getEmployeeuid(),
           employmentInformation.getEmpinfouid(),
           employmentInformation.getDesignation(),
           employmentInformation.getReportingManager(),
           employmentInformation.getEmploymentType(),
           employmentInformation.getWorkLocation(),
           employmentInformation.getOfficeAddress(),
           employmentInformation.getRemoteLocation(),
           employmentInformation.getDateOfJoining(),
           employmentInformation.getProbationEndDate(),
           employmentInformation.getStatus(),
           employmentInformation.getShiftDetails(),
         employmentInformation.getWorkHours(),
         formattedTimestamp,
         formattedTimestamp
      );
    
        
    }  
    
    
    public List<Map<String, Object>> findAll() {
        String sql = "select einfo.id,einfo.designation,einfo.reporting_manager, einfo.office_address, einfo.date_of_joining, einfo.shift_details, einfo.work_hours,\r\n"
        		+ "emp.employeeuid, emp.departmentname from employment_information einfo\r\n"
        		+ "join employee emp on einfo.employeeuid = emp.employeeuid";
        return jdbcTemplate.queryForList(sql);
    }
    
    public void delete(Long id) {
        String sql = "DELETE FROM employment_information WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @SuppressWarnings("deprecation")
	public EmploymentInformation findById(Long id) {
        String sql = "SELECT * FROM employment_information WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            EmploymentInformation info = new EmploymentInformation();
            info.setId(rs.getLong("id"));
            info.setEmployeeuid(rs.getString("employeeuid"));
            info.setEmpinfouid(rs.getString("empinfouid"));
            info.setDesignation(rs.getString("designation"));
            info.setReportingManager(rs.getString("reporting_manager"));
            info.setEmploymentType(rs.getString("employment_type"));
            info.setWorkLocation(rs.getString("work_location"));
            info.setOfficeAddress(rs.getString("office_address"));
            info.setRemoteLocation(rs.getString("remote_location"));
            info.setDateOfJoining(rs.getString("date_of_joining"));
            info.setProbationEndDate(rs.getString("probation_end_date"));
            info.setStatus(rs.getString("status"));
            info.setShiftDetails(rs.getString("shift_details"));
            info.setWorkHours(rs.getString("work_hours"));
            return info;
        });
    
    }
    
    
    public void update(EmploymentInformation employmentInformation) {
        String sql = "UPDATE employment_information SET " +
                     " designation = ?, reporting_manager = ?, employment_type = ?, work_location = ?, " +
                     "office_address = ?, remote_location = ?, date_of_joining = ?, probation_end_date = ?, status = ?, " +
                     "shift_details = ?, work_hours = ?, updatedate=?  " +
                     "WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        jdbcTemplate.update(
           sql,
           
           
           employmentInformation.getDesignation(),
           employmentInformation.getReportingManager(),
           employmentInformation.getEmploymentType(),
           employmentInformation.getWorkLocation(),
           employmentInformation.getOfficeAddress(),
           employmentInformation.getRemoteLocation(),
           employmentInformation.getDateOfJoining(),
           employmentInformation.getProbationEndDate(),
           employmentInformation.getStatus(),
           employmentInformation.getShiftDetails(),
           employmentInformation.getWorkHours(),
           formattedTimestamp,
           employmentInformation.getId()
        );
    }
  
}

