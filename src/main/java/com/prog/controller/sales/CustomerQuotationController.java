package com.prog.controller.sales;

import org.springframework.stereotype.Controller;

import com.prog.model.erp.customerquotation;
import com.prog.service.sales.CustomerQuotationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class CustomerQuotationController {
	
	@Autowired
    private CustomerQuotationService customerQuotationService;

    
	@GetMapping("/sales/customerquotationgrid")
	public String showcustomerquotationgrid() {
		return "SALESgrid/CustomerQuotationGrid";
	}
	
	// Show Customer Quotation form
    @GetMapping("/customerquotationform")
    public String showCustomerQuotationForm(Model model) {
        List<String> customeruids = customerQuotationService.fetchCustomerUIds();
        model.addAttribute("customeruids", customeruids);
        
        List<String> productuid = customerQuotationService.fetchProductUIds();
        model.addAttribute("productuid", productuid);
        
        return "sales/CustomerQuotationForm"; 
    }

     //Submit Customer Quotation form
    @PostMapping("/customerquotationform/save")
    public String submitCustomerQuotation(@ModelAttribute customerquotation customerquotation) {
        customerQuotationService.saveCustomerQuotation(customerquotation);
        return "redirect:/sales/customerquotationgrid";
    }

    // Display list of Customer Quotations
    @GetMapping("/viewallcustomerquotationlist")
	@ResponseBody
	public Map<String, Object> showlistofsales() {
	    List<String> headers = List.of(
	        "ID", "Customer Quotation ID", "Customer Name",
	        "Product Name","Quotation Date","Quotation Amount", "Total Amount", "Quotation Status"
	    );
	    
        List<Map<String, Object>> customerquotationInfoList = customerQuotationService.getAllCustomerQuotations();
	    List<Map<String, String>> customerQuotationList = new ArrayList<>();
	    
	    for (Map<String, Object> customerquotationInfo : customerquotationInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(customerquotationInfo.get("id")));
	        row.put("Customer Quotation ID", String.valueOf(customerquotationInfo.get("customerquotationuid")));
	        row.put("Customer Name", String.valueOf(customerquotationInfo.get("customername")));
	        row.put("Product Name", String.valueOf(customerquotationInfo.get("productname")));
	        row.put("Quotation Date", String.valueOf(customerquotationInfo.get("quotationdate")));
	        row.put("Quotation Amount", String.valueOf(customerquotationInfo.get("quotationamount")));
	        row.put("Total Amount", String.valueOf(customerquotationInfo.get("totalamount")));
	        row.put("Quotation Status", String.valueOf(customerquotationInfo.get("quotationstatus")));
	        customerQuotationList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", customerQuotationList);
	    response.put("status", "success");
	    
	    return response;
	}
	

    // Edit Customer Quotation
    @GetMapping("/editcustomerquotation/{id}")
    public String editCustomerQuotation(@PathVariable Long id, Model model) {
        customerquotation quotation = customerQuotationService.getCustomerQuotationById(id);
        model.addAttribute("quotation", quotation);
        return "sales/CustomerQuotationForm";
    }

    // Update Customer Quotation
    @PostMapping("/customerquotation/update")
    public String updateCustomerQuotation(@ModelAttribute("customerQuotation") customerquotation customerquotation) {
        customerQuotationService.updateCustomerQuotation(customerquotation);
        return "redirect:/sales/customerquotationgrid";
    }

    // Delete Customer Quotation
    @GetMapping("/deletecustomerquotationinfo")
    public String deleteCustomerQuotation(@RequestParam("id") Long id) {
        customerQuotationService.deleteCustomerQuotation(id);
        return "redirect:/sales/customerquotationgrid";
    }

    // Get product details based on product UID
    @GetMapping("/getquotationproductdetails")
    @ResponseBody
    public List<Map<String, Object>> getProductInfo(@RequestParam("productuid") String productuid) {
        if (productuid == null || productuid.isEmpty()) {
            throw new IllegalArgumentException("Product UID must not be null or empty.");
        }
        return customerQuotationService.getDataByProductUid(productuid);
    }

    // Get customer details based on customer UID
    @GetMapping("/getcustomerdetails")
    @ResponseBody
    public List<Map<String, Object>> getCustomerInfo(@RequestParam("customeruid") String customeruid) {
        if (customeruid == null || customeruid.isEmpty()) {
            throw new IllegalArgumentException("Customer UID must not be null or empty.");
        }
        return customerQuotationService.getDataByCustomerUid(customeruid);
    }
    
    
    @PostMapping("/calculateTotalAmount")
    public ResponseEntity<Map<String, Double>> calculateTotalAmount(
            @RequestParam double quotationAmount, 
            @RequestParam double discountPercentage) {

        double discountAmount = (quotationAmount * discountPercentage) / 100;
        double totalAmount = quotationAmount - discountAmount;

        if (totalAmount < 0) {
            totalAmount = 0;
        }

        Map<String, Double> response = new HashMap<>();
        response.put("discountAmount", discountAmount);
        response.put("totalAmount", totalAmount);

        return ResponseEntity.ok(response);
    }




	

}
