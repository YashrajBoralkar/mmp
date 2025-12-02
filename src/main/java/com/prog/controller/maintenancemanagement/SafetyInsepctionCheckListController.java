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
import com.prog.model.erp.SafetyInsepctionCheckList;
import com.prog.service.maintenancemanagement.SafetyInsepctionCheckListService;

@Controller
public class SafetyInsepctionCheckListController {

	@Autowired
	private SafetyInsepctionCheckListService safetyInsepctionCheckListService;
	
	@GetMapping("/maintenancemanagementdepartment/safetyinsepctionchecklistgrid")
	public String showSafetyInsepctionCheckListGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/SafetyInsepctionCheckListGrid";
	}
	
	@GetMapping("/safetyinsepctionchecklistform")
	public String viewSafetyInsepctionCheckListForm(Model model) {
		model.addAttribute("mhj",new SafetyInsepctionCheckList());
		List<String> equipmentmasteruid=safetyInsepctionCheckListService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		
		return "MaintenanceManagementDepartment/SafetyInsepctionCheckListForm";
	}
	
	@PostMapping("/savesafetyinsepctionchecklist")
	public String saveSafetyInsepctionCheckList(@ModelAttribute SafetyInsepctionCheckList sicl) {
		safetyInsepctionCheckListService.saveSafetyInspection(sicl);
		return "redirect:/maintenancemanagementdepartment/safetyinsepctionchecklistgrid";
	}
	
	@GetMapping("/viewsafetyinsepctionchecklist")
    @ResponseBody
    public Map<String,Object> getMaintenanceHistoryLogList(Model modedl){
    	List<String> headers = List.of("ID","Safety Insepction Check List UID", "Equipment Master UID", "Equipment Name", "Inspection Date",
				"Checklist Items", "Inspected By", "Remarks", "Status");
		
		List<Map<String, Object>> safetyinsepctionchecklistinfolist = safetyInsepctionCheckListService.getAllSafetyInspection();

		List<Map<String, String>> safetyinsepctionchecklistlist = new ArrayList<>();
		
		for (Map<String, Object> safetyinsepctionchecklistinfo : safetyinsepctionchecklistinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(safetyinsepctionchecklistinfo.get("id")));
			row.put("Safety Insepction Check List UID", String.valueOf(safetyinsepctionchecklistinfo.get("safetyinspectionchecklistuid")));
			row.put("Equipment Master UID",String.valueOf(safetyinsepctionchecklistinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(safetyinsepctionchecklistinfo.get("equipmentname")));
			row.put("Inspection Date", String.valueOf(safetyinsepctionchecklistinfo.get("inspectiondate")));
			row.put("Checklist Items", String.valueOf(safetyinsepctionchecklistinfo.get("checklistitems")));
			row.put("Inspected By", String.valueOf(safetyinsepctionchecklistinfo.get("inspectedby")));
			row.put("Remarks", String.valueOf(safetyinsepctionchecklistinfo.get("remarks")));
			row.put("Status", String.valueOf(safetyinsepctionchecklistinfo.get("status")));
			safetyinsepctionchecklistlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", safetyinsepctionchecklistlist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/safetyinsepctionchecklistdeleteinfo")
		public String deleteMaintenanceHistoryLogById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Safety Insepction Check List with ID: " + id); // DEBUG
	            safetyInsepctionCheckListService.deleteSafetyInspection(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Safety Insepction Check List Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Safety Insepction Check List Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/safetyinsepctionchecklistgrid";
		}
	
	 @GetMapping("/updatesafetyinsepctionchecklist/{id}")
		public String getSafetyInspectionById(@PathVariable("id") Long id, Model model) {
		 SafetyInsepctionCheckList sicl=safetyInsepctionCheckListService.getSafetyInspectionById(id);
			model.addAttribute("checklist", sicl);
			return "MaintenanceManagementDepartment/SafetyInsepctionCheckListForm";
		} 
	 
	@PostMapping("/updatesafetyinsepctionchecklist")
	public String updateSafetyInspection(@ModelAttribute SafetyInsepctionCheckList sicl) {
		safetyInsepctionCheckListService.updateSafetyInspection(sicl);
		return "redirect:/maintenancemanagementdepartment/safetyinsepctionchecklistgrid";
	}
	
	
	
	@GetMapping("/fetchEquipmentDetailsInSafetyInsepctionCheckList")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return safetyInsepctionCheckListService.getEquipmentDetails(equipmentmasteruid);
	}
	
	
}
