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

import com.prog.model.erp.DowntimeAnalysis;
import com.prog.model.erp.EquipmentMaster;
import com.prog.service.maintenancemanagement.DowntimeAnalysisService;

@Controller
public class DowntimeAnalysisController {
	@Autowired
	private DowntimeAnalysisService downtimeAnalysisService;
	
	@GetMapping("/maintenancemanagementdepartment/downtimeanalysisgrid")
	public String viewDowntimeAnalysisGrid() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/DowntimeAnalysisGrid";
	}
	
	@GetMapping("/downtimeanalysisform")
	public String viewDowntimeAnalysisForm(String noofdowntime,Model model) {
		model.addAttribute("da",new DowntimeAnalysis());
		List<String> equipmentmasteruid=downtimeAnalysisService.getEquipmentDetailsById();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		return "MaintenanceManagementDepartment/DowntimeAnalysisForm";
	}
	
	@PostMapping("/savedowntimeanalysis")
	public String saveDowntimeanalysis(@ModelAttribute DowntimeAnalysis da) {
		downtimeAnalysisService.saveDowntimeanalysis(da);
		return "redirect:/maintenancemanagementdepartment/downtimeanalysisgrid";
	}	
	
	@GetMapping("/viewdowntimeanalysis")
    @ResponseBody
    public Map<String,Object> getAlldowntimeAnalysisData(Model modedl){
    	List<String> headers = List.of("ID","Downtime Analysis UID", "Equipment Master UID", "Equipment Name", "Total Downtime",
				"No of Breakdowns", "Frequent Issues", "Preventive Actions Suggested", "Remarks");
		
		List<Map<String, Object>> downtimeanalysisinfolist = downtimeAnalysisService.getAlldowntimeAnalysisData();

		List<Map<String, String>> downtimeanalysislist = new ArrayList<>();
		
		for (Map<String, Object> downtimeanalysisinfo : downtimeanalysisinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(downtimeanalysisinfo.get("id")));
			row.put("Downtime Analysis UID", String.valueOf(downtimeanalysisinfo.get("downtimeanalysisuid")));
			row.put("Equipment Master UID",String.valueOf(downtimeanalysisinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(downtimeanalysisinfo.get("equipmentname")));
			row.put("Total Downtime", String.valueOf(downtimeanalysisinfo.get("totaldowntime")));
			row.put("No of Breakdowns", String.valueOf(downtimeanalysisinfo.get("noofbreakdown")));
			row.put("Frequent Issues", String.valueOf(downtimeanalysisinfo.get("frequentissues")));
			row.put("Preventive Actions Suggested", String.valueOf(downtimeanalysisinfo.get("preventiveactionssuggested")));
			row.put("Remarks", String.valueOf(downtimeanalysisinfo.get("remarks")));
			downtimeanalysislist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", downtimeanalysislist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/downtimeanalysisdeleteinfo")
		public String deleteDowntimeAnalysisById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Downtime Analysis with ID: " + id); // DEBUG
	            downtimeAnalysisService.deleteDowntimeAnalysisById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Downtime Analysis Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Downtime Analysis Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/downtimeanalysisgrid";
		}
	
	 @GetMapping("/updatedowntimeanalysis/{id}")
		public String getDowntimeAnalysisById(@PathVariable("id") Long id, Model model) {
		 DowntimeAnalysis da=downtimeAnalysisService.getDowntimeAnalysisById(id);
			model.addAttribute("downtime", da);
			return "MaintenanceManagementDepartment/DowntimeAnalysisForm";
		} 
	 
	@PostMapping("/updatedowntimeanalysis")
	public String updateDowntimeAnalysis(@ModelAttribute DowntimeAnalysis da) {
		downtimeAnalysisService.updateDowntimeAnalysis(da);
		return "redirect:/maintenancemanagementdepartment/downtimeanalysisgrid";
	}
	
	
	@GetMapping("/fetchEquipmentDetailsInDowntimeAnalysis")
	@ResponseBody
	public EquipmentMaster getEquipmentDetailsById(String equipmentmasteruid) {
		return downtimeAnalysisService.getEquipmentDetails(equipmentmasteruid);
	}
	
	 //  Get total downtime duration for an equipment
    @GetMapping("/downtime/total/{equipmentmasteruid}")
    @ResponseBody
    public String getdowntimeduration(@PathVariable("equipmentmasteruid") String equipmentmasteruid) {
        return downtimeAnalysisService.getdowntimeduration(equipmentmasteruid);
    }
}
