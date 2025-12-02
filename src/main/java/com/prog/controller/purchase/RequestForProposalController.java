package com.prog.controller.purchase;
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


import java.util.ArrayList;
import java.util.HashMap;

import com.prog.model.erp.RequestForProposal;
import com.prog.service.purchase.RequestForProposalService;
import com.prog.service.supplier.RawMaterialSupplierRegistrationService;

@Controller
public class RequestForProposalController {

	@Autowired
	private RequestForProposalService requestForProposalService;
	@Autowired
	private RawMaterialSupplierRegistrationService supplierservice;
	
	 @GetMapping("/purchase/requestforproposalgrid")
	    public String showRFPGrid() {
	        return "PURCHASEGrid/RequestForProposalGrid";
	    }
		
	@GetMapping("/showrequestforproposalform")
	public String getRfpForm(Model model) {
		model.addAttribute("rfp",new RequestForProposal());
		List<String> rawmaterialsupplieruid = supplierservice.getallsupplieruids();
        model.addAttribute("supplieruid", rawmaterialsupplieruid);
        return "purchase/RequestForProposalForm";
	}
	
	@PostMapping("/requestforproposal/save")
	public String addRFPFormData(@ModelAttribute RequestForProposal rfp, Model model) {
	    requestForProposalService.addRFPFormData(rfp);
	    // Store success message
	    model.addAttribute("message","Data saved successfully!");
	    return "redirect:/purchase/requestforproposalgrid"; // Ensure fresh data after saving
	}

	
	// Display list 
    @GetMapping("/viewallrequestforproposalinfo")
	@ResponseBody
	public Map<String, Object> showlistofgoodsreceiptnote() {
	    List<String> headers = List.of(
	        "ID","RFP ID","Request for Proposal Issue Date","Submission Deadline","Supplier Name", "Proposed Cost ($)");
	    
		List<Map<String,Object>> requestforproposalInfoList=requestForProposalService.getAllRFPFormData();
	    List<Map<String, String>> requestforproposalList = new ArrayList<>();
	    
	    for (Map<String, Object> requestforproposalInfo : requestforproposalInfoList) {
	        Map<String, String> row = new HashMap<>();
	        row.put("ID", String.valueOf(requestforproposalInfo.get("id")));
	        row.put("RFP ID", String.valueOf(requestforproposalInfo.get("requestforproposaluid")));
	        row.put("Request for Proposal Issue Date", String.valueOf(requestforproposalInfo.get("rfpissuedate")));
	        row.put("Submission Deadline", String.valueOf(requestforproposalInfo.get("submissiondeadline")));
	        row.put("Supplier Name", String.valueOf(requestforproposalInfo.get("suppliername")));
	        row.put("Proposed Cost ($)", String.valueOf(requestforproposalInfo.get("proposedcost")));
	        requestforproposalList.add(row);
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("headers", headers);
	    response.put("data", requestforproposalList);
	    response.put("status", "success");
	    
	    return response;
	}

	
	@GetMapping("/requestforproposal/update/{id}")
	public String getRFPFormDataById(@PathVariable("id") Long id, Model model) {
		RequestForProposal rfp=requestForProposalService.getRFPFormDataById(id);
		model.addAttribute("rfpdata", rfp);
		return "purchase/RequestForProposalForm";
	}
	
	@PostMapping("/requestforproposal/update")
	public String updateRFPFormById(@ModelAttribute RequestForProposal rfp) {
		 requestForProposalService.updataRFPFormData(rfp);
		 return "redirect:/purchase/requestforproposalgrid";
	}
	
	
	@GetMapping("/deleterequestforproposalinfo")
	public String deleteRFPFormData(@RequestParam("id") Long id) {
		requestForProposalService.deleteRFPFormById(id);
		return "redirect:/purchase/requestforproposalgrid";
	}
	
	//FETCHING
    @GetMapping("/requestforproposal/getsupplieruidinfo")
    @ResponseBody 
    public List<Map<String, Object>> getSupplierUidInfo(@RequestParam("supplieruid") String rawmaterialsupplieruid) {
        if (rawmaterialsupplieruid == null || rawmaterialsupplieruid.isEmpty()) {
            throw new IllegalArgumentException("supplieruid must not be null or empty.");
        }
        return supplierservice.getSupplierDetailsByUids(rawmaterialsupplieruid);
    }
	
}
