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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.CustomerRegistration;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.SalesOrder;
import com.prog.service.inventory.ProductInfoService;
import com.prog.service.sales.SalesOrderservice;
import com.prog.service.wholesellerandretailer.CustomerRegistrationService;

@Controller
public class SalesOrderController {

	@Autowired
	private SalesOrderservice soservice;
	@Autowired
	private CustomerRegistrationService customerservice;
	@Autowired
	private ProductInfoService productservice;

	@GetMapping("/sales/salesordergrid")
	public String showsalesordergrid() {
		return "SALESgrid/SalesOrderGrid";
	}

	// Sales Order Controller

	@GetMapping("/salesorderform")
	public String salesorder(Model model) {
		model.addAttribute("so", new SalesOrder());

		List<String> customeruid = customerservice.showcustomerdeatils();// for the customer data fetching.
		model.addAttribute("customeruid", customeruid);

		List<String> productuid = productservice.soallproductuid();// for the customer data fetching.
		model.addAttribute("productuid", productuid);

		return "sales/SalesOrderForm";

	}

//===============================================================================================================================	
	@PostMapping("/savedsalesorder")
	public String Salesordersaving(SalesOrder so, Model model) {
		soservice.savesales(so);
		model.addAttribute("savedsuccessful", "saved successfully sales order");
		return "redirect:/sales/salesordergrid";
	}
//===================================================================================================================	

	@GetMapping("/viewallsalesorderlist")
	@ResponseBody
	public Map<String, Object> showlistofsales() {
		List<String> headers = List.of("ID", "Sales Order ID", "Customer Name", "Phone Number", "Order Date",
				"Delivery Date", "Product Name", "Ordered Quantity", "Total Amount");

		List<Map<String, Object>> salesOrderInfoList = soservice.showjoinedsales();
		List<Map<String, String>> salesOrderList = new ArrayList<>();

		for (Map<String, Object> salesOrderInfo : salesOrderInfoList) {
			Map<String, String> row = new HashMap<>();
			row.put("ID", String.valueOf(salesOrderInfo.get("id")));
			row.put("Sales Order ID", String.valueOf(salesOrderInfo.get("salesorderuid")));
			row.put("Customer Name", String.valueOf(salesOrderInfo.get("companyname")));
			row.put("Phone Number", String.valueOf(salesOrderInfo.get("phonenumber")));
			row.put("Order Date", String.valueOf(salesOrderInfo.get("orderdate")));
			row.put("Delivery Date", String.valueOf(salesOrderInfo.get("deliverydate")));
			row.put("Product Name", String.valueOf(salesOrderInfo.get("productname")));
			row.put("Ordered Quantity", String.valueOf(salesOrderInfo.get("orderquantity")));
			row.put("Total Amount", String.valueOf(salesOrderInfo.get("totalamount")));
			salesOrderList.add(row);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("headers", headers);
		response.put("data", salesOrderList);
		response.put("status", "success");

		return response;
	}
//===================================================================================================================	

	@GetMapping("/deletesalesorderinfo")
	public String deletefromsales(@RequestParam("id") Long id, Model model) {
		soservice.deletsalesfromlist(id);
		return "redirect:/sales/salesordergrid";

	}
//================================================================================================================================	

	@GetMapping("/updatesalesorder/{id}")
	public String updatesales(@PathVariable Long id, Model model) {
		SalesOrder so = soservice.getsalesbyid(id);
		model.addAttribute("so", so);
		return "sales/SalesOrderForm";

	}
//===============================================================================================================================	

	@PostMapping("/salesorder/update")
	public String updatesalespost(@ModelAttribute SalesOrder so, RedirectAttributes redirectAttributes) {
		try {
			soservice.updatesalesorderform(so);
			redirectAttributes.addFlashAttribute("message", "Updated successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating.");
		}
		return "redirect:/sales/salesordergrid"; // Ensure proper redirection
	}

//===============================================================================================================================	

	@GetMapping("/sales/customer/GETdetails")
	@ResponseBody
	public CustomerRegistration showlistofcustomer(@RequestParam("customeruid") String customeruid) {
		return customerservice.getcustomerbyuid(customeruid);

	}

//===============================================================================================================================	

	@GetMapping("/sales/product/GETproducts")
	@ResponseBody
	public Productinfo showallproductuids(@RequestParam("productuid") String productuid) {
		return productservice.sogetproductdetailsbyuid(productuid);
	}
//===============================================================================================================================	

	@GetMapping("/sales/customeruid")
	@ResponseBody
	public List<String> getcustomerUIDbytype(@RequestParam("customertype") String customertype) {
		return soservice.getcustomerUIDbytype(customertype);
	}
}
