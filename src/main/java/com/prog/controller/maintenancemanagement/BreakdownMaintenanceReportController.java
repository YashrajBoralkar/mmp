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

import com.prog.model.erp.BreakdownMaintenanceReport;
import com.prog.model.erp.Employee;
import com.prog.model.erp.EquipmentMaster;
import com.prog.service.maintenancemanagement.BreakdownMaintenanceReportService;

@Controller
public class BreakdownMaintenanceReportController {
	
	@Autowired
	private BreakdownMaintenanceReportService breakdownMaintenanceReportService;
	
	@GetMapping("/maintenancemanagementdepartment/breakdownmaintenancereportgrid")
	public String viewBreakdownMaintenanceReportGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/BreakdownMaintenanceReportGrid";
	}
	
	@GetMapping("/breakdownmaintenancereportform")
	public String viewBreakdownMaintenanceReportForm(Model model) {
		model.addAttribute("bmr",new BreakdownMaintenanceReport());
		List<String> equipmentmasteruid=breakdownMaintenanceReportService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		List<String> employeeuid=breakdownMaintenanceReportService.getEmployeeDetailsById();
		model.addAttribute("employeeuid",employeeuid);		
		return "MaintenanceManagementDepartment/BreakdownMaintenanceReportForm";
	}
	
	@PostMapping("savebreakdownmaintenancereport")
	public String saveBreakdownMaintenanceReport(@ModelAttribute BreakdownMaintenanceReport bmr) {
		breakdownMaintenanceReportService.saveBreakdownMaintenanceReport(bmr);
		return "redirect:/maintenancemanagementdepartment/breakdownmaintenancereportgrid";
	}
	
	@GetMapping("/viewbreakdownmaintenancereport")
    @ResponseBody
    public Map<String,Object> getMaintenanceHistoryLogList(Model modedl){
    	List<String> headers = List.of("ID","Breakdown Maintenance Report UID", "Equipment Master UID", "Equipment Name", "Breakdown Date and Time",
				"Reported By UID", "Reported By", "Issue Description", "Downtime Duration", "Repair Action Taken","Root Cause","Repaired By","Repair Completion Date","Status");
		
		List<Map<String, Object>> breakdownmaintenancereportinfolist = breakdownMaintenanceReportService.getAllBreakdownMaintenanceReportData();

		List<Map<String, String>> breakdownmaintenancereportlist = new ArrayList<>();
		
		for (Map<String, Object> breakdownmaintenancereportinfo : breakdownmaintenancereportinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(breakdownmaintenancereportinfo.get("id")));
			row.put("Breakdown Maintenance Report UID", String.valueOf(breakdownmaintenancereportinfo.get("breakdownmaintenancereportuid")));
			row.put("Equipment Master UID",String.valueOf(breakdownmaintenancereportinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(breakdownmaintenancereportinfo.get("equipmentname")));
			row.put("Breakdown Date and Time", String.valueOf(breakdownmaintenancereportinfo.get("breakdowndatetime")));
			row.put("Reported By UID", String.valueOf(breakdownmaintenancereportinfo.get("employeeuid")));
			row.put("Reported By", String.valueOf(breakdownmaintenancereportinfo.get("first_name")));
			row.put("Issue Description", String.valueOf(breakdownmaintenancereportinfo.get("issuedescription")));
			row.put("Downtime Duration", String.valueOf(breakdownmaintenancereportinfo.get("downtimeduration")));
			row.put("Repair Action Taken", String.valueOf(breakdownmaintenancereportinfo.get("repairactiontaken")));
			row.put("Root Cause", String.valueOf(breakdownmaintenancereportinfo.get("rootcause")));
			row.put("Repaired By",String.valueOf(breakdownmaintenancereportinfo.get("repairedby")));
			row.put("Repair Completion Date",String.valueOf(breakdownmaintenancereportinfo.get("repaircompletiondate")));
			row.put("Status",String.valueOf(breakdownmaintenancereportinfo.get("status")));

			breakdownmaintenancereportlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", breakdownmaintenancereportlist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/breakdownmaintenancereportdeleteinfo")
		public String deleteBreakdownMaintenanceReportById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Breakdown Maintenance Report with ID: " + id); // DEBUG
	            breakdownMaintenanceReportService.deleteBreakdownMaintenanceReportById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Breakdown Maintenance Report Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Breakdown Maintenance Report Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/breakdownmaintenancereportgrid";
		}
	
	 @GetMapping("/updatebreakdownmaintenancereport/{id}")
		public String getBreakdownMaintenanceReportById(@PathVariable("id") Long id, Model model) {
		 BreakdownMaintenanceReport bmr=breakdownMaintenanceReportService.getBreakdownMaintenanceReportById(id);
			model.addAttribute("report", bmr);
			return "MaintenanceManagementDepartment/BreakdownMaintenanceReportForm";
		} 
	 
	@PostMapping("/updatebreakdownmaintenancereport")
	public String updateBreakdownMaintenanceReport(@ModelAttribute BreakdownMaintenanceReport bmr) {
		breakdownMaintenanceReportService.updateBreakdownMaintenanceReport(bmr);
		return "redirect:/maintenancemanagementdepartment/breakdownmaintenancereportgrid";
	}
	
	
	
	@GetMapping("/fetchEquipmentDetailsInBreakdownMaintenanceReport")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return breakdownMaintenanceReportService.getEquipmentDetails(equipmentmasteruid);
	}
	
	@GetMapping("/fetchEmployeeDetailsInBreakdownMaintenanceReport")
	@ResponseBody
	public Employee getEmployeeDetails(String employeeuid) {
		return breakdownMaintenanceReportService.getEmployeeDetails(employeeuid);
	}
}
