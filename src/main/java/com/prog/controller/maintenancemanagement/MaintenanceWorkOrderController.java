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
import com.prog.model.erp.MaintenanceWorkOrder;
import com.prog.service.maintenancemanagement.MaintenanceWorkOrderService;
 
@Controller
public class MaintenanceWorkOrderController {
	@Autowired
	private MaintenanceWorkOrderService maintenanceWorkOrderService;
	
	@GetMapping("/maintenancemanagementdepartment/maintenanceworkordergrid")
	public String viewMaintenanceWorkOrderFormGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/MaintenanceWorkOrderGrid";
	}
	
	@GetMapping("/maintenanceworkorderform")
	public String showMaintenanceWorkOrderForm(Model model) {
		model.addAttribute("mhj",new MaintenanceHistoryLogForm());
		List<String> equipmentmasteruid=maintenanceWorkOrderService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		
		return "MaintenanceManagementDepartment/MaintenanceWorkOrderForm";
	}
	
	@PostMapping("/savemaintenanceworkorder")
	public String saveMaintenanceWorkOrder(@ModelAttribute MaintenanceWorkOrder mwo) {
		maintenanceWorkOrderService.saveMaintenanceworkorder(mwo);
		return "redirect:/maintenancemanagementdepartment/maintenanceworkordergrid";
	}
	
	@GetMapping("/viewmaintenanceworkorder")
    @ResponseBody
    public Map<String,Object> getMaintenanceWorkOrderList(Model modedl){
    	List<String> headers = List.of("ID","Maintenance Work Order UID", "Work Type", "Equipment Master UID", "Equipment Name",
				"Request Date", "Task Description", "Priority Level", "Estimated Completion Date", "Status");
		
		List<Map<String, Object>> maintenanceworkorderinfolist = maintenanceWorkOrderService.getAllMaintenanceWorkOrder();

		List<Map<String, String>> maintenanceworkorderlist = new ArrayList<>();
		
		for (Map<String, Object> maintenanceworkorderinfo : maintenanceworkorderinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(maintenanceworkorderinfo.get("id")));
			row.put("Maintenance Work Order UID", String.valueOf(maintenanceworkorderinfo.get("equipmentmasteruid")));
			row.put("Work Type",String.valueOf(maintenanceworkorderinfo.get("worktype")));
			row.put("Equipment Master UID", String.valueOf(maintenanceworkorderinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenanceworkorderinfo.get("equipmentname")));
			row.put("Request Date", String.valueOf(maintenanceworkorderinfo.get("requestedate")));
			row.put("Task Description", String.valueOf(maintenanceworkorderinfo.get("taskdescription")));
			row.put("Priority Level", String.valueOf(maintenanceworkorderinfo.get("prioritylevel")));
			row.put("Estimated Completion Date", String.valueOf(maintenanceworkorderinfo.get("estimatedcompletiondate")));
			row.put("Status", String.valueOf(maintenanceworkorderinfo.get("status")));
			maintenanceworkorderlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenanceworkorderlist);
		response.put("status", "success");
		
		return response;
 
    }
    
    @GetMapping("/maintenanceworkorderdeleteinfo")
	public String deleteMaintenanceWorkOrderById(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Equipment Master with ID: " + id); // DEBUG
	            maintenanceWorkOrderService.deleteMaintenanceWorkOrder(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Equipment Master entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Equipment Master.");
	        }
		return "redirect:/maintenancemanagementdepartment/maintenanceworkordergrid";
	}
	
	@GetMapping("/updatemaintenanceworkorder/{id}")
	public String updateMaintenanceWorkOrderById(@PathVariable("id") Long id,Model model) {
		MaintenanceWorkOrder mwo=maintenanceWorkOrderService.getMaintenanceWorkOrderById(id);
		model.addAttribute("order", mwo);
		return "MaintenanceManagementDepartment/MaintenanceWorkOrderForm";
	}
	
	@PostMapping("/updatemaintenanceworkorder")
	public String updateMaintenanceWorkOrderData(@ModelAttribute MaintenanceWorkOrder mwo) {
		try {
			maintenanceWorkOrderService.updateMaintenanceWorkOrder(mwo);
		} catch(Exception e) {
			
		}
		return "redirect:/maintenancemanagementdepartment/maintenanceworkordergrid";
	}
	
	
	@GetMapping("/fetchEquipmentMasterDetailsInMaintenanceWorkOrder")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return maintenanceWorkOrderService.getEquipmentDetails(equipmentmasteruid);
	}

}
