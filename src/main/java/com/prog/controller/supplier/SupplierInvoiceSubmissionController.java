package com.prog.controller.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prog.model.erp.PurchaseOrder;
import com.prog.model.erp.SupplierInvoiceSubmission;
import com.prog.model.erp.SupplierRegistrationForm;
import com.prog.service.inventory.PurchaseOrderService;
import com.prog.service.supplier.SupplierInvoiceSubmissionService;




@Controller
public class SupplierInvoiceSubmissionController {
	
	@Autowired
	private SupplierInvoiceSubmissionService supplierInvoiceSubmissionService; 

	
	
	@GetMapping("/supplier/supplierinvoicesubmissiongrid")
	public String showsupplierregistrationgrid(){
		return ("SUPPLIERgrid/SupplierInvoiceSubmissionGrid");
	}
	
	@GetMapping("/showsupplierinvoicesubmission")
	public String fetchInvoiceSubmission(Model model) {
	    model.addAttribute("invoice", new SupplierInvoiceSubmission());
		model.addAttribute("approvalAuthorities", supplierInvoiceSubmissionService.getApprovalAuthorities());

	    List<String> rawmaterialuids = supplierInvoiceSubmissionService.getAllPurchaseOrderDetailsDataINSupplier();
	    model.addAttribute("rawmaterialuids", rawmaterialuids);
	    System.out.println("Fetched PO UIDs: " + rawmaterialuids);

	    return "supplier/SupplierInvoiceSubmissionForm";
	}


	

	
	@PostMapping("/supplierinvoicesubmission/save")
	public String addInvoiceSubmission(SupplierInvoiceSubmission sis, Model model) {
	    // Ensure the correct "totalamount" is set in the object to be saved
	  
		model.addAttribute("approvalAuthorities", supplierInvoiceSubmissionService.getApprovalAuthorities());

	    supplierInvoiceSubmissionService.addSupplierInvoiceSubmission(sis);
	    model.addAttribute("successMessage", "Saved Successfully Supplier Invoice Submission Form");
	    return "redirect:/supplier/supplierinvoicesubmissiongrid";
	}

	
	// Display list of Supplier Invoice Submission Form
	@GetMapping("/viewallsupplierinvoicesubmissionlist")
	@ResponseBody
	public Map<String, Object> showlistofcustomerreturn() {
	    List<String> headers = List.of(
	        "ID", "Supplier Invoice ID", "Invoice Date",
	        "Supplier Name", "Total Amount", "Invoice Approval Status");

	    // Fetch data only from rawmaterialpurchaseorder and related data
	    List<Map<String, Object>> SupplierInvoiceSubmissionInfoList = supplierInvoiceSubmissionService.fetchSupplierInvoiceSubmission();
	    List<Map<String, String>> supplierinvoicesubmissionList = new ArrayList<>();

	    for (Map<String, Object> supplierinvoicesubmissionInfo : SupplierInvoiceSubmissionInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(supplierinvoicesubmissionInfo.get("id")));
	        row.put("Supplier Invoice ID", String.valueOf(supplierinvoicesubmissionInfo.get("supplierinvoiceuid")));
	        row.put("Invoice Date", String.valueOf(supplierinvoicesubmissionInfo.get("invoicedate")));
	        row.put("Supplier Name", String.valueOf(supplierinvoicesubmissionInfo.get("suppliername")));
	        row.put("Total Amount", String.valueOf(supplierinvoicesubmissionInfo.get("totalvalue"))); // fetch from rawmaterialpurchaseorder
	        row.put("Invoice Approval Status", String.valueOf(supplierinvoicesubmissionInfo.get("invoiceapprovalstatus")));
	        supplierinvoicesubmissionList.add(row);
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", supplierinvoicesubmissionList);
	    response.put("status", "success");

	    return response;
	}

	
	
	@GetMapping("/supplierinvoicesubmission/update/{id}")
	public String updateInvoiceSubmission(@PathVariable("id") Long id, Model model) {
	    SupplierInvoiceSubmission sis = supplierInvoiceSubmissionService.getSupplierInvoiceSubmissionById(id);
	    model.addAttribute("invoice", sis);

	    // Fetch all Purchase Order details for the dropdown
	    List<String> rawmaterialuids = supplierInvoiceSubmissionService.getAllPurchaseOrderDetailsDataINSupplier();
	    model.addAttribute("rawmaterialuids", rawmaterialuids);
	    
		model.addAttribute("approvalAuthorities", supplierInvoiceSubmissionService.getApprovalAuthorities());

	    return "supplier/SupplierInvoiceSubmissionForm";
	}

	@PostMapping("/supplierinvoicesubmission/update")
	public String updateInvoiceSubmission(@ModelAttribute SupplierInvoiceSubmission sis) {
	    // Update the record with the new data
	    supplierInvoiceSubmissionService.updateSupplierInvoiceSubmission(sis);
	    return "redirect:/supplier/supplierinvoicesubmissiongrid";
	}

	
	
	@GetMapping("/deletesupplierinvoicesubmissioninfo")
	public String deleteSupplierInvoiceSubmissionById(@RequestParam("id") Long id) {
		supplierInvoiceSubmissionService.deleteSupplierInvoiceSubmissionById(id);
		return "redirect:/supplier/supplierinvoicesubmissiongrid";
	}
	
	
	//FETCHING FROM SUPPLIER REGISTER AND PURCHASE ORDER
		
//	@GetMapping("/supplierinvoicesubmission/supplierdetails")
//	@ResponseBody
//	public SupplierRegistrationForm getSupplierRegistrationDataById(@RequestParam("supplieruid") String supplieruid) {
//		return supplierRegistrationService.getSupplierRegistrationDataById(supplieruid);
//	}
//	
		
	
	
	@GetMapping("/supplierinvoicesubmission/rawmaterialpodetails")
	@ResponseBody
	public ResponseEntity<?> getRawMaterialPODetails(@RequestParam String uid) {
	    Map<String, Object> data = supplierInvoiceSubmissionService.getPODataByUid(uid);

	    // Check if data exists
	    if (data != null && !data.isEmpty()) {
	        return ResponseEntity.ok(data);
	    } else {
	        // Return an error message if no data found
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(Map.of("error", "No data found for selected Purchase Order UID."));
	    }
	}



	
}
