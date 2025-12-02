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

import com.prog.model.erp.Employee;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceRequestInternalTicketing;
import com.prog.service.maintenancemanagement.MaintenanceRequestInternalTicketingService;

@Controller
public class MaintenanceRequestInternalTicketingController {
	@Autowired
	private MaintenanceRequestInternalTicketingService maintenanceRequestInternalTicketingService;
	
	@GetMapping("/maintenancemanagementdepartment/maintenancerequestinternalticketinggrid")
	public String showMaintenanceRequestInternalTicketingGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/MaintenanceRequestInternalTicketingGrid";
	}
	
	@GetMapping("/maintenancerequestinternalticketingform")
	public String viewMaintenanceRequestInternalTicketingForm(Model model) {
		model.addAttribute("mrit",new MaintenanceRequestInternalTicketing());
		List<String> equipmentmasteruid=maintenanceRequestInternalTicketingService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		List<String> employeeuid=maintenanceRequestInternalTicketingService.getEmployeeDetailsById();
		model.addAttribute("employeeuid",employeeuid);
		List<String> departmentname=maintenanceRequestInternalTicketingService.getDepartmentName();
		model.addAttribute("departmentname",departmentname);
		return "MaintenanceManagementDepartment/MaintenanceRequestInternalTicketing";
	}

	@PostMapping("/savemaintenancerequestinternalticketing")
	public String saveMaintenanceRequestInternalTicketingForm(@ModelAttribute MaintenanceRequestInternalTicketing mr) {
		maintenanceRequestInternalTicketingService.savemaintenance(mr);
		return "redirect:/maintenancemanagementdepartment/maintenancerequestinternalticketinggrid";
	}
	
	@GetMapping("/viewmaintenancerequestinternalticketing")
    @ResponseBody
    public Map<String,Object> showMaintenanceRequestInternalTicketinglist(Model modedl){
    	List<String> headers = List.of("ID","Maintenance Request Internal Ticketing UID", "Equipment Master UID", "Equipment Name", "Employee UID",
				"Employee Name", "Department Name", "Priority Level", "Request Date", "Status","Issue Description");
		
		List<Map<String, Object>> maintenancerequestinternalticketinginfolist = maintenanceRequestInternalTicketingService.showMaintenanceRequestInternalTicketinglist();

		List<Map<String, String>> maintenancerequestinternalticketinglist = new ArrayList<>();
		
		for (Map<String, Object> maintenancerequestinternalticketinginfo : maintenancerequestinternalticketinginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(maintenancerequestinternalticketinginfo.get("id")));
			row.put("Maintenance Request Internal Ticketing UID", String.valueOf(maintenancerequestinternalticketinginfo.get("maintenancerequestinternalticketinguid")));
			row.put("Equipment Master UID",String.valueOf(maintenancerequestinternalticketinginfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(maintenancerequestinternalticketinginfo.get("equipmentname")));
			row.put("Employee UID", String.valueOf(maintenancerequestinternalticketinginfo.get("employeeuid")));
			row.put("Employee Name", String.valueOf(maintenancerequestinternalticketinginfo.get("first_name")));
			row.put("Department Name", String.valueOf(maintenancerequestinternalticketinginfo.get("departmentname")));
			row.put("Priority Level", String.valueOf(maintenancerequestinternalticketinginfo.get("prioritylevel")));
			row.put("Request Date", String.valueOf(maintenancerequestinternalticketinginfo.get("requestdate")));
			row.put("Status", String.valueOf(maintenancerequestinternalticketinginfo.get("status")));
			row.put("Issue Description", String.valueOf(maintenancerequestinternalticketinginfo.get("issuedescription")));
			maintenancerequestinternalticketinglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", maintenancerequestinternalticketinglist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/maintenancerequestinternalticketingdeleteinfo")
		public String deleteMaintenanceRequestInternalTicketing(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Maintenance Request Internal Ticketing with ID: " + id); // DEBUG
	            maintenanceRequestInternalTicketingService.deleteMaintenanceRequestInternalTicketing(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance Request Internal Ticketing Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance Request Internal Ticketing Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/maintenancerequestinternalticketinggrid";
		}
	
	 @GetMapping("/updatemaintenancerequestinternalticketing/{id}")
		public String getMaintenanceRequestInternalTicketingById(@PathVariable("id") Long id, Model model) {
		 MaintenanceRequestInternalTicketing mrit=maintenanceRequestInternalTicketingService.getMaintenanceRequestInternalTicketingById(id);
			model.addAttribute("mr", mrit);
			return "MaintenanceManagementDepartment/MaintenanceRequestInternalTicketing";
		} 
	 
	@PostMapping("/updatemaintenancerequestinternalticketing")
	public String updateMaintenanceRequestInternalTicketing(@ModelAttribute MaintenanceRequestInternalTicketing mrit) {
		maintenanceRequestInternalTicketingService.updateMaintenanceRequestInternalTicketing(mrit);
		return "redirect:/maintenancemanagementdepartment/maintenancerequestinternalticketinggrid";
	}
	
	@GetMapping("/fetchEquipmentDetailsInMaintenanceRequestInternalTicketing")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return maintenanceRequestInternalTicketingService.getEquipmentDetails(equipmentmasteruid);
	}
	
	@GetMapping("/fetchEmployeeDetailsInMaintenanceRequestInternalTicketing")
	@ResponseBody
	public Employee getEmployeeDetails(String employeeuid) {
		return maintenanceRequestInternalTicketingService.getEmployeeDetails(employeeuid);
	}
	
}
