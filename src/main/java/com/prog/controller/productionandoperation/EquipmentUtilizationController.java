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
import com.prog.model.erp.EquipmentUtilization;
import com.prog.model.erp.MaintenanceRequest;
import com.prog.service.productionandoperation.EquipmentUtilizationService;

@Controller
public class EquipmentUtilizationController {
	@Autowired 
	private EquipmentUtilizationService equipmentUtilizationService;
	@GetMapping("/productionandoperationmanagement/equipmentutilizationgrid")
	public String viewGrid() {
		return "ProductionAndOperationManagementgrid/EquipmentUtilizationGrid";
	}
	
	@GetMapping("/equipmentutilizationform")
	public String showEquipmentUtilizationForm(Model model) {
		model.addAttribute("equipmentutilization", new EquipmentUtilization());
		List<String> equipmentmasteruid= equipmentUtilizationService.getEquipmentMasteruid();
		model.addAttribute("equipmentmasteruid",equipmentmasteruid);
		return "ProductionAndOperationManagement/EquipmentUtilizationForm";
	}
	
	@PostMapping("/saveequipmentutilization")
	public String saveEquipmentUtilization(@ModelAttribute EquipmentUtilization eq, Model model) {
		equipmentUtilizationService.saveEquipmentUtilization(eq);
		 model.addAttribute("message", "Equipment Utilization data saved successfully!");			
	        return "redirect:/productionandoperationmanagement/equipmentutilizationgrid";			
	}
	
	@GetMapping("/viewequipmentutilizationlist")
	@ResponseBody
	public Map<String, Object> viewAllEquipmentUtilization(Model model) {
		List<String> headers = List.of("ID", "Equipment Utilization UID", "Equipment Master UID", "Equipment Name", "Utilization Rate",
				"Down Time Occurrence", "Energy Consumption");

		List<Map<String, Object>> equipmentutilizationinfolist = equipmentUtilizationService.findallEquipmentUtilization();

		List<Map<String, String>> equipmentutilizationlist = new ArrayList<>();

		for (Map<String, Object> equipmentutilizationinfo : equipmentutilizationinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(equipmentutilizationinfo.get("id")));
			row.put("Equipment Utilization UID", String.valueOf(equipmentutilizationinfo.get("equipmentutilizationuid")));
			row.put("Equipment Master UID", String.valueOf(equipmentutilizationinfo.get("equipmentmasteruid")));
			row.put("Equipment Name", String.valueOf(equipmentutilizationinfo.get("equipmentname")));
			row.put("Utilization Rate", String.valueOf(equipmentutilizationinfo.get("utilizationrate")));
			row.put("Down Time Occurrence", String.valueOf(equipmentutilizationinfo.get("downtimeoccurrence")));
			row.put("Energy Consumption",String.valueOf(equipmentutilizationinfo.get("energyconsumption")));
			equipmentutilizationlist.add(row);			
		}
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", equipmentutilizationlist);
		response.put("status", "success");
		return response;
		
	}
	
	@GetMapping("/equipmentutilizationdeleteinfo")
	public String deleteEquipmentUtilization(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		 try {
			 equipmentUtilizationService.deleteEquipmentUtilization(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Maintenance Request entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Maintenance Request.");
	        }
			return "redirect:/productionandoperationmanagement/equipmentutilizationgrid";
	}
	
	@GetMapping("/equipmentutilization/{id}")
	public String updateEquipmentUtilizationbyid(@PathVariable("id") Long id, Model model) {
		EquipmentUtilization eu = equipmentUtilizationService.getEquipmentUtilizationbyid(id);
		model.addAttribute("equipment", eu);
		return "ProductionAndOperationManagement/EquipmentUtilizationForm";

	}

	@PostMapping("/equipmentutilizationupdate")
	public String updateproduction(@ModelAttribute EquipmentUtilization eu) {
		try {
			equipmentUtilizationService.updateEquipmentUtilization(eu);
		} catch(Exception e) {
			
		}
		return "redirect:/productionandoperationmanagement/equipmentutilizationgrid";
	}

	
	@GetMapping("/fetchequipmentmasterdetails")
	@ResponseBody
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return equipmentUtilizationService.getEquipmentMasterDetails(equipmentmasteruid);
	}
}
