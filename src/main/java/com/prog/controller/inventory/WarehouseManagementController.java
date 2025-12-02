package com.prog.controller.inventory;

import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prog.model.erp.Warehouseinfo;
import com.prog.service.inventory.WarehouseInfoService;



@Controller
public class WarehouseManagementController {
	
	@Autowired
    private WarehouseInfoService warehouseService;

	

	@GetMapping("/inventory/warehousemanagement")
	    public  String viewgrid() {
	    	return "INVENTORYgrid/warehouseGrid";
	    }
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		//Warehouse Setup Controller
	 
	    @GetMapping("/showwarehouseform")
	    public String showForm(Model model) {
	        model.addAttribute("warehouse", new Warehouseinfo());
	        return "inventory/WarehouseSetupForm";
	    }

	    @PostMapping("/submitwarehouseform")
	    public String submitIdSupport(@ModelAttribute Warehouseinfo warehouse,
	    		@RequestParam("file") MultipartFile file) throws IOException {
	        	 
	    	if (!file.isEmpty()) {
	    		warehouse.setSafetycertification(file.getBytes());
	    	}
	    	warehouseService.saveWarehouse(warehouse); 
	            return "redirect:/inventory/warehousemanagement"; 
	        
	            }

	    
	 // Show Warehouse Info List
	    @GetMapping("/viewwarehouseinfolist")
	    @ResponseBody
	    public Map<String, Object> GetAllStockInfo() {        
	        List<String> headers = List.of(
	            "ID", "Warehouse UID", "Warehouse Name", "Warehouse Type", "City Name", 
	            "Address", "Associated Inventory Locations", "Contact Number", "Contact Person", 
	            "Dock Availability", "Dock Operation", "Fire Safety Equipment", "Operational Hours", 
	            "Storage Capacity", "Storage Type", "Unit", "Zones and Sections", "Status"
	        );

	        List<Map<String, Object>> warehouseinfolist = warehouseService.getAllWarehouses();

	        List<Map<String, String>> warehouseinfoList = new ArrayList<>();

	        for (Map<String, Object> warehouseinfo : warehouseinfolist) {
	            Map<String, String> row = new HashMap<>();
	            row.put("ID", String.valueOf(warehouseinfo.get("id")));
	            row.put("Warehouse UID", String.valueOf(warehouseinfo.get("warehouseuid")));
	            row.put("Warehouse Name", String.valueOf(warehouseinfo.get("warehousename")));
	            row.put("Warehouse Type", String.valueOf(warehouseinfo.get("warehousetype")));
	            row.put("City Name", String.valueOf(warehouseinfo.get("cityname")));
	            row.put("Address", String.valueOf(warehouseinfo.get("address")));
	            row.put("Associated Inventory Locations", String.valueOf(warehouseinfo.get("associatedinventorylocations")));
	            row.put("Contact Number", String.valueOf(warehouseinfo.get("contactnumber")));
	            row.put("Contact Person", String.valueOf(warehouseinfo.get("contactperson")));
	            row.put("Dock Availability", String.valueOf(warehouseinfo.get("dockavailability")));
	            row.put("Dock Operation", String.valueOf(warehouseinfo.get("dockoperation")));
	            row.put("Fire Safety Equipment", String.valueOf(warehouseinfo.get("firesafetyequipment")));
	            row.put("Operational Hours", String.valueOf(warehouseinfo.get("operationalhours")));
	            row.put("Storage Capacity", String.valueOf(warehouseinfo.get("storagecapacity")));
	            row.put("Storage Type", String.valueOf(warehouseinfo.get("storagetype")));
	            row.put("Unit", String.valueOf(warehouseinfo.get("unit")));
	            row.put("Zones and Sections", String.valueOf(warehouseinfo.get("zonesandsections")));
	            row.put("Status", String.valueOf(warehouseinfo.get("status")));

	            warehouseinfoList.add(row);
	        }

	        Map<String, Object> response = new HashMap<>();
	        response.put("headers", headers);
	        response.put("data", warehouseinfoList);
	        response.put("status", "success");

	        return response;
	    }

	    @GetMapping("/deletewarehouseinfo")
	    public String deleteWarehouse(@RequestParam("id") Long id) {
	        warehouseService.deleteWarehouse(id);
	        return "redirect:/inventory/warehousemanagement"; // Redirect to the list after deletion
	    }

	    
	    @GetMapping("/updatewarehouseinfo/{id}")
	    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    	Warehouseinfo warehouse = warehouseService.findWarehouseById(id);
	        model.addAttribute("warehouse", warehouse);
	        return "inventory/WarehouseSetupForm"; // Return the same form for updating
	    } 

	    @PostMapping("/updatewarehouseinfo")
	    public String updateWarehouse(@ModelAttribute Warehouseinfo warehouse) {
	        warehouseService.saveWarehouse(warehouse); // Assuming saveWarehouse can handle both insert and update
	        return "redirect:/inventory/warehousemanagement"; // Redirect to the list after updating
	    }

		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
    //Warehouse Setup Controller


	
	
	
	
	
	
	
}

