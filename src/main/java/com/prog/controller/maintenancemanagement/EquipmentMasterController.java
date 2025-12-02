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

import com.prog.model.erp.EquipmentMaster;
import com.prog.service.maintenancemanagement.EquipmentMasterService;

@Controller
public class EquipmentMasterController {
	@Autowired 
	private EquipmentMasterService equipmentMasterService;
	
	@GetMapping("/maintenancemanagementdepartment/equipmentmastergrid")
	public String showEquipmentMasterGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/EquipmentMasterGrid";
	}
	
	@GetMapping("/equipmentmasterform")
	public String  showEquipmentMaster(Model model) {
		model.addAttribute("em", new EquipmentMaster());
		List<String> departmentname= equipmentMasterService.getDepartmentName();
		model.addAttribute("departmentname", departmentname);
		return "MaintenanceManagementDepartment/EquipmentMasterForm";
	}
	
	 // Submit equipmentmaster Form
    @PostMapping("/saveequipmentmaster")
    public String submitEquipmentmasterform(@ModelAttribute EquipmentMaster em) {
        // Save the submitted equipmentmaster data using the service layer
    	equipmentMasterService.saveEquipmentmaster(em);
        return "redirect:/maintenancemanagementdepartment/equipmentmastergrid"; // After saving, redirect the user to the equipmentmaster listing page
    }
    
    @GetMapping("/viewequipmentmaster")
    @ResponseBody
    public Map<String,Object> getEquipmentMasterList(Model modedl){
    	List<String> headers = List.of("ID","Equipment UID", "Equipment Name", "Category", "Location",
				"Purchase Date", "Warranty Start Date", "Warranty End Date", "Manufacturer Name", "Maintenance Frequency", "Department Name","Status");
		
		List<Map<String, Object>> equipmentmasterinfolist = equipmentMasterService.getAllEquipmentmaster();

		List<Map<String, String>> equipmentmasterlist = new ArrayList<>();
		
		for (Map<String, Object> equipmentmasterinfo : equipmentmasterinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(equipmentmasterinfo.get("id")));
			row.put("Equipment UID", String.valueOf(equipmentmasterinfo.get("equipmentmasteruid")));
			row.put("Equipment Name",String.valueOf(equipmentmasterinfo.get("equipmentname")));
			row.put("Category", String.valueOf(equipmentmasterinfo.get("category")));
			row.put("Location", String.valueOf(equipmentmasterinfo.get("location")));
			row.put("Purchase Date", String.valueOf(equipmentmasterinfo.get("purchasedate")));
			row.put("Warranty Start Date", String.valueOf(equipmentmasterinfo.get("warrantystartdate")));
			row.put("Warranty End Date", String.valueOf(equipmentmasterinfo.get("warrantyenddate")));
			row.put("Manufacturer Name", String.valueOf(equipmentmasterinfo.get("manufacturername")));
			row.put("Maintenance Frequency", String.valueOf(equipmentmasterinfo.get("maintenancefrequency")));
			row.put("Department Name", String.valueOf(equipmentmasterinfo.get("departmentname")));
			row.put("Status", String.valueOf(equipmentmasterinfo.get("status")));
			equipmentmasterlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", equipmentmasterlist);
		response.put("status", "success");
		
		return response;
 
    }
    
    @GetMapping("/equipmentmasterdeleteinfo")
	public String deleteEquipmentMasterById(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Equipment Master with ID: " + id); // DEBUG
	            equipmentMasterService.deleteEquipmentmaster(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Equipment Master entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Equipment Master.");
	        }
		return "redirect:/maintenancemanagementdepartment/equipmentmastergrid";
	}
	
	@GetMapping("/updateequipmentmaster/{id}")
	public String updateEquipmentMasterById(@PathVariable("id") Long id,Model model) {
		EquipmentMaster em=equipmentMasterService.getEquipmentmasterById(id);
		model.addAttribute("em", em);
		return "MaintenanceManagementDepartment/EquipmentMasterForm";
	}
	
	@PostMapping("/updateequipmentmaster")
	public String updateEquipmentMasterData(@ModelAttribute EquipmentMaster em) {
		try {
			equipmentMasterService.updateEquipmentmaster(em);
		} catch(Exception e) {
			
		}
		return "redirect:/maintenancemanagementdepartment/equipmentmastergrid";
	}
	
}
