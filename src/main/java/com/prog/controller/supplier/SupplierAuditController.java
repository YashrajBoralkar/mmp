package com.prog.controller.supplier;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.model.erp.Employee;
import com.prog.model.erp.SupplierAudit;
import com.prog.model.erp.SupplierRegistrationForm;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;
import com.prog.service.supplier.SupplierAuditService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SupplierAuditController {

    @Autowired
    private SupplierAuditService supplierAuditService;
    @Autowired
    private EmployeeService employeeService;   
    @Autowired
    private RawMaterialSupplierRegistrationService supplierregistrationservice;
    
    
    @GetMapping("/supplier/supplierauditgrid")
    public String showauditgrid() {
    	return "SUPPLIERgrid/SupplierAuditGrid";
    }
    

    @GetMapping("/showsupplierauditform")
    public String showForm( Model model) {
            List<String> rawmaterialsupplieruid =supplierregistrationservice.getallsupplieruids();
            model.addAttribute("rawmaterialsupplieruid",rawmaterialsupplieruid); 
            
         // EMPUID and Name dropdown
            List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
            Map<String, String> auditornameMap = new LinkedHashMap<>();
            for (Employee emp : employeeList) {
                String uid = emp.getEmployeeUID().trim();
                String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
                String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
                auditornameMap.put(uid, (firstName + " " + lastName).trim());
            }
            model.addAttribute("auditornameMap", auditornameMap);
            
            return "supplier/SupplierAuditForm";
    }

    @PostMapping("/supplierauditform/save")
    public String saveform(@ModelAttribute SupplierAudit audit) {
        supplierAuditService.saveSupplierAudit(audit);
        return "redirect:/supplier/supplierauditgrid";
    }

   
  
 // Display list of all Supplier Audits
    @GetMapping("/viewallSupplierAuditslist")
	@ResponseBody
	public Map<String, Object> showlistofcustomerreturn() {
	    List<String> headers = List.of(
	        "ID", "Audit ID","RM Supplier ID", "Supplier Name",
	        "Audit Date","Auditor Name", "Audit Outcome");
	    
        List<Map<String, Object>> supplierauditInfoList = supplierAuditService.getAuditWithSupplierDetails();
	    List<Map<String, String>> supplierauditList = new ArrayList<>();
	    
	    for (Map<String, Object> PoAcknowledgmenInfo : supplierauditInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(PoAcknowledgmenInfo.get("id")));
	        row.put("Audit ID", String.valueOf(PoAcknowledgmenInfo.get("supplieraudituid")));
	        row.put("RM Supplier ID", String.valueOf(PoAcknowledgmenInfo.get("rawmaterialsupplieruid")));
	        row.put("Supplier Name", String.valueOf(PoAcknowledgmenInfo.get("suppliername")));
	        row.put("Audit Date", String.valueOf(PoAcknowledgmenInfo.get("auditdate")));
	        row.put("Auditor Name", String.valueOf(PoAcknowledgmenInfo.get("auditorname")));
	        row.put("Audit Outcome", String.valueOf(PoAcknowledgmenInfo.get("auditoutcome")));
	        supplierauditList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", supplierauditList);
	    response.put("status", "success");
	    
	    return response;
	}
	

   @GetMapping("/editsupplierauditform/{id}")
   public String updateform(@PathVariable("id") Long id, Model model) {
    SupplierAudit audit = supplierAuditService.getSupplierAuditById(id);
       model.addAttribute("audit", audit);
      return "supplier/SupplierAuditForm"; 
   }
   
   @PostMapping("/supplierauditform/update")
   public String updateAudit(@ModelAttribute SupplierAudit audit) {
      supplierAuditService.updateSupplierAudit(audit);  
       return "redirect:/supplier/supplierauditgrid";
   }
 
     @GetMapping("/deletesupplierauditinfo")
     public String deleteAuditform(@RequestParam("id") Long id) {
         supplierAuditService.deleteAuditById(id);
         return "redirect:/supplier/supplierauditgrid";
     }
     	
     

 //FETCHING
     @GetMapping("/supplieraudit/fetchSupplierDetails")
     @ResponseBody
     public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("rawmaterialsupplieruid") String rawmaterialsupplieruid) {
         if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
             throw new IllegalArgumentException("supplieruid must not be null or empty.");
         }
         return supplierregistrationservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
     }
     
 }
