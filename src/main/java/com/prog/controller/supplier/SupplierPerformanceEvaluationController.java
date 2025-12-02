package com.prog.controller.supplier;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Employee;
import com.prog.model.erp.SupplierPerformanceEvaluation;
import com.prog.model.erp.SupplierRegistrationForm;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;
import com.prog.service.supplier.SupplierPerformanceEvaluationService;


@Controller 
public class SupplierPerformanceEvaluationController {

    @Autowired
    private SupplierPerformanceEvaluationService supplierPerformanceEvaluationService;
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RawMaterialSupplierRegistrationService supplierservice;
    
    @GetMapping("/supplier/supplierperformanceevaluationgrid")
    public String showsupplierperformanceevaluationgrid() {
    	return "SUPPLIERgrid/SupplierPerformanceEvaluationGrid";
    }

//    @GetMapping("/showsupplierperformanceevaluationform")
//    public String showSupplierForm(Model model) {
//        model.addAttribute("supplier", new SupplierPerformanceEvaluation()); // Changed key to 'supplier' to match Thymeleaf
//        List<String> supplieruid = supplierservice.getallsupplieruidInPreformanceEvaluation();
//        model.addAttribute("supplieruid", supplieruid);
//        
//        List<String> employeeuid = employeeService.getAlleDetails(); 
//        model.addAttribute("employeeuid", employeeuid);
//        
//        return "supplier/SupplierPerformanceEvaluationForm"; 
//    }
    
    @GetMapping("/showsupplierperformanceevaluationform")
    public String showSupplierForm(Model model) {
        model.addAttribute("supplier", new SupplierPerformanceEvaluation());

        // Supplier UID list
        List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
        model.addAttribute("rawmaterialsupplieruid", rawmaterialsupplieruid);

        // Employees list
        List<Employee> employees = employeeService.getAllEmployees(); // DB मधून सर्व employees
        model.addAttribute("employees", employees); // Thymeleaf template ला पाठवा

        
        return "supplier/SupplierPerformanceEvaluationForm";
    }


    // Save new evaluation
    @PostMapping("/supplierperformanceevaluation/save")
    public String submitEvaluation(@ModelAttribute("supplier") SupplierPerformanceEvaluation supplierPerformanceEvaluation, RedirectAttributes redirectAttributes) {
        supplierPerformanceEvaluationService.saveSupplier(supplierPerformanceEvaluation);
        redirectAttributes.addFlashAttribute("message", "SupplierPerformanceEvaluation saved successfully!");
        return "redirect:/supplier/supplierperformanceevaluationgrid";
    }

    
    
 // Display list of all Supplier Performance Evaluation
    @GetMapping("/viewallsupplierperformanceevaluationlist")
	@ResponseBody
	public Map<String, Object> showlistofcustomerreturn() {
	    List<String> headers = List.of(
	        "ID", "Performance Evaluation ID","Supplier ID", "Supplier Name",
	        "Evaluation Period","Performance Status", "Action Required","Reviewed By");
	    
        List<Map<String, Object>> supplierperformanceevaluationInfoList = supplierPerformanceEvaluationService.showFindAll();
	    List<Map<String, String>> supplierperformanceevaluationList = new ArrayList<>();
	    
	    for (Map<String, Object> supplierperformanceevaluationInfo : supplierperformanceevaluationInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(supplierperformanceevaluationInfo.get("id")));
	        row.put("Performance Evaluation ID", String.valueOf(supplierperformanceevaluationInfo.get("supplierperformanceevaluationuid")));
	        row.put("Supplier ID", String.valueOf(supplierperformanceevaluationInfo.get("rawmaterialsupplieruid")));
	        row.put("Supplier Name", String.valueOf(supplierperformanceevaluationInfo.get("suppliername")));
	        row.put("Evaluation Period", String.valueOf(supplierperformanceevaluationInfo.get("evaluationperiod")));
	        row.put("Performance Status", String.valueOf(supplierperformanceevaluationInfo.get("performancestatus")));
	        row.put("Action Required", String.valueOf(supplierperformanceevaluationInfo.get("actionrequired")));
	        row.put("Reviewed By", String.valueOf(supplierperformanceevaluationInfo.get("reviewedby")));
	        supplierperformanceevaluationList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", supplierperformanceevaluationList);
	    response.put("status", "success");
	    
	    return response;
	}
	



    @GetMapping("/supplierperformanceevaluation/edit/{id}")
    public String editSupplierPerformance(@PathVariable("id") Long id, Model model) {
        SupplierPerformanceEvaluation supplier = supplierPerformanceEvaluationService.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/SupplierPerformanceEvaluationForm"; // ✅ Should match the Thymeleaf HTML file name
    }



    @PostMapping("/supplierperformanceevaluation/update")
    public String updatesupplierperformance(@ModelAttribute SupplierPerformanceEvaluation supplierPerformanceEvaluation,
                                            RedirectAttributes redirectAttributes) {
        supplierPerformanceEvaluationService.updatesupplierperformance(supplierPerformanceEvaluation);
        redirectAttributes.addFlashAttribute("message", "Supplier Performance updated successfully!");
        return"redirect:/supplier/supplierperformanceevaluationgrid";
    }

    

    @GetMapping("/deletesupplierperformanceevaluationinfo")
    public String deleteSupplierPerformance(@RequestParam("id") Long id) {
        supplierPerformanceEvaluationService.deleteById(id);  
        return "redirect:/supplier/supplierperformanceevaluationgrid";  
    }
    
    
    //FETCH
    @GetMapping("/supplier/fetchSupplierDetails")
    @ResponseBody
    public List<Map<String, Object>> getSupplierDetails(@RequestParam("rawmaterialsupplieruid") String rawmaterialsupplieruid) {
        return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
    }
    @GetMapping("/supplier/fetchReviewerName")
    @ResponseBody
    public String getReviewerName(Principal principal) {
        return principal.getName();
    }
}
