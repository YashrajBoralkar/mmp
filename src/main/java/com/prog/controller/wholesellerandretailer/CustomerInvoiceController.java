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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.customerinvoice;
import com.prog.service.wholesellerandretailer.CustomerInvoiceService;
import com.prog.service.wholesellerandretailer.CustomerOrderService;

@Controller
public class CustomerInvoiceController {

	@Autowired
	private CustomerInvoiceService customerinvoiceservice;
	@Autowired
	private CustomerOrderService customerorderservice;
	
	@GetMapping("/wholesellerandretailer/customerinvoicegrid")
	public String showgrid(){
		return "WHOLESELLERANDRETAILERgrid/CustomerInvoiceGrid";
	}

	 @GetMapping("/showcustomerinvoice")
	    public String showForm(Model model){
	        model.addAttribute("customerinvoice", new customerinvoice());

	        List<String>customerorderuid = customerorderservice.getCustomerOrderUId();
	        		   
		   model.addAttribute("customerorderuid", customerorderuid);
	        return "wholesellerandretailer/CustomerInvoiceForm";
	    }
	
	 
	 @PostMapping("/customerinvoice/save")
	    public String  saveinvoice(@ModelAttribute customerinvoice customerinvoice,RedirectAttributes redirectAttributes ) {
		 customerinvoiceservice.saveinvoice(customerinvoice);
		 redirectAttributes.addAttribute("message", "Retail Inovice saved successfully");
		 redirectAttributes.addAttribute("successmsg", "Saved successfully");
	  	return "redirect:/wholesellerandretailer/customerinvoicegrid";
	    }
	 
	// Display list 
	    @GetMapping("/viewallcustomerinvoiceinfo")
		@ResponseBody
		public Map<String, Object> showlistofpurchasedandsalesagreement() {
		    List<String> headers = List.of(
		        "ID","Customer Invoice ID","Customer Order ID","Customer ID","Company Name","Product Name", "Invoice Date","Payment Mode");
		    
			List<Map<String,Object>>retailinvoiceInfoList = customerinvoiceservice.getAllCustomerInvoice();
		    List<Map<String, String>> retailinvoiceList = new ArrayList<>();
		    
		    for (Map<String, Object> retailinvoiceInfo : retailinvoiceInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(retailinvoiceInfo.get("id")));
		        row.put("Customer Invoice ID", String.valueOf(retailinvoiceInfo.get("customerinvoiceuid")));
		        row.put("Customer Order ID", String.valueOf(retailinvoiceInfo.get("customerorderuid")));
		        row.put("Customer ID", String.valueOf(retailinvoiceInfo.get("customeruid")));
		        row.put("Company Name", String.valueOf(retailinvoiceInfo.get("companyname")));
		        row.put("Product Name", String.valueOf(retailinvoiceInfo.get("productname")));
		        row.put("Invoice Date", String.valueOf(retailinvoiceInfo.get("invoicedate")));
		        row.put("Payment Mode", String.valueOf(retailinvoiceInfo.get("paymentmode")));

		        retailinvoiceList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", retailinvoiceList);
		    response.put("status", "success");
		    
		    return response;
		}

	 
	 @GetMapping("/customerinvoice/update/{id}")
	   public String editinvoice(@PathVariable("id") Long id, Model model) {
		 customerinvoice customerinvoice = customerinvoiceservice.getRetailInvoicebyid(id);
	       model.addAttribute("customerinvoice", customerinvoice);
	       return "wholesellerandretailer/CustomerInvoiceForm";
	   
	   }
	   
	   @PostMapping("/customerinvoice/update")
	   public String updateinvoice(@ModelAttribute customerinvoice customerinvoice,RedirectAttributes redirectAttributes){
		   customerinvoiceservice.updateCustomerInvoice(customerinvoice);
		   return "redirect:/wholesellerandretailer/customerinvoicegrid";
	   }
	   
	   @GetMapping("/deletecustomerinvoiceinfo")
		  public String deleteinvoice(@RequestParam("id") Long id) {
		   customerinvoiceservice.deleteinvoice(id);  
		      return "redirect:/wholesellerandretailer/customerinvoicegrid";  
		  }
	   
	 //FETCHING
	   @GetMapping("/customerinvoice/getretsilorderdetails")
		@ResponseBody
		public List<Map<String, Object>> getCustomerOrderDetailsByuid(@RequestParam("customerorderuid")String customerorderuid){
			return customerorderservice.getCustomerOrderDetailsByuid(customerorderuid);
		}
	   
//------------------------------------------------------------------------Customer Order Table Data---------------------------------------------------------------------------------------------------------
	   
	   
	// 1️⃣ Get Customer UIDs by Type
	   @GetMapping("/getCustomerUidsByType")
	   @ResponseBody
	   public List<String> getCustomerUidsByType(@RequestParam("customertype") String type) {
	       return customerinvoiceservice.getCustomerUidsByType(type);
	   }

	   // 2️⃣ Get Company Name and Orders for Customer UID
	   @GetMapping("/getCustomerDetails")
	   @ResponseBody
	   public Map<String, Object> getCustomerDetails(@RequestParam("customeruid") String customeruid) {
	       Map<String, Object> response = new HashMap<>();
	       response.put("companyname", customerinvoiceservice.getCompanyName(customeruid));
	       response.put("orders", customerinvoiceservice.getOrdersByCustomerUid(customeruid));
	       return response;
	   }

	   // 3️⃣ Get Product details for selected order
	   @GetMapping("/getOrderDetails")
	   @ResponseBody
	   public List<Map<String, Object>> getOrderDetails(@RequestParam("customerorderuid") String orderuid) {
	       return customerinvoiceservice.getOrderDetailsByUid(orderuid);
	   }
}
