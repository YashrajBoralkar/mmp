package com.prog.Dao.inventory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.prog.model.erp.Productinfo;

@Repository
public class ProductInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ======================= Insert =======================
    public int AddProductInfo(Productinfo pi) {
        String sql = "INSERT INTO productinfo (productuid, productname, productcategory, productsubcategory, " +
                "specifications, rawmaterialuid, rawmaterialname, hsnsaccode, unitofmeasure, leadtime, " +
                "minorderquantity, sellingprice, taxrate, reorderlevel, safetystocklevel, itemstatus, insertdate, updateddate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return jdbcTemplate.update(sql,
                pi.getProductuid(),
                pi.getProductname(),
                pi.getProductcategory(),      // ✅ categoryuid
                pi.getProductsubcategory(),   // ✅ subcategoryuid
                pi.getSpecifications(),
                pi.getRawmaterialuid(),
                pi.getRawmaterialname(),
                pi.getHsnsaccode(),
                pi.getUnitofmeasure(),
                pi.getLeadtime(),
                pi.getMinorderquantity(),
                pi.getSellingprice(),
                pi.getTaxrate(),
                pi.getReorderlevel(),
                pi.getSafetystocklevel(),
                pi.getItemstatus(),
                now, now
        );
    }

    // ======================= Update =======================
    public int updateProductInfo(Productinfo pi) {
        String sql = "UPDATE productinfo SET " +
                "productname=?, productcategory=?, productsubcategory=?, specifications=?, rawmaterialuid=?, rawmaterialname=?, " +
                "hsnsaccode=?, unitofmeasure=?, leadtime=?, minorderquantity=?, " +
                "sellingprice=?, taxrate=?, reorderlevel=?, safetystocklevel=?, itemstatus=?, updateddate=? " +
                "WHERE id=?";

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return jdbcTemplate.update(sql,
                pi.getProductname(),
                pi.getProductcategory(),     // ✅ categoryuid
                pi.getProductsubcategory(),  // ✅ subcategoryuid
                pi.getSpecifications(),
                pi.getRawmaterialuid(),
                pi.getRawmaterialname(),
                pi.getHsnsaccode(),
                pi.getUnitofmeasure(),
                pi.getLeadtime(),
                pi.getMinorderquantity(),
                pi.getSellingprice(),
                pi.getTaxrate(),
                pi.getReorderlevel(),
                pi.getSafetystocklevel(),
                pi.getItemstatus(),
                now,
                pi.getId()
        );
    }

    // ======================= Delete =======================
    public int DeleteProductinfo(Long id) {
        return jdbcTemplate.update("DELETE FROM productinfo WHERE id = ?", id);
    }

    // ======================= Fetch by ID =======================
    public Productinfo GetProductInfoByid(Long id) {
        String sql = "SELECT p.*, c.categoryname, s.subcategoryname " +
                "FROM productinfo p " +
                "LEFT JOIN productcategory c ON p.productcategory = c.categoryuid " +
                "LEFT JOIN productsubcategory s ON p.productsubcategory = s.subcategoryuid " +
                "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductInfoRowMapper(), id);
    }

    // ======================= RowMapper =======================
    private static class ProductInfoRowMapper implements RowMapper<Productinfo> {
        @Override
        public Productinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Productinfo pi = new Productinfo();
            pi.setId(rs.getLong("id"));
            pi.setProductuid(rs.getString("productuid"));
            pi.setProductname(rs.getString("productname"));
            pi.setProductcategory(rs.getString("productcategory"));       // uid stored in DB
            pi.setProductsubcategory(rs.getString("productsubcategory")); 
            pi.setSpecifications(rs.getString("specifications"));
            pi.setRawmaterialuid(rs.getString("rawmaterialuid"));
            pi.setRawmaterialname(rs.getString("rawmaterialname"));
            pi.setHsnsaccode(rs.getString("hsnsaccode"));
            pi.setUnitofmeasure(rs.getString("unitofmeasure"));
            pi.setLeadtime(rs.getString("leadtime"));
            pi.setMinorderquantity(rs.getString("minorderquantity"));
            pi.setSellingprice(rs.getString("sellingprice"));
            pi.setTaxrate(rs.getString("taxrate"));
            pi.setReorderlevel(rs.getString("reorderlevel"));
            pi.setSafetystocklevel(rs.getString("safetystocklevel"));
            pi.setItemstatus(rs.getString("itemstatus"));
            return pi;
        }
    }
    

    // ======================= Fetch All for View =======================
    public List<Map<String, Object>> getProductInfo() {
        String sql = "SELECT p.id, p.productuid, p.productname, " +
                "c.categoryname AS productcategory, " +
                "s.subcategoryname AS productsubcategory, " +
                "p.unitofmeasure, p.sellingprice, p.taxrate, " +
                "p.manufacturingdate, p.expirydate, p.warrantyperiod, p.itemstatus " +
                "FROM productinfo p " +
                "LEFT JOIN productcategory c ON p.productcategory = c.categoryname " +
                "LEFT JOIN productsubcategory s ON p.productsubcategory = s.subcategoryname";
        return jdbcTemplate.queryForList(sql);
    }
    

    // ======================= Product Category & Subcategory =======================
    public List<String> getAllProductCategoryNames() {
        String sql = "SELECT DISTINCT categoryname FROM productcategory WHERE categoryname IS NOT NULL";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getSubcategoriesByCategory(String categoryName) {
        String sql = "SELECT ps.subcategoryname " +
                "FROM productsubcategory ps " +
                "JOIN productcategory pc ON ps.categoryuid = pc.categoryuid " +
                "WHERE pc.categoryname = ? AND ps.subcategoryname IS NOT NULL";
        return jdbcTemplate.queryForList(sql, String.class, categoryName);
    }

    // ======================= Raw Material UID & Name =======================
    public List<String> getRawMaterialNamesByUIDs(List<String> rawMaterialUIDs) {
        if (rawMaterialUIDs == null || rawMaterialUIDs.isEmpty()) {
            return Collections.emptyList();
        }

        String placeholders = String.join(",", Collections.nCopies(rawMaterialUIDs.size(), "?"));
        String sql = "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid IN (" + placeholders + ")";

        return jdbcTemplate.query(sql, rawMaterialUIDs.toArray(), (rs, rowNum) -> rs.getString("rawmaterialname"));
    }

    // ======================= Used in Sales Order =======================
    public List<String> Allonlyproducts() {
        return jdbcTemplate.queryForList("SELECT productuid FROM productinfo", String.class);
    }

    public Productinfo getproductDetailsByUid(String productuid) {
        String sql = "SELECT productname, sellingprice FROM productinfo WHERE productuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productuid}, (rs, rowNum) -> {
            Productinfo pi = new Productinfo();
            pi.setProductname(rs.getString("productname"));
            pi.setSellingprice(rs.getString("sellingprice"));
            return pi;
        });
    }

    // ======================= FEFO =======================
    public List<String> showallProduct() {
        return jdbcTemplate.queryForList("SELECT batchuid FROM productinfo", String.class);
    }

    public Productinfo getproductbyUid(String batchuid) {
        String sql = "SELECT productname, productuid FROM productinfo WHERE batchuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{batchuid}, (rs, rowNum) -> {
            Productinfo pi = new Productinfo();
            pi.setProductname(rs.getString("productname"));
            pi.setProductuid(rs.getString("productuid"));
            return pi;
        });
    }

    // ======================= Goods Receipt Note =======================
    public List<String> getAllProductuids() {
        return jdbcTemplate.queryForList("SELECT productuid FROM productinfo", String.class);
    }

    public List<Map<String, Object>> getproductDetailsByProductuid(String productuid) {
        String sql = "SELECT productname, sellingprice FROM productinfo WHERE productuid = ?";
        return jdbcTemplate.queryForList(sql, productuid);
    }

    // ======================= Fallback findAll =======================
    public List<Productinfo> findAll() {
        String sql = "SELECT * FROM productinfo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Productinfo.class));
    }

    public List<String> getAllRawMaterialUIDs() {
        String sql = "SELECT rawmaterialuid FROM rawmaterialinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialuid"));
    }
}
