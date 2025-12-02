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

import com.prog.model.erp.SparePartUsageLog;
import com.prog.service.maintenancemanagement.SparePartUsageLogService;

@Controller
public class SparePartUsageLogController {
	@Autowired
	private SparePartUsageLogService sparePartUsageLogService;
	
	@GetMapping("maintenancemanagementdepartment/sparepartusageloggrid")
	public String viewSparePartUsageLogGird() {
		return "MAINTENANCEMANAGEMENTDEPERTMENTgrid/SparePartUsageLogGrid";
	}
	
	@GetMapping("/sparepartusagelogform")
	public String vireSparePartUsageLogForm(Model model) {
		 model.addAttribute("spul", new SparePartUsageLog());
	        List<String> sparepartsinventoryuid=sparePartUsageLogService.getSparepartDetailsByuid();
	        model.addAttribute("sparepartsinventoryuid",sparepartsinventoryuid);
	       
		return "MaintenanceManagementDepartment/SparePartUsageLogForm";
	}
	
	@PostMapping("/savesparepartusagelog")
	public String saveSparePartUsageLogData(@ModelAttribute SparePartUsageLog spul) {
		sparePartUsageLogService.addSparePartUsageLogData(spul);
		return "redirect:/maintenancemanagementdepartment/sparepartusageloggrid";
	}
	
	@GetMapping("/viewsparepartusagelog")
    @ResponseBody
    public Map<String,Object> getSparePartUsageLogList(Model modedl){
    	List<String> headers = List.of("ID","Spare Part  Usage UID", "Spare Part inventory UID", "Date of Usage", "Used For Equipment",
				"Quantity Used", "Used By", "Technician Name", "Parts Used", "Next Due Maintenance ");
		
		List<Map<String, Object>> sparepartusageloginfolist = sparePartUsageLogService.AllSparePartUsageLogList();

		List<Map<String, String>> sparepartusageloglist = new ArrayList<>();
		
		for (Map<String, Object> sparepartusageloginfo : sparepartusageloginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(sparepartusageloginfo.get("id")));
			row.put("Spare Part  Usage UID", String.valueOf(sparepartusageloginfo.get("sparepartusageuid")));
			row.put("Spare Part inventory UID",String.valueOf(sparepartusageloginfo.get("sparepartsinventoryuid")));
			row.put("Date of Usage", String.valueOf(sparepartusageloginfo.get("dateofusage")));
			row.put("Used For Equipment", String.valueOf(sparepartusageloginfo.get("usedforequipment")));
			row.put("Quantity Used", String.valueOf(sparepartusageloginfo.get("quantityused")));
			row.put("Used By", String.valueOf(sparepartusageloginfo.get("usedby")));
			row.put("Reason For Use", String.valueOf(sparepartusageloginfo.get("reasonforuse")));
			sparepartusageloglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", sparepartusageloglist);
		response.put("status", "success");
		
		return response;
 
    }

	 @GetMapping("/sparepartusagelogdeleteinfo")
		public String deleteSparePartUsageLogById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Spare Part Usage Log with ID: " + id); // DEBUG
	            sparePartUsageLogService.deleteSparePartUsageLogById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Spare Part Usage Log Form entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); 
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Spare Part Usage Log Form.");
	        }
			return "redirect:/maintenancemanagementdepartment/sparepartusageloggrid";
		}
	
	 @GetMapping("/updatesparepartusagelog/{id}")
		public String updateSparePartUsageLogById(@PathVariable("id") Long id, Model model) {
		 SparePartUsageLog spul=sparePartUsageLogService.getSparePartUsageLogById(id);
			model.addAttribute("updatespul", spul);
			return "MaintenanceManagementDepartment/SparePartUsageLogForm";
		} 
	 
	@PostMapping("/updatesparepartusagelog")
	public String updateSparePartUsageLogData(@ModelAttribute SparePartUsageLog spul) {
		sparePartUsageLogService.updateSparePartUsageLog(spul);
		return "redirect:/maintenancemanagementdepartment/sparepartusageloggrid";
	}
	

}
