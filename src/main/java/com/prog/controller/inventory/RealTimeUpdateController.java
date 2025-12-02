package com.prog.controller.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.RealTimeUpdate;
import com.prog.service.inventory.RealTimeUpdateService;

@Controller
public class RealTimeUpdateController {

    @Autowired
    private RealTimeUpdateService realtimeupdateservice;

    @GetMapping("/inventory/realtimeupdategrid")
	public String showrealtimeupdategrid() {
		return "INVENTORYgrid/RealTimeUpdateGrid";
	}
    
    @GetMapping("/realtimeupdateform")
    public String showRealtimeUpdateForm(Model model) {
        RealTimeUpdate realtimeupdate = new RealTimeUpdate();
        realtimeupdate.setRealtimequantity(0.0);
        model.addAttribute("realtimeupdate", realtimeupdate);
        return "inventory/RealTimeUpdateForm";
    }

    @PostMapping("/realtimeupdate/save")
    public ResponseEntity<String> saveRealTimeUpdates(@RequestBody List<RealTimeUpdate> updates) {
        for (RealTimeUpdate update : updates) {
            realtimeupdateservice.addRealTimeUpdate(update);
        }
        return ResponseEntity.ok("Saved");
    }
 // Display list 
    @GetMapping("/viewallrealtimeupdateinfo")
	@ResponseBody
	public Map<String, Object> showlistofviewallrealtimeupdateinfo() {
	    List<String> headers = List.of(
	        "ID","RTU ID","Product Type","Product ID", "Product Name", "Transaction Type","Transaction Quantity","Warehouse ID","Transaction Date");
	    
    	List<Map<String, Object>> realtimeupdateInfoList = realtimeupdateservice.getAllRealTimeUpdates();
	    List<Map<String, String>> realtimeupdateList = new ArrayList<>();
	    
	    for (Map<String, Object> realtimeupdateInfo : realtimeupdateInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(realtimeupdateInfo.get("id")));
	        row.put("RTU ID", String.valueOf(realtimeupdateInfo.get("realtimeupdateuid")));
	        row.put("Product Type", String.valueOf(realtimeupdateInfo.get("producttype")));
	        row.put("Product ID", String.valueOf(realtimeupdateInfo.get("productuid")));
	        row.put("Product Name", String.valueOf(realtimeupdateInfo.get("productname")));
	        row.put("Transaction Type", String.valueOf(realtimeupdateInfo.get("transactiontype")));
	        row.put("Transaction Quantity", String.valueOf(realtimeupdateInfo.get("quantity")));
	        row.put("Warehouse ID", String.valueOf(realtimeupdateInfo.get("warehouseuid")));
	        row.put("Transaction Date", String.valueOf(realtimeupdateInfo.get("transactiondate")));	        
	        realtimeupdateList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", realtimeupdateList);
	    response.put("status", "success");
	    
	    return response;
	}

    @GetMapping("/warehouses-by-producttype")
    @ResponseBody
    public List<String> getWarehouseUIDsByProductTypeAndUID(
            @RequestParam String producttype,
            @RequestParam String productuid) {
        return realtimeupdateservice.getWarehouseUIDsFromInventory(producttype, productuid);
    }

    @GetMapping("/productuids-by-type")
    @ResponseBody
    public List<String> getProductUIDsByProductType(@RequestParam String producttype) {
        return realtimeupdateservice.getProductUIDsByProductType(producttype);
    }

    @GetMapping("/latest-quantity-by-warehouse")
    @ResponseBody
    public Double getLatestQuantityByWarehouse(@RequestParam String productuid,
                                               @RequestParam String warehouseuid) {
        return realtimeupdateservice.getLatestStockByProductAndWarehouse(productuid, warehouseuid);
    }

    // ✅ Matches HTML fetch in productUID change event
    @GetMapping("/latest-globalrealtimequantity")
    @ResponseBody
    public Double getLatestGlobalStockByProductUid(
            @RequestParam String productuid,
            @RequestParam String producttype) {
        return realtimeupdateservice.getLatestGlobalStockByProductUid(productuid, producttype);
    }

   

    @GetMapping("/deleterealtimeupdateinfo")
    public String deleteRealtimeUpdate(@RequestParam("id") Long id) {
        realtimeupdateservice.deleteRealTimeUpdate(id);
        return "redirect:/inventory/realtimeupdategrid";
    }
}
