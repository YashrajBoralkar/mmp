package com.prog.Dao.hrms;
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

import com.prog.model.erp.Onboarding;

@Repository
public class OnboardingDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String sql="insert into onboarding (onboardinguid, employeeuid,academiccertificates, accesstype, allocationdate, approvedby, assetid, assetname, assettype, assignedto, conditionatallocation, configurationdetails, dateofjoining, employeeagreementsigned, identityproof, levelofaccess, managername, mandatorytrainingprogram, offerlettersigned, orientationdate, serialnumber, systemaccess, taxdocuments,designation,insertdate, updatedate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
	public int saveonboarding (Onboarding onboarding) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formattedTimestamp = LocalDateTime.now().format(formatter);
        
		 return jdbcTemplate.update(sql, 
		 
	     onboarding.getOnboardinguid(),
	     onboarding.getEmployeeuid(),
	     onboarding.getAcademiccertificates(),
		 onboarding.getAccesstype(),
		 onboarding.getAllocationdate(),
		 onboarding.getApprovedby(),
		 onboarding.getAssetid(),
		 onboarding.getAssetname(),
		 onboarding.getAssettype(),
		 onboarding.getAssignedto(),
		 onboarding.getConditionatallocation(),
		 onboarding.getConfigurationdetails(),
		 onboarding.getDateofjoining(),
		 onboarding.getEmployeeagreementsigned(),
		 onboarding.getIdentityproof(),
		 onboarding.getLevelofaccess(),
		 onboarding.getManagername(),
		 onboarding.getMandatorytrainingprogram(),
		 onboarding.getOfferlettersigned(),
		 onboarding.getOrientationdate(),
		 onboarding.getSerialnumber(),
		 onboarding.getSystemaccess(),
		 onboarding.getTaxdocuments(),
		 onboarding.getDesignation(),
		  formattedTimestamp,
          formattedTimestamp
	
		);
				 
}
	
	
	public Onboarding getonboardingbyid(Long id) 
	{
		String sql="select * from onboarding where id=?";
		return jdbcTemplate.queryForObject(sql,new OnboardingRowmapper(),id);
	}
	
	public List<Map<String, Object>>getAllonboarding() 
	{
		String sql="select onbod.id,onbod.designation, onbod.dateofjoining,onbod.managername,onbod.approvedby,\r\n"
				+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from onboarding onbod\r\n"
				+ "join employee emp on onbod.employeeuid = emp.employeeuid;\r\n"
				+ "";
		return jdbcTemplate.queryForList(sql);
			};
	
	public void deleteonboardingbyId(Long id) 
	{
		String sql = "DELETE FROM onboarding WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No Onboarding found with id "+id);
        }
		
	}
	
	public int updateonboarding(Onboarding onboarding) 
	{
		String sql = "UPDATE onboarding SET academiccertificates = ?, accesstype = ?, allocationdate = ?, " +
                "approvedby = ?, assetid = ?, assetname = ?, assettype = ?, assignedto = ?, " +
                "conditionatallocation = ?, configurationdetails = ?, dateofjoining = ?, " +
                "employeeagreementsigned = ?,identityproof = ?, " +
                "levelofaccess = ?, managername = ?, mandatorytrainingprogram = ?, offerlettersigned = ?, " +
                "orientationdate = ?, serialnumber = ?, systemaccess = ?, taxdocuments = ? ,designation = ?, updatedate=? "+
                "WHERE id = ?";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
		return jdbcTemplate.update(sql,
				
				onboarding.getAcademiccertificates(),
				onboarding.getAccesstype(),
				onboarding.getAllocationdate(),
				onboarding.getApprovedby(),
				onboarding.getAssetid(),
				onboarding.getAssetname(),
				onboarding.getAssettype(),
				onboarding.getAssignedto(),
				onboarding.getConditionatallocation(),
				onboarding.getConfigurationdetails(),
				onboarding.getDateofjoining(),
				onboarding.getEmployeeagreementsigned(),
				onboarding.getIdentityproof(),
				onboarding.getLevelofaccess(),
				onboarding.getManagername(),
				onboarding.getMandatorytrainingprogram(),
				onboarding.getOfferlettersigned(),
				onboarding.getOrientationdate(),
				onboarding.getSerialnumber(),
				onboarding.getSystemaccess(),
				onboarding.getTaxdocuments(),
				onboarding.getDesignation(),
                formattedTimestamp,
				onboarding.getId());
	
				
				
	}
	
	private static class OnboardingRowmapper implements RowMapper<Onboarding>
	{
		public Onboarding mapRow(ResultSet rs ,int rowNum) throws SQLException {
			Onboarding on=new Onboarding();
			on.setId(rs.getLong("id"));
			on.setOnboardinguid(rs.getString("onboardinguid"));
			on.setEmployeeuid(rs.getString("employeeuid"));
			on.setAcademiccertificates(rs.getString("academiccertificates"));
			on.setAccesstype(rs.getString("accesstype"));
			on.setAllocationdate(rs.getString("allocationdate"));
			on.setApprovedby(rs.getString("approvedby"));
			on.setAssetid(rs.getString("assetid"));
			on.setAssetname(rs.getString("assetname"));
			on.setAssettype(rs.getString("assettype"));
			on.setAssignedto(rs.getString("assignedto"));
			on.setConditionatallocation(rs.getString("conditionatallocation"));
			on.setConfigurationdetails(rs.getString("configurationdetails"));
			on.setDateofjoining(rs.getString("dateofjoining"));
			on.setEmployeeagreementsigned(rs.getString("employeeagreementsigned"));
			on.setIdentityproof(rs.getString("identityproof"));
			on.setLevelofaccess(rs.getString("levelofaccess"));
			on.setManagername(rs.getString("managername"));
			on.setMandatorytrainingprogram(rs.getString("mandatorytrainingprogram"));
			on.setOfferlettersigned(rs.getString("offerlettersigned"));
			on.setOrientationdate(rs.getString("orientationdate"));
			on.setSerialnumber(rs.getString("serialnumber"));
			on.setSystemaccess(rs.getString("systemaccess"));
			on.setTaxdocuments(rs.getString("taxdocuments"));
			on.setDesignation(rs.getString("designation"));

			
			return on;
			
		}
		
	}


}

