package com.prog.controller.purchase;

import com.prog.model.erp.Employee;
import com.prog.model.erp.RawmaterialInfo;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.purchase.CompetitiveProcurementRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller for handling Competitive Procurement Requests (CPR).
 * Provides endpoints for CRUD operations and AJAX support (suppliers, RFQs, employees, etc.).
 */
@Controller
public class CompetitiveProcurementRequestController {

    @Autowired
    private CompetitiveProcurementRequestService service;
    @Autowired
    private EmployeeService empservice;

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @GetMapping("/competitiveprocurementrequestgrid")
//    public String viewCompetitiveProcurementRequestGrid(Model model) {
//        // add any model attributes
//        return "PURCHASEgrid/CompetitiveProcurementRequestGrid";
//    }
//    
    @GetMapping("/purchase/competitiveprocurementrequestgrid")
    public String showpasagrid() {
    	return "PURCHASEgrid/CompetitiveProcurementRequestGrid";
    }
    
    
    
    @GetMapping("/showcompetitiveprocurementrequestform")
    public String competitiveRequestPage(Model model) {
    	// EMPUID and Name dropdown
        List<Employee> employeeList = empservice.getBasicEmployeeDetails();
        Map<String, String> approvedbyMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            approvedbyMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("approvedbyMap", approvedbyMap);

        return "purchase/CompetitiveProcurementRequestFrom"; // Thymeleaf template
    }

    @PostMapping("/competitive-procurement/save")
    public String saveCompetitiveProcurementRequest(@RequestBody Map<String, Object> data, Model model) {
        service.saveCompetitiveProcurementRequest(data);
        model.addAttribute("message", "Saved successfully!");
        model.addAttribute("requests", service.getAllWithLinks());
        return "redirect:/purchase/competitiveprocurementrequestgrid";
    }
    
    
    // Display list 
    @GetMapping("/viewallcompetitiveprocurementrequestinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID","CPR ID","Raw Material Name", "RFQ ID", "Supplier Details","Request Date","Approved By");
	    
		List<Map<String,Object>> competitiveprocurementInfoList  = service.getAllWithLinks();
	    List<Map<String, String>> competitiveprocurementList = new ArrayList<>();
	    
	    for (Map<String, Object> competitiveprocurementInfo : competitiveprocurementInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(competitiveprocurementInfo.get("id")));
	        row.put("CPR ID", String.valueOf(competitiveprocurementInfo.get("competitiveprocurementrequestuid")));
	        row.put("Raw Material Name", String.valueOf(competitiveprocurementInfo.get("rawmaterialname")));
	        row.put("RFQ ID", String.valueOf(competitiveprocurementInfo.get("requestforquotationuid")));
	        row.put("Supplier Details", String.valueOf(competitiveprocurementInfo.get("supplierdetails")));
	        row.put("Request Date", String.valueOf(competitiveprocurementInfo.get("requestdate")));
	        row.put("Approved By", String.valueOf(competitiveprocurementInfo.get("approvedby")));
	        competitiveprocurementList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", competitiveprocurementList);
	    response.put("status", "success");
	    
	    return response;
	}

   
    @GetMapping("/competitive-procurement/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Map<String, Object> request = service.getById(id);
        model.addAttribute("request", request);
        
        List<Employee> employeeList = empservice.getBasicEmployeeDetails();
        Map<String, String> approvedbyMap = new LinkedHashMap<>();
        for (Employee emp : employeeList) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            approvedbyMap.put(uid, (firstName + " " + lastName).trim());
        }
        model.addAttribute("approvedbyMap", approvedbyMap);

        
        return "purchase/CompetitiveProcurementRequestFrom"; // <-- your existing Thymeleaf form
    }

   
    @PostMapping("/competitive-procurement/update")
    public String updateCompetitiveProcurementRequest(@RequestBody Map<String, Object> data, Model model) {
        Long id = Long.valueOf(data.get("id").toString());
        String remark = data.get("remark") != null ? data.get("remark").toString() : null;
        String requestdate = data.get("requestdate") != null ? data.get("requestdate").toString() : null;

        int rows = service.updateRequest(data, id, remark, requestdate);
        model.addAttribute("message", rows > 0 ? "Updated successfully!" : "Request not found!");
        model.addAttribute("requests", service.getAllWithLinks());
        return "redirect:/purchase/competitiveprocurementrequestgrid";
    }
    
    
    
    @GetMapping("/deletecompetitive-procurementinfo")
    public String deleteRequest(@RequestParam("id") Long id, Model model) {
        service.deleteById(id);
       return "redirect:/purchase/competitiveprocurementrequestgrid";
    }
    
    
   
    @GetMapping("/competitive-procurement/get/{id}")
    @ResponseBody
    public Map<String, Object> getRequestById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/competitive-procurement/update-data/{id}")
    @ResponseBody
    public Map<String, Object> getUpdateData(@PathVariable Long id) {
        return service.getById(id);
    }


   
    /**
     * Get employees for dropdown (AJAX endpoint).
     */
    
    @GetMapping("/competitive-procurement/employees")
    @ResponseBody
    public List<Map<String, Object>> getEmployees(Model nodel) {
        return service.getAllEmployees();
    }

    /**
     * Get RFQs by raw material name (AJAX endpoint).
     */
    
    @GetMapping("/competitive-procurement/rfqs-by-material")
    @ResponseBody
    public List<Map<String, Object>> getRfqsByMaterial(@RequestParam String rawMaterialName) {
        return service.getRfqsByMaterial(rawMaterialName);
    }

    /**
     * Get suppliers linked to a specific RFQ (AJAX endpoint).
     */
   
    @GetMapping("/competitive-procurement/suppliers-by-rfq")
    @ResponseBody
    public List<Map<String, Object>> getSuppliersByRfq(@RequestParam String rfqUid) {
        List<Map<String, Object>> rfqRecords = service.getRfqByUid(rfqUid);
        List<Map<String, Object>> suppliers = new ArrayList<>();

        for (Map<String, Object> rfqRecord : rfqRecords) {
            if (rfqRecord.get("supplierdetails") != null) {
                Object supplierJson = rfqRecord.get("supplierdetails");
                try {
                    // Handle JSON stored as object or array
                    if (supplierJson.toString().trim().startsWith("{")) {
                        // Supplier details as object
                        Map<String, Map<String, Object>> parsed =
                                objectMapper.readValue(supplierJson.toString(), Map.class);
                        for (Map.Entry<String, Map<String, Object>> entry : parsed.entrySet()) {
                            Map<String, Object> sup = new HashMap<>();
                            sup.put("supplierUid", entry.getKey());
                            sup.put("supplierName", entry.getValue().get("name"));
                            suppliers.add(sup);
                        }
                    } else if (supplierJson.toString().trim().startsWith("[")) {
                        // Supplier details as array
                        List<Map<String, Object>> parsed =
                                objectMapper.readValue(supplierJson.toString(), List.class);
                        suppliers.addAll(parsed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return suppliers;
    }

    // ============================================================
    // RAW MATERIAL ENDPOINT
    // ============================================================

    /**
     * Get all raw materials (AJAX endpoint).
     */
   
    @GetMapping("/competitive-procurement/api/rawmaterials/list")
    @ResponseBody
    public List<RawmaterialInfo> listAllRawMaterials() {
        return service.getAllRawMaterials();
    }

        // ---------------------------
        // Thymeleaf Page Rendering
        // ---------------------------
        @GetMapping("/list")
        public String getAllRequests(Model model) {
            List<Map<String,Object>> requests = service.getAllWithLinks();
            model.addAttribute("requests", requests);
            return "PURCHASEgrid/CompetitiveProcurementRequestGrid"; // your Thymeleaf template
        }


    
}
