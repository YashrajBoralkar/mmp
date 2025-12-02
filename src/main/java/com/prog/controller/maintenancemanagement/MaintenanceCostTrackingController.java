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
import com.prog.model.erp.MaintenanceCostTracking;
import com.prog.service.maintenancemanagement.MaintenanceCostTrackingService;

@Controller
public class MaintenanceCostTrackingController {
	
	@Autowired
	private MaintenanceCostTrackingService maintenanceCostTrackingService;
	
	@GetMapping("/maintenancemanagementdepartment/maintenancecosttrackinggrid")
	public String viewMaintenanceCostTrackingGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/MaintenanceCostTrackingGrid";
	}
	
	@GetMapping("/maintenancecosttrackingform")
	public String showMaintenanceCostTrackingform(Model model) {
		model.addAttribute("mct",new MaintenanceCostTracking());
		List<String> equipmentmasteruid=maintenanceCostTrackingService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		
		return "MaintenanceManagementDepartment/MaintenanceCostTrackingForm";
	}
	
	@PostMapping("/savemaintenancecosttracking")
	public String saveMaintenanceCostTracking(@ModelAttribute MaintenanceCostTracking mct) {
		maintenanceCostTrackingService.saveMaintenanceCostTracking(mct);
		return "redirect:/maintenancemanagementdepartment/maintenancecosttrackinggrid";
	}
	
	@GetMapping("/viewmaintenancecosttracking")
    @ResponseBody
    public Map<String,Object> getMaintenanceHistoryLogList(Model modedl){
    	List<String> headers = List.of("ID","Maintenance Cost Tracking UID", "Equipment Master UID", "Equipment Name", "Maintenance Date",
				"Type of Maintenance", "Labour Cost", "Parts Cost", "Parts Used", "Next Due Maintenance ");
		
		List<Map<String, Object>> maintenancecosttrackinginfolist = maintenanceCostTrackingService.getAllMaintenanceCostTrackingData();

		List<Map<String, String>> maintenancecosttrackinglist = new ArrayList<>();
		
		for (Map<String, Object> maintenancecosttrackinginfo : maintenancecosttrackinginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(maintenancecosttrackinginfo.get("id")));
			row.put("Maintenance Cost Tracking UID", String.valueOf(maintenancecosttrackinginfo.get("maintenancecosttrackinguid")));
			row.put("Equipment Master UID",String.valueOf(maintenancecosttrackinginfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenancecosttrackinginfo.get("equipmentname")));
			row.put("Maintenance Date", String.valueOf(maintenancecosttrackinginfo.get("date")));
			row.put("Type of Maintenance", String.valueOf(maintenancecosttrackinginfo.get("typeofmaintenance")));
			row.put("Labour Cost", String.valueOf(maintenancecosttrackinginfo.get("labourcost")));
			row.put("Parts Cost", String.valueOf(maintenancecosttrackinginfo.get("partscost")));
			row.put("Other Costs", String.valueOf(maintenancecosttrackinginfo.get("othercosts")));
			row.put("Total Cost", String.valueOf(maintenancecosttrackinginfo.get("totalcost")));
			maintenancecosttrackinglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenancecosttrackinglist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/maintenancecosttrackingdeleteinfo")
		public String deleteMaintenanceCostTrackingById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Cost Tracking with ID: " + id); // DEBUG
	            maintenanceCostTrackingService.deleteMaintenanceCostTrackingById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance Cost Tracking Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance Cost Tracking Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/maintenancecosttrackinggrid";
		}
	
	 @GetMapping("/updatemaintenancecosttracking/{id}")
		public String getMaintenanceCostTrackingById(@PathVariable("id") Long id, Model model) {
		 MaintenanceCostTracking mct=maintenanceCostTrackingService.getMaintenanceCostTrackingById(id);
			model.addAttribute("cost", mct);
			return "MaintenanceManagementDepartment/MaintenanceCostTrackingForm";
		} 
	 
	@PostMapping("/updatemaintenancecosttracking")
	public String updateMaintenanceCostTracking(@ModelAttribute MaintenanceCostTracking mct) {
		maintenanceCostTrackingService.updateMaintenanceCostTracking(mct);
		return "redirect:/maintenancemanagementdepartment/maintenancecosttrackinggrid";
	}
	
	

	
	@GetMapping("/fetchEquipmentDetailsInMaintenanceCostTracking")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return maintenanceCostTrackingService.getEquipmentDetails(equipmentmasteruid);
	}
	
}
