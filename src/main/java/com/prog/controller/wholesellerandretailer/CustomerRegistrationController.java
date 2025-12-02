package com.prog.controller.wholesellerandretailer;

import java.io.IOException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prog.model.erp.CustomerRegistration;
import com.prog.service.wholesellerandretailer.CustomerRegistrationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerRegistrationController {
	@Autowired
	private CustomerRegistrationService wrService;
	
	
	@GetMapping("/customermanagement/sellerregistrationgrid")
	public String showcustomerregistrationgrid(){
		return "WHOLESELLERANDRETAILERgrid/CustomerRegistrationGrid";
	}
	
	@GetMapping("/showcustomerregistrationform")
	public String getsrform(Model model) {
		model.addAttribute("wr", new CustomerRegistration());
		return "wholesellerandretailer/CustomerRegistrationForm";
	}

	@PostMapping("/customerregistration/save")
	@ResponseBody
	public Map<String, Object> addRegistration(@ModelAttribute CustomerRegistration wrData ,@RequestParam("file") MultipartFile file) throws IOException{
		
		if (!file.isEmpty()) {
            wrData.setCustomerdoc(file.getBytes()); // Save the binary file
        }
		wrService.addRegistration(wrData);

		Map<String, Object> response = new HashMap<>();
		response.put("status", "Customer Registration Saved successfully!...");
		return response;
	}

	// Display list 
    @GetMapping("/viewallcustomerregistrationinfo")
	@ResponseBody
	public Map<String, Object> showlistofcustomerregistration() {
	    List<String> headers = List.of(
	        "ID","Customer ID","Customer Type","Company Name","Contact Person", "Phone Number");
	    
		List<Map<String, Object>> sellerRegistrationInfoList = wrService.getAllCustomers();
	    List<Map<String, String>> sellerRegistrationList = new ArrayList<>();
	    
	    for (Map<String, Object> sellerRegistrationInfo : sellerRegistrationInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(sellerRegistrationInfo.get("id")));
	        row.put("Customer ID", String.valueOf(sellerRegistrationInfo.get("customeruid")));
	        row.put("Customer Type", String.valueOf(sellerRegistrationInfo.get("customertype")));

	        row.put("Company Name", String.valueOf(sellerRegistrationInfo.get("companyname")));
	        row.put("Contact Person", String.valueOf(sellerRegistrationInfo.get("contactperson")));
	        row.put("Phone Number", String.valueOf(sellerRegistrationInfo.get("phonenumber")));
	        sellerRegistrationList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", sellerRegistrationList);
	    response.put("status", "success");
	    
	    return response;
	}

	@GetMapping("/customerregistration/edit/{id}")
	public String getWrDataById(@PathVariable Long id, Model model) {
		CustomerRegistration wrData = wrService.getCustomerById(id);
		model.addAttribute("wr", wrData); 
		return "wholesellerandretailer/CustomerRegistrationForm";
	}

	@PostMapping("/customerregistration/update")
	@ResponseBody
	public Map<String, Object> updatewrData(@ModelAttribute CustomerRegistration wrData,
	                                        @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

	    // Step 1: Existing record fetch करा
	    CustomerRegistration existing = wrService.getCustomerById(wrData.getId());

	    if (existing == null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "Error: Seller not found!");
	        return response;
	    }

	    // Step 2: Frontend मधून आलेले values set करा
	    existing.setCompanyname(wrData.getCompanyname());
	    existing.setContactperson(wrData.getContactperson());
	    existing.setPhonenumber(wrData.getPhonenumber());
	    existing.setEmail(wrData.getEmail());
	    existing.setAddress(wrData.getAddress());
	    existing.setGstnumber(wrData.getGstnumber());
	    existing.setPannumber(wrData.getPannumber());
	    existing.setBankaccountnum(wrData.getBankaccountnum());
	    existing.setAccholder(wrData.getAccholder());
	    existing.setIfsccode(wrData.getIfsccode());

	    // Step 3: File update केल्यास set करा
	    if (file != null && !file.isEmpty()) {
	        existing.setCustomerdoc(file.getBytes());
	    }

	    // Step 4: Save updated record
	    wrService.updateCustomerData(existing);

	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "Customer Registration Updated successfully!...");
	    return response;
	}


	@GetMapping("/deletecustomerregistrationinfo")
	public String deletewrData(@RequestParam("id") Long id) {
		wrService.deleteCustomerData(id);
		return "redirect:/customermanagement/sellerregistrationgrid";
	}
	
	@GetMapping("/customerregistration/file/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> getSellerFile(@PathVariable Long id) {
	    CustomerRegistration wrData = wrService.getCustomerById(id);
	    byte[] fileData = wrData.getCustomerdoc();

	    if (fileData == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok()
	            .header("Content-Disposition", "inline; filename=document.pdf")
	            .header("Content-Type", "application/pdf")  
	            .body(fileData);
	}

}
