package com.prog.controller.qualitycontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.Employee;
import com.prog.model.erp.RawmaterialQualityinspection;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.qualitycontrol.RawmaterialQualityinspectionService;


@Controller
public class RawmaterialQualityinspectionController {

    @Autowired
    private RawmaterialQualityinspectionService rawmaterialQualityinspectionService;
   @Autowired
   private EmployeeService employeeService;

    
    @GetMapping("/qualitycontrol/rawmaterialqualityinspectiongrid")
	 public String showrawmaterialqualityinspectiongrid() {
		 return "QUALITYCONTROLgrid/RawMaterialQualityInspectionGrid";
	 }
    
    
    // Load form
    @GetMapping("/rawmaterialqualityinspection")
    public String showForm(Model model) {
        model.addAttribute("inspection", new RawmaterialQualityinspection());

        // Load only PO UIDs (distinct)
        List<String> poUids = rawmaterialQualityinspectionService.getAllPurchaseOrderUids();
        model.addAttribute("rawmaterialpurchaseorderuid", poUids);

     // EMPUID and Name dropdown
        List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
        Map<String, String> checkedbyMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            checkedbyMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("checkedbyMap", checkedbyMap);

        return "qualitycontrol/RawMaterialQualityInspectionForm";
    }

    // Save new inspection
    @PostMapping("/rawmaterialqualityinspection/save")
    public String saveInspection(@ModelAttribute("inspection") RawmaterialQualityinspection inspection,
                                 RedirectAttributes redirectAttributes) {
        rawmaterialQualityinspectionService.addInspection(inspection);
        redirectAttributes.addFlashAttribute("message", "Inspection saved successfully!");
        return "redirect:/qualitycontrol/rawmaterialqualityinspectiongrid";
    }

   
    
    // Display list 
    @GetMapping("/viewallrmqualityinspectioninfo")
	@ResponseBody
	public Map<String, Object> showlistofviewallrmdeliverychallan() {
	    List<String> headers = List.of(
	        "ID","RM Quality Inspection ID","RM Purchase Order ID","Raw Material Details", "Inspection Date", "Inspection Result");
	    
        List<Map<String, Object>> rmqualityinspectionsInfoList = rawmaterialQualityinspectionService.getAllInspections();
	    List<Map<String, String>> rmqualityinspectionsList = new ArrayList<>();
	    
	    for (Map<String, Object> rmqualityinspectionsInfo : rmqualityinspectionsInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(rmqualityinspectionsInfo.get("id")));
	        row.put("RM Quality Inspection ID", String.valueOf(rmqualityinspectionsInfo.get("rawmaterialqualityinspectionuid")));
	        row.put("RM Purchase Order ID", String.valueOf(rmqualityinspectionsInfo.get("rawmaterialpurchaseorderuid")));
	        row.put("Raw Material Details", String.valueOf(rmqualityinspectionsInfo.get("rawmaterialdetails")));
	        row.put("Inspection Date", String.valueOf(rmqualityinspectionsInfo.get("inspectiondate")));
	        row.put("Inspection Result", String.valueOf(rmqualityinspectionsInfo.get("inspectionresult")));
	        rmqualityinspectionsList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", rmqualityinspectionsList);
	    response.put("status", "success");
	    
	    return response;
	}
   

    // Delete inspection
    @GetMapping("/deleterawmaterialqualityinspectioninfo")
    public String deleteInspection(@RequestParam("id") Long id) {
        rawmaterialQualityinspectionService.deleteById(id);
        return "redirect:/qualitycontrol/rawmaterialqualityinspectiongrid";
    }

    // Load form for edit
    @GetMapping("/rawmaterialqualityinspection/edit/{id}")
    public String editInspection(@PathVariable("id") Long id, Model model) {
        RawmaterialQualityinspection inspectionList = rawmaterialQualityinspectionService.getById(id);
        model.addAttribute("inspectionList", inspectionList);

        // Also load PO UIDs and employee names for dropdowns
        List<String> poUids = rawmaterialQualityinspectionService.getAllPurchaseOrderUids();
        model.addAttribute("rawmaterialpurchaseorderuid", poUids);
        model.addAttribute("employeeList", rawmaterialQualityinspectionService.getEmployeeNames());

        return "qualitycontrol/RawMaterialQualityInspectionForm";
    }

    // Update inspection
    @PostMapping("/rawmaterialqualityinspection/update")
    public String updateInspection(@ModelAttribute RawmaterialQualityinspection inspection) {
        rawmaterialQualityinspectionService.update(inspection);
        return "redirect:/qualitycontrol/rawmaterialqualityinspectiongrid";
    }

    
    // AJAX: Fetch employee names
    @GetMapping("/getEmployeeNamelist")
    @ResponseBody
    public List<Map<String, String>> getEmployeeNames() {
        return rawmaterialQualityinspectionService.getEmployeeNames();
    }

    
    @GetMapping("/fetchRawMaterialsByPoUid")
    @ResponseBody
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUid(
            @RequestParam("rawmaterialpurchaseorderuid") String rawmaterialpurchaseorderuid) {
        return rawmaterialQualityinspectionService.getRawMaterialsByPurchaseOrderUid(rawmaterialpurchaseorderuid);
    }

}
