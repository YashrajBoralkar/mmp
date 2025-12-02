package com.prog.Dao.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Stocktransfer;

@Repository
public class StocktransferDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ---------------------- INSERT ----------------------
    private final String insertSql = "INSERT INTO stocktransfer (" +
            "source_warehouse_uid, destination_warehouse_uid, approvaldate, approvedby, carriername, destinationsection, " +
            "reasonfortransfer, remarks, requestedby, sourcesection, status, transfercost, transferdate, transfermode, transfertype, " +
            "stocktransferuid, quantitytotransfer,producttype, productuid, productname, address, toaddress, actualquantity,insertdate,updatedate) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

    public int saveStocktransfer(Stocktransfer stocktransfer) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
        return jdbcTemplate.update(insertSql,
                stocktransfer.getSourceWarehouseUid(),
                stocktransfer.getDestinationWarehouseUid(),
                stocktransfer.getApprovaldate(),
                stocktransfer.getApprovedby(),
                stocktransfer.getCarriername(),
                stocktransfer.getDestinationsection(),
                stocktransfer.getReasonfortransfer(),
                stocktransfer.getRemarks(),
                stocktransfer.getRequestedby(),
                stocktransfer.getSourcesection(),
                stocktransfer.getStatus(),
                stocktransfer.getTransfercost(),
                stocktransfer.getTransferdate(),
                stocktransfer.getTransfermode(),
                stocktransfer.getTransfertype(),
                stocktransfer.getStocktransferuid(),
                stocktransfer.getQuantitytotransfer(),
                stocktransfer.getProducttype(),
                stocktransfer.getProductuid(),
                stocktransfer.getProductname(),
                stocktransfer.getAddress(),
                stocktransfer.getToaddress(),
                stocktransfer.getActualquantity(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    public List<Map<String, Object>> fetchAllStockTransfers() {
        String sql = "SELECT st.id, st.stocktransferuid, st.productuid, st.productname, " +
                "st.quantitytotransfer, st.requestedby, st.approvedby, st.approvaldate, " +
                "st.source_warehouse_uid AS sourceWarehouseUid, " +
                "sw.warehousename AS sourceWarehouseName, " +
                "sw.cityname AS sourceCity, " +
                "sw.address AS sourceAddress, " +
                "st.destination_warehouse_uid AS destinationWarehouseUid, " +
                "dw.warehousename AS destinationWarehouseName, " +
                "dw.cityname AS destinationCity, " +
                "dw.address AS destinationAddress, " +
                "st.transferdate, st.transfertype, st.status " +
                "FROM stocktransfer st " +
                "LEFT JOIN warehouseinfo sw ON st.source_warehouse_uid = sw.warehouseuid " +
                "LEFT JOIN warehouseinfo dw ON st.destination_warehouse_uid = dw.warehouseuid";
        return jdbcTemplate.queryForList(sql);
    }


    public Stocktransfer getstocktransferbyId(Long id) {
        String sql = "SELECT * FROM stocktransfer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StocktransferRowmapper(), id);
    }

    // ---------------------- UPDATE ----------------------
    public void updateStockTransfer(Stocktransfer stocktransfer) {
        String sql = "UPDATE stocktransfer SET approvaldate = ?, approvedby = ?, carriername = ?, destinationsection = ?, " +
                "reasonfortransfer = ?, remarks = ?, requestedby = ?, sourcesection = ?, status = ?, transfercost = ?, " +
                "transferdate = ?, transfermode = ?, transfertype = ?, quantitytotransfer = ?, " +
                " producttype=?,productuid = ?, productname = ?, actualquantity = ?, updatedate=? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
        jdbcTemplate.update(sql,
                stocktransfer.getApprovaldate(),
                stocktransfer.getApprovedby(),
                stocktransfer.getCarriername(),
                stocktransfer.getDestinationsection(),
                stocktransfer.getReasonfortransfer(),
                stocktransfer.getRemarks(),
                stocktransfer.getRequestedby(),
                stocktransfer.getSourcesection(),
                stocktransfer.getStatus(),
                stocktransfer.getTransfercost(),
                stocktransfer.getTransferdate(),
                stocktransfer.getTransfermode(),
                stocktransfer.getTransfertype(),
                stocktransfer.getQuantitytotransfer(),
                stocktransfer.getProducttype(),
                stocktransfer.getProductuid(),
                stocktransfer.getProductname(),
                stocktransfer.getActualquantity(),
                formattedTimestamp,
                stocktransfer.getId()
        );
    }

    // ---------------------- DELETE ----------------------
    public void deletestocktransferbyId(Long id) {
        String sql = "DELETE FROM stocktransfer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // ---------------------- Warehouse & Product Data ----------------------
    public List<String> getwarehouseuid() {
        String sql = "SELECT warehouseuid FROM inventoryentry";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"));
    }

    public List<Map<String, Object>> getDataBywarehouseuid(String warehouseuid) {
        String sql = "SELECT address FROM inventoryentry WHERE warehouseuid = ?";
        return jdbcTemplate.queryForList(sql, warehouseuid);
    }

    // ---------------------- Product Name by Product UID ----------------------
    public String getProductNameByUid(String productuid) {
        if (productuid == null || productuid.isEmpty()) return "";
        String sql = "SELECT productname FROM inventoryentry WHERE productuid = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productuid}, String.class);
        } catch (Exception e) {
            System.out.println("Product name not found in inventoryentry for UID: " + productuid);
            return "";
        }
    }

    public List<Map<String, Object>> getWarehousesByProductUid(String productuid) {
        String sql = "SELECT DISTINCT ie.warehouseuid, wi.warehousename " +
                     "FROM inventoryentry ie " +
                     "JOIN warehouseinfo wi ON ie.warehouseuid = wi.warehouseuid " +
                     "WHERE ie.productuid = ?";

        try {
            return jdbcTemplate.query(sql, new Object[]{productuid}, (rs, rowNum) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("warehouseuid", rs.getString("warehouseuid"));
                map.put("warehousename", rs.getString("warehousename"));
                return map;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    // Get product name + warehouses
    public Map<String, Object> getProductNameAndWarehousesByUid(String productuid) {
        String sql = "SELECT productname, warehouseuid, warehousename FROM inventoryentry WHERE productuid = ?";
        try {
            List<Map<String, Object>> rows = jdbcTemplate.query(sql, new Object[]{productuid}, (rs, rowNum) -> {
                Map<String, Object> row = new HashMap<>();
                row.put("warehouseuid", rs.getString("warehouseuid"));
                row.put("address", rs.getString("warehousename"));
                return row;
            });

            if (rows.isEmpty()) return Collections.emptyMap();

            Map<String, Object> result = new HashMap<>();
            result.put("productname", rows.get(0).get("productname"));
            result.put("warehouses", rows);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    // 1️⃣ Fetch all product UIDs + names
    public List<Map<String, Object>> getAllProductUIDsFromInventory() {
        String sql = "SELECT DISTINCT productuid, productname FROM inventoryentry";
        return jdbcTemplate.queryForList(sql);
    }

    // 2️⃣ Get product details by UID
    public Map<String, Object> getProductDetailsByUid(String productuid) {
        String sql = "SELECT i.productname, i.actualquantity, i.warehouseuid AS sourceWarehouseUid, i.warehousename AS address " +
                "FROM inventoryentry i WHERE i.productuid = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productuid}, (rs, rowNum) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("productname", rs.getString("productname"));
                map.put("actualquantity", rs.getInt("actualquantity"));
                map.put("sourceWarehouseUid", rs.getString("sourceWarehouseUid"));
                map.put("address", rs.getString("address"));
                return map;
            });
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    // 3️⃣ Warehouses for product UID
    public List<Map<String, Object>> getWarehousesByProduct(String productuid) {
        String sql = "SELECT DISTINCT i.warehouseuid, i.warehousename FROM inventoryentry i WHERE i.productuid = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{productuid}, (rs, rowNum) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("warehouseuid", rs.getString("warehouseuid"));
                map.put("warehousename", rs.getString("warehousename"));
                return map;
            });
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    // 4️⃣ Stock + address by product + warehouse
    public Map<String, Object> getWarehouseStockByProduct(String productuid, String warehouseuid) {
        String sql = "SELECT i.actualquantity, i.warehousename FROM inventoryentry i " +
                "WHERE i.productuid = ? AND i.warehouseuid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productuid, warehouseuid}, (rs, rowNum) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("actualquantity", rs.getInt("actualquantity"));
                map.put("address", rs.getString("warehousename"));
                return map;
            });
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
    
    //--------------------------Warehouse from realtimeupdate -----------------------------------------------------------------
    
    // For Goods
    public Map<String, Object> fetchWarehouseStockByType(String warehouseUid, String productType, String productUid) {
        String sql = "SELECT r.realtimequantity AS actualquantity, w.warehousename AS sourcelocation " +
                     "FROM realtimeupdate r " +
                     "JOIN warehouseinfo w ON r.warehouseuid = w.warehouseuid " +
                     "WHERE r.id = ( " +
                     "  SELECT MAX(id) FROM realtimeupdate " +
                     "  WHERE warehouseuid = ? AND productuid = ? AND producttype = ? " +
                     ")";

        try {
            return jdbcTemplate.queryForMap(sql, warehouseUid, productUid, productType);
        } catch (EmptyResultDataAccessException e) {
            return Map.of("actualquantity", 0, "sourcelocation", "");
        }
    }


    // For Raw Material
    public Map<String, Object> fetchRawMaterialStock(String warehouseUid, String rawMaterialUid) {
        String sql = "SELECT r.realtimequantity AS actualquantity, w.warehousename AS sourcelocation " +
                     "FROM realtimeupdate r " +
                     "JOIN warehouseinfo w ON r.warehouseuid = w.warehouseuid " +
                     "WHERE r.id = ( " +
                     "  SELECT MAX(id) FROM realtimeupdate " +
                     "  WHERE warehouseuid = ? AND productuid = ? AND producttype = 'Raw_Material' " +
                     ")";

        try {
            return jdbcTemplate.queryForMap(sql, warehouseUid, rawMaterialUid);
        } catch (EmptyResultDataAccessException e) {
            return Map.of("actualquantity", 0, "sourcelocation", "");
        }
    }


    // 5️⃣ Warehouse address only
    public List<Map<String, Object>> getWarehouseDataByUid(String warehouseuid) {
        String sql = "SELECT warehousename AS address FROM inventoryentry WHERE warehouseuid = ?";
        return jdbcTemplate.queryForList(sql, warehouseuid);
    }
    public List<Map<String, Object>> getAllDestinationWarehouses() {
        String sql = "SELECT  warehousename AS destinationlocation " +
                     "FROM warehouseinfo " +
                     "ORDER BY warehousename DESC";
        return jdbcTemplate.queryForList(sql);
    }
    
    public List<Map<String, Object>> fetchDestinationWarehousesExcludingSource(String sourceUid) {
        String sql = "SELECT warehouseuid, warehousename AS toaddress  FROM warehouseinfo " +
                     "WHERE warehouseuid != ? ";
        return jdbcTemplate.queryForList(sql, sourceUid);
    }

    public Map<String, Object> fetchDestinationWarehouseDetails(String warehouseUid) {
        String sql = "SELECT warehousename FROM warehouseinfo WHERE warehouseuid = ?";

        try {
            return jdbcTemplate.queryForMap(sql, warehouseUid);
        } catch (EmptyResultDataAccessException e) {
            return Map.of("warehousename", "");
        }
    }

  

    // ---------------------- RowMapper ----------------------
    class StocktransferRowmapper implements RowMapper<Stocktransfer> {
        @Override
        public Stocktransfer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Stocktransfer stock = new Stocktransfer();
            stock.setId(rs.getLong("id"));
            stock.setSourceWarehouseUid(rs.getString("source_warehouse_uid"));
            stock.setDestinationWarehouseUid(rs.getString("destination_warehouse_uid"));
            stock.setApprovaldate(rs.getString("approvaldate"));
            stock.setApprovedby(rs.getString("approvedby"));
            stock.setCarriername(rs.getString("carriername"));
            stock.setDestinationsection(rs.getString("destinationsection"));
            stock.setReasonfortransfer(rs.getString("reasonfortransfer"));
            stock.setRemarks(rs.getString("remarks"));
            stock.setRequestedby(rs.getString("requestedby"));
            stock.setSourcesection(rs.getString("sourcesection"));
            stock.setStatus(rs.getString("status"));
            stock.setTransfercost(rs.getString("transfercost"));
            stock.setTransferdate(rs.getString("transferdate"));
            stock.setTransfermode(rs.getString("transfermode"));
            stock.setTransfertype(rs.getString("transfertype"));
            stock.setStocktransferuid(rs.getString("stocktransferuid"));
            stock.setQuantitytotransfer(rs.getString("quantitytotransfer"));
            stock.setProducttype(rs.getString("producttype"));;
            stock.setProductuid(rs.getString("productuid"));
            stock.setProductname(rs.getString("productname"));
            stock.setAddress(rs.getString("address"));
            stock.setToaddress(rs.getString("toaddress"));
            stock.setActualquantity(rs.getInt("actualquantity"));
            return stock;
        }
    }

    // ---------------------- Employees ----------------------
    public List<Map<String, Object>> getAllEmployees() {
        String sql = "SELECT employeeuid AS id, employeeuid AS empid, " +
                "CONCAT(first_name, ' ', last_name) AS name " +
                "FROM employee ORDER BY first_name";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getEmployeeByUid(String employeeuid) {
        String sql = "SELECT employeeuid AS id, employeeuid AS empid, " +
                "CONCAT(first_name, ' ', last_name) AS name " +
                "FROM employee WHERE employeeuid = ?";
        return jdbcTemplate.queryForMap(sql, employeeuid);
    }
    
    //------------------------------FETCH PRODUCT TYPE -----------------

    
 // Fetch UIDs dynamically based on product type
    public List<Map<String, Object>> getProductUIDsByType(String productType) {
        String sql = "SELECT DISTINCT productuid FROM inventoryentry WHERE producttype = ?";
        return jdbcTemplate.queryForList(sql, productType);
    }

    
    
 // Fetch UIDs by product type
    public List<Map<String, String>> getUIDsByProductType(String productType) {
        String sql = "SELECT productuid, productname FROM inventoryentry WHERE producttype = ? GROUP BY productuid, productname";
        return jdbcTemplate.query(sql, new Object[]{productType}, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("productuid", rs.getString("productuid"));
            map.put("productname", rs.getString("productname"));
            return map;
        });
    }
}
