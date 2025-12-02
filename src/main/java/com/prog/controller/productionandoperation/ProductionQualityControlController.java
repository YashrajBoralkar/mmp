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
import com.prog.model.erp.ProductionQualityControl;
import com.prog.service.productionandoperation.ProductionQualityControlService;

@Controller
public class ProductionQualityControlController {
	@Autowired
	private ProductionQualityControlService productionQualityControlService;
	
	@GetMapping("/productionandoperationmanagement/productionqualitycontrolgrid")
	public String viewGrid() {
		return "ProductionAndOperationManagementgrid/ProductionQualityControlGrid";
	}
	
	@GetMapping("/productionqualitycontrolform")
	public String showForm(Model model) {
		model.addAttribute("pqc",new MaterialRequirementPlanning());
		List<String> workorderuid=productionQualityControlService.getWorkOrderUID();
		model.addAttribute("workorderuid",workorderuid);
		return "ProductionAndOperationManagement/ProductionQualityControlForm";
	}
	@PostMapping("/saveproductionqualitycontrol")
	public String saveproductionqualitycontroldata(@ModelAttribute ProductionQualityControl qpc, Model model) {
		productionQualityControlService.saveProductionQualityControl(qpc);
		 model.addAttribute("message", "Production Planning data saved successfully!");
        model.addAttribute("successMessage", "Production Planning has been saved successfully!");
		return "redirect:/productionandoperationmanagement/productionqualitycontrolgrid";
	}
	
	@GetMapping("/viewproductionqualitycontrollist")
	@ResponseBody
	public Map<String, Object> viewAllProductionQualityControl(Model model) {
	    List<String> headers = List.of(
	        "ID", "Production Quality Control ID", "Work Order UID",
	        "Product UID", "Product Name", "Inspector Name", "Test Performed",
	        "Measurement Standard", "Defect Classification", "Inspection Status",
	        "Rework Required", "Root Cause Analysis"
	    );

	    List<Map<String, Object>> productionqualitycontrolinfolist =
	        productionQualityControlService.showProductionQualityControlList();

	    List<Map<String, Object>> productionqualitycontrollist = new ArrayList<>();

	    for (Map<String, Object> productionqualitycontrolinfo : productionqualitycontrolinfolist) {
	        Map<String, Object> row = new HashMap<>();
	        row.put("ID", productionqualitycontrolinfo.get("id"));
	        row.put("Production Quality Control ID", productionqualitycontrolinfo.get("productionqualitycontroluid"));
	        row.put("Work Order UID", productionqualitycontrolinfo.get("workorderuid"));
	        row.put("Product UID", productionqualitycontrolinfo.get("productuid"));
	        row.put("Product Name", productionqualitycontrolinfo.get("productname"));
	        row.put("Inspector Name", productionqualitycontrolinfo.get("inspectorname"));
	        row.put("Test Performed", productionqualitycontrolinfo.get("testperformed"));
	        row.put("Measurement Standard", productionqualitycontrolinfo.get("measurementstandard"));
	        row.put("Defect Classification", productionqualitycontrolinfo.get("defectclassification"));
	        row.put("Inspection Status", productionqualitycontrolinfo.get("inspectionstatus"));
	        row.put("Rework Required", productionqualitycontrolinfo.get("reworkrequire"));
	        row.put("Root Cause Analysis", productionqualitycontrolinfo.get("rootcauseanalysisreport"));
	        productionqualitycontrollist.add(row);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", productionqualitycontrollist);
	    response.put("status", "success");

	    return response;
	}

	@GetMapping("/productionqualitycontroldeleteinfo")
	 public String deleteProductionQualityControlById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		try {
            System.out.println("Deleting Work Order with ID: " + id); // DEBUG
            productionQualityControlService.deleteProductionQualityControlById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Production Quality Control entry deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // DEBUG
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Production Quality Control.");
        }
        return "redirect:/productionandoperationmanagement/productionqualitycontrolgrid";  
        } 
	
	@GetMapping("/updateproductionqualitycontrol/{id}")
	public String updatProductionQualityControlByID(@PathVariable("id") Long id, Model model) {
		ProductionQualityControl pqc=productionQualityControlService.getProductionQualityControlById(id);
		model.addAttribute("updatepqc", pqc);
		return "ProductionAndOperationManagement/ProductionQualityControlForm";
		
	}
	
	@PostMapping("/updateproductionqualitycontrol")
	public String updateProductionQualityControl(@ModelAttribute ProductionQualityControl pqc) {
		try {
			productionQualityControlService.updatProductionQualityControl(pqc);
		} catch(Exception e) {
			
		}
		return "redirect:/productionandoperationmanagement/productionqualitycontrolgrid";
	}
	@GetMapping("/getworkorderdetailsbyid")
	@ResponseBody
	public Map<String, Object> getProductDetalisByWorkOrderUID(@RequestParam("workorderuid") String workorderuid) {
	    List<Map<String, Object>> list = productionQualityControlService.getProductDetalisByWorkOrderUID(workorderuid);
	    return list.isEmpty() ? new HashMap<>() : list.get(0);
	}
}
