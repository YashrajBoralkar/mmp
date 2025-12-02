package com.prog.controller.purchase;


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

import com.prog.model.erp.SalesPraposal;
import com.prog.service.purchase.SalesProposalService;


@Controller
public class SalesProposalController {
	

    @Autowired
    private SalesProposalService spservice;

    
    @GetMapping("/purchase/salesproposalgrid")
    public String showrfigrid() {
    	return "PURCHASEgrid/SalesProposalGrid";
    }
    
     
    @GetMapping("/showsalesproposalform")
    public String showSPform(Model model) {
    	model.addAttribute("sales", new SalesPraposal());
        List<String> clientuid = spservice.getClientId();
        model.addAttribute("clientuid", clientuid);
        return "purchase/SalesProposalForm"; 
    }
    
    
    @PostMapping("/salesproposal/save")
    public String savesp(@ModelAttribute("sales") SalesPraposal sales) {
    	spservice.save(sales);  
        return "redirect:/purchase/salesproposalgrid";  
    }

    
	 // Display list 
	    @GetMapping("/viewallsalesproposalinfo")
		@ResponseBody
		public Map<String, Object> showlistofgoodsreceiptnote() {
		    List<String> headers = List.of(
		        "ID","Sales Proposal ID","Proposal Date", "Client Name", "Price Quotation");
		    
	        List<Map<String,Object>> salesproposalInfoList = spservice.fetchAllData();
		    List<Map<String, String>> salesproposalList = new ArrayList<>();
		    
		    for (Map<String, Object> salesproposalInfo : salesproposalInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(salesproposalInfo.get("id")));
		        row.put("Sales Proposal ID", String.valueOf(salesproposalInfo.get("salesproposaluid")));
		        row.put("Proposal Date", String.valueOf(salesproposalInfo.get("proposaldate")));
		        row.put("Client Name", String.valueOf(salesproposalInfo.get("clientname")));
		        row.put("Price Quotation", String.valueOf(salesproposalInfo.get("pricequotation")));
		        salesproposalList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", salesproposalList);
		    response.put("status", "success");
		    
		    return response;
		}

  
    @GetMapping("/salesproposal/update/{id}")
    public String updatedata(@PathVariable("id") Long id, Model model) {
        SalesPraposal proposals = spservice.GetById(id);  
        model.addAttribute("updatesp", proposals); // Add RMA for updating
        return "purchase/SalesProposalForm";  // Return to the index form page for updating
    }

   
    @PostMapping("/salesproposal/update")
    public String updatesp(@ModelAttribute("sales") SalesPraposal updatesp) {
    	spservice.update(updatesp);  
        return "redirect:/purchase/salesproposalgrid";  
    }
    
    
    @GetMapping("/deletesalesproposalinfo")
    public String deletesp(@RequestParam("id") Long id) {
    	spservice.deleteById(id);
        return "redirect:/purchase/salesproposalgrid"; // After deletion, redirect to the list
    }
    
    
    //FETCHING CLIENT INFO
    
    @GetMapping("/salesproposal/fetchclientDetails")
    @ResponseBody
    public  List<Map<String, Object>> getSalesDetailsByuid(@RequestParam("clientuid") String clientuid) {
        
    	return spservice.getclientDetailsByuid(clientuid);
    }
    
}



