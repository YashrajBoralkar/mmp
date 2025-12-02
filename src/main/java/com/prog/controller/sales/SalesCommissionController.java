package com.prog.controller.sales;

import org.springframework.stereotype.Controller;

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

import com.prog.model.erp.SalesCommission;
import com.prog.service.sales.SalesCommissionService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesCommissionController {
	
	@Autowired
    private SalesCommissionService salesCommissionService;

	
		@GetMapping("/sales/salescommissiongrid")
		public String showsalescommissiongrid() {
			return "SALESgrid/SalesCommissionGrid";
		}
	

	    @GetMapping("/showsalescommissionform")
	    public String showForm( Model model) {
	    	return "sales/SalesCommissionForm";
	    }


	    @PostMapping("/savesalescommissionform")
	    public String saveSales(@ModelAttribute SalesCommission sales, Model model) {
	    	salesCommissionService.saveSales(sales); //  Use instance method instead of static
	    	model.addAttribute("SuccessMsg", "Sales Commission Form saved successfully");
	        return "redirect:/sales/salescommissiongrid";
	    }
	    
	    
	    
	 // Display list of Sales Commission
	    @GetMapping("/viewallsalescommissionlist")
		@ResponseBody
		public Map<String, Object> showlistofsalescommission() {
		    List<String> headers = List.of(
		        "ID", "Sales Commission ID", "Sales Representative Name",
		        "Commission Period","Total sales Amount","Commission Earned", "Approval Status");
		    
    	    List<Map<String, Object>> salescommissionInfoList = salesCommissionService.getAllSales();
		    List<Map<String, String>> salescommissionList = new ArrayList<>();
		    
		    for (Map<String, Object> salescommissionInfo : salescommissionInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(salescommissionInfo.get("id")));
		        row.put("Sales Commission ID", String.valueOf(salescommissionInfo.get("salescommissionuid")));
		        row.put("Sales Representative Name", String.valueOf(salescommissionInfo.get("salesrepname")));
		        row.put("Commission Period", String.valueOf(salescommissionInfo.get("commissionperiod")));
		        row.put("Total sales Amount", String.valueOf(salescommissionInfo.get("totalsales")));
		        row.put("Commission Earned", String.valueOf(salescommissionInfo.get("commissionearned")));
		        row.put("Approval Status", String.valueOf(salescommissionInfo.get("approvalstatus")));
		        salescommissionList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", salescommissionList);
		    response.put("status", "success");
		    
		    return response;
		}
		
	    
	    
	    @GetMapping("/editsalescommissionform/{id}")
	    public String showEditForm(@PathVariable("id") Long id, Model model) {
	    	SalesCommission sale= salesCommissionService.getSalesById(id);
	    	model.addAttribute("sales",sale);
	    	return "sales/SalesCommissionForm";
	    }
	    
	    @PostMapping("/salescommissionform/update")
	    public String updateSales(@ModelAttribute SalesCommission sales) {
	    salesCommissionService.updateSales(sales);
	        return "redirect:/sales/salescommissiongrid";
	    }
	    
	    @GetMapping("/deletesalescommissioninfo")
	    public String deleteSales(@RequestParam("id") Long id) {
	    	salesCommissionService.deleteSalesById(id);
	        return "redirect:/sales/salescommissiongrid";
	    }
	    

	

}
