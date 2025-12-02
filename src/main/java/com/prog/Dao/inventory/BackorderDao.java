package com.prog.Dao.inventory;

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

import com.prog.model.erp.Backorder;




@Repository
public class BackorderDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper for Backorder entity
    private static class BackorderRowMapper implements RowMapper<Backorder> {
        @Override
        public Backorder mapRow(ResultSet rs, int rowNum) throws SQLException {
            Backorder bo = new Backorder();
            bo.setId(rs.getLong("id"));
            bo.setSalesorderuid(rs.getString("salesorderuid"));
            bo.setLotuid(rs.getString("lotuid"));
            bo.setAccepteddeliverydate(rs.getString("accepteddeliverydate"));
            bo.setFulfillmentstatus(rs.getString("fulfillmentstatus"));
            bo.setStockavailability(rs.getString("stockavailability"));
            bo.setWarehouseuid(rs.getString("warehouseuid"));
            bo.setExpectedfulfillmentdate(rs.getString("expectedfulfillmentdate"));
            bo.setTrackbackorder(rs.getString("trackbackorder"));
            bo.setCustomerdate(rs.getString("customerdate"));
            bo.setCreateddate(rs.getString("createddate"));
            bo.setLastmodifiedby(rs.getString("lastmodifiedby"));

            return bo;
        }
    }

    
    
    
    
    public List<Map<String, Object>> showBackorder() {
        String sql = "                             SELECT bo.id,bo.accepteddeliverydate,bo.fulfillmentstatus,bo.stockavailability,bo.warehouseuid,bo.expectedfulfillmentdate,\r\n"
        		+ "        			bo.trackbackorder,bo.customerdate,bo.createddate,bo.lastmodifiedby,\r\n"
        		+ "        			lt.originalorderedquantity,lt.pendingquantity,lt.unitprice,lt.totalpendingvalue,\r\n"
        		+ "        			so.orderdate, so.customername,\r\n"
        		+ "        			wr.currentstock,wr.warehouselocation\r\n"
        		+ "        			FROM backorder bo\r\n"
        		+ "        			JOIN salesorder so ON bo.salesorderuid = so.salesorderuid\r\n"
        		+ "        			JOIN lotdetails lt ON bo.lotuid = lt.lotuid\r\n"
        		+ "        			JOIN warehousedetails wr ON bo.warehouseuid = wr.warehouseuid;\r\n"
        		+ "";
                             
        return jdbcTemplate.queryForList(sql);
    }


    // Fetch backorder by ID
    public Backorder getBackorderById(Long id) {
        String sql = "SELECT * FROM backorder WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BackorderRowMapper(), id);
    }

    // Add a new backorder
    public int addBackorder(Backorder backorder) {
        String sql = "INSERT INTO backorder (backorderuid,salesorderuid, lotuid,accepteddeliverydate ,fulfillmentstatus,stockavailability,warehouseuid, expectedfulfillmentdate, trackbackorder, customerdate, createddate, lastmodifiedby,insertdate,updatedate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        
        return jdbcTemplate.update(sql, 
            backorder.getBackorderuid(),
            backorder.getSalesorderuid(),
            backorder.getLotuid(),
            backorder.getAccepteddeliverydate(),
            backorder.getFulfillmentstatus(),
            backorder.getStockavailability(),
            backorder.getWarehouseuid(),
            backorder.getExpectedfulfillmentdate(),
            backorder.getTrackbackorder(),
            backorder.getCustomerdate(),
            backorder.getCreateddate(),
            backorder.getLastmodifiedby(),
            formattedTimestamp, // Insert date
            formattedTimestamp
        );
    }
    

    // Update a backorder
    public int updateBackorder(Backorder backorder) {
        String sql = "UPDATE backorder SET accepteddeliverydate=?,fulfillmentstatus=?,stockavailability=?, expectedfulfillmentdate = ?, trackbackorder = ?, customerdate = ?, createddate = ?, lastmodifiedby = ?, updatedate = ? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

        
        return jdbcTemplate.update(sql,     
            backorder.getAccepteddeliverydate(),
            backorder.getFulfillmentstatus(),
            backorder.getStockavailability(),
            backorder.getExpectedfulfillmentdate(),
            backorder.getTrackbackorder(),
            backorder.getCustomerdate(),
            backorder.getCreateddate(),
            backorder.getLastmodifiedby(),
            formattedTimestamp, // Updated timestamp

            backorder.getId()
        );
    }

    // Delete a backorder by ID
    public int deleteBackorder(Long id) {
        String sql = "DELETE FROM backorder WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

	
   
	}

    

