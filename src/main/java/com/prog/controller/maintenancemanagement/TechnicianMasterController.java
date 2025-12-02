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

import com.prog.model.erp.TechnicianMaster;
import com.prog.service.maintenancemanagement.TechnicianMasterService;

@Controller
public class TechnicianMasterController {

	@Autowired
	private TechnicianMasterService technicianMasterService;
	
	@GetMapping("/maintenancemanagementdepartment/technicianmastergrid")
	public String viewTechnicianMasterGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/TechnicianMasterGrid";
	}
	
	@GetMapping("/technicianmasterform")
	public String showTechnicianMasterForm() {
		return "MaintenanceManagementDepartment/TechnicianMasterForm";
	}
	
	@PostMapping("/savetechnicianmaster")
	public String saveTechnicianMasterData(@ModelAttribute TechnicianMaster technicianmaster) {
		technicianMasterService.saveTechnician(technicianmaster);
		return "redirect:/maintenancemanagementdepartment/technicianmastergrid";
	}
	
	@GetMapping("/viewtechnicianmaster")
    @ResponseBody
    public Map<String,Object> getTechnicianMasterList(Model modedl){
    	List<String> headers = List.of("ID","Technician Master UID", "Technician Name", "Contact Number", "Department",
				"Skill Set", "Availability Status");
		
		List<Map<String, Object>> technicianmasterinfolist = technicianMasterService.findAllTechnicianMasterList();

		List<Map<String, String>> technicianmasterlist = new ArrayList<>();
		
		for (Map<String, Object> technicianmasterinfo : technicianmasterinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(technicianmasterinfo.get("id")));
			row.put("Technician Master UID", String.valueOf(technicianmasterinfo.get("technicianmasteruid")));
			row.put("Technician Name",String.valueOf(technicianmasterinfo.get("technicianname")));
			row.put("Contact Number", String.valueOf(technicianmasterinfo.get("contactnumber")));
			row.put("Department", String.valueOf(technicianmasterinfo.get("department")));
			row.put("Skill Set", String.valueOf(technicianmasterinfo.get("skillset")));
			row.put("Availability Status", String.valueOf(technicianmasterinfo.get("availabilitystatus")));
			technicianmasterlist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", technicianmasterlist);
		response.put("status", "success");
		
		return response;
 
    }

	@GetMapping("/technicianmasterdeleteinfo")
	public String deleteMaintenanceHistoryLogById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
	 try {
            System.out.println("Deleting Technician Master with ID: " + id); // DEBUG
            technicianMasterService.deleteTechnicianMasterById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Technician Master Form entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace(); 
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Technician Master Form.");
        }
		return "redirect:/maintenancemanagementdepartment/technicianmastergrid";
	}

 @GetMapping("/updatetechnicianmaster/{id}")
	public String updateMaintenanceHistoryLogById(@PathVariable("id") Long id, Model model) {
	 TechnicianMaster tm=technicianMasterService.getTechnicianMasterById(id);
		model.addAttribute("technician", tm);
		return "MaintenanceManagementDepartment/TechnicianMasterForm";
	} 
 
@PostMapping("/updatetechnicianmaster")
public String updateMaintenanceHistoryLogData(@ModelAttribute TechnicianMaster tm) {
	technicianMasterService.updateTechnicianMaster(tm);
	return "redirect:/maintenancemanagementdepartment/technicianmastergrid";
}


}
