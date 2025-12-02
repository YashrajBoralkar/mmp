package com.prog.controller.inventory;

import com.prog.model.erp.Stocktransfer;
import com.prog.service.inventory.StocktransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class StockTransferController {

    @Autowired
    private StocktransferService stocktransferService;

    // -------------------- GRID --------------------
    @GetMapping("/inventory/stocktransfergrid")
    public String showStocktransferGrid(Model model) {
        List<Map<String, Object>> stocktransferList = stocktransferService.getAllStockTransfers();
        model.addAttribute("stocktransferList", stocktransferList);
        return "INVENTORYgrid/StockTransferGrid";
    }

    // -------------------- FORM (CREATE) --------------------
    @GetMapping("/showstocklevelform")
    public String showStocktransferForm(Model model) {
        model.addAttribute("stocktransfer", new Stocktransfer());

        // ✅ Warehouses
        model.addAttribute("warehouseuid", stocktransferService.getWarehouseUids());

        // ✅ Product UIDs
        model.addAttribute("uidDropdownList", stocktransferService.getAllProductUIDsFromInventory());

        // ✅ Employees
        model.addAttribute("employeeList", stocktransferService.getAllEmployees());

        return "inventory/StockTransferForm";
    }

    // -------------------- SAVE --------------------
    @PostMapping("/savestocktransfer")
    public String saveStocktransfer(@ModelAttribute Stocktransfer stocktransfer,
                                    RedirectAttributes redirectAttributes) {
        stocktransferService.saveStocktransfer(stocktransfer);
        redirectAttributes.addFlashAttribute("successMessage", "Stock Dispatch Details saved successfully!");
        return "redirect:/inventory/stocktransfergrid";
    }

    //LIST
    @GetMapping("/stocktransferlist")
    @ResponseBody
    public Map<String, Object> getAllStocktransferInfo() {
        List<Map<String, Object>> rawList = stocktransferService.getAllStockTransfers();
        List<Map<String, Object>> formattedList = new ArrayList<>();

        for (Map<String, Object> entry : rawList) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", entry.get("id"));
            row.put("stocktransferuid", entry.get("stocktransferuid"));
            row.put("transfertype", entry.get("transfertype"));
            row.put("productuid", entry.get("productuid"));
            row.put("productname", entry.get("productname"));
            row.put("quantitytotransfer", entry.get("quantitytotransfer"));

            // Use UID if present, otherwise fallback to warehouse name
            row.put("sourceWarehouseUid", entry.get("sourceWarehouseUid") != null 
                ? entry.get("sourceWarehouseUid") 
                : (entry.get("sourceWarehouseName") != null ? entry.get("sourceWarehouseName") : ""));
            row.put("destinationWarehouseUid", entry.get("destinationWarehouseUid") != null 
                ? entry.get("destinationWarehouseUid") 
                : (entry.get("destinationWarehouseName") != null ? entry.get("destinationWarehouseName") : ""));

            formattedList.add(row);
        }

        return Map.of("data", formattedList, "status", "success");
    }

    // -------------------- FORM (UPDATE) --------------------
    @GetMapping("/updatestocktransfer/{id}")
    public String editStocktransfer(@PathVariable Long id, Model model) {
        Stocktransfer stocktransfer = stocktransferService.getStocktransferById(id);
        model.addAttribute("stocktransfer", stocktransfer);

        // ✅ Re-populate all dropdowns
        model.addAttribute("warehouseuid", stocktransferService.getWarehouseUids());
        model.addAttribute("uidDropdownList", stocktransferService.getAllProductUIDsFromInventory());
        model.addAttribute("employeeList", stocktransferService.getAllEmployees());

        // ✅ Preserve selected UID
        model.addAttribute("selectedProductUid", stocktransfer.getProductuid());

        return "inventory/StockTransferForm";
    }

    // -------------------- UPDATE --------------------
    @PostMapping("/updatestocktransfer")
    public String updateStocktransfer(@ModelAttribute Stocktransfer stocktransfer,
                                      RedirectAttributes redirectAttributes) {
        stocktransferService.updateStocktransfer(stocktransfer);
        redirectAttributes.addFlashAttribute("successMessage", "Stock Dispatch Details updated successfully!");
        return "redirect:/inventory/stocktransfergrid";
    }
    
    
 // -------------------- DELETE --------------------
    @GetMapping("/deletestocktransferinfo")
    public String deleteStocktransfer(@RequestParam("id") Long id,
                                      RedirectAttributes redirectAttributes) {
        try {
            stocktransferService.deleteStocktransfer(id);
            redirectAttributes.addFlashAttribute("successMessage", "Stock Transfer deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Stock Transfer.");
        }
        return "redirect:/inventory/stocktransfergrid";
    }

  //------------------------------FETCH PRODUCT TYPE -----------------
    
    
    // ✅ Fetch Product UIDs based on Product Type
       @GetMapping("/getProductUIDsByType")
       @ResponseBody
       public List<Map<String, Object>> getProductUIDsByType(@RequestParam String producttype) {
           if (producttype == null || producttype.isEmpty()) {
               return List.of();
           }

           // Normalize casing/spelling
           String normalizedType = producttype.trim().toUpperCase();
           if (normalizedType.equals("RAW_MATERIAL")) {
               normalizedType = "RAW MATERIAL"; // match DB value
           }

           return stocktransferService.getProductUIDsByType(
                   normalizedType.substring(0, 1).toUpperCase() + normalizedType.substring(1).toLowerCase()
           );
       }
       
       
    // Fetch UIDs by type
       @GetMapping("/getProductDetailsByType")
       @ResponseBody
       public List<Map<String, String>> getProductDetailsUIDsByType(@RequestParam String producttype) {
           return stocktransferService.getUIDsByProductType(producttype);
       }

       
    // -------------------- AJAX ENDPOINTS --------------------
    // ✅ Warehouse details by Warehouse UID
    @GetMapping("/getwarehousedatabyuid")
    @ResponseBody
    public List<Map<String, Object>> getWarehouseDataByUid(@RequestParam String warehouseuid) {
        return stocktransferService.getWarehouseDataByUid(warehouseuid);
    }
    @GetMapping("/getAllDestinationWarehouses")
    @ResponseBody
    public List<Map<String, Object>> getAllDestinationWarehouses() {
        return stocktransferService.getAllDestinationWarehouses();
    }

    @GetMapping("/getDestinationWarehousesExcludingSource")
    @ResponseBody
    public List<Map<String, Object>> getDestinationWarehousesExcludingSource(
            @RequestParam String sourceuid) {
        return stocktransferService.getDestinationWarehousesExcludingSource(sourceuid);
    }


    // ✅ Product Name by Product UID
    @GetMapping("/getProductNameByUid")
    @ResponseBody
    public Map<String, Object> getProductNameByUid(@RequestParam String uid) {
        String name = stocktransferService.getProductNameByUid(uid);
        return Map.of("productname", name != null ? name : "");
    }

    // ✅ Full Product Details (Name, Stock, Warehouse) by UID
    @GetMapping("/getProductDetailsByUid")
    @ResponseBody
    public Map<String, Object> getProductDetailsByUid(@RequestParam String uid) {
        Map<String, Object> details = stocktransferService.getProductDetailsByUid(uid);
        return details != null ? details : Map.of();
    }

    // ✅ Warehouses for a given product
    @GetMapping("/getWarehousesByProduct")
    @ResponseBody
    public List<Map<String, Object>> getWarehousesByProduct(@RequestParam String productuid) {
        return stocktransferService.getWarehousesByProductUid(productuid);
    }

    // ✅ Stock + address for product + warehouse
    @GetMapping("/getWarehouseStockByProduct")
    @ResponseBody
    public Map<String, Object> getWarehouseStockByProduct(@RequestParam String productuid,
                                                          @RequestParam String warehouseuid) {
        return stocktransferService.getWarehouseStockByProduct(productuid, warehouseuid);
    }

    // ✅ Employee by UID
    @GetMapping("/inventory/getEmployeeByUid/{uid}")
    @ResponseBody
    public Map<String, Object> getEmployeeByUid(@PathVariable("uid") String uid) {
        return stocktransferService.getEmployeeByUid(uid);
    }
    //---------------------------------------------------Warehouse info from realtimeupdate-----------------------------------------------------------
    @GetMapping("/getSourceWarehouseDetails")
    public ResponseEntity<Map<String, Object>> getSourceWarehouseDetails(
            @RequestParam String warehouseuid,
            @RequestParam String producttype,
            @RequestParam(required = false) String productuid) {

        Map<String, Object> details = stocktransferService.getStockDetails(warehouseuid, producttype, productuid);
        return ResponseEntity.ok(details);
    }
    @GetMapping("/getDestinationWarehouseDetails")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDestinationWarehouseDetails(
            @RequestParam String warehouseuid) {

        Map<String, Object> details = stocktransferService.getDestinationWarehouseDetails(warehouseuid);
        return ResponseEntity.ok(details);
    }

}
