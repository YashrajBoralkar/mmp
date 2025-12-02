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
import com.prog.model.erp.PreventiveMaintenanceSchedule;
import com.prog.service.maintenancemanagement.PreventiveMaintenanceScheduleService;

@Controller
public class PreventiveMaintenanceScheduleController {
	@Autowired
	private PreventiveMaintenanceScheduleService preventiveMaintenanceScheduleService;
	
	@GetMapping("/maintenancemanagementdepartment/preventivemaintenanceschedulegrid")
	public String showPreventiveMaintenanceScheduleGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/PreventiveMaintenanceScheduleGrid";
	}
	
	@GetMapping("preventivemaintenancescheduleform")
	public String showPreventiveMaintenanceScheduleForm(Model model) {
		model.addAttribute("preventivemaintenance", new PreventiveMaintenanceSchedule());
		List<String> equipmentmasteruid= preventiveMaintenanceScheduleService.getEquipmentMasteruid();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);	
		return "MaintenanceManagementDepartment/PreventiveMaintenanceScheduleForm";
	}
	
	@PostMapping("/savepreventivemaintenanceschedule")
	public String savePreventiveSchedule(@ModelAttribute PreventiveMaintenanceSchedule pms) {
		preventiveMaintenanceScheduleService.savePreventiveSchedule(pms);
		return "redirect:/maintenancemanagementdepartment/preventivemaintenanceschedulegrid";
	}
	
	 @GetMapping("/viewpreventivemaintenanceschedule")
	    @ResponseBody
	    public Map<String,Object> getEquipmentMasterList(Model modedl){
	    	List<String> headers = List.of("ID","Preventive Maintenance Schedule UID","Equipment Master UID", "Equipment Name", "Maintenance Task", "Frequency",
					"Next Due Date", "Assigned Technician",  "Checklist Items","Status");
			
			List<Map<String, Object>> preventivemaintenancescheduleinfolist = preventiveMaintenanceScheduleService.getAllPreventiveSchedules();

			List<Map<String, String>> preventivemaintenanceschedulelist = new ArrayList<>();
			
			for (Map<String, Object> preventivemaintenancescheduleinfo : preventivemaintenancescheduleinfolist) {
				Map<String, String> row = new HashMap<>();
				row.put("ID",String.valueOf(preventivemaintenancescheduleinfo.get("id")));
				row.put("Preventive Maintenance Schedule UID",String.valueOf(preventivemaintenancescheduleinfo.get("id")));
				row.put("Equipment Master UID", String.valueOf(preventivemaintenancescheduleinfo.get("equipmentmasteruid")));
				row.put("Equipment Name",String.valueOf(preventivemaintenancescheduleinfo.get("equipmentname")));
				row.put("Maintenance Task", String.valueOf(preventivemaintenancescheduleinfo.get("maintenancetask")));
				row.put("Frequency", String.valueOf(preventivemaintenancescheduleinfo.get("frequency")));
				row.put("Next Due Date", String.valueOf(preventivemaintenancescheduleinfo.get("nextduedate")));
				row.put("Assigned Technician", String.valueOf(preventivemaintenancescheduleinfo.get("assignedtechnician")));
				row.put("Checklist Items", String.valueOf(preventivemaintenancescheduleinfo.get("checklistitems")));
				row.put("Status", String.valueOf(preventivemaintenancescheduleinfo.get("status")));
				preventivemaintenanceschedulelist.add(row);

			}

			Map<String, Object> response = new HashMap<>();
			response.put("headers", headers);
			response.put("data", preventivemaintenanceschedulelist);
			response.put("status", "success");
			
			return response;
	 
	    }
	    
	    @GetMapping("/preventivemaintenancescheduledeleteinfo")
		public String deleteEquipmentMasterById(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
			 try {
		            System.out.println("Deleting Preventive Schedule with ID: " + id); // DEBUG
		            preventiveMaintenanceScheduleService.deletePreventiveSchedule(id);
		            redirectAttributes.addFlashAttribute("successMessage", "Preventive Schedule entry deleted successfully!");
		        } catch (Exception e) {
		            e.printStackTrace(); // DEBUG
		            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Preventive Schedule.");
		        }
			return "redirect:/maintenancemanagementdepartment/preventivemaintenanceschedulegrid";
		}
		
		@GetMapping("/updatepreventivemaintenanceschedule/{id}")
		public String getPreventiveScheduleById(@PathVariable("id") Long id,Model model) {
			PreventiveMaintenanceSchedule pms=preventiveMaintenanceScheduleService.getPreventiveScheduleById(id);
			model.addAttribute("pmsf", pms);
			return "MaintenanceManagementDepartment/PreventiveMaintenanceScheduleForm";
		}
		
		@PostMapping("/updatepreventivemaintenanceschedule")
		public String updatePreventiveSchedule(@ModelAttribute PreventiveMaintenanceSchedule pms) {
			try {
				preventiveMaintenanceScheduleService.updatePreventiveSchedule(pms);
			} catch(Exception e) {
				
			}
			return "redirect:/maintenancemanagementdepartment/preventivemaintenanceschedulegrid";
		}

	
	@GetMapping("/fetchequipmentmasterdata")
	@ResponseBody
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return preventiveMaintenanceScheduleService.getEquipmentMasterDetails(equipmentmasteruid);
	}
}
