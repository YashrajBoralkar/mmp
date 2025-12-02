package com.prog.controller.wholesellerandretailer;

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

import com.prog.service.wholesellerandretailer.CustomerInvoiceService;
import com.prog.service.wholesellerandretailer.CustomerpaymentcollectionService;
import com.prog.model.erp.customerpaymentcollection;

@Controller
public class CustomerpaymentcollectionController {

	
	 @Autowired
	 private CustomerpaymentcollectionService customerpaymentcollectionService;
	 @Autowired
	 private CustomerInvoiceService customerinvoiceservice;
	 
	 @GetMapping("/wholesellerandretailer/customerpaymentcollectiongrid")
		public String showgrid(){
			return "WHOLESELLERANDRETAILERgrid/CustomerPaymentCollectionGrid";
		}
	 
	// Show Retailer Payment Collection form
	    @GetMapping("/showcustomerpaymentcollectionform")
	    public String showCustomerpaymentcollection(Model model) {
	        // Fetch all retail invoice UIDs to populate the dropdown in the form
	        List<String> retailinvoiceuid = customerinvoiceservice.fetchretailinvoiceUIds();
	        model.addAttribute("retailinvoiceuid", retailinvoiceuid);
	        List<String> customerinvoiceuid = customerinvoiceservice.fetchCustomerInvoiceUIds();
	        model.addAttribute("customerinvoiceuid", customerinvoiceuid);

	        
	        return "wholesellerandretailer/CustomerPaymentCollectionForm"; // Return the form view
    
	    }
	    
	    
	 // Handle submission of Retailer Payment Collection form
	    @PostMapping("/customerpaymentcollection/save")
	    public String submitCustomerpaymentcollection(@ModelAttribute customerpaymentcollection customerpaymentcollection) {
	        // Save the submitted payment collection details using the service
	    	customerpaymentcollectionService.saveCustomerpaymentcollection(customerpaymentcollection);
	        return "redirect:/wholesellerandretailer/customerpaymentcollectiongrid";    // Redirect to the list view after successful save

	    }
	    
	    
	 // Display list 
	    @GetMapping("/viewallcustomerpaymentcollectioninfo")
		@ResponseBody
		public Map<String, Object> showlistofpurchasedandsalesagreement() {
		    List<String> headers = List.of(
		        "ID","Customer Invoice ID","Customer ID","Payment Date","Amount Paid", "Payment Mode");
		    
	    	List<Map<String, Object>> customerpaymentcollectionInfoList = customerpaymentcollectionService.getAllCustomerPaymentCollection();
		    List<Map<String, String>> customerpaymentcollectionList = new ArrayList<>();
		    
		    for (Map<String, Object> customerpaymentcollectionInfo : customerpaymentcollectionInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(customerpaymentcollectionInfo.get("id")));
		        row.put("Customer Invoice ID", String.valueOf(customerpaymentcollectionInfo.get("customerinvoiceuid")));
		        row.put("Customer ID", String.valueOf(customerpaymentcollectionInfo.get("customeruid")));
		        row.put("Payment Date", String.valueOf(customerpaymentcollectionInfo.get("paymentdate")));
		        row.put("Amount Paid", String.valueOf(customerpaymentcollectionInfo.get("amountpaid")));
		        row.put("Payment Mode", String.valueOf(customerpaymentcollectionInfo.get("paymentmode")));
		        customerpaymentcollectionList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", customerpaymentcollectionList);
		    response.put("status", "success");
		    
		    return response;
		}
	    
	 // Load the Customer Payment Collection form with existing data for editing
	    @GetMapping("/customerpaymentcollection/edit/{id}")
	    public String editRetailerpaymentcollectionList(@PathVariable Long id, Model model) {
	    	customerpaymentcollection payments = customerpaymentcollectionService.getCustomerPaymentCollectionById(id);
	        model.addAttribute("payments", payments);// Pre-fill form with existing data
	        return "wholesellerandretailer/CustomerPaymentCollectionForm";// Return form view
	    }


	 // Handle update of Customer Payment Collection data
	    @PostMapping("/customerpaymentcollection/update")
	    public String updateCustomerPaymentCollection(@ModelAttribute("order") customerpaymentcollection payments) {
	    	customerpaymentcollectionService.updateCustomerPaymentCollection(payments);// Save updated data
	        return "redirect:/wholesellerandretailer/customerpaymentcollectiongrid"; // Redirect to list after update

	    }	    
	    
	 // Handle deletion of a Retailer Payment Collection by ID
	    @GetMapping("/deletecustomerpaymentcollectioninfo")
	    public String deleteCustomerPaymentCollection(@RequestParam("id") Long id) {
	        // Call service to delete the retailer payment record with the given ID
	    	customerpaymentcollectionService.deleteCustomerPaymentCollection(id);
	        return "redirect:/wholesellerandretailer/retailerpaymentcollectiongrid";    // Redirect to the list view after deletion

	    }
	    
	    
//FETCHING PURPOSE METHOD
	    
	 // Handle AJAX request to fetch Retailer Payment Collection details based on Retail Invoice UID
	    @GetMapping("/customerpaymentcollection/getcustomerinvoice1")
	    @ResponseBody
	    public List<Map<String, Object>> getCustomerInfo(@RequestParam("customerinvoiceuid") String customerinvoiceuid) {
	        if (customerinvoiceuid == null || customerinvoiceuid.isEmpty()) {
	            throw new IllegalArgumentException("customerinvoiceuid UID must not be null or empty.");
	        }
	        return customerinvoiceservice.getDataByretailinvoiceUid(customerinvoiceuid);
	    }
	    	    
	    
	 // Fetch customer invoice details (customeruid & invoiceamount) based on customerinvoiceuid
	    @GetMapping("/customerpaymentcollection/getcustomerinvoice")
	    @ResponseBody
	    public List<Map<String, Object>> getCustomerInvoiceDetails(@RequestParam("customerinvoiceuid") String customerinvoiceuid) {
	        if (customerinvoiceuid == null || customerinvoiceuid.isEmpty()) {
	            throw new IllegalArgumentException("Customer Invoice UID must not be null or empty.");
	        }
	        return customerinvoiceservice.getDataByCustomerInvoiceUid(customerinvoiceuid);
	    }
	    
	    
	    
}
