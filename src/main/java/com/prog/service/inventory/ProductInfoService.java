package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.ProductInfoDAO;
import com.prog.model.erp.Productinfo;

@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoDAO productinfodao;

    // Save New Product Info
    public int SaveProductInfo(Productinfo productinfo) {
        if (productinfo.getProductuid() == null || productinfo.getProductuid().isEmpty()) {
            String Productid = generateProductid();
            productinfo.setProductuid(Productid);
        }
        return productinfodao.AddProductInfo(productinfo);
    }

    // Update Product Info
    public int updateProductInfo(Productinfo productinfo) {
        return productinfodao.updateProductInfo(productinfo);
    }

    // Generate Unique Product UID
    private String generateProductid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder productid = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            productid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "PRD" + productid.toString();
    }

    // Fetch all Product Info records
    public List<Map<String, Object>> getAllProductInfo() {
        return productinfodao.getProductInfo();
    }

    // Fetch by ID
    public Productinfo FetchProductInfoByid(Long id) {
        return productinfodao.GetProductInfoByid(id);
    }

    // Delete
    public int DeleteProductInfoByid(Long id) {
        return productinfodao.DeleteProductinfo(id);
    }

    // ========== Subcategories & Categories ==========
    public List<String> getAllProductCategoryNames() {
        return productinfodao.getAllProductCategoryNames();
    }

    public List<String> getSubcategoriesByCategory(String categoryName) {
        return productinfodao.getSubcategoriesByCategory(categoryName);
    }

    // ========== Raw Material UIDs and Names ==========
    public List<String> getAllRawMaterialUIDs() {
        return productinfodao.getAllRawMaterialUIDs();
    }
    public List<String> getRawMaterialNamesByUIDs(List<String> rawMaterialUIDs) {
        return productinfodao.getRawMaterialNamesByUIDs(rawMaterialUIDs);
    }

    // ========== Other Module Integrations ==========
    // Sales Order - fetch product UIDs
    public List<String> soallproductuid() {
        return productinfodao.Allonlyproducts();
    }

    public Productinfo sogetproductdetailsbyuid(String productuid) {
        return productinfodao.getproductDetailsByUid(productuid);
    }

    // FEFO - fetch
    public List<String> fefoshowallproductdetails() {
        return productinfodao.showallProduct();
    }

    public Productinfo fefogetproductbyuidss(String batchuid) {
        return productinfodao.getproductbyUid(batchuid);
    }

    // Goods Receipt Note usage
    public List<String> getAllProductuids() {
        return productinfodao.getAllProductuids();
    }

    public List<Map<String, Object>> getproductDetailsByProductuid(String productuid) {
        return productinfodao.getproductDetailsByProductuid(productuid);
    }

    // General product fetch (used in some modules)
    public List<Productinfo> getAllGoods() {
        return productinfodao.findAll();
    }


	
}
