package com.prog.controller.qualitycontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Nonconformancereport;
import com.prog.service.inventory.PhysicalCountService;
import com.prog.service.qualitycontrol.NonConformanceReportService;

import org.springframework.ui.Model;


@Controller
public class NonConformanceReportController {
	
	


    @Autowired
    private NonConformanceReportService nonconformancereportService;
    
    
    @GetMapping("/qualitycontrol/nonconformancereportgrid")
    public String shownonconformancereportform() {
    	return "QUALITYCONTROLgrid/NonConformanceReportGrid";
    }


    // Open form for new NCR
    @GetMapping("/shownonconformancereport")
    public String showNcrPage(Model model) {
    	
    	List<String> productuids = nonconformancereportService.fetchProductUIds();
    	
        model.addAttribute("productuids", productuids);
        model.addAttribute("nonconformancereport", new Nonconformancereport());

        return "qualitycontrol/NonConformanceReportForm";  
    }

    // Save new NCR
    @PostMapping("/nonconformancereport/save")
    public String submitNCR(@ModelAttribute Nonconformancereport nonconformancereport, RedirectAttributes redirectAttributes) {
        nonconformancereportService.saveNcr(nonconformancereport);
        redirectAttributes.addFlashAttribute("message", "NCR report saved successfully!");
        return "redirect:/qualitycontrol/nonconformancereportgrid";
    }

    // Display list
    @GetMapping("/viewallnonconformancereportinfo")
	@ResponseBody
	public Map<String, Object> showNcrList() {
	    List<String> headers = List.of(
	        "ID", "Non Conformance Report ID","Department Name","Product Name", "Inspection Date","Action Taken","Supervisor Approval Name","Approval Status");
	    
        List<Map<String, Object>> nonconformancereportInfoList = nonconformancereportService.showAllRecords();
        List<Map<String, String>> nonconformancereportList = new ArrayList<>();
	    
	    for (Map<String, Object> nonconformancereportInfo : nonconformancereportInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(nonconformancereportInfo.get("id")));
	        row.put("Non Conformance Report ID", String.valueOf(nonconformancereportInfo.get("nonconformancereportuid")));
	        row.put("Department Name", String.valueOf(nonconformancereportInfo.get("department")));
	        row.put("Product Name", String.valueOf(nonconformancereportInfo.get("productname")));
	        row.put("Inspection Date", String.valueOf(nonconformancereportInfo.get("inspectiondate")));
	        row.put("Action Suggested", String.valueOf(nonconformancereportInfo.get("preventiveactionsuggested")));
	        row.put("Supervisor Approval Name", String.valueOf(nonconformancereportInfo.get("suppervisorapproval")));
	        row.put("Approval Status", String.valueOf(nonconformancereportInfo.get("approvalstatus")));
	        nonconformancereportList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", nonconformancereportList);
	    response.put("status", "success");
	    
	    return response;
	}
   

    
    // Edit NCR
//    @GetMapping("/nonconformancereport/edit/{id}")
//    public String editNcr(@PathVariable("id") Long id, Model model) {
//        Nonconformancereport ncr = nonconformancereportService.getNcrByid(id);
//
//        // Fetch product UIDs for dropdown
//        List<String> productuids = nonconformancereportService.fetchProductUIds();
//        // Fetch batch UIDs for dropdown
//        List<String> batchuids = nonconformancereportService.getBatchId();
//
//        model.addAttribute("ncr", ncr);
//        model.addAttribute("productuids", productuids);
//        model.addAttribute("batchuid", batchuids);
//
//        return "qualitycontrol/NonConformanceReportForm";  
//    }
    
    @GetMapping("/nonconformancereport/edit/{id}")
    public String editNcr(@PathVariable("id") Long id, Model model) {
        Nonconformancereport ncr = nonconformancereportService.getNcrByid(id);

        // Fetch product UIDs for dropdown (optional if you want readonly)
        List<String> productuids = nonconformancereportService.fetchProductUIds();
        // Fetch batch UIDs for dropdown

        // Use the same attribute name as in create form
        model.addAttribute("nonconformancereport", ncr);
        model.addAttribute("productuids", productuids);

        return "qualitycontrol/NonConformanceReportForm";  
    }



    // Update NCR
    @PostMapping("/nonconformancereport/update")
    public String updateNcr(@ModelAttribute("nonconformancereport") Nonconformancereport nonconformancereport, RedirectAttributes redirectAttributes) {
        nonconformancereportService.updateNcr(nonconformancereport);
        redirectAttributes.addFlashAttribute("message", "NCR updated successfully!");
        return "redirect:/qualitycontrol/nonconformancereportgrid";
    }

    
 // Delete NCR
    @GetMapping("/deletenonconformacereportinfo")
    public String deletenonconformacereport(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            nonconformancereportService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "NCR deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting NCR.");
        }
        return "redirect:/qualitycontrol/nonconformancereportgrid";
    }
    
    
//FETCHING    
 // AJAX call to get product details by UID
    @GetMapping("/nonconformancereport/product/getdetails")
    @ResponseBody
    public List<Map<String, Object>> getProductDetails(@RequestParam("productuid") String productuid) {
        return nonconformancereportService.getProductDetailsByuid(productuid);
    }

}
