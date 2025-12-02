package com.prog.controller.productionandoperation;

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
import com.prog.model.erp.MaintenanceRequest;
import com.prog.service.productionandoperation.MaintenanceRequestService;
@Controller
public class MaintenanceRequestController {
	
	@Autowired
	private MaintenanceRequestService maintenanceRequestService;
	
	@GetMapping("/productionandoperationmanagementgrid/maintanancerequestgrid")
	public String viewGird() {
		return "ProductionAndOperationManagementgrid/MaintenanceRequestGrid";
	}
	
	@GetMapping("/maintenancerequestform")
	public String showMaintenanceRequestForm(Model model) {
		model.addAttribute("request", new MaintenanceRequest());
		List<String> equipmentmasteruid= maintenanceRequestService.getEquipmentMasteruid();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		List<String> employeeuid= maintenanceRequestService.getEmployeeuid();
		model.addAttribute("employeeuid",employeeuid);
		return "ProductionAndOperationManagement/MaintenanceRequestForm";
	}
	
	@PostMapping("/savemaintenancerequest")
	public String saveMaintenanceRequest(@ModelAttribute MaintenanceRequest mr, Model model) {
		maintenanceRequestService.saveMaintenanceRequest(mr);
		 model.addAttribute("message", "Maintenance Request data saved successfully!");			
	        return "redirect:/productionandoperationmanagementgrid/maintanancerequestgrid";			
	}
	
	@GetMapping("/viewmaintenancerequestlist")
	@ResponseBody
	public Map<String, Object> viewAllProductionPlanning(Model model) {
		List<String> headers = List.of("ID", "Maintenance Request Id", "Equipment Master UID", "Equipment Name", "Maintenance Type",
				"Issue Description", "Spare Parts Required", "Estimated Repair Cost", "Estimated Completion Date", "Requested Status", "Requested By (Employee UID)",
				"Requested By Name");

		List<Map<String, Object>> maintenancerequestinfolist = maintenanceRequestService.getMaintenanceRequestList();

		List<Map<String, String>> maintenancerequestlist = new ArrayList<>();

		for (Map<String, Object> maintenancerequestinfo : maintenancerequestinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(maintenancerequestinfo.get("id")));
			row.put("Maintenance Request Id", String.valueOf(maintenancerequestinfo.get("maintenancerequestuid")));
			row.put("Equipment Master UID", String.valueOf(maintenancerequestinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenancerequestinfo.get("equipmentname")));
			row.put("Maintenance Type", String.valueOf(maintenancerequestinfo.get("maintenancetype")));
			row.put("Issue Description",String.valueOf(maintenancerequestinfo.get("issuedescription")));
			row.put("Spare Parts Required", String.valueOf(maintenancerequestinfo.get("sparepartsrequired")));
			row.put("Estimated Repair Cost", String.valueOf(maintenancerequestinfo.get("estimatedrepaircost")));
			row.put("Estimated Completion Date", String.valueOf(maintenancerequestinfo.get("estimatedcompletiondate")));
			row.put("Requested Status", String.valueOf(maintenancerequestinfo.get("requestedstatus")));
			row.put("Requested By (Employee UID)",String.valueOf(maintenancerequestinfo.get("employeeuid")));
			row.put("Requested By Name", String.valueOf(maintenancerequestinfo.get("first_name")));
			maintenancerequestlist.add(row);			
		}
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenancerequestlist);
		response.put("status", "success");
		return response;
		
	}
	@GetMapping("/maintenancerequestdeleteinfo")
	public String deleteproductionplanning(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            maintenanceRequestService.deleteMaintenanceRequestById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance Request entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance Request.");
	        }
			return "redirect:/productionandoperationmanagementgrid/maintanancerequestgrid";
	}
	
	@GetMapping("/updatemaintenancerequest/{id}")
	public String updateProductionPlanningById(@PathVariable("id") Long id, Model model) {
		MaintenanceRequest mr = maintenanceRequestService.getMaintenanceRequestbyid(id);
		model.addAttribute("request", mr);
		return "ProductionAndOperationManagement/MaintenanceRequestForm";

	}

	@PostMapping("/maintenancerequestupdate")
	public String updateproduction(@ModelAttribute MaintenanceRequest mr) {
		try {
			maintenanceRequestService.updateMaintenanceRequest(mr);
		} catch(Exception e) {
			
		}
		return "redirect:/productionandoperationmanagementgrid/maintanancerequestgrid";
	}
	@GetMapping("/getequipmentmasterdetails")
	@ResponseBody
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return maintenanceRequestService.getEquipmentMasterDetails(equipmentmasteruid);
	}
	
	@GetMapping("/getemployeenamebyid")
	@ResponseBody
	public String getEmployeeNameById(String employeeuid) {
	    return maintenanceRequestService.getEmployeeFullNameById(employeeuid);
	}
}
