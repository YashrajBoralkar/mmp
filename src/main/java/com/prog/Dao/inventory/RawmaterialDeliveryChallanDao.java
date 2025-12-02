package com.prog.Dao.inventory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Rawmaterialdeliverychallan;
import com.prog.model.erp.Rawmaterialpurchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RawmaterialDeliveryChallanDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Rawmaterialdeliverychallan> rowMapper = new RowMapper<>() {
        @Override
        public Rawmaterialdeliverychallan mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Rawmaterialdeliverychallan challan = new Rawmaterialdeliverychallan();
            challan.setId(rs.getLong("id"));
            challan.setRawmaterialdeliverychallanuid(rs.getString("rawmaterialdeliverychallanuid"));
            challan.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            challan.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            challan.setSuppliername(rs.getString("suppliername"));
            challan.setRawmaterialname(rs.getString("rawmaterialname"));
            challan.setRawmaterialuid(rs.getString("rawmaterialuid"));
            challan.setDeliverydate(rs.getString("deliverydate"));
            challan.setQuantitydelivered(rs.getInt("quantitydelivered"));
            challan.setVehiclenumber(rs.getString("vehiclenumber"));
            challan.setReceivedby(rs.getString("receivedby"));
            return challan;
        }
    };

    public int saveDeliveryChallan(Rawmaterialdeliverychallan challan) {
        String sql = "INSERT INTO rawmaterialdeliverychallan " +
                "(rawmaterialdeliverychallanuid, rawmaterialpurchaseorderuid, "
                + "rawmaterialsupplieruid, suppliername, rawmaterialname, rawmaterialuid, deliverydate, " +
                "quantitydelivered, vehiclenumber, receivedby, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        return jdbcTemplate.update(sql,
                challan.getRawmaterialdeliverychallanuid(),
                challan.getRawmaterialpurchaseorderuid(),
                challan.getRawmaterialsupplieruid(),
                challan.getSuppliername(),
                challan.getRawmaterialname(),
                challan.getRawmaterialuid(),
                challan.getDeliverydate(),
                challan.getQuantitydelivered(),
                challan.getVehiclenumber(),
                challan.getReceivedby(),
                formattedTimestamp,
                formattedTimestamp
                );
    }

    public int updateDeliveryChallan(Rawmaterialdeliverychallan challan) {
        String sql = "UPDATE rawmaterialdeliverychallan SET " +
                "rawmaterialpurchaseorderuid = ?, suppliername = ?, " +
                "deliverydate = ?, quantitydelivered = ?, vehiclenumber = ?,  receivedby = ?, updatedate = ? " +
                "WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        return jdbcTemplate.update(sql,
                challan.getRawmaterialpurchaseorderuid(),
                challan.getSuppliername(),
                challan.getDeliverydate(),
                challan.getQuantitydelivered(),
                challan.getVehiclenumber(),
                //challan.getRequestedemployeeUID(),
                challan.getReceivedby(),
                formattedTimestamp,
                challan.getId()
        );
    }

    public int deleteDeliveryChallan(Long id) {
        String sql = "DELETE FROM rawmaterialdeliverychallan WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Rawmaterialdeliverychallan getChallanById(Long id) {
        String sql = "SELECT * FROM rawmaterialdeliverychallan WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Map<String, Object>> getAllChallans() {
        String sql = "SELECT * FROM rawmaterialdeliverychallan ORDER BY id";
        return jdbcTemplate.queryForList(sql);
    }

    public Rawmaterialpurchaseorder getPurchaseOrderDetailsByUid(String uid) {
        String sql = "SELECT * FROM rawmaterialpurchaseorder WHERE rawmaterialpurchaseorderuid = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        	Rawmaterialpurchaseorder material = new Rawmaterialpurchaseorder();
            material.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            material.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            material.setSuppliername(rs.getString("suppliername"));
            material.setMaterialnames(rs.getString("materialnames"));
            material.setRawmaterialuid(rs.getString("rawmaterialuid"));
            return material;
        }, uid);
    }

    public List<String> getAllPurchaseOrderUIDs() {
        String sql = "SELECT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder ORDER BY id";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public Map<String, String> getEmployeeNameByUid(String uid) {
        String sql = "SELECT employeename FROM employee WHERE employeeUID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{uid}, (rs, rowNum) -> Map.of(
                "employeename", rs.getString("employeename")
        ));
    }

//    public List<Map<String, String>> getAllEmployees() {
//        String sql = "SELECT employeeUID, employeename FROM employee";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Map<String, String> map = new HashMap<>();
//            map.put("employeeUID", rs.getString("employeeUID"));
//            map.put("employeename", rs.getString("employeename"));
//            return map;
//        });
//    }
//    
    
	public List<Map<String, String>> getAllEmployees() {
	    String sql = "SELECT employeeuid, CONCAT(first_name, ' ', last_name) AS name FROM employee";
	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        Map<String, String> map = new HashMap<>();
	        map.put("code", rs.getString("employeeuid"));
	        map.put("name", rs.getString("name"));
	        return map;
	    });
	}

}
