package com.prog.controller.maintenancemanagement;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.SparePartsInventoryForm;
import com.prog.service.maintenancemanagement.SparePartsInventoryService;


@Controller
public class SparePartsInventoryController {

	@Autowired
	private SparePartsInventoryService  sparePartsInventoryService;

	@GetMapping("/maintenancemanagementdepartment/sparepartsinventorygrid")
	public String showSparePartsInventoryGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/SparePartsInventoryGrid";
	}
	
	@GetMapping("/sparepartsinventoryform")
	public String showSparePartsInventoryForm() {
		return "MaintenanceManagementDepartment/SparePartsInventoryForm";
	}
	
	@PostMapping("/savesparepartsinventory")
	public String addSparePartsInventoryData(@ModelAttribute SparePartsInventoryForm spid) {
		sparePartsInventoryService.addsparePartsInventoryData(spid);
		return "redirect:/maintenancemanagementdepartment/sparepartsinventorygrid";
	}
	
	 @GetMapping("/viewsparepartsinventory")
	    @ResponseBody
	    public Map<String,Object> getSparePartsInventoryList(Model modedl){
	    	List<String> headers = List.of("ID","Spare Parts Inventory UID", "Spare Parts Name", "Spare Parts Description", "Category",
					"unit of Measure", "Current Stock Level", "Minimum Stock level", "Location", "Supplier Name", "Last Used Date");
			
			List<Map<String, Object>> sparepartsinventoryinfolist = sparePartsInventoryService.getAllSparePartsInventoryData();

			List<Map<String, String>> sparepartsinventorylist = new ArrayList<>();
			
			for (Map<String, Object> sparepartsinventoryinfo : sparepartsinventoryinfolist) {
				Map<String, String> row = new HashMap<>();
				row.put("ID",String.valueOf(sparepartsinventoryinfo.get("id")));
				row.put("Spare Parts Inventory UID", String.valueOf(sparepartsinventoryinfo.get("sparepartsinventoryuid")));
				row.put("Spare Parts Name",String.valueOf(sparepartsinventoryinfo.get("sparepartsname")));
				row.put("Spare Parts Description", String.valueOf(sparepartsinventoryinfo.get("sparepartsdescription")));
				row.put("Category", String.valueOf(sparepartsinventoryinfo.get("category")));
				row.put("unit of Measure", String.valueOf(sparepartsinventoryinfo.get("unitofmeasure")));
				row.put("Current Stock Level", String.valueOf(sparepartsinventoryinfo.get("currentstocklevel")));
				row.put("Minimum Stock level", String.valueOf(sparepartsinventoryinfo.get("minimimstocklevel")));
				row.put("Location", String.valueOf(sparepartsinventoryinfo.get("location")));
				row.put("Supplier Name", String.valueOf(sparepartsinventoryinfo.get("suppliername")));
				row.put("Last Used Date", String.valueOf(sparepartsinventoryinfo.get("lastuseddate")));
				sparepartsinventorylist.add(row);

			}

			Map<String, Object> response = new HashMap<>();
			response.put("headers", headers);
			response.put("data", sparepartsinventorylist);
			response.put("status", "success");
			
			return response;
	 
	    }
	    
	 
	 @GetMapping("/sparepartsinventorydeleteinfo")
		public String deleteSparePartsInventory(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Spare Parts Inventory with ID: " + id); // DEBUG
	            sparePartsInventoryService.deleteSparePartsInventoryById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Spare Parts Inventory entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Spare Parts Inventory.");
	        }
			return "redirect:/maintenancemanagementdepartment/sparepartsinventorygrid";
		}
	
	 @GetMapping("/updatesparepartsinventory/{id}")
		public String updateSparePartsInventoryById(@PathVariable("id") Long id, Model model) {
			SparePartsInventoryForm spid=sparePartsInventoryService.getSparePartsInventoryById(id);
			model.addAttribute("spid", spid);
			return "MaintenanceManagementDepartment/SparePartsInventoryForm";
		} 
	 
	@PostMapping("/updatesparepartsinventory")
	public String updateSparePartsInventoryData(@ModelAttribute SparePartsInventoryForm spif) {
		sparePartsInventoryService.updateSparePartsInventory(spif);
		return "redirect:/maintenancemanagementdepartment/sparepartsinventorygrid";
	}
		 
}
