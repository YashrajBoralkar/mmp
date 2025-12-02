package com.prog.controller.inventory;

import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.Employee;
import com.prog.model.erp.InventoryEntry;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.inventory.InventoryEntryService;


@Controller
public class InventoryEntryController {

    @Autowired
    private InventoryEntryService service;
    
    @Autowired
    private EmployeeService eService;
    
    @GetMapping("/inventory/inventoryentrygrid")
	public String showinventoryentrygrid() {
		return "INVENTORYgrid/InventoryEntryGrid";
	}
	

    /* ---------------- LIST ---------------- */
    @GetMapping("/inventoryentry/list")
    public String viewList(Model model) {
        model.addAttribute("list", service.getAllEntriesForList());
        model.addAttribute("warehouseList", service.getAllWarehouses());
        model.addAttribute("empuid", service.getAllEmployeeUid());
        return "inventory entry/inventory_list.html";
    }

    /* ---------------- FORM ---------------- */
    @GetMapping("/inventoryentry/form")
    public String showForm(Model model) {
        model.addAttribute("inventoryentry", new InventoryEntry());
        model.addAttribute("entry", new InventoryEntry());
        model.addAttribute("warehouseList", service.getAllWarehouses());
        model.addAttribute("empuid", service.getAllEmployeeUid());
        
        // Employee map
        List<Employee> employeeList = eService.getBasicEmployeeDetails();
        Map<String, String> empMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName() : "";
            empMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
        }
        model.addAttribute("empMap", empMap);

        // ✅ Fetch used Reference UIDs per type
        List<String> usedGoodsUids = service.getUsedReferenceUids("GOODS");
        List<String> usedRawUids = service.getUsedReferenceUids("RAW MATERIAL");
        Map<String, List<String>> usedUidsMap = new HashMap<>();
        usedUidsMap.put("GOODS", usedGoodsUids);
        usedUidsMap.put("RAW MATERIAL", usedRawUids);
        model.addAttribute("usedUidsMap", usedUidsMap);

        return "inventory/InventoryEntryForm";
    }

    @PostMapping("/inventoryentry/save")
    public String save(@ModelAttribute("entry") InventoryEntry entry) {
        service.saveEntry(entry);
        return "redirect:/inventory/inventoryentrygrid";
    }
    
 // Show Product Info List
 			@GetMapping("/viewinventoryentrylist")
 			@ResponseBody
 			public Map<String, Object> GetAllInventoryEntryInfo() {        
 			List<String> headers = List.of("ID", "Product Type", "Product Name", "Reference ID", "Approved Quantity", "Actual Quantity", "Warehouse Name","Entry Date");
 			
 			List <Map<String,Object>> inventoryentrylist = service.getAllEntriesForList();
 			
 			List<Map<String, String>>  inventoryentryList = new ArrayList<>();
 			
 			for (Map<String, Object> inventoryentry : inventoryentrylist) {
 			Map<String, String> row = new HashMap<>();
 			row.put("ID", String.valueOf(inventoryentry.get("id")));
 			row.put("Product Type", String.valueOf(inventoryentry.get("producttype")));
 			row.put("Product Name", String.valueOf(inventoryentry.get("productname")));
 			row.put("Reference ID", String.valueOf(inventoryentry.get("referenceuid")));
// 			row.put("Approved Quantity", String.valueOf(inventoryentry.get("approvedquantity")));
 			row.put("Actual Quantity", String.valueOf(inventoryentry.get("actualquantity")));
 			row.put("Warehouse Name", String.valueOf(inventoryentry.get("warehousename")));
 			row.put("Entry Date", String.valueOf(inventoryentry.get("entrydate")));

 			inventoryentryList.add(row);
 			}
 			
 			Map<String, Object> response = new HashMap<>();
 			response.put("headers", headers);
 			response.put("data", inventoryentryList);
 			response.put("status", "success");
 			
 			return response;
 			}
 			
 			
    @GetMapping("/inventoryentry/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("entry", service.getEntryById(id));
        model.addAttribute("warehouseList", service.getAllWarehouses());
        model.addAttribute("empuid", service.getAllEmployeeUid());

        List<Employee> employeeList = eService.getBasicEmployeeDetails();
        Map<String, String> empMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String firstName = emp.getFirstName() != null ? emp.getFirstName() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName() : "";
            empMap.put(emp.getEmployeeUID(), (firstName + " " + lastName).trim());
        }
        model.addAttribute("empMap", empMap);

        // ✅ Also pass used UIDs so edit mode can disable them if needed
        List<String> usedGoodsUids = service.getUsedReferenceUids("GOODS");
        List<String> usedRawUids = service.getUsedReferenceUids("RAW MATERIAL");
        Map<String, List<String>> usedUidsMap = new HashMap<>();
        usedUidsMap.put("GOODS", usedGoodsUids);
        usedUidsMap.put("RAW MATERIAL", usedRawUids);
        model.addAttribute("usedUidsMap", usedUidsMap);

        return "inventory/InventoryEntryForm";
    }

    @PostMapping("/inventoryentry/updateEntry/{id}")
    @ResponseBody
    public Map<String, Object> updateEntry(@PathVariable Long id, @ModelAttribute InventoryEntry updated) {
        updated.setId(id);
        service.updateEntry(updated);
        return Map.of("status", "Entry updated successfully");
    }

    @GetMapping("/deleteinventoryentryinfo")
    public String delete(@RequestParam("id") Long id) {
        service.deleteEntry(id);
        return "redirect:/inventory/inventoryentrygrid";
    }

    /* ---------------- AJAX ENDPOINTS ---------------- */

    @GetMapping("/inventoryentry/fetchProductUids")
    @ResponseBody
    public List<Map<String, Object>> fetchProductUids(@RequestParam String type) {
        return service.getApprovedProductUids(type);
    }

    @GetMapping("/inventoryentry/fetchFinishGoodsQcUid")
    @ResponseBody
    public List<Map<String, Object>> fetchFinishGoodsQcUids(@RequestParam String productuid) {
        return service.getFinishGoodsQcUidsByProduct(productuid);
    }

//    @GetMapping("/inventoryentry/fetchRawMaterialReceiptNoteUid")
//    @ResponseBody
//    public List<Map<String, Object>> fetchRawMaterialReceiptNoteUids(@RequestParam String rawmaterialuid) {
//        return service.getRawMaterialReceiptNoteUidsByRawMaterial(rawmaterialuid);
//    }

    @GetMapping("/inventoryentry/productDetails")
    @ResponseBody
    public Map<String, Object> getProductDetails(
            @RequestParam String type,
            @RequestParam String productUid,
            @RequestParam String referenceUid) {
        
        return service.getProductDetails(type, productUid, referenceUid);
    }

    @GetMapping("/inventoryentry/warehouse/details")
    @ResponseBody
    public Map<String, String> getWarehouseDetails(@RequestParam("warehouseuid") String warehouseuid) {
        String name = service.getWarehouseName(warehouseuid);
        return Map.of("warehousename", name);
    }
    
    // Separate fetching
    
    @GetMapping("/inventoryentry/productName")
    @ResponseBody
    public Map<String, Object> fetchProductName(
            @RequestParam String type,
            @RequestParam String productUid) {
        String productName = service.getProductName(type, productUid);
        if (productName == null) return Collections.emptyMap();
        return Map.of("productname", productName);
    }

    @GetMapping("/inventoryentry/approvedQuantity")
    @ResponseBody
    public Map<String, Object> fetchApprovedQuantity(
            @RequestParam String type,
            @RequestParam String productUid,
            @RequestParam String referenceUid) {

        Integer approvedQty = service.getApprovedQuantity(type, productUid, referenceUid);
        if (approvedQty == null) return Collections.emptyMap();
        return Map.of("approvedquantity", approvedQty);
    }
    
    @GetMapping("/inventoryentry/usedReferenceUids")
    @ResponseBody
    public Map<String, List<String>> getUsedReferenceUids() {
        Map<String, List<String>> usedMap = new HashMap<>();
        usedMap.put("GOODS", service.getUsedReferenceUids("GOODS"));
        usedMap.put("RAW MATERIAL", service.getUsedReferenceUids("RAW MATERIAL"));
        return usedMap;
    }


}
