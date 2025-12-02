package com.prog.Dao.productionandoperation;

import com.prog.model.erp.ProductionOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ProductionOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 🔹 Insert
    public int saveProductionOrder(ProductionOrder order) {
        String sql = """
            INSERT INTO productionorder (
                productuid, productionplanninguid, workorderuid, productname,
                workorderquantity, productionorderquantity, productionorderuid,
                productionorderstartdate, productionorderenddate,
                plannedstartdate, plannedenddate, prioritylevel,
               supervisorname, productionstatus, productionremarks,
                insertdate, updatedate
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(sql,
                order.getProductuid(),
                order.getProductionplanninguid(),
                order.getWorkorderuid(),
                order.getProductname(),
                order.getWorkorderquantity(),
                order.getProductionorderquantity(),
                order.getProductionorderuid(),
                order.getProductionorderstartdate(),
                order.getProductionorderenddate(),
                order.getPlannedstartdate(),
                order.getPlannedenddate(),
                order.getPrioritylevel(),
                order.getSupervisorname(),
                order.getProductionstatus(),
                order.getProductionremarks(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    // 🔹 Get All Orders
    public List<Map<String, Object>> getAllOrders() {
        String sql = """
            SELECT po.id, po.productionorderuid, po.productionplanninguid, po.workorderuid,
                   po.productuid, po.productname, po.workorderquantity, po.productionorderquantity,
                   po.productionorderstartdate, po.productionorderenddate,
                   po.plannedstartdate, po.plannedenddate, po.prioritylevel,
                   po.productionstatus, po.productionremarks, po.insertdate, po.updatedate
            FROM productionorder po;
        """;
        return jdbcTemplate.queryForList(sql);
    }

    // 🔹 Get by ID
    public ProductionOrder getOrderById(Long id) {
        String sql = "SELECT * FROM productionorder WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductionOrderRowMapper(), id);
    }

    // 🔹 Update
    public int updateProductionOrder(ProductionOrder order) {
        String sql = """
            UPDATE productionorder 
            SET productionorderquantity=?,
                productionorderstartdate=?,
                productionorderenddate=?,
                plannedenddate=?,
                prioritylevel=?,
                productionstatus=?,
                productionremarks=?,
                updatedate=?
            WHERE id=?
        """;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(sql,
                order.getProductionorderquantity(),
                order.getProductionorderstartdate(),
                order.getProductionorderenddate(),
                order.getPlannedenddate(),
                order.getPrioritylevel(),
                order.getProductionstatus(),
                order.getProductionremarks(),
                formattedTimestamp,
                order.getId()
        );
    }

    // 🔹 Delete
    public int deleteOrder(Long id) {
        String sql = "DELETE FROM productionorder WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 🔹 Row Mapper
    class ProductionOrderRowMapper implements RowMapper<ProductionOrder> {
        @Override
        public ProductionOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductionOrder po = new ProductionOrder();
            po.setId(rs.getLong("id"));
            po.setProductuid(rs.getString("productuid"));
            po.setProductionplanninguid(rs.getString("productionplanninguid"));
            po.setWorkorderuid(rs.getString("workorderuid"));
            po.setProductname(rs.getString("productname"));
            po.setWorkorderquantity(rs.getInt("workorderquantity"));
            po.setProductionorderquantity(rs.getInt("productionorderquantity"));
            po.setProductionorderuid(rs.getString("productionorderuid"));
            po.setPlannedstartdate(rs.getString("plannedstartdate"));
            po.setPlannedenddate(rs.getString("plannedenddate"));
            po.setProductionorderstartdate(rs.getString("productionorderstartdate"));
            po.setProductionorderenddate(rs.getString("productionorderenddate"));
            po.setPrioritylevel(rs.getString("prioritylevel"));
            po.setProductionstatus(rs.getString("productionstatus"));
            po.setProductionremarks(rs.getString("productionremarks"));
            po.setSupervisorname(rs.getString("supervisorname"));
            return po;
        }
    }

 // ✅ 1. Get Production Planning UIDs by Product UID
    public List<String> getPlanningUIDsByProduct(String productuid) {
        String sql = "SELECT DISTINCT productionplanninguid FROM productionplanning WHERE productuid = ? AND productionplanningcompletionstatus = 'Pending';";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productionplanninguid"), productuid);
    }

    // ✅ 2. Get Work Orders by Planning UID
    public List<String> getWorkOrdersByPlanningUID(String productionplanninguid) {
        String sql = "SELECT workorderuid FROM workorder WHERE productionplanninguid = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("workorderuid"), productionplanninguid);
    }

    // ✅ 3. Get Planned Quantity by WorkOrder UID
    public Integer getPlannedQuantityByWorkOrder(String workorderuid) {
        String sql = "SELECT plannedquantity FROM workorder WHERE workorderuid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, workorderuid);
    }

 // ✅ Fetch Planned Start & End Date by Planning UID
    public Map<String, Object> getPlannedDatesByPlanningUID(String productionplanninguid) {
        String sql = "SELECT DISTINCT productionstartdate, productionenddate FROM productionplanning WHERE productionplanninguid = ?";
        try {
            return jdbcTemplate.queryForMap(sql, productionplanninguid);
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }


    
    // 🔹 Get Planned End Date from Planning UID
    public LocalDate getPlannedEndDate(String planningUID) {
        String sql = "SELECT plannedenddate FROM productionplanning WHERE productionplanninguid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{planningUID}, LocalDate.class);
    }


    public List<String> getProductuid() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }

    public List<Map<String, Object>> getProductdataByuid(String productuid) {
        String sql = """
            SELECT pi.productname,
                   pp.productionplanninguid,
                   pp.productionstartdate,
                   pp.productionenddate,
                   wo.plannedquantity,
                   wo.workorderuid
            FROM productinfo pi
            LEFT JOIN productionplanning pp ON pi.productuid = pp.productuid
            LEFT JOIN workorder wo ON pp.productionplanninguid = wo.productionplanninguid
            WHERE pi.productuid = ?
        """;
        return jdbcTemplate.queryForList(sql, productuid);
    }

    public List<ProductionOrder> getAllProductionOrders() {
        String sql = "SELECT * FROM productionorder";
        return jdbcTemplate.query(sql, new ProductionOrderRowMapper());
    }
}
