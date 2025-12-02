package com.prog.controller.sales;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.CustomerReturn;
import com.prog.service.sales.CustomerReturnService;


@Controller
public class CustomerReturnController {
	
	@Autowired
    private CustomerReturnService customerReturnService;


	
	@GetMapping("/sales/customerreturngrid")
	public String showcustomerreturngrid() {
		return "SALESgrid/CustomerReturnGrid";
	}

	 @GetMapping("/showcustomerreturn")
	    public String customerReturn(Model model) {
	        model.addAttribute("CustomerReturn", new CustomerReturn());
	        List<String> salesorderuid = customerReturnService.getSalesOrderUId();
	        model.addAttribute("salesorderuid", salesorderuid);
	        List<String> productuid = customerReturnService.getProductOrderUId();
	        model.addAttribute("productuid", productuid);
	        return "/sales/CustomerReturnForm"; // Make sure this exists in templates
	    }

	    @PostMapping("/customerreturn/save")
	    public String submitCustomerReturn(@ModelAttribute CustomerReturn customerReturn, Model model) {
	        customerReturnService.addCustomerReturn(customerReturn);
	        model.addAttribute("message", "Customer return saved successfully!");
	        return "redirect:/sales/customerreturngrid"; // or redirect if needed
	    }


	   
	 // Display list of Customer Return
	    @GetMapping("/viewallcustomerreturnlist")
		@ResponseBody
		public Map<String, Object> showlistofcustomerreturn() {
		    List<String> headers = List.of(
		        "ID", "Customer Return ID", "Sales Order ID",
		        "Customer Name","Product Name","Return Date", "Return Status","Refund or Replacement");
		    
    	    List<Map<String, Object>> customerreturnInfoList = customerReturnService.getAllCustomerReturn();
		    List<Map<String, String>> customerreturnList = new ArrayList<>();
		    
		    for (Map<String, Object> customerreturnInfo : customerreturnInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(customerreturnInfo.get("id")));
		        row.put("Customer Return ID", String.valueOf(customerreturnInfo.get("customerreturnuid")));
		        row.put("Sales Order ID", String.valueOf(customerreturnInfo.get("salesorderuid")));
		        row.put("Customer Name", String.valueOf(customerreturnInfo.get("customername")));
		        row.put("Product Name", String.valueOf(customerreturnInfo.get("productname")));
		        row.put("Return Date", String.valueOf(customerreturnInfo.get("returndate")));
		        row.put("Return Status", String.valueOf(customerreturnInfo.get("returnstatus")));
		        row.put("Refund or Replacement", String.valueOf(customerreturnInfo.get("refundorreplacement")));
		        customerreturnList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", customerreturnList);
		    response.put("status", "success");
		    
		    return response;
		}
		
	    

	    @GetMapping("/customerreturn/edit/{id}")
	    public String editCustomerReturn(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
	        CustomerReturn customerReturn = customerReturnService.getCustomerReturnById(id);
	        if (customerReturn == null) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Customer Return not found!");
	            return "redirect:/customerreturn";
	        }
	        model.addAttribute("customerReturns", customerReturn);
	        return "/sales/CustomerReturnForm";
	    }


	    @PostMapping("/customerreturn/update")
	    public String updateCustomerReturn(@ModelAttribute CustomerReturn customerReturn, RedirectAttributes redirectAttributes) {
	        customerReturnService.updateCustomerReturn(customerReturn);
	        redirectAttributes.addFlashAttribute("successMessage", "Customer Return updated successfully!");
	        return "redirect:/sales/customerreturngrid";
	    }
	    
	    @GetMapping("/deletecustomerreturninfo")
	    public String deleteCustomerReturn(@RequestParam("id") Long id,RedirectAttributes redirectAttributes) {
	    	try {
	    		customerReturnService.deleteById(id);
	            redirectAttributes.addFlashAttribute("successMessage", "SupplierPerformanceEvaluation deleted successfully.");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting SupplierPerformanceEvaluation.");
	        }
	        return "redirect:/sales/customerreturngrid"; // Fixed path
	    }
	    
	    
	    //Fetching
	    @GetMapping("/sales/getsalesorderdetails")
	    @ResponseBody
	    public List<Map<String, Object>> getSalesOrderDetailsByuid(@RequestParam("salesorderuid") String salesorderuid) {
	        return customerReturnService.ggetSalesOrderDetailsByuid(salesorderuid);
	    }

	    @GetMapping("/sales/getprodetails")
	    @ResponseBody
	    public List<Map<String, Object>> getProductOrderDetailsByuid(@RequestParam("productuid") String productuid) {
	        return customerReturnService.getProductOrderDetailsByuid(productuid);
	    }

	   
	    
	   

	         
	    

	
}
