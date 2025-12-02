package com.prog.controller.inventory;

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

import com.prog.model.erp.rawmaterialshortagealert;
import com.prog.service.inventory.RawmaterialShortageAlertService;

@Controller
public class RawmaterialShortageAlertController {
	private static final Object allRawMaterialUIDs = null;
	private static final Object existingRecord = null;

	@Autowired
	private RawmaterialShortageAlertService RawmaterialShortageAlertService;
	
	
	 @GetMapping("/inventory/rawmaterialshortagealertgrid")
		public String showrawmaterialshortagealertgridgrid() {
			return "INVENTORYgrid/RawMaterialShortageAlertGrid";
		}
	 
	 
	// Show Materialshortagealert form
	@GetMapping("/materialshortagealertform")
	public String showMaterialShortageAlert(Model model) {
		List<String> rawmaterialuid = RawmaterialShortageAlertService.fetchrawmaterialUIds();
		model.addAttribute("rawmaterialuid", rawmaterialuid);

		return "inventory/RawMaterialShortageAlertForm"; // Returning the name of the Thymeleaf/HTML template
	}
	
	 // Handle submission of Materialshortagealert form

	@PostMapping("/materialshortagealert/save")
	public String submitMaterialShortageAlert(@ModelAttribute rawmaterialshortagealert alert) {
		// Save the submitted rawmaterialreturntosupplier data to the database
		RawmaterialShortageAlertService.savematerialshortagealert(alert);
		return "redirect:/inventory/rawmaterialshortagealertgrid"; // Redirect to the Materialshortagealert list view after successful
													// submission
	}
	
    
        // Show Product Info List
		@GetMapping("/viewmaterialshortagealertlist")
		@ResponseBody
		public Map<String, Object> GetAllInventoryEntryInfo() {        
		List<String> headers = List.of("ID", "RM Shortage Alert ID", "Raw Material ID", "Raw Material Name", "Minimum Stock Level", "Current Stock Level", "Alert Date");
		
    	List<Map<String, Object>> shortagealertlist = RawmaterialShortageAlertService.getAllMaterialShortageAlerts();
		
		List<Map<String, String>>  shortagealertList = new ArrayList<>();
		
		for (Map<String, Object> shortagealert : shortagealertlist) {
		Map<String, String> row = new HashMap<>();
		row.put("ID", String.valueOf(shortagealert.get("id")));
		row.put("RM Shortage Alert ID", String.valueOf(shortagealert.get("rawmaterialshortagealertuid")));
		row.put("Raw Material ID", String.valueOf(shortagealert.get("rawmaterialuid")));		
		row.put("Raw Material Name", String.valueOf(shortagealert.get("rawmaterialname")));
		row.put("Minimum Stock Level", String.valueOf(shortagealert.get("minimumstocklevel")));
		row.put("Current Stock Level", String.valueOf(shortagealert.get("currentstocklevel")));
		row.put("Alert Date", String.valueOf(shortagealert.get("alertdate")));

		shortagealertList.add(row);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", shortagealertList);
		response.put("status", "success");
		
		return response;
		}
		
       
    
    // Display the form for editing an existing Materialshortagealert
    @GetMapping("/materialshortagealert/edit/{id}")
    public String editMaterialShortageAlertsList(@PathVariable Long id, Model model) {
        // Fetch the Materialshortagealert record by ID from the service
    	rawmaterialshortagealert alert = RawmaterialShortageAlertService.getMaterialShortageAlertsById(id);
       model.addAttribute("alert", alert);      
       List<String> rawmaterialuid = RawmaterialShortageAlertService.fetchrawmaterialUIds();
		model.addAttribute("rawmaterialuid", rawmaterialuid);	
        return "inventory/RawMaterialShortageAlertForm";  // Return the view name for the Materialshortagealert form

    }


 // Handle the submission of updated Materialshortagealert data
    @PostMapping("/materialshortagealert/update")
    public String updateMaterialShortageAlerts(@ModelAttribute("returnRequest") rawmaterialshortagealert alert) {
        // Call the service to update the Materialshortagealert record in the database
    	RawmaterialShortageAlertService.updateMaterialShortageAlerts(alert);
        return "redirect:/inventory/rawmaterialshortagealertgrid"; // Redirect to the listing page after successful update

    }
	
 // Delete Materialshortagealert by ID
    @GetMapping("/deleterawmaterialshortagealertinfo")
    public String deleteMaterialShortageAlerts(@RequestParam("id") Long id) {
        // Call service to delete the Materialshortagealert record with the specified ID
    	RawmaterialShortageAlertService.deleteMaterialShortageAlerts(id);
        return "redirect:/inventory/rawmaterialshortagealertgrid";  // Redirect to the list view after successful deletion

    }
     
	
	// Get rawmaterial details based on rawmaterial UID
		@GetMapping("/shortagealert/getrawmaterialdetails")
		@ResponseBody
		public List<Map<String, Object>> getrawmaterialInfo(@RequestParam("rawmaterialuid") String rawmaterialuid) {
			if (rawmaterialuid == null || rawmaterialuid.isEmpty()) {
				throw new IllegalArgumentException("campaign UID must not be null or empty.");
			}
			return RawmaterialShortageAlertService.getDataByrawmaterialUid(rawmaterialuid);
		}
		
		


}
