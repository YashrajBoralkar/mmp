package com.prog.controller.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.RequestforQuotation;
import com.prog.service.hrms.EmployeeService;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.inventory.RawmaterialInfoService;
import com.prog.service.purchase.RequestForQuotationService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestForQuotationController {

	@Autowired
	private RequestForQuotationService rfqService;
	@Autowired
	private RawMaterialSupplierRegistrationService supplierservice;

	@Autowired
	private RawmaterialInfoService rmservice;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/purchase/requestforquotationgrid")
	public String showRFQGrid() {
		return "PURCHASEGrid/RequestForQuotationGrid";
	}

	@GetMapping("/showrequestforquotationform")
	public String showRFQForm(Model model) {
		model.addAttribute("rfq", new RequestforQuotation());
		List<String> supplieruid = supplierservice.getallsupplieruids();
		model.addAttribute("supplieruid", supplieruid);
		List<String> rawmaterialuid = rmservice.fetchrawmaterialUIds();
		model.addAttribute("rawmaterialuid", rawmaterialuid);

		// fetch employee list
		List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
		Map<String, String> employeeMap = new LinkedHashMap<>();
		for (Employee emp : employeeList) {
			String uid = emp.getEmployeeUID().trim();
			String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
			String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
			employeeMap.put(uid, (firstName + " " + lastName).trim());
		}

		model.addAttribute("employeeMap", employeeMap);

		return "purchase/RequestForQuotationForm";
	}

	@PostMapping("/requestforquotation/save")
	public String saveRFQ(@ModelAttribute RequestforQuotation rfq,
	                      @RequestParam("supplierdetails") String supplierdetails) throws JsonProcessingException {

	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Map<String, String>> details = mapper.readValue(supplierdetails, Map.class);

	    // Loop over suppliers and enrich with name + contact
	    for (String uid : details.keySet()) {
	        Map<String, Object> supplierInfo = supplierservice.getSupplierDetailsByUids(uid).get(0);
	        Map<String, String> supplierData = details.get(uid);
	        supplierData.put("name", supplierInfo.get("suppliername").toString());
	        supplierData.put("contact", supplierInfo.get("mobilenumber").toString());
	    }

	    // Save enriched JSON
	    rfq.setSupplierdetails(mapper.writeValueAsString(details));
	    rfqService.saveRFQ(rfq);

	    return "redirect:/purchase/requestforquotationgrid";
	}

	// Display list 
    @GetMapping("/viewallrequestforquotationinfo")
    @ResponseBody
    public Map<String, Object> showlistofgoodsreceiptnote() {
        List<String> headers = List.of(
            "ID", "RFQ ID", "Issued By", "Raw Material Name", "Supplier Details", "Expected Delivery Date"
        );

        List<Map<String, Object>> requestforquotationInfoList = rfqService.getAllRFQs();
        List<Map<String, String>> requestforquotationList = new ArrayList<>();

        // 🔥 Build employee UID → FullName map
        Map<String, String> employeeNameMap = new HashMap<>();
        for (Employee emp : employeeService.getBasicEmployeeDetails()) {
            String uid = emp.getEmployeeUID().trim();
            String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
            String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
            String fullName = (firstName + " " + lastName).trim();
            employeeNameMap.put(uid, fullName);
        }

        for (Map<String, Object> requestforquotationInfo : requestforquotationInfoList) {
            Map<String, String> row = new HashMap<>();
            row.put("ID", String.valueOf(requestforquotationInfo.get("id")));
            row.put("RFQ ID", String.valueOf(requestforquotationInfo.get("requestforquotationuid")));

            // ✅ Format Issued By as "UID - FullName"
           String issuedByUid = String.valueOf(requestforquotationInfo.get("issuedby"));
           String issuedByName = employeeNameMap.getOrDefault(issuedByUid, "");
            row.put("Issued By", issuedByUid + " - " + issuedByName);
           // row.put("Issued By", String.valueOf(requestforquotationInfo.get("issuedby")));
            row.put("Raw Material Name", String.valueOf(requestforquotationInfo.get("rawmaterialname")));
            row.put("Supplier Details", String.valueOf(requestforquotationInfo.get("supplierdetails")));
            row.put("Expected Delivery Date", String.valueOf(requestforquotationInfo.get("expecteddeliverydate")));
            requestforquotationList.add(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("headers", headers);
        response.put("data", requestforquotationList);
        response.put("status", "success");

        return response;
    }

	@GetMapping("/requestforquotation/update/{id}")
	public String editRFQ(@PathVariable("id") Long id, Model model) {
		RequestforQuotation quotation = rfqService.getRFQById(id);
		model.addAttribute("rfq", quotation);

		// Supplier list
		List<String> supplieruid = supplierservice.getallsupplieruids();
		model.addAttribute("supplieruid", supplieruid);

		// Raw material list
		List<String> rawmaterialuid = rmservice.fetchrawmaterialUIds();
		model.addAttribute("rawmaterialuid", rawmaterialuid);

		// Employee list
		List<Employee> employeeList = employeeService.getBasicEmployeeDetails();
		Map<String, String> employeeMap = new LinkedHashMap<>();
		for (Employee emp : employeeList) {
			String uid = emp.getEmployeeUID().trim();
			String firstName = emp.getFirstName() != null ? emp.getFirstName().trim() : "";
			String lastName = emp.getLastName() != null ? emp.getLastName().trim() : "";
			employeeMap.put(uid, (firstName + " " + lastName).trim());
		}
		model.addAttribute("employeeMap", employeeMap);

		return "purchase/RequestForQuotationForm";
	}

	@PostMapping("/requestforquotation/update")
	public String updateRFQ(@ModelAttribute RequestforQuotation rfq,
	                        @RequestParam("supplierdetails") String supplierdetails) throws JsonProcessingException {

	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Map<String, String>> details = mapper.readValue(supplierdetails, Map.class);

	    // Enrich supplier JSON with name + contact
	    for (String uid : details.keySet()) {
	        Map<String, Object> supplierInfo = supplierservice.getSupplierDetailsByUids(uid).get(0);
	        Map<String, String> supplierData = details.get(uid);
	        supplierData.put("name", supplierInfo.get("suppliername").toString());
	        supplierData.put("contact", supplierInfo.get("mobilenumber").toString());
	    }

	    rfq.setSupplierdetails(mapper.writeValueAsString(details));
	    rfqService.updateRFQ(rfq);

	    return "redirect:/purchase/requestforquotationgrid";
	}


	@GetMapping("/deleterequestforquotationinfo")
	public String deleteForm(@RequestParam("id") Long id) {
		rfqService.deleteRFQ(id);
		return "redirect:/purchase/requestforquotationgrid";
	}

	// FETCHING
	@GetMapping("/requestforquotation/getsupplieruidinfo")
	@ResponseBody
	public List<Map<String, Object>> getSupplierUidInfo(
			@RequestParam("rawmaterialsupplieruid") String rawmaterialsupplieruid) {
		if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
			throw new IllegalArgumentException("supplieruid must not be null or empty.");
		}
		return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
	}

	@GetMapping("/requestforquotation/getrawmaterialdetails")
	@ResponseBody
	public List<Map<String, Object>> getProductDetails(@RequestParam("rawmaterialuid") String rawmaterialuid) {

		return rmservice.getDataByrawmaterialUid(rawmaterialuid);
	}

	@GetMapping("/requestforquotation/withsuppliers/{rawmaterialuid}")
	@ResponseBody
	public List<Map<String, Object>> getRawMaterialWithSuppliers(@PathVariable String rawmaterialuid) {
		System.out.println("suplier info in getRawMaterialWithSuppliers : " + rmservice.getRawMaterialWithSuppliers(rawmaterialuid) + ",uid : " + rawmaterialuid);
		return rmservice.getRawMaterialWithSuppliers(rawmaterialuid);
	}

}
