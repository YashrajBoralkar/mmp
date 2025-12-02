package com.prog.controller.qualitycontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.prog.model.erp.Firstarticleinspection;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.qualitycontrol.FirstArticleInspectionService;

@Controller
public class FirstArticleInspectionController {

    @Autowired
    private FirstArticleInspectionService faiService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/qualitycontrol/firstarticleinspectiongrid")
    public String showFirstArticleInspection() {
    	return "QUALITYCONTROLgrid/FirstArticleInspectionGrid";
    }

    @GetMapping("/showfirstarticleinspection")
    public String showFirstArticleInspectionForm(Model model) {
        model.addAttribute("fai", new Firstarticleinspection());

        // Dropdowns
        List<String> productuid = faiService.getAllProductUIDs();
        List<String> planninguid = faiService.getAllPlanningUIDs();
        List<String> workorderuid = faiService.getAllWorkOrderUids();
        List<String> productionorderuid = faiService.getAllProductionOrderUIDs();

        model.addAttribute("productuids", productuid);
        model.addAttribute("planninguids", planninguid);
        model.addAttribute("workorderuids", workorderuid);
        model.addAttribute("productionorderuids", productionorderuid);

        // Inspector dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> inspectorMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            inspectorMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("inspectorMap", inspectorMap);

        return "qualitycontrol/FirstArticleInspectionForm";
    }

    @GetMapping("/fai/fetch/product-details")
    @ResponseBody
    public Map<String, Object> fetchProductDetails(@RequestParam("productuid") String productuid) {
        Map<String, Object> response = new HashMap<>();
        response.put("productname", faiService.getProductNameByUID(productuid));
        response.put("productionplanninguids", faiService.getProductionPlanningUIDs(productuid));
        return response;
    }
    @GetMapping("/fai/fetch/workorderuids")
    @ResponseBody
    public List<String> fetchWorkOrderUIDs(@RequestParam("productionplanninguid") String planninguid) {
        return faiService.getWorkOrderUIDs(planninguid);
    }
    @GetMapping("/fai/fetch/productionorderuids")
    @ResponseBody
    public List<String> fetchProductionOrderUIDs(@RequestParam("workorderuid") String workorderuid) {
        return faiService.getProductionOrderUIDs(workorderuid);
    }
    @GetMapping("/fai/fetch/plannedquantity")
    @ResponseBody
    public Map<String, Object> fetchPlannedQuantity(@RequestParam("productionorderuid") String productionorderuid) {
        Map<String, Object> response = new HashMap<>();
        response.put("plannedquantity", faiService.getPlannedQuantity(productionorderuid));
        return response;
    }
    
    @PostMapping("/firstarticleinspection/save")
    public String saveFirstArticleInspection(@ModelAttribute Firstarticleinspection firstarticleinspection, Model model) {
        faiService.saveFai(firstarticleinspection);
        model.addAttribute("message", "First Article Inspection record saved successfully");
        model.addAttribute("successmsg", "Saved successfully");
        return "redirect:/qualitycontrol/firstarticleinspectiongrid"; // Redirect back to the form
    }

    
    // Display list
    @GetMapping("/viewallfirstarticleinspectioninfo")
	@ResponseBody
	public Map<String, Object> showlistoffirstarticleinspection() {
	    List<String> headers = List.of(
	        "ID", "First Article Inspection ID","Product Name", "Total Inspected Units","Defective Units","Defect Count",
	        "Inspector Name","Approval Status");
	    
    	List<Map<String, Object>> firstarticleinspectionInfoList = faiService.getAllFirstarticleinspection();
	    List<Map<String, String>> firstarticleinspectionList = new ArrayList<>();
	    
	    for (Map<String, Object> firstarticleinspectionInfo : firstarticleinspectionInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(firstarticleinspectionInfo.get("id")));
	        row.put("First Article Inspection ID", String.valueOf(firstarticleinspectionInfo.get("firstarticleinspectionuid")));
	        row.put("Product Name", String.valueOf(firstarticleinspectionInfo.get("productname")));
	        row.put("Total Inspected Units", String.valueOf(firstarticleinspectionInfo.get("totalinspectedunits")));
	        row.put("Defective Units", String.valueOf(firstarticleinspectionInfo.get("defectiveunits")));
	        row.put("Defect Count", String.valueOf(firstarticleinspectionInfo.get("defectcount")));
	        row.put("Inspector Name", String.valueOf(firstarticleinspectionInfo.get("inspectorname")));
	        row.put("Approval Status", String.valueOf(firstarticleinspectionInfo.get("approvalstatus")));
	        firstarticleinspectionList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", firstarticleinspectionList);
	    response.put("status", "success");
	    
	    return response;
	}
   

    @GetMapping("/deletefirstarticleinspectioninfo")
    public String deleteFirstArticleInspection(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            faiService.deleteFai(id);
            redirectAttributes.addFlashAttribute("successMessage", "First Article Inspection record deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete record.");
        }
        return "redirect:/qualitycontrol/firstarticleinspectiongrid";
    }

    @GetMapping("/firstarticleinspection/update/{id}")
    public String editFirstArticleInspection(@PathVariable("id") Long id, Model model) {
        // Fetch the existing FAI record by ID
        Firstarticleinspection firstarticleinspection = faiService.getFaiById(id);
        model.addAttribute("fai", firstarticleinspection);

        // Prepare inspector dropdown (UID -> Full Name)
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> inspectorMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            inspectorMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("inspectorMap", inspectorMap);

        return "qualitycontrol/FirstArticleInspectionForm"; // Same form for both create and update
    }



    @PostMapping("/firstarticleinspection/update")
    public String updateFirstArticleInspection(
            @ModelAttribute Firstarticleinspection firstarticleinspection,
            RedirectAttributes redirectAttributes) {

        try {
            // Update the record using service
            faiService.updateFai(firstarticleinspection);
            redirectAttributes.addFlashAttribute("successMessage", "First Article Inspection record updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating the record.");
        }

        return "redirect:/qualitycontrol/firstarticleinspectiongrid";
    }


    
    
 //FETCHING 
    
    @GetMapping("/firstarticleinspection/batch/faibatchdetails")
    @ResponseBody
    public List<Map<String, Object>> getstockDeatils(@RequestParam("batchuid")String batchuid){
    	return faiService.getbatchDetailsbyid(batchuid);
    }
}