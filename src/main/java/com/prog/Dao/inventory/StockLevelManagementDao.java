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

import com.prog.model.erp.StockLevelManagement;

@Repository
public class StockLevelManagementDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String sql = """
        INSERT INTO stock_level_management(batchuid, currentstockquantity,  shelfnumber, stockleveluid,stockuid, warehouseuid, insertdate, updatedate, minstockquantity, maxstockquantity, defectivestockquantity, reorederstock) 
        VALUES(?,?,?,?,?,?,?,?,?,?,?,?)
        """;

    public int saveStockelevelmanagement(StockLevelManagement slm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(
            sql,
            slm.getBatchuid(),
            slm.getCurrentstockquantity(),
            slm.getShelfnumber(),
            slm.getStockleveluid(),
            slm.getStockuid(),
            slm.getWarehouseuid(),
            formattedTimestamp, // Insert date
            formattedTimestamp, // Update date
            slm.getMinstockquantity(),
            slm.getMaxstockquantity(),
            slm.getDefectivestockquantity(),
            slm.getReorederstock()
        );
    }

    public List<StockLevelManagement> getStockByWarehouseUid(String warehouseuid) {
        String sql = "SELECT * FROM stock_level_management WHERE warehouseuid = ?";
        return jdbcTemplate.query(sql, new StocklevelRowMapper(), warehouseuid);
    }

    public StockLevelManagement getstocklevelById(Long id) {
        String sql = "SELECT * FROM stock_level_management WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StocklevelRowMapper(), id);
    }

    public List<Map<String, Object>> getStockLevelWithWarehouse() {
        String sql = """
            select slm.id, slm.stockuid, slm.minstockquantity, slm.maxstockquantity , slm.currentstockquantity,slm.reorederstock,
        		wh.address , wh.cityname, wh.warehouseuid, si.stockname, si.stockuid
        		from stock_level_management slm
        		join warehouseinfo wh on wh.warehouseuid = slm.warehouseuid
        		join stock_info si ON slm.stockuid=si.stockuid ;

        	  """;

        // Use queryForList to get a list of maps, where each map represents a row
        return jdbcTemplate.queryForList(sql);
    }

    public void deletestockbyId(Long id) {
        String sql = "DELETE FROM stock_level_management WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No stock level found with id " + id);
        }
    }

    public int updateStocklevel(StockLevelManagement stocklevel) {
        String sql = """
            UPDATE stock_level_management 
            SET currentstockquantity = ?, maxstockquantity = ?, 
                minstockquantity = ?, shelfnumber = ?, 
                defectivestockquantity = ?, reorederstock = ?, 
                updatedate = ? 
            WHERE id = ?
            """;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(sql,
            stocklevel.getCurrentstockquantity(),
            stocklevel.getMaxstockquantity(),
            stocklevel.getMinstockquantity(),
            stocklevel.getShelfnumber(),
            stocklevel.getDefectivestockquantity(),
            stocklevel.getReorederstock(),
            formattedTimestamp, // Updated timestamp
            stocklevel.getId()
        );
    }


    private static class StocklevelRowMapper implements RowMapper<StockLevelManagement> {
        @Override
        public StockLevelManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
            StockLevelManagement level = new StockLevelManagement();
            level.setId(rs.getLong("id"));
            level.setBatchuid(rs.getString("batchuid"));
            level.setStockuid(rs.getString("stockuid"));
            level.setCurrentstockquantity(rs.getString("currentstockquantity"));
            level.setMaxstockquantity(rs.getString("maxstockquantity"));
            level.setMinstockquantity(rs.getString("minstockquantity"));
            level.setShelfnumber(rs.getString("shelfnumber"));
            level.setStockleveluid(rs.getString("stockleveluid"));
            level.setWarehouseuid(rs.getString("warehouseuid"));
            level.setDefectivestockquantity(rs.getString("defectivestockquantity"));
            level.setReorederstock(rs.getString("reorederstock"));
            return level;
        }
    }
}
