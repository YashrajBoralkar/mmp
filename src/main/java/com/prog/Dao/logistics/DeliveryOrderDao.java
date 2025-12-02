package com.prog.Dao.logistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Deliveryorder;

@Repository
public class DeliveryOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveDeliveryOrder(Deliveryorder deliveryOrder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String insertSql = "INSERT INTO deliveryorder (deliveryorderuid, salesorderuid, customeruid, customername, " +
                "deliveryaddress, deliverydate, productuid, quantitydispatched, shippingmethod, " +
                "dispatchedby, receivedby, deliverystatus, remarks, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        return jdbcTemplate.update(insertSql,
                deliveryOrder.getDeliveryorderuid(),
                deliveryOrder.getSalesorderuid(),
                deliveryOrder.getCustomeruid(),
                deliveryOrder.getCustomername(),
                deliveryOrder.getDeliveryaddress(),
                deliveryOrder.getDeliverydate(),
                deliveryOrder.getProductuid(),
                deliveryOrder.getQuantitydispatched(),
                deliveryOrder.getShippingmethod(),
                deliveryOrder.getDispatchedby(),
                deliveryOrder.getReceivedby(),
                deliveryOrder.getDeliverystatus(),
                deliveryOrder.getRemarks(),
                formattedTimestamp,
                formattedTimestamp
        );
    }


    public List<Map<String, Object>> fetchAllDeliveryOrders() {
        String sql = "SELECT d.id, d.deliveryorderuid, d.salesorderuid, d.customername, " +
                     "d.deliverydate, d.quantitydispatched, d.deliverystatus, po.productname  " +
                     "FROM deliveryorder d " +
                     "JOIN customerregistration cd ON cd.customeruid = d.customeruid "
                     +"JOIN productinfo po ON po.productuid = d.productuid  " +
                     "ORDER BY d.id ASC";
        return jdbcTemplate.queryForList(sql);
    }

    public void deleteDeliveryOrderById(Long id) {
        String sql = "DELETE FROM deliveryorder WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No Delivery Order found with id " + id);
        }
    }

    class DeliveryOrderRowMapper implements RowMapper<Deliveryorder> {
        @Override
        public Deliveryorder mapRow(ResultSet rs, int rowNum) throws SQLException {
            Deliveryorder deliveryOrder = new Deliveryorder();
            deliveryOrder.setId(rs.getLong("id"));
            deliveryOrder.setDeliveryorderuid(rs.getString("deliveryorderuid"));
            deliveryOrder.setSalesorderuid(rs.getString("salesorderuid"));
            deliveryOrder.setCustomeruid(rs.getString("customeruid"));
            deliveryOrder.setCustomername(rs.getString("customername"));
            deliveryOrder.setDeliverydate(rs.getString("deliverydate"));
            deliveryOrder.setDeliveryaddress(rs.getString("deliveryaddress"));
            deliveryOrder.setProductuid(rs.getString("productuid"));
            deliveryOrder.setQuantitydispatched(rs.getString("quantitydispatched"));
            deliveryOrder.setShippingmethod(rs.getString("shippingmethod"));
            deliveryOrder.setDispatchedby(rs.getString("dispatchedby"));
            deliveryOrder.setReceivedby(rs.getString("receivedby"));
            deliveryOrder.setDeliverystatus(rs.getString("deliverystatus"));
            deliveryOrder.setRemarks(rs.getString("remarks"));
            return deliveryOrder;
        }
    }

    public Deliveryorder getDeliveryOrderById(Long id) {
        String sql = "SELECT * FROM deliveryorder WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new DeliveryOrderRowMapper(), id);
    }

        public void updateDeliveryOrder(Deliveryorder deliveryOrder) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);

            String sql = "UPDATE deliveryorder SET deliverydate = ?, quantitydispatched = ?, shippingmethod = ?, dispatchedby = ?, receivedby = ?, deliverystatus = ?, remarks = ?, updatedate = ? WHERE id = ?";

            jdbcTemplate.update(sql,
                    deliveryOrder.getDeliverydate(),   // <-- add this
                    deliveryOrder.getQuantitydispatched(),
                    deliveryOrder.getShippingmethod(),
                    deliveryOrder.getDispatchedby(),
                    deliveryOrder.getReceivedby(),
                    deliveryOrder.getDeliverystatus(),
                    deliveryOrder.getRemarks(),
                    formattedTimestamp,
                    deliveryOrder.getId()
            );
        }

    public List<String> getItemId() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }

    public List<Map<String, Object>> getSalesOrderDetails(String salesorderuid) {
        String sql = "SELECT so.salesorderuid, so.customeruid, cd.companyname, " +
                     "so.deliverydate, so.deliveryaddress " +
                     "FROM salesorder so " +
                     "JOIN customerregistration cd ON so.customeruid = cd.customeruid " +
                     "WHERE so.salesorderuid = ?";
        return jdbcTemplate.queryForList(sql, salesorderuid);
    }

    public List<Map<String, Object>> getProductsBySalesOrder(String salesorderuid) {
        String sql = "SELECT productuid FROM salesorder WHERE salesorderuid = ?";
        return jdbcTemplate.queryForList(sql, salesorderuid);
    }

    public Map<String, Object> getQuantityDispatched(String salesorderuid, String productuid) {
        String sql = "SELECT deliveredquantity AS quantitydispatched FROM salesorder " +
                     "WHERE salesorderuid = ? AND productuid = ?";
        try {
            return jdbcTemplate.queryForMap(sql, salesorderuid, productuid);
        } catch (EmptyResultDataAccessException e) {
            return Map.of("quantitydispatched", 0);
        }
    }
   
    public Map<String, Object> getDeliveryDateByProduct(String salesorderuid, String productuid) {
        String sql = "SELECT deliverydate FROM salesorder WHERE salesorderuid = ? AND productuid = ?";
        try {
            return jdbcTemplate.queryForMap(sql, salesorderuid, productuid);
        } catch (EmptyResultDataAccessException e) {
            return Map.of("deliverydate", null);
        }
        
    }
    
    

}
