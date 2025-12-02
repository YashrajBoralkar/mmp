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
import com.prog.model.erp.MaintenanceHistoryLogForm;
import com.prog.service.maintenancemanagement.MaintenanceHistoryLogService;


@Controller
public class MaintenanceHistoryLogController {
	@Autowired
	private MaintenanceHistoryLogService maintenanceHistoryLogService;
	
	@GetMapping("/maintenancemanagementdepartment/maintenancehistoryloggrid")
	public String viewMaintenanceHistoryLogGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/MaintenanceHistoryLogGrid";
	}
	
	@GetMapping("/maintenancehistorylogform")
	public String showMaintenanceHistoryLogForm(Model model) {
		model.addAttribute("mhj",new MaintenanceHistoryLogForm());
		List<String> equipmentmasteruid=maintenanceHistoryLogService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		
		return "MaintenanceManagementDepartment/MaintenanceHistoryLogForm";
	}
	
	@PostMapping("/savemaintenancehistorylog")
	public String addMaintenanceHistoryLogData(@ModelAttribute MaintenanceHistoryLogForm mhl) {
		maintenanceHistoryLogService.addMaintenanceHistoryLogData(mhl);
		return "redirect:/maintenancemanagementdepartment/maintenancehistoryloggrid";
	}
	
	@GetMapping("/viewmaintenancehistorylog")
    @ResponseBody
    public Map<String,Object> getMaintenanceHistoryLogList(Model modedl){
    	List<String> headers = List.of("ID","Maintenance History Log UID", "Equipment Master UID", "Equipment Name", "Maintenance Type",
				"Service Date", "Service Details", "Technician Name", "Parts Used", "Next Due Maintenance ");
		
		List<Map<String, Object>> maintenancehistoryloginfolist = maintenanceHistoryLogService.getAllMaintenanceHistoryLogData();

		List<Map<String, String>> maintenancehistoryloglist = new ArrayList<>();
		
		for (Map<String, Object> maintenancehistoryloginfo : maintenancehistoryloginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(maintenancehistoryloginfo.get("id")));
			row.put("Maintenance History Log UID", String.valueOf(maintenancehistoryloginfo.get("maintenancehistoryloguid")));
			row.put("Equipment Master UID",String.valueOf(maintenancehistoryloginfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenancehistoryloginfo.get("equipmentname")));
			row.put("Maintenance Type", String.valueOf(maintenancehistoryloginfo.get("maintenancetype")));
			row.put("Service Date", String.valueOf(maintenancehistoryloginfo.get("servicedate")));
			row.put("Service Details", String.valueOf(maintenancehistoryloginfo.get("servicedetails")));
			row.put("Technician Name", String.valueOf(maintenancehistoryloginfo.get("technicianname")));
			row.put("Parts Used", String.valueOf(maintenancehistoryloginfo.get("partsused")));
			row.put("Next Due Maintenance ", String.valueOf(maintenancehistoryloginfo.get("nextduedate")));
			maintenancehistoryloglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenancehistoryloglist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/maintenancehistorylogdeleteinfo")
		public String deleteMaintenanceHistoryLogById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Spare Parts Inventory with ID: " + id); // DEBUG
	            maintenanceHistoryLogService.deleteMaintenanceHistoryLogById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance History Log Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance History Log Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/maintenancehistoryloggrid";
		}
	
	 @GetMapping("/updatemaintenancehistorylog/{id}")
		public String updateMaintenanceHistoryLogById(@PathVariable("id") Long id, Model model) {
		 MaintenanceHistoryLogForm mjlf=maintenanceHistoryLogService.getMaintenanceHistoryLogById(id);
			model.addAttribute("maintenancehistorylog", mjlf);
			return "MaintenanceManagementDepartment/MaintenanceHistoryLogForm";
		} 
	 
	@PostMapping("/updatemaintenancehistorylog")
	public String updateMaintenanceHistoryLogData(@ModelAttribute MaintenanceHistoryLogForm mhjl) {
		maintenanceHistoryLogService.updateMaintenanceHistoryLogData(mhjl);
		return "redirect:/maintenancemanagementdepartment/maintenancehistoryloggrid";
	}
	
	
	@GetMapping("/fetchEquipmentDetails")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return maintenanceHistoryLogService.getEquipmentDetails(equipmentmasteruid);
	}
}
