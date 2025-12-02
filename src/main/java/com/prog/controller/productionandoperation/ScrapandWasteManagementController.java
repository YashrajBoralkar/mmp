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

import com.prog.model.erp.Productinfo;
import com.prog.model.erp.ScrapandWasteManagement;
import com.prog.service.productionandoperation.ScrapandWasteManagementService;

@Controller
public class ScrapandWasteManagementController {
	
	@Autowired
	private ScrapandWasteManagementService scrapandWasteManagementService;
	
	@GetMapping("/productionandoperationmanagement/scrapandwastemanagementgrid")
	public String viewGrid() {
		return "ProductionAndOperationManagementgrid/ScrapandWasteManagementFormGrid";
	}
	
	@GetMapping("/scrapandwastemanagementform")
	public String showScrapAndWasteManagementForm(Model model) {
		List<String> Productuid = scrapandWasteManagementService.getProductUID();
		model.addAttribute("scrapandwaste", new ScrapandWasteManagement());
		model.addAttribute("Productuid", Productuid);
		
		return "ProductionAndOperationManagement/ScrapandWasteManagementForm";
	}
	
	@PostMapping("/savescrapandwastemanagementform")
	public String addScrapandWasteManagement(@ModelAttribute ScrapandWasteManagement swm, Model model) {
		scrapandWasteManagementService.addScrapandWasteManagement(swm);
		model.addAttribute("message", "Scrap and Waste Management data saved successfully!");
	    model.addAttribute("successMessage", "Scrap and Waste Management has been saved successfully!");
			
	    return "redirect:/productionandoperationmanagement/scrapandwastemanagementgrid";			
	
	}
	
	@GetMapping("/viewscrapandwastemanagementlist")
	@ResponseBody
	public Map<String,Object> ViewAllScrapAndWasteManagementData(Model model) {
		List<String> headers = List.of("ID","Scrap & Waste Management ID","Product ID","Product Name","Scrap Type",
				"Quantity Scrapped","Recycling Method","Waste Disposal Company","Environmental Impact");
		
		List<Map<String, Object>> scrapandwastemanagementinfolist=scrapandWasteManagementService.findAllScrapandWasteManagementData();
		
		List<Map<String,String>> scrapandwastemanagementlist = new ArrayList<>();
		
		for(Map<String, Object> scrapandwastemanagementinfo: scrapandwastemanagementinfolist) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(scrapandwastemanagementinfo.get("id")));
			row.put("Scrap & Waste Management ID", String.valueOf(scrapandwastemanagementinfo.get("scrapuid")));
			row.put("Product ID", String.valueOf(scrapandwastemanagementinfo.get("productuid")));
			row.put("Product Name", String.valueOf(scrapandwastemanagementinfo.get("productname")));
			row.put("Scrap Type", String.valueOf(scrapandwastemanagementinfo.get("scraptype")));
			row.put("Quantity Scrapped", String.valueOf(scrapandwastemanagementinfo.get("quantityscrapped")));
			row.put("Recycling Method", String.valueOf(scrapandwastemanagementinfo.get("recyclingmethod")));
			row.put("Waste Disposal Company", String.valueOf(scrapandwastemanagementinfo.get("wastedisposalcampany")));
			row.put("Environmental Impact", String.valueOf(scrapandwastemanagementinfo.get("environmentalimpactassessment")));
			scrapandwastemanagementlist.add(row);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", scrapandwastemanagementlist);
		response.put("status", "success");
		return response;
		
	}
	
	@GetMapping("/scrapandwastemanagementdeleteinfo")
	public String deleteScrapandWasteManagementById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		try {
            System.out.println("Deleting Production Planning with ID: " + id); // DEBUG
            scrapandWasteManagementService.deleteScrapandWasteManagementById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Scrap and Waste Management entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // DEBUG
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Scrap and Waste Management.");
        }
		return "redirect:/productionandoperationmanagement/scrapandwastemanagementgrid";
	}
	
	@GetMapping("/updatescrapandwastemanagement/{id}")
	public String getScrapandWasteManagementById(@PathVariable("id") Long id, Model model) {
		ScrapandWasteManagement swm = scrapandWasteManagementService.getScrapandWasteManagementById(id);
		model.addAttribute("updateswm", swm);
		return "ProductionAndOperationManagement/ScrapandWasteManagementForm";
	}
	
	@PostMapping("/updatescrapandwastemanagementdata")
	public String updatecrapandWasteManagement(@ModelAttribute ScrapandWasteManagement swm) {
		try {
			scrapandWasteManagementService.updatecrapandWasteManagement(swm);
		} catch( Exception e) {
			
		}
		return "redirect:/productionandoperationmanagement/scrapandwastemanagementgrid";
	}
	
	// Product name fetch by product name
	@GetMapping("/getproductinfodetails")
	@ResponseBody
	public Productinfo getProductDetails(String productuid) {
		return scrapandWasteManagementService.getProductDetails(productuid);
	}
}
