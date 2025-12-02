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

import com.prog.model.erp.MaterialRequirementPlanning;
import com.prog.service.productionandoperation.MaterialRequirementPlanningService;


@Controller
public class MaterialRequirementPlanningController {
	@Autowired
	private MaterialRequirementPlanningService materialRequirementPlanningService;
	
	@GetMapping("/productionandoperationmanagement/materialrequirementplanninggrid")
	public String viewgrid() {
		return "ProductionAndOperationManagementgrid/MaterialRequirementPlanninggrid";
	}
	
	@GetMapping("/materialrequirementplanningform")
	public String showForm(Model model) {
		model.addAttribute("mrp",new MaterialRequirementPlanning());
		List<String> productionplanninguid=materialRequirementPlanningService.getProductionPlanningUID();
		model.addAttribute("productionplanninguid",productionplanninguid);
		return "ProductionAndOperationManagement/MaterialRequirementPlanning";
	}
	
	
	@PostMapping("/savematerialrequirementplanning")
	public String addMRPData(@ModelAttribute MaterialRequirementPlanning mrp, Model model) {
		materialRequirementPlanningService.addMRPData(mrp);
		model.addAttribute("message", "Data saved successfully!");
		return "redirect:/productionandoperationmanagement/materialrequirementplanninggrid";
	}
	@GetMapping("/viewmaterialrequirementplanninglist")
	@ResponseBody
	public Map<String,Object> getMRPViewList(Model model) {
		List<String> headers = List.of("ID","Material Requirement Planning ID", "Production Planning ID", "Product ID", "Required Quantity",
				"Available Stock", "Recorded Level", "Supplier Name", "Supplier Lead Time", "Supplier Lead Date", "Proc Request Status");
		
		List<Map<String, Object>> materialrequirementplanninginfolist = materialRequirementPlanningService.getAllMRPFData();

		List<Map<String, String>> materialrequirementplanninglist = new ArrayList<>();
		
		for (Map<String, Object> materialrequirementplanninginfo : materialrequirementplanninginfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID",String.valueOf(materialrequirementplanninginfo.get("id")));
			row.put("Material Requirement Planning ID", String.valueOf(materialrequirementplanninginfo.get("materialrequirementplanninguid")));
			row.put("Production Planning ID",String.valueOf(materialrequirementplanninginfo.get("productionplanninguid")));
			row.put("Product ID", String.valueOf(materialrequirementplanninginfo.get("productuid")));
			row.put("Required Quantity", String.valueOf(materialrequirementplanninginfo.get("requiredquantity")));
			row.put("Available Stock", String.valueOf(materialrequirementplanninginfo.get("availablestock")));
			row.put("Recorded Level", String.valueOf(materialrequirementplanninginfo.get("recorderlevel")));
			row.put("Supplier Name", String.valueOf(materialrequirementplanninginfo.get("suppliername")));
			row.put("Supplier Lead Time", String.valueOf(materialrequirementplanninginfo.get("supplierleadtime")));
			row.put("Supplier Lead Date", String.valueOf(materialrequirementplanninginfo.get("supplierleaddate")));
			row.put("Proc Request Status", String.valueOf(materialrequirementplanninginfo.get("procrequeststatus")));
			materialrequirementplanninglist.add(row);

		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", materialrequirementplanninglist);
		response.put("status", "success");
		
		return response;
	}
	
	@GetMapping("/materialrequirementplanningdeleteinfo")
	public String deleteMrpDataById(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
		 try {
	            System.out.println("Deleting Work Order with ID: " + id); // DEBUG
	            materialRequirementPlanningService.deleteMrpDataById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "Material Requirement Planning entry deleted successfully!");
	        } catch (Exception e) {
	            e.printStackTrace(); // DEBUG
	            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Material Requirement Planning.");
	        }
		return "redirect:/productionandoperationmanagement/materialrequirementplanninggrid";
	}
	
	@GetMapping("/updatematerialrequirementplanning/{id}")
	public String updateMRPFById(@PathVariable("id") Long id,Model model) {
		MaterialRequirementPlanning mrp=materialRequirementPlanningService.getMRPDataById(id);
		model.addAttribute("mrp", mrp);
		return "ProductionAndOperationManagement/MaterialRequirementPlanning";
	}
	
	@PostMapping("/updatematerialrequirementplanning")
	public String updateMRPFData(@ModelAttribute MaterialRequirementPlanning mrp) {
		try {
			materialRequirementPlanningService.updateMRPFData(mrp);
		} catch(Exception e) {
			
		}
		return "redirect:/productionandoperationmanagement/materialrequirementplanninggrid";
	}
	
	@GetMapping("/getproductionplanningdetailsbyid")
	@ResponseBody
	public Map<String, Object> getProductDetalisByProductionPlanningUID(@RequestParam("productionplanninguid") String productionplanninguid) {
	    List<Map<String, Object>> list = materialRequirementPlanningService.getProductDetalisByProductionPlanningUID(productionplanninguid);
	    return list.isEmpty() ? new HashMap<>() : list.get(0);
	}

}
