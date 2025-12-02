package com.prog.Dao.logistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.TransportationRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TransportationRequestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<TransportationRequest> rowMapper = (rs, rowNum) -> {
        TransportationRequest request = new TransportationRequest();
        request.setId(rs.getLong("id"));
        request.setTransportationrequestuid(rs.getString("transportationrequestuid"));
        request.setRequestdate(rs.getString("requestdate"));
        request.setRequestedby(rs.getString("requestedby"));
        request.setDepartmentname(rs.getString("departmentname"));
        request.setSourcewarehouseuid(rs.getString("sourcewarehouseuid"));
        request.setSourcelocation(rs.getString("sourcelocation"));
        request.setDestinationwarehouseuid(rs.getString("destinationwarehouseuid"));
        request.setDestinationlocation(rs.getString("destinationlocation"));
        request.setShipmenttype(rs.getString("shipmenttype"));
        request.setWeightvolume(rs.getString("weightvolume"));
        request.setModeoftransport(rs.getString("modeoftransport"));
        request.setExpecteddeliverydate(rs.getString("expecteddeliverydate"));
        request.setTransportprovidername(rs.getString("transportprovidername"));
        request.setVehiclenumber(rs.getString("vehiclenumber"));
        request.setDrivername(rs.getString("drivername"));
        request.setDrivercontact(rs.getString("drivercontact"));
        request.setApprovalstatus(rs.getString("approvalstatus"));
        
        return request;
    };

    private static final String sql="INSERT INTO transportationrequest (transportationrequestuid, requestdate, requestedby, departmentname, " +
            "sourcewarehouseuid,sourcelocation, destinationwarehouseuid, destinationlocation,shipmenttype, weightvolume, modeoftransport, expecteddeliverydate, " +
            "transportprovidername, vehiclenumber, drivername, drivercontact, approvalstatus, insertdate, updatedate) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int saveLogistics(TransportationRequest request) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
         

        return jdbcTemplate.update(sql,
        		request.getTransportationrequestuid(),
        		request.getRequestdate(), 
        		request.getRequestedby(),
                request.getDepartmentname(), 
                request.getSourcewarehouseuid(), 
                request.getSourcelocation(),
                request.getDestinationwarehouseuid(),
                request.getDestinationlocation(),
                request.getShipmenttype(), 
                request.getWeightvolume(), 
                request.getModeoftransport(),
                request.getExpecteddeliverydate(), 
                request.getTransportprovidername(), 
                request.getVehiclenumber(),
                request.getDrivername(), 
                request.getDrivercontact(), 
                request.getApprovalstatus(),
                formattedTimestamp,
                formattedTimestamp
                );
    }

    public int updateLogistics(TransportationRequest request) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
    	String sql = "UPDATE transportationrequest SET sourcewarehouseuid=?, sourcelocation=? , destinationwarehouseuid=? ,destinationlocation=? ,requestdate=?, requestedby=?, departmentname=?, " +
                      " shipmenttype=?, weightvolume=?, modeoftransport=?, " +
                     "expecteddeliverydate=?, transportprovidername=?, vehiclenumber=?, drivername=?, drivercontact=?, " +
                     "approvalstatus=?, updatedate=? WHERE id=?";

        return jdbcTemplate.update(sql,
        		request.getRequestdate(), 
        		request.getRequestedby(),
                request.getDepartmentname(),
                request.getSourcewarehouseuid(),
                request.getSourcelocation(),
                request.getDestinationwarehouseuid(),
                request.getDestinationlocation(),
                request.getShipmenttype(), 
                request.getWeightvolume(), 
                request.getModeoftransport(),
                request.getExpecteddeliverydate(), 
                request.getTransportprovidername(), 
                request.getVehiclenumber(),
                request.getDrivername(), 
                request.getDrivercontact(), 
                request.getApprovalstatus(),
                formattedTimestamp,
                request.getId());
    }

    public List<Map<String, Object>> getAllRequest() {
        String sql = "SELECT id, transportationrequestuid, destinationwarehouseuid,destinationlocation, sourcewarehouseuid,sourcelocation, requestedby, transportprovidername, drivername, drivercontact, approvalstatus\r\n"
        		+ "			FROM transportationrequest";
        return jdbcTemplate.queryForList(sql);
    }

    public TransportationRequest getRequestById(Long id) {
        String sql = "SELECT * FROM transportationrequest WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    public void deleteRequestById(Long id) {
        String sql = "DELETE FROM transportationrequest WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

   
   
  
 
}
