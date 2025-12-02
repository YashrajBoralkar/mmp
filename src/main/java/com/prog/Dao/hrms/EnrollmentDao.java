package com.prog.Dao.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Employee;
import com.prog.model.erp.Enrollment;
import com.prog.model.erp.Onboarding;
import com.prog.model.erp.Training;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class EnrollmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
   

    // Insert a new enrollment
    public int addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (enrollmentuid,additional_info, completion_date, " +
                " employee_feedback, employeeuid, enrollment_status, overall_rating, " +
                "progress_description, quality_feedback, registration_date, trainer_feedback, training_content_feedback,training_uid) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
        		enrollment.getEnrollmentuid(),
                enrollment.getAdditionalInfo(),
                enrollment.getCompletionDate(),
                enrollment.getEmployeeFeedback(),
                enrollment.getEmployeeuid(),
                enrollment.getEnrollmentStatus(),
                enrollment.getOverallRating(),
                enrollment.getProgressDescription(),
                enrollment.getQualityFeedback(),
                enrollment.getRegistrationDate(),
                enrollment.getTrainerFeedback(),
                enrollment.getTrainingContentFeedback(),
                enrollment.getTrainingUid());
    }

    // Update an existing enrollment
    public int updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE enrollment SET additional_info = ?, completion_date = ?, " +
                "employee_feedback = ?, enrollment_status = ?, overall_rating = ?, " +
                "progress_description = ?, quality_feedback = ?, registration_date = ?, " +
                "trainer_feedback = ?, training_content_feedback = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                enrollment.getAdditionalInfo(),
                enrollment.getCompletionDate(),
                enrollment.getEmployeeFeedback(),
                enrollment.getEnrollmentStatus(),
                enrollment.getOverallRating(),
                enrollment.getProgressDescription(),
                enrollment.getQualityFeedback(),
                enrollment.getRegistrationDate(),
                enrollment.getTrainerFeedback(),
                enrollment.getTrainingContentFeedback(),
                enrollment.getId());
    }

    
   


    // Fetch all enrollments
    public List<Map<String, Object >> getAllEnrollment() {
    	String sql="select en.id, en.registration_date, en.completion_date, en.enrollment_status, \r\n"
    			+ "emp.employeeuid,emp.first_name,emp.last_name, emp.departmentname, tr.trainer_name,tr.trainingtitle,tr.duration\r\n"
    			+ "from enrollment en\r\n"
    			+ "join employee emp on en.employeeuid = emp.employeeuid\r\n"
    			+ "join training tr on en.training_uid = tr.training_uid;\r\n"
    			+ "";
		return jdbcTemplate.queryForList(sql);
	};

    // Fetch a single enrollment by ID
    public Enrollment getEnrollmentById(Long id) {
        String sql = "SELECT * FROM enrollment WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EnrollmentRowMapper(), id);
    }

    // Delete an enrollment by ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM enrollment WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No enrollment found with id " + id);
        }
    }

    // RowMapper for Enrollment
    private static class EnrollmentRowMapper implements RowMapper<Enrollment> {
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enrollment enrollment = new Enrollment();
            enrollment.setId(rs.getLong("id"));
            enrollment.setEnrollmentuid(rs.getString("enrollmentuid"));
            enrollment.setEmployeeuid(rs.getString("employeeuid"));
            enrollment.setTrainingUid(rs.getString("training_uid"));
            enrollment.setRegistrationDate(rs.getObject("registration_date", LocalDate.class));
            enrollment.setEnrollmentStatus(rs.getString("enrollment_status"));
            enrollment.setCompletionDate(rs.getObject("completion_date", LocalDate.class));
            enrollment.setProgressDescription(rs.getString("progress_description"));
            enrollment.setTrainerFeedback(rs.getString("trainer_feedback"));
            enrollment.setEmployeeFeedback(rs.getString("employee_feedback"));
            enrollment.setTrainingContentFeedback(rs.getString("training_content_feedback"));
            enrollment.setQualityFeedback(rs.getString("quality_feedback"));
            enrollment.setOverallRating(rs.getInt("overall_rating"));
            enrollment.setAdditionalInfo(rs.getString("additional_info"));

            return enrollment;
        }
        
       
    }
    
    //FETCHING
    public Onboarding getDesignationdetailsByuid(String employeeuid) {
        if (employeeuid == null || employeeuid.trim().isEmpty()) {
            return null; // Avoid querying with null/empty employee ID
        }

        String sql = "SELECT designation FROM Onboarding WHERE employeeuid = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{employeeuid}, (rs, rowNum) -> {
                Onboarding onboarding = new Onboarding();
                onboarding.setDesignation(rs.getString("designation"));
                return onboarding;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // Handle case where no record is found
        }
    }
}
