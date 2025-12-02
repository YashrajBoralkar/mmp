package com.prog.controller.sales;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prog.model.erp.SalesInvoiceForm;
import com.prog.service.sales.SalesInvoiceService;


@Controller
public class SalesInvoiceController {
	
	@Autowired
	private SalesInvoiceService salesInvoiceService;
	
	@GetMapping("/sales/salesinvoicegrid")
	public String showsalesinvoicegrid() {
		return "SALESgrid/SalesInvoiceGrid";
	}
	
       
		@GetMapping("/salesinvoiceForm")
		public String getSalesInvoiceForm(Model model) {
			
			model.addAttribute("si",new SalesInvoiceForm());
			List<String> salesorderuid=salesInvoiceService.getSalesOrderUId();
			model.addAttribute("salesorderuid",salesorderuid);
			return "sales/SalesInvoiceForm";
		}
		
		
		@PostMapping("/savesalesinvoice")
		public String getSalesInvoiceData(SalesInvoiceForm si,Model model) {
			salesInvoiceService.addSalesInvoiceData(si);
			model.addAttribute("Data Saved Succussfully","Sales Invoise data Saved Succussfully");
			return "redirect:/sales/salesinvoicegrid";
		}
		
		
		@GetMapping("/viewallsalesinvoicelist")
		@ResponseBody
		public Map<String, Object> showlistofsales() {
		    List<String> headers = List.of(
		        "ID", "Sales Invoice ID", "Customer Name",
		        "Product Name", "Total Invoice Amount", "Invoice Date","Payment Status"
		    );
		    
			List<Map<String,Object>> salesinvoiceInfoList = salesInvoiceService.getAllSalesInvoiseData();
		    List<Map<String, String>> salesInvoiceList = new ArrayList<>();
		    
		    for (Map<String, Object> salesInvoiceInfo : salesinvoiceInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(salesInvoiceInfo.get("id")));
		        row.put("Sales Invoice ID", String.valueOf(salesInvoiceInfo.get("salesinvoiceuid")));
		        row.put("Customer Name", String.valueOf(salesInvoiceInfo.get("companyname")));
		        row.put("Product Name", String.valueOf(salesInvoiceInfo.get("productname")));
		        row.put("Total Invoice Amount", String.valueOf(salesInvoiceInfo.get("totalinvoiceamount")));
		        row.put("Invoice Date", String.valueOf(salesInvoiceInfo.get("invoicedate")));
		        row.put("Payment Status", String.valueOf(salesInvoiceInfo.get("paymentstatus")));
		        salesInvoiceList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", salesInvoiceList);
		    response.put("status", "success");
		    
		    return response;
		}
		
		@GetMapping("/updatesalesinvoice/{id}")
		public String updateSalesInvoice(@PathVariable("id") Long id,Model model) {
			SalesInvoiceForm si=salesInvoiceService.getSalesInvoiceDataById(id);
			 model.addAttribute("salesinvoice",si);
			return "sales/SalesInvoiceForm";
		}
		
		@PostMapping("/updateexistinginvoice")
		public String updateSalesInvoice(@ModelAttribute SalesInvoiceForm si) {
			salesInvoiceService.updateSalesInvoice(si);
			return "redirect:/sales/salesinvoicegrid";
		}
		
		
		@GetMapping("/deletesalesinvoiceinfo")
		public String deleteSalesInvoice(@RequestParam("id")  Long id) {
			salesInvoiceService.deleteSalesInvoice(id);
			return "redirect:/sales/salesinvoicegrid";
		}
		@GetMapping("/sales/getsaleorderdetails")
		@ResponseBody
		public List<Map<String, Object>> getSalesOrderDetailsByuid(@RequestParam("salesorderuid")String salesorderuid){
			return salesInvoiceService.ggetSalesOrderDetailsByuid(salesorderuid);
		}
}

