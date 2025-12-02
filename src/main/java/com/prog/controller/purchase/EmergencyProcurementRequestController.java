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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.model.erp.EmergencyProcurementRequest;
import com.prog.model.erp.PurchasedAndSalesAgreement;
import com.prog.service.purchase.EmergencyProcurementRequestService;


@Controller
public class EmergencyProcurementRequestController {
	@Autowired
	private EmergencyProcurementRequestService emergencyProcurementRequestService;
	


	@GetMapping("/purchase/emergencyprocurementrequestgrid")
    public String showpasagrid() {
    	return "PURCHASEgrid/EmergencyProcurementRequestGrid";
    }
	
    //Displays the Emergency Procurement Request form.
	 
	@GetMapping("/showemergencyprocurementrequestform")
	public String show_epr_form(Model model) 
	{
		model.addAttribute("eprf", new EmergencyProcurementRequest());
		return "purchase/EmergencyProcurementRequestForm";
	}

	
	  //Saves the submitted Emergency Procurement Request form to the database.
	 
	@PostMapping("/emergencyprocurementrequest/save")
	public String saved_epr_form(@ModelAttribute EmergencyProcurementRequest eprf, Model model) 
	{
		emergencyProcurementRequestService.epr_save_backend(eprf);
		model.addAttribute("message", "EPRFORM SAVED SUCCESSFULLY");
		return "redirect:/purchase/emergencyprocurementrequestgrid";
	}

	// Display list 
	    @GetMapping("/viewallemergencyprocurementrequesttinfo")
		@ResponseBody
		public Map<String, Object> showlistofpurchasedandsalesagreement() {
		    List<String> headers = List.of(
		        "ID","EPRF ID","Request Date","Reason For Emergency","Approval Status");
		    
			List<Map<String, Object>> emergencyProcurementRequestInfoList = emergencyProcurementRequestService.show_eprf_list();
		    List<Map<String, String>> emergencyProcurementRequestList = new ArrayList<>();
		    
		    for (Map<String, Object> emergencyProcurementRequestInfo : emergencyProcurementRequestInfoList) {
		        Map<String, String> row = new HashMap<>();
		        row.put("ID", String.valueOf(emergencyProcurementRequestInfo.get("id")));
		        row.put("EPRF ID", String.valueOf(emergencyProcurementRequestInfo.get("emergencyprocurementrequestuid")));
		        row.put("Request Date", String.valueOf(emergencyProcurementRequestInfo.get("requestdate")));
		        row.put("Reason For Emergency", String.valueOf(emergencyProcurementRequestInfo.get("reasonforemergency")));
		        row.put("Approval Status", String.valueOf(emergencyProcurementRequestInfo.get("approvalstatus")));
		        emergencyProcurementRequestList.add(row);
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("headers", headers);
		    response.put("data", emergencyProcurementRequestList);
		    response.put("status", "success");
		    
		    return response;
		}
		

		@GetMapping("/emergencyprocurementrequest/update/{id}")
		public String updatepsaentrybyid(@PathVariable Long id, Model model) 
		{
			EmergencyProcurementRequest EmergencyProcurementRequest = emergencyProcurementRequestService.geteeprbyid(id);
			model.addAttribute("eprf", EmergencyProcurementRequest);
			return "purchase/EmergencyProcurementRequestForm";
		}
		

		@PostMapping("/emergencyprocurementrequest/update")
		public String update_epr(@ModelAttribute EmergencyProcurementRequest eprf, RedirectAttributes redirectAttributes) {
			try {
				emergencyProcurementRequestService.updateepr(eprf);
				redirectAttributes.addFlashAttribute("message", "EPRF updated successfully!");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating.");
			}
			return "redirect:/purchase/emergencyprocurementrequestgrid";
		}
		
		//Deletes a specific PSA entry based on the provided ID.
		 @GetMapping("/deleteemergencyprocurementrequesttinfo")
		public String deleteeprfentrybyid(@RequestParam("id") Long id, Model model) {
			 emergencyProcurementRequestService.deleteeprf(id);
			return "redirect:/purchase/emergencyprocurementrequestgrid";
		}
		

	 
	 
}
