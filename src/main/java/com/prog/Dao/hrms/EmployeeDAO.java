package com.prog.Dao.hrms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Employee;


@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Save an employee to the database.
     *
     * @param employee Employee object to save
     */
    public int addEmployee(Employee employee) {
    	String sql = "INSERT INTO employee " +
    		    "(employeeuid,first_name, middle_name, last_name, date_of_birth, gender, marital_status, nationality, departmentuid, departmentname, " +
    		    "permanent_address_line1, permanent_address_line2, permanent_city, permanent_state, permanent_country, permanent_zip_code, " +
    		    "current_address_line1, current_address_line2, current_city, current_state, current_country, current_zip_code, " +
    		    "primary_contact_number, alternate_contact_number, personal_email, blood_group, " +
    		    "emergency_contact_name, emergency_relationship, emergency_contact_number, emergency_email, " +
    		    "passport_number, visa_number, visa_expiry_date, aadhar_ssn_file, upload_profile_photo_file, pan_tax_id_file, " +
    		    "education_certificates_file, professional_certificates_file, resume_file, aadhar_ssn_file_name, upload_profile_photo_file_name, pan_tax_id_file_name, " +
    		    "education_certificates_file_name, professional_certificates_file_name, resume_file_name,insertdate, updatedate) " +
    		    "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
       return jdbcTemplate.update(
            sql,
            employee.getEmployeeUID(),
            employee.getFirstName(),
            employee.getMiddleName(),
            employee.getLastName(),
            employee.getDateOfBirth(),
            employee.getGender(),
            employee.getMaritalStatus(),
            employee.getNationality(),
            employee.getDepartmentuid(),
            employee.getDepartmentname(),
            employee.getPermanentAddressLine1(),
            employee.getPermanentAddressLine2(),
            employee.getPermanentCity(),
            employee.getPermanentState(),
            employee.getPermanentCountry(),
            employee.getPermanentZipCode(),
            employee.getCurrentAddressLine1(),
            employee.getCurrentAddressLine2(),
            employee.getCurrentCity(),
            employee.getCurrentState(),
            employee.getCurrentCountry(),
            employee.getCurrentZipCode(),
            employee.getPrimaryContactNumber(),
            employee.getAlternateContactNumber(),
            employee.getPersonalEmail(),
            employee.getBloodGroup(),
            employee.getEmergencyContactName(),
            employee.getEmergencyRelationship(),
            employee.getEmergencyContactNumber(),
            employee.getEmergencyEmail(),
            employee.getPassportNumber(),
            employee.getVisaNumber(),
            employee.getVisaExpiryDate(),
            employee.getAadharSsnFile(),
            employee.getUploadProfilePhotoFile(),
            employee.getPanTaxIdFile(),
            employee.getEducationCertificatesFile(),
            employee.getProfessionalCertificatesFile(),
            employee.getResumeFile(),
            // ✅ new file name fields
            employee.getAadharSsnFileName(),
            employee.getUploadProfilePhotoFileName(),
            employee.getPanTaxIdFileName(),
            employee.getEducationCertificatesFileName(),
            employee.getProfessionalCertificatesFileName(),
            employee.getResumeFileName(),
            formattedTimestamp,
            formattedTimestamp
            
            
           
            
        );
       
    }

	
		
		 public List<Employee> getAllEmployees() {
		        String sql = "SELECT * FROM employee"; // Query to fetch all employees
		        return jdbcTemplate.query(sql, (rs, rowNum) -> {
		            Employee employee = new Employee();
		            employee.setId(rs.getLong("id"));
		            employee.setEmployeeUID(rs.getString("employeeuid"));
		            employee.setFirstName(rs.getString("first_name"));
		            employee.setMiddleName(rs.getString("middle_name"));
		            employee.setLastName(rs.getString("last_name"));
		            employee.setDateOfBirth(rs.getString("date_of_birth"));
		            employee.setGender(rs.getString("gender"));
		            employee.setMaritalStatus(rs.getString("marital_status"));
		            employee.setNationality(rs.getString("nationality"));
		            employee.setDepartmentuid(rs.getString("departmentuid"));
		            employee.setDepartmentname(rs.getString("departmentname"));
		            employee.setPermanentAddressLine1(rs.getString("permanent_address_line1"));
		            employee.setPermanentAddressLine2(rs.getString("permanent_address_line2"));
		            employee.setPermanentCity(rs.getString("permanent_city"));
		            employee.setPermanentState(rs.getString("permanent_state"));
		            employee.setPermanentCountry(rs.getString("permanent_country"));
		            employee.setPermanentZipCode(rs.getString("permanent_zip_code"));
		            employee.setCurrentAddressLine1(rs.getString("current_address_line1"));
		            employee.setCurrentAddressLine2(rs.getString("current_address_line2"));
		            employee.setCurrentCity(rs.getString("current_city"));
		            employee.setCurrentState(rs.getString("current_state"));
		            employee.setCurrentCountry(rs.getString("current_country"));
		            employee.setCurrentZipCode(rs.getString("current_zip_code"));
		            employee.setPrimaryContactNumber(rs.getString("primary_contact_number"));
		            employee.setAlternateContactNumber(rs.getString("alternate_contact_number"));
		            employee.setPersonalEmail(rs.getString("personal_email"));
		            employee.setBloodGroup(rs.getString("blood_group"));
		            employee.setEmergencyContactName(rs.getString("emergency_contact_name"));
		            employee.setEmergencyRelationship(rs.getString("emergency_relationship"));
		            employee.setEmergencyContactNumber(rs.getString("emergency_contact_number"));
		            employee.setEmergencyEmail(rs.getString("emergency_email"));
		            employee.setPassportNumber(rs.getString("passport_number"));
		            employee.setVisaNumber(rs.getString("visa_number"));
		            employee.setVisaExpiryDate(rs.getString("visa_expiry_date"));
		            employee.setAadharSsnFile(rs.getBytes("aadhar_ssn_file"));
		            employee.setUploadProfilePhotoFile(rs.getBytes("upload_profile_photo_file"));
		            employee.setPanTaxIdFile(rs.getBytes("pan_tax_id_file"));
		            employee.setEducationCertificatesFile(rs.getBytes("education_certificates_file"));
		            employee.setProfessionalCertificatesFile(rs.getBytes("professional_certificates_file"));
		            employee.setResumeFile(rs.getBytes("resume_file"));

		            // Set other fields similarly...
		            return employee;
		        });
	}
		 
		 
		 public int deleteEmployee(Long id) {
			    String sql = "DELETE FROM employee WHERE id = ?";
			    return jdbcTemplate.update(sql, id);
			}
		 
		 @SuppressWarnings("deprecation")
		public Employee getEmployeeById(Long id) {
			    String sql = "SELECT * FROM employee WHERE id = ?";
			    return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
			        Employee employee = new Employee();
			        employee.setId(rs.getLong("id")); // Assuming you have an ID field
			        employee.setEmployeeUID(rs.getString("employeeuid"));
			        employee.setFirstName(rs.getString("first_name"));
			        employee.setMiddleName(rs.getString("middle_name"));
			        employee.setLastName(rs.getString("last_name"));
			        employee.setDateOfBirth(rs.getString("date_of_birth"));
			        employee.setGender(rs.getString("gender"));
			        employee.setMaritalStatus(rs.getString("marital_status"));
			        employee.setNationality(rs.getString("nationality"));
			        employee.setDepartmentuid(rs.getString("departmentuid"));
			        employee.setDepartmentname(rs.getString("departmentname"));
			        employee.setPermanentAddressLine1(rs.getString("permanent_address_line1"));
			        employee.setPermanentAddressLine2(rs.getString("permanent_address_line2"));
			        employee.setPermanentCity(rs.getString("permanent_city"));
			        employee.setPermanentState(rs.getString("permanent_state"));
			        employee.setPermanentCountry(rs.getString("permanent_country"));
			        employee.setPermanentZipCode(rs.getString("permanent_zip_code"));
			        employee.setCurrentAddressLine1(rs.getString("current_address_line1"));
			        employee.setCurrentAddressLine2(rs.getString("current_address_line2"));
			        employee.setCurrentCity(rs.getString("current_city"));
			        employee.setCurrentState(rs.getString("current_state"));
			        employee.setCurrentCountry(rs.getString("current_country"));
			        employee.setCurrentZipCode(rs.getString("current_zip_code"));
			        employee.setPrimaryContactNumber(rs.getString("primary_contact_number"));
			        employee.setAlternateContactNumber(rs.getString("alternate_contact_number"));
			        employee.setPersonalEmail(rs.getString("personal_email"));
			        employee.setBloodGroup(rs.getString("blood_group"));
			        employee.setEmergencyContactName(rs.getString("emergency_contact_name"));
			        employee.setEmergencyRelationship(rs.getString("emergency_relationship"));
			        employee.setEmergencyContactNumber(rs.getString("emergency_contact_number"));
			        employee.setEmergencyEmail(rs.getString("emergency_email"));
			        employee.setPassportNumber(rs.getString("passport_number"));
			        employee.setVisaNumber(rs.getString("visa_number"));
			        employee.setVisaExpiryDate(rs.getString("visa_expiry_date"));
			        employee.setAadharSsnFile(rs.getBytes("aadhar_ssn_file"));
			        employee.setAadharSsnFileName(rs.getString("aadhar_ssn_file_name"));
			        employee.setUploadProfilePhotoFile(rs.getBytes("upload_profile_photo_file"));
			        employee.setUploadProfilePhotoFileName(rs.getString("upload_profile_photo_file_name"));
			        employee.setPanTaxIdFile(rs.getBytes("pan_tax_id_file"));
			        employee.setPanTaxIdFileName(rs.getString("pan_tax_id_file_name")); // FIXED

			        employee.setEducationCertificatesFile(rs.getBytes("education_certificates_file"));
			        employee.setEducationCertificatesFileName(rs.getString("education_certificates_file_name")); // FIXED

			        employee.setProfessionalCertificatesFile(rs.getBytes("professional_certificates_file"));
			        employee.setProfessionalCertificatesFileName(rs.getString("professional_certificates_file_name")); // FIXED

			        employee.setResumeFile(rs.getBytes("resume_file"));
			        employee.setResumeFileName(rs.getString("resume_file_name")); // FIXED

			        return employee;
			    });
			}
		

		 public int updateEmployee(Employee employee) {
			    String sql = "UPDATE employee SET employeeuid=?, first_name=?, middle_name=?, last_name=?, departmentuid=?, date_of_birth=?, " +
			                 "gender=?, marital_status=?, nationality=?, permanent_address_line1=?, permanent_address_line2=?, " +
			                 "permanent_city=?, permanent_state=?, permanent_country=?, permanent_zip_code=?, " +
			                 "current_address_line1=?, current_address_line2=?, current_city=?, current_state=?, current_country=?, " +
			                 "current_zip_code=?, primary_contact_number=?, alternate_contact_number=?, personal_email=?, blood_group=?, " +
			                 "emergency_contact_name=?, emergency_relationship=?, emergency_contact_number=?, emergency_email=?, " +
			                 "passport_number=?, visa_number=?, visa_expiry_date=?, " +
			                 "aadhar_ssn_file=?, upload_profile_photo_file=?, pan_tax_id_file=?, education_certificates_file=?, " +
			                 "professional_certificates_file=?, resume_file=?, " +
			                 "aadhar_ssn_file_name=?, upload_profile_photo_file_name=?, pan_tax_id_file_name=?, " +
			                 "education_certificates_file_name=?, professional_certificates_file_name=?, resume_file_name=?, updatedate=?  " +
			                 "WHERE id = ?";
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		      
			    return jdbcTemplate.update(sql,
			        employee.getEmployeeUID(),
			        employee.getFirstName(),
			        employee.getMiddleName(),
			        employee.getLastName(),
			        employee.getDepartmentuid(),
			        employee.getDateOfBirth(),
			        employee.getGender(),
			        employee.getMaritalStatus(),
			        employee.getNationality(),
			        employee.getPermanentAddressLine1(),
			        employee.getPermanentAddressLine2(),
			        employee.getPermanentCity(),
			        employee.getPermanentState(),
			        employee.getPermanentCountry(),
			        employee.getPermanentZipCode(),
			        employee.getCurrentAddressLine1(),
			        employee.getCurrentAddressLine2(),
			        employee.getCurrentCity(),
			        employee.getCurrentState(),
			        employee.getCurrentCountry(),
			        employee.getCurrentZipCode(),
			        employee.getPrimaryContactNumber(),
			        employee.getAlternateContactNumber(),
			        employee.getPersonalEmail(),
			        employee.getBloodGroup(),
			        employee.getEmergencyContactName(),
			        employee.getEmergencyRelationship(),
			        employee.getEmergencyContactNumber(),
			        employee.getEmergencyEmail(),
			        employee.getPassportNumber(),
			        employee.getVisaNumber(),
			        employee.getVisaExpiryDate(),
			        employee.getAadharSsnFile(),
			        employee.getUploadProfilePhotoFile(),
			        employee.getPanTaxIdFile(),
			        employee.getEducationCertificatesFile(),
			        employee.getProfessionalCertificatesFile(),
			        employee.getResumeFile(),
			        // Newly added file name fields
			        employee.getAadharSsnFileName(),
			        employee.getUploadProfilePhotoFileName(),
			        employee.getPanTaxIdFileName(),
			        employee.getEducationCertificatesFileName(),
			        employee.getProfessionalCertificatesFileName(),
			        employee.getResumeFileName(),
			        formattedTimestamp,
		            
			        // WHERE id = ?
			        employee.getId()
			    );
			}
		 
		 public List<Employee> getBasicEmployeeDetails() {
		        String sql = "SELECT employeeuid, first_name, last_name FROM employee";

		        return jdbcTemplate.query(sql, (rs, rowNum) -> {
		            Employee emp = new Employee();
		            emp.setEmployeeUID(rs.getString("employeeuid"));
		            emp.setFirstName(rs.getString("first_name"));
		            emp.setLastName(rs.getString("last_name"));
		            return emp;
		        });
		        
		        
		    }

		 
		 
		 // FETCHING
		 public List<String> getallemplyeuids(){
			 String sql = "SELECT employeeuid FROM employee";
			 return jdbcTemplate.queryForList(sql, String.class);
		 }
		 
		// FETCHING
		 public Employee getEmployeedetailsByuid(String employeeuid) {
			 String sql = "SELECT first_name, last_name, departmentname FROM employee WHERE employeeuid = ? ";
			 return jdbcTemplate.queryForObject(sql, new Object[] {employeeuid},(rs,rowNum ) -> {
				 Employee employee = new Employee();
				 employee.setFirstName(rs.getString("first_name"));
				 employee.setLastName(rs.getString("last_name"));
				 employee.setDepartmentname(rs.getString("departmentname"));
				 
				 return employee;
				 

			 });
		 }
		 public boolean isDuplicatePrimaryContact(String contactNumber, Long currentId) {
			    String sql;
			    Object[] params;

			    if (currentId == null) {
			        // New employee, check if contact already exists
			        sql = "SELECT id FROM employee WHERE primary_contact_number = ?";
			        params = new Object[]{ contactNumber };
			    } else {
			        // Existing employee update, check if any other employee has this contact
			        sql = "SELECT id FROM employee WHERE primary_contact_number = ? AND id != ?";
			        params = new Object[]{ contactNumber, currentId };
			    }

			    List<Long> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getLong("id"));
			    return !result.isEmpty(); // true means duplicate found
			}


			   
			

		 public boolean isPersonalEmailDuplicate(String email, Long currentId) {
			    String sql;
			    Object[] params;

			    if (currentId == null) {
			        sql = "SELECT id FROM employee WHERE personal_email = ?";
			        params = new Object[]{email};
			    } else {
			        sql = "SELECT id FROM employee WHERE personal_email = ? AND id != ?";
			        params = new Object[]{email, currentId};
			    }

			    List<Long> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getLong("id"));
			    return !result.isEmpty();
			}

		 public boolean isPassportNumberDuplicate(String passportNumber, Long currentId) {
			    String sql;
			    Object[] params;

			    if (currentId == null) {
			        sql = "SELECT id FROM employee WHERE passport_number = ?";
			        params = new Object[]{passportNumber};
			    } else {
			        sql = "SELECT id FROM employee WHERE passport_number = ? AND id != ?";
			        params = new Object[]{passportNumber, currentId};
			    }

			    List<Long> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getLong("id"));
			    return !result.isEmpty(); // true if duplicate found
			}

			public boolean isVisaNumberDuplicate(String visaNumber, Long currentId) {
			    String sql;
			    Object[] params;

			    if (currentId == null) {
			        sql = "SELECT id FROM employee WHERE visa_number = ?";
			        params = new Object[]{visaNumber};
			    } else {
			        sql = "SELECT id FROM employee WHERE visa_number = ? AND id != ?";
			        params = new Object[]{visaNumber, currentId};
			    }

			    List<Long> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getLong("id"));
			    return !result.isEmpty(); // true if duplicate found
			}







		
}