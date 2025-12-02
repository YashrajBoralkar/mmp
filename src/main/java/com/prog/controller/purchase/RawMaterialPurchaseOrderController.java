package com.prog.controller.purchase;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.RawMaterialSupplierRegistration;
import com.prog.model.erp.Rawmaterialpurchaseorder;
import com.prog.service.purchase.RawMaterialPurchaseOrderService;

@Controller
public class RawMaterialPurchaseOrderController {
    @Autowired
    private RawMaterialPurchaseOrderService rawMaterialPurchaseOrderService;
    
    @GetMapping("/purchase/rawmaterialpurchaseordergrid")
    public String showrmpogrid() {
    	return "PURCHASEgrid/RawMaterialPurchaseOrderGrid";
    }
    
    // Show form for new or edit
    @GetMapping("/Rawmaterialpurchaseorder/form")
    public String showForm(Model model) {     
    	// Now get the full list of suppliers (with uid and name)
        List<String> suppliers = rawMaterialPurchaseOrderService.getAllSuppliers();
        
        model.addAttribute("suppliers", suppliers);
        return "purchase/RawMaterialPurchaseOrderForm";
 }
    // Save new raw material purchase
    @PostMapping("/Rawmaterialpurchaseorder/save")
    public String saveRawmaterial(@ModelAttribute Rawmaterialpurchaseorder po) {
    	rawMaterialPurchaseOrderService.saveRawmaterialpurchase(po);
        return "redirect:/purchase/rawmaterialpurchaseordergrid";
   }
    

 // Display list 
    @GetMapping("/viewallpurchaseorderinfo")
	@ResponseBody
	public Map<String, Object> showlistofviewallpurchaseorder() {
	    List<String> headers = List.of(
	        "ID","RM Purchase Order ID","Supplier ID", "Supplier Name", "Raw Materials Details","Order Date","Total Value");
	    
    	List<Map<String, Object>> purchaseorderInfoList = rawMaterialPurchaseOrderService.getAllRawmaterialpurchase();
	    List<Map<String, String>> purchaseorderList = new ArrayList<>();
	    
	    for (Map<String, Object> purchaseorderInfo : purchaseorderInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(purchaseorderInfo.get("id")));
	        row.put("RM Purchase Order ID", String.valueOf(purchaseorderInfo.get("rawmaterialpurchaseorderuid")));
	        row.put("Supplier ID", String.valueOf(purchaseorderInfo.get("rawmaterialsupplieruid")));
	        row.put("Supplier Name", String.valueOf(purchaseorderInfo.get("suppliername")));
	        row.put("Raw Materials Details", String.valueOf(purchaseorderInfo.get("materialdetails")));
	        row.put("Order Date", String.valueOf(purchaseorderInfo.get("orderdate")));
	        row.put("Total Value", String.valueOf(purchaseorderInfo.get("totalvalue")));
	        purchaseorderList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", purchaseorderList);
	    response.put("status", "success");
	    
	    return response;
	}
   
    @GetMapping("/Rawmaterialpurchaseorder/edit/{id}")
    public String editRawMaterial(@PathVariable("id") Long id, Model model) {
    	Rawmaterialpurchaseorder po = rawMaterialPurchaseOrderService.getRawmaterialById(id);
        System.out.println(po);
        model.addAttribute("po", po);

        // Populate suppliers list again for the form
        List<String> suppliers = rawMaterialPurchaseOrderService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);

        return "purchase/RawMaterialPurchaseOrderForm";
    }
    // Update existing raw material
    @PostMapping("/Rawmaterialpurchaseorder/update")
    public String updateRawMaterial(@ModelAttribute Rawmaterialpurchaseorder po) {
    	rawMaterialPurchaseOrderService.updateRawmaterial(po);
        return "redirect:/purchase/rawmaterialpurchaseordergrid";
    }

    // Delete raw material by ID
    @GetMapping("/deleterawmaterialpurchaseorderinfo")
    public String deleteRawMaterial(@RequestParam("id") Long id) {
    	rawMaterialPurchaseOrderService.deleteRawmaterialpurchaseById(id);
        return "redirect:/purchase/rawmaterialpurchaseordergrid";
    }

    
    @GetMapping("/Rawmaterialpurchaseorder/getSupplierDetails")
    @ResponseBody
    public List<Map<String, Object>> getSupplierDetails(@RequestParam String rawmaterialsupplieruid) {
        return rawMaterialPurchaseOrderService.findSupplierNameBySupplierUid(rawmaterialsupplieruid);
    }
    
    @GetMapping("/Rawmaterialpurchaseorder/getMaterialDetails")
    @ResponseBody
    public Map<String, Object> getMaterialDetails(@RequestParam String materialname) {
        Map<String, Object> response = new HashMap<>();

        // Fetch data from service
        Map<String, Object> materialData = rawMaterialPurchaseOrderService.getMaterialDetailsByName(materialname);

        if (materialData != null && !materialData.isEmpty()) {
            response.put("price", materialData.get("standardpurchaseprice"));
            response.put("rawmaterialuid", materialData.get("rawmaterialuid"));
            response.put("quantity", materialData.get("quantity")); // if quantity is available
        } else {
            response.put("error", "Material not found");
        }

        return response;
    }

}
